package Utils;

import java.awt.*;

public class ColorsPicker {
    /**
     * Mapping of grid numbers to colors. Sets {@link Graphics2D} to color represented by a number.
     *
     * @param color color number
     */
    public static void setColor(Graphics2D graphics, int color) {
        Color VIOLET = new Color(127, 0, 255);
        Color BROWN = new Color(139, 69, 19);
        Color LIGHT_GREEN = new Color(128, 255, 0);
        Color LIGHT_BLUE = new Color(0, 128, 255);
        Color OLIVE = new Color(128, 128, 0);
        Color NAVY = new Color(0, 0, 128);
        Color TEAL = new Color(0, 128, 128);
        Color CORAL = new Color(240, 128, 128);
        Color KHAKI = new Color(240, 232, 170);
        Color BACKGROUND = new Color(41, 41, 41, 255);

        switch (color) {
            case -1 -> graphics.setColor(BACKGROUND);
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
