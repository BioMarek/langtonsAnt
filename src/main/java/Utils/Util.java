package Utils;

public class Util {

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
     * Formats number as string with space every three numbers.
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
     * @param originalSize size of image in pixels
     * @return either originalSize or originalSize + 1 whichever is even
     */
    public static int sizeDivisibleByTwo(int originalSize) {
        return (originalSize % 2 == 0) ? originalSize : originalSize - 1;
    }
}
