package Gui;

import javax.swing.JFrame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class GridFrame extends JFrame {
    GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice device = graphics.getDefaultScreenDevice();

    public GridFrame(GridPanel gridPanel) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(gridPanel);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);
        this.pack();
        device.setFullScreenWindow(this);
    }
}
