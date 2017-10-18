import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ArraySubtractionTest {
    private Dyad<Integer, Integer, Integer> minus = new Conveniencer().dyad((left, right) -> left - right);

    @Test
    public void canSubtractScalarFromScalar() {
        assertThat(minus.scalar_scalar(4, 5), equalTo(-1));
    }

    @Test
    public void canSubtractVectorFromScalar() {
        assertThat(minus.scalar_vector(5, new Integer[]{3, 4, 5}), equalTo(new Integer[]{2, 1, 0}));
    }

    @Test
    public void canSubtractScalarFromVector() {
        assertThat(minus.vector_scalar(new Integer[]{0, 2, 6, 9, 4}, 3), equalTo(new Integer[]{-3, -1, 3, 6, 1}));
    }

    @Test
    public void canSubtractVectorFromVector() throws LengthError {
        assertThat(minus.vector_vector(new Integer[]{0, 2, 6, 9, 4}, new Integer[]{1, 2, 3, 4, 5}), equalTo(new Integer[]{-1, 0, 3, 5, -1}));
    }

    @Test(expected = LengthError.class)
    public void throwsLengthErrorIfVectorsNotSameLength() throws LengthError {
        minus.vector_vector(new Integer[]{0, 1, 2}, new Integer[]{10, 11});
    }
}
