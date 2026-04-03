package sdg.start;

import sdg.climaterisk.ClimateRiskServer;
import sdg.emission.EmissionServer;
import sdg.mitigation.MitigationServer;
import sdg.naming.NamingServer;

public class SmartClimateSystemLauncher {
    public static void main(String[] args) throws Exception {
        // Start naming first, because the other services need to register themselves.
        NamingServer namingServer = new NamingServer();
        namingServer.start();

        // These are the three business services required by the CA.
        EmissionServer emissionServer = new EmissionServer();
        ClimateRiskServer climateRiskServer = new ClimateRiskServer();
        MitigationServer mitigationServer = new MitigationServer();

        emissionServer.start();
        climateRiskServer.start();
        mitigationServer.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            mitigationServer.stop();
            climateRiskServer.stop();
            emissionServer.stop();
            namingServer.stop();
        }));

        System.out.println("All services are running.");
        Thread.currentThread().join();
    }
}
