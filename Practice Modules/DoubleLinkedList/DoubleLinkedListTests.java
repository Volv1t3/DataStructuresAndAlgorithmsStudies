package DoubleLinkedList;

import java.util.Iterator;
import java.util.List;

public class DoubleLinkedListTests {

    public static void main(String[] args) {
        DoubleLinkedList<String> list = new DoubleLinkedList<String>();
        list.add("A");
        list.add("B");
        System.out.println(list.toString());
        list.addAll(List.of("C", "D", "E"));
        System.out.println(list.toString());
        Iterator<String> iterator = list.reverseIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        Iterator<String> iterator2 = list.reverseIterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
            iterator2.remove();
        }
        System.out.println(list.toString());

        list.add("F");
        list.add("G");
        System.out.println(list.toString());
        list.add(1, "Hello World", false);
        System.out.println(list.toString());

        DoubleLinkedList<Integer> listTwo = new DoubleLinkedList<Integer>(new Node<Integer>(10), new Node<Integer>(20));
        System.out.println(listTwo.toString());
        listTwo.addLast(30);
        System.out.println(listTwo.toString());
        listTwo.addFirst(40);
        System.out.println(listTwo.toString());
        System.out.println("Does list contain '30': " + listTwo.contains(30));
        System.out.println("Does list contain '40': " + listTwo.contains(40));
        System.out.println("Does list contain '50': " + listTwo.contains(50));
        listTwo.removeFirst();
        System.out.println(listTwo.toString());
        listTwo.removeLast();
        System.out.println(listTwo.toString());
        System.out.println("Was '10' in the list and was it removed?: " +  listTwo.remove(10));
        listTwo.add(1, 50, false);
        System.out.println(listTwo.toString());
        System.out.println("Was '50' in the list and was it removed?: " +  listTwo.remove(50));
        System.out.println(listTwo.toString());

        //!size test
        System.out.println("Size of list: " + listTwo.size());
        var prev= listTwo.set(listTwo.size() -1, 100, true);
        System.out.println(listTwo.toString());
        System.out.println("Previous value: " + prev);
        System.out.println("Does list contain '100': " + listTwo.contains(100));
        System.out.println("Size of list: " + listTwo.size());
        System.out.println(listTwo.toString());
    }
}
