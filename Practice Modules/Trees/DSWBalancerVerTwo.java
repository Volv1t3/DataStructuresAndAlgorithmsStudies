package Trees;

/**
 * @author : Santiago Arellano (With Help of Online Sources of Course)
 * @Date: November 5th, 2024
 * @Description: The present static helper class presents a single entry point method designed to allow the user to balance
 * their own tree based on the <b>DSW algorithm defined in my Tree Document</b>, the idea behind it is that it first grabs
 * the tree and it forces it down to a single right skewed linked-list which is then decompressed into a sorted tree
 * using rotations (to the right to compress, and to the left to decompress). The method has been tested in another file
 * <code>DSWBalancerTests</code> where all the tests logically pass, some inconsistencies with Online sources answers have been found
 * however the main logic and balancing remains and has shown itself to work properly.
 * <br><br>
 * <p>To accomplish this implementation, the file <code>DSWBalancer</code> was devised and through iterations and outside
 * sources its code was updated to provide a more mathematically approximate method which produces the overall balanced
 * tree.</p>
 * <br>
 * <br>
 * <p>The sources used were</p>
 * <ul>
 *     <li><a href="https://dl.acm.org/doi/pdf/10.1145/358105.358191">Efficient Algorithms to Globally Balance a Binary Search Tree</a></li>
 *      <li><a href="https://research.cs.wisc.edu/wpis/papers/sas06-dsw.pdf>Automated Verification of the Deutsch-Schorr-Waite  Tree-Traversal Algorithm</a></li>
 *      <li><a href="https://www.geeksforgeeks.org/day-stout-warren-algorithm-to-balance-given-binary-search-tree/">Day-Stout-Warren algorithm to balance given Binary Search Tree</a></li>
 *      <li><a href="https://xuyuanguo.wordpress.com/2013/02/06/dsw-algorithm-balancing-binary-search-tree/">DSW algorithm (balancing binary search tree) | Guo, Xuyuan's Blog</a></li>
 *      <li><a href="http://www.smunlisted.com/day-stout-warren-dsw-algorithm.html">The Day-Stout-Warren (DSW) Algorithm</a></li>
 * </ul>
 */
public class DSWBalancerVerTwo {
        public static <E extends Comparable<E>> void balanceYourTree(BinaryTree<E> externalTree) {
            if (externalTree == null || externalTree.getEInternalRoot() == null) {
                return;
            }

            // Create dummy root for consistent handling
            BinaryTreeNode<E> dummyRoot = new BinaryTreeNode<>();
            dummyRoot.setRightOrThreadedChild(externalTree.getEInternalRoot());

            // Create the initial vine (backbone)
            int nodeCount = createBackbone(dummyRoot);

            // Calculate perfect tree height and nodes
            int h = (int)(Math.log(nodeCount + 1) / Math.log(2));
            int m = (int)Math.pow(2, h) - 1;

            // Perform the initial compression for excess nodes
            compress(dummyRoot, nodeCount - m);

            // Perform the remaining compressions
            for (m = m / 2; m > 0; m /= 2) {
                compress(dummyRoot, m);
            }

            // Update the tree's root
            externalTree.setInternalRoot(dummyRoot.getRightOrThreadedChild());
        }

        private static <E extends Comparable<E>> int createBackbone(BinaryTreeNode<E> dummyRoot) {
            int count = 0;
            BinaryTreeNode<E> current = dummyRoot.getRightOrThreadedChild();
            BinaryTreeNode<E> parent = dummyRoot;

            while (current != null) {
                if (current.getLeftOrThreadedChild() == null) {
                    // Move to next node if no left child
                    count++;
                    parent = current;
                    current = current.getRightOrThreadedChild();
                } else {
                    // Perform right rotation
                    BinaryTreeNode<E> leftChild = current.getLeftOrThreadedChild();
                    current.setLeftOrThreadedChild(leftChild.getRightOrThreadedChild());
                    leftChild.setRightOrThreadedChild(current);
                    parent.setRightOrThreadedChild(leftChild);
                    current = leftChild;
                }
            }
            return count;
        }

        private static <E extends Comparable<E>> void compress(BinaryTreeNode<E> dummyRoot, int count) {
            BinaryTreeNode<E> scanner = dummyRoot;

            for (int i = 0; i < count; i++) {
                BinaryTreeNode<E> child = scanner.getRightOrThreadedChild();
                BinaryTreeNode<E> grandChild = child.getRightOrThreadedChild();

                scanner.setRightOrThreadedChild(grandChild);
                child.setRightOrThreadedChild(grandChild.getLeftOrThreadedChild());
                grandChild.setLeftOrThreadedChild(child);
                scanner = grandChild;
            }
        }

        // Helper method to calculate log base 2
        private static int log2(int n) {
            return (int)(Math.log(n) / Math.log(2));
        }
    }
