<br>
<h1 style=" color: cornflowerblue; text-align: center; font-family: 'Consolas', sans-serif;">
Data Structures and Algorithms | External Sorting Methods and Definitions | USFQ | Santiago Arellano
</h1>


***
<ul style="font-family: 'Consolas', sans-serif;">
<code >Main Information Section</code>
<li><b style="color: cornflowerblue; font-weight: bold">Date:</b>: October 14th, 2024. </li>
<li><b style="color: cornflowerblue; font-weight: bold">Unit</b>:  Unit Four</li>
<li><b style="color: cornflowerblue; font-weight: bold">Description</b>: This file pertains the following contents: <code><b>
external sorting, its basic conceptions, applications, and various effective external sorting algorithm implementation</b></code>.</li>
</ul>

***
<br>
<h3 style=" color: cornflowerblue; text-align: center; font-family: 'Consolas', sans-serif;">
"What is External Sorting?", "", 
</h3>
<ul style="font-family: Consolas, sans-serif">
<li><code style="color: cornflowerblue; font-weight: bold">"What is External Sorting?"</code>:
<p>
External sorting is a process through which a large data file (often in the size ranges that vastly exceed a given 
computer's main memory) can be sorted by breaking it down into smaller, manageable, chunks that can fit straight into the RAM
for an in place, efficient sorting algorithm to be applied to them.
<br><br>
The core of this algorithm falls onto the way in which the chunks are created, the way these are sorted and finally, 
merged back together. Back in the day of Memory Tapes, individuals who had to sort large amounts of data would have to
adapt the algorithms we can study today to physically constrained devices and their usage. However, now, with the advent
of modern Hard Drives we are able to do this without having to think about the physical constraints of our hardware as much 
as before. Of course, memory availability and sizing still plays a major role in our algorithm design.
<br><br>
Hence, why some algorithms developed for magnetic memory tapes are still used, with some adaptations, to sort large amounts 
of data without directly loading it into primary memory. 
</p>
<br>
<p>Having given out an overview of how these algorithms often work, and what they are applied to. We should take a look
at a pseudocode for their behavior, both one from <q><code>Advanced Data Structures and Algorithms, Vijayalakshmi Pai (2023)</code></q>
, and our classic <q><code>"Introduction to Java Programming Comprehensive, Daniel Liang (2012)</code></q>. These two will 
be presented inside the following blockquote and will be analyzed subsequently.</p>
<blockquote style="font-style: italic; color: whitesmoke"> 
<q>Thinking about Abstract External Sorting</q>
<ul>
<li><b style="color: cornflowerblue; font-weight: bold">"Advanced Data Structures and Sorting Algorithms</b>: 
<p>In general, due to their larger volumes, files and data sources are usually stored in external memory objects. For 
this reason, any algorithm implemented in this regard must take into account the physical limitations of the device
of storage.
<br><br>
The basic idea behind the concept of external sorting is the division of larger data sources into batches that can be 
read, sorted, and stored in the computer's main memory. These can be sorted by <b>any of the efficient internal sorting
algorithms developed</b>. Moreover, from this <code>batches of data concept, comes the term <b>run</b></code>. 
<br>
<br>
<q>A <b>run</b> can be defined as the overarching process of sorting a given batch of data from a larger source.</q>
<br><br>
Interestingly, <b><code>the variety of external sorting does not come from the way in which we sort, or the way in which
we divide the data, rather it comes from the way we merge these runs back together before the final sort is obtained</code></b>
</p>
<br><p>Nevertheless, we can define a main pseudocode of sorts, or a methodology to be more precise, that any external sorting
algorithm must abide to</p>
<blockquote>
<ul style=" list-style: -moz-japanese-formal">
<li>Internally sort batches of records from the source file to generate runs. Write out the runs as, and when they are 
generated onto an external storage device (This means that we should not write data onto the original source, drive, or file. 
Something that ties up with the next description of external sorting from Liang)</li>
<li>Merge the runs generated in the earlier phase to get larger but fewer runs. Write them out onto the external 
storage devices</li>
<li>Repeat the run generation and mere, until in the final phase only one run gets generated on which the sorting of the file
is done</li>
</ul>
</blockquote>
</li>
<li><b style="color: cornflowerblue; font-weight: bold"> "Introduction to Java Programming, Comprehensive</b>: 
<p>External sort is a way through which programmers can sort data sources that cannot fit into the main memory, as opposed, 
to the heavy reliance of in-memory sorting done by the previous internal sorting algorithms. To remedy this dependency, 
we can consider a variation of insertion sort (Generally a Two Way Merge Sort, which grabs two runs and merges them before 
recursively doing the same until one is left alone). These general steps can be taken to sort a large external data source</p>
<blockquote>
<ul style="list-style: -moz-japanese-formal">
<li><b>Phase 1:</b> Repeatedly bring data from a given file to an array and sort the array internally. The size of the array
should be maximized to a number just below the JVM's memory allocation for a given program.</li>
<li><b>Phase 2:</b> Merge a pair of sorted segments (Two Way Merge Sort) into a larger sorted segment and save the new segment
into a temporary file. Continue this process until we are left with just a singular sorted segment.</li>
</ul>
</blockquote>
</li>
</ul>
</blockquote>
<p>One interesting thing to note is that the first book goes much deeper into theory rather than implementations of these 
algorithms, while the second book tends to go hands on with an implementation. No different from any other chapter, it provided
an implementation for externally sorting two million integers which are stored in a binary file. Meanwhile, the first book 
only provides various graphs and mental exercises to show you the way the algorithm works although removing implementation 
details from the user's view.
<br><br>
Both approaches, however, are correct as we will discuss both an implementation of our, with generic support, and the 
concrete example to see the ways that we could make our work better.
</p>
<br>
<p>Now that we have defined the way we abstractly think of external sorting, it would be good to mention its applications 
and some of its pros and cons</p>
</li>
<!--! A comment to Separate them all -->
<li><code style="color: cornflowerblue; font-weight: bold">""</code>:</li>
</ul>