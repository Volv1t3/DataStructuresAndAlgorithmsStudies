package DeberSeisRadixSortSantiagoArellano;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : Santiago Arellano
 * @Date: October 19th, 2024
 * @Description: El presente archivo implementa el algoritmo RadixSort basado en buckets de digitos y no rangos de
 * numeros como seria histogram counting sort. En este sentido, el programa solo presenta la implementacion requerida para
 * organizar numeros.
 * @implNote : Esta clase hace uso de la interface Queue y de la implementacion de LinkedList de Java 21
 */
public class RadixSortDriver {

    public static <E extends Number & Comparable<E>> void radixSort(E[] data, int n) {
        final int radix = 10;
        final int digits = 10; // The maximum number of digits for a long

        // Create an array of 10 queues
        Queue<E>[] queues = new LinkedList[radix];
        for (int i = 0; i < radix; i++) {
            queues[i] = new LinkedList<>();
        }

        for (int d = 0, factor = 1; d < digits; factor *= radix, d++) {
            // Distribute the numbers into buckets
            for (int j = 0; j < n; j++) {
                int bucketIndex =((data[j].intValue() / factor) % radix);
                queues[bucketIndex].add(data[j]);
            }

            // Collect the numbers from the buckets and put them back into the array
            int k = 0;
            for (int j = 0; j < radix; j++) {
                while (!queues[j].isEmpty()) {
                    data[k++] = queues[j].poll();
                }
            }
        }
    }

}
