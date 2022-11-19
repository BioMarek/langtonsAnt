package Graphic.Components;

import Utils.Colors;
import Utils.Settings;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class Cross {
    public Graphics2D graphics;

    /**
     * Draws white cross to divide four ant images.
     */
    public void drawCross() {
        graphics.setColor(Colors.TEXT.getColor());
        graphics.setStroke(new BasicStroke(2f));
        graphics.drawLine(Settings.BACKGROUND_WIDTH / 2, 0, Settings.BACKGROUND_WIDTH / 2, Settings.BACKGROUND_HEIGHT);
        graphics.drawLine(0, Settings.BACKGROUND_HEIGHT / 2, Settings.BACKGROUND_WIDTH, Settings.BACKGROUND_HEIGHT / 2);
    }
}
