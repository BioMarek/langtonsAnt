import Logic.RulesGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RulesGeneratorTest {
    private RulesGenerator rulesGenerator;

    @BeforeEach
    void init() {
        rulesGenerator = new RulesGenerator(2);
    }

    @Test
    void allRulesExhausted() {
        assertThat(rulesGenerator.allRulesExhausted(), is(false));
        rulesGenerator.charArray = new char[]{'R', 'L'};
        assertThat(rulesGenerator.allRulesExhausted(), is(false));
        rulesGenerator.charArray = new char[]{'R', 'R'};
        assertThat(rulesGenerator.allRulesExhausted(), is(true));
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
}
