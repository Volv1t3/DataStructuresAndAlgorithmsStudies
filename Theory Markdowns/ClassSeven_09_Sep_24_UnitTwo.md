
<h1 style=" color: cornflowerblue; text-align: center; font-family: 'Consolas', sans-serif;">
Data Structure And Algorithms | Eight Queens Algorithm | USFQ | Santiago Arellano
</h1>


***
<ul>
<code>Main Information Section</code>
<li><b style="color: cornflowerblue; font-weight: bold">Date:</b>: 9th of September 2024</li>
<li><b style="color: cornflowerblue; font-weight: bold">Unit</b>: Unit 2</li>
<li><b style="color: cornflowerblue; font-weight: bold">Description</b>: This file contains information about the Eight Queen's algorithms 
implementation details and information.</li>
</ul>

***
<br>
<h3 style=" color: cornflowerblue; text-align: center; font-family: 'Consolas', sans-serif;">
"Eight Queens Algorithm ", "Backtracking in Dynamic Programming ", "String Matching Algorithms"
</h3>
<ul style="font-family: Consolas, sans-serif">

<li><code style="color: cornflowerblue; font-weight: bold">"Eight Queens Algorithm"</code>:
The eight queens algorithm consists of placing eight queens into the same grid of a chessboard in a matter where
 they cannot attack each other. To do this the program, iterates over the board in a certain fashion and trying to 
locate all the queens that it could quickly. If it encounters a point in which no other queen can be placed for a
given iteration, <code>the code backtracks to another known position and retries in a different way</code>.
<br><br>
The code related to this algorithm is presented in the following blockquote.
<blockquote style="font-style: italic; color: black"> 
<body>

```java
package EightQueensAlgorithm;

import java.util.ArrayList;

public class EightQueenAlgorithm {
    private ArrayList<Integer> queensLocations;

    public EightQueenAlgorithm(Integer Queens) {
        this.queensLocations = new ArrayList<>(Queens);
    }

    public void EightQueenMethodCalling() {

        this.EightQueenAlgorithmImpl();
    }

    public boolean EightQueenAlgorithmImpl() {
        int queensPlacedSoFar = 0;
        while (queensPlacedSoFar >= 0 && queensPlacedSoFar < this.queensLocations.size()) {
            int positionToPlaceNewQueen = this.findPositionToPlace(queensPlacedSoFar);
            if (positionToPlaceNewQueen < 0) { /* This is the backtracking case, this only happens if we cannot continue the FindPositionToPlace
             method with the given queensPlacedSoFar*/
                this.queensLocations.set(queensPlacedSoFar, -1);
                queensPlacedSoFar--;
            } else { /*This is the step where we accept the result from positionToPlaceNewQueen and assign it in the arrayList
             */
                this.queensLocations.set(queensPlacedSoFar, positionToPlaceNewQueen);
                queensPlacedSoFar++;
            }


        }
        if (queensPlacedSoFar == -1) {
            return false; /*NO SOLUTION*/
        } else {
            return true;
        }
    }

    public int findPositionToPlace(int queensPlacedSoFarInput) {
        int startSearch = this.queensLocations.get(queensPlacedSoFarInput) + 1;

        for (int j = startSearch; j < this.queensLocations.size(); j++) {
            if (this.isValid(j)) {
                return j;
            }
        }
        return -1;
    }

    public boolean isValid(int queenRow, int queenColumn) {
        for (int i = 1; i <= queenRow; i++) {
            if (queensLocations.get(queenRow - i) == queenColumn
                    || queensLocations.get(queenRow - i) == queenColumn - i
                    || queensLocations.get(queenRow + i) == queenColumn + i
            ) {
                return false;
            }
        }
        return true;

    }
    
}
```

</body>
</blockquote>
</li>
<!--! A comment to separate them all -->
<li><code style="color: cornflowerblue; font-weight: bold">"Backtracking in Dynamic Programming"</code>:
This is a strategy that, as Fausto explained, <code><b>is a process in which the code advances until it reaches
 a position it cannot find a next move, if it comes to this, the code backtracks a step and retries to find a solution
</b></code>.
</li>
<!--! A comment to separate them all -->
<li><code style="color: cornflowerblue; font-weight: bold">"String Matching Algorithms"</code>:
One of the most important applications of algorithms that we can take a look in programming is finding 
different matching substring to a given pattern in a given string. To do this there were three concrete algorithms 
introduced this section regarding search of substring, all of which will be presented now.
<br><br>Commonly we know the overall string as the <code><b>text</b></code> and the substring that we are looking
for is called a <code><b>pattern</b></code>. Based on this pattern we try to find all possible matches in the text.
<blockquote style="font-style: italic; color: black"> 
<ul>
<code>String Pattern Matching Algorithms</code>
<li><b style="color: cornflowerblue; font-weight: bold">Brute Force Algorithm</b>:The first algorithm we take a look at 
is the simplest way in which we could try and find the string. This consists of comparing every possible substring 
in the text with the given pattern.
<body>

```java
package Examples;

public class MethodOne
{
//! Returns the index of the first match of a given pattern in the text, -1 otherwise 
    public static int bruteForceMatching(String text, String pattern)
    {
        //! Iterate over the text taking care of going until we are N pattern characters plus one, this ensures
        //! that we are inside the bounds for the internal for loop
        
        for (int index = 0; i < text.length() - pattern.length() + 1; index++)
        {
            if (isMatched(index,  text, pattern))
            {
                return index;
            }
            
        }
        return -1;
    }
    
    private static boolean isMatched(int i, String text, String pattern)
    {
        for(int kindex = 0; k < pattern.length(); k++)
        {
            if (pattern.charAt(k) != text.charAt(i + kindex))
            {
                return false;
            }
        }
        return true;
    }
}
```
</body>
<p>The previous algorithm runs in the following way. Once you receive an input for the string (text) and the 
substring to find (pattern), it creates a loop which iterates from index 0 all the way up to the total length of the 
string minus the length of the pattern, and plus one. This plus one ensures that the iteration happes <b>all the way to 
the last element in the original text</b> which can be truncated to make the pattern fit and analyze a coherent same 
length string.
<br><br>
This means that for the algorithm to work properly, we need to know that at the last iteration there will be just enough
characters left in the text for the pattern to analyze each of them.
<br><br>
Finally, we notice that since there are two inputs and we are not using nested loops directly, we notice that 
th upper bound asymptotic complexity is <code><b>O(n*m)</b>, where n represents the length of the text, and m the 
length of the pattern</code></p>
</li>
<li><b style="color: cornflowerblue; font-weight: bold">The Boyer-Moore Algorithm</b>:This algorithm is a substantial 
improvement from the last one. Whereas the brute force algorithm checked from left to right, index by index, the Boyer-Moore
algorithm checks from right to left. A key difference from these two algorithms is
<blockquote style="font-style: italic; color: black"> <q>
The Boyer-Moore algorithm finds a matching by comparing the 
pattern with a substring in the text from right to left. If a character in the text does not match 
the one in the pattern, and this character is not in the remaining part of the pattern, you can slide 
the pattern all the way passing this character
</q></blockquote>
This is one interesting way to find the matching patterns as we are effectively removing redundant, or rather unnecesary, 
checks from the algorithm by noticing that we can move over certain characters that we do not have in the pattern, rather
than linearly searching for them. <br><br>
The implementation of this algorithm looks like this.
<body>

```java
Package Examples;

public class MethodTwo
{
    public static int BoyerMooreMatching(String text, String pattern)
    {
        int index = pattern.length() - 1;
        while (index < (text.length() -1 ))
        {
         int kindex = index;
         int jindex = pattern.length() -1;
         while (jindex>=0)
         {
             if (text.charAt(kindex) == pattern.charAt(jindex))
             {
                 kindex--; jindex--;
             }
             else {break;}
         }
         if (jindex < 0)
         {
             return index = pattern.length() + 1;
         }
         
         int u = findLastIndex(text.charAt(kindex),  jindex -1, pattern);
         if (u >= 0)
         {
             index = kindex + (pattern.length() - 1) - u;
         }
         else
         {
             i = kindex + pattern.length();
         }
        }
        return -1;
    }
    
    private static int findLastIndex(char ch, int j, String pattern)
    {for (int k = j; k >= 0; k--)
    {
        if (ch == pattern.charAt(k))
        {
            return k;
        }
    }
        return -1;
    }
}
```
</body>
This algorithm is increasingly more complex, and even to follow it becomes arguably harder than any other string matching
implementation. The best way to explain it is that, it will first look at an index, counting from the left, that is inside the 
text the length of the pattern string. Once it is here, it will compare the first two characters. 
<br>
<br>
<ul>
<li>If the two characters, 1 text and 1 pattern, match the algorithm will move from right to left until it either detects
the full pattern and returns, or fails and adjusts the indexes.</li>
To adjust the indicies it takes a look at the pattern, the location the character was in the pattern and the character itself.
Based on these inputs it decides whether the character is inside the pattern (for which it returns its location inside it), or not 
and returns a negative number.
<br><br>
If it returns a negative number, this means that the character was not in the pattern and therefore the lookup indices should
move the length of the pattern to the right. However, if the character was in the string, it returns the location of said
character and moves the string (length of pattern - location) places to the right. 
<br><br>
Upon further inspection, it is straightforward to see that the way this algorithm works it essentially removes indexes of values that 
it does not need. Therefore, this algorithm is more efficient, but even with these improvements, we still see that 
<code><b>its upper bound asymptotic complexity is O(n*m)</b></code>
</ul>
</li>
<li><b style="color: cornflowerblue; font-weight: bold">The Knuth-Morris-Pratt Algorithm</b>: This is the most efficient
algorithm that was presented in the book for string matching. It has an upper bound asymptotic complexity of <code><b>
O(n+m)</b></code> because the characters in the input pattern and text must be checked at least once in the worst case.
<br><br>
Despite its benefits, this algorithm was not discussed in class and was skipped over due to its heightened complexity, a 
picture of its implementation is presented here:
<body>

```java
public static int match(String text, String pattern) {
    int[] fail = getFailure(pattern);
    int i = 0; // Index on text
    int k = 0; // Index on pattern
    while (i < text.length()) {
      if (text.charAt(i) == pattern.charAt(k)) {
        if (k == pattern.length() - 1) {
          return i - pattern.length() + 1; // pattern matched
        }
        i++; // Compare the next pair of characters
        k++;
      }
      else {
        if (k > 0) {
          k = fail[k - 1]; // Matching prefix position
        }
        else {
          i++; // No prefix
        }
      }
    }

    return -1;
  }
	
  // Compute failure function
  private static int[] getFailure(String pattern) {
    int[] fail = new int[pattern.length()];
    int i = 1;
    int k = 0;
    while (i < pattern.length()) {
      if (pattern.charAt(i) == pattern.charAt(k)) {
        fail[i] = k + 1;
        i++;
        k++;
      }
      else if (k > 0) {
        k = fail[k - 1];
      }
      else {
        i++;
      }
    }
    
    return fail;
  }

```
</body>
</li>
</ul>
</blockquote>
</li>
<!-- A comment to separate them all-->
<li><code style="color: cornflowerblue; font-weight: bold">"Amortized Algorithmic Growth Rate"</code>:
The theory of amortized algorithmic analysis tells us that we would get a better and more appropriate measurement of an 
an algorithm complexity by running the algorithm over certain large inputs and running an average on the way the 
algorithm behaves. 
<blockquote style="font-style: italic; color: black"> 
<code>Definition, taken from StackOverflow</code>
Amortized algorithm complexity is a way to analyze the time complexity of an algorithm over a 
sequence of operations, rather than just considering the worst-case scenario for a
single operation. It provides a more realistic estimate of the algorithm's performance 
by taking into account the average cost of operations.
</blockquote>
<blockquote style="font-style: italic; color: black"> 
<code>Common Use Case, taken from Medium</code>
when an algorithm has occasional expensive operations that are offset by many economical operations. For example, in a dynamic 
array implementation, resizing the array to accommodate new elements is an 
expensive operation, but it is amortized over the many inexpensive insertions that follow.
</blockquote>
<blockquote style="font-style: italic; color: black"> 
<code>Calculating Amortized Complexity</code>
you need to consider the cost of each operation and divide it by 
the number of operations. This gives you the average cost per operation, which is 
the amortized complexity. The analysis often involves identifying the worst-case 
scenarios and showing that they occur infrequently enough to be offset by the 
cheaper operations.
</blockquote>
<blockquote style="font-style: italic; color: black"> 
<ul>
<code>Examples</code>
<li><b style="color: cornflowerblue; font-weight: bold">Push Operation in Dynamic Array
Implementations</b>:While a single push operation may have a worst-case time complexity of O(n) when the array needs to be 
resized, the amortized complexity of push is O(1) because the cost of resizing is spread over many subsequent push 
operations that can be performed in constant time.</li>
<li><b style="color: cornflowerblue; font-weight: bold">Union-Find Data Structure</b>: 
where the find operation has a worst-case time complexity of O(n), but its amortized complexity is O(α(n)), where α(n) 
is the inverse Ackermann function, which grows extremely slowly. 
</li></ul>
</blockquote>
</li>
<!--! A comment to separate them all -->
<li><code style="color: cornflowerblue; font-weight: bold">"Introduction to Data Structures"</code>:
When we work with an abstract data structure, the first thing that we have is an ADT, abstract data type. This can be 
defined as a data type defined through its operations, it does not specify any implementation details.
<blockquote style="font-style: italic; color: black"> 
An example of an ADT is, for example, a set, which is defined as a set of data without duplicates.
We defined the operations that this data structure has to have
<ul>
<li><b style="color: cornflowerblue; font-weight: bold">Add an element</b></li>
<li><b style="color: cornflowerblue; font-weight: bold">Remove an element </b></li>
<li><b style="color: cornflowerblue; font-weight: bold">Search for an element</b></li>
<li><b style="color: cornflowerblue; font-weight: bold">Clear the values inside the structure</b></li>
</ul>
<br>
Having defined the methods we should take a look at how we will implement them, from this we can get complexities, etc.
</blockquote>
<br><br>
However, when we define an implementation, we can use either <code><b>linear data structures (i.e. arrays and lists
</b></code>, <code><b>non-linear (i.e. maps and sets)</b></code>
<br><br>
Another important aspect that we ought to consider inside the Java development environment is that we already have most ADTs 
defined for us, and even skeleton implementations that we can extend upon or vary to create our own. Most classes and data 
structures in Java have interfaces behind them defined in the <code>Java Collections Framework</code> which contains skeleton
interfaces for almost every data structure you might want, and even for variations of those. 
<br><br>

</li>
</ul>