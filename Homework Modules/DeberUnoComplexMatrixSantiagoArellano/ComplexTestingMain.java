package DeberUnoComplexMatrixSantiagoArellano;

/**=============================================
 * ?                Data
 * 1. Nombre: Santiago Arellano [00328370]
 * 2. Clase: Data Structures And Algorithms
 * 3. Descripcion: La presente interface, introduce las funciones necesarias para una clase base de numeros complejos
 requeridos por el ejercicio 19.11 basado en 13.17
 ============================================**/

import java.math.BigDecimal;

public class ComplexTestingMain {

    public static void main(String[] args) {

        ComplexMatrix complexMatrix = new ComplexMatrix();
        System.out.printf("%90s\n", "Test Set: Complex Matrix and Complex");
        System.out.println("ComplexMatrix Test [1] - Suma de Matrices");
        Complex[][] matrix1 = {
                { new Complex(new BigDecimal("1.0"), new BigDecimal("2.0")), new Complex(new BigDecimal("3.0"), new BigDecimal("4.0")) },
                { new Complex(new BigDecimal("5.0"), new BigDecimal("6.0")), new Complex(new BigDecimal("7.0"), new BigDecimal("8.0")) }
        };
        Complex[][] matrix2 = {
                { new Complex(new BigDecimal("8.0"), new BigDecimal("7.0")), new Complex(new BigDecimal("6.0"), new BigDecimal("5.0")) },
                { new Complex(new BigDecimal("4.0"), new BigDecimal("3.0")), new Complex(new BigDecimal("2.0"), new BigDecimal("1.0")) }
        };
        Complex[][] matrix3 = {
                { new Complex(new BigDecimal("10.5"), new BigDecimal("20.7")), new Complex(new BigDecimal("30.8"), new BigDecimal("-40.9")) },
                { new Complex(new BigDecimal("-50.1"), new BigDecimal("60.2")), new Complex(new BigDecimal("70.3"), new BigDecimal("80.4")) }
        };
        Complex[][] matrix4 = {
                { new Complex(new BigDecimal("18.9"), new BigDecimal("27.6")), new Complex(new BigDecimal("35.2"), new BigDecimal("48.3")) },
                { new Complex(new BigDecimal("55.5"), new BigDecimal("63.1")), new Complex(new BigDecimal("-72.8"), new BigDecimal("89.4")) }
        };
        Complex[][] matrix5 = {
                { new Complex(new BigDecimal("19.7"), new BigDecimal("28.4")), new Complex(new BigDecimal("36.9"), new BigDecimal("45.2")) },
                { new Complex(new BigDecimal("56.3"), new BigDecimal("64.6")), new Complex(new BigDecimal("73.7"), new BigDecimal("80.5")) }
        };
        Complex[][] matrix6 = {
                { new Complex(new BigDecimal("22.3"), new BigDecimal("33.9")), new Complex(new BigDecimal("44.8"), new BigDecimal("57.1")) },
                { new Complex(new BigDecimal("60.5"), new BigDecimal("77.2")), new Complex(new BigDecimal("82.6"), new BigDecimal("-98.3")) }
        };
        Complex[][] matrix7 = {
                { new Complex(new BigDecimal("25.7"), new BigDecimal("-35.6")), new Complex(new BigDecimal("46.5"), new BigDecimal("59.4")) },
                { new Complex(new BigDecimal("65.7"), new BigDecimal("72.3")), new Complex(new BigDecimal("83.5"), new BigDecimal("91.7"))}
        };
        Complex[][] matrix8 = {
                { new Complex(new BigDecimal("27.5"), new BigDecimal("39.8")), new Complex(new BigDecimal("48.1"), new BigDecimal("69.2")) },
                { new Complex(new BigDecimal("-75.3"), new BigDecimal("88.4")), new Complex(new BigDecimal("92.1"), new BigDecimal("107.5")) }
        };
        Complex[][] matrix9 = {
                { new Complex(new BigDecimal("32.1"), new BigDecimal("42.5")), new Complex(new BigDecimal("-49.2"), new BigDecimal("-65.8")) },
                { new Complex(new BigDecimal("78.9"), new BigDecimal("90.2")), new Complex(new BigDecimal("102.3"), new BigDecimal("115.6")) }
        };
        Complex[][] matrix10 = {
                { new Complex(new BigDecimal("35.0"), new BigDecimal("47.1")), new Complex(new BigDecimal("53.9"), new BigDecimal("66.6")) },
                { new Complex(new BigDecimal("81.7"), new BigDecimal("93.8")), new Complex(new BigDecimal("104.7"), new BigDecimal("119.4")) }
        };
        Complex[][] matrix11 = {
                { new Complex(new BigDecimal("38.8"), new BigDecimal("52.3")), new Complex(new BigDecimal("56.9"), new BigDecimal("68.2")) },
                { new Complex(new BigDecimal("85.3"), new BigDecimal("97.5")), new Complex(new BigDecimal("108.6"), new BigDecimal("121.9")) }
        };
        Complex[][] matrix12 = {
                { new Complex(new BigDecimal("41.7"), new BigDecimal("54.9")), new Complex(new BigDecimal("62.7"), new BigDecimal("70.3")) },
                { new Complex(new BigDecimal("89.4"), new BigDecimal("98.2")), new Complex(new BigDecimal("111.7"), new BigDecimal("124.5")) }
        };

        Complex[][] resultAdd = complexMatrix.addMatrix(matrix1, matrix2);
        System.out.println("Matrix 1 + Matrix 2 = ");
        GenericMatrix.printResult(matrix1, matrix2, resultAdd, '+');

        Complex[][] resultAdd2 = complexMatrix.addMatrix(matrix3, matrix4);
        System.out.println("Matrix 3 + Matrix 4 = ");
        GenericMatrix.printResult(matrix3, matrix4, resultAdd2, '+');

        Complex[][] resultAdd3 = complexMatrix.addMatrix(matrix5, matrix6);
        System.out.println("Matrix 5 + Matrix 6 = ");
        GenericMatrix.printResult(matrix5, matrix6, resultAdd3, '+');

        Complex[][] resultAdd4 = complexMatrix.addMatrix(matrix7, matrix8);
        System.out.println("Matrix 7 + Matrix 8 = ");
        GenericMatrix.printResult(matrix7, matrix8, resultAdd4, '+');

        Complex[][] resultAdd5 = complexMatrix.addMatrix(matrix9, matrix10);
        System.out.println("Matrix 9 + Matrix 10 = ");
        GenericMatrix.printResult(matrix9, matrix10, resultAdd5, '+');

        Complex[][] resultAdd6 = complexMatrix.addMatrix(matrix11, matrix12);
        System.out.println("Matrix 11 + Matrix 12 = ");
        GenericMatrix.printResult(matrix11, matrix12, resultAdd6, '+');

        // Demonstrate matrix multiplication
        System.out.println("ComplexMatrix Test [2] - Multiplicacion de Matrices");

        Complex[][] resultMultiply = complexMatrix.multiplyMatrix(matrix1, matrix2);
        System.out.println("Matrix 1 * Matrix 2 = ");
        GenericMatrix.printResult(matrix1, matrix2, resultMultiply, '*');

        Complex[][] resultMultiply2 = complexMatrix.multiplyMatrix(matrix3, matrix4);
        System.out.println("Matrix 3 * Matrix 4 = ");
        GenericMatrix.printResult(matrix3, matrix4, resultMultiply2, '*');

        Complex[][] resultMultiply3 = complexMatrix.multiplyMatrix(matrix5, matrix6);
        System.out.println("Matrix 5 * Matrix 6 = ");
        GenericMatrix.printResult(matrix5, matrix6, resultMultiply3, '*');

        Complex[][] resultMultiply4 = complexMatrix.multiplyMatrix(matrix7, matrix8);
        System.out.println("Matrix 7 * Matrix 8 = ");
        GenericMatrix.printResult(matrix7, matrix8, resultMultiply4, '*');

        Complex[][] resultMultiply5 = complexMatrix.multiplyMatrix(matrix9, matrix10);
        System.out.println("Matrix 9 * Matrix 10 = ");
        GenericMatrix.printResult(matrix9, matrix10, resultMultiply5, '*');

        Complex[][] resultMultiply6 = complexMatrix.multiplyMatrix(matrix11, matrix12);
        System.out.println("Matrix 11 * Matrix 12 = ");
        GenericMatrix.printResult(matrix11, matrix12, resultMultiply6, '*');

        System.out.println();
        System.out.println("ComplexMatrix Test [3] - Suma, Multiplicacion, Resta y Division de Complex");
        Complex complexBase1 = new Complex(new BigDecimal("1.0"), new BigDecimal("2.0"));
        Complex complexBase2 = new Complex(new BigDecimal("3.0"), new BigDecimal("4.0"));
        Complex complexBase3 = new Complex(new BigDecimal("5.0"), new BigDecimal("6.0"));
        System.out.println("Complex 1: " + complexBase1);
        System.out.println("Complex 2: " + complexBase2);
        System.out.println("Complex 3: " + complexBase3);
        System.out.println();
        complexBase1.add(complexBase2);
        complexBase3.add(complexBase2);
        System.out.println("Complex 1 + Complex 2 = " + complexBase1.toString());
        System.out.println("Complex 1 + Complex 2 = " + complexBase3.toString());
        complexBase1.subtract(complexBase2);
        complexBase3.subtract(complexBase2);
        System.out.println("Complex 1 - Complex 2 = " + complexBase1.toString());
        System.out.println("Complex 3 - Complex 2 = " + complexBase3.toString());
        System.out.println();
        complexBase3.multiply(complexBase1);
        System.out.println("Complex 1 * Complex 3 = " + complexBase3.toString());
        complexBase1.multiply(complexBase2);
        System.out.println("Complex 1 * Complex 2 = " + complexBase1.toString());
        complexBase3.divide(complexBase2);
        System.out.println("Complex 3 / Complex 2 = " + complexBase3.toString());



    }
}
