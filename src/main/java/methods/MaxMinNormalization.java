package methods;


public class MaxMinNormalization implements MatrixNormalization{

    private DecisionProblem decisionProblem;

    private double[] maxValuesForEachCriteria;

    private double[] minValuesForEachCriteria;

    private double[][] normalizedMatrix;

    private int numberOfVariants;

    private  int  numberOfCriteria;

    public void setDecisionProblem(DecisionProblem decisionProblem) {
        this.decisionProblem = decisionProblem;
    }

    @Override
    public double[][] normalizeMatrix() {
        setData();
        for (int i = 0; i < numberOfCriteria; i++) {
            if (decisionProblem.getCriteriaTypes().get(i) == CriterionType.MAX) {
                   normalizeBenefitCriteria(i);
                } else {
                   normalizeCostCriteria(i);
            }
        }
        return normalizedMatrix;
    }

    private void setData() {
        numberOfVariants = decisionProblem.getNumberOfVariants();
        numberOfCriteria = decisionProblem.getNumberOfCriteria();
        normalizedMatrix = new double[numberOfCriteria][numberOfVariants];
        maxValuesForEachCriteria = decisionProblem.findMaxValueForEachCriterion();
        minValuesForEachCriteria = decisionProblem.findMinValueForEachCriterion();
    }


    private void normalizeBenefitCriteria(int criteriaNumber) {
        double[][] matrixToNormalized = decisionProblem.getDecisionMatrix();
        for (int i = 0; i < numberOfVariants; i++) {
                normalizedMatrix[criteriaNumber][i] =
                        (matrixToNormalized[criteriaNumber][i] - minValuesForEachCriteria[criteriaNumber]) /
                (maxValuesForEachCriteria[criteriaNumber] - minValuesForEachCriteria[criteriaNumber]);
        }
    }

    private void normalizeCostCriteria(int criteriaNumber) {
        double[][] matrixToNormalized = decisionProblem.getDecisionMatrix();
        for (int i = 0; i < numberOfVariants; i++) {
            normalizedMatrix[criteriaNumber][i] =
                    ( maxValuesForEachCriteria[criteriaNumber] - matrixToNormalized[criteriaNumber][i]) /
                            (maxValuesForEachCriteria[criteriaNumber] - minValuesForEachCriteria[criteriaNumber]);
        }
    }
}
