import Logic.SquareAnt;
import Utils.SquareRule;
import Utils.Settings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SquareAntTest {
    private final int SIZE = 2;
    private SquareAnt squareAnt;
    private final SquareRule squareRule = new SquareRule("RL", 1, 1, 1);

    @BeforeEach
    public void init() {
        Settings.MAX_MOVES = 1_000_000;
        Settings.IMAGE_PADDING = 0;
        Settings.GRID_HEIGHT = 2;
        Settings.SIZE_OF_SQUARE = 1;
    }

    @Test
    void parseRuleTest() {
        squareAnt = new SquareAnt(squareRule);
        assertThat(squareAnt.charRule, is(new char[]{'R', 'L'}));
    }

//    @Test
//    void checkBorderCollisionTest() {
//        squareAnt = new SquareAnt(rule);
//        squareAnt.antPosition.row = -1;
//        squareAnt.checkBorderCollision();
//        assertThat(squareAnt.stopped, is(true));
//
//        squareAnt = new SquareAnt(rule); // doesn't work because ant no longer accepts size of grid as parameter ant takes it from settings
//        squareAnt.antPosition.column = 2;
//        squareAnt.checkBorderCollision();
//        assertThat(squareAnt.stopped, is(true));
//    }
}
