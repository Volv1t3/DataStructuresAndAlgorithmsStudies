package DeberDosOptimalBinPackageSantiagoArellano;/*=============================================
  ?                Data
  1. Name: Santiago Arellano [00328370]
  2. Class: Data Structures And Algorithms
  3. Description: La presente clase facilita el control de datos y comparaciones
  de los paquetes a usar dentro del algoritmo Optimal Bin Packaging.
 ============================================*/

import java.math.BigDecimal;
import java.util.HashMap;

public class PackageObject {

    public static final int MAX_CAPACITY_KILOS = 10;
    private Integer m_totalObjectsStored = 0;
    private BigDecimal m_totalWeightStored = BigDecimal.ZERO;
    private HashMap<String, BigDecimal> m_internalObjectStorage = new HashMap<>();

    public PackageObject(){;}

    //! Metodo para anadir objetos al paquete.
    public boolean addCargoToPackage(CargoObject cargoObject) {
        if (m_totalWeightStored.compareTo(BigDecimal.valueOf(MAX_CAPACITY_KILOS)) < 0) {
            if (m_totalWeightStored.add(cargoObject.getE_CargoWeight()).
                    compareTo(BigDecimal.valueOf(MAX_CAPACITY_KILOS)) <= 0) {
                m_internalObjectStorage.put(cargoObject.toMapEntry().getKey(),
                        cargoObject.toMapEntry().getValue());
                m_totalWeightStored = m_totalWeightStored.add(cargoObject.getE_CargoWeight());
                m_totalObjectsStored++;
                return true;
            }
            else {return false;}
        }
        else {return false;}
    }

    public HashMap<String, BigDecimal> getM_internalObjectStorage() {
        return m_internalObjectStorage;
    }

    public static void main(String[] args) {
        CargoObject cargo1 = new CargoObject("Carga1", new BigDecimal("2.50"));
        CargoObject cargo2 = new CargoObject("Carga2", new BigDecimal("7.50"));
        PackageObject packageObject = new PackageObject();
        if (packageObject.addCargoToPackage(cargo1)) {
            System.out.println("Cargo added successfully.");
        } else {
            System.out.println("Failed to add cargo.");
        }
        if (packageObject.addCargoToPackage(cargo2)) {
            System.out.println("Cargo added successfully.");
        } else {
            System.out.println("Failed to add cargo.");
        }
        System.out.println(packageObject.getM_internalObjectStorage().keySet().toString());
    }
}
