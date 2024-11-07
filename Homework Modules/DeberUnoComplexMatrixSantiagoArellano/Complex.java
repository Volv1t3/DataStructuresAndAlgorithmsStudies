package DeberUnoComplexMatrixSantiagoArellano;


/**=============================================
 * ?                Data
 * 1. Nombre: Santiago Arellano [00328370]
 * 2. Clase: Data Structures And Algorithms
 * 3. Descripcion: La presente interface, introduce las funciones necesarias para una clase base de numeros complejos
 requeridos por el ejercicio 19.11 basado en 13.17
 ============================================**/

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Complex implements Cloneable, Comparable<Complex> {

    //! Definicion de Variables Privadas
    private BigDecimal m_parteReal = BigDecimal.ZERO;
    private BigDecimal m_parteImaginaria = BigDecimal.ZERO;
    private BigDecimal m_modulo = BigDecimal.ZERO;

    //! Definicion Constructores
    public  Complex(BigDecimal m_parteReal, BigDecimal m_parteImaginaria) {
        this.m_parteReal = m_parteReal;
        this.m_parteImaginaria = m_parteImaginaria;
    }

    public Complex(BigDecimal m_parteReal) {
        this.m_parteReal = m_parteReal;
        this.m_parteImaginaria = BigDecimal.valueOf(0);
    }
    public Complex() {
        this.m_parteReal = BigDecimal.valueOf(0);
        this.m_parteImaginaria = BigDecimal.valueOf(0);
    }

    //! Definicion Getters, no se usan setters
    public BigDecimal getParteReal() {
        return m_parteReal;
    }
    protected void setParteReal(BigDecimal m_parteReal) {
        this.m_parteReal = m_parteReal;
    }
    public BigDecimal getParteImaginaria() {
        return m_parteImaginaria;
    }
    protected void setParteImaginaria(BigDecimal m_parteImaginaria) {
        this.m_parteImaginaria = m_parteImaginaria;
    }
    public BigDecimal getModulo() {
        this.abs();
        return m_modulo;
    }

    //! Definicion Metodos

    public void add(Complex m_right) throws NullPointerException {
        //! Paso Base: Revision de Instancias Enviadas
        nullCheck(m_right);
        //! Paso Inductivo:  Suma de Numeros Complejos
        this.setParteReal(this.getParteReal().add(m_right.getParteReal()));
        this.setParteImaginaria(this.getParteImaginaria().add(m_right.getParteImaginaria()));
    }

    public void subtract(Complex m_right) throws NullPointerException {
        //! Paso Base: Revision de Instancias Enviadas
        nullCheck(m_right);
        //! Paso Inductivo: Resta de Numeros Complejos
        this.setParteReal(this.getParteReal().subtract(m_right.getParteReal()));
        this.setParteImaginaria(this.getParteImaginaria().subtract(m_right.getParteImaginaria()));
    }

    public void multiply(Complex m_right) throws NullPointerException {
        //! Paso Base: Revision de Instancias Enviadas
        nullCheck(m_right);
        Complex internalCopy = this.clone();
        //! Paso Inductivo: Multiplicacion de Numeros Complejos
        this.setParteReal(
                internalCopy.getParteReal().
                        multiply(m_right.getParteReal()).
                        subtract(internalCopy.getParteImaginaria().
                                multiply(m_right.getParteImaginaria()
                                ))
        );
        this.setParteImaginaria(internalCopy.getParteImaginaria()
                .multiply(m_right.getParteReal())
                .add(internalCopy.getParteReal()
                        .multiply(m_right.getParteImaginaria())
                ));
    }

    public void divide(Complex m_right) throws NullPointerException {
        //! Paso Base: Revision de Instancias Enviadas
        nullCheck(m_right);
        Complex internalCopy = this.clone();
        //! Paso Inductivo: Division de Complejos
        BigDecimal realPartNumerator = internalCopy.getParteReal().
                multiply(m_right.getParteReal())
                .add(internalCopy.getParteImaginaria()
                        .multiply(m_right.getParteImaginaria()));
        BigDecimal realPartDenominator = m_right.getParteReal()
                .pow(2)
                .add(m_right.getParteImaginaria().pow(2));
        BigDecimal imaginaryPartNumerator = internalCopy.getParteImaginaria()
                .multiply(m_right.getParteReal())
                .subtract(internalCopy.getParteReal()
                        .multiply(m_right.getParteImaginaria()));

        //! Storage of Results
        int decimals = 3;
        this.setParteReal(realPartNumerator.divide(realPartDenominator, decimals, RoundingMode.HALF_DOWN));
        this.setParteImaginaria(imaginaryPartNumerator.divide(realPartDenominator, decimals,RoundingMode.HALF_DOWN));
    }


    protected void abs() throws NullPointerException {
        //! Paso Base: Revision de Instancias Enviadas
        this.m_modulo = this.getParteReal()
                .pow(2)
                .add(this.getParteImaginaria()
                        .pow(2)).sqrt(MathContext.DECIMAL32);
    }

    @Override
    public int compareTo(Complex other) {
        return this.getModulo().compareTo(other.getModulo());
    }


    private static void nullCheck(Complex m_right) {
        if (m_right == null) {
            throw new NullPointerException("Error Code 0x0001 - [Raised] Uno o ambos parametros enviados a la funcion @add," +
                    "fueron nulos. Favor revisar su input.");
        }
    }

    @Override
    public Complex clone() {
        try {
            Complex clone = (Complex) super.clone();
            clone.setParteReal(this.getParteReal());
            clone.setParteImaginaria(this.getParteImaginaria());
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        try {
            switch(this.getParteReal().compareTo(BigDecimal.ZERO)) {
                case -1: { result.append("-" + this.getParteReal().abs()); break; }
                default: { result.append(this.getParteReal()); break; }
            }
            switch(this.getParteImaginaria().compareTo(BigDecimal.ZERO)) {
                case 1: { result.append(" + " + this.getParteImaginaria() + "i"); break; }
                case -1: { result.append(" - " + this.getParteImaginaria().abs() + "i"); break; }
            } return result.toString();
        }
        catch(ArithmeticException e) {
            throw new RuntimeException(e);
        }

    }
}
