package DoubleLinkedList;

public class Node<T> {

    private Node<T> backwardLink;
    private Node<T> forwardLink;
    private T data;


    //! Single Data File Constructor, only takes in data and allows both links to be null
    public Node(T data) {
        this.data = data;
    }
    //! No data file constructor, creates all variables and defines them as null
    public Node() {
    }

    //! Setters y Getters para getBackwardLink
    //? Getter
    public Node<T> getBackwardLink() {
        return backwardLink;
    }
    //? Setter
    public void setBackwardLink(Node<T> backwardLink) {
        this.backwardLink = backwardLink;
    }

    //! Setters y Getters para getForwardLink
    //? Getter
    public Node<T> getForwardLink() {
        return forwardLink;
    }
    //? Setter
    public void setForwardLink(Node<T> forwardLink) {
        this.forwardLink = forwardLink;
    }

    //! Setters y Getters para Data
    //? Getter
    public T getData() {
        return data;
    }
    //? Setter
    public void setData(T data) {
        this.data = data;
    }
}
