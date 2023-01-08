import Logic.AntHexagonal;
import Utils.HexMoves;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AntHexagonalTest {
    private AntHexagonal antHexagonal;

    @Test
    void rule_R1U_works() {
        antHexagonal = new AntHexagonal(List.of(HexMoves.R1, HexMoves.U), 4, 4);
        antHexagonal.nextMove();
        assertThat(antHexagonal.grid[2][2], is(1));
        assertThat(antHexagonal.antPosition.row, is(3));
        assertThat(antHexagonal.antPosition.column, is(3));

        antHexagonal.grid[3][3] = 1;
        antHexagonal.nextMove();
        assertThat(antHexagonal.grid[3][3], is(2));
        assertThat(antHexagonal.antPosition.row, is(2));
        assertThat(antHexagonal.antPosition.column, is(2));
    }

    @Test
    void rule_L1L2N_works() {
        antHexagonal = new AntHexagonal(List.of(HexMoves.L1, HexMoves.L2, HexMoves.N), 4, 4);
        antHexagonal.nextMove();
        assertThat(antHexagonal.grid[2][2], is(1));
        assertThat(antHexagonal.antPosition.row, is(1));
        assertThat(antHexagonal.antPosition.column, is(3));

        antHexagonal.grid[1][3] = 1;
        antHexagonal.nextMove();
        assertThat(antHexagonal.grid[1][3], is(2));
        assertThat(antHexagonal.antPosition.row, is(1));
        assertThat(antHexagonal.antPosition.column, is(2));

        antHexagonal.grid[1][2] = 2;
        antHexagonal.nextMove();
        assertThat(antHexagonal.grid[1][2], is(3));
        assertThat(antHexagonal.antPosition.row, is(1));
        assertThat(antHexagonal.antPosition.column, is(1));
    }
    @Test
    void rule_R2N_works() {
        antHexagonal = new AntHexagonal(List.of(HexMoves.R2, HexMoves.N), 4, 4);
        antHexagonal.nextMove();
        assertThat(antHexagonal.grid[2][2], is(1));
        assertThat(antHexagonal.antPosition.row, is(3));
        assertThat(antHexagonal.antPosition.column, is(2));

        antHexagonal.grid[3][2] = 1;
        antHexagonal.nextMove();
        assertThat(antHexagonal.antPosition.row, is(4));
        assertThat(antHexagonal.antPosition.column, is(1));
    }
}
