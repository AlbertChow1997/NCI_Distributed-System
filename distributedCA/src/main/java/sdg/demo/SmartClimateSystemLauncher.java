package sdg.demo;

import sdg.climaterisk.ClimateRiskServer;
import sdg.emission.EmissionServer;
import sdg.mitigation.MitigationServer;
import sdg.naming.NamingServer;

public class SmartClimateSystemLauncher {
    public static void main(String[] args) throws Exception {
        NamingServer namingServer = new NamingServer();
        namingServer.start();

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

        Thread.currentThread().join();
    }
}
