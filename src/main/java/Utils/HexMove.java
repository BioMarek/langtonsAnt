package Utils;

public enum HexMove {
    N(0), R1(60), R2(120), U(180), L2(-120), L1(-60);

    public final int degrees;

    HexMove(int degrees) {
        this.degrees = degrees;
    }

    public static HexMove stringToHexMove(String str){
        return switch (str) {
            case "N" -> HexMove.N;
            case "R1" -> HexMove.R1;
            case "R2" -> HexMove.R2;
            case "U" -> HexMove.U;
            case "L2" -> HexMove.L2;
            case "L1" -> HexMove.L1;
            default -> throw new RuntimeException("wrong string in stringToHexMove");
        };
    }
}
