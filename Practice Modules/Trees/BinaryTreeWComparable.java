package Trees;

import java.math.BigInteger;
import java.util.Optional;
import java.util.function.Function;

public class BinaryTreeWComparable<E extends Comparable<E>> {
    //! Internal values for tree
    /**
     * Value which represents the root of the tree
     */
    private BinaryTreeNode<E> eBinTreeRoot;
    /**
     * Value representative of the Height of the tree. The height of a tree is the maximum level of any given node in
     * a tree; i.e., how deep the tree goes from the root to the Kst level of a branch node.
      */
    private BigInteger eBinTreeHeight = BigInteger.ZERO;
    /**
     * Functional Programming value meant to allow for the storage of a user defined processing function.
     */
    private Function<BinaryTreeNode<E>, Optional<E>> userProcessingFunction = new Function<BinaryTreeNode<E>, Optional<E>>() {
        @Override
        public Optional<E> apply(BinaryTreeNode<E> eBinaryTreeNode) {
            return Optional.of(eBinaryTreeNode.getInternalStoredData());
        }
    };
    //! Constructor Methods
    /**
     * No args constructor, designed for bare-bones usage even from the root definition
     */
    public BinaryTreeWComparable(){}
    /**
     * One-arg constructor, allowing the user to define the initial root of the Binary Tree.
     * @param initialRoot: Initial Root passed into the constructor.
     */
    public BinaryTreeWComparable(BinaryTreeNode<E> initialRoot){
            this.seteBinTreeRoot(initialRoot);
    }

    /*
     * The following sections are a one-to-one structurally similar implementation of the model provided in the book
     * Data Structures and Algorithms in C++, which has been used as a study pathway for my entire class. Since this is
     * a new data structure, I chose to follow this book for its simplicity and informative code snippets. One thing that
     * will be done, however, is checking with Liang's Book for any inconsistencies, or translations of methodologies from
     * C++ that are not supported directly in Java, like swapping.
     */

    /**
     * Basic method for checking if the tree is empty, it will be empty if the root is null, otherwise it will not be empty.
     * @return : Boolean depending on the root being, or not, null
     */
     public boolean isEmpty(){
         return this.eBinTreeRoot == null;
     }
    /**
     * Helper method which allows for the clearing of the internal structure. Unlike C++, since we cannot handle pointers
     * directly we do not need to care for the memory, rather the GC of the JVM will return it to the heap in a timely manner.
     */
     public void clear(){
         this.eBinTreeRoot = null;
         this.eBinTreeHeight = BigInteger.ZERO;
     }

    /**
     * Method which depending on the functionality assigned into the processing function (by the user) performs a PLR type
     * of node traversal on this tree. In other words, it first analyzes any given node, and then recursively (or iteratively)
     * work to traverse all nodes on the left before moving to the right.
     */
    public void preorderIteration(){

    }

    /**
     * Method which depending on the functionality assigned into the processing function (by the user) performs an LPR type
     * of node traversal on this tree.
     */
    public void inorderIteration(){

    }

    /**
     * Method that depending on the functionality assigned into the processing function (by the user) performs an LRP
     * type of node traversal on this tre
     */
    public void postorderIteration(){

    }



    //! Setter and Getters
    //* Root
    private void seteBinTreeRoot(BinaryTreeNode<E> root) throws NullPointerException{
        if (root == null){throw new NullPointerException("Any given root cannot be null for an assignment operation");}
        this.eBinTreeRoot = root;
    }
    public BinaryTreeNode<E> geteBinTreeRoot(){
        return this.eBinTreeRoot;
    }
    //* Height
    public BigInteger geteBinTreeHeight(){
        return this.eBinTreeHeight;
    }

    /**
     * Method which allows for the retrieval of the user defined Processing Function used in this Binary Tree.
     * @return Function< BinaryTree < E >, Optional < E > > reference, representative of the user defined function.
     */
    public Function<BinaryTreeNode<E>, Optional<E>> getUserProcessingFunction() {
        return userProcessingFunction;
    }

    /**
     * Method which allows the user to define their own implementation of a processing function.
     * @implNote : <p>THe processing function defined must be an implemnetation of the following class. <code>
     *     Function < BinaryTreeNode< E > , Optional< E > >
     * </code>, the reason for this is that the user can either do something with the value, like changing it and reappend
     * it to each node, or they could simply print it out. For this reason we are using optionals, to allow for</p>
     * <ul>
     *     <li>Flexibility in analysis</li>
     *     <li>Extensible and reworkable structure for input passing across functions</li>
     * </ul>
     * <p>The reason for this is that if the user defines a print only or non-varying analysis function, they could simply return
     * an empty optional with <code>Optional.empty()</code>, this would be received by the analysis input and for each of the traversing
     * methods, based on the input the code will work.</p>
     * @param userProcessingFunction
     */
    public void setUserProcessingFunction(Function<BinaryTreeNode<E>, Optional<E>> userProcessingFunction) {
        this.userProcessingFunction = userProcessingFunction;
    }
}
