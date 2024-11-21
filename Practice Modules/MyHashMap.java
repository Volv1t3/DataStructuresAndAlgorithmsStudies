package DeberDiezHashMapComparisonSantiagoArellano;

import java.util.LinkedList;


/**
 * @Author: Santiago Arellano
 * @Date: November 18th, 2024
 * @Description: EL presente archivo contiene la definicion clave de un HashMap como se ha presentado en el texto de
 * Introduction to Java Programming (Daniel Liang) con varios cambios y comentarios de analisis utilizados para incrementar
 * la compresion del documento.
 * <br><br>
 * La mayoria de las variables no han sido cambiadas, sin embargo, varios nombres de funciones cambian y se han adaptado
 * para mantener una estructura coherente entre accesso public y privado.
 * @param <K> : Tipo de dato de la llave
 * @param <V> : Tipo de dato del valor
 */
public class MyHashMap<K, V> implements MyMap<K, V> {
  /*
  * ! Declaracion de Variables Internas
  */
  private final static int DEFAULT_INITIAL_CAPACITY = 4;
  private final static int MAXIMUM_CAPACITY = 1 << 30;
  private int capacity;
  private final static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;
  private final float loadFactorThreshold;
  private int internalSIze = 0;
  LinkedList<Entry<K,V>>[] table;

  /*
   * ! Declaration de constructor sin valores, se declaara directamente como una instancia con los valores internos de
   * una capacidad base de 4 y con un factor de carga del 0.75 un valor medio entre el 0.90 para hash tables con
   * listas encadenadas directamente, y 0.50 para aquellas con un double hashing.
   */
  public MyHashMap() {
    this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
  }

  /*
   * Constructor public con valores, permite al usuario crear un HashMap en base a una capacidad inicial, claro esta que esta
   * capacidad se la tiene que modificar hacia un power-of-two para garantizar el correcto funcionamiento de la clase.
   */
  public MyHashMap(int initialCapacity) {
    this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
  }

  /**
   * Constructor public con valores, permite al usuario crear un HashMap en base a una capacidad inicial y un factor de carga
   * deseado, claro esta que esta capacidad se la tiene que modificar hacia un power-of-two para garantizar el correcto
   * funcionamiento de la clase.
   * @param initialCapacity: Capacidad inicial del HashMap
   * @param loadFactorThreshold: Factor de carga deseado del HashMap
   */
  public MyHashMap(int initialCapacity, float loadFactorThreshold) {
    if (initialCapacity > MAXIMUM_CAPACITY)
      this.capacity = MAXIMUM_CAPACITY; //! Revision para evaluar caso de enviar un tamano mayor a 2^30 (equivalente
                                        //! a una GB de memoria. Es decir, se puede guardar en teoria 1.07 billones
                                        //! de datos
    else
      this.capacity = trimToPowerOf2(initialCapacity); //! Llamada interna para arreglar el valor si no es un power-of-two

    this.loadFactorThreshold = loadFactorThreshold;
    table = new LinkedList[capacity];
  }

  /**
   * Elimina todos los elementos del HashMap. Este metodo se encarga de eliminar valor por valor todas las entraadas
   * anadidas para limpiar la memoria extra utilizada.
   */
  @Override
  public void clear() {
    internalSIze = 0;
    removeEntries();
  }

  /**
   * Primer metodo de trabajo de la clase, realiza una busqueda exhaustiva utilizando el metodo interno get(key) para
   * encontrar una llave dentro de la tabla. Este metodo no debe retornar el valor de la llave si se encuentra, solamente
   * el valor booleano significativo de true or false
   */
  @Override
  public boolean containsKey(K key) {
      return get(key) != null;
  }

  /**
   * Segundo metodo de trabajo de la clase, realiza un analisis similar al anterior, llevando una busqueda anidada que en primera
   * instancia itera sobre todos los indices (0 hasta capacity -1), en donde se evalua si existe una entrada de lista encadenada
   * dentro de la misma. Si esta existe, se toma la tabla y realiza un for iterativo con iteradores para determinar la igualdad
   * entre la llave leida (dentro de cada bucket o lista encadenada) comparada con el valor ingresado por el usuario
   * @param value: Valor a buscar dentro del HashMap
   * @return : Valor booleano significativo de true or false
   * @throws NullPointerException: Excepcion en caso de que el valor ingresado sea nulo
   */
  @Override
  public boolean containsValue(V value) throws NullPointerException{
    if (value == null){
      throw new NullPointerException("Error Code 0x001 - [Raised] Valor ingresado en clave 'valor' no puede ser nulo." );
    }

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


  /**
   * Tercer Metodo de trabajo de la clase. Permite al usuario obtener un Set (Implementado como un HashSet, es decir nada
   * de duplicados (algo que rompe con el javadoc de map)) con todos los valores internos del arreglo colocados sin un orden
   * pero en una estructura de datos navegable.
   * @return: Set con todas las entradas del HashMap
   */
  @Override
  public java.util.Set<Entry<K,V>> entrySet() {
    java.util.Set<Entry<K, V>> set =
            new java.util.HashSet<>();

    for (int i = 0; i < capacity; i++) {
      if (table[i] != null) {
        LinkedList<Entry<K, V>> bucket = table[i];
          set.addAll(bucket);
      }
    }

    return set;
  }

  /**
   * Cuarto Metodo de trabajo de la clase. Permite al usuario obtener un valor asociado con una clave si este existe
   * dentro del arreglo. Para este proceso, realiza una busqueda basada en la utilizacion del  hashCode calculado para
   * la key enviada y revisando si esta existe dentro de arreglo.
   * <br><br>
   * <p>Para esta verificacion, el codigo realiza una llamada interna hacia la funcion hash, con argumentos de entero (representativo
   * de la clave hash calculada por java.internal), luego utiliza este valor e implementa una funcion de hash suplementaria en
   * supplementary hash, usada para modificar el hash y dispersarlo de mejor manera asi como para matenerlo en el arreglo.
   * <br><br>
   * Una vez con esta llave, el codigo revisa si esta llave no es nula, porque si la es tiene retornar directamente nulo.
   * Si no lo es, realiza una busqueda linear por cada entry del bucket que fue encontrado y busca por el valor interno,
   * si este valor no existe simplemente retorna nulo
   * </p>
    * @param key: Llave a buscar dentro del HashMap
   * @return V: Valor asociado a la llave, si no existe retorna nulo
   * @throws NullPointerException: Excepcion en caso de que el valor ingresado sea nulo
   */
  @Override
  public V get(K key) throws NullPointerException {
    if (key == null) {
      throw new NullPointerException("Error Code 0x001 - [Raised] Valor ingresado en clave 'llave' no puede ser nulo." );
    }

    int bucketIndex = hash(key.hashCode());
    if (table[bucketIndex] != null) {
      LinkedList<Entry<K, V>> bucket = table[bucketIndex];
      for (Entry<K, V> entry: bucket)
        if (entry.getKey().equals(key))
          return entry.getValue();
    }

    return null;
  }

  /**
   * Helper methodo disenado para avisar al usuario si todavia existen datos dentro de la estructura interna de memoria
   */
  @Override
  public boolean isEmpty() {
    return internalSIze == 0;
  }

  /**
   * Quinto Metodo de trabajo de la clase. Este metodo permite al usuario obtener un set integrado de todas las llaves
   * pertenecientes al arreglo. En general, por el contrato establecido dentro del metodo put, no se puede tener claves duplicadas
   * en insercion y por lo tanto, se espera que aunque se use un set internamente, este set siempre tendra valores unicos.
   * <br><br>
   * <p>La tabla interna se traversa en un sentido unidireccional lineal tal como seusa para buscar get, contains, entrySet, etc.
   * En donde se realiza un analisis interno en primera instancia en base a la longitud interna del arreglo, e internamente con un analisis
   * iterativo basado en un iterador que traversa sobre todas las entradas de la linked list interna y anade sus keys.</p>
   * @return : Set con todas las llaves del HashMap
   */
  @Override
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

  /**
   * Sexto y mas important metodo de trabajo de la clase. Este metodo permite al usuario ingresar un par de valores de llave
   * y valor para ser colocados dentro de la tabla de dispersion interna, estos valores pueden ser de cualquier tipo que
   * extienda Object. En general, el proceso del codigo sigue la serie de pasos a detallar a continuacion.
   * <ul>
   *     <li>En primera instancia, el codigo realiza una llamada para obtener el valor asociado con la clave en afan de
   *     revisar si esta ya fue ingresada dentro del arreglo. Si ya fue ingresada, esta es solo actualizada con su nuevo valor,
   *     eliminando la posibilidad de duplicados.
   *     <br><br>
   *     Para hacer esto, el metodo realiza una llamada interna hacia el metodo hash, el cual convierte el hashCode primitivo
   *     de java, en base de un metodo interno de hashing, para obtener un doble hashing de esta clave y en si encontrar
   *     la localizacion exacta del mismo dentro del arreglo. Como sabemos que las funciones nos dejan en el rango del arreglo,
   *     simplemente extraemos este arreglo, y buscamos iterativamente sobre el por la llave para actualizar su valor
   *     y retornar el antiguo.
   *     </li>
   *     <br>
   *     <li>Si el primer paso no funciona, esto quiere decir que no hemos logrado encontrar la llave dentro del arreglo, y por
   *     tanto revisamos si tenemos que hacer una redimension en primer lugar. Esta redimension se realiza con la funcion <code>
   *         rehash()</code>, la cual forza al programa  a recalcular el hash para el nuevo tamano de la tabla, moviendo todos
   *         los valores a sus nuevas posiciones. Luego de realiza este proceso, procede a calcular el nuevo hash para el valor original
   *         (dado el cambio de tamano tiene que recalcular esta posicion tambien)
   *     Una vez calculado, procede a revisar si el arreglo correspondiente a esa posicion esta vacio para crear un lista encadenada en esta poscion,
   *     si el arreglo no lo esta, este bloque se pasa y se anade directamente el valor dentro de nuestra lista encadenada</li>
   *     <br>
   *     <li>Nota: Solo en el segundo caso, la variable size interna se aumenta</li>
   * </ul>
   * @param key: Valor Ingresado por el usuario como Llave
   * @param value: Valor Ingresado por el usuario como Valor
   * @return : Valor antiguo asociado con la llave, si esta llave fue nueva, se retorna el mismo valor.
   * @throws NullPointerException: Excepcion en caso de que el valor ingresado sea nulo
   */
  @Override
  public V put(K key, V value) throws NullPointerException {
    if (key == null || value == null){
      throw new NullPointerException("Error Code 0x001 - [Raised] Valor ingresado en clave 'llave' o 'valor' no puede ser nulo." );
    }
    if (get(key) != null) { // The key is already in the map
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

    // Check a load factor
    if (internalSIze >= capacity * loadFactorThreshold) {
      if (capacity == MAXIMUM_CAPACITY) {
        throw new RuntimeException("Exceeding maximum capacity");
      }
      rehash();
    }

    int bucketIndex = hash(key.hashCode());

    // Create a linked list for the bucket if it is not created
    if (table[bucketIndex] == null) {
      table[bucketIndex] = new LinkedList<>();
    }

    // Add a new entry (key, value) to hashTable[index]
    table[bucketIndex].add(new MyMap.Entry<>(key, value));

    internalSIze++; // Increase size

    return value;
  }

  /**
   * Septimo metodo de trabajo para esta clase. Permite al usuario eliminar un valor contenido dentro del arreglo con el
   * hecho de enviar la llave a la que se piensa que este valor esta asociado. Este metodo corresponde a buscar un par de datos
   * en base de la llave y remover este par completo.
   * <br><br>
   * <p>Internamente, este metodo trabaja de neuvo con una busqueda basada en el hash obtenido de la llave, para luego de
   * esto bucar iterativamente en el bucket (linked list) asignado a este valor para encontrar la clave que concuerte con
   * <code>entry.getKey().equals(key)</code>, si esta condicion se cumple, entonces se llama al metodo de linkedList remove
   * para eliminarla en un proceso secuencial interno, el tamano se reduce y se sale immediatamente del lazo para evitar
   * iteraciones demas.</p>
   * @param key: Llave a buscar dentro del HashMap
   * @throws NullPointerException: Excepcion en caso de que el valor ingresado sea nulo
   */
  @Override
  public void remove(K key) throws NullPointerException{
    if (key == null){
      throw new NullPointerException("Error Code 0x001 - [Raised] Valor ingresado en clave 'llave' no puede ser nulo." );
    }

    int bucketIndex = hash(key.hashCode());

    // Remove the first entry that matches the key from a bucket
    if (table[bucketIndex] != null) {
      LinkedList<Entry<K, V>> bucket = table[bucketIndex];
      for (Entry<K, V> entry: bucket)
        if (entry.getKey().equals(key)) {
          bucket.remove(entry);
          internalSIze--; // Decrease size
          break; // Remove just one entry that matches the key
        }
    }
  }

  /**
   * Metodo interno de ayuda disenado para obtener el tamano real del numero de pares de claves-valor asociadas a una
   * instancia de la clase.
   * @return Entero con el tamano de la estructura interna de memoria
   */
  @Override
  public int size() {
    return internalSIze;
  }

  /**
   * Octavo Metodo de Trabajo de la clase. Permite a los usuarios obtener un set comformado por todos los pares llave-valor
   * contenidos dentro del mapa. Por parte del contrato de esta clase, se garantiza que todas las clases de este arreglo
   * sean completamente unicas, sin embargo, los valores puede repetirse a cuenta de que solo la llave se revisa por duplicacion.
   * En este sentido, el metodo trabaja para retornar un set con todos los pares con clave unica del arreglo.
   * <br><br>
   * <p>Internamente, el arreglo funciona de igual manera que los metodos de contains, get, entry o key set en el sentido
   * que realiza una busqueda lineal por todas las claves en primera instancia, y en cuestion de que no sean nulas, itera
   * a base de un iterador sobre todas las claves de cada bucket para anadirlas al HashSet creado antes de la ejecucion
   * de este bloque.</p>
   * @return : Set con todos los pares de datos del HashMap
   */
  @Override
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

  /**
   * Calcula el indice del bucket para un codigo hash dado usando hash suplementario y mascara de bits.
   * Este metodo es crucial para determinar donde deben almacenarse los elementos en la tabla hash.
   * <br><br>
   * <p>El metodo utiliza dos tecnicas clave para generar un indice de bucket de alta calidad:</p>
   * <ol>
   *   <li>Hash suplementario: Revuelve aun mas el hashCode de entrada para reducir agrupamientos</li>
   *   <li>AND bit a bit con (capacity - 1): Mapea el hash a un indice de bucket valido</li>
   * </ol>
   *
   * <p>La operacion AND bit a bit con (capacity - 1) funciona porque:
   * <ul>
   *   <li>La capacidad siempre es una potencia de 2 (por ejemplo, 16, 32, 64)</li>
   *   <li>Por lo tanto (capacity - 1) crea una mascara de bits de todos 1s (por ejemplo, 15 = 1111 en binario)</li>
   *   <li>Esto efectivamente realiza una operacion modulo (hash % capacity) pero es mucho mas rapida</li>
   * </ul>
   * </p>
   *
   * <p>Ejemplo: Si la capacidad es 16:
   * <pre>
   *   capacidad   = 16    = 10000 (binario)
   *   capacidad-1 = 15    = 01111 (binario)
   *   hash        = 27    = 11011 (binario)
   *   resultado   = 11    = 01011 (binario)
   * </pre>
   * Esto asegura que el resultado siempre este entre 0 y (capacidad - 1)</p>
   *
   * @param hashCode el codigo hash inicial de la clave
   * @return un indice dentro de los limites del arreglo subyacente (entre 0 y capacidad-1)
   */
  private int hash(int hashCode) {
    return supplementalHash(hashCode) & (capacity - 1); //This is using a bitwise AND
  }


  /**
   * Aplica una funcion de hash suplementaria para mejorar la distribucion de los valores hash.
   * Este metodo reduce las colisiones aplicando multiples desplazamientos XOR al valor hash original.
   * <br><br>
   * <p>La funcion realiza las siguientes operaciones:
   * <ol>
   *   <li>Desplaza el hash 20 y 12 bits a la derecha y aplica XOR con el original</li>
   *   <li>Luego desplaza el resultado 7 y 4 bits a la derecha y aplica XOR nuevamente</li>
   * </ol>
   * Esto ayuda a distribuir mejor los bits y reduce la probabilidad de colisiones.</p>
   *
   * @param h el valor hash original
   * @return un nuevo valor hash con mejor distribucion de bits
   */
  private static int supplementalHash(int h) {
    h ^= (h >>> 20) ^ (h >>> 12);
    return h ^ (h >>> 7) ^ (h >>> 4);
  }

  /**
   * Ajusta la capacidad inicial a la potencia de 2 mas cercana igual o mayor al valor dado.
   * Este metodo es crucial para mantener la eficiencia de las operaciones de hash.
   * <br><br>
   * <p>El metodo utiliza desplazamiento de bits a la izquierda para encontrar
   * la primera potencia de 2 que es mayor o igual a la capacidad inicial solicitada.
   * Esto es necesario porque las operaciones de hash son mas eficientes cuando
   * la tabla tiene un tamano que es potencia de 2.</p>
   *
   * <p>Ejemplo de funcionamiento:
   * <pre>
   *   initialCapacity = 10
   *   Iteracion 1: capacity = 1  (2^0)
   *   Iteracion 2: capacity = 2  (2^1)
   *   Iteracion 3: capacity = 4  (2^2)
   *   Iteracion 4: capacity = 8  (2^3)
   *   Iteracion 5: capacity = 16 (2^4) - Se detiene aqui
   * </pre>
   * El resultado seria 16, la primera potencia de 2 mayor que 10</p>
   *
   * @param initialCapacity la capacidad inicial solicitada
   * @return la potencia de 2 mas cercana mayor o igual a initialCapacity
   */
  private int trimToPowerOf2(int initialCapacity) {
    int capacity = 1;
    while (capacity < initialCapacity) {
      capacity <<= 1;
    }

    return capacity;
  }

  /**
   * Noveno Metodo de Trabajo de la clase. Permite a los usuarios eliminar todas las entradas contenidas en el mapa, a traves
   * de un analisis iterativo de todos aquellos buckets (linked list) no nulos en donde se aplicar el metodo <code>.clear()</code>
   * para borrar todos los valores de la lista encadenada
   */
  private void removeEntries() {
    for (int i = 0; i < capacity; i++) {
      if (table[i] != null) {
        table[i].clear();
      }
    }
  }

  /**
   * Decimo Metodo de Trabajo de la clase. Permite a los usuarios recalcular el hash de todos los valores contenidos en el
   * mapa, a traves de un analisis iterativo de todos aquellos buckets (linked list) no nulos, luego de esto, se procede a anadir todos los
   * valores de nuevo, a traves de la funcion <code>put()</code> que se encarga de calcular el nuevo hash y de agregar el valor
   * en la posicion correspondiente.
   */
  private void rehash() {
    java.util.Set<Entry<K, V>> set = entrySet(); // Get entries
    capacity <<= 1; // Double capacity
    table = new LinkedList[capacity]; // Create a new hash table
    internalSIze = 0; // Reset size to 0

    for (Entry<K, V> entry: set) {
      put(entry.getKey(), entry.getValue()); // Store to new table
    }
  }

  /**
   * Metodo que permite a los usuarios obtener un string con todos los pares de datos contenidos en el mapa, a traves de un
   * analisis iterativo de todos aquellos buckets (linked list) no nulos y cuyo conteo de valores sea mayor de cero. Luego de esto,
   * se procede a anadir todos los valores de nuevo, a traves de la funcion <code>put()</code> que se encarga de calcular
   * el nuevo hash y de agregar el valor en la posicion correspondiente.
   * @return: String con todos los pares de datos del mapa
   */
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