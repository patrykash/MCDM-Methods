package methods;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class MaxMinNormalizationTest {


    @ParameterizedTest
    @MethodSource
    void shouldNormalizeMatrix(DecisionProblem decisionProblem, double[][] correctNormalizedMatrix) {
        MaxMinNormalization normalization = new MaxMinNormalization(decisionProblem);


        double[][] normalizedMatrix = normalization.normalizeMatrix();


        assertThat(normalizedMatrix[0]).
                as("normalized matrix").
                containsExactly(correctNormalizedMatrix[0], withPrecision(0.00001));
        assertThat(normalizedMatrix[1])
                .as("normalized matrix")
                .containsExactly(correctNormalizedMatrix[1], withPrecision(0.00001));
        assertThat(normalizedMatrix[2])
                .as("normalized matrix")
                .containsExactly(correctNormalizedMatrix[2], withPrecision(0.00001));

    }

    static Stream<Arguments> shouldNormalizeMatrix() {
        String[] twoVariants = {"variant1", "variant2"};
        String[] threeVariants = {"variant1", "variant2","variant3"};

        return Stream.of(
                Arguments.of(DecisionProblem.Builder.builder()
                                .withCriteriaTypes(Arrays.asList(CriterionType.MAX, CriterionType.MIN, CriterionType.MIN))
                                .withDecisionMatrix(new double[][]{{0.340, 0.316}, {8.0, 7.7}, {4, 5}})
                                .withVariants(twoVariants)
                                .build(),
                        new double[][]{{1, 0}, {0, 1,}, {1, 0}}),
                Arguments.of(DecisionProblem.Builder.builder()
                                .withCriteriaTypes(Arrays.asList(CriterionType.MAX, CriterionType.MIN, CriterionType.MIN))
                                .withDecisionMatrix(new double[][]{{Double.MAX_VALUE, Double.MIN_VALUE},
                                        {Double.MAX_VALUE, Double.MIN_VALUE},
                                        {Double.MIN_VALUE, Double.MAX_VALUE}})
                                .withVariants(twoVariants)
                                .build(),
                        new double[][]{{1, 0}, {0, 1,}, {1, 0}}),
                Arguments.of(DecisionProblem.Builder.builder()
                                .withCriteriaTypes(Arrays.asList(CriterionType.MAX, CriterionType.MIN, CriterionType.MIN))
                                .withDecisionMatrix(new double[][]{{0.225, 0.445, 0.875},
                                        {80, 127.7, 7.8},
                                        {1114, 2225, 3336}})
                                .withVariants(threeVariants)
                                .build(),
                        new double[][]{{0, 0.33846, 1}, {0.39783, 0, 1}, {1, 0.5, 0}})
        );
    }


}