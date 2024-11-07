package DeberCuatroLinkedListSantiagoArellano;
import java.lang.reflect.Array;
import java.util.*;

public class SingleLinkedList<E> implements MyList<E>{

    //! Variables Internas
    /**
     * Nodo inicial de la linked list, determina el inicio de la cadena y el punto de entrada al analisis iterativo de
     * esta. Se usa en metodos especificos de insercion, remocion o modificacion de datos al inicio de la lista
     */
    protected Node<E> headNodeStartPoint;
    /**
     * Nodo final para la linked list, determina el fin de la cadena y es usada en metodos especificamente preparados
     * para insertar, eliminar o modificar elementos al final de la lista
     */
    protected Node<E> tailNodeEndPoint;

    /**
     * Contador numerico de enteros, non-zero based, para determinar la longitud total de la linked list. Es decir,
     * para una linked list de cuatro elementos este valor seria de cuatro, no de tres. Por tanto no puede ser usada
     * directamente como indice.
     **/
    protected int sizeIntegerNonZeroBased = 0;

    //! Constructores

    /**
     * Constructor por defecto de la clase SingleLinkedList
     */
    public SingleLinkedList() {
    }

    /**
     * Constructor que recibe un arreglo de objetos de tipo E. Utiliza una logica de iteracion simple para anadir cada
     * objeto de la clase basado simplemente en el objeto y su referencia mas no en un indice especifico.
     *
     * @param objects: representativo de un arreglo (built in array) de objetos del tipo parametrizado E
     */
    public SingleLinkedList(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }


    //! Implementacion de metodos especificos para la clase

    //? addFirst(E e)
    public void addFirst(E elementToAdd)
    {
        //! Paso Base: Creamos un nuevo nodo
        Node<E> newHeaderNode = new Node<>(elementToAdd);
        //! Intercambiamos con las referencias de head
        newHeaderNode.setForwardLink(this.headNodeStartPoint); /*Colocamos el link hacia el header anterior*/
        this.headNodeStartPoint = newHeaderNode; /*Reemplazamos la referencia de la variable header al actual*/
        sizeIntegerNonZeroBased++; /*Incrementamos contador de items*/

        //! Paso Inductivo: revisamos si no existe valores en el tail. Esto determina que si no existian valores antes
        //! de ingresar el nuevo nodo, ambas referencias internas apunten hacia el primero nodo como dice el TDA.
        if (this.tailNodeEndPoint == null)
        {
            this.tailNodeEndPoint = this.headNodeStartPoint;
        }
    }
    //? getFirst()
    public E getFirst()
    {
        //! Paso base: revisamos si la lista esta vacia.
        if (this.sizeIntegerNonZeroBased == 0) {return null;}
        else{return this.headNodeStartPoint.getStoredValueInNode();}
    }
    //? removeFirst()

    public E removeFirst()
    {
        //! Paso base: si el arreglo esta vacio regresamos null
        if (this.sizeIntegerNonZeroBased == 0) {return null;}
        //! Paso Inductivo: si no esta vacio procedemos a eliminar el antiguo head
        /*Copiamos la head antigua para guardar su informacion*/
        Node<E> previousHeadBeforeDeletion = this.headNodeStartPoint;
        /*Reemplazamos el puntero de head actual, para que apunte al nodo siguiente*/
        this.headNodeStartPoint = this.headNodeStartPoint.getForwardLink();
        /*Reducimos el registro del tamano de la lista*/
        this.sizeIntegerNonZeroBased--;
        /*Si nuestro nodo es nulo, significa que no tenemos elementos en el arreglo y por tanto tail tiene que ser nulo*/
        if (this.headNodeStartPoint == null){this.tailNodeEndPoint = null;}
        /*Retornamos el valor guardado en la antigua cabeza*/
        return previousHeadBeforeDeletion.getStoredValueInNode();
    }


    //? addLast(E e)
    public void addLast(E elementToAdd)
    {
        //! Paso Base: creamos el nuevo nodo
        Node<E> newTailNode = new Node<>(elementToAdd);

        //! Paso Inductivo.1: si la cola de la linked list no tiene referencia, significa que no existe valores
        //! dentro de la lista, por tanto colocamos las referencias internas apuntando al nuevo nodo
        if (this.tailNodeEndPoint == null)
        {
            this.headNodeStartPoint = this.tailNodeEndPoint = newTailNode;
        }
        else
        {
            this.tailNodeEndPoint.setForwardLink(newTailNode); /*Colocamos la referencia siguiente en la cola antigua,
            hacia la nueva*/
            this.tailNodeEndPoint = newTailNode; /*Reemplazamos la referencia interna con el nuevo nodo, desplazando el \
            puntero tail*/
        }
        sizeIntegerNonZeroBased++;
    }
    //? getLast()
    public E getLast()
    {
        if (this.sizeIntegerNonZeroBased == 0) {return null;}
        else {return this.tailNodeEndPoint.getStoredValueInNode();}
    }
    //? removeLast()
    public E removeLast()
    {
        //! Paso Base: si la lista esta vacia regresamos nulo
        if (this.sizeIntegerNonZeroBased == 0){return null;}

        //!Paso Inductivo: regresamos el valor del ultimo elemento porque no estaba vacia la lista
        if (this.sizeIntegerNonZeroBased == 1)
        {
            Node<E> previousTailBeforeDeletion = this.tailNodeEndPoint;
            this.headNodeStartPoint = this.tailNodeEndPoint = null;
            this.sizeIntegerNonZeroBased = 0;
            return previousTailBeforeDeletion.getStoredValueInNode();
        }
        else
        {
            Node<E> oneBeforeTailEndPoint = this.headNodeStartPoint;
            for(int i = 0; i < this.sizeIntegerNonZeroBased - 2 /*Dos para llegar al penultimo*/; i++)
            {
                oneBeforeTailEndPoint = oneBeforeTailEndPoint.getForwardLink();}
            //! Procedemos a hacer el intercambio de referencias y valores
            Node<E> tailPointEndPointTemporal = oneBeforeTailEndPoint.getForwardLink();
            this.tailNodeEndPoint = oneBeforeTailEndPoint;
            this.tailNodeEndPoint.setForwardLink(null);
            this.sizeIntegerNonZeroBased--;
            return tailPointEndPointTemporal.getStoredValueInNode();
        }
    }


    /**
     * Metodo para anadir elementos a la lista basado en un indice que puede estar entre [0, sizeIntegerNonZeroBased - 1].
     *
     * <p>Internamente se realiza una revision por el indice, y solo se aceptan cuales se encuentren dentro del rango especificado
     * anteriormente. Cualquier indice superior se evita con una excepcion de tipo IndexOutOfBounds</p>
     * <p>El motivo de este cambio es realizar chequeos simples, y evitar la utilizacion de indices demasiado grandes, que
     * puedan llegar a confundir al usuario.</p>
     *
     * @param indexToInsertIn: indice al cual se desea insertar el elemento
     * @param elementToInsert: elemento a insertar en la lista
     * @throws IndexOutOfBoundsException: si el indice enviado supera el rango prestablecido
     */
    @Override
    public void addElementAtIndex(int indexToInsertIn, E elementToInsert) {
        //! Paso base: revision de indices
        if(indexToInsertIn < 0 || indexToInsertIn > this.sizeIntegerNonZeroBased - 1)
        {
            throw new IndexOutOfBoundsException("Error Code 0x001 - [Raised] El indice especificado no puede ser aceptado." +
                    "No se encuentra en el rango [0, " + (this.sizeIntegerNonZeroBased - 1) + "]");
        }

        //! Paso Base.1: redireccion si el indice es 0
        if (Objects.equals(indexToInsertIn,0)){this.addFirst(elementToInsert);}

        //! Paso Base.2: redireccion si el indice es size -1
        if (Objects.equals(indexToInsertIn, this.sizeIntegerNonZeroBased - 1))
        {this.addLast(elementToInsert);}

        //! Paso Inductivo: Si ninguno de los casos bases paso, procedemos a iterar sobre la coleccion interna delimitada
        //! desde head, para insertar el nuevo elemento

        /*Establecemos una variable placeholder para el nodo justo antes del indice a insertar*/
        Node<E> oneBeforeIndexToInsertIn = this.headNodeStartPoint;
        /*Iteramos sequencialmente hasta indexToInsertIn - 1, actualizando el placeholder anterior con su .next()*/
        for(int i = 0; i < indexToInsertIn; i++){oneBeforeIndexToInsertIn = oneBeforeIndexToInsertIn.getForwardLink();}
        /*Establecemos una variable placeholder para el nodo justo despues de indexToInsertIn - 1, es decir, para */
        /*el nodo anterior*/
        Node<E> oneAfterIndexToInsertInPreviously = oneBeforeIndexToInsertIn.getForwardLink();
        /*Conectamos el nuevo nodo de elementToInsert, al nodo justo antes del indice dado*/
        oneBeforeIndexToInsertIn.setForwardLink(new Node<E>(elementToInsert));
        /*Conectamos el nuevo nodo (i.e. el del paso anterior) con el nodo del segundo placeholder*/
        oneBeforeIndexToInsertIn.getForwardLink().setForwardLink(oneAfterIndexToInsertInPreviously);
        this.sizeIntegerNonZeroBased++;
    }

    /**
     * Return the element from this list at the specified index
     *
     * @param index
     */
    @Override
    public E getElementAtIndex(int index) {
        //! Paso Base: revisamos si el index esta en el rango apropiado
        if (index < 0 || index > this.sizeIntegerNonZeroBased - 1){
            throw new IndexOutOfBoundsException("Error Code 0x001 - [Raised] El indice especificado no puede ser aceptado." +
                    "No se encuentra en el rango [0, " + (this.sizeIntegerNonZeroBased - 1) + "]");
        }
        //! Paso Inductivo: iteramos hasta llegar al indice deseado
        Node<E> nodeAtAnalysis = this.headNodeStartPoint;
        for(int i = 0; i < index; i++){
            nodeAtAnalysis = nodeAtAnalysis.getForwardLink();
        }
        //! Como nuestro valor esta un detras del indice requerido,
        if (nodeAtAnalysis != null)
        {
            return nodeAtAnalysis.getStoredValueInNode();
        }
        else {return null;}
    }

    /**
     * Return the index of the first matching element in this list.
     * Return -1 if no match.
     *
     * @param e
     */
    @Override
    public int indexOfElement(Object e) {
        //! Paso Inductivo: realizamos un analisis discreto de cada nodo hasta encontrar uno con un storage igual al objeto
        //! enviado
        Node<E> nodeAtAnalysis = this.headNodeStartPoint;
        for(int i = 0; i < this.sizeIntegerNonZeroBased; i++)
        {
            if (nodeAtAnalysis.getStoredValueInNode().equals(e)){return i;}
            nodeAtAnalysis = nodeAtAnalysis.getForwardLink();
        }
        return -1;
    }

    /**
     * Return the index of the last matching element in this list
     * Return -1 if no match.
     *
     * @param e
     */
    @Override
    public int lastIndexOfElement(E e) {

            //! Paso Inductivo: realizamos un analisis discreto de cada nodo hasta encontrar uno con un storage
            //! igual al objeto pero retornamos solo al final
            Node<E> nodeAtAnalysis = this.headNodeStartPoint;
            int indexAtReturn = -1;
            for(int i = 0; i < this.sizeIntegerNonZeroBased; i++)
            {
                if (nodeAtAnalysis.getStoredValueInNode().equals(e)){indexAtReturn = i;}
                nodeAtAnalysis = nodeAtAnalysis.getForwardLink();
            }
            return indexAtReturn;
    }

    /**
     * Remove the element at the specified position in this list
     * Shift any subsequent elements to the left.
     * Return the element that was removed from the list.
     *
     * @param indexToDeleteNodeFrom: indice del nodo a eliminar
     * @throws IndexOutOfBoundsException: si el indice enviado supera el rango prestablecido
     * @return elemento eliminado
     */
    @Override
    public E removeElementAtIndex(int indexToDeleteNodeFrom) throws IndexOutOfBoundsException{
        if (indexToDeleteNodeFrom < 0 || indexToDeleteNodeFrom > this.sizeIntegerNonZeroBased - 1) {
            throw new IndexOutOfBoundsException("Error Code 0x001 - [Raised] El indice especificado no puede ser aceptado." +
                    "No se encuentra en el rango [0, " + (this.sizeIntegerNonZeroBased - 1) + "]");
        }
        //! Paso Base.1: si el indice es exactamente zero llamamos a removeFirst()
        if (Objects.equals(indexToDeleteNodeFrom, 0)){return this.removeFirst();}
        //! Paso Base.2: si el indicie es la longitud del arreglo - 1 llamamos a removeLast()
        if (Objects.equals(indexToDeleteNodeFrom, this.sizeIntegerNonZeroBased - 1)){return this.removeLast();}

        //! Paso Inductivo: revisamos iterativamente hasta encontrar el elemento por eliminar
        Node<E> nodeAtAnalysis = this.headNodeStartPoint;
        for(int i = 0; i < indexToDeleteNodeFrom; i++)
        {
            nodeAtAnalysis = nodeAtAnalysis.getForwardLink();
        }
        //! Como nos encontramos en un nodo menor del que buscamos, simplemente variamos las referencias de este
        Node<E> securityCopyOfDeletedNode = nodeAtAnalysis.getForwardLink();
        nodeAtAnalysis.setForwardLink(securityCopyOfDeletedNode.getForwardLink());
        this.sizeIntegerNonZeroBased--;
        return securityCopyOfDeletedNode.getStoredValueInNode();
    }

    /**
     * Replace the element at the specified position in this list
     * with the specified element and returns the new set.
     *
     * @param indexToDeleteNodeFrom:  indice del nodo a reemplazar
     * @param elementToUpdateTo: elemento a reemplazar
     */
    @Override
    public E setElementAtIndex(int indexToDeleteNodeFrom, E elementToUpdateTo) {
        if (indexToDeleteNodeFrom < 0 || indexToDeleteNodeFrom > this.sizeIntegerNonZeroBased - 1) {
            throw new IndexOutOfBoundsException("Error Code 0x001 - [Raised] El indice especificado no puede ser aceptado." +
                    "No se encuentra en el rango [0, " + (this.sizeIntegerNonZeroBased - 1) + "]");
        }

        //! Paso Inductivnductivo: revisamos iterativamente hasta encontrar el elemento por variar
        Node<E> nodeAtAnalysis = this.headNodeStartPoint;
        for(int i = 0; i < indexToDeleteNodeFrom; i++)
        {
            nodeAtAnalysis = nodeAtAnalysis.getForwardLink();
        }
        //! Variamos los datos
        E storedValuePreviously = nodeAtAnalysis.getStoredValueInNode();
        nodeAtAnalysis.setStoredValueInNode(elementToUpdateTo);
        return storedValuePreviously;
    }

    @Override
    public int size() {
        return this.sizeIntegerNonZeroBased;
    }


    @Override
    public boolean contains(Object o) {
        Node<E> nodeAtAnalysis = this.headNodeStartPoint;
        for(int i = 0; i < this.sizeIntegerNonZeroBased; i++)
        {
            if (nodeAtAnalysis.getStoredValueInNode().equals(o)){return true;}
            nodeAtAnalysis = nodeAtAnalysis.getForwardLink();
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new SingleLinkedListIterator();
    }


    @Override
    public void clear() {
        this.headNodeStartPoint = this.tailNodeEndPoint = null;
        this.sizeIntegerNonZeroBased = 0;
    }

    private class SingleLinkedListIterator implements Iterator<E>
    {
        protected Node<E> currentNodeAtAnalysis = headNodeStartPoint;
        protected Node<E> previousNodeAtAnalysis;
        protected Boolean canRemoveNodeAtAnalysis = false;

        @Override
        public boolean hasNext() {
            return (currentNodeAtAnalysis != null);
        }

        @Override
        public E next() {
            if (!this.hasNext())
            {
                throw new NoSuchElementException("Error Code 0x001 - [Raised] No hay mas elementos en la lista");
            }

            E data = currentNodeAtAnalysis.getStoredValueInNode();
            previousNodeAtAnalysis = currentNodeAtAnalysis;
            currentNodeAtAnalysis = currentNodeAtAnalysis.getForwardLink();
            canRemoveNodeAtAnalysis = true;
            return data;
        }
        @Override
        public void remove() {
            if (!canRemoveNodeAtAnalysis) {throw new
                    IllegalStateException("Error Code 0x001 - [Raised] No se puede remover ahora, solo luego de realizar " +
                    "una operacion de next()");}

            if (previousNodeAtAnalysis == headNodeStartPoint) {
                // Removing the first element
                removeFirst();
            } else if (currentNodeAtAnalysis == null) {
                // Removing the last element
                removeLast();
            } else {
                // Removing an element in the middle
                Node<E> beforePrev = headNodeStartPoint;
                while (beforePrev != null && beforePrev.getForwardLink() != previousNodeAtAnalysis) {
                    beforePrev = beforePrev.getForwardLink();
                }

                if (beforePrev != null) {
                    beforePrev.setForwardLink(currentNodeAtAnalysis);
                    sizeIntegerNonZeroBased--;
                }
            }

            canRemoveNodeAtAnalysis = false;
        }

    }


    @Override /** Override toString() to return elements in the list */
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        if (this.sizeIntegerNonZeroBased == 0) {result.append("]");}
        Node<E> current = this.headNodeStartPoint;
        for (int i = 0; i < this.sizeIntegerNonZeroBased; i++) {
            result.append(current.getStoredValueInNode());
            current = current.getForwardLink();
            if (current != null) {
                result.append("] --> ["); // Separate two elements with a comma
            }
            else {
                result.append("]"); // Insert the closing ] in the string
            }
        }

        return result.toString();
    }


    @Override
    public boolean add(E e) {
        int previousSize = this.sizeIntegerNonZeroBased;
        this.addLast(e);
        return previousSize < this.sizeIntegerNonZeroBased;
    }

    @Override
    public boolean isEmpty() {
        return (this.size() == 0);
    }

    @Override
    public boolean remove(Object e) {
        Node<E> nodeAtAnalysis = this.headNodeStartPoint;
        for(int i= 0; i < this.sizeIntegerNonZeroBased; i++ )
        {
            if(nodeAtAnalysis.getStoredValueInNode().equals(e))
            {
                var element = this.removeElementAtIndex(i);
                return true;
            }
            nodeAtAnalysis = nodeAtAnalysis.getForwardLink();
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int previousSize = this.sizeIntegerNonZeroBased;
        for(E o : c)
        {
            this.addLast(o);
        }
        return previousSize < this.sizeIntegerNonZeroBased;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean wasModifiedInAnalysis = false;
        SingleLinkedListIterator iterator = new SingleLinkedListIterator();
        while (iterator.hasNext())
        {
            var result = iterator.next();
            if (c.contains(result))
            {
                iterator.remove();
                wasModifiedInAnalysis = true;
            }
        }
        return wasModifiedInAnalysis;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean wasModifiedInAnalysis = false;
        SingleLinkedListIterator it = new SingleLinkedListIterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                wasModifiedInAnalysis = true;
            }
        }
        return wasModifiedInAnalysis;
    }


    @Override
    public Object[] toArray() {
        return toArray(new Object[this.sizeIntegerNonZeroBased]);
    }

    @Override
    public <T> T[] toArray(T[] array) {
       //! Paso Base.1: como no conocemos la longitud del arreglo, la arreglamos para que tenga un tamano adecuado
        if (array.length < this.size())
        {
            array = (T[]) Array.newInstance(array.getClass().getComponentType(), this.size());
        }

        //! Paso Inductivo: copiamos los valores
        Node<E> nodeAtAnalysis = this.headNodeStartPoint;
        for(int i = 0; i < this.size(); i++)
        {
            array[i] = (T) nodeAtAnalysis.getStoredValueInNode();
            nodeAtAnalysis = nodeAtAnalysis.getForwardLink();
        }
        if (array.length > this.size()) {
            array[this.size()] = null;
        }
        return array;
    }
}
