package DeberDosOptimalBinPackageSantiagoArellano;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*=============================================
        ?                Data
  1. Name: Santiago Arellano [00328370]
  2. Class: Data Structures And Algorithms
  3. Description: El presente archivo, incluye todas las funciones necesarias para la ejecucion del algoritmo
  Optimal Bin Packaging, basado en la implementacion del Longest Subarray Sum, Divide And Conquer Approach, presentado
   en Weiss Capitulo 2 Running Time Calculations
 ============================================*/
public class OptimalBinPackagingDriver {


    public static int[] __driver__BuscarSubArray(ArrayList<CargoObject> items,
                                                    BigDecimal maxWeight,
                                                    HashSet<Integer> usedIndices) {
        return maxSumRec(items, 0, items.size() - 1, maxWeight, usedIndices);
    }

    public static ArrayList<PackageObject> __driver__BuscarPaquetesOptimos(
            ArrayList<CargoObject> items, BigDecimal maxWeight) {

        //! Variables Internas
        ArrayList<PackageObject> internal_Paquetes = new ArrayList<>(); //? ArrayListSA para contener a los paquetes
        HashSet<Integer> internal_IndicesUsados = new HashSet<>(); //? HashSet para contener a los indices de elementos
        //? ya visitados


        while (internal_IndicesUsados.size() < items.size()) {
            //! Paso Base: Inicializamos una variable base para recibir a los indices de los objetos
            //! a empaquetar
            int[] internal_ObjectResultArray = __driver__BuscarSubArray(items, maxWeight, internal_IndicesUsados);

            //! Paso Base.1: Si recibimos un -1 en la posicion 0 o 1,
            //! significa que hemos termiado nuestras opciones para particionar el arreglo, o solo existe un valor
            if (internal_ObjectResultArray[0] == -1 || internal_ObjectResultArray[1] == -1) break;

            //! Paso Inductivo.1: Creamos una variable paquete para almacenar los objetos retornados en subset
            PackageObject internal_PaqueteALlenar = new PackageObject();
            BigDecimal internal_PesoPorPaquete = BigDecimal.ZERO;

            //! Paso Inductivo.2: Utilizamos el metodo extraido para iterar sobre nuestros items y anadirlos a su paquete
            //! de destino
            __internal__IteratorSobreObjetos(items, maxWeight, internal_ObjectResultArray, internal_IndicesUsados, internal_PesoPorPaquete, internal_PaqueteALlenar);
            internal_Paquetes.add(internal_PaqueteALlenar);

            //! Paso Inductivo.3: Si el tamano fina de ambos arreglos es el mismo, entonces sabemos que se han usado
            //! todos los indices dentro de nuestro ArrayListSA de objetos.
            if (internal_IndicesUsados.size() == items.size()) break;
        }

        //! Paso Inductivo.4: Si el algoritmo fallo en detectar los ultimos objeto, usamos tres metodos de programacion
        //! funcional para hallarlos y repartirlos
        /*
         * Explicacion de cadena funcional:
         * Utilizamos stream(), para producir un Stream<DeberDosOptimalBinPackageSantiagoArellano.CargoObject> a partir de items.stream()
         * Luego, utilizamos filter() para filtrar los objetos que no han sido usados en internal_IndicesUsados
         * Finalmente, utilizamos toList() para convertir el Stream<DeberDosOptimalBinPackageSantiagoArellano.CargoObject> en una List<DeberDosOptimalBinPackageSantiagoArellano.CargoObject>
         * */
        List<CargoObject> internal_CargosRestantes = items.stream()
                .filter(i -> !internal_IndicesUsados.contains(items.indexOf(i)))
                .toList();

        /*
         * Explicacion de cadena funcional
         *
         * El presente metodo hace un analisis interno restante para intentar llenar a todos los paquetes con un numero
         * cercan al maximo de 10. Para esto partimos de un forEach general a los valores de cargas restantes, que ya
         * fueron filtrados en el paso anterior. Como trabajamos en paralelo aprovechamos la fuerza del computador aunque
         * el numero de datos no sea elevado.
         * Luego de esto utilizamos un for normal anidado. Dentro de este for, tenemos que analizar la condicion del peso
         * total del paquete que se esta viendo. Como dentro de la clase DeberDosOptimalBinPackageSantiagoArellano.PackageObject guardamos los objetos como pares
         * de Key:Value en un mapa, usamos programacion funcional para reducir todos los valores de pesos a un valor numerico
         * aceptable.
         *
         * Usamos .values().stream() para obtener un Stream<BigDecimal> (como se guardan los pesos en DeberDosOptimalBinPackageSantiagoArellano.CargoObject),
         * luego usamos .reduce( BigDecimal.Zero, BigDecimal::add) para reducir estos valores a un solo BigDecimal. El valor
         * de la izquierda es un valor base que el acumulador utiliza como pas primario, luego usamos la funcion general de los
         * BigDecimal enviada como referencia.
         * */
        internal_CargosRestantes.stream().forEach( (cargoObject) -> {
            boolean addedToExistingPackage = false;
            for (PackageObject pkg : internal_Paquetes) {
                if (pkg.getM_internalObjectStorage()
                        .values()
                        .stream()
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .add(cargoObject.getE_CargoWeight()).compareTo(maxWeight) <= 0)
                {
                    pkg.addCargoToPackage(cargoObject);
                    addedToExistingPackage = true;
                    break;
                }
            }
            //! Si no se ha anadido el valor a un paquete, y ya no se pudo rellenar otros, se crea un paquete nuevo.
            if (!addedToExistingPackage) {
                PackageObject newPackage = new PackageObject();
                newPackage.addCargoToPackage(cargoObject);
                internal_Paquetes.add(newPackage);
            }
        });

        return internal_Paquetes;
    }

    private static void __internal__IteratorSobreObjetos(ArrayList<CargoObject> items,
                                                         BigDecimal maxWeight,
                                                         int[] internal_ObjectResultArray,
                                                         HashSet<Integer> internal_IndicesUsados,
                                                         BigDecimal internal_PesoPorPaquete,
                                                         PackageObject internal_PaqueteALlenar)
    {
        //! Paso Extraido de __driver__BuscarPaquetesOptimos
        /*
         * Dentro de la aplicacion principal, y el metodo BsucarPaquetesOptimos, utilizamos un for loop para iterarar
         * sobre los objetos que fueron retornados como la cadena de objetos mas pequena, o libres que tenia nuestros
         * objetos principales.
         *
         * Basados en esto, hacemos un analisis de: (1) Si el indice ya se encuentra utilizado, y (2) Si el peso total
         * de los objetos en el paquete actual, mas el peso del objeto actual, es menor o igual al peso maximo permitido.
         *
         * Con estos datos, trabajamos para formar paquetes con un peso menor a 10kg, la constante del problema.
         * */
        for (int i = internal_ObjectResultArray[0]; i <= internal_ObjectResultArray[1]; i++) {
            if (!internal_IndicesUsados.contains(i) &&
                    internal_PesoPorPaquete.add(items.get(i).getE_CargoWeight()).compareTo(maxWeight) <= 0) {
                internal_PaqueteALlenar.addCargoToPackage(items.get(i));
                internal_PesoPorPaquete = internal_PesoPorPaquete.add(items.get(i).getE_CargoWeight());
                internal_IndicesUsados.add(i);
            }
        }
    }

    private static int[] maxSumRec(List<CargoObject> items, int left, int right, BigDecimal maxWeight, Set<Integer> usedIndices) {

        if (left == right) {
            if (items.get(left).getE_CargoWeight().compareTo(maxWeight) <= 0 && !usedIndices.contains(left)) {
                /*Este retorno significa {array empieza en left, termina en left, con tal peso proveniente de left}*/
                return new int[]{left, left, items.get(left).getE_CargoWeight().intValue()};
            } else {
                /*Este retorno significa {out of bounds, out of bounds, sin peso}*/
                return new int[]{-1, -1, 0};
            }
        }

        //! Calculo de la division por la mitad (como busqueda binaria) y llamadas recursivas iniciales a cada seccion
        //! de los lados
        int center = (left + right) / 2;
        int[] maxLeftSum = maxSumRec(items, left, center, /*Anadimos dos datos mas al algoritmo original,
                                    memoizacion y peso maximo*/ maxWeight, usedIndices);
        int[] maxRightSum = maxSumRec(items, center + 1, right,
                /*Anademos de nuevo, dos datos mas a la llamada, peso maximo y memoizacion*/maxWeight, usedIndices);

        BigDecimal maxLeftBorderSum = BigDecimal.ZERO, leftBorderSum = BigDecimal.ZERO;
        int maxLeft = -1;
        for (int i = center; i >= left; i--) {
            /*
            * Procedimiento igual al algoritmo base, trabajamos simplemente con chequeos de memoizacion,
            * check en el if, y con checkeos al peso total, dentro del segundo if, antes de enviar cambios a las
            * variables
            * */
            if (!usedIndices.contains(i)) {
                leftBorderSum = leftBorderSum.add(items.get(i).getE_CargoWeight());
                if (leftBorderSum.compareTo(maxWeight) <= 0 && leftBorderSum.compareTo(maxLeftBorderSum) > 0) {
                    maxLeftBorderSum = leftBorderSum;
                    maxLeft = i;
                }
            }
        }

        BigDecimal maxRightBorderSum = BigDecimal.ZERO, rightBorderSum = BigDecimal.ZERO;
        int maxRight = -1;
        for (int i = center + 1; i <= right; i++) {
            if (!usedIndices.contains(i)) {
                rightBorderSum = rightBorderSum.add(items.get(i).getE_CargoWeight());
                if (rightBorderSum.compareTo(maxWeight) <= 0 && rightBorderSum.compareTo(maxRightBorderSum) > 0) {
                    maxRightBorderSum = rightBorderSum;
                    maxRight = i;
                }
            }
        }

        BigDecimal leftSum = BigDecimal.valueOf(maxLeftSum[2]);
        BigDecimal rightSum = BigDecimal.valueOf(maxRightSum[2]);
        BigDecimal borderSum = maxLeftBorderSum.add(maxRightBorderSum);

        if (leftSum.compareTo(rightSum) >= 0 && leftSum.compareTo(borderSum) >= 0) {
            return maxLeftSum;
        } else if (rightSum.compareTo(leftSum) >= 0 && rightSum.compareTo(borderSum) >= 0) {
            return maxRightSum;
        } else {
            return new int[]{maxLeft, maxRight, borderSum.intValue()};
        }
    }
}
