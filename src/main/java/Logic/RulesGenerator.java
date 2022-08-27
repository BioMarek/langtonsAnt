package Logic;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Rules are saved as binary array of 'R's and 'L's which correspond to 1s and 0s in binary array. Array is returned
 * as string and increased by one to get next rule. All Rs and Ls rules are not returned as they don't create image.
 */
public class RulesGenerator implements Iterator<String> {
    private final int length;
    public char[] charArray;

    /**
     * Generates all rules of given length without all L and all R rules.
     *
     * @param length number of Rs and Ls in total in rule
     */
    public RulesGenerator(int length) {
        this.length = length;
        charArray = new char[length];
        Arrays.fill(charArray, 'L');
    }

    @Override
    public boolean hasNext() {
        return !allRulesExhausted();
    }

    @Override
    public String next() {
        increaseByOne();
        return new String(charArray);
    }

    /**
     * Checks whether all rules were already returned. It doesn't check 0-th index as it doesn't matter. All Rs are
     * not allowed and rule LR...R will become all Rs by adding one. And array will be checked once LR...R was already
     * returned therefore it doesn't matter what letter is on the 0-th index.
     *
     * @return true if all correct rules have been returned, false otherwise.
     */
    public boolean allRulesExhausted() {
        for (int i = 1; i < length; i++) {
            if (charArray[i] == 'L')
                return false;
        }
        return true;
    }

    public void increaseByOne() {
        int index = 0;
        while (index < length) {
            if (charArray[index] == 'L') {
                charArray[index] = 'R';
                break;
            }
            if (charArray[index] == 'R')
                charArray[index] = 'L';
            index++;
        }
    }
}
