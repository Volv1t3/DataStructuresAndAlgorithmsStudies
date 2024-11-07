package DeberSeisRadixSortSantiagoArellano;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

public class RadixSortMain {
    public static void main(String[] args) {
        System.out.printf("%120s","Radix Sort Para 1_000_000 Enteros\n");
        Integer[] integers = IntStream.range(0,1_000_000).boxed().toArray(Integer[]::new);
        Collections.shuffle(Arrays.asList(integers));
        System.out.println("1. Collection de Enteros Desordenados [Imprimimos los 20 primeros]");
        Arrays.stream(integers).limit(20).forEach(number -> {
            System.out.print(" , " + number);
        });
        System.out.println();
        RadixSortDriver.radixSort(integers, integers.length);
        System.out.println("2. Collection de Enteros Ordenados [Imprimimos los 20 primeros]");
        Arrays.stream(integers).limit(20).forEach(number ->{
            System.out.print(" , " + number);
        });
        System.out.println();
    }

}
