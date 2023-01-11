package Threads;

import Graphic.Visualization.HexGraphicSingle;
import Logic.HexAnt;
import Utils.HexRule;
import Utils.Settings;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class HexImageRunnable implements Runnable {
    private final List<HexRule> rules;
    private final AtomicInteger counter;
    private final CountDownLatch latch;

    public HexImageRunnable(List<HexRule> rules, AtomicInteger counter, CountDownLatch latch) {
        this.rules = rules;
        this.counter = counter;
        this.latch = latch;
    }

    @Override
    public void run() {
        for (HexRule rule : rules) {
            saveImage(rule);
        }
        latch.countDown();
    }

    /**
     * Saves image generated by ant running given rule. Creates image from grid and saves it directly into file. Saves
     * only images that used all colors i.e. weren't generated previously.
     *
     * @param hexRule that ant is running
     */
    private void saveImage(HexRule hexRule) {
        System.out.println(Thread.currentThread().getName() + " working on: " + hexRule);

        HexAnt hexAnt = new HexAnt(hexRule);
        HexGraphicSingle hexGraphicSingle = new HexGraphicSingle(hexAnt);
        hexAnt.allMoves();
        if (hexAnt.usedTopColor) {
            BufferedImage bImg = new BufferedImage(Settings.GRID_WIDTH, Settings.BACKGROUND_HEIGHT, BufferedImage.TYPE_INT_RGB);
            hexGraphicSingle.drawImage(bImg.createGraphics());

            try {
                String path = String.format(Settings.IMAGE_BASE_PATH + "/%d/%s.png", hexRule.rule.size(), hexRule);
                Files.createDirectories(Paths.get(path));
                ImageIO.write(bImg, "png", new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("--- not saving: " + hexRule);
            counter.getAndIncrement();
        }
    }
}
