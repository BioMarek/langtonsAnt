import Logic.HexagonalRuleGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HexagonalRuleGeneratorTest {
    private HexagonalRuleGenerator hexagonalRuleGenerator;

    @BeforeEach
    void setup() {
        hexagonalRuleGenerator = new HexagonalRuleGenerator(2);
    }

    @Test
    void numberOfRulesIsCorrect() {
        assertThat(hexagonalRuleGenerator.totalNumOfRules, is(36));
        hexagonalRuleGenerator = new HexagonalRuleGenerator(3);
        assertThat(hexagonalRuleGenerator.totalNumOfRules, is(216));
    }

    @Test
    void increaseByOne_worksCorrectly() {
        for (int i = 0; i < 6; i++) {
            hexagonalRuleGenerator.next();
        }
        assertThat(hexagonalRuleGenerator.intArray[1], is(1));
        assertThat(hexagonalRuleGenerator.intArray[0], is(0));
    }

    @Test
    void hasNext_worksCorrectly() {
        for (int i = 0; i < 35; i++) {
            hexagonalRuleGenerator.next();
        }
        assertThat(hexagonalRuleGenerator.hasNext(), is(true));

        hexagonalRuleGenerator.next();
        assertThat(hexagonalRuleGenerator.hasNext(), is(false));
    }

    @Test
    void generateRule_worksCorrectly() {
        hexagonalRuleGenerator.intArray[0]=1;
        hexagonalRuleGenerator.intArray[1]=5;
        assertThat(hexagonalRuleGenerator.next(), is(List.of("R2", "L1")));

        hexagonalRuleGenerator = new HexagonalRuleGenerator(2);
        hexagonalRuleGenerator.next();
        hexagonalRuleGenerator.next();
        assertThat(hexagonalRuleGenerator.next(), is(List.of("U", "N")));

        hexagonalRuleGenerator.next();
        assertThat(hexagonalRuleGenerator.next(), is(List.of("L1", "N")));
    }
}
