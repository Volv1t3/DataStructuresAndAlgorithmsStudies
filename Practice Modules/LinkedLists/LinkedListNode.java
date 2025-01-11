package LinkedLists;

public class LinkedListNode<E extends Comparable<E>>{
    private E data; //data is the data stored in the node
    private LinkedListNode<E> next; //next is a reference to the next node in the list
    private LinkedListNode<E> prev; //prev is a reference to the previous node in the list

    /**
     * Constructor for creating a new LinkedListNode
     * @param data the data to be stored in the node
     */
    public LinkedListNode(E data){
        this.setData(data);
        this.setNext(null);
        this.setPrev(null);
        //initially, the next and prev nodes are set to null
    }
    /**
     * Getter method for the data stored in the node
     * @return the data stored in the node
     */
    public E getData(){
        return this.data;
    }
    /**
     * Setter method for the data stored in the node
     * @param data the new data to be stored in the node
     */
    public void setData(E data){
        this.data = data;
    }
    /**
     * Getter method for the next node in the list
     * @return the next node in the list
     */
    public LinkedListNode<E> getNext(){
        return this.next;
    }
    /**
     * Setter method for the next node in the list
     * @param next the new next node in the list
     */
    public void setNext(LinkedListNode<E> next){
        this.next = next;
    }
    /**
     * Getter method for the previous node in the list
     * @return the previous node in the list
     */
    public LinkedListNode<E> getPrev() {
        return prev;
    }
    /**
     * Setter method for the previous node in the list
     * @param prev the new previous node in the list
     */
    public void setPrev(LinkedListNode<E> prev) {
        this.prev = prev;
    }
}
