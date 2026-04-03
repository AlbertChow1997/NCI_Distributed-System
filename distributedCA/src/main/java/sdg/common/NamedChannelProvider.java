package sdg.common;

import generated.grpc.naming.DiscoverReply;
import generated.grpc.naming.DiscoverRequest;
import generated.grpc.naming.NamingServiceGrpc;
import generated.grpc.naming.ServiceEndpoint;
import io.grpc.ManagedChannel;

import java.util.concurrent.TimeUnit;

public class NamedChannelProvider {
    // This class follows the naming service idea from class.
    // The client does not hardcode the real service address first,
    // it asks the naming service and then builds a channel to the discovered endpoint.
    public ManagedChannel channelFor(String serviceName) {
        ManagedChannel namingChannel = GrpcChannels.build(ServiceDirectory.HOST, ServiceDirectory.NAMING_PORT);
        try {
            NamingServiceGrpc.NamingServiceBlockingStub stub = NamingServiceGrpc.newBlockingStub(namingChannel)
                    .withDeadlineAfter(3, TimeUnit.SECONDS);

            // Unary call to naming service to resolve a service name.
            DiscoverReply reply = stub.discover(DiscoverRequest.newBuilder().setServiceName(serviceName).build());
            if (reply.getEndpointsCount() == 0) {
                throw new IllegalStateException("No registered endpoint for " + serviceName);
            }

            // In this project I use the first result because one service usually has one local endpoint.
            ServiceEndpoint endpoint = reply.getEndpoints(0);
            return GrpcChannels.build(endpoint.getHost(), endpoint.getPort());
        } finally {
            namingChannel.shutdownNow();
        }
    }
}
