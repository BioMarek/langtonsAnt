package Graphic.Visualization;

import Graphic.AntVisualization;
import Graphic.Components.Background;
import Graphic.Components.Cross;
import Graphic.Components.Legend;
import Logic.Ant;
import Utils.Colors;
import Utils.Settings;

import java.awt.Graphics2D;

public class AntGraphicFour implements AntVisualization {
    private Graphics2D graphics;
    private final Ant antTopLeft;
    private final Ant antTopRight;
    private final Ant antBottomLeft;
    private final Ant antBottomRight;
    private final Legend legend;
    private final Background background;
    private final Cross cross;
    private int imageCount = 0;

    public AntGraphicFour(Ant antTopLeft, Ant antTopRight, Ant antBottomLeft, Ant antBottomRight) {
        this.antTopLeft = antTopLeft;
        this.antTopRight = antTopRight;
        this.antBottomLeft = antBottomLeft;
        this.antBottomRight = antBottomRight;
        this.legend = new Legend(antTopLeft);
        this.background = new Background();
        this.cross = new Cross();
    }

    /**
     * Draws simple image of end result that can be saved as file.
     */
    public void drawImage(Graphics2D graphics) {
        this.graphics = graphics;
        legend.graphics = graphics;
        background.graphics = graphics;
        background.setBackground(false);
        draw();
    }

    @Override
    public void createNextFrame() {
        System.out.println("creating frame " + imageCount++);
        antTopLeft.nextMoves();
        antTopRight.nextMoves();
        antBottomLeft.nextMoves();
        antBottomRight.nextMoves();
    }

    /**
     * Draws image that can be presented as frame of animation. Contains info on the right side of image.
     */
    @Override
    public void drawPresentation(Graphics2D graphics) {
        this.graphics = graphics;
        legend.graphics = graphics;
        background.graphics = graphics;
        cross.graphics = graphics;
        background.setBackground(true);
        legend.drawInfoForForImages();
        draw();
        cross.drawCross();
    }

    @Override
    public boolean stopped() {
        return antTopLeft.stopped && antTopRight.stopped && antBottomLeft.stopped && antBottomRight.stopped;
    }

    public void draw() {
        drawAnt(antTopLeft, 0, 0);
        drawAnt(antTopRight, 0, Settings.BACKGROUND_WIDTH / 2);
        drawAnt(antBottomLeft, Settings.BACKGROUND_HEIGHT / 2, 0);
        drawAnt(antBottomRight, Settings.BACKGROUND_HEIGHT / 2, Settings.BACKGROUND_WIDTH / 2);
    }

    /**
     * Converts grid of numbers to {@link Graphics2D}.
     */
    public void drawAnt(Ant ant, int startRow, int startColumn) {
        int borderPadding = Settings.IMAGE_PADDING / Settings.SIZE_OF_SQUARE;
        for (int row = 0; row < ant.gridRows - borderPadding; row++) {
            for (int column = 0; column < ant.gridColumns - borderPadding; column++) {
                Colors.setColor(graphics, ant.grid[row + borderPadding / 2][column + borderPadding / 2]);
                graphics.fillRect(startColumn + (column * Settings.SIZE_OF_SQUARE), startRow + (row * Settings.SIZE_OF_SQUARE), Settings.SIZE_OF_SQUARE, Settings.SIZE_OF_SQUARE);
            }
        }
    }
}
