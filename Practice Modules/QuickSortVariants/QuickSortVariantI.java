package QuickSortVariants;

public class QuickSortVariantI {
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
        int pivot = arr[high]; //! Elegimos el ultimo elemento como pivote

        // Index of smaller element
        int i = (low - 1);

        // Compare each element with pivot
        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;    // increment index of smaller element
                swap(arr, i, j);
            }
        }

        // Place pivot in its correct position
        swap(arr, i + 1, high);

        return i + 1;
    }

    private static void swap(Integer[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
