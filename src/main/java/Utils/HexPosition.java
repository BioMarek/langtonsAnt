package Utils;

import java.util.List;

/**
 * Degrees on hexagon
 * ....-30    30      |      330    30
 * -90            90  |  270            90
 * ...-150    150     |      210    150
 */
public class HexPosition {
    public int row;
    public int column;
    public int currentRotation;

    public HexPosition(int row, int column, int currentRotation) {
        this.row = row;
        this.column = column;
        this.currentRotation = currentRotation;

        List<Integer> allowedRotations = List.of(30, 90, 150, 210, 270, 330);
        if (allowedRotations.stream().noneMatch(el -> el == this.currentRotation))
            throw new RuntimeException("Rotation" + currentRotation + " is not allowed");
    }

    public HexPosition() {
    }

    public void move(HexMoves hexMoves) {
        turn(hexMoves);
        moveForward();
    }

    public void turn(HexMoves hexMoves) {
        currentRotation += hexMoves.degrees;
        if (currentRotation > 360) // TODO can be done as modulo?
            currentRotation -= 360;
        if (currentRotation < 0)
            currentRotation += 360;
    }

    public void moveForward() {
        // TODO refactor
        if (row % 2 == 0)
            switch (currentRotation) {
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
                default ->
                        throw new RuntimeException("Rotation " + currentRotation + " in PositionHexagonal is not allowed");
            }
        else
            switch (currentRotation) {
                case 30 -> row--;
                case 90 -> column++;
                case 150 -> row++;
                case 210 -> {
                    row++;
                    column--;
                }
                case 270 -> column--;
                case 330 -> {
                    row--;
                    column--;
                }
                default ->
                        throw new RuntimeException("Rotation " + currentRotation + " in PositionHexagonal is not allowed");
            }
    }
}
