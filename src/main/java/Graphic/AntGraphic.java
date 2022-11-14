package Graphic;

import Logic.Ant;
import Utils.Colors;
import Utils.Settings;
import Utils.Util;

import java.awt.Graphics2D;

/**
 * Creates graphic for ant grid and handles legend.
 */
public class AntGraphic {
    private Graphics2D graphics;
    public final int squares = (Settings.SIZE_IN_PIXELS + Settings.IMAGE_PADDING) / Settings.SIZE_OF_SQUARE;
    private final Ant ant;
    private final Legend legend;

    public AntGraphic(Ant ant) {
        this.ant = ant;
        this.legend = new Legend(ant);
    }

    /**
     * Draws simple image of end result that can be saved as file.
     */
    public void drawImage(Graphics2D graphics) {
        this.graphics = graphics;
        legend.graphics = graphics;
        setBackground();
        draw();
    }

    /**
     * Draws image that can be presented as frame of animation. Contains info on the right side of image.
     */
    public void drawPresentation(Graphics2D graphics) {
        this.graphics = graphics;
        legend.graphics = graphics;
        setBackgroundPresentation();
        draw();

        if (Settings.INFO_FOR_4_IMAGES)
            legend.drawInfoForForImages();
        else {
            legend.drawLegend();
        }
    }

    /**
     * Converts grid of numbers to {@link Graphics2D}.
     */
    public void draw() {
        int borderPadding = Settings.IMAGE_PADDING / 2;
        for (int row = 0; row < squares; row++) {
            for (int column = 0; column < squares; column++) {
                Colors.setColor(graphics, ant.grid[row + borderPadding][column + borderPadding]);
                int sizeOfSquare = Settings.SHOW_GRID ? Settings.SIZE_OF_SQUARE - 1 : Settings.SIZE_OF_SQUARE;
                // part that makes zoom
                if (Settings.ZOOMED)
                    graphics.fillRect(column * Settings.SIZE_OF_SQUARE + Settings.GRAPHIC_SHIFT, row * Settings.SIZE_OF_SQUARE + Settings.GRAPHIC_SHIFT, sizeOfSquare, sizeOfSquare);
                else
                    graphics.fillRect(column * Settings.SIZE_OF_SQUARE, row * Settings.SIZE_OF_SQUARE, sizeOfSquare, sizeOfSquare);
            }
        }
    }

    /**
     * Sets background of {@link Graphics2D} object. Because default {@link Graphics2D} setBackground method
     * doesn't work.
     */
    public void setBackground() {
        graphics.setColor(Colors.BACKGROUND.getColor());
        int sizeInPixels = Util.sizeDivisibleByTwo(Settings.SHOW_GRID ? Settings.SIZE_IN_PIXELS + 1 : Settings.SIZE_IN_PIXELS);
        graphics.fillRect(0, 0, sizeInPixels, sizeInPixels);
    }

    /**
     * Sets background of {@link Graphics2D} object for presentation animation the background is wider to accommodate for
     * information displayed on the right side.
     */
    public void setBackgroundPresentation() {
        graphics.setColor(Colors.BACKGROUND.getColor());
        int sizeInPixels = Util.sizeDivisibleByTwo(Settings.SHOW_GRID ? Settings.SIZE_IN_PIXELS + 1 : Settings.SIZE_IN_PIXELS);
        if (Settings.INFO_FOR_4_IMAGES)
            graphics.fillRect(0, 0, sizeInPixels, sizeInPixels);
        else
            graphics.fillRect(0, 0, sizeInPixels + Settings.SIZE_OF_LEGEND, sizeInPixels);
    }
}
