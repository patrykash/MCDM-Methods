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



}
