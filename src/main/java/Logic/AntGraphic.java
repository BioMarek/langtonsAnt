package Logic;

import Utils.Colors;
import Utils.ColorsPicker;
import Utils.Settings;
import Utils.Util;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.RoundRectangle2D;

public class AntGraphic {
    private Graphics2D graphics;
    public final int squares = Settings.SIZE_IN_PIXELS / Settings.SIZE_OF_SQUARE;
    private final Ant ant;

    public AntGraphic(Ant ant) {
        this.ant = ant;
    }

    /**
     * Draws image that can be saved as file.
     */
    public void drawImage(Graphics2D graphics) {
        this.graphics = graphics;
        setBackground();
        draw();
    }

    /**
     * Draws image that can be presented as frame of animation. Contains info on the right side of image.
     */
    public void drawPresentation(Graphics2D graphics) {
        this.graphics = graphics;
        setBackgroundPresentation();
        draw();

        if (Settings.INFO_FOR_4_IMAGES)
            drawInfoForForImages();
        else {
            turnAntiAliasingOn(true);
            drawInfo();
            drawLegend();
            turnAntiAliasingOn(false);
        }
    }

    /**
     * Converts grid of numbers to {@link Graphics2D}.
     */
    public void draw() {
        int borderPadding = Settings.IMAGE_PADDING / 2;
        for (int row = 0; row < squares; row++) {
            for (int column = 0; column < squares; column++) {
                ColorsPicker.setColor(graphics, ant.grid[row + borderPadding][column + borderPadding]);
                int sizeOfSquare = Settings.SHOW_GRID ? Settings.SIZE_OF_SQUARE - 1 : Settings.SIZE_OF_SQUARE;
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
            graphics.fillRect(0, 0, sizeInPixels + Settings.SIZE_IN_PIXELS / 3, sizeInPixels);
    }

    /**
     * Displays information about ant rule being animated and number of steps ant has made.
     */
    public void drawInfo() {
        int fontUnit = Settings.SIZE_IN_PIXELS / 60;
        graphics.setColor(Colors.TEXT.getColor());
        graphics.setFont(new Font("Arial", Font.BOLD, (int) (fontUnit * 1.2)));
        graphics.drawString("Rule:   " + new String(ant.rule), Settings.SIZE_IN_PIXELS + fontUnit, fontUnit * 2);
        graphics.drawString("Steps: " + Util.numberFormatter(ant.steps), Settings.SIZE_IN_PIXELS + fontUnit, fontUnit * 4);

        graphics.setStroke(new BasicStroke(3f));
        graphics.drawLine(Settings.SIZE_IN_PIXELS, 0, Settings.SIZE_IN_PIXELS, Settings.SIZE_IN_PIXELS);
    }

    /**
     * Info is just name of rule written over grid in big font, used when there are 4 images per screen
     */
    public void drawInfoForForImages() {
        int fontUnit = Settings.SIZE_IN_PIXELS / 60;
        graphics.setColor(Colors.TEXT.getColor());
        graphics.setFont(new Font("Arial", Font.BOLD, (int) (fontUnit * 1.5)));
        graphics.drawString("Rule: " + new String(ant.rule), fontUnit * 2, fontUnit * 3);
    }

    public void drawLegend() {
        int squareSize = Settings.SIZE_IN_PIXELS / 25;
        int topPadding = squareSize * 4;
        int gap = squareSize * 2;
        int ruleHalf = (ant.rule.length % 2 == 0) ? ant.rule.length / 2 : ant.rule.length / 2 + 1; // ensures left column is longer than right one

        graphics.setColor(Colors.TEXT.getColor());
        graphics.draw(new RoundRectangle2D.Double(Settings.SIZE_IN_PIXELS + 2.5 * squareSize,
                squareSize * 3.5,
                squareSize * 3,
                squareSize * ruleHalf * 2, 20, 20));

        drawLeftArrow(Settings.SIZE_IN_PIXELS + squareSize * 15 / 4, squareSize * 7 / 2);
        drawRightArrow(Settings.SIZE_IN_PIXELS + squareSize * 15 / 4, squareSize * 7 / 2 + ruleHalf * gap);

        for (int i = 0; i < ruleHalf; i++) {
            if (i != ruleHalf - 1)
                drawDownArrow(Settings.SIZE_IN_PIXELS + squareSize * 5 / 2, i * gap + squareSize * 11 / 2);
            drawFilledRect(Settings.SIZE_IN_PIXELS + gap, i * gap + topPadding, i);
            drawLegendInnerArrows(i, squareSize, 2.3, false);
        }

        for (int i = ruleHalf; i < ant.rule.length; i++) {
            if (i != ant.rule.length - 1)
                drawUpArrow(Settings.SIZE_IN_PIXELS + squareSize * 11 / 2, (i - ruleHalf) * gap + squareSize * 11 / 2);
            drawFilledRect(Settings.SIZE_IN_PIXELS + squareSize * 5, (i - ruleHalf) * gap + topPadding, i);
            drawLegendInnerArrows(i - ruleHalf, squareSize, 5.2, true);
        }
    }

    public void drawLegendInnerArrows(int i, int squareSize, double xLegendColumnCoefficient, boolean reverse) {
        graphics.setColor(Colors.TEXT.getColor());
        char ruleArrow = reverse ? ant.rule[ant.rule.length - i - 1] : ant.rule[i];
        if (ruleArrow == 'L') {
            drawInnerLeftArrow((int) (Settings.SIZE_IN_PIXELS + squareSize * xLegendColumnCoefficient), (int) (i * squareSize * 2 + squareSize * 4.3));
        } else
            drawInnerRightArrow((int) (Settings.SIZE_IN_PIXELS + squareSize * xLegendColumnCoefficient), (int) (i * squareSize * 2 + squareSize * 4.3));
    }

    public void drawFilledRect(int x, int y, int i) {
        int squareSize = Settings.SIZE_IN_PIXELS / 25;
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

    public void drawDownArrow(int x, int y) {
        graphics.setColor(Colors.TEXT.getColor());
        graphics.drawLine(x, y + 15, x - 10, y);
        graphics.drawLine(x, y + 15, x + 10, y);
    }

    public void drawUpArrow(int x, int y) {
        graphics.setColor(Colors.TEXT.getColor());
        graphics.drawLine(x, y - 15, x - 10, y);
        graphics.drawLine(x, y - 15, x + 10, y);
    }

    public void drawRightArrow(int x, int y) {
        graphics.drawLine(x + 20, y, x, y + 10);
        graphics.drawLine(x + 20, y, x, y - 10);
    }

    public void drawLeftArrow(int x, int y) {
        graphics.drawLine(x, y, x + 20, y + 10);
        graphics.drawLine(x, y, x + 20, y - 10);
    }

    public void drawInnerLeftArrow(int x, int y) {
        graphics.setStroke(new BasicStroke(2f));
        graphics.draw(new Arc2D.Double(x, y, 20, 20, -90, 225, Arc2D.OPEN));
        graphics.drawLine(x + 1, y + 1, x + 7, y - 6);
        graphics.drawLine(x + 1, y + 2, x + 9, y + 6);
        graphics.setStroke(new BasicStroke(3f));
    }

    public void drawInnerRightArrow(int x, int y) {
        graphics.setStroke(new BasicStroke(2f));
        graphics.draw(new Arc2D.Double(x, y, 20, 20, 45, 225, Arc2D.OPEN));
        graphics.drawLine(x + 19, y + 1, x + 10, y - 6);
        graphics.drawLine(x + 18, y + 1, x + 9, y + 6);
        graphics.setStroke(new BasicStroke(3f));
    }

    public void turnAntiAliasingOn(boolean on) {
        if (on) {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        } else {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        }
    }
}
