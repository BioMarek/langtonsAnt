package Logic;

import Utils.Direction;
import Utils.Position;
import Utils.Settings;

import java.util.Arrays;
import java.util.List;

public class AntHexagonal {
    public final int[][] grid;
    public final int gridColumns;
    public final int gridRows;
    public List<String> rule;
    public Position antPosition = new Position();
    public boolean stopped = false;
    public int steps = 0;
    public boolean usedTopColor = false;

    public AntHexagonal(List<String> rule, int gridColumns, int gridRows) {
        this.gridColumns = gridColumns;
        this.gridRows = gridRows;
        this.grid = new int[gridRows][gridColumns];
        this.rule = rule;

        for (int i = 0; i < gridRows; i++) {
            grid[i] = new int[gridColumns];
            Arrays.fill(grid[i], -1);
        }
        antPosition.row = gridRows / 2;
        antPosition.column = gridColumns / 2;
//        antPosition.direction = Direction.HARD_LEFT;
    }

    public void allMoves() {
        long countDown = Settings.MAX_MOVES;
        while (countDown >= 0 && !stopped) {
            nextMove();
            countDown--;
        }
    }

    public void nextMoves() {
        int countDown = Settings.SKIP;
        if (steps > Settings.SLOWDOWN_STEPS)
            countDown = (int) (countDown * Settings.SLOWDOWN_MODIFIER);
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

        String move = rule.get(grid[currentRow][currentColumn]);

        switch (move){
//            case "N":
            case "R1":



        }
    }


    /**
     * Moves ant to the left based on direction ant is facing.
     */
    public void moveLeft() {

    }

    /**
     * Checks whether ant moved out of grid bounds.
     */
    public void checkBorderCollision() {
        if (antPosition.row < 0 || antPosition.column < 0 || antPosition.row == gridRows || antPosition.column == gridColumns)
            stopped = true;
    }
}
