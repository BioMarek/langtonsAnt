package Logic;

import Utils.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImageGenerator {
    private Ant ant;

    /**
     * Cycles through all rules from length 2 to Settings.I_SIZE_IN_PIXELS and saves images into file.
     */
    public void drawAllRules() {
        List<String> rules = RulesGenerator.generateRules(Settings.I_RULES_MIN_LENGTH, Settings.I_RULES_MAX_LENGTH);
        int squares = Settings.I_SIZE_IN_PIXELS / Settings.I_SIZE_OF_SQUARE;
        for (String rule : rules) {
            System.out.println("working on: " + rule);
            ant = new Ant(squares, Settings.I_MAX_MOVES, rule);
            saveImageWithoutPanel(rule);
        }
    }

    /**
     * Saves image generated by ant running given rule. Creates image from grid and saves it directly into file.
     *
     * @param rule that ant is running
     */
    private void saveImageWithoutPanel(String rule) {
        BufferedImage bImg = new BufferedImage(Settings.I_SIZE_IN_PIXELS, Settings.I_SIZE_IN_PIXELS, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bImg.createGraphics();
        ant.allMoves();
        ant.draw(graphics);
        InterestingnessEvaluator interestingnessEvaluator = new InterestingnessEvaluator(ant.grid);

        try {
            ImageIO.write(bImg, "png", new File(String.format("./images/%.2f_%s.png", interestingnessEvaluator.getFinalScore(), rule)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
