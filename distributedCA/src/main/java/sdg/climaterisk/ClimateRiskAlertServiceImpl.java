package sdg.climaterisk;

import generated.grpc.climaterisk.AlertSubscription;
import generated.grpc.climaterisk.ClimateRiskAlertServiceGrpc;
import generated.grpc.climaterisk.RiskAlert;
import generated.grpc.climaterisk.RiskRequest;
import generated.grpc.climaterisk.RiskState;
import generated.grpc.climaterisk.RiskType;
import io.grpc.Context;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ClimateRiskAlertServiceImpl extends ClimateRiskAlertServiceGrpc.ClimateRiskAlertServiceImplBase {
    // Same zone list as the emission service, so the whole campus scenario stays consistent.
    private static final Set<String> VALID_ZONES = Set.of("library", "science-lab", "gym", "dorm-a", "canteen");

    @Override
    public void getCurrentRisk(RiskRequest request, StreamObserver<RiskState> responseObserver) {
        if (!VALID_ZONES.contains(request.getZoneId())) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("Unknown zone_id: " + request.getZoneId())
                    .asRuntimeException());
            return;
        }

        responseObserver.onNext(RiskState.newBuilder()
                .setZoneId(request.getZoneId())
                .addAllCurrent(currentAlertsFor(request.getZoneId()))
                .setEpochMs(System.currentTimeMillis())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void subscribeAlerts(AlertSubscription request, StreamObserver<RiskAlert> responseObserver) {
        if (!VALID_ZONES.contains(request.getZoneId())) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("Unknown zone_id: " + request.getZoneId())
                    .asRuntimeException());
            return;
        }
        if (request.getIntervalSeconds() <= 0 || request.getIntervalSeconds() > 10) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("interval_seconds must be between 1 and 10")
                    .asRuntimeException());
            return;
        }

        Context.CancellableContext context = Context.current().withCancellation();
        context.run(() -> {
            List<RiskAlert> alerts = rotatingAlertsFor(request.getZoneId());
            try {
                // I send several alerts one by one to simulate the real-time warning feeling.
                for (RiskAlert alert : alerts) {
                    if (Context.current().isCancelled()) {
                        return;
                    }
                    boolean typeMatches = request.getTypesCount() == 0 || request.getTypesList().contains(alert.getType());
                    boolean severityMatches = alert.getSeverity() >= request.getMinSeverity();
                    if (typeMatches && severityMatches) {
                        responseObserver.onNext(alert);
                    }
                    Thread.sleep(request.getIntervalSeconds() * 1000L);
                }
                responseObserver.onCompleted();
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
                responseObserver.onCompleted();
            }
        });
    }

    private List<RiskAlert> currentAlertsFor(String zoneId) {
        // This part gives the current state, so I use a smaller fixed set of alerts.
        return switch (zoneId) {
            case "science-lab" -> List.of(buildAlert(RiskType.HEAT, 4, "Science lab temperature is rising quickly.",
                    List.of("Lower HVAC set point", "Move practical sessions if needed")));
            case "gym" -> List.of(buildAlert(RiskType.WIND, 2, "Wind gust warning around the sports hall.",
                    List.of("Secure outdoor equipment", "Monitor roof access")));
            case "canteen" -> List.of(buildAlert(RiskType.FLOOD, 3, "Drainage sensors report minor overflow risk.",
                    List.of("Inspect drain covers", "Prepare sandbags near delivery bay")));
            default -> List.of(buildAlert(RiskType.HEAT, 1, "No major climate alert. Routine monitoring only.",
                    List.of("Continue monitoring", "Keep evacuation plan visible")));
        };
    }

    private List<RiskAlert> rotatingAlertsFor(String zoneId) {
        // Streaming version adds extra alerts so the client can see the server push continuously.
        List<RiskAlert> alerts = new ArrayList<>(currentAlertsFor(zoneId));
        alerts.add(buildAlert(RiskType.FLOOD, 4, "Rain intensity is increasing near " + zoneId + ".",
                List.of("Protect low-level equipment", "Check drainage sensors")));
        alerts.add(buildAlert(RiskType.WIND, 3, "Cross-campus wind advisory for " + zoneId + ".",
                List.of("Restrict rooftop access", "Secure outdoor signage")));
        return alerts;
    }

    private RiskAlert buildAlert(RiskType type, int severity, String message, List<String> actions) {
        return RiskAlert.newBuilder()
                .setType(type)
                .setSeverity(severity)
                .setMessage(message)
                .addAllRecommendedActions(actions)
                .setEpochMs(System.currentTimeMillis())
                .build();
    }
}
