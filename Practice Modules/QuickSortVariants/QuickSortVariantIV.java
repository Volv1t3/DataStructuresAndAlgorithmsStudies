package QuickSortVariants;

public class QuickSortVariantIV {

    public static void sort(Integer[] arr){
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(Integer[] arr, int low, int high){
        if(low < high){
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }
    private static int partition(Integer[] arr, int low, int high){
        int pivot = arr[high];
        int i = low;
        for(int j = low; j < high; j++){
            if(arr[j] <= pivot){
                if (i != j){
                    swap(arr, i, j);
                }
                i++;
            }
        }
        if (i != high) {
            swap(arr, i, high);
        }
        return i;
    }


    private static void swap(Integer[] arr, int i, int j) {
        int temp = arr[i];
         arr[i] = arr[j];
        arr[j] = temp;
    }

}
