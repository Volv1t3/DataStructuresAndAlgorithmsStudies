package HeapImplementations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The present class is an implementation of the Heap data structure only that it is reversed. Rather than the root being the
 * largest element in the array, it will be the smallest, and we will keep the notion that any of its children must be
 * to a certain extent larger or equal to the root.
 * @param <E>: Parameter type for class instantiation; must be an implementor of <code>Comparable< E ></code>
 * @see Comparable
 */
public class MinHeapWithArrayList<E extends Comparable<E>>{

    //! Backing data structure
    private ArrayList<E> data = new ArrayList<>();

    //! Constructors
    /**
     * Constructor that takes in an external source of elements to be added to the heap
     * @param externalSource: Collection<E> extending class which provides the external data source
     * @see Collection
     * @throws NullPointerException if externalSource is null
     * @throws IllegalArgumentException if externalSource is empty
     * @apiNote: The Collection passed into the constructor must provide an iterator implementation accessible through the
     * <b>iterator() method of the Collection interface</b>
     */
    public MinHeapWithArrayList(Collection<E> externalSource)
            throws NullPointerException, IllegalArgumentException{
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
     * Default constructor for the class.
     */
    public MinHeapWithArrayList(){}

    //! Add element method
    public void addElement(E elementToAdd){
        //! Base Step: add the element into the list on its own
        this.data.add(elementToAdd);
        //! Inductive Step: proceed with the sorting mechanism to organize elements
        int indexAtAnalysis = this.data.size() -1;
        while (indexAtAnalysis > 0){
            int parentIndexAtAnalysis = (indexAtAnalysis - 1)/2;
            if (this.data.get(indexAtAnalysis)
                    .compareTo(this.data.get(parentIndexAtAnalysis)) < 0){
                E temporal = this.data.get(indexAtAnalysis);
                this.data.set(indexAtAnalysis, this.data.get(parentIndexAtAnalysis));
                this.data.set(parentIndexAtAnalysis, temporal);
            }
            else {break;}
            indexAtAnalysis = parentIndexAtAnalysis;
        }
    }
    //! Remove element method
    public E removeRetrievesRoot() {
        if (this.data.isEmpty()){return null;}

        E removedObject = this.data.getFirst();
        this.data.set(0, this.data.getLast());
        this.data.removeLast();

        int indexAtAnalysis = 0;
        while (indexAtAnalysis < this.data.size()){
            int leftChildIndex = (2*indexAtAnalysis) + 1;
            int rightChildIndex = (2*indexAtAnalysis) + 2;

            //! Find the minimum between the two children
            if (leftChildIndex >= this.data.size()){break;}
            int minIndex = leftChildIndex;
            if (rightChildIndex < this.data.size()){
                if (this.data.get(minIndex)
                        .compareTo(this.data.get(rightChildIndex)) > 0){
                    minIndex = rightChildIndex;
                }
            }

            //! Swap the root node with the min child
            if (this.data.get(indexAtAnalysis)
                    .compareTo(this.data.get(minIndex)) > 0){
                E temp = this.data.get(minIndex);
                this.data.set(minIndex, this.data.get(indexAtAnalysis));
                this.data.set(indexAtAnalysis, temp);
                indexAtAnalysis = minIndex;
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
        MinHeapWithArrayList<Integer> test = new MinHeapWithArrayList<>();
        test.addElement(10);
        test.addElement(20);
        test.addElement(5);
        List.of(10,100,20,30,5,1,230, -20).forEach(test::addElement);
        while (!test.isEmpty()){
            System.out.printf("%d ", test.removeRetrievesRoot());
        }
    }
}
