package DeberOchoBinaryTreesSantiagoArellano;


import java.util.List;
import java.util.Optional;

/**
 * @Author: Santiago Arellano
 * @Date: Octubre 31, 2024
 * @Description: El presente archivo muestra los metodos para obtener los nodos hojas y no hojas de un arbol binario. Ademas
 * presenta una aproximacion a un rebalancea del arbol a traves del metodo sort heredado de la interface List incluida en la
 * interface Tree.
 */
public class BinaryTreeDriver {
    public static void main(String[] args) {
        System.out.printf("%120s\n","Test Suite Para BinaryTree<E> y sus metodos getNumberOfLeaves() getNumberOfNonLeaves()");
        BinaryTree<Integer> testTree = new BinaryTree<>(new Integer[]{1,2,3,4,5,6,7,8,9,10});
        BinaryTree<String> testTreeTwo = new BinaryTree<>(new String[]{"a","d","c","f","e","g","h","x"});
        BinaryTree<Double> testTreeThree = new BinaryTree<>(new Double[]{12.5,24.3,55.67,1.23,2.45,3.5,3.6,3.7,4.56});
        BinaryTree[] trees = new BinaryTree[]{testTree,testTreeTwo,testTreeThree};
        System.out.printf("%120s\n","Impresion de Corridas Top Bottom Left Right BFS para evaluar orden de los nodos por nivel");
        printUsingBFS(trees);
        System.out.printf("%120s\n", "Utilizando el metodo sort(Comparable c) para rebalancear los arboles");
        testTree.sort(Integer::compareTo);
        testTreeTwo.sort(String::compareTo);
        testTreeThree.sort(Double::compareTo);
        System.out.printf("%120s\n", "Impresion de Corridas Top Bottom Left Right BFS para evaluar orden de los nodos por nivel");
        printUsingBFS(trees);
        System.out.printf("%120s\n", "Impresion de los valores de Nodos Hoja de cada arbol");
        printLeafNodes(trees);
        System.out.printf("%120s\n", "Impresion de los valores de Nodos Internos de cada arbol");
        printNonLeafNodes(trees);
        System.out.printf("%120s\n", "Ingresando valores nuevos a cada arbol sin balancear el arbol");
        testTree.addAll(List.of(100,450,23,4,55,121,243,11));
        testTreeTwo.addAll(List.of("y","hello","world","print","multivariable"));
        testTreeThree.addAll(List.of(145.25,133.224,156.23,2456.3,5.55));
        System.out.printf("%120s\n", "Impresion de Corridas Top Bottom Left Right BFS para evaluar orden de los nodos por nivel");
        printUsingBFS(trees);
        System.out.printf("%120s\n", "Impresion de los valores de Nodos Hoja de cada arbol");
        printLeafNodes(trees);
        System.out.printf("%120s\n", "Impresion de los valores de Nodos Internos de cada arbol");
        printNonLeafNodes(trees);
        System.out.printf("%120s\n", "Utilizando el metodo sort(Comparable c) para rebalancear los arboles");
        testTree.sort(Integer::compareTo);
        testTreeTwo.sort(String::compareTo);
        testTreeThree.sort(Double::compareTo);
        printUsingBFS(trees);
        System.out.printf("%120s\n", "Impresion de los valores de Nodos Hoja de cada arbol");
        printLeafNodes(trees);
        System.out.printf("%120s\n", "Impresion de los valores de Nodos Internos de cada arbol");
        printNonLeafNodes(trees);

    }
    private static <E extends Comparable<E>> void printUsingBFS(BinaryTree<E>[] trees){
        for(BinaryTree<E> tree: trees){
            System.out.println("BFS de arbol de tipo [" + tree.getFirst().getClass().getTypeName() + "] = " +
                    tree.topBottomLeftRightBFS(tree.getEInternalRoot(), Optional::of).get());
        }
    }
    private static <E extends Comparable<E>> void printLeafNodes(BinaryTree<E>[] trees){
        for(BinaryTree<E> tree: trees){
            System.out.println("Nodos Hoja de arbol de tipo [" + tree.getFirst().getClass().getTypeName() + "] = " +
                    tree.getNumberOfLeaves());
        }
    }
    private static <E extends Comparable<E>> void printNonLeafNodes(BinaryTree<E>[] trees){
        for (BinaryTree<E> tree:trees) {
            System.out.println("Nodos Internos de arbol de tipo [" + tree.getFirst().getClass().getTypeName() + "] = " +
                    tree.getNumberOfNonLeaves());
        }
    }
}

