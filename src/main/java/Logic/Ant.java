package Logic;

import Utils.Direction;
import Utils.Position;
import Utils.Settings;

public class Ant {
    public int[][] grid;
    public int size;
    private char[] rule;
    public Position antPosition = new Position();
    public boolean stopped = false;

    public Ant(int size) {
        this.grid = new int[size][size];
        this.size = size;

        for (int i = 0; i < size; i++) {
            grid[i] = new int[size];
        }
        antPosition.row = size / 2;
        antPosition.column = size / 2;
        antPosition.direction = Direction.NORTH;

        parseRule();
    }

    private void parseRule() {
        rule = new char[Settings.rule.length()];
        for (int i = 0; i < Settings.rule.length(); i++) {
            rule[i] = Settings.rule.charAt(i);
        }
    }

    public void nextMove() {
        int currentRow = antPosition.row;
        int currentColumn = antPosition.column;
        if (rule[grid[currentRow][currentColumn]] == 'L')
            moveLeft();
        if (rule[grid[currentRow][currentColumn]] == 'R')
            moveRight();
        grid[currentRow][currentColumn] = (grid[currentRow][currentColumn] + 1) % Settings.rule.length();
        checkBorderCollision();
    }

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

    public void checkBorderCollision() {
        if (antPosition.row < 0 || antPosition.column < 0 || antPosition.row == size || antPosition.column == size)
            stopped = true;
    }

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
