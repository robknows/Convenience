import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ConveniencerTest {
    private Conveniencer c = new Conveniencer();

    @Test
    public void canAddScalarToScalar() {
        assertEquals(9, c.add(4, 5));
    }

    @Test
    public void canAddScalarToVector() {
        assertThat(c.add(5, new int[]{3, 4, 5}), equalTo(new int[]{8, 9, 10}));
    }
}