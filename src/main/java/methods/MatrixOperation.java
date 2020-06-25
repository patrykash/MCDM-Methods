package methods;



class MatrixOperation {

    double[] multiplyMatrixByVector(double[][] matrix, double[] vector) {
        int vectorSize= vector.length;
        int matrixSize = matrix.length;
        double[] result = new double[vectorSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < vectorSize; j++) {
                result[i] = result[i]+ matrix[i][j] * vector[j];
            }
        }
        return result;
    }



}
