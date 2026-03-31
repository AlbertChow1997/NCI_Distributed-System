package sdg.demo;

import sdg.client.SmartClimateControllerFrame;
import sdg.climaterisk.ClimateRiskServer;
import sdg.emission.EmissionServer;
import sdg.mitigation.MitigationServer;
import sdg.naming.NamingServer;

import javax.swing.SwingUtilities;

public class SmartClimateWorkbench {
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

        SwingUtilities.invokeLater(() -> {
            SmartClimateControllerFrame frame = new SmartClimateControllerFrame();
            frame.setVisible(true);
        });
    }
}
