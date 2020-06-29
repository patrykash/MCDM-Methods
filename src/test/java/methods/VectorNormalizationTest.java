package methods;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class VectorNormalizationTest {


    @ParameterizedTest
    @MethodSource
    void shouldNormalizeMatrix(DecisionProblem decisionProblem, double[][] correctNormalizedMatrix) {
        VectorNormalization vectorNormalization = new VectorNormalization();
        vectorNormalization.setDecisionProblem(decisionProblem);

        double[][] normalizedMatrix = vectorNormalization.normalizeMatrix();

        assertThat(normalizedMatrix[0]).
                as("normalized matrix").
                containsExactly(correctNormalizedMatrix[0], withPrecision(0.0001));
        assertThat(normalizedMatrix[1])
                .as("normalized matrix")
                .containsExactly(correctNormalizedMatrix[1], withPrecision(0.0001));
        assertThat(normalizedMatrix[2])
                .as("normalized matrix")
                .containsExactly(correctNormalizedMatrix[2], withPrecision(0.0001));
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
                        new double[][]{{0.7325, 0.6808}, {0.2795, 0.3065,}, {0.3753, 0.2191}}),
                Arguments.of(DecisionProblem.Builder.builder()
                                .withCriteriaTypes(Arrays.asList(CriterionType.MAX, CriterionType.MIN, CriterionType.MIN))
                                .withDecisionMatrix(new double[][]{{0.225, 0.445, 0.875},
                                        {80, 127.7, 80.8},
                                        {1114, 2225, 3336}})
                                .withVariants(threeVariants)
                                .build(),
                        new double[][]{{0.22341, 0.44186, 0.86882},
                                {0.53212, 0.25315, 0.52744},
                                {0.73233, 0.46537, 0.19842}})
        );
    }


}