package methods;

public class ResultRank implements Comparable<ResultRank> {
    private String variantName;

    private double rating;

    public ResultRank(String variantName, double rating) {
        this.variantName = variantName;
        this.rating = rating;
    }

    public String getVariantName() {
        return variantName;
    }

    public double getRating() {
        return rating;
    }


    @Override
    public int compareTo(ResultRank o) {
        return 0;
    }
}
