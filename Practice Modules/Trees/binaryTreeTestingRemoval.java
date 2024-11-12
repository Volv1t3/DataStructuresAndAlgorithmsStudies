package Trees;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
@DisplayName("Removal Through Delete By Copying")
public class binaryTreeTestingRemoval {

    @Test
    @DisplayName("Test Case 1: Delete leaf node 35")
    void testCase1() {
        assertEquals("[20, 15, 30, 10, 18, 25]", performDeletion(new Integer[]{20, 15, 30, 10, 18, 25, 35}, 35));
    }

    @Test
    @DisplayName("Test Case 2: Delete root node 20")
    void testCase2() {
        assertEquals("[18, 15, 25, 10, 24, 26]", performDeletion(new Integer[]{20, 15, 25, 10, 18, 24, 26}, 20));
    }

    @Test
    @DisplayName("Test Case 3: Delete node 15 with two children")
    void testCase3() {
        assertEquals("[20, 10, 25, 5, 17, 24, 27]", performDeletion(new Integer[]{20, 15, 25, 10, 5, 17, 24, 27}, 15));
    }

    @Test
    @DisplayName("Test Case 4: Delete node 25 with one child")
    void testCase4() {
        assertEquals("[20, 22, 30, 28, 35, 40]", performDeletion(new Integer[]{20, 25, 30, 22, 28, 35, 40}, 25));
    }

    @Test
    @DisplayName("Test Case 5: Delete root node 10")
    void testCase5() {
        assertEquals("[7, 5, 15, 2, 12, 20]", performDeletion(new Integer[]{10, 5, 15, 2, 7, 12,20}, 10));
    }

    @Test
    @DisplayName("Test Case 6: Delete node 30 with two children")
    void testCase6() {
        assertEquals("[50, 20, 70, 40, 60, 80]", performDeletion(new Integer[]{50, 30, 70, 20, 40, 60, 80}, 30));
    }

    @Test
    @DisplayName("Test Case 7: Delete node 5 with two children")
    void testCase7() {
        assertEquals("[10, 1, 15, 8]", performDeletion(new Integer[]{10, 5, 15, 1, 8}, 5));
    }

    @Test
    @DisplayName("Test Case 8: Delete leaf node 25")
    void testCase8() {
        assertEquals("[30, 20, 40, 10, 35, 50]", performDeletion(new Integer[]{30, 20, 40, 10, 25, 35, 50}, 25));
    }

    @Test
    @DisplayName("Test Case 9: Delete node 20 with two children")
    void testCase9() {
        assertEquals("[40, 15, 60, 10, 30, 50, 70, 5, 25, 35, 45, 55]",
                performDeletion(new Integer[]{40, 20, 60, 10, 30, 50, 70, 5, 15, 25, 35, 45, 55}, 20));
    }

    @Test
    @DisplayName("Test Case 10: Delete node 150 with two children")
    void testCase10() {
        assertEquals("[100, 50, 125, 25, 75, 175]", performDeletion(new Integer[]{100, 50, 150, 25, 75, 125, 175}, 150));
    }

    @Test
    @DisplayName("Test Case 11: Delete node 11")
    void testCase11(){
        assertEquals("[10, 5, 12, 2, 13, 1, 3, 14, 4]", performDeletion(new Integer[]{10,5,11,2,1,3,4,12,13,14}, 11));
    }
    private String performDeletion(Integer[] elements, int deleteElement) {
        BinaryTree<Integer> tree = new BinaryTree<>(elements);
        tree.deleteNode(deleteElement);
        return tree.topBottomLeftRightBFS(tree.getEInternalRoot(), Optional::of).get().toString();
    }
}
