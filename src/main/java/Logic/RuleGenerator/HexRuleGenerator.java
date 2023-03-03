package Logic.RuleGenerator;

import Logic.HexMove;
import Logic.Rule.HexRule;
import Logic.Rule.Rule;
import Utils.Settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * moves: N (no change), R1 (60° clockwise), R2 (120° clockwise), U (180°), L2 (120° counter-clockwise), L1 (60° counter-clockwise)
 */
public class HexRuleGenerator extends RuleGenerator implements Iterator<Rule> {
    private static final Random random = new Random();
    private final Set<String> mirroredRules = new HashSet<>();
    private final String lastRule;
    private String currentRuleString;
    public int[] intArray;

    public HexRuleGenerator(int rulesLength) {
        if (rulesLength < 2) {
            throw new RuntimeException("Rule must be longer than 2");
        }
        this.rulesLength = rulesLength;
        this.intArray = new int[rulesLength];
        Arrays.fill(intArray, 0);
        this.rulesReturned = 0;
        this.lastRule = "5".repeat(rulesLength);
        this.currentRuleString = getRuleString();
    }

    @Override
    public HexRule next() {
        increaseByOne();
        rulesReturned++;
        return generateRule();
    }

    private void increaseByOne() {
        int index = 0;
        while (index < rulesLength) {
            if (intArray[index] < 5) {
                intArray[index]++;
                break;
            }
            if (intArray[index] == 5) {
                intArray[index] = 0;
                index++;
            }
        }
    }

    private HexRule generateRule() {
        List<HexMove> result = new ArrayList<>();
        for (int move : intArray) {
            switch (move) {
                case 0 -> result.add(HexMove.N);
                case 1 -> result.add(HexMove.R1);
                case 2 -> result.add(HexMove.R2);
                case 3 -> result.add(HexMove.U);
                case 4 -> result.add(HexMove.L2);
                case 5 -> result.add(HexMove.L1);
            }
        }
        return new HexRule(result, 1, 1);
    }

    @Override
    public List<List<Rule>> getAllRulesForThreads() {
        List<List<Rule>> result = new ArrayList<>();
        List<Rule> generatedRules = new ArrayList<>();

        if (Settings.RANDOM_RULES) {
            for (int i = 0; i < Settings.RANDOM_RULES_LIMIT; i++) {
                generateRandomRule();
                updateSets(generatedRules);
            }
        } else {
            while (!currentRuleString.equals(lastRule)) {
                increaseByOne();
                updateSets(generatedRules);
            }
        }

        if (rulesReturned > 12) {
            int forThread = (rulesReturned / Settings.THREADS) + 1;
            int start = 0;
            int end = forThread;
            for (int i = 0; i < Settings.THREADS; i++) {
                result.add(generatedRules.subList(start, end));
                start += forThread;
                end = Math.min(end + forThread, rulesReturned);
            }
        } else result.add(generatedRules);

        return result;
    }

    private void updateSets(List<Rule> generatedRules) {
        if (!mirroredRules.contains(currentRuleString)) {
            mirroredRules.add(getMirroredString());
            generatedRules.add(generateRule());
            rulesReturned++;
        }
        currentRuleString = getRuleString();
    }

    private String getMirroredString() {
        StringBuilder result = new StringBuilder();
        for (int i : intArray) {
            switch (i) {
                case 1 -> result.append("5");
                case 2 -> result.append("4");
                case 4 -> result.append("2");
                case 5 -> result.append("1");
                default -> result.append(i);
            }
        }
        return result.toString();
    }

    private String getRuleString() {
        StringBuilder result = new StringBuilder();
        for (int i : intArray) {
            result.append(i);
        }
        return result.toString();
    }

    private void generateRandomRule() {
        for (int i = 0; i < Settings.RULES_LENGTH; i++) {
            intArray[i] = random.nextInt(6);
        }
    }
}
