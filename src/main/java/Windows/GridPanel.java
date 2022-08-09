package Windows;

import Logic.Ant;
import Utils.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridPanel extends JPanel implements ActionListener {
    private final int squares = Settings.SIZE_IN_PIXELS / Settings.SIZE_OF_SQUARE;
    private static Graphics2D graphics;
    private final Ant ant = new Ant(squares);
    Timer timer;

    public GridPanel() {
        this.setPreferredSize(new Dimension(Settings.SIZE_IN_PIXELS, Settings.SIZE_IN_PIXELS));
        this.setBackground(Color.gray);
        this.setFocusable(true);
        startAnimation();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = (Graphics2D) g;
        draw();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ant.nextMove();
        repaint();

        if (ant.stopped)
            timer.stop();
    }

    public void draw() {
        for (int row = 0; row < squares; row++) {
            for (int column = 0; column < squares; column++) {
                setColor(ant.grid[row][column]);
                graphics.fillRect(row * Settings.SIZE_OF_SQUARE,
                        column * Settings.SIZE_OF_SQUARE,
                        Settings.SIZE_OF_SQUARE,
                        Settings.SIZE_OF_SQUARE);
            }
        }
    }

    public void startAnimation() {
        timer = new Timer(Settings.DELAY, this);
        timer.start();
    }

    private void setColor(int color) {
        switch (color) {
            case 0 -> graphics.setColor(Color.BLACK);
            case 1 -> graphics.setColor(Color.RED);
            case 2 -> graphics.setColor(Color.GREEN);
            case 3 -> graphics.setColor(Color.BLUE);
            case 4 -> graphics.setColor(Color.YELLOW);
            case 5 -> graphics.setColor(Color.ORANGE);
            case 6 -> graphics.setColor(Color.MAGENTA);
            case 7 -> graphics.setColor(Color.CYAN);
            case 8 -> graphics.setColor(Color.PINK);
        }
    }
}
