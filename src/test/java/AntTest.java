import Logic.Ant;
import Utils.Settings;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AntTest {
    private final int SIZE = 2;
    private Ant ant;

    @Test
    void parseRuleTest() {
        Settings.RULE = "RL";
        ant = new Ant(SIZE);
        assertThat(ant.rule, is(new char[]{'R', 'L'}));
    }

    @Test
    void checkBorderCollisionTest() {
        ant = new Ant(SIZE);
        ant.antPosition.row = -1;
        ant.checkBorderCollision();
        assertThat(ant.stopped, is(true));

        ant = new Ant(SIZE);
        ant.antPosition.column = 2;
        ant.checkBorderCollision();
        assertThat(ant.stopped, is(true));
    }
}
