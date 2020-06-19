package model;


public class DecisionProblem {

    private CriterionType[] criteriaTypes;
    private double [] weightsVector;
    private String[] variants;
    private double[][] decisionMatrix;

    public DecisionProblem(CriterionType[] criteriaTypes, double[] weightsVector, String[] variants, double[][] decisionMatrix) {
        this.criteriaTypes = criteriaTypes;
        this.weightsVector = weightsVector;
        this.variants = variants;
        this.decisionMatrix = decisionMatrix;
    }

    public CriterionType[] getCriteriaTypes() {
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

}
