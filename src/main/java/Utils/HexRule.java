package Utils;

import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

public class HexRule {
    public List<HexMoves> rule;

    public HexRule(List<HexMoves> rule) {
        this.rule = rule;
    }

    public static List<HexRule> hexagonalReferenceRules() {
        List<HexRule> result = new ArrayList<>();
        result.add(new HexRule(List.of(HexMoves.R1, HexMoves.R2, HexMoves.N, HexMoves.U, HexMoves.R2, HexMoves.R1, HexMoves.L2)));
        result.add(new HexRule(List.of(HexMoves.L1, HexMoves.L2, HexMoves.N, HexMoves.U, HexMoves.L2, HexMoves.L1, HexMoves.R2)));
        result.add(new HexRule(List.of(HexMoves.L2, HexMoves.N, HexMoves.N, HexMoves.L1, HexMoves.L2, HexMoves.L1)));
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
        for (HexMoves i : rule) {
            result.append(i.toString());
        }
        return result.toString();
    }
}
