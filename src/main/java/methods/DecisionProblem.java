package methods;


import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class DecisionProblem {

    private List<CriterionType> criteriaTypes;
    private double[] weightsVector;
    private String[] variants;
    private double[][] decisionMatrix;


    public DecisionProblem(List<CriterionType> criteriaTypes, double[] weightsVector, String[] variants, double[][] decisionMatrix) {
        this.criteriaTypes = criteriaTypes;
        this.weightsVector = weightsVector;
        this.variants = variants;
        this.decisionMatrix = decisionMatrix;
    }

    public List<CriterionType> getCriteriaTypes() {
        return criteriaTypes;
    }

    public double[] getWeightsVector() {
        return weightsVector;
    }

    public String[] getVariants() {
        return variants;
    }

    public double[][] getDecisionMatrix() {
        return decisionMatrix;
    }


     int getNumberOfCriteria() {
        return decisionMatrix.length;
    }

     int getNumberOfVariants() {
        return variants.length;
    }

     double[] findMaxValueForEachCriterion() {
        /*int numberOfCriteria = getNumberOfCriteria();
        double[] maxValues = new double[numberOfCriteria];
        for (int i = 0; i < numberOfCriteria; i++) {
            maxValues[i] = Arrays.stream(this.decisionMatrix[i])
                    .max()
                    .orElseThrow(()->
                            new NoSuchElementException("messagae"));
        }*/
        return MatrixOperation.findPisVector(decisionMatrix);
    }

    double[] findMinValueForEachCriterion() {
        return MatrixOperation.findNisVector(decisionMatrix);
    }

    public static final class Builder {
        private List<CriterionType> criteriaTypes;
        private double[] weightsVector;
        private String[] variants;
        private double[][] decisionMatrix;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder withCriteriaTypes(List<CriterionType> criteriaTypes) {
            this.criteriaTypes = criteriaTypes;
            return this;
        }

        public Builder withWeightsVector(double[] weightsVector) {
            this.weightsVector = weightsVector;
            return this;
        }

        public Builder withVariants(String[] variants) {
            this.variants = variants;
            return this;
        }

        public Builder withDecisionMatrix(double[][] decisionMatrix) {
            this.decisionMatrix = decisionMatrix;
            return this;
        }

        public DecisionProblem build() {
            return new DecisionProblem(criteriaTypes, weightsVector, variants, decisionMatrix);
        }
    }
}
