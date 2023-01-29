package Logic.Rule;

import Logic.HexMove;
import Utils.Settings;

import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

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
        interesting.add(new HexRule("L1R2L1L2L1R1NUL2L1L1R2R1R1UR2"));


        return interesting;
    }

    public static List<HexRule> getToHighRes() {
        Settings.generateHexInterestingSettings();
        List<HexRule> interesting = new ArrayList<>();
        interesting.add(new HexRule("L1L2NL2L1L1R2"));
        interesting.add(new HexRule("L1L2NL2L1UR2"));
        interesting.add(new HexRule("L1L2R1NUL2R1"));
        interesting.add(new HexRule("L1R2L1L2L1R1NUL2L1L1R2R1R1UR2"));
        interesting.add(new HexRule("L1R2L1R2L1L2UL1R1L2R1L1L2NR2L2L2"));
        interesting.add(new HexRule("L1R2NL2UUR2UR2NL2L2NR2L1UN"));
        interesting.add(new HexRule("L1R2UUUUUU"));
        interesting.add(new HexRule("L1UL2L2R1NUR1"));
        interesting.add(new HexRule("L1UL2UL1UL1R1"));
        interesting.add(new HexRule("L1UUL2R2NR2"));
        interesting.add(new HexRule("L2L1UL2L2R1NU"));
        interesting.add(new HexRule("L2L2L2NUL1"));
        interesting.add(new HexRule("L2NNL1L2L1"));
        interesting.add(new HexRule("L2R2R1UUR1L2R2"));
        interesting.add(new HexRule("L2R2R2L2UL2R2UR2"));
        interesting.add(new HexRule("R1L1UR2R2UU"));
        interesting.add(new HexRule("R1L2R1L1UR2R1R2"));
        interesting.add(new HexRule("R1L2R1R2R2NL2R2"));
        interesting.add(new HexRule("R1R1NR1R2N"));
        interesting.add(new HexRule("R1R1NR2R1N"));
        interesting.add(new HexRule("R1R2L1R1NU"));
        interesting.add(new HexRule("R1UR2L2L2R1NU"));
        interesting.add(new HexRule("R2L2R2L1R2UU"));
        interesting.add(new HexRule("R2NNR1R2R1"));
        interesting.add(new HexRule("R2R1L2R2R1UR2"));
        interesting.add(new HexRule("R2R1UNR1R1R1R2"));
        interesting.add(new HexRule("R2R2R2NUR1"));
        interesting.add(new HexRule("R2R2UL2R2"));

        return interesting;
    }

    public static List<HexRule> getHighways() {
        Settings.generateHexHighwaysSettings();
        List<HexRule> interesting = new ArrayList<>();
        interesting.add(new HexRule("L1R2L1R2NR2UR1"));
        interesting.add(new HexRule("L2L2L2UUL1R1"));
        interesting.add(new HexRule("L2UR1L2UR1R2R2"));
        interesting.add(new HexRule("R1NR2R2R1L1NR2"));
        interesting.add(new HexRule("UR2R2R1L2R2UR2"));
        interesting.add(new HexRule("L1L2L2L2UNL1R1"));
        interesting.add(new HexRule("L1R2UUNL2R1L1L1L1R1NL2NL1"));
        interesting.add(new HexRule("L2L2UL2R1L2L1R1"));
        interesting.add(new HexRule("L2R2R2UR1L2UR1"));
        interesting.add(new HexRule("L2UR2L2UL2R1U"));
        interesting.add(new HexRule("R1L2UR2UR2NR1R2L2NUR2L1R2NR2"));
        interesting.add(new HexRule("R1UR2NL2R1UR2R2"));
        interesting.add(new HexRule("R2L1L2R1R1UR1"));
        interesting.add(new HexRule("R2R2R2UL2UR2U"));
        interesting.add(new HexRule("R2UUNNR2NR1"));
        interesting.add(new HexRule("UL1UL1UR2L1L2R2"));
        interesting.add(new HexRule("L1R1UUUUR2R2NL1U"));
        interesting.add(new HexRule("L2R2UNL2R2R2"));
        interesting.add(new HexRule("R1L1R1UL1R1UU"));
        interesting.add(new HexRule("R1R2R2R1L1L2R2R1R2"));
        interesting.add(new HexRule("R2L2UL2R2"));

        return interesting;
    }
}
