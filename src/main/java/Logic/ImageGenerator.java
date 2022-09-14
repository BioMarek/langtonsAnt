package Logic;

import Utils.Settings;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageGenerator {
    private Ant ant;

    /**
     * Cycles through all rules of length Settings.RULES_LENGTH and saves images into file.
     */
    public void drawAllRules() {
        RulesGenerator rulesGenerator = new RulesGenerator(Settings.RULES_LENGTH);
        int squares = Settings.SIZE_IN_PIXELS / Settings.SIZE_OF_SQUARE;
        while (rulesGenerator.hasNext()) {
            String rule = rulesGenerator.next();
            System.out.println("working on: " + rule);
            ant = new Ant(squares, Settings.MAX_MOVES, rule);
            saveImageWithoutPanel(rule);
        }
    }

    /**
     * Saves image generated by ant running given rule. Creates image from grid and saves it directly into file.
     *
     * @param rule that ant is running
     */
    private void saveImageWithoutPanel(String rule) {
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
        ant.draw(bImg.createGraphics());

        try {
            ImageIO.write(bImg, "png", new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
