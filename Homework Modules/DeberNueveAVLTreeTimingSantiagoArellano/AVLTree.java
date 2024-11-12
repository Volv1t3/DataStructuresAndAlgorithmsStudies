package DeberNueveAVLTreeTimingSantiagoArellano;

import java.util.*;
import java.util.function.Function;

/**
 * @Author: Santiago Arellano
 * @Date: Noviembre 11, 2024
 * @Description: El presente archivo muestra la implementacion de un arbol AVL basado en la implementacion del libro
 * Introduction to Java Programming con las especificaciones tecnicas del libro A Textbook For Data Structures And Algorith,s
 * @param <E> Tipo de dato a almacenar en el arbol
 */
public class AVLTree<E extends Comparable<E>> {

    //! Let us define the internal variables required
    private Integer eTreeSize = 0;
    private AVLTreeNode<E> eRoot;

    //! Let us define constructors, just as in the BinaryTree<E> Case
    public AVLTree(E  toHeadElement) throws IllegalArgumentException{
        if (toHeadElement != null){
            this.setInternalRoot(new AVLTreeNode<>(toHeadElement));
            this.eTreeSize++;
        }
        else{
            throw new IllegalArgumentException("Error Code 0x001 - [Raised] Value passed into this function was null");
        }
    }
    public AVLTree(AVLTreeNode<E> rootToPlace) throws NullPointerException{
        this.setInternalRoot(rootToPlace);
        this.eTreeSize++;
    }
    public AVLTree(E[] elementsToAdd){
        this.addAll(Arrays.asList(elementsToAdd));
    }
    public AVLTree(AVLTreeNode<E>[] nodesToAdd){
        this.insertionOfVariousNodesForTrees(nodesToAdd);
    }
    public AVLTree(Collection<E> nodesToAdd){
        this.insertionOfVariousNodesForTrees(nodesToAdd);
    }
    public AVLTree(BinaryTree<E> treeToAdd){
        Optional<List<E>> result = treeToAdd.topBottomLeftRightBFS(treeToAdd.getEInternalRoot(), Optional::of);
        result.ifPresent(this::insertionOfVariousNodesForTrees);
    }
    public AVLTree(AVLTree<E> treeToAdd){
        Optional<List<E>> traversalResult = treeToAdd.topBottomLeftRightBFS(treeToAdd.getERoot(),Optional::of);
        traversalResult.ifPresent(this::insertionOfVariousNodesForTrees); //suppress
    }


    //? Main Internal Adding Elements Methods
    public boolean add(E elementToInsert){

        if (this.getERoot() == null){
            this.setInternalRoot(new AVLTreeNode<>(elementToInsert));
            this.eTreeSize++;
            return true;
        }

        AVLTreeNode<E> refToRoot = this.eRoot;
        AVLTreeNode<E> parentOfref = null;
        while (refToRoot != null){
            parentOfref = refToRoot;
            if (elementToInsert.compareTo(refToRoot.getInternalStoredData()) < 0){
                if (refToRoot.getLeftOrThreadedChild() == null){
                    refToRoot.setLeftOrThreadedChild(new AVLTreeNode<>(elementToInsert));
                    this.eTreeSize++;
                    break;
                }
                else{refToRoot = refToRoot.getLeftOrThreadedChild();}
            }
            else if (elementToInsert.compareTo(refToRoot.getInternalStoredData())> 0){
                if (refToRoot.getRightOrThreadedChild() == null){
                    refToRoot.setRightOrThreadedChild(new AVLTreeNode<>(elementToInsert));
                    this.eTreeSize++;
                    break;
                }
                else {refToRoot = refToRoot.getRightOrThreadedChild();}
            }
            else {
                return false;
            }
        }

        this.balancingAPath(elementToInsert);
        return true;
    }
    private void addAll(List<E> list) {
        this.insertionOfVariousNodesForTrees(list);
    }
    private void insertionOfVariousNodesForTrees(AVLTreeNode<E>[] nodesToAdd) {
        if (nodesToAdd != null){
            Integer index = 0;
            if (nodesToAdd.length >= 1 && this.eRoot == null){
                this.setInternalRoot(nodesToAdd[index++]);
            }
            for(int i = index; i < nodesToAdd.length; i++){
                this.add(nodesToAdd[i].getInternalStoredData());
            }

        }
        else {
            throw new NullPointerException("Eror Code 0001 - [Raised] The collection of nodes passed into this method" +
                    "was null.");
        }
    }
    private void insertionOfVariousNodesForTrees(Collection<E> nodesToAdd){
        if (nodesToAdd != null) {
            Integer index = 0;
            List<E> nodesToAddTwo = nodesToAdd.stream().toList();
            if (nodesToAdd.size() >= 1 && this.eRoot == null) {
                this.setInternalRoot(new AVLTreeNode<>(nodesToAddTwo.get(index++)));
            }
            for(int i = index; i < nodesToAdd.size(); i++){
                this.add(nodesToAddTwo.get(i));
            }
        }
        else {
            throw new NullPointerException("Error Code 0x0001 - [Raised] The collection of nodes passed into this method " +
                    "was null.");
        }
    }

    //? Main Internal Removing Element Methods

    public void delete(E elementToInsert){
        AVLTreeNode<E> nodeNow = this.getERoot(), nodeParent = this.getERoot();
        while (nodeNow != null){
            if (elementToInsert.compareTo(nodeNow.getInternalStoredData()) < 0){
                nodeParent = nodeNow; nodeNow = nodeNow.getLeftOrThreadedChild();
            }
            else if (elementToInsert.compareTo(nodeNow.getInternalStoredData()) > 0){
                nodeParent = nodeNow; nodeNow = nodeNow.getRightOrThreadedChild();
            }
            else {
                //! Analyze first case
                if (nodeNow.getLeftOrThreadedChild() == null
                    && nodeNow.getRightOrThreadedChild() == null){
                    if (nodeNow == this.getERoot()){
                        this.setInternalRoot(null);
                    }

                    if (nodeParent.getLeftOrThreadedChild() == nodeNow){
                        nodeParent.setLeftOrThreadedChild(null);
                    }
                    else {nodeParent.setRightOrThreadedChild(null);}
                    balancingAPath(nodeNow.getInternalStoredData());
                }
                //! Aanlyze second case
                else if (nodeNow.getLeftOrThreadedChild() == null || nodeNow.getRightOrThreadedChild() == null){
                    AVLTreeNode<E> childNode = null;
                    if (nodeNow.getLeftOrThreadedChild() != null){childNode = nodeNow.getLeftOrThreadedChild();}
                    else if (nodeNow.getRightOrThreadedChild() != null){childNode = nodeNow.getRightOrThreadedChild();}

                    if (nodeNow == this.getERoot()){
                        this.setInternalRoot(childNode);
                    }

                    if (nodeParent.getLeftOrThreadedChild() == nodeNow){
                        nodeParent.setLeftOrThreadedChild(childNode);
                    }
                    else {
                        nodeParent.setRightOrThreadedChild(childNode);
                    }
                }
                else if (nodeNow.getLeftOrThreadedChild() != null && nodeNow.getRightOrThreadedChild() != null){
                    this.deleteByMerging(nodeNow);
                }

                reBalancePath(nodeParent);
                break;
            }
        }
    }

    private void reBalancePath(AVLTreeNode<E> startNode) {
        while (startNode != null) {
            balancingAPath(startNode.getInternalStoredData());
            startNode = findParent(this.eRoot, startNode.getInternalStoredData());
        }
    }

    private AVLTreeNode<E> findParent(AVLTreeNode<E> rootNode, E data) {
        AVLTreeNode<E> parent = null;
        AVLTreeNode<E> current = rootNode;

        while (current != null && !current.getInternalStoredData().equals(data)) {
            parent = current;
            if (data.compareTo(current.getInternalStoredData()) < 0) {
                current = current.getLeftOrThreadedChild();
            } else {
                current = current.getRightOrThreadedChild();
            }
        }
        return parent;
    }
    private void deleteByMerging(AVLTreeNode<E> nodeToDelete){
        AVLTreeNode<E> leftChild = nodeToDelete.getLeftOrThreadedChild(); AVLTreeNode<E> rightChild = nodeToDelete.getRightOrThreadedChild();
        AVLTreeNode<E> rightMost = leftChild; AVLTreeNode<E> parentOfRightMost = nodeToDelete;
        while (rightMost.getRightOrThreadedChild() != null){
            parentOfRightMost = rightMost;
            rightMost = rightMost.getRightOrThreadedChild();
        }

        if (parentOfRightMost != nodeToDelete){
            parentOfRightMost.setRightOrThreadedChild(rightMost.getLeftOrThreadedChild());
            rightMost.setLeftOrThreadedChild(leftChild);
        }

        rightMost.setRightOrThreadedChild(rightChild);

        if (nodeToDelete == this.getERoot()){
            this.setInternalRoot(rightMost);
        }
        else {
            updateParentReference(nodeToDelete, rightMost);
        }
    }

    private void updateParentReference(AVLTreeNode<E> nodeNow, AVLTreeNode<E> newChild){
        AVLTreeNode<E> parent = this.getERoot();
        while (parent != null) {
            if (parent.getLeftOrThreadedChild() == nodeNow) {
                parent.setLeftOrThreadedChild(newChild);
                break;
            } else if (parent.getRightOrThreadedChild() == nodeNow) {
                parent.setRightOrThreadedChild(newChild);
                break;
            }

            int compareResult = nodeNow.getInternalStoredData().compareTo(parent.getInternalStoredData());
            parent = compareResult < 0 ? parent.getLeftOrThreadedChild() : parent.getRightOrThreadedChild();
        }
    }


    //? Main internal setters and Getters
    public void setInternalRoot(AVLTreeNode<E> rootToPlace) throws NullPointerException{
        if (rootToPlace == null){throw new NullPointerException("Error Code 0x001 - [Raised] Node passed into this function " +
                "is null.");}
        else{
            this.eRoot = rootToPlace;
            this.eTreeSize++;
        }
    }



    //? Main Balancing Method Prototypes
    private void balancingAPath(E elementToInsert){
        ArrayList<AVLTreeNode<E>> path = this.path(elementToInsert);
        for (int i = path.size() - 1; i >= 0; i--) {
            AVLTreeNode<E> A = path.get(i);
            updateHeight(A);
            AVLTreeNode<E> parentOfA = (i > 0) ? path.get(i -1) : null;

            switch (balanceFactor(A)) {
                case -2: {
                    if (balanceFactor(A.getLeftOrThreadedChild()) <= 0) {
                        balancingLLImbalance(A, parentOfA); // Perform LL rotation
                    } else {
                        balancingLRImbalance(A, parentOfA); // Perform LR rotation
                    }
                    break;
                }
                case +2: {
                    if (balanceFactor(A.getRightOrThreadedChild()) >= 0) {
                        balancingRRImbalance(A, parentOfA); // Perform RR rotation
                    } else {
                        balancingRLImbalance(A, parentOfA); // Perform RL rotation
                    }
                    break;
                }
            }
        }
    }
    private void updateHeight(AVLTreeNode<E> node) {
        if (node.getLeftOrThreadedChild() == null && node.getRightOrThreadedChild() == null) // node is a leaf
            node.setInternalLocalizedHeight(0);
        else if (node.getLeftOrThreadedChild() == null) // node has no left subtree
            node.setInternalLocalizedHeight(1 + node.getRightOrThreadedChild().getInternalLocalizedHeight());
        else if (node.getRightOrThreadedChild() == null) // node has no right subtree
            node.setInternalLocalizedHeight(1 + node.getLeftOrThreadedChild().getInternalLocalizedHeight());
        else
            node.setInternalLocalizedHeight( 1 +
                    Math.max(node.getRightOrThreadedChild().getInternalLocalizedHeight(),
                            node.getLeftOrThreadedChild().getInternalLocalizedHeight()));
    }
    private ArrayList<AVLTreeNode<E>> path(E e) {
        ArrayList<AVLTreeNode<E>> list = new ArrayList<>();
        AVLTreeNode<E> current = this.eRoot; // Start from the root

        while (current != null) {
            list.add(current); // Add the node to the list
            if (e.compareTo(current.getInternalStoredData()) < 0) {
                current = current.getLeftOrThreadedChild();
            }
            else if (e.compareTo(current.getInternalStoredData())> 0) {
                current = current.getRightOrThreadedChild();
            }
            else
                break;
        }

        return list; // Return an array list of nodes
    }
    private int balanceFactor(AVLTreeNode<E> node) {
        //! Base Case: in the case that our node is null, then we return a zero.
        if (node == null) {
            return 0;
        }

        int leftSubtreeHeight = (node.getLeftOrThreadedChild() == null) ? -1: node.getLeftOrThreadedChild()
                .getInternalLocalizedHeight();
        int rightSubtreeHeight =(node.getRightOrThreadedChild() == null) ? -1: node.getRightOrThreadedChild()
                .getInternalLocalizedHeight();

        return rightSubtreeHeight - leftSubtreeHeight;
    }
    private void balancingLLImbalance(AVLTreeNode<E> A, AVLTreeNode<E> parentOfA){
        AVLTreeNode<E> B = A.getLeftOrThreadedChild(); // A is left-heavy and B is left-heavy

        if (A == this.eRoot) {
            this.eRoot = B;
        }
        else {
            if (parentOfA.getLeftOrThreadedChild() == A) {
                parentOfA.setLeftOrThreadedChild(B);
            }
            else {
                parentOfA.setRightOrThreadedChild(B);
            }
        }

        A.setLeftOrThreadedChild(B.getRightOrThreadedChild()); // Make T2 the left subtree of A
        B.setRightOrThreadedChild(A); // Make A the left child of B
        List.of(A,B).forEach(this::updateHeight);
    }
    private void balancingRRImbalance(AVLTreeNode<E> A, AVLTreeNode<E> parentOfA){
        AVLTreeNode<E> B = A.getRightOrThreadedChild(); // A is right-heavy and B is right-heavy

        if (A == this.eRoot) {
            this.eRoot = B;
        }
        else {
            if (parentOfA.getLeftOrThreadedChild() == A) {
                parentOfA.setLeftOrThreadedChild(B);
            }
            else {
                parentOfA.setRightOrThreadedChild(B);
            }
        }

        A.setRightOrThreadedChild( B.getLeftOrThreadedChild()); // Make T2 the right subtree of A
        B.setLeftOrThreadedChild(A);
        List.of(A,B).forEach(this::updateHeight);
    }
    private void balancingLRImbalance(AVLTreeNode<E> A, AVLTreeNode<E> parentOfA){
        AVLTreeNode<E> B = A.getLeftOrThreadedChild(); // A is left-heavy
        AVLTreeNode<E> C = B.getRightOrThreadedChild(); // B is right-heavy

        if (A == this.eRoot) {
            this.eRoot = C;
        }
        else {
            if (parentOfA.getLeftOrThreadedChild() == A) {
                parentOfA.setLeftOrThreadedChild(C);
            }
            else {
                parentOfA.setRightOrThreadedChild(C);
            }
        }

        A.setLeftOrThreadedChild( C.getRightOrThreadedChild()); // Make T3 the left subtree of A
        B.setRightOrThreadedChild(C.getLeftOrThreadedChild()); // Make T2 the right subtree of B
        C.setLeftOrThreadedChild(B);
        C.setRightOrThreadedChild(A);

        // Adjust heights
        List.of(A,B,C).forEach(this::updateHeight);

    }
    private void balancingRLImbalance(AVLTreeNode<E> A, AVLTreeNode<E> parentOfA){
        AVLTreeNode<E> B = A.getRightOrThreadedChild(); // A is right-heavy
        AVLTreeNode<E> C = B.getLeftOrThreadedChild(); // B is left-heavy

        if (A == this.eRoot) {
            this.eRoot = C;
        }
        else {
            if (parentOfA.getLeftOrThreadedChild() == A) {
                parentOfA.setLeftOrThreadedChild(C);
            }
            else {
                parentOfA.setRightOrThreadedChild(C);
            }
        }

        A.setRightOrThreadedChild( C.getLeftOrThreadedChild()); // Make T2 the right subtree of A
        B.setLeftOrThreadedChild( C.getRightOrThreadedChild()); // Make T3 the left subtree of B
        C.setLeftOrThreadedChild(A);
        C.setRightOrThreadedChild(B);

        // Adjust heights
        List.of(A,B,C).forEach(this::updateHeight);
    }


    //? Generate Getter methods
    public Integer size(){
        return this.eTreeSize;
    }


    public Optional<List<E>> topBottomLeftRightBFS(AVLTreeNode<E> Root, Function<E, Optional<E>> userDefinedProcessing) {
        if (Root == null || userDefinedProcessing == null){
            throw new NullPointerException("Error Code 0x001 - [Raised] Either of the parameters passed into this function " +
                    "was null");
        }
        List<E> resultList = new ArrayList<>();
        Queue<AVLTreeNode<E>> nodeQueue = new ArrayDeque<>();
        AVLTreeNode<E> nodeTemp = Root;
        if (nodeTemp != null){
            nodeQueue.offer(nodeTemp);
            while (!nodeQueue.isEmpty()){
                nodeTemp = nodeQueue.poll();
                var result = userDefinedProcessing.apply(nodeTemp.getInternalStoredData());
                result.ifPresent(resultList::add);
                if (nodeTemp.getLeftOrThreadedChild() != null){
                    nodeQueue.offer(nodeTemp.getLeftOrThreadedChild());
                }
                if (nodeTemp.getRightOrThreadedChild() != null){
                    nodeQueue.offer(nodeTemp.getRightOrThreadedChild());
                }
            }
        }
        return (!resultList.isEmpty() ? Optional.of(resultList): Optional.empty());
    }

    public final boolean searchInBinaryTrees(BinaryTreeNode<E> Root, final E  element){
        if (Root == null || element == null){
            throw new NullPointerException("Error Code 0x001 - [Raised] Either of the inputs sent into this function were " +
                    "null.");
        }
        while (Root != null){
            if (element.compareTo(Root.getInternalStoredData()) == 0){
                return true;
            }
            else if (element.compareTo(Root.getInternalStoredData()) < 0){
                Root = Root.getLeftOrThreadedChild();
            }
            else {Root = Root.getRightOrThreadedChild();}
        }
        return false;
    }

    public final AVLTreeNode<E> getERoot(){
        return this.eRoot;
    }

    public final void clear(){
        this.eRoot = null;
        this.eTreeSize = 0;
    }
}
