package methods;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UtilTest {

    @Test
    void shouldAssignRatingsToVariants() {
        double[] ratings = {0.543, 0.435, 0.123, 0.512};
        String[] variants = {"variant1", "variant2", "variant3", "variant4"};
        List<ResultRank> expectedResultRank = Arrays.asList(
                new ResultRank("variant1", 0.543),
                new ResultRank("variant2", 0.435),
                new ResultRank("variant3", 0.123),
                new ResultRank("variant4", 0.512)
                );

        List<ResultRank> resultRanks = Util.assignRatingsToVariants(ratings, variants);

        assertThat(resultRanks)
                .isEqualTo(expectedResultRank);

    }

}