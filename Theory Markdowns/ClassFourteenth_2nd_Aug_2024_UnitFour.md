    
<h1 style=" color: cornflowerblue; text-align: center; font-family: 'Consolas', sans-serif;">
Data Structures And Algorithms | Efficient Sorting Algorithms | USFQ | Santiago Arellano
</h1>


***
<ul style="font-family: 'Consolas', sans-serif;">
<code >Main Information Section</code>
<li><b style="color: cornflowerblue; font-weight: bold">Date:</b>: August 2nd, 2024.</li>
<li><b style="color: cornflowerblue; font-weight: bold">Unit</b>: Four.</li>
<li><b style="color: cornflowerblue; font-weight: bold">Description</b>: This file pertains the following 
contents: <code><b>Efficient Sorting Algorithms</b></code>.</li>
</ul>

***
<br>
<h3 style=" color: cornflowerblue; text-align: center; font-family: 'Consolas', sans-serif;">
"Shell Sort ", "Merge Sort ","Quick Sort ","Radix Sort ", "Bucket Sort ", "Counting Sort ", "Heap Sort"
</h3>
<ul style="font-family: Consolas, sans-serif">
<li><code style="color: cornflowerblue; font-weight: bold">"Efficient Sorting Algorithm"</code>: 
<p>These are some of the most refined and well-behaved algorithms developed for sorting. While one could create their
own implementations (refer to an example I did in C++ implementing a sorting and removal algorithm), these algorithms have
been tested to provide complexities which do not approach polynomial orders, rather they are more efficient. 
<br><br>
In this section, like the class before, we will take a look at various algorithms, like ShellSort or QuickSort, and will 
provide various aspects of them, like complexity analysis, implementations and revised pros and cons. These algorithms
although not squarely the same as the ones presented in Liangs' textbook, will closely follow the implementations and
analysis done mathematically in the C++ book, Data Structure and Algorithms</p>
</li>
<!--! A comment to Separate them all -->
<li><code style="color: cornflowerblue; font-weight: bold">"Shell Sort"</code>:
<p>
One of the most important improvements we can do over the algorithms presented before, is the need to analyze the entirety
of an array, multiple times in given examples, to order it completely. In our case, we could improve this if we begin by 
ordering subsets of the larger dataset and moving in this matter until we have ordered the entirety of the array. This means
that <b>it would be much more efficient to sort subarrays from the larger array and then use those sorted subarrays, to 
develop a sorted larger set</b>
<br>
This is the main logic behind the <q>Diminishing Increment Sort or Shell Sort</q> developed by <b>Donald L Shell</b>. 
This algorithms works by finding increments, or rather decrements for the size in which the array can be subdivided, 
creating these subarrays in which <b><q>The Elements farther apart are reviewed first, and we continue decrementing our 
steps until we reach adjacent elements</q></b>
</p>
<blockquote style="font-style: italic; color: black"> 
<b>Thinking About Subdivisions:</b>
<br>
In the beginning of the algorithm, in many implementations we devote a given period to calculating the decrements in which 
the subarrays will be handled. One way we could do this is through the following pseudocode
<body>

```text
determine numbers h_t, ... h_1 of ways of dividing array data[] into subarrays;
    for (h = h_t; t > 1; t--, h = h_t)
        divide data into h subarrays;
        for i = 1 to  h
            sort subarray data[]_i;
    sort array data;
```
</body>
<p>While this way of thinking about it makes sense mathematically, this basically says 
that we are going to look down from a given number h_t towards 1, dividing the array in h given
subarrays. After having divided the array we proceed to apply the sorting algorithm on <b>each of those
sub-arrays</b> to finally combine them into a sort larger array.
</p>

<p>
Now the observant would realize that the way in which we implemented this does <b>not</b> tell us in any way how to calculate
these divisions. However, mathematical models and computer scientists have developed notions that could guide us in 
deciding the subdivisions we need to do.
</p> 
<ul>
<code>Various Guidelines For Subdivision Selection</code>
<li><b style="color: cornflowerblue; font-weight: bold">Donald Knuth's Divisions</b>: He demonstrated that the performance
of insertion sort, when used in conjunction with ShellSort, had a complexity of O(n^(5/3)) when there are only <b>two 
increments</b> namely <code><math><mrow><msup><mi>(16*n/&pi;)</mi>^<mn>1/3</mn></msup></mrow></math> and 1 </code>
</li>
<li><b style="color: cornflowerblue; font-weight: bold">Do not use multiples</b>: It is imprudent, as mentioned by the 
authors to use sequences of divisions like 1,2,4,8... or 1,3,6,9,... given that <b>this removes the mixing of data which
makes it so that sorting is more efficient</b></li>
<li><b style="color: cornflowerblue; font-weight: bold">Empirical Mathematical Models</b>: The best model we have for 
determining the divisions comes from a recurrence relationship
<body>


$ h_1 = 1 $

$ h_(i+1) = 3h_i + 1 $

with a stop in $ h_t $ when $ h_(t+2) >= n $

</body>
<br>
This is the most general method for calculating divisions and is the one we will use in our implementations
</li>
</ul>
</blockquote>
<p>
Having defined the code and the issue of subdivisions, we shall take a look at the implementations for this algorithm both in 
C++ and in Java
</p>
<blockquote style="font-style: italic; color: black"> 
<ul style="list-style: -moz-japanese-formal">
<li><b style="color: cornflowerblue; font-weight: bold">"C++"</b>: 

<body>

```C++
template<Class T>
void ShellShort(T data[], int n)
{
    register int i,j,hCnt, h;
    int increments[20], k;
    //! Create an appropiate number of increments h
    for(h = 1, i = 0; h < n; i++)
    {
        increments[i] = h;
        h = 3*h + 1;
    } 
    //! Loop on the number of different increments h
    for(i--; i>=0; i--)
    {
        h = increments[i];
        //! Loop on the number of subarrays h-sorted in ith pass
        for (hCnt = h; hCnt < 2*h; hCnt++)
        {
        //!Insertion sort for subarray containing every hth element of data
            for(j = hCnt; j < n;)
            {
                T tmp = data[j];
                k = j;
                while (k -h >= 0 && tmp < data[k-h])
                {
                    data[k] = data[k-h];
                    k -=h;
                }
                data[k] = tmp;
                j += h;
            }
        }
    }
}
```
</body>
</li>
<li><b style="color: cornflowerblue; font-weight: bold">"Java"</b>: 
<body>

```java
public class ShellSort {
    public static <T extends Comparable<T>> void shellSort(T[] data, int size) {
        int[] increments = new int[20];
        int h, i, j, k, hCnt;

        // Generate a sequence of increments
        for(h = 1, i = 0; h < size; i++) {
            increments[i] = h;
            h = 3 * h + 1;
        }

        // Loop through the increments in reverse order
        for(i--; i >= 0; i--) {
            h = increments[i];
            // Loop on the number of subarrays h-sorted in ith pass
            for (hCnt = h; hCnt < 2 * h; hCnt++) {
                // Insertion sort for the subarray containing every h-th element of data
                for(j = hCnt; j < size;) {
                    T tmp = data[j];
                    k = j;
                    while (k - h >= 0 && tmp.compareTo(data[k - h]) < 0) {
                        data[k] = data[k - h];
                        k -= h;
                    }
                    data[k] = tmp;
                    j += h;
                }
            }
        }
    }
}
```
</body>
</li>
</ul>
</blockquote>
<p>Sadly, as much as we would like to present a mathematical performance analysis which produces a <b>concrete</b>
asymptotic complexity, we know that this is not possible and that only empirical performance can be attained, which can 
vary from <code>O(n^1.25) all the way to  O(nlg(N))^2)</code>. Finally, we present the pros and cons for this algorithm
</p>
<blockquote style="font-style: italic; color: black"> 
<body>

```plantuml

package ProsAndConsShellSort {

interface ShellSort

ShellSort : [1] **Improved Insertion Sort**: significantly faster than Insertion Sort for large data sets.
ShellSort : [2] **In-Place Sorting**: doesn't require extra memory beyond the input array.
ShellSort : [3] **Efficient for Medium-Sized Data Sets**: performs reasonably well for medium-sized datasets.
ShellSort : [4] **Simple to Implement**: relatively straightforward to understand and implement compared to other efficient sorts.
ShellSort : [5] **Adaptive (with Optimization)**: quickly reduces the number of inversions in the array, making it closer to a sorted state.

ShellSort : (1) **Not a Stable Sort**: doesn't guarantee the relative order of equal elements.
ShellSort : (2) **Gap Sequence Dependent**: the choice of a gap sequence can significantly influence performance, with no optimal sequence known for all cases.
ShellSort : (3) **Worst-Case Complexity is Hard to Determine**: can vary depending on the gap sequence used, and the worst-case time complexity isn't always straightforward.
ShellSort : (4) **Outperformed by Advanced Algorithms**: usually outperformed by Quick Sort, Merge Sort, and Heap Sort on larger datasets.
}
```
</body>
</blockquote>
</li>
<!--! A comment to Separate them all -->
<li><code style="color: cornflowerblue; font-weight: bold">"Merge Sort"</code>:
<p>
Merge sort is another type of algorithm, put closer to the top in this list due to Fausto mentioning it quicker than others,
in which the main idea is to break down the array into halves, recursively, sorting these halves into order and then merging
them together into the big array to make it all work. This subdivision process stops when <b>the arrays will have less 
than two elements in it</b>. While this is the general notion of the algorithm, the complexity comes from the process of
merging arrays together to sort the data in the end.
<br><br>
This happens because you would have to either copy elements over or duplicate elements over unless you use some sort of 
temporary vector or an intermediate step through which you can merge everything back up in the end. The following pieces 
of pseudocode will demonstrate how this is done
</p>
<blockquote style="font-style: italic; color: black"> 
<b>Thinking about merging the arrays: first approach</b>
<br>
<p>Consider an array only divided into two halves (although the nature is recursive, and we know we would have a ton more 
than just two, consider just two), if we consider three different arrays of data, we could consider this approach
for merging the elements</p>
<body>

```text
merge(array1[], array2[], array3[])
    i1, i2, i3 are properly initialized;
    while both array2 and array3 contain elements
        if array2[i2] < array3[i3]
            array1[i1++] = array2[i2++];
            else array1[i1++] = array3[i3++];
    load into array1 the remaining elements of either array2 or array3;
```
</body>
<p>This method is generally just a check between the two halves to determine which element should be put first and 
which should be done later. However, this causes issues in the copying of data given that we are appending values 
independently of the presence or not of the items in the array. For this reason we developed this better prototype</p>
<body>

```text
merge (array1[], first, last)
    mid = (first + last) / 2;
    i1 = 0;
    i2 = first;
    i3 = mid + 1;
    while both left and right subarrays of array1 contain elements
        if array1[i2] < array1[i3]
            temp[i1++] = array1[i2++];
        else temp[i1++] = array1[i3++];
    load into temp the remaining elements of array1;
    load to array1 the content of temp;
```
</body>
<p>As can be noted, we are now using the indices which indicate the start and end of the array, and if we are
only dividing into two halves, we can assume that the first array will go from 0 to first, and the second would go 
from mid +1 to last. This allows us to iterate and pass elements, either into the array or to another temporary array, 
which reduces the issues of data duplication.<br><br>Now, let us think about the pseudocode for the mergeSort complete 
algorithm</p>
<body>

```text
mergeSort(data[], first, last)
    if first < last
        mid = (first + last) / 2
        mergeSort(data[], first, mid);
        mergeSort(data[], mid +1, last);
        merge(data[], first, last);
```
</body>
</blockquote>
<p>Despite the previous pseudocode, we do not have a direct implementation for C++ for this algorithm, this means 
that our implementations will begin with Java, and will move over to C++ as a derivate of it.</p>
<blockquote style="font-style: italic; color: black"> 
<ul>
<code>Implementations</code>
<li><b style="color: cornflowerblue; font-weight: bold">" Java [Basic MergeSort With Recursive 
Subdivision and ArrayCopy]"</b>: 
<body>

```java
import java.util.Comparator;
import java.lang.reflect.Array;

public class MergeSort {

    public static <E> void mergeSort(E[] list, Comparator<? super E> comparator) {
        if (list.length > 1) {
            // Merge sort the first half
            int mid = list.length / 2;
            E[] firstHalf = (E[]) Array.newInstance(list.getClass().getComponentType(), mid);
            System.arraycopy(list, 0, firstHalf, 0, mid);
            mergeSort(firstHalf, comparator);

            // Merge sort the second half
            int secondHalfLength = list.length - mid;
            E[] secondHalf = (E[]) Array.newInstance(list.getClass().getComponentType(), secondHalfLength);
            System.arraycopy(list, mid, secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf, comparator);

            // Merge firstHalf with secondHalf
            E[] temp = merge(firstHalf, secondHalf, comparator);
            System.arraycopy(temp, 0, list, 0, temp.length);
        }
    }

    private static <E> E[] merge(E[] list1, E[] list2, Comparator<? super E> comparator) {
        E[] temp = (E[]) Array.newInstance(list1.getClass().getComponentType(), list1.length + list2.length);

        int current1 = 0; // Index in list1
        int current2 = 0; // Index in list2
        int current3 = 0; // Index in temp

        while (current1 < list1.length && current2 < list2.length) {
            if (comparator.compare(list1[current1], list2[current2]) < 0) {
                temp[current3++] = list1[current1++];
            } else {
                temp[current3++] = list2[current2++];
            }
        }

        while (current1 < list1.length) {
            temp[current3++] = list1[current1++];
        }

        while (current2 < list2.length) {
            temp[current3++] = list2[current2++];
        }

        return temp;
    }
}
```
</body>
</li>
<li><b style="color: cornflowerblue; font-weight: bold">" Java [Recursive iteration over a single TemporalArray, 
Out of Place Ordering, Copy of Data]"</b>: 
<body>

```java
import java.util.Comparator;

public class MergeSort {

    public static <E> void mergeSort(E[] list, Comparator<? super E> comparator) {
        // Create a temporary array only once
        E[] tempArray = (E[]) Array.newInstance(list.getClass().getComponentType(), list.length);
        mergeSort(list, tempArray, 0, list.length - 1, comparator);
    }

    private static <E> void mergeSort(E[] list, E[] tempArray, int left, int right, Comparator<? super E> comparator) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Sort first and second halves recursively
            mergeSort(list, tempArray, left, mid, comparator);
            mergeSort(list, tempArray, mid + 1, right, comparator);

            // Merge sorted halves
            merge(list, tempArray, left, mid, right, comparator);
        }
    }

    private static <E> void merge(E[] list, E[] tempArray, int left, int mid, int right, Comparator<? super E> comparator) {
        System.arraycopy(list, left, tempArray, left, right - left + 1);

        int i = left; // Initial index of the first subarray
        int j = mid + 1; // Initial index of the second subarray
        int k = left; // Initial index of the merged subarray

        while (i <= mid && j <= right) {
            if (comparator.compare(tempArray[i], tempArray[j]) <= 0) {
                list[k++] = tempArray[i++];
            } else {
                list[k++] = tempArray[j++];
            }
        }

        while (i <= mid) {
            list[k++] = tempArray[i++];
        }

        while (j <= right) {
            list[k++] = tempArray[j++];
        }
    }
}
```
</body>
</li>
</ul>
</blockquote>
<p>Let us discuss the complexity of this algorithm by just stating it <b>The complexity of merge sort is defined to be
O(n*lg(n))</b>. Finally, let us discuss the pros and cons about this algorithm.</p>
<body>

```plantuml

package ProsAndConsMergeSort {

interface MergeSort

MergeSort : [1] **Stable Sort**: preserves the relative order of equal elements.
MergeSort : [2] **Predictable Performance**: consistent time complexity of O[n log n].
MergeSort : [3] **Divide and Conquer**: easily parallelizable.
MergeSort : [4] **External Sorting**: suitable for sorting large datasets that don't fit into memory [external merge sort].

MergeSort : (1) **Not In-Place**: requires additional memory proportional to the size of the input array O[n] space complexity].
MergeSort : (2) **More Memory Usage**: can be less efficient in terms of space compared to other in-place algorithms like Heap Sort.
MergeSort : (3) **Overhead**: involves more overhead due to recursive function calls and array copying.
MergeSort : (4) **Slower for Small Arrays**: may be slower for small arrays compared to simple algorithms like Insertion Sort due to overhead.
}
```
</body>
</li>
<!--! A comment to Separate them all -->
<li><code style="color: cornflowerblue; font-weight: bold">"Quick Sort"</code>:
<p>Quick sort is another algorithm that works like merge and shell, i.e., by subdividing the array into smaller sub-arrays
that allow us to recursively order the array faster and with less memory overhead. The main difference that shell sort and 
merge sort have with quick sort is that <b>quick sort works by iteratively select a bound inside the array with, that can be
used as a flag to partition the array.</b></p>
<br><p>To determine the way in which we can partition the arrays, we can follow these explanations</p>
<blockquote style="font-style: italic; color: black"> 
<b>Understanding Quick Sort</b>:
<br><br>
In order to comprehend the inner-workings of QuickSort we need to take a look at how the algorithm works in terms of a 
pseudocode explanation.
<body>

```text
quicksort(array[])
    if length(array) > 1
    choose bound; // partition array into subarray1 and subarray2
    while there are elements left in array
        include element either in subarray1 = {el: el ≤ bound}
        or in subarray2 = {el: el ≥ bound};
    quicksort(subarray1);
    quicksort(subarray2);
```
</body>
<p>In the previous algorithm, one can notice that the way it works is by recursion on both sides of the array, 
subdividing the array into smaller versions of it until <b>one-cell arrays are left and this returns up the recursive calls</b>
This means that one of the main issues that we have now is the way we choose to handle the bound for the division point for 
each later array.
<br><br>
An interesting point to make is that quickSort works by sorting the array as it subdivides the array into smaller subdivisions.
It does not have a merging state.
</p>
</blockquote>
<p>Let us take a look at the implementations of the algorithms with respect to each of the languages at analysis</p>
<blockquote style="font-style: italic; color: black"> 
<ul>
<code>Implementations</code>
<li><b style="color: cornflowerblue; font-weight: bold">"C++ Double Implementations"</b>: 
<body>

```C++

template<class T>
void quicksort(T data[], int first, int last) {
    int lower = first+1, upper = last;
    swap(data[first],data[(first+last)/2]);
 T   bound = data[first];
    while (lower <= upper) {
        while (bound > data[lower])
            lower++;
        while (bound < data[upper])
            upper--;
        if (lower < upper)
            swap(data[lower++],data[upper--]);
        else lower++;
 }
    swap(data[upper],data[first]);
    if (first < upper-1)
        quicksort (data,first,upper-1);
    if (upper+1 < last)
        quicksort (data,upper+1,last);
}
template<class T>
void quicksort(T data[], int n) {
    int i, max;
    if (n < 2)
        return;
    for (i = 1, max = 0; i < n; i++) // find the largest
        if (data[max] < data[i]) // element and put it
        max = i; // at the end of data[];
 swap(data[n-1],data[max]); // largest el is now in its
 quicksort(data,0,n-2); // final position;
}

```
</body>
<p>An interesting aspect to note about this implementation is that the function <code>
quicksort(T data[], int first, int last){}</code> is a function which is used inside to actually run the recursive code 
that orders the array. Meanwhile, the second function <code>quicksort(T data[], int n)</code> is the main entry point of
the function, and this also handles base case checks like when the array does not even have enough data to check</p>
</li>
<li><b style="color: cornflowerblue; font-weight: bold">"Java Implementation"</b>: 
<body>

```java
public static <T extends Comparable<T>> void quicksort(T[] data) {
        int n = data.length;
        if (n < 2) return; // Boundary condition: if the array has fewer than 2 elements, it's already sorted

        // Find the largest element and move it to the last position
        int max = 0;
        for (int i = 1; i < n; i++) {
            if (data[max].compareTo(data[i]) < 0) {
                max = i;
            }
        }
        swap(data, n - 1, max); // Move the largest element to the end

        // Call the recursive quicksort function to sort the rest of the array
        quicksort(data, 0, n - 2);
    }

    private static <T extends Comparable<T>> void quicksort(T[] data, int first, int last) {
        if (first >= last) return; // Base condition: if the segment has 0 or 1 elements, it's already sorted

        int lower = first + 1;
        int upper = last;

        swap(data, first, (first + last) / 2); // Move the pivot (middle element) to the first position
        T bound = data[first]; // Pivot element

        // Partition the array
        while (lower <= upper) {
            while (bound.compareTo(data[lower]) > 0) lower++;
            while (bound.compareTo(data[upper]) < 0) upper--;
            if (lower < upper) {
                swap(data, lower++, upper--);
            } else {
                lower++;
            }
        }
        swap(data, upper, first); // Place the pivot in its correct position

        // Recursively sort the partitions
        if (first < upper - 1) quicksort(data, first, upper - 1);
        if (upper + 1 < last) quicksort(data, upper + 1, last);
    }

    private static <T> void swap(T[] data, int i, int j) {
        T temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
```
</body>
<p>It is important to note that the presented implementation in Java, differs from the one in the book by virtue of being a 
direct translation of the C++ code into Java including the necessary std::swap method to transfer data between array indices.
We could've copied and presented the Liang's implementation; however, I think that with this implementation it should work 
correctly too</p>
</li>
<li><b style="color: cornflowerblue; font-weight: bold">" Java [Median-Of-Three Implementation]</b>: 
<body>

```java
public static <DataType extends Comparable<DataType>> void medianQuickSort(DataType[] externalData,
                                                                          int arraySize)
            throws NullPointerException, IllegalArgumentException
    {
        //! Given the implementation requirements, we can still do the same logic as before, we can find the largest element
        // and proceed to move it over to the end of the array like before
        if (externalData == null) {
            throw new NullPointerException("The external data array is null.");
        }
        if (externalData.length == 0 || arraySize == 0) {
            throw new IllegalArgumentException("The external data array is empty or the array size is zero.");
        }
        if (externalData.length != arraySize)
        {
            throw new IllegalArgumentException("The array size does not match the external data array size.");
        }

        //! Moving the largest index to the back of the list
        int max = 0;
        for (int i =0; i < arraySize; i++)
        {
            if (externalData[max].compareTo(externalData[i]) < 0) max = i;
        }
        swap(externalData, externalData.length -1,max );

        //! Use the median as the partition point
        medianQuickSortHelper(externalData, 0, arraySize - 2);
    }
    public static <DataType extends Comparable<DataType>> void medianQuickSortHelper(DataType[] externalData,
                                                                                     int first,
                                                                                     int last)
    {
        if (first >= last) return; // Base condition: if the segment has 0 or 1 elements, it's already sorted
        // Median-of-three pivot selection
        //! Iterate and find the median between the mid, low and high indices

        int lowIndex = first;
        int highIndex = last;
        int midIndex = (first + last ) / 2;
        DataType low = externalData[lowIndex];
        DataType mid = externalData[midIndex];
        DataType high = externalData[highIndex];
        //! Calculate the median of the three values
        ArrayList<DataType> col = new ArrayList<>(3){
            {
                add(low);
                add(mid);
                add(high);
            }
        };
        Collections.sort(col);
        DataType median = col.get(1);
        int medianIndex = 0;

        if (median == mid) {medianIndex = midIndex;}
        else {medianIndex = highIndex;}


        // Move the median value to the 'first' position to use as pivot
        swap(externalData, first, medianIndex);

        //! Using the partitioning function to delimit the array midpoint
        int partitionIndex = medianPartitioningHelper(externalData, first, last);


        //! Recursive partition call
        medianQuickSortHelper(externalData, first, partitionIndex - 1);
        medianQuickSortHelper(externalData, partitionIndex + 1, last);
    }

    private static <DataType extends Comparable<DataType>> int medianPartitioningHelper(DataType[] externalData,
                                                                                         int first,
                                                                                         int last)
    {
        DataType pivot = externalData[first];
        int lower = first + 1;
        int upper = last;

        //! FindingCorrect Partitioning index
        while (lower <= upper) {
            while (lower <= upper && externalData[lower].compareTo(pivot) <= 0) lower++;
            while (lower <= upper && externalData[upper].compareTo(pivot) >= 0) upper--;
            if (lower < upper) {
                swap(externalData, lower++, upper--);
            }
        }


        //! Move the median value to the 'first' position to use as pivot
        swap(externalData, first, upper);
        return upper;
    }
```
</body>
</li>
</ul>
</blockquote>
<p>Whatever the implementation that we do, <code>the complexity of this algorithm is O(nlg(n))</code>. Lastly, we present
the pros and cons here.</p>
<body>

```plantuml

package ProsAndConsQuickSort {

interface QuickSort

QuickSort : [1] **Efficient Average Case Performance**: usually performs well with an average-case time complexity of O[n log n].
QuickSort : [2] **In-Place Sorting**: requires only a small, constant amount of additional storage space.
QuickSort : [3] **Cache-Friendly**: due to its in-place nature and good locality of reference.
QuickSort : [4] **Divide and Conquer**: the algorithm can be easily parallelized.
QuickSort : [5] **Highly Tunable**: various optimizations [like choosing different pivot selection strategies] can improve performance.

QuickSort : (1) **Not Stable**: doesn't guarantee the relative order of equal elements.
QuickSort : (2) **Worst-Case Performance**: has a worst-case time complexity of O(n^2), typically mitigated by good pivot selection techniques.
QuickSort : (3) **Performance on Small Arrays**: may be slower for small arrays compared to simpler sorts like Insertion Sort due to overhead.
QuickSort : (4) **Sensitive to Pivot Choice**: performance depends significantly on the choice of pivot, which affects partitioning.
}
```
</body>
</li>
<!--! A comment to Separate them all -->
<li><b style="color: cornflowerblue; font-weight: bold">"Radix Sort"</b>: 
<p>Radix sort is an algorithm based on the common sorting technique of creating piles of data, similar of course, and 
categorizing, organizing, and sorting the data given a simple sorting parameter. In general, one of the implementations we can see 
is sorting integers based on <b>their most significant digits from left to right.</b>Now that we have checked this, let us 
think about how radix sort would take shape.</p>
<blockquote style="font-style: italic; color: black"> 
<b>Thinking of sorting non-digit even numbers:</b>
<br>
<p>Consider sorting an array of numbers in which the input numbers can vary from single digits (e.g., 0, 1, 3) or double 
or triple digits, or n digits. Now if we take a look at these numbers, and we try to sort them in piles, how would we do this 
if there are an uneven number of digits, sorting by left-significant-digit from the left would lead us to think that single or double numbers
would have to be placed alongside other numbers which are larger. The issue lies in the way we analyze numbers and the way 
we are taught to extract the digits.
<br><br>
One solution would argue for adding zeros or null characters in front of the single digit numbers to make them match up 
with the largest number in terms of digits, and check their divisibility based on their size. And while other approaches work
by transforming into strings, we will mostly discuss radix sort by way of sorting integers.
<br>
The following pseudocode presents the overview of the algorithm.
</p>
<body>

```text
radixSort()
    for d = 1 to the position of the leftmost digit of longest number
        distribute all numbers among piles 0 through 9 according to the dth digit;
        put all integers on one list;
```
</body>
Think about the previous pseudocode, and think about the way we could organize and make those piles. An argument could
be made that they could be defined as stacks, however their ordering would be twisted and forced upon us. Conversely, we 
could implement the storage as queues, in which the FIFO logic would aid us in outputting the numbers from each in the relative
order that they arrived.
</blockquote>
<p>Having analyzed the backend implementation and the conceptualization of the numbers, let us take a look at the algorithms'
implementations both in C++ and in Java</p>
<blockquote style="font-style: italic; color: black"> 
<ul style="list-style: -moz-japanese-formal">
<li><b style="color: cornflowerblue; font-weight: bold">"C++"</b>: 
<body>

```C++ 
void radixSort(long[] data, int n)
{
    register int d, j, k, factor;
    const int radix = 10;
    const int digits = 10;
    Queue<long>[radix] queues;
    for(d = 0, factor =1; d < digits; factor *=radix; d++)
    {
        for(j = 0; j < n; j++)
        {
            queues[(data[j]/factor) % radix].enqueue(data[j]);
        }
        for (j = k = 0; j < radix; j++)
        {
            while (!queues[j].empty()
            {
                data[k++] = queues[j].dequeue();
            }
        }
    }
}
```
</body>
<p>One thing to note here is that in the case of an integer not having more factors to do, for example, 10, when factor is 100
would result in 0 in a normal integer division in C++, this would mean that the smallest numbers, as the algorithm progressed
will be ordered in the queues[0] queue.</p>
</li>
<li><b style="color: cornflowerblue; font-weight: bold">"Java"</b>: 
<body>

```java
import com.sun.source.tree.DeconstructionPatternTree;

import java.util.ArrayDeque;

public static void radixSort(long[] data, int n) {
    final int radix = 10;
    final int digits = 10; // The maximum number of digits for a number

    // Create an array of 10 queues
    Queue<Long>[] queues = new LinkedList[radix];
    for (int i = 0; i < radix; i++) {
        queues[i] = new LinkedList<>();
    }

    for (int d = 0, factor = 1; d < digits; factor *= radix, d++) {
        // Distribute the numbers into buckets
        for (int j = 0; j < n; j++) {
            int bucketIndex = (int) ((data[j] / factor) % radix);
            queues[bucketIndex].add(data[j]);
        }

        // Collect the numbers from the buckets and put them back into the array
        int k = 0;
        for (int j = 0; j < radix; j++) {
            while (!queues[j].isEmpty()) {
                data[k++] = queues[j].poll();
            }
        }
    }
}
```
</body>

</li>
</ul>
</blockquote>
<p>Theoretically, its performance is <code><b>O(n)</b></code>, however, this depends on the size of the numbers, the amount 
of numbers, and it also depends on the underlying data structure used to hold onto the buckets of numbers. In our case,
 since we used LinkedLists we expect to be very dependent on their operation-per-operation performance.</p>
</li>
<!--! A comment to Separate them all -->
<li><b style="color: cornflowerblue; font-weight: bold">"Bucket Sort"</b>: 
<p>Bucket sort is an algorithm that works similarly to radix sort, actually it is a close cousin of it, in the sense that
it distributes data according to a fixed and statistical number of buckets. It could be approximated as a function which 
<b>disperses data into buckets, stores corresponding elements of the original array in these buckets, and finally merges
these buckets once ordered together</b>.
<br><br>
The beauty of this algorithm is that it does not provide a clear and cut way of implementing it. There are a variety of 
sorting algorithms that can be used to implement the internal bucket sorting. From simple inspection sort, to quick sort, 
we can basically decide which way to implement this in terms of the optimizations that we had done on other algorithms.
<br><br>
For this reason, bucket sort can be implemented in various ways, and even have some specifications for itself. For example, 
we have the <code><b>Generic Bucket Sort, Proxmap Sort, Histogram Sort (counting sort), Postman Sort, Shuffle Sort</b></code>
<br><br>
Generally in our applications we will work on the main Generic Bucket Sort, algorithm. However, I will try to include some
statistical theory to make the sorting and bucket distribution more fair and mathematically precise to the data at hand.
Moreover, the methods that will be implemented here will overall be usable with classes that extend the <code>Comparable</code> 
interface and provide at least a single <code>compareTo(E other)</code> implementation, as we will use this interface
to facilitate comparisons between objects. Moreover, for better memory usage we will utilize quick sort to sort the internal
arrays effectively.
</p>
<blockquote style="font-style: italic; color: whitesmoke"> 
<q>Thinking about Bucket Sort</q>
<p>As with every other algorithm presented here, it would be best to see the pseudocode first and then break it down into
logical steps. (Also as a side note, I will most likely, because I enjoy this, do another document just for bucket sort 
implementations). The basic
</p>
<blockquote>
<body>

```text
Procedure BucketSort(arr, n)
    Input: 
        arr: Array of elements to be sorted
        n: Size of the array

    // Step 1: Create empty buckets
    Create an array of empty buckets B

    // Step 2: Insert elements into buckets
    for i = 0 to n-1 do
        Find the appropriate bucket for arr[i]
        Insert arr[i] into the found bucket

    // Step 3: Sort individual buckets
    for each bucket in B do
        Sort(bucket)

    // Step 4: Concatenate all sorted buckets into arr
    idx = 0
    for each bucket in B do
        for each element in bucket do
            arr[idx] = element
            idx = idx + 1

EndProcedure
```
</body>
</blockquote>
<p>The previous pseudocode opens up various pathways for interpretation and extensibility. One of the main 
ways in which it does this is by, in step one, to create empty buckets. A simple way of doing this would be, 
the brute force way would be to determine that we could have a fixed number of buckets, one could be a good answer, and 
simply apply a sorting algorithm to this singular bucket. However, the main way through which this algorithm excels is by
making use of either mathematical methodology, code data manipulation, or in our case, statistical methods.
<br><br>
This is far from the only place in the pseudocode in where the generality is left to be answered. In step two, inserting 
each item into buckets is just as complicated as devising these buckets. In our case, we can use statistically methods 
both for determining the number of buckets, their width, and even the places in which they are meant to fall.
<br><br>
Lastly, although already spoiled by our introduction to this algorithm, in step three we can use quick sort (imported from
another library of course, or as a reference) to sort each array directly before in step four concatenating them. Generally, 
there really is not anything too complicated to think about in this implementation. While there are better alternatives like the 
ones posited above, this is good enough for a demonstration of the algorithm, as in most cases we will inevitably adapt 
our applications to use the fastest algorithms, and time proven implementations, we can find.
</p>
</blockquote>
<p>Having defined the pseudocode for this algorithm, sadly we do not have a direct implementation for C++, and since the focus
of this lesson is to make use of Java to produce this Bucket Sort generic; we will not be providing an implementation for
C++</p>
<blockquote style="font-style: italic; color: whitesmoke"> 
<ul>
<code>Implementations</code>
<li><b style="color: cornflowerblue; font-weight: bold">"Java [Generic Bucket Sort]</b>: 
<blockquote>
<body>

```java
package BucketSortImplementations;

import SortingAlgorithmPractice.QuickSort;

import javax.lang.model.type.TypeMirror; //Lets test these two out later
import javax.lang.model.type.UnionType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.lang.Number;

public class GenericBucketSort {
    //! Declaring a main static algorithm exposure
    public static <E extends Number & Comparable<E>> void bucketSort(E[] externalSource) throws NullPointerException, IllegalArgumentException {
        //! Step -1: Gather internal constants and perform preflight checks
        List<E> eList = List.of(externalSource);
        isNull(eList);
        isEmpty(eList);
        int lengthOfSource = externalSource.length;

        //! Step -1: Gather internal constants and perform preflight checks
        List<E> eList = List.of(externalSource);
        isNull(eList);
        isEmpty(eList);
        int lengthOfSource = externalSource.length;

        //! Step 1: Creating the Empty Buckets based on statistics
        int numberOfBuckets = (int) Math.ceil(1 + 3.22 * Math.log10(lengthOfSource));
        E minItem = externalSource[0];
        E maxItem = externalSource[0];
        for (E element : externalSource) {
            if (element.compareTo(minItem) < 0 ) {
                minItem = element;
            }
            if (element.compareTo(maxItem) > 0) {
                maxItem = element;
            }
        }

        double bucketSize = (maxItem.doubleValue() - minItem.doubleValue()) / numberOfBuckets;
        ArrayList<ArrayList<E>> buckets = new ArrayList<>(numberOfBuckets);
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets.add(new ArrayList<>());
        }
        double bucketSize = (maxItem.doubleValue() - minItem.doubleValue()) / numberOfBuckets;
        ArrayList<ArrayList<E>> buckets = new ArrayList<>(numberOfBuckets);
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets.add(new ArrayList<>());
        }

        //! Step 2: Inserting the elements in the array based on bucket size
        for (E element : externalSource) {
            int bucketIndex = (int) ((int) (element.doubleValue() - minItem.doubleValue()) / bucketSize);
            bucketIndex = Math.min(bucketIndex, numberOfBuckets - 1);
            buckets.get(bucketIndex).add(element);
        }

        //! Step 3: Sort individual buckets
        buckets.forEach(bucket -> {
            MinHeapWithArrayList<E> sorting;
            try{
                sorting = new MinHeapWithArrayList<>(bucket);
                bucket.clear();
                while (!sorting.isEmpty()){
                    bucket.add(sorting.removeRetrievesRoot());
                }
            }
            catch(IllegalArgumentException _){}
        });

        //! Step 4: Concatenate all buckets into a single array
        AtomicInteger indexAtAnalysis = new AtomicInteger(0);
        buckets.forEach(bucket -> {
            for (E element : bucket) {
                externalSource[indexAtAnalysis.getAndIncrement()] = element;
            }
        });
    }

    private static <E extends Comparable<E>> void isNull(Collection<E> externalSource) throws NullPointerException {
        if (externalSource == null) {
            throw new NullPointerException("externalSource is Null");
        }
    }

    private static <E extends Comparable<E>> void isEmpty(Collection<E> externalSource) throws IllegalArgumentException {
        if (externalSource.isEmpty()) {
            throw new IllegalArgumentException("externalSource is Empty");
        }
    }

    public static <E extends  Comparable<E>> void bucketSort(E[] externalSource, Function<E, Double> dispersionHashingFunction){
        //! Step -1: Gather internal constants and perform preflight checks
        List<E> eList = List.of(externalSource);
        isNull(eList);
        isEmpty(eList);
        int lengthOfSource = externalSource.length;

        //! Step 1: Creating the Empty Buckets based on statistics
        int numberOfBuckets = (int) Math.ceil(1 + 3.22 * Math.log10(lengthOfSource));
        ArrayList<ArrayList<E>> buckets = new ArrayList<>(numberOfBuckets);
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets.add(new ArrayList<>());
        }
        //! Step 3: Insert elements into the buckets using the dispersion hashing function
        for (E element : externalSource) {
            double hashValue = dispersionHashingFunction.apply(element);
            assert hashValue >= 0 && hashValue <= 1;
            int bucketIndex = (int) (hashValue * numberOfBuckets);
            bucketIndex = Math.min(bucketIndex, numberOfBuckets - 1); // Ensure index is within bounds
            buckets.get(bucketIndex).add(element);
        }
        //! Step 4: Sort individual buckets
        buckets.forEach(bucket -> {
            MinHeapWithArrayList<E> sorting;
            try {
                System.out.println(bucket);
                sorting = new MinHeapWithArrayList<>(bucket);
                bucket.clear();
                while (!sorting.isEmpty()) {
                    bucket.add(sorting.removeRetrievesRoot());
                }
            } catch (IllegalArgumentException _){}

        });

        //! Step 5: Concatenate all buckets into a single array
        AtomicInteger indexAtAnalysis = new AtomicInteger(0);
        buckets.forEach(bucket -> {
            for (E element : bucket) {
                externalSource[indexAtAnalysis.getAndIncrement()] = element;
            }
        });

    }
}

```
</body>
</blockquote>
<p>The two previous implementations provide ways of managing objects which are either: numbers (which then would benefit
from the Histogram-based generic bucket sort algorithm), or general classes that extend Comparable (which would benefit from 
the second method, however, this would also require a hashing function defined for the specific sorting needed. One 
improvement that could be done here is extending both the backend (Min Heap) and the front end (Bucket Sort) to handle
those classes that do not implement Comparable and only provide Comparator.
</p>
</li>
</ul>
</blockquote>
<p>Lastly, we should discuss the complexity of this algorithm. Given that we are using a minHeap, we are tied to the 
complexity of heap sort, i.e., <code>O(nlog(n))</code>, that is it. Now we can add the pros an cons for bucket sort</p>
<body>
<body>

```plantuml

package ProsAndConsBucketSort {

interface BucketSort

BucketSort : [1] **Linear Time Complexity**: can achieve O(n) time complexity on average for uniformly distributed data.
BucketSort : [2] **Distributed Sorting**: segments data into buckets, which allows for parallel processing.
BucketSort : [3] **Simple and Intuitive**: straightforward to implement and understand when dealing with a known distribution.
BucketSort : [4] **Efficient for Large Data**: very effective when dealing with large datasets of uniformly distributed data.
BucketSort : [5] **Scalable**: performance can be improved by increasing the number of buckets.

BucketSort : (1) **Not In-Place**: requires additional memory for the buckets, leading to higher space complexity.
BucketSort : (2) **Data Distribution Dependent**: performance depends heavily on the distribution of input data.
BucketSort : (3) **Pre-Sorting Requirement**: may require prior knowledge of the data range and distribution.
BucketSort : (4) **Overhead of Bucket Management**: managing and sorting individual buckets can introduce additional overhead.
BucketSort : (5) **Less Effective for Uniform Data**: if the data is not uniformly distributed, bucket sort may perform poorly.
}
```
</body>
</body>
</li>
<!--! A comment to Separate them all -->
<li><code style="color: cornflowerblue; font-weight: bold">"Counting Sort"</code>:
<p>Counting sort is an algorithm which could be considered a superset of radix. It works on the same theoretical basis, 
i.e., breaking down an array into different buckets of numbers, however, counting sort (also known as bucket sort) differs
in the way it handles these large buckets. While radix sort used just <i>10 buckets (one for each digit)</i>, bucket sort 
works by creating a number of buckets comparable to <b>the highest value in the original unsorted array</b>. This means that 
in the case of bucket sort our iterations might grow to inefficiently large amount of memory and time usage if we were to 
apply it to a large array of numbers. Conversely, radix sort controls memory usage.</p>
<br><p>Given that this algorithm is more of a generalization of radix sort, we will only take a look at the implementations
for both C++ and Java</p>
<blockquote style="font-style: italic; color: black"> 
<ul>
<code>Implementations</code>
<li><b style="color: cornflowerblue; font-weight: bold">"C++"</b>: 
<body>

```C++
void countingsort(long data[], const long n) {
	 long i;
	 long largest = data[0];
	 long *tmp = new long[n];
	 for (i = 1; i < n; i++)		 // find the largest number
	 	 if (largest < data[i])	 // in data and create the array
	 	 	 largest = data[i];	 // of counters accordingly;
	 unsigned long *count = new unsigned long[largest+1];
	 for (i = 0; i <= largest; i++)
	 	 count[i] = 0;
	 for (i = 0; i < n; i++) // count numbers in data[];
	 	 count[data[i]]++;
	 for (i = 1; i <= largest; i++) // count numbers ≤ i;
	 	 count[i] = count[i-1] + count[i];
	 for (i = n-1; i >= 0; i--) {	 // put numbers in order in tmp[];
	 	 tmp[count[data[i]]-1] = data[i];
	 	 count[data[i]]--;
	 }
	 for (i = 0; i < n; i++)		 // transfer numbers from tmp[]
	 	 data[i] = tmp[i];		 // to the original array;
	 }
```
</body>
<p>As can be noted through the implementation below, we would have three different arrays in here. One array for the count,
another for the temporal values, and another for the original data. The count array is initialized to be the length of the largest value plus one,
this in turn would give us an array of n elements from 0 to largest where each value is updated depending on the value found in the array 
for a given index i, from 0 to the length of the original array. Once this is done, we go ahead and cumulatively move the count
over.
<br><br>
To do this, we cumulatively add elements. First we define a loop in which we go from 1 to the largest element we have, 
adding up the counts of the index before it and the one we are in and effectively making the relative position of i in the 
original array to be present in count[i]. With this and then our transfer of information into the temp array, we finally move
the data over into the original array now sorted and we are done.
</p>
</li>
<li><b style="color: cornflowerblue; font-weight: bold">"Java:</b>: 
<body>

```java
import java.util.Arrays;

static void countingSort(long[] data, final int size) {
    int i;
    long largest = data[0];
    long[] tmp = new long[size];
    //! Finding the largest subarray to create the count array
    for (i = 1; i < size; i++) {
        if (largest < data[i]) {
            largest = data[i];
        }
    }
    //! Creating and Generating the array count
    long[] count = new long[(int) (largest + 1)];
    Arrays.fill(count, 0);
    //! Updating the count given their appearance in the data array
    for (i = 0; i < size; i++) {
        count[(int) data[i]]++;
    }
    //! Count numbers <=1
    for (i = 1; i <= largest; i++) {
        count[i] = count[i - 1] + count[i];
    }
    //! Put the numbers in the temp array
    for (i = size - 1; i >= 0; i--) {
        tmp[(int) count[(int) (data[i] )]- 1] = data[i];
        count[(int) data[i]]--;
    }
    //! Transfer the numbers in their ordering to tmp[]
    for (i = 0; i < size; i++) {
        data[i] = tmp[i];
    }
}
```
</body>
</li>
</ul>
</blockquote>
</li>
<!--! A comment to Separate them all -->
<li><code style="color: cornflowerblue; font-weight: bold">"Heap Sort"</code>:
<p>Another interesting algorithm to know is the Heap Sort algorithm. It works on the basis of binary trees, specifically
complete binary trees. These trees are designed to have the highest element to be the head at all times, and the left 
and right child nodes of the root will always be smaller, therefore ensuring that the three goes down in size of elements
while guaranteeing that during extraction the elements will come out in descending order.
<br>
To further comprehend the notions behind this sorting algorithm, it would be wise to take a look at the <code>Heap</code>
as a data structure presented in the book. For this, I turn to our trusted blockquote introductions</p>
<blockquote style="font-style: italic; color: whitesmoke"> 
<q>Thinking about Heaps and Implementations</q>
<br>
<p>As mentioned earlier, a heap is a data structure that is based on the concepts of full binary trees. These types of 
binary trees can be defined as balanced <code> in the sense that each of its branches must be full or at least every left
children node has to be full of some value.</code>
<br>
Behind the notion of full binary trees, there are other notions too, to be listed below.
</p>
<blockquote style="font-style: italic; color: whitesmoke"> 
<ul>
<code>Considerations of Binary Tres in General</code>
<li><b style="color: cornflowerblue; font-weight: bold">Main Definition</b>: A hierarchical structure which <code>
consists of an element called the root, that has two distinct binary subtrees, the right and left subtrees</code>.</li>
<li><b style="color: cornflowerblue; font-weight: bold">Length of a path</b>: Defined as the total number of edges (i.e., 
connections) in the path</li>
<li><b style="color: cornflowerblue; font-weight: bold">Depth of a node</b>: Defined as the total amount of nodes 
between the root node and the node in question.</li>
<li><b style="color: cornflowerblue; font-weight: bold">Main Properties</b>: 
<ul>
<li><b style="color: cornflowerblue; font-weight: bold">Shape Property</b>: It must be a complete binary tree</li>
<li><b style="color: cornflowerblue; font-weight: bold">Heap Property</b>: Every node is greater than or equal to their
children</li>
</ul>
</li>
</ul>
</blockquote>
</blockquote>
<p>Having defined the main concepts behind binary trees in general. We ought to look at the way we implement a heap and the
operations that are meant to be done behind the scenes to order the nodes in such a way that a root's children are always 
smallert or equal to it, while also keeping the tree balanced and making the children be sorted too.
<br><br>
To do this, we will rely on the definition of various operations that a Heap has to uphold, their pseudocode and an
analysis of each. After this we will present implementations for heaps both as LinkedLists and ArrayLists (this example will
come from the book).
</p>
<blockquote style="font-style: italic; color: whitesmoke"> 
<q>Thinking about Heap's Methods</q>
<ul>
<li><b style="color: cornflowerblue; font-weight: bold">Adding Elements</b>: 
<p>When it comes to adding elements, there are two main cases we have to consider. There might be elements already present 
in the backend data structure, or there may be zero elements in the data structure. Whichever the case may be, we ought to 
be able to sort the items in a way that maintains the initial promise of the heap <code>every children must be less 
than or equal to their parents</code>.</p>
<br>
<p>Generally, within the graphs that one can see for heaps, the children do not need to be organized directly. This will be discussed
further when we think about removing elements and how we retrieve the ordered data structure. For now, let us only worry about
adding elements. The following pseudocode can be used to understand the addition method</p>
<blockquote style="font-style: italic; color: whitesmoke"> 
<body>

```text
Add the new node to the end of the internal data structure;
Let the last node be the current node;
while (the current node is greater than its parent)
{
    Swap the current node with its parent
    Now the current node is one level up;
}
```
</body>
</blockquote>
<p>If we take a look at the code, we can notice that the algorithm works by following a chain of steps which could 
be analyzed as being the reverse of a depth-first search. The comparison grows stronger when one thinks of the way the 
node does not check for it being larger than its siblings, rather it only checks for it being larger than its parent or not.
<br><br>
This is a clear parallel of DFS given that it analyzes a single branch of computation until it reaches the end node of a given 
branch. In our case, adding elements to the heap forces us to go from the lowest level of the branch, up to the level in which
the new parent is larger than the node in question.</p>
<p>
This method seems simple enough, the only complication that can be visible is the way in which we swap the data points, in
C++ we would use the function swap to swap memory addresses of nodes. However, this also depends on the data structure and the language used.
Moreover, if we were to use a linked structure, would this mean changing entire references, or would it only mean swapping data around.
<br><br>
To a certain extent, this operation seems more logical to implement with a linked list with updated nodes to keep pointers to their
left and right children. However, this would also present issues during iteration to even find the nodes, moreover, this would
raise issues in the way the nodes are organized together, comparator implementations, etc. 
<br><br>
As a method definition, it seems simple, but the backend implementation raises concerns.
</p>
</li>
<li><b style="color: cornflowerblue; font-weight: bold">Removing Elements</b>:
<p>One key operation that a heap has is the removal of elements. Elements are removed from the root down, i.e., the first element
and the last element to be removed will be the root. As we know, the root of the tree is basically a holder for the largest
element as the heap is ordered, for this reason, we can be certain that the tree will be returned to us, as we remove elements, in a 
descending order.
<br><br>
The main way we can do this is discussed in the following pseudocode.
</p>
<blockquote style="font-style: italic; color: whitesmoke"> 
<body>

```text
Remove the root of the tree;
Move the last node to replace the root;
Let the root be the current node;
while (the current node has children and the current node is
        smaller than one of its children) {
            Swap the current node with the larger of its children;
            Now the current node is one level down;
}
```
</body>
</blockquote>
<p>If we take a look at the previous pseudocode, it becomes clear that this algorithm is now executing a kind of breath first
search adaptation on a subtree formed by the last node and its children, per level of iteration the subtree is replaced
by our original node in its new position and its new children. 
<br><br>
We can see that first, we begin by removing the root of the tree (this could be an issue given that in a linked list implementation
would require us to have references to the children which could be lost if not handled properly). Once the root of the tree
is removed, we move to bring up the last of the items in the heap up to be the head. Of course this does not guarantee that the value
we are selecting is completely ordered w.r.t the rest of the elements in the heap, rather it only tells us that we would most
likely have a partial ordering, i.e., we will most likely arrive at a level in which the new head is the largest element.
<br><br>
The replacement operations are done with every iteration as we move the node we assumed to be the header in the following way.
<body>
        
            - First we look at the new element, and we move it so long as any of its children are smaller
                - Internally we review its children and select the higher one among the two, this one will be moved to 
                  be the new head.
</body>
As can be noted through the previous analysis, we are doing a type of BFS in which the only nodes we are checking are those
formed by the subtree of our tree and its children, i.e., the entire tree as we iterate down.
</p>
</li>
</ul>
</blockquote>
<p>These are the two main operations that must be supported inside a heap. This means that we are now ready to
</p>
<ul>
<li><b style="color: cornflowerblue; font-weight: bold">Analyze</b>: Daniel Liang's implementation of Heaps using ArrayLists</li>
<li><b style="color: cornflowerblue; font-weight: bold">Introduce</b>: Our own implementation using linked lists (I will try!) </li>
</ul>
<blockquote style="font-style: italic; color: whitesmoke"> 
<ul>
<code>Heap Implementations</code>
<li><b style="color: cornflowerblue; font-weight: bold">Max Heap with an ArrayList Backend</b>: 
<p>The book chose to use an Array list because there are mathematical models already developed for ways of storing the 
children nodes and the root nodes in an array list. Of course, this means that we would need to know <b><code>the size
of the array we would need beforehand, i.e., the number of values to sort</code></b>. Moreover, this implementation
delieantes us to use contiguous memory, which might not be efficient for larger data sources.
<br><br>
The mathematical model can be defined as such:
</p>
<body>
<code>For any value of i</code>

$$ 
left-child = 2i + 1 
$$
$$
right-child = 2i + 2
$$
$$
parent = (i-1) / 2
$$
</body>
<p>Based on this mathematical model, we can see that for <code>an ArrayList implementation, <b>the root would be at index zero,
whilst its left child would be at index 1, and its right child at index 2</b></code>. This means that these formulas can be
used not only to find any children if we know the head node position, but it could also be used as a check for loops.
</p>
<p>If we push the value of i to be close or the exact length of the list, we know that both children will be out of bounds, 
therefore through this we can check if we have arrived or iterated over the entire list already.</p>
<br>
<p>With this introduction, we are now equipped to tackle the implementation of a Max Heap (where all nodes are larger than or 
equal to any of their children.</p>
<blockquote style="font-style: italic; color: black"> 
<body>

```java
package maxHeapStruture;

import java.util.ArrayList;
import java.util.Collection;

public class HeapMaximus<E extends Comparable<E>> {
    //! Internally, we use an ArrayList to store our numbers;
    private ArrayList<E> data = new ArrayList<>();

    //! Let us define some constructors

    /**
     * Provides a way to construct a Max Heap through an already defined list of values
     * @param externalSource external source of data points for this constructor. Argument must extend <code>Collection < E > </code>
     **/
    public HeapMaximus(Collection<E> externalSource) throws NullPointerException, IllegalArgumentException {
        if (externalSource == null) {
            throw new NullPointerException("Parameter passed into externalSource is null");
        }
        if (externalSource.isEmpty()) {
            throw new IllegalArgumentException("Parameter passed into externalSource is empty");
        }
        //! Move the elements with an iterator
        for (E e : externalSource) {
            this.addElement(e);
        }
    }

    //? No Argument constructor
    public HeapMaximus() {
    }

    //? Adding method
    public void addElement(E elementToAdd) {
        //! Add the element directly into the list
        this.data.add(elementToAdd);
        //! Proceed with the sorting mechanism to organize elements
        int indexAtAnalysis = this.data.size() - 1; //? Index of the last element since this method works by ordering from the last
        while (indexAtAnalysis > 0) /*i.e., we have not gone past the last element*/ {
            int parentOfIndexAtAnalysis = (indexAtAnalysis - 1) / 2;
            if (this.data.get(indexAtAnalysis).compareTo(this.data.get(parentOfIndexAtAnalysis)) > 0) {
                E temporal = this.data.get(indexAtAnalysis);
                this.data.set(indexAtAnalysis, this.data.get(parentOfIndexAtAnalysis));
                this.data.set(parentOfIndexAtAnalysis, temporal);
            } else {
                break;
            }

            indexAtAnalysis = parentOfIndexAtAnalysis;
        }
    }

    /**
     * Method that allows for the returning of the root node in this heap. After this it executes a piece of code which will sort all nodes
     * again.
     * @return E: last value stored at the root node
     */
    public E removeRetrievesRoot() {
        if (this.data.isEmpty()) {
            return null;
        } //! If no elements are inside, we return null, and that is our end    

        E removedObject = this.data.getFirst();
        this.data.set(0, this.data.getLast());

        int indexAtAnalysis = 0;
        while (indexAtAnalysis < this.data.size()) {
            int leftChildIndex = (2 * indexAtAnalysis) + 1;
            int rightChildIndex = (2 * indexAtAnalysis) - 1;

            //! Find the maximum between the two children
            if (leftChildIndex >= this.data.size()){break;} //! The tree is a heap?
            int maxIndex = leftChildIndex;
            if (rightChildIndex < this.data.size()) {
                if (this.data.get(maxIndex).compareTo(this.data.get(rightChildIndex) ) < 0) {
                    maxIndex = rightChildIndex;
                }
            }

            //!Swap the current node if it's less than the max of the two children.
            if (this.data.get(indexAtAnalysis).compareTo(this.data.get(maxIndex) ) < 0) {
                E temp = this.data.get(maxIndex);
                this.data.set(maxIndex, this.data.get(indexAtAnalysis));
                this.data.set(indexAtAnalysis, temp);
                indexAtAnalysis = maxIndex;
            }
            else {break;} //! Tree is a heap
        }
        return removedObject;
    }
    
    //? Helper method for getting the size
    public Integer getSize(){
        return this.data.size();
    }
    
    //? Helper method to know if the list is empty
    public Boolean isEmpty(){
        return this.data.isEmpty();
    }
}
```

</body>
<p>As can be noted in the previous implementation, the contract this structure uses is somewhat vague and straightforward to implement as
it is not exactly tied to this implementation. Moreover, we can easily change this one to make it so that the data structure
is a minimum heap (where all elements are greater than the root.</p>
<br>
<p>This is the basis of the heapsort algorithm which can be used generally through this implementation alone. The idea for 
the sorting is to retrieve the elements using the removeRetrievesRoot() method to pop the highest elements one by one. 
This then returns a descending order sorted collection. While this implementation provides a Collection < E > overloading,
it might be prudent to only use ArrayLists or arrays before we test this for other data structures. Moreover, this data structure
assumes that the data values extend Comparable.</p>
</blockquote>
</li>
<li><b style="color: cornflowerblue; font-weight: bold">Max Heap with Linked Nodes Backend</b>: 
<body>

```java
package HeapImplementations;

import DoubleLinkedList.DoubleLinkedList;

import java.util.Collection;
import java.util.List;

/**
 * The present file will allow the user to interact with a max heap, which underneath has a linked list
 * (my Double Linked List Implementation for that matter), the sorting operations will be handled much in the same
 * way as the array list implementation is done. The main difference will be in iteration times, and a better
 * memory usage profile.
 */
public class MaxHeapWithLinkedList<E extends Comparable<E>> {
    //! Define the internal data structure
    DoubleLinkedList<E> data = new DoubleLinkedList<E>();

    /**
     * Constructor method which allows the user to create an instance of this heap based on a collection passed into it.
     * @param externalSource: <code> Collection< E ></code> extending structure which provides iterator support underneath.
     *
     */
    public MaxHeapWithLinkedList(Collection<E> externalSource){
        if (externalSource == null) {
            throw new NullPointerException("Parameter passed into externalSource is null");
        }
        if (externalSource.isEmpty()) {
            throw new IllegalArgumentException("Parameter passed into externalSource is empty");
        }
        //! Move the elements with an iterator
        for (E e : externalSource) {
            this.addElement(e);
        }
    }

    /**
     * No argument constructor
     */
    public MaxHeapWithLinkedList(){}

    /**
     * Method for adding an element straight into the heap implementation. It will leverage internally the double iteration
     * available in the Double Linked List Implementation used, iteration and index formation
     * @param elementToAdd: element to be added to the heap
     * @return void
     */

    public void addElement(E elementToAdd) {
        //! Base Step: Add the element into the underlying data structure
        this.data.add(elementToAdd);
        //! Inductive Step: Proceed with sorting mechanism to organize the newly formed tree
        int indexAtAnalysis = this.data.size() -1; //? Index of the last element
        while (indexAtAnalysis > 0){
            int parentOfIndexAtAnalysis = (indexAtAnalysis - 1)/2;
            if (this.data.get(indexAtAnalysis, true)
                    .compareTo(this.data.get(parentOfIndexAtAnalysis, true)) > 0){
                E temporalValue = this.data.get(indexAtAnalysis, true);
                this.data.set(indexAtAnalysis,
                        this.data.get(parentOfIndexAtAnalysis, true),
                        true);
                this.data.set(parentOfIndexAtAnalysis, temporalValue, true);
            }
            else {break;}
            indexAtAnalysis = parentOfIndexAtAnalysis;
        }

    }
    //? Public method to removeTheLast Element
    public E removeRetrievesRoot(){
        if (this.data.isEmpty()){
            return null;
        }

        //! Inductive Step: Perform base heap operations on each subtree
        E removedObject = this.data.getFirst();
        this.data.set(0, this.data.getLast(), true);
        this.data.removeLast();

        int indexAtAnalysis = 0;
        while (indexAtAnalysis < this.data.size()){
            int leftChildIndex = (2*indexAtAnalysis) + 1;
            int rightChildIndex = (2*indexAtAnalysis) + 2;

            //! Find the maximum between the two children
            if (leftChildIndex >= this.data.size()) {break;}
            int maxIndex = leftChildIndex;
            if (rightChildIndex < this.data.size()){
                if (this.data.get(maxIndex, true)
                        .compareTo(this.data.get(rightChildIndex, true)) < 0){
                    maxIndex = rightChildIndex;
                }
            }
            //! Swap the current node if it is less than the max of the two children
            if (this.data.get(indexAtAnalysis, true)
                    .compareTo(this.data.get(maxIndex, true)) < 0){
                E temporalValue = this.data.get(maxIndex, true);
                this.data.set(maxIndex, this.data.get(indexAtAnalysis, true), true);
                this.data.set(indexAtAnalysis, temporalValue, true);
                indexAtAnalysis = maxIndex;
            }
            else {break;}
        }
        return removedObject;
    }
    
}
```
</body>
<p>The present file showcases the versatility of the implemented Doubly Linked List that I have done, 
its methods have allowed for a quick and simple translation of the arraylist methods towards a more memory efficient 
doubly linked list backed implementation. This Heap implementation can in turn be improved, <code>measuring for the distance
to the center of the linked list could be done to handle iteration from left to right or right to left.</code>. These methods
will be implemented later on, in a separate document all about trees and heaps</p>
</li>
<li><b style="color: cornflowerblue; font-weight: bold">Min Heap with ArrayList Backend</b>:
<p>This implementation was left as an exercise inside the book, however, it appeared interesting, and I figured we should 
tackle it inside this doc. The idea seems simple enough, if the previous implementation effectively sorted incoming
items in descending order, to make it ascending, we should change comparisons and algorithm sections. 
<br>
<br>
For example, in the removal, we ought not to check for the max of the children, rather for the min of the children. The 
same can be said in the add elements method, we ought to change the way the algorithm works based on its comparisons</p>
<body>

```java

package HeapImplementations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The present class is an implementation of the Heap data structure only that it is reversed. Rather than the root being the
 * largest element in the array, it will be the smallest, and we will keep the notion that any of its children must be
 * to a certain extent larger or equal to the root.
 * @param <E>: Parameter type for class instantiation; must be an implementor of <code>Comparable< E ></code>
 * @see Comparable
 */
public class MinHeapWithArrayList<E extends Comparable<E>> {

    //! Backing data structure
    ArrayList<E> data = new ArrayList<>();

    //! Constructors

    /**
     * Constructor that takes in a collection of elements and adds them to the heap.
     * @param externalSource: Collection of elements to be added to the heap.
     */
    public MinHeapWithArrayList(Collection<E> externalSource) {
        if (externalSource == null) {
            throw new NullPointerException("Parameter passed into externalSource is null");
        }
        if (externalSource.isEmpty()) {
            throw new IllegalArgumentException("Parameter passed into externalSource is empty");
        }
        //! Move the elements with an iterator
        for (E e : externalSource) {
            this.addElement(e);
        }
    }

    /**
     * Default constructor for the class.
     */
    public MinHeapWithArrayList() {
    }

    //! Add element method
    public void addElement(E elementToAdd) {
        //! Base Step: add the element into the list on its own
        this.data.add(elementToAdd);
        //! Inductive Step: proceed with the sorting mechanism to organize elements
        int indexAtAnalysis = this.data.size() - 1;
        while (indexAtAnalysis > 0) {
            int parentIndexAtAnalysis = (indexAtAnalysis - 1) / 2;
            if (this.data.get(indexAtAnalysis)
                    .compareTo(this.data.get(parentIndexAtAnalysis)) < 0) {
                E temporal = this.data.get(indexAtAnalysis);
                this.data.set(indexAtAnalysis, this.data.get(parentIndexAtAnalysis));
                this.data.set(parentIndexAtAnalysis, temporal);
            } else {
                break;
            }
            indexAtAnalysis = parentIndexAtAnalysis;
        }
    }

    //! Remove element method
    public E removeRetrievesRoot() {
        if (this.data.isEmpty()) {
            return null;
        }

        E removedObject = this.data.getFirst();
        this.data.set(0, this.data.getLast());
        this.data.removeLast();

        int indexAtAnalysis = 0;
        while (indexAtAnalysis < this.data.size()) {
            int leftChildIndex = (2 * indexAtAnalysis) + 1;
            int rightChildIndex = (2 * indexAtAnalysis) + 2;

            //! Find the minimum between the two children
            if (leftChildIndex >= this.data.size()) {
                break;
            }
            int minIndex = leftChildIndex;
            if (rightChildIndex < this.data.size()) {
                if (this.data.get(minIndex)
                        .compareTo(this.data.get(rightChildIndex)) > 0) {
                    minIndex = rightChildIndex;
                }
            }

            //! Swap the root node with the min child
            if (this.data.get(indexAtAnalysis)
                    .compareTo(this.data.get(minIndex)) > 0) {
                E temp = this.data.get(minIndex);
                this.data.set(minIndex, this.data.get(indexAtAnalysis));
                this.data.set(indexAtAnalysis, temp);
                indexAtAnalysis = minIndex;
            } else {
                break;
            }
        }
        return removedObject;
    }
}

```
</body>
<p>As can be noted through the previous implementation, most of the data structure's core functions and overall structures 
were kept. What changed was the way these were handled. As mentioned before the implementation, most comparisons had to 
be swapped in order to make this work. Despite this, I did do my work and analyzed the code beforehand instead of just 
switching symbols around. For this final implementation before we delve into the actual heap sport, I would like to make
my Double Linked List implementation as the backend for the min heap/</p>
</li>
<li><b style="color: cornflowerblue; font-weight: bold">Min Heap With Linked List Backend</b>: 
<p>As with the Max Heap, the only changes were to the data structure that lays underneath the general class. The methods,
comparisons and variable names have been kept the same for simplicity and readability.</p>
<body>

```java
package HeapImplementations;

import DoubleLinkedList.DoubleLinkedList;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Collection;
import java.util.List;

public class MinHeapWithLinkedList <E extends Comparable<E>>{

    //! Underlying data structure
    private DoubleLinkedList<E> data = new DoubleLinkedList<E>();

    //! Constructor Implementations

    /**
     * Constructor that takes in an external source of elements to be added to the heap
     * @param externalSource: Collection<E> extending class which provides the external data source
     * @see Collection
     * @throws NullPointerException if externalSource is null
     * @throws IllegalArgumentException if externalSource is empty
     * @apiNote : The Collection passed into the constructor must provide an iterator implementation accessible through the
     * <b>iterator() method of the Collection interface</b>
     */
    public MinHeapWithLinkedList(Collection<E> externalSource)
            throws NullPointerException, IllegalArgumentException {
        if (externalSource == null) {
            throw new NullPointerException("Parameter passed into externalSource is null");
        }
        if (externalSource.isEmpty()) {
            throw new IllegalArgumentException("Parameter passed into externalSource is empty");
        }
        //! Move the elements with an iterator
        for (E e : externalSource) {
            this.addElement(e);
        }
    }

    /**
     * Default constructor
     * @apiNote : This constructor is used when no external data source is provided
     * @implNote  : The underlying data structure is a DoubleLinkedList
     */
    public MinHeapWithLinkedList(){}

    //? Adding methods to the linked list
    public void addElement(E elementToAdd){
//! Base Step: Add the element into the underlying data structure
        this.data.add(elementToAdd);
        //! Inductive Step: Proceed with sorting mechanism to organize the newly formed tree
        int indexAtAnalysis = this.data.size() -1; //? Index of the last element
        while (indexAtAnalysis > 0){
            int parentOfIndexAtAnalysis = (indexAtAnalysis - 1)/2;
            if (this.data.get(indexAtAnalysis, true)
                    .compareTo(this.data.get(parentOfIndexAtAnalysis, true)) < 0){
                E temporalValue = this.data.get(indexAtAnalysis, true);
                this.data.set(indexAtAnalysis,
                        this.data.get(parentOfIndexAtAnalysis, true),
                        true);
                this.data.set(parentOfIndexAtAnalysis, temporalValue, true);
            }
            else {break;}
            indexAtAnalysis = parentOfIndexAtAnalysis;
        }

    }

    public E removeRetrievesRoot(){
        if (this.data.isEmpty()){
            return null;
        }

        //! Inductive Step: Perform base heap operations on each subtree
        E removedObject = this.data.getFirst();
        this.data.set(0, this.data.getLast(), true);
        this.data.removeLast();

        int indexAtAnalysis = 0;
        while (indexAtAnalysis < this.data.size()){
            int leftChildIndex = (2*indexAtAnalysis) + 1;
            int rightChildIndex = (2*indexAtAnalysis) + 2;

            //! Find the maximum between the two children
            if (leftChildIndex >= this.data.size()) {break;}
            int maxIndex = leftChildIndex;
            if (rightChildIndex < this.data.size()){
                if (this.data.get(maxIndex, true)
                        .compareTo(this.data.get(rightChildIndex, true)) > 0){
                    maxIndex = rightChildIndex;
                }
            }
            //! Swap the current node if it is less than the max of the two children
            if (this.data.get(indexAtAnalysis, true)
                    .compareTo(this.data.get(maxIndex, true)) > 0){
                E temporalValue = this.data.get(maxIndex, true);
                this.data.set(maxIndex, this.data.get(indexAtAnalysis, true), true);
                this.data.set(indexAtAnalysis, temporalValue, true);
                indexAtAnalysis = maxIndex;
            }
            else {break;}
        }
        return removedObject;
    }


    //? Helper method for getting the size
    public Integer getSize(){
        return this.data.size();
    }

    //? Helper method to know if the list is empty
    public Boolean isEmpty(){
        return this.data.isEmpty();
    }
    
}

```
</body>
</li>
</ul>
<p>Having presented all four implementations for this algorithm's backend data structure, let us present two ways of sorting
data. These two ways are:
</p>
<ul style="list-style: -moz-japanese-formal">
<li>Using Max Heap Sort and mapping back reversed values</li>
<li>Using Min Heap Sort and mapping back normal values</li>
</ul>
</blockquote>
<p>These two implementations will be shown here in the same file. These two will use the Linked List implemetation
(because I am proud of it), but they could use the array list implementation too.</p>
<blockquote style="font-style: italic; color: black"> 
<p><q>Thinking About HeapSort Implementations</q></p>
<body>

```java
public class HeapSort {

    public static <E extends Comparable<E>> void minHeapSort(E[] externalSource) {
        //! Create a new instance with the data
        MinHeapWithLinkedList<E> dataSorted = new MinHeapWithLinkedList<>();
        Arrays.stream(externalSource).forEach(dataSorted::addElement);
        //! Map sorted to the original array
        for (int i = 0; i < externalSource.length; i++) {
            externalSource[i] = dataSorted.removeRetrievesRoot();
        }
    }

    public static <E extends Comparable<E>> void maxHeapSort(E[] externalSource) {
        //! Create a new instance with the data
        MaxHeapWithLinkedList<E> dataSorted = new MaxHeapWithLinkedList<>();
        Arrays.stream(externalSource).forEach(dataSorted::addElement);
        //! Map sorted to the original array
        for (int i = externalSource.length - 1; i >= 0; i--) {
            externalSource[i] = dataSorted.removeRetrievesRoot();
        }
    }
}
```
</body>
</blockquote>
<p>This was the last method implementation for this precise algorithm. As can be noted, both min heap and max heap can be 
used with only varying syntactical elements like <code>ordering from zero to n, or from n-1 to zero</code>. Moreover, 
the implementations both for the Heaps and the algorithms allow the user to pass in any type generic so long as it
implements at least the comparable <code>compareTo(E other)</code> method.</p>
<br><p>Now let us list the complexity for this algorithm. Given that we have used internally heapsort, our complexity then
is the one of heapSort. This means that Heap Sort is O(nln(n)).
<br><br>
Lastly, we present the pros and cons for this algorithm
</p>

</li>
</ul>