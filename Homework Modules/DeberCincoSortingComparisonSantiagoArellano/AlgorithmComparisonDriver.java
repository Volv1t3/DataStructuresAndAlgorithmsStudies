package DeberCincoSortingComparisonSantiagoArellano;


import SortingAlgoPractice.AdvancedSortingAlgorithms;

import java.io.FilenameFilter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * Datasheet: SortingAlgorithmsImplementation.java
 * @Author: Santiago Arellano | 00328370
 * @Date: October 4th, 2024.
 * @Description: La presente clase contiene el programa main del comparativo entre algoritmos. Presenta helper metodos
 * privados especificos que permiten trabajar con arreglos de datos de n cantidad de entradas en formato de funciones
 * lambdas enviadas a un metodo como referencia
 */
public class AlgorithmComparisonDriver {

    /**
     * Constante estatica con los tamanos de cada arreglo a generar para las pruebas de los algoritmos
     */
    private static final int[] ARRAY_SIZES = new int[]{50_000, 100_000, 150_000,200_000, 250_000, 300_000};
    private static final LinkedHashMap<String, ArrayList<BigDecimal>> resultados = new LinkedHashMap<>();
    private static final String[] ALGORITHMS = new String[]{"SelectionSort", "InsertionSort", "BubbleSort", "MergeSort", "QuickSort"};


    public static void main(String[] args) {
        testAlgorithm("SelectionSort", SortingAlgorithmsImplementation::selectionSort);
        testAlgorithm("InsertionSort", SortingAlgorithmsImplementation::insertionSort);

        testAlgorithm("BubbleSort", SortingAlgorithmsImplementation::bubbleSort);
        testAlgorithm("MergeSort", SortingAlgorithmsImplementation::mergeSort);
        testAlgorithm("QuickSort", SortingAlgorithmsImplementation::quicksort);

        //! Imprimimos los resultados separados por tipo de algoritmo y tiempo de ejecucion
        for (String algorithm : ALGORITHMS) {
            System.out.println("Algoritmo: " + algorithm);
            for (int size : ARRAY_SIZES) {
                String key = algorithm + size;
                var result = resultados.get(key);
                if (result != null) {
                    System.out.println("Tamano de entrada: " + size + " Tiempo de ejecucion: " + result.getFirst() + " segundos");
                }
            }
        }
    }
    private static Integer[] generateArray(int size) {
            Integer[] data = IntStream.rangeClosed(0, size)
                    .boxed()
                    .toArray(Integer[]::new);
            Collections.shuffle(Arrays.asList(data));
            return data;
    }

    private static void testAlgorithm(String AlgorithmName, BiConsumer<Integer[], Integer> algorithm) {
        Arrays.stream(ARRAY_SIZES).parallel().forEach(size ->
        {
            var array = generateArray(size);
            //! Get time Before
            long startTime = System.nanoTime();
            algorithm.accept(array, array.length);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            BigDecimal timeInSeconds = BigDecimal.valueOf(duration).divide(BigDecimal.valueOf(1_000_000_000)
                    ,9, RoundingMode.HALF_UP);
            //! Convertimos a string cientifica
            DecimalFormat df = new DecimalFormat("#.##E0");
            String formattedTime = df.format(timeInSeconds.stripTrailingZeros());
            resultados.put(AlgorithmName + size, new ArrayList<>(Collections.singleton(new BigDecimal(formattedTime))));
        });
    }
    private static void testAlgorithm(String AlgorithmName, Consumer<Integer[]> algorithm) {
        Arrays.stream(ARRAY_SIZES).parallel().forEach(size ->
        {
            var array = generateArray(size);
            //! Get time Before
            long startTime = System.nanoTime();
            algorithm.accept(array);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            BigDecimal timeInSeconds = BigDecimal.valueOf(duration).divide(BigDecimal.valueOf(1_000_000_000)
                    ,9, RoundingMode.HALF_UP);
            //! Convertimos a string cientifica
            DecimalFormat df = new DecimalFormat("#.##E0");
            String formattedTime = df.format(timeInSeconds.stripTrailingZeros());
            resultados.put(AlgorithmName + size, new ArrayList<>(Collections.singleton(new BigDecimal(formattedTime))));
        });
    }
}
