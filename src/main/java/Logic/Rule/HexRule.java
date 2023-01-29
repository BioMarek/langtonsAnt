package Logic.Rule;

import Logic.HexMove;
import Utils.Settings;

import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HexRule extends Rule {
    // TODO unify HexRule nad Rule
    public List<HexMove> rule;

    public HexRule(List<HexMove> rule, int slowdownSteps, double slowdownModifier) {
        this.rule = rule;
        this.slowdownSteps = slowdownSteps;
        this.slowdownModifier = slowdownModifier;
    }

    public HexRule(String rule) {
        this.rule = stringToMoves(rule);
        this.slowdownSteps = 1;
        this.slowdownModifier = 1;
    }

    @Override
    public int getSize() {
        return rule.size();
    }

    @Override
    public String getElement(int position) {
        return rule.get(position).toString();
    }

    @Override
    public String getType() {
        return "hex";
    }

    public static List<HexRule> hexagonalReferenceRules() {
        List<HexRule> result = new ArrayList<>();
        result.add(new HexRule(List.of(HexMove.R1, HexMove.R2, HexMove.N, HexMove.U, HexMove.R2, HexMove.R1, HexMove.L2), 1, 1));
        result.add(new HexRule(List.of(HexMove.L1, HexMove.L2, HexMove.N, HexMove.U, HexMove.L2, HexMove.L1, HexMove.R2), 1, 1));
        result.add(new HexRule(List.of(HexMove.L2, HexMove.N, HexMove.N, HexMove.L1, HexMove.L2, HexMove.L1), 1, 1));
        return result;
    }

    public AttributedString getAttributeString(int fontUnit) {
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
    public void setVariables() {
        Settings.HEX_RULE = this;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (HexMove hexMove : rule)
            stringBuilder.append(hexMove.toString());
        return stringBuilder.toString();
    }

    private List<HexMove> stringToMoves(String rule) {
        List<HexMove> result = new ArrayList<>();
        while (rule.length() > 0) {
            String shortMove = rule.substring(0, 1);
            String longMove = rule.substring(0, Math.min(2, rule.length()));
            if (substringToMove(shortMove) != null) {
                result.add((substringToMove(shortMove)));
                rule = rule.substring(1);
            } else {
                result.add((substringToMove(longMove)));
                rule = rule.substring(2);
            }
        }

        return result;
    }

    private HexMove substringToMove(String substring) {
        switch (substring) {
            case "N" -> {
                return HexMove.N;
            }
            case "R1" -> {
                return HexMove.R1;
            }
            case "R2" -> {
                return HexMove.R2;
            }
            case "U" -> {
                return HexMove.U;
            }
            case "L2" -> {
                return HexMove.L2;
            }
            case "L1" -> {
                return HexMove.L1;
            }
            default -> {
                return null;
            }
        }
    }

    public static List<HexRule> getInteresting() {
        Settings.generateHexInterestingSettings();
        List<HexRule> interesting = new ArrayList<>();
//        interesting.add(new HexRule(List.of(HexMove.L1, HexMove.L2, HexMove.N, HexMove.U, HexMove.U, HexMove.R2, HexMove.U, HexMove.U), 1, 1));
        interesting.add(new HexRule("R1R1UR1R2NU"));

        return interesting;
    }
}
