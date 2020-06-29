package methods;

import data.Validator;

import java.util.List;

public class TopsisMethod implements McdmMethod {
    private DecisionProblem decisionProblem;

    public TopsisMethod(DecisionProblem decisionProblem) {
        this.decisionProblem = decisionProblem;
    }

    @Override
    public List<ResultRank> calculateResult() throws ValidationException {
        Validator validator = new Validator();
        validator.validate(decisionProblem);
        VectorNormalization vectorNormalization = new VectorNormalization();
        vectorNormalization.setDecisionProblem(decisionProblem);
        double[][] normalizedMatrix = vectorNormalization.normalizeMatrix();
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


}
