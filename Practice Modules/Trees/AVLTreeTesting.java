package Trees;

import org.junit.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Optional;


public class AVLTreeTesting {

    @Test
    @DisplayName("Testing AVLTree Creation")
    public void Test(){
        AVLTree<Integer> testTree = new AVLTree<>(16);
        System.out.println("testTree.topBottomLeftRightBFS() = " + testTree.topBottomLeftRightBFS(testTree.getERoot(), Optional::of).get());
        testTree.add(17);
        System.out.println("testTree = " + testTree.topBottomLeftRightBFS(testTree.getERoot(), Optional::of).get());
        testTree.add(18);
        System.out.println("testTree.topBottomLeftRightBFS(test) = " + testTree.topBottomLeftRightBFS(testTree.getERoot(), Optional::of).get());
        var root = testTree.getERoot();
        testTree.add(14);
        System.out.println("testTree.topBottomLeftRightBFS(testTree.getERoot(), Optional::of).get( = " + testTree.topBottomLeftRightBFS(testTree.getERoot(), Optional::of).get());
        testTree.add(15);
        System.out.println("testTree.topBottomLeftRightBFS(testTree.getERoot(), Optional::of).get( = " + testTree.topBottomLeftRightBFS(testTree.getERoot(), Optional::of).get());
        testTree.delete(15);
        System.out.println("testTree.topBottomLeftRightBFS(testTree.getERoot(), Optional::of).get(= " + testTree.topBottomLeftRightBFS(testTree.getERoot(), Optional::of).get());
        testTree.add(19);
        System.out.println("testTree.topBottomLeftRightBFS(testTree.getERoot(), Optional::of).get(= " + testTree.topBottomLeftRightBFS(testTree.getERoot(), Optional::of).get());
        testTree.add(13);
        System.out.println("testTree.topBottomLeftRightBFS(testTree.getERoot(), Optional::of).get(= " + testTree.topBottomLeftRightBFS(testTree.getERoot(), Optional::of).get());
        testTree.add(12);
        System.out.println("testTree.topBottomLeftRightBFS(testTree.getERoot(), Optional::of).get(= " + testTree.topBottomLeftRightBFS(testTree.getERoot(), Optional::of).get());
        testTree.add(11);
        System.out.println("testTree.topBottomLeftRightBFS(testTree.getERoot(), Optional::of).get(= " + testTree.topBottomLeftRightBFS(testTree.getERoot(), Optional::of).get());
    }

}