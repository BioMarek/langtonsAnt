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
     * Goes through all possible grid 1D arrays we want to evaluate and calculates score.
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
     * interesting lines would get lower score. Therefore, we calculate how much of the image is filled.
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

    /**
     * Detects highway in image generated by ant. Highway needs to reach border and in outmost circle at most 50% of the
     * image can be filled. Algorithm goes in circles. Each circle is from four parts horizontal top, vertical right,
     * horizontal bottom and vertical left line. See circleAlgorithmFourPhases.png in documentation.
     *
     * @param filledPortionLimit how much of outmost circle can be filled before image will be marked as without highway
     * @return score representing likeliness of highway. For filledPortionLimit 0.5, score 2.2 under is not highway
     */
    public double highwayEvaluator(double filledPortionLimit) {
        double result = 0.0D;
        int maxCircles = dimension / 2;

        for (int circle = 0; circle < maxCircles; circle++) {
            int circleLength = (dimension - circle * 2 - 1) * 4;
            int filled = 0;
            for (int cell = circle; cell < dimension - circle - 1; cell++) {
                // horizontal top
                if (grid[circle][cell] != -1)
                    filled++;
                // vertical right
                if (grid[cell][dimension - circle - 1] != -1)
                    filled++;
                // vertical left
                if (grid[cell + 1][circle] != -1)
                    filled++;
                // horizontal bottom
                if (grid[dimension - circle - 1][cell + 1] != -1)
                    filled++;
            }
            double filledPortion = 1.0D * filled / circleLength;
            if (circle == 0 && filledPortion > filledPortionLimit || circle == 0 && filledPortion == 0)
                return 1.0D;
            result += filledPortion;
        }
        return 1 / (result / maxCircles);
    }

    /**
     * @param edgeLimit how close to the border we won't calculate symmetry
     * @return the highest number of symmetric blocks from all vertical axes of symmetry
     */
    public double bestVerticalSymmetry(int edgeLimit) {
        // TODO check that having edgeLimit same in bestVerticalSymmetry and verticalSymmetryEvaluator makes sense
        // why has 891.00_RLLRRLLRRLLR low score?
        int result = 0;
        for (int center = edgeLimit; center < dimension - edgeLimit; center++) {
            result = Math.max(result, verticalSymmetryEvaluator(center, edgeLimit));
        }
        return result;
    }

    /**
     * Calculates number of symmetric blocks in grid when axis of symmetry is given.
     *
     * @param axis      horizontal axis of symmetry
     * @param edgeLimit how close to the border we won't calculate symmetry
     * @return number of symmetric blocks in grid
     */
    public int verticalSymmetryEvaluator(int axis, int edgeLimit) {
        int sameCells = 0;
        for (int row = 0; row < dimension; row++) {
            int left = axis;
            int right = axis + 1;
            while (left >= edgeLimit && right <= dimension - edgeLimit - 1) {
                if (grid[row][left] != -1 && grid[row][left] == grid[row][right])
                    sameCells++;
                left--;
                right++;
            }
        }
        return sameCells;
    }

    public enum LineType {
        HORIZONTAL, VERTICAL
    }
}
