package Utils;

import java.awt.Color;
import java.awt.Graphics2D;

public enum Colors {
    LIGHT_GREY(new Color(210, 210, 210, Settings.ALPHA)),
    RED(new Color(255, 0, 0, Settings.ALPHA)),
    GREEN(new Color(0, 255, 0, Settings.ALPHA)),
    BLUE(new Color(0, 0, 255, Settings.ALPHA)),
    YELLOW(new Color(255, 255, 0, Settings.ALPHA)),
    ORANGE(new Color(255, 200, 0, Settings.ALPHA)),
    MAGENTA(new Color(255, 0, 255, Settings.ALPHA)),
    CYAN(new Color(0, 255, 255, Settings.ALPHA)),
    PINK(new Color(255, 175, 175, Settings.ALPHA)),

    VIOLET(new Color(127, 0, 255, Settings.ALPHA)),
    BROWN(new Color(139, 69, 19, Settings.ALPHA)),
    LIGHT_GREEN(new Color(128, 255, 0, Settings.ALPHA)),
    LIGHT_BLUE(new Color(0, 128, 255, Settings.ALPHA)),
    OLIVE(new Color(128, 128, 0, Settings.ALPHA)),
    NAVY(new Color(0, 0, 128, Settings.ALPHA)),
    TEAL(new Color(0, 128, 128, Settings.ALPHA)),
    CORAL(new Color(240, 128, 128, Settings.ALPHA)),
    KHAKI(new Color(240, 232, 170, Settings.ALPHA)),
    BACKGROUND(new Color(40, 40, 40, 255)),

    TEXT(new Color(215, 215, 215, 255));

    private final Color color;

    Colors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public static Color getColor(int ordinal) {
        return switch (ordinal) {
            case 0 -> LIGHT_GREY.getColor();
            case 1 -> RED.getColor();
            case 2 -> GREEN.getColor();
            case 3 -> BLUE.getColor();
            case 4 -> YELLOW.getColor();
            case 5 -> ORANGE.getColor();
            case 6 -> VIOLET.getColor();
            case 7 -> MAGENTA.getColor();
            case 8 -> CYAN.getColor();
            case 9 -> PINK.getColor();
            case 10 -> BROWN.getColor();
            case 11 -> LIGHT_GREEN.getColor();
            case 12 -> LIGHT_BLUE.getColor();
            case 13 -> OLIVE.getColor();
            case 14 -> NAVY.getColor();
            case 15 -> TEAL.getColor();
            case 16 -> CORAL.getColor();
            case 17 -> KHAKI.getColor();
            case -1 -> BACKGROUND.getColor();
            default -> throw new RuntimeException("Wrong number supplied");
        };
    }

    public static void setColor(Graphics2D graphics, int color) {
        graphics.setColor(getColor(color));
    }
}
