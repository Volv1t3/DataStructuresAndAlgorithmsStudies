package QuickSortVariants;

import java.util.Arrays;
import java.util.Collections;

public class WaterlooThreePivotQuickSort {

    public static void sort(Integer[] arr) {

        if (arr == null || arr.length < 2) return;
        WaterlooQuickSort(arr, 0, arr.length -1);
    }

    private static void WaterlooQuickSort(Integer[] arr, int left, int right){
        if (right - left < 1 ){
            /*
             * Caso base: Si la lista tiene menos de 2 elementos, la lista se considera ordenada y no se realiza una
             * llamada al algoritmo.
             */
            return;
        }
        else {
            /*
            * Se procede a organizar los pivots de la lista (left, left + 1, right) en el orden correcto. Esto es similar
            * al proceso que se realiza en aquellos algoritmos de doble pivote en donde los valores se ordenan entre si
            * para mantener las invariantes como: a[left] < a[left + 1] < a[right].
            */
            if (arr[left] > arr[left + 1]) /*!Si el leftmost pivot es mayor que el second to leftmost se intercambia*/{
                swap(arr, left, left + 1);
            }
            if (arr[left + 1] > arr[right])/*!Si el second to leftmost es mayor que el rightmost se intercambia*/{
                swap(arr, left + 1, right);
            }
            if (arr[left] > arr[left + 1])/*!Si el leftmost pivot es mayor que el second to leftmost se intercambia (Chequeo repetido por  cambios que
            /*!el segundo queueo puede ocasionar*/{
                swap(arr, left, left + 1);
            }

            //! LLamada a la funcion de particion para ordenar el arreglo antes de la recursion
            WaterlooQuickSortPartition(arr, left, right);

            //! Recursive Calls
            WaterlooQuickSort(arr, left, leftMostPointer); /*! LLamada recursiva a la seccion 1, desde 0 hasta leftmost pointer para aquellos menores que left*/
            WaterlooQuickSort(arr, leftMostPointer + 1, leftMostPointerTwo); /*!Llamada recursiva de la seccion 2 aquellos entre left y left + 1*/
            WaterlooQuickSort(arr, leftMostPointerTwo +1 , rightMostPointer -1 ) /*! LLamada recursiva de la seccion 3, aquellos mayores que left + 1 y menores que right*/;
            WaterlooQuickSort(arr, rightMostPointer, right); /*! Llamada recursiva de la seccion 4, aquellos mayores que right*/
        }
    }

    /*
    * Usamos constantes estaticas dado que usamos recursividad y estos valores se requieren tanto para las llamadas como para
    * la particion, sin embargo, no se retornan a la funcion principal.
    */
    private static int leftMostPointer;
    private static int leftMostPointerTwo;
    private static int rightMostPointer;
    private static void WaterlooQuickSortPartition(Integer[] arr, int left, int right){
        leftMostPointer = left + 2;
        leftMostPointerTwo = left + 2;
        int rightMostPointerTwo = right - 1;
        rightMostPointer = right - 1;
        int leftMostPivot = arr[left], leftMostPivotSecond = arr[left + 1], rightMostPivot = arr[right];

        while (leftMostPointerTwo <= rightMostPointerTwo){
            /*
             * Primera seccion de la particion. Los elementos se revisan entre los punteros leftMostPointerTwo y leftMostPointer,
             * es decir en nuestro diagrama, en las secciones I y II, con los punteros a y b teniendo en cuenta que se
             * encuentren entre los pivotes para determinar su posicion.
             *
             * Esta revision, evalua si, en primera instancia el valor del puntero b es menor que el pivote dos (left +1) Si esta regla pasa
             * *entonces estamos en la seccion inferior de los valores. Luego se pregunt si el valor de bes menor que el pivote izquierdo
             * *(left), si esta regla pasa entonces intercambiamos a con b para poner a b en su lugar e incrementamos a, si no solo
             * *incrementamos b ya que este no ha estado en la primera region sino en la segunda.
             */
            while (leftMostPointerTwo <= rightMostPointerTwo && arr[leftMostPointerTwo] < leftMostPivotSecond){
                if (arr[leftMostPointerTwo] < leftMostPivot){
                    swap(arr, leftMostPointer, leftMostPointerTwo);
                    leftMostPointer++;
                }
                leftMostPointerTwo++;
            }
            /*
             * Segundo analisis interno preliminar, este trabaja con las seccioens III y IV, y con los punteros c y d. En primera instancia,
             * *se revisa si el valor guardado en el puntero c es mayor que el segundo pivote (left + 1) lo que nos pone en la seccion III U IV.
             * *Luego revisamos si el valor del pointer c es mayor que el pivote tres (right), lo que nos mueve, si es cierto, a la seccion IV.
             * *Si la condicion se cumple se intercambian los valores del puntero c con el d, sino solo se decrementa el c y el d solo en el interior.
             */
            while (leftMostPointerTwo <= rightMostPointerTwo && arr[rightMostPointerTwo] > leftMostPivotSecond){
                if (arr[rightMostPointerTwo] > rightMostPivot){
                    swap(arr, rightMostPointerTwo, rightMostPointer);
                    rightMostPointer--;
                }
                rightMostPointerTwo--;
            }
            /*
             * En esta seccion se trabajan en mover valores que se encuentran oscilando entre ambas regiones. El primer chequeo
             ! revisa si el puntero b sigue siendo menor que el c, si lo son entramos a revisar los valores y no solo posiciones.
             * El primer if que nos encontramos revisa si el valor del puntero b es mayor que el pivote tres, esto significa que tendriamos
             * *que pasar de la region II a la III. Internamente revisamos si el valor del puntero c es menor que el pivote uno, lo que indica un
             * *cambio cruzado de III a I y de II a III. Si estas dos se cumplen entonces realizxamos un intercambio entre los punteros izquierdos (a,b)
             * *y luego a con c, incrementando al final el valor del puntero a.
             * *
             * *Si no se cumple que c < pivote 1, entonces solo intercambiamos de II a III con un intercambio de b hacia c.
             * *Al final realizamos un intercambio entre c y d para luego incrementar b y reducir en uno c y d.
             * *Los cambios son
             *
             * SI b > P3 {
             * *    SI c < P1 {
             * *        a,b <-> b,a
             * *        a,c <-> c,a
             * *        a++
             * *   }
             * *    ENTONCES{
             * *        b,c <-> c,b
             * *   }
             * *    c,d <-> d,c
             * *    b++ c-- d--
             * *}
             * *ENTONCES{
             * *    SI c < P1{
             * *        a,b <-> b,a
             * *        a,c <-> c,a
             * *    }
             * *    ENTONCES{
             * *    b,c <-> c,b
             * *}
             * *    b++ c--
             * *}
             */
            if (leftMostPointerTwo <= rightMostPointerTwo){
                if (arr[leftMostPointerTwo] > rightMostPivot){
                    if (arr[rightMostPointerTwo] < leftMostPivot){
                        swap(arr, leftMostPointerTwo, leftMostPointer);
                        swap(arr, leftMostPointer, rightMostPointerTwo);
                        leftMostPointer++;
                    }
                    else {
                        swap(arr, leftMostPointerTwo, rightMostPointerTwo);
                    }
                    swap(arr, rightMostPointerTwo, rightMostPointer);
                    leftMostPointerTwo++; rightMostPointerTwo--; rightMostPointer--;
                }
                else {
                    if (arr[rightMostPointerTwo] < leftMostPivot){
                        swap(arr, leftMostPointerTwo, leftMostPointer);
                        swap(arr, leftMostPointer, rightMostPointerTwo);
                        leftMostPointer++;
                    }
                    else {
                        swap(arr, leftMostPointerTwo, rightMostPointerTwo);
                    }
                    leftMostPointerTwo++;
                    rightMostPointerTwo--;
                }
            }
        }


        leftMostPointer--;
        leftMostPointerTwo--;
        rightMostPointerTwo++;
        rightMostPointer++;
        swap(arr, left + 1, leftMostPointer);
        swap(arr, leftMostPointer, leftMostPointerTwo);
        leftMostPointer--;
        swap(arr, left, leftMostPointer);
        swap(arr, right, rightMostPointer);
    }

    public static void swap(Integer[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] arr = {1,7,3,11,9,8,5,2};
        System.out.println("Before sorting: " + Arrays.toString(arr));
        WaterlooThreePivotQuickSort.sort(arr);
        System.out.println("After sorting:  " +Arrays.toString(arr));
    }
}
