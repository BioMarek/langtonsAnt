package Logic.Ant;

import Logic.Rule.Rule;
import Utils.Settings;

public abstract class Ant {
    public Rule rule;
    public int[][] grid;
    public int gridColumns;
    public int gridRows;
    public boolean stopped = false;
    public int steps = 0;
    public boolean usedTopColor = false;

    public void allMoves() {
        long countDown = Settings.MAX_MOVES;
        while (countDown >= 0 && !stopped) {
            nextMove();
            countDown--;
        }
    }

    public void nextMoves() {
        int countDown = Settings.SKIP;
        if (steps > rule.getSlowdownSteps())
            countDown = (int) (countDown * rule.getSlowdownModifier());
        while (countDown > 0 && !stopped) {
            nextMove();
            countDown--;
        }
    }

    public abstract void nextMove();

    public abstract int ruleLength();

    /**
     * Determines whether rule that Ant just run should be saved as image. Only unique images should be saved.
     *
     * @return true if image is unique, false otherwise
     */
    public abstract boolean shouldBeSaved();
}
