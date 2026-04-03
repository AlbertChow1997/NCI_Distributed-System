package sdg.client;

import generated.grpc.climaterisk.AlertSubscription;
import generated.grpc.climaterisk.ClimateRiskAlertServiceGrpc;
import generated.grpc.climaterisk.RiskAlert;
import generated.grpc.climaterisk.RiskRequest;
import generated.grpc.climaterisk.RiskState;
import generated.grpc.climaterisk.RiskType;
import generated.grpc.emission.BatchSummary;
import generated.grpc.emission.CarbonSnapshot;
import generated.grpc.emission.EmissionAccountingServiceGrpc;
import generated.grpc.emission.SnapshotRequest;
import generated.grpc.emission.SourceType;
import generated.grpc.emission.UsageRecord;
import generated.grpc.mitigation.CancelReply;
import generated.grpc.mitigation.CancelRequest;
import generated.grpc.mitigation.ControlCommand;
import generated.grpc.mitigation.ControlEvent;
import generated.grpc.mitigation.MitigationOrchestratorServiceGrpc;
import generated.grpc.mitigation.TargetService;
import generated.grpc.naming.DiscoverReply;
import generated.grpc.naming.DiscoverRequest;
import generated.grpc.naming.NamingServiceGrpc;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import sdg.common.AuthClientInterceptor;
import sdg.common.GrpcChannels;
import sdg.common.NamedChannelProvider;
import sdg.common.ServiceDirectory;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SmartClimateControllerFrame extends JFrame {
    // This GUI is the main controller of the project.
    // It first discovers services from Naming Service, then calls each business service.
    private final NamedChannelProvider channelProvider = new NamedChannelProvider();
    private final JTextArea discoveryOutput = buildOutput();
    private final JTextArea emissionOutput = buildOutput();
    private final JTextArea riskOutput = buildOutput();
    private final JTextArea mitigationOutput = buildOutput();

    private final JComboBox<String> emissionZone = new JComboBox<>(new String[]{"library", "science-lab", "gym", "dorm-a", "canteen"});
    private final JTextField emissionWindow = new JTextField("60");

    private final JComboBox<String> riskZone = new JComboBox<>(new String[]{"library", "science-lab", "gym", "dorm-a", "canteen"});
    private final JComboBox<RiskType> riskType = new JComboBox<>(new RiskType[]{RiskType.HEAT, RiskType.FLOOD, RiskType.WIND});
    private final JTextField riskSeverity = new JTextField("2");
    private final JTextField riskInterval = new JTextField("1");

    private final JTextField strategyId = new JTextField("hvac-001");
    private final JComboBox<String> mitigationZone = new JComboBox<>(new String[]{"library", "science-lab", "gym", "dorm-a", "canteen"});
    private final JComboBox<TargetService> mitigationTarget = new JComboBox<>(new TargetService[]{TargetService.HVAC, TargetService.EV, TargetService.ALERT});
    private final JTextField mitigationMode = new JTextField("peak-reduction");

    private volatile ManagedChannel alertSubscriptionChannel;
    private volatile StreamObserver<ControlCommand> mitigationCommandStream;
    private volatile Channel mitigationChannel;
    private volatile ManagedChannel mitigationTransportChannel;

    public SmartClimateControllerFrame() {
        setTitle("SDG Smart Climate Action Controller");
        setSize(1150, 820);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2, 12, 12));

        add(discoveryPanel());
        add(emissionPanel());
        add(riskPanel());
        add(mitigationPanel());
    }

    private JPanel discoveryPanel() {
        JPanel panel = panel("Naming Service Discovery");
        JPanel actions = new JPanel(new GridLayout(0, 1, 6, 6));
        actions.add(button("Discover all services", this::discoverServices));
        panel.add(actions, BorderLayout.NORTH);
        panel.add(new JScrollPane(discoveryOutput), BorderLayout.CENTER);
        return panel;
    }

    private JPanel emissionPanel() {
        JPanel panel = panel("Emission Accounting");
        JPanel form = new JPanel(new GridLayout(0, 2, 6, 6));
        form.add(new JLabel("Zone"));
        form.add(emissionZone);
        form.add(new JLabel("Window minutes"));
        form.add(emissionWindow);
        form.add(button("Get Carbon Snapshot", this::getCarbonSnapshot));
        form.add(button("Upload Sample Batch", this::uploadBatch));
        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(emissionOutput), BorderLayout.CENTER);
        return panel;
    }

    private JPanel riskPanel() {
        JPanel panel = panel("Climate Risk Alerts");
        JPanel form = new JPanel(new GridLayout(0, 2, 6, 6));
        form.add(new JLabel("Zone"));
        form.add(riskZone);
        form.add(new JLabel("Risk type"));
        form.add(riskType);
        form.add(new JLabel("Min severity"));
        form.add(riskSeverity);
        form.add(new JLabel("Stream interval seconds"));
        form.add(riskInterval);
        form.add(button("Get Current Risk", this::getCurrentRisk));
        form.add(button("Subscribe Alerts", this::subscribeAlerts));
        form.add(button("Cancel Alert Stream", this::cancelAlertSubscription));
        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(riskOutput), BorderLayout.CENTER);
        return panel;
    }

    private JPanel mitigationPanel() {
        JPanel panel = panel("Mitigation Orchestrator");
        JPanel form = new JPanel(new GridLayout(0, 2, 6, 6));
        form.add(new JLabel("Strategy id"));
        form.add(strategyId);
        form.add(new JLabel("Zone"));
        form.add(mitigationZone);
        form.add(new JLabel("Target"));
        form.add(mitigationTarget);
        form.add(new JLabel("Mode"));
        form.add(mitigationMode);
        form.add(button("Open Control Stream", this::openMitigationStream));
        form.add(button("Send Command", this::sendMitigationCommand));
        form.add(button("Cancel Strategy", this::cancelStrategy));
        form.add(button("Close Stream", this::closeMitigationStream));
        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(mitigationOutput), BorderLayout.CENTER);
        return panel;
    }

    private JPanel panel(String title) {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createTitledBorder(title));
        return panel;
    }

    private JTextArea buildOutput() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        return area;
    }

    private JButton button(String text, Runnable action) {
        JButton button = new JButton(text);
        button.addActionListener(e -> action.run());
        return button;
    }

    private void discoverServices() {
        runAsync(() -> {
            ManagedChannel channel = GrpcChannels.build(ServiceDirectory.HOST, ServiceDirectory.NAMING_PORT);
            try {
                NamingServiceGrpc.NamingServiceBlockingStub stub = NamingServiceGrpc.newBlockingStub(channel)
                        .withDeadlineAfter(2, TimeUnit.SECONDS);
                append(discoveryOutput, "Service discovery at " + System.currentTimeMillis());
                for (String serviceName : List.of(
                        ServiceDirectory.EMISSION_SERVICE,
                        ServiceDirectory.CLIMATE_RISK_SERVICE,
                        ServiceDirectory.MITIGATION_SERVICE
                )) {
                    DiscoverReply reply = stub.discover(DiscoverRequest.newBuilder().setServiceName(serviceName).build());
                    append(discoveryOutput, serviceName + " -> " + reply.getEndpointsList());
                }
            } catch (Exception ex) {
                append(discoveryOutput, friendlyError(ex));
            } finally {
                channel.shutdownNow();
            }
        });
    }

    private void getCarbonSnapshot() {
        runAsync(() -> {
            ManagedChannel channel = channelProvider.channelFor(ServiceDirectory.EMISSION_SERVICE);
            try {
                EmissionAccountingServiceGrpc.EmissionAccountingServiceBlockingStub stub =
                        EmissionAccountingServiceGrpc.newBlockingStub(channel).withDeadlineAfter(2, TimeUnit.SECONDS);
                CarbonSnapshot snapshot = stub.getCarbonSnapshot(SnapshotRequest.newBuilder()
                        .setZoneId(emissionZone.getSelectedItem().toString())
                        .setWindowMinutes(Integer.parseInt(emissionWindow.getText().trim()))
                        .build());
                append(emissionOutput, "Snapshot -> kWh=" + snapshot.getCurrentKwh() + ", kgCO2e=" + snapshot.getKgCo2E()
                        + ", peakKW=" + snapshot.getPeakDemandKw() + ", tips=" + snapshot.getTipsList());
            } catch (Exception ex) {
                append(emissionOutput, friendlyError(ex));
            } finally {
                channel.shutdownNow();
            }
        });
    }

    private void uploadBatch() {
        runAsync(() -> {
            ManagedChannel channel = channelProvider.channelFor(ServiceDirectory.EMISSION_SERVICE);
            CountDownLatch latch = new CountDownLatch(1);
            try {
                EmissionAccountingServiceGrpc.EmissionAccountingServiceStub stub =
                        EmissionAccountingServiceGrpc.newStub(channel);
                StreamObserver<UsageRecord> requestObserver = stub.uploadUsageBatch(new StreamObserver<>() {
                    @Override
                    public void onNext(BatchSummary batchSummary) {
                        append(emissionOutput, "Batch summary -> records=" + batchSummary.getRecordCount()
                                + ", totalKWh=" + batchSummary.getTotalKwh()
                                + ", totalKgCO2e=" + batchSummary.getTotalKgCo2E()
                                + ", anomalies=" + batchSummary.getAnomaliesList());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        append(emissionOutput, friendlyError(throwable));
                        latch.countDown();
                    }

                    @Override
                    public void onCompleted() {
                        latch.countDown();
                    }
                });

                long now = System.currentTimeMillis();
                // The third line is intentionally not valid, so the anomaly handling can be shown in demo.
                requestObserver.onNext(usage("meter-demo-01", now - 300_000L, 3.4, SourceType.GRID));
                requestObserver.onNext(usage("solar-demo-02", now - 120_000L, 1.2, SourceType.SOLAR));
                requestObserver.onNext(usage("", now - 90_000L, 0.8, SourceType.WIND));
                requestObserver.onCompleted();
                latch.await(3, TimeUnit.SECONDS);
            } catch (Exception ex) {
                append(emissionOutput, friendlyError(ex));
            } finally {
                channel.shutdownNow();
            }
        });
    }

    private UsageRecord usage(String deviceId, long timestamp, double kwh, SourceType sourceType) {
        return UsageRecord.newBuilder()
                .setDeviceId(deviceId)
                .setTimestampEpochMs(timestamp)
                .setKwh(kwh)
                .setSourceType(sourceType)
                .setZoneId(emissionZone.getSelectedItem().toString())
                .build();
    }

    private void getCurrentRisk() {
        runAsync(() -> {
            ManagedChannel channel = channelProvider.channelFor(ServiceDirectory.CLIMATE_RISK_SERVICE);
            try {
                ClimateRiskAlertServiceGrpc.ClimateRiskAlertServiceBlockingStub stub =
                        ClimateRiskAlertServiceGrpc.newBlockingStub(channel).withDeadlineAfter(2, TimeUnit.SECONDS);
                RiskState riskState = stub.getCurrentRisk(RiskRequest.newBuilder()
                        .setZoneId(riskZone.getSelectedItem().toString())
                        .build());
                append(riskOutput, "Current risk -> " + riskState.getCurrentList());
            } catch (Exception ex) {
                append(riskOutput, friendlyError(ex));
            } finally {
                channel.shutdownNow();
            }
        });
    }

    private void subscribeAlerts() {
        cancelAlertSubscription();
        runAsync(() -> {
            ManagedChannel channel = channelProvider.channelFor(ServiceDirectory.CLIMATE_RISK_SERVICE);
            alertSubscriptionChannel = channel;
            try {
                // Blocking stub is enough here because I already run it in a background thread.
                ClimateRiskAlertServiceGrpc.ClimateRiskAlertServiceBlockingStub stub =
                        ClimateRiskAlertServiceGrpc.newBlockingStub(channel);
                var iterator = stub.subscribeAlerts(AlertSubscription.newBuilder()
                        .setZoneId(riskZone.getSelectedItem().toString())
                        .addTypes((RiskType) riskType.getSelectedItem())
                        .setMinSeverity(Integer.parseInt(riskSeverity.getText().trim()))
                        .setIntervalSeconds(Integer.parseInt(riskInterval.getText().trim()))
                        .build());
                while (iterator.hasNext()) {
                    RiskAlert riskAlert = iterator.next();
                    append(riskOutput, "Alert -> " + riskAlert.getType() + " severity=" + riskAlert.getSeverity()
                            + " message=" + riskAlert.getMessage()
                            + " actions=" + riskAlert.getRecommendedActionsList());
                }
                append(riskOutput, "Alert stream completed.");
            } catch (Exception ex) {
                append(riskOutput, friendlyError(ex));
            } finally {
                channel.shutdownNow();
                if (alertSubscriptionChannel == channel) {
                    alertSubscriptionChannel = null;
                }
            }
        });
    }

    private void cancelAlertSubscription() {
        ManagedChannel channel = alertSubscriptionChannel;
        if (channel != null) {
            channel.shutdownNow();
            alertSubscriptionChannel = null;
            append(riskOutput, "Alert subscription cancelled by user.");
        }
    }

    private void openMitigationStream() {
        if (mitigationCommandStream != null) {
            append(mitigationOutput, "Mitigation stream is already open.");
            return;
        }

        runAsync(() -> {
            mitigationTransportChannel = channelProvider.channelFor(ServiceDirectory.MITIGATION_SERVICE);
            // Mitigation service is the protected one, so metadata auth is added here.
            mitigationChannel = io.grpc.ClientInterceptors.intercept(
                    mitigationTransportChannel,
                    new AuthClientInterceptor()
            );
            MitigationOrchestratorServiceGrpc.MitigationOrchestratorServiceStub stub =
                    MitigationOrchestratorServiceGrpc.newStub(mitigationChannel);
            mitigationCommandStream = stub.runControlLoop(new StreamObserver<>() {
                @Override
                public void onNext(ControlEvent controlEvent) {
                    append(mitigationOutput, "Control event -> strategy=" + controlEvent.getStrategyId()
                            + ", status=" + controlEvent.getStatus()
                            + ", telemetry=" + controlEvent.getTelemetryMap()
                            + (controlEvent.getErrorDetail().isBlank() ? "" : ", error=" + controlEvent.getErrorDetail()));
                }

                @Override
                public void onError(Throwable throwable) {
                    append(mitigationOutput, friendlyError(throwable));
                    mitigationCommandStream = null;
                    shutdownMitigationChannel();
                }

                @Override
                public void onCompleted() {
                    append(mitigationOutput, "Mitigation control stream completed.");
                    mitigationCommandStream = null;
                    shutdownMitigationChannel();
                }
            });
            append(mitigationOutput, "Mitigation stream opened with API key authentication.");
        });
    }

    private void sendMitigationCommand() {
        if (mitigationCommandStream == null) {
            append(mitigationOutput, "Open the mitigation stream first.");
            return;
        }

        mitigationCommandStream.onNext(ControlCommand.newBuilder()
                .setStrategyId(strategyId.getText().trim())
                .setTarget((TargetService) mitigationTarget.getSelectedItem())
                .putParams("zone", mitigationZone.getSelectedItem().toString())
                .putParams("mode", mitigationMode.getText().trim())
                .build());
    }

    private void cancelStrategy() {
        runAsync(() -> {
            ManagedChannel transport = channelProvider.channelFor(ServiceDirectory.MITIGATION_SERVICE);
            Channel rawChannel = io.grpc.ClientInterceptors.intercept(
                    transport,
                    new AuthClientInterceptor()
            );
            try {
                MitigationOrchestratorServiceGrpc.MitigationOrchestratorServiceBlockingStub stub =
                        MitigationOrchestratorServiceGrpc.newBlockingStub(rawChannel).withDeadlineAfter(2, TimeUnit.SECONDS);
                CancelReply reply = stub.cancelStrategy(CancelRequest.newBuilder()
                        .setStrategyId(strategyId.getText().trim())
                        .build());
                append(mitigationOutput, "Cancel reply -> " + reply.getMessage());
            } catch (Exception ex) {
                append(mitigationOutput, friendlyError(ex));
            } finally {
                transport.shutdownNow();
            }
        });
    }

    private void closeMitigationStream() {
        if (mitigationCommandStream != null) {
            mitigationCommandStream.onCompleted();
            mitigationCommandStream = null;
        }
        shutdownMitigationChannel();
    }

    private void shutdownMitigationChannel() {
        mitigationChannel = null;
        if (mitigationTransportChannel != null) {
            mitigationTransportChannel.shutdownNow();
            mitigationTransportChannel = null;
        }
    }

    private String friendlyError(Throwable throwable) {
        if (throwable instanceof StatusRuntimeException statusEx) {
            return "RPC error -> " + statusEx.getStatus().getCode() + ": " + statusEx.getStatus().getDescription();
        }
        return "Error -> " + throwable.getMessage();
    }

    private void append(JTextArea area, String text) {
        SwingUtilities.invokeLater(() -> area.append(text + System.lineSeparator()));
    }

    private void runAsync(Runnable action) {
        Thread thread = new Thread(action);
        thread.setDaemon(true);
        thread.start();
    }
}
