package DeberSieteComparativaQuickSortSantiagoArellano;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Santiago Arellano
 * @Date: Octubre 24, 2024
 * @Description: El presente archivo contiene el driver principal para la ejecucion de los algoritmos de QuickSort y su
 * comparacion de tiempo de ejecucion.
 * @see TimeUtils
 * @see QuickSort
 * @see Comparable
 * @implNote : El driver principal contiene un arreglo de tamaños de arreglo, y un mapa para cada
 * algoritmo de QuickSort, el cual contiene el tiempo de ejecucion de cada algoritmo para cada tamaño de arreglo. El driver
 * principal imprime el tiempo de ejecucion de cada algoritmo para cada tamaño de arreglo.
 */
public class QuickSortComparisonDriver {

    private static final Integer[] arraySizes = {50_000,100_000,150_000,200_000,250_000,300_000, 350_000,400_000};
    private static HashMap<String, String> recursiveQuickSortMap = new HashMap<>();
    private static HashMap<String, String> iterativeQuickSortMap = new HashMap<>();
    public static void main(String[] args) {
        //! Declaracion de variables internas
        final String RECURSIVE_QUICK_SORT_NAME = "recursiveQuickSort";
        final String ITERATIVE_QUICK_SORT_NAME = "iterativeQuickSort";

        //! Calculo
        for(Integer size : arraySizes){
            Map.Entry<String,String> entry = TimeUtils.executeAnAlgorithm(size,
                    RECURSIVE_QUICK_SORT_NAME + size.toString(),
                    QuickSort::recursiveQuickSort);
            recursiveQuickSortMap.put(entry.getKey(), entry.getValue()); //! Agrega el tiempo de ejecucion al mapa (LinkedHashMap
                                                                         //! recursiveSorting)

            entry = TimeUtils.executeAnAlgorithm(size,
                    ITERATIVE_QUICK_SORT_NAME + size.toString(),
                    QuickSort::inPlaceNonRecursiveQuickSort);

            iterativeQuickSortMap.put(entry.getKey(), entry.getValue()); //! Agrega el tiempo de ejecucion al mapa (LinkedHashMap
                                                                        //! inPlaceSorting
        }

        //! Iteracion para la Impresion
        System.out.printf("%40s\t|%40s\t|%40s","Tamano de Arreglo","QuickSort Iterativo","QuickSort Recursivo");
        for(Integer size : arraySizes){
            System.out.printf("\n%40s\t|%40s\t|%40s",
                    size.toString(),
                    recursiveQuickSortMap.get(RECURSIVE_QUICK_SORT_NAME + size.toString()),
                    iterativeQuickSortMap.get(ITERATIVE_QUICK_SORT_NAME + size.toString()));
        }

    }
}
