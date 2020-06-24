package model;


import java.util.List;

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
