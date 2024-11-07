package DeberCuatroLinkedListSantiagoArellano;
import java.util.*;

public class SingleLinkedListDriver {
    public static void main(String[] args) {
        //! Declaramos una serie de valores para la linked list de Marcas de Automoviles
        SingleLinkedList<String> cars = new SingleLinkedList<>();
        //! Impresiones generales
        System.out.printf("%120s\n","Test Driver Para Singled Linked List - Santiago Arellano - 00328370");
        System.out.printf("%123s\n", "Test Set #1 - Procesos de Insercion, Remocion, y Manipulacion de Elementos");

        System.out.println("\nTest Suite #1.1 | Comportamiento de add(), addAll(), addFirst(), addLast(), y addElementAtIndex()\n");
        //? Anadimos dos elementos a la lista e imprimimos
        cars.add("Ford");
        System.out.println("Luego de Anadir un Elemento ['Ford']: " + cars.toString() + "\n");
        cars.add("Chevrolet");
        System.out.println("Luego de Anadir un Elemento ['Cherolet']: " + cars.toString() + "\n");
        cars.addFirst("Cadillac");
        System.out.println("Luego de Anadir un Elemento ['Cadilac'] al inicio de la lista con addFirst(): " + cars.toString() + "\n");
        cars.addLast("Lincoln");
        System.out.println("Luego de Anadir un Elemento ['Lincoln'] al final de la lista con addLast(): " + cars.toString() + "\n");
        List<String> ls = List.of("Buick","Dodge","Jeep","Tesla");
        cars.addAll(ls);
        System.out.println("Luego de Anadir los elementos " + ls.toString() + " con addAll: " + cars.toString() + "\n");
        try
        {
            // Bloque de intento de ingreso de index size + 100
            cars.addElementAtIndex(cars.size() + 100, "Hino");
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("Por diseno, al intentar anadir un valor en una posicion incorrecta, se lanzo una excepcion de tipo " +
                    e.getClass().getCanonicalName() +"con el siguiente mensaje");
            System.out.println(e.getMessage());
            System.out.println("\n");
        }
        cars.addElementAtIndex(5, "Rivian");
        System.out.println("Luego de Anadir un Elemento ['Rivian'] en la posicion correcta [5] con addElementAtIndex: " + cars.toString() + "\n");

        System.out.println("\n Test Suite #1.2 | Comportamiento de remove(), removeFirst(), removeLast(), removeAll(), retainAll() y removeElementAtIndex()\n");
        if (cars.remove("Hino"))
        {
            System.out.println("Luego de remover el valor ['Hino'] de la linked list : " + cars.toString() + "\n");
        }
        else {
            System.out.println("Como el valor ['Hino'] no se encuentra en la linked list, la lista se mantiene igual: " + cars.toString() + "\n");
        }
        if (cars.remove("Jeep")){
            System.out.println("Luego de eliminar el valor ['Jeep'] usando remove(), que si estaba en la linked list, tenemos : " + cars.toString() + "\n");
        }
        int indexToDeleteNodeFrom = cars.size() - 1;
        cars.removeElementAtIndex(indexToDeleteNodeFrom);
        System.out.println("Luego de eliminar el valor en el indice [" + indexToDeleteNodeFrom + "] usando removeElementAtIndex(): " + cars.toString() + "\n");
        List<String> l2 = List.of("Hino","Toyota", "Buick");
        cars.removeAll(l2);
        System.out.println("Luego de eliminar los valores " + l2.toString() + " usando removeAll: " + cars.toString() + "\n");
        cars.retainAll(ls);
        System.out.println("Luego de aplicar retainAll() con los valores de " + ls.toString() + " : " + cars.toString() + "\n");
        List<String> l3 = List.of("Peugeot","Renault","Volvo","SAAB","BMW","Mercedez Benz","Aston Martin");
        cars.addAll(l3);
        System.out.println("Luego de anadir los valores " + l3.toString() + " : " + cars.toString());
        var result = cars.removeFirst();
        System.out.println("Luego de eliminar el valor ['" + result +"'] con removeFirst(): " + cars.toString() + "\n");
        result = cars.removeLast();
        System.out.println("Luego de eliminar el valor ['" + result + "'] con removeLast(): " + cars.toString() + "\n");

        System.out.printf("%123s\n", "Test Set #2 - Procesos de size() y clear()");
        System.out.println("\n Test Suite #2.1 | Comportamiento de size() y clear()\n");
        System.out.println("Para la cadena final de la iteracion anterior, el total de elementos es: " + cars.size());
        cars.clear();
        System.out.println("Luego de aplicar .clear() en la lista: " + cars.toString());

        System.out.printf("%123s\n", "Test Set #3 - Procesos de Contains() y ContainsAll()");
        System.out.println("\n Test Suite #3.1 | Comportamiento de contains() y containsALl()\n");
        cars.addAll(l2);
        cars.addAll(l3);
        if (cars.containsAll(ls))
        {
            System.out.println("Los elementos de la lista ls ["+ ls.toString() +" fueron encontrados en la lista");
        }
        else
        {
            System.out.println("Los elementos de la lista ls ["+ ls.toString() +" no fueron encontrados en la lista: " + cars.toString());
        }
        if (cars.contains(l2.getLast()))
        {
            System.out.println("El elemento [" + l2.getLast() + "] fue encontrado en la lista: " + cars.toString());
        }
        else
        {
            System.out.println("El elemento [" + l2.getLast() + "] no fue encontrado en la lista: " + cars.toString());
        }
        System.out.printf("%123s\n", "Test Set #4 - Procesos con Iterador");
        Iterator<String> iterator = cars.iterator();
        //! Impresion en pantalla por medio de iterador
        System.out.println("Imprimimos en pantalla, valor por valor mediante un iterador");
        while (iterator.hasNext())
        {
            System.out.println("Valor del iterador: " + iterator.next());
        }
        System.out.println("Limpiamos la lista mediante el iterador");
        iterator = cars.iterator();
        while (iterator.hasNext())
        {
            System.out.println("Valor a eliminar del iterador: " + iterator.next());
            iterator.remove();

        }
        System.out.println("Luego de eliminar los valores con el iterador, nuestra lista es: " + cars.toString());


    }
}
