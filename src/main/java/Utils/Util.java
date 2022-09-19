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
}
