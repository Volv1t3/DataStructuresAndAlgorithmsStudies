package Trees;

import java.util.Optional;

public class BinaryTreeTesting {

    public static void main(String[] args) {
     testingConstructorsTwo();
    }
    public static void testingConstructorsTwo(){
        BinaryTree<Integer> tree = new BinaryTree<>(new Integer[]{20,15,10,8,11,17,16,25,24,27});
        System.out.println(tree.topBottomLeftRightBFS(tree.getEInternalRoot(), Optional::of).get());
        tree.deleteNode(27);
        System.out.println(tree.topBottomLeftRightBFS(tree.getEInternalRoot(), Optional::of).get());
        tree.deleteNode(16); //! Extremma works
        System.out.println(tree.topBottomLeftRightBFS(tree.getEInternalRoot(), Optional::of).get());
        tree.deleteNode(25);
        System.out.println(tree.topBottomLeftRightBFS(tree.getEInternalRoot(), Optional::of).get());
        tree.deleteNode(10);
        System.out.println(tree.topBottomLeftRightBFS(tree.getEInternalRoot(), Optional::of).get());
        BinaryTree<Integer> tree1 = new BinaryTree<>(new Integer[]{10,5,4,3,2,1,6,11,13,12});
        tree1.inorderRecursive(tree1.getEInternalRoot());
       System.out.println( tree1.inorderIterative(tree1.getEInternalRoot(), Optional::of).get());
    }
}
