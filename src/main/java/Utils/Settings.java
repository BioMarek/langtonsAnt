package Utils;

public class Settings {
    // General

    public static int ALPHA = 200;
    public static boolean SHOW_GRID = false;
    public static String RULE = "RL";
    public static int DELAY = 10;
    public static int SKIP = 10;
//    public static long MAX_MOVES = 1_000_000;
//    public static int SIZE_IN_PIXELS = 900;
//    public static int SIZE_OF_SQUARE = 10;

    public static long MAX_MOVES = 20_000_000;
    public static int SIZE_IN_PIXELS = 2000;
    public static int SIZE_OF_SQUARE = 2;

    // Settings for image generation
    public static int RULES_LENGTH = 4;
    public static boolean ONLY_HIGHWAYS = false; // saves image only if highway is detected
    public static double FILED_PORTION_LIMIT = 0.5D; // highest portion of filled blocks on border allowed in highway
    public static boolean NO_EVAL = true;
    public static String BASE_PATH = "/media/marek/Data/AntImages";
    public static int THREADS = 6;
}
