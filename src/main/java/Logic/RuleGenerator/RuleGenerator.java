package Logic.RuleGenerator;

import Logic.Rule.Rule;
import Utils.Settings;

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

    /**
     * Generates all rules of length specified in {@link Settings} divides them into lists of equal size. Each list will
     * be processed by one thread. Number of threads is again specified in {@link Settings}.
     *
     * @return List of rules
     */
    public List<List<Rule>> getAllRulesForThreads() {
        List<List<Rule>> result = new ArrayList<>();
        int forThread = (totalNumOfRules / Settings.THREADS) + 1;

        for (int i = 0; i < Settings.THREADS; i++) {
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
