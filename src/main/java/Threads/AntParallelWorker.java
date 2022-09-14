package Threads;

import Logic.RulesGenerator;
import Utils.Settings;

import java.util.List;

/**
 * Divides list of generated rules into sublists and evaluates in parallel them in {@link AntRunnable}.
 */
public class AntParallelWorker {
    public void drawAllRulesInParallel() {
        RulesGenerator rulesGenerator = new RulesGenerator(Settings.RULES_LENGTH);
        List<List<String>> rules = rulesGenerator.getAllRulesForThreads(Settings.THREADS);
        for (List<String> item : rules) {
            Thread t = new Thread(new AntRunnable(item));
            t.start();
        }
    }
}
