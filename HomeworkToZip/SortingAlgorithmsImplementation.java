import java.lang.reflect.Array;

/**
 * Datasheet: SortingAlgorithmsImplementation.java
 * @Author: Santiago Arellano | 00328370
 * @Date: October 4th, 2024.
 * @Description: La presente clase contiene los detalles de implementacion sobre los diversos algoritmos usados
 * en la comparativa de tiempo de ejecucion de:
 * <ul>
 *     <code>Algoritmos Elementales</code>
 *     <li>Selection Sort</li>
 *     <li>Insertion Sort</li>
 *     <li>Bubble Sort</li>
 *     <code>Algoritmos Eficientes</code>
 *     <li>Quick Sort</li>
 *     <li>Merge Sort</li>
 * </ul>
 * <p>Las presentes implementaciones son adaptaciones de las presentadas en el libro: <i>Data Structures, and Algorithms
 * in C++ de Adam Drozdek</i></p>
 */
public class SortingAlgorithmsImplementation {


    /**
     * Implementacion de un metodo generico para realizar selectionSort en un rango de datos. El algoritmo funciona basado
     * en la implementacion dada en <b>Data Structures and Algorithms in C++</b>, cambiando el formato y la utilizacion
     * de la interfaz <code>Comparable</code> para poder evaluar los datos.
     * <p>En este sentido, el algoritmo funciona con clases cuya implementacion establezca el metodo <code>compareTo(T other)
     * </code>proveniente de la interface funcional <code>Comparable</code>.</p>
     * @param data: Arreglo a ordenar.
     * @param size: Tamano del arreglo.
     * @param <T>: Tipo de Dato Generico.
     * @throws NullPointerException: En caso de que el arreglo ingresado sea nulo.
     * @throws IllegalArgumentException: En caso de que el arreglo ingresado este vacio o el parametro size sea 0, o
     *                                  que el arreglo ingresado no coincida con el parametro size.
     */
    public static <T extends Comparable<T>> void selectionSort(T[] data, int size)
                            throws NullPointerException, IllegalArgumentException
    {
        validateInput(data, size);

        //! Inductive Step: Analyze elements and order them
        for (int i = 0, j, least; i < size - 1; i++) {
            T temp = data[i];
            for (j = i + 1, least = i; j < size; j++) {
                if (data[j].compareTo(data[least]) < 0) {
                    least = j;
                }
            }
            T temp2 = data[least];
            data[least] = temp;
            data[i] = temp2;
        }
    }


    /**
     * Implementacion de un metodo generico para realizar insertionSort en un rango de datos. El algoritmo funciona basado
     * en la implementacion dada en <b>Data Structures and Algorithms in C++</b>, cambiando el formato y la utilizacion
     * de la interfaz <code>Comparable</code> para poder evaluar los datos.
     * <p>En este sentido, el algoritmo funciona con clases cuya implementacion establezca el metodo <code>compareTo(T other)
     * </code>proveniente de la interface funcional <code>Comparable</code>.</p>
     * @param data: Arreglo a ordenar.
     * @param size: Tamano del arreglo.
     * @param <T>: Tipo de Dato Generico.
     * @throws NullPointerException: En caso de que el arreglo ingresado sea nulo.
     * @throws IllegalArgumentException: En caso de que el arreglo ingresado este vacio o el parametro size sea 0, o
     *                                  que el arreglo ingresado no coincida con el parametro size.
     */
    public static <T extends Comparable<T>> void insertionSort(T[] data, Integer size)
                                throws NullPointerException, IllegalArgumentException {

        validateInput(data, size);
        
        //! Inductive Step: Check elements and order them
        for (int i = 1, j; i < size; i++) {
            T temp = data[i];
            for (j = i; j > 0 && temp.compareTo(data[j - 1]) < 0; j--) {
                data[j] = data[j - 1];
            }
            data[j] = temp;
        }
    }


    /**
     * Implementacion de un metodo generico para realizar bubbleSort en un rango de datos. El algoritmo funciona basado
     * en la implementacion dada en <b>Data Structures and Algorithms in C++</b>, cambiando el formato y la utilizacion
     * de la interfaz <code>Comparable</code> para poder evaluar los datos.
     * <p>En este sentido, el algoritmo funciona con clases cuya implementacion establezca el metodo <code>compareTo(T other)
     * </code>proveniente de la interface funcional <code>Comparable</code>.</p>
     * @param data: Arreglo a ordenar.
     * @param size: Tamano del arreglo.
     * @param <T>: Tipo de Dato Generico.
     * @throws NullPointerException: En caso de que el arreglo ingresado sea nulo.
     * @throws IllegalArgumentException: En caso de que el arreglo ingresado este vacio o el parametro size sea 0, o
     *                                  que el arreglo ingresado no coincida con el parametro size.
     */

    public static <T extends Comparable<T>> void bubbleSort(T[] data, int size) {

        validateInput(data,size);

        /*
         * El presente metodo funciona revisando los valores del arreglo desde el indice 0 hasta size - 1 para iterar,
         * esto debido a que nuestro algoritmo, por logica de implementacion, llevara al valor mas grande al final y no
         * necesitamos iterar hasta este punto.
         *
         * En el sengundo loop, iniciamos desde el penultimo indice hacia el inicio del algoritmo, revisando pares de datos
         * para su orden parcial, es decir si estos pares estan ordenados ascendentemente. Si lo estan, no realizamos un
         * cambio y la variable swapped se mantiene en false, pero si hay un cambio esta variable nos permite saber si
         * los datos fueron cambiados para evitar el worst case de iterar un arreglo ordenado.
         */
        boolean swapped = true;
        for (int i = 0; i < size - 1 && swapped; i++) {

            for (int j = size - 1; j > i; --j) {
                swapped = false;
                if (data[j].compareTo(data[j - 1]) < 0) {
                    T tempJ = data[j];
                    T tempJMinusOne = data[j - 1];
                    //Intercambio
                    data[j - 1] = tempJ;
                    data[j] = tempJMinusOne;
                    swapped = true;
                }
            }
        }
    }

    /**
     * Implementacion de un metodo generico para realizar quickSort en un rango de datos. El algoritmo funciona basado
     * en la implementacion dada en <b>Data Structures and Algorithms in C++</b>, cambiando el formato y la utilizacion
     * de la interfaz <code>Comparable</code> para poder evaluar los datos.
     * <p>Para su funcionamiento, el algoritmo trabaja con dos funciones internas privadas. La primera <code>private
     * static < T extends Comparable< T > > void quicksort(T[] data, int first, int last)</code> se encarga de realizar
     * la logica interna del programa, es decir, buscar el maximo y empujarlo al final, y luego dividimos el arreglo
     * en dos basados en la mitad del arreglo. En base a esta mitad usamos recursion para romper los arreglos en
     * base a nuevos valores de puntos medios.</p>
     * <p>El segundo metodo, <code> private static < T > void swap(T[] data, int i, int j) </code> es una copia del
     * funcionamiento de std::swap de C++ en donde los valores de dos indices en una referencia de memoria se
     * intercambian.</p>
     * <p>El algoritmo funciona con clases cuya implementacion establezca el metodo <code>compareTo(T other)
     * </code>proveniente de la interface funcional <code>Comparable</code>.</p>
     * @param data: Arreglo a Ordenar
     * @param <T>: Tipo de dato Generico
     * @throws NullPointerException: En caso de que el arreglo ingresado sea nulo.
     * @throws IllegalArgumentException: En caso de que el arreglo ingresado este vacio
     */

    public static <T extends Comparable<T>> void quicksort(T[] data)
                throws NullPointerException, IllegalArgumentException {
        //! Base Cases
        if (data == null) {
            throw new NullPointerException("Error Code 0x0001 - [Raised] El arreglo ingresado para parametro data es nulo.");
        }
        if (data.length == 0) {
            throw new IllegalArgumentException("Error Code 0x0001 - [Raised] El arreglo ingresado para parametro data esta vacio.");
        }

        int n = data.length;
        if (n < 2) return; // Condicion Base: si el tamano del arreglo es menor de dos, el arreglo se encuentra ordenado

        /*
         * El presente metodo encuentra el valor mas grande del arreglo y lo intercambia con la posicion final del arreglo.
         * El proposito de este cambio se puede describir como:
         * 1. El valor maximo puede alterar el comportamiento interno para encontrar el pivote de los arreglos subsequentes,
         *      por tanto se mueve al final para que no cause problemas.
         */
        int max = 0;
        for (int i = 1; i < n; i++) {
            if (data[max].compareTo(data[i]) < 0) {
                max = i;
            }
        }
        swap(data, n - 1, max);

        // Llamamos quick sort para el resto del arreglo hasta n-2 para saltar el ultimo elemento ya ordenado
        quicksort(data, 0, n - 2);
    }

    /**
     * Metodo privado de implementacion de quickSort, se encarga de realizar la logica interna del algoritmo.
     * @param data: Arreglo a ordenar
     * @param first: Primer indice del arreglo
     * @param last: Ultimo indice del arreglo
     * @param <T>: Tipo de dato Generico
     */
    private static <T extends Comparable<T>> void quicksort(T[] data, int first, int last) {
        // Condicion secundaria para la recursion, avisa al sistema si hemos llegado a arreglos
        // de un solo elemento
        if (first >= last) {
            return;
        }

        int lower = first + 1;
        int upper = last;

        // Seleccionamos el elemento pivote como el medio del arreglo y lo movemos
        // al inicio
        swap(data, first, (first + last) / 2);
        T bound = data[first]; // Seleccionamos el elemento pivote

        // Partimos el arreglo y dejamos los indices apuntando a las divisiones correctas
        while (lower <= upper) {
            while (bound.compareTo(data[lower]) > 0) lower++;
            while (bound.compareTo(data[upper]) < 0) upper--;
            if (lower < upper) {
                swap(data, lower++, upper--);
            } else {
                lower++;
            }
        }
        swap(data, upper, first); // Movemos el nuevo pivote a su posicion y llamamos recursivamente al arreglo con
        // las divisiones realizadas
        if (first < upper - 1) quicksort(data, first, upper - 1);
        if (upper + 1 < last) quicksort(data, upper + 1, last);
    }

    /**
     * Metodo privado de intercambio de datos, metodo privado usado en quickSort
     * @param data: Arreglo a ordenar
     * @param i: Primer indice del arreglo
     * @param j: Segundo indice del arreglo
     * @param <T>: Tipo de dato Generico
     */
    private static <T> void swap(T[] data, int i, int j) {
        T temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }


    /**
     * Implementacion de un metodo generico para realizar mergeSort en un rango de datos. El algoritmo funciona basado
     * en la implementacion dada en <b>Data Structures and Algorithms in C++</b>, cambiando el formato y la utilizacion
     * de la interfaz <code>Comparable</code> para poder evaluar los datos.
     * <p>En este sentido, el algoritmo funciona con clases cuya implementacion establezca el metodo <code>compareTo(T other)
     * </code>proveniente de la interface funcional <code>Comparable</code>.</p>
     * <p>Este algoritmo funciona en base a un metodo privado con prototipo <code> private static < E extends Comparable< E > >
     *     E[] merge(E[] list1, E[] list2)</code> el cual se encarga de realizar la union de los arreglos temporales basados
     *     en los valores de los mismos y los indices internos de los arreglos.</p>
     *
     * @param data: Arreglo a ordenar.
     * @param <E>: Tipo de Dato Generico.
     * @throws NullPointerException: En caso de que el arreglo ingresado sea nulo.
     * @throws IllegalArgumentException: En caso de que el arreglo ingresado este vacio o su longitud sea cero.
     *
     */
    @SuppressWarnings("unchecked")
    public static <E extends Comparable<E>> void mergeSort(E[] data) {
        if (data == null) {
            throw new NullPointerException("Error Code 0x0001 - [Raised] El arreglo ingresado para parametro data es nulo.");
        }
        if (data.length == 0) {
            throw new IllegalArgumentException("Error Code 0x0001 - [Raised] El arreglo ingresado para parametro data esta vacio.");
        }

        if (data.length > 1) {
            /*
             * La presente subseccion del algoritmo realiza un trabajo de subseccionamiento iterativo del arreglo basado
             * en el punto medio del arreglo original.
             *
             * El algoritmo funciona al copiar el arreglo original, los valores hasta mid, hacia el nuevo arreglo temporal
             * que sera el que se organizara internamente usando la funcion merge interna. Esta seccion es recursiva,
             * realizando llamadas para la mitad izquierda del arreglo original hasta que la longitud de estos subarreglos
             * es 2
             */
            int mid = data.length / 2;
            E[] firstHalf = (E[]) Array.newInstance(data.getClass().getComponentType(), mid);
            System.arraycopy(data, 0, firstHalf, 0, mid);
            mergeSort(firstHalf);

            /*
             * La presente subseccion del algoritmo realiza un trabajo de subseccionamiento iterativo del arreglo basado
             * en el punto medio del arreglo original.
             *
             * El algoritmo funciona al copiar el arreglo original, los valores hasta el final del arreglo, sin incluir mid,
             * hacia el nuevo arreglo temporal que sera el que se organizara internamente usando la funcion merge interna.
             * Esta seccion es recursiva, realizando llamadas para la mitad derecha del arreglo original hasta que
             * la longitud de estos subarreglos es 2
             */
            int secondHalfLength = data.length - mid;
            E[] secondHalf = (E[]) Array.newInstance(data.getClass().getComponentType(), secondHalfLength);
            System.arraycopy(data, mid, secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);

            //* Combinamos ambas partes utilizando el metodo privado merge
            E[] temp = merge(firstHalf, secondHalf);
            System.arraycopy(temp, 0, data, 0, temp.length);
        }
    }

    /**
     * Metodo privado de implementacion de mergeSort, se encarga de realizar la union de los arreglos temporales.
     * @param list1: Primer arreglo a unir
     * @param list2: Segundo arreglo a unir
     * @param <E>: Tipo de dato Generico
     * @return: Arreglo resultante de la union de ambos arreglos.
     */
    private static <E extends Comparable<E>> E[] merge(E[] list1, E[] list2) {
        E[] temp = (E[]) Array.newInstance(list1.getClass().getComponentType(), list1.length + list2.length);

        int current1 = 0; // Index in list1
        int current2 = 0; // Index in list2
        int current3 = 0; // Index in temp

        /*
         3. Comparar los elementos de list1 y list2:
 *    a. Mientras haya elementos en ambos arreglos:
 *       i. Si el elemento actual de list1 es menor que el elemento actual de list2, agregar el elemento de list1 a temp
 *          y mover current1 al siguiente elemento.
 *       ii. De lo contrario, agregar el elemento de list2 a temp y mover current2 al siguiente elemento.
         */
        while (current1 < list1.length && current2 < list2.length) {
            if ((list1[current1].compareTo(list2[current2])) < 0) {
                temp[current3++] = list1[current1++];
            } else {
                temp[current3++] = list2[current2++];
            }
        }

        /*
         * 4. Si aun quedan elementos en list1 despues de que list2 se haya
         * procesado completamente, copiar estos elementos restantes de list1 a temp.
         */
        while (current1 < list1.length) {
            temp[current3++] = list1[current1++];
        }

        /*
         * 5. Si aun quedan elementos en list2 despues de que list1 se haya
         * procesado completamente, copiar estos elementos
         */
        while (current2 < list2.length) {
            temp[current3++] = list2[current2++];
        }

        return temp;
    }

    /**
     * Metodo de validacion de datos de entrada.
     * @param data: Arreglo a validar
     * @param size: Tamaño del arreglo
     * @param <T>: Tipo de dato Generico
     * @throws NullPointerException: En caso de que el arreglo ingresado sea nulo.
     * @throws IllegalArgumentException: En caso de que el arreglo ingresado este vacio o su longitud no coincida con size.
     */
    private static <T extends Comparable<T>>void validateInput (T[]data, Integer size){
        if (data == null) {
            throw new NullPointerException("Error Code 0x0001 - [Raised] El arreglo ingresado para parametro data es nulo.");
        }
        if (data.length == 0 || size == 0) {
            throw new IllegalArgumentException("Error Code 0x0001 - [Raised] El arreglo ingresado para " +
                    "parametro data esta vacio o el parametro size es 0.");
        }
        if (data.length != size) {
            throw new IllegalArgumentException("Error Code 0x0001 - [Raised] El arreglo ingresado para " +
                    "parametro data no coincide con el parametro size.");
        }
    }
}

