package Utils;

import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

public class HexRule {
    public List<HexMove> rule;

    public HexRule(List<HexMove> rule) {
        this.rule = rule;
    }

    public static List<HexRule> hexagonalReferenceRules() {
        List<HexRule> result = new ArrayList<>();
        result.add(new HexRule(List.of(HexMove.R1, HexMove.R2, HexMove.N, HexMove.U, HexMove.R2, HexMove.R1, HexMove.L2)));
        result.add(new HexRule(List.of(HexMove.L1, HexMove.L2, HexMove.N, HexMove.U, HexMove.L2, HexMove.L1, HexMove.R2)));
        result.add(new HexRule(List.of(HexMove.L2, HexMove.N, HexMove.N, HexMove.L1, HexMove.L2, HexMove.L1)));
        return result;
    }

    public AttributedString hexRuleToAttributeString(int fontUnit) {
        String translatedRule = translateRuleToString();
        AttributedString attributedString = new AttributedString(translateRuleToString());
        attributedString.addAttribute(TextAttribute.SIZE, fontUnit * 1.2);
        attributedString.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD, 0, translatedRule.length());

        for (int i = 0; i < translatedRule.length(); i++) {
            if (translatedRule.charAt(i) == '1' || translatedRule.charAt(i) == '2')
                attributedString.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUB, i, i + 1);
        }

        return attributedString;
    }

    public String translateRuleToString() {
        StringBuilder result = new StringBuilder();
        for (HexMove i : rule) {
            result.append(i.toString());
        }
        return result.toString();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (HexMove hexMove : rule)
            stringBuilder.append(hexMove.toString());
        return stringBuilder.toString();
    }
}
