package DoubleLinkedList;

import java.util.Collection;
import java.util.Iterator;

public interface LinkedListInterface<E extends Comparable<E>> extends Collection<E> {

    //! Metodos con implementacion vacia directamente necesarios para la doubly linked list
    //? Get first y Get Last
    /**
     * Returns the value stored in the first node of the double-linked list based on <b>left to right traversal</b>
     */
    public abstract E getFirst();
    /**
     * Returns the value stored in the first node of the double-linked list based on <b>right to left traversal</b>
     */
    public abstract E getFirstReversed();
    /**
     * Returns the value stored in the last node of the double-linked list based on <b>left to right traversal</b>
     */
    public abstract E getLast();
    /**
     * Returns the value stored in the last node of the double-linked list based on <b>right to left traversal</b>
     */
    public abstract E getLastReversed();
    /**
     * Allows for the retrieval of a value based on an index for its location and a boolean value determining a direction
     * of iteration. This method assumes that the user enters a non zero based index (e.g., if the list contains four elements,
     * the user might be tempted to ask for index four. The program accepts this behavior and adapts to it)
     * @param index: Index of the node to be retrieved
     * @param leftToRightTraversal: Boolean value determining a direction of iteration
     * @return E: Value stored in the node at the specified index
     */
    public abstract E get(int index, boolean leftToRightTraversal);

    //? Add first y Add Last antes y despues de los nodos
    /**
     * Adds a new node to the forward link of the <b>head node</b>. Keeping the head intact
     * @param data:  Data to be stored in the new node
     */
    public abstract void addFirst(E data);
    /**
     * Adds a new node to the backward link of the <b>head node</b>. Replacing the previous head with this new node
     * @param data:  Data to be stored in the new node
     */
    public abstract void addFirstReplaceHead(E data);
    /**
     * Adds a new node to the forward link of the <b>tail node</b>. Replacing the previous tail with this new node.
     * @param data:  Data to be stored in the new node
     */
    public abstract void addLastReplaceTail(E data);
    /**
     * Adds a new node to the backward link of the <b>tail node</b>. Keeping the tail intact
     * @param data:  Data to be stored in the new node
     */
    public abstract void addLast(E data);

    //?Add methods based on index, and Collections adding methods
    /**
     * Adds a new node to the double-linked list at the specified index, based on the boolean parameter passed into the
     * method. If the boolean parameter is true, the new node is added to the right (<b>adding it to the forward link</b>)
     * of the node at the specified index,
     * otherwise, it is added to the left (<b>adding it to the backward link</b>)
     * of the node at the specified index. The head and tail nodes are also updated
     * accordingly.
     * @param index: Index at which the new node should be added
     * @param data: Data to be stored in the new node
     */
    public abstract void add(int index, E data, boolean leftToRightTraversal);

    /**
     * Adds the present data value to the list by utilizing an appending method to the end of the list, i.e., replacing
     * the tail node. This is the equivalent of calling add(E e) from a single linked list.
     * @param e element whose presence in this collection is to be ensured
     * @return true if the element was added successfully, false otherwise
     */
    @Override
    public abstract boolean add(E e);

    /**
     * Adds all of the elements in the specified collection to this list.
     * @param c collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     */
    @Override
    boolean addAll(Collection<? extends E> c);

    //? Remove First and Remove Last, antes y despues de los nodos
    /**
     * Removes the First node in the double-linked list, i.e., <b>the head</b>. Equivalent to removing the backward link
     * of the second node in the linked list. This effectively acts like the simple <b>removeFirst()</b> from a single
     * linked list
     */
    public abstract E removeFirst();
    /**
     * Removes the first node <b>in the forward link from the head</b>, i.e., it removes the node stored right after
     * the head node going from <b>left to right</b>. This is the equivalent of calling remove(1) where 1 represents the
     * index-based removal operations of a single linked list.
     */
    public abstract E removeFirstForwardLink();
    /**
     * Removes the Last node in the double-linked list, i.e., <b>the tail</b>. Equivalent to removing the forward link
     * of the second to last node in the linked list. This effectively acts like the simple <b>removeLast()</b> from a
     * single linked list
     */
    public abstract E removeLast();
    /**
     * Removes the last node <b>in the backward link from the tail</b>, i.e., it removes the node stored right before
     * the tail node going from <b>right to left</b>. This is the equivalent of calling remove(size()-1) where size()
     * represents the total number of nodes in a single linked list.
     */
    public abstract E removeLastBackwardLink();

    //? Remove based on index, item, remove(), removeAll() and retainAll() methods
    /**
     * Removes the first occurrence of the specified element from this list if it is present. If the list does not
     * contain the element, it is unchanged.
     * @param o: Element to be removed from the list
     * @param leftToRightScanning: If true, the scanning direction is from left to right, otherwise, it is from right to left
     * @return E: The element that was removed from the list, or null if the element was not present in the list
     */
    public abstract E remove(E o, boolean leftToRightScanning);
    /**
     * Removes the element at the specified position in this list, based on the searching criteria passed to the boolean
     * parameter
     * @param index: Index of the element to be removed
     * @param leftToRightScanning:  If true, the scanning direction is from left to right, otherwise, it is from right to left
     * @return E: The element that was removed from the list
     */
    public abstract E remove(int index, boolean leftToRightScanning);

    /**
     * Default implementation of the Collection< E > method for removing an object from the doubly linked list. Internally
     * it handles the case as if it were a linear search from left to right with the remove(E o, boolean leftToRightScanning)
     * method
     * @param o element to be removed from this collection
     * @return true if this collection contained the specified element
     * @throws ClassCastException if the type of the specified element is incompatible with this collection
     * @throws NullPointerException if the specified element is null and this collection does not permit null elements
     */
    @Override
    default boolean remove(Object o) throws ClassCastException, NullPointerException{
        try
        {
            return remove((E) o, true) != null;
        }
        catch(Exception e)
        {
            throw new ClassCastException("Object is not of type E or convertible to E");
        }
    }
    /**
     * Removes from this collection all of its elements that are contained in the specified collection.
     * @param c collection containing elements to be removed from this collection
     * @return true if this collection changed as a result of the call
     * @throws ClassCastException if the types of one or more elements in the specified collection are incompatible with this collection
     * @throws NullPointerException if the specified collection contains one or more null elements and this collection does not permit null elements
     */
    @Override
    default boolean removeAll(Collection<?> c) throws ClassCastException, NullPointerException{
        boolean changed = false;
        for(Object o : c)
        {
            if(remove(o))
            {
                changed = true;
            }
        }
        return changed;
    }

    /**
     * Retains only the elements in this collection that are contained in the specified collection.
     * @param c collection containing elements to be retained in this collection
     * @return true if this collection changed as a result of the call
     * @throws ClassCastException if the types of one or more elements in the specified collection are incompatible with this collection
     * @throws NullPointerException if the specified collection contains one or more null elements and this collection does not permit null elements
     */
    @Override
    default boolean retainAll(Collection<?> c) throws ClassCastException, NullPointerException{
        return true;
    }

    //? Helper methods, clear(), size(), isEmpty(), contains(), toArray(), and iterator()
    /**
     * Removes all the elements of this collection, the method has been left with no implementation given that it has
     * two avenues for working, but this depends on implementation details.
     */
    @Override
    public abstract void clear();
    /**
     * Returns the number of elements in this collection.
     * @return int: The number of elements in this collection
     */
    @Override
    public abstract int size();
    /**
     * Returns true if this collection contains no elements.
     * @return boolean: true if this collection contains no elements
     */
    @Override
    public abstract boolean isEmpty();
    /**
     * Returns true if this collection contains the specified element.
     * @param o element whose presence in this collection is to be tested
     * @return boolean: true if this collection contains the specified element
     * @throws ClassCastException if the type of the specified element is incompatible with this collection
     * @throws NullPointerException if the specified element is null and this collection does not permit null elements
     */
    @Override
    public abstract boolean contains(Object o) throws ClassCastException, NullPointerException;


    /**
     * Returns true if this collection contains all of the elements in the specified collection.
     * @param c collection to be checked for containment in this collection
     * @return boolean: true if this collection contains all of the elements in the specified collection
     * @throws ClassCastException if the types of one or more elements in the specified collection are incompatible with this collection
     * @throws NullPointerException if the specified collection contains one or more null elements and this collection does not permit null elements
     */
    @Override
    boolean containsAll(Collection<?> c);

    /**
     * Returns an array containing all the elements in this collection.
     * @return Object[]: An array containing all the elements in this collection
     */
    @Override
    public abstract Object[] toArray();
    /**
     * Returns an iterator over the elements in this collection.
     * @return Iterator<E>: An iterator over the elements in this collection
     */
    @Override
    public abstract Iterator<E> iterator();
    /**
     * Returns an array containing all the elements in this collection; the runtime type of the returned array is that
     * of the specified array.
     * @param a the array into which the elements of this collection are to be stored, if it is big enough; otherwise, a new array of the same runtime type is allocated for this purpose.
     * @return E[]: An array containing all the elements in this collection
     * @throws ArrayStoreException if the runtime type of the specified array is not a supertype of the runtime type of every element in this collection
     * @throws NullPointerException if the specified array is null
     */
    @Override
    public abstract <T> T[] toArray(T[] a) throws ArrayStoreException, NullPointerException;

    //? Method for setting the value of an element given an index
    /**
     * Sets the value of an element given an index, this method has been left with no implementation given that it has
     * two avenues for working, but this depends on implementation details.
     * @param index: Index of the element to be set
     * @param element: Element to be set
     * @return E: The previous value of the element at the specified index
     */
    public abstract E set(int index, E element, boolean leftToRightTraversal);
}
