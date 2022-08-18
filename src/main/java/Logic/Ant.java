package Logic;

import Utils.ColorsPicker;
import Utils.Direction;
import Utils.Position;
import Utils.Settings;

import java.awt.*;
import java.util.Arrays;

public class Ant {
    private final int squares = Settings.SIZE_IN_PIXELS / Settings.SIZE_OF_SQUARE;
    public final int[][] grid;
    private final int size;
    private final long maxMoves;
    public char[] rule;
    public Position antPosition = new Position();
    public boolean stopped = false;
    private int moves = 0;

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
        while (countDown > 0 && !stopped) {
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

        grid[currentRow][currentColumn] = (grid[currentRow][currentColumn] + 1) % rule.length;

        moves++;
        stopped = moves > maxMoves;
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
     * Converts grid numbers to {@link Graphics2D}.
     */
    public void draw(Graphics2D graphics) {
        setBackground(graphics);
        for (int row = 0; row < squares; row++) {
            for (int column = 0; column < squares; column++) {
                ColorsPicker.setColor(graphics, grid[row][column]);
                if (Settings.I_SHOW_GRID || Settings.SHOW_GRID)
                    graphics.fillRect(row * Settings.SIZE_OF_SQUARE + 1,
                            column * Settings.SIZE_OF_SQUARE + 1,
                            Settings.SIZE_OF_SQUARE - 1,
                            Settings.SIZE_OF_SQUARE - 1);
                else graphics.fillRect(row * Settings.SIZE_OF_SQUARE,
                        column * Settings.SIZE_OF_SQUARE,
                        Settings.SIZE_OF_SQUARE,
                        Settings.SIZE_OF_SQUARE);
            }
        }
    }

    public void setBackground(Graphics2D graphics) {
        graphics.setColor(new Color(40, 40, 40));
        if (Settings.I_SHOW_GRID || Settings.SHOW_GRID)
            graphics.fillRect(0, 0, Settings.SIZE_IN_PIXELS + 1, Settings.SIZE_IN_PIXELS + 1);
        else
            graphics.fillRect(0, 0, Settings.SIZE_IN_PIXELS, Settings.SIZE_IN_PIXELS);
    }


    /**
     * Prints grid, used for debugging purposes.
     */
    public void printGrid() {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                System.out.print(grid[row][column] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
