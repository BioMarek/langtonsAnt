import Logic.InterestingnessEvaluator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class InterestingnessEvaluatorTest {
    private int[][] grid;
    private final int SIZE = 6;
    private final double FILED_PORTION_LIMIT = 0.5d;
    private InterestingnessEvaluator interestingnessEvaluator;

    @BeforeEach
    void init() {
        grid = new int[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            grid[row] = new int[SIZE];
            Arrays.fill(grid[row], -1);
        }
        interestingnessEvaluator = new InterestingnessEvaluator(grid);
    }

    @Test
    void horizontalLineEvaluator_LineOfLengthThree() {
        grid[1][0] = 1;
        grid[1][1] = 1;
        grid[1][2] = 1;
        assertThat(interestingnessEvaluator.arrayEvaluator(
                interestingnessEvaluator.getLineForEvaluation(InterestingnessEvaluator.LineType.HORIZONTAL, 1)), is(0.4));
    }

    @Test
    void horizontalLineEvaluator_TwoLinesOfLengthTwo() {
        grid[1][0] = 1;
        grid[1][1] = 1;
        grid[1][4] = 1;
        grid[1][5] = 1;
        assertThat(interestingnessEvaluator.arrayEvaluator(
                interestingnessEvaluator.getLineForEvaluation(InterestingnessEvaluator.LineType.HORIZONTAL, 1)), is(0.2));
    }

    @Test
    void horizontalLineEvaluator_LineStartsInMiddle() {
        grid[1][2] = 1;
        grid[1][3] = 1;
        grid[1][4] = 1;
        assertThat(interestingnessEvaluator.arrayEvaluator(
                interestingnessEvaluator.getLineForEvaluation(InterestingnessEvaluator.LineType.HORIZONTAL, 1)), is(0.4));
    }

    @Test
    void horizontalLineEvaluator_TooShortLines() {
        grid[1][0] = 1;
        grid[1][2] = 1;
        grid[1][4] = 1;
        assertThat(interestingnessEvaluator.arrayEvaluator(
                interestingnessEvaluator.getLineForEvaluation(InterestingnessEvaluator.LineType.HORIZONTAL, 1)), is(0.0));
    }

    @Test
    void evaluateAllHorizontalLines_AllLineTypes() {
        grid[0][0] = 1;
        grid[0][1] = 1; // 0.1
        grid[0][4] = 1;
        grid[0][5] = 1; // 0.1

        grid[1][2] = 4;
        grid[1][3] = 4;
        grid[1][4] = 4; // 0.4

        grid[2][0] = 1;
        grid[2][2] = 1;
        grid[2][4] = 1; // 0.0

        grid[3][1] = 2;
        grid[3][2] = 2;
        grid[3][3] = 2;
        grid[3][4] = 2;
        grid[3][5] = 2; // 1.6
        assertThat(interestingnessEvaluator.evaluateAllLines(), is(2.2));
    }

    @Test
    void getLineForEvaluation_horizontal() {
        grid[1][0] = 1;
        grid[2][2] = 1;
        grid[3][4] = 1;

        assertThat(interestingnessEvaluator.getLineForEvaluation(InterestingnessEvaluator.LineType.HORIZONTAL, 1), is(new int[]{1, -1, -1, -1, -1, -1, 100}));
        assertThat(interestingnessEvaluator.getLineForEvaluation(InterestingnessEvaluator.LineType.HORIZONTAL, 2), is(new int[]{-1, -1, 1, -1, -1, -1, 100}));
    }

    @Test
    void getLineForEvaluation_vertical() {
        grid[1][0] = 1;
        grid[2][2] = 1;
        grid[3][4] = 1;

        assertThat(interestingnessEvaluator.getLineForEvaluation(InterestingnessEvaluator.LineType.VERTICAL, 0), is(new int[]{-1, 1, -1, -1, -1, -1, 100}));
        assertThat(interestingnessEvaluator.getLineForEvaluation(InterestingnessEvaluator.LineType.VERTICAL, 2), is(new int[]{-1, -1, 1, -1, -1, -1, 100}));
    }

    @Test
    void girdFillFraction_works() {
        grid[1][0] = 1;
        grid[2][2] = 1;
        grid[3][4] = 1;
        grid[3][5] = 1;
        assertThat(interestingnessEvaluator.girdFillFraction(), is(closeTo(0.11, 0.01)));
    }

    /**
     * Prints grid, used for debugging purposes.
     */
    public void printGrid() {
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                if (grid[row][column] == -1)
                    System.out.print(grid[row][column] + " ");
                else
                    System.out.print(" " + grid[row][column] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Test
    void highwayEvaluator_touchesBorderNutNoHighway() {
        grid[0][0] = 1;
        grid[0][1] = 1;
        grid[0][2] = 1;
        grid[0][3] = 1;
        grid[0][4] = 1;
        grid[0][5] = 1;

        grid[5][1] = 1;
        grid[5][2] = 1;
        grid[5][3] = 1;
        grid[5][4] = 1;
        assertThat(interestingnessEvaluator.highwayEvaluator(FILED_PORTION_LIMIT), is(greaterThan(1.0D)));
        grid[5][5] = 1;
        assertThat(interestingnessEvaluator.highwayEvaluator(FILED_PORTION_LIMIT), is(1.0D));
    }

    @Test
    void highwayEvaluator_NoHighway() {
        grid[1][1] = 1;
        grid[1][2] = 1;
        grid[2][2] = 1;
        grid[2][3] = 1;
        grid[3][3] = 1;
        assertThat(interestingnessEvaluator.highwayEvaluator(FILED_PORTION_LIMIT), is(1.0D));
    }

    @Test
    void highwayEvaluator_all() {
        grid[0][0] = 1;
        grid[0][1] = 1;
        grid[1][1] = 1;
        grid[1][2] = 1;
        grid[2][2] = 1;
        grid[2][3] = 1;
        assertThat(interestingnessEvaluator.highwayEvaluator(FILED_PORTION_LIMIT), is(greaterThan(3.5D)));
    }

    @Test
    void verticalSymmetryEvaluator_works() {
        grid[0][2] = 1;
        grid[0][3] = 1;
        grid[0][1] = 1;
        grid[0][4] = 1;
        grid[1][4] = 1;
        assertThat(interestingnessEvaluator.verticalSymmetryEvaluator(2), is(2));
    }

    @Test
    void bestVerticalSymmetry_works() {
        grid[0][2] = 1;
        grid[0][3] = 1;
        grid[0][1] = 1;
        grid[0][4] = 1;

        grid[1][2] = 1;
        grid[1][3] = 1;

        grid[2][3] = 1;
        grid[2][4] = 1;
        printGrid();
        assertThat(interestingnessEvaluator.bestVerticalSymmetry(1), is(3.0D));
    }
}
