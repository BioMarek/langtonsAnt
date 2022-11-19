package Graphic;

import Graphic.Visualization.Background;
import Graphic.Visualization.Cross;
import Graphic.Visualization.Legend;
import Logic.Ant;
import Utils.Colors;
import Utils.Settings;

import java.awt.Graphics2D;

public class AntGraphicFour implements AntVisualization {
    private Graphics2D graphics;
    private final Ant ant;
    private final Legend legend;
    private final Background background;
    private final Cross cross;
    private int imageCount = 0;

    public AntGraphicFour(Ant ant) {
        this.ant = ant;
        this.legend = new Legend(ant);
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
        ant.nextMoves();
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
                    graphics.fillRect(column * Settings.SIZE_OF_SQUARE + (int) Settings.GRAPHIC_SHIFT_COLUMN, row * Settings.SIZE_OF_SQUARE + (int) Settings.GRAPHIC_SHIFT_COLUMN, sizeOfSquare, sizeOfSquare);
                else
                    graphics.fillRect(column * Settings.SIZE_OF_SQUARE, row * Settings.SIZE_OF_SQUARE, sizeOfSquare, sizeOfSquare);
            }
        }
    }
}
