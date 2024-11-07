<br>
<h1 style=" color: cornflowerblue; text-align: center; font-family: 'Consolas', sans-serif;">
Data Structure and Algorithms | Elementary Sorting Algorithms | USFQ | Santiago Arellano
</h1>


***
<ul style="font-family: 'Consolas', sans-serif;">
<code >Main Information Section</code>
<li><b style="color: cornflowerblue; font-weight: bold">Date:</b>: September 30th, 2024. </li>
<li><b style="color: cornflowerblue; font-weight: bold">Unit</b>:  Unit Four</li>
<li><b style="color: cornflowerblue; font-weight: bold">Description</b>: This file pertains the following contents: 
<code><b>Elementary Sorting Algorithms</b></code>.</li>
</ul>

***
<br>
<h3 style=" color: cornflowerblue; text-align: center; font-family: 'Consolas', sans-serif;">
"Insertion Sort ", "Selection Sort ", "Bubble Sort ", "Comb Sort"
</h3>
<ul style="font-family: Consolas, sans-serif">
<li><code style="color: cornflowerblue; font-weight: bold">"Elementary Sorting Algorithms"</code>:
<p>The following algorithms provide a basic view of the ways an array of elements can be sorted into order. Generally 
these algorithms work to order an array in ascending order, i.e. lowest in the first index and highest in 
the index <code>.length() -1</code>. Most of the following algorithms run in complexities that are not ideal, 
they are not linear, let alone logarithmic. Rather they are most of the time <code> O(n^2)</code>, with only 
some changing to linear complexity in their worst or best case scenario.</p>
<br>
<p>Moreover, we should consider that in the presentation of implementations, I will be presenting first a C++ equivalent 
code, and then translate to java as an exercise. These algorithms were taken from the book <i>Data Structures And 
Algorithms in C++</i> by Adam Drozdek. An analysis of their best, worst, and average case complexities will also be 
presented as a way to illustrate their operational behavior. Lastly, AI technologies will be used as with any study session
to find information about use cases, pros, and cons, summarized in uml diagrams</p></li>
<!--! A comment to Separate them all -->
<li ><code style="color: cornflowerblue; font-weight: bold">"Insertion Sort"</code>:
<p>One of the most basic algorithms presented in the book. This algorithm works in the following way</p>
<blockquote style="font-style: italic; color: black">Logic Behind Insertion Sort:
<br>
<q>Provided a length which allows for a n-1 comparisons, it begins to execute in the following
pattern
<ul style="list-style-type: -moz-japanese-formal;">
<li>Considers the elements <code>data[0] and data[1]</code>, and compares them.
<ul>
<li>If data[1] > data[0] it stays in its position for now,</li>
<li>If data[0] > data[1] we switch the positions moving data[1] to position 0 and data[0] to 1</li>
</ul></li>
<li>Once this base step has passed, it adds in the comparison data[3] and attempts to place it correctly, depending
on the results of internal comparisons done to all  <code>elements before it</code></li></ul></q>
</blockquote>
<p>Through a simple method, this algorithm guarantees that every item <b>will be considered and ordered with 
respect to all the ones behind it.</b><br>For this reason, this algorithm ensures that <code>any element data[i]
 will be inserte into <b>its proper location j</b>, such that 0<=j<=i. Meanwhile all elements greater than data[i] are
moved by one position above</code>.<br><br>The following pseudocode demonstrates textually its inner-workings</p>
<blockquote style="font-style: italic; color: black">
<body>

```text
insertionSort(data[], int size)
    for i = 0 to size - 1:
        move all elements data[j] greater than data[i] by one position;
        place data[i] in its proper position;
```
</body>
</blockquote>
<p>As can be noted through the pseudocode, we are talking about moving all elements up, sequentially, creating a large 
loop of calls which move items less than our data[i] until we find the location at which it is no longer smaller, and boom
insert it. This mechanism can be viewed better through the following C++ code, and later translation into Java</p>
<blockquote style="font-style: italic; color: black"> 
<ul>
<code>Implementations</code>
<li><b style="color: cornflowerblue; font-weight: bold">"C++"</b>: 
<body>

```c++
template<Class T>
void insertionSort(T data[], int size)
{
    for(int i = 0, j; i < size; i++)
    {
        T temporalValue = data[i];
        for(j = i; j > 0 && data[j-1] > temporalValue; j--)
        {
            data[j] = data[j-1]
        }
        data[j] = temporalValue;
    }
}
```
</body>
</li>
<li><b style="color: cornflowerblue; font-weight: bold">"Java"</b>: 
<body>

```java
public <T extends Comparable<T>> void insertionSort(T[] data, Integer size)
{
    //! Opening main loop logic
    for(int i=1,j ; i < size; i++)
    {
        T temp = data[i];
        for(j = i; j > 0 && temp.compareTo(data[j-1]) < 0; j--)
        {
            data[j] = data[j-1];
        }
        data[j] =temp;
    }
}

```
</body>
</li>
</ul>
</blockquote>
<p>After defining the methods, while we do not want to delve into the way the complexity has been calculated through, 
we will present it here for completeness.
</p>
<blockquote style="font-style: italic; color: black"> 
<ul>
<code>Complexities</code>
<li><b style="color: cornflowerblue; font-weight: bold">Best Case Scenario O(n)</b>: Measured to be this due to 
no swap operations needing to be done in the second loop, rather only n-1 comparisons in the outer loop are done.
</li>
<li><b style="color: cornflowerblue; font-weight: bold">Average Case Scenario O(n^2) </b>: Measured to be this due to 
having to execute n^2/4 iterations in the average case even if the array is completely randomized and in disorder.</li>
<li><b style="color: cornflowerblue; font-weight: bold">Worst Case Scenario O(n^2)</b>: Measured in the case all comparisons
in the inner array have to be done, and the array is in <b>reverse order</b></li>
</ul>
</blockquote>
<p>Lastly, we present a list of pros and cons for the algorithm.</p>
<body>

```plantuml

package ProsAndConsInsertionSort {

interface InsertionSort

InsertionSort : [1] **Simple Implementation**: easy to implement and understand.
InsertionSort : [2] **Efficient for Small Data Sets**: performs well with small or nearly sorted data.
InsertionSort : [3] **Stable Sort**: maintains the relative order of equal elements.
InsertionSort : [4] **In-Place Sorting**: doesn't require extra memory beyond the input array.
InsertionSort : [5] **Online Algorithm**: can sort a list as it receives elements.

InsertionSort : (1) **Inefficient for Large Data Sets**: has a time complexity of O(n^2) in the average and worst cases.
InsertionSort : (2) **Not Suitable for Unsorted Large Data Sets**: performance degrades significantly with large, unsorted data.    
InsertionSort : (3) **Limited Practical Usage**: often outperformed by more advanced algorithms like Quick Sort, Merge Sort, and Heap Sort on larger datasets.
}

```
</body>
</li>
<!--! A comment to Separate them all -->
<li><code style="color: cornflowerblue; font-weight: bold">"Selection Sort"</code>:
<p>Selection sort is another elementary algorithm for sorting datasets, especially arrays of data. The algorithm works in
the following way</p>
<blockquote style="font-style: italic; color: black"> 
Logic Behind Selection Sort:
<q>Considering an array which provides a length n.
<ul style="list-style-type: -moz-japanese-formal;">
<li>Grab the first element, i.e., data[i] starting from zero and compare it with all the other elements until data[n].
<ul>
<li>We grab this element and move it to the first location in the array</li></ul>
</li>
<li>From the remainder data[i+1] to data[n-1] repeat the same process moving each element to its correct position by 
finding the smallest element in the subarray.</li>
<li>This process moves from 0 to n-2 given that in general, we assume that through all the iterations done beforehand the
last element in the array will always be the largest.</li>
</ul></q>
</blockquote>
<p>Through the logic explained before, it is clear that the mathematical model works by comparing elements together, 
in subgroups of the array updated when an element is moved to its correct position, to produce an ordered set of
elements. The conditional about the last element its clear, given that if we have <code>n elements, assuming we do n-1 
comparisons and transpositions of data, then the last element will ultimately be the largest, or an element will be 
pushed through these changes to be the largest</code>
<br>
Having defined in words what it does, it might be better to see the pseudocode.
</p>
<blockquote style="font-style: italic; color: black"> 
<body>

```text
selectionSort(data[], size)
    for i =0 to n-2
        select the smallest element among data[i], ... , data[n-1];
        swap it with data[i];
```
</body>
</blockquote>
<p>As before, the pseudocode is clear in that it <b>selects the smallest element from a subset
of indices created from a subarray from i to n-1, i.e. to the end of the array, and through these subarrays it
attempts to find the smallest and swap it with data[i]</b>.This means that data[i] will always be updated
to be the first index of our subarray and the smallest item will be moved to it directly. The following
implementations will show you how this works</p>
<blockquote style="font-style: italic; color: black"> 
<ul>
<code>Implementations</code>
<li><b style="color: cornflowerblue; font-weight: bold">"C++</b>: 
<body>

```C++
template<Class T>
void selectionSort(T[] data, int size)
{
    for(int i = 0,j, least; i < size - 1; i++)
    {
        for(j = i + 1, least = i; j < size; j++)
        {
            if (data[j] < data[least]) {least = j;} 
        }
        std::swap(data[least],data[i]);
    }
}
```
</body>
</li>
<li><b style="color: cornflowerblue; font-weight: bold">"Java"</b>: 
<body>

```java
public <T extends Comparable<T>> void selectionSort(T[] data, int size)
{
    //! Main Loop from data[i] to data[n-2]
    for(int i =0,j, least; i < size -1; i++)
    {
        T temp = data[i];
        for(j = i+1, least = i; j < size; j++)
        {
            if (data[j].compareTo(data[least]) < 0){least = j;}
        }
        T temp2 = data[least];
        data[least] = temp;
        data[i] = temp2;
    }
}
```
</body>
</li>
</ul>
</blockquote>
<p>Given that we have implemented the methods, now we should take a look at the complexities.</p>
<blockquote style="font-style: italic; color: black"> 
<ul>
<code>Complexities:</code>
<li><b style="color: cornflowerblue; font-weight: bold">Worst, Best, and Average Case Scenarios O(n^2)</b>: All of these have 
been identified as being polynomial complexity and squared given that it has two nested loops which in every case have to iterate over
the entirety of the array. While they might not do any changes in cases in which the data is ordered, it still messes us up in
terms of complexity</li>
</ul>
</blockquote>
<p>Lastly, we should present some pros and cons of this algorithm.</p>
<body>

```plantuml

package ProsAndConsSelectionSort {

interface SelectionSort

SelectionSort : [1] **Simple Implementation**: easy to understand and implement.
SelectionSort : [2] **In-Place Sorting**: doesn't require extra memory beyond the input array.
SelectionSort : [3] **Good for Small Data Sets**: performs adequately for small datasets.
SelectionSort : [4] **Performance Not Dependent on Initial Order**: always has a time complexity of O{n^2}, regardless of initial order of the elements.
SelectionSort : [5] **Few Swaps**: does the minimum number of swaps; `O{n}` swaps in worst case.

SelectionSort : (1) **Inefficient for Large Data Sets**: has a time complexity of O(n^2), making it impractical for large datasets.
SelectionSort : (2) **Not Adaptive**: performance doesn't improve for partially sorted arrays.
SelectionSort : (3) **Stable Sorting Not Guaranteed**: doesn't always maintain the relative order of equal elements.
SelectionSort : (4) **Reduced Practical Usage**: usually outperformed by more advanced algorithms like Quick Sort, Merge Sort, and Heap Sort on larger datasets.
}
```
</body>
</li>
<!--!A Comment to separate them all -->
<li><code style="color: cornflowerblue; font-weight: bold">"Bubble Sort"</code>:
<p>Bubble sort is one of the weirdest 
algorithms of the bunch. It can be best understood as <b>a column of elements in which successive, two element comparisons, 
push the smallest elements to the top while keeping the largest at the bottom</b></p>
<br>
<p>Having, ever so slightly defined the nature of the algorithm, we should take a look at the behavior in words to get it
better</p>
<blockquote style="font-style: italic; color: black"> 
<ul style="list-style: -moz-japanese-formal">
<li>We begin by taking two items, data[n-1] and data[n-2] and comparing them together to check if they <b>are in
the wrong order w.r.t each other</b>, swapping them if necessary
</li>
<li>Following this check, it moves to data[n-3] and data[n-2] swapping them if necessary to make the order concise.</li>
<li>Lastly, we check all the way up to data[1] since we assume that data[0] is already ordered.</li>
</ul>
</blockquote>
<p>With this information, it is straightforward to see tha the algorithm checks from the bottom up ordering it by moving the smallest elements
to the top by virtue of its logical steps, which begin in n-1. It is clear that if we push all elements to the top directly, 
our last element will always be ordered by virtue of having been bubbled up to its position. Perhaps, the following
pseudocode will exemplify its inner-workings.</p>
<blockquote style="font-style: italic; color: black"> 
<body>

```text
bubbleSort(data[], size)
    for i = 0 to n -2
        for j = n-1 down to i + 1
            swap elements in positions j and j-1 if they are out of order;
```
</body>
</blockquote>
<p>Given this pseudocode, let us move towards the implementations we can provide both for java and C++</p>
<blockquote style="font-style: italic; color: black"> 
<ul>
<code>Implementations</code>
<li><b style="color: cornflowerblue; font-weight: bold">"C++"</b>:
<body>

```C++
template<Class T>
void bubbleSort(T[] data, int size)
{
bool again = true;
    for(int i = 0; i < size - 1 && again; i++)
    {
        for(int j = size - 1; j > i; --j)
        {
        again = false;
            if (data[j] < data[j-1])
            {
                std::swap(data[j],data[j-1]);
                again = true;
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

public static <T extends Comparable<T>> void bubbleSort(T[] data, int size)
{
    //! Main Loop
    boolean swapped = true;
    for (int i = 0; i < size - 1 && swapped; i++) {

        for (int j = size - 1; j > i; --j) {
            swapped = false;
            if (data[j].compareTo(data[j - 1]) < 0) {
                T tempJ = data[j];
                T tempJMinusOne = data[j - 1];
                //Intercambio
                data[j - 1] = tempJ;
                data[j] = tempJMinusOne;
                swapped = true;
            }
        }
    }
}

```
</body>
</li>
</ul>
</blockquote>
<p>Having defined the algorithm implementations for both languages, it would be useful for us to define their 
complexities in a little table</p>
<blockquote style="font-style: italic; color: black"> 
<ul>
<li><b style="color: cornflowerblue; font-weight: bold">Worst, Best, Average Case Scenario O(N^2)</b>: These have been
measured since the amount of comparisons and iterations done in the inner loop determine the amount of iterations done 
in the entirety of the array. This means that no matter if the data is reversed we have the same complexities.
<br><br>
However, it is important to note that this algorithm is <b>twice as slow as insertion sort</b>.</li>
</ul>
</blockquote>
<p>Finally, we should present our pros and cons list for this algorithm</p>
<body>

```plantuml

package ProsAndConsBubbleSort {

interface BubbleSort

BubbleSort : [1] **Simple Implementation**: easy to understand and implement.
BubbleSort : [2] **In-Place Sorting**: doesn't require extra memory beyond the input array.
BubbleSort : [3] **Stable Sort**: maintains the relative order of equal elements.
BubbleSort : [4] **Adaptive {with Optimization}**: can finish early if no swaps are made during a pass.

BubbleSort : (1) **Inefficient for Large Data Sets**: has a time complexity of O(n^2) in the average and worst cases.
BubbleSort : (2) **High Number of Comparisons**: makes unnecessary comparisons even if the array is partially sorted.
BubbleSort : (3) **Limited Practical Usage**: often outperformed by more advanced algorithms like Quick Sort, Merge Sort, and Heap Sort on larger datasets.
BubbleSort : (4) **Slow on Average**: generally slower than insertion sort for small arrays with random order.
}
```
</body>
</li>
<!--! A comment to separate them all -->
<li><code style="color: cornflowerblue; font-weight: bold">"Comb Sort"</code>:
<p>Our last algorithm for this section is Comb Sort, a two-phase algorithm which swaps first items with respect to their 
step distance between on another before the actual sorting can even begin. This method works well and can present, only on
the worst case a compelxity higher than that of the first phase (to be defined later). Now, we shall see the algorithm defined
in words</p>
<blockquote style="font-style: italic; color: black"> 
<body>

```text
algorithm CombSort(A):
    // INPUT
    //    A = the shuffled array
    // OUTPUT
    //    The sorted array with all its elements in increasing order

    gap <- length(A)
    isSwapped <- true

    // Keep iterating and stop if and only if the gap equals 1 and the array is fully sorted
    while gap != 1 or isSwapped:
        isSwapped <- false

        gap <- floor(gap / 1.3)

        if gap < 1:
            gap <- 1

        // Look at the subarray A[0 : N-gap-1]
        for i <- 0 to N - gap - 1:
            // Compare each pair of elements separated by a gap, swapping values if necessary
            if A[i] > A[i + gap]:
                temp <- A[i]
                A[i] <- A[i + gap]
                A[i + gap] <- temp

                isSwapped <- true

    return A
```
</body>
</blockquote>
<p>Having defined the pseudocode, we can now take a look at the implementations for both languages.</p>
<blockquote style="font-style: italic; color: black"> 
<ul>
<code>Implementations</code>
<li><b style="color: cornflowerblue; font-weight: bold"> "C++"/b>: 
<body>

```C++
template<Class T>
void combSearch(T[] data, size)
{
    //! Step One: Phase One prechecks
    int step = n,j,k;
    while ((step = int(step/1.3)) > 1)
    {
        for(j = n-1; j >= step; i--)
        {
            k = j- step;
            if (data[j] < data[k])
            {
                std::swap(data[j],data[k]);
            }
        }
    }
    bool again= true;
    for(int i = 0; i < n-1 && again; i++)
    {
        for(j = n-1; again =false; j > 1; --j)
        {
            if (data[j] < data[j-1])
            {
                std::swap(data[j], data[j-1]);
                again = true;
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
public static <T extends Comparable<T>> void combSort(T[] data) {
    int n = data.length;
    int step = n;
    int j, k;

    while ((step = (int) (step / 1.3)) > 1) {
        for (j = n - 1; j >= step; j--) {
            k = j - step;
            if (data[j].compareTo(data[k]) < 0) {
                T temp = data[j];
                data[j] = data[k];
                data[k] = temp;
            }
        }
    }

    boolean again = true;
    for (int i = 0; i < n - 1 && again; i++) {
        for (j = n - 1, again = false; j > 0; j--) {
            if (data[j].compareTo(data[j - 1]) < 0) {
                T temp = data[j];
                data[j] = data[j - 1];
                data[j - 1] = temp;
                again = true;
            }
        }
    }
}
```
</body>
</li>
</ul>
</blockquote>
<p>Having defined both implementations, it is time for us to define its complexities</p>
<blockquote style="font-style: italic; color: black"> 
<ul>
<li><b style="color: cornflowerblue; font-weight: bold">Worst Case O(n^2)</b>: which happens
during the second step of the algorithm, like bubbleSort.</li>
<li><b style="color: cornflowerblue; font-weight: bold">Average Case O(nlog(n)</b>: In the average case scenario the 
algorithm performs like QuickSort</li>
</ul>
</blockquote>
<p>Finally, we present the pros and cons as before.</p>
<body>

```plantuml

package ProsAndConsCombSort {

interface CombSort

CombSort : [1] **Improved Bubble Sort**: generally faster than Bubble Sort due to fewer comparisons.
CombSort : [2] **In-Place Sorting**: doesn't require extra memory beyond the input array.
CombSort : [3] **Simple Implementation**: relatively straightforward to understand and implement.
CombSort : [4] **Adaptive {with Optimization}**: can finish early if no swaps are made during a pass, similar to Bubble Sort.

CombSort : (1) **Not a Stable Sort**: doesn't guarantee the relative order of equal elements.
CombSort : (2) **Performance Degrades on Nearly Sorted Data**: the performance improvement over Bubble Sort reduces as the array becomes more sorted.
CombSort : (3) **Limited Practical Usage**: not commonly used in practical applications compared to more efficient algorithms like Quick Sort and Merge Sort.
CombSort : (4) **Gaps Sequence Dependent**: the choice of gap sequence can significantly influence performance.
}
```
</body>
</li>
</ul>