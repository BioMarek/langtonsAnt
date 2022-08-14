import Logic.RulesGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GridPanelTest {
    private final int RULES_MAX_LENGTH = 5;
    private final int RULES_MIN_LENGTH = 2;

    @Test
    void rulesGeneratorTest() {
        List<String> rules = RulesGenerator.generateRules(RULES_MIN_LENGTH, RULES_MAX_LENGTH);
        System.out.println(rules.stream()
                .filter(str -> str.length() == RULES_MAX_LENGTH)
                .toList());
    }
}
