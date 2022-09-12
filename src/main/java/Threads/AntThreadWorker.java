package Threads;

import Logic.RulesGenerator;
import Utils.Settings;

import java.util.List;

public class AntThreadWorker {
    private List<List<String>> items;

    private List<List<String>> rules;

    public AntThreadWorker(List<List<String>> items) {
        this.items = items;
    }

    public void doWork() {
        RulesGenerator rulesGenerator = new RulesGenerator(Settings.RULES_LENGTH);
        rules = rulesGenerator.getAllRulesForThreads(Settings.THREADS);
        for (List<String> item : rules) {
            Thread t = new Thread(new AntRunnable(item));
            t.start();
        }
    }


}
