package Utils;

import java.awt.*;

public class ColorsPicker {
    public static final Color WHITE = new Color(255, 255, 255, Settings.ALPHA);
    public static final Color RED = new Color(255, 0, 0, Settings.ALPHA);
    public static final Color GREEN = new Color(0, 255, 0, Settings.ALPHA);
    public static final Color BLUE = new Color(0, 0, 255, Settings.ALPHA);
    public static final Color YELLOW = new Color(255, 255, 0, Settings.ALPHA);
    public static final Color ORANGE = new Color(255, 200, 0, Settings.ALPHA);
    public static final Color MAGENTA = new Color(255, 0, 255, Settings.ALPHA);
    public static final Color CYAN = new Color(0, 255, 255, Settings.ALPHA);
    public static final Color PINK = new Color(255, 175, 175, Settings.ALPHA);

    public static final Color VIOLET = new Color(127, 0, 255, Settings.ALPHA);
    public static final Color BROWN = new Color(139, 69, 19, Settings.ALPHA);
    public static final Color LIGHT_GREEN = new Color(128, 255, 0, Settings.ALPHA);
    public static final Color LIGHT_BLUE = new Color(0, 128, 255, Settings.ALPHA);
    public static final Color OLIVE = new Color(128, 128, 0, Settings.ALPHA);
    public static final Color NAVY = new Color(0, 0, 128, Settings.ALPHA);
    public static final Color TEAL = new Color(0, 128, 128, Settings.ALPHA);
    public static final Color CORAL = new Color(240, 128, 128, Settings.ALPHA);
    public static final Color KHAKI = new Color(240, 232, 170, Settings.ALPHA);
    public static final Color BACKGROUND = new Color(40, 40, 40, 255);

    /**
     * Mapping of grid numbers to colors. Sets {@link Graphics2D} to color represented by a number.
     *
     * @param color color number
     */
    public static void setColor(Graphics2D graphics, int color) {

        switch (color) {
            case -1 -> graphics.setColor(ColorsPicker.BACKGROUND);
            case 0 -> graphics.setColor(ColorsPicker.WHITE);
            case 1 -> graphics.setColor(ColorsPicker.RED);
            case 2 -> graphics.setColor(ColorsPicker.GREEN);
            case 3 -> graphics.setColor(ColorsPicker.BLUE);
            case 4 -> graphics.setColor(ColorsPicker.YELLOW);
            case 5 -> graphics.setColor(ColorsPicker.ORANGE);
            case 6 -> graphics.setColor(ColorsPicker.VIOLET);
            case 7 -> graphics.setColor(ColorsPicker.MAGENTA);
            case 8 -> graphics.setColor(ColorsPicker.CYAN);
            case 9 -> graphics.setColor(ColorsPicker.PINK);
            case 10 -> graphics.setColor(ColorsPicker.BROWN);
            case 11 -> graphics.setColor(ColorsPicker.LIGHT_GREEN);
            case 12 -> graphics.setColor(ColorsPicker.LIGHT_BLUE);
            case 13 -> graphics.setColor(ColorsPicker.OLIVE);
            case 14 -> graphics.setColor(ColorsPicker.NAVY);
            case 15 -> graphics.setColor(ColorsPicker.TEAL);
            case 16 -> graphics.setColor(ColorsPicker.CORAL);
            case 17 -> graphics.setColor(ColorsPicker.KHAKI);
        }
    }
}
