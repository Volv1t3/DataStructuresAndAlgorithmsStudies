package QuickSortVariants;

import java.util.ArrayList;
import java.util.Collections;

public class MedianOfThreeQuickSort {

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
        int medianIndex = first;

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

    public static <DataType extends Comparable<DataType>> void swap(DataType[] arr, int i, int j){
        DataType temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
