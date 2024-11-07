package Helpers;

public class GCDHelpers {

    public static int basicGDC(int m, int n)
    {
        int gdc = 1;
        for (int divisor = 2; divisor <= m && divisor <= n; divisor++)
        {
            if (m % divisor == 0 && n % divisor == 0) {gdc = divisor;}
        }
        return gdc;
    }

    public static int reverseGDC(int m, int n)
    {
        int gdc = 1;
        for(int divisor = m /2; divisor >= 1; divisor--)
        {
            if (m % divisor == 0 && n % divisor == 0)
            {
                gdc = divisor;
                break;
            }
        }
        return gdc;
    }

    public static int euclidGDC(int m, int n)
    {
        if (m % n == 0){return n;}
        else {return euclidGDC(n, m% n);}
    }

    public static void main(String[] args) {
        System.out.println(basicGDC(12, 18));
        System.out.println(reverseGDC(12, 18));
        System.out.println(euclidGDC(3, 5));
    }
}
