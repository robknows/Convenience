import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

public class Conveniencer {
    // Dyads

    public Dyad add = Dyad.fromOperator((left, right) -> left + right);
    public Dyad times = Dyad.fromOperator((left, right) -> left * right);
    public Dyad minus = Dyad.fromOperator((left, right) -> left - right);
    public Dyad divide = Dyad.fromOperator((left, right) -> left / right);

    public Dyad dyad(DoubleBinaryOperator operator) {
        return Dyad.fromOperator(operator);
    }

    // Monads

    public Monad neg = Monad.fromOperator(operand -> -operand);
    public Monad reciprocal = Monad.fromOperator(operand -> 1 / operand);
    public Monad sqrt = Monad.fromOperator(Math::sqrt);

    public Monad monad(DoubleUnaryOperator operator) {
        return Monad.fromOperator(operator);
    }
}
