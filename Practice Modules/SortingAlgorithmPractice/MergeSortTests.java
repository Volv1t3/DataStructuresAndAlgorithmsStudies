package SortingAlgorithmPractice;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Comparator;

/**
 * Test class for MergeSort
 */
public class MergeSortTests {

    @Test
    public void mergeSort_WithSortedArray_ReturnsSortedArray() {
        Integer[] data = {1, 2, 3, 4, 5};
        MergeSort.mergeSort(data, data.length);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, data);
    }

    @Test
    public void mergeSort_WithReverseSortedArray_ReturnsSortedArray() {
        Integer[] data = {5, 4, 3, 2, 1};
        MergeSort.mergeSort(data, data.length);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, data);
    }

    @Test
    public void mergeSort_WithUnsortedArray_ReturnsSortedArray() {
        Integer[] data = {4, 2, 5, 3, 1};
        MergeSort.mergeSort(data, data.length);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, data);
    }

    @Test
    public void mergeSort_WithDuplicateElements_ReturnsSortedArray() {
        Integer[] data = {3, 5, 2, 5, 1};
        MergeSort.mergeSort(data, data.length);
        assertArrayEquals(new Integer[]{1, 2, 3, 5, 5}, data);
    }

    @Test
    public void mergeSort_WithNullArray_ThrowsNullPointerException() {
        Integer[] data = null;
        assertThrows(NullPointerException.class, () -> MergeSort.mergeSort(data, 0));
    }

    @Test
    public void mergeSort_WithEmptyArray_ThrowsIllegalArgumentException() {
        Integer[] data = {};
        assertThrows(IllegalArgumentException.class, () -> MergeSort.mergeSort(data, 0));
    }

    @Test
    public void mergeSort_WithSingleElementArray_ReturnsSameArray() {
        Integer[] data = {1};
        MergeSort.mergeSort(data, data.length);
        assertArrayEquals(new Integer[]{1}, data);
    }

    @Test
    public void mergeSortComparator_WithCustomComparator_ReturnsSortedArray() {
        String[] data = {"pear", "apple", "orange"};
        MergeSort.mergeSort(data, data.length, new ReverseStringComparator());
        assertArrayEquals(new String[]{"pear", "orange", "apple"}, data);
    }

    @Test
    public void mergeSortComparator_WithNullArray_ThrowsNullPointerException() {
        String[] data = null;
        assertThrows(NullPointerException.class, () -> MergeSort.mergeSort(data, 0, new ReverseStringComparator()));
    }

    @Test
    public void mergeSortComparator_WithEmptyArray_ThrowsIllegalArgumentException() {
        String[] data = {};
        assertThrows(IllegalArgumentException.class, () -> MergeSort.mergeSort(data, 0, new ReverseStringComparator()));
    }

    @Test
    public void mergeSortComparator_WithNullComparator_ThrowsNullPointerException() {
        String[] data = {"pear", "apple", "orange"};
        Comparator<String> comparator = null;
        assertThrows(NullPointerException.class, () -> MergeSort.mergeSort(data, data.length, comparator));
    }

    @Test
    public void mergeSortComparator_WithSingleElementArray_ReturnsSameArray() {
        String[] data = {"apple"};
        MergeSort.mergeSort(data, data.length, new ReverseStringComparator());
        assertArrayEquals(new String[]{"apple"}, data);
    }

    // Comparator for sorting strings in reverse order
    private static class ReverseStringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o2.compareTo(o1);
        }
    }
}