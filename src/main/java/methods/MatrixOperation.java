package methods;



class MatrixOperation {

    static double[] multiplyMatrixByVector(double[][] matrix, double[] vector) {
        int numberOfCriteria= vector.length;
        int numberOfVariants = matrix[0].length;
        double[] result = new double[numberOfVariants];
        for (int i = 0; i < numberOfVariants; i++) {
            for (int j = 0; j < numberOfCriteria; j++) {
                result[i] = result[i]+ matrix[j][i] * vector[j];
            }
        }
        return result;
    }

    static double[][] calculateWeightedMatrix(double[][] matrix, double[] weightsVector) {
        double[][] weightedMatrix = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < weightsVector.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                weightedMatrix[i][j] = matrix[i][j] * weightsVector[i];
            }
        }
        return weightedMatrix;
    }



}
