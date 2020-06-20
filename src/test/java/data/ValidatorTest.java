package data;

import model.CriterionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = new Validator();
    }


    @ParameterizedTest
    @MethodSource
    void shouldNotAcceptIncorrectVariants(String[] incorrectVariants, String expectedMessage) {
        boolean isCorrect = validator.isEachVariantsCorrect(incorrectVariants);
        List<String> messages = validator.getErrorMessages();


        assertThat(isCorrect)
                .as("are variants correct")
                .isFalse();
        assertThat(messages).containsOnlyOnce(expectedMessage);

    }

    static Stream<Arguments> shouldNotAcceptIncorrectVariants() {
        return Stream.of(
                Arguments.of(null, "Variants array can't be null"),
                Arguments.of(new String[]{}, "Variants array can't be empty"),
                Arguments.of(new String[]{"variant1", "variant2", null}, "Variant can't be null"),
                Arguments.of(new String[]{"variant1", "variant2", ""}, "Variant can't be empty")

        );
    }

    @Test
    void shouldAcceptCorrectVariants() {
        String[] correctVariants = {"variant1", "variant2", "variant3"};
        boolean isCorrect = validator.isEachVariantsCorrect(correctVariants);
        List<String> messages = validator.getErrorMessages();

        assertThat(isCorrect)
                .as("are variants correct")
                .isTrue();
        assertThat(messages).isEmpty();
    }

    @ParameterizedTest
    @MethodSource
    void shouldNotAcceptIncorrectDecisionMatrix(double[][] incorrectDecisionMatrix, String expectedMessage) {
        boolean isCorrect = validator.isDecisionMatrixCorrect(incorrectDecisionMatrix);
        List<String> messages = validator.getErrorMessages();

        assertThat(isCorrect)
                .as("is decision matrix correct")
                .isFalse();
        assertThat(messages).containsOnlyOnce(expectedMessage);
    }

    static Stream<Arguments> shouldNotAcceptIncorrectDecisionMatrix() {
        double[][] decisionMatrixWithNull = {{1.52, 1.0,}, null};
        double[][] decisionMatrixWithEmptyValue = {{1.52, 1.0,}, {}};

        return Stream.of(
                Arguments.of(null, "Decision matrix can't be null"),
                Arguments.of(new double[][]{}, "Decision matrix can't be empty"),
                Arguments.of(decisionMatrixWithNull, "Decision matrix can't have null"),
                Arguments.of(decisionMatrixWithEmptyValue, "Decision matrix can't have empty value")
        );
    }


    @Test
    void shouldAcceptCorrectDecisionMatrix() {
        double[][] correctDecisionMatrix = {{1.2, 3.2, 43.0}, {3.2, 2.2, 30.1}};

        boolean isCorrect = validator.isDecisionMatrixCorrect(correctDecisionMatrix);
        List<String> messages = validator.getErrorMessages();

        assertThat(isCorrect)
                .as("is decision matrix correct")
                .isTrue();
        assertThat(messages).isEmpty();
    }

    @ParameterizedTest
    @MethodSource
    void shouldAcceptCorrectWeightVector(double[] correctWeightsVector) {

        boolean isCorrect = validator.isWeightsVectorCorrect(correctWeightsVector);

        assertThat(isCorrect)
                .as("is weight vector correct")
                .isTrue();
    }

    static Stream<double[]> shouldAcceptCorrectWeightVector() {
        return Stream.of(
                new double[]{0.1, 0.3, 0.6},
                new double[]{0.2, 0.3, 0.1, 0.05, 0.05, 0.3}
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldNotAcceptIncorrectWeightVector(double[] incorrectWeightsVector, String expectedMessage) {
        boolean isCorrect = validator.isWeightsVectorCorrect(incorrectWeightsVector);
        List<String> message = validator.getErrorMessages();

        assertThat(isCorrect)
                .as("is weights vector correct")
                .isFalse();
        assertThat(message).containsOnlyOnce(expectedMessage);
    }

    static Stream<Arguments> shouldNotAcceptIncorrectWeightVector() {
        return Stream.of(
                Arguments.of(null, "Weights vector can't be null"),
                Arguments.of(new double[]{}, "Weights vector can't be empty"),
                Arguments.of(new double[]{0.3, 0.2, 0.1}, "Weights vector sum isn't equals 1"),
                Arguments.of(new double[]{0.2, 0.2, 0.1, 0.05}, "Weights vector sum isn't equals 1")
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldAcceptCorrectCriteriaTypes(List<CriterionType> criteriaTypes) {
        boolean isCorrect = validator.isCriteriaTypesCorrect(criteriaTypes);
        List<String> messages = validator.getErrorMessages();

        assertThat(isCorrect)
                .as("are criteria types correct")
                .isTrue();
        assertThat(messages).isEmpty();
    }

    static Stream<List<CriterionType>> shouldAcceptCorrectCriteriaTypes() {
        return Stream.of(
                Arrays.asList(CriterionType.MAX, CriterionType.MIN, CriterionType.MAX),
                Arrays.asList(CriterionType.MAX, CriterionType.MIN, CriterionType.MAX,CriterionType.MAX)
                );
    }

    @ParameterizedTest
    @MethodSource
    void shouldNotAcceptIncorrectCriteriaTypes(List<CriterionType> criteriaTypes, String expectedMessage) {
        boolean isCorrect = validator.isCriteriaTypesCorrect(criteriaTypes);
        List<String> messages = validator.getErrorMessages();


        assertThat(isCorrect)
                .as("are criteria types correct")
                .isFalse();
        assertThat(messages).containsOnlyOnce(expectedMessage);

    }

    static Stream<Arguments> shouldNotAcceptIncorrectCriteriaTypes() {
        return Stream.of(
                Arguments.of(null, "Criteria types can't be null"),
                Arguments.of(Collections.emptyList(), "Criteria types can't be empty"),
                Arguments.of(Arrays.asList(null, CriterionType.MIN, CriterionType.MIN), "Criterion type can't be null")
        );
    }
    }