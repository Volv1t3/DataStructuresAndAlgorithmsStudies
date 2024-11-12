package DeberNueveAVLTreeTimingSantiagoArellano;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * @Author: Santiago Arellano
 * @Date: Noviembre 11, 2024
 * @Description: El presente archivo contiene la clase main donde se prueba el tiempo de ejecucion de los diversos
 * rpcoesos dentro de un arbol AVL y Binary Tree normal
 */
public class TimingTestDriver {

    public static void main(String[] args) {
        //! Definimos el arreglo de 500 000 enteros
        Integer[] arrOfNums = IntStream.rangeClosed(0,500_000).boxed().toArray(Integer[]::new);
        BinaryTree<Integer> integerBinaryTree = new BinaryTree<>(1); integerBinaryTree.clear();
        AVLTree<Integer> integerAVLTree = new AVLTree<>(1); integerAVLTree.clear();
        //! Definimos interface amigable
        System.out.printf("%120s", "Test Suite | Temporizacion de Arboles AVL y BST | Santiago Arellano");
        System.out.println("Test Suite #1 | Insercion de 500_000 elements en cada uno");
        String[] stringsResultingFromOperations = new String[2];
        stringsResultingFromOperations[0] = executeAndTime(integerBinaryTree, arrOfNums, (tree, elements) -> {
            Arrays.stream(elements).forEach(tree::insertionForBinaryTree);
        });
        stringsResultingFromOperations[1] = executeAndTime(integerAVLTree, arrOfNums, (tree, elements) -> {
            Arrays.stream(elements).forEach(tree::add);
        });
        System.out.println("Binary Tree insertion time: " + stringsResultingFromOperations[0]);
        System.out.println("AVL Tree insertion time: " + stringsResultingFromOperations[1]);
    }
    private static <E extends Comparable<E>> String executeAndTime(BinaryTree<E> treeToApplyOn, Integer[] integers, BiConsumer<BinaryTree<E>, Integer[]> operation){
        Long timeBefore = System.currentTimeMillis();
        operation.accept(treeToApplyOn, integers);
        Long timeAfter = System.currentTimeMillis();
        //! Registramos el tiempo en una variable de String ahora en formato cientifico y en segundos
        return getFormattedTime(timeAfter, timeBefore);
    }
    private static <E extends Comparable<E>> String executeAndTime(AVLTree<E> treeToApplyON, Integer[] integers, BiConsumer<AVLTree<E>, Integer[]> operation){
        Long timeBefore = System.currentTimeMillis();
        operation.accept(treeToApplyON, integers);
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