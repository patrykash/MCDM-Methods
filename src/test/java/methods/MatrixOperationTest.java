package methods;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class MatrixOperationTest {

    @ParameterizedTest
    @MethodSource
    void shouldMultiplyForCorrectMatrixAndVector(double[][] matrix, double[] vector, double[] expectedResult) {
        double[] result = MatrixOperation.multiplyMatrixByVector(matrix, vector);

        assertThat(result)
                .containsExactly(expectedResult, withPrecision(0.001));
    }

    static Stream<Arguments> shouldMultiplyForCorrectMatrixAndVector() {
        return Stream.of(
                Arguments.of(
                        new double[][]{{0.129, 1, 1}, {0, 5, 0.25}, {0.666, 10, 1}},
                        new double[]{0.6, 0.1, 0.3},
                        new double[]{0.2772, 4.1, 0.925}),
                Arguments.of(
                        new double[][]{{0.129, 1, 1, 0}, {0, 5, 0.25, 0.333}, {0.666, 10, 1, 1}},
                        new double[]{0.6, 0.1, 0.3},
                        new double[]{0.2772, 4.1, 0.925, 0.333})
        );
    }

}