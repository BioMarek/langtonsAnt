package Windows;

import Logic.Ant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridPanel extends JPanel implements ActionListener {
    private final int SIZE_IN_PIXELS = 750;
    private final int SIZE_OF_SQUARE = 10;
    private final int squares = SIZE_IN_PIXELS / SIZE_OF_SQUARE;
    private int delay;
    private Graphics2D graphics;

    private Ant ant = new Ant(squares);

    public GridPanel(int delay) {
        this.delay = delay;

        this.setPreferredSize(new Dimension(SIZE_IN_PIXELS, SIZE_IN_PIXELS));
        this.setBackground(Color.gray);
        this.setFocusable(true);
        startAnimation();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        draw();
    }

    public void draw() {
        for (int row = 0; row < squares; row++) {
            for (int column = 0; column < squares; column++) {
                if (ant.grid[row][column] == 1) {
                    graphics.setColor(Color.WHITE);
                }
                if (ant.grid[row][column] == 2) {
                    graphics.setColor(Color.RED);
                }
                if (ant.grid[row][column] == 0) {
                    graphics.setColor(Color.BLACK);
                }
                graphics.fillRect(row * SIZE_OF_SQUARE, column * SIZE_OF_SQUARE, SIZE_OF_SQUARE, SIZE_OF_SQUARE);
            }
        }
    }

    public void startAnimation() {
        Timer timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ant.nextMove();
        repaint();
    }
}
