package DeberOchoBinaryTreesSantiagoArellano;

import DeberOchoBinaryTreesSantiagoArellano.BinaryTreeNode;
import java.util.*;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @Description: El presente archivo muestra una implementacion compleja de un Arbol Binario basado en la interface Tree
 * definida anteriormente. Los metodos de esta clase han sido probados en su totalidad y funcionan tanto con metodos de
 * Listas como metodos normales de un arbol binario.
 * <br><br>
 * La clase muestra las siguientes utilidades:
 * <ul>
 *     <li>Implementaciones Recursivas e Iterativas para Transversacion DFS</li>
 *     <li>Implementaciones Iterativas de t odo orden para Transversacion BFS</li>
 *     <li>Implementacion Iterativa para detectar altura del arbol interno</li>
 *     <li>Varios constructors probados para Collection, built-in array, single value y otro arbol binario</li>
 *     <li>Varios metodos remove tanto unicos para la clase como implementaciones de la interface Lista</li>
 *     <li>Varios metodos para insertar y variar elementos dentro del arreglo</li>
 * </ul>
 * <p><code>Es importante destacar que la clase <b>no presenta una implementacion de iteradores por el momento,
 * la clase no retornara un iterador para cualquier llamada al mismo, tanto iterator() como spliterator()</b></code></p>
 * <br><br>
 * <p>La presente implementacion <b>incluye los metodos de getNumberofLeaves() y getNumberOfNonLeaves() provenientes de los
 * ejercicios 25.6 y 25.7 enviados como deber</b></p>
 * @param <E>: Tipo de dato Generico utilizado para instanciar la clase, este dato debe extender Comparable
 */
public class BinaryTree<E extends Comparable<E>> implements Tree<E> {

    //? Main initializer variable section
    private BinaryTreeNode<E> eInternalRoot;
    private Integer eInternalSize = 0;
    private Integer eInternalHeight = 0;

    //? Main Constructor definition and implementation
    public BinaryTree(E  toHeadElement) throws IllegalArgumentException{
        if (toHeadElement != null){
            this.setInternalRoot(new BinaryTreeNode<>(toHeadElement));
            this.eInternalSize++;
            this.eInternalHeight = 1;
        }
        else{
            throw new IllegalArgumentException("Error Code 0x001 - [Raised] Value passed into this function was null");
        }
    }
    public BinaryTree(BinaryTreeNode<E> rootToPlace) throws NullPointerException{
        this.setInternalRoot(rootToPlace);
        this.eInternalSize++;
    }
    public BinaryTree(E[] elementsToAdd){
        this.addAll(Arrays.asList(elementsToAdd));
    }
    public BinaryTree(BinaryTreeNode<E>[] nodesToAdd){
        this.insertionOfVariousNodesForTrees(nodesToAdd);
    }
    public BinaryTree(Collection<E> nodesToAdd){
        this.insertionOfVariousNodesForTrees(nodesToAdd);
    }
    public BinaryTree(BinaryTree<E> treeToAdd){
        Optional<List<E>> result = treeToAdd.topBottomLeftRightBFS(treeToAdd.getEInternalRoot(), new Function<E, Optional<E>>() {
            @Override
            public Optional<E> apply(E e) {
                return Optional.of(e);
            }
        });
        if (result.isPresent()){
            this.insertionOfVariousNodesForTrees(result.get());
        }
    }

    //! Find The Leaves Method
    public int getNumberOfLeaves(){
        if (this.getEInternalRoot() == null) {
            return 0;
        }

        int leafCount = 0;
        Queue<BinaryTreeNode<E>> queue = new LinkedList<>();
        queue.add(this.getEInternalRoot());

        while (!queue.isEmpty()) {
            BinaryTreeNode<E> current = queue.poll();

            // Check if the node is a leaf
            if (current.getLeftOrThreadedChild() == null && current.getRightOrThreadedChild() == null) {
                leafCount++;
            }

            // Add child nodes to the queue
            if (current.getLeftOrThreadedChild() != null) {
                queue.add(current.getLeftOrThreadedChild());
            }
            if (current.getRightOrThreadedChild() != null) {
                queue.add(current.getRightOrThreadedChild());
            }
        }

        return leafCount;
    }

    //! Find the Non-Leaves Method
    public int getNumberOfNonLeaves(){
        if (this.getEInternalRoot() == null) {
        return 0;
    }

        int nonLeafCount = 0;
        Queue<BinaryTreeNode<E>> queue = new LinkedList<>();
        queue.add(this.getEInternalRoot());

        while (!queue.isEmpty()) {
            BinaryTreeNode<E> current = queue.poll();

            // Check if the node is a non-leaf
            if (current.getLeftOrThreadedChild() != null || current.getRightOrThreadedChild() != null) {
                nonLeafCount++;
            }

            // Add child nodes to the queue
            if (current.getLeftOrThreadedChild() != null) {
                queue.add(current.getLeftOrThreadedChild());
            }
            if (current.getRightOrThreadedChild() != null) {
                queue.add(current.getRightOrThreadedChild());
            }
        }

        return nonLeafCount;
    }



    //? Main Traversal Method Definition
    @Override
    public void inorderRecursive(BinaryTreeNode<E> Root){
        if (Root != null){
            inorderRecursive(Root.getLeftOrThreadedChild());
            System.out.println("Root Value At the Time= " + Root.getInternalStoredData());
            inorderRecursive(Root.getRightOrThreadedChild());
        }
    }
    @Override
    public Optional<List<E>> inorderIterative(BinaryTreeNode<E> Root, Function<E, Optional<E>> userDefinedProcessing)
            throws NullPointerException{
        if (Root == null || userDefinedProcessing == null){
            throw new NullPointerException("Error Code 0x001 - [Raised] Either of the parameters passed into this function was null.");
        }

        Stack<BinaryTreeNode<E>> travStack = new Stack<>();
        BinaryTreeNode<E> refToRoot = Root;
        List<E> resultsList = new ArrayList<>();
        while (refToRoot != null) {
            while (refToRoot != null) {
                if (refToRoot.getRightOrThreadedChild() != null) {
                    travStack.push(refToRoot.getRightOrThreadedChild());
                }
                travStack.push(refToRoot);
                refToRoot = refToRoot.getLeftOrThreadedChild();
            }
            refToRoot = travStack.pop();
            while (!travStack.isEmpty() && refToRoot.getRightOrThreadedChild() == null){
                var result = userDefinedProcessing.apply(refToRoot.getInternalStoredData());
                result.ifPresent(resultsList::add);
                refToRoot = travStack.pop();
            }
            var result = userDefinedProcessing.apply(refToRoot.getInternalStoredData());
            result.ifPresent(resultsList::add);
            if (!travStack.empty()){
                refToRoot = travStack.pop();
            }
            else {
                refToRoot = null;
            }
        }
        return (!resultsList.isEmpty() ? Optional.of(resultsList): Optional.empty());
    }
    @Override
    public void preorderRecursive(BinaryTreeNode<E> Root) {
        if (Root != null) {
            System.out.println("Root Value At the Time= " + Root.getInternalStoredData());
            preorderRecursive(Root.getLeftOrThreadedChild());
            preorderRecursive(Root.getRightOrThreadedChild());
        }
    }
    @Override
    public Optional<List<E>> preorderIterative(BinaryTreeNode<E> Root, Function<E, Optional<E>> userDefinedProcessing)
            throws NullPointerException {
        if (Root == null || userDefinedProcessing == null){
                throw new NullPointerException("Error Code 0x001 - [Raised] Either of the parameters passed into this function was null.");
        }

        Stack<BinaryTreeNode<E>> travStack = new Stack<>();
        List<E> resultList = new ArrayList<>();
        BinaryTreeNode<E> p = Root;
        if (p != null){
            travStack.push(p);
            while (!travStack.empty()){
                p = travStack.pop();
                var result = userDefinedProcessing.apply(p.getInternalStoredData());
                result.ifPresent(resultList::add);
                if (p.getRightOrThreadedChild() != null){
                    travStack.push(p.getRightOrThreadedChild());
                }
                if (p.getLeftOrThreadedChild() != null){
                    travStack.push(p.getLeftOrThreadedChild());
                }
            }
        }
        return (!resultList.isEmpty() ? Optional.of(resultList): Optional.empty());
    }
    @Override
    public void postorderRecursive(BinaryTreeNode<E> Root) {
        if (Root != null){
            postorderRecursive(Root.getLeftOrThreadedChild());
            postorderRecursive(Root.getRightOrThreadedChild());
            System.out.println("Root Value At The Time = " + Root.getInternalStoredData());
        }
    }
    @Override
    public Optional<List<E>> postorderIterative(BinaryTreeNode<E> Root, Function<E, Optional<E>> userDefinedProcessing)
            throws NullPointerException{
        if (Root == null || userDefinedProcessing == null){
            throw new NullPointerException("Either of the parameters passed into this function was null.");
        }
        Stack<BinaryTreeNode<E>> travStack = new Stack<>();
        BinaryTreeNode<E> p = Root, q = Root;
        List<E> resultList = new ArrayList<>();
        while (p != null){
            for(; p.getLeftOrThreadedChild() != null; p = p.getLeftOrThreadedChild()){
                travStack.push(p);
            }
            while (p.getRightOrThreadedChild() == null || p.getRightOrThreadedChild() == q){
                var result = userDefinedProcessing.apply(p.getInternalStoredData());
                result.ifPresent(resultList::add);
                q = p;
                if (travStack.isEmpty()){return (!resultList.isEmpty() ? Optional.of(resultList): Optional.empty());}
                p = travStack.pop();
            }
            travStack.push(p);
            p = p.getRightOrThreadedChild();
        }
        return (!resultList.isEmpty() ? Optional.of(resultList) : Optional.empty());
    }
    @Override
    public Optional<List<E>> topBottomLeftRightBFS(BinaryTreeNode<E> Root, Function<E, Optional<E>> userDefinedProcessing)
            throws NullPointerException{
        if (Root == null || userDefinedProcessing == null){
            throw new NullPointerException("Error Code 0x001 - [Raised] Either of the parameters passed into this function " +
                    "was null");
        }
        List<E> resultList = new ArrayList<>();
        Queue<BinaryTreeNode<E>> nodeQueue = new ArrayDeque<>();
        BinaryTreeNode<E> nodeTemp = Root;
        if (nodeTemp != null){
            nodeQueue.offer(nodeTemp);
            while (!nodeQueue.isEmpty()){
                nodeTemp = nodeQueue.poll();
                var result = userDefinedProcessing.apply(nodeTemp.getInternalStoredData());
                result.ifPresent(resultList::add);
                if (nodeTemp.getLeftOrThreadedChild() != null){
                    nodeQueue.offer(nodeTemp.getLeftOrThreadedChild());
                }
                if (nodeTemp.getRightOrThreadedChild() != null){
                    nodeQueue.offer(nodeTemp.getRightOrThreadedChild());
                }
            }
        }
        return (!resultList.isEmpty() ? Optional.of(resultList): Optional.empty());
    }
    @Override
    public Optional<List<E>> topBottomRightLeftBFS(BinaryTreeNode<E> Root, Function<E, Optional<E>> userDefinedProcessing)
            throws NullPointerException{
        if (Root == null || userDefinedProcessing == null){
            throw new NullPointerException("Error Code 0x001 - [Raised] Either of the parameters passed into this function " +
                    "was null");
        }
        List<E> resultList = new ArrayList<>();
        Queue<BinaryTreeNode<E>> nodeQueue = new ArrayDeque<>();
        BinaryTreeNode<E> nodeTemp = Root;
        if (nodeTemp != null){
            nodeQueue.offer(nodeTemp);
        }
        while (!nodeQueue.isEmpty()){
            nodeTemp = nodeQueue.poll();
            var result = userDefinedProcessing.apply(nodeTemp.getInternalStoredData());
            result.ifPresent(resultList::add);
            if (nodeTemp.getRightOrThreadedChild() != null){
                nodeQueue.offer(nodeTemp.getRightOrThreadedChild());
            }
            if (nodeTemp.getLeftOrThreadedChild() != null){
                nodeQueue.offer(nodeTemp.getLeftOrThreadedChild());
            }
        }
        return (!resultList.isEmpty() ? Optional.of(resultList): Optional.empty());
    }

    @Override
    public Optional<List<E>> bottomTopLeftRightBFS(BinaryTreeNode<E> Root, Function<E, Optional<E>> userDefinedProcessing)
            throws NullPointerException{
        if (Root == null || userDefinedProcessing == null){
            throw new NullPointerException("Error Code 0x001 - [Raised] Either of the parameters passed into this function " +
                    "was null");
        }
        List<E> resultList = new ArrayList<>();
        Queue<BinaryTreeNode<E>> nodeQueue = new ArrayDeque<>();
        ArrayList<ArrayList<E>> levelList = new ArrayList<>();
        BinaryTreeNode<E> rootTemp = Root;
        if (rootTemp != null) {
            nodeQueue.offer(rootTemp);
        }
        while (!nodeQueue.isEmpty()) {
            int levelSize = nodeQueue.size();
            ArrayList<E> currentLevel = new ArrayList<>();
            for (int i = 1; i <= levelSize; i++) {
                rootTemp = nodeQueue.poll();
                currentLevel.add(rootTemp.getInternalStoredData());
                if (rootTemp.getLeftOrThreadedChild() != null) {
                    nodeQueue.offer(rootTemp.getLeftOrThreadedChild());
                }
                if (rootTemp.getRightOrThreadedChild() != null) {
                    nodeQueue.offer(rootTemp.getRightOrThreadedChild());
                }
            }
            levelList.add(currentLevel);
        }
        Collections.reverse(levelList);
        for(ArrayList<E> level: levelList){
            for(E value: level){
                var result = userDefinedProcessing.apply(value);
                result.ifPresent(resultList::add);
            }
        }
        return (!resultList.isEmpty() ? Optional.of(resultList) : Optional.empty());
    }

    @Override
    public Optional<List<E>> bottomTopRightLeftBFS(BinaryTreeNode<E> Root, Function<E, Optional<E>> userDefinedProcessing)
            throws NullPointerException {
        if (Root == null || userDefinedProcessing == null){
            throw new NullPointerException("Error Code 0x001 - [Raised] Either of the parameters passed into this function " +
                    "was null");
        }
        List<E> resultList = new ArrayList<>();
        Queue<BinaryTreeNode<E>> nodeQueue = new ArrayDeque<>();
        ArrayList<ArrayList<E>> levelList = new ArrayList<>();
        BinaryTreeNode<E> rootTemp = Root;
        if (rootTemp != null) {
            nodeQueue.offer(rootTemp);
        }
        while (!nodeQueue.isEmpty()) {
            int levelSize = nodeQueue.size();
            ArrayList<E> currentLevel = new ArrayList<>();
            for (int i = 1; i <= levelSize; i++) {
                rootTemp = nodeQueue.poll();
                currentLevel.add(rootTemp.getInternalStoredData());

                if (rootTemp.getRightOrThreadedChild() != null) {
                    nodeQueue.offer(rootTemp.getRightOrThreadedChild());
                }
                if (rootTemp.getLeftOrThreadedChild() != null) {
                    nodeQueue.offer(rootTemp.getLeftOrThreadedChild());
                }
            }
            levelList.add(currentLevel);
        }
        Collections.reverse(levelList);
        for(ArrayList<E> level: levelList){
            for(E value: level){
                var result = userDefinedProcessing.apply(value);
                result.ifPresent(resultList::add);
            }
        }
        return (!resultList.isEmpty() ? Optional.of(resultList) : Optional.empty());
    }

    //? Main Insertion Method Definitions
    public void insertionForBinaryTree(E elementToInsert){
        BinaryTreeNode<E> nodeAtAnalysis = this.eInternalRoot;
        if (elementToInsert == null){
            throw new NullPointerException("Error Code 0x001 - [Raised] The value passed into this method was null");
        }
        if (nodeAtAnalysis == null) {
            this.setInternalRoot(new BinaryTreeNode<>(elementToInsert));
        }
            while (nodeAtAnalysis != null){
                if (elementToInsert.compareTo(nodeAtAnalysis.getInternalStoredData()) < 0){
                    if (nodeAtAnalysis.getLeftOrThreadedChild() == null){
                        nodeAtAnalysis.setLeftOrThreadedChild(new BinaryTreeNode<>(elementToInsert));
                        this.eInternalSize++;
                        return;
                    }
                    else {
                        nodeAtAnalysis = nodeAtAnalysis.getLeftOrThreadedChild();
                    }
                }
                else if (elementToInsert.compareTo(nodeAtAnalysis.getInternalStoredData()) > 0){
                    if (nodeAtAnalysis.getRightOrThreadedChild() == null){
                        nodeAtAnalysis.setRightOrThreadedChild(new BinaryTreeNode<>(elementToInsert));
                        this.eInternalSize++;
                        return;
                    }
                    else {
                        nodeAtAnalysis = nodeAtAnalysis.getRightOrThreadedChild();
                    }
                }
                else {
                    return; // This implementation assumes that the child node is already in place, if at some point the
                    // same value is compared with the passed in value this block will simply quit the function.
                }
            }
        }

    @Override
    public void insertionOfVariousNodesForTrees(Collection<E> nodesToAdd) throws NullPointerException{
        if (nodesToAdd != null) {
            Integer index = 0;
            List<E> nodesToAddTwo = nodesToAdd.stream().toList();
            if (nodesToAdd.size() >= 1 && this.getEInternalRoot() == null) {
                this.setInternalRoot(new BinaryTreeNode<>(nodesToAddTwo.get(index++)));
            }
            for(int i = index; i < nodesToAdd.size(); i++){
                this.insertionForBinaryTree(nodesToAddTwo.get(i));
            }
        }
        else {
            throw new NullPointerException("Error Code 0x0001 - [Raised] The collection of nodes passed into this method " +
                    "was null.");
        }
    }
    @Override
    public void insertionOfVariousNodesForTrees(BinaryTreeNode<E>[] nodesToAdd) throws NullPointerException{
        if (nodesToAdd != null){
            Integer index = 0;
            if (nodesToAdd.length >= 1 && this.getEInternalRoot() == null){
                this.setInternalRoot(nodesToAdd[index++]);
            }
            for(int i = index; i < nodesToAdd.length; i++){
                this.insertionForBinaryTree(nodesToAdd[i].getInternalStoredData());
            }

        }
        else {

            throw new NullPointerException("Eror Code 0001 - [Raised] The collection of nodes passed into this method" +
                    "was null.");
        }
    }

    //? Main Search Method Definition
    public final boolean searchInBinaryTrees(BinaryTreeNode<E> Root, final E  element)
            throws NullPointerException{
        if (Root == null || element == null){
            throw new NullPointerException("Error Code 0x001 - [Raised] Either of the inputs sent into this function were " +
                    "null.");
        }
        while (Root != null){
            if (element.compareTo(Root.getInternalStoredData()) == 0){
                return true;
            }
            else if (element.compareTo(Root.getInternalStoredData()) < 0){
                Root = Root.getLeftOrThreadedChild();
            }
            else {Root = Root.getRightOrThreadedChild();}
        }
        return false;
    }

    //? Helper Methods
    @Override
    public int size() {
        return this.eInternalSize;
    }
    @Override
    public boolean isEmpty() {
        return this.eInternalSize == 0;
    }
    @Override
    public boolean contains(Object o) {
        try{
            E element = (E) o;
            return this.searchInBinaryTrees(this.eInternalRoot, element);
        }
        catch (ClassCastException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public Iterator<E> iterator() {
        return null;
    }
    @Override
    public void clear() {
        this.eInternalRoot = null;
        this.eInternalSize = 0;
    }
    @Override
    public Object[] toArray() {
        Optional<List<E>> resultOfBFS = this.topBottomLeftRightBFS(this.eInternalRoot, new Function<E, Optional<E>>() {
            @Override
            public Optional<E> apply(E e) {
                return Optional.of(e);
            }
        });
        return (resultOfBFS.map(List::toArray).orElseGet(() -> new Object[]{}));
    }
    public Integer geteInternalHeight(){
        //! Calculate the height of the tree based on the number of arrays structured through BFS
        Queue<BinaryTreeNode<E>> nodeQueue = new ArrayDeque<>();
        ArrayList<ArrayList<E>> levelList = new ArrayList<>();
        BinaryTreeNode<E> rootTemp = this.getEInternalRoot();
        if (rootTemp != null) {
            nodeQueue.offer(rootTemp);
        }
        while (!nodeQueue.isEmpty()) {
            int levelSize = nodeQueue.size();
            ArrayList<E> currentLevel = new ArrayList<>();
            for (int i = 1; i <= levelSize; i++) {
                rootTemp = nodeQueue.poll();
                currentLevel.add(rootTemp.getInternalStoredData());
                if (rootTemp.getLeftOrThreadedChild() != null) {
                    nodeQueue.offer(rootTemp.getLeftOrThreadedChild());
                }
                if (rootTemp.getRightOrThreadedChild() != null) {
                    nodeQueue.offer(rootTemp.getRightOrThreadedChild());
                }
            }
            levelList.add(currentLevel);
        }

        return levelList.size();
    }
    @Override
    public <T> T[] toArray(T[] a) {
        Optional<List<E>> resultOfBFS = this.topBottomLeftRightBFS(this.eInternalRoot, new Function<E, Optional<E>>() {
            @Override
            public Optional<E> apply(E e) {
                return Optional.of(e);
            }
        });
        ArrayList<T> arrayList = new ArrayList<>();
        if(resultOfBFS.isPresent()){
            for(E element: resultOfBFS.get()){
                arrayList.add((T) element);
            }
        }
        return arrayList.toArray((T[]) new Object[0]);
    }

    //? Main Addition Method Declaration
    @Override
    public boolean add(E e) {
        var sizePrev = this.size();
        this.insertionForBinaryTree(e);
        return sizePrev < this.size();
    }
    @Override
    public boolean addAll(Collection<? extends E> c) {
        var sizePrev = this.size();
        if (c != null){
            if (!c.isEmpty()) {
                if (this.getEInternalRoot() == null) {
                    List<E> list = (List<E>) c.stream().toList();
                    this.setInternalRoot(new BinaryTreeNode<>(list.getFirst()));
                    this.eInternalSize++;
                    for (int i = 1; i < list.size(); i++) {
                        this.insertionForBinaryTree(list.get(i));
                    }
                } else {
                    for (E element : c) {
                        this.insertionForBinaryTree(element);
                    }
                }
            }
            else{
                throw new IllegalArgumentException("Error Code 0x001 - [Raised] The collection passed into this emthod was empty");
            }
        }
        else{
            throw new NullPointerException("Error Code 0x001 - [Raised] The collection passed into this segment of code was null");
        }
        return sizePrev < this.size();
    }
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        //! Given that  do not have a way to modify the tree iteartively without breaking down the tree, then we have to agree that in general
        //! we need to append the items just as if we were doing a normal addAll
        var sizePrev = this.size();
        this.addAll(c);
        return sizePrev < this.size();
    }
    @Override
    public void addFirst(E e) {
        //! Inductive Step: check for nullitiy
        if (e == null || this.getEInternalRoot() == null)/*We are lost in a case of nullities*/{
            throw new NullPointerException("Error Code 0x001 - [Raised] The parameters passed into this method were null");
        }
        //! Extract the values of the tree in the way one would do for inorder traversal
        Optional<List<E>> result = this.topBottomLeftRightBFS(this.getEInternalRoot(), Optional::of);
        //! Clear the list such that we can add the nodes
        this.clear();
        this.setInternalRoot(new BinaryTreeNode<>(e));
        for(E element: result.get()){
            this.insertionForBinaryTree(element);
        }

    }
    @Override
    public void addLast(E e) throws NullPointerException{
        this.insertionForBinaryTree(e);
    }
    //? Main Node Removal Definition
    public void deleteNode(E element) {
        //! This method will iterate over the tree and try to find a node with the given value, starting from its root to
        //! the deepest node using BFS Top-Bottom -> Left-Right approach
        BinaryTreeNode<E> nodeParent = this.getEInternalRoot();
        BinaryTreeNode<E> nodeNow = this.getEInternalRoot();

        if (nodeNow == null){
            return; //Arbol Vacio
        }

        while (nodeNow != null){

            int compareResult = element.compareTo(nodeNow.getInternalStoredData());

            if (compareResult < 0){
                nodeParent = nodeNow;
                nodeNow = nodeNow.getLeftOrThreadedChild();
            }
            else if (compareResult > 0){
                nodeParent = nodeNow;
                nodeNow = nodeNow.getRightOrThreadedChild();
            }
            else {
                //! 1.st Check for the single node case
                if (nodeNow.getLeftOrThreadedChild() == null && nodeNow.getRightOrThreadedChild() == null){
                    if (nodeNow == this.getEInternalRoot()){
                        this.eInternalRoot = null;
                    }
                    else if (nodeParent.getLeftOrThreadedChild() == nodeNow){
                        nodeParent.setLeftOrThreadedChild(null);
                    }
                    else {
                        nodeParent.setRightOrThreadedChild(null);
                    }
                }
                //! 2.nd Check for Node with a single child node
                else if (nodeNow.getRightOrThreadedChild() != null || nodeNow.getLeftOrThreadedChild() != null){
                    BinaryTreeNode<E> childNode;
                    if (nodeNow.getLeftOrThreadedChild() != null){childNode = nodeNow.getLeftOrThreadedChild();}
                    else {childNode = nodeNow.getRightOrThreadedChild();}

                    if (nodeNow == this.getEInternalRoot()){
                        this.setInternalRoot(childNode);
                    }
                    else if (nodeParent.getLeftOrThreadedChild() == nodeNow){
                        nodeParent.setLeftOrThreadedChild(childNode);
                    }
                    else{nodeParent.setRightOrThreadedChild(childNode);}
                }
                else if (nodeNow.getLeftOrThreadedChild() != null && nodeNow.getRightOrThreadedChild() != null){
                    deleteByCopying(nodeNow);
                }
                return;
            }
        }
    }
    private void deleteByCopying(BinaryTreeNode<E> node){
        BinaryTreeNode<E> previous, temp = node;
        if (node.getRightOrThreadedChild() == null){
            node = node.getLeftOrThreadedChild();
        }
        else if (node.getLeftOrThreadedChild() == null){
            node = node.getRightOrThreadedChild();
        }
        else{
            temp = node.getLeftOrThreadedChild();
            previous = node;
            while (temp.getRightOrThreadedChild() != null){
                previous = temp;
                temp = temp.getRightOrThreadedChild();
            }
            node.setInternalStoredData(temp.getInternalStoredData());
            if (previous == node){
                previous.setLeftOrThreadedChild(temp.getLeftOrThreadedChild());
            }
            else {
                previous.setRightOrThreadedChild(temp.getLeftOrThreadedChild());
            }
        }
        temp = null;
    }
    @Override
    public boolean remove(Object o) {
        var sizePrev = this.size();
        try {
            this.deleteNode((E) o);
        }
        catch(ClassCastException e ){
            throw new RuntimeException(e);
        }
        return sizePrev > this.size();
    }
    @Override
    public boolean removeAll(Collection<?> c) {
        var sizePrev = this.size();
        if (c != null){
            c.forEach(item -> {
                if (item.getClass().isInstance(this.getClass())){
                    this.remove((E) item);
                }
                else throw new ClassCastException("Unsupported casting operation");
            });
        }else {
            throw new NullPointerException("Null value passed into removeAll function");
        }
        return size() < sizePrev;
    }
    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Null value passed into retainAll function");
        }

        var sizePrev = this.size();
        List<E> innerList = this.topBottomLeftRightBFS(this.getEInternalRoot(), Optional::of).get();
        boolean modified = false;

        for (E element : innerList) {
            if (!c.contains(element)) {
                this.remove(element);
                modified = true;
            }
        }
        return modified;
    }
    @Override
    public E remove(int index) {
        List<E> innerList = this.topBottomLeftRightBFS(this.getEInternalRoot(), Optional::of).get();
        if (0 <= index && index < innerList.size()){
            var expectedValue = innerList.get(index);
            this.remove(expectedValue);
            return expectedValue;
        }
        else{
            throw new IllegalArgumentException("Error Code 0x001 - [Raised] The index passed into this function was out of bounds");
        }
    }
    //? Main Relational Check Methods
    @Override
    public boolean containsAll(Collection<?> c) {
        boolean result = false;
        if (c != null){
            for(Object element : c){
                result = this.searchInBinaryTrees(this.eInternalRoot, (E) c);
            }
        }
        return result;
    }
    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        BinaryTreeNode<E> temp = this.eInternalRoot;
        //! Let use use a little bit of bfs to apply this unary operator
        if (operator == null){
            throw new NullPointerException("Error Code 0x001 - [Raised] Either of the parameters passed into this function " +
                    "was null");
        }
        List<E> resultList = new ArrayList<>();
        Queue<BinaryTreeNode<E>> nodeQueue = new ArrayDeque<>();
        if (temp != null){
            nodeQueue.offer(temp);
            while (!nodeQueue.isEmpty()){
                temp = nodeQueue.poll();
                var result = operator.apply(temp.getInternalStoredData());
                temp.setInternalStoredData(result);
                if (temp.getLeftOrThreadedChild() != null){
                    nodeQueue.offer(temp.getLeftOrThreadedChild());
                }
                if (temp.getRightOrThreadedChild() != null){
                    nodeQueue.offer(temp.getRightOrThreadedChild());
                }
            }
        }

    }
    @Override
    public void sort(Comparator<? super E> c) {
        if (c == null){
            throw new NullPointerException("Error Code 0x001 - [Raised] Either of the parameters passed into this function " +
                    "was null");
        }
        ArrayList<E> innerList = new ArrayList<>(this.topBottomLeftRightBFS(this.getEInternalRoot(), Optional::of).get());
        clear();
        innerList.sort(c);
        balancingSortedTree(innerList, 0, innerList.size() -1);

    }
    private void balancingSortedTree(ArrayList<E> list , int first, int last){
        if (list != null){
            if (first <= last ){
                int mid = (first + last) /2;
                this.insertionForBinaryTree(list.get(mid));
                balancingSortedTree(list, first, mid -1);
                balancingSortedTree(list, mid+1, last);
            }
        }
    }
    @Override
    public E get(int index) {
        Optional<List<E>> resultBFS = this.topBottomLeftRightBFS(this.eInternalRoot,
                new Function<E, Optional<E>>() {
                    @Override
                    public Optional<E> apply(E e) {
                        return Optional.of(e);
                    }
                });
        return resultBFS.map(es -> es.get(index)).orElse(null);
    }
    @Override
    public E set(int index, E element) {
        return null;
    }
    @Override
    public void add(int index, E element) throws IllegalArgumentException{
        if ( element == null){
            throw new NullPointerException("Error Code 0x001 - [Raised] The value passed into this function to be the element was null");
        }
        else if (0 <= index && index < this.size()){
            this.insertionForBinaryTree(element);
        }
        else {
            throw new IllegalArgumentException("Not only does this structure not use indeces, you are passing a wrong index too?");
        }


    }
    @Override
    public int indexOf(Object o) {

        return this.topBottomLeftRightBFS(this.eInternalRoot, new Function<E, Optional<E>>() {
            @Override
            public Optional<E> apply(E e) {
                return Optional.of(e);
            }
        }).get().indexOf((E) o);
    }
    @Override
    public int lastIndexOf(Object o) {
        return this.indexOf(o);
    }
    @Override
    public ListIterator<E> listIterator() {
        return null;
    }
    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        Optional<List<E>> results = this.topBottomLeftRightBFS(this.eInternalRoot, new Function<E, Optional<E>>() {
            @Override
            public Optional<E> apply(E e) {
                return Optional.of(e);
            }
        });

        ArrayList<E> resultingList = new ArrayList<>();
        if (fromIndex >= 0 && fromIndex < results.get().size()){
            if (toIndex >= 0 && toIndex < results.get().size()){
                for(int i = fromIndex; i < toIndex; i++){
                    resultingList.add(results.get().get(i));
                }
            }
        }
        return resultingList;
    }
    @Override
    public Spliterator<E> spliterator() {
        return null;
    }
    @Override
    public E getFirst() {
        var result =  this.topBottomLeftRightBFS(this.eInternalRoot, new Function<E, Optional<E>>() {
            @Override
            public Optional<E> apply(E e) {
                return Optional.of(e);
            }
        });
        return result.map(List::getFirst).orElse(null);
    }
    @Override
    public E getLast() {
        var result =  this.topBottomLeftRightBFS(this.eInternalRoot, new Function<E, Optional<E>>() {
            @Override
            public Optional<E> apply(E e) {
                return Optional.of(e);
            }
        });
        return result.map(List::getLast).orElse(null);
    }
    @Override
    public E removeFirst() {
        var elementRemoved = this.getEInternalRoot().getInternalStoredData();
        this.remove(this.getEInternalRoot().getInternalStoredData());
        return elementRemoved;
    }
    @Override
    public E removeLast() {
        var valueToRemove = this.getLast();
        if (valueToRemove != null){
            this.remove(valueToRemove);
        }
        else{
            throw new IllegalStateException("Attempted to remove a value from an empty tree");
        }
        return valueToRemove;
    }
    @Override
    public List<E> reversed() {
        var result = this.bottomTopLeftRightBFS(this.eInternalRoot, new Function<E, Optional<E>>() {
            @Override
            public Optional<E> apply(E e) {
                return Optional.of(e);
            }
        });

        if(result.isPresent()){
            return result.get().reversed();
        }
        else{
            return null;
        }
    }



    //? Main internal setters and Getters
    public void setInternalRoot(BinaryTreeNode<E> rootToPlace) throws NullPointerException{
        if (rootToPlace == null){throw new NullPointerException("Error Code 0x001 - [Raised] Node passed into this function " +
                "is null.");}
        else{
            this.eInternalRoot = rootToPlace;
        }
    }
    public BinaryTreeNode<E> getEInternalRoot(){
        return this.eInternalRoot;
    }

}
