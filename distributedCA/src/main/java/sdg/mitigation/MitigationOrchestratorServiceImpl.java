package sdg.mitigation;

import generated.grpc.mitigation.CancelReply;
import generated.grpc.mitigation.CancelRequest;
import generated.grpc.mitigation.ControlCommand;
import generated.grpc.mitigation.ControlEvent;
import generated.grpc.mitigation.MitigationOrchestratorServiceGrpc;
import generated.grpc.mitigation.StrategyStatus;
import generated.grpc.mitigation.TargetService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MitigationOrchestratorServiceImpl extends MitigationOrchestratorServiceGrpc.MitigationOrchestratorServiceImplBase {
    // If one strategy is cancelled, I keep its id here.
    // Then if the same id comes again, the server can return FAILED as described in the proposal.
    private final Set<String> cancelledStrategies = ConcurrentHashMap.newKeySet();

    @Override
    public StreamObserver<ControlCommand> runControlLoop(StreamObserver<ControlEvent> responseObserver) {
        return new StreamObserver<>() {
            @Override
            public void onNext(ControlCommand command) {
                validateCommand(command);

                if (cancelledStrategies.contains(command.getStrategyId())) {
                    responseObserver.onNext(event(command, StrategyStatus.FAILED, Map.of("target", command.getTarget().name()),
                            "Cancelled strategy cannot be run again"));
                    return;
                }

                // These three events are used to show a simple control loop in the GUI.
                responseObserver.onNext(event(command, StrategyStatus.ACCEPTED,
                        Map.of("target", command.getTarget().name(), "step", "queued"), ""));
                responseObserver.onNext(event(command, StrategyStatus.RUNNING,
                        Map.of("zone", command.getParamsOrDefault("zone", "unknown"), "mode", strategyMode(command)), ""));
                responseObserver.onNext(event(command, StrategyStatus.DONE,
                        Map.of("outcome", outcomeMessage(command), "target", command.getTarget().name()), ""));
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public void cancelStrategy(CancelRequest request, StreamObserver<CancelReply> responseObserver) {
        if (request.getStrategyId().isBlank()) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("strategy_id is required")
                    .asRuntimeException());
            return;
        }

        cancelledStrategies.add(request.getStrategyId());
        responseObserver.onNext(CancelReply.newBuilder()
                .setOk(true)
                .setMessage("Strategy " + request.getStrategyId() + " is cancelled")
                .build());
        responseObserver.onCompleted();
    }

    private void validateCommand(ControlCommand command) {
        if (command.getStrategyId().isBlank()) {
            throw Status.INVALID_ARGUMENT.withDescription("strategy_id is required").asRuntimeException();
        }
        if (command.getTarget() == TargetService.TARGET_SERVICE_UNSPECIFIED) {
            throw Status.INVALID_ARGUMENT.withDescription("target must be specified").asRuntimeException();
        }
        if (!command.containsParams("zone")) {
            throw Status.INVALID_ARGUMENT.withDescription("params.zone is required").asRuntimeException();
        }
    }

    private ControlEvent event(ControlCommand command, StrategyStatus status, Map<String, String> telemetry, String error) {
        return ControlEvent.newBuilder()
                .setStrategyId(command.getStrategyId())
                .setStatus(status)
                .putAllTelemetry(telemetry)
                .setErrorDetail(error)
                .setEpochMs(System.currentTimeMillis())
                .build();
    }

    private String strategyMode(ControlCommand command) {
        return command.getParamsOrDefault("mode", switch (command.getTarget()) {
            case HVAC -> "cooling-reduction";
            case EV -> "off-peak-charging";
            case ALERT -> "broadcast-warning";
            default -> "standard";
        });
    }

    private String outcomeMessage(ControlCommand command) {
        return switch (command.getTarget()) {
            case HVAC -> "HVAC load reduced by 18% in " + command.getParamsOrDefault("zone", "unknown");
            case EV -> "EV charging throttled for peak avoidance";
            case ALERT -> "Alert workflow dispatched to campus safety";
            default -> "Completed";
        };
    }
}
