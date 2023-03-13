package Logic.Rule;

import Utils.Settings;

import java.text.AttributedString;

public abstract class Rule {
    public int slowdownSteps;
    public double slowdownModifier;
    public int sizeOfSquare = Settings.SIZE_OF_SQUARE;

    public int getSlowdownSteps() {
        return slowdownSteps;
    }

    public double getSlowdownModifier() {
        return slowdownModifier;
    }

    public abstract int getSize();

    public abstract String getElement(int position);

    public abstract AttributedString getAttributeString(int fontUnit);

    public String getSquareRule() {
        return null;
    }

    public abstract void setVariables();
}
