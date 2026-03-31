package sdg.naming;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import sdg.common.ServiceDirectory;

import java.io.IOException;

public class NamingServer {
    private final Server server;

    public NamingServer() {
        this.server = ServerBuilder.forPort(ServiceDirectory.NAMING_PORT)
                .addService(new NamingServiceImpl(new RegistryStore()))
                .build();
    }

    public void start() throws IOException {
        server.start();
    }

    public void blockUntilShutdown() throws InterruptedException {
        server.awaitTermination();
    }

    public void stop() {
        server.shutdownNow();
    }

    public static void main(String[] args) throws Exception {
        NamingServer server = new NamingServer();
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
        server.blockUntilShutdown();
    }
}
