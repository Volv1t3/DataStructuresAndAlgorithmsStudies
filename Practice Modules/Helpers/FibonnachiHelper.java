package Helpers;

import java.util.ArrayList;
import java.util.List;

public class FibonnachiHelper {

    public static int fiboFinder(int fiboToFind, List<Integer> memoization)
    {
        memoization.addAll(List.of(0,1));
        while (memoization.size() <= fiboToFind) {
            int size = memoization.size();
            memoization.add(memoization.get(size - 1) + memoization.get(size - 2));
        }
        return memoization.get(fiboToFind);
    }

    public static void main(String[] args) {
        System.out.println(fiboFinder(4, new ArrayList<>()));
    }
}
