package Windows;

import Logic.Ant;
import Utils.Settings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GridPanel extends JPanel implements ActionListener {
    private final Ant ant;
    private Timer timer;


    public GridPanel() {
        int sizeInPixels = Settings.SHOW_GRID ? Settings.SIZE_IN_PIXELS + 1 : Settings.SIZE_IN_PIXELS;
        this.setPreferredSize(new Dimension(sizeInPixels, sizeInPixels));
        this.setFocusable(true);

        int squares = Settings.SIZE_IN_PIXELS / Settings.SIZE_OF_SQUARE;
        ant = new Ant(squares, Settings.MAX_MOVES, Settings.RULE);
        startAnimation();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        ant.draw(graphics);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ant.nextMoves();
        repaint();

        if (ant.stopped) {
            timer.stop();
            saveImage();
        }
    }

    /**
     * Starts timer, delay says how often {@link Graphics2D} is repainted.
     */
    public void startAnimation() {
        timer = new Timer(Settings.DELAY, this);
        timer.start();
    }

    /**
     * Saves image after ant stops moving either because move limit was exceeded or because ant moved over border.
     */
    public void saveImage() {
        BufferedImage bImg = new BufferedImage(Settings.SIZE_IN_PIXELS, Settings.SIZE_IN_PIXELS, BufferedImage.TYPE_INT_RGB);
        Graphics2D cg = bImg.createGraphics();
        this.paintAll(cg);

        try {
            ImageIO.write(bImg, "png", new File("./images/panel_" + Settings.RULE + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
