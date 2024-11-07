package DeberCuatroLinkedListSantiagoArellano;
public class Node<E>{

    //! Variables Internas
    protected E storedValueInNode;
    protected Node<E> forwardLink;
    
    //! Implementacion de un constructor sin argumentos para iniciacion simple
    public Node(){
        this.setStoredValueInNode(null);
        this.setForwardLink(null);
    }
    
    //! Implementacion de un constructor con solo e_storedValueInNode y locationalIndex
    public Node(E e_storedValueInNode){
        this.setStoredValueInNode(e_storedValueInNode);
        this.setForwardLink(null);

    }
    //! Getters y Setters Para storedValueInNode
    //? Getter 
    public E getStoredValueInNode() {
        return storedValueInNode;
    }
    //! Setter
    public void setStoredValueInNode(E e_storedValueInNode) {
        this.storedValueInNode = e_storedValueInNode;
    }

    //! Getters y Setters para forwardLink
    //? Getter
    public Node<E> getForwardLink() {
        return forwardLink;
    }
    //? Setter
    public void setForwardLink(Node<E> forwardLink) {
        this.forwardLink = forwardLink;
    }

}
