package Threads;

import Logic.Rule.Rule;
import Logic.RuleGenerator.RuleGenerator;
import Utils.Settings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static Utils.Util.formatTime;

public class ImageParallelWorker {
    private final AtomicInteger counter = new AtomicInteger();

    public void drawAllRulesInParallel(RuleGenerator ruleGenerator) {
        long start = System.currentTimeMillis();
        deleteFilesInDirectory();
        List<List<Rule>> rules = ruleGenerator.getAllRulesForThreads();
        CountDownLatch latch = new CountDownLatch(rules.size());

        for (List<Rule> item : rules) {
            Thread t = new Thread(new ImageRunnable(item, counter, latch));
            t.start();
        }
        try {
            latch.await();
            long stop = System.currentTimeMillis();
            System.out.println("There is: " + totalElements(rules) + " rules generated");
            System.out.println("Skipped:  " + counter);
            System.out.println("Saved:    " + (totalElements(rules) - counter.get()));
            System.out.println("It took:  " + formatTime(stop - start));
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
