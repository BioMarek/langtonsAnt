import Utils.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UtilTest {
    @Test
    void numberFormatter(){
        assertThat(Util.numberFormatter(1000000), is("1 000 000"));
        assertThat(Util.numberFormatter(50), is("50"));
    }
}
