package methods;


public class MaxMinNormalization implements MatrixNormalization{

    private DecisionProblem decisionProblem;

    private double[] maxValuesForEachCriteria;

    private double[] minValuesForEachCriteria;

    private double[][] normalizedMatrix;

    public MaxMinNormalization(DecisionProblem decisionProblem) {
        this.decisionProblem = decisionProblem;
        this.normalizedMatrix =
                new double[decisionProblem.getNumberOfCriteria()][decisionProblem.getNumberOfVariants()];

    }

    @Override
    public double[][] normalizeMatrix() {
        int numberOfCriteria = decisionProblem.getNumberOfCriteria();
        maxValuesForEachCriteria = decisionProblem.findMaxValueForEachCriterion();
        minValuesForEachCriteria = decisionProblem.findMinValueForEachCriterion();
        for (int i = 0; i < numberOfCriteria; i++) {
            if (decisionProblem.getCriteriaTypes().get(i) == CriterionType.MAX) {
                   normalizeBenefitCriteria(i);
                } else {
                   normalizeCostCriteria(i);
            }
        }
        return normalizedMatrix;
    }


    private void normalizeBenefitCriteria(int criteriaNumber) {
        double[][] matrixToNormalized = decisionProblem.getDecisionMatrix();
        for (int i = 0; i < decisionProblem.getNumberOfVariants(); i++) {
                normalizedMatrix[criteriaNumber][i] =
                        (matrixToNormalized[criteriaNumber][i] - minValuesForEachCriteria[criteriaNumber]) /
                (maxValuesForEachCriteria[criteriaNumber] - minValuesForEachCriteria[criteriaNumber]);
        }
    }

    private void normalizeCostCriteria(int criteriaNumber) {
        int numberOfVariants = decisionProblem.getNumberOfVariants();
        double[][] matrixToNormalized = decisionProblem.getDecisionMatrix();
        for (int i = 0; i < numberOfVariants; i++) {
            normalizedMatrix[criteriaNumber][i] =
                    ( maxValuesForEachCriteria[criteriaNumber] - matrixToNormalized[criteriaNumber][i]) /
                            (maxValuesForEachCriteria[criteriaNumber] - minValuesForEachCriteria[criteriaNumber]);
        }
    }
}
