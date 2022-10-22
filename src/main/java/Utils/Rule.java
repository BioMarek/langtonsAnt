package Utils;


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
