package sdg.naming;

import generated.grpc.naming.DiscoverReply;
import generated.grpc.naming.DiscoverRequest;
import generated.grpc.naming.NamingServiceGrpc;
import generated.grpc.naming.RegisterReply;
import generated.grpc.naming.ServiceEndpoint;
import generated.grpc.naming.ServiceInfo;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.Map;

public class NamingServiceImpl extends NamingServiceGrpc.NamingServiceImplBase {
    private final RegistryStore registryStore;

    public NamingServiceImpl(RegistryStore registryStore) {
        this.registryStore = registryStore;
    }

    @Override
    public void register(ServiceInfo request, StreamObserver<RegisterReply> responseObserver) {
        if (request.getServiceName().isBlank() || request.getHost().isBlank() || request.getPort() <= 0) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("service_name, host and port must be provided")
                    .asRuntimeException());
            return;
        }

        registryStore.register(request.getServiceName(), new RegistryStore.Entry(
                request.getHost(),
                request.getPort(),
                request.getVersion(),
                Map.copyOf(request.getMetadataMap()),
                System.currentTimeMillis()
        ));

        responseObserver.onNext(RegisterReply.newBuilder()
                .setOk(true)
                .setMessage("Registered " + request.getServiceName() + " at " + request.getHost() + ":" + request.getPort())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void discover(DiscoverRequest request, StreamObserver<DiscoverReply> responseObserver) {
        if (request.getServiceName().isBlank()) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("service_name is required")
                    .asRuntimeException());
            return;
        }

        DiscoverReply.Builder reply = DiscoverReply.newBuilder();
        registryStore.discover(request.getServiceName()).forEach(entry -> reply.addEndpoints(ServiceEndpoint.newBuilder()
                .setHost(entry.host)
                .setPort(entry.port)
                .setVersion(entry.version)
                .putAllMetadata(entry.metadata)
                .setRegisteredEpoch(entry.registeredAt)
                .build()));
        responseObserver.onNext(reply.build());
        responseObserver.onCompleted();
    }
}
