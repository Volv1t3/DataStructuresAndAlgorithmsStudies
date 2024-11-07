package Trees;

import java.util.Optional;

public class DSWBalancer {

    public static <E extends Comparable<E>> void balanceYourTree(BinaryTree<E> externalTree) {
        if (externalTree == null || externalTree.getEInternalRoot() == null) {
            return; //could be an exception here too
        }

        //! Utilize an external method to first create the backbone
        createBackbone(externalTree);
        var root = externalTree.getEInternalRoot();
        while (root != null){
            System.out.println("root.getInternalStoredData() (Only Moving After BackboneCreation [Right Node])= " + root.getInternalStoredData());
            root = root.getRightOrThreadedChild();
        }
        //! Call an extenral method to then balance the backbone
        balanceBackbone(externalTree);
    }

    private static <E extends Comparable<E>> void createBackbone(BinaryTree<E> externalTree) {
        BinaryTreeNode<E> grandParent = null;
        BinaryTreeNode<E> temporal = externalTree.getEInternalRoot();

        while (temporal != null) {
            if (temporal.getLeftOrThreadedChild() == null) {
                grandParent = temporal;
                temporal = temporal.getRightOrThreadedChild();
            } else {
                //! In this point we perform a right rotation
                BinaryTreeNode<E> leftChild = temporal.getLeftOrThreadedChild();
                helper_rotateNodesRight(grandParent, temporal, leftChild, externalTree);
                temporal = leftChild;
            }
        }
    }

    private static <E extends Comparable<E>> void helper_rotateNodesRight(BinaryTreeNode<E> grandParent,
                                                                          BinaryTreeNode<E> temporalParent,
                                                                          BinaryTreeNode<E> leftChild,
                                                                          BinaryTree<E> internalTree){
        if (temporalParent == null || leftChild == null){
            throw new IllegalStateException("Parent and child nodes must not be null");
        }

        if (grandParent != null){
            if (grandParent.getLeftOrThreadedChild() == temporalParent){
                grandParent.setLeftOrThreadedChild(leftChild);
            }
            else if (grandParent.getRightOrThreadedChild() == temporalParent){
                grandParent.setRightOrThreadedChild(leftChild);
            }
        }
        else {
            internalTree.setInternalRoot(leftChild);
        }

        BinaryTreeNode<E> leftChildRightChild = leftChild.getRightOrThreadedChild();
        leftChild.setRightOrThreadedChild(temporalParent);
        temporalParent.setLeftOrThreadedChild(leftChildRightChild);
    }

    private static <E extends Comparable<E>> void balanceBackbone(BinaryTree<E> externalTree){

        int numberOfNodes = externalTree.size();
        System.out.println("numberOfNodes = " + numberOfNodes);
        int perfectLeaveCount = helper_getPerfectTreeLeaveCount(numberOfNodes);
        System.out.println("perfectLeaveCount = " + perfectLeaveCount);
        //! Calculating the real amount of operations to do based on the difference between the perfectLeave count and
        //! number of nodes
        int totalOperations = numberOfNodes - perfectLeaveCount;
        helper_makeRotations(externalTree, totalOperations);
        //! Perform the rest of the rotations for those perfet leaves
        while(perfectLeaveCount > 1){
            perfectLeaveCount /=2;
            helper_makeRotations(externalTree, perfectLeaveCount);
        }
    }

    private static <E extends Comparable<E>> void helper_makeRotations(BinaryTree<E> internalTree,
                                                                       int totalOperations){
        BinaryTreeNode<E> grandParent = null;
        BinaryTreeNode<E> temporalParent = internalTree.getEInternalRoot();

        while (totalOperations > 0 && temporalParent != null){
            BinaryTreeNode<E> childNode = temporalParent.getRightOrThreadedChild();
            if (childNode == null){
                break; //! This case indicates we have reached the end of our backbone.
            }

            //! The following will perform the LEFT rotation to make the tree come back into its place
            helper_rotateNodesLeft(grandParent, temporalParent, childNode, internalTree);

            //! Updating references
            grandParent = temporalParent;
            temporalParent = temporalParent.getRightOrThreadedChild();
            totalOperations--;
        }
    }


    private static <E extends Comparable<E>> void helper_rotateNodesLeft(BinaryTreeNode<E> grandParent,
                                                                         BinaryTreeNode<E> temporalParent,
                                                                         BinaryTreeNode<E> rightChild,
                                                                         BinaryTree<E> internalTree) {
        if (temporalParent == null || rightChild == null) {
            throw new IllegalStateException("Parent and child nodes must not be null");
        }

        BinaryTreeNode<E> rightChildLeft = rightChild.getLeftOrThreadedChild();

        // Step 1: Make T2 the left subtree of A
        temporalParent.setRightOrThreadedChild(rightChildLeft);

        // Step 2: Make A the right child of B
        rightChild.setLeftOrThreadedChild(temporalParent);

        // Step 3: Update the grandparent's reference
        if (grandParent != null) {
            if (grandParent.getRightOrThreadedChild() == temporalParent) {
                grandParent.setRightOrThreadedChild(rightChild);
            } else {
                grandParent.setLeftOrThreadedChild(rightChild);
            }
        } else {
            // Update tree root if no grandParent
            internalTree.setInternalRoot(rightChild);
        }
    }
    private static <E extends Comparable<E>> Integer helper_getPerfectTreeLeaveCount(int numberOfNodes){
        int perfectCount = 1;
        while (perfectCount < numberOfNodes){
            perfectCount *= 2;
        }
        return perfectCount / 2;
    }
}
