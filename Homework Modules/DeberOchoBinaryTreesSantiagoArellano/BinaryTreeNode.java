package DeberOchoBinaryTreesSantiagoArellano;

/**
 * @Description: La siguiente clase sera la base para un Arbol Binario Normal, mientras que tambien presenta caracteristicas internas
 * que permitirian construir un arbol con hilos sobre el. Tendra campos tanto para los hijos izquierdo como derecho, donde
 * cualquiera de ellos puede ser hilos para hacer de este un Arbol Binario con Hilos Simples o Dobles.
 * @author : Santiago Arellano
 * @Date: Octubre 18th, 2024.
 * @param <E> : Tipo de Dato Parametrizado que se utiliza para instanciar la clase, require extender Comparable
 * @see Comparable
 */
public class BinaryTreeNode<E extends Comparable<E>> {
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
    public void setLeftOrThreadedChild(BinaryTreeNode<E> leftOrThreadedChild) {
        this.leftOrThreadedChild = leftOrThreadedChild;
    }
    public BinaryTreeNode<E> getLeftOrThreadedChild() {return this.leftOrThreadedChild;}
    //* Right Child
    public void setRightOrThreadedChild(BinaryTreeNode<E> rightOrThreadedChild) {
        this.rightOrThreadedChild = rightOrThreadedChild;
    }
    public BinaryTreeNode<E> getRightOrThreadedChild(){return this.rightOrThreadedChild;}

}

