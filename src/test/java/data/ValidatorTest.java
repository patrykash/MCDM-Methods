package data;

import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.Argument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;


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
    void shouldNotAcceptIncorrectVariants(String[] incorrectVariants, String expectedMessage)
    {
        boolean isCorrect = validator.isEachVariantsCorrect(incorrectVariants);
        List<String> messages = validator.getErrorMessages();


        assertThat(isCorrect)
                .as("are variants correct")
                .isFalse();
        assertThat(messages).containsOnlyOnce(expectedMessage);

    }

    static Stream<Arguments> shouldNotAcceptIncorrectVariants() {
      return Stream.of(
              Arguments.of(null,"Variants array can't be null"),
              Arguments.of(new String[]{},"Variants array can't be empty"),
              Arguments.of(new String[]{"variant1", "variant2",null},"Variant can't be null"),
              Arguments.of(new String[]{"variant1", "variant2",""},"Variant can't be empty")

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

}