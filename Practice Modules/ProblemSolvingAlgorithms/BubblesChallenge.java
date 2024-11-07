package ProblemSolvingAlgorithms;

import java.lang.reflect.Array;
import java.util.*;
import java.util.HashSet;

public class BubblesChallenge {
    public static void main(String[] args) {
        /*
         *  The idea behind this is that we are given an input string which on the first line tells us the number of cases.
         *  After these, they will tell us the integers that are the number of vertices and edges we have (this can be
         *  tentatively an incidencia matrix, we can find loops through dynamic programming.
         *
         *  The idea is that then we will have a pair of integers, which shows connections between vertices.
         * Now the thing is creating the incidency matrix.
         */

        Scanner scanner = new Scanner(System.in);
        int totalCases = scanner.nextInt();
        ;
        //! Fill it up
        //! Register Values
        for (int cases = 0; cases < totalCases; cases++) {
            int totalVertices = scanner.nextInt();
            int totalEdges = scanner.nextInt();
            Integer[][] incidenceMatrix = new Integer[totalVertices][totalVertices];
            for (int row = 0; row < totalVertices; row++) {
                for (int column = 0; column < totalVertices; column++) {
                    incidenceMatrix[row][column] = 0;
                }
            }
            //Read integers
            scanner.nextLine();
            boolean hasImmediateCycle = false;
            Integer[] arrConnections = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
            for (int i = 0; i < arrConnections.length; i += 2) {
                int verticeRow = arrConnections[i];
                int verticeColumn = arrConnections[i + 1];
                if (verticeRow == verticeColumn){
                    hasImmediateCycle = true;
                }
                incidenceMatrix[verticeRow][verticeColumn]++;
                incidenceMatrix[verticeColumn][verticeRow]++;
            }

            if (hasImmediateCycle){
                System.out.println(1);
            }
            else{
                /*
                 * Call external function to determine these relationships here
                 */
                System.out.println(determineCycles(incidenceMatrix));
            }
        }
    }

    private static Integer determineCycles(Integer[][] dpMatrix) {
        /*
         * Our method will work based on column analysis, if the node exists and we see that in a single column we are getting
         * a pair of indices then we check  (since these nodes are double connected) that there should be the transitive property
         * between them. If this transitive property exists then we should use that to assert that they are connected, if
         * for any case we notice that it does not exist then we should immediately return 0. Otherwise we keep analyzing
         * until we find all possible connections.
         *
         */
        int n = dpMatrix.length;
//        System.out.println("n = " + n);
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            visited.clear();
            if (isCyclic(dpMatrix, i, visited, -1)) {
                return 1;
            }
        }
        return 0;
    }// missing return
    private static boolean isCyclic(Integer[][] dpMatrix, int node, Set<Integer> visited, int parent) {
        visited.add(node);
        for (int neighbor = 0; neighbor < dpMatrix.length; neighbor++) {
            if (dpMatrix[node][neighbor] == 1) {
                if (visited.contains(neighbor) && neighbor != parent) {
                    return true;
                }
                if (!visited.contains(neighbor) &&
                        isCyclic(dpMatrix, neighbor, visited, node)){
                    return true;
                }
            }
        }
        return false;
    }// missing return (Set<Integer
}
