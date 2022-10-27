package Utils;

public class Settings {
    // General
    public static int ALPHA = 200;
    public static boolean SHOW_GRID = false;
    public static String RULE = "RRLLLRLLLLLLLLL";
    public static int DELAY = 10;
    public static int SKIP = 40000;
    public static long MAX_MOVES = 50_000_000;
    public static int SIZE_IN_PIXELS = 1000;
    public static int SIZE_OF_SQUARE = 1;

//    public static long MAX_MOVES = 50_000_000;
//    public static int SIZE_IN_PIXELS = 2000;
//    public static int SIZE_OF_SQUARE = 2;

    // Settings for image generation
    public static int RULES_LENGTH = 10;
    public static boolean ONLY_HIGHWAYS = false; // saves image only if highway is detected
    public static double FILED_PORTION_LIMIT = 0.5D; // highest portion of filled blocks on border allowed in highway
    public static boolean NO_EVAL = true;
    public static String BASE_PATH = "/media/marek/Data/AntImages";
    public static int THREADS = 6;

    // Settings for gif generation
    public static int GIF_DELAY = 35; // 35ms delay for video
    public static int GIF_NUM_IMAGES = 240; // number of images in video, we expect 30 images per second, 240 image, 180 highway
    public static int GIF_WIDTH = 1333; // set to 1000 when 4 images on screen
    public static int GIF_HEIGHT = 1000;
    public static String GIF_BASE_PATH = "./gifs/";
    public static boolean INFO_FOR_4_IMAGES = false; // image with only rule info, used when there is 4 images on screen
    // use to show only center of image and fill entire screen with even if some part already reached the border,
    // use 500 for thumbnail image
    public static int IMAGE_PADDING = 200;

    public static int SLOWDOWN_STEPS = 100_000_000;
    public static double SLOWDOWN_MODIFIER = 1.0;
}
