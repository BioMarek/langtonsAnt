package Graphic;

import Utils.Colors;
import Utils.Settings;

import java.awt.Graphics2D;

public class Background {
    public Graphics2D graphics;

    /**
     * Sets background of {@link Graphics2D} object. Because default {@link Graphics2D} setBackground method
     * doesn't work.
     */
    public void setBackground() {
        graphics.setColor(Colors.BACKGROUND.getColor());
        graphics.fillRect(0, 0, Settings.BACKGROUND_WIDTH, Settings.BACKGROUND_HEIGHT);
    }
}
