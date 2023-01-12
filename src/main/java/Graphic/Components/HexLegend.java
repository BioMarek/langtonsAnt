package Graphic.Components;

import Logic.Ant;
import Logic.HexAnt;
import Utils.Colors;
import Utils.Settings;
import Utils.Util;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class HexLegend {
    public Graphics2D graphics;
    private Ant hexAnt;
    private final int fontUnit = Settings.BACKGROUND_HEIGHT / 60;

    public void drawLegend(Ant hexAnt) {
        this.hexAnt = hexAnt;
        turnAntiAliasingOn(true);
        drawInfo();
//        drawDiagram(); // TODO implement
        turnAntiAliasingOn(false);
    }

    /**
     * Displays information about ant rule being animated and number of steps ant has made.
     */
    public void drawInfo() {
        graphics.setColor(Colors.TEXT.getColor());
        graphics.setFont(new Font("Arial", Font.BOLD, (int) (fontUnit * 1.2)));
        graphics.drawString("Rule:   ", Settings.LEGEND_START_X + fontUnit, fontUnit * 2);
        graphics.drawString(hexAnt.rule.getAttributeString(fontUnit).getIterator(), Settings.LEGEND_START_X + fontUnit * 5, fontUnit * 2);
        graphics.drawString("Steps: " + Util.numberFormatter(hexAnt.steps), Settings.LEGEND_START_X + fontUnit, fontUnit * 4);

        graphics.setStroke(new BasicStroke(3f));
        graphics.drawLine(Settings.LEGEND_START_X, 0, Settings.LEGEND_START_X, Settings.BACKGROUND_HEIGHT);
    }


    private void turnAntiAliasingOn(boolean isOn) {
        if (isOn) {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        } else {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        }
    }
}
