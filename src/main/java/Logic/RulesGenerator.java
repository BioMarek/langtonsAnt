package Logic;

import java.util.Arrays;
import java.util.Iterator;

public class RulesGenerator implements Iterator<String> {
    private final int length;
    public char[] charArray;
    private boolean first = true;

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
        if (first) {
            first = false;
            return new String(charArray);
        }
        increaseByOne();
        return new String(charArray);
    }

    public boolean allRulesExhausted() {
        for (int i = 0; i < length; i++) {
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
