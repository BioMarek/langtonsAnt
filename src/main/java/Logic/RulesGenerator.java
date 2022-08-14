package Logic;

import java.util.ArrayList;
import java.util.List;

public class RulesGenerator {
    private static final List<String> allRules = new ArrayList<>();

    public static List<String> generateRules(int minLength, int maxLength) {
        // TODO refactor so bounds work same as in regular Java
        int finalMinLength = Math.max(minLength, 2);
        generateRecursive(maxLength, "RL");
        generateRecursive(maxLength, "LR");
        return allRules.stream()
                .filter(rule -> rule.length() > finalMinLength)
                .toList();
    }

    private static void generateRecursive(int maxLength, String subString) {
        allRules.add(subString);
        if (subString.length() == maxLength)
            return;

        generateRecursive(maxLength, subString + "R");
        generateRecursive(maxLength, subString + "L");
    }
}
