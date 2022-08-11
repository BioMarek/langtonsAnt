package Logic;

import java.util.ArrayList;
import java.util.List;

public class RulesGenerator {
    public List<String> allRules = new ArrayList<>();

    public void generateRules(int maxLength) {
        generateRecursive(maxLength, "RL");
        generateRecursive(maxLength, "LR");
    }

    public void generateRecursive(int maxLength, String addition) {
        allRules.add(addition);
        if (addition.length() == maxLength)
            return;

        generateRecursive(maxLength, addition + "R");
        generateRecursive(maxLength, addition + "L");
    }
}
