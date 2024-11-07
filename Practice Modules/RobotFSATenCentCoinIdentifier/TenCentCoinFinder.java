package RobotFSATenCentCoinIdentifier;

import java.math.BigInteger;
import java.util.Objects;

public class TenCentCoinFinder {

    public static Boolean[] isBinaryPrimeOrControlCharacter(String BinaryPrimeCandidate)
    {
        //! Paso Base: Transformar binNumber a Integer


        Boolean[] FALSE = determineIfControl(BinaryPrimeCandidate);
        if (FALSE != null) return FALSE;

        BigInteger bigInteger = new BigInteger(BinaryPrimeCandidate, 2);
        //! Paso Inductivo: Analizamos el numero para determinar si es binario
        if ( bigInteger.compareTo(BigInteger.ONE) < 0) { return new Boolean[] {Boolean.FALSE, Boolean.FALSE};
        }
        if (bigInteger.compareTo(BigInteger.TWO) ==0) { return new Boolean[] {Boolean.TRUE, Boolean.FALSE};}
        if (bigInteger.mod(BigInteger.TWO).intValue() == 0) { return new Boolean[] {Boolean.FALSE, Boolean.FALSE};}
        for (int i = 3; i <= bigInteger.sqrt().intValue(); i += 2)
        {
            if (bigInteger.intValue() % i == 0) {return new Boolean[] {Boolean.FALSE, Boolean.FALSE};}
        }

        return new Boolean[] {Boolean.TRUE, Boolean.FALSE};
    }

    public static Boolean[] containsBinaryStringTenOrControl(String BinaryTenCentCandidate)
    {

        Boolean[] FALSE = determineIfControl(BinaryTenCentCandidate);
        if (FALSE != null) return FALSE;

        //! Paso Inductivo: Analizamos caracter por caracter buscando una subsequencia que sea similar a 10
        if (BinaryTenCentCandidate.contains("10")) {return new Boolean[] {Boolean.TRUE, Boolean.FALSE};}

        return new Boolean[] {Boolean.FALSE, Boolean.FALSE};
    }



    public static Boolean[] isBinaryTenCentCoin(String BinaryTenCentCandidate)
    {
        Boolean[] FALSE = determineIfControl(BinaryTenCentCandidate);
        if (FALSE != null) return FALSE;
        // Convert binary string to a BigInteger
        BigInteger bigInteger = new BigInteger(BinaryTenCentCandidate, 2);
        // Check if the decimal value is 10
        if (bigInteger.equals(BigInteger.TEN)) {
            return new Boolean[] {Boolean.TRUE, Boolean.FALSE};
        }
        return new Boolean[] {Boolean.FALSE, Boolean.FALSE}; 
    
    }
    
    public static Boolean[]  isBinaryMoneyDivisibleIntoTenCentCointsOrControl(String BinaryTenCentCandidate)
    {
        Boolean[] FALSE = determineIfControl(BinaryTenCentCandidate);
        if (FALSE != null) return FALSE;

        //! Paso Inductivo: Analizamos caracter por caracter buscando una subsequencia que sea similar a 10
        BigInteger amount = new BigInteger(BinaryTenCentCandidate, 2);

        // Check if the amount is divisible by 10
        if (amount.mod(BigInteger.TEN).equals(BigInteger.ZERO)) {
            return new Boolean[] {Boolean.TRUE, Boolean.FALSE};
        }
        return new Boolean[] {Boolean.FALSE, Boolean.FALSE};
    }

    private static Boolean[] determineIfControl(String BinaryTenCentCandidate) {
        //! Paso Base: Revision de Casos de Escape
        if (Objects.equals(BinaryTenCentCandidate, "#"))
        {
            return new Boolean[]{Boolean.FALSE, Boolean.TRUE};
        }
        if (Objects.equals(BinaryTenCentCandidate, "*"))
        {
            return new Boolean[]{Boolean.FALSE, Boolean.TRUE};
        }
        return null;
    }
}
