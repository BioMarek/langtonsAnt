import Logic.RulesGenerator;
import Utils.SquareRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RulesGeneratorTest {
    private RulesGenerator rulesGenerator;

    @BeforeEach
    void init() {
        rulesGenerator = new RulesGenerator(2);
    }

    @Test
    void increaseByOne() {
        rulesGenerator.increaseByOne();
        assertThat(rulesGenerator.charArray, is(new char[]{'R', 'L'}));
        rulesGenerator.increaseByOne();
        assertThat(rulesGenerator.charArray, is(new char[]{'L', 'R'}));
        rulesGenerator.increaseByOne();
        assertThat(rulesGenerator.charArray, is(new char[]{'R', 'R'}));
        rulesGenerator.increaseByOne();
        assertThat(rulesGenerator.charArray, is(new char[]{'L', 'L'}));
    }

    @Test
    void rulesGenerator_returnsCorrectRules() {
        assertThat(rulesGenerator.next().rule, is("RL"));
        assertThat(rulesGenerator.hasNext(), is(false));
        rulesGenerator = new RulesGenerator(3);
        assertThat(rulesGenerator.next().rule, is("RLL"));
        assertThat(rulesGenerator.next().rule, is("LRL"));
        assertThat(rulesGenerator.next().rule, is("RRL"));
        assertThat(rulesGenerator.hasNext(), is(false));
    }

    @Test
    void rulesGenerator_createsCorrectNumberOfRules() {
        assertThat(getAllRules(2), is(1));
        assertThat(getAllRules(3), is(3));
        assertThat(getAllRules(8), is(127));
    }

    private int getAllRules(int length) {
        rulesGenerator = new RulesGenerator(length);
        List<SquareRule> result = new ArrayList<>();
        while (rulesGenerator.hasNext())
            result.add(rulesGenerator.next());
        return result.size();
    }
}
