package Logic;

import java.util.List;

/**
 * Describes where the {@link Logic.Ant.HexAnt} is on the grid and its orientation. See /documentation/hexagonalGrid.png.
 * Degrees of directions the Ant can face on hexagon:
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

    /**
     * Moves {@link Logic.Ant.HexAnt} based on its current orientation and color of square it is standing on.
     *
     * @param hexMove move which should be performed
     */
    public void move(HexMove hexMove) {
        turn(hexMove);
        moveForward();
    }

    /**
     * Calculates where {@link Logic.Ant.HexAnt} is going to be facing after one move. It depends on color of hexagon
     * the ant is standing on.
     *
     * @param hexMove move which should be performed
     */
    public void turn(HexMove hexMove) {
        currentRotation += hexMove.degrees;
        if (currentRotation > 360)
            currentRotation -= 360;
        if (currentRotation < 0)
            currentRotation += 360;
    }

    /**
     * Calculates where {@link Logic.Ant.HexAnt} is going to be after move forward. It depends on current ant position
     * and orientation.
     */
    public void moveForward() {
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
