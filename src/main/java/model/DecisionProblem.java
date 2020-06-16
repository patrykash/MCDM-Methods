package model;


public class DecisionProblem {

    private CriterionType[] criteriaTypes;
    private double [] weightVector;
    private String[] variants;
    private double[][] decisionMatrix;

    public DecisionProblem(CriterionType[] criteriaTypes, double[] weightVector, String[] variants, double[][] decisionMatrix) {
        this.criteriaTypes = criteriaTypes;
        this.weightVector = weightVector;
        this.variants = variants;
        this.decisionMatrix = decisionMatrix;
    }

    public CriterionType[] getCriteriaTypes() {
        return criteriaTypes;
    }

    public double[] getWeightVector() {
        return weightVector;
    }

    public String[] getVariants() {
        return variants;
    }

    public double[][] getDecisionMatrix() {
        return decisionMatrix;
    }

}
