package sdg.emission;

import generated.grpc.emission.BatchSummary;
import generated.grpc.emission.CarbonSnapshot;
import generated.grpc.emission.EmissionAccountingServiceGrpc;
import generated.grpc.emission.SnapshotRequest;
import generated.grpc.emission.SourceType;
import generated.grpc.emission.UsageRecord;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class EmissionAccountingServiceImpl extends EmissionAccountingServiceGrpc.EmissionAccountingServiceImplBase {
    // I only use several campus zones here because the GUI also uses the same fixed list.
    private static final Set<String> VALID_ZONES = Set.of("library", "science-lab", "gym", "dorm-a", "canteen");
    private static final Map<SourceType, Double> CO2_FACTORS = Map.of(
            SourceType.GRID, 0.42,
            SourceType.SOLAR, 0.05,
            SourceType.WIND, 0.02,
            SourceType.SOURCE_TYPE_UNSPECIFIED, 0.50
    );

    private final Map<String, List<UsageRecord>> usageByZone = new ConcurrentHashMap<>();

    public EmissionAccountingServiceImpl() {
        seedUsage();
    }

    @Override
    public void getCarbonSnapshot(SnapshotRequest request, StreamObserver<CarbonSnapshot> responseObserver) {
        validateSnapshotRequest(request);

        List<UsageRecord> records = usageByZone.getOrDefault(request.getZoneId(), List.of());
        long cutoff = Instant.now().minusSeconds(request.getWindowMinutes() * 60L).toEpochMilli();

        // First calculate the usage in the required time window.
        double totalKwh = records.stream()
                .filter(record -> record.getTimestampEpochMs() >= cutoff)
                .mapToDouble(UsageRecord::getKwh)
                .sum();

        if (totalKwh == 0) {
            // If there is no fresh uploaded data, I still return a simulated value.
            totalKwh = baselineFor(request.getZoneId(), request.getWindowMinutes());
        }

        double totalCo2 = records.stream()
                .filter(record -> record.getTimestampEpochMs() >= cutoff)
                .mapToDouble(record -> record.getKwh() * CO2_FACTORS.getOrDefault(record.getSourceType(), 0.50))
                .sum();
        if (totalCo2 == 0) {
            // This part is a fallback estimation, it makes the demo easier to show in the GUI.
            totalCo2 = totalKwh * 0.28;
        }

        responseObserver.onNext(CarbonSnapshot.newBuilder()
                .setCurrentKwh(round(totalKwh))
                .setKgCo2E(round(totalCo2))
                .setCo2EFactor(round(totalCo2 / totalKwh))
                .setPeakDemandKw(round(Math.max(8.0, totalKwh / Math.max(1, request.getWindowMinutes() / 15.0))))
                .addAllTips(buildTips(request.getZoneId(), totalKwh))
                .setGeneratedAtEpochMs(System.currentTimeMillis())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<UsageRecord> uploadUsageBatch(StreamObserver<BatchSummary> responseObserver) {
        List<UsageRecord> accepted = new ArrayList<>();
        List<String> anomalies = new ArrayList<>();

        return new StreamObserver<>() {
            @Override
            public void onNext(UsageRecord record) {
                // I do not reject the whole batch if one line is bad.
                // It is better for demo because the client can still get a summary with anomaly messages.
                if (record.getDeviceId().isBlank()) {
                    anomalies.add("Skipped record with empty device_id");
                    return;
                }
                if (!VALID_ZONES.contains(record.getZoneId())) {
                    anomalies.add("Skipped record for unknown zone: " + record.getZoneId());
                    return;
                }
                if (record.getKwh() <= 0) {
                    anomalies.add("Skipped non-positive kWh for " + record.getDeviceId());
                    return;
                }
                accepted.add(record);
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onCompleted() {
                accepted.forEach(record -> usageByZone
                        .computeIfAbsent(record.getZoneId(), ignored -> new ArrayList<>())
                        .add(record));

                double totalKwh = accepted.stream().mapToDouble(UsageRecord::getKwh).sum();
                double totalCo2 = accepted.stream()
                        .mapToDouble(record -> record.getKwh() * CO2_FACTORS.getOrDefault(record.getSourceType(), 0.50))
                        .sum();

                responseObserver.onNext(BatchSummary.newBuilder()
                        .setRecordCount(accepted.size())
                        .setTotalKwh(round(totalKwh))
                        .setTotalKgCo2E(round(totalCo2))
                        .addAllAnomalies(anomalies)
                        .build());
                responseObserver.onCompleted();
            }
        };
    }

    private void validateSnapshotRequest(SnapshotRequest request) {
        if (!VALID_ZONES.contains(request.getZoneId())) {
            throw Status.INVALID_ARGUMENT.withDescription("Unknown zone_id: " + request.getZoneId()).asRuntimeException();
        }
        if (request.getWindowMinutes() <= 0 || request.getWindowMinutes() > 1440) {
            throw Status.INVALID_ARGUMENT.withDescription("window_minutes must be between 1 and 1440").asRuntimeException();
        }
    }

    private double baselineFor(String zoneId, int windowMinutes) {
        double zoneBase = switch (zoneId) {
            case "science-lab" -> 22.5;
            case "gym" -> 18.0;
            case "canteen" -> 15.5;
            case "dorm-a" -> 11.0;
            default -> 9.5;
        };
        return zoneBase * Math.max(1, windowMinutes / 60.0);
    }

    private List<String> buildTips(String zoneId, double totalKwh) {
        List<String> tips = new ArrayList<>();
        if (totalKwh > 30) {
            tips.add("Reduce HVAC usage during low occupancy periods in " + zoneId + ".");
            tips.add("Shift EV charging or heavy loads to lower-demand windows.");
        } else {
            tips.add("Current consumption is stable; keep monitoring device scheduling.");
            tips.add("Prefer solar-backed loads where possible.");
        }
        return tips;
    }

    private void seedUsage() {
        // Some sample records are added at startup so the unary call has meaningful result even before streaming upload.
        long now = System.currentTimeMillis();
        addSeed("library", "meter-lib-01", now - 15 * 60_000L, 6.4, SourceType.GRID);
        addSeed("science-lab", "meter-lab-02", now - 22 * 60_000L, 11.8, SourceType.GRID);
        addSeed("science-lab", "roof-solar-02", now - 18 * 60_000L, 4.3, SourceType.SOLAR);
        addSeed("gym", "meter-gym-04", now - 27 * 60_000L, 9.1, SourceType.GRID);
        addSeed("canteen", "kitchen-06", now - 12 * 60_000L, 7.5, SourceType.GRID);
    }

    private void addSeed(String zoneId, String deviceId, long timestamp, double kwh, SourceType sourceType) {
        usageByZone.computeIfAbsent(zoneId, ignored -> new ArrayList<>())
                .add(UsageRecord.newBuilder()
                        .setZoneId(zoneId)
                        .setDeviceId(deviceId)
                        .setTimestampEpochMs(timestamp)
                        .setKwh(kwh)
                        .setSourceType(sourceType)
                        .build());
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
