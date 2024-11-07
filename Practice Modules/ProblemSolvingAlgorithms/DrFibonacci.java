package ProblemSolvingAlgorithms;
import java.util.*;
import java.lang.*;
import java.io.*;


public class DrFibonacci {
    private static final int PISANO_PERIOD_MOD_10 = 60;
    public static void main (String[] args) {

        /*
         * The main idea behind this algorithm is a quick an simple non recursive approach to calculating the fib sequence,
         * for this we are using the tabulation (bottom-up) DP approach shown in the private method below. To do this we
         * read the problem and noticed that they said that in t0 they had one cell, and at t1 they kept that cell and so forth
         * so in our case we adapted the array to have 0,1 to be 1 and 2 to be their sum, adapting to the requirements
         * of the problem and forcing the program to iterate from index three which would be 3 in our case.
         *
         * To calculate the output, they told us that a certain number % 10 of humans would survive which means the output
         * of the fib matters because it provides the number of humans from where to extrac the others.
         */
        Scanner scanner = new Scanner(System.in);
        Integer totalCases = scanner.nextInt();
        scanner.nextLine();
        for(int caso = 0 ; caso < totalCases; caso++){
            Integer generation = scanner.nextInt();
            Integer totalHumans = fibonnaciCalculator(generation);
            System.out.println(totalHumans);
        }
    }

    /*
     * One of the key aspects that boosted this algorithms performance is the correct suggestion made by Jetbrains AI
     * about using the PISANO Period calculation to take advante of  periodicity in the fibonnaci series. This meant
     * that instead of doing a linear analysis to 10^9 items, we now do a much faster calculation which I shall take a
     * look at later on. Given the problem Specification the program has been updated to abide by the d[0] = 1 rules and
     * so on
     */
    private static int fibonnaciCalculator(int n){
        // Pisano period for modulo 10 is 60
        n = (n -1) % PISANO_PERIOD_MOD_10 + 1;

        if (n == 1) return 1;
        if (n == 2) return 2;

        int previous = 1;
        int current = 2;

        for (int i = 3; i <= n; i++) {
            int temp = (previous + current) % 10;
            previous = current;
            current = temp;
        }

        return current;
    }
}
