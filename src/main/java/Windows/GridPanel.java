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
        ant.nextMoves();
        repaint();

        if (ant.stopped)
            timer.stop();
    }

    /**
     * Converts grid numbers to {@link Graphics2D}.
     */
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

    /**
     * Starts timer, delay says how often {@link Graphics2D} is refreshed.
     */
    public void startAnimation() {
        timer = new Timer(Settings.DELAY, this);
        timer.start();
    }

    /**
     * Mapping of grid numbers to colors. Sets {@link Graphics2D} to color of current grid tile.
     *
     * @param color grid number
     */
    private void setColor(int color) {
        Color VIOLET = new Color(127, 0, 255);
        Color BROWN = new Color(139, 69, 19);
        Color LIGHT_GREEN = new Color(128, 255, 0);
        Color LIGHT_BLUE = new Color(0, 128, 255);
        Color OLIVE = new Color(128, 128, 0);
        Color NAVY = new Color(0, 0, 128);
        Color TEAL = new Color(0, 128, 128);
        Color CORAL = new Color(240, 128, 128);
        Color KHAKI = new Color(240, 232, 170);

        switch (color) {
            case -1 -> graphics.setColor(Color.BLACK);
            case 0 -> graphics.setColor(Color.WHITE);
            case 1 -> graphics.setColor(Color.RED);
            case 2 -> graphics.setColor(Color.GREEN);
            case 3 -> graphics.setColor(Color.BLUE);
            case 4 -> graphics.setColor(Color.YELLOW);
            case 5 -> graphics.setColor(Color.ORANGE);
            case 6 -> graphics.setColor(VIOLET);
            case 7 -> graphics.setColor(Color.MAGENTA);
            case 8 -> graphics.setColor(Color.CYAN);
            case 9 -> graphics.setColor(Color.PINK);
            case 10 -> graphics.setColor(BROWN);
            case 11 -> graphics.setColor(LIGHT_GREEN);
            case 12 -> graphics.setColor(LIGHT_BLUE);
            case 13 -> graphics.setColor(OLIVE);
            case 14 -> graphics.setColor(NAVY);
            case 15 -> graphics.setColor(TEAL);
            case 16 -> graphics.setColor(CORAL);
            case 17 -> graphics.setColor(KHAKI);
        }
    }
}
