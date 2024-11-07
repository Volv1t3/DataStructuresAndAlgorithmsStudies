package SortingAlgorithmPractice;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Comparator;

/**
 * Test Classes for Bubble Sort
 */
public class BubbleSortTests {

    @Test
    public void basicBubbleSort_WithSortedArray_ReturnsSameArray() {
        Integer[] data = { 1, 2, 3, 4, 5 };
        BubbleSort.basicBubbleSort(data, data.length);
        assertArrayEquals(new Integer[] { 1, 2, 3, 4, 5 }, data);
    }

    @Test
    public void basicBubbleSort_WithReverseSortedArray_ReturnsSortedArray() {
        Integer[] data = { 5, 4, 3, 2, 1 };
        BubbleSort.basicBubbleSort(data, data.length);
        assertArrayEquals(new Integer[] { 1, 2, 3, 4, 5 }, data);
    }

    @Test
    public void basicBubbleSort_WithUnsortedArray_ReturnsSortedArray() {
        Integer[] data = { 4, 2, 5, 3, 1 };
        BubbleSort.basicBubbleSort(data, data.length);
        assertArrayEquals(new Integer[] { 1, 2, 3, 4, 5 }, data);
    }

    @Test
    public void basicBubbleSort_WithNullArray_ThrowsNullPointerException() {
        Integer[] data = null;
        assertThrows(NullPointerException.class, () -> BubbleSort.basicBubbleSort(data, 0));
    }

    @Test
    public void basicBubbleSort_WithEmptyArray_ThrowsIllegalArgumentException() {
        Integer[] data = {};
        assertThrows(IllegalArgumentException.class, () -> BubbleSort.basicBubbleSort(data, 0));
    }

    @Test
    public void basicBubbleSortComparator_WithCustomComparator_ReturnsSortedArray() {
        String[] data = { "pear", "apple", "orange" };
        BubbleSort.basicBubbleSort(data, data.length, new ReverseStringComparator());
        assertArrayEquals(new String[] { "pear", "orange", "apple" }, data);
    }

    // Comparator for sorting strings in reverse order
    private static class ReverseStringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o2.compareTo(o1);
        }
    }
}