package Helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class PrimeNumberListingHelpers {

    public static void basicPrimeNumberListing(int m)
    {
        int number = 2;
        while (number < m)
        {
            boolean isPrime = true;

            for (int divisor = 2; divisor < number; divisor++)
            {
                if (number % divisor == 0){isPrime = false; break;}
            }

            if (isPrime)
            {
                System.out.println(number);
            }
            number++;
        }
    }

    public static void betterPrimeNumberListing(int m)
    {
        int number = 2;
        int sqrt = 1;
        while (number < m)
        {
            boolean isPrime = true;


            for (int divisor = 2; divisor <= sqrt; divisor++)
            {
                if (number % divisor == 0){isPrime = false; break;}
            }
            if (sqrt * sqrt < number) {sqrt++;}

            if (isPrime)
            {
                System.out.println(number);
            }
            number++;
        }
    }
    public static void evenBetterPrimeNumberListing(int m, List<Integer> primes)
    {
        int number = 2;
        int sqrt = 1;
        while (number < m)
        {
            boolean isPrime = true;
            if (sqrt * sqrt < number) {sqrt++;}

            for(int divisor = 0; divisor < primes.size() &&
                                primes.get(divisor) <= sqrt; divisor++)
            {
                if (number % primes.get(divisor) == 0){isPrime = false; break;}
            }

            if (isPrime)
            {
                System.out.println(number);
                primes.add(number);
            }
            number++;

        }
    }

    public static void sieveOfEratosthenes(int m)
    {
        boolean[] primes = new boolean[m +1];
        Arrays.fill(primes, true);

        for(int number = 2; number <= m/number; number++)
        {
            if (primes[number])
            {
                for(int multiplier = number; multiplier <= m/number; multiplier++)
                {
                    primes[number * multiplier] = false;
                }
            }
        }
        for(int number = 2; number <= m; number++)
        {
            if (primes[number])
            {
                System.out.println(number);
            }
        }
    }

    public static void main(String[] args) {
        betterPrimeNumberListing(20);
        System.out.println("Done");
        basicPrimeNumberListing(20);
        System.out.println("Done");
        evenBetterPrimeNumberListing(20, new ArrayList<>());
        System.out.println("Done");
        sieveOfEratosthenes(20);
    }
}
