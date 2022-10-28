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

    public static int sizeDivisibleByTwo(int originalSize){
        return (originalSize % 2 == 0) ? originalSize : originalSize + 1;
    }

    public static List<Rule> getInteresting() {
        List<Rule> interesting = new ArrayList<>();
        interesting.add(new Rule("RRLLLRLLLLLLLLL", 0, 1));
        interesting.add(new Rule("LLLLLRLLLRRLLLLL", 0, 1));
        interesting.add(new Rule("LLLRLLRRRLLLLL", 0, 1));
        interesting.add(new Rule("LLRLRRLLRLLLLLL", 0, 1));
        interesting.add(new Rule("LLRLRRLRLRRRRRRL", 0, 1));
        interesting.add(new Rule("LRLRRLLLLLLLRL", 0, 1));
        interesting.add(new Rule("LRRLRLLLLLRRLL", 0, 1));
        interesting.add(new Rule("LRRRLLLLLLRRLL", 0, 1));
        interesting.add(new Rule("LRRRRRRRRLLRRLRL", 0, 1));
        interesting.add(new Rule("RLLLLLRRRLLLLRRL", 0, 1));
        interesting.add(new Rule("RLLLLRRRLLL", 0, 1));
        interesting.add(new Rule("RRLLLLRLRLRRLLL", 0, 1));
//        interesting.add(new Rule("RRLRLRRRRRLRRLL", 38_000_000, 0.1)); // column moved by 250
//        interesting.add(new Rule("RRRRRRRRLLLLLLLL", 0, 1)); // 15 frames per sec, 150 images, 2 pixel size
        interesting.add(new Rule("LLLLRRRRRLLRRRRL", 0, 1));
        interesting.add(new Rule("LLLRRRRRRLLL", 0, 1));
        interesting.add(new Rule("LLRLLLRRRRRRRLLL", 0, 1));
        interesting.add(new Rule("LLRLRLRRRRRRL", 0, 1));
        interesting.add(new Rule("LLRLRRLLLLLRLRL", 0, 1));
        interesting.add(new Rule("LRLRLLRLLLRRLLLL", 0, 1));
//        interesting.add(new Rule("LRRLLLRLRLRRLLL", 0, 1)); // 300 frames
        interesting.add(new Rule("LRRRRLLLRLRRRRL", 0, 1));
//        interesting.add(new Rule("LRRRRLRRLLRRRRRL", 0, 1)); // IMAGE_PADDING 100
        interesting.add(new Rule("RLLLLLLLRRL", 0, 1));
        interesting.add(new Rule("RRLLLLRLLLLRRLLL", 0, 1));
        interesting.add(new Rule("RRLLLLRRLRLLLLLL", 0, 1));
        interesting.add(new Rule("RRLLLLRRLRLRLLL", 0, 1));
        interesting.add(new Rule("RRLLRLLLLLLLRRL", 0, 1));
        interesting.add(new Rule("RRLRLLLLRLLLLLLL", 0, 1));
        interesting.add(new Rule("RRLRLLLRRRRRLL", 0, 1));
        interesting.add(new Rule("RRRLRRLLRLRRRLL", 0, 1));
//        interesting.add(new Rule("LLRLLLRRRRRLLRLL", 0, 1)); // IMAGE_PADDING 500
        interesting.add(new Rule("LLRRLLRRRRLLRRLL", 0, 1));
        interesting.add(new Rule("LRRLLLRLRRRRRRL", 0, 1));
        interesting.add(new Rule("LRRLLRRLLLRRRLLL", 0, 1));
        interesting.add(new Rule("RRRLRRRRLLRRRRLL", 0, 1));

        return interesting;
    }

    public static List<Rule> getHighways() {
        List<Rule> interesting = new ArrayList<>();
        interesting.add(new Rule("LLRLRLLLLRLRLRRL", 1_500_000, 0.1));
        interesting.add(new Rule("LRRRRRLRRRRLLLL", 480_000, 0.5));
        interesting.add(new Rule("LLLLLLLRRLLRLLL", 0, 1));
        interesting.add(new Rule("LLLRLLRLRRRLLLLL", 0, 1));
        interesting.add(new Rule("LLRLRLLLRLLLLL", 6_500_000, 0.1));
        interesting.add(new Rule("LLRLRRLRLRRLLLLL", 5_500_000, 0.3));
        interesting.add(new Rule("LLRRRLLLRRRLLLLL", 3_350_000, 0.3));
        interesting.add(new Rule("LLRRRLRRRLLLLRRL", 0, 1));
        interesting.add(new Rule("LLRRRLRRRLRLLLL", 0, 1));
        interesting.add(new Rule("RRLRLLLLRLLRLLL", 2_350_000, 0.4));
        interesting.add(new Rule("RRLRLLLRLLRRRRLL", 950_000, 0.5));
        interesting.add(new Rule("LLLLRLLLRRLRLLL", 16_000_000, 0.1));
        interesting.add(new Rule("RRLLLRRLRRRRLLLL", 960_000, 0.2));
        interesting.add(new Rule("RRLRLLRRRRLL", 0, 1));

        return interesting;
    }

    public static List<Rule> getFour() {
        List<Rule> interesting = new ArrayList<>();
        interesting.add(new Rule("LRRLLLRRRLLLL", 0, 1));
        interesting.add(new Rule("LRRLLLRRRLLLLL", 0, 1));
        interesting.add(new Rule("LRRLLLRRRLLLLLL", 0, 1));
        interesting.add(new Rule("LRRLLLRRRLLLLLLL", 0, 1));

        interesting.add(new Rule("LRRRRRLRRRRLLL", 0, 1));
        interesting.add(new Rule("LRRRRRLRRRRLLLL", 0, 1));
        interesting.add(new Rule("LRRRRRLRRRRLLLLL", 0, 1)); //
        interesting.add(new Rule("LRRRRRLRRRRLLLLLL", 0, 1)); //

        interesting.add(new Rule("RRLLLRLLLRRRLLL", 0, 1));
        interesting.add(new Rule("RRLLLRLLLRRRLRL", 0, 1));
        interesting.add(new Rule("RRLLLRLLLRRRRL", 0, 1));
        interesting.add(new Rule("RRLLLRLLLRRRRLL", 0, 1));

        interesting.add(new Rule("RRLLLRRRLLLLLLL", 0, 1));
        interesting.add(new Rule("RRLLLRRRLLLLLLLL", 0, 1));
        interesting.add(new Rule("RRLLRLLLLLLL", 0, 1));
        interesting.add(new Rule("RRLLRLLLLLLLL", 0, 1));
        interesting.add(new Rule("RRLLRLLLLLLLLL", 0, 1));
        interesting.add(new Rule("RRLLRLLLLLLLLLL", 0, 1));

        return interesting;
    }

    public static Position explanationAnimationPositions(int i){
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(460, 460, Direction.NORTH));
        positions.add(new Position(500, 460, Direction.EAST));
        positions.add(new Position(500, 500, Direction.SOUTH));
        positions.add(new Position(460, 500, Direction.WEST));

        return (i < positions.size()) ? positions.get(i) : new Position(460, 460);
    }
}
