import Logic.RuleGenerator.HexRuleGenerator;
import Logic.HexMove;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HexRuleGeneratorTest {
    private HexRuleGenerator hexRuleGenerator;

    @BeforeEach
    void setup() {
        hexRuleGenerator = new HexRuleGenerator(2);
    }

    @Test
    void numberOfRulesIsCorrect() {
        assertThat(hexRuleGenerator.totalNumOfRules, is(36));
        hexRuleGenerator = new HexRuleGenerator(3);
        assertThat(hexRuleGenerator.totalNumOfRules, is(216));
    }

    @Test
    void increaseByOne_worksCorrectly() {
        for (int i = 0; i < 6; i++) {
            hexRuleGenerator.next();
        }
        assertThat(hexRuleGenerator.intArray[1], is(1));
        assertThat(hexRuleGenerator.intArray[0], is(0));
    }

    @Test
    void hasNext_worksCorrectly() {
        for (int i = 0; i < 35; i++) {
            hexRuleGenerator.next();
        }
        assertThat(hexRuleGenerator.hasNext(), is(true));

        hexRuleGenerator.next();
        assertThat(hexRuleGenerator.hasNext(), is(false));
    }

    @Test
    void generateRule_worksCorrectly() {
        hexRuleGenerator.intArray[0]=1;
        hexRuleGenerator.intArray[1]=5;
        assertThat(hexRuleGenerator.next().rule, is(List.of(HexMove.R2, HexMove.L1)));

        hexRuleGenerator = new HexRuleGenerator(2);
        hexRuleGenerator.next();
        hexRuleGenerator.next();
        assertThat(hexRuleGenerator.next().rule, is(List.of(HexMove.U, HexMove.N)));

        hexRuleGenerator.next();
        assertThat(hexRuleGenerator.next().rule, is(List.of(HexMove.L1, HexMove.N)));
    }
}
