package Utils;

public class Settings {
    /**
     * General settings
     */
    public static int ALPHA = 200; // alpha of colors used in AntGraphic
    public static boolean SHOW_GRID = false;
    public static String RULE = "RL";
    public static int TIMER_DELAY = 100; // realtime animation delay between frames
    public static int SKIP = 100; // steps to skip between frames
    public static long MAX_MOVES = 50_000_000;
    public static int SIZE_OF_SQUARE = 1; // size of square on ant board
    public static int LEGEND_START_X = 1500;
    public static int GRID_WIDTH = 1500;
    public static int GRID_HEIGHT = 1080;
    public static int BACKGROUND_WIDTH = 1920;
    public static int BACKGROUND_HEIGHT = 1080;

    /**
     * Settings for parallel threads image generation
     */
    public static int RULES_LENGTH = 10;
    public static String IMAGE_BASE_PATH = "/media/marek/Data/AntImages";
    public static int THREADS = 6;

    /**
     * Settings for gif and mp4 generation
     */
    public static int VIDEO_FPS = 30; // frames per second for mp4
    public static int VIDEO_NUM_IMAGES = 180; // number of images in video, we expect 30 images per second, 240 image, 180 highway
    public static String VIDEO_BASE_PATH = "./movies/";
    public static int VIDEO_REPEAT_LAST_FRAME = 30; // how many times the last image should be repeated in video, 30 is one second

    // use to show only center of image and fill entire screen with even if some part already reached the border,
    public static int IMAGE_PADDING = 0; // use 500 for thumbnail image
    public static int SLOWDOWN_STEPS = 100_000_000;
    public static double SLOWDOWN_MODIFIER = 1.0;

    /**
     * Settings for explanation
     */
    public static boolean EXPLANATION_ANIMATION = false; // true only when we are doing explanation animation, (more frames for animation)
    public static int FRAMES_BETWEEN_STEPS = 30; // for explanation animation frames
    public static boolean ZOOMED = false; // whether graphic has been zoomed in
    public static int ZOOM_STEPS = 8; // number of ant steps after which there will be zoom
    public static double GRAPHIC_SHIFT_COLUMN = 0; // during zoom how much is graphic column moved with respect to grid column
    public static double GRAPHIC_SHIFT_ROW = 0; // during zoom how much is graphic row moved with respect to grid row

    public static void showExplanationFirstPartSettings() {
        Settings.SHOW_GRID = true;
        Settings.SKIP = 1;
        Settings.MAX_MOVES = 100;
        Settings.SIZE_OF_SQUARE = 80;
        Settings.EXPLANATION_ANIMATION = true;
        Settings.IMAGE_PADDING = 0;
        Settings.VIDEO_REPEAT_LAST_FRAME = 0;
    }

    public static void showExplanationSecondPartSettings() {
        Settings.SHOW_GRID = true;
        Settings.SKIP = 50;
        Settings.MAX_MOVES = 20000;
        Settings.SIZE_OF_SQUARE = 10;
        Settings.ZOOMED = true;
        Settings.IMAGE_PADDING = 0;
        Settings.VIDEO_REPEAT_LAST_FRAME = 30;
    }

    public static void generateHighwaysSettings() {
        Settings.VIDEO_NUM_IMAGES = 180;
    }

    public static void generateInterestingSettings() {
        Settings.VIDEO_NUM_IMAGES = 255;
        Settings.IMAGE_PADDING = 500;
    }

    public static void generateRandomWithPatternSettings() {
        Settings.VIDEO_NUM_IMAGES = 180;
    }

    public static void generateTestSettings() {
        Settings.VIDEO_NUM_IMAGES = 100;
        Settings.IMAGE_PADDING = 600;
    }

    public static void generateFourImagesPerScreenSettings() {
        Settings.VIDEO_NUM_IMAGES = 240;
        Settings.GRID_HEIGHT = 540;
        Settings.GRID_WIDTH = 960;
        Settings.IMAGE_PADDING = 200;
    }
}
