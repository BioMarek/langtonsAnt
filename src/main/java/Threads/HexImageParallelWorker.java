package Threads;

import Logic.HexRuleGenerator;
import Utils.HexRule;
import Utils.Settings;

import java.util.List;

public class HexImageParallelWorker {
    public void drawAllRulesInParallel() {
        HexRuleGenerator hexRuleGenerator = new HexRuleGenerator(Settings.RULES_LENGTH);
        List<List<HexRule>> rules = hexRuleGenerator.getAllRulesForThreads(Settings.THREADS);
        for (List<HexRule> item : rules) {
            Thread t = new Thread(new HexImageRunnable(item));
            t.start();
        }
    }
}
