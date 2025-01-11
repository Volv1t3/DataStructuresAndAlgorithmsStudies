package QuickSortVariants;

import org.apache.commons.collections4.ArrayStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TetraPivotQuickSort {

    public static List<Integer> sort(List<Integer> arr){
            var result = TetraPivotQuickSort(arr);
            arr.clear();
            return result;

    }

    private static List<Integer> TetraPivotQuickSort(List<Integer> arr) {
        int length = arr.size();
        if (length <= 1) {
            return arr;
        } else if (length == 2 || length == 3) {
            Collections.sort(arr);
            return arr;
        }
        List<Integer> pivots = new ArrayList<>(4) {{
            add(arr.removeFirst());
            add(arr.removeFirst());
            add(arr.removeFirst());
            add(arr.removeFirst());
        }};
        Collections.sort(pivots);
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();
        ArrayList<Integer> c = new ArrayList<>();
        ArrayList<Integer> d = new ArrayList<>();
        ArrayList<Integer> e = new ArrayList<>();
        particionInternaPorSegmentos(arr, pivots, a, b, c, d, e);

        ArrayList<Integer> result = new ArrayList<>();
        result.addAll(TetraPivotQuickSort(a));
        result.add(pivots.getFirst());
        result.addAll(TetraPivotQuickSort(b));
        result.add(pivots.get(1));
        result.addAll(TetraPivotQuickSort(c));
        result.add(pivots.get(2));
        result.addAll(TetraPivotQuickSort(d));
        result.add(pivots.get(3));
        result.addAll(TetraPivotQuickSort(e));

        return result;
    }

    private static void particionInternaPorSegmentos(List<Integer> arr,
                                                     List<Integer> pivots,
                                                     ArrayList<Integer> a,
                                                     ArrayList<Integer> b,
                                                     ArrayList<Integer> c,
                                                     ArrayList<Integer> d,
                                                     ArrayList<Integer> e) {
        for (Integer element : arr) {
            if (element < pivots.getFirst()) {
                a.add(element);
            } else if (pivots.getFirst() <= element && element < pivots.get(1)) {
                b.add(element);
            } else if (pivots.get(1) <= element && element < pivots.get(2)) {
                c.add(element);
            } else if (pivots.get(2) <= element && element < pivots.get(3)) {
                d.add(element);
            } else {
                e.add(element);
            }
        }
    }

    private static void swap(Integer[] arr, int i, int j){
        Integer temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
