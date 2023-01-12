package Logic;

public abstract class Ant {
    public  int[][] grid;
    public  int gridColumns;
    public  int gridRows;

    public boolean stopped = false;
    public int steps = 0;
    public boolean usedTopColor = false;

    public abstract void allMoves();

    public abstract void nextMoves();

    public abstract int ruleLength();

}
