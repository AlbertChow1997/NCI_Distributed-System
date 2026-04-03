package sdg.client;

import javax.swing.SwingUtilities;

public class SmartClimateControllerApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SmartClimateControllerFrame frame = new SmartClimateControllerFrame();
            frame.setVisible(true);
        });
    }
}
