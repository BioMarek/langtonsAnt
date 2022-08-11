import Logic.RulesGenerator;
import org.junit.jupiter.api.Test;

public class GridPanelTest {
    private RulesGenerator rulesGenerator = new RulesGenerator();

    @Test
    void rulesGeneratorTest() {
        rulesGenerator.generateRules(5);
        System.out.println(rulesGenerator
                .allRules.
                stream()
                .filter(str -> str.length() == 5)
                .toList());
    }
}
