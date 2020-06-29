package methods;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class TopsisRatingTest {


    @Test
    void shouldCalculateRatingsForEachVariants() {
        double[] distanceFromPis = {0.1175, 0.0635, 0.0487, 0.1307};
        double[] distanceFromNis = {0.1193, 0.1223, 0.1446, 0.0655};
        TopsisRating topsisRating = new TopsisRating(distanceFromPis, distanceFromNis,4);
        double[] expectedRatings = {0.5038, 0.6582, 0.7481, 0.3338};

        double[] result = topsisRating.calculateRatings();

        assertThat(result)
                .containsExactly(expectedRatings, withPrecision(0.0001));

    }


}