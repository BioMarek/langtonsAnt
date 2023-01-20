package Graphic.Visualization;

import Graphic.AntVisualization;
import Graphic.Components.Background;
import Graphic.Components.SquareLegend;
import Logic.Ant.SquareAnt;
import Utils.Colors;
import Logic.Direction;
import Logic.Position;
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

/**
 * Used for explanation part of video where ant image is show and then grid is zoomed out. Explanations is from two
 * parts. In first and image is shown and zoomed out from 80 to 10 pixels per square and runs to 100 steps. In second
 * part ant runs 10 pixel squares. It would be hard to make zoom and use padding and to see ant to go to the edge.
 */
public class AntExplanation implements AntVisualization {
    private Graphics2D graphics;
    private final SquareAnt squareAnt;
    private final SquareLegend squareLegend;
    private final Background background;
    private BufferedImage antImage = null;
    private double rotateAngle = Math.toRadians(3);
    private double currentAngle = Math.toRadians(0);
    private double startX = 730;
    private double startY = 470;
    private int currentCycle = 0;
    private int imageCount = 0;
    private final int explanationFrames = Settings.FRAMES_BETWEEN_STEPS * 2;

    public AntExplanation(SquareAnt squareAnt) {
        this.squareAnt = squareAnt;
        this.squareLegend = new SquareLegend();
        this.background = new Background();

        try {
            File imageFile = new File("ant.png");
            antImage = ImageIO.read(imageFile);
        } catch (IOException ignored) {
        }
    }

    @Override
    public void createNextFrame() {
        System.out.println("creating frame " + imageCount++ + " " + squareAnt.steps + "/" + Settings.MAX_MOVES);
        if (++currentCycle % explanationFrames == 0
                || squareAnt.steps < 20 && Settings.ZOOMED && currentCycle % 5 == 0 // first speedup during zoom
                || squareAnt.steps >= 20) // second speedup after zoom
            squareAnt.nextMoves();
        if (currentCycle == explanationFrames)
            currentCycle = 0;
        //
        // part that makes zoom
        if (squareAnt.steps > Settings.ZOOM_STEPS) {
            Settings.ZOOMED = true;
            if (Settings.SIZE_OF_SQUARE > 10) {
                Settings.SIZE_OF_SQUARE -= 1;
                Settings.GRAPHIC_SHIFT_COLUMN += 9.5;
                Settings.GRAPHIC_SHIFT_ROW += 6.9;
            }
        }
    }

    /**
     * Draws image that can be presented as frame of animation. Contains info on the right side of image.
     */
    @Override
    public void drawPresentation(Graphics2D graphics) {
        this.graphics = graphics;
        squareLegend.graphics = graphics;
        background.graphics = graphics;
        background.setBackground(true);
        squareLegend.drawLegend(squareAnt);
        drawGrid();
        if (!Settings.ZOOMED)
            drawAntImage();
    }

    @Override
    public boolean stopped() {
        return squareAnt.stopped;
    }

    /**
     * Converts grid of numbers to {@link Graphics2D}.
     */
    public void drawGrid() {
        // in order to connect first part of explanation video with second part which is regular ant without zoom we
        // need to adjust where squares are rendered on the screen because just updating GRAPHIC_SHIFT_COLUMN and
        // GRAPHIC_SHIFT_ROW is not precise.
        int column_adjustment = -4;
        int row_adjustment = -2;

        int borderPadding = Settings.IMAGE_PADDING / Settings.SIZE_OF_SQUARE;
        for (int row = 0; row < squareAnt.gridRows - borderPadding; row++) {
            for (int column = 0; column < squareAnt.gridColumns - borderPadding; column++) {
                Colors.setColor(graphics, squareAnt.grid[row + borderPadding / 2][column + borderPadding / 2]);
                int sizeOfSquare = Settings.SHOW_GRID ? Settings.SIZE_OF_SQUARE - 1 : Settings.SIZE_OF_SQUARE;
                // part that makes zoom
                if (Settings.ZOOMED)
                    graphics.fillRect(column * Settings.SIZE_OF_SQUARE + (int) Settings.GRAPHIC_SHIFT_COLUMN + column_adjustment,
                            row * Settings.SIZE_OF_SQUARE + (int) Settings.GRAPHIC_SHIFT_ROW + row_adjustment, sizeOfSquare, sizeOfSquare);
                else
                    graphics.fillRect(column * Settings.SIZE_OF_SQUARE, row * Settings.SIZE_OF_SQUARE, sizeOfSquare, sizeOfSquare);
            }
        }
    }

    private void drawAntImage() {
        double locationX = antImage.getWidth() / 2.0;
        double locationY = antImage.getHeight() / 2.0;
        AffineTransform tx = AffineTransform.getRotateInstance(currentAngle, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

        graphics.drawImage(antImage, op, (int) startX, (int) startY);
        Position position = antImagePositions(squareAnt.steps);

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

    private Position antImagePositions(int i) {
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
