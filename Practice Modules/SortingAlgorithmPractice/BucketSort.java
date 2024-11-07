package SortingAlgorithmPractice;

import HeapImplementations.HeapSort;
import HeapImplementations.MaxHeapWithLinkedList;
import HeapImplementations.MinHeapArrayListNoComparator;
import HeapImplementations.MinHeapWithArrayList;

import javax.naming.Name;
import java.rmi.Naming;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class BucketSort {
    //! Declaring a main static algorithm exposure
    public static <E extends Number & Comparable<E>> void bucketSort(E[] externalSource) throws NullPointerException, IllegalArgumentException {
        //! Step -1: Gather internal constants and perform preflight checks
        List<E> eList = List.of(externalSource);
        isNull(eList);
        isEmpty(eList);
        int lengthOfSource = externalSource.length;

        //! Step 1: Creating the Empty Buckets based on statistics
        int numberOfBuckets = (int) Math.ceil(1 + 3.22 * Math.log10(lengthOfSource));
        E minItem = externalSource[0];
        E maxItem = externalSource[0];
        for (E element : externalSource) {
            if (element.compareTo(minItem) < 0 ) {
                minItem = element;
            }
            if (element.compareTo(maxItem) > 0) {
                maxItem = element;
            }
        }

        double bucketSize = (maxItem.doubleValue() - minItem.doubleValue()) / numberOfBuckets;
        ArrayList<ArrayList<E>> buckets = new ArrayList<>(numberOfBuckets);
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets.add(new ArrayList<>());
        }

        //! Step 2: Inserting the elements in the array based on bucket size
        for (E element : externalSource) {
            int bucketIndex = (int) ((int) (element.doubleValue() - minItem.doubleValue()) / bucketSize);
            bucketIndex = Math.min(bucketIndex, numberOfBuckets - 1);
            buckets.get(bucketIndex).add(element);
        }

        //! Step 3: Sort individual buckets
        buckets.forEach(bucket -> {
            MinHeapWithArrayList<E> sorting;
            try{
                sorting = new MinHeapWithArrayList<>(bucket);
                bucket.clear();
                while (!sorting.isEmpty()){
                    bucket.add(sorting.removeRetrievesRoot());
                }
            }
            catch(IllegalArgumentException e){};

        });

        //! Step 4: Concatenate all buckets into a single array
        AtomicInteger indexAtAnalysis = new AtomicInteger(0);
        buckets.forEach(bucket -> {
            for (E element : bucket) {
                externalSource[indexAtAnalysis.getAndIncrement()] = element;
            }
        });
    }

    private static <E extends Comparable<E>> void isNull(Collection<E> externalSource) throws NullPointerException {
        if (externalSource == null) {
            throw new NullPointerException("externalSource is Null");
        }
    }

    private static <E> void isNullNormal(Collection<E> externalSource) throws NullPointerException{
        if (externalSource == null) {
            throw new NullPointerException("externalSource is Null");
        }
    }
    private static <E extends Comparable<E>> void isEmpty(Collection<E> externalSource) throws IllegalArgumentException {
        if (externalSource.isEmpty()) {
            throw new IllegalArgumentException("externalSource is Empty");
        }
    }

    private static <E> void isEmptyNormal(Collection<E> externalSource) throws IllegalArgumentException{
        if (externalSource.isEmpty()) {
            throw new IllegalArgumentException("externalSource is Empty");
        }
    }



    public static <E extends  Comparable<E>> void bucketSort(E[] externalSource, Function<E, Double> dispersionHashingFunction){
        //! Step -1: Gather internal constants and perform preflight checks
        List<E> eList = List.of(externalSource);
        isNull(eList);
        isEmpty(eList);
        int lengthOfSource = externalSource.length;

        //! Step 1: Creating the Empty Buckets based on statistics
        int numberOfBuckets = (int) Math.ceil(1 + 3.22 * Math.log10(lengthOfSource));
        ArrayList<ArrayList<E>> buckets = new ArrayList<>(numberOfBuckets);
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets.add(new ArrayList<>());
        }
        //! Step 2: Insert elements into the buckets using the dispersion hashing function
        for (E element : externalSource) {
            double hashValue = dispersionHashingFunction.apply(element);
            assert hashValue >= 0 && hashValue <= 1;
            int bucketIndex = (int) (hashValue * numberOfBuckets);
            bucketIndex = Math.min(bucketIndex, numberOfBuckets - 1); // Ensure index is within bounds
            buckets.get(bucketIndex).add(element);
        }
        //! Step 3: Sort individual buckets
        buckets.forEach(bucket -> {
            MinHeapWithArrayList<E> sorting;
            try {
                System.out.println(bucket);
                sorting = new MinHeapWithArrayList<>(bucket);
                bucket.clear();
                while (!sorting.isEmpty()) {
                    bucket.add(sorting.removeRetrievesRoot());
                }
            } catch (IllegalArgumentException e){}

        });

        //! Step 4: Concatenate all buckets into a single array
        AtomicInteger indexAtAnalysis = new AtomicInteger(0);
        buckets.forEach(bucket -> {
            for (E element : bucket) {
                externalSource[indexAtAnalysis.getAndIncrement()] = element;
            }
        });

    }

    public static <E> void bucketSort(E[] externalSource, Function<E, Double> dispersionHashingFunction, Comparator<E> comparatorInstance)
    throws NullPointerException{
        //! Step -1: Gather internal constants and perform preflight checks
        if (comparatorInstance == null){
            throw new NullPointerException("Comparator cannot be null");}
        List<E> eList = List.of(externalSource);
        isNullNormal(eList);
        isEmptyNormal(eList);
        int lengthOfSource = externalSource.length;

        //! Step 1: Creating the Empty Buckets based on statistics
        int numberOfBuckets = (int) Math.ceil(1 + 3.22 * Math.log10(lengthOfSource));
        ArrayList<ArrayList<E>> buckets = new ArrayList<>(numberOfBuckets);
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets.add(new ArrayList<>());
        }
        //! Step 2: Insert elements into the buckets using the dispersion hashing function
        for (E element : externalSource) {
            double hashValue = dispersionHashingFunction.apply(element);
            assert hashValue >= 0 && hashValue <= 1;
            int bucketIndex = (int) (hashValue * numberOfBuckets);
            bucketIndex = Math.min(bucketIndex, numberOfBuckets - 1); // Ensure index is within bounds
            buckets.get(bucketIndex).add(element);
        }
        //! Step 3: Sort individual buckets
        buckets.forEach(bucket -> {
            MinHeapArrayListNoComparator<E> sorting;
            try {
                System.out.println(bucket);
                sorting = new MinHeapArrayListNoComparator<>(bucket, comparatorInstance);
                bucket.clear();
                while (!sorting.isEmpty()) {
                    bucket.add(sorting.removeRetrievesRoot());
                }
            } catch (IllegalArgumentException e){}

        });

        //! Step 4: Concatenate all buckets into a single array
        AtomicInteger indexAtAnalysis = new AtomicInteger(0);
        buckets.forEach(bucket -> {
            for (E element : bucket) {
                externalSource[indexAtAnalysis.getAndIncrement()] = element;
            }
        });
    }



    public static void main(String[] args) {
        Double[] arr = IntStream.rangeClosed(1,1000).asDoubleStream().boxed().toArray(new IntFunction<Double[]>() {
            @Override
            public Double[] apply(int value) {
                return new Double[value];
            }
        });
        Collections.sort(Arrays.asList(arr));// Example input array
        bucketSort(arr, new Function<Double, Double>() {
            @Override
            public Double apply(Double aLong) {
                return (aLong / arr.length);
            }
        }); // Call the bucketSort method with the input array
        Arrays.stream(arr).limit(25).forEach(System.out::println);

        //! Example with a custom comparator
        final Comparator<Integer> comparatorInstance = Comparator.comparingInt(Math::abs);
        Integer[] arr2 = IntStream.rangeClosed(1, 1000).boxed().toArray(new IntFunction<Integer[]>() {
            @Override
            public Integer[] apply(int value) {
                return new Integer[value];
            }
        });
        bucketSort(arr2, new Function<Integer, Double>() {
            @Override
            public Double apply(Integer integer) {
                return (integer.doubleValue() / arr2.length);
            }
        }, comparatorInstance);
        Arrays.stream(arr2).limit(25).forEach(System.out::println);
        //! Example with a custom comparator

    }
}


