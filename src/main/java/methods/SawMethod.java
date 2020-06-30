package methods;

import data.Validator;

import java.util.List;


public class SawMethod implements McdmMethod{
    private DecisionProblem decisionProblem;

    private MatrixNormalization matrixNormalization;


    private SawMethod(DecisionProblem decisionProblem, MatrixNormalization matrixNormalization) {
        this.decisionProblem = decisionProblem;
        this.matrixNormalization = matrixNormalization;
    }

    public List<ResultRank> calculateResult() throws ValidationException {
        Validator validator = new Validator();
        validator.validate(decisionProblem);
        matrixNormalization.setDecisionProblem(decisionProblem);
        double[][] normalizedMatrix = matrixNormalization.normalizeMatrix();

        double[] rating = MatrixOperation.multiplyMatrixByVector(normalizedMatrix, decisionProblem.getWeightsVector());

        return Util.assignRatingsToVariants(rating, decisionProblem.getVariants());

    }

    public static SawMethod createWithMaxMinNormalization(DecisionProblem decisionProblem) {
        return new SawMethod(decisionProblem, new MaxMinNormalization());
    }

    public static SawMethod createWithVectorNormalization(DecisionProblem decisionProblem) {
        return new SawMethod(decisionProblem, new VectorNormalization());
    }





}
