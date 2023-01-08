package Logic;

import Utils.RotationHexagonal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * moves: N (no change), R1 (60° clockwise), R2 (120° clockwise), U (180°), L2 (120° counter-clockwise), L1 (60° counter-clockwise)
 */
public class HexagonalRuleGenerator implements Iterator<List<RotationHexagonal>> {
    private final int rulesLength;
    public int[] intArray;
    private int rulesReturned = 0;
    public final int totalNumOfRules;

    public HexagonalRuleGenerator(int rulesLength) {
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
    public List<RotationHexagonal> next() {
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

    private List<RotationHexagonal> generateRule() {
        List<RotationHexagonal> result = new ArrayList<>();
        for (int move : intArray) {
            switch (move) {
                case 0 -> result.add(RotationHexagonal.N);
                case 1 -> result.add(RotationHexagonal.R1);
                case 2 -> result.add(RotationHexagonal.R2);
                case 3 -> result.add(RotationHexagonal.U);
                case 4 -> result.add(RotationHexagonal.L2);
                case 5 -> result.add(RotationHexagonal.L1);
            }
        }
        return result;
    }
}
