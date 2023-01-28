package Logic.Rule;

import Utils.Settings;

import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Langton's ant rule and metadata. For highways we want to slow down animation when highway starts growing. Number of
 * steps when to slow down is slowdownSteps. How much to slow down is defined in slowdownModifier 1 is no slow down,
 * more than 1 is speed up.
 */
public class SquareRule extends Rule {
    public String rule;

    @Override
    public int getSize() {
        return rule.length();
    }

    @Override
    public String getElement(int position) {
        return String.valueOf(rule.charAt(position));
    }

    @Override
    public String getType() {
        return "square";
    }

    @Override
    public String getSquareRule() {
        return rule;
    }

    public AttributedString getAttributeString(int fontUnit){
        AttributedString attributedString = new AttributedString(rule);
        attributedString.addAttribute(TextAttribute.SIZE, fontUnit * 1.2);
        attributedString.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD, 0, rule.length());
        return attributedString;
    }

    public SquareRule(String rule, int slowdownSteps, double slowdownModifier, int sizeOfSquare) {
        this.rule = rule;
        this.slowdownSteps = slowdownSteps;
        this.slowdownModifier = slowdownModifier;
        this.sizeOfSquare = sizeOfSquare;
    }

    public void setVariables() {
        Settings.SQUARE_RULE = this;
        Settings.SIZE_OF_SQUARE = sizeOfSquare;
    }

    @Override
    public String toString() {
        return rule;
    }

    public static List<SquareRule> getInteresting() {
        Settings.generateInterestingSettings();
        List<SquareRule> interesting = new ArrayList<>();
        interesting.add(new SquareRule("RRLLLRLLLLLLLLL", 0, 1, 1));  // IMAGE_PADDING 600
        interesting.add(new SquareRule("LLLLLRLLLRRLLLLL", 0, 1, 1));
        interesting.add(new SquareRule("LLLRLLRRRLLLLL", 0, 1, 1));
        interesting.add(new SquareRule("LLRLRRLLRLLLLLL", 0, 1, 1));
        interesting.add(new SquareRule("LLRLRRLRLRRRRRRL", 0, 1, 1));
        interesting.add(new SquareRule("LRLRRLLLLLLLRL", 0, 1, 1));
        interesting.add(new SquareRule("LRRLRLLLLLRRLL", 0, 1, 1));
        interesting.add(new SquareRule("LRRRLLLLLLRRLL", 0, 1, 1));
        interesting.add(new SquareRule("LRRRRRRRRLLRRLRL", 0, 1, 1));
        interesting.add(new SquareRule("RLLLLLRRRLLLLRRL", 0, 1, 1));
        interesting.add(new SquareRule("RLLLLRRRLLL", 0, 1, 1));
        interesting.add(new SquareRule("RRLLLLRLRLRRLLL", 0, 1, 1));
        interesting.add(new SquareRule("RRLRLRRRRRLRRLL", 38_000_000, 0.2, 1)); // column moved by +300 rows by + 150 padding 1000
        interesting.add(new SquareRule("RRRRRRRRLLLLLLLL", 0, 1, 4)); // 10 frames per sec, 150 images
        interesting.add(new SquareRule("LLLLRRRRRLLRRRRL", 0, 1, 1));
        interesting.add(new SquareRule("LLLRRRRRRLLL", 0, 1, 1));
        interesting.add(new SquareRule("LLRLLLRRRRRRRLLL", 0, 1, 1));
        interesting.add(new SquareRule("LLRLRLRRRRRRL", 0, 1, 1));
        interesting.add(new SquareRule("LLRLRRLLLLLRLRL", 0, 1, 1));
        interesting.add(new SquareRule("LRLRLLRLLLRRLLLL", 0, 1, 1));
        interesting.add(new SquareRule("LRRLLLRLRLRRLLL", 0, 1, 1)); // 300 frames
        interesting.add(new SquareRule("LRRRRLLLRLRRRRL", 0, 1, 1));
        interesting.add(new SquareRule("LRRRRLRRLLRRRRRL", 0, 1, 1));
        interesting.add(new SquareRule("RLLLLLLLRRL", 0, 1, 1));
        interesting.add(new SquareRule("RRLLLLRLLLLRRLLL", 0, 1, 1));
        interesting.add(new SquareRule("RRLLLLRRLRLLLLLL", 0, 1, 1));
        interesting.add(new SquareRule("RRLLLLRRLRLRLLL", 0, 1, 1));
        interesting.add(new SquareRule("RRLLRLLLLLLLRRL", 0, 1, 1));
        interesting.add(new SquareRule("RRLRLLLLRLLLLLLL", 0, 1, 1));
        interesting.add(new SquareRule("RRLRLLLRRRRRLL", 0, 1, 1));
        interesting.add(new SquareRule("RRRLRRLLRLRRRLL", 0, 1, 1));
        interesting.add(new SquareRule("LLRLLLRRRRRLLRLL", 0, 1, 1)); // IMAGE_PADDING 600
        interesting.add(new SquareRule("LLRRLLRRRRLLRRLL", 0, 1, 1));
        interesting.add(new SquareRule("LRRLLLRLRRRRRRL", 0, 1, 1));
        interesting.add(new SquareRule("LRRLLRRLLLRRRLLL", 0, 1, 1));
        interesting.add(new SquareRule("RRRLRRRRLLRRRRLL", 0, 1, 1));
        interesting.add(new SquareRule("RLRRRL", 0, 1, 2));

        interesting.add(new SquareRule("LRRLLLLRLLRLL", 0, 1, 1));
        interesting.add(new SquareRule("LRRRLLLRLLLLLLL", 0, 1, 1));
        interesting.add(new SquareRule("RLLLLLLLLLRRL", 0, 1, 1));
        interesting.add(new SquareRule("RLLLRRRLLLL", 0, 1, 1));
        interesting.add(new SquareRule("RRLLLLRRRLLLLLLL", 0, 1, 1)); // IMAGE_PADDING 500
        interesting.add(new SquareRule("RRRRLRRRLLRRRLL", 0, 1, 1)); // IMAGE_PADDING 500
        interesting.add(new SquareRule("RLLLRLRRRRRLRL", 0, 1, 1));

        interesting.add(new SquareRule("LLLLLLRLLLLRRLL", 0, 1, 1));
        interesting.add(new SquareRule("LLLLLRRLLLLRLLL", 0, 1, 1));
        interesting.add(new SquareRule("LLLLRLLLRLLLLL", 0, 1, 1));
        interesting.add(new SquareRule("LLLRRRRRRLLLLRRL", 0, 1, 1));
        interesting.add(new SquareRule("LLRLRRLRLLRRLLL", 0, 1, 1));
        interesting.add(new SquareRule("LLRRLLRRRRRLRL", 0, 1, 1));

        interesting.add(new SquareRule("LRRLLLLRRLLRLLLL", 0, 1, 1));

        interesting.add(new SquareRule("LRRLLLLRRRLLLRRL", 0, 1, 1));
        interesting.add(new SquareRule("LRRLLLRRRLLLL", 0, 1, 1));
        interesting.add(new SquareRule("LRRLLLRRRLLLLL", 0, 1, 1));
        interesting.add(new SquareRule("LRRLLLRRRRRRRL", 0, 1, 1));

        interesting.add(new SquareRule("LRRLLRRL", 0, 1, 1));
        interesting.add(new SquareRule("LRRLLRRLLLLRLLLL", 0, 1, 1));
        interesting.add(new SquareRule("LRRLLRRLLLRRRLLL", 0, 1, 1));
        interesting.add(new SquareRule("LRRRLLLLLLLLRRL", 0, 1, 1));
        interesting.add(new SquareRule("LRRRLLLLLLRRLL", 0, 1, 1));
        interesting.add(new SquareRule("LRRRLLLLLLRRLLL", 0, 1, 1));

        interesting.add(new SquareRule("LRRRLLRLRRRRRRRL", 0, 1, 1));
        interesting.add(new SquareRule("LRRRRLLLRRRLRL", 0, 1, 1));
        interesting.add(new SquareRule("LRRRRRLLRRLL", 0, 1, 1));
        interesting.add(new SquareRule("RLLLRLLLLRRLLLLL", 0, 1, 1));

        return interesting;
    }

    public static List<SquareRule> getHighways() {
        Settings.generateHighwaysSettings();
        List<SquareRule> interesting = new ArrayList<>();
        interesting.add(new SquareRule("LRRRRRLRRRRLLLL", 480_000, 0.5, 2));
        interesting.add(new SquareRule("LLRLRLLLLRLRLRRL", 1_500_000, 0.1, 1));
        interesting.add(new SquareRule("LLLLLLLRRLLRLLL", 0, 1, 2));
        interesting.add(new SquareRule("LLLRLLRLRRRLLLLL", 0, 1, 2));
        interesting.add(new SquareRule("LLRLRLLLRLLLLL", 6_500_000, 0.1, 1));
        interesting.add(new SquareRule("LLRLRRLRLRRLLLLL", 5_500_000, 0.3, 1));
        interesting.add(new SquareRule("LLRRRLLLRRRLLLLL", 3_350_000, 0.3, 1));
        interesting.add(new SquareRule("LLRRRLRRRLLLLRRL", 0, 1, 2));
        interesting.add(new SquareRule("LLRRRLRRRLRLLLL", 0, 1, 2));
        interesting.add(new SquareRule("RRLRLLLLRLLRLLL", 2_350_000, 0.4, 1));
        interesting.add(new SquareRule("RRLRLLLRLLRRRRLL", 950_000, 0.5, 1));
        interesting.add(new SquareRule("LLLLRLLLRRLRLLL", 16_000_000, 0.1, 1));
        interesting.add(new SquareRule("RRLLLRRLRRRRLLLL", 960_000, 0.2, 1));
        interesting.add(new SquareRule("RRLRLLRRRRLL", 0, 1, 1));

        interesting.add(new SquareRule("RRLLLRRRRRLRLLL", 0, 1, 1));
        interesting.add(new SquareRule("LLLLRRLRLLLL", 0, 1, 1));
        interesting.add(new SquareRule("LLRLRRRLRRLLLL", 20_000_000, 0.3, 1));

        return interesting;
    }

    public static Set<List<SquareRule>> getFour() {
        Settings.generateFourImagesPerScreenSettings();
        Set<List<SquareRule>> interesting = new HashSet<>();

        // only first rule variables are set
        List<SquareRule> first = new ArrayList<>();
        first.add(new SquareRule("LRRLLLRRRLLLL", 0, 1, 1));
        first.add(new SquareRule("LRRLLLRRRLLLLL", 0, 1, 1));
        first.add(new SquareRule("LRRLLLRRRLLLLLL", 0, 1, 1));
        first.add(new SquareRule("LRRLLLRRRLLLLLLL", 0, 1, 1));
        interesting.add(first);

        List<SquareRule> second = new ArrayList<>();
        second.add(new SquareRule("LRRRRRLRRRRLLL", 0, 1, 1));
        second.add(new SquareRule("LRRRRRLRRRRLLLL", 0, 1, 1));
        second.add(new SquareRule("LRRRRRLRRRRLLLLL", 0, 1, 1));
        second.add(new SquareRule("LRRRRRLRRRRLLLLLL", 0, 1, 1));
        interesting.add(second);

        List<SquareRule> third = new ArrayList<>();
        third.add(new SquareRule("RRLLLRLLLRRRLLL", 0, 1, 1));
        third.add(new SquareRule("RRLLLRLLLRRRLRL", 0, 1, 1));
        third.add(new SquareRule("RRLLLRLLLRRRRL", 0, 1, 1));
        third.add(new SquareRule("RRLLLRLLLRRRRLL", 0, 1, 1));
        interesting.add(third);

        List<SquareRule> fourth = new ArrayList<>();
        fourth.add(new SquareRule("RRLLLRRRLLLLLLL", 0, 1, 1));
        fourth.add(new SquareRule("RRLLLRRRLLLLLLLL", 0, 1, 1));
        fourth.add(new SquareRule("RRLLLRRRLLLLLLLLL", 0, 1, 1));
        fourth.add(new SquareRule("RRLLLRRRLLLLLLLLLL", 0, 1, 1));
        interesting.add(fourth);

        List<SquareRule> fifth = new ArrayList<>();
        fifth.add(new SquareRule("RRLLRLLLLLLL", 0, 1, 1));
        fifth.add(new SquareRule("RRLLRLLLLLLLL", 0, 1, 1));
        fifth.add(new SquareRule("RRLLRLLLLLLLLL", 0, 1, 1));
        fifth.add(new SquareRule("RRLLRLLLLLLLLLL", 0, 1, 1));
        interesting.add(fifth);

        // Symmetric rules
        List<SquareRule> sixth = new ArrayList<>();
        sixth.add(new SquareRule("RRLLLLLRLLLLLLLL", 0, 1, 1));
        sixth.add(new SquareRule("LLRRRRRLRRRRRRRR", 0, 1, 1));
        sixth.add(new SquareRule("RRLRLLRRRRRLRL", 0, 1, 2));
        sixth.add(new SquareRule("LLRLRRLLLLLRLR", 0, 1, 2));
        interesting.add(sixth);

        List<SquareRule> seventh = new ArrayList<>();
        seventh.add(new SquareRule("RLLRRRLLLRRLRRRL", 0, 1, 1));
        seventh.add(new SquareRule("LRRLLLRRRLLRLLLR", 0, 1, 1));
        seventh.add(new SquareRule("LRRRRLLLRLRRRRL", 0, 1, 1));
        seventh.add(new SquareRule("RLLLLRRRLRLLLLR", 0, 1, 1));
        interesting.add(seventh);

        // Different rule same image
        List<SquareRule> eighth = new ArrayList<>();
        eighth.add(new SquareRule("LLRLLRRRRRRRRR", 0, 1, 5));
        eighth.add(new SquareRule("LLRLLRRRRRRLLL", 0, 1, 5));
        eighth.add(new SquareRule("LLRLLRRRRLLLLL", 0, 1, 5));
        eighth.add(new SquareRule("LLRLLRLLLLLLLL", 0, 1, 5));
        interesting.add(eighth);

        // Random noise, padding 0
        List<SquareRule> nineth = new ArrayList<>();
        nineth.add(new SquareRule("LLLRLL", 0, 1, 2));
        nineth.add(new SquareRule("LRLLRL", 0, 1, 2));
        nineth.add(new SquareRule("RLLRRL", 0, 1, 2));
        nineth.add(new SquareRule("RLRLLL", 0, 1, 2));
        interesting.add(nineth);

        // Highways
        List<SquareRule> tenth = new ArrayList<>();
        tenth.add(new SquareRule("LLRRRLRLRLLR", 0, 1, 2));
        tenth.add(new SquareRule("LLRLRRLL", 0, 1, 1));
        tenth.add(new SquareRule("LRLRRLRLLLRLRLRL", 0, 1, 1));
        tenth.add(new SquareRule("LLLRRRL", 0, 1, 1));
        interesting.add(tenth);

        return interesting;
    }

    public static List<SquareRule> getRandomWithPatternRules() {
        Settings.generateRandomWithPatternSettings();
        List<SquareRule> interesting = new ArrayList<>();
        interesting.add(new SquareRule("RRLLLRLRRLRRLRLL", 0, 1, 1));
        interesting.add(new SquareRule("RLRLRRRRRRRRRL", 0, 1, 1));
        interesting.add(new SquareRule("LRRRRLLLRLLLLLLL", 0, 1, 1));
        interesting.add(new SquareRule("LRRLRLLLLL", 0, 1, 1));
        interesting.add(new SquareRule("LRRLLRRLLLRLLLL", 0, 1, 1));
        interesting.add(new SquareRule("LRRLLLLRLLRRLLLL", 0, 1, 1));
        interesting.add(new SquareRule("LRLLLRRRRRLRLL", 0, 1, 1));
        interesting.add(new SquareRule("LLRLRRRLRRRRRRLL", 0, 1, 1));
        interesting.add(new SquareRule("LLLRLLRRLLLLLRRL", 0, 1, 1));

        return interesting;
    }

    public static List<SquareRule> getHighWayScreenFillRules() {
        List<SquareRule> interesting = new ArrayList<>();
        interesting.add(new SquareRule("RRL", 0, 1, 10)); // add grid
        interesting.add(new SquareRule("RLRRRL", 0, 1, 1));
        interesting.add(new SquareRule("RRLLLRLLLLLLLLL", 0, 1, 1)); // padding 500

        return interesting;
    }

    public static List<SquareRule> getDifferentTestRules() {
        Settings.generateTestSettings();
        List<SquareRule> interesting = new ArrayList<>();
        interesting.add(new SquareRule("LRRRRRLRRRRLLLL", 480_000, 0.5, 2));
        interesting.add(new SquareRule("RRLLLRLLLLLLLLL", 0, 1, 1));  // IMAGE_PADDING 600
        interesting.add(new SquareRule("RLL", 0, 1, 1));

        return interesting;
    }
}
