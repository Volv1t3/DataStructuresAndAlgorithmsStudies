package HeapImplementations;

import DoubleLinkedList.DoubleLinkedList;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Collection;
import java.util.List;

public class MinHeapWithLinkedList <E extends Comparable<E>>{

    //! Underlying data structure
    private DoubleLinkedList<E> data = new DoubleLinkedList<E>();

    //! Constructor Implementations

    /**
     * Constructor that takes in an external source of elements to be added to the heap
     * @param externalSource: Collection<E> extending class which provides the external data source
     * @see Collection
     * @throws NullPointerException if externalSource is null
     * @throws IllegalArgumentException if externalSource is empty
     * @apiNote : The Collection passed into the constructor must provide an iterator implementation accessible through the
     * <b>iterator() method of the Collection interface</b>
     */
    public MinHeapWithLinkedList(Collection<E> externalSource)
            throws NullPointerException, IllegalArgumentException {
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
     * Default constructor
     * @apiNote : This constructor is used when no external data source is provided
     * @implNote  : The underlying data structure is a DoubleLinkedList
     */
    public MinHeapWithLinkedList(){}

    //? Adding methods to the linked list
    public void addElement(E elementToAdd){
//! Base Step: Add the element into the underlying data structure
        this.data.add(elementToAdd);
        //! Inductive Step: Proceed with sorting mechanism to organize the newly formed tree
        int indexAtAnalysis = this.data.size() -1; //? Index of the last element
        while (indexAtAnalysis > 0){
            int parentOfIndexAtAnalysis = (indexAtAnalysis - 1)/2;
            if (this.data.get(indexAtAnalysis, true)
                    .compareTo(this.data.get(parentOfIndexAtAnalysis, true)) < 0){
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
                        .compareTo(this.data.get(rightChildIndex, true)) > 0){
                    maxIndex = rightChildIndex;
                }
            }
            //! Swap the current node if it is less than the max of the two children
            if (this.data.get(indexAtAnalysis, true)
                    .compareTo(this.data.get(maxIndex, true)) > 0){
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
        MinHeapWithLinkedList<Integer> test = new MinHeapWithLinkedList<>();
        test.addElement(10);
        test.addElement(20);
        test.addElement(5);
        List.of(10,100,20,30,5,1,230, -20).forEach(test::addElement);
        while (!test.isEmpty()){
            System.out.printf("%d ", test.removeRetrievesRoot());
        }
    }
}
