//package LinkedLists;
//
//import java.util.Arrays;
//import java.util.Collection;
//
//public class SingleLinkedList<E extends Comparable<E>> implements LinkedList<E>{
//    //! Private fields
//    private LinkedListNode<E> eHead;
//    private Integer eSize = 0;
//    private LinkedListNode<E> eTail;
//
//
//    //! Constructors
//    public SingleLinkedList() {
//     this.setHead(null);
//     this.setTail(null);
//    }
//    public SingleLinkedList(LinkedListNode<E> eHead) {
//        this.setHead(eHead);
//    }
//    public SingleLinkedList(E data) {
//        this.setHead(new LinkedListNode<E>(data));
//        this.setTail(this.getHead());
//    }
//    public SingleLinkedList(E data, LinkedListNode<E> nextNode) {
//        this.setHead(new LinkedListNode<E>(data));
//        this.getHead().setNext(nextNode);
//        this.setTail(nextNode);
//    }
//    public SingleLinkedList(Collection<E> dataInputs){
//
//        for(E data: dataInputs){
//            this.addFirstReplaceHead(data);
//        }
//    }
//    public SingleLinkedList(SingleLinkedList<E> singleLinkedList){
//        this.setHead(singleLinkedList.getHead());
//        this.eSize = singleLinkedList.getSize();
//    }
//    public SingleLinkedList(E[] arrayOfData){
//
//        for(E data: arrayOfData){
//            this.addFirstReplaceHead(data);
//        }
//    }
//
//    //! Implementacion de metodos de de add
//
//
//    @Override
//    public E getFirst() {
//        return this.eHead.getData();
//    }
//
//    @Override
//    public E getFirstReversed() {
//        throw new UnsupportedOperationException("Unimplemented method 'getFirstReversed' for Single Linked Lists");
//    }
//
//    @Override
//    public E getLast() {
//        LinkedListNode<E> currentNode = this.getHead();
//        while (currentNode.getNext() != null) {
//            currentNode = currentNode.getNext();
//        }
//
//        return currentNode.getData();
//    }
//
//    @Override
//    public E getLastReversed() {
//        throw new UnsupportedOperationException("Unimplemented method 'getLastReversed' for Single Linked Lists");
//    }
//
//    @Override
//    public E get(int index) {
//        LinkedListNode<E> currentNode = this.getHead();
//        for(int i = 0; i < index; i++){
//            currentNode = currentNode.getNext();
//        }
//        return currentNode.getData();
//    }
//
//    @Override
//    public E get(int index, boolean leftToRightTraversal) {
//        //! Ignoramos leftToRightTraversal porque no se implementa aqui
//        this.get(index);
//    }
//
//    @Override
//    public E get(E data) {
//        LinkedListNode<E> currentNode = this.getHead();
//        while (currentNode != null) {
//            if (currentNode.getData().equals(data)) {
//                break;
//            }
//            currentNode = currentNode.getNext();
//        }
//        if (currentNode == null) {
//            return null;
//        }
//        return (currentNode.getData() != null ? currentNode.getData() :  null);
//    }
//
//    @Override
//    public E get(E data, boolean leftToRightTraversal) {
//        return this.get(data);
//    }
//
//    //! Implementacion de Add methods
//
//
//    /**
//     * Adds a new node to the forward link of the <b>head node</b>. Keeping the head intact
//     *
//     * @param data :  Data to be stored in the new node
//     */
//    @Override
//    public void addFirst(E data) {
//        LinkedListNode<E> newHead = new LinkedListNode<E>(data);
//        if (this.eHead == null) {
//            this.setHead(newHead);
//            this.setTail(newHead);
//        }
//        else {
//            LinkedListNode<E> followerNodeAfterHead = this.eHead.getNext();
//            if (followerNodeAfterHead == null) /*Case when the next node is null and we only have a head*/{
//                this.eHead.setNext(newHead);
//            }
//            else /*The next node is not null and we need to match connections*/{
//                //!Append head's previous follower
//                newHead.setNext(followerNodeAfterHead);
//                //! Append new head's next node
//                this.eHead.setNext(newHead);
//            }
//        }
//        this.eSize++;
//    }
//
//    /**
//     * Adds a new node to the backward link of the <b>tail node</b>, keeping the tail intact.
//     * If the list is empty, it will initialize the list with this node as both head and tail.
//     *
//     * @param data Data to be stored in the new node.
//     */
//    @Override
//    public void addFirstReplaceHead(E data) {
//        LinkedListNode<E> newHead = new LinkedListNode<E>(data);
//        //! Base Case when there is no head nor any tail
//        if (this.eHead == null) {
//            this.setHead(newHead);
//            this.setTail(newHead);
//        }
//        else /*Replace the head since the head does exist*/ {
//
//        }
//
//
//    }
//
//    @Override
//    public void addLast(E data) {
//        /*
//         * La idea de este metodo,  es agregar un nodo al final de la lista
//         * 1. Recorrer la lista hasta el penultimo nodo
//         * 2. Agregar el nuevo nodo al final de la lista
//         * 3. Aumentar el tamaño de la lista
//         */
//        LinkedListNode<E> currentNode = this.getHead();
//        while (!currentNode.getNext().getData().equals(data)) {
//            currentNode = currentNode.getNext();
//        }
//        //! Extraemos el next pointer de verdad
//
//        currentNode.setNext(new LinkedListNode<E>(data));
//        this.eSize++;
//    }
//
//    @Override
//    public void addLastReplaceTail(E data) {
//
//    }
//
//    //! Setters
//    private void setHead(LinkedListNode<E> eHead) throws NullPointerException{
//        this.eHead = eHead;
//    }
//    private LinkedListNode<E> getHead() {
//        return this.eHead;
//    }
//    public Integer getSize() {
//        return this.eSize;
//    }
//    private void setTail(LinkedListNode<E> eTail) {
//        this.eTail = eTail;
//    }
//}
