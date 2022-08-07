package Windows;

import javax.swing.*;
import Utils.Settings;

public class GridFrame extends JFrame {
    public GridFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new GridPanel(Settings.DELAY));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.pack();
    }
}
