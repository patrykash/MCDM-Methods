package methods;

public class ResultRank {
    private String variantName;

    private double result;

    public ResultRank(String variantName, double result) {
        this.variantName = variantName;
        this.result = result;
    }

    public String getVariantName() {
        return variantName;
    }

    public double getResult() {
        return result;
    }


}
