package sdg.climaterisk;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import sdg.common.ServiceDirectory;
import sdg.common.ServiceRegistrar;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ClimateRiskServer {
    private final Server server;

    public ClimateRiskServer() {
        // This server hosts the climate risk alert service.
        this.server = ServerBuilder.forPort(ServiceDirectory.CLIMATE_RISK_PORT)
                .addService(new ClimateRiskAlertServiceImpl())
                .build();
    }

    public void start() throws IOException {
        server.start();
        System.out.println("Climate Risk Server started on port " + ServiceDirectory.CLIMATE_RISK_PORT);
        ServiceRegistrar.register(ServiceDirectory.CLIMATE_RISK_SERVICE, ServiceDirectory.CLIMATE_RISK_PORT, "1.0.0",
                List.of("GetCurrentRisk", "SubscribeAlerts"),
                Map.of("transport", "grpc", "streaming", "server"));
    }

    public void blockUntilShutdown() throws InterruptedException {
        server.awaitTermination();
    }

    public void stop() {
        server.shutdownNow();
        System.out.println("Climate Risk Server stopped.");
    }

    public static void main(String[] args) throws Exception {
        ClimateRiskServer server = new ClimateRiskServer();
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
        server.blockUntilShutdown();
    }
}
