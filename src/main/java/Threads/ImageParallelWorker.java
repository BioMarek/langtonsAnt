package Threads;

import Logic.RuleGenerator;
import Utils.Rule;
import Utils.Settings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ImageParallelWorker {
    private final AtomicInteger counter = new AtomicInteger();

    public void drawAllRulesInParallel(RuleGenerator ruleGenerator) {
        deleteFilesInDirectory();
        List<List<Rule>> rules = ruleGenerator.getAllRulesForThreads(Settings.THREADS);
        CountDownLatch latch = new CountDownLatch(rules.size());

        for (List<Rule> item : rules) {
            Thread t = new Thread(new ImageRunnable(item, counter, latch));
            t.start();
        }
        try {
            latch.await();
            System.out.println("\nThere is: " + totalElements(rules) + " rules total");
            System.out.println("Skipped:  " + counter);
            System.out.println("Saved:    " + (totalElements(rules) - counter.get()));
        } catch (InterruptedException ignored) {
        }
    }

    private int totalElements(List<List<Rule>> rules) {
        int result = 0;
        for (List<Rule> ruleList : rules)
            result += ruleList.size();
        return result;
    }

    private void deleteFilesInDirectory() {
        try {
            Files.walk(Path.of(String.format(Settings.IMAGE_BASE_PATH + "/%d/", Settings.RULES_LENGTH)))
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException ignored) {
        }
    }
}
