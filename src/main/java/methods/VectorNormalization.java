package methods;

import java.util.List;

public class VectorNormalization implements MatrixNormalization {
    private DecisionProblem decisionProblem;



    @Override
    public void setDecisionProblem(DecisionProblem decisionProblem) {
        this.decisionProblem = decisionProblem;
    }
    @Override
    public double[][] normalizeMatrix() {
        double[][] normalizedMatrix = new double[decisionProblem.getNumberOfCriteria()][decisionProblem.getNumberOfVariants()];
        double[] vectorsLength = calculateVectorsLength();
        double[][] matrixToNormalize = decisionProblem.getDecisionMatrix();
        for (int i = 0; i < decisionProblem.getNumberOfCriteria(); i++) {
            for (int j = 0; j < decisionProblem.getNumberOfVariants(); j++) {
                if (decisionProblem.getCriteriaTypes().get(i)==CriterionType.MAX){
                    normalizedMatrix[i][j] = matrixToNormalize[i][j] / vectorsLength[i];
                } else
                    normalizedMatrix[i][j] =1 - (matrixToNormalize[i][j] / vectorsLength[i]);

            }
        }

        return normalizedMatrix;
    }

    private double[] calculateVectorsLength(){
        double[][] matrixToNormalize = decisionProblem.getDecisionMatrix();
        double[] vectorsLength = new double[decisionProblem.getNumberOfCriteria()];
        for (int i = 0; i < decisionProblem.getNumberOfCriteria(); i++) {
            for (int j = 0; j < decisionProblem.getNumberOfVariants(); j++) {
                vectorsLength[i] = vectorsLength[i] + Math.pow(matrixToNormalize[i][j], 2);
            }
            vectorsLength[i] = Math.sqrt(vectorsLength[i]);
        }
        return vectorsLength;
    }





}
