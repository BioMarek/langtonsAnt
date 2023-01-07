package Utils;

import java.util.List;

/**
 * Degrees on hexagon
 * ....-30    30      |      330    30
 * -90            90  |  270            90
 * ...-150    150     |      210    150
 */
public class PositionHexagonal {
    private final List<Integer> allowedRotations = List.of(30, 90, 150, 210, 270, 330);
    public int row;
    public int column;
    public int rotation;

    public PositionHexagonal(int row, int column, int rotation) {
        this.row = row;
        this.column = column;
        this.rotation = rotation;

        if (allowedRotations.stream().noneMatch(el -> el == this.rotation))
            throw new RuntimeException("Rotation" + rotation + " is not allowed");
    }

    public void move(DirectionHexagonal directionHexagonal) {
        turn(directionHexagonal);
        moveForward();
    }

    public void turn(DirectionHexagonal directionHexagonal) {
        rotation += directionHexagonal.degrees;
        if (rotation > 360) // TODO can be done as modulo?
            rotation -= 360;
        if (rotation < 0)
            rotation += 360;
    }

    public void moveForward() {
        switch (rotation) {
            case 30 -> {
                row--;
                column++;
            }
            case 90 -> column++;
            case 150 -> {
                row++;
                column++;
            }
            case 210 -> row++;
            case 270 -> column--;
            case 330 -> row--;
            default -> throw new RuntimeException("Rotation " + rotation + " in PositionHexagonal is not allowed");
        }
    }
}
