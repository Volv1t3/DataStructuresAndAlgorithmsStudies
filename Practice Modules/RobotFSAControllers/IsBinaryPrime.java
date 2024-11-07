package RobotFSAControllers;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

public class IsBinaryPrime {

    public static void main(String[] args) {
        System.out.println(isBinaryPrime("1010").toMap().toString());
    }
    public static results isBinaryPrime(String binNumber)
    {
        //! Paso Base: Transformar binNumber a Integer
        BigInteger  bigInteger = new BigInteger(binNumber.toString(), 2);

        //! Paso Base: Revision de Casos de Escape
        if (Objects.equals(binNumber, "#"))
        {
            return new results(Boolean.FALSE, Boolean.TRUE);
        }
        if (Objects.equals(binNumber, "*"))
        {
            return new results(Boolean.FALSE, Boolean.TRUE);
        }

        //! Paso Inductivo: Analizamos el numero para determinar si es binario
        if ( bigInteger.compareTo(BigInteger.ONE) < 0) {
            return new results(Boolean.FALSE, Boolean.FALSE);
        }
        if (bigInteger.compareTo(BigInteger.TWO) ==0) {
            return new results(Boolean.TRUE, Boolean.FALSE);}
        if (bigInteger.mod(BigInteger.TWO).intValue() == 0) {
            return new results(Boolean.FALSE, Boolean.FALSE);}
        for (int i = 3; i <= bigInteger.sqrt().intValue(); i += 2)
        {
            System.out.printf("ith value = %s\n",i);
            if (bigInteger.intValue() % i == 0) {
                return new results(Boolean.FALSE, Boolean.FALSE);}
        }

        return new results(Boolean.TRUE, Boolean.FALSE);
    }

    public static final record results(Boolean isBinaryPrime, Boolean isControlCharacter)
    {
        public results(Boolean isBinaryPrime, Boolean isControlCharacter) {
            this.isBinaryPrime = isBinaryPrime;
            this.isControlCharacter = isControlCharacter;
        }

        public Boolean getIsBinaryPrime() {
            return isBinaryPrime;
        }
        public Boolean getIsControlCharacter() {
            return isControlCharacter;
        }

        //! Transformar resultado en entrada de Mapa
        public Map<String, Boolean> toMap()
        {
            return Map.of("isBinaryPrime",isBinaryPrime,"isControlCharacter",isControlCharacter);
        }

        //! ToString Overwrite
        @Override
        public String toString() {
            return "results{" +
                    "isBinaryPrime=" + isBinaryPrime +
                    ", isControlCharacter=" + isControlCharacter +
                    '}';
        }
    }
}
