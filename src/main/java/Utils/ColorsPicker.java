package Utils;

import java.awt.Color;
import java.awt.Graphics2D;

public class ColorsPicker {
    /**
     * Mapping of grid numbers to colors. Sets {@link Graphics2D} to color represented by a number.
     *
     * @param color color number
     */
    public static void setColor(Graphics2D graphics, int color) {
        graphics.setColor(numberToColor(color));
    }

    public static Color numberToColor(int num) {
        switch (num) {
            case -1:
                return Colors.BACKGROUND.getColor();
            case 0:
                return Colors.GREY.getColor();
            case 1:
                return Colors.RED.getColor();
            case 2:
                return Colors.GREEN.getColor();
            case 3:
                return Colors.BLUE.getColor();
            case 4:
                return Colors.YELLOW.getColor();
            case 5:
                return Colors.ORANGE.getColor();
            case 6:
                return Colors.VIOLET.getColor();
            case 7:
                return Colors.MAGENTA.getColor();
            case 8:
                return Colors.CYAN.getColor();
            case 9:
                return Colors.PINK.getColor();
            case 10:
                return Colors.BROWN.getColor();
            case 11:
                return Colors.LIGHT_GREEN.getColor();
            case 12:
                return Colors.LIGHT_BLUE.getColor();
            case 13:
                return Colors.OLIVE.getColor();
            case 14:
                return Colors.NAVY.getColor();
            case 15:
                return Colors.TEAL.getColor();
            case 16:
                return Colors.CORAL.getColor();
            case 17:
                return Colors.KHAKI.getColor();
        }
        throw new RuntimeException("Wrong number supplied");
    }
}
