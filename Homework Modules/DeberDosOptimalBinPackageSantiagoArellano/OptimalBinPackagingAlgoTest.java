package DeberDosOptimalBinPackageSantiagoArellano;

import java.math.BigDecimal;
import java.util.*;

/*=============================================
        ?                Data
  1. Name: Santiago Arellano [00328370]
  2. Class: Data Structures And Algorithms
  3. Description: El presente archivo implementa una variacion del algoritmo Divide-And-Conquer para Maximum Contigous
  Subarray. En base a este algoritmo se trabaja para encontrar rapidamente y recursivamente las posibles separaciones
  por orden de longitud de los objetos en sus respectivos paquetes
 ============================================*/

public class OptimalBinPackagingAlgoTest {

    public static void main(String[] args) {
        //! Structura para mantener los dato
        ArrayList<CargoObject> cargos = new ArrayList<>();
        cargos.add(new CargoObject("Carga 1", BigDecimal.valueOf(2.5)));
        cargos.add(new CargoObject("Carga 2", BigDecimal.valueOf(3.7)));
        cargos.add(new CargoObject("Carga 3", BigDecimal.valueOf(1.8)));
        cargos.add(new CargoObject("Carga 4", BigDecimal.valueOf(4.2)));
        cargos.add(new CargoObject("Carga 5", BigDecimal.valueOf(2.1)));
        cargos.add(new CargoObject("Carga 6", BigDecimal.valueOf(3.9)));
        cargos.add(new CargoObject("Carga 7", BigDecimal.valueOf(1.7)));
        cargos.add(new CargoObject("Carga 8", BigDecimal.valueOf(4.1)));
        cargos.add(new CargoObject("Carga 9", BigDecimal.valueOf(2.3)));
        cargos.add(new CargoObject("Carga 10", BigDecimal.valueOf(3.6)));
        cargos.add(new CargoObject("Carga 11", BigDecimal.valueOf(1.9)));
        cargos.add(new CargoObject("Carga 12", BigDecimal.valueOf(4.0)));
        cargos.add(new CargoObject("Carga 13", BigDecimal.valueOf(2.2)));
        cargos.add(new CargoObject("Carga 14", BigDecimal.valueOf(3.8)));
        cargos.add(new CargoObject("Carga 15", BigDecimal.valueOf(1.6)));
        cargos.add(new CargoObject("Carga 16", BigDecimal.valueOf(4.4)));
        cargos.add(new CargoObject("Carga 17", BigDecimal.valueOf(2.0)));
        cargos.add(new CargoObject("Carga 18", BigDecimal.valueOf(3.5)));
        cargos.add(new CargoObject("Carga 19", BigDecimal.valueOf(1.7)));
        cargos.add(new CargoObject("Carga 20", BigDecimal.valueOf(4.3)));
        cargos.add(new CargoObject("Carga 21", BigDecimal.valueOf(2.4)));
        cargos.add(new CargoObject("Carga 22", BigDecimal.valueOf(3.4)));
        cargos.add(new CargoObject("Carga 23", BigDecimal.valueOf(1.8)));

        //! Para trabajar con nuestra busqueda, colocamos los valores en orden reverso, de mayor a menor
        /*
        * */
        //! Utilizamos un nuevo ArrayListSA Para Guardar El Resultado del Algoritmo
        ArrayList<PackageObject> packages = OptimalBinPackagingDriver.__driver__BuscarPaquetesOptimos(cargos, BigDecimal.
                valueOf(PackageObject.MAX_CAPACITY_KILOS));

        for (int i = 0; i < packages.size(); i++) {
            System.out.println("Package " + (i + 1) + " contains objects: " + packages.get(i).getM_internalObjectStorage());
        }
        System.out.println("The optimal number of packages is " + packages.size());
    }









}