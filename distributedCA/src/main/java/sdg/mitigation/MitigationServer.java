package sdg.mitigation;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;
import sdg.common.AuthServerInterceptor;
import sdg.common.ServiceDirectory;
import sdg.common.ServiceRegistrar;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MitigationServer {
    private final Server server;

    public MitigationServer() {
        // Interceptor is added here so every RPC to this service is checked before entering the real logic.
        this.server = ServerBuilder.forPort(ServiceDirectory.MITIGATION_PORT)
                .addService(ServerInterceptors.intercept(new MitigationOrchestratorServiceImpl(), new AuthServerInterceptor()))
                .build();
    }

    public void start() throws IOException {
        server.start();
        System.out.println("Mitigation Server started on port " + ServiceDirectory.MITIGATION_PORT);
        ServiceRegistrar.register(ServiceDirectory.MITIGATION_SERVICE, ServiceDirectory.MITIGATION_PORT, "1.0.0",
                List.of("RunControlLoop", "CancelStrategy"),
                Map.of("transport", "grpc", "streaming", "bidi", "auth", "api-key"));
    }

    public void blockUntilShutdown() throws InterruptedException {
        server.awaitTermination();
    }

    public void stop() {
        server.shutdownNow();
        System.out.println("Mitigation Server stopped.");
    }

    public static void main(String[] args) throws Exception {
        MitigationServer server = new MitigationServer();
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
        server.blockUntilShutdown();
    }
}
