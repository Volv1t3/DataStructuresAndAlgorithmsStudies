package QuickSortVariants;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
public class ParallelDualPivotLomutoQuickSort {

    private static ExecutorService parallelExecutor = Executors.newFixedThreadPool(24);
    private static int Ustl = 200_000;
    private static int Udf = 1_000_000;


    public static void sort(Integer[] arr){
        if (arr.length <= 1 || arr == null){
            return;
        }
            System.err.println("array length = " + arr.length);
            ParallelDualPivotLomutoQuickSort(arr, 0, arr.length - 1);

    }

    private static void ParallelDualPivotLomutoQuickSort(Integer[] arr, int beg, int end){
        if (beg >= end){
            return; //! Recursion Base Case
        }
        if (end - beg  + 1 < Ustl){
            Arrays.sort(arr, beg, end);
            return;
        }

        //! Proceso de Division y Seleccion de Pivote
        int mid = (beg + end) / 2;
        int iPlow = beg;
        int iPHigh = end;
        int vPlow = arr[iPlow];
        int vPHigh = arr[iPHigh];
        if (vPlow > vPHigh){
            swap(arr, iPlow, iPHigh);
            vPlow = arr[iPlow];
            vPHigh = arr[iPHigh];
        }
        //! Acercamos los pivotes al medio del arreglo como menciona el paper [10]
        swap(arr, iPlow, mid - 1);
        swap(arr, iPHigh, mid + 1);
        iPlow = mid - 1;
        iPHigh = mid + 1;
        vPlow = arr[iPlow];
        vPHigh = arr[iPHigh];

        //! Realizamos dos particiones lomuto utilizando las funciones estaticas de la segunda implementacion de [11]
        CountDownLatch partitionLatch = new CountDownLatch(2);
        AtomicInteger iL = new AtomicInteger(beg);
        AtomicInteger iR = new AtomicInteger(end);
        int finalvPlow = vPlow;
        int finaliPlow = iPlow;
        parallelExecutor.submit(() -> {
            try {
                if (beg < finaliPlow ){
                    iL.set(LR_Lomuto(arr, beg, finaliPlow -1, beg, finalvPlow));
                }
            } finally {
                partitionLatch.countDown();
            }
        });
        int finalvPhigh = vPHigh;
        int finaliPhigh = iPHigh;
        parallelExecutor.submit(() -> {
            try {
                if (finaliPhigh + 1 < end){
                    iR.set(RL_Lomuto(arr, finaliPhigh + 1, end, end, finalvPhigh));
                }
            } finally {
                partitionLatch.countDown();
            }
        });

        try {
            partitionLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        //! Intercambios de los pivotes a sus posiciones correctas
        swap(arr, iL.get(), iPlow);
        swap(arr, iR.get(), iPHigh);

        //! Formato de Trabajo Paralelo Para Sorting
        //! Left Section
        iL.set(LR_Lomuto(arr, mid, iR.get(), iL.get(), vPlow));
        CountDownLatch recursiveLatch = new CountDownLatch(3);
        if (iL.get() - beg + 1 > Udf){
            parallelExecutor.submit(() -> {
                try {
                    ParallelDualPivotLomutoQuickSort(arr, beg, iL.get());
                } finally {
                    recursiveLatch.countDown();
                }
            });
        } else {
            ParallelDualPivotLomutoQuickSort(arr, beg, iL.get());
        }
        //! Middle Section
        iR.set( RL_Lomuto(arr, iL.get(), iR.get(), iR.get(), vPHigh));
        if (iR.get() - iL.get() > Udf){
            parallelExecutor.submit(() -> {
                try {
                    ParallelDualPivotLomutoQuickSort(arr, iL.get(), iR.get() + 1);
                } finally {
                    recursiveLatch.countDown();
                }
            });
        }else {
            ParallelDualPivotLomutoQuickSort(arr, iL.get(), iR.get() + 1);
        }
        //! Right Section
        if (end - iR.get() + 1 > Udf){
            parallelExecutor.submit(() -> {
                try {
                    ParallelDualPivotLomutoQuickSort(arr, iR.get() + 1, end);
                } finally {
                    recursiveLatch.countDown();
                }
            });
        }
        else {
            ParallelDualPivotLomutoQuickSort(arr, iR.get() + 1, end);
        }
    }

    private static void swap(Integer[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int LR_Lomuto(Integer[] arr,int bb,int ee,int j,int p){
        for(int i= bb; i<= ee; i++){
            if (arr[i] < p){
                swap(arr, i, j++);
            }
        }
        return j;
    }
    private static int RL_Lomuto(Integer[] arr, int bb, int ee, int j, int p){
        for(int i = ee; i >= bb; i--){
            if (arr[i] >= p){
                swap(arr, i, j--);
            }
        }
        return j;
    }
}
