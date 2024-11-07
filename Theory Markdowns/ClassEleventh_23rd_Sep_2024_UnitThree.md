<h1 style=" color: cornflowerblue; text-align: center; font-family: 'Consolas', sans-serif;">
 Data Structures And Algorithms | linkedList Analysis | USFQ | Santiago Arellano
</h1>


***
<ul style="font-family: 'Consolas', sans-serif;">
<code >Main Information Section</code>
<li><b style="color: cornflowerblue; font-weight: bold">Date:</b>: September 23rd of 2024</li>
<li><b style="color: cornflowerblue; font-weight: bold">Unit</b>: Unit Three</li>
<li><b style="color: cornflowerblue; font-weight: bold">Description</b>: This file pertains the following 
contents: <code><b></b></code>.</li>
</ul>

***
<br>
<h3 style=" color: cornflowerblue; text-align: center; font-family: 'Consolas', sans-serif;">
"Linked Lists ", "Doubly Linked Lists "
</h3>
<ul style="font-family: Consolas, sans-serif">
<li><code style="color: cornflowerblue; font-weight: bold">"Linked Lists"</code>: 
<p>
In order to comprehend the way Linked lists are implemented, we must first know about the internal structures that 
are the structural basis for these linked implementations.
</p>
<blockquote style="font-style: italic; color: black"> 
Nodes are the building blocks of linked lists, be it simple or doubly liked lists. These are helper classes
 usually defined internally, which contain two basic variables, and are parametrized for a single value.
<ul>
<li><code> E element</code>: which holds the value stored inside of it</li>
<li><code>Node< E > forwardLink</code>: which holds a <b>reference (this is normal behavior in Java) towards the next node in a 
linked list</b></li>
<br>
Based on these data values we ought now to define the basic skeleton implementation for a simple <code>Node< E > </code>
class.
<body>

```java
package example;

private class Node<E>
{
    E element; /*value stored inside*/
    Node<E> forwardLink; /*reference to node linked in the front of .this */

    public Node(E elementToStore)
    {
        this.element = elementToStore;
        this.forwardLink = null;
    }
}
```
</body>
This implementation is the most barebones that you can use to represent nodes. Of course, you can have improved nodes which
store a backwardLink, or even that present better accessor methods (i.e., following this pattern). However, the most 
important thing to remember out of this is that <b>in a linked list, be it simple or double, there is always <code>
a Head node, and a Tail Node, indicating the start of the list and the end of the list respectively</code></b>
</ul></blockquote>
<p>Having defined this private class, we should now take a look at how they work generally, without going into the linked
list implementation details.</p>
<blockquote style="font-style: italic; color: black"> 
Generally, given the basic implementation provided, each <code>new Node< E > </code> declaration any instance will have a 
single value and a reference to the next that is empty or null.
<br><br>
<ul style="list-style: -moz-japanese-formal">
<code>Basic Operations to Nodes</code>
<li>In general, to addition an element to the next value of another we do the following 
<body>

```java

public class test {
    public static void main(String[] args) {
        Node<String> n1 = new Node<String>("Cadillac"); //Base declaration element:"Cadillac", next = null
        n1.next = new Node<String>("Ford"); //.next = Node["Ford", null]
    }
}

```
</body>
</li>
<li>To addition an element to the head of the linked list, if we assume that our previous n1 node was the head of said
list, we can easily do this through the following snippet. Similarly if we are to add to the end of the list, the same 
snippet shows how.
<body>

```java

public class test {
    public static void main(String[] args)
    {
        Node<String> newHead = new Node<>("Chevrolet");
        newHead.next = n1;

        Node<String> newTail = new Node<>("Fiat");
        newHead.next.next.next = newTail;
    }    
}
```
</body>
</li>
<li>The removal operations will be discussed in further length in the implementation of the actual data structure</li>
</ul>
</blockquote>
<p>Having discussed the main ways in which we could create a simple data structure, albeit with required memory management,
we can take a look at how this structure is implemented in an actual class for a LinkedList</p>
<blockquote style="font-style: italic; color: black"> 
The proceeding file contains information regarding the skeleton implementation given through the book for this LinkedList
data structure. Comments on important snippets have been added to this blockquote.
<body>

```java

public class MyLinkedList<E> implements MyList<E> {
  private Node<E> head, tail;
  private int size = 0; // Number of elements in the list
  
  /** Create an empty list */
  public MyLinkedList() {
  }

  /** Create a list from an array of objects */
  public MyLinkedList(E[] objects) {
    for (int i = 0; i < objects.length; i++)
      add(objects[i]); 
  }

  /** Return the head element in the list */
  public E getFirst() {
    if (size == 0) {
      return null;
    }
    else {
      return head.element;
    }
  }

  /** Return the last element in the list */
  public E getLast() {
    if (size == 0) {
      return null;
    }
    else {
      return tail.element;
    }
  }

  /** Add an element to the beginning of the list */
  public void addFirst(E e) {
    Node<E> newNode = new Node<>(e); // Create a new node
    newNode.next = head; // link the new node with the head
    head = newNode; // head points to the new node
    size++; // Increase list size

    if (tail == null) // the new node is the only node in list
      tail = head;
  }

  /** Add an element to the end of the list */
  public void addLast(E e) {
    Node<E> newNode = new Node<>(e); // Create a new for element e

    if (tail == null) {
      head = tail = newNode; // The new node is the only node in list
    }
    else {
      tail.next = newNode; // Link the new with the last node
      tail = newNode; // tail now points to the last node
    }

    size++; // Increase size
  }

  @Override /** Add a new element at the specified index 
   * in this list. The index of the head element is 0 */
  public void add(int index, E e) {
    if (index == 0) {
      addFirst(e);
    }
    else if (index >= size) {
      addLast(e);
    }
    else {
      Node<E> current = head;
      for (int i = 1; i < index; i++) {
        current = current.next;
      }
      Node<E> temp = current.next;
      current.next = new Node<>(e);
      (current.next).next = temp;
      size++;
    }
  }

  /** Remove the head node and
   *  return the object that is contained in the removed node. */
  public E removeFirst() {
    if (size == 0) {
      return null;
    }
    else {
      E temp = head.element;
      head = head.next;
      size--;
      if (head == null) {
        tail = null;
      }
      return temp;
    }
  }

  /** Remove the last node and
   * return the object that is contained in the removed node. */
  public E removeLast() {
    if (size == 0) {
      return null;
    }
    else if (size == 1) {
      E temp = head.element;
      head = tail = null;
      size = 0;
      return temp;
    }
    else {
      Node<E> current = head;

      for (int i = 0; i < size - 2; i++) {
        current = current.next;
      }

      E temp = tail.element;
      tail = current;
      tail.next = null;
      size--;
      return temp;
    }
  }

  @Override /** Remove the element at the specified position in this 
   *  list. Return the element that was removed from the list. */
  public E remove(int index) {   
    if (index < 0 || index >= size) {
      return null;
    }
    else if (index == 0) {
      return removeFirst();
    }
    else if (index == size - 1) {
      return removeLast();
    }
    else {
      Node<E> previous = head;

      for (int i = 1; i < index; i++) {
        previous = previous.next;
      }

      Node<E> current = previous.next;
      previous.next = current.next;
      size--;
      return current.element;
    }
  }

  @Override /** Override toString() to return elements in the list */
  public String toString() {
    StringBuilder result = new StringBuilder("[");

    Node<E> current = head;
    for (int i = 0; i < size; i++) {
      result.append(current.element);
      current = current.next;
      if (current != null) {
        result.append(", "); // Separate two elements with a comma
      }
      else {
        result.append("]"); // Insert the closing ] in the string
      }
    }

    return result.toString();
  }

  @Override /** Clear the list */
  public void clear() {
    size = 0;
    head = tail = null;
  }

  @Override /** Return true if this list contains the element e */
  public boolean contains(Object e) {
    // Left as an exercise 
    return true;
  }

  @Override /** Return the element at the specified index */
  public E get(int index) {
    // Left as an exercise 
    return null;
  }

  @Override /** Return the index of the head matching element in 
   *  this list. Return -1 if no match. */
  public int indexOf(Object e) {
    // Left as an exercise
    return 0;
  }

  @Override /** Return the index of the last matching element in 
   *  this list. Return -1 if no match. */
  public int lastIndexOf(E e) {
    // Left as an exercise
    return 0;
  }

  @Override /** Replace the element at the specified position 
   *  in this list with the specified element. */
  public E set(int index, E e) {
    // Left as an exercise
    return null;
  }

  @Override /** Override iterator() defined in Iterable */
  public java.util.Iterator<E> iterator() {
    return new LinkedListIterator();
  }
  
  private class LinkedListIterator 
      implements java.util.Iterator<E> {
    private Node<E> current = head; // Current index 
    
    @Override
    public boolean hasNext() {
      return (current != null);
    }

    @Override
    public E next() {
      E e = current.element;
      current = current.next;
      return e;
    }

    @Override
    public void remove() {
      // Left as an exercise
    }
  }
  
  private static class Node<E> {
    E element;
    Node<E> next;

    public Node(E element) {
      this.element = element;
    }
  }
  
  @Override /** Return the number of elements in this list */
  public int size() {
    return size;
  }
}
```

</body>
Having presented the code, it is time for us to define the given methods that are provided and subtleties in their
respective implementations.
<ul>
<code>Points to Note From This</code>
<li><b style="color: cornflowerblue; font-weight: bold">Two Ways of Knowing if a Linked List is Empty</b>: there are 
two ways we can know a linked list is empty. Either we call the size() method (inherited from MyList< E >) which would 
return a zero. Or we call the methods <code>getFirst() or getLast()</code> which in this case would return <b>null</b></li>
<li><b style="color: cornflowerblue; font-weight: bold">Instantiation of head and tail, and the consequences</b>: Given 
that these two variables are instantiated directly to null, this results in easier checks in every method, and more importantly,
transitive checks in every method. <br><br>
In our case, if <code>tail == null -> true</code> then this also means that
<code>head == null -> true</code>. This because in the base case, both pointers would be null, and given that 
in the first case the linked list is empty <b>by definition we make the head and tail point to the same value. </b> However,
once we add values into the list, the head pointer will remain the same unless <code>addFirst(E element)</code> is 
called, and tail will be the one who moves all the time even if <code>addLast(E element)</code> is called.
<br><br>
It is important to note, that in the implementations of the previous methods <code>addFirst(E element) and addLast(E element)
</code> steps are taken in the code to adjust the list in the case that it is empty beforehand. The checks expressed before 
adjust the list to make sure that if it is empty, if we call first or last it would still make it the only node in the 
list. This can be seen in lines like (addLast implementation)
<body>

```java
if (tail == null) {
head = tail = newNode; // The new node is the only node in list
    }
```
</body>Or in more visible cases like. (addFirst implementation)
<body>

```java
if (tail == null) // the new node is the only node in list
      tail = head;
  }
```
</body></li>
<li><b style="color: cornflowerblue; font-weight: bold">O(n) traversal for add and iterators</b>: Since the linked 
list is not backed by an array or hashmap, we know that for any kind of <code>traversal operation</code>, be it to <b>
remove or add an item</b> we are forced directly to iterate <b>from the head to the node before</b>.
<blockquote style="font-style: italic; color: black"> 
The reason we iterate from the head to the node towards the right is that in a simple linked list we do not keep a) 
indeces to know where each element should go, and b) how many elements there will. Therefore, if I want to execute
something like <code>public void add(int index, E element)</code> I require iterating from the head, index - 1 times such that
we are behind the desired location.
<br><br>
We place ourselves before the desired location given that the references point towards the right, i.e., forward references. 
If we wanted to, in a four-element linked list, to insert something at index 3, we can only do so from index 2, or a 
node after the head. This will allow us to do the .next operations and correctly place our new node.
</blockquote>
Moreover, if we are looking at iterators, the same kind of behavior has to be done if by any chance we want to remove 
an element or add an element through the given iterator.</li>
<li><b style="color: cornflowerblue; font-weight: bold">Clearing method discards dynamic memory</b>: Due to Java being 
garbage collected, in the clear method we do not care for the references inside it. Rather what we do is we discard 
that memory, leaving it to the GC to remove, and essentially reset the list to a size of zero and null references to 
both the head and the tail.</li>
<li><b style="color: cornflowerblue; font-weight: bold">Comprehensive Iterator Implementation</b>: This is the first
iterator implementation that is correct and provides a complex method for working. In general inside the iterator, 
the iterator stores a reference to the head. To implement the hasNext() it checks that the current is not null, and 
for the .next() element it specifically returns the value stored in the current node and before doing so, updates 
the location of the current.</li>
</ul>
</blockquote>
</li>
<!--! A comment to separate them all -->
<li><code style="color: cornflowerblue; font-weight: bold">"Doubly Linked Lists"</code>:

</li>
</ul>