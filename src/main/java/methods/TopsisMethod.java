package methods;

import data.Validator;

import java.util.List;

public class TopsisMethod implements McdmMethod {
    private DecisionProblem decisionProblem;

    private MatrixNormalization matrixNormalization;

    private TopsisMethod(DecisionProblem decisionProblem, MatrixNormalization normalization) {
        this.decisionProblem = decisionProblem;
        this.matrixNormalization = normalization;
    }

    @Override
    public List<ResultRank> calculateResult() throws ValidationException {
        Validator validator = new Validator();
        validator.validate(decisionProblem);
        matrixNormalization.setDecisionProblem(decisionProblem);
        double[][] normalizedMatrix = matrixNormalization.normalizeMatrix();
        double[][] weightedNormalizedMatrix = MatrixOperation.calculateWeightedMatrix(normalizedMatrix,
                decisionProblem.getWeightsVector());

        double[] pisVector = MatrixOperation.findPisVector(weightedNormalizedMatrix);
        double[] nisVector = MatrixOperation.findNisVector(weightedNormalizedMatrix);

        double[] distanceFromPis = MatrixOperation.calculateEuclideanDistances(weightedNormalizedMatrix, pisVector);
        double[] distanceFromNis = MatrixOperation.calculateEuclideanDistances(weightedNormalizedMatrix, nisVector);

        TopsisRating topsisRating = new TopsisRating(distanceFromPis, distanceFromNis,
                decisionProblem.getNumberOfVariants());
        double[] ratings = topsisRating.calculateRatings();
        return Util.assignRatingsToVariants(ratings, decisionProblem.getVariants());
    }

    public static TopsisMethod createWithMaxMinNormalization(DecisionProblem decisionProblem) {
        return  new TopsisMethod(decisionProblem, new MaxMinNormalization());
    }

    public static TopsisMethod createWithVetorNormalization(DecisionProblem decisionProblem) {
        return  new TopsisMethod(decisionProblem, new VectorNormalization());
    }





}
