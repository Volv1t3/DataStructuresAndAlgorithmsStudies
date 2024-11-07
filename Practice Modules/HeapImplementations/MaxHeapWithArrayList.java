package HeapImplementations;
import DoubleLinkedList.DoubleLinkedList;
import org.junit.platform.commons.util.BlacklistedExceptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class MaxHeapWithArrayList<E extends Comparable<E>>{

    //! Internally, we use an ArrayList to store our numbers;
    private ArrayList<E> data = new ArrayList<>();

    //! Let us define some constructors

    /**
     * Provides a way to construct a Max Heap through an already defined list of values
     * @param externalSource external source of data points for this constructor. Argument must extend <code>Collection < E > </code>
     **/
    public MaxHeapWithArrayList(Collection<E> externalSource) throws NullPointerException, IllegalArgumentException {
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

    //? No Argument constructor
    public MaxHeapWithArrayList() {
    }

    //? Adding method
    public void addElement(E elementToAdd) {
        //! Add the element directly into the list
        this.data.add(elementToAdd);
        //! Proceed with the sorting mechanism to organize elements
        int indexAtAnalysis = this.data.size() - 1; //? Index of the last element since this method works by ordering from the last
        while (indexAtAnalysis > 0) /*i.e., we have not gone past the last element*/ {
            int parentOfIndexAtAnalysis = (indexAtAnalysis - 1) / 2;
            if (this.data.get(indexAtAnalysis).compareTo(this.data.get(parentOfIndexAtAnalysis)) > 0) {
                E temporal = this.data.get(indexAtAnalysis);
                this.data.set(indexAtAnalysis, this.data.get(parentOfIndexAtAnalysis));
                this.data.set(parentOfIndexAtAnalysis, temporal);
            } else {
                break;
            }

            indexAtAnalysis = parentOfIndexAtAnalysis;
        }
    }

    /**
     * Method that allows for the returning of the root node in this heap. After this it executes a piece of code which will sort all nodes
     * again.
     * @return E: last value stored at the root node
     */
    public E removeRetrievesRoot() {
        if (this.data.isEmpty()) {
            return null;
        } //! If no elements are inside, we return null, and that is our end

        E removedObject = this.data.getFirst();
        this.data.set(0, this.data.getLast());
        this.data.removeLast();

        int indexAtAnalysis = 0;
        while (indexAtAnalysis < this.data.size()) {
            int leftChildIndex = (2 * indexAtAnalysis) + 1;
            int rightChildIndex = (2 * indexAtAnalysis) + 2;

            //! Find the maximum between the two children
            if (leftChildIndex >= this.data.size()){break;} //! The tree is a heap?
            int maxIndex = leftChildIndex;
            if (rightChildIndex < this.data.size()) {
                if (this.data.get(maxIndex).compareTo(this.data.get(rightChildIndex) ) < 0) {
                    maxIndex = rightChildIndex;
                }
            }

            //!Swap the current node if it's less than the max of the two children.
            if (this.data.get(indexAtAnalysis).compareTo(this.data.get(maxIndex) ) < 0) {
                E temp = this.data.get(maxIndex);
                this.data.set(maxIndex, this.data.get(indexAtAnalysis));
                this.data.set(indexAtAnalysis, temp);
                indexAtAnalysis = maxIndex;
            }
            else {break;} //! Tree is a heap
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


    public static <E extends Comparable<E>> void heapSort(E[] externalSource)
    {
        MaxHeapWithArrayList<E> internalSortingStructure = new MaxHeapWithArrayList<>(List.of(externalSource));
        //! Replace the values in the order the heap retrieves them
        for (int i = externalSource.length - 1; i >= 0; i--) {
            externalSource[i] = internalSortingStructure.removeRetrievesRoot();
        }
    }


    public static void main(String[] args) {
    //! Construct a heap for integers
        MaxHeapWithArrayList<Integer> heapForIntegers = new MaxHeapWithArrayList<>(List.of(21,30,22,11,40));
        while (!heapForIntegers.isEmpty())
        {
            System.out.printf("%d ", heapForIntegers.removeRetrievesRoot());
        }
        System.out.println();
        MaxHeapWithArrayList<Integer> heapForLinkedList = new MaxHeapWithArrayList<>(new DoubleLinkedList<Integer>(10,20,30,50,22,3));
        while (!heapForLinkedList.isEmpty())
        {
            System.out.printf("%d ", heapForLinkedList.removeRetrievesRoot());
        }
        System.out.println();
        Integer[] arrayToSort = {10, 20, 30, 50, 22, 3};
        heapSort(arrayToSort);
        for (int i = 0; i < arrayToSort.length; i++) {
            System.out.printf("%d ", arrayToSort[i]);
        }
    }
}



