package sdg.common;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcChannels {
    public static ManagedChannel build(String host, int port) {
        return ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
    }
}
