package Gui;

import Graphic.AntVisualization;
import Graphic.Visualization.AntGraphicFour;
import Graphic.Visualization.AntGraphicSingle;
import Graphic.Visualization.HexExplanation;
import Graphic.Visualization.HexGraphicSingle;
import Logic.Ant.SquareAnt;
import Logic.Rule.SquareRule;
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

    public GridPanel(SquareRule rule1, SquareRule rule2, SquareRule rule3, SquareRule rule4) {
        this.setPreferredSize(new Dimension(Settings.BACKGROUND_WIDTH, Settings.BACKGROUND_HEIGHT));
        this.setFocusable(true);
        this.squareAnt = new SquareAnt(Settings.SQUARE_RULE);

        Settings.generateFourImagesPerScreenSettings();
        SquareAnt antTopLeft = new SquareAnt(rule1);
        SquareAnt antTopRight = new SquareAnt(rule2);
        SquareAnt antBottomLeft = new SquareAnt(rule3);
        SquareAnt antBottomRight = new SquareAnt(rule4);
        this.antVisualization = new AntGraphicFour(antTopLeft, antTopRight, antBottomLeft, antBottomRight);

        startTimer();
    }

    /**
     * Hexagonal visualization
     */
    public GridPanel(HexGraphicSingle hexGraphicSingle) {
        this.setPreferredSize(new Dimension(Settings.BACKGROUND_WIDTH, Settings.BACKGROUND_HEIGHT));
        this.setFocusable(true);
        this.squareAnt = new SquareAnt(Settings.SQUARE_RULE);

        Settings.generateHexagonalGridSettings();

        this.antVisualization = hexGraphicSingle;

        startTimer();
    }

    public GridPanel(AntGraphicSingle antGraphicSingle) {
        this.setPreferredSize(new Dimension(Settings.BACKGROUND_WIDTH, Settings.BACKGROUND_HEIGHT));
        this.setFocusable(true);
        this.squareAnt = new SquareAnt(Settings.SQUARE_RULE);

        Settings.generateHexagonalGridSettings();

        this.antVisualization = antGraphicSingle;

        startTimer();
    }

    /**
     * Hexagonal explanation
     */
    public GridPanel(HexExplanation hexExplanation) {
        this.setPreferredSize(new Dimension(Settings.BACKGROUND_WIDTH, Settings.BACKGROUND_HEIGHT));
        this.setFocusable(true);
        this.squareAnt = new SquareAnt(Settings.SQUARE_RULE);

        Settings.generateHexagonalGridSettings();

        this.antVisualization = hexExplanation;

        startTimer();
    }

    public GridPanel() {
        this.setPreferredSize(new Dimension(Settings.BACKGROUND_WIDTH, Settings.BACKGROUND_HEIGHT));
        this.setFocusable(true);
        this.squareAnt = new SquareAnt(Settings.SQUARE_RULE);
        this.antVisualization = new AntGraphicSingle();
        ((AntGraphicSingle) antVisualization).squareAnt = squareAnt;
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
            ImageIO.write(bImg, "png", new File("./images/panel_" + Settings.SQUARE_RULE + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
