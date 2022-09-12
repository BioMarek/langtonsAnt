package Threads;

import Logic.Ant;
import Logic.InterestingnessEvaluator;
import Utils.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class AntRunnable implements Runnable{

    private List<String> rules;
    private Ant ant;

    public AntRunnable(List<String> rules) {
        this.rules = rules;
    }

    @Override
    public void run() {
        for (String rule : rules){
            saveImageWithoutPanel(rule);
        }
    }

    /**
     * Saves image generated by ant running given rule. Creates image from grid and saves it directly into file.
     *
     * @param rule that ant is running
     */
    private void saveImageWithoutPanel(String rule) {
        ant = new Ant(Settings.SIZE_IN_PIXELS / Settings.SIZE_OF_SQUARE, Settings.MAX_MOVES, rule);


        System.out.println(Thread.currentThread().getName() + " working on: " + rule);
        InterestingnessEvaluator interestingnessEvaluator = new InterestingnessEvaluator(ant.grid);
        String fileName;

        ant.allMoves();

        if (Settings.NO_EVAL) {
            fileName = String.format(Settings.BASE_PATH + "/%d/%s.png", rule.length(), rule);
        } else {
            if (Settings.ONLY_HIGHWAYS)
                if (interestingnessEvaluator.highwayEvaluator(Settings.FILED_PORTION_LIMIT) < 2.2D)
                    return;
                else
                    fileName = String.format("./images/%.2f_%s.png", interestingnessEvaluator.highwayEvaluator(Settings.FILED_PORTION_LIMIT), rule);
            else
                fileName = String.format("./images/%.2f_%s.png", interestingnessEvaluator.getFinalScore(), rule);
        }


        BufferedImage bImg = new BufferedImage(Settings.SIZE_IN_PIXELS, Settings.SIZE_IN_PIXELS, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bImg.createGraphics();
        ant.draw(graphics);

        try {
            ImageIO.write(bImg, "png", new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
