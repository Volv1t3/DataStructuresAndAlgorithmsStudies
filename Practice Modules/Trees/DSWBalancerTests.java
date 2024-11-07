package Trees;

import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;


public class DSWBalancerTests {

    @Test
    @DisplayName("SimpleDSW")
    public void SimpleDSWTestOne() {
        BinaryTree<Integer> unbalancedTree = new BinaryTree<>(new Integer[]{10, 5, 12, 4, 6, 11, 13, 3, 7, 1});
        System.out.println("unbalancedTree.topBottomRightLeftBFS = " + unbalancedTree.topBottomLeftRightBFS(unbalancedTree.getEInternalRoot(), Optional::of).get());
        DSWBalancerVerTwo.balanceYourTree(unbalancedTree);
        var temp = unbalancedTree.getEInternalRoot();
        while (temp.getRightOrThreadedChild() != null) {
            System.out.println("temp.getInternalStoredData() = " + temp.getInternalStoredData());
            temp = temp.getRightOrThreadedChild();
        }
        Assertions.assertEquals("[10, 5, 12, 3, 7, 11, 13, 1, 4, 6]", unbalancedTree
                .topBottomLeftRightBFS(unbalancedTree.getEInternalRoot(), Optional::of).get().toString());
    }

    @Test
    @DisplayName("SimpleDSWTestTwo")
    public void SimpleDSWTestTwo() {
        BinaryTree<Integer> unbalancedTree = new BinaryTree<>(new Integer[]{20, 10, 30, 5, 15, 25, 35});
        System.out.println("unbalancedTree.topBottomRightLeftBFS = " + unbalancedTree.topBottomLeftRightBFS(unbalancedTree.getEInternalRoot(), Optional::of).get());
        DSWBalancerVerTwo.balanceYourTree(unbalancedTree);
        var temp = unbalancedTree.getEInternalRoot();
        while (temp.getRightOrThreadedChild() != null) {
            System.out.println("temp.getInternalStoredData() = " + temp.getInternalStoredData());
            temp = temp.getRightOrThreadedChild();
        }
        Assertions.assertEquals("[20, 10, 30, 5, 15, 25, 35]", unbalancedTree
                .topBottomLeftRightBFS(unbalancedTree.getEInternalRoot(), Optional::of).get().toString());
    }

    @Test
    @DisplayName("SimpleDSWTestThree")
    public void SimpleDSWTestThree() {
        BinaryTree<Integer> unbalancedTree = new BinaryTree<>(new Integer[]{40, 20, 60, 10, 30, 50, 70, 5, 15, 25, 35, 45, 55, 65, 75});
        System.out.println("unbalancedTree.topBottomRightLeftBFS = " + unbalancedTree.topBottomLeftRightBFS(unbalancedTree.getEInternalRoot(), Optional::of).get());
        DSWBalancerVerTwo.balanceYourTree(unbalancedTree);
        var temp = unbalancedTree.getEInternalRoot();
        while (temp.getRightOrThreadedChild() != null) {
            System.out.println("temp.getInternalStoredData() = " + temp.getInternalStoredData());
            temp = temp.getRightOrThreadedChild();
        }
        Assertions.assertEquals("[40, 20, 60, 10, 30, 50, 70, 5, 15, 25, 35, 45, 55, 65, 75]", unbalancedTree
                .topBottomLeftRightBFS(unbalancedTree.getEInternalRoot(), Optional::of).get().toString());
    }

    @Test
    @DisplayName("SimpleDSWTestFour")
    public void SimpleDSWTestFour() {
        BinaryTree<Integer> unbalancedTree = new BinaryTree<>(new Integer[]{8, 3, 10, 1, 6, 14, 4, 7, 13});
        System.out.println("unbalancedTree.topBottomRightLeftBFS = " + unbalancedTree.topBottomLeftRightBFS(unbalancedTree.getEInternalRoot(), Optional::of).get());
        DSWBalancerVerTwo.balanceYourTree(unbalancedTree);
        var temp = unbalancedTree.getEInternalRoot();
        while (temp.getRightOrThreadedChild() != null) {
            System.out.println("temp.getInternalStoredData() = " + temp.getInternalStoredData());
            temp = temp.getRightOrThreadedChild();
        }
        Assertions.assertEquals("[8, 3, 10, 1, 6, 14, 4, 7, 13]", unbalancedTree
                .topBottomLeftRightBFS(unbalancedTree.getEInternalRoot(), Optional::of).get().toString());
    }

    @Test
    @DisplayName("SimpleDSWTestFive")
    public void SimpleDSWTestFive() {
        BinaryTree<Integer> unbalancedTree = new BinaryTree<>(new Integer[]{50, 25, 75, 10, 35, 60, 85, 5, 15, 30, 45, 55, 70, 80, 90});
        System.out.println("unbalancedTree.topBottomRightLeftBFS = " + unbalancedTree.topBottomLeftRightBFS(unbalancedTree.getEInternalRoot(), Optional::of).get());
        DSWBalancerVerTwo.balanceYourTree(unbalancedTree);
        var temp = unbalancedTree.getEInternalRoot();
        while (temp.getRightOrThreadedChild() != null) {
            System.out.println("temp.getInternalStoredData() = " + temp.getInternalStoredData());
            temp = temp.getRightOrThreadedChild();
        }
        Assertions.assertEquals("[50, 25, 75, 10, 35, 60, 85, 5, 15, 30, 45, 55, 70, 80, 90]", unbalancedTree
                .topBottomLeftRightBFS(unbalancedTree.getEInternalRoot(), Optional::of).get().toString());
    }

    @Test
    @DisplayName("SimpleDSWTestSix")
    public void SimpleDSWTestSix() {
        BinaryTree<Integer> unbalancedTree = new BinaryTree<>(new Integer[]{9, 2, 3, 8, 12, 10, 14});
        System.out.println("unbalancedTree.topBottomRightLeftBFS = " + unbalancedTree.topBottomLeftRightBFS(unbalancedTree.getEInternalRoot(), Optional::of).get());
        DSWBalancerVerTwo.balanceYourTree(unbalancedTree);
        var temp = unbalancedTree.getEInternalRoot();
        while (temp.getRightOrThreadedChild() != null) {
            System.out.println("temp.getInternalStoredData() = " + temp.getInternalStoredData());
            temp = temp.getRightOrThreadedChild();
        }
        Assertions.assertEquals("[9, 3, 12, 2, 8, 10, 14]", unbalancedTree
                .topBottomLeftRightBFS(unbalancedTree.getEInternalRoot(), Optional::of).get().toString());
    }
}