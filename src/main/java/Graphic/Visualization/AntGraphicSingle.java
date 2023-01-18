package Graphic.Visualization;

import Graphic.AntVisualization;
import Graphic.Components.Background;
import Graphic.Components.SquareLegend;
import Logic.Ant.Ant;
import Utils.Colors;
import Utils.Settings;

import java.awt.Graphics2D;

/**
 * Creates graphic for ant grid.
 */
public class AntGraphicSingle implements AntVisualization {
    private Graphics2D graphics;
    private final Ant squareAnt;
    private final SquareLegend squareLegend;
    private final Background background;
    private int imageCount = 0;

    public AntGraphicSingle(Ant squareAnt) {
        this.squareAnt = squareAnt;
        this.squareLegend = new SquareLegend();
        this.background = new Background();
    }

    /**
     * Draws simple image of end result that can be saved as file.
     */
    public void drawImage(Graphics2D graphics) {
        this.graphics = graphics;
        background.graphics = graphics;
        background.setBackground(false);
        drawGrid();
    }

    @Override
    public void createNextFrame() {
        System.out.println("creating frame " + imageCount++);
        squareAnt.nextMoves();
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
    }

    @Override
    public boolean stopped() {
        return squareAnt.stopped;
    }

    /**
     * Converts grid of numbers to {@link Graphics2D}.
     */
    public void drawGrid() {
        int borderPadding = Settings.IMAGE_PADDING / Settings.SIZE_OF_SQUARE;
        for (int row = 0; row < squareAnt.gridRows - borderPadding; row++) {
            for (int column = 0; column < squareAnt.gridColumns - borderPadding; column++) {
                if (squareAnt.grid[row][column] == -1)
                    continue;
                Colors.setColor(graphics, squareAnt.grid[row + borderPadding / 2][column + borderPadding / 2]);
                int sizeOfSquare = Settings.SHOW_GRID ? Settings.SIZE_OF_SQUARE - 1 : Settings.SIZE_OF_SQUARE;
                // part that makes zoom
                if (Settings.ZOOMED)
                    graphics.fillRect(column * Settings.SIZE_OF_SQUARE + (int) Settings.GRAPHIC_SHIFT_COLUMN, row * Settings.SIZE_OF_SQUARE + (int) Settings.GRAPHIC_SHIFT_COLUMN, sizeOfSquare, sizeOfSquare);
                else
                    graphics.fillRect(column * Settings.SIZE_OF_SQUARE, row * Settings.SIZE_OF_SQUARE, sizeOfSquare, sizeOfSquare);
            }
        }
    }
}
