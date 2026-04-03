package sdg.start;

import sdg.client.SmartClimateControllerFrame;
import sdg.climaterisk.ClimateRiskServer;
import sdg.emission.EmissionServer;
import sdg.mitigation.MitigationServer;
import sdg.naming.NamingServer;

import javax.swing.SwingUtilities;

public class SmartClimateWorkbench {
    public static void main(String[] args) throws Exception {
        // This launcher is for the easiest demo.
        // It starts all services and then opens the GUI controller.
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
        System.out.println("Smart Climate Workbench started.");
    }
}
