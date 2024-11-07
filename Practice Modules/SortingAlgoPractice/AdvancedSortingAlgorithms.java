package SortingAlgoPractice;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class AdvancedSortingAlgorithms {

    public static void main(String[] args) {
        Integer[] arr = {5, 2, 8, 1, 9, 3};
        System.out.println("Unsorted array: ");
        System.out.println(Arrays.asList(arr));
        shellSort(arr, arr.length);
        System.out.println("Sorted array: ");
        System.out.println(Arrays.stream(arr).toList());
        System.out.println();
        Collections.shuffle(Arrays.asList(arr));
        System.out.println("Unsorted array: ");
        System.out.println(Arrays.stream(arr).toList());
        System.out.println();
        quicksort(arr);
        System.out.println("Sorted array with quickSort: ");
        System.out.println(Arrays.stream(arr).toList());
        System.out.println();
        Collections.shuffle(Arrays.asList(arr));
        System.out.println("Unsorted array: ");
        System.out.println(Arrays.stream(arr).toList());
        System.out.println();
        long[] longs = {5, 2, 8, 1, 9, 3,3,8};
        radixSort(longs, longs.length);
        System.out.println("Sorted array: ");
        System.out.println(Arrays.toString(longs));
        System.out.println();
        Collections.shuffle(Arrays.stream(longs).boxed().collect(Collectors.toList()));
        System.out.println("Unsorted array: ");
        System.out.println(Arrays.toString(longs));
        System.out.println();
        longs = new long[]{5, 2, 8, 1, 9, 3};
        countingSort(longs, longs.length);
        System.out.println("Sorted array: ");
        System.out.println(Arrays.toString(longs));

        System.out.println();
        Collections.shuffle(Arrays.stream(longs).boxed().collect(Collectors.toList()));
        System.out.println("Unsorted array: ");
        System.out.println(Arrays.toString(longs));
        System.out.println();
        Integer[] ints = {5, 2, 8, 1, 9, 3};
        System.out.println("Unsorted array: ");
        System.out.println(Arrays.asList(ints));
        mergeSort(ints);
        System.out.println("Sorted array: ");
        System.out.println(Arrays.stream(ints).toList());

    }


        public static <T extends Comparable<T>> void shellSort(T[] data, int size) {
            int[] increments = new int[20];
            int h, i, j, k, hCnt;

            // Generate a sequence of increments
            for(h = 1, i = 0; h < size; i++) {
                increments[i] = h;
                h = 3 * h + 1;
            }

            // Loop through the increments in reverse order
            for(i--; i >= 0; i--) {
                h = increments[i];
                // Loop on the number of subarrays h-sorted in ith pass
                for (hCnt = h; hCnt < 2 * h; hCnt++) {
                    // Insertion sort for the subarray containing every h-th element of data
                    for(j = hCnt; j < size;) {
                        T tmp = data[j];
                        k = j;
                        while (k - h >= 0 && tmp.compareTo(data[k - h]) < 0) {
                            data[k] = data[k - h];
                            k -= h;
                        }
                        data[k] = tmp;
                        j += h;
                    }
                }
            }
        }
    public static <T extends Comparable<T>> void quicksort(T[] data) {
        int n = data.length;
        if (n < 2) return; // Boundary condition: if the array has fewer than 2 elements, it's already sorted

        // Find the largest element and move it to the last position
        int max = 0;
        for (int i = 1; i < n; i++) {
            if (data[max].compareTo(data[i]) < 0) {
                max = i;
            }
        }
        swap(data, n - 1, max); // Move the largest element to the end

        // Call the recursive quicksort function to sort the rest of the array
        quicksort(data, 0, n - 2);
    }
    private static <T extends Comparable<T>> void quicksort(T[] data, int first, int last) {
        if (first >= last) return; // Base condition: if the segment has 0 or 1 elements, it's already sorted

        int lower = first + 1;
        int upper = last;

        swap(data, first, (first + last) / 2); // Move the pivot (middle element) to the first position
        T bound = data[first]; // Pivot element

        // Partition the array
        while (lower <= upper) {
            while (bound.compareTo(data[lower]) > 0) lower++;
            while (bound.compareTo(data[upper]) < 0) upper--;
            if (lower < upper) {
                swap(data, lower++, upper--);
            } else {
                lower++;
            }
        }
        swap(data, upper, first); // Place the pivot in its correct position

        // Recursively sort the partitions
        if (first < upper - 1) quicksort(data, first, upper - 1);
        if (upper + 1 < last) quicksort(data, upper + 1, last);
    }

    private static <T> void swap(T[] data, int i, int j) {
        T temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

        public static void radixSort(long[] data, int n) {
            final int radix = 10;
            final int digits = 10; // The maximum number of digits for a long

            // Create an array of 10 queues
            LinkedList<Long>[] queues = new LinkedList[radix];
            for (int i = 0; i < radix; i++) {
                queues[i] = new LinkedList<>();
            }

            for (int d = 0, factor = 1; d < digits; factor *= radix, d++) {
                // Distribute the numbers into buckets
                for (int j = 0; j < n; j++) {
                    int bucketIndex = (int) ((data[j] / factor) % radix);
                    queues[bucketIndex].add(data[j]);
                }

                // Collect the numbers from the buckets and put them back into the array
                int k = 0;
                for (int j = 0; j < digits; j++) {
                    while (!queues[j].isEmpty()) {
                        data[k++] = queues[j].poll();
                    }
                }
            }
        }

    static void countingSort(long[] data, final int size) {
        int i;
        long largest = data[0];
        long[] tmp = new long[size];
        //! Finding the largest subarray in order to create the count array
        for (i = 1; i < size; i++) {
            if (largest < data[i]) {
                largest = data[i];
            }
        }
        //! Creating and Generating the array count
        long[] count = new long[(int) (largest + 1)];
        Arrays.fill(count, 0);
        //! Updating the count given their appearance in the data array
        for (i = 0; i < size; i++) {
            count[(int) data[i]]++;
        }
        //! Count numbers <=1
        for (i = 1; i <= largest; i++) {
            count[i] = count[i - 1] + count[i];
        }
        //! Put the numbers in the temp array
        for (i = size - 1; i >= 0; i--) {
            tmp[(int) count[(int) (data[i] )]- 1] = data[i];
            count[(int) data[i]]--;
        }
        //! Transfer the numbers in their ordering to tmp[]
        for (i = 0; i < size; i++) {
            data[i] = tmp[i];
        }
    }

    public static <E extends Comparable<E>> void mergeSort(E[] list) {
        if (list.length > 1) {
            // Merge sort the first half
            int mid = list.length / 2;
            E[] firstHalf = (E[]) Array.newInstance(list.getClass().getComponentType(), mid);
            System.arraycopy(list, 0, firstHalf, 0, mid);
            mergeSort(firstHalf);

            // Merge sort the second half
            int secondHalfLength = list.length - mid;
            E[] secondHalf = (E[]) Array.newInstance(list.getClass().getComponentType(), secondHalfLength);
            System.arraycopy(list, mid, secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);

            // Merge firstHalf with secondHalf
            E[] temp = merge(firstHalf, secondHalf);
            System.arraycopy(temp, 0, list, 0, temp.length);
        }
    }

    private static <E extends Comparable<E>> E[] merge(E[] list1, E[] list2) {
        E[] temp = (E[]) Array.newInstance(list1.getClass().getComponentType(), list1.length + list2.length);

        int current1 = 0; // Index in list1
        int current2 = 0; // Index in list2
        int current3 = 0; // Index in temp

        while (current1 < list1.length && current2 < list2.length) {
            if ((list1[current1].compareTo(list2[current2])) < 0) {
                temp[current3++] = list1[current1++];
            } else {
                temp[current3++] = list2[current2++];
            }
        }

        while (current1 < list1.length) {
            temp[current3++] = list1[current1++];
        }

        while (current2 < list2.length) {
            temp[current3++] = list2[current2++];
        }

        return temp;
    }
}


