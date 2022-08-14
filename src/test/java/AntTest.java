import Logic.Ant;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AntTest {
    private final int SIZE = 2;
    private Ant ant;
    private final String rule = "RL";
    private final long maxMoves = 1_000_000;

    @Test
    void parseRuleTest() {
        ant = new Ant(SIZE, maxMoves, rule);
        assertThat(ant.rule, is(new char[]{'R', 'L'}));
    }

    @Test
    void checkBorderCollisionTest() {
        ant = new Ant(SIZE, maxMoves, rule);
        ant.antPosition.row = -1;
        ant.checkBorderCollision();
        assertThat(ant.stopped, is(true));

        ant = new Ant(SIZE, maxMoves, rule);
        ant.antPosition.column = 2;
        ant.checkBorderCollision();
        assertThat(ant.stopped, is(true));
    }
}
