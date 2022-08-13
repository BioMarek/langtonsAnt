package Logic;

import java.util.ArrayList;
import java.util.List;

public class RulesGenerator {
    private static final List<String> allRules = new ArrayList<>();

    public static List<String> generateRules(int maxLength) {
        generateRecursive(maxLength, "RL");
        generateRecursive(maxLength, "LR");
        return allRules;
    }

    private static void generateRecursive(int maxLength, String subString) {
        allRules.add(subString);
        if (subString.length() == maxLength)
            return;

        generateRecursive(maxLength, subString + "R");
        generateRecursive(maxLength, subString + "L");
    }
}
