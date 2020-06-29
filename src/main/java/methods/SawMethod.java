package methods;

import data.Validator;

import java.util.List;


public class SawMethod implements McdmMethod{
    private DecisionProblem decisionProblem;

    public SawMethod(DecisionProblem decisionProblem) {
        this.decisionProblem = decisionProblem;
    }

    public List<ResultRank> calculateResult() throws ValidationException {
        Validator validator = new Validator();
        validator.validate(decisionProblem);
        MaxMinNormalization maxMinNormalization = new MaxMinNormalization();
        maxMinNormalization.setDecisionProblem(decisionProblem);
        double[][] normalizedMatrix = maxMinNormalization.normalizeMatrix();

        double[] rating = MatrixOperation.multiplyMatrixByVector(normalizedMatrix, decisionProblem.getWeightsVector());

        return Util.assignRatingsToVariants(rating, decisionProblem.getVariants());

    }





}
