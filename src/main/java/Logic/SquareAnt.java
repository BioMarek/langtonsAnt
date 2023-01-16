package Logic;

import Utils.Direction;
import Utils.Position;
import Utils.Rule;
import Utils.Settings;

import java.util.Arrays;

public class SquareAnt extends Ant {
    public char[] charRule;
    public Position antPosition = new Position();

    public SquareAnt(Rule rule) {
        this.gridColumns = (Settings.GRID_WIDTH + Settings.IMAGE_PADDING) / Settings.SIZE_OF_SQUARE;
        this.gridRows = (Settings.GRID_HEIGHT + Settings.IMAGE_PADDING) / Settings.SIZE_OF_SQUARE;
        this.grid = new int[gridRows][gridColumns];

        for (int i = 0; i < gridRows; i++) {
            grid[i] = new int[gridColumns];
            Arrays.fill(grid[i], -1);
        }
        antPosition.row = gridRows / 2;
        antPosition.column = gridColumns / 2;
        antPosition.direction = Direction.NORTH;

        this.rule = rule;
        parseRule(rule.getSquareRule());
    }

    /**
     * Rules are specified as String of type "LR" where L is turn left and R is turn right. In order to speed up
     * processing Rule is parsed as array of chars rather than call charAt(i) multiple times.
     */
    public void parseRule(String givenRule) {
        charRule = new char[givenRule.length()];
        for (int i = 0; i < givenRule.length(); i++) {
            charRule[i] = givenRule.charAt(i);
        }
    }

    /**
     * Moves ant to next tile based on rule and on color of the square the ant is standing on.
     */
    public void nextMove() {
        int currentRow = antPosition.row;
        int currentColumn = antPosition.column;
        grid[currentRow][currentColumn] = (grid[currentRow][currentColumn] == -1) ? 0 : grid[currentRow][currentColumn];

        if (charRule[grid[currentRow][currentColumn]] == 'L')
            moveLeft();
        if (charRule[grid[currentRow][currentColumn]] == 'R')
            moveRight();

        if (grid[currentRow][currentColumn] == charRule.length - 1)
            usedTopColor = true;
        grid[currentRow][currentColumn] = (grid[currentRow][currentColumn] + 1) % charRule.length;

        steps++;
        stopped = steps >= Settings.MAX_MOVES;
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
        if (antPosition.row < 0 || antPosition.column < 0 || antPosition.row == gridRows || antPosition.column == gridColumns)
            stopped = true;
    }

    @Override
    public int ruleLength() {
        return charRule.length;
    }

    @Override
    public boolean shouldBeSaved() {
        return usedTopColor;
    }
}
