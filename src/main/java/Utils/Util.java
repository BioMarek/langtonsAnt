package Utils;

import java.util.ArrayList;
import java.util.List;

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

    public static List<String> getInteresting() {
        List<String> interesting = new ArrayList<>();
//        interesting.add("0_RRLLLRLLLLLLLLL");
        interesting.add("1_LLLLLRLLLRRLLLLL");
        interesting.add("1_LLLRLLRRRLLLLL");
        interesting.add("1_LLRLRRLLRLLLLLL");
        interesting.add("1_LLRLRRLRLRRRRRRL");
        interesting.add("1_LRLRRLLLLLLLRL");
        interesting.add("1_LRRLRLLLLLRRLL");
        interesting.add("1_LRRRLLLLLLRRLL");
        interesting.add("1_LRRRRRRRRLLRRLRL");
        interesting.add("1_RLLLLLRRRLLLLRRL");
        interesting.add("1_RLLLLRRRLLL");
        interesting.add("1_RRLLLLRLRLRRLLL");
        interesting.add("1_RRLRLRRRRRLRRLL");
        interesting.add("1_RRRRRRRRLLLLLLLL");
        interesting.add("2_LLLLRRRRRLLRRRRL");
        interesting.add("2_LLLRRRRRRLLL");
        interesting.add("2_LLRLLLRRRRRRRLLL");
        interesting.add("2_LLRLRLRRRRRRL");
        interesting.add("2_LLRLRRLLLLLRLRL");
        interesting.add("2_LRLRLLRLLLRRLLLL");
        interesting.add("2_LRRLLLRLRLRRLLL");
        interesting.add("2_LRRRRLLLRLRRRRL");
        interesting.add("2_LRRRRLRRLLRRRRRL");
        interesting.add("2_RLLLLLLLRRL");
        interesting.add("2_RRLLLLRLLLLRRLLL");
        interesting.add("2_RRLLLLRRLRLLLLLL");
        interesting.add("2_RRLLLLRRLRLRLLL");
        interesting.add("2_RRLLRLLLLLLLRRL");
        interesting.add("2_RRLRLLLLRLLLLLLL");
        interesting.add("2_RRLRLLLRRRRRLL");
        interesting.add("2_RRRLRRLLRLRRRLL");
        interesting.add("3_LLRLLLRRRRRLLRLL");
        interesting.add("3_LLRRLLRRRRLLRRLL");
        interesting.add("3_LRRLLLRLRRRRRRL");
        interesting.add("3_LRRLLRRLLLRRRLLL");
        interesting.add("3_RRRLRRRRLLRRRRLL");

        return interesting;
    }
}
