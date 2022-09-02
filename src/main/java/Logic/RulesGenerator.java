package Logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Rules are saved as binary array of 'R's and 'L's which correspond to 1s and 0s in binary array. Array is returned
 * as string and increased by one to get next rule. All Rs and Ls rules are not returned as they don't create image.
 * Also, only half of rules is generated as LRR is same as RLL but mirrored there is no need to generate complementary
 * rules.
 */
public class RulesGenerator implements Iterator<String> {
    private final int rulesLength;
    public char[] charArray;
    private int rulesReturned = 1;
    private final int totalNumOfRules;

    /**
     * Generates all rules of given length without all L and all R rules.
     *
     * @param rulesLength number of Rs and Ls in total in rule
     */
    public RulesGenerator(int rulesLength) {
        this.rulesLength = rulesLength;
        this.totalNumOfRules = (int) Math.pow(2, rulesLength - 1);
        charArray = new char[rulesLength];
        Arrays.fill(charArray, 'L');
    }

    @Override
    public boolean hasNext() {
        return rulesReturned < totalNumOfRules;
    }

    @Override
    public String next() {
        increaseByOne();
        rulesReturned++;
        return new String(charArray);
    }

    public void increaseByOne() {
        int index = 0;
        while (index < rulesLength) {
            if (charArray[index] == 'L') {
                charArray[index] = 'R';
                break;
            }
            if (charArray[index] == 'R')
                charArray[index] = 'L';
            index++;
        }
    }

    public List<List<String>> getAllRulesForThreads(int threads) {
        List<List<String>> result = new ArrayList<>();
        int forThread = totalNumOfRules / threads + 1;

        for (int i = 0; i < threads; i++) {
            List<String> sublist = new ArrayList<>();
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
