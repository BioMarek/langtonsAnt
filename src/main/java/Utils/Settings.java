package Utils;

public class Settings {
    // General
    public static int ALPHA = 200; // alpha of colors used in AntGraphic
    public static boolean SHOW_GRID = false;
    public static String RULE = "RL";
    public static int DELAY = 100; // realtime animation delay between frames
    public static int SKIP = 100; // steps to skip between frames
    public static long MAX_MOVES = 50_000_000;
    public static int SIZE_IN_PIXELS = 1080; // size of ant board
    public static int SIZE_OF_SQUARE = 1; // size of square on ant board
    public static int LEGEND_START_X = 1500;
    public static int GRID_WIDTH = 1500;
    public static int GRID_HEIGHT = 1080;
    public static int BACKGROUND_WIDTH = 1920;
    public static int BACKGROUND_HEIGHT = 1080;


    // Settings for image generation
    public static int RULES_LENGTH = 10;
    public static String IMAGE_BASE_PATH = "/media/marek/Data/AntImages";
    public static int THREADS = 6;

    // Settings for gif and mp4 generation
    public static int GIF_DELAY = 35; // 35ms delay for video
    public static int VIDEO_FPS = 30; // frames per second for mp4
    public static int VIDEO_NUM_IMAGES = 180; // number of images in video, we expect 30 images per second, 240 image, 180 highway
    public static int GIF_WIDTH = 1413; // determine size when 4 images
    public static int GIF_HEIGHT = 1080;
    public static String VIDEO_BASE_PATH = "./gifs/";
    public static boolean INFO_FOR_4_IMAGES = false; // image with only rule info, used when there is 4 images on screen TODO doesn't work currently

    // use to show only center of image and fill entire screen with even if some part already reached the border,
    public static int IMAGE_PADDING = 0; // use 500 for thumbnail image
    public static int SLOWDOWN_STEPS = 100_000_000;
    public static double SLOWDOWN_MODIFIER = 1.0;
    public static boolean EXPLANATION_ANIMATION = false; // true only when we are doing explanation animation, (more frames for animation)
    public static int FRAMES_BETWEEN_STEPS = 30; // for explanation animation frames
    public static int VIDEO_REPEAT_LAST_FRAME = 30; // how many times the last image should be repeated in video, 30 is one second
    public static boolean ZOOMED = false; // whether graphic has been zoomed in
    public static int ZOOM_STEPS = 8; // number of ant steps after which there will be zoom
    // TODO better explanation
    public static double GRAPHIC_SHIFT_COLUMN = 0; // during zoom how much is graphic column moved with respect to grid column
    public static double GRAPHIC_SHIFT_ROW = 0; // during zoom how much is graphic row moved with respect to grid row

    public static void showExplanationSettings() {
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
        Settings.VIDEO_NUM_IMAGES = 240;
        Settings.IMAGE_PADDING = 200;
    }

    public static void generateRandomWithPatternSettings() {
        Settings.VIDEO_NUM_IMAGES = 180;
    }

    public static void generateDifferentRuleSamePatternSettings() {
        Settings.VIDEO_NUM_IMAGES = 180;
        Settings.INFO_FOR_4_IMAGES = true;
    }

    public static void generateTestSettings() {
        Settings.VIDEO_NUM_IMAGES = 100;
        Settings.IMAGE_PADDING = 600;
    }

    public static void fourImagesPerScreenSettings() {
        Settings.VIDEO_NUM_IMAGES = 100; // 240 after testing
        Settings.GRID_HEIGHT = 540;
        Settings.GRID_WIDTH = 960;
        Settings.IMAGE_PADDING = 100;
    }
}
