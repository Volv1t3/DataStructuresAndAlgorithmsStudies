package DeberDosOptimalBinPackageSantiagoArellano;/*=============================================
  ?                Data
  1. Name: Santiago Arellano [00328370]
  2. Class: Data Structures And Algorithms
  3. Description: El presente Record, parte de Data Oriented Programming, facilita el control de datos y comparaciones
  de los objetos a enviar dentro del algoritmo Optimal Bin Packaging.
 ============================================*/

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

public record CargoObject(String e_CargoName, BigDecimal e_CargoWeight) implements Comparable<CargoObject> {

    //! Constructor del Record DeberDosOptimalBinPackageSantiagoArellano.CargoObject
    /**
     * Constructor del record `DeberDosOptimalBinPackageSantiagoArellano.CargoObject`.
     *
     * @param e_CargoName   Nombre del cargo. No puede ser null.
     * @param e_CargoWeight Peso del cargo en forma de BigDecimal. No puede ser null.
     *
     * <p>Este constructor inicializa un objeto `DeberDosOptimalBinPackageSantiagoArellano.CargoObject` con el nombre del cargo y el peso del cargo
     * proporcionados. Ambos parametros deben ser no nulos. </p>
     *
     * Ejemplo de uso:
     * <pre>
     * {@code
     * DeberDosOptimalBinPackageSantiagoArellano.CargoObject cargo = new DeberDosOptimalBinPackageSantiagoArellano.CargoObject("Carga1", new BigDecimal("100.50"));
     * }
     * </pre>
     *<br>
     * <p>Mecanismo interno: el constructor asigna los valores proporcionados a las variables de instancia
     * `e_CargoName` y `e_CargoWeight` del objeto que se esta creando. </p>
     *
     */
    public CargoObject(String e_CargoName, BigDecimal e_CargoWeight) {
        this.e_CargoName = e_CargoName;
        this.e_CargoWeight = e_CargoWeight;
    }

    //! Getter Para Cargo Name
    /**
     * Metodo getter para obtener el nombre del cargo.
     *
     * @return El nombre del cargo de tipo String.
     *
     * <p>Este metodo retorna el valor de la variable de instancia `e_CargoName`.
     * Es utilizado para acceder al nombre del cargo almacenado en el objeto `DeberDosOptimalBinPackageSantiagoArellano.CargoObject`.
     * </p>

     * <p>Ejemplo de uso:
     * <pre>
     * {@code
     * DeberDosOptimalBinPackageSantiagoArellano.CargoObject cargo = new DeberDosOptimalBinPackageSantiagoArellano.CargoObject("Carga1", new BigDecimal("100.50"));
     * String nombreCargo = cargo.getE_CargoName();
     * }
     * </pre>
     * </p>
     */
    public String getE_CargoName() {
        return e_CargoName;

    }
    //! Getter Para Cargo Weight
    /**
     * Metodo getter para obtener el peso del cargo.
     *
     * @return El peso del cargo de tipo BigDecimal.
     *
     * <p>Este metodo retorna el valor de la variable de instancia `e_CargoWeight`.
     * Es utilizado para acceder al peso del cargo almacenado en el objeto `DeberDosOptimalBinPackageSantiagoArellano.CargoObject`.
     * </p>
     *
     * <p>Ejemplo de uso:
     * <pre>
     * {@code
     * DeberDosOptimalBinPackageSantiagoArellano.CargoObject cargo = new DeberDosOptimalBinPackageSantiagoArellano.CargoObject("Carga1", new BigDecimal("100.50"));
     * BigDecimal pesoCargo = cargo.getE_CargoWeight();
     * }
     * </pre>
     * </p>
     *
     * <p>No se espera que este metodo arroje ninguna excepcion. </p>
     */
    public BigDecimal getE_CargoWeight() {
        return e_CargoWeight;
    }

    //! Metodo para transformar a entrada de mapa
    /**
     * Metodo para convertir los datos del objeto DeberDosOptimalBinPackageSantiagoArellano.CargoObject a una entrada de mapa (Map.Entry).
     *
     * @return Un objeto de tipo Map.Entry<String, BigDecimal> que contiene el nombre del cargo como clave (String)
     * y el peso del cargo como valor (BigDecimal).
     *
     * <p>Este metodo no tiene parametros de entrada ya que utiliza los valores de las variables de instancia
     * `e_CargoName` y `e_CargoWeight` del objeto DeberDosOptimalBinPackageSantiagoArellano.CargoObject.</p>

     * <p>Ejemplo de uso:
     * <pre>
     * {@code
     * DeberDosOptimalBinPackageSantiagoArellano.CargoObject cargo = new DeberDosOptimalBinPackageSantiagoArellano.CargoObject("Carga1", new BigDecimal("100.50"));
     * Map.Entry<String, BigDecimal> entradaMapa = cargo.toMapEntry();
     * }
     * </pre>
     * </p>
     */
    public Map.Entry<String, BigDecimal> toMapEntry() {
        return Map.entry(e_CargoName, e_CargoWeight);
    }

    //! Metodo para retornar una string del objeto
    /**
     * Este metodo sobrescribe el metodo `toString` de la clase `Object`.
     *
     * @return Una representacion en cadena (String) del objeto `DeberDosOptimalBinPackageSantiagoArellano.CargoObject`.
     *
     * <p>El metodo no recibe parametros de entrada. Al ser un metodo sobrescrito de la clase `Object`,
     * este no arroja ninguna excepcion controlada.
     *
     * <p>La cadena de texto retornada tiene el siguiente formato:
     * <pre>
     * {@code
     * DeberDosOptimalBinPackageSantiagoArellano.CargoObject{e_CargoName='[nombre del cargo]', e_CargoWeight=[peso del cargo]}
     * }
     * </pre>
     * donde `[nombre del cargo]` es el valor de la variable `e_CargoName` y `[peso del cargo]` es el valor de la
     * variable `e_CargoWeight`.
     *
     *
     */
    @Override
    public String toString() {
        return "DeberDosOptimalBinPackageSantiagoArellano.CargoObject{" +
                "e_CargoName='" + e_CargoName + '\'' +
                ", e_CargoWeight=" + e_CargoWeight +
                '}';
    }
    //! Metodo para comparar objetos
    /**
     * Sobrescribe el metodo `equals` de la clase `Object` para comparar dos objetos `DeberDosOptimalBinPackageSantiagoArellano.CargoObject`.
     *
     * @param obj El objeto a comparar con el objeto actual.
     * @return `true` si los objetos son iguales, `false` en caso contrario.
     *
     * <p>Este metodo utiliza las siguientes reglas para determinar si los objetos son iguales:</p>
     * <ul>
     *   <li>Si `obj` es el mismo que el objeto actual (`this`), retorna `true`.</li>
     *   <li>Si `obj` no es una instancia de `DeberDosOptimalBinPackageSantiagoArellano.CargoObject`, retorna `false`.</li>
     *   <li>Si `obj` es una instancia de `DeberDosOptimalBinPackageSantiagoArellano.CargoObject`, compara los nombres de cargo y los pesos de cargo
     *       utilizando los metodos `Objects.equals`. Si ambos valores son iguales, retorna `true`; de lo
     *       contrario, retorna `false`.</li>
     * </ul>
     *
     * <p>Ejemplo de uso:</p>
     * <pre>
     * {@code
     * DeberDosOptimalBinPackageSantiagoArellano.CargoObject cargo1 = new DeberDosOptimalBinPackageSantiagoArellano.CargoObject("Carga1", new BigDecimal("100.50"));
     * DeberDosOptimalBinPackageSantiagoArellano.CargoObject cargo2 = new DeberDosOptimalBinPackageSantiagoArellano.CargoObject("Carga1", new BigDecimal("100.50"));
     * boolean sonIguales = cargo1.equals(cargo2);  // true
     * }
     * </pre>
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CargoObject)) {
            return false;
        }
        CargoObject other = (CargoObject) obj;
        return Objects.equals(this.getE_CargoName(), other.getE_CargoName()) &&
                Objects.equals(this.getE_CargoWeight(), other.getE_CargoWeight());
    }

    //! Metodo para implementar la interface comparable
    @Override
    public int compareTo(CargoObject o) {
        return Comparator.comparing(CargoObject::getE_CargoWeight)
                .compare(this, o);
    }
}
