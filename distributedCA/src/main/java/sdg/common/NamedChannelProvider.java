package sdg.common;

import generated.grpc.naming.DiscoverReply;
import generated.grpc.naming.DiscoverRequest;
import generated.grpc.naming.NamingServiceGrpc;
import generated.grpc.naming.ServiceEndpoint;
import io.grpc.ManagedChannel;

import java.util.concurrent.TimeUnit;

public class NamedChannelProvider {
    public ManagedChannel channelFor(String serviceName) {
        ManagedChannel namingChannel = GrpcChannels.build(ServiceDirectory.HOST, ServiceDirectory.NAMING_PORT);
        try {
            NamingServiceGrpc.NamingServiceBlockingStub stub = NamingServiceGrpc.newBlockingStub(namingChannel)
                    .withDeadlineAfter(3, TimeUnit.SECONDS);
            DiscoverReply reply = stub.discover(DiscoverRequest.newBuilder().setServiceName(serviceName).build());
            if (reply.getEndpointsCount() == 0) {
                throw new IllegalStateException("No registered endpoint for " + serviceName);
            }
            ServiceEndpoint endpoint = reply.getEndpoints(0);
            return GrpcChannels.build(endpoint.getHost(), endpoint.getPort());
        } finally {
            namingChannel.shutdownNow();
        }
    }
}
