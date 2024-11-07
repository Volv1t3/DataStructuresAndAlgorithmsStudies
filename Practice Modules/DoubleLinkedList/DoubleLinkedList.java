package DoubleLinkedList;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.prefs.NodeChangeEvent;

public class DoubleLinkedList<E extends Comparable<E>> implements LinkedListInterface<E>{

    //! Private data points for operation
    Node<E> startPointHeadNode;
    Node<E> endPointTailNode;
    int size;


    //! Constuctores determinados para crear instancias de la double-linked list
    //? Simple no argument constructor for initialization
    public DoubleLinkedList() {
        this.startPointHeadNode = null;
        this.endPointTailNode = null;
        this.size = 0;
    }
    //? Constructor with variable-length parameter data for collection input
    @SafeVarargs
    public DoubleLinkedList(E... data)
    {
        this.addAll(Arrays.asList(data));
    }
    //? Constructor with Collection data type input
    public DoubleLinkedList(Collection<E> dataCollection)
    {
        this.addAll(dataCollection);
    }
    //? Constructor with head node input

    public DoubleLinkedList(Node<E> headNode)
    {
        this.startPointHeadNode = headNode;
        this.endPointTailNode = headNode;
        this.size = 1;
    }
    //? Constructor with head and tail node
    public DoubleLinkedList(Node<E> headNode, Node<E> tailNode)
    {
        this.startPointHeadNode = headNode;
        this.endPointTailNode = tailNode;
        /*Make the interconections between head and tail*/
        this.startPointHeadNode.setForwardLink(this.endPointTailNode);
        this.endPointTailNode.setBackwardLink(this.startPointHeadNode);
        this.size = 2;
    }

    //! Bulk implementations of getter methods for first and last elements stored in our nodes
    /**
     * Returns the value stored in the first node of the double-linked list based on <b>left to right traversal</b>
     */
    @Override
    public E getFirst() {
        return this.startPointHeadNode.getData();
    }
    /**
     * Returns the value stored in the last node of the double-linked list based on <b>left to right traversal</b>
     */
    @Override
    public E getLast() {
        return this.endPointTailNode.getData();
    }
    /**
     * Returns the value stored in the first node of the double-linked list based on <b>right to left traversal</b>
     */
    @Override
    public E getFirstReversed() {
        return this.getLast();
    }
    /**
     * Returns the value stored in the last node of the double-linked list based on <b>right to left traversal</b>
     */
    @Override
    public E getLastReversed() {
        return this.getFirst();
    }
    /**
     * Allows for the retrieval of a value based on an index for its location and a boolean value determining a direction
     *       of iteration. This method assumes that the user enters a non zero based index (e.g., if the list contains four elements,
     *      the user might be tempted to ask for index four. The program accepts this behavior and adapts to it)
     *
     * @param index                : Index of the node to be retrieved
     * @param leftToRightTraversal : Boolean value determining a direction of iteration
     * @return E: Value stored in the node at the specified index
     */
    @Override
    public E get(int index, boolean leftToRightTraversal) {
        //! Paso Base: Revisamos index para ver si esta dentro del rango aceptado
        this.checkIndex(index);
        
        //! Paso Intermedio: Iteramos sobre el arreglo basado en los valores de index 
        if (index == 0) {
            return leftToRightTraversal ? this.getFirst() : this.getFirstReversed();
        }
        else if (index == size) {
            return leftToRightTraversal ? this.getLast() : this.getLastReversed();
        }
        else
        {
            if (leftToRightTraversal)
            {
                return this.traversalLeftToRight.apply(this.startPointHeadNode, index);
            }
            else {return this.traversalRightToLeft.apply(this.endPointTailNode, index);}
        }
    }

    //! Bulk Implementations of element insertion methods
    /**
     * Adds a new node to the forward link of the <b>head node</b>. Keeping the head intact
     *
     * @param data :  Data to be stored in the new node
     */
    @Override
    public void addFirst(E data) {
        Node<E> newNodeAfterHead = new Node<E>(data);
        if (this.startPointHeadNode == null) {
            this.endPointTailNode = this.startPointHeadNode = newNodeAfterHead;
        }
        else
        {
            Node<E> followerNodeAfterHead = this.startPointHeadNode.getForwardLink();
            if (followerNodeAfterHead == null) /*This means that the only node we have is our head*/
            {
                /*Connect the new node to the head through backward-link*/
                newNodeAfterHead.setBackwardLink(this.startPointHeadNode);
                /*Connect the head with the new node wit the forward-link*/
            }
            else /*This case only happens if the list is not empty*/
            {
                /*Connect the new node with the node after the head*/
                newNodeAfterHead.setForwardLink(followerNodeAfterHead);
                /*Connect the new node with the head with backward-link*/
                newNodeAfterHead.setBackwardLink(this.startPointHeadNode);

                /*Connect the follower node to the new node with backward link*/
                followerNodeAfterHead.setBackwardLink(newNodeAfterHead);

                /*Connect the head to the new node through forward link*/
            }
            this.startPointHeadNode.setForwardLink(newNodeAfterHead);

        }
        this.size++;
    }


    /**
     * Adds a new node to the backward link of the <b>tail node</b>, keeping the tail intact.
     * If the list is empty, it will initialize the list with this node as both head and tail.
     *
     * @param data Data to be stored in the new node.
     */
    @Override
    public void addLast(E data) {
        Node<E> newNodeBeforeTail = new Node<E>(data);

        // Base case: The list is empty, so initialize head and tail
        if (this.endPointTailNode == null) {
            this.startPointHeadNode = this.endPointTailNode = newNodeBeforeTail;
        } else {
            // Inductive Case 1: If the node before the tail is empty, we know that it is the head
            // i.e., the only node in the list. We link the new node as the second node.
            Node<E> previousBeforeTailNode = this.endPointTailNode.getBackwardLink();
            if (previousBeforeTailNode == null) {
                this.startPointHeadNode.setBackwardLink(newNodeBeforeTail);
                newNodeBeforeTail.setForwardLink(this.startPointHeadNode);
            } else {
                // Inductive Case 2: There are more than one node in the list
                // Make the forward and backward connections for the new node
                newNodeBeforeTail.setForwardLink(this.endPointTailNode);
                newNodeBeforeTail.setBackwardLink(previousBeforeTailNode);

                // Make the backward connection from the previous node to the new node
                this.endPointTailNode.setBackwardLink(newNodeBeforeTail);

                // Make the previous node's forward link connected to the new node
                previousBeforeTailNode.setForwardLink(newNodeBeforeTail);
            }
        }
        size++;
    }

    /**
     * Adds a new node to the backward link of the <b>head node</b>. Replacing the previous head with this new node
     *
     * @param data :  Data to be stored in the new node
     */
    @Override
    public void addFirstReplaceHead(E data) {
        Node<E> newHeadNodeFromData = new Node<E>(data);
        /* Base case: The list is empty, so initialize head and tail */
        if (this.startPointHeadNode == null) 
        {
            /* Initialize both head and tail to the new node */
            this.startPointHeadNode = this.endPointTailNode = newHeadNodeFromData;
        }
        else
        {
            /*Connect the new head with the previous head through a forward-link*/
            newHeadNodeFromData.setForwardLink(this.startPointHeadNode);
            /*Connect the previous head to the new head with a backward-link*/
            this.startPointHeadNode.setBackwardLink(newHeadNodeFromData);
            /*make the new head the head in the reference*/
            this.startPointHeadNode = newHeadNodeFromData;
            /*Increase internal size holder*/
        }
        size++;
    }

    /**
     * Adds a new node to the forward link of the <b>tail node</b>. Replacing the previous tail with this new node.
     *
     * @param data :  Data to be stored in the new node
     */
    @Override
    public void addLastReplaceTail(E data) {
        Node<E> newTailNodeFromData = new Node<E>(data);
        /*Base Case in which we have no data points*/
        if (this.endPointTailNode == null)
        {
            this.startPointHeadNode = this.endPointTailNode = newTailNodeFromData;
        }
        else
        {
            /*Connect the previous head to the new head through a forward-link*/
            this.endPointTailNode.setForwardLink(newTailNodeFromData);
            /*Connect the new head with a backward-link to the previous head*/
            newTailNodeFromData.setBackwardLink(this.endPointTailNode);
            /*Make the new node the tail*/
            this.endPointTailNode = newTailNodeFromData;
            
        }
        size++;
    }

    /**
     * Adds a new node to the double-linked list at the specified index, based on the boolean parameter passed into the
     * method. If the boolean parameter is true, the new node is added to the right (<b>adding it to the forward link</b>)
     * of the node at the specified index,
     * otherwise, it is added to the left (<b>adding it to the backward link</b>)
     * of the node at the specified index. The head and tail nodes are also updated
     * accordingly.
     *
     * @param index                : Index at which the new node should be added
     * @param data                 : Data to be stored in the new node
     * @param leftToRightTraversal
     */
    @Override
    public void add(int index, E data, boolean leftToRightTraversal) {

        /*Paso Base: revisamos el indice enviado*/
        this.checkIndex(index);
        Node<E> newNodeToInsertAtIndex = new Node<E>(data);
        if (index == 0) {
            if (leftToRightTraversal) {
                this.addFirstReplaceHead(data);
            } else {
                this.addLastReplaceTail(data);
            }
        }
        else if (index == size-1) {
            if (leftToRightTraversal) {
                this.addLastReplaceTail(data);
            } else {
                this.addFirstReplaceHead(data);
            }
        }
        else
        {
            if (leftToRightTraversal) {
                this.insertionLeftToRight.apply(
                    new Node[]{this.startPointHeadNode, newNodeToInsertAtIndex}, index );
            }
            else {
                this.insertionRightToLeft.apply(
                    new Node[]{this.endPointTailNode, newNodeToInsertAtIndex}, index );}
            size++;
        }
    }
    

    /**
     * Adds the present data value to the list by using an appending method to the end of the list, i.e., replacing
     * the tail node. This is the equivalent of calling add(E e) from a single linked list.
     *
     * @param e element whose presence in this collection is to be ensured
     * @return true if the element was added successfully, false otherwise
     */
    @Override
    public boolean add(E e) {
        int sizeBefore = this.size;
        this.addLastReplaceTail(e);
        return this.size > sizeBefore;
    }

    /**
     * Adds all of the elements in the specified collection to this list.
     *
     * @param c collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        int sizeBefore = this.size;
        for (E e : c) {
            this.addLastReplaceTail(e);
        }
        return this.size > sizeBefore;
    }

    /**
     * Removes the First node in the double-linked list, i.e., <b>the head</b>. Equivalent to removing the backward link
     * of the second node in the linked list. This effectively acts like the simple <b>removeFirst()</b> from a single
     * linked list
     */
    @Override
    public E removeFirst() {
        /*Base Case: if the head is null, then the tail is null, and if both are null we return null */
        if (this.startPointHeadNode == null) {return null;}
        /*Inductive Case: If the head is not empty, then what we can do is grab the head*/
        else
        {
            Node<E> followerNodeAfterHead = this.startPointHeadNode.getForwardLink();
            E dataStoredInHead = this.startPointHeadNode.getData();
            if (followerNodeAfterHead == null) /*This means that the only node we have is our head*/
            {
                this.startPointHeadNode = this.endPointTailNode = null;
            }
            else /*This case only happens if the list is not empty*/
            {
                this.startPointHeadNode.setForwardLink(null);
                followerNodeAfterHead.setBackwardLink(null);
                this.startPointHeadNode = followerNodeAfterHead;
            }
            this.size--;
            return dataStoredInHead;
        }
    }

    /**
     * Removes the Last node in the double-linked list, i.e., <b>the tail</b>. Equivalent to removing the forward link
     * of the second to last node in the linked list. This effectively acts like the simple <b>removeLast()</b> from a
     * single linked list
     */
    @Override
    public E removeLast() {
        /*Base Case: If the head is null, then the tail is null and if both are null we return null!*/
        if (this.endPointTailNode == null) {return null;}
        /*Inductive Case: If the tail is not null, then there might as well be a value stored inside*/
        else
        {
            Node<E> previousNodeBeforeHead = this.endPointTailNode.getBackwardLink();
            E dataStoredInTail = this.endPointTailNode.getData();
            if (previousNodeBeforeHead == null) /*This means that the only node we have is our tail*/
            {
                this.startPointHeadNode = this.endPointTailNode = null;
            }
            else /*This only happens if we have more than one node in our list*/
            {
                this.endPointTailNode.setBackwardLink(null);
                previousNodeBeforeHead.setForwardLink(null);
                this.endPointTailNode = previousNodeBeforeHead;
            }
            size--;
            return dataStoredInTail;
        }
    }

    /**
     * Removes the first node <b>in the forward link from the head</b>, i.e., it removes the node stored right after
     * the head node going from <b>left to right</b>. This is the equivalent of calling remove(1) where 1 represents the
     * index-based removal operations of a single linked list.
     */
    @Override
    public E removeFirstForwardLink() {
        var result = this.get(1, true);
        this.remove(1, true);
        return result;
    }

    /**
     * Removes the last node <b>in the backward link from the tail</b>, i.e., it removes the node stored right before
     * the tail node going from <b>right to left</b>. This is the equivalent of calling remove(size()-1) where size()
     * represents the total number of nodes in a single linked list.
     */
    @Override
    public E removeLastBackwardLink() {
        var result = this.get(size()- 2, false);
        this.remove(size()-2, false);
        return result;
    }

    /**
     * Removes the element at the specified position in this list, based on the searching criteria passed to the boolean
     * parameter
     *
     * @param index               : Index of the element to be removed
     * @param leftToRightScanning :  If true, the scanning direction is from left to right, otherwise, it is from right to left
     * @return E: The element that was removed from the list
     */
    @Override
    public E remove(int index, boolean leftToRightScanning) {
        this.checkIndex(index);
        /*Retrieve the data point stored*/
        var result = this.get(index, leftToRightScanning);

        /*Inductive step: we check the index for edge cases*/
        if (index == 0) {
            return this.removeFirst();
        }
        else if (index == size - 1) {
            return this.removeLast();
        }
        else
        {
            if (leftToRightScanning) {
                result = this.removalLeftToRight.apply(this.startPointHeadNode, index);
            }
            else {
                result = this.removalRightToLeft.apply(this.endPointTailNode, index);
            }
            size--;
        }
        return result;
    }

    /**
     * Removes the first occurrence of the specified element from this list if it is present. If the list does not
     * contain the element, it is unchanged.
     *
     * @param o                   : Element to be removed from the list
     * @param leftToRightScanning : If true, the scanning direction is from left to right, otherwise, it is from right to left
     * @return E: The element that was removed from the list, or null if the element was not present in the list
     */
    @Override
    public E remove(E o, boolean leftToRightScanning) {
        Node<E> nodeAtAnalysis = this.startPointHeadNode;
        var result = nodeAtAnalysis.getData();
        if (leftToRightScanning) /*Left to Right Scanning from the headStart*/
        {
            for(int i = 0; i < size; i++)
            {
                if (nodeAtAnalysis.getData().equals(o))
                {
                    result = nodeAtAnalysis.getData();
                    return this.remove(i, true);
                }
                nodeAtAnalysis = nodeAtAnalysis.getForwardLink();
            }
        }
        else /*Assume right to left scanning*/
        {
            nodeAtAnalysis = this.endPointTailNode;
            for(int i = size - 1; i >= 0; i--)
            {
                if (nodeAtAnalysis.getData().equals(o))
                {
                    result = nodeAtAnalysis.getData();
                    return this.remove(i, false);
                }
                nodeAtAnalysis = nodeAtAnalysis.getBackwardLink();
            }
        }
        return result;
    }

    /**
     * Default implementation of the Collection< E > method for removing an object from the doubly linked list. Internally
     * it handles the case as if it were a linear search from left to right with the remove(E o, boolean leftToRightScanning)
     * method
     *
     * @param o element to be removed from this collection
     * @return true if this collection contained the specified element
     * @throws ClassCastException   if the type of the specified element is incompatible with this collection
     * @throws NullPointerException if the specified element is null and this collection does not permit null elements
     */
    @Override
    public boolean remove(Object o) throws ClassCastException, NullPointerException {
        int sizeBefore = this.size;
        this.remove((E)o, true);
        System.out.println("Test size before deletion = " + sizeBefore + " and after = " + this.size);
        return this.size < sizeBefore;
    }

    /**
     * Removes from this collection all of its elements that are contained in the specified collection.
     *
     * @param c collection containing elements to be removed from this collection
     * @return true if this collection changed as a result of the call
     * @throws ClassCastException   if the types of one or more elements in the specified collection are incompatible with this collection
     * @throws NullPointerException if the specified collection contains one or more null elements and this collection does not permit null elements
     */
    @Override
    public boolean removeAll(Collection<?> c) throws ClassCastException, NullPointerException {
        int sizeBefore = this.size;
        for(Object o : c)
        {
            this.remove(o);
        }
        return this.size < sizeBefore;
    }

    /**
     * Retains only the elements in this collection that are contained in the specified collection.
     *
     * @param c collection containing elements to be retained in this collection
     * @return true if this collection changed as a result of the call
     * @throws ClassCastException   if the types of one or more elements in the specified collection are incompatible with this collection
     * @throws NullPointerException if the specified collection contains one or more null elements and this collection does not permit null elements
     */
    @Override
    public boolean retainAll(Collection<?> c) throws ClassCastException, NullPointerException {
        int sizeBefore = this.size;
        for(int i = 0; i < size; i++)
        {
            if (!c.contains(this.get(i, true)))
            {
                this.remove(i);
            }
        }
        return this.size < sizeBefore;
    }


    //! Bulk Additional Methods
    /**
     * Removes all the elements of this collection, the method has been left with no implementation given that it has
     * two avenues for working, but this depends on implementation details.
     */
    @Override
    public void clear() {
        this.startPointHeadNode = this.endPointTailNode = null;
        this.size = 0;
    }

    /**
     * Returns the number of elements in this collection.
     *
     * @return int: The number of elements in this collection
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns true if this collection contains no elements.
     *
     * @return boolean: true if this collection contains no elements
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns true if this collection contains the specified element.
     *
     * @param o element whose presence in this collection is to be tested
     * @return boolean: true if this collection contains the specified element
     * @throws ClassCastException   if the type of the specified element is incompatible with this collection
     * @throws NullPointerException if the specified element is null and this collection does not permit null elements
     */
    @Override
    public boolean contains(Object o) throws ClassCastException, NullPointerException {
        var result = this.evaluationLeftToRight.apply(this.startPointHeadNode, (E) o);
        return result;
    }

    /**
     * Returns true if this collection contains all of the elements in the specified collection.
     *
     * @param c collection to be checked for containment in this collection
     * @return boolean: true if this collection contains all of the elements in the specified collection
     * @throws ClassCastException   if the types of one or more elements in the specified collection are incompatible with this collection
     * @throws NullPointerException if the specified collection contains one or more null elements and this collection does not permit null elements
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object o : c)
        {
            if (!this.contains(o))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns an array containing all the elements in this collection.
     *
     * @return Object[]: An array containing all the elements in this collection
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[this.size];
        for(int i = 0; i < this.size; i++)
        {
            array[i] = this.get(i, true);
        }
        return array;
    }

    /**
     * Returns an iterator over the elements in this collection.
     *
     * @return Iterator<E>: An iterator over the elements in this collection
     */
    @Override
    public Iterator<E> iterator() {
        return new ForwardIterator();
    }

    public Iterator<E> reverseIterator() {
        return new BackwardIterator();
    }

    /**
     * Returns an array containing all the elements in this collection; the runtime type of the returned array is that
     * of the specified array.
     *
     * @param a the array into which the elements of this collection are to be stored, if it is big enough; otherwise, a new array of the same runtime type is allocated for this purpose.
     * @return E[]: An array containing all the elements in this collection
     * @throws ArrayStoreException  if the runtime type of the specified array is not a supertype of the runtime type of every element in this collection
     * @throws NullPointerException if the specified array is null
     */
    @Override
    public <T> T[] toArray(T[] a) throws ArrayStoreException, NullPointerException {
        return null;
    }


    //! Helper methods for douoble-linked list

    /**
     * Checks if the provided index is within the bounds of the double-linked list
     * @param index:  Index to be checked
     * @throws IndexOutOfBoundsException: If the index is out of bounds
     */
    private void checkIndex(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Error Code 0x0001 - [Raised] Index" + index
            + " out of bounds for range [0, " + size + "].");
        }
    }
    /**
     * BiFunction implementation for left to right traversal and data retrieval
     */
    BiFunction<Node<E>, Integer, E> traversalLeftToRight = new BiFunction<Node<E>, Integer, E>() {
        @Override
        public E apply(Node<E> eNode, Integer index) {
            Node<E> currentNode = eNode;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getForwardLink();
            }
            return currentNode.getData();
        }
    };
    /**
     * BiFunction implementation for right to left traversal and data retrieval
     */
    BiFunction<Node<E>, Integer, E> traversalRightToLeft = new BiFunction<Node<E>, Integer, E>() {
        @Override
        public E apply(Node<E> eNode, Integer index) {
            Node<E> currentNode = eNode;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getBackwardLink();
            }
            return currentNode.getData();
        }
    };
    /**
     * BiFunction Implementation for left to right traversal and node insertion
     */
    BiFunction<Node<E>[], Integer, Boolean > insertionLeftToRight = new BiFunction<Node<E>[], Integer, Boolean >() {
        @Override
        public Boolean apply(Node<E>[] eNodes, Integer index) {
            Node<E> headNodeForCounting = eNodes[0];
            Node<E> newNodeToInsert = eNodes[1];
            for(int i= 0; i < index; i++)
            {
                headNodeForCounting = headNodeForCounting.getForwardLink();
            }

            Node<E> followerNodeAfterNodeAtAnalysis = headNodeForCounting.getForwardLink();
            /*At the current node, we break and connect the new one*/
            /*Make the connection for a forward-link between analysis node and new one*/
            headNodeForCounting.setForwardLink(newNodeToInsert);
            /*Make the connection for a backward-link between analysis node and new one*/
            newNodeToInsert.setBackwardLink(headNodeForCounting);

            if (followerNodeAfterNodeAtAnalysis != null)
            {
                /*Make the connection for a forward-link between the new node and the next one from the headFor Counting*/
                newNodeToInsert.setForwardLink(followerNodeAfterNodeAtAnalysis);
                /*Make the connection for a backward-link between the new node and the next one from the headFor Counting*/
                followerNodeAfterNodeAtAnalysis.setBackwardLink(newNodeToInsert);

            }

            return true;
        }
    };
    /**
     * BiFunction Implementation for right to left traversal and node insertion
     */
    BiFunction<Node<E>[],Integer, Boolean> insertionRightToLeft = new BiFunction<Node<E>[], Integer, Boolean>() {
        @Override
        public Boolean apply(Node<E>[] eNodes, Integer index) {
            Node<E> tailNodeForCounting = eNodes[0];
            Node<E> newNodeToInsert = eNodes[1];

            for(int i= 0; i < index; i++)
            {
                tailNodeForCounting = tailNodeForCounting.getBackwardLink();
            }

            Node<E> followerNodeAfterNodeAtAnalysis = tailNodeForCounting.getBackwardLink();
            /*At the current node, we break and connect the new one*/
            /*Make the connection for a backward-link between analysis node and new one*/
            tailNodeForCounting.setBackwardLink(newNodeToInsert);
            /*Make the connection for a forward-link between analysis node and new one*/
            newNodeToInsert.setForwardLink(tailNodeForCounting);

            if (followerNodeAfterNodeAtAnalysis != null)
            {
                /*Make the connection for a backward-link between the new node and the next one from the headFor Counting*/
                newNodeToInsert.setBackwardLink(followerNodeAfterNodeAtAnalysis);
                /*Make the connection for a forward-link between the new node and the next one from the headFor Counting*/
                followerNodeAfterNodeAtAnalysis.setForwardLink(newNodeToInsert);
            }

            return true;
        }
    };
    /**
     * BiFunction Implementation for left to right traversal and node removal
     */
    BiFunction<Node<E>,Integer, E> removalLeftToRight = new BiFunction<Node<E>, Integer, E>() {
        @Override
        public E apply(Node<E> eNode, Integer index) {
           Node<E> nodeAtAnalysis = eNode;
           for(int i= 0 ; i < index; i++)
           {
               nodeAtAnalysis = nodeAtAnalysis.getForwardLink();
           }
           E dataStoredInNodeAtAnalysis = nodeAtAnalysis.getData();
           Node<E> followerNodeAfterNodeAtAnalysis = nodeAtAnalysis.getForwardLink();
           Node<E> previousNodeBeforeNodeAtAnalysis = nodeAtAnalysis.getBackwardLink();
           if (followerNodeAfterNodeAtAnalysis != null && previousNodeBeforeNodeAtAnalysis != null)
               /*The list is not empty and the element we are looking at is not the first*/
           {
               /*Make the connection for a forward-link between the previous node and the next one*/
               previousNodeBeforeNodeAtAnalysis.setForwardLink(followerNodeAfterNodeAtAnalysis);
               /*Make the connection for a backward-link between the previous node and the next one*/
               followerNodeAfterNodeAtAnalysis.setBackwardLink(previousNodeBeforeNodeAtAnalysis);
           }
           else if (followerNodeAfterNodeAtAnalysis != null && previousNodeBeforeNodeAtAnalysis == null)
               /*The element we are looking at is the first*/
           {
               /*Make the connection for a forward-link between the previous node and the next one*/
               followerNodeAfterNodeAtAnalysis.setBackwardLink(null);
               startPointHeadNode = followerNodeAfterNodeAtAnalysis;
           }
           else if (followerNodeAfterNodeAtAnalysis == null && previousNodeBeforeNodeAtAnalysis != null)
               /*The element we are looking at is the last*/
           {
               /*Make the connection for a backward-link between the previous node and the next one*/
               previousNodeBeforeNodeAtAnalysis.setForwardLink(null);
               endPointTailNode = previousNodeBeforeNodeAtAnalysis;
           }
           size--;
            return dataStoredInNodeAtAnalysis;
        }
    };
    /**
     * BiFunction Implementation for right to left traversal and node removal
     */
    BiFunction<Node<E>, Integer, E> removalRightToLeft = new BiFunction<Node<E>, Integer, E>() {
        @Override
        public E apply(Node<E> eNode, Integer index) {
            Node<E> nodeAtAnalysis = eNode;
            for(int i= 0 ; i < index; i++)
            {
                nodeAtAnalysis = nodeAtAnalysis.getBackwardLink();
            }
            E dataStoredInNodeAtAnalysis = nodeAtAnalysis.getData();
            Node<E> followerNodeAfterNodeAtAnalysis = nodeAtAnalysis.getBackwardLink();
            Node<E> previousNodeBeforeNodeAtAnalysis = nodeAtAnalysis.getForwardLink();

            /*Base Case: If both of them are not null then we are in the middle of two nodes*/
            if (followerNodeAfterNodeAtAnalysis != null && previousNodeBeforeNodeAtAnalysis != null)
            {
                /*Make a connection between previous node with a backward-link to the follower node*/
                previousNodeBeforeNodeAtAnalysis.setBackwardLink(followerNodeAfterNodeAtAnalysis);
                /*Make a connection between follower node with a forward-link to the previous node*/
                followerNodeAfterNodeAtAnalysis.setForwardLink(previousNodeBeforeNodeAtAnalysis);
            }
            else if (followerNodeAfterNodeAtAnalysis != null && previousNodeBeforeNodeAtAnalysis == null)
                /*The element we are looking at is the last*/
            {
                /*Make the connection for a forward-link between the previous node and the next one*/
                followerNodeAfterNodeAtAnalysis.setForwardLink(null);
                nodeAtAnalysis.setBackwardLink(null);
                endPointTailNode = followerNodeAfterNodeAtAnalysis;
            }
            else if (followerNodeAfterNodeAtAnalysis == null && previousNodeBeforeNodeAtAnalysis != null)
                /*The element we are looking at is the first*/
            {
                /*Make the connection for a backward-link between the previous node and the next one*/
                previousNodeBeforeNodeAtAnalysis.setForwardLink(null);
                nodeAtAnalysis.setBackwardLink(null);
                startPointHeadNode = previousNodeBeforeNodeAtAnalysis;
            }

            size--;
            return dataStoredInNodeAtAnalysis;
        }
    };
    /**
     * Function Implementation for left to right traversal and node evaluation
     */
    BiFunction<Node<E>,E, Boolean> evaluationLeftToRight = new BiFunction<Node<E>, E, Boolean>() {
        @Override
        public Boolean apply(Node<E> eNode, E element) {
            Node<E> nodeAtAnalysis = startPointHeadNode;
            for(int i= 0; i < size; i++)
            {
                if (nodeAtAnalysis.getData().equals(element))
                {
                    return true;
                }
                nodeAtAnalysis = nodeAtAnalysis.getForwardLink();
            }
            return false;
        }
    };

    /**
     * BiFunction Implementation for left to right traversal and node value update
     */
    BiFunction<Integer,E, E> setLeftToRight = new BiFunction<Integer, E, E>() {
        @Override
        public E apply(Integer index, E e) {
            //! We will traverse using the heading node passed in eNode
            Node<E> nodeAtAnalysis = startPointHeadNode;
            E previouslyStoredData = null;
            for(int i= 0; i < index; i++)
            {
                nodeAtAnalysis = nodeAtAnalysis.getForwardLink();
            }
            previouslyStoredData = nodeAtAnalysis.getData();
            nodeAtAnalysis.setData(e);
            return previouslyStoredData;
        }
    };
    /**
     * BiFunction Implementation for right to left traversal and node value update
     */
    BiFunction<Integer,E, E> setRightToLeft = new BiFunction<Integer, E, E>() {
        @Override
        public E apply(Integer index, E e) {
            //! We will traverse using the heading node passed in eNode
            Node<E> nodeAtAnalysis = endPointTailNode;
            E previouslyStoredData = null;
            for(int i= 0; i < index; i++)
            {
                nodeAtAnalysis = nodeAtAnalysis.getBackwardLink();
            }
            previouslyStoredData = nodeAtAnalysis.getData();
            nodeAtAnalysis.setData(e);
            return previouslyStoredData;
        }
    };


    //! Iterators for DoubleLinkedlist
    private class ForwardIterator implements Iterator<E> {
        private Node<E> currentNodeAtAnalysis = startPointHeadNode;
        private Node<E> previousNodeAtAnalysis;
        private Boolean canRemoveNodeAtAnalysis = false;

        @Override
        public boolean hasNext() {
            return (currentNodeAtAnalysis != null);
        }

        @Override
        public E next() {
            if (!this.hasNext())
            {
                throw new NoSuchElementException("Error Code 0x001 - [Raised] No hay mas elementos en la lista");
            }

            E data = currentNodeAtAnalysis.getData();
            previousNodeAtAnalysis = currentNodeAtAnalysis;
            currentNodeAtAnalysis = currentNodeAtAnalysis.getForwardLink();
            canRemoveNodeAtAnalysis = true;
            return data;
        }
        @Override
        public void remove() {
            if (!canRemoveNodeAtAnalysis) {throw new
                    IllegalStateException("Error Code 0x001 - [Raised] No se puede remover ahora, solo luego de realizar " +
                    "una operacion de next()");
            }

            if (previousNodeAtAnalysis == startPointHeadNode) {
                // Removing the first element
                removeFirst();
            } else if (currentNodeAtAnalysis == null) {
                // Removing the last element
                removeLast();
            } else {
                // Removing an element in the middle
                Node<E> beforePrev = startPointHeadNode;
                while (beforePrev != null && beforePrev.getForwardLink() != previousNodeAtAnalysis) {
                    beforePrev = beforePrev.getForwardLink();
                }

                if (beforePrev != null) {
                    beforePrev.setForwardLink(currentNodeAtAnalysis);
                    size--;
                }
            }

            canRemoveNodeAtAnalysis = false;
        }
    }

    private class BackwardIterator implements Iterator<E> {
        private Node<E> currentNodeAtAnalysis = endPointTailNode;
        private Node<E> previousNodeAtAnalysis;
        private Boolean canRemoveNodeAtAnalysis = false;

        @Override
        public boolean hasNext() {
            return (currentNodeAtAnalysis != null);
        }

        @Override
        public E next() {
            if (!this.hasNext())
            {
                throw new NoSuchElementException("Error Code 0x001 - [Raised] No hay mas elementos en la lista");
            }

            E data = currentNodeAtAnalysis.getData();
            previousNodeAtAnalysis = currentNodeAtAnalysis;
            currentNodeAtAnalysis = currentNodeAtAnalysis.getBackwardLink();
            canRemoveNodeAtAnalysis = true;
            return data;
        }
        @Override
        public void remove() {
            if (!canRemoveNodeAtAnalysis) {throw new
                    IllegalStateException("Error Code 0x001 - [Raised] No se puede remover ahora, solo luego de realizar " +
                    "una operacion de next()");
            }

            if (previousNodeAtAnalysis == endPointTailNode) {
                // Removing the first element
                removeLast();
            } else if (currentNodeAtAnalysis == null) {
                // Removing the last element
                removeFirst();
            } else {
                // Removing an element in the middle
                Node<E> beforePrev = endPointTailNode;
                while (beforePrev != null && beforePrev.getBackwardLink() != previousNodeAtAnalysis) {
                    beforePrev = beforePrev.getBackwardLink();
                }

                if (beforePrev != null) {
                    beforePrev.setBackwardLink(currentNodeAtAnalysis);
                    size--;
                }
            }

            canRemoveNodeAtAnalysis = false;
        }
    }

    @Override /** Override toString() to return elements in the list */
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        if (this.size == 0) {result.append("]");}
        Node<E> current = this.startPointHeadNode;

        while (current != null) {
            result.append(current.getData());
            if (current.getForwardLink() != null) {
                result.append("] <--> [");
            }
            current = current.getForwardLink();
        }
        result.append("]");
        return result.toString();
    }

    /**
     * Sets the value of an element given an index, this method has been left with no implementation given that it has
     * two avenues for working, but this depends on implementation details.
     *
     * @param index                : Index of the element to be set
     * @param element              : Element to be set
     * @param leftToRightTraversal: Boolean value to indicate the traversal direction
     * @return E: The previous value of the element at the specified index
     */
    @Override
    public E set(int index, E element, boolean leftToRightTraversal) {
        //! Base Check, review if the index is valid
        this.checkIndex(index);

        //! Base Check.2: review if conversion between element and our data can be done
        if (!element.getClass().isInstance(this.getFirst())){
            throw new ClassCastException("Error Code 0x001 - [Raised] The element is not of the same type as the list");
        }

        //! Inductive Step: Call the appropriate method based on the traversal direction
        return (leftToRightTraversal) ? this.setLeftToRight.apply(index, element) : this.setRightToLeft.apply(index, element);
    }
}
