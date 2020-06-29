package methods;

public class VectorNormalization implements MatrixNormalization {
    private DecisionProblem decisionProblem;

    private double[][] normalizedMatrix;
    private double[][] matrixToNormalize;


    @Override
    public void setDecisionProblem(DecisionProblem decisionProblem) {
        this.decisionProblem = decisionProblem;
    }
    @Override
    public double[][] normalizeMatrix() {
        normalizedMatrix = new double[decisionProblem.getNumberOfCriteria()][decisionProblem.getNumberOfVariants()];
        matrixToNormalize = decisionProblem.getDecisionMatrix();
        double[] vectorsLength = calculateVectorsLength(decisionProblem.getDecisionMatrix());
        for (int i = 0; i < decisionProblem.getNumberOfCriteria(); i++) {
            if (decisionProblem.getCriteriaTypes().get(i)==CriterionType.MAX){
                normalizeBenefitCriteria(i,vectorsLength);
            }else{
                normalizeCostCriteria(i, vectorsLength);
            }
        }
        return normalizedMatrix;
    }

    private double[] calculateVectorsLength(double[][] matrixToNormalize){
        double[] vectorsLength = new double[decisionProblem.getNumberOfCriteria()];
        for (int i = 0; i < decisionProblem.getNumberOfCriteria(); i++) {
            for (int j = 0; j < decisionProblem.getNumberOfVariants(); j++) {
                vectorsLength[i] = vectorsLength[i] + Math.pow(matrixToNormalize[i][j], 2);
            }
            vectorsLength[i] = Math.sqrt(vectorsLength[i]);
        }
        return vectorsLength;
    }

    private void normalizeBenefitCriteria(int criterionNumber, double[] vectorsLength){
        for (int j = 0; j < decisionProblem.getNumberOfVariants(); j++) {
            normalizedMatrix[criterionNumber][j] = matrixToNormalize[criterionNumber][j] / vectorsLength[criterionNumber];
        }
    }

    private void normalizeCostCriteria(int criterionNumber, double[] vectorsLength) {
        for (int j = 0; j < decisionProblem.getNumberOfVariants(); j++) {
            normalizedMatrix[criterionNumber][j] = 1 - (matrixToNormalize[criterionNumber][j] / vectorsLength[criterionNumber]);
        }
    }





}
