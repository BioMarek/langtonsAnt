import Utils.HexMoves;
import Utils.PositionHexagonal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PositionHexagonalTest {
    PositionHexagonal positionHexagonal;

    @BeforeEach
    void setup() {
        positionHexagonal = new PositionHexagonal(2, 2, 30);
    }

    @Test
    void turn_isCorrect() {
        positionHexagonal = new PositionHexagonal(2, 2, 30);
        positionHexagonal.turn(HexMoves.N);
        assertThat(positionHexagonal.currentRotation, is(30));

        positionHexagonal.turn(HexMoves.U);
        assertThat(positionHexagonal.currentRotation, is(210));

        positionHexagonal = new PositionHexagonal(2, 2, 330);
        positionHexagonal.turn(HexMoves.R1);
        assertThat(positionHexagonal.currentRotation, is(30));

        positionHexagonal = new PositionHexagonal(2, 2, 30);
        positionHexagonal.turn(HexMoves.L1);
        assertThat(positionHexagonal.currentRotation, is(330));
    }

    @Test
    void move_isCorrect() {
        positionHexagonal = new PositionHexagonal(2, 2, 30);
        positionHexagonal.moveForward();
        assertThat(positionHexagonal.row, is(1));
        assertThat(positionHexagonal.column, is(3));

        positionHexagonal = new PositionHexagonal(2, 2, 90);
        positionHexagonal.moveForward();
        assertThat(positionHexagonal.row, is(2));
        assertThat(positionHexagonal.column, is(3));

        positionHexagonal = new PositionHexagonal(2, 2, 150);
        positionHexagonal.moveForward();
        assertThat(positionHexagonal.row, is(3));
        assertThat(positionHexagonal.column, is(3));

        positionHexagonal = new PositionHexagonal(2, 2, 210);
        positionHexagonal.moveForward();
        assertThat(positionHexagonal.row, is(3));
        assertThat(positionHexagonal.column, is(2));

        positionHexagonal = new PositionHexagonal(2, 2, 270);
        positionHexagonal.moveForward();
        assertThat(positionHexagonal.row, is(2));
        assertThat(positionHexagonal.column, is(1));

        positionHexagonal = new PositionHexagonal(2, 2, 330);
        positionHexagonal.moveForward();
        assertThat(positionHexagonal.row, is(1));
        assertThat(positionHexagonal.column, is(2));
    }
}
