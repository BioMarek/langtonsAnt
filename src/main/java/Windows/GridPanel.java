package Windows;

import Logic.Ant;
import Utils.Direction;
import Utils.Position;
import Utils.Settings;
import Utils.Util;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GridPanel extends JPanel implements ActionListener {
    private final Ant ant;
    private Timer timer;
    private int currentCycle = 0;
    private BufferedImage antImage = null;

    private double rotateAngle = (Math.toRadians(90) - Math.toRadians(0)) / Settings.FRAMES_BETWEEN_STEPS;
    private double startAngle = Math.toRadians(0);
    private double startX = 450;
    private double startY = 450;

    public GridPanel() {
        int sizeInPixels = Settings.SHOW_GRID ? Settings.SIZE_IN_PIXELS + 1 : Settings.SIZE_IN_PIXELS;
        // Settings.SIZE_IN_PIXELS / 3 space is for left margin with info
        if (Settings.INFO_FOR_4_IMAGES)
            this.setPreferredSize(new Dimension(sizeInPixels, sizeInPixels));
        else
            this.setPreferredSize(new Dimension(sizeInPixels + Settings.SIZE_IN_PIXELS / 3, sizeInPixels));
        this.setFocusable(true);

        int squares = Settings.SIZE_IN_PIXELS / Settings.SIZE_OF_SQUARE;

        ant = new Ant(squares, Settings.MAX_MOVES, Settings.RULE);

        try {
            File imageFile = new File("gifs/ant.png");
            antImage = ImageIO.read(imageFile);
        } catch (IOException ignored) {
        }

        startAnimation();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        ant.drawPresentation(graphics);
        drawExplanation(graphics);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (Settings.EXPLANATION_ANIMATION) {
            if (++currentCycle % Settings.FRAMES_BETWEEN_STEPS == 0) // we have time for ant move animation
                ant.nextMoves();
        } else
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
        BufferedImage bImg = new BufferedImage(Settings.SIZE_IN_PIXELS + Settings.SIZE_IN_PIXELS / 3, Settings.SIZE_IN_PIXELS, BufferedImage.TYPE_INT_RGB);
        Graphics2D cg = bImg.createGraphics();
        this.paintAll(cg);

        try {
            ImageIO.write(bImg, "png", new File("./images/panel_" + Settings.RULE + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawExplanation(Graphics2D graphics) {
        double locationX = antImage.getWidth() / 2.0;
        double locationY = antImage.getHeight() / 2.0;
        AffineTransform tx = AffineTransform.getRotateInstance(startAngle, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        Position position = Util.explanationAnimationPositions(ant.steps);
        System.out.println(ant.steps + " " + position);
        startX = startX + position.row * 1.0 / Settings.FRAMES_BETWEEN_STEPS;
        startY = startY + position.column * 1.0 / Settings.FRAMES_BETWEEN_STEPS;
        System.out.println(startX + " " + startY);
        graphics.drawImage(antImage, op, (int) startX, (int) startY);
        if (position.direction == Direction.RIGHT)
            startAngle += rotateAngle;
        else
            startAngle -= rotateAngle;
    }

    private double getRotationAngles(int degreesFrom, int degreesTo) {
        List<Double> result = new ArrayList<>();
        double radiansFrom = Math.toRadians(degreesFrom);
        double radiansTo = Math.toRadians(degreesTo);

        double step = (radiansTo - radiansFrom) / Settings.FRAMES_BETWEEN_STEPS;
        for (int i = 0; i < Settings.FRAMES_BETWEEN_STEPS; i++) {
            result.add(i * step);
        }

        return result.get(currentCycle);
    }

}
