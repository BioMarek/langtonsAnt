import Logic.RulesGenerator;
import org.junit.jupiter.api.Test;

public class GridPanelTest {
    private RulesGenerator rulesGenerator = new RulesGenerator();

    @Test
    void rulesGeneratorTest() {
        rulesGenerator.generateRules(4);
        System.out.println(rulesGenerator.allRules);
    }
}
