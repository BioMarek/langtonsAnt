package Windows;

import Logic.Ant;
import Logic.AntGraphic;
import Utils.Direction;
import Utils.Position;
import Utils.Settings;

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
    private double rotateAngle = Math.toRadians(6);
    private double currentAngle = Math.toRadians(0);
    private double startX = 490;
    private double startY = 475;

    public GridPanel() {
        int sizeInPixels = Settings.SHOW_GRID ? Settings.SIZE_IN_PIXELS + 1 : Settings.SIZE_IN_PIXELS;
        // Settings.SIZE_IN_PIXELS / 3 space is for left margin with info
        if (Settings.INFO_FOR_4_IMAGES)
            this.setPreferredSize(new Dimension(sizeInPixels, sizeInPixels));
        else
            this.setPreferredSize(new Dimension(sizeInPixels + Settings.SIZE_IN_PIXELS / 3, sizeInPixels));
        this.setFocusable(true);

        ant = new Ant(Settings.RULE);

        try {
            File imageFile = new File("gifs/ant.png");
            antImage = ImageIO.read(imageFile);
        } catch (IOException ignored) {
        }

        startTimer();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        AntGraphic antGraphic = new AntGraphic(ant);
        antGraphic.drawPresentation(graphics);
        if (Settings.EXPLANATION_ANIMATION)
            if (ant.steps <= Settings.ZOOM_STEPS)
                drawExplanation(graphics);
        //
        // part that increases speed of animation after zoom
        if (Settings.SIZE_OF_SQUARE == 10 && Settings.DELAY > 20) {
            Settings.DELAY -= 1;
            startTimer();
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (Settings.EXPLANATION_ANIMATION) {
            if (++currentCycle % Settings.FRAMES_BETWEEN_STEPS == 0) // we have time for ant move animation
                ant.nextMoves();
            if (currentCycle == 30)
                currentCycle = 0;
            //
            // part that makes zoom
            if (ant.steps > Settings.ZOOM_STEPS) {
                Settings.ZOOMED = true;
                if (Settings.SIZE_OF_SQUARE > 10) {
                    Settings.SIZE_OF_SQUARE -= 2;
                    Settings.GRAPHIC_SHIFT += 12;
                }
            }
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
    public void startTimer() {
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
        AffineTransform tx = AffineTransform.getRotateInstance(currentAngle, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

        graphics.drawImage(antImage, op, (int) startX, (int) startY);
        Position position = explanationAnimationPositions(ant.steps);

        if (currentCycle < 15) {
            startX = startX + position.row * 2.0 / Settings.FRAMES_BETWEEN_STEPS;
            startY = startY + position.column * 2.0 / Settings.FRAMES_BETWEEN_STEPS;
        }
        if (currentCycle >= 15) {
            if (position.direction == Direction.RIGHT)
                currentAngle += rotateAngle;
            if (position.direction == Direction.LEFT)
                currentAngle -= rotateAngle;
        }
    }

    private Position explanationAnimationPositions(int i) {
        List<Position> positions = new ArrayList<>();

        positions.add(new Position(0, 0, Direction.RIGHT));
        positions.add(new Position(80, 0, Direction.RIGHT));
        positions.add(new Position(0, 80, Direction.RIGHT));
        positions.add(new Position(-80, 0, Direction.RIGHT));

        positions.add(new Position(0, -80, Direction.LEFT));
        positions.add(new Position(-80, 0, Direction.RIGHT));
        positions.add(new Position(0, -80, Direction.RIGHT));
        positions.add(new Position(80, 0, Direction.RIGHT));

        positions.add(new Position(0, 80, Direction.RIGHT));
        positions.add(new Position(-80, 0, Direction.LEFT));
        positions.add(new Position(0, 80, Direction.RIGHT));

        return (i < positions.size()) ? positions.get(i) : new Position(0, 0, Direction.RIGHT);
    }
}
