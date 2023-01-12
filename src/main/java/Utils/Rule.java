package Utils;

public interface Rule {
    // TODO rule info for legend
    int getSlowdownSteps();
    double getSlowdownModifier();
    int getSize();
    String getElement(int position);

    // TODO refactor
    String getType();

    default String getSquareRule(){
        return null;
    };
}
