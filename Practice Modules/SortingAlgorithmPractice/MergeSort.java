package SortingAlgorithmPractice;


import javax.xml.stream.events.StartDocument;
import java.awt.image.DataBuffer;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * @Datasheet: MergeSort.java
 * @Author: Santiago Arellano
 * @Date: October 4th, 2024.
 * @Description: The present file contains information about the generic, recursive implementation of merge sort. It works
 * by dividing by half the array and subsequently each half until we have arrays with one singular element. Our implementations
 * will focus on allowing both <code>Comparable and Comparator</code> interfaces to be accepted.
 * <p>The methods implemented here will provide memory optimizations and recursion optimizations presented in the Advanced
 * Algorithms studies</p>
 * @apiNote : <b>The program will be implemented to be usable through Consumer references based on either generic arrays
 * and generic arrays with size implementations</b>
 * @Version: 1.0
 * @Since: 1.0
 * @See: XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
 */
public class MergeSort {


    /**
     * Merge sort implementation in which we accept an external array, an array size and we can only work with data types
     * which extend <code>Comparable</code>
     * @param externalData: The external data array to be sorted.
     * @param arraySize: The size of the external data array.
     * @apiNote : The method will throw an exception if the external data array is null or empty, or if the array size is zero.
     * @param <DataType>: The generic type of the external data array.
     * @return : void
     * @throws NullPointerException: If the external data array is null.
     * @throws IllegalArgumentException: If the external data array is empty or the array size is zero.
     */
    public static <DataType extends Comparable<DataType>> void mergeSort(DataType[] externalData,
                                                                         int arraySize)
    throws NullPointerException,IllegalArgumentException
    {
        //! Base Checks
        boolean isExternalArrayNull = externalData == null;
        boolean isExternalArrayEmpty = (isExternalArrayNull ? 0: externalData.length) == 0;
        boolean isArraySizeZero = arraySize == 0;
        if (isExternalArrayNull)
        {
            throw new NullPointerException("The external data array is null");
        }
        else if (isExternalArrayEmpty)
        {
            throw new IllegalArgumentException("The external data array is empty");
        }
        else if (isArraySizeZero)
        {
            throw new IllegalArgumentException("The array size is zero");
        }

        mergeSortInnerHelper(externalData);
    }

    /**
     * Merge sort implementation in which we accept an external array and we can only work with data types
     * which extend <code>Comparable</code>
     * @param externalData: The external data array to be sorted.
     * @apiNote: The method will throw an exception if the external data array is null or empty.
     * @param <DataType>: The generic type of the external data array.
     * @return: void
     * @throws NullPointerException: If the external data array is null.
     * @throws IllegalArgumentException: If the external data array is empty.
     */
    public static <DataType extends Comparable<DataType>> void mergeSort(DataType[] externalData)
    {
        mergeSort(externalData, (externalData != null ? externalData.length: 0));
    }

    /**
     * Merge sort implementation in which we accept an external array, an array size and we can only work with data types
     * which extend <code>Comparator</code>
     * @param externalData: The external data array to be sorted.
     * @param arraySize: The size of the external data array.
     * @param comparatorInstance: The comparator instance to be used for sorting.
     * @apiNote: The method will throw an exception if the external data array is null or empty, or if the array size is zero.
     * @param <DataType>: The generic type of the external data array.
     * @return: void
     * @throws NullPointerException: If the external data array or the comparator instance is null.
     * @throws IllegalArgumentException: If the external data array is empty or the array size is zero.
     */
    @SuppressWarnings("unchecked")
    public static <DataType> void mergeSort(DataType[] externalData,
                                            int arraySize,
                                            Comparator<DataType> comparatorInstance)
    {
        //! Base Checks
        boolean isExternalArrayNull = externalData == null;
        boolean isComparatorNull = comparatorInstance == null;
        boolean isExternalArrayEmpty = (isExternalArrayNull ? 0: externalData.length) == 0;
        boolean isArraySizeZero = arraySize == 0;
        if (isExternalArrayNull || isComparatorNull)
        {
            throw new NullPointerException("The external data array or " +
                    "comparator instance is null");
        }
        else if (isExternalArrayEmpty)
        {
            throw new IllegalArgumentException("The external data array is empty");
        }
        else if (isArraySizeZero)
        {
            throw new IllegalArgumentException("The array size is zero");
        }

        mergeSortInnerHelper(externalData, comparatorInstance);
    }


    private static <E extends Comparable<E>> void mergeSortInnerHelper(E[] list) {

        if (list.length > 1) {
            // Merge sort the first half
            int mid = list.length / 2;
            E[] firstHalf = (E[]) Array.newInstance(list.getClass().getComponentType(), mid);
            /**
             * The method here, array copy moves the first half of the array UP TO BUT WITHOUT INCLUDING the mid index
             */
            System.arraycopy(list, 0, firstHalf, 0, mid); //This method is exclusive

            mergeSortInnerHelper(firstHalf);

            // Merge sort the second half
            int secondHalfLength = list.length - mid;
            E[] secondHalf = (E[]) Array.newInstance(list.getClass().getComponentType(), secondHalfLength);
            System.arraycopy(list, mid, secondHalf, 0, secondHalfLength);
            mergeSortInnerHelper(secondHalf);

            // Merge firstHalf with secondHalf
            E[] temp = mergeTwoArraysHelper(firstHalf, secondHalf);
            System.arraycopy(temp, 0, list, 0, temp.length);
        }
    }
    private static <E extends Comparable<E>> E[] mergeTwoArraysHelper(E[] list1, E[] list2) {
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

        //Leftover elements on list1 are appended to the end of temp
        while (current1 < list1.length) {
            temp[current3++] = list1[current1++];
        }

        //Leftovers elements on list2 are appended to the end of temp
        while (current2 < list2.length) {
            temp[current3++] = list2[current2++];
        }

        //! returning temp to the original caller to keep merging and sorting
        return temp;
    }
    private static <E> void mergeSortInnerHelper(E[] list, Comparator<E> instance) {
        if (list.length > 1) {
            // Merge sort the first half
            int mid = list.length / 2;
            E[] firstHalf = (E[]) Array.newInstance(list.getClass().getComponentType(), mid);
            System.arraycopy(list, 0, firstHalf, 0, mid);
            mergeSortInnerHelper(firstHalf, instance);

            // Merge sort the second half
            int secondHalfLength = list.length - mid;
            E[] secondHalf = (E[]) Array.newInstance(list.getClass().getComponentType(), secondHalfLength);
            System.arraycopy(list, mid, secondHalf, 0, secondHalfLength);
            mergeSortInnerHelper(secondHalf, instance);

            // Merge firstHalf with secondHalf
            E[] temp = mergeTwoArraysHelper(firstHalf, secondHalf, instance);
            System.arraycopy(temp, 0, list, 0, temp.length);
        }
    }
    private static <E> E[] mergeTwoArraysHelper(E[] list1, E[] list2, Comparator<E> instance) {
        E[] temp = (E[]) Array.newInstance(list1.getClass().getComponentType(), list1.length + list2.length);

        int current1 = 0; // Index in list1
        int current2 = 0; // Index in list2
        int current3 = 0; // Index in temp

        while (current1 < list1.length && current2 < list2.length) {
            if ((instance.compare(list1[current1], list2[current2]) < 0)) {
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


    public static <Datatype extends Comparable<Datatype>> void inPlaceMergeSort(Datatype[] externalData, int arraySize)
            throws NullPointerException
    {
        if (externalData == null){ throw new NullPointerException("The external data array is null"); }
        if (externalData.length == 0 || arraySize == 0) { throw new IllegalArgumentException("The external data array is empty or the array size is zero"); }
        if (externalData.length != arraySize) { throw new IllegalArgumentException("The external data array size does not match the array size"); }

        inPlaceMergeSort(externalData, 0, externalData.length -1);

    }

    private static <DataType extends Comparable<DataType>> void inPlaceMergeSort(DataType[] externalData,
                                                                                int start, int end)
    {
        if (start >= end) {return;}

        //! Find the mid value
        int mid = (start + end) / 2;

        //! Recursively sort first and second halves in place
        inPlaceMergeSort(externalData, start, mid);
        inPlaceMergeSort(externalData, mid + 1, end);

        //! Merge he two halves in-place
        mergeInPlace(externalData, start, mid, end);
    }

    private static <DataType extends Comparable<DataType>> void mergeInPlace(DataType[] externalData, int start, int mid,
                                                                             int end)
    {
        //! Start index for first subarray
        int firstSubArrayStart = mid + 1;

        if (externalData[mid].compareTo(externalData[firstSubArrayStart]) <= 0) {
            return;
        }

        //! Return if the direct merge is already sorted
        while (start <= mid && firstSubArrayStart <= end){
            if (externalData[start].compareTo(externalData[firstSubArrayStart]) <= 0) {
                start++;
            } else {
                var value = externalData[firstSubArrayStart];
                var index = firstSubArrayStart;

                //! Mover todos los elemenos entre start y el nuevo start por un indice
                while (index != start) {
                    externalData[index] = externalData[index - 1];
                    index--;
                }

                externalData[start] = value;

                //! Update all pointers
                start++;
                mid++;
                firstSubArrayStart++;
            }
        }
    }


    public static void main(String[] args) {
        Integer[] array = {3,1,9,7,8,10,2};
        mergeSort(array, array.length);
        System.out.println(Arrays.toString(array));
    }
}
