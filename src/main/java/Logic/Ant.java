package Logic;

import Utils.Direction;
import Utils.Position;

public class Ant {
    public int[][] grid;
    public int size;
    public Position antPosition = new Position();

    public Ant(int size) {
        this.grid = new int[size][size];
        this.size = size;
        for (int i = 0; i < size; i++) {
            grid[i] = new int[size];
        }
        antPosition.row = size / 2;
        antPosition.column = size / 2;
        antPosition.direction = Direction.NORTH;
    }

    public void nextMove() {
        if (grid[antPosition.row][antPosition.column] == 0 || grid[antPosition.row][antPosition.column] == 1) {
            grid[antPosition.row][antPosition.column] = 2;
            moveRight();
        }
        if (grid[antPosition.row][antPosition.column] == 2) {
            grid[antPosition.row][antPosition.column] = 1;
            moveLeft();
        }
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
