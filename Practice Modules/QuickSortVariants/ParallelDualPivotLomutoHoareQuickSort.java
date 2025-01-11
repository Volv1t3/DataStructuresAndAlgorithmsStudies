package QuickSortVariants;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ParallelDualPivotLomutoHoareQuickSort
{
    private static final ExecutorService parallelExecutor = Executors.newFixedThreadPool(24);
    private static final int Ustl = 200_000; //! Limite Base de elementos para realizar un sort paralelo
    private static final int Udf = 1_000_000; //! Limite Base de elementos en una llamada recursiva paralelizada
    public static void sort(Integer[] arr){
        if (arr.length <= 1 || arr == null){
            return;
        }
        ParallelDualPivotLomutoHoare(arr, 0, arr.length -1);

    }
    private static void ParallelDualPivotLomutoHoare(Integer[] arr, int beg, int end) {
        if (beg >= end) {
            return;
        }

        if (end - beg + 1 < Ustl) {
            Arrays.sort(arr, beg, end + 1);
            return;
        }

        int mid = (beg + end) / 2;

        /**
         * Proceso de Seleccion de Pivotes, utilizamos inicio y final por facilidad de implementacion (el paper no menciona
         * que mecanismo usar en esta seccion para optimos resultados)
         */
        int iPLo = beg;
        int iPHi = end;
        int pLo = arr[iPLo]; //Estas dos llamadas toman los valores originales de los extremos para ver si estan fuera de
                            // lugar, parecido a un Sedgewick quick sort
        int pHi = arr[iPHi];

        /* * Si Los pivotes estan fuera de lugar, se intercambian y se seleccionan nuevamente*/
        if (pLo > pHi) {
            swap(arr, iPLo, iPHi);
            pLo = arr[iPLo];
            pHi = arr[iPHi];
        }

        //! Acercamos los pivotes al valor medio del arreglo y seleccionamos de nuevo
        swap(arr, iPLo, mid - 1);
        swap(arr, iPHi, mid + 1);
        iPLo = mid - 1;
        iPHi = mid + 1;
        pLo = arr[iPLo];
        pHi = arr[iPHi];

        //! Modelo de particion de Lomuto para la izquierda y la derecha de los arreglos
        CountDownLatch partitionLatch = new CountDownLatch(2);
        AtomicInteger iLMTL = new AtomicInteger(beg);
        AtomicInteger iLMTR = new AtomicInteger(end);

        int finalPLo = pLo;
        int finalIPLo = iPLo;
        parallelExecutor.submit(() -> {
            try {
                if (beg < finalIPLo - 1) {
                    iLMTL.set(LR_Lomuto(arr, beg, finalIPLo - 1, finalPLo));
                }
            } finally {
                partitionLatch.countDown();
            }
        });

        int finalIPHi = iPHi;
        int finalPHi = pHi;
        parallelExecutor.submit(() -> {
            try {
                if (finalIPHi + 1 < end) {
                    iLMTR.set(RL_Lomuto(arr, finalIPHi + 1, end, finalPHi));
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

        //! Intercambios de los pivotes a sus posiciones correctas como menciona Lomuto
        swap(arr, iLMTL.get(), iPLo);
        swap(arr, iLMTR.get(), iPHi);

        //! Particion de Hoare para la seccion del medio
        int iHoL = Hoare_Partition(arr, iLMTL.get(), iLMTR.get(), pLo);
        int iHoR = Hoare_Partition(arr, iLMTL.get(), iLMTR.get(), pHi);

        //! Revision de nivel de recursion tolerable del paper
        if (end - beg + 1 > Udf) {
            CountDownLatch recursiveLatch = new CountDownLatch(3);

            if (beg < iHoL) {
                parallelExecutor.submit(() -> {
                    try {
                        ParallelDualPivotLomutoHoare(arr, beg, iHoL - 1);
                    } finally {
                        recursiveLatch.countDown();
                    }
                });
            } else {
                recursiveLatch.countDown();
            }

            if (iHoL < iHoR - 1) {
                parallelExecutor.submit(() -> {
                    try {
                        ParallelDualPivotLomutoHoare(arr, iHoL, iHoR - 1);
                    } finally {
                        recursiveLatch.countDown();
                    }
                });
            } else {
                recursiveLatch.countDown();
            }

            if (iHoR < end) {
                parallelExecutor.submit(() -> {
                    try {
                        ParallelDualPivotLomutoHoare(arr, iHoR, end);
                    } finally {
                        recursiveLatch.countDown();
                    }
                });
            } else {
                recursiveLatch.countDown();
            }

            try {
                recursiveLatch.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } else {
            if (beg < iHoL) ParallelDualPivotLomutoHoare(arr, beg, iHoL - 1);
            if (iHoL < iHoR - 1) ParallelDualPivotLomutoHoare(arr, iHoL, iHoR - 1);
            if (iHoR < end) ParallelDualPivotLomutoHoare(arr, iHoR, end);
        }
    }
    private static int Hoare_Partition(Integer[] arr, int bb, int ee, int pivot){
        int i =bb, j =ee;
        while (i < j){
            while (i < j && arr[i] < pivot)i++;
            while ( i < j && arr[j] >= pivot) j--;
            if (i  < j){
                swap(arr, i ,j);
            }
        }
        return i;
    }
    public static int LR_Lomuto(Integer[] arr, int bb, int ee, int pivot){
        int j = bb;
        for (int i = bb;  i<= ee; i++){
            if (arr[i] < pivot){
                swap(arr, i, j++);
            }
        }
        return j;
    }
    public static int RL_Lomuto(Integer[] arr, int bb, int ee, int pivot){
        int j = ee;
        for(int i = ee; i >= bb; i--){
            if (arr[i] >= pivot){
                swap(arr, i, j--);
            }
        }
        return j;
    }
    private static void swap(Integer[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
