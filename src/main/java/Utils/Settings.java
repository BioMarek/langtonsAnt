package Utils;

public class Settings {
    // General
    public static boolean SHOW_GRID = true;

    // Settings for visualization
    public static String RULE = "RL";
    public static int DELAY = 10;
    public static int SKIP = 10;
    public static long MAX_MOVES = 1_000_000;
    public static int SIZE_IN_PIXELS = 900;
    public static int SIZE_OF_SQUARE = 10;

    // Settings for image generation
    public static int I_RULES_MIN_LENGTH = 13;
    public static int I_RULES_MAX_LENGTH = 14;
    public static long I_MAX_MOVES = 1_000_000;
    public static int I_SIZE_IN_PIXELS = 900;
    public static int I_SIZE_OF_SQUARE = 10;
    public static boolean I_ONLY_HIGHWAYS = true;
}
