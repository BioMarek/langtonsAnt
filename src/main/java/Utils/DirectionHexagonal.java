package Utils;

public enum DirectionHexagonal {
    N(0), R1(60), R2(120), U(180), L2(-120), L1(-60);

    public final int degrees;

    DirectionHexagonal(int degrees) {
        this.degrees = degrees;
    }
}
