package Logic;

import Utils.*;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Arrays;

public class Ant {
    private final int squares = Settings.SIZE_IN_PIXELS / Settings.SIZE_OF_SQUARE;
    public final int[][] grid;
    private final int size;
    private final long maxMoves;
    public char[] rule;
    public Position antPosition = new Position();
    public boolean stopped = false;
    private int steps = 0;
    public boolean usedTopColor = false;
    private Graphics2D graphics;

    public Ant(int size, long maxMoves, String givenRule) {
        this.grid = new int[size][size];
        this.size = size;
        this.maxMoves = maxMoves;

        for (int i = 0; i < size; i++) {
            grid[i] = new int[size];
            Arrays.fill(grid[i], -1);
        }
        antPosition.row = size / 2;
        antPosition.column = size / 2;
        antPosition.direction = Direction.NORTH;

        parseRule(givenRule);
    }

    /**
     * Rules are specified as String of type "LR" where L is turn left and R is turn right. In order to speed up
     * processing Rule is parsed as array of chars rather than call charAt(i) multiple times.
     */
    public void parseRule(String givenRule) {
        rule = new char[givenRule.length()];
        for (int i = 0; i < givenRule.length(); i++) {
            rule[i] = givenRule.charAt(i);
        }
    }

    public void allMoves() {
        long countDown = maxMoves;
        while (countDown >= 0 && !stopped) {
            nextMove();
            countDown--;
        }
    }

    public void nextMoves() {
        int countDown = Settings.SKIP;
        while (countDown > 0 && !stopped) {
            nextMove();
            countDown--;
        }
    }

    /**
     * Moves ant to next tile based on rule and on color of the square the ant is standing on.
     */
    public void nextMove() {
        int currentRow = antPosition.row;
        int currentColumn = antPosition.column;
        grid[currentRow][currentColumn] = (grid[currentRow][currentColumn] == -1) ? 0 : grid[currentRow][currentColumn];

        if (rule[grid[currentRow][currentColumn]] == 'L')
            moveLeft();
        if (rule[grid[currentRow][currentColumn]] == 'R')
            moveRight();

        if (grid[currentRow][currentColumn] == rule.length - 1)
            usedTopColor = true;
        grid[currentRow][currentColumn] = (grid[currentRow][currentColumn] + 1) % rule.length;

        steps++;
        stopped = steps > maxMoves;
        checkBorderCollision();
    }

    /**
     * Moves ant to the right based on direction ant is facing.
     */
    public void moveRight() {
        switch (antPosition.direction) {
            case NORTH -> {
                antPosition.direction = Direction.EAST;
                antPosition.column++;
            }
            case EAST -> {
                antPosition.direction = Direction.SOUTH;
                antPosition.row++;
            }
            case SOUTH -> {
                antPosition.direction = Direction.WEST;
                antPosition.column--;
            }
            case WEST -> {
                antPosition.direction = Direction.NORTH;
                antPosition.row--;
            }
        }
    }

    /**
     * Moves ant to the left based on direction ant is facing.
     */
    public void moveLeft() {
        switch (antPosition.direction) {
            case NORTH -> {
                antPosition.direction = Direction.WEST;
                antPosition.column--;
            }
            case EAST -> {
                antPosition.direction = Direction.NORTH;
                antPosition.row--;
            }
            case SOUTH -> {
                antPosition.direction = Direction.EAST;
                antPosition.column++;
            }
            case WEST -> {
                antPosition.direction = Direction.SOUTH;
                antPosition.row++;
            }
        }
    }

    /**
     * Checks whether ant moved out of grid bounds.
     */
    public void checkBorderCollision() {
        if (antPosition.row < 0 || antPosition.column < 0 || antPosition.row == size || antPosition.column == size)
            stopped = true;
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

        turnAntiAliasingOn(true);
        drawInfo();
        drawLegend();
        turnAntiAliasingOn(false);
    }

    /**
     * Converts grid of numbers to {@link Graphics2D}.
     */
    public void draw() {
        for (int column = 0; column < squares; column++) {
            for (int row = 0; row < squares; row++) {
                ColorsPicker.setColor(graphics, grid[column][row]);
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
        graphics.setColor(new Color(40, 40, 40));
        int sizeInPixels = Settings.SHOW_GRID ? Settings.SIZE_IN_PIXELS + 1 : Settings.SIZE_IN_PIXELS;
        graphics.fillRect(0, 0, sizeInPixels, sizeInPixels);
    }

    /**
     * Sets background of {@link Graphics2D} object for presentation animation the background is wider to accomodate for
     * information displayed on the right side.
     */
    public void setBackgroundPresentation() {
        graphics.setColor(new Color(40, 40, 40));
        int sizeInPixels = Settings.SHOW_GRID ? Settings.SIZE_IN_PIXELS + 1 : Settings.SIZE_IN_PIXELS;
        graphics.fillRect(0, 0, sizeInPixels + Settings.SIZE_IN_PIXELS / 3, sizeInPixels);
    }

    /**
     * Displays information about ant rule being animated and number of steps ant has made.
     */
    public void drawInfo() {
        int fontUnit = Settings.SIZE_IN_PIXELS / 60;
        graphics.setColor(Colors.TEXT.getColor());
        graphics.setFont(new Font("Arial", Font.BOLD, (int) (fontUnit * 1.2)));
        graphics.drawString("Rule:   " + new String(rule), Settings.SIZE_IN_PIXELS + fontUnit, fontUnit * 2);
        graphics.drawString("Steps: " + Util.numberFormatter(steps), Settings.SIZE_IN_PIXELS + fontUnit, fontUnit * 4);

        graphics.setStroke(new BasicStroke(3f));
        graphics.drawLine(Settings.SIZE_IN_PIXELS, 0, Settings.SIZE_IN_PIXELS, Settings.SIZE_IN_PIXELS);
    }

    public void drawLegend() {
        int squareSize = Settings.SIZE_IN_PIXELS / 25;
        int topPadding = squareSize * 4;
        int gap = squareSize * 2;
        int ruleHalf = (rule.length % 2 == 0) ? rule.length / 2 : rule.length / 2 + 1; // ensures left column is longer than right one

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
            drawLegendInnerArrows(i, squareSize, 2.3);
        }

        for (int i = ruleHalf; i < rule.length; i++) {
            if (i != rule.length - 1)
                drawUpArrow(Settings.SIZE_IN_PIXELS + squareSize * 11 / 2, (i - ruleHalf) * gap + squareSize * 11 / 2);
            drawFilledRect(Settings.SIZE_IN_PIXELS + squareSize * 5, (i - ruleHalf) * gap + topPadding, i);
            drawLegendInnerArrows(i - ruleHalf, squareSize, 5.2);
        }
    }

    public void drawLegendInnerArrows(int i, int squareSize, double xLegendColumnCoefficient) {
        graphics.setColor(Colors.TEXT.getColor());
        if (rule[i] == 'L') {
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
