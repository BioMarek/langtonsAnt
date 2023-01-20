package Graphic.Components;

import Logic.Ant.Ant;
import Logic.Ant.SquareAnt;
import Utils.Colors;
import Utils.Settings;
import Utils.Util;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.RoundRectangle2D;

public class SquareLegend {
    public Graphics2D graphics;
    private SquareAnt ant;

    public void drawLegend(Ant ant) {
        this.ant = (SquareAnt) ant;
        turnAntiAliasingOn(true);
        drawInfo();
        drawDiagram();
        turnAntiAliasingOn(false);
    }

    /**
     * Displays information about ant rule being animated and number of steps ant has made.
     */
    public void drawInfo() {
        int fontUnit = Settings.BACKGROUND_HEIGHT / 60;
        graphics.setColor(Colors.TEXT.getColor());
        graphics.setFont(new Font("Arial", Font.BOLD, (int) (fontUnit * 1.2)));
        graphics.drawString("Rule:   " + ant.rule.getSquareRule(), Settings.LEGEND_START_X + fontUnit, fontUnit * 2);
        graphics.drawString("Steps: " + Util.numberFormatter(ant.steps), Settings.LEGEND_START_X + fontUnit, fontUnit * 4);

        graphics.setStroke(new BasicStroke(3f));
        graphics.drawLine(Settings.LEGEND_START_X, 0, Settings.LEGEND_START_X, Settings.BACKGROUND_HEIGHT);
    }

    /**
     * Info is just name of rule written over grid in big font, used when there are 4 images per screen
     */
    public void drawInfoForFourImages(SquareAnt squareAntTopLeft, SquareAnt squareAntTopRight, SquareAnt squareAntBottomLeft, SquareAnt squareAntBottomRight) {
        // TODO handle new Rule
        int fontUnit = Settings.BACKGROUND_HEIGHT / 90;
        graphics.setColor(Colors.TEXT.getColor());
        graphics.setFont(new Font("Arial", Font.BOLD, (int) (fontUnit * 1.5)));
        graphics.drawString("Rule: " + new String(squareAntTopLeft.charRule), fontUnit * 2, fontUnit * 3);
        graphics.drawString("Rule: " + new String(squareAntTopRight.charRule), fontUnit * 2 + Settings.BACKGROUND_WIDTH / 2, fontUnit * 3);
        graphics.drawString("Rule: " + new String(squareAntBottomLeft.charRule), fontUnit * 2, fontUnit * 3 + Settings.BACKGROUND_HEIGHT / 2);
        graphics.drawString("Rule: " + new String(squareAntBottomRight.charRule), fontUnit * 2 + Settings.BACKGROUND_WIDTH / 2, fontUnit * 3 + Settings.BACKGROUND_HEIGHT / 2);
    }

    /**
     * Draws legend on the right side of the window. works only for SIZE_IN_PIXELS = 1000 because arrows in legend.
     */
    public void drawDiagram() {
        int squareSize = Settings.BACKGROUND_HEIGHT / 25;
        int topPadding = squareSize * 4;
        int gap = squareSize * 2;
        int ruleHalf = (ant.ruleLength() % 2 == 0) ? ant.ruleLength() / 2 : ant.ruleLength() / 2 + 1; // ensures left column is longer than right one

        graphics.setColor(Colors.TEXT.getColor());
        graphics.draw(new RoundRectangle2D.Double(Settings.LEGEND_START_X + 3.5 * squareSize,
                squareSize * 3.5,
                squareSize * 3,
                squareSize * ruleHalf * 2, 20, 20));

        drawLeftArrow(Settings.LEGEND_START_X + squareSize * 19 / 4, squareSize * 7 / 2);
        drawRightArrow(Settings.LEGEND_START_X + squareSize * 19 / 4, squareSize * 7 / 2 + ruleHalf * gap);

        for (int i = 0; i < ruleHalf; i++) {
            if (i != ruleHalf - 1)
                drawDownArrow(Settings.LEGEND_START_X + squareSize * 7 / 2, i * gap + squareSize * 11 / 2);
            drawFilledRect(Settings.LEGEND_START_X + squareSize * 3, i * gap + topPadding, i);
            drawLegendInnerArrows(i, squareSize, 3.3, false);
        }

        for (int i = ruleHalf; i < ant.ruleLength(); i++) {
            if (i != ant.ruleLength() - 1)
                drawUpArrow(Settings.LEGEND_START_X + squareSize * 13 / 2, (i - ruleHalf) * gap + squareSize * 11 / 2);
            drawFilledRect(Settings.LEGEND_START_X + squareSize * 6, (i - ruleHalf) * gap + topPadding, i);
            drawLegendInnerArrows(i - ruleHalf, squareSize, 6.3, true);
        }
    }

    private void drawLegendInnerArrows(int i, int squareSize, double xLegendColumnCoefficient, boolean reverse) {
        graphics.setColor(Colors.TEXT.getColor());
        char ruleArrow = reverse ? ant.charRule[ant.ruleLength() - i - 1] : ant.charRule[i];
        if (ruleArrow == 'L') {
            drawInnerLeftArrow((int) (Settings.LEGEND_START_X + squareSize * xLegendColumnCoefficient), (int) (i * squareSize * 2 + squareSize * 4.3));
        } else
            drawInnerRightArrow((int) (Settings.LEGEND_START_X + squareSize * xLegendColumnCoefficient), (int) (i * squareSize * 2 + squareSize * 4.3));
    }

    private void drawFilledRect(int x, int y, int i) {
        int squareSize = Settings.BACKGROUND_HEIGHT / 25;
        int fillSquareSize = squareSize - 1;
        graphics.drawRect(x, y, squareSize, squareSize);
        graphics.setColor(Colors.BACKGROUND.getColor()); // drawing background square over white rectangle with arrows
        graphics.fillRect(x + 1, y + 1, fillSquareSize, fillSquareSize);
        graphics.setColor(Colors.getColor(i));
        if (i != 0)
            graphics.fillRect(x + 1, y + 1, fillSquareSize, fillSquareSize);
        else {
            graphics.fillPolygon(new int[]{x + 1, x - 1 + squareSize, x + 1}, new int[]{y + 1, y + 1, y - 1 + squareSize}, 3);
        }
    }

    private void drawDownArrow(int x, int y) {
        graphics.setColor(Colors.TEXT.getColor());
        graphics.drawLine(x, y + 15, x - 10, y);
        graphics.drawLine(x, y + 15, x + 10, y);
    }

    private void drawUpArrow(int x, int y) {
        graphics.setColor(Colors.TEXT.getColor());
        graphics.drawLine(x, y - 15, x - 10, y);
        graphics.drawLine(x, y - 15, x + 10, y);
    }

    private void drawRightArrow(int x, int y) {
        graphics.drawLine(x + 20, y, x, y + 10);
        graphics.drawLine(x + 20, y, x, y - 10);
    }

    private void drawLeftArrow(int x, int y) {
        graphics.drawLine(x, y, x + 20, y + 10);
        graphics.drawLine(x, y, x + 20, y - 10);
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
