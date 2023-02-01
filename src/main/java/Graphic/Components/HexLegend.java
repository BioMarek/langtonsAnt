package Graphic.Components;

import Logic.Ant.Ant;
import Utils.Colors;
import Utils.Settings;
import Utils.Util;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.RoundRectangle2D;

public class HexLegend {
    public Graphics2D graphics;
    private Ant hexAnt;
    private final int fontUnit = Settings.BACKGROUND_HEIGHT / 60;

    public void drawLegend(Ant hexAnt) {
        this.hexAnt = hexAnt;
        turnAntiAliasingOn(true);
        drawInfo();
        drawDiagram();
        turnAntiAliasingOn(false);
    }

    /**
     * Displays information about ant rule being animated and number of steps ant has made.
     */
    public void drawInfo() {
        graphics.setColor(Colors.TEXT.getColor());
        graphics.setFont(new Font("Arial", Font.BOLD, (int) (fontUnit * 1.1)));
        graphics.drawString("Rule: ", Settings.LEGEND_START_X + fontUnit, fontUnit * 2);
        graphics.drawString(hexAnt.rule.getAttributeString(fontUnit).getIterator(), Settings.LEGEND_START_X + (int) (fontUnit * 4.5), fontUnit * 2);
        graphics.drawString("Steps: " + Util.numberFormatter(hexAnt.steps), Settings.LEGEND_START_X + fontUnit, fontUnit * 4);


        graphics.setStroke(new BasicStroke(3f * Settings.HEX_MULTIPLIER));
        graphics.drawLine(Settings.LEGEND_START_X, 0, Settings.LEGEND_START_X, Settings.BACKGROUND_HEIGHT);
    }

    /**
     * Draws legend on the right side of the window. works only for SIZE_IN_PIXELS = 1000 because arrows in legend.
     */
    public void drawDiagram() {
        int squareSize = Settings.BACKGROUND_HEIGHT / 25;
        int topPadding = squareSize * 4;
        int gap = squareSize * 2;
        int ruleHalf = (hexAnt.ruleLength() % 2 == 0) ? hexAnt.ruleLength() / 2 : hexAnt.ruleLength() / 2 + 1; // ensures left column is longer than right one

        graphics.setColor(Colors.TEXT.getColor());
        graphics.draw(new RoundRectangle2D.Double(Settings.LEGEND_START_X + 3.5 * squareSize,
                squareSize * 3,
                squareSize * 3,
                squareSize * ruleHalf * 2, 20, 20));

        drawLeftArrow(Settings.LEGEND_START_X + squareSize * 19 / 4, squareSize * 6 / 2);
        drawRightArrow(Settings.LEGEND_START_X + squareSize * 19 / 4, squareSize * 6 / 2 + ruleHalf * gap);

        for (int i = 0; i < ruleHalf; i++) {
            if (i != ruleHalf - 1)
                drawDownArrow(Settings.LEGEND_START_X + squareSize * 7 / 2, i * gap + squareSize * 10 / 2);
            drawFillHex((int) (Settings.LEGEND_START_X + squareSize * 3.5), i * gap + topPadding, i);
            drawLegendInnerArrows(i, squareSize, 3.3, false);
        }

        for (int i = ruleHalf; i < hexAnt.ruleLength(); i++) {
            if (i != hexAnt.ruleLength() - 1)
                drawUpArrow(Settings.LEGEND_START_X + squareSize * 13 / 2, (i - ruleHalf) * gap + squareSize * 10 / 2);
            drawFillHex((int) (Settings.LEGEND_START_X + squareSize * 6.5), (i - ruleHalf) * gap + topPadding, i);
            drawLegendInnerArrows(i - ruleHalf, squareSize, 6.3, true);
        }
    }

    private void drawLegendInnerArrows(int i, int squareSize, double xLegendColumnCoefficient, boolean reverse) {
//        graphics.setColor(Colors.TEXT.getColor());
//        char ruleArrow = reverse ? hexAnt.charRule[hexAnt.ruleLength() - i - 1] : hexAnt.charRule[i];
//        if (ruleArrow == 'L') {
//            drawInnerLeftArrow((int) (Settings.LEGEND_START_X + squareSize * xLegendColumnCoefficient), (int) (i * squareSize * 2 + squareSize * 4.3));
//        } else
//            drawInnerRightArrow((int) (Settings.LEGEND_START_X + squareSize * xLegendColumnCoefficient), (int) (i * squareSize * 2 + squareSize * 4.3));
    }

    private void drawFillHex(int x, int y, int i) {
        int squareSize = Settings.BACKGROUND_HEIGHT / 35;
        int fillSquareSize =  (int) (squareSize * 0.875);

        // text background for outline
        Polygon hexagon = getHexagon(x, y, squareSize);
        graphics.setColor(Colors.TEXT.getColor());
        graphics.fillPolygon(hexagon);

        // background of fill
        hexagon = getHexagon(x, y, fillSquareSize);
        graphics.setColor(Colors.BACKGROUND.getColor());
        graphics.fillPolygon(hexagon);

        // fill
        graphics.setColor(Colors.getColor(i));
        hexagon = getHexagon(x, y, fillSquareSize);
        graphics.fillPolygon(hexagon);

        // first hexagon should be half background half first color
//        if (i != 0)
//            graphics.fillRect(x + 1, y + 1, fillSquareSize, fillSquareSize);
//        else {
//            graphics.fillPolygon(new int[]{x + 1, x - 1 + squareSize, x + 1}, new int[]{y + 1, y + 1, y - 1 + squareSize}, 3);
//        }
    }

    private void drawDownArrow(int x, int y) {
        graphics.setColor(Colors.TEXT.getColor());
        graphics.drawLine(x, y + 15 * Settings.HEX_MULTIPLIER, x - 10 * Settings.HEX_MULTIPLIER, y);
        graphics.drawLine(x, y + 15 * Settings.HEX_MULTIPLIER, x + 10 * Settings.HEX_MULTIPLIER, y);
    }

    private void drawUpArrow(int x, int y) {
        graphics.setColor(Colors.TEXT.getColor());
        graphics.drawLine(x, y - 15 * Settings.HEX_MULTIPLIER, x - 10 * Settings.HEX_MULTIPLIER, y);
        graphics.drawLine(x, y - 15 * Settings.HEX_MULTIPLIER, x + 10 * Settings.HEX_MULTIPLIER, y);
    }

    private void drawRightArrow(int x, int y) {
        graphics.drawLine(x + 20 * Settings.HEX_MULTIPLIER, y, x, y + 10 * Settings.HEX_MULTIPLIER);
        graphics.drawLine(x + 20 * Settings.HEX_MULTIPLIER, y, x, y - 10 * Settings.HEX_MULTIPLIER);
    }

    private void drawLeftArrow(int x, int y) {
        graphics.drawLine(x, y, x + 20 * Settings.HEX_MULTIPLIER, y + 10 * Settings.HEX_MULTIPLIER);
        graphics.drawLine(x, y, x + 20 * Settings.HEX_MULTIPLIER, y - 10 * Settings.HEX_MULTIPLIER);
    }

    private void drawInnerLeftArrow(int x, int y) {
        graphics.setStroke(new BasicStroke(2f));
        graphics.draw(new Arc2D.Double(x, y, 20, 20, -90, 225, Arc2D.OPEN));
        graphics.drawLine(x + 1, y + 1, x + 7, y - 6);
        graphics.drawLine(x + 1, y + 2, x + 9, y + 6);
        graphics.setStroke(new BasicStroke(3f));
    }

    private void drawInnerRightArrow(int x, int y) {
        graphics.setStroke(new BasicStroke(2f));
        graphics.draw(new Arc2D.Double(x, y, 20, 20, 45, 225, Arc2D.OPEN));
        graphics.drawLine(x + 19, y + 1, x + 10, y - 6);
        graphics.drawLine(x + 18, y + 1, x + 9, y + 6);
        graphics.setStroke(new BasicStroke(3f));
    }

    public Polygon getHexagon(int column, int row, int size) {
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++)
            p.addPoint((int) (column + size * Math.sin(i * 2 * Math.PI / 6)),
                    (int) (row + size * Math.cos(i * 2 * Math.PI / 6)));
        return p;
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
