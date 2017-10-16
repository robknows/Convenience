import java.util.Arrays;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

public abstract class Dyadic {
    public abstract double scalar_scalar(double left, double right);
    public abstract double[] scalar_vector(double left, double[] right);
    public abstract double[] vector_scalar(double[] left, double right);
    public abstract double[] vector_vector(double[] left, double[] right) throws LengthError;

    public double[] eachBoth(double[] left, double[] right) throws LengthError {
        if (left.length != right.length) {
            throw new LengthError();
        }
        double[] result = new double[left.length];
        for (int i = 0; i < left.length; i++) {
            result[i] = scalar_scalar(left[i], right[i]);
        }
        return result;
    }

    public static Dyadic commutative(DoubleBinaryOperator operator) {
        return new Dyadic() {
            public double scalar_scalar(double left, double right) {
                return operator.applyAsDouble(left, right);
            }

            @Override
            public double[] scalar_vector(double left, double[] right) {
                return Arrays.stream(right).map(operand -> operator.applyAsDouble(left, operand)).toArray();
            }

            @Override
            public double[] vector_scalar(double[] left, double right) {
                return scalar_vector(right, left);
            }

            @Override
            public double[] vector_vector(double[] left, double[] right) throws LengthError {
                return eachBoth(left, right);
            }
        };
    }

    public static Dyadic fromInverse(Dyadic inverse, DoubleUnaryOperator operator) {
        return new Dyadic() {
            @Override
            public double scalar_scalar(double left, double right) {
                return inverse.scalar_scalar(left, operator.applyAsDouble(right));
            }

            @Override
            public double[] scalar_vector(double left, double[] right) {
                return inverse.scalar_vector(left, Arrays.stream(right).map(operator).toArray());
            }

            @Override
            public double[] vector_scalar(double[] left, double right) {
                return inverse.vector_scalar(left, operator.applyAsDouble(right));
            }

            @Override
            public double[] vector_vector(double[] left, double[] right) throws LengthError {
                return inverse.vector_vector(left, Arrays.stream(right).map(operator).toArray());
            }
        };
    }
}
