package Utils;

import java.awt.*;

public enum Colors {
    WHITE(new Color(255, 255, 255, Settings.ALPHA)),
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
    BACKGROUND(new Color(40, 40, 40, 255));

    private final Color color;

    Colors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }
}
