package Trees;

import java.util.Comparator;

/**
 * @Description: The following class will be the foundations for a Normal Binary Tree, while also presenting internal charactersitics
 * that would allow for a threaded tree to be built atop it. It will have fields both for left and right children, where
 * either of those can be threaded to make this a Single Threaded or Doubly Threaded Binary Tree.
 * @author : Santiago Arellano
 * @Date: October 18th, 2024.
 * @apiNote : The present class, as well as its implementation rely on a clear comparable implementation being defined
 * within the parametrized type used in instantiation. In further iterations, this method will be reviewed (in this same
 * file to allow for Comparator instances to be used
 * @param <E> : Parametrized data type which must extend the comparable functional interface.
 * @see Comparable
 */
public class BinaryTreeNode<E extends Comparable<E>>{
    //! Internal Variables
    private E internalStoredData;
    private BinaryTreeNode<E> leftOrThreadedChild;
    private BinaryTreeNode<E> rightOrThreadedChild;

    //! Constructors

    //? Single-args constructor.
    public BinaryTreeNode(E externalData) throws NullPointerException{
        this.setInternalStoredData(externalData);
    }

    //? No-args constructor
    public BinaryTreeNode(){}


    //! Getters and Setters
    //* InternalStoredData
    public void setInternalStoredData(E externalData)
            throws NullPointerException{
        if (externalData == null){throw new NullPointerException(("External Data cannot be null!"));}
        if (this.internalStoredData == externalData){return;}
        else {
            this.internalStoredData = externalData;
        }
    }
    public E getInternalStoredData(){return this.internalStoredData;}
    //* Left Child
    public void setLeftOrThreadedChild(BinaryTreeNode<E> leftOrThreadedChild)
            throws NullPointerException{
        this.leftOrThreadedChild = leftOrThreadedChild;
    }
    public BinaryTreeNode<E> getLeftOrThreadedChild() {return this.leftOrThreadedChild;}
    //* Right Child
    public void setRightOrThreadedChild(BinaryTreeNode<E> rightOrThreadedChild) throws NullPointerException{
        this.rightOrThreadedChild = rightOrThreadedChild;
    }

    public BinaryTreeNode<E> getRightOrThreadedChild(){return this.rightOrThreadedChild;}
}

