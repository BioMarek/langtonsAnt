package Logic.RuleGenerator;

import Logic.Rule.Rule;
import Logic.Rule.SquareRule;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Rules are saved as binary array of 'R's and 'L's which correspond to 1s and 0s in binary array. Array is returned
 * as string and increased by one to get next rule. All Rs and Ls rules are not returned as they don't create image.
 * Also, only half of rules is generated as LRR is same as RLL but mirrored there is no need to generate complementary
 * rules.
 */
public class SquareRulesGenerator extends RuleGenerator implements Iterator<Rule> {
    public char[] charArray;

    /**
     * Generates all rules of given length without all L and all R rules.
     *
     * @param rulesLength number of Rs and Ls in total in rule
     */
    public SquareRulesGenerator(int rulesLength) {
        this.rulesLength = rulesLength;
        this.totalNumOfRules = (int) Math.pow(2, rulesLength - 1);
        charArray = new char[rulesLength];
        Arrays.fill(charArray, 'L');
        this.rulesReturned = 1;
    }

    @Override
    public SquareRule next() {
        increaseByOne();
        rulesReturned++;
        return new SquareRule(new String(charArray), 1, 1, 1);
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
}
