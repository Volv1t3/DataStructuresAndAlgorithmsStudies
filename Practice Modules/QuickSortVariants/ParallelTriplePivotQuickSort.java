package QuickSortVariants;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Random;

public class ParallelTriplePivotQuickSort {
    private static final int Ustl = 100000;
    private static final ExecutorService executor = Executors.newFixedThreadPool(24);
    private static final Random random = new Random();

    public static void ptpSort(Integer[] a, int beg, int end) throws InterruptedException {
        if (end - beg + 1 < Ustl) {
            Arrays.sort(a, beg, end + 1);
            return;
        }

        int len = (end - beg) / 2;

        // Sample and get three pivots
        int[] pivots = sampling(a, beg, end);
        int PLo = pivots[0];
        int PMi = pivots[1];
        int PHi = pivots[2];

        // Use CountDownLatch for synchronization
        CountDownLatch partitionLatch = new CountDownLatch(2);
        final int[] iL = new int[1];
        final int[] iH = new int[1];

        // Left partition
        executor.submit(() -> {
            try {
                iL[0] = hoare(a, beg, beg + len - 1, PLo);
            } finally {
                partitionLatch.countDown();
            }
        });

        // Right partition
        executor.submit(() -> {
            try {
                iH[0] = hoare(a, beg + len, end, PHi);
            } finally {
                partitionLatch.countDown();
            }
        });

        // Wait for both partitions to complete
        partitionLatch.await();

        // Middle partition
        int iM = hoare(a, iL[0], iH[0] - 1, PMi);

        // Use CountDownLatch for recursive calls
        CountDownLatch recursiveLatch = new CountDownLatch(2);

        // Left recursive calls
        executor.submit(() -> {
            try {
                int ilm = hoare(a, iL[0], iM - 1, PLo);
                ptpSort(a, beg, ilm - 1); // First Subarray, from the beginning to the leftmost pointer -1
                ptpSort(a, ilm, iM - 1); //! Second Subarray, from leftmost pointer to middle pointer -1
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                recursiveLatch.countDown();
            }
        });

        // Right recursive calls
        executor.submit(() -> {
            try {
                int imh = hoare(a, iM, iH[0] - 1, PHi);
                ptpSort(a, iM, imh - 1); //! Third Subarray, from the middle up to rightmost -1
                ptpSort(a, imh, end); //! Fourth subarray, all elements that are higher than the rightmost pointer
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                recursiveLatch.countDown();
            }
        });

        // Wait for all recursive calls to complete
        recursiveLatch.await();
    }

    private static synchronized int hoare(Integer[] a, int b, int e, int P) {
        int i = b;
        int j = e;

        while (true) {
            while (i <= e && a[i] < P) i++;
            while (j >= b && a[j] >= P) j--;

            if (i > j) return j + 1;

            // Synchronized swap
            WaterlooThreePivotQuickSort.swap(a, i, j);
            i++;
            j--;
        }
    }

    private static int[] sampling(Integer[] a, int beg, int end) {
        int[] pivots = new int[3];
        for (int i = 0; i < 3; i++) {
            pivots[i] = a[beg + random.nextInt(end - beg + 1)];
        }
        Arrays.sort(pivots);
        return pivots;
    }

}