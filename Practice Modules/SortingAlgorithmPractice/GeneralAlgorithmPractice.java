package SortingAlgorithmPractice;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import org.apache.commons.collections4.bag.HashBag;

public class GeneralAlgorithmPractice {

    //? Insertion Sort
    public static <E extends Comparable<E>> void insertionSort(E[] array, int size){
        for (int i = 1,j; i < size; i++){
            E temporalLow = array[i];
            for(j = i; j > 0 && temporalLow.compareTo(array[j-1]) < 0; j--){
                array[j] = array[j-1];
            }
            array[j] = temporalLow;
        }

    }
    //? Selection Sort
    /*
    This method usually works by first grabbing the lowest index (0) and then comparing the subarray left to find the
    lowest element before moving it back to the lowest index.
     */
    public static <E extends Comparable<E>> void selectionSort(E[] array, int size){
        for(int i = 0,j, least; i < size - 1; i++){
            E temporalLow = array[i];
            for(j = i+1, least = i; j < size; j++){
                if (array[j].compareTo(array[least]) < 0){
                    least = j;
                }
            }
            E temporalLeast = array[least];
            array[i] = temporalLeast;
            array[least] = temporalLow;
        }
    }

    //? Bubble Sort
    public static <E extends Comparable<E>> void bubbleSort(E[] data, int size){
        boolean swapped = true;
        for(int i = 0; i < size -1 && swapped; i++){
            for(int j = size - 1; j > i; --j){
                swapped = false;
                if (data[j].compareTo(data[j-1]) < 0){
                    E temporalJ = data[j];
                    E teoporalJminusOne = data[j-1];
                    data[j] = teoporalJminusOne;
                    data[j-1] = temporalJ;
                    swapped = true;
                }
            }
        }
    }

    //? Merge Sort
    public static <E extends Comparable<E>> void mergeSort(E[] data){
        mergeSortInnerHelper(data);
    }

    public static <E extends Comparable<E>> void mergeSortInnerHelper(E[] data){

        if (data.length <= 1){return;}
        //! First Half Up to but without including mid
        int mid = data.length / 2;
        E[] leftHalf = (E[]) Array.newInstance(data.getClass().getComponentType(),mid);
        System.arraycopy(data, 0, leftHalf, 0, mid);
        mergeSortInnerHelper(leftHalf);

        //! Second Half Up to And Including the end
        int rightLength = data.length - mid;
        E[] rightHalf = (E[]) Array.newInstance(data.getClass().getComponentType(), rightLength);
        System.arraycopy(data,mid,rightHalf,0,rightLength);
        mergeSortInnerHelper(rightHalf);

        //! Merge the two halves
        E[] temporal = merge(leftHalf, rightHalf);
        System.arraycopy(temporal, 0, data, 0, temporal.length);
    }

    public static  <E extends Comparable<E>> E[] merge(E[] leftHalf, E[] rightHalf){
        //! Create a temp storage solution and also pointers
        E[] temp = (E[]) Array.newInstance(leftHalf.getClass().getComponentType(), leftHalf.length + rightHalf.length);
        int currentRight = 0, currentLeft = 0, currentTemp = 0;

        while (currentRight < rightHalf.length && currentLeft < leftHalf.length){
            if (leftHalf[currentLeft].compareTo(rightHalf[currentRight]) < 0){
                temp[currentTemp++] = leftHalf[currentLeft++];
            }
            else {
                temp[currentTemp++] = rightHalf[currentRight++];
            }
        }

        //! Add any left over elements
        while (currentLeft < leftHalf.length){
            temp[currentTemp++] = leftHalf[currentLeft++];
        }

        while (currentRight < rightHalf.length){
            temp[currentTemp++] = rightHalf[currentRight++];
        }

        return temp;
    }

    public static <E extends Comparable<E>> void quickSort(E[] data){
        //! Move the largest element to the end of the list to avoid the O(n^2) case
        int max = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i].compareTo(data[max]) >0){max = i;}
        }
        swap(data, max, data.length -1);
        //! Call the helper function
        quickSortHelper(data, 0, data.length - 2);

    }
    private static <E extends Comparable<E>> void quickSortHelper(E[] data, int left, int right){
        //! Base case is when the length is no longer suitable
        if (left >= right){
            return;
        }
        //? Declare variables
        int lower = left + 1;
        int upper = right;
        swap(data, left, (left+right )/2);
        E pivot = data[left];

        while (lower <= upper){
            while (pivot.compareTo(data[lower]) > 0){lower++;}
            while (pivot.compareTo(data[upper]) <0 ){upper--;}
            if (lower < upper){
                swap(data, lower++, upper--);
            }
            else {lower++;}
        }
        swap(data, left,   upper);

        if (left < upper - 1){quickSortHelper(data, left, upper -1);}
        if (upper + 1 < right){quickSortHelper(data, upper + 1, right);}
    }
    private static <T> void swap(T[] data, int i, int j) {
        T temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] arr = {3,1,9,10,11,4,5,5,2,7,6,8};
        insertionSort(arr, arr.length);
        System.out.println(Arrays.asList(arr));
        Double[] arr2 = {24.5,12.24,12.25,13.5,11.0,10.0};
        System.out.println(arr2.length);
        selectionSort(arr2, arr2.length);
        System.out.println(Arrays.asList(arr2));
        String[] arr3 = {"hello","world","Santiago","Armchair","Sun","hELLO"};
        bubbleSort(arr3, arr3.length);
        System.out.println(Arrays.asList(arr3));
        Collections.shuffle(Arrays.asList(arr3));
        mergeSort(arr3);
        System.out.println(Arrays.asList(arr3));
        Integer[] integers = {10,5,4,23,4,1,5,7,8,9};
        quickSort(integers);
        System.out.println(Arrays.asList(integers));
    }
}
