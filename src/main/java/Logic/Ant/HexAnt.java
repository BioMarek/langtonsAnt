package Logic.Ant;

import Logic.HexMove;
import Logic.HexPosition;
import Logic.Rule.Rule;
import Utils.Settings;
import Utils.Util;

import static Utils.Util.calculateStandardDeviation;
import static Utils.Util.normalize;

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

    @Override
    public boolean shouldBeSaved() {
        return usedTopColor && filledEnoughHexes() && calculateStandardDeviation(normalize(getDistribution())) > 0.09;
    }

    /**
     * There are images that have filled only fex hexes, these should not be saved. This function will disqualify them.
     */
    public boolean filledEnoughHexes() {
        int filled = 0;
        for (int[] row : grid) {
            for (int column = 0; column < grid.length; column++) {
                if (row[column] != -1)
                    filled++;
            }
        }
        return filled > Settings.HEXES_USED;
    }

    public int[] getDistribution() {
        int[] result = new int[Settings.RULES_LENGTH];
        for (int[] row : grid) {
            for (int column = 0; column < grid.length; column++) {
                if (row[column] != -1)
                    result[row[column]]++;
            }
        }
        return result;
    }
}
