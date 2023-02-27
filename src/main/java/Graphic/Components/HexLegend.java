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

import static Utils.Util.hexagonalPolygon;

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
     * Draws legend on the right side of the window. Works for all resolutions thanks to adj().
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
            drawFilledHexagon((int) (Settings.LEGEND_START_X + squareSize * 3.5), i * gap + topPadding, i);
            drawLegendInnerArrows(i, squareSize, 3.3, false);
        }

        for (int i = ruleHalf; i < hexAnt.ruleLength(); i++) {
            if (i != hexAnt.ruleLength() - 1)
                drawUpArrow(Settings.LEGEND_START_X + squareSize * 13 / 2, (i - ruleHalf) * gap + squareSize * 10 / 2);
            drawFilledHexagon((int) (Settings.LEGEND_START_X + squareSize * 6.5), (i - ruleHalf) * gap + topPadding, i);
            drawLegendInnerArrows(i - ruleHalf, squareSize, 6.3, true);
        }
    }

    private void drawLegendInnerArrows(int i, int squareSize, double xLegendColumnCoefficient, boolean reverse) {
        graphics.setColor(Colors.TEXT.getColor());
        String ruleArrow = reverse ? hexAnt.rule.getElement(hexAnt.ruleLength() - i - 1) : hexAnt.rule.getElement(i);
        switch (ruleArrow) {
            case "R1" ->
                    drawInnerR1Arrow((int) (Settings.LEGEND_START_X + squareSize * xLegendColumnCoefficient), (int) (i * squareSize * 2 + squareSize * 3.9));
            case "L1" ->
                    drawInnerL1Arrow((int) (Settings.LEGEND_START_X + squareSize * xLegendColumnCoefficient), (int) (i * squareSize * 2 + squareSize * 3.9));
            case "L2" ->
                    drawInnerL2Arrow((int) (Settings.LEGEND_START_X + squareSize * xLegendColumnCoefficient), (int) (i * squareSize * 2 + squareSize * 3.9));
            case "R2" ->
                    drawInnerR2Arrow((int) (Settings.LEGEND_START_X + squareSize * xLegendColumnCoefficient), (int) (i * squareSize * 2 + squareSize * 3.9));
            case "N" ->
                    drawInnerNArrow((int) (Settings.LEGEND_START_X + squareSize * xLegendColumnCoefficient), (int) (i * squareSize * 2 + squareSize * 3.9));
            case "U" ->
                    drawInnerUArrow((int) (Settings.LEGEND_START_X + squareSize * xLegendColumnCoefficient), (int) (i * squareSize * 2 + squareSize * 3.9));
        }
    }

    private void drawFilledHexagon(int x, int y, int position) {
        int squareSize = Settings.BACKGROUND_HEIGHT / 35;
        int fillSquareSize = (int) (squareSize * 0.875);

        // text background for outline
        Polygon hexagon = hexagonalPolygon(x, y, squareSize);
        graphics.setColor(Colors.TEXT.getColor());
        graphics.fillPolygon(hexagon);

        // background of fill
        hexagon = hexagonalPolygon(x, y, fillSquareSize);
        graphics.setColor(Colors.BACKGROUND.getColor());
        graphics.fillPolygon(hexagon);

        // fill
        if (Settings.HEX_ALTERNATIVE_COLOR)
            graphics.setColor(Colors.getVioletAlternativeColor(position));
        else
            graphics.setColor(Colors.getColor(position));
        hexagon = hexagonalPolygon(x, y, fillSquareSize);
        graphics.fillPolygon(hexagon);

        // first hexagon should be half background half first color
        if (position == 0) {
            graphics.setColor(Colors.BACKGROUND.getColor());
            int[] xCoordinates = new int[4];
            int[] yCoordinates = new int[4];
            for (int j = 2; j < 6; j++) {
                xCoordinates[j - 2] = (int) (x + fillSquareSize * Math.sin(j * 2 * Math.PI / 6));
                yCoordinates[j - 2] = (int) (y + fillSquareSize * Math.cos(j * 2 * Math.PI / 6));
            }
            graphics.fillPolygon(xCoordinates, yCoordinates, 4);
        }
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

    private void drawInnerR1Arrow(int x, int y) {
        graphics.setStroke(new BasicStroke(2f * Settings.HEX_MULTIPLIER));
        graphics.drawLine(x - adj(2), y - adj(5), x + adj(7), y - adj(5));
        graphics.draw(new Arc2D.Double(x - adj(3), y - adj(5), adj(20), adj(15), 90, -80, Arc2D.OPEN));
        graphics.drawLine(x + adj(16), y + adj(2), x + adj(16), y + adj(13));
        graphics.drawLine(x + adj(16), y + adj(12), x + adj(22), y - adj(1));
        graphics.drawLine(x + adj(16), y + adj(12), x + adj(9), y + adj(1));
        graphics.setStroke(new BasicStroke(3f * Settings.HEX_MULTIPLIER));
    }

    private void drawInnerL1Arrow(int x, int y) {
        graphics.setStroke(new BasicStroke(2f * Settings.HEX_MULTIPLIER));
        graphics.drawLine(x - adj(2), y + adj(15), x + adj(7), y + adj(15));
        graphics.draw(new Arc2D.Double(x - adj(3), y + adj(1), adj(20), adj(15), 0, -80, Arc2D.OPEN));
        graphics.drawLine(x + adj(18), y + adj(7), x + adj(19), y - adj(3));
        graphics.drawLine(x + adj(24), y + adj(11), x + adj(19), y - adj(3));
        graphics.drawLine(x + adj(11), y + adj(9), x + adj(19), y - adj(3));
        graphics.setStroke(new BasicStroke(3f * Settings.HEX_MULTIPLIER));
    }

    private void drawInnerR2Arrow(int x, int y) {
        graphics.setStroke(new BasicStroke(2f * Settings.HEX_MULTIPLIER));
        graphics.drawLine(x, y - adj(5), x + adj(9), y - adj(5));
        graphics.draw(new Arc2D.Double(x, y - adj(5), adj(20), adj(15), 90, -120, Arc2D.OPEN));
        graphics.drawLine(x + adj(5), y + adj(14), x + adj(17), y + adj(7));
        graphics.drawLine(x + adj(5), y + adj(14), x + adj(19), y + adj(14));
        graphics.drawLine(x + adj(5), y + adj(14), x + adj(12), y + adj(2));
        graphics.setStroke(new BasicStroke(3f * Settings.HEX_MULTIPLIER));
    }

    private void drawInnerL2Arrow(int x, int y) {
        graphics.setStroke(new BasicStroke(2f * Settings.HEX_MULTIPLIER));
        graphics.drawLine(x, y + adj(15), x + adj(9), y + adj(15));
        graphics.draw(new Arc2D.Double(x, y, adj(20), adj(15), -90, 120, Arc2D.OPEN));
        graphics.drawLine(x + adj(4), y - adj(6), x + adj(17), y + adj(2));
        graphics.drawLine(x + adj(4), y - adj(6), x + adj(19), y - adj(5));
        graphics.drawLine(x + adj(4), y - adj(6), x + adj(12), y + adj(8));
        graphics.setStroke(new BasicStroke(3f * Settings.HEX_MULTIPLIER));
    }

    private void drawInnerNArrow(int x, int y) {
        graphics.setStroke(new BasicStroke(2f * Settings.HEX_MULTIPLIER));
        graphics.drawLine(x - adj(2), y + adj(5), x + adj(21), y + adj(5));
        graphics.drawLine(x + adj(9), y + adj(11), x + adj(21), y + adj(5));
        graphics.drawLine(x + adj(9), y - adj(1), x + adj(21), y + adj(5));
        graphics.setStroke(new BasicStroke(3f * Settings.HEX_MULTIPLIER));
    }

    private void drawInnerUArrow(int x, int y) {
        graphics.setStroke(new BasicStroke(2f * Settings.HEX_MULTIPLIER));
        graphics.drawLine(x, y, x + adj(9), y);
        graphics.drawLine(x, y + adj(15), x + adj(9), y + adj(15));
        graphics.draw(new Arc2D.Double(x, y, adj(20), adj(15), -90, 180, Arc2D.OPEN));
        graphics.drawLine(x, y, x + adj(12), y - adj(6));
        graphics.drawLine(x, y, x + adj(12), y + adj(6));
        graphics.setStroke(new BasicStroke(3f * Settings.HEX_MULTIPLIER));
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

    /**
     * Adjusts pixel position based on HEX_MULTIPLIER
     *
     * @param num number to adjust
     * @return adjust number
     */
    private int adj(int num) {
        return num * Settings.HEX_MULTIPLIER;
    }
}
