import Logic.Ant;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AntTest {
    private final int SIZE = 2;
    private Ant ant;
    private String rule = "RL";

    @Test
    void parseRuleTest() {
        ant = new Ant(SIZE, rule);
        assertThat(ant.rule, is(new char[]{'R', 'L'}));
    }

    @Test
    void checkBorderCollisionTest() {
        ant = new Ant(SIZE, rule);
        ant.antPosition.row = -1;
        ant.checkBorderCollision();
        assertThat(ant.stopped, is(true));

        ant = new Ant(SIZE, rule);
        ant.antPosition.column = 2;
        ant.checkBorderCollision();
        assertThat(ant.stopped, is(true));
    }
}
