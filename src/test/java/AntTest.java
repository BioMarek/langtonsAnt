import Logic.Ant;
import Utils.Settings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AntTest {
    private final int SIZE = 2;
    private Ant ant;
    private final String rule = "RL";

    @BeforeEach
    public void init() {
        Settings.MAX_MOVES = 1_000_000;
        Settings.IMAGE_PADDING = 0;
        Settings.GRID_HEIGHT = 2;
        Settings.SIZE_OF_SQUARE = 1;
    }

    @Test
    void parseRuleTest() {
        ant = new Ant(rule);
        assertThat(ant.rule, is(new char[]{'R', 'L'}));
    }

    @Test
    void checkBorderCollisionTest() {
        ant = new Ant(rule);
        ant.antPosition.row = -1;
        ant.checkBorderCollision();
        assertThat(ant.stopped, is(true));

        ant = new Ant(rule);
        ant.antPosition.column = 2;
        ant.checkBorderCollision();
        assertThat(ant.stopped, is(true));
    }
}
