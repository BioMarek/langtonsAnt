package Utils;

public class Settings {
    // General
    public static int ALPHA = 200;
    public static boolean SHOW_GRID = false;
    public static String RULE = "RL";
    public static int DELAY = 100; // realtime animation frame delay
    public static int SKIP = 40000; // steps to skip between frames
    public static long MAX_MOVES = 50_000_000;
    public static int SIZE_IN_PIXELS = 1000; // size of ant board
    public static int SIZE_OF_SQUARE = 1; // size of square on ant board

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

    // Settings for gif and mp4 generation
    public static int GIF_DELAY = 35; // 35ms delay for video
    public static int VIDEO_FPS = 30; // frames per second for mp4
    public static int VIDEO_NUM_IMAGES = 180; // number of images in video, we expect 30 images per second, 240 image, 180 highway
    public static int GIF_WIDTH = 1333; // set to 1000 when 4 images on screen
    public static int GIF_HEIGHT = 1000;
    public static String GIF_BASE_PATH = "./gifs/";
    public static boolean INFO_FOR_4_IMAGES = false; // image with only rule info, used when there is 4 images on screen
    // use to show only center of image and fill entire screen with even if some part already reached the border,
    // use 500 for thumbnail image
    public static int IMAGE_PADDING = 0;
    public static int SLOWDOWN_STEPS = 100_000_000;
    public static double SLOWDOWN_MODIFIER = 1.0;
    public static boolean EXPLANATION_ANIMATION = false; // true only when we are doing explanation animation, (more frames for animation)
    public static int FRAMES_BETWEEN_STEPS = 30; // for explanation animation frames
    public static int VIDEO_REPEAT_LAST_FRAME = 30; // how many times the last image should be repeated in video, 30 is one second

    public static void showExplanationSettings() {
        Settings.SHOW_GRID = true;
        Settings.SKIP = 1;
        Settings.MAX_MOVES = 300;
        Settings.SIZE_OF_SQUARE = 80;
        Settings.EXPLANATION_ANIMATION = true;
        Settings.IMAGE_PADDING = 0;
    }

    public static void generateHighwaysSettings() {
        Settings.SHOW_GRID = false;
        Settings.MAX_MOVES = 50_000_000;
        Settings.SIZE_OF_SQUARE = 1;
        Settings.VIDEO_NUM_IMAGES = 180;
        Settings.IMAGE_PADDING = 0;
    }

    public static void generateInterestingSettings() {
        Settings.SHOW_GRID = false;
        Settings.MAX_MOVES = 50_000_000;
        Settings.SIZE_OF_SQUARE = 1;
        Settings.VIDEO_NUM_IMAGES = 240;
        Settings.IMAGE_PADDING = 200;
    }

    public static void generateFourSettings() {
        Settings.SHOW_GRID = false;
        Settings.MAX_MOVES = 50_000_000;
        Settings.VIDEO_NUM_IMAGES = 240;
        Settings.IMAGE_PADDING = 0;
        Settings.INFO_FOR_4_IMAGES = true;
    }

    public static void generateRandomWithPatternSettings() {
        Settings.SHOW_GRID = false;
        Settings.MAX_MOVES = 50_000_000;
        Settings.SIZE_OF_SQUARE = 1;
        Settings.VIDEO_NUM_IMAGES = 180;
        Settings.IMAGE_PADDING = 0;
    }

    public static void generateDifferentRuleSamePatternSettings() {
        Settings.SHOW_GRID = false;
        Settings.MAX_MOVES = 50_000_000;
        Settings.SIZE_OF_SQUARE = 1;
        Settings.VIDEO_NUM_IMAGES = 180;
        Settings.IMAGE_PADDING = 0;
        Settings.INFO_FOR_4_IMAGES = true;
    }
}
