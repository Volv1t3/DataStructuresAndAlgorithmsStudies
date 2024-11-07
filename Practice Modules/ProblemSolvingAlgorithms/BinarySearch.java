package ProblemSolvingAlgorithms;

import SortingAlgorithmPractice.QuickSort;

/**
 * @author : Santiago Arellano
 * @Date: October 15th, 2024
 * @Description: THe present file will contain information about algorithms related to squares. For example, the
 * first algorithm will implement an efficient way of determining if a number is a perfect square.
 * @version : 1.0
 * @apiNote : The following algorithms will assume generics only when needed.
 */
public class BinarySearch {
    public static void main(String[] args) {

        var name = isPerfectSquare(9);
        System.out.println(name.getResultBoolean());
        System.out.println(name.getResultNumeric());
        Integer[] arr = {7,2,9,5,6,1,3,10};
        var name2 = isElementInArray(arr, 10);
        System.out.println(name2.getResultBoolean());
        System.out.println(name2.resultNumeric);

    }


    /**
     * An algorithm which allows you to find the perfect square of a number if it exists. The number is checked using a
     * variation of binary search and extensions from concepts applied to prime number identification.
     * @param <E> Parameter type of this function, the user must follow the contract of <code>E extends < Number & Comparable
     *         < E > for this algorithm to work</code>
     * @param inputToTest : The actual value sent into the function for analysis
     * @throws NullPointerException : Thrown if the number passed into the array is null
     * @throws IllegalArgumentException: Thrown if the number is not positive (the algorithm does not correct for you, although
     * capable), or if the number exceeds the bounds set up by <code>Integer.MAX_VALUE</code>.
     * @see Integer
     * @see Comparable
     * @see Number
     * @apiNote : This algorithm utilizes a variation of the common Perfect Square algorithm which in turn makes the
     * algorithm run in an O(log n) time complexity.
     * @return : An instance of result(Boolean, Number) record if the given parameter has passed initial evaluations.
     */
    public static <E extends Number & Comparable<E>> result isPerfectSquare(E inputToTest) throws
            NullPointerException,
            IllegalArgumentException {
        //! Base Check: Review nullity or invalidity of the number
        if (inputToTest == null) {
            throw new NullPointerException("The argument passed into inputToTest was null");
        }
        if (inputToTest.intValue() < 0) {
            throw new IllegalArgumentException("The argument passed into inputToTest did not follow the contract of this function");
        }

        if (inputToTest.intValue() == 0) return new result(true, 0);
        if (inputToTest.intValue() == 1) return new result(true, 1);

        //! Inductive Step: Use Binary Search to look for the most appropiate perfect square
        int high = inputToTest.intValue() / 2, low = 0, mid = 0; //We are cutting down the range of squares possible
        mid = (high + low) / 2;
        while ((high - low) > 1) {
            int power = mid * mid;
            if (power == inputToTest.intValue()) return new result(true, mid);
            else if ( power > inputToTest.intValue()) {
                high  = mid;
            }
            else{
                low = mid;
            }

            //! Recalculate the mid
            mid = (high + low) / 2;
            }
        return new result(false, -1);
    }

    /**
     * An algorithm which allows for the retrieval of the index in an array given that a key is passed into the second argument.
     * This is a second implementation example of binary search, where the range of search is divided by half with
     * each iteration.
     * @param inputArray : The array to be searched
     * @param elementToFind : The element to find in the array
     * @return : An instance of resultAverage(Boolean, Integer) record if the given parameter has passed initial evaluations.
     * @param <E> : The type of the array and the element to find.
     * @apiNote : The class and element passed into the function must implement the <code>Comparable < E > </code> interface.
     * @throws NullPointerException : Thrown if either the array or the element to find is null
     */
    public static <E extends Comparable<E>> resultAverage isElementInArray(E[] inputArray, E elementToFind)
                    throws  NullPointerException{
        if (inputArray == null || elementToFind == null) {
            throw new NullPointerException("The array passed into the function, or the element to find, is null");
        }
        if (inputArray.length == 0) return new resultAverage(false, -1);
        if (inputArray.length == 1) return new resultAverage(inputArray[0].equals(elementToFind),
                (inputArray[0].equals(elementToFind)) ? 0 : -1);

        QuickSort.basicQuickSort(inputArray);
        int high = inputArray.length - 1;
        int low = 0;
        int mid = (high + low) / 2;

        while (low <= high){
            int comparisonResult = inputArray[mid].compareTo(elementToFind);
            if (comparisonResult == 0) return new resultAverage(true, mid);
            else if (comparisonResult < 0) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
            mid = (high + low) / 2;
        }

        return new resultAverage(false, -1);
    }

    /**
     * A record class which will store the result of the algorithm.
     * @apiNote :This record class is used to store the result of the algorithm.
     * The record class is used to store the result of the algorithm.
     * @param resultBoolean : The boolean result of the algorithm
     * @param resultNumeric : The numeric result of the algorithm
     */
    public record result(Boolean resultBoolean,Number resultNumeric){
        public result(Boolean resultBoolean, Number resultNumeric){
            this.resultBoolean = resultBoolean;
            this.resultNumeric = resultNumeric;
        }

        public Boolean getResultBoolean(){return this.resultBoolean;}
        public Number getResultNumeric(){return this.resultNumeric;}
    }

    public record resultAverage(Boolean resultBoolean, Integer resultNumeric){
        public resultAverage(Boolean resultBoolean, Integer resultNumeric){
            this.resultBoolean = resultBoolean;
            this.resultNumeric = resultNumeric;
        }

        public Boolean getResultBoolean(){return this.resultBoolean;}
        public Integer getResultNumeric(){return this.resultNumeric;}
    }

}
