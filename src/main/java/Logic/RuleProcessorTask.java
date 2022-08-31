package Logic;

import Utils.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RuleProcessorTask implements Runnable{
    private final int squares = Settings.SIZE_IN_PIXELS / Settings.SIZE_OF_SQUARE;
    private final int name;
    private final List<String> rules;
    private Ant ant;

    public RuleProcessorTask(List<String> rules, int name) {
        this.rules = rules;
        this.name = name;
    }

    @Override
    public void run() {
        final String orginalName = Thread.currentThread().getName();
        Thread.currentThread().setName(orginalName + " - " + name);
        for (String rule : rules) {
            System.out.println("working on: "  + Thread.currentThread().getName() + " " + rule);
            ant = new Ant(squares, Settings.MAX_MOVES, rule);
            saveImageWithoutPanel(rule);
        }
    }

    public static void doWork() {
        RulesGenerator rulesGenerator = new RulesGenerator(Settings.RULES_LENGTH);
        List<List<String>> rulesLists = rulesGenerator.getAllRulesForThreads(Settings.THREADS);
        rulesLists.parallelStream().forEach(list -> {
            ExecutorService executorService = Executors.newFixedThreadPool(Settings.THREADS);
            int name = 0;
            for (List<String> rules: rulesLists){
                RuleProcessorTask ruleProcessorTask = new RuleProcessorTask(rules, name);
                executorService.submit(ruleProcessorTask);
                name++;
            }
        });

    }

    private void saveImageWithoutPanel(String rule) {
        String fileName = String.format(Settings.BASE_PATH + "/%d/%s.png", rule.length(), rule);
        BufferedImage bImg = new BufferedImage(Settings.SIZE_IN_PIXELS, Settings.SIZE_IN_PIXELS, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bImg.createGraphics();

        ant.allMoves();
        ant.draw(graphics);

        try {
            ImageIO.write(bImg, "png", new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
