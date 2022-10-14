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
//        interesting.add("RRLLLRLLLLLLLLL");
        interesting.add("LLLLLRLLLRRLLLLL");
        interesting.add("LLLRLLRRRLLLLL");
        interesting.add("LLRLRRLLRLLLLLL");
        interesting.add("LLRLRRLRLRRRRRRL");
        interesting.add("LRLRRLLLLLLLRL");
        interesting.add("LRRLRLLLLLRRLL");
        interesting.add("LRRRLLLLLLRRLL");
        interesting.add("LRRRRRRRRLLRRLRL");
        interesting.add("RLLLLLRRRLLLLRRL");
        interesting.add("RLLLLRRRLLL");
        interesting.add("RRLLLLRLRLRRLLL");
        interesting.add("RRLRLRRRRRLRRLL");
        interesting.add("RRRRRRRRLLLLLLLL");
        interesting.add("LLLLRRRRRLLRRRRL");
        interesting.add("LLLRRRRRRLLL");
        interesting.add("LLRLLLRRRRRRRLLL");
        interesting.add("LLRLRLRRRRRRL");
        interesting.add("LLRLRRLLLLLRLRL");
        interesting.add("LRLRLLRLLLRRLLLL");
        interesting.add("LRRLLLRLRLRRLLL");
        interesting.add("LRRRRLLLRLRRRRL");
        interesting.add("LRRRRLRRLLRRRRRL");
        interesting.add("RLLLLLLLRRL");
        interesting.add("RRLLLLRLLLLRRLLL");
        interesting.add("RRLLLLRRLRLLLLLL");
        interesting.add("RRLLLLRRLRLRLLL");
        interesting.add("RRLLRLLLLLLLRRL");
        interesting.add("RRLRLLLLRLLLLLLL");
        interesting.add("RRLRLLLRRRRRLL");
        interesting.add("RRRLRRLLRLRRRLL");
        interesting.add("LLRLLLRRRRRLLRLL");
        interesting.add("LLRRLLRRRRLLRRLL");
        interesting.add("LRRLLLRLRRRRRRL");
        interesting.add("LRRLLRRLLLRRRLLL");
        interesting.add("RRRLRRRRLLRRRRLL");

        return interesting;
    }
}
