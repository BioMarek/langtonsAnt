import Utils.HexMoves;
import Utils.HexPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HexPositionTest {
    HexPosition hexPosition;

    @BeforeEach
    void setup() {
        hexPosition = new HexPosition(2, 2, 30);
    }

    @Test
    void turn_isCorrect() {
        hexPosition = new HexPosition(2, 2, 30);
        hexPosition.turn(HexMoves.N);
        assertThat(hexPosition.currentRotation, is(30));

        hexPosition.turn(HexMoves.U);
        assertThat(hexPosition.currentRotation, is(210));

        hexPosition = new HexPosition(2, 2, 330);
        hexPosition.turn(HexMoves.R1);
        assertThat(hexPosition.currentRotation, is(30));

        hexPosition = new HexPosition(2, 2, 30);
        hexPosition.turn(HexMoves.L1);
        assertThat(hexPosition.currentRotation, is(330));
    }

    @Test
    void move_isCorrect() {
        hexPosition = new HexPosition(2, 2, 30);
        hexPosition.moveForward();
        assertThat(hexPosition.row, is(1));
        assertThat(hexPosition.column, is(3));

        hexPosition = new HexPosition(2, 2, 90);
        hexPosition.moveForward();
        assertThat(hexPosition.row, is(2));
        assertThat(hexPosition.column, is(3));

        hexPosition = new HexPosition(2, 2, 150);
        hexPosition.moveForward();
        assertThat(hexPosition.row, is(3));
        assertThat(hexPosition.column, is(3));

        hexPosition = new HexPosition(2, 2, 210);
        hexPosition.moveForward();
        assertThat(hexPosition.row, is(3));
        assertThat(hexPosition.column, is(2));

        hexPosition = new HexPosition(2, 2, 270);
        hexPosition.moveForward();
        assertThat(hexPosition.row, is(2));
        assertThat(hexPosition.column, is(1));

        hexPosition = new HexPosition(2, 2, 330);
        hexPosition.moveForward();
        assertThat(hexPosition.row, is(1));
        assertThat(hexPosition.column, is(2));
    }
}
