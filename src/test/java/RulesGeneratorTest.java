import Logic.SquareRulesGenerator;
import Utils.SquareRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RulesGeneratorTest {
    private SquareRulesGenerator squareRulesGenerator;

    @BeforeEach
    void init() {
        squareRulesGenerator = new SquareRulesGenerator(2);
    }

    @Test
    void increaseByOne() {
        squareRulesGenerator.increaseByOne();
        assertThat(squareRulesGenerator.charArray, is(new char[]{'R', 'L'}));
        squareRulesGenerator.increaseByOne();
        assertThat(squareRulesGenerator.charArray, is(new char[]{'L', 'R'}));
        squareRulesGenerator.increaseByOne();
        assertThat(squareRulesGenerator.charArray, is(new char[]{'R', 'R'}));
        squareRulesGenerator.increaseByOne();
        assertThat(squareRulesGenerator.charArray, is(new char[]{'L', 'L'}));
    }

    @Test
    void rulesGenerator_returnsCorrectRules() {
        assertThat(squareRulesGenerator.next().rule, is("RL"));
        assertThat(squareRulesGenerator.hasNext(), is(false));
        squareRulesGenerator = new SquareRulesGenerator(3);
        assertThat(squareRulesGenerator.next().rule, is("RLL"));
        assertThat(squareRulesGenerator.next().rule, is("LRL"));
        assertThat(squareRulesGenerator.next().rule, is("RRL"));
        assertThat(squareRulesGenerator.hasNext(), is(false));
    }

    @Test
    void rulesGenerator_createsCorrectNumberOfRules() {
        assertThat(getAllRules(2), is(1));
        assertThat(getAllRules(3), is(3));
        assertThat(getAllRules(8), is(127));
    }

    private int getAllRules(int length) {
        squareRulesGenerator = new SquareRulesGenerator(length);
        List<SquareRule> result = new ArrayList<>();
        while (squareRulesGenerator.hasNext())
            result.add(squareRulesGenerator.next());
        return result.size();
    }
}
