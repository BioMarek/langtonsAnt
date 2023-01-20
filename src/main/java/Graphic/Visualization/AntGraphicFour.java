package Graphic.Visualization;

import Graphic.AntVisualization;
import Graphic.Components.Background;
import Graphic.Components.Cross;
import Graphic.Components.SquareLegend;
import Logic.Ant.SquareAnt;
import Utils.Colors;
import Utils.Settings;

import java.awt.Graphics2D;

public class AntGraphicFour implements AntVisualization {
    private Graphics2D graphics;
    private final SquareAnt squareAntTopLeft;
    private final SquareAnt squareAntTopRight;
    private final SquareAnt squareAntBottomLeft;
    private final SquareAnt squareAntBottomRight;
    private final SquareLegend squareLegend;
    private final Background background;
    private final Cross cross;
    private int imageCount = 0;

    public AntGraphicFour(SquareAnt squareAntTopLeft, SquareAnt squareAntTopRight, SquareAnt squareAntBottomLeft, SquareAnt squareAntBottomRight) {
        this.squareAntTopLeft = squareAntTopLeft;
        this.squareAntTopRight = squareAntTopRight;
        this.squareAntBottomLeft = squareAntBottomLeft;
        this.squareAntBottomRight = squareAntBottomRight;
        this.squareLegend = new SquareLegend();
        this.background = new Background();
        this.cross = new Cross();
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
        squareAntTopLeft.nextMoves();
        squareAntTopRight.nextMoves();
        squareAntBottomLeft.nextMoves();
        squareAntBottomRight.nextMoves();
    }

    /**
     * Draws image that can be presented as frame of animation. Contains info on the right side of image.
     */
    @Override
    public void drawPresentation(Graphics2D graphics) {
        this.graphics = graphics;
        squareLegend.graphics = graphics;
        background.graphics = graphics;
        cross.graphics = graphics;
        background.setBackground(true);
        drawGrid();
        squareLegend.drawInfoForFourImages(squareAntTopLeft, squareAntTopRight, squareAntBottomLeft, squareAntBottomRight);
        cross.drawCross();
    }

    @Override
    public boolean stopped() {
        return squareAntTopLeft.stopped && squareAntTopRight.stopped && squareAntBottomLeft.stopped && squareAntBottomRight.stopped;
    }

    public void drawGrid() {
        drawAnt(squareAntTopLeft, 0, 0);
        drawAnt(squareAntTopRight, 0, Settings.BACKGROUND_WIDTH / 2);
        drawAnt(squareAntBottomLeft, Settings.BACKGROUND_HEIGHT / 2, 0);
        drawAnt(squareAntBottomRight, Settings.BACKGROUND_HEIGHT / 2, Settings.BACKGROUND_WIDTH / 2);
    }

    /**
     * Converts grid of numbers to {@link Graphics2D}.
     */
    public void drawAnt(SquareAnt squareAnt, int startRow, int startColumn) {
        int borderPadding = Settings.IMAGE_PADDING / Settings.SIZE_OF_SQUARE;
        for (int row = 0; row < squareAnt.gridRows - borderPadding; row++) {
            for (int column = 0; column < squareAnt.gridColumns - borderPadding; column++) {
                if (squareAnt.grid[row][column] == -1)
                    continue;
                Colors.setColor(graphics, squareAnt.grid[row + borderPadding / 2][column + borderPadding / 2]);
                graphics.fillRect(startColumn + (column * Settings.SIZE_OF_SQUARE), startRow + (row * Settings.SIZE_OF_SQUARE), Settings.SIZE_OF_SQUARE, Settings.SIZE_OF_SQUARE);
            }
        }
    }
}
