package Logic;

import Utils.Direction;
import Utils.Position;
import Utils.Settings;

import java.util.Arrays;

public class Ant {
    public int[][] grid;
    public int size;
    public char[] rule;
    public Position antPosition = new Position();
    public boolean stopped = false;
    public int moves = 0;

    public Ant(int size) {
        this.grid = new int[size][size];
        this.size = size;

        for (int i = 0; i < size; i++) {
            grid[i] = new int[size];
            Arrays.fill(grid[i], -1);
        }
        antPosition.row = size / 2;
        antPosition.column = size / 2;
        antPosition.direction = Direction.NORTH;

        parseRule();
    }

    /**
     * Rules are specified as String of type "LR" where L is turn left and R is turn right. In order to speed up
     * processing Rule is parsed as array of chars rather than call charAt(i) multiple times.
     */
    public void parseRule() {
        rule = new char[Settings.RULE.length()];
        for (int i = 0; i < Settings.RULE.length(); i++) {
            rule[i] = Settings.RULE.charAt(i);
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

        grid[currentRow][currentColumn] = (grid[currentRow][currentColumn] + 1) % Settings.RULE.length();

        moves++;
        stopped = moves > Settings.MAX_MOVES;
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
