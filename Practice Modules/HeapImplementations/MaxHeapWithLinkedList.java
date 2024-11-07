package HeapImplementations;

import DoubleLinkedList.DoubleLinkedList;

import java.util.Collection;
import java.util.List;

/**
 * The present file will allow the user to interact with a max heap, which underneath has a linked list
 * (my Double Linked List Implementation for that matter), the sorting operations will be handled much in the same
 * way as the array list implementation is done. The main difference will be in iteration times, and a better
 * memory usage profile.
 */
public class MaxHeapWithLinkedList<E extends Comparable<E>> {
    //! Define the internal data structure
    private DoubleLinkedList<E> data = new DoubleLinkedList<E>();

    /**
     * Constructor method which allows the user to create an instance of this heap based on a collection passed into it.
     * @param externalSource: <code> Collection< E ></code> extending structure which provides iterator support underneath.
     *
     */
    public MaxHeapWithLinkedList(Collection<E> externalSource){
        if (externalSource == null) {
            throw new NullPointerException("Parameter passed into externalSource is null");
        }
        if (externalSource.isEmpty()) {
            throw new IllegalArgumentException("Parameter passed into externalSource is empty");
        }
        //! Move the elements with an iterator
        for (E e : externalSource) {
            this.addElement(e);
        }
    }

    /**
     * No argument constructor
     */
    public MaxHeapWithLinkedList(){}

    /**
     * Method for adding an element straight into the heap implementation. It will leverage internally the double iteration
     * available in the Double Linked List Implementation used, iteration and index formation
     * @param elementToAdd: element to be added to the heap
     * @return void
     */

    public void addElement(E elementToAdd) {
        //! Base Step: Add the element into the underlying data structure
        this.data.add(elementToAdd);
        //! Inductive Step: Proceed with sorting mechanism to organize the newly formed tree
        int indexAtAnalysis = this.data.size() -1; //? Index of the last element
        while (indexAtAnalysis > 0){
            int parentOfIndexAtAnalysis = (indexAtAnalysis - 1)/2;
            if (this.data.get(indexAtAnalysis, true)
                    .compareTo(this.data.get(parentOfIndexAtAnalysis, true)) > 0){
                E temporalValue = this.data.get(indexAtAnalysis, true);
                this.data.set(indexAtAnalysis,
                        this.data.get(parentOfIndexAtAnalysis, true),
                        true);
                this.data.set(parentOfIndexAtAnalysis, temporalValue, true);
            }
            else {break;}
            indexAtAnalysis = parentOfIndexAtAnalysis;
        }

    }
    //? Public method to removeTheLast Element
    public E removeRetrievesRoot(){
        if (this.data.isEmpty()){
            return null;
        }

        //! Inductive Step: Perform base heap operations on each subtree
        E removedObject = this.data.getFirst();
        this.data.set(0, this.data.getLast(), true);
        this.data.removeLast();

        int indexAtAnalysis = 0;
        while (indexAtAnalysis < this.data.size()){
            int leftChildIndex = (2*indexAtAnalysis) + 1;
            int rightChildIndex = (2*indexAtAnalysis) + 2;

            //! Find the maximum between the two children
            if (leftChildIndex >= this.data.size()) {break;}
            int maxIndex = leftChildIndex;
            if (rightChildIndex < this.data.size()){
                if (this.data.get(maxIndex, true)
                        .compareTo(this.data.get(rightChildIndex, true)) < 0){
                    maxIndex = rightChildIndex;
                }
            }
            //! Swap the current node if it is less than the max of the two children
            if (this.data.get(indexAtAnalysis, true)
                    .compareTo(this.data.get(maxIndex, true)) < 0){
                E temporalValue = this.data.get(maxIndex, true);
                this.data.set(maxIndex, this.data.get(indexAtAnalysis, true), true);
                this.data.set(indexAtAnalysis, temporalValue, true);
                indexAtAnalysis = maxIndex;
            }
            else {break;}
        }
        return removedObject;
    }


    //? Helper method for getting the size
    public Integer getSize(){
        return this.data.size();
    }

    //? Helper method to know if the list is empty
    public Boolean isEmpty(){
        return this.data.isEmpty();
    }

    public static void main(String[] args) {
        MaxHeapWithLinkedList<Integer> heapForIntegers = new MaxHeapWithLinkedList<>(List.of(21,30,22,11,40));
        heapForIntegers.addElement(1200);
        while (!heapForIntegers.isEmpty()) {
            System.out.printf("%d ", heapForIntegers.removeRetrievesRoot());
        }
    }
}


