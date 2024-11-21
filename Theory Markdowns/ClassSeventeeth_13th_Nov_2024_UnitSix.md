<h1 style=" color: cornflowerblue; text-align: center; font-family: 'Consolas', sans-serif;">
Data Structures And Algorithms | Hashing and HashTables | USFQ | Santiago Arellano
</h1>


***
<ul style="font-family: 'Consolas', sans-serif;">
<code >Main Information Section</code>
<li><b style="color: cornflowerblue; font-weight: bold">Date:</b>: 13th November 2024. </li>
<li><b style="color: cornflowerblue; font-weight: bold">Unit</b>: Unit Six </li>
<li><b style="color: cornflowerblue; font-weight: bold">Description</b>: This file pertains the following contents: 
<code><b>Hashing, Hash Functions, Hash Tables</b></code>.</li>
</ul>

***
<br>
<h3 style=" color: cornflowerblue; text-align: center; font-family: 'Consolas', sans-serif;">
"Hashing", "", 
</h3>
<ul style="font-family: Consolas, sans-serif">
<li><code style="color: cornflowerblue; font-weight: bold">"Hashing"</code>:
<p>One of the main applications of hashing is in the form of Hash Tables, Sets and Maps, where insertion, deletion, and lookuip
operations are meant to be constant asymptotic complexity {O(1)}. Behind this process then, there is the entire concept of hashing.</p>
<br><br>
<p>A hashing function produces, based on a key or a value a hash, or an integer that can be though of as an index, inside a table 
of where this value has to go in. Consider having an array with values, if we were to use a Hash Table, what we do is input
the the key-value pair's key to a hash function that returns the index mapped to the values passed in. This means that <b> our
key becomes an index</b>.
<br><br>
One key idea, of course, is that the function returns the same key for the same value. However, there are some risks to this 
way of implementing data structures
</p>
<blockquote style="font-style: italic; color: whitesmoke"> <ul>
<li><b style="color: cornflowerblue; font-weight: bold">Collisions</b>: there can be two keys that, through a given hash
function are assigned the same hash value, thereby producing collisions. Sophistication in the hashing function often 
helps to reduce this risk.
<br><br>
<code>In Java, Object.hashCode() already produces good hash codes</code><br>
<code>Once a collision has occurred, the probability of another one occurring grows exponentially.</code>
<p>In general, if collisions grow large and there are clusters of data points in our program, then one straightforward solution
that can be used is resizing the hash table, increasing the size and then rehashing each data point in the original hash table.</p>
</li>
<li><b style="color: cornflowerblue; font-weight: bold">Memory wastage</b>: there can be situations where, although the 
table is sized up for N items, we end up using a subsection of it named k, this means that there will be N-k items that
are left empty, wasting memory.
<br><br>
Sometimes, our functions can produce hashcodes with n digits, which would produce n-length arrays, here we use the concept of 
<b>compression</b>, reducing the has <b>but increasing collision probability</b>. Here comes an important factor to consider
when dimensioning our Hash Tables and the way it handles loads, <b>load balancing is defined as the number of keys stored, divided 
by the size of the table.</b>This value is often recommended to be between .50 to .70
<br><br>
<p>Moreover, we do not know if the positions of indices will respect the natural order between inputs to the Hash Table. </p>
</li>
</ul> </blockquote>
<p>Now let us talk about hashing functions in specific, beginning with the concept of perfect hashing functions</p>
<blockquote style="font-style: italic; color: whitesmoke"> <q>Hashing Functions</q> 
<ul>
<li><b style="color: cornflowerblue; font-weight: bold">Perfect Hashing Function</b>: a perfect hashing function is one
that has a <code>homogeneous distribution with little to no collisions. It might use all digits of a hash key. </code> In general, homogeneous distributions
produce hash values which reduce the possible number of collisions that can occur in our structure.</li>
<li><b style="color: cornflowerblue; font-weight: bold">Components of Hash Functions</b>: it has two steps, the first step is the 
production of values that can be used as hashes, these can come from various mathematical operations. The second step (component), is 
the <b>compression state</b>, where the value of the hash is folded such that it can fit in the size of the hash table. This second state
is the one where the risk of collisions grows highly.</li>
</ul>
</blockquote>
<p>There are other ways in which we can address collisions to try and reduce them, which are</p>
<blockquote style="font-style: italic; color: whitesmoke"> Hashing Methods 
<ul>
<li><b style="color: cornflowerblue; font-weight: bold">Hashing by Folding</b>: splitting a key in some arbitrary 
midpoint and them adding corresponding digits to produce a new hash for a given value. This then means that, although the 
hash function is the same, we are no longer leaving some values of the hash outside, producing a new hash from the entirety
of the original hash.</li>
</ul>

</blockquote>
</li>
<!--! A comment to Separate them all -->
<li><code style="color: cornflowerblue; font-weight: bold">"Hashing Methods Implemented In Java"</code>:
<p>Some of the most important disadvantages of hashing is one that (A) disperses data over a given range of indeces
guaranteeing O(1) search, insertions and deletions. <br><br>
One of the most basic implementations of hashing in Java is using HashSet, although there are various implementations
for primitives and string methods for creating hashcodes for these data types. 
<br><br>
Additionally, the book presents explanations of hashing compressions (something that increases the probability of 
collisions from happening in our data). This process reduces the possible range of indices that our function can create, 
however this also means that values can get clustered together. To make this possible, we use prime numbers to reduce the probability 
of collisions.
<br><br>
Load factor is often defined as 50% to 60%, if this threshold is surpassed, the idea is that the table should resize (however this method 
is quite complicated as it requires rehashing every single value inside the array already to find their new positions, 
effectively making this operation linear.
<br><br>
To solve collisions, we can use various methods, Open Addressing is a way to solve this that <b>checks for empty
spaces where an element can be stored. </b>
</p>
<ul>
<li><b style="color: cornflowerblue; font-weight: bold">OA [Linear Probing]</b>: Linear probing says that if there is 
a collision with an already placed position, the code should look for <b>any open space where the value could fit</b>. This then means
that we can produce 
<ul>
<li>Clustering: where data points cluster around a position in the array</li>
<li>Devolution into linear search: where data points are moved so much from their original position that we have to do
linear search to find them</li>
<li>Deleting elements: if an element is displaced, then we might end up deleting either wrong values or not deleting at all
if the value was displaced from its original spot.</li>
</ul>
<p>This solution might be the simplest way to implement a hash helper function, but it can produce various pathways for errors</p>
</li>
<li><b style="color: cornflowerblue; font-weight: bold">OA [Quadratic Probing]</b>: <p>This method can avoid the clustering problem by looking at an index in the form of <code> (I + J^2) % N</code>, where I is the original
index, and J represents the <code><b>number of test that is being performed to determine the correct position</b> (this means that
J goes from 0 to k, where k belongs to the Integers</code>. The last section, modulo N, is just a way for us to compress the number
produced and wrap it to fit in the size of our table.</p>
<br>
<p>If we were to do this, we would generally see that in the end, if an element is placed after n iterations, and we delete those n 
values before the one we inserted, this means that we would not be able to get back into our original data point. For this reason,
one solution is to essentially <b>mark deleted values as deleted and not delete them entirely</b></p>
<br>
</li>
<li><b style="color: cornflowerblue; font-weight: bold">OA [Double Hashing] </b>:
<p>this means that we will have a secondary hash function to determine the increments to avoid the clustering issues. This means
that these two fill be applied in tandem as in <code>(I + J*h'(key)) % N</code>, where, as before, I is the index where we started,
and then the h function represents the second hashing function. that is multiplied by the number of iterations done.</p>
<br>
</li>
</ul>
<p>In general, the best way for us to address the issues of collisions, is to create a hash table with an internal single linked list 
instance for each index in our HashTable, that is for each index we will have a zero-or-more elements' linked lists.
<br><br>
The load factor of a HashTable is defined as the rate between the number of elements inserted and the size of the hash table (n/N).
</p>
<br><br>
<blockquote style="font-style: italic; color: whitesmoke"> <q>Hash Functions: Implementation Code</q>
<p>The first example we are going to touch on is the example of MyMap and MyHashMap</p>
<blockquote style="font-style: italic; color: whitesmoke"> <q>HashMaps: MyMap Implementation</q> 
<body>

```java 22
public interface MyMap<K, V> {
  /** Remove all of the entries from this map */ 
  public void clear();
  
  /** Return true if the specified key is in the map */
  public boolean containsKey(K key);
  
  /** Return true if this map contains the specified value */ 
  public boolean containsValue(V value);

  /** Return a set of entries in the map */
  public java.util.Set<Entry<K, V>> entrySet();

  /** Return the first value that matches the specified key */
  public V get(K key);
  
  /** Return true if this map contains no entries */
  public boolean isEmpty();

  /** Return a set consisting of the keys in this map */
  public java.util.Set<K> keySet();
  
  /** Add an entry (key, value) into the map */
  public V put(K key, V value);

  /** Remove the entries for the specified key */
  public void remove(K key);

  /** Return the number of mappings in this map */
  public int size();

  /** Return a set consisting of the values in this map */
  public java.util.Set<V> values();
  
  /** Define inner class for Entry */
  public static class Entry<K, V> {
    K key;
    V value;
    
    public Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }
    
    public K getKey() {
      return key;
    }
    
    public V getValue() {
      return value;
    }
    
    @Override
    public String toString() {
      return "[" + key + ", " + value + "]";
    }
  }
}    

 ```
</body>
<p>One of the key things to not here is the way it has included various methods from interfaces it does not extend from. For example,
the methods for entrySet, keySet and values, come from iterableSet interface, and it is providing a way for us to present
functionality from various interfaces in a single file, reducing code complexity. Furthermore, we are defining our own implementation
for Map.Entry instead of using the Map.Entry inner class defined in the java Map class.</p>
</blockquote>
<p>This previous file is simple, there is nothing in it that would bring some kind of trouble or difficulty of understanding. However, 
the next class presents a series of methods that provide ways for us to put, get and remove values in a HashTable.</p>
<blockquote style="font-style: italic; color: whitesmoke"> <q>HashMap: MyHashMap implementation</q> 
<body>

```java 22 

import java.util.LinkedList;

public class MyHashMap<K, V> implements MyMap<K, V> {
  // Define the default hash table size. Must be a power of 2
  private final static int DEFAULT_INITIAL_CAPACITY = 4;

  // Define the maximum hash table size. 1 << 30 is same as 2^30
  private final static int MAXIMUM_CAPACITY = 1 << 30;

  // Current hash table capacity. Capacity is a power of 2
  private int capacity;

  // Define default load factor
  private final static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;

  // Specify a load factor used in the hash table
  private float loadFactorThreshold;

  // The number of entries in the map
  private int size = 0;

  // Hash table is an array with each cell that is a linked list
  LinkedList<MyMap.Entry<K,V>>[] table;

  /** Construct a map with the default capacity and load factor */
  public MyHashMap() {
    this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
  }

  /** Construct a map with the specified initial capacity and
   * default load factor */
  public MyHashMap(int initialCapacity) {
    this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
  }

  /** Construct a map with the specified initial capacity
   * and load factor */
  public MyHashMap(int initialCapacity, float loadFactorThreshold) {
    if (initialCapacity > MAXIMUM_CAPACITY)
      this.capacity = MAXIMUM_CAPACITY;
    else
      this.capacity = trimToPowerOf2(initialCapacity);

    this.loadFactorThreshold = loadFactorThreshold;
    table = new LinkedList[capacity];
  }

  @Override /** Remove all of the entries from this map */
  public void clear() {
    size = 0;
    removeEntries();
  }

  @Override /** Return true if the specified key is in the map */
  public boolean containsKey(K key) {
    if (get(key) != null)
      return true;
    else
      return false;
  }

  @Override /** Return true if this map contains the value */
  public boolean containsValue(V value) {
    for (int i = 0; i < capacity; i++) {
      if (table[i] != null) {
        LinkedList<Entry<K, V>> bucket = table[i];
        for (Entry<K, V> entry: bucket)
          if (entry.getValue().equals(value))
            return true;
      }
    }

    return false;
  }

  @Override /** Return a set of entries in the map */
  public java.util.Set<MyMap.Entry<K,V>> entrySet() {
    java.util.Set<MyMap.Entry<K, V>> set =
      new java.util.HashSet<>();

    for (int i = 0; i < capacity; i++) {
      if (table[i] != null) {
        LinkedList<Entry<K, V>> bucket = table[i];
        for (Entry<K, V> entry: bucket)
          set.add(entry);
      }
    }

    return set;
  }

  @Override /** Return the value that matches the specified key */
  public V get(K key) {
    int bucketIndex = hash(key.hashCode());
    if (table[bucketIndex] != null) {
      LinkedList<Entry<K, V>> bucket = table[bucketIndex];
      for (Entry<K, V> entry: bucket)
        if (entry.getKey().equals(key))
          return entry.getValue();
    }

    return null;
  }

  @Override /** Return true if this map contains no entries */
  public boolean isEmpty() {
    return size == 0;
  }

  @Override /** Return a set consisting of the keys in this map */
  public java.util.Set<K> keySet() {
    java.util.Set<K> set = new java.util.HashSet<>();

    for (int i = 0; i < capacity; i++) {
      if (table[i] != null) {
        LinkedList<Entry<K, V>> bucket = table[i];
        for (Entry<K, V> entry: bucket)
          set.add(entry.getKey());
      }
    }

    return set;
  }

  /** Add an entry (key, value) into the map */
  @Override
  public V put(K key, V value) {
    if (get(key) != null) { // The key is already in the map
      /**
       * This method here, calls internal methods like hash (which itself calls
       * another internall method called supplementalHash() to create a hash that
       * is completely withheld inside the bounds of the internal LinkedList array.
       * <br><br>
       * THe way this works is that it supplements (basically does a double hashing on the
       * java.Object.hashCode() method that provides an already appropiately calculated hash
       * value for our objects.
       */
      int bucketIndex = hash(key.hashCode());
      LinkedList<Entry<K, V>> bucket = table[bucketIndex];
      for (Entry<K, V> entry: bucket)

        if (entry.getKey().equals(key)) {
          V oldValue = entry.getValue();
          // Replace old value with new value
          entry.value = value;
          // Return the old value for the key
          return oldValue;
        }
    }

    // Check load factor
    if (size >= capacity * loadFactorThreshold) {
      if (capacity == MAXIMUM_CAPACITY)
        throw new RuntimeException("Exceeding maximum capacity");

      rehash();
    }

    int bucketIndex = hash(key.hashCode());

    // Create a linked list for the bucket if it is not created
    if (table[bucketIndex] == null) {
      table[bucketIndex] = new LinkedList<Entry<K, V>>();
    }

    // Add a new entry (key, value) to hashTable[index]
    table[bucketIndex].add(new MyMap.Entry<K, V>(key, value));

    size++; // Increase size

    return value;
  }

  @Override /** Remove the entries for the specified key */
  public void remove(K key) {
    int bucketIndex = hash(key.hashCode());

    // Remove the first entry that matches the key from a bucket
    if (table[bucketIndex] != null) {
      LinkedList<Entry<K, V>> bucket = table[bucketIndex];
      for (Entry<K, V> entry: bucket)
        if (entry.getKey().equals(key)) {
          bucket.remove(entry);
          size--; // Decrease size
          break; // Remove just one entry that matches the key
        }
    }
  }

  @Override /** Return the number of entries in this map */
  public int size() {
    return size;
  }

  @Override /** Return a set consisting of the values in this map */
  public java.util.Set<V> values() {
    java.util.Set<V> set = new java.util.HashSet<>();

    for (int i = 0; i < capacity; i++) {
      if (table[i] != null) {
        LinkedList<Entry<K, V>> bucket = table[i];
        for (Entry<K, V> entry: bucket)
          set.add(entry.getValue());
      }
    }

    return set;
  }

  /** Hash function */
  private int hash(int hashCode) {
    return supplementalHash(hashCode) & (capacity - 1); //This is using a bitwise AND
  }

  /** Ensure the hashing is evenly distributed */
  private static int supplementalHash(int h) {
    h ^= (h >>> 20) ^ (h >>> 12);
    return h ^ (h >>> 7) ^ (h >>> 4);
  }

  /** Return a power of 2 for initialCapacity */
  private int trimToPowerOf2(int initialCapacity) {
    int capacity = 1;
    while (capacity < initialCapacity) {
      capacity <<= 1;
    }

    return capacity;
  }

  /** Remove all entries from each bucket */
  private void removeEntries() {
    for (int i = 0; i < capacity; i++) {
      if (table[i] != null) {
        table[i].clear();
      }
    }
  }

  /** Rehash the map */
  private void rehash() {
    java.util.Set<Entry<K, V>> set = entrySet(); // Get entries
    capacity <<= 1; // Double capacity
    table = new LinkedList[capacity]; // Create a new hash table
    size = 0; // Reset size to 0

    for (Entry<K, V> entry: set) {
      put(entry.getKey(), entry.getValue()); // Store to new table
    }
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("[");

    for (int i = 0; i < capacity; i++) {
      if (table[i] != null && table[i].size() > 0)
        for (Entry<K, V> entry: table[i])
          builder.append(entry);
    }

    builder.append("]");
    return builder.toString();
  }
}
```
</body>

</blockquote>
</blockquote>
</li>
</ul>