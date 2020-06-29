package methods;


import java.util.Arrays;
import java.util.NoSuchElementException;

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

    //NIS - negative ideal solution, min value for each criterion
    static double[] findNisVector(double[][] matrix) {
        int numberOfCriteria = matrix.length;
        double[] nisVector = new double[numberOfCriteria];
        for (int i = 0; i < numberOfCriteria; i++) {
            nisVector[i] = Arrays.stream(matrix[i])
                    .min()
                    .orElseThrow(NoSuchElementException::new);
        }
        return nisVector;
    }

    //PIS - positive ideal solution, max value for each criterion
    static double[] findPisVector(double[][] matrix) {
        int numberOfCriteria = matrix.length;
        double[] nisVector = new double[numberOfCriteria];
        for (int i = 0; i < numberOfCriteria; i++) {
            nisVector[i] = Arrays.stream(matrix[i])
                    .max()
                    .orElseThrow(NoSuchElementException::new);
        }
        return nisVector;
    }

    static double[] calculateEuclideanDistances(double[][] matrix, double[] vector) {
        int numberOfCriteria = matrix.length;
        int numberOfVariants = matrix[0].length;
        double[] euclideanDistances = new double[numberOfVariants];
        for (int i = 0; i < numberOfVariants; i++) {
            for (int j = 0; j < numberOfCriteria; j++) {
                euclideanDistances[i] =euclideanDistances[i] +  Math.pow(matrix[j][i] - vector[j],2);
            }
            euclideanDistances[i] = Math.sqrt(euclideanDistances[i]);
        }
        return euclideanDistances;
    }



}
