package methods;

import java.util.List;

public class VectorNormalization implements MatrixNormalization {
    private DecisionProblem decisionProblem;

    private double[][] normalizedMatrix;
    private double[][] matrixToNormalize;
    private double[] vectorsLength;
    private int numberOfCriteria;
    private int numberOfVariants;


    @Override
    public void setDecisionProblem(DecisionProblem decisionProblem) {
        this.decisionProblem = decisionProblem;
    }
    @Override
    public double[][] normalizeMatrix() {
        setData();
        List<CriterionType> criteriaTypes = decisionProblem.getCriteriaTypes();
        for (int i = 0; i < numberOfCriteria; i++) {
            if (criteriaTypes.get(i)==CriterionType.MAX){
                normalizeBenefitCriteria(i);
            }else{
                normalizeCostCriteria(i);
            }
        }
        return normalizedMatrix;
    }

    private void setData(){
        numberOfCriteria = decisionProblem.getNumberOfCriteria();
        numberOfVariants = decisionProblem.getNumberOfVariants();
        normalizedMatrix = new double[numberOfCriteria][numberOfVariants];
        matrixToNormalize = decisionProblem.getDecisionMatrix();
        vectorsLength = calculateVectorsLength(decisionProblem.getDecisionMatrix());
    }

    private double[] calculateVectorsLength(double[][] matrixToNormalize){
        double[] vectorsLength = new double[numberOfCriteria];
        for (int i = 0; i < numberOfCriteria; i++) {
            for (int j = 0; j < numberOfVariants; j++) {
                vectorsLength[i] = vectorsLength[i] + Math.pow(matrixToNormalize[i][j], 2);
            }
            vectorsLength[i] = Math.sqrt(vectorsLength[i]);
        }
        return vectorsLength;
    }

    private void normalizeBenefitCriteria(int criterionNumber){
        for (int j = 0; j < numberOfVariants; j++) {
            normalizedMatrix[criterionNumber][j] = matrixToNormalize[criterionNumber][j] / vectorsLength[criterionNumber];
        }
    }

    private void normalizeCostCriteria(int criterionNumber) {
        for (int j = 0; j < numberOfVariants; j++) {
            normalizedMatrix[criterionNumber][j] = 1 - (matrixToNormalize[criterionNumber][j] / vectorsLength[criterionNumber]);
        }
    }





}
