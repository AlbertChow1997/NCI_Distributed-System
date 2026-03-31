package sdg.common;

import generated.grpc.naming.NamingServiceGrpc;
import generated.grpc.naming.RegisterReply;
import generated.grpc.naming.ServiceInfo;
import io.grpc.ManagedChannel;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class ServiceRegistrar {
    private ServiceRegistrar() {
    }

    public static RegisterReply register(String serviceName, int port, String version, List<String> capabilities,
                                         Map<String, String> metadata) {
        ManagedChannel channel = GrpcChannels.build(ServiceDirectory.HOST, ServiceDirectory.NAMING_PORT);
        try {
            NamingServiceGrpc.NamingServiceBlockingStub stub = NamingServiceGrpc.newBlockingStub(channel)
                    .withDeadlineAfter(3, TimeUnit.SECONDS);
            return stub.register(ServiceInfo.newBuilder()
                    .setServiceName(serviceName)
                    .setHost(ServiceDirectory.HOST)
                    .setPort(port)
                    .setVersion(version)
                    .addAllCapabilities(capabilities)
                    .putAllMetadata(metadata)
                    .build());
        } finally {
            channel.shutdownNow();
        }
    }
}
