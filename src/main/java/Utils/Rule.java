package Utils;

/**
 * Langton's ant rule and metadata. For highways we want to slow down animation when highway starts growing. Number of
 * steps when to slow down is slowdownSteps. How much to slow down is defined in slowdownModifier 1 is no slow down,
 * more than 1 is speed up.
 */
public class Rule {
    public String rule;
    public int slowdownSteps;
    public double slowdownModifier;

    public Rule(String rule, int slowdownSteps, double slowdownModifier) {
        this.rule = rule;
        this.slowdownSteps = slowdownSteps;
        this.slowdownModifier = slowdownModifier;
    }
}
