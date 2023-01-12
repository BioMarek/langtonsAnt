package Threads;

import Logic.RulesGenerator;
import Utils.SquareRule;
import Utils.Settings;

import java.util.List;

/**
 * Divides list of generated rules into sub-lists and evaluates in parallel them in {@link ImageRunnable}.
 */
public class ImageParallelWorker {
    public void drawAllRulesInParallel() {
        RulesGenerator rulesGenerator = new RulesGenerator(Settings.RULES_LENGTH);
        List<List<SquareRule>> rules = rulesGenerator.getAllRulesForThreads(Settings.THREADS);
        for (List<SquareRule> item : rules) {
            Thread t = new Thread(new ImageRunnable(item));
            t.start();
        }
    }
}
