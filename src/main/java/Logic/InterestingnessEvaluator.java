package Logic;

/**
 * Calculates how interesting grid generated by {@link Ant} is.
 */
public class InterestingnessEvaluator {
    private final int[][] grid;
    private final int dimension;
    private final int[] helperArray;

    public InterestingnessEvaluator(int[][] grid) {
        this.grid = grid;
        dimension = grid.length;
        helperArray = new int[dimension + 1];
        helperArray[dimension] = 100; // sentinel value
    }

    /**
     * Takes array and finds number of lines of same number created by ant. The longer the line the greater is number
     * so the one longer line has bigger weight number os smaller ones.
     *
     * @param line 1D array to evaluate
     * @return score given to array, longer lines produce bigger number
     */
    public double arrayEvaluator(int[] line) {
        int lastValue = line[0];
        int lastIndex = 0;
        double currentLineScore = 0;

        for (int i = 1; i < dimension + 1; i++) {
            if (line[i] != lastValue) {
                // we subtract 1 because we want to give 0 score to line of length 1;
                if (lastValue != -1)
                    currentLineScore += (Math.pow(i - lastIndex - 1, 2) * 0.1);
                lastValue = line[i];
                lastIndex = i;
            }
        }
        return currentLineScore;
    }

    public double getFinalScore() {
        return evaluateAllLines() / girdFillFraction();
    }

    /**
     * Goes through all possible grid 1D arrays we want evaluate and calculates score.
     *
     * @return final score
     */
    public double evaluateAllLines() {
        double result = 0;
        for (int line = 0; line < dimension; line++) {
            result += arrayEvaluator(getLineForEvaluation(LineType.HORIZONTAL, line));
            result += arrayEvaluator(getLineForEvaluation(LineType.VERTICAL, line));
        }
        return result;
    }

    /**
     * Generates 1D array from grid based on what type of 1D array we want horizontal, vertical, ...
     *
     * @param lineType specifies whether we want to create horizontal, vertical or diagonal 1D array
     * @param line     number of grid line we want to extract
     * @return 1D array representing specified line
     */
    public int[] getLineForEvaluation(LineType lineType, int line) {
        switch (lineType) {
            case HORIZONTAL -> {
                System.arraycopy(grid[line], 0, helperArray, 0, dimension);
            }
            case VERTICAL -> {
                for (int column = 0; column < dimension; column++) {
                    helperArray[column] = grid[column][line];
                }
            }
        }
        return helperArray;
    }

    /**
     * Calculates fraction of filled grid. Because big images can have lots of small random lines but small image with
     * interesting lines would get lower score. Therefore we calculate how much of the image is filled.
     *
     * @return fraction of filed squares
     */
    public double girdFillFraction() {
        double filled = 0.0D;
        for (int row = 0; row < dimension; row++) {
            for (int column = 0; column < dimension; column++) {
                if (grid[row][column] != -1)
                    filled++;
            }
        }
        return filled / (dimension * dimension);
    }

    public enum LineType {
        HORIZONTAL, VERTICAL, DIAGONAL_LEFT_RIGHT, DIAGONAL_RIGHT_LEFT
    }
}