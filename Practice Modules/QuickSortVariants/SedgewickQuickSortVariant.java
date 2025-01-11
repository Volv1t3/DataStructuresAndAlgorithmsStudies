package QuickSortVariants;


public class SedgewickQuickSortVariant {
    private static void swap(Integer[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void quicksort(Integer[] arr, int left, int right) {
        if (right - left < 1) return;

        // Initial check and swap if needed
        if (arr[left] > arr[right]) {
            swap(arr, left, right);
        }

        int i = left + 1;
        int k = left + 1;
        int j = right - 1;
        int v1 = arr[left];
        int v2 = arr[right];

        while (k <= j) {
            // Handle elements smaller than v1
            if (arr[k] < v1) {
                swap(arr, k, i);
                i++;
            }
            // Handle elements larger than v2
            else if (arr[k] > v2) {
                while (arr[j] > v2 && k < j) {
                    j--;
                }
                swap(arr, k, j);
                j--;

                if (arr[k] < v1) {
                    swap(arr, k, i);
                    i++;
                }
            }
            k++;
        }

        i--;
        j++;

        // Place the pivots in their final positions
        swap(arr, left, i);
        swap(arr, right, j);

        // Recursive calls
        quicksort(arr, left, i - 1);    // Sort left subarray
        quicksort(arr, i + 1, j - 1);   // Sort middle subarray
        quicksort(arr, j + 1, right);   // Sort right subarray
    }

    // Utility method to sort an array
    public static void sort(Integer[] arr) {
        if (arr == null || arr.length < 2) return;
        quicksort(arr, 0, arr.length - 1);
    }

}