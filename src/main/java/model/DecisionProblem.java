package model;


import java.util.List;

public class DecisionProblem {

    private List<CriterionType> criteriaTypes;
    private double [] weightsVector;
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

}
