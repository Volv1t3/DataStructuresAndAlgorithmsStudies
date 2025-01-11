package QuickSortVariants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PentaPivotQuickSort {

    public static List<Integer> sort(List<Integer> arr){
        return PentaPivotQuickSort(arr);
    }

    public static List<Integer> PentaPivotQuickSort(List<Integer> arr){
        //! Implementacion similar de la de Tetra Pivote, cambia la cantidad de listas y pivotes
        int length = arr.size();
        if (length <= 1){
            return arr;
        }
        else if (length <= 5){
            Collections.sort(arr);
            return arr;
        }

        //! Implementacion del metodo general
        List<Integer> pivots = new ArrayList<>(5){{
            add(arr.removeFirst()) /*Primer Pivote*/;
            add(arr.removeFirst()) /*Segundo Pivote*/;
            add(arr.removeFirst()) /*Tercer Pivote*/;
            add(arr.removeFirst()) /*Cuarto Pivote*/;
            add(arr.removeFirst()) /*Quinto Pivote*/;
        }};

        //! Organizamos para obtener la distribucion de intervalos
        Collections.sort(pivots);
        //! Creamos las listas de recepcion de valores
        ArrayList<Integer> a = new ArrayList<>(); /*Valores menores que leftmost pivot*/
        ArrayList<Integer> b = new ArrayList<>(); /*Valores entre leftmost y second leftmost pivot*/
        ArrayList<Integer> c = new ArrayList<>(); /*Valores entre second leftmost y  center pivot*/
        ArrayList<Integer> d = new ArrayList<>(); /*Valores entre center y second rightmost pivot*/
        ArrayList<Integer> e = new ArrayList<>(); /*Valores entre second rightmost y rightmost pivot*/
        ArrayList<Integer> f = new ArrayList<>(); /*Valores mayores que rightmost pivot*/

        //! Organizamos los valores de la lista de acuerdo a su rango
        particionInteraPorSegmentos(arr, pivots, a, b, c, d, e, f);

        //! Preparando nueva lista de retorno organizada
        List<Integer> result = new ArrayList<>();
        result.addAll(PentaPivotQuickSort(a));
        result.add(pivots.getFirst());
        result.addAll(PentaPivotQuickSort(b));
        result.add(pivots.get(1));
        result.addAll(PentaPivotQuickSort(c));
        result.add(pivots.get(2));
        result.addAll(PentaPivotQuickSort(d));
        result.add(pivots.get(3));
        result.addAll(PentaPivotQuickSort(e));
        result.add(pivots.get(4));
        result.addAll(PentaPivotQuickSort(f));
        return result;
    }

    private static void particionInteraPorSegmentos(List<Integer> arr,
                                                    List<Integer> pivots,
                                                    ArrayList<Integer> a,
                                                    ArrayList<Integer> b,
                                                    ArrayList<Integer> c,
                                                    ArrayList<Integer> d,
                                                    ArrayList<Integer> e,
                                                    ArrayList<Integer> f) {
        for(Integer value: arr){
            if (value < pivots.getFirst()) /*Intervalo de a*/{
                a.add(value);
            }
            else if (pivots.getFirst() <= value && value < pivots.get(1))
            /*Intervalo de b*/{
                b.add(value);
            }
            else if (pivots.get(1) <= value && value < pivots.get(2)) /*
            Intervalo de c*/{
               c.add(value);
            }
            else if (pivots.get(2) <= value && value < pivots.get(3))/*
            Intervalo de d*/{
                d.add(value);
            }
            else if (pivots.get(3) <= value && value < pivots.get(4))
                /*Interalo de e*/{
                e.add(value);
            }
            else /*Intervalo de f*/{
                f.add(value);
            }

        }
    }
}
