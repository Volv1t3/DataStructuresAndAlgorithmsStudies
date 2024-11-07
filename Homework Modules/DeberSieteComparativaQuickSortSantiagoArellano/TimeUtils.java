package DeberSieteComparativaQuickSortSantiagoArellano;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * @author : Santiago Arellano
 * @Date: Octubre 24, 2024
 * @Description: El presente archivo contiene metodos estaticos que facilitan el analisis de los algoritmos basados en
 * constantes internas estaticas que fueron obtenidas de la declaracion del problema 23.13. Las funciones presenten
 * facilitan el analisis de los algoritmos para encontrar su tiempo de ejecucion en un formato de String numerica trabajable
 * en una impresion de tabla.
 */
public class TimeUtils {


    /**
     * <p>Este metodo ejecuta un algoritmo en un arreglo de enteros de un tamano especificado y mide el tiempo de ejecucion.</p>
     * <br>
     * <p>Internamente, este metodo realiza las siguientes tareas:<br>
     *    1. Valida que los parametros de entrada no sean nulos y que cumplan con las restricciones de tamano del arreglo y nombre del algoritmo.<br>
     *    2. Registra el tiempo antes de ejecutar el algoritmo.<br>
     *    3. Ejecuta el algoritmo utilizando la referencia proporcionada y un arreglo de enteros generados secuencialmente desde 0 hasta arrayTestSize.<br>
     *    4. Registra el tiempo despues de ejecutar el algoritmo.<br>
     *    5. Calcula la duracion de la ejecucion del algoritmo en nanosegundos.<br>
     *    7. Formatea el tiempo en segundos en notacion cientifica.<br>
     *    6. Convierte la duracion a segundos, redondeando a nueve decimales.<br>
     *    8. Retorna un entry de Map con el nombre del algoritmo y el tiempo de ejecucion formateado.<br></p>
     * @param arrayTestSize Tamano del arreglo de enteros a usar en la prueba del algoritmo. Debe ser mayor a 1.
     * @param algorithmName Nombre del algoritmo que se va a ejecutar. No puede estar vacio.
     * @param algorithmReference Referencia al consumidor que ejecuta el algoritmo sobre el arreglo de enteros.
     * @return Un entry de Map con el nombre del algoritmo y el tiempo de ejecucion en segundos, en formato cientifico.
     * @throws NullPointerException Si alguno de los parametros es nulo.
     * @throws IllegalArgumentException Si el tamano del arreglo es menor o igual a 1, o si el nombre del algoritmo esta vacio.
     *
     *
     */
    public static Map.Entry<String, String> executeAnAlgorithm(Integer arrayTestSize,
                                                                                          String algorithmName,
                                                                                          Consumer<Integer[]> algorithmReference)
            throws NullPointerException,  IllegalArgumentException{
        if (arrayTestSize == null || algorithmName == null || algorithmReference == null){
            throw new NullPointerException("Error Code 0x001 - [Raised] Uno de los valores ingresados fue nulo, favor revisar ingresos.");
        }
        if (arrayTestSize <= 1){
            throw new IllegalArgumentException("Error Code 0x001 - [Raised] El tamano del arreglo debe ser mayor a 1");
        }
        if (algorithmName.isEmpty()){
            throw new IllegalArgumentException("Error Code 0x001 - [Raised] El nombre del algoritmo no puede estar vacio");
        }

        //! Realizamos el analysis de tiempo anterior
        Long timeBefore = System.nanoTime();
        Integer[] array = IntStream.rangeClosed(0,arrayTestSize).boxed().toArray(Integer[]::new);
        Collections.shuffle(Arrays.asList((array)));
//        //! DEBUG
//        System.err.println(Arrays.stream(array).limit(10).toList());
        algorithmReference.accept(  array);
        Long timeAfter = System.nanoTime();
        String formattedTime = getFormattedTime(timeAfter, timeBefore);
        return Map.entry(algorithmName, formattedTime.toString());
    }

    private static String getFormattedTime(Long timeAfter, Long timeBefore) {
        Long duration = timeAfter - timeBefore;
//        //! DEBUG
//        System.err.println(Arrays.stream(array).limit(10).toList());
        //! Calculamos el tiempo y lo pasamos a una string en formato cientifico
        BigDecimal timeInSeconds = BigDecimal.valueOf(duration).divide(BigDecimal.valueOf(1_000_000_000)
                ,9, RoundingMode.HALF_UP);
        //! Convertimos a string cientifica
        DecimalFormat df = new DecimalFormat("#.##E0");
        String formattedTime = df.format(timeInSeconds.stripTrailingZeros());
        return formattedTime;
    }
}
