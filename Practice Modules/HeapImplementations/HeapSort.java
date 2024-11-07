package HeapImplementations;

import DeberCuatroLinkedListSantiagoArellano.SingleLinkedList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class HeapSort {

    public static <E extends Comparable<E>> void minHeapSort(E[] externalSource){
        //! Create a new instance with the data
        MinHeapWithLinkedList<E> dataSorted = new MinHeapWithLinkedList<>();
        Arrays.stream(externalSource).forEach(dataSorted::addElement);
        //! Map sorted to the original array
        for(int i = 0; i < externalSource.length; i++){
            externalSource[i] = dataSorted.removeRetrievesRoot();
        }
    }

    public static <E extends Comparable<E>> void maxHeapSort(E[] externalSource){
        //! Create a new instance with the data
        MaxHeapWithLinkedList<E> dataSorted = new MaxHeapWithLinkedList<>();
        Arrays.stream(externalSource).forEach(dataSorted::addElement);
        //! Map sorted to the original array
        for(int i = externalSource.length -1; i >=0; i--){
            externalSource[i] = dataSorted.removeRetrievesRoot();
        }
    }


    public static void main(String[] args) {
        Double[] list = (IntStream.rangeClosed(1, 1000)
                .mapToObj(Double::valueOf).toArray(new IntFunction<Double[]>() {
                    @Override
                    public Double[] apply(int value) {
                        return new Double[value];
                    }
                }));
        Collections.shuffle(Arrays.asList(list));
        System.out.println("Before sorting: ");
        Arrays.stream(list).limit(20).forEach(value -> {System.out.print(value + " ");});

        System.out.println("\nAfter sorting: ");
        minHeapSort(list);
        Arrays.stream(list).limit(20).forEach(value -> {System.out.print(value + " ");});

        System.out.println("\nShuffling before sorting again: ");
        Collections.shuffle(Arrays.asList(list));
        Arrays.stream(list).limit(20).forEach(value -> {System.out.print(value + " ");});
        System.out.println("\nAfter sorting again: ");
        maxHeapSort(list);
        Arrays.stream(list).limit(20).forEach(value -> {System.out.print(value + " ");});

        SingleLinkedList<Integer> list1 = new SingleLinkedList<>(new Integer[]{2,10,13,11,20,25,29,31});
        Integer[] arr = list1.toArray(new Integer[0]);
        list1.clear();
        minHeapSort(arr);
        list1.addAll(List.of(arr));
        System.out.println(list1.toString());
    }
}


