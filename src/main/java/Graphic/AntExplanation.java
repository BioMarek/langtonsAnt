package Graphic;

import Logic.Ant;
import Utils.Colors;
import Utils.Direction;
import Utils.Position;
import Utils.Settings;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AntExplanation implements AntVisualization {
    private Graphics2D graphics;
    private final Ant ant;
    private final Legend legend;
    private final Background background;
    private BufferedImage antImage = null;
    private double rotateAngle = Math.toRadians(3);
    private double currentAngle = Math.toRadians(0);
    private double startX = 730;
    private double startY = 470;
    private int currentCycle = 0;
    private int imageCount = 0;
    private final int explanationFrames = Settings.FRAMES_BETWEEN_STEPS * 2;

    public AntExplanation(Ant ant) {
        this.ant = ant;
        this.legend = new Legend(ant);
        this.background = new Background();

        try {
            File imageFile = new File("gifs/ant.png");
            antImage = ImageIO.read(imageFile);
        } catch (IOException ignored) {
        }
    }

    @Override
    public void createNextImage() {
        System.out.println("creating image " + imageCount++);
        if (++currentCycle % explanationFrames == 0) // we have time for ant move animation
            ant.nextMoves();
        if (currentCycle == explanationFrames)
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
    }

    /**
     * Draws image that can be presented as frame of animation. Contains info on the right side of image.
     */
    @Override
    public void drawPresentation(Graphics2D graphics) {
        this.graphics = graphics;
        legend.graphics = graphics;
        background.graphics = graphics;
        background.setBackground(true);
        legend.drawLegend();
        draw();
        drawExplanation();
    }


    /**
     * Converts grid of numbers to {@link Graphics2D}.
     */
    public void draw() {
        int borderPadding = Settings.IMAGE_PADDING / Settings.SIZE_OF_SQUARE;
        for (int row = 0; row < ant.gridRows - borderPadding; row++) {
            for (int column = 0; column < ant.gridColumns - borderPadding; column++) {
                Colors.setColor(graphics, ant.grid[row + borderPadding / 2][column + borderPadding / 2]);
                int sizeOfSquare = Settings.SHOW_GRID ? Settings.SIZE_OF_SQUARE - 1 : Settings.SIZE_OF_SQUARE;
                // part that makes zoom
                if (Settings.ZOOMED)
                    graphics.fillRect(column * Settings.SIZE_OF_SQUARE + Settings.GRAPHIC_SHIFT, row * Settings.SIZE_OF_SQUARE + Settings.GRAPHIC_SHIFT, sizeOfSquare, sizeOfSquare);
                else
                    graphics.fillRect(column * Settings.SIZE_OF_SQUARE, row * Settings.SIZE_OF_SQUARE, sizeOfSquare, sizeOfSquare);
            }
        }
    }

    private void drawExplanation() {
        double locationX = antImage.getWidth() / 2.0;
        double locationY = antImage.getHeight() / 2.0;
        AffineTransform tx = AffineTransform.getRotateInstance(currentAngle, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

        graphics.drawImage(antImage, op, (int) startX, (int) startY);
        Position position = explanationAnimationPositions(ant.steps);

        if (currentCycle < explanationFrames / 2) {
            startX = startX + position.row * 2.0 / explanationFrames;
            startY = startY + position.column * 2.0 / explanationFrames;
        }
        if (currentCycle >= explanationFrames / 2) {
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
