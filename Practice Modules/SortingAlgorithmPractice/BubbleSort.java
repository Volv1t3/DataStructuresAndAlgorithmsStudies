package SortingAlgorithmPractice;


import java.util.Comparator;

/**
 * @Datasheet: BubbleSort.java
 * @Author: Santiago Arellano
 * @Date: October 4th, 2024.
 * @Description: The present file contains various versions of bubble sort, from basic to boolean-controlled base
 * case scenario. These implementations will be based on generic data types.
 * <p>The implementations in this file will depend on external sources, like Data Structures, And Algorithms in C++
 * , as well as the questions and information Introduction to Java Programming book.
 * <br>
 * The versions of the algorithms we are going to implement here will work both with comparable and comparator interfaces
 * allowing both for internal references while also allowing the user to provide their own implementations for comparators
 * </p>
 * @apiNote : <b>The program will be implemented to be usable through Consumer references based on either generic arrays
 * and generic arrays with size implementations</b>
 * @Version: 1.0
 * @Since: 1.0
 * @See: XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
 */
public class BubbleSort {

    /**
     * BubbleSort Implementation based on an external array and an array size. This version of the application can work for a
     * Consumer of type <code>Consumer< T[] , Integer>. <b>Moreover, this implementation assumes that the data type
     * already implements its own version of comparable</b></code>
     * @param <DataType> : The data type that will be used in the array
     * @param externalData : The external array that will be sorted
     * @param arraySize : The size of the array
     * @apiNote : <b>This implementation will work for any data type that implements its own version of comparable</b>
     * @throws NullPointerException : If the external data or the array size is null
     * @throws IllegalArgumentException : If the external data is empty or the array size is 0
     */
    public static <DataType extends Comparable<DataType>> void basicBubbleSort(DataType[] externalData,
                                                                               int arraySize)
            throws NullPointerException, IllegalArgumentException
    {

        //! Base Checks for data nullity
        boolean externalDataCheck = externalData == null;
        boolean arraySizeEmpty = arraySize == 0;
        if (externalDataCheck)
        {
            throw new NullPointerException("The given parameter " +
                    "externalData[]" + " did not follow the conditional " +
                    "logics for this method");
        }

        if (arraySizeEmpty) {
            throw new IllegalArgumentException("The given parameter arraySize is 0");}

        if (externalData.length == 0)
        {
            throw new IllegalArgumentException("The given parameter externalData[] is " +
                    "empty or arraySize is 0");
        }
        if (arraySize < 0)
        {
            throw new IllegalArgumentException("The given parameter arraySize is out of bounds");
        }


        for(int i = 0; i < arraySize - 1; i++)
        {
            for(int j = arraySize - 1; j > i; --j)
            {
                if (externalData[j].compareTo(externalData[j-1]) < 0)
                {
                    DataType tempJ = externalData[j];
                    DataType tempJMinusOne = externalData[j-1];
                    externalData[j-1] = tempJ;
                    externalData[j] = tempJMinusOne;
                }
            }
        }
    }

    /**
     * BubbleSort Implementation based on an external array. This version of the application can work for a
     * Consumer of type <code>Consumer< T[] >. <b>Moreover, this implementation assumes that the data type
     * already implements its own version of comparable</b></code>
     * @param externalData : The external array that will be sorted
     * @param <DataType> : The data type that will be used in the array
     * @apiNote : <b>This implementation will work for any data type that implements its own version of comparable</b>
     * @throws NullPointerException : If the external data is null
     * @throws IllegalArgumentException : If the external data is empty
     */
    public static <DataType extends Comparable<DataType>> void basicBubbleSort(DataType[] externalData)
    {
        basicBubbleSort(externalData, (externalData != null ? externalData.length : 0));
    }

    /**
     * BubbleSort implementation based on an external array and an array size, while also allowing the user to pass
     * a custom comparator function. Like before it implements the basic bubble sort with no base case checks for swapping
     * being done on the data.
     * <p>This implementation will work with consumers of the type <code>Consumer< T[], Integer, Comparator<T >> </code></p>
     * @param externalData: The external array that will be sorted
     * @param arraySize: The size of the array
     * @param comparatorInstance: The comparator instance that will be used to compare the data
     * @param <DataType>: The data type that will be used in the array
     * @apiNote: <b>This implementation will work for any data type</b>
     * @throws NullPointerException: If the external data or the array size is null
     * @throws IllegalArgumentException: If the external data is empty or the array size is 0
     */
    public static <DataType> void basicBubbleSort(DataType[] externalData,
                                                  int arraySize,
                                                  Comparator<DataType> comparatorInstance)
            throws NullPointerException, IllegalArgumentException
    {
        //! Base Check for nullity of values
        boolean isExternalDataNull = externalData == null;
        boolean isExternalDataEmpty = (isExternalDataNull || externalData.length == 0);
        boolean isArraySizeZero = arraySize == 0;
        boolean isComparatorNull = comparatorInstance == null;

        if (isExternalDataNull || isArraySizeZero || isComparatorNull)
        {
            throw new NullPointerException("The given parameter " +
                    ((isExternalDataNull ? "externalData[]" : (isArraySizeZero ? "arraySize" : "comparatorInstance"))) +
                    " did not follow the conditional " +
                    "logics for this method");
        }
        if (isExternalDataEmpty)
        {
            throw new IllegalArgumentException("The given parameter externalData[] is " +
                    "empty or arraySize is 0");
        }
        if (arraySize < 0)
        {
            throw new IllegalArgumentException("The given parameter arraySize is out of bounds");
        }
        //! Main Method
        for(int i = 0; i < arraySize - 1; i++)
        {
            for(int j = arraySize - 1; j > i; --j)
            {
                if (comparatorInstance.compare(externalData[j], externalData[j-1]) < 0)
                {
                    //! Making the Move
                    DataType tempJ = externalData[j];
                    DataType tempJMinusOne = externalData[j-1];
                    externalData[j-1] = tempJ;
                    externalData[j] = tempJMinusOne;
                }
            }
        }
    }

    /**
     * BubbleSort implementation based on an external array, while also allowing the user to pass
     * a custom comparator function. Like before it implements the basic bubble sort with no base case checks for swapping
     * being done on the data. This implementation will work for any data type.
     * @param externalData: The external array that will be sorted
     * @param comparatorInstance: The comparator instance that will be used to compare the data
     * @param <DataType>: The data type that will be used in the array
     * @apiNote: <b>This implementation will work for any data type </b>
     */
    public static <DataType> void basicBubbleSort(DataType[] externalData, Comparator<DataType> comparatorInstance)
    {
        basicBubbleSort(externalData, (externalData != null ? externalData.length : 0 ), comparatorInstance);
    }

    /**
     * Secure Bubble Sort implementation based on an external array and an array size. This version of the application
     * can work for a Consumer of type <code>Consumer< T[] , Integer>. <b>Moreover, this implementation assumes that the
     * data type already implements its own version of comparable</b></code>
     * @param <DataType> : The data type that will be used in the array
     * @param externalData : The external array that will be sorted
     * @param arraySize : The size of the array
     * @apiNote : <b>This implementation will work for any data type that implements its own version of comparable</b>
     * @throws NullPointerException : If the external data or the array size is null
     * @throws IllegalArgumentException : If the external data is empty or the array size is 0
     */
    public static <DataType extends Comparable<DataType>> void secureBubbleSort(DataType[] externalData,
                                                                                int arraySize)
            throws NullPointerException, IllegalArgumentException
    {
        //! Base Checks
        boolean externalDataCheck = externalData == null;
        boolean arraySizeEmpty = arraySize == 0;
        if (externalDataCheck || arraySizeEmpty)
        {
            throw new NullPointerException("The given parameter " +
                    ((externalDataCheck ? "externalData[]":"arraySize")) + " did not follow the conditional " +
                    "logics for this method");
        }
        if (externalData.length == 0)
        {
            throw new IllegalArgumentException("The given parameter externalData[] is " +
                    "empty or arraySize is 0");
        }
        if (arraySize < 0)
        {
            throw new IllegalArgumentException("The given parameter arraySize is out of bounds");
        }

        boolean swapped = true;
        for(int i = 0; i < arraySize - 1; i++)
        {
            swapped = false;
            for(int j = arraySize -1; j > i; --j)
            {
                if (externalData[j].compareTo(externalData[j-1]) < 0)
                {
                    DataType tempJ = externalData[j];
                    DataType tempJMinusOne = externalData[j-1];
                    externalData[j-1] = tempJ;
                    externalData[j] = tempJMinusOne;
                    swapped = true;
                }
            }
        }
    }

    /**
     * Secure Bubble Sort Implementation based on an external array. This version of the application can work for a
     * Consumer of type <code>Consumer< T[] >. <b>Moreover, this implementation assumes that the data type
     * already implements its own version of comparable</b></code>
     * @param externalData : The external array that will be sorted
     * @param <DataType> : The data type that will be used in the array
     * @apiNote : <b>This implementation will work for any data type that implements its own version of comparable</b>
     * @throws NullPointerException : If the external data is null
     * @throws IllegalArgumentException : If the external data is empty
     */
    public static <DataType extends Comparable<DataType>> void secureBubbleSort(DataType[] externalData)
    {
        secureBubbleSort(externalData, (externalData != null ? externalData.length : 0));
    }

    /**
     * Secure BubbleSort implementation based on an external array and an array size, while also allowing the user to pass
     * a custom comparator function.
     * <p>This implementation will work with consumers of the type <code>Consumer< T[], Integer, Comparator<T >> </code></p>
     * @param externalData: The external array that will be sorted
     * @param arraySize: The size of the array
     * @param comparatorInstance: The comparator instance that will be used to compare the data
     * @param <DataType>: The data type that will be used in the array
     * @apiNote: <b>This implementation will work for any data type</b>
     * @throws NullPointerException: If the external data or the array size is null
     * @throws IllegalArgumentException: If the external data is empty or the array size is 0
     */
    public static <DataType> void secureBubbleSort(DataType[] externalData, int arraySize, Comparator<DataType> comparatorInstance)
    {
        //! Base Checks
        boolean externalDataCheck = externalData == null;
        boolean arraySizeEmpty = arraySize == 0;
        if (externalDataCheck || arraySizeEmpty)
        {
            throw new NullPointerException("The given parameter " +
                    ((externalDataCheck ? "externalData[]":"arraySize")) + " did not follow the conditional " +
                    "logics for this method");
        }
        if (externalData.length == 0)
        {
            throw new IllegalArgumentException("The given parameter externalData[] is " +
                    "empty or arraySize is 0");
        }
        if (arraySize < 0)
        {
            throw new IllegalArgumentException("The given parameter arraySize is out of bounds");
        }

        boolean swapped = true;
        for(int i = 0; i < arraySize - 1; i++)
        {
            swapped = false;
            for(int j = arraySize -1; j > i; --j)
            {
                if (comparatorInstance.compare(externalData[j],externalData[j-1]) < 0)
                {
                    DataType tempJ = externalData[j];
                    DataType tempJMinusOne = externalData[j-1];
                    externalData[j-1] = tempJ;
                    externalData[j] = tempJMinusOne;
                    swapped = true;
                }
            }
        }
    }

    /**
     * Secure Bubble Sort Implementation based on an external array, while also allowing the user to pass
     * a custom comparator function.
     * @param externalData: The external array that will be sorted
     * @param comparatorInstance: The comparator instance that will be used to compare the data
     * @param <DataType>: The data type that will be used in the array
     * @apiNote: <b>This implementation will work for any data type</b>
     */
    public static <DataType> void secureBubbleSort(DataType[] externalData, Comparator<DataType> comparatorInstance)
    {
        secureBubbleSort(externalData, (externalData != null ? externalData.length : 0), comparatorInstance);
    }

}
