package Utils;

public enum RotationHexagonal {
    N(0), R1(60), R2(120), U(180), L2(-120), L1(-60);

    public final int degrees;

    RotationHexagonal(int degrees) {
        this.degrees = degrees;
    }
}
