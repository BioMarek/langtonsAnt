import javax.swing.*;

public class GridFrame extends JFrame {
    public GridFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new GridPanel());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.pack();
    }
}
