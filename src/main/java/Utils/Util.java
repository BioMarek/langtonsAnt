package Utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.util.Arrays;
import java.util.Random;

/**
 * Helper functions used across the project.
 */
public class Util {
    private static final Random random = new Random();

    /**
     * Prints grid, used for debugging purposes.
     */
    public static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int column = 0; column < grid.length; column++) {
                System.out.print(row[column] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Formats number as a String with space every three numbers.
     *
     * @param num number to format
     * @return num in as string with spaces
     */
    public static String numberFormatter(long num) {
        String numString = String.valueOf(num);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numString.length(); i++) {
            if (i % 3 == 0)
                result.append(' ');
            result.append(numString.charAt(numString.length() - i - 1));
        }
        return result.reverse().toString().trim();
    }

    /**
     * Ensures that size is divisible by two by increasing odd sizes by one. Required for mpr4 encoding
     *
     * @param originalSize size of image in pixels
     * @return either originalSize or originalSize + 1 whichever is even
     */
    public static int sizeDivisibleByTwo(int originalSize) {
        return (originalSize % 2 == 0) ? originalSize : originalSize - 1;
    }

    /**
     * Formats number representing milliseconds into human-readable format.
     *
     * @param durationInMillis number of milliseconds
     * @return formatted time as String
     */
    public static String formatTime(long durationInMillis) {
        long millis = durationInMillis % 1000;
        long second = (durationInMillis / 1000) % 60;
        long minute = (durationInMillis / (1000 * 60)) % 60;
        long hour = (durationInMillis / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d.%d", hour, minute, second, millis);
    }

    /**
     * @return width of hexagon in pixels if its side has length Settings.HEX_SIDE_LEN
     */
    public static int getHexWidth() {
        return (int) (Settings.HEX_SIDE_LEN * 1.7); // 17 when hexagon is 10
    }

    /**
     * @return height of hexagon in pixels if its side has length Settings.HEX_SIDE_LEN
     */
    public static int getHexHeight() {
        return (int) (Settings.HEX_SIDE_LEN * 1.5); // 15 when hexagon is 10
    }

    /**
     * Hexagons on the odd row are moved by half of width hexagon to fit in gaps created by hexagons on even line.
     * .......*       *
     * .....*   *   *   *
     * ...*       *       *
     * ...*       *       *
     * ...| *   *   *   *   *
     * ...|   *       *       *
     * ...|   *       *       *
     * ...|     *   *   *   *
     * ...|       *       *
     * ...|       |
     * ...|<----->|
     * ...Length of shift
     *
     * @return how much to shift starting position of hexagon on the odd line
     */
    public static int getHexPositionShift() {
        return getHexWidth() / 2; // 15 when hexagon is 10
    }

    /**
     * Calculates standard deviation of array.
     */
    public static double calculateStandardDeviation(double[] array) {
        double sum = 0.0;
        for (double i : array) {
            sum += i;
        }

        int length = array.length;
        double mean = sum / length;

        double standardDeviation = 0.0;
        for (double num : array) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation / length);
    }

    /**
     * Normalize scale of number in array, all numbers will be in interval [0, 1]
     *
     * @param array to normalize
     * @return normalized array
     */
    public static double[] normalize(int[] array) {
        double[] result = new double[array.length];
        double max = Arrays.stream(array).max().getAsInt();
        for (int i = 0; i < array.length; i++)
            result[i] = array[i] / max;

        return result;
    }

    /**
     * Generates {@link Polygon} composed of {@link java.awt.Point}s of hexagon
     *
     * @param x        x-axis of hexagon first point
     * @param y        y-axis of hexagon first point
     * @param edgeSize length of hexagon edge
     * @return polygon with hexagon points
     */
    public static Polygon hexagonalPolygon(int x, int y, int edgeSize) {
        Polygon polygon = new Polygon();
        for (int i = 0; i < 6; i++)
            polygon.addPoint((int) (x + edgeSize * Math.sin(i * 2 * Math.PI / 6)),
                    (int) (y + edgeSize * Math.cos(i * 2 * Math.PI / 6)));
        return polygon;
    }

    /**
     * Switches anti-aliasing on or off.
     *
     * @param isOn whether AA should be turned on
     */
    public static void switchAntiAliasing(Graphics2D graphics, boolean isOn) {
        if (isOn) {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        } else {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        }
    }

    public static Color randomColor() {
        return new Color(random.nextInt(0, 255), random.nextInt(0, 255), random.nextInt(0, 255));
    }
}
