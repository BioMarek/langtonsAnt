package Logic;

import Utils.HexMove;
import Utils.HexPosition;
import Utils.Rule;
import Utils.Settings;
import Utils.Util;

import java.util.Arrays;

public class HexAnt extends Ant {
    public HexPosition antPosition = new HexPosition();

    public HexAnt(Rule rule) {
        this.gridColumns = Settings.GRID_WIDTH / Util.getHexWidth();
        this.gridRows = Settings.GRID_HEIGHT / Util.getHexHeight();
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

    /**
     * Moves ant to next tile based on rule and on color of the square the ant is standing on.
     */
    public void nextMove() {
        int currentRow = antPosition.row;
        int currentColumn = antPosition.column;
        grid[currentRow][currentColumn] = (grid[currentRow][currentColumn] == -1) ? 0 : grid[currentRow][currentColumn];

        HexMove moveDirection = HexMove.stringToHexMove(rule.getElement(grid[currentRow][currentColumn]));
        grid[currentRow][currentColumn]++;
        antPosition.move(moveDirection);

        if (grid[currentRow][currentColumn] == rule.getSize() - 1)
            usedTopColor = true;

        grid[currentRow][currentColumn] = (grid[currentRow][currentColumn]) % rule.getSize();

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

    @Override
    public int ruleLength() {
        return rule.getSize();
    }
}
