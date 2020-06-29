package methods;

class TopsisRating {

    private double[] distanceFromPis;
    private double[] distanceFromNis;
    private int numberOfVariants;

    TopsisRating(double[] distanceFromPis, double[] distanceFromNis, int numberOfVariants) {
        this.distanceFromPis = distanceFromPis;
        this.distanceFromNis = distanceFromNis;
        this.numberOfVariants = numberOfVariants;
    }

    double[] calculateRatings() {
        double[] ratings = new double[numberOfVariants];
        for (int i = 0; i < numberOfVariants; i++) {
            ratings[i] = distanceFromNis[i] / (distanceFromPis[i] + distanceFromNis[i]);
        }
        return ratings;
    }
}
