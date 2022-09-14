package Utils;

import java.awt.*;

public class ColorsPicker {
    /**
     * Mapping of grid numbers to colors. Sets {@link Graphics2D} to color represented by a number.
     *
     * @param color color number
     */
    public static void setColor(Graphics2D graphics, int color) {
        Color WHITE = new Color(255, 255, 255, Settings.ALPHA);
        Color RED = new Color(255, 0, 0, Settings.ALPHA);
        Color GREEN = new Color(0, 255, 0, Settings.ALPHA);
        Color BLUE = new Color(0, 0, 255, Settings.ALPHA);
        Color YELLOW = new Color(255, 255, 0, Settings.ALPHA);
        Color ORANGE = new Color(255, 200, 0, Settings.ALPHA);
        Color MAGENTA = new Color(255, 0, 255, Settings.ALPHA);
        Color CYAN = new Color(0, 255, 255, Settings.ALPHA);
        Color PINK = new Color(255, 175, 175, Settings.ALPHA);

        Color VIOLET = new Color(127, 0, 255, Settings.ALPHA);
        Color BROWN = new Color(139, 69, 19, Settings.ALPHA);
        Color LIGHT_GREEN = new Color(128, 255, 0, Settings.ALPHA);
        Color LIGHT_BLUE = new Color(0, 128, 255, Settings.ALPHA);
        Color OLIVE = new Color(128, 128, 0, Settings.ALPHA);
        Color NAVY = new Color(0, 0, 128, Settings.ALPHA);
        Color TEAL = new Color(0, 128, 128, Settings.ALPHA);
        Color CORAL = new Color(240, 128, 128, Settings.ALPHA);
        Color KHAKI = new Color(240, 232, 170, Settings.ALPHA);
        Color BACKGROUND = new Color(40, 40, 40, 255);

        switch (color) {
            case -1 -> graphics.setColor(BACKGROUND);
            case 0 -> graphics.setColor(WHITE);
            case 1 -> graphics.setColor(RED);
            case 2 -> graphics.setColor(GREEN);
            case 3 -> graphics.setColor(BLUE);
            case 4 -> graphics.setColor(YELLOW);
            case 5 -> graphics.setColor(ORANGE);
            case 6 -> graphics.setColor(VIOLET);
            case 7 -> graphics.setColor(MAGENTA);
            case 8 -> graphics.setColor(CYAN);
            case 9 -> graphics.setColor(PINK);
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
