package SortingAlgoPractice;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ElementarySortingAlgorithms {

    public static void main(String[] args) {
        Integer[] arr = {5, 2, 8, 1, 9, 3,6,10,2,3,4,5,9};
        //! First Test: using insertion sort
        insertionSort(arr, arr.length);
        System.out.println("Insertion Sort:");
        System.out.println(Arrays.stream(arr).toList().toString());
        //! Second Test: using selection sort
        Collections.shuffle(Arrays.asList(arr));
        System.out.println("Unsorted Array:");
        System.out.println(Arrays.stream(arr).toList().toString());
        selectionSort(arr, arr.length);
        System.out.println("Selection Sort:");
        System.out.println(Arrays.stream(arr).toList().toString());
        //! Third Test: using comb sort
        Collections.shuffle(Arrays.asList(arr));
        System.out.println("Unsorted Array:");
        Collections.shuffle(Arrays.asList(arr));
        System.out.println(Arrays.stream(arr).toList().toString());
        combSearch(arr);
        System.out.println("Comb Sort:");
        System.out.println(Arrays.stream(arr).toList().toString());

        //! Fourth Test: using bubble sort
        Collections.shuffle(Arrays.asList(arr));
        System.out.println("Unsorted Array:");
        Collections.shuffle(Arrays.asList(arr));
        System.out.println(Arrays.stream(arr).toList().toString());
        bubbleSort(arr, arr.length);
        System.out.println("Bubble Sort:");
        bubbleSort(arr, arr.length);
        System.out.println(Arrays.stream(arr).toList().toString());
    }


    public static <T extends Comparable<T>> void insertionSort(T[] data, Integer size)
    {
        //! Opening main loop logic
        for(int i=1,j ; i < size; i++)
        {
            T temp = data[i];
            for(j = i; j > 0 && temp.compareTo(data[j-1]) < 0; j--)
            {
                data[j] = data[j-1];
            }
            data[j] =temp;
        }
    }

    public static <T extends Comparable<T>> void selectionSort(T[] data, int size)
    {
        //! Main Loop from data[i] to data[n-2]
        for(int i =0,j, least; i < size -1; i++)
        {
            T temp = data[i];
            for(j = i+1, least = i; j < size; j++)
            {
                if (data[j].compareTo(data[least]) < 0){least = j;}
            }
            T temp2 = data[least];
            data[least] = temp;
            data[i] = temp2;
        }
    }

    public static <T extends Comparable<T>> void combSearch(T[] data) {
        int n = data.length;
        int step = n;
        int j, k;

        while ((step = (int) (step / 1.3)) > 1) {
            for (j = n - 1; j >= step; j--) {
                k = j - step;
                if (data[j].compareTo(data[k]) < 0) {
                    T temp = data[j];
                    data[j] = data[k];
                    data[k] = temp;
                }
            }
        }

        boolean again = true;
        for (int i = 0; i < n - 1 && again; i++) {
            for (j = n - 1, again = false; j > 0; j--) {
                if (data[j].compareTo(data[j - 1]) < 0) {
                    T temp = data[j];
                    data[j] = data[j - 1];
                    data[j - 1] = temp;
                    again = true;
                }
            }
        }
    }
    public static <T extends Comparable<T>> void bubbleSort(T[] data, int size)
    {
        //! Main Loop
        boolean swapped = true;
        for(int i = 0; i < size - 1 && swapped; i++)
        {
            //! Secondary loop
            for(int j = size - 1; j > i; --j)
            {
                swapped = false;
                if(data[j].compareTo(data[j-1]) < 0)
                {
                    T tempJ = data[j];
                    T tempJMinusOne = data[j-1];
                    //Intercambio
                    data[j-1] = tempJ;
                    data[j] = tempJMinusOne;
                    swapped = true;
                }
            }
        }
    }
}
