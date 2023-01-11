package Logic;

import Utils.HexMove;
import Utils.HexRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * moves: N (no change), R1 (60° clockwise), R2 (120° clockwise), U (180°), L2 (120° counter-clockwise), L1 (60° counter-clockwise)
 */
public class HexRuleGenerator implements Iterator<HexRule> {
    private final int rulesLength;
    public int[] intArray;
    private int rulesReturned = 0;
    public final int totalNumOfRules;

    public HexRuleGenerator(int rulesLength) {
        if (rulesLength < 2) {
            throw new RuntimeException("Rule must be longer than 2");
        }
        this.rulesLength = rulesLength;
        this.totalNumOfRules = (int) Math.pow(6, rulesLength); // non-reduced number rules
        this.intArray = new int[rulesLength];
        Arrays.fill(intArray, 0);
    }

    @Override
    public boolean hasNext() {
        return rulesReturned < totalNumOfRules;
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
        return new HexRule(result);
    }
    public List<List<HexRule>> getAllRulesForThreads(int threads) {
        List<List<HexRule>> result = new ArrayList<>();
        int forThread = totalNumOfRules / threads + 1;

        for (int i = 0; i < threads; i++) {
            List<HexRule> sublist = new ArrayList<>();
            int count = 0;
            while (hasNext() && count < forThread) {
                sublist.add(next());
                count++;
            }
            result.add(sublist);
        }
        return result;
    }
}
