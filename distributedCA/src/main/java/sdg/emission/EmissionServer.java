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
        this.server = ServerBuilder.forPort(ServiceDirectory.EMISSION_PORT)
                .addService(new EmissionAccountingServiceImpl())
                .build();
    }

    public void start() throws IOException {
        server.start();
        ServiceRegistrar.register(ServiceDirectory.EMISSION_SERVICE, ServiceDirectory.EMISSION_PORT, "1.0.0",
                List.of("GetCarbonSnapshot", "UploadUsageBatch"),
                Map.of("transport", "grpc", "streaming", "client"));
    }

    public void blockUntilShutdown() throws InterruptedException {
        server.awaitTermination();
    }

    public void stop() {
        server.shutdownNow();
    }

    public static void main(String[] args) throws Exception {
        EmissionServer server = new EmissionServer();
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
        server.blockUntilShutdown();
    }
}
