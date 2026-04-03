package sdg.common;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public final class GrpcChannels {
    // This is a small helper class.
    // A channel is the pipeline between the client stub and the gRPC server.
    // I use plaintext here because this project is only a local CA demo.
    private GrpcChannels() {
    }

    public static ManagedChannel build(String host, int port) {
        return ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
    }
}
