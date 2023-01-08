package Logic;

import Utils.PositionHexagonal;
import Utils.RotationHexagonal;
import Utils.Settings;

import java.util.Arrays;
import java.util.List;

public class AntHexagonal {
    public final int[][] grid;
    public final int gridColumns;
    public final int gridRows;
    public List<RotationHexagonal> rule;
    public PositionHexagonal antPosition = new PositionHexagonal();
    public boolean stopped = false;
    public int steps = 0;
    public boolean usedTopColor = false;

    public AntHexagonal(List<RotationHexagonal> rule, int gridColumns, int gridRows) {
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
        antPosition.currentRotation = 90;
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

        RotationHexagonal moveDirection = rule.get(grid[currentRow][currentColumn]);
        grid[currentRow][currentColumn]++;
        antPosition.move(moveDirection);

        grid[currentRow][currentColumn] = (grid[currentRow][currentColumn]) % rule.size();

        steps++;
        stopped = steps >= Settings.MAX_MOVES;
        checkBorderCollision();
    }

    /**
     * Checks whether ant moved out of grid bounds.
     */
    public void checkBorderCollision() {
        if (antPosition.row < 0 || antPosition.column < 0 || antPosition.row == gridRows || antPosition.column == gridColumns)
            stopped = true;
    }
}
