package methods;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class DecisionProblemTest {


    @ParameterizedTest
    @MethodSource
    void shouldFindMaxValuesForCriteriaInDecisionMatrix(DecisionProblem decisionProblem, double[] expectedMaxValues) {
        double[] maxCriteriaValues = decisionProblem.findMaxValueForEachCriterion();

        assertThat(maxCriteriaValues)
                .containsExactly(expectedMaxValues);
    }

    static Stream<Arguments> shouldFindMaxValuesForCriteriaInDecisionMatrix() {
        return Stream.of(
                Arguments.of(DecisionProblem.Builder.builder()
                        .withDecisionMatrix(new double[][]{{0.1, 0.01, 0.001}, {1, 10, 100}, {1, 1, 1}})
                        .build(), new double[]{0.1, 100, 1}),
                Arguments.of(DecisionProblem.Builder.builder()
                        .withDecisionMatrix(new double[][]{{-0.1, -0.01, -0.001}, {10, 10, 10}, {0.11, 0.10, 0.12}})
                        .build(), new double[]{-0.001, 10, 0.12}),
                Arguments.of(DecisionProblem.Builder.builder()
                        .withDecisionMatrix(new double[][]{{11, 11}, {23.1, 23.1}})
                        .build(), new double[]{11, 23.1}),
                Arguments.of(DecisionProblem.Builder.builder()
                        .withDecisionMatrix(new double[][]{{Double.MAX_VALUE, 55, Double.MIN_VALUE},
                                {Double.MIN_VALUE, Double.MAX_VALUE, 0.002}})
                        .build(), new double[]{Double.MAX_VALUE, Double.MAX_VALUE})

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
    void shouldFindMinValuesForCriteriaInDecisionMatrix(DecisionProblem decisionProblem, double[] expectedMinValues) {
        double[] minCriteriaValues = decisionProblem.findMinValueForEachCriterion();

        assertThat(minCriteriaValues)
                .containsExactly(expectedMinValues);
    }

    static Stream<Arguments> shouldFindMinValuesForCriteriaInDecisionMatrix() {
        return Stream.of(
                Arguments.of(DecisionProblem.Builder.builder()
                        .withDecisionMatrix(new double[][] {{0.1, 0.01, 0.001}, {1, 10, 100}, {1, 1, 1}})
                        .build(), new double[] {0.001, 1, 1}),
                Arguments.of(DecisionProblem.Builder.builder()
                        .withDecisionMatrix(new double[][] {{-0.1, -0.01, -0.001}, {10, 10, 10}, {0.11, 0.10, 0.12}})
                        .build(), new double[] {-0.1, 10, 0.1}),
                Arguments.of(DecisionProblem.Builder.builder()
                        .withDecisionMatrix(new double[][]{{11, 11}, {23.1, 23.1}})
                        .build(), new double[]{11, 23.1}),
                Arguments.of(DecisionProblem.Builder.builder()
                        .withDecisionMatrix(new double[][]{{Double.MAX_VALUE, 55, Double.MIN_VALUE},
                                {Double.MIN_VALUE, Double.MAX_VALUE, 0.002}})
                        .build(), new double[]{Double.MIN_VALUE, Double.MIN_VALUE})
        );
    }


}