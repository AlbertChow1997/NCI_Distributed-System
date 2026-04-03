package sdg.emission;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import sdg.common.ServiceDirectory;
import sdg.common.ServiceRegistrar;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class EmissionServer {
    private final Server server;

    public EmissionServer() {
        // This server hosts the emission accounting service implementation.
        this.server = ServerBuilder.forPort(ServiceDirectory.EMISSION_PORT)
                .addService(new EmissionAccountingServiceImpl())
                .build();
    }

    public void start() throws IOException {
        server.start();
        System.out.println("Emission Server started on port " + ServiceDirectory.EMISSION_PORT);
        ServiceRegistrar.register(ServiceDirectory.EMISSION_SERVICE, ServiceDirectory.EMISSION_PORT, "1.0.0",
                List.of("GetCarbonSnapshot", "UploadUsageBatch"),
                Map.of("transport", "grpc", "streaming", "client"));
    }

    public void blockUntilShutdown() throws InterruptedException {
        server.awaitTermination();
    }

    public void stop() {
        server.shutdownNow();
        System.out.println("Emission Server stopped.");
    }

    public static void main(String[] args) throws Exception {
        EmissionServer server = new EmissionServer();
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
        server.blockUntilShutdown();
    }
}
