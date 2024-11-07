package ProblemSolvingAlgorithms;

import org.apache.commons.math3.genetics.Fitness;

import java.util.*;
import java.math.*;
import java.lang.*;

public class BeettleBagResolution {
    public static void main(String[] args) {
        /*
         * This is a variation of the famous Knapsack 0/1 algorithm in which the ideal solution comes in the form of a linear
         * time complexity based on tabulation. To do this tabulation, we do not involve recursion, rather we calculate
         * each of the slots for the given array based on the inputs of the program.
         */

        Scanner scanner = new Scanner(System.in);
        Integer testCases = scanner.nextInt();
        Integer maxCapacityOfBag = scanner.nextInt();
        Integer numberOfGadgetsToRead = scanner.nextInt();


        for(int count = 0; count < testCases; count++){
            ArrayList<Integer> gadgetWeights = new ArrayList<>();
            ArrayList<Integer> gadgetPowers = new ArrayList<>();

        /*
        * Now that we have defined our variables, it is time to read the input from the console*/
        for(int i = 0 ; i < numberOfGadgetsToRead; i++){
            Integer itemWeight = scanner.nextInt();
            Integer itemPower = scanner.nextInt();
            gadgetWeights.add(itemWeight);
            gadgetPowers.add(itemPower);
        }
//        /* * Debug statement to print out the values read in*/
//        System.out.println(gadgetWeights);
//        System.out.println(gadgetPowers);
//        System.out.println(maxCapacityOfBag);
//        System.out.println(numberOfGadgetsToRead);
//        System.out.println(testCases);
//        System.out.println(gadgetWeights.size() +1);

        /*
        * Let us now try to think of  a way to make the table. In general, our tabulation method works by having base
        * cases on the oth row of items and oth row of weights. Now if our weigts well have two items but those go from 1 to
        * 6 then I guess we need to gather the max weight and create the value there, or use the length of it to form the array
        */

        Integer[][] knapsack2D = new Integer[numberOfGadgetsToRead + 1][maxCapacityOfBag +1];
        /*
        * Each cell in the given matrix will represent the maximum power achievable using the first ith elements with a
        * total weight of w.
        * The reccurence here can be defined as:
        *   1. Base Cases:
        *       - If there are no items, then their max power is zero, also if the weight is zero, then max power is zero
        *  2. Recurrence Relationship:
        *       - If the relationship (gadgetWeights.get(row -1) <= column) is false, then we exclude the current item
        *         i.e., the else statement used at the end of the fill method.
        *       - If the relationship is held, then we find the max of either adding the item to the current sum or the
        *        power that has been achieved with the current combination.
        *  3. Why the condition  gadgetWeights.get(row -1) <= column:
        *       - If I understand  this correctly, this means that in general we are hoping to have a weight, for the previous item that is
        *         less than or equal to the weight of the current column, if we are still in that case then we move
        *           to decide whether to include it or not
        *  4. Iteration Differences:
        *     - In the two arrays, given that we are moving through an array which inherently starts at zero and is supposed
        *       to go to the max value of weight and items, it would be useless for us to define only < value conditions
        *       in either of the loops. This would make row, for example, move from 0 to items -1 i.e., from 0 to 1 instead
        *       of zero to two.
        */
        for(int row = 0; row <= numberOfGadgetsToRead; row++){
            for(int column = 0; column <= maxCapacityOfBag; column++){
                if (row == 0 || column == 0){
                    knapsack2D[row][column] = 0; // Base Cases for Zero Items or Zero Weight
                }else if (gadgetWeights.get(row -1) <= column){
                    knapsack2D[row][column] = Math.max(gadgetPowers.get(row -1)
                                                          + knapsack2D[row -1][column - gadgetWeights.get(row - 1)],
                                                        knapsack2D[row -1][column]);
                }
                else {
                    knapsack2D[row][column] = knapsack2D[row - 1][column];
                }
            }
        }
        int maxPower = knapsack2D[numberOfGadgetsToRead][maxCapacityOfBag];
        System.out.println(maxPower);
    }
    }
}
