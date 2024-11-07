package SortingAlgorithmPractice;


import org.apache.commons.math3.stat.descriptive.rank.Median;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * @Datasheet: QuickSort.java
 * @Author: Santiago Arellano
 * @Date: October 4th, 2024.
 * @Description: The present file will hold two direct implementations of Quick Sort, one for comparable and another
 * for a comparator interface implementation. The broader implementation of QuickSort has been taken from C++ Data Structures
 * and Algorithms. This file will contain further refinements to the algorithm and simplifications done to it.
 * @apiNote : <b>The program will be implemented to be usable through Consumer references based on either generic arrays
 * and generic arrays with size implementations</b>
 * @Version: 1.0
 * @Since: 1.0
 * @See: XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
 */
public class QuickSort {


    /**
     * @param externalData: The external data array to be sorted.
     * @apiNote: The method will throw an exception if the external data array is null or empty.
     * @param <T>: The generic type of the external data array.
     * @return: void
     * @throws NullPointerException: If the external data array is null.
     * @throws IllegalArgumentException: If the external data array is empty.
     */
    public static <T extends Comparable<T>> void basicQuickSort(T[] externalData)
            throws IllegalArgumentException, NullPointerException
    {
        if (externalData == null)
        {
            throw new NullPointerException("The external data array is null.");
        }
        if (externalData.length == 0)
        {
            throw new IllegalArgumentException("The external data array is empty.");
        }

        int arrayLength = externalData.length;
        if (arrayLength < 2) return; // Boundary condition: if the array has fewer than 2 elements, it's already sorted

        // Find the largest element and move it to the last position
        int max = 0;
        for (int i = 1; i < arrayLength; i++) {
            if (externalData[max].compareTo(externalData[i]) < 0) {
                max = i;
            }
        }
        swap(externalData, arrayLength - 1, max); // Move the largest element to the end

        // Call the recursive quicksort function to sort the rest of the array
        basicQuickSortHelper(externalData, 0, arrayLength - 2);
    }
    private static <T extends Comparable<T>> void basicQuickSortHelper(T[] externalData, int first, int last) {
        if (first >= last) return; // Base condition: if the segment has 0 or 1 elements, it's already sorted

        int lower = first + 1;
        int upper = last;

        swap(externalData, first, (first + last) / 2); // Move the pivot (middle element) to the first position
        T bound = externalData[first]; // Pivot element
        // Partition the array6

        while (lower <= upper) {
            while (bound.compareTo(externalData[lower]) > 0) lower++;
            while (bound.compareTo(externalData[upper]) < 0) upper--;
            if (lower < upper) {
                swap(externalData, lower++, upper--);
            } else {
                lower++;
            }
        }

        swap(externalData, upper, first); // Place the pivot in its correct position

        // Recursively sort the partitions
        if (first < upper - 1) basicQuickSortHelper(externalData, first, upper - 1);
        if (upper + 1 < last) basicQuickSortHelper(externalData, upper + 1, last);
    }

    private static <T> void swap(T[] data, int i, int j) {
        T temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /**
     * @param externalData: The external data array to be sorted.
     * @param comparatorInstance: The comparator instance to be used for comparison.
     * @apiNote: The method will throw an exception if the external data array or comparator instance is null.
     * @param <DataType>: The generic type of the external data array.
     * @return: void
     * @throws NullPointerException: If the external data array or comparator instance is null.
     * @throws IllegalArgumentException: If the external data array is empty.
     */
    public static <DataType> void basicQuickSort(DataType[] externalData, Comparator<DataType> comparatorInstance)
    {
        if (externalData == null || comparatorInstance == null)
        {
            throw new NullPointerException("The external data array or " +
                    "comparator instance is null");
        }
        if (externalData.length == 0)
        {
            throw new IllegalArgumentException("The external data array is empty.");
        }

        int arrayLength = externalData.length;
        if (arrayLength < 2) return; // Boundary condition: if the array has fewer than 2 elements, it's already sorted

        // Find the largest element and move it to the last position
        int max = 0;
        for (int i = 1; i < arrayLength; i++) {
            if (comparatorInstance.compare(externalData[max],(externalData[i])) < 0) {
                max = i;
            }
        }
        swap(externalData, arrayLength - 1, max); // Move the largest element to the end

        // Call the recursive quicksort function to sort the rest of the array
        basicQuickSortHelper(externalData, 0, arrayLength - 2, comparatorInstance);
    }

    /**
     * @param externalData: The external data array to be sorted.
     * @param first: The first index of the segment to be sorted.
     * @param last: The last index of the segment to be sorted.
     * @param comparatorInstance: The comparator instance to be used for comparison.
     * @apiNote: The method will throw an exception if the external data array is null or empty.
     * @param <DataType>: The generic type of the external data array.
     * @return: void
     * @throws NullPointerException: If the external data array or comparator instance is null.
     * @throws IllegalArgumentException: If the external data array is empty.
     */
    public static <DataType> void basicQuickSortHelper(DataType[] externalData, int first, int last, Comparator<DataType>
                                                 comparatorInstance)
    {
        if (first >= last) return; // Base condition: if the segment has 0 or 1 elements, it's already sorted

        int lower = first + 1;
        int upper = last;

        swap(externalData, first, (first + last) / 2); // Move the pivot (middle element) to the first position
        DataType bound = externalData[first]; // Pivot element

        // Partition the array
        while (lower <= upper) {
            while (comparatorInstance.compare(bound,(externalData[lower])) > 0) lower++;
            while (comparatorInstance.compare(bound, (externalData[upper])) < 0) upper--;
            if (lower < upper) {
                swap(externalData, lower++, upper--);
            } else {
                lower++;
            }
        }
        swap(externalData, upper, first); // Place the pivot in its correct position

        // Recursively sort the partitions
        if (first < upper - 1) basicQuickSortHelper(externalData, first, upper - 1, comparatorInstance);
        if (upper + 1 < last) basicQuickSortHelper(externalData, upper + 1, last, comparatorInstance);
    }


    public static <DataType extends Comparable<DataType>> void medianQuickSort(DataType[] externalData,
                                                                          int arraySize)
            throws NullPointerException, IllegalArgumentException
    {
        //! Given the implementation requirements, we can still do the same logic as before, we can find the largest element
        // and proceed to move it over to the end of the array like before
        if (externalData == null) {
            throw new NullPointerException("The external data array is null.");
        }
        if (externalData.length == 0 || arraySize == 0) {
            throw new IllegalArgumentException("The external data array is empty or the array size is zero.");
        }
        if (externalData.length != arraySize)
        {
            throw new IllegalArgumentException("The array size does not match the external data array size.");
        }

        //! Moving the largest index to the back of the list
        int max = 0;
        for (int i =0; i < arraySize; i++)
        {
            if (externalData[max].compareTo(externalData[i]) < 0) max = i;
        }
        swap(externalData, externalData.length -1,max );

        //! Use the median as the partition point
        medianQuickSortHelper(externalData, 0, arraySize - 2);
    }
    public static <DataType extends Comparable<DataType>> void medianQuickSortHelper(DataType[] externalData,
                                                                                     int first,
                                                                                     int last)
    {
        if (first >= last) return; // Base condition: if the segment has 0 or 1 elements, it's already sorted
        // Median-of-three pivot selection
        //! Iterate and find the median between the mid, low and high indices

        int lowIndex = first;
        int highIndex = last;
        int midIndex = (first + last ) / 2;
        DataType low = externalData[lowIndex];
        DataType mid = externalData[midIndex];
        DataType high = externalData[highIndex];
        //! Calculate the median of the three values
        ArrayList<DataType> col = new ArrayList<>(3){
            {
                add(low);
                add(mid);
                add(high);
            }
        };
        Collections.sort(col);
        DataType median = col.get(1);
        int medianIndex = 0;

        if (median == mid) {medianIndex = midIndex;}
        else {medianIndex = highIndex;}


        // Move the median value to the 'first' position to use as pivot
        swap(externalData, first, medianIndex);

        //! Using the partitioning function to delimit the array midpoint
        int partitionIndex = medianPartitioningHelper(externalData, first, last);


        //! Recursive partition call
        medianQuickSortHelper(externalData, first, partitionIndex - 1);
        medianQuickSortHelper(externalData, partitionIndex + 1, last);
    }

    private static <DataType extends Comparable<DataType>> int medianPartitioningHelper(DataType[] externalData,
                                                                                         int first,
                                                                                         int last)
    {
        DataType pivot = externalData[first];
        int lower = first + 1;
        int upper = last;

        //! FindingCorrect Partitioning index
        while (lower <= upper) {
            while (lower <= upper && externalData[lower].compareTo(pivot) <= 0) lower++;
            while (lower <= upper && externalData[upper].compareTo(pivot) >= 0) upper--;
            if (lower < upper) {
                swap(externalData, lower++, upper--);
            }
        }


        //! Move the median value to the 'first' position to use as pivot
        swap(externalData, first, upper);
        return upper;
    }

    public static  <E extends Comparable<E>> void inPlaceNonRecursiveQuickSort(E[] externalData)
            throws NullPointerException, IllegalArgumentException{
        if (externalData ==null){
            throw new NullPointerException("The external data array is null.");
        }
        if (externalData.length <= 1){
            throw new IllegalArgumentException("The external data array is empty.");
        }

        int[] storedPivots = new int[externalData.length + 1];
        storedPivots[externalData.length] = externalData.length;
        int from = 0;
        int to = externalData.length;
        int top = externalData.length;

        while (externalData.length - from > 1){
            if ((to - from )<= 1) {
                from = to + 1;
                top++;
                to = storedPivots[top];
            }
            else{
                E pivot = externalData[from];
                int partitionIndex = partition(externalData, from +1, to, pivot);
                swap(externalData, from, partitionIndex -1);
                top--;
                storedPivots[top] = partitionIndex - 1;
                to = partitionIndex - 1;
            }

        }
    }
    private static <E extends Comparable<E>> int partition(E[] a, int from, int to, E pivot) {
        int lower = from;
        int upper = to - 1;

        while (true) {
            while ( lower <= upper && a[lower].compareTo(pivot) < 0) {
                lower++;
            }
            while ( lower <= upper && a[upper].compareTo(pivot) >= 0) {
                upper--;
            }
            if (lower > upper) {
                return lower;
            }
            swap(a, lower++, upper--);
        }
    }




    public static void main(String[] args) {
        Integer[] arr = IntStream.rangeClosed(0,20).boxed().toArray(Integer[]::new);
        Collections.shuffle(Arrays.asList(arr));
        System.out.println(Arrays.toString(arr));
        inPlaceNonRecursiveQuickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
