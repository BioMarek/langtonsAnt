package Logic;

import Utils.*;

import java.awt.*;
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
        setBackground(graphics);
        draw(graphics);
    }

    /**
     * Draws image that can be presented as frame of animation. Contains info on the right side of image.
     */
    public void drawPresentation(Graphics2D graphics) {
        setBackgroundPresentation(graphics);
        draw(graphics);
        drawInfo(graphics);
        drawLegend(graphics);
    }

    /**
     * Converts grid of numbers to {@link Graphics2D}.
     */
    public void draw(Graphics2D graphics) {
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
     *
     * @param graphics {@link Graphics2D} where we want to set color.
     */
    public void setBackground(Graphics2D graphics) {
        graphics.setColor(new Color(40, 40, 40));
        int sizeInPixels = Settings.SHOW_GRID ? Settings.SIZE_IN_PIXELS + 1 : Settings.SIZE_IN_PIXELS;
        graphics.fillRect(0, 0, sizeInPixels, sizeInPixels);
    }

    /**
     * Sets background of {@link Graphics2D} object for presentation animation the background is wider to accomodate for
     * information displayed on the right side.
     *
     * @param graphics {@link Graphics2D} where we want to set color.
     */
    public void setBackgroundPresentation(Graphics2D graphics) {
        graphics.setColor(new Color(40, 40, 40));
        int sizeInPixels = Settings.SHOW_GRID ? Settings.SIZE_IN_PIXELS + 1 : Settings.SIZE_IN_PIXELS;
        graphics.fillRect(0, 0, sizeInPixels + Settings.SIZE_IN_PIXELS / 3, sizeInPixels);
    }

    /**
     * Displays information about ant rule being animated and number of steps ant has made.
     */
    public void drawInfo(Graphics2D graphics) {
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        graphics.setColor(Colors.TEXT.getColor());
        int fontUnit = Settings.SIZE_IN_PIXELS / 60;
        graphics.setFont(new Font("Arial", Font.BOLD, (int) (fontUnit * 1.2)));
        graphics.drawString("Rule:   " + new String(rule), Settings.SIZE_IN_PIXELS + fontUnit, fontUnit * 2);
        graphics.drawString("Steps: " + Util.numberFormatter(steps), Settings.SIZE_IN_PIXELS + fontUnit, fontUnit * 4);

        graphics.setColor(Colors.TEXT.getColor());
        graphics.setStroke(new BasicStroke(3f));
        graphics.drawLine(Settings.SIZE_IN_PIXELS, 0, Settings.SIZE_IN_PIXELS, Settings.SIZE_IN_PIXELS);

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
    }

    public void drawLegend(Graphics2D graphics) {
        int squareSize = Settings.SIZE_IN_PIXELS / 25;
        int topPadding = squareSize * 4;
        int gap = squareSize * 2;
        int ruleHalf = (rule.length % 2 == 0) ? rule.length / 2 : rule.length / 2 + 1; // ensures left column is longer than right one

        graphics.setColor(Colors.TEXT.getColor());
        graphics.draw(new RoundRectangle2D.Double(Settings.SIZE_IN_PIXELS + 2.5 * squareSize,
                topPadding - 0.5 * squareSize,
                squareSize * 3,
                squareSize * ruleHalf * 2, 20, 20));

        drawLeftArrow(graphics, (int) (Settings.SIZE_IN_PIXELS + 3.75 * squareSize), (int) (topPadding - 0.5 * squareSize));
        drawRightArrow(graphics, (int) (Settings.SIZE_IN_PIXELS + 3.75 * squareSize), (int) (topPadding - 0.5 * squareSize + ruleHalf * 2 * squareSize));

        for (int i = 0; i < ruleHalf; i++) {
            graphics.setColor(Colors.TEXT.getColor());
            if (i != ruleHalf - 1)
                drawDownArrow(graphics, (int) (Settings.SIZE_IN_PIXELS + squareSize * 2.5), (int) (i * squareSize * 2 + topPadding + squareSize * 1.5));
            graphics.drawRect(Settings.SIZE_IN_PIXELS + gap, i * gap + topPadding, squareSize, squareSize);
            graphics.setColor(Colors.BACKGROUND.getColor());
            graphics.fillRect(Settings.SIZE_IN_PIXELS + gap + 1, i * gap + topPadding + 1, squareSize - 1, squareSize - 1);
            graphics.setColor(Colors.getColor(i));
            graphics.fillRect(Settings.SIZE_IN_PIXELS + gap + 1, i * gap + topPadding + 1, squareSize - 1, squareSize - 1);
        }

        for (int i = ruleHalf; i < rule.length; i++) {
            graphics.setColor(Colors.TEXT.getColor());
            if (i != rule.length - 1)
                drawUpArrow(graphics, (int) (Settings.SIZE_IN_PIXELS + squareSize * 5.5), (int) ((i - ruleHalf) * squareSize * 2 + topPadding + squareSize * 1.5));
            graphics.drawRect(Settings.SIZE_IN_PIXELS + squareSize * 5, (i - ruleHalf) * gap + topPadding, squareSize, squareSize);
            graphics.setColor(Colors.BACKGROUND.getColor());
            graphics.fillRect(Settings.SIZE_IN_PIXELS + squareSize * 5 + 1, (i - ruleHalf) * gap + topPadding + 1, squareSize - 1, squareSize - 1);
            graphics.setColor(Colors.getColor(i));
            graphics.fillRect(Settings.SIZE_IN_PIXELS + squareSize * 5 + 1, (i - ruleHalf) * gap + topPadding + 1, squareSize - 1, squareSize - 1);
        }
    }

    public void drawDownArrow(Graphics2D graphics, int x, int y) {
        graphics.drawLine(x, y + 15, x - 10, y);
        graphics.drawLine(x, y + 15, x + 10, y);
    }

    public void drawUpArrow(Graphics2D graphics, int x, int y) {
        graphics.drawLine(x, y - 15, x - 10, y);
        graphics.drawLine(x, y - 15, x + 10, y);
    }

    public void drawRightArrow(Graphics2D graphics, int x, int y) {
        graphics.drawLine(x + 20, y, x, y + 10);
        graphics.drawLine(x + 20, y, x, y - 10);
    }

    public void drawLeftArrow(Graphics2D graphics, int x, int y) {
        graphics.drawLine(x, y, x + 20, y + 10);
        graphics.drawLine(x, y, x + 20, y - 10);
    }
}
