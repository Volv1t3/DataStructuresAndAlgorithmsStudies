package LinkedLists;

import java.util.*;
import java.util.function.UnaryOperator;

public interface LinkedList<E extends Comparable<E>> extends List<E> {

    //! Getter for Elements
    public abstract E getFirst();
    public abstract E getFirstReversed();
    public abstract E getLast();
    public abstract E getLastReversed();
    public abstract E get(int index);
    public abstract E get(int index, boolean leftToRightTraversal);
    public abstract E get(E data);
    public abstract E get(E data, boolean leftToRightTraversal);

    //! Adding Elements
    public abstract void addFirst(E data);
    public abstract void addFirstReplaceHead(E data);
    public abstract void addLast(E data);
    public abstract void addLastReplaceTail(E data);

    //!  Removing Elements
    public abstract E removeFirst();
    public abstract E removeFirstForwardLink();
    public abstract E removeLast();
    public abstract E removeLastBackwardLink();
    public abstract E remove(E data);
    public abstract E remove(E data, boolean leftToRightTraversal);
    public abstract E remove(int index);
    public abstract E remove(int index, boolean leftToRightTraversal);
    public abstract boolean remove(Object object);
    public abstract boolean remove(Object object, boolean leftToRightTraversal);

    //! List methods
    @Override
    int size();
    @Override
    boolean isEmpty();
    @Override
    boolean contains(Object o);
    @Override
    Iterator<E> iterator();
    @Override
    Object[] toArray();
    @Override
    <T> T[] toArray(T[] a);
    @Override
    boolean add(E e);
    @Override
    boolean containsAll(Collection<?> c);
    @Override
    boolean addAll(Collection<? extends E> c);
    @Override
    boolean addAll(int index, Collection<? extends E> c);
    @Override
    boolean removeAll(Collection<?> c);
    @Override
    boolean retainAll(Collection<?> c);
    @Override
    default void replaceAll(UnaryOperator<E> operator) {
        List.super.replaceAll(operator);
    }
    @Override
    default void sort(Comparator<? super E> c) {
        List.super.sort(c);
    }
    @Override
    void clear();
    @Override
    boolean equals(Object o);
    @Override
    int hashCode();
    @Override
    E set(int index, E element);

    @Override
    void add(int index, E element);

    @Override
    int indexOf(Object o);

    @Override
    int lastIndexOf(Object o);

    @Override
    ListIterator<E> listIterator();

    @Override
    ListIterator<E> listIterator(int index);

    @Override
    List<E> subList(int fromIndex, int toIndex);

    @Override
    default Spliterator<E> spliterator() {
        return List.super.spliterator();
    }

    @Override
    default List<E> reversed() {
        return List.super.reversed();
    }
}
