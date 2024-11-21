package DeberDiezHashMapComparisonSantiagoArellano;

import javax.annotation.processing.Generated;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.lang.Integer;
/**
 * @Author: Santiago Arellano
 * @Date: November 19th, 2024
 * @Description: EL presente archivo contiene el codigo base de comparativa para las clases implementadas en el texto
 * Introduction To Java Programming por Daniel Liang. En especcifico se va a probar dos clases, MyArrayList, y MyHashMap
 * a traves de pruebas con 1_000_000 entero de insercion y 1_000_000 de enteros de busqueda en variados rangos
 */
public class ComparisonDriver {
    //! Definicion de variables internas staticas para acceso interno por el metodo main
    private static Double[] insertionNumbersRange = IntStream
            .rangeClosed(0, 999999)
            .asDoubleStream()
            .boxed()
            .toArray(Double[]::new);

    private static Double[] searchNumbersRange = IntStream
            .rangeClosed(0, 1_999_9)
            .asDoubleStream()
            .boxed()
            .toArray(Double[]::new);
    private static int SIZE_OF_ARRAY = 1_000_000;
    //! Punto de entrada de la aplicacion
    public static void main(String[] args) {
        MyHashSet<Double> testCaseHashSet = new MyHashSet<>();
        MyArrayList<Double> testCaseArrayList = new MyArrayList<>();
        System.out.printf("%120s","Test Suite | Comparativa de velocidad de insercion y busqueda MyArrayList y MyHashSet");
        System.out.println("Test #1 | Comparativo de Velocidad de Insercion Para Ambas Clases");
        String[] results = new String[2];
        results[0] = executeAndTime(testCaseHashSet, insertionNumbersRange,
                (hashSet, insertionNumbersRange) -> {
                    Arrays.stream(insertionNumbersRange).forEach(hashSet::add);
                });
        results[1] = executeAndTime(testCaseArrayList, insertionNumbersRange,
                (arrayList, insertionNumbersRange) -> {
                    Arrays.stream(insertionNumbersRange).forEach(arrayList::add);
                });
        System.out.println("results = " + testCaseHashSet.size());
        //! Printing results in formatted order
        System.out.println("Tiempo de Insercion Para MyHashSet [s]: " + results[0]);
        System.out.println("Tiempo de Insercion Para MyArrayList [s]: " + results[1]);
        System.out.println();
        System.out.println("Test #2 | Comparativo Velocidad de Busqueda con valores generados en segundo arreglo");
        results[0] = executeAndTime(testCaseHashSet, searchNumbersRange,
                (hashSet, searchNumbersRange) -> {
                    Arrays.stream(searchNumbersRange).forEach(hashSet::contains);
                });
        results[1] = executeAndTime(testCaseArrayList, searchNumbersRange,
                (arrayList, searchNumbersRange) -> {
                    Arrays.stream(searchNumbersRange).forEach(arrayList::contains);
                });
        System.out.println("Tiempo de Busqueda Para MyHashSet [s]: " + results[0]);
        System.out.println("Tiempo de Busqueda Para MyArrayList [s]: " + results[1]);
        
    }

    private static <E> String executeAndTime(MyHashSet<E> structureToModify, Double[] valuesToApply, BiConsumer<MyHashSet<E>, Double[]>
                                             functionToExecute){
        Long timeBefore = System.currentTimeMillis();
        functionToExecute.accept(structureToModify, valuesToApply);
        Long timeAfter = System.currentTimeMillis();
        //! Registramos el tiempo en una variable de String ahora en formato cientifico y en segundos
        return getFormattedTime(timeAfter, timeBefore);
    }

    private static <E> String executeAndTime(MyArrayList<E> structureToModify, Double[] valuesToApply, BiConsumer<MyArrayList<E>, Double[]> functionToExecute){
        Long timeBefore = System.currentTimeMillis();
        functionToExecute.accept(structureToModify, valuesToApply);
        Long timeAfter = System.currentTimeMillis();
        //! Registramos el tiempo en una variable de String ahora en formato cientifico y en segundos
        return getFormattedTime(timeAfter, timeBefore);
    }

    private static String getFormattedTime(Long timeAfter, Long timeBefore) {
        Long duration = timeAfter - timeBefore;
        //! Calculamos el tiempo y lo pasamos a una string en formato cientifico
        BigDecimal timeInSeconds = BigDecimal.valueOf(duration).divide(BigDecimal.valueOf(1_000_000_000)
                ,9, RoundingMode.HALF_UP);
        //! Convertimos a string cientifica
        DecimalFormat df = new DecimalFormat("#.##E0");
        String formattedTime = df.format(timeInSeconds.stripTrailingZeros());
        return formattedTime;
    }
}
