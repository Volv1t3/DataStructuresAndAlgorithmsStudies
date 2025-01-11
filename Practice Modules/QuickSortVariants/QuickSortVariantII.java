package QuickSortVariants;
import java.util.Random;

public class QuickSortVariantII {
    private static Random random = new Random();

    public static void sort(Integer[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(Integer[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }

    }

    private static int partition(Integer[] arr, int low, int high) {
        //! Paso Base: Seleccionamos el indice del pivote
        int pivotIndex = low + random.nextInt(high - low + 1);
        int pivot = arr[pivotIndex];


        //! Paso Base1: Comparamos aquellos valores a la izquierda del pivote
        for (int i = low; i < pivotIndex; i++) {
            if (arr[i] > pivot) {
                //! Paso Base2: Movemos el valor que fue mayor al final de la lista,
                //! y desplazamos todo hacia la izquierda
                int temp = arr[i];
                for (int j = i; j < high; j++) {
                    arr[j] = arr[j + 1];
                }
                arr[high] = temp;
                //! Actualizamos indices de analisis
                pivotIndex--;
                i--;
            }
        }

        //! Paso Base3: Retomamos una busqueda sequencial a la derecha del pivote
        for (int i = pivotIndex + 1; i <= high; i++) {
            if (arr[i] < pivot) {
                int temp = arr[i];
                for (int j = i; j > pivotIndex; j--) {
                    arr[j] = arr[j - 1];
                }
                arr[pivotIndex] = temp;
                pivotIndex++;
            }
        }

        return pivotIndex;
    }

    private static void swap(Integer[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
