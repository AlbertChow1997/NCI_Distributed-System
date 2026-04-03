package sdg.common;

import generated.grpc.naming.NamingServiceGrpc;
import generated.grpc.naming.RegisterReply;
import generated.grpc.naming.ServiceInfo;
import io.grpc.ManagedChannel;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class ServiceRegistrar {
    // This utility is used by each service server when it starts.
    // It sends one unary Register RPC to the Naming Service.
    private ServiceRegistrar() {
    }

    public static RegisterReply register(String serviceName, int port, String version, List<String> capabilities,
                                         Map<String, String> metadata) {
        ManagedChannel channel = GrpcChannels.build(ServiceDirectory.HOST, ServiceDirectory.NAMING_PORT);
        try {
            NamingServiceGrpc.NamingServiceBlockingStub stub = NamingServiceGrpc.newBlockingStub(channel)
                    .withDeadlineAfter(3, TimeUnit.SECONDS);
            RegisterReply reply = stub.register(ServiceInfo.newBuilder()
                    .setServiceName(serviceName)
                    .setHost(ServiceDirectory.HOST)
                    .setPort(port)
                    .setVersion(version)
                    .addAllCapabilities(capabilities)
                    .putAllMetadata(metadata)
                    .build());
            System.out.println("Registered service: " + serviceName + " on port " + port);
            return reply;
        } finally {
            channel.shutdownNow();
        }
    }
}
