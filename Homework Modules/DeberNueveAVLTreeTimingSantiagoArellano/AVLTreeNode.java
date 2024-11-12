package DeberNueveAVLTreeTimingSantiagoArellano;

/**
 * @Author: Santiago Arellano
 * @Date: Noviembre 11, 2024
 * @Description: AVL Tree Node Class
 * @param <E>
 */
public class AVLTreeNode<E extends Comparable<E>> {
    //! Defining internal variables
    private E internalStoredData;
    private Integer internalBalanceFactor = 0;
    private AVLTreeNode<E> leftOrThreadedChild;
    private AVLTreeNode<E> rightOrThreadedChild;

    public Integer getInternalLocalizedHeight() {
        return internalLocalizedHeight;
    }

    public void setInternalLocalizedHeight(Integer internalLocalizedHeight) {
        this.internalLocalizedHeight = internalLocalizedHeight;
    }

    private Integer internalLocalizedHeight;

    //! Constructors
    public AVLTreeNode(E externalData) throws NullPointerException{
        this.setInternalStoredData(externalData);
    }

    public AVLTreeNode(){;}

    //! Setters
    public void setInternalStoredData(E externalData) throws NullPointerException{
        if (externalData == null){
            throw new NullPointerException("Null AVLTreeNode.internalStoredData input");
        }
        this.internalStoredData = externalData;
    }
    public void setLeftOrThreadedChild(AVLTreeNode<E> leftOrThreadedChild1){
            this.leftOrThreadedChild = leftOrThreadedChild1;
    }
    public void setRightOrThreadedChild(AVLTreeNode<E> rightOrThreadedChild1){
        this.rightOrThreadedChild = rightOrThreadedChild1;
    }
    public void setInternalBalanceFactor(Integer internalBalanceFactor1){
        this.internalBalanceFactor = internalBalanceFactor1;
    }

    //! Getters
    public E getInternalStoredData(){return this.internalStoredData;}
    public AVLTreeNode<E> getLeftOrThreadedChild(){return this.leftOrThreadedChild;}
    public AVLTreeNode<E> getRightOrThreadedChild(){return this.rightOrThreadedChild;}
    public Integer getInternalBalanceFactor(){return this.internalBalanceFactor;}

}
