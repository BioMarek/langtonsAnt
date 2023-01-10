import Logic.HexAnt;
import Utils.HexMoves;
import Utils.HexRule;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HexAntTest {
    private HexAnt hexAnt;

    @Test
    void rule_R1U_works() {
        hexAnt = new HexAnt(new HexRule(List.of(HexMoves.R1, HexMoves.U)), 4, 4);
        hexAnt.nextMove();
        assertThat(hexAnt.grid[2][2], is(1));
        assertThat(hexAnt.antPosition.row, is(3));
        assertThat(hexAnt.antPosition.column, is(3));

        hexAnt.grid[3][3] = 1;
        hexAnt.nextMove();
        assertThat(hexAnt.grid[3][3], is(2));
        assertThat(hexAnt.antPosition.row, is(2));
        assertThat(hexAnt.antPosition.column, is(2));
    }

    @Test
    void rule_L1L2N_works() {
        hexAnt = new HexAnt(new HexRule(List.of(HexMoves.L1, HexMoves.L2, HexMoves.N)), 4, 4);
        hexAnt.nextMove();
        assertThat(hexAnt.grid[2][2], is(1));
        assertThat(hexAnt.antPosition.row, is(1));
        assertThat(hexAnt.antPosition.column, is(3));

        hexAnt.grid[1][3] = 1;
        hexAnt.nextMove();
        assertThat(hexAnt.grid[1][3], is(2));
        assertThat(hexAnt.antPosition.row, is(1));
        assertThat(hexAnt.antPosition.column, is(2));

        hexAnt.grid[1][2] = 2;
        hexAnt.nextMove();
        assertThat(hexAnt.grid[1][2], is(3));
        assertThat(hexAnt.antPosition.row, is(1));
        assertThat(hexAnt.antPosition.column, is(1));
    }
    @Test
    void rule_R2N_works() {
        hexAnt = new HexAnt(new HexRule(List.of(HexMoves.R2, HexMoves.N)), 4, 4);
        hexAnt.nextMove();
        assertThat(hexAnt.grid[2][2], is(1));
        assertThat(hexAnt.antPosition.row, is(3));
        assertThat(hexAnt.antPosition.column, is(2));

        hexAnt.grid[3][2] = 1;
        hexAnt.nextMove();
        assertThat(hexAnt.antPosition.row, is(4));
        assertThat(hexAnt.antPosition.column, is(1));
    }
}
