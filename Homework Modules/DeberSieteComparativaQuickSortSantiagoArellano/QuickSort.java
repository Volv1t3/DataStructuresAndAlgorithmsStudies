package DeberSieteComparativaQuickSortSantiagoArellano;

/**
 * @author : Santiago Arellano
 * @Date: Octubre 24, 2024
 * @Description: El presente archivo muestra dos implementaciones de QuickSort, una iterativa y otra recursiva basada en
 *               una implementacion recursiva de <b>Data Structures And Algorithms in C++</b>, y en la teoria presentada en el
 *               libro <b>Theory and Practice of Formal Methods</b>, capitulo, <b>Quicksort Revisited: Verifying Alternative
 *               Versions of Quicksort </b>
 *
 */
public class QuickSort {

    /**
     * <p>El presente metodo estatico implementa la interface principal para la implementacion recursiva de QuickSort.
     * Este metodo prepara los datos moviendo el valor maximo al final del arreglo para evitar el peor caso de QuickSort,
     * el cual se da cuando el pivote es el valor mayor del grupo de datos y causa que el algoritmo se transforme en un
     * SelectionSort de orden O(n^2)</p>
     * <br>
     * <p>Luego de realizar el cambio de variable hacia el tope del arreglo para el mayor valor, el arreglo se envia
     * a un metodo interno para su organizacion recursiva</p>
     * <br>
     * <p>La implementacion de este metodo es una adaptacion a Java de la presentada en el libro de Data Structures and
     * Algorithms in C++ de Adam Drozdek. Mientras que algunas refacciones al codigo se han realizado para volverlo mas
     * en linea con el formato de Java y para volverlo mas seguro ante inputs erroneos.</p>
     * @param externalData: Arreglo de datos a ordenar
     * @param <E>: Tipo de Dato Generico (que extiende <b>Comparable</b>) con el que se instancia este metodo
     * @see Comparable
     */
    public static <E extends Comparable<E>> void recursiveQuickSort(E[] externalData)
            throws NullPointerException, IllegalArgumentException
    {
        if (externalData == null) {
            throw new NullPointerException("Error Code 0x001 - [Raised] El arreglo de datos es nulo");
        }
        if (externalData.length == 0){
            throw new IllegalArgumentException("Error Code 0x001 - [Raised] El arreglo de datos esta vacio");
        }
        if (externalData.length == 1){
            return;
        }

        //! Movemos el valor maximo
        int max = 0;
        for (int i = 0; i < externalData.length; i++){
            if (externalData[max].compareTo(externalData[i]) < 0){
                max = i;
            }
        }
        swap(externalData, externalData.length - 1, max);

        //! Call Internal Recursive Function
        recursiveQuickSortHelper(externalData, 0, externalData.length -2);
    }

    private static <E extends Comparable<E>> void recursiveQuickSortHelper(E[] externalData, int first, int last){
        //? Caso base recursivo
        if (first >= last){return;}
        //! Definimos variables y realizamos primer movimiento de pivote
        int lower = first +1;
        int upper = last;
        swap(externalData, first,  (first+last) /2);
        E bound = externalData[first];
        while (lower <= upper){
            while (bound.compareTo(externalData[lower]) >0){lower++;}
            while (bound.compareTo(externalData[upper]) <0){upper--;}
            if (lower < upper){
                swap(externalData, lower++, upper--);
            }
            else {lower++;}
        }

        //! Segundo swap con pivote actualizado
        swap(externalData, first, upper);

        //! Llamadas Recursivas
        if (first < upper -1){recursiveQuickSortHelper(externalData, first, upper -1);}
        if (upper + 1 < last){recursiveQuickSortHelper(externalData, upper +1, last);}

    }

    private static <T> void swap(T[] data, int i, int j) {
        T temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /**
     * El presente metoodo implementa el algorithm iterativo de QuickSort basado en el registro de los pivotes utilizados
     * en un arreglo interno. El algoritmo se basa en la implementacion de la teoria presentada en el libro de Theory and
     * Practice of Formal Methods, capitulo <b>Quicksort Revisited: Verifying Alternative Versions of Quicksort</b>.
     * <br><br>
     * El algoritmo trabaja sobre el rango de la lista, asumiendo un pivote sentinela en la posicion <code>length -1</code>.
     * En base de este valor, el algoritmo iterave sobre punteros preestablecidos tanto al inicio como al final de la lista,
     * y los mueve de acuerdo a los valores que encuentre y la comparativa entre el valor enviado al metodo interno partition.
     * <br><br>
     * El metodo partition trabaja de una manera similar al realizado por el quickSort recurisvo en el busca un valor
     * pivote nuevo que se encuentra en la variable lower, si esta variable supera el valor de a variable upper, es
     * retornado como el nuevo pivote (menos uno), y si no se realiza un cambio entre las variables en las que se encuentre upper
     * y lower, en este sentido el algoritmo trabaja y ordena dos lados del arreglo al mismo tiempo, moviendo los valores
     * a sus sublistas correspondientes con respecto al pivote seleccionado.
     * @param externalData: Arreglo a Ordenar
     * @param <E>: Tipo de dato del Arreglo, generico pero debe extender <code>Comparable</code>
     * @throws NullPointerException: Si el arreglo enviado es nulo
     * @throws IllegalArgumentException: Si el arreglo tiene una longitud menor o igual que uno
     */
    public static  <E extends Comparable<E>> void inPlaceNonRecursiveQuickSort(E[] externalData)
            throws NullPointerException, IllegalArgumentException{
        if (externalData ==null){
            throw new NullPointerException("The external data array is null.");
        }
        if (externalData.length <= 1){
            throw new IllegalArgumentException("The external data array is empty.");
        }

        int[] storedPivots = new int[externalData.length + 1];
        storedPivots[externalData.length] = externalData.length;
        int from = 0;
        int to = externalData.length;
        int top = externalData.length;

        while (externalData.length - from > 1){
            if ((to - from )<= 1) {
                from = to + 1;
                top++;
                to = storedPivots[top];
            }
            else{
                E pivot = externalData[from];
                int partitionIndex = partition(externalData, from +1, to, pivot);
                swap(externalData, from, partitionIndex -1);
                top--;
                storedPivots[top] = partitionIndex - 1;
                to = partitionIndex - 1;
            }

        }
    }
    private static <E extends Comparable<E>> int partition(E[] a, int from, int to, E pivot) {
        int lower = from;
        int upper = to - 1;

        while (true) {
            while ( lower <= upper && a[lower].compareTo(pivot) < 0) {
                lower++;
            }
            while ( lower <= upper && a[upper].compareTo(pivot) >= 0) {
                upper--;
            }
            if (lower > upper) {
                return lower;
            }
            swap(a, lower++, upper--);
        }
    }

}
