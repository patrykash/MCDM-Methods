package methods;



class MatrixOperation {

    double[] multiplyMatrixByVector(double[][] matrix, double[] vector) {
        double[] result = new double[vector.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < vector.length; j++) {
                result[i] = result[i]+ matrix[i][j] * vector[j];
            }
        }
        return result;
    }



}
