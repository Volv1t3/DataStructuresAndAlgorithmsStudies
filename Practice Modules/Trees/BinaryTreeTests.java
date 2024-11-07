package Trees;

import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import java.util.*;
import java.util.List;
import java.util.function.Function;

@Category(BinaryTree.class)
public class BinaryTreeTests {

    @Test
    @DisplayName(value = "Test #1 - Testing For Single Value Constructors | Comparing length of tree")
    @Order(1)
    public void testingConstructors(){
        final Integer EXPECTED_LENGTH  = 1;
        System.out.println("Creating a single Binary Tree of Type Integer using Element adding constructor");
        Assertions.assertEquals(EXPECTED_LENGTH, new BinaryTree<Integer>(10).size());
        System.out.println("Creating a single Binary Tree of Type Integer using Node adding constructor");
        Assertions.assertEquals(EXPECTED_LENGTH, new BinaryTree<Integer>(
                new BinaryTreeNode<>(10)).size());
        System.out.println("Creating a single Binary Tree of Type Integer forcing IllegalArgumentException for incorrect value");
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new BinaryTree<Integer>( (Integer) null ));
        System.out.println("Creating a single Binary Tree of Type Integer forcing NullPointerException for incorrect value");
        Assertions.assertThrows(NullPointerException.class, () -> new BinaryTree<Integer>((BinaryTreeNode<Integer>)  null));
    }


    @Test
    @DisplayName("Test #2 - Testing for Variable Args Constructors | Using all multi-value constructors (Collection<E>, E[]," +
            "BinaryTreeNode<E>[], BinaryTree<E> ")
    @Order(2)
    @Tag("Constructors")
    public void testingConstructorsTwo(){
        final Integer EXPECTED_LENGTH = 10;
        System.out.println("Creating a single Binary Tree of Type Integer using multi-value E[] constructor");
        BinaryTree<Integer> test = new BinaryTree<Integer>(new Integer[]{10,4,2,5,6,18});
        test.inorderRecursive(test.getEInternalRoot());
        System.out.println("Creating a single Binary Tree of Type Integer using multi-value BianryTreeNode<E>[] constructor");
        test.clear();
        test = new BinaryTree<>(new BinaryTreeNode[]{new BinaryTreeNode<>(10), new BinaryTreeNode<>(4),
        new BinaryTreeNode<>(2), new BinaryTreeNode<>(4), new BinaryTreeNode(5)});
        test.inorderRecursive(test.getEInternalRoot());
        System.out.println("Creating a single Binary Tree of Type Integer using BinaryTree Copy Constructor");
        BinaryTree<Integer> copyTest  =new BinaryTree<>(test);
        copyTest.inorderRecursive(copyTest.getEInternalRoot());
    }

    @Test
    @DisplayName("Test #3 - Testing Traversal Methods | Recursive and Iterative DFS Methods")
    @Tag("TraversalMethods")
    @Order(3)
    public void testingTraversalMethods(){
        System.out.println("Printing Results For Recursive Inorder");
        BinaryTree<Integer> test = new BinaryTree<Integer>(new Integer[]{10,4,2,5,6,18});
        test.inorderRecursive(test.getEInternalRoot());
        System.out.println("Printing Results for Iterative Inorder");
        var result = test.inorderIterative(test.getEInternalRoot(), Optional::of);
        Assertions.assertEquals("[2, 4, 5, 6, 10, 18]", result.get().toString());
        System.out.println("result = " + result.get().toString());
        System.out.println("Printing Results For Recursive Preorder");
        test.preorderRecursive(test.getEInternalRoot());
        System.out.println("Printing Results for Iterative Preorder");
        result = test.preorderIterative(test.getEInternalRoot(), Optional::of);
        Assertions.assertEquals("[10, 4, 2, 5, 6, 18]", result.get().toString());
        System.out.println("result = " + result.get().toString());
        System.out.println("Printing Results for Recursive PostOrder");
        test.postorderRecursive(test.getEInternalRoot());
        result = test.postorderIterative(test.getEInternalRoot(), Optional::of);
        Assertions.assertEquals("[2, 6, 5, 4, 18, 10]", result.get().toString());
        System.out.println("result = " + result.get().toString());
    }

    @Test
    @DisplayName("Test #4 - Testing Traversal Methods | Iterative BFS Methods")
    @Tag("TraversalMethods")
    @Order(4)
    public void testingTraversalMethods2(){
        System.out.println("Printing Results for Iterative Top Bottom Left Right BFS");
        BinaryTree<Integer> test = new BinaryTree<Integer>(new BinaryTreeNode[]{new BinaryTreeNode<>(10), new BinaryTreeNode<>(4),
                new BinaryTreeNode<>(2), new BinaryTreeNode<>(4), new BinaryTreeNode<>(5), new BinaryTreeNode<>(45)
        , new BinaryTreeNode<>(18)});
        var result = test.topBottomLeftRightBFS(test.getEInternalRoot(), new Function<Integer, Optional<Integer>>() {
            @Override
            public Optional<Integer> apply(Integer integer) {
                System.out.println("Value at analysis level  = " + integer);
                return Optional.empty();
            }
        });
        result = test.topBottomLeftRightBFS(test.getEInternalRoot(), Optional::of);
        Assertions.assertEquals("[10, 4, 45, 2, 5, 18]", result.get().toString());
        System.out.println("Printing Results for Iterative Top Bottom Right Left BFS");
        test.topBottomRightLeftBFS(test.getEInternalRoot(), (integer) -> {
            System.out.println("Value at analysis level = " + integer);
            return Optional.empty();
        });
        result = test.topBottomRightLeftBFS(test.getEInternalRoot(), Optional::of );
        Assertions.assertEquals("[10, 45, 4, 18, 5, 2]", result.get().toString());
        System.out.println("Printing Results for Iterative Bottom Top Left Right BFS");
        test.bottomTopLeftRightBFS(test.getEInternalRoot(), (integer) -> {
            System.out.println("Value at analysis level = " + integer);
            return Optional.empty();
        });
        result = test.bottomTopLeftRightBFS(test.getEInternalRoot(), Optional::of);
        Assertions.assertEquals("[2, 5, 18, 4, 45, 10]", result.get().toString());
        System.out.println("Printing Results for Iterative Bottom Top Right Left BFS");
        test.bottomTopRightLeftBFS(test.getEInternalRoot(), (integer) -> {
            System.out.println("value at analysis level = " + integer);
            return Optional.empty();
        });
        result = test.bottomTopRightLeftBFS(test.getEInternalRoot(), Optional::of);
        Assertions.assertEquals("[18, 5, 2, 45, 4, 10]", result.get().toString());
    }

    @Test
    @DisplayName("Test #5 - Testing Node Insertion | Usage of generic insertion method (By extension Add), addFirst and addLast")
    @Tag("NodeInsertion")
    @Order(5)
    public void testingInsertionMethods(){
        System.out.println("Testing usage of generic method for insertion of a node in a binary tree");
        BinaryTree<Integer> test = new BinaryTree<Integer>(new BinaryTreeNode[]{new BinaryTreeNode<>(10), new BinaryTreeNode<>(4),
                new BinaryTreeNode<>(2), new BinaryTreeNode<>(4), new BinaryTreeNode<>(5), new BinaryTreeNode<>(45)
                , new BinaryTreeNode<>(18)});
        Integer prevSizeCounter = test.size();
        test.insertionForBinaryTree(1);
        Assertions.assertEquals(6, prevSizeCounter = test.size()); // Initially we had five items, then we added 1.
        //? Second test, check if it throws when you try to add something null
        System.out.println("Testing usage of generic method by forcing a throw exception case, code should continue underneath if " +
                "test passed");
        Assertions.assertThrows(NullPointerException.class, () -> test.insertionForBinaryTree((Integer) null));
        //? Third test, check if the value already exists the length does not change
        System.out.println("Testing usage of generic method by forcing an already present item to be added");
        test.insertionForBinaryTree(18);
        Assertions.assertEquals(6, prevSizeCounter);
        //? Given that add(E element) internally calls upon the previous method, its tests are omitted

        //! Checking addFIrst method
        System.out.println("Testing usage of addFirst(E element) method  by sending first a random value");
        test.addFirst(25);
        System.out.println("Performing an analysis in such a way that we shall see if the root node now becomes the sent one in (i.e. 25)");
        Optional<List<Integer>> result = test.topBottomLeftRightBFS(test.getEInternalRoot(), Optional::of);
        Assertions.assertEquals("[25, 10, 45, 4, 18, 2, 5, 1]", result.get().toString());
        System.out.println("Testing usage of addFirst(E element) method by forcing throw exception case, code should continue underneath it");
        Assertions.assertThrows(NullPointerException.class, () -> test.addFirst((Integer) null));
        System.out.println("Testing usage of addFirst(E element) method by forcing an already present item to be added");
        Assertions.assertEquals(7, test.size());

        //! Checking addLast method
        System.out.println("Testing usage of addLast(E element) method by sending first a random value");
        test.addLast(35);
        Assertions.assertEquals(8, test.size());
        System.out.println("Testing usage of addLast(E element) method by sending a value that ");
        Assertions.assertThrows(NullPointerException.class, () -> test.addLast((Integer) null));
    }
    @Test
    @DisplayName("Test $6 - Testing Node Insertion | Usage of add(int index)")
    @Tag("NodeInsertion")
    @Order(6)
    public void testingInsertionMethods2(){
        BinaryTree<Integer> test = new BinaryTree<>(new Integer[]{10,2,4,5,2,6,7,20});
        System.out.println("test = " + test.topBottomLeftRightBFS(test.getEInternalRoot(), Optional::of).get().toString());
        System.out.println("Testing usage of List inherited method for adding onto an index");
        //? First Test to add a single element and Assert lenth
        test.add(4, 120);
        System.out.println("test = " + test.topBottomLeftRightBFS(test.getEInternalRoot(), Optional::of).get().toString());
        Assertions.assertEquals(8,test.size() );
        //?Second test: Take a look at how insertion works when we are sending an illegal index
        System.out.println("Testing usage of List inherited method for adding onto an index by forcing an illegal index, if method correctly handles and throws an exception, \n" +
                "code should continue underneath");
        Assertions.assertThrows(IllegalArgumentException.class, () -> test.add(24000, 1));
        //? Third Test: Take a look at how insertion works when we are sending an illegal valu
        System.out.println("Testing usage of List inherited method for adding onto an index by forcing an illegal value, if method correctly handles and throws an exception, \n" +
                "code should continue underneath");
        Assertions.assertThrows(NullPointerException.class, () -> test.add(1, null));
    }

    @Test
    @DisplayName("Test #7 - Testing Node Insertion | Using methods like addAll")
    @Order(7)
    public void testingInsertionMethods3(){
        BinaryTree<Integer> test = new BinaryTree<>(new Integer[]{10,2,4,5,2,6,7,20});
        System.out.println("test = " + test.topBottomLeftRightBFS(test.getEInternalRoot(), Optional::of).get().toString());
        System.out.println("Testing addAll with a simple range of values");
        test.addAll(new ArrayList<>(){{add(21);add(15);add(2);add(5);add(8);}});
        System.out.println("test = " + test.topBottomLeftRightBFS(test.getEInternalRoot(), Optional::of).get().toString());
        Assertions.assertEquals(10, test.size());
        System.out.println("Testing addAll with a nullish value for reliability, if it throws code should continue");
        Assertions.assertThrows(NullPointerException.class, () -> test.addAll((ArrayList<Integer>) null));
        System.out.println("Testing addALl with an empty range, it should throw an exception, if it throws code should continue");
        Assertions.assertThrows(IllegalArgumentException.class, () -> test.addAll(new ArrayList<>(){}));

        //! Second testing section: addAll with a range
        test.clear();
        test.addAll(new ArrayList<>(){{add(21);add(15);add(2);add(5);add(8);}});
        System.out.println("test = " + test.topBottomLeftRightBFS(test.getEInternalRoot(), Optional::of).get().toString());
        System.out.println("Testing addALl with a range of values given a substring delimited by an index");
        test.addAll(100, new ArrayList<>(){{add(450); add(1000); add(440);}});
        Assertions.assertEquals(8, test.size());
        System.out.println("test = " + test.topBottomLeftRightBFS(test.getEInternalRoot(), Optional::of).get().toString());
    }

    @Test
    @DisplayName("Test #8 - Testing Node Lookup | Using search(), contains(), containsAll(), retainAll()")
    @Tag("NodeLookup")
    @Order(8)
    public void testingNodeLookUp(){
        //! First batch of internal tests, using the basic search method to find a given object.
        System.out.println("Testing basic search method on a given node to find the value 2 [If result is true then code should continue underneath");
        BinaryTree<Integer> test = new BinaryTree<>(new Integer[]{10,2,4,5,2,6,7,20});
        Assertions.assertTrue(test.searchInBinaryTrees(test.getEInternalRoot(), 2));
        System.out.println("Testing basic search method on a given node to find a nonexistent value [Code should continue once false is returned");
        Assertions.assertFalse(test.searchInBinaryTrees(test.getEInternalRoot(), 1400));
        System.out.println("Testing basic search method on a null root node [Will throw an exception, if correct code should continue underneath");
        Assertions.assertThrows(NullPointerException.class,  () -> test.searchInBinaryTrees((BinaryTreeNode<Integer>) null, 1200));
        System.out.println("Testing basic search method on a null Integer value [Will throw an exception, if correct code should continue underneath");
        Assertions.assertThrows(NullPointerException.class, () -> test.searchInBinaryTrees(test.getEInternalRoot(), (Integer) null));

        //! Second batch of tests: using the contains method)
        System.out.println("Testing contains() method to find a given value in the tree, to force an exception to be thrown and a null value");
        Assertions.assertTrue(test.contains(2));
        System.out.println("Testing contains by forcing a bad cast exception, if thrown then code should continue underneath");
        Assertions.assertThrows(ClassCastException.class, () -> test.contains("hello"));
        System.out.println("Testing contains by forcing a null value input, if thrown then code should continue underneath");
        Assertions.assertThrows(NullPointerException.class, () -> test.contains((String) null));

        //! Third batch of tests: using containsAll()
        System.out.println("Testing containsAll() method to find a given set of values in the tree");
        Assertions.assertTrue(test.containsAll(List.of(20,7,5,2)));
        System.out.println("Testing containsALl() by sending a value that is not inside the tree");
        Assertions.assertFalse(test.containsAll(List.of(27)));
        System.out.println("Testing containsAll() by sending a class cast exception Collection");
        Assertions.assertThrows(ClassCastException.class, () -> test.containsAll(List.of("String1", "String2")));
        System.out.println("Testing containsAll() by sending a null collection");
        Assertions.assertThrows(NullPointerException.class, () -> test.containsAll((Collection<Integer>) null));

        //! Fourth Batch: testing retainsAll()
        System.out.println("Testing retainsAll() by giving it an input list of values inside");
        System.out.println("test.topBottomLeftRightBFS = " + test.topBottomLeftRightBFS(test.getEInternalRoot(), Optional::of).get());
        Assertions.assertTrue(test.retainAll(List.of(20,7,5,2)));
        System.out.println("test.topBottomLeftRightBFS = " + test.topBottomLeftRightBFS(test.getEInternalRoot(), Optional::of).get());
        System.out.println("Testing retainsAll() by giving it an empty list as input");
        Assertions.assertThrows(IllegalArgumentException.class, () -> test.retainAll(List.of()));
        System.out.println("Testing retainsAll() by giving it a null list of values as input");
        Assertions.assertThrows(NullPointerException.class, () -> test.retainAll((List<?>) null));
    }

    @Test
    @DisplayName("Test #9 - ListIterator | Testing methods for linear traversal and removal of values in a list")
    public void testingListIterator(){
        BinaryTree<String> tree = new BinaryTree<>(new String[]{"el","sol","la","casa","familia","espejo"});
        System.out.println("tree = " + tree.topBottomLeftRightBFS(tree.getEInternalRoot(), Optional::of).get());
        ListIterator<String> iterator = tree.listIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("iterator.hasNext() = " + iterator.hasNext());
        while (iterator.hasPrevious()){
            System.out.println(iterator.previous());
        }
        Assertions.assertThrows(NoSuchElementException.class, () ->System.out.println("iterator.previous() = " + iterator.previous()));
    }
}

