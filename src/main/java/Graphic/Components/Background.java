package Graphic.Components;

import Utils.Colors;
import Utils.Settings;

import java.awt.Graphics2D;

public class Background {
    public Graphics2D graphics;

    /**
     * Sets background of {@link Graphics2D} object. Because default {@link Graphics2D} setBackground method
     * doesn't work.
     */
    public void setBackground(boolean legend) { // TODO is boolean legend necessary?
        graphics.setColor(Colors.BACKGROUND.getColor());
        int width = legend ? Settings.BACKGROUND_WIDTH : Settings.GRID_WIDTH;
        graphics.fillRect(0, 0, width, Settings.BACKGROUND_HEIGHT);
    }
}
