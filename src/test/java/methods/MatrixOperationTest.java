package methods;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class MatrixOperationTest {

    @Test
    void shouldMultiplyForCorrectMatrixAndVector() {
        MatrixOperation matrixOperation = new MatrixOperation();
        double[][] matrix = new double[][]{{0.129, 0, 0.666}, {1, 5, 10}, {1, 0.25, 1}};
        double[] vector = new double[]{0.6, 0.1, 0.3};
        double[] expectedResult = {0.2772, 4.1, 0.925};

        double[] result = matrixOperation.multiplyMatrixByVector(matrix, vector);

        assertThat(result)
                .containsExactly(expectedResult, withPrecision(0.0001));
    }

}