package Threads;

import Logic.HexRuleGenerator;
import Utils.HexRule;
import Utils.Settings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class HexImageParallelWorker {
    // TODO merge with ImageParallelWorker
    private final AtomicInteger counter = new AtomicInteger();
    private final CountDownLatch latch = new CountDownLatch(Settings.THREADS);

    public void drawAllRulesInParallel() {
        deleteFilesInDirectory();
        HexRuleGenerator hexRuleGenerator = new HexRuleGenerator(Settings.RULES_LENGTH);
        List<List<HexRule>> rules = hexRuleGenerator.getAllRulesForThreads(Settings.THREADS);
        for (List<HexRule> item : rules) {
            Thread t = new Thread(new HexImageRunnable(item, counter, latch));
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

    private int totalElements(List<List<HexRule>> rules) {
        int result = 0;
        for (List<HexRule> ruleList : rules)
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
