package methods;

import com.sun.org.apache.xpath.internal.Arg;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

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
                        new double[]{0.2772, 4.1, 0.925})
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldCalculateWeightedMatrixForCorrectSourceMatrixAndWeightsVector(double[][] matrix,
                                                           double[] weightsVector, double[][] expectedWeightedMatrix) {
        double[][] weightedMatrix = MatrixOperation.calculateWeightedMatrix(matrix, weightsVector);

        assertThat(weightedMatrix[0])
                .containsExactly(expectedWeightedMatrix[0], withPrecision(0.001));
        assertThat(weightedMatrix[1])
                .containsExactly(expectedWeightedMatrix[1], withPrecision(0.001));
        assertThat(weightedMatrix[2])
                .containsExactly(expectedWeightedMatrix[2], withPrecision(0.001));
    }

    static Stream<Arguments> shouldCalculateWeightedMatrixForCorrectSourceMatrixAndWeightsVector() {
        return Stream.of(
                Arguments.of(
                        new double[][]{{0.129, 1, 1}, {0, 5, 0.25}, {0.666, 10, 1}},
                        new double[]{0.6, 0.1, 0.3},
                        new double[][]{{0.0774, 0.6, 0.6},{0, 0.5, 0.025},{0.1998, 3, 0.3}}
                ),
                Arguments.of(
                        new double[][]{{1, 50, 100, 200}, {0.25, 0.5, 0.75, 1.5}, {0.99, 0.01, 0, 1}},
                        new double[]{0.7, 0.2, 0.1},
                        new double[][]{{0.7, 35, 70, 140},{0.05, 0.1, 0.15, 0.3}, {0.099, 0.001, 0, 0.1}}
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldFindMaxValuesForCriteriaInDecisionMatrix(double[][] matrix, double[] expectedPisVector) {
        double[] pisVector= MatrixOperation.findPisVector(matrix);

        assertThat(pisVector)
                .containsExactly(expectedPisVector);
    }

    static Stream<Arguments> shouldFindMaxValuesForCriteriaInDecisionMatrix() {
        return Stream.of(
                Arguments.of(new double[][]{{0.1, 0.01, 0.001}, {1, 10, 100}, {1, 1, 1}},
                        new double[]{0.1, 100, 1}),
                Arguments.of(new double[][]{{-0.1, -0.01, -0.001}, {10, 10, 10}, {0.11, 0.10, 0.12}},
                        new double[]{-0.001, 10, 0.12}),
                Arguments.of(new double[][]{{11, 11}, {23.1, 23.1}},
                        new double[]{11, 23.1}),
                Arguments.of(new double[][]{{Double.MAX_VALUE, 55, Double.MIN_VALUE},
                                {Double.MIN_VALUE, Double.MAX_VALUE, 0.002}},
                        new double[]{Double.MAX_VALUE, Double.MAX_VALUE})

        );
    }

    @Test
    void shouldThrowNoSuchElementExceptionForInvalidDecisionMatrix() {
        DecisionProblem decisionProblem = DecisionProblem.Builder.builder()
                .withDecisionMatrix(new double[2][0])
                .build();

        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(decisionProblem::findMaxValueForEachCriterion);
    }

    @ParameterizedTest
    @MethodSource
    void shouldFindMinValuesForCriteriaInDecisionMatrix(double[][] matrix, double[] expectedNisVector) {
        double[] nisVector = MatrixOperation.findNisVector(matrix);

        assertThat(nisVector)
                .containsExactly(expectedNisVector);
    }

    static Stream<Arguments> shouldFindMinValuesForCriteriaInDecisionMatrix() {
        return Stream.of(
                Arguments.of(new double[][] {{0.1, 0.01, 0.001}, {1, 10, 100}, {1, 1, 1}},
                        new double[] {0.001, 1, 1}),
                Arguments.of(new double[][] {{-0.1, -0.01, -0.001}, {10, 10, 10}, {0.11, 0.10, 0.12}},
                        new double[] {-0.1, 10, 0.1}),
                Arguments.of(new double[][]{{11, 11}, {23.1, 23.1}},
                        new double[]{11, 23.1}),
                Arguments.of(new double[][]{{Double.MAX_VALUE, 55, Double.MIN_VALUE},
                                {Double.MIN_VALUE, Double.MAX_VALUE, 0.002}},
                        new double[]{Double.MIN_VALUE, Double.MIN_VALUE})
        );
    }






}