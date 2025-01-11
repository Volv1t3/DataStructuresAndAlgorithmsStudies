<h1 style=" color: cornflowerblue; text-align: center; font-family: 'Consolas', sans-serif;">
Data Structures and Algorithms | | USFQ | Santiago Arellano
</h1>


***
<ul style="font-family: 'Consolas', sans-serif;">
<code >Main Information Section</code>
<li><b style="color: cornflowerblue; font-weight: bold">Date:</b>: September 25th, 2024</li>
<li><b style="color: cornflowerblue; font-weight: bold">Unit</b>: Thee</li>
<li><b style="color: cornflowerblue; font-weight: bold">Description</b>: This file pertains the following 
contents: <code><b>Stacks</b></code>.</li>
</ul>

***
<br>
<h3 style=" color: cornflowerblue; text-align: center; font-family: 'Consolas', sans-serif;">
"Stack"
</h3>
<ul style="font-family: Consolas, sans-serif">
<li><code style="color: cornflowerblue; font-weight: bold">"Stack"</code>:
A stack is a given data structure where only the uppermost element is visible to the user at all times. It 
includes various methods like: <b>pop(), push(E element), or peek()</b>, which remove, add, and visualize
the element at the top respectively.  Lastly we have the <b>empty() method</b> which allows us to clear the 
entire stack.
<br><br>
<p>Internally, we can think of varied ways of implementing this structure. An example could be an array, where our
operations would interactively append values onto the 0th element, i.e., the beginning of it.
However, we could argue about using <b>single linked lists</b> in which to add an element we could use either addFirst() 
or removeFirst(), moreover, we know that the single linked list grows dynamically rather than programically like 
an arrayList does.</p>
<p>The same thing can be said about Queues, we could use the already defined single linked lists to handle their implementations,
obscuring their operations through the linked lists operations.</p>
<p>We could also attempt to implement a priority queue with single linked lists, however, the issue comes when we use the 
<code>deque()</code> method, since we would need to sort as we enter values or order the element before returning the 
given value.</p>
<p>Going back to our previous classes, in many cases we would like to iteratively analyze a string representation of a 
mathematical operation, we would like to know if there are pairs of opened and close brackets, etc. To do this we use <b>
Stacks</b>, we push the (into the stack if we see them, on he other hand if we see an ) we pop a single 
element from the stack.
<br><br>
<q>In order to have a correct, paired up expression, the stack must end up empty once the string has been analyzed. We can also
analyze mathematical strings through the use of multiple stacks.</q></p>
<p>We can also go from infix to posix mathematical notation (infix because the operator is inside the operands, and posix
because the operator is in front of the operands. Morever, we can use this same logic to transform from posix to infix and then 
operate to find a result</p>
<p>Another application could be string and file content inversion, using a stck to push items and them pull them out in reverse
order.</p>
</li>
<!--! A comment to Separate them all -->
<li><code style="color: cornflowerblue; font-weight: bold">"Ordering of Data"</code>:
<p>In our class we will look at internal sorting, meaning that all the objects are stored in a single array of memory.External ordering, 
when the information is not storable in memory, will be touched on later.</p>
<blockquote style="font-style: italic; color: black"> 
There are a series of definitions and requirements
<ul>
<li><b style="color: cornflowerblue; font-weight: bold">Comparator and Comparable </b>: these are required for the classes
that will be used inside these sorting algorithms</li>
<li><b style="color: cornflowerblue; font-weight: bold">In-situ ordering</b>: algorithms where there is no external memory used, i.e.,
we are not creating different structures to order. Rather we use temporary variables to order</li>
<li><b style="color: cornflowerblue; font-weight: bold">Stable duplicated-keys Algorithms</b>: 
means that an algorithm knows how to handle duplicate keys when comparing two, keeping their relative ordering 
from before the application of the algorithm. 
</li>
<li><b style="color: cornflowerblue; font-weight: bold">Inversion Algorithms</b>: a key whose position after 
ordering is not in the position it started in. It can be thought of as the amount of <b>position changes 
that it has to do to move to its correct location. (The distance between location before and current location after 
ordering)</b></li>
</ul>
</blockquote>
<blockquote style="font-style: italic; color: black">Consider sorting a student object, given a series of we select <b>one</b>
that could be ordered, and we use this value to order objects. The specification of ordering criteria, does not take away from
the abstract ordering concepts.
</blockquote>
<blockquote style="font-style: italic; color: black"> The reason why we analyze the ordering of data it's because some data 
objects are only important when they are ordered, according to a certain criteria. An example could be dictionaries, libraries,
etc. Often searching processes can benefit from binary search (and its more powerful brother, fibonacci binary search), 
and for these algorithms we need to have the data <b>ordered</b></blockquote>
<blockquote style="font-style: italic; color: black"> The first algorithm we will take a look at is 
<code>Insertion Sort!</code>
<blockquote style="font-style: italic; color: black"> 

![img.png](Images/SedgewickQickSortPartitionScheme.png)
</blockquote></blockquote>
</li>
</ul>