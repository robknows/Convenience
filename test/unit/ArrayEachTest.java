package unit;

import convenience.Conveniencer;
import convenience.Monad;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ArrayEachTest {
    private Conveniencer c = new Conveniencer();

    @Test
    public void canSquareEachOfAList() {
        Monad<Double, Double> square = c.monad(operand -> operand * operand);
        assertThat(square.each(Arrays.stream(new Double[]{3.0, 4.0, 5.0})).toArray(), equalTo(new Double[]{9.0, 16.0, 25.0}));
    }
}
