package Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Langton's ant rule and metadata. For highways we want to slow down animation when highway starts growing. Number of
 * steps when to slow down is slowdownSteps. How much to slow down is defined in slowdownModifier 1 is no slow down,
 * more than 1 is speed up.
 */
public class Rule {
    public String rule;
    public int slowdownSteps;
    public double slowdownModifier;
    public int sizeOfSquare;

    public Rule(String rule, int slowdownSteps, double slowdownModifier, int sizeOfSquare) {
        this.rule = rule;
        this.slowdownSteps = slowdownSteps;
        this.slowdownModifier = slowdownModifier;
        this.sizeOfSquare = sizeOfSquare;
    }

    public void setVariables() {
        Settings.RULE = rule;
        Settings.SLOWDOWN_STEPS = slowdownSteps;
        Settings.SLOWDOWN_MODIFIER = slowdownModifier;
        Settings.SIZE_OF_SQUARE = sizeOfSquare;
    }

    public static List<Rule> getInteresting() {
        Settings.generateInterestingSettings();
        List<Rule> interesting = new ArrayList<>();
        interesting.add(new Rule("RRLLLRLLLLLLLLL", 0, 1, 1));  // IMAGE_PADDING 600
        interesting.add(new Rule("LLLLLRLLLRRLLLLL", 0, 1, 1));
        interesting.add(new Rule("LLLRLLRRRLLLLL", 0, 1, 1));
        interesting.add(new Rule("LLRLRRLLRLLLLLL", 0, 1, 1));
        interesting.add(new Rule("LLRLRRLRLRRRRRRL", 0, 1, 1));
        interesting.add(new Rule("LRLRRLLLLLLLRL", 0, 1, 1));
        interesting.add(new Rule("LRRLRLLLLLRRLL", 0, 1, 1));
        interesting.add(new Rule("LRRRLLLLLLRRLL", 0, 1, 1));
        interesting.add(new Rule("LRRRRRRRRLLRRLRL", 0, 1, 1));
        interesting.add(new Rule("RLLLLLRRRLLLLRRL", 0, 1, 1));
        interesting.add(new Rule("RLLLLRRRLLL", 0, 1, 1));
        interesting.add(new Rule("RRLLLLRLRLRRLLL", 0, 1, 1));
        interesting.add(new Rule("RRLRLRRRRRLRRLL", 38_000_000, 0.2, 1)); // column moved by +300 rows by + 150 padding 1000
        interesting.add(new Rule("RRRRRRRRLLLLLLLL", 0, 1, 4)); // 10 frames per sec, 150 images
        interesting.add(new Rule("LLLLRRRRRLLRRRRL", 0, 1, 1));
        interesting.add(new Rule("LLLRRRRRRLLL", 0, 1, 1));
        interesting.add(new Rule("LLRLLLRRRRRRRLLL", 0, 1, 1));
        interesting.add(new Rule("LLRLRLRRRRRRL", 0, 1, 1));
        interesting.add(new Rule("LLRLRRLLLLLRLRL", 0, 1, 1));
        interesting.add(new Rule("LRLRLLRLLLRRLLLL", 0, 1, 1));
        interesting.add(new Rule("LRRLLLRLRLRRLLL", 0, 1, 1)); // 300 frames
        interesting.add(new Rule("LRRRRLLLRLRRRRL", 0, 1, 1));
        interesting.add(new Rule("LRRRRLRRLLRRRRRL", 0, 1, 1));
        interesting.add(new Rule("RLLLLLLLRRL", 0, 1, 1));
        interesting.add(new Rule("RRLLLLRLLLLRRLLL", 0, 1, 1));
        interesting.add(new Rule("RRLLLLRRLRLLLLLL", 0, 1, 1));
        interesting.add(new Rule("RRLLLLRRLRLRLLL", 0, 1, 1));
        interesting.add(new Rule("RRLLRLLLLLLLRRL", 0, 1, 1));
        interesting.add(new Rule("RRLRLLLLRLLLLLLL", 0, 1, 1));
        interesting.add(new Rule("RRLRLLLRRRRRLL", 0, 1, 1));
        interesting.add(new Rule("RRRLRRLLRLRRRLL", 0, 1, 1));
        interesting.add(new Rule("LLRLLLRRRRRLLRLL", 0, 1, 1)); // IMAGE_PADDING 600
        interesting.add(new Rule("LLRRLLRRRRLLRRLL", 0, 1, 1));
        interesting.add(new Rule("LRRLLLRLRRRRRRL", 0, 1, 1));
        interesting.add(new Rule("LRRLLRRLLLRRRLLL", 0, 1, 1));
        interesting.add(new Rule("RRRLRRRRLLRRRRLL", 0, 1, 1));
        interesting.add(new Rule("RLRRRL", 0, 1, 2));

        interesting.add(new Rule("LRRLLLLRLLRLL", 0, 1, 1));
        interesting.add(new Rule("LRRRLLLRLLLLLLL", 0, 1, 1));
        interesting.add(new Rule("RLLLLLLLLLRRL", 0, 1, 1));
        interesting.add(new Rule("RLLLRRRLLLL", 0, 1, 1));
        interesting.add(new Rule("RRLLLLRRRLLLLLLL", 0, 1, 1)); // IMAGE_PADDING 500
        interesting.add(new Rule("RRRRLRRRLLRRRLL", 0, 1, 1)); // IMAGE_PADDING 500
        interesting.add(new Rule("RLLLRLRRRRRLRL", 0, 1, 1));

        return interesting;
    }

    public static List<Rule> getHighways() {
        Settings.generateHighwaysSettings();
        List<Rule> interesting = new ArrayList<>();
        interesting.add(new Rule("LRRRRRLRRRRLLLL", 480_000, 0.5, 2));
        interesting.add(new Rule("LLRLRLLLLRLRLRRL", 1_500_000, 0.1, 1));
        interesting.add(new Rule("LLLLLLLRRLLRLLL", 0, 1, 2));
        interesting.add(new Rule("LLLRLLRLRRRLLLLL", 0, 1, 2));
        interesting.add(new Rule("LLRLRLLLRLLLLL", 6_500_000, 0.1, 1));
        interesting.add(new Rule("LLRLRRLRLRRLLLLL", 5_500_000, 0.3, 1));
        interesting.add(new Rule("LLRRRLLLRRRLLLLL", 3_350_000, 0.3, 1));
        interesting.add(new Rule("LLRRRLRRRLLLLRRL", 0, 1, 2));
        interesting.add(new Rule("LLRRRLRRRLRLLLL", 0, 1, 2));
        interesting.add(new Rule("RRLRLLLLRLLRLLL", 2_350_000, 0.4, 1));
        interesting.add(new Rule("RRLRLLLRLLRRRRLL", 950_000, 0.5, 1));
        interesting.add(new Rule("LLLLRLLLRRLRLLL", 16_000_000, 0.1, 1));
        interesting.add(new Rule("RRLLLRRLRRRRLLLL", 960_000, 0.2, 1));
        interesting.add(new Rule("RRLRLLRRRRLL", 0, 1, 1));

        interesting.add(new Rule("RRLLLRRRRRLRLLL", 0, 1, 1));
        interesting.add(new Rule("LLLLRRLRLLLL", 0, 1, 1));
        interesting.add(new Rule("LLRLRRRLRRLLLL", 20_000_000, 0.3, 1));

        return interesting;
    }

    public static Set<List<Rule>> getFour() {
        Settings.generateFourImagesPerScreenSettings();
        Set<List<Rule>> interesting = new HashSet<>();

        // only first rule variables are set
        List<Rule> first = new ArrayList<>();
        first.add(new Rule("LRRLLLRRRLLLL", 0, 1, 1));
        first.add(new Rule("LRRLLLRRRLLLLL", 0, 1, 1));
        first.add(new Rule("LRRLLLRRRLLLLLL", 0, 1, 1));
        first.add(new Rule("LRRLLLRRRLLLLLLL", 0, 1, 1));
        interesting.add(first);

        List<Rule> second = new ArrayList<>();
        second.add(new Rule("LRRRRRLRRRRLLL", 0, 1, 1));
        second.add(new Rule("LRRRRRLRRRRLLLL", 0, 1, 1));
        second.add(new Rule("LRRRRRLRRRRLLLLL", 0, 1, 1));
        second.add(new Rule("LRRRRRLRRRRLLLLLL", 0, 1, 1));
        interesting.add(second);

        List<Rule> third = new ArrayList<>();
        third.add(new Rule("RRLLLRLLLRRRLLL", 0, 1, 1));
        third.add(new Rule("RRLLLRLLLRRRLRL", 0, 1, 1));
        third.add(new Rule("RRLLLRLLLRRRRL", 0, 1, 1));
        third.add(new Rule("RRLLLRLLLRRRRLL", 0, 1, 1));
        interesting.add(third);

        List<Rule> fourth = new ArrayList<>();
        fourth.add(new Rule("RRLLLRRRLLLLLLL", 0, 1, 1));
        fourth.add(new Rule("RRLLLRRRLLLLLLLL", 0, 1, 1));
        fourth.add(new Rule("RRLLLRRRLLLLLLLLL", 0, 1, 1));
        fourth.add(new Rule("RRLLLRRRLLLLLLLLLL", 0, 1, 1));
        interesting.add(fourth);

        List<Rule> fifth = new ArrayList<>();
        fifth.add(new Rule("RRLLRLLLLLLL", 0, 1, 1));
        fifth.add(new Rule("RRLLRLLLLLLLL", 0, 1, 1));
        fifth.add(new Rule("RRLLRLLLLLLLLL", 0, 1, 1));
        fifth.add(new Rule("RRLLRLLLLLLLLLL", 0, 1, 1));
        interesting.add(fifth);

        // Symmetric rules
        List<Rule> sixth = new ArrayList<>();
        sixth.add(new Rule("RRLLLLLRLLLLLLLL", 0, 1, 1));
        sixth.add(new Rule("LLRRRRRLRRRRRRRR", 0, 1, 1));
        sixth.add(new Rule("RRLRLLRRRRRLRL", 0, 1, 2));
        sixth.add(new Rule("LLRLRRLLLLLRLR", 0, 1, 2));
        interesting.add(sixth);

        List<Rule> seventh = new ArrayList<>();
        seventh.add(new Rule("RLLRRRLLLRRLRRRL", 0, 1, 1));
        seventh.add(new Rule("LRRLLLRRRLLRLLLR", 0, 1, 1));
        seventh.add(new Rule("LRRRRLLLRLRRRRL", 0, 1, 1));
        seventh.add(new Rule("RLLLLRRRLRLLLLR", 0, 1, 1));
        interesting.add(seventh);

        // Different rule same image
        List<Rule> eighth = new ArrayList<>();
        eighth.add(new Rule("LLRLLRRRRRRRRR", 0, 1, 5));
        eighth.add(new Rule("LLRLLRRRRRRLLL", 0, 1, 5));
        eighth.add(new Rule("LLRLLRRRRLLLLL", 0, 1, 5));
        eighth.add(new Rule("LLRLLRLLLLLLLL", 0, 1, 5));
        interesting.add(eighth);

        // Random noise, padding 0
        List<Rule> nineth = new ArrayList<>();
        nineth.add(new Rule("LLLRLL", 0, 1, 2));
        nineth.add(new Rule("LRLLRL", 0, 1, 2));
        nineth.add(new Rule("RLLRRL", 0, 1, 2));
        nineth.add(new Rule("RLRLLL", 0, 1, 2));
        interesting.add(nineth);

        // Highways
        List<Rule> tenth = new ArrayList<>();
        tenth.add(new Rule("LLRRRLRLRLLR", 0, 1, 2));
        tenth.add(new Rule("LLRLRRLL", 0, 1, 1));
        tenth.add(new Rule("LRLRRLRLLLRLRLRL", 0, 1, 1));
        tenth.add(new Rule("LLLRRRL", 0, 1, 1));
        interesting.add(tenth);

        return interesting;
    }

    public static List<Rule> getRandomWithPatternRules() {
        Settings.generateRandomWithPatternSettings();
        List<Rule> interesting = new ArrayList<>();
        interesting.add(new Rule("RRLLLRLRRLRRLRLL", 0, 1, 1));
        interesting.add(new Rule("RLRLRRRRRRRRRL", 0, 1, 1));
        interesting.add(new Rule("LRRRRLLLRLLLLLLL", 0, 1, 1));
        interesting.add(new Rule("LRRLRLLLLL", 0, 1, 1));
        interesting.add(new Rule("LRRLLRRLLLRLLLL", 0, 1, 1));
        interesting.add(new Rule("LRRLLLLRLLRRLLLL", 0, 1, 1));
        interesting.add(new Rule("LRLLLRRRRRLRLL", 0, 1, 1));
        interesting.add(new Rule("LLRLRRRLRRRRRRLL", 0, 1, 1));
        interesting.add(new Rule("LLLRLLRRLLLLLRRL", 0, 1, 1));

        return interesting;
    }

    public static List<Rule> getHighWayScreenFillRules() {
        List<Rule> interesting = new ArrayList<>();
        interesting.add(new Rule("RRL", 0, 1, 10)); // add grid
        interesting.add(new Rule("RLRRRL", 0, 1, 1));
        interesting.add(new Rule("RRLLLRLLLLLLLLL", 0, 1, 1)); // padding 500

        return interesting;
    }

    public static List<Rule> getDifferentTestRules() {
        Settings.generateTestSettings();
        List<Rule> interesting = new ArrayList<>();
        interesting.add(new Rule("LRRRRRLRRRRLLLL", 480_000, 0.5, 2));
        interesting.add(new Rule("RRLLLRLLLLLLLLL", 0, 1, 1));  // IMAGE_PADDING 600
        interesting.add(new Rule("RLL", 0, 1, 1));

        return interesting;
    }
}
