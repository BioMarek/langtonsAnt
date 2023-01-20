package Threads;

import Graphic.Visualization.AntGraphicSingle;
import Graphic.Visualization.HexGraphicSingle;
import Logic.Ant.Ant;
import Logic.Ant.HexAnt;
import Logic.Ant.SquareAnt;
import Logic.Rule.Rule;
import Utils.Settings;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ImageRunnable implements Runnable {
    private final List<Rule> rules;
    private final AtomicInteger counter;
    private final CountDownLatch latch;
    private final HexGraphicSingle hexGraphicSingle = new HexGraphicSingle();
    private final AntGraphicSingle antGraphicSingle = new AntGraphicSingle();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public ImageRunnable(List<Rule> rules, AtomicInteger counter, CountDownLatch latch) {
        this.rules = rules;
        this.counter = counter;
        this.latch = latch;
    }

    @Override
    public void run() {
        for (int i = 0; i < rules.size(); i++) {
            if (i % 100 == 0)
                System.out.println(ANSI_RED + "--- " + i + " out of " + rules.size() + " done" + ANSI_RESET);
            saveImage(rules.get(i));
        }
        latch.countDown();
    }

    /**
     * Saves image generated by ant running given rule. Creates image from grid and saves it directly into file. Saves
     * only images that should be saved based on Ant rules.
     *
     * @param rule that ant is running
     */
    private void saveImage(Rule rule) {
        System.out.println(Thread.currentThread().getName() + " working on: " + rule);
        Ant ant;

        if (Objects.equals(rule.getType(), "hex")) {
            ant = new HexAnt(rule);
            hexGraphicSingle.ant = ant;
        } else {
            ant = new SquareAnt(rule);
            antGraphicSingle.squareAnt = ant;
        }

        ant.allMoves();
        if (ant.shouldBeSaved()) {
            BufferedImage bImg = new BufferedImage(Settings.GRID_WIDTH, Settings.BACKGROUND_HEIGHT, BufferedImage.TYPE_INT_RGB);
            hexGraphicSingle.drawImage(bImg.createGraphics());

            try {
                String path = String.format(Settings.IMAGE_BASE_PATH + "/%d/%s.png", rule.getSize(), rule);
                Files.createDirectories(Paths.get(path));
                ImageIO.write(bImg, "png", new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("--- not saving: " + rule);
            counter.getAndIncrement();
        }
    }
}
