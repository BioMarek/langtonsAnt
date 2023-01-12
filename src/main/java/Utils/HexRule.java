package Utils;

import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

public class HexRule {
    // TODO unify HexRule nad Rule
    public List<HexMove> rule;
    public int slowdownSteps;
    public double slowdownModifier;
    public int sizeOfSquare = Settings.SIZE_OF_SQUARE;

    public HexRule(List<HexMove> rule, int slowdownSteps, double slowdownModifier) {
        this.rule = rule;
        this.slowdownSteps = slowdownSteps;
        this.slowdownModifier = slowdownModifier;
    }

    public static List<HexRule> hexagonalReferenceRules() {
        List<HexRule> result = new ArrayList<>();
        result.add(new HexRule(List.of(HexMove.R1, HexMove.R2, HexMove.N, HexMove.U, HexMove.R2, HexMove.R1, HexMove.L2), 1, 1));
        result.add(new HexRule(List.of(HexMove.L1, HexMove.L2, HexMove.N, HexMove.U, HexMove.L2, HexMove.L1, HexMove.R2), 1, 1));
        result.add(new HexRule(List.of(HexMove.L2, HexMove.N, HexMove.N, HexMove.L1, HexMove.L2, HexMove.L1), 1, 1));
        return result;
    }

    public AttributedString hexRuleToAttributeString(int fontUnit) {
        String translatedRule = toString();
        AttributedString attributedString = new AttributedString(translatedRule);
        attributedString.addAttribute(TextAttribute.SIZE, fontUnit * 1.2);
        attributedString.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD, 0, translatedRule.length());

        for (int i = 0; i < translatedRule.length(); i++) {
            if (translatedRule.charAt(i) == '1' || translatedRule.charAt(i) == '2')
                attributedString.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUB, i, i + 1);
        }

        return attributedString;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (HexMove hexMove : rule)
            stringBuilder.append(hexMove.toString());
        return stringBuilder.toString();
    }
}
