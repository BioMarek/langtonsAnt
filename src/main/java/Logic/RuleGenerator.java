package Logic;

import Utils.Rule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class RuleGenerator implements Iterator<Rule> {
    public int rulesLength;
    public int totalNumOfRules;
    public int rulesReturned = 0;

    @Override
    public boolean hasNext() {
        return rulesReturned < totalNumOfRules;
    }

    public List<List<Rule>> getAllRulesForThreads(int threads) {
        List<List<Rule>> result = new ArrayList<>();
        int forThread = totalNumOfRules / threads;

        for (int i = 0; i < threads; i++) {
            List<Rule> sublist = new ArrayList<>();
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
