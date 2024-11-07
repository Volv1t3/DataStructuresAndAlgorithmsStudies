package Trees;

/**
 * @Description: The following file contains an extension of the basic binary tree to be usable for those methods that
 * do not have a Comparable implementation within them, rather it will be usable with a comparator instance passed in
 * instantiation.
 * @author : Santiago Arellano
 * @Date: October 18th, 2024.
 */
public class BinaryTreeNodeCompare<E> {
    //! Internal Variables
    private E eStoredInformation;
    private BinaryTreeNodeCompare<E> eLeftOrThreadedChild;
    private BinaryTreeNodeCompare<E> eRightOrThreadedChild;

    //! Constructors
    //* Two-args Constructor
    public BinaryTreeNodeCompare(E externalInformation) throws NullPointerException{
        this.seteStoredInformation(externalInformation);
    }
    //* No-args Constructor
    public BinaryTreeNodeCompare(){
    }

    //! Setters and Getters
    //* eStoredInformation

    /**
     * Allows for the user to set the information stored in a given node from where this method is instantiated.
     * @param externalInformation : Information to be placed in the given node.
     * @throws NullPointerException : If the information passed into the node is nullish.
     */
    public void seteStoredInformation(E externalInformation)
            throws NullPointerException{
        if (externalInformation == null){throw new NullPointerException("Information cannot be null in an assignment operation");}
        if (externalInformation == this.eStoredInformation){return;}
        else {
            this.eStoredInformation = externalInformation;
        }
    }

    /**
     * Method which allows for the retrieval of the stored information in the node.
     * @return : Data value of Type E representing the information in the node.
     */
    public final E geteStoredInformation() {return this.eStoredInformation;}

    //* Left Child

    /**
     * Method which allows for the user to set the left children or a reference to a threaded connection for a Threaded Binary Tree.
     * @param leftOrThreadedChild: The reference to the children or threaded connection to be stored.
     * @throws NullPointerException: If the value passed into the function is nullish.
     */
    public void seteLeftOrThreadedChild(BinaryTreeNodeCompare<E> leftOrThreadedChild)
            throws NullPointerException{
        if (leftOrThreadedChild == null){throw new NullPointerException("Left Child cannot be null in an assignment operation");}
        this.eLeftOrThreadedChild = leftOrThreadedChild;
    }

    /**
     * Allows for the retrieval of the node stored in the left or Threaded Connection of a given node.
     * @return : A BinaryTreeNodeCompare< E > Node representative of the left child for a given node.
     */
    public final BinaryTreeNodeCompare<E> geteLeftOrThreadedChild() {return this.eLeftOrThreadedChild;}


    //* Right Child

    /**
     * Method that allows the user to assign a node to the given right children or Threaded connection for the node from which
     * this method is called.
     * @param rightOrThreadedChild : Instance of this class which represents the node to be added.
     */
    public void seteRightOrThreadedChild(BinaryTreeNodeCompare<E> rightOrThreadedChild){
        if (rightOrThreadedChild == null){throw new NullPointerException("Right Child cannot be null in an assignment operation");}
        this.eRightOrThreadedChild = rightOrThreadedChild;
    }

    /**
     * Allows for the retrieval of the node stored on the right children of a given node.
     * @return : A BinaryTreeNodeCompare< E > instance representative of the right child node
     */
    public final BinaryTreeNodeCompare<E> geteRightOrThreadedChild(){return this.eRightOrThreadedChild;}
}
