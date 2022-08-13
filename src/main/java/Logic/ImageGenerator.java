package Logic;

import Utils.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageGenerator {
    private final int squares = Settings.I_SIZE_IN_PIXELS / Settings.I_SIZE_OF_SQUARE;
    private static Graphics2D graphics;
    private Ant ant;

    public void drawAllRules() {
        java.util.List<String> rules = RulesGenerator.generateRules(Settings.I_RULES_MAX_LENGTH);
        for (String rule : rules) {
            Settings.RULE = rule;
            ant = new Ant(squares, rule);
            saveImageWithoutPanel(rule);
        }
    }

    private void saveImageWithoutPanel(String rule) {
        BufferedImage bImg = new BufferedImage(Settings.I_SIZE_IN_PIXELS, Settings.I_SIZE_IN_PIXELS, BufferedImage.TYPE_INT_RGB);
        graphics = bImg.createGraphics();
        ant.allMoves();
        draw();

        try {
            ImageIO.write(bImg, "png", new File("./" + rule + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts grid numbers to {@link Graphics2D}.
     */
    private void draw() {
        for (int row = 0; row < squares; row++) {
            for (int column = 0; column < squares; column++) {
                setColor(ant.grid[row][column]);
                graphics.fillRect(row * Settings.SIZE_OF_SQUARE,
                        column * Settings.SIZE_OF_SQUARE,
                        Settings.SIZE_OF_SQUARE,
                        Settings.SIZE_OF_SQUARE);
            }
        }
    }

    /**
     * Mapping of grid numbers to colors. Sets {@link Graphics2D} to color of current grid tile.
     *
     * @param color grid number
     */
    private void setColor(int color) {
        Color VIOLET = new Color(127, 0, 255);
        Color BROWN = new Color(139, 69, 19);
        Color LIGHT_GREEN = new Color(128, 255, 0);
        Color LIGHT_BLUE = new Color(0, 128, 255);
        Color OLIVE = new Color(128, 128, 0);
        Color NAVY = new Color(0, 0, 128);
        Color TEAL = new Color(0, 128, 128);
        Color CORAL = new Color(240, 128, 128);
        Color KHAKI = new Color(240, 232, 170);

        switch (color) {
            case -1 -> graphics.setColor(Color.BLACK);
            case 0 -> graphics.setColor(Color.WHITE);
            case 1 -> graphics.setColor(Color.RED);
            case 2 -> graphics.setColor(Color.GREEN);
            case 3 -> graphics.setColor(Color.BLUE);
            case 4 -> graphics.setColor(Color.YELLOW);
            case 5 -> graphics.setColor(Color.ORANGE);
            case 6 -> graphics.setColor(VIOLET);
            case 7 -> graphics.setColor(Color.MAGENTA);
            case 8 -> graphics.setColor(Color.CYAN);
            case 9 -> graphics.setColor(Color.PINK);
            case 10 -> graphics.setColor(BROWN);
            case 11 -> graphics.setColor(LIGHT_GREEN);
            case 12 -> graphics.setColor(LIGHT_BLUE);
            case 13 -> graphics.setColor(OLIVE);
            case 14 -> graphics.setColor(NAVY);
            case 15 -> graphics.setColor(TEAL);
            case 16 -> graphics.setColor(CORAL);
            case 17 -> graphics.setColor(KHAKI);
        }
    }
}
