package DeberUnoComplexMatrixSantiagoArellano;

/**=============================================
 * ?                Data
 * 1. Nombre: Santiago Arellano [00328370]
 * 2. Clase: Data Structures And Algorithms
 * 3. Descripcion: La presente interface, introduce las funciones necesarias para una clase base de numeros complejos
 requeridos por el ejercicio 19.11 basado en 13.17
 ============================================**/

public class ComplexMatrix extends GenericMatrix<Complex> {
    /**
     * Abstract method for adding two elements of the matrices
     *
     * @param o1
     * @param o2
     */
    @Override
    protected Complex add(Complex o1, Complex o2) {
        Complex internalCopy = o1.clone();
        internalCopy.add(o2);
        return internalCopy;
    }

    /**
     * Abstract method for multiplying two elements of the matrices
     *
     * @param o1
     * @param o2
     */
    @Override
    protected Complex multiply(Complex o1, Complex o2) {
        Complex internalCopy = o1.clone();
        internalCopy.multiply(o2);
        return internalCopy;
    }

    /**
     * Abstract method for defining zero for the matrix element
     */
    @Override
    protected Complex zero() {
        return new Complex();
    }

    @Override
    public Complex[][] addMatrix(Complex[][] matrix1, Complex[][] matrix2) {
        // Check bounds of the two matrices
        if ((matrix1.length != matrix2.length) ||
                (matrix1[0].length != matrix2[0].length)) {
            throw new RuntimeException(
                    "The matrices do not have the same size");
        }

        Complex[][] result =
               new Complex[matrix1.length][matrix1[0].length];

        // Perform addition
        for (int i = 0; i < result.length; i++)
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = add(matrix1[i][j], matrix2[i][j]);
            }

        return result;
    }

    @Override
    public Complex[][] multiplyMatrix(Complex[][] matrix1, Complex[][] matrix2) {
        // Check bounds
        if (matrix1[0].length != matrix2.length) {
            throw new RuntimeException(
                    "The matrices do not have compatible size");
        }

        // Create result matrix
        Complex[][] result =
                new Complex[matrix1.length][matrix2[0].length];

        // Perform multiplication of two matrices
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = zero();

                for (int k = 0; k < matrix1[0].length; k++) {
                    result[i][j] = add(result[i][j],
                            multiply(matrix1[i][k], matrix2[k][j]));
                }
            }
        }

        return result;
    }
}
