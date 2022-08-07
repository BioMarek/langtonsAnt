import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridPanel extends JPanel implements ActionListener {
    private final int SIZE_IN_PIXELS = 750;
    public GridPanel() {
        this.setPreferredSize(new Dimension(SIZE_IN_PIXELS, SIZE_IN_PIXELS));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
