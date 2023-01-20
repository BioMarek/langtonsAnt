package Windows;

import Graphic.AntVisualization;
import Graphic.Visualization.HexGraphicSingle;
import Logic.Ant.SquareAnt;
import Logic.Ant.HexAnt;
import Utils.Settings;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GridPanel extends JPanel implements ActionListener {
    private final SquareAnt squareAnt;
    private final AntVisualization antVisualization;
    private Timer timer;

//    public GridPanel() {
//        this.setPreferredSize(new Dimension(Settings.BACKGROUND_WIDTH, Settings.BACKGROUND_HEIGHT));
//        this.setFocusable(true);
//        this.ant = new Ant(Settings.RULE);
//
//        // TODO refactor
//        Settings.generateFourImagesPerScreenSettings();
//        Ant antTopLeft = new Ant("RL");
//        Ant antTopRight = new Ant("RLR");
//        Ant antBottomLeft = new Ant("RLRR");
//        Ant antBottomRight = new Ant("RLRRR");
//        this.antVisualization = new AntGraphicFour(antTopLeft, antTopRight, antBottomLeft, antBottomRight);
//
//        startTimer();
//    }

    public GridPanel() {
        this.setPreferredSize(new Dimension(Settings.BACKGROUND_WIDTH, Settings.BACKGROUND_HEIGHT));
        this.setFocusable(true);
        this.squareAnt = new SquareAnt(Settings.SquareRULE);

        Settings.generateHexagonalGridSettings();

        HexAnt ant = new HexAnt(Settings.HEX_RULE);
        this.antVisualization = new HexGraphicSingle();

        startTimer();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        antVisualization.drawPresentation(graphics);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        antVisualization.createNextFrame();
        repaint();

        if (squareAnt.stopped) {
            timer.stop();
            saveImage();
        }
    }

    /**
     * Starts timer, delay says how often {@link Graphics2D} is repainted.
     */
    public void startTimer() {
        timer = new Timer(Settings.TIMER_DELAY, this);
        timer.start();
    }

    /**
     * Saves image after ant stops moving either because move limit was exceeded or because ant moved over border.
     */
    public void saveImage() {
        BufferedImage bImg = new BufferedImage(Settings.BACKGROUND_WIDTH, Settings.BACKGROUND_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D cg = bImg.createGraphics();
        this.paintAll(cg);

        try {
            ImageIO.write(bImg, "png", new File("./images/panel_" + Settings.SquareRULE + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
