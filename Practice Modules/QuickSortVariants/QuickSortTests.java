package QuickSortVariants;

import jdk.jfr.Description;
import org.junit.*;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.DisplayName;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.IntStream;

@Description("Tests For Quick Sort Implementations")
public class QuickSortTests {

    private Integer[] streamOfValues100_000 = IntStream.rangeClosed(0, 100_000).boxed().toArray(Integer[]::new);
    private Integer[] streamOfValues500_000 = IntStream.rangeClosed(0, 500_000).boxed().toArray(Integer[]::new);
    private Integer[] streamOfValues1_000_000 = IntStream.rangeClosed(0, 1_000_000).boxed().toArray(Integer[]::new);
    private Integer[] streamOfValues1_500_000 = IntStream.rangeClosed(0, 1_500_000).boxed().toArray(Integer[]::new);
    
    @Test
    public void testingVariantISinglePivotQuickSort100000(){
        Collections.shuffle(Arrays.asList(streamOfValues100_000));
        var result = this.executeAndTime(streamOfValues100_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                QuickSortVariantI.sort(integers);
            }
        });
        System.out.println("result for 100,000 = " + result);
        Arrays.stream(streamOfValues100_000).limit(10).forEach(System.out::println);
    }

    @Test
    public void testingVariantISinglePivotQuickSort500000(){
        Collections.shuffle(Arrays.asList(streamOfValues500_000));
        var result = this.executeAndTime(streamOfValues500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                QuickSortVariantI.sort(integers);
            }
        });
        System.out.println("result for 500,000 = " + result);
    }

    @Test
    public void testingVariantISinglePivotQuickSort1000000(){
        Collections.shuffle(Arrays.asList(streamOfValues1_000_000));
        var result = this.executeAndTime(streamOfValues1_000_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                QuickSortVariantI.sort(integers);
            }
        });
        System.out.println("result for 1,000,000 = " + result);
        Arrays.stream(streamOfValues1_000_000).limit(10).forEach(System.out::println);
    }

    @Test
    public void testingVariantISinglePivotQuickSort1500000(){
        Collections.shuffle(Arrays.asList(streamOfValues1_500_000));
        var result = this.executeAndTime(streamOfValues1_500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                QuickSortVariantI.sort(integers);
            }
        });
        System.out.println("result for 1,500,000 = " + result);
        Arrays.stream(streamOfValues1_500_000).limit(10).forEach(System.out::println);
    }


    //! Tests for QuickSortII


    //! Tests for QuickSortIII
    @Test
    public void testingVariantIIIPivotQuickSort100000(){
        Collections.shuffle(Arrays.asList(streamOfValues100_000));
        var result = this.executeAndTime(streamOfValues100_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                QuickSortVariantIII.sort(integers);
            }
        });
        System.out.println("result for 100, 000 = " + result);
        Arrays.stream(streamOfValues100_000).limit(10).forEach(System.out::println);
    }
    @Test
    public void testingVariantIIIPivotQuickSort500000(){
        Collections.shuffle(Arrays.asList(streamOfValues500_000));
        var result = this.executeAndTime(streamOfValues500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                QuickSortVariantIII.sort(integers);
            }
        });
        System.out.println("result for 500, 000 = " + result);
        Arrays.stream(streamOfValues500_000).limit(10).forEach(System.out::println);
    }
    @Test
    public void testingVariantIIIPivotQuickSort1000000(){
        Collections.shuffle(Arrays.asList(streamOfValues1_000_000));
        var result = this.executeAndTime(streamOfValues1_000_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                QuickSortVariantIII.sort(integers);
            }
        });
        System.out.println("result for 1, 000, 000 = " + result);
        Arrays.stream(streamOfValues1_000_000).limit(10).forEach(System.out::println);
    }
    @Test
    public void testingVariantIIIPivotQuickSort1500000(){
        Collections.shuffle(Arrays.asList(streamOfValues1_500_000));
        var result = this.executeAndTime(streamOfValues1_500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                QuickSortVariantIII.sort(integers);
            }
        });
        System.out.println("result for 1, 500, 000 = " + result);
        Arrays.stream(streamOfValues1_500_000).limit(10).forEach(System.out::println);
    }
    //! Tests for QuickSortIV
    @Test
    public void testingVariantIVPivotQuickSort100000(){
        Collections.shuffle(Arrays.asList(streamOfValues100_000));
        var result = this.executeAndTime(streamOfValues100_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                QuickSortVariantIV.sort(integers);
            }
        });
        System.out.println("result for 100, 000 = " + result);
        Arrays.stream(streamOfValues100_000).limit(10).forEach(System.out::println);
    }
    @Test
    public void testingVariantIVPivotQuickSort500000(){
        Collections.shuffle(Arrays.asList(streamOfValues500_000));
        var result = this.executeAndTime(streamOfValues500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                QuickSortVariantIV.sort(integers);
            }
        });
        System.out.println("result for 500, 000 = " + result);
        Arrays.stream(streamOfValues500_000).limit(10).forEach(System.out::println);
    }
    @Test
    public void testingVariantIVPivotQuickSort1000000(){
        Collections.shuffle(Arrays.asList(streamOfValues1_000_000));
        var result = this.executeAndTime(streamOfValues1_000_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                QuickSortVariantIV.sort(integers);
            }
        });
        System.out.println("result for 1, 000, 000 = " + result);
        Arrays.stream(streamOfValues1_000_000).limit(10).forEach(System.out::println);
    }
    @Test
    public void testingVariantIVPivotQuickSort1500000(){
        Collections.shuffle(Arrays.asList(streamOfValues1_500_000));
        var result = this.executeAndTime(streamOfValues1_500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                QuickSortVariantIV.sort(integers);
            }
        });
        System.out.println("result for 1, 500, 000 = " + result);
        Arrays.stream(streamOfValues1_500_000).limit(10).forEach(System.out::println);
    }

    /**
     * Tests Para Median Of Three Pivot Quick Sort Implementation
     */

    @Test
    @DisplayName("Median Of Three Pivot Quick Sort 100_000 Elements")
    @Tag("MedianOfThreePivotQuickSort")
    public void testingMedianOfThreePivotQuickSort100000() {
        Collections.shuffle(Arrays.asList(streamOfValues100_000));
        var result = executeAndTime(streamOfValues100_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                MedianOfThreeQuickSort.medianQuickSort(integers, integers.length);
            }
        });
        System.out.println("result for 100, 000 = " + result);
        Arrays.stream(streamOfValues100_000).limit(10).forEach(System.out::println);
    }

    @Test
    @DisplayName("Median Of Three Pivot Quick Sort 500_000 Elements")
    @Tag("MedianOfThreePivotQuickSort")
    public void testingMedianOfThreePivotQuickSort500000() {
        Collections.shuffle(Arrays.asList(streamOfValues500_000));
        var result = executeAndTime(streamOfValues500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                MedianOfThreeQuickSort.medianQuickSort(integers, integers.length);
            }
        });
        System.out.println("result for 500, 000 = " + result);
        Arrays.stream(streamOfValues500_000).limit(10).forEach(System.out::println);
    }

    @Test
    @DisplayName("Median Of Three Pivot Quick Sort 1_000_000 Elements")
    @Tag("MedianOfThreePivotQuickSort")
    public void testingMedianOfThreePivotQuickSort1000000() {
        Collections.shuffle(Arrays.asList(streamOfValues1_000_000));
        var result = executeAndTime(streamOfValues1_000_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                MedianOfThreeQuickSort.medianQuickSort(integers, integers.length);
            }
        });
        System.out.println("result for 1, 000, 000 = " + result);
        Arrays.stream(streamOfValues1_000_000).limit(10).forEach(System.out::println);
    }

    @Test
    @DisplayName("Median Of Three Pivot Quick Sort 1_500_000 Elements")
    @Tag("MedianOfThreePivotQuickSort")
    public void testingMedianOfThreePivotQuickSort1500000() {
        Collections.shuffle(Arrays.asList(streamOfValues1_500_000));
        var result = executeAndTime(streamOfValues1_500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                MedianOfThreeQuickSort.medianQuickSort(integers, integers.length);
            }
        });
        System.out.println("result for 1, 500, 000 = " + result);
        Arrays.stream(streamOfValues1_500_000).limit(10).forEach(System.out::println);
    }


    /**
     * Tests Para Dual Pivot Implementations
     */

    @Test
    @DisplayName("YBB Dual Pivot Quick Sort 100000 Elements")
    @Tag("YBBDualPivotQuickSort")
    public void testingVariantYBBPivotQuickSort100000(){
        Collections.shuffle(Arrays.asList(streamOfValues100_000));
        var result = this.executeAndTime(streamOfValues100_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                YBBQuickSortVariant.sort(integers);
            }
        });
        System.out.println("result for 100, 000 = " + result);
        Arrays.stream(streamOfValues100_000).limit(10).forEach(System.out::println);
    }

    @Test
    @DisplayName("YBB Dual Pivot Quick Sort 500000 Elements")
    @Tag("YBBDualPivotQuickSort")
    public void testingVariantYBBPivotQuickSort500000(){
        Collections.shuffle(Arrays.asList(streamOfValues500_000));
        var result = this.executeAndTime(streamOfValues500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                YBBQuickSortVariant.sort(integers);
            }
        });
        System.out.println("result for 500, 000 = " + result);
        Arrays.stream(streamOfValues500_000).limit(10).forEach(System.out::println);

    }

    @Test
    @DisplayName("YBB Dual Pivot Quick Sort 1000000 Elements")
    @Tag("YBBDualPivotQuickSort")
    public void testingVariantYBBPivotQuickSort1000000(){
        Collections.shuffle(Arrays.asList(streamOfValues1_000_000));
        var result = this.executeAndTime(streamOfValues1_000_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                YBBQuickSortVariant.sort(integers);
            }
        });
        System.out.println("result for 1, 000, 000 = " + result);
        Arrays.stream(streamOfValues1_000_000).limit(10).forEach(System.out::println);
    }

    @Test
    @DisplayName("YBB Dual Pivot Quick Sort 1500000 Elements")
    @Tag("YBBDualPivotQuickSort")
    public void testingVariantYBBPivotQuickSort1500000(){
        Collections.shuffle(Arrays.asList(streamOfValues1_500_000));
        var result = this.executeAndTime(streamOfValues1_500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                YBBQuickSortVariant.sort(integers);
            }
        });
        System.out.println("result for 1, 500, 000 = " + result);
        Arrays.stream(streamOfValues1_500_000).limit(10).forEach(System.out::println);
    }

    @Test
    @DisplayName("Sedgewick Dual Pivot Quick Sort 100000 Elements")
    @Tag("SedgewickDualPivotQuickSort")
    public void testingSedwickQuickSortVariant(){
        Collections.shuffle(Arrays.asList(streamOfValues100_000));
        var result = executeAndTime(streamOfValues100_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                SedgewickQuickSortVariant.sort(integers);
            }
        });
        System.out.println("result for 100, 000 = " + result);
        Arrays.stream(streamOfValues100_000).limit(10).forEach(System.out::println);
    }

    @Test
    @DisplayName("Sedgewick Dual Pivot Quick Sort 500000 Elements")
    @Tag("SedgewickDualPivotQuickSort")
    public void testingSedwickQuickSortVariant500000() {
        Collections.shuffle(Arrays.asList(streamOfValues500_000));
        var result = executeAndTime(streamOfValues500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                SedgewickQuickSortVariant.sort(integers);
            }
        });
        System.out.println("result for 500, 000 = " + result);
        Arrays.stream(streamOfValues500_000).limit(10).forEach(System.out::println);
    }

    @Test
    @DisplayName("Sedgewick Dual Pivot Quick Sort 1000000 Elements")
    @Tag("SedgewickDualPivotQuickSort")
    public void testingSedwickQuickSortVariant1000000() {
        Collections.shuffle(Arrays.asList(streamOfValues1_000_000));
        var result = executeAndTime(streamOfValues1_000_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                SedgewickQuickSortVariant.sort(integers);
            }
        });
        System.out.println("result for 1, 000, 000 = " + result);
        Arrays.stream(streamOfValues1_000_000).limit(10).forEach(System.out::println);
    }

    @Test
    @DisplayName("Sedgewick Dual Pivot Quick Sort 1500000 Elements")
    @Tag("SedgewickDualPivotQuickSort")
    public void testingSedwickQuickSortVariant1500000() {
        Collections.shuffle(Arrays.asList(streamOfValues1_500_000));
        var result = executeAndTime(streamOfValues1_500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                SedgewickQuickSortVariant.sort(integers);
            }
        });
        System.out.println("result for 1, 500, 000 = " + result);
        Arrays.stream(streamOfValues1_500_000).limit(10).forEach(System.out::println);
    }

    //TODO: Implementaciones Parallelas para Doble Pivote
    @Test
    @DisplayName("Parallel Lomuto-Hoare Hibrid Dual Pivot Sorting Test with 100_000 Elements")
    @Tag("LomutoHoareParallelDPSorting")
    public void testingLomutoHoareHibridVariant100000(){
        Collections.shuffle(Arrays.asList(streamOfValues100_000));
        var result = executeAndTime(streamOfValues100_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                ParallelDualPivotLomutoHoareQuickSort.sort(integers);
            }
        });
        System.out.println("result for 100, 000 = " + result);
        Arrays.stream(streamOfValues100_000).limit(10).forEach(System.out::println);
    }

    @Test
    @DisplayName("Parallel Lomuto-Hoare Hibrid Dual Pivot Sorting Test with 500_000 Elements")
    @Tag("LomutoHoareParallelDPSorting")
    public void testingLomutoHoareHibridVariant500000(){
        Collections.shuffle(Arrays.asList(streamOfValues500_000));
        var result = executeAndTime(streamOfValues500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                ParallelDualPivotLomutoHoareQuickSort.sort(integers);
            }
        });
        System.out.println("result for 500, 000 = " + result);
        Arrays.stream(streamOfValues500_000).limit(10).forEach(System.out::println);
    }
    @Test
    @DisplayName("Parallel Lomuto-Hoare Hibrid Dual Pivot Sorting Test with 1_000_000 Elements")
    @Tag("LomutoHoareParallelDPSorting")
    public void testingLomutoHoareHibridVariant1000000(){
        Collections.shuffle(Arrays.asList(streamOfValues1_000_000));
        var result = executeAndTime(streamOfValues1_000_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                ParallelDualPivotLomutoHoareQuickSort.sort(integers);
            }
        });
        System.out.println("result for 1, 000, 000 = " + result);
        Arrays.stream(streamOfValues1_000_000).limit(10).forEach(System.out::println);
        var correctStream = IntStream.rangeClosed(0,1_000_000).boxed().toArray();
        Assert.assertArrayEquals(streamOfValues1_000_000, correctStream);
    }
    @Test
    @DisplayName("Parallel Lomuto-Hoare Hibrid Dual Pivot Sorting Test with 1_500_000 Elements")
    @Tag("LomutoHoareParallelDPSorting")
    public void testingLomutoHoareHibridVariant1500000(){
        Collections.shuffle(Arrays.asList(streamOfValues1_500_000));
        var result = executeAndTime(streamOfValues1_500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                ParallelDualPivotLomutoHoareQuickSort.sort(integers);
            }
        });
        System.out.println("result for 1, 500, 000 = " + result);
        Arrays.stream(streamOfValues1_500_000).limit(10).forEach(System.out::println);
        var correctStream = IntStream.rangeClosed(0,1_500_000).boxed().toArray();
        Assert.assertArrayEquals(streamOfValues1_500_000, correctStream);
    }


    @Test
    @DisplayName("Parallel Lomuto Dual Pivot Sorting Test with 100_000 Elements")
    @Tag("LomutoParallelDPSorting")
    public void testingLomutoParallelVariant100000(){
        Collections.shuffle(Arrays.asList(streamOfValues100_000));
        var result = this.executeAndTime(streamOfValues100_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                ParallelDualPivotLomutoQuickSort.sort(integers);
            }
        });
        System.out.println("result for 100, 000 = " + result);
        Arrays.stream(streamOfValues100_000).limit(10).forEach(System.out::println);
    }

    @Test
    @DisplayName("Parallel Lomuto Dual Pivot Sorting Test with 500_000 Elements")
    @Tag("LomutoParallelDPSorting")
    public void testingLomutoParallelVariant500000(){
        Collections.shuffle(Arrays.asList(streamOfValues500_000));
        var result = this.executeAndTime(streamOfValues500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                ParallelDualPivotLomutoQuickSort.sort(integers);
            }
        });
        System.out.println("result for 500, 000 = " + result);
        Arrays.stream(streamOfValues500_000).limit(10).forEach(System.out::println);
    }

    @Test
    @DisplayName("Parallel Lomuto Dual Pivot Sorting Test with 1_000_000 Elements")
    @Tag("LomutoParallelDPSorting")
    public void testingLomutoParallelVariant1000000(){
        Collections.shuffle(Arrays.asList(streamOfValues1_000_000));
        var result = this.executeAndTime(streamOfValues1_000_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                ParallelDualPivotLomutoQuickSort.sort(integers);
            }
        });
        System.out.println("result for 1, 000, 000 = " + result);
        Arrays.stream(streamOfValues1_000_000).limit(10).forEach(System.out::println);
    }

    @Test
    @DisplayName("Parallel Lomuto Dual Pivot Sorting Test with 1_500_000 Elements")
    @Tag("LomutoParallelDPSorting")
    public void testingLomutoParallelVariant1500000(){
        Collections.shuffle(Arrays.asList(streamOfValues1_500_000));
        var result = this.executeAndTime(streamOfValues1_500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                ParallelDualPivotLomutoQuickSort.sort(integers);
            }
        });
        System.out.println("result for 1, 500, 000 = " + result);
        Arrays.stream(streamOfValues1_500_000).limit(10).forEach(System.out::println);
    }

    /**
     * Tests Para Waterloo (Kushagra) Triple Pivot Quick Sort
     */

    @Test
    @DisplayName("Waterloo Triple Pivot Quick Sort 500_000 Elements")
    @Tag("WaterlooTriplePivotQuickSort")
    public void testingWaterlooThreePivotQuickSort500000() {
        Collections.shuffle(Arrays.asList(streamOfValues500_000));
        var result = executeAndTime(streamOfValues500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                WaterlooThreePivotQuickSort.sort(integers);
            }
        });
        System.out.println("result for 500, 000 = " + result);
        Arrays.stream(streamOfValues500_000).limit(10).forEach(System.out::println);
    }

    @Test
    @DisplayName("Waterloo Triple Pivot Quick Sort 100_000 Elements")
    @Tag("WaterlooTriplePivotQuickSort")
    public void testingWaterlooThreePivotQuickSort(){
        Collections.shuffle(Arrays.asList(streamOfValues100_000));
        var result = executeAndTime(streamOfValues100_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                WaterlooThreePivotQuickSort.sort(integers);
            }
        });
        System.out.println("result for 100, 000 = " + result);
        Arrays.stream(streamOfValues100_000).limit(10).forEach(System.out::println);
    }

    @Test
    @DisplayName("Waterloo Triple Pivot Quick Sort 1_000_000 Elements")
    @Tag("WaterlooTriplePivotQuickSort")
    public void testingWaterlooThreePivotQuickSort1000000() {
        Collections.shuffle(Arrays.asList(streamOfValues1_000_000));
        var result = executeAndTime(streamOfValues1_000_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                WaterlooThreePivotQuickSort.sort(integers);
            }
        });
        System.out.println("result for 1, 000, 000 = " + result);
            Arrays.stream(streamOfValues1_000_000).limit(10).forEach(System.out::println);
    }

    @Test
    @DisplayName("Waterloo Triple Pivot Quick Sort 1_500_000 Elements")
    @Tag("WaterlooTriplePivotQuickSort")
    public void testingWaterlooThreePivotQuickSort1500000() {
        Collections.shuffle(Arrays.asList(streamOfValues1_500_000));
        var result = executeAndTime(streamOfValues1_500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                WaterlooThreePivotQuickSort.sort(integers);
            }
        });
        System.out.println("result for 1, 500, 000 = " + result);
        Arrays.stream(streamOfValues1_500_000).limit(10).forEach(System.out::println);
    }


    /**
     *  Tests para Parallel Triple Pivot Quick Sort Adaptation
     */

    @Test
    @DisplayName("Parallel Triple Pivot Quick Sort 100_000 Elements")
    @Tag("ParallelTriplePivotQuickSort")
    public void testingParallelTriplePivotQuickSort() {
        Collections.shuffle(Arrays.asList(streamOfValues100_000));
        var result = executeAndTime(streamOfValues100_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                try {
                    ParallelTriplePivotQuickSort.ptpSort(integers, 0, integers.length - 1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        System.out.println("result for 100, 000 = " + result);
        Arrays.stream(streamOfValues100_000).limit(10).forEach(System.out::println);
    }

    @Test
    @DisplayName("Parallel Triple Pivot Quick Sort 500, 000 Elements")
    @Tag("ParallelTriplePivotQuickSort")
    public void testingParallelTriplePivotQuickSort500000() {
        Collections.shuffle(Arrays.asList(streamOfValues500_000));
        var result = executeAndTime(streamOfValues500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                try {
                    ParallelTriplePivotQuickSort.ptpSort(integers, 0, integers.length - 1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        System.out.println("result for 500, 000 = " + result);
        Arrays.stream(streamOfValues500_000).limit(10).forEach(System.out::println);
    }

    @Test
    @DisplayName("Parallel Triple Pivot Quick Sort 1_000_000 Elements")
    @Tag("ParallelTriplePivotQuickSort")
    public void testingParallelTriplePivotQuickSort1000000() {
        Collections.shuffle(Arrays.asList(streamOfValues1_000_000));
        var result = executeAndTime(streamOfValues1_000_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                try {
                    ParallelTriplePivotQuickSort.ptpSort(integers, 0, integers.length - 1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        System.out.println("result for 1, 000, 000 = " + result);
        Arrays.stream(streamOfValues1_000_000).limit(10).forEach(System.out::println);
    }

    @Test
    @DisplayName("Parallel Triple Pivot Quick Sort 1_500_000 Elements")
    @Tag("ParallelTriplePivotQuickSort")
    public void testingParallelTriplePivotQuickSort1500000() {
        Collections.shuffle(Arrays.asList(streamOfValues1_500_000));
        var result = executeAndTime(streamOfValues1_500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                try {
                    ParallelTriplePivotQuickSort.ptpSort(integers, 0, integers.length - 1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        System.out.println("result for 1, 500, 000 = " + result);
        Arrays.stream(streamOfValues1_500_000).limit(10).forEach(System.out::println);
    }


    /**
     * Tests Para Tetra Pivot Quick Sort Implementation
     */

    @Test
    @DisplayName("Tetra Pivot Quick Sort 100_000 Elements")
    @Tag("TetraPivotQuickSort")
    public void testingTetraPivotQuickSort(){
        Collections.shuffle(Arrays.asList(streamOfValues100_000));
        var result = executeAndTime(streamOfValues100_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                List<Integer> result = TetraPivotQuickSort.sort(new ArrayList<>(){{addAll(Arrays.asList(integers));}});
            }
        });
        streamOfValues100_000 = TetraPivotQuickSort.sort(new ArrayList<>() {{
            addAll(Arrays.asList(streamOfValues100_000));
        }}).toArray(new Integer[0]);
        System.out.println("result for 100, 000 = " + result);
        Arrays.stream(streamOfValues100_000).limit(10).forEach(System.out::println);
    }

    @Test
    @DisplayName("Tetra Pivot Quick Sort 500,000 Elements")
    @Tag("TetraPivotQuickSort")
    public void testingTetraPivotQuickSort500000() {
        Collections.shuffle(Arrays.asList(streamOfValues500_000));
        var result = executeAndTime(streamOfValues500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                TetraPivotQuickSort.sort(new ArrayList<>(){{addAll(Arrays.asList(integers));}});
            }
        });
        streamOfValues500_000 = TetraPivotQuickSort.sort(new ArrayList<>() {{
            addAll(Arrays.asList(streamOfValues500_000));
        }}).toArray(new Integer[0]);
        System.out.println("result for 500, 000 = " + result);
        Arrays.stream(streamOfValues500_000).limit(10).forEach(System.out::println);
    }

    @Test
    @DisplayName("Tetra Pivot Quick Sort 1_000_000 Elements")
    @Tag("TetraPivotQuickSort")
    public void testingTetraPivotQuickSort1000000() {
        Collections.shuffle(Arrays.asList(streamOfValues1_000_000));
        var result = executeAndTime(streamOfValues1_000_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                TetraPivotQuickSort.sort(new ArrayList<>(){{addAll(Arrays.asList(integers));}});
            }
        });
        streamOfValues1_000_000 = TetraPivotQuickSort.sort(new ArrayList<>() {{
            addAll(Arrays.asList(streamOfValues1_000_000));
        }}).toArray(new Integer[0]);
        System.out.println("result for 1, 000, 000 = " + result);
        Arrays.stream(streamOfValues1_000_000).limit(10).forEach(System.out::println);
    }

    @Test
    @DisplayName("Tetra Pivot Quick Sort 1_500_000 Elements")
    @Tag("TetraPivotQuickSort")
    public void testingTetraPivotQuickSort1500000() {
        Collections.shuffle(Arrays.asList(streamOfValues1_500_000));
        var result = executeAndTime(streamOfValues1_500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                TetraPivotQuickSort.sort(new ArrayList<>(){{addAll(Arrays.asList(integers));}});
            }
        });
        streamOfValues1_500_000 = TetraPivotQuickSort.sort(new ArrayList<>() {{
            addAll(Arrays.asList(streamOfValues1_500_000));
        }}).toArray(new Integer[0]);
        System.out.println("result for 1, 500, 000 = " + result);
        Arrays.stream(streamOfValues1_500_000).limit(10).forEach(System.out::println);
    }

    /**
     * Tests Para Penta Pivot Quick Sort Implementation
     */

    @Test
    @DisplayName("Penta Pivot Quick Sort 500_000 Elements")
    @Tag("PentaPivotQuickSort")
    public void testingPentaPivotQuickSort500000() {
        Collections.shuffle(Arrays.asList(streamOfValues500_000));
        var result = executeAndTime(streamOfValues500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                PentaPivotQuickSort.sort(new ArrayList<>(){{addAll(Arrays.asList(integers));}});
            }
        });
        System.out.println("result for 500, 000 = " + result);
        streamOfValues500_000 = PentaPivotQuickSort.sort(new ArrayList<>() {{
            addAll(Arrays.asList(streamOfValues500_000));
        }}).toArray(new Integer[0]);
        Arrays.stream(streamOfValues500_000).limit(10).forEach(System.out::println);
    }


    @Test
    @DisplayName("Penta Pivot Quick Sort 100_000 Elements")
    @Tag("PentaPivotQuickSort")
    public void testingPentaPivotQuickSort100000() {
        Collections.shuffle(Arrays.asList(streamOfValues100_000));
        var result = executeAndTime(streamOfValues100_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                PentaPivotQuickSort.sort(new ArrayList<>(){{addAll(Arrays.asList(integers));}});
            }
        });
        System.out.println("result for 100, 000 = " + result);
        streamOfValues100_000 = PentaPivotQuickSort.sort(new ArrayList<>() {{
            addAll(Arrays.asList(streamOfValues100_000));
        }}).toArray(new Integer[0]);
        Arrays.stream(streamOfValues100_000).limit(10).forEach(System.out::println);
    }

    @Test
    @Tag("PentaPivotQuickSort")
    @DisplayName("Penta Pivot Quick Sort 1_000_000 Elements")
    public void testingPentaPivotQuickSort1000000() {
        Collections.shuffle(Arrays.asList(streamOfValues1_000_000));
        var result = executeAndTime(streamOfValues1_000_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                PentaPivotQuickSort.sort(new ArrayList<>(){{addAll(Arrays.asList(integers));}});
            }
        });
        System.out.println("result for 1, 000, 000 = " + result);
        streamOfValues1_000_000 = PentaPivotQuickSort.sort(new ArrayList<>()
        {{addAll(Arrays
                .asList(streamOfValues1_000_000));}}
                )
                .toArray(new Integer[0]);
        Arrays.stream(streamOfValues1_000_000).limit(10).forEach(System.out::println);
    }

    @Test
    @Tag("PentaPivotQuickSort")
    @DisplayName("Penta Pivot Quick Sort 1_500_000 Elements")
    public void testingPentaPivotQuickSort1500000() {
        Collections.shuffle(Arrays.asList(streamOfValues1_500_000));
        var result = executeAndTime(streamOfValues1_500_000, new Consumer<Integer[]>() {
            @Override
            public void accept(Integer[] integers) {
                PentaPivotQuickSort.sort(new ArrayList<>(){{addAll(Arrays.asList(integers));}});

            }
        });
        System.out.println("result for 1, 500, 000 = " + result);
        streamOfValues1_500_000 = PentaPivotQuickSort.sort(new ArrayList<>() {{
            addAll(Arrays.asList(streamOfValues1_500_000));
        }}).toArray(new Integer[0]);
        Arrays.stream(streamOfValues1_500_000).limit(10).forEach(System.out::println);
    }


    //! Timing Functions
    private <E> String executeAndTime(Integer[] valuesToApply, Consumer<Integer[]> functionToExecute) {
        long timeBefore = System.nanoTime(); // Changed to nanoTime for higher precision
        functionToExecute.accept(valuesToApply);
        long timeAfter = System.nanoTime();
        return getFormattedTime(timeAfter, timeBefore);
    }


    private String getFormattedTime(long timeAfter, long timeBefore) {
        long durationNanos = timeAfter - timeBefore;
        // Convert nanoseconds to seconds
        BigDecimal timeInSeconds = BigDecimal.valueOf(durationNanos)
                .divide(BigDecimal.valueOf(1_000_000_000), 9, RoundingMode.HALF_UP);

        DecimalFormat df = new DecimalFormat("0.##E0");
        return df.format(timeInSeconds);
    }


}