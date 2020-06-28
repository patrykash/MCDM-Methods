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
        return Double.compare(this.rating, o.getRating());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultRank)) return false;

        ResultRank that = (ResultRank) o;

        if (Double.compare(that.rating, rating) != 0) return false;
        return variantName.equals(that.variantName);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = variantName.hashCode();
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
