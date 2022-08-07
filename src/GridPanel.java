import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridPanel extends JPanel implements ActionListener {
    int SIZE_IN_PIXELS = 750;
    private int delay;

    public GridPanel(int delay) {
        this.delay = delay;

        this.setPreferredSize(new Dimension(SIZE_IN_PIXELS, SIZE_IN_PIXELS));
        this.setBackground(Color.black);
        this.setFocusable(true);
    }

    public void startGame() {
        Timer timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        repaint();
    }
}
