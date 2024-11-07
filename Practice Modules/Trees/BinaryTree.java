package Trees;


import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.function.UnaryOperator;


public class BinaryTree<E extends Comparable<E>> implements Tree<E>{

    //? Main initializer variable section
    private BinaryTreeNode<E> eInternalRoot;
    private Integer eInternalSize = 0;

    //? Main Constructor definition and implementation
    public BinaryTree(E  toHeadElement) throws IllegalArgumentException{
        if (toHeadElement != null){
            this.setInternalRoot(new BinaryTreeNode<>(toHeadElement));
            this.eInternalSize++;
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

    /**
     * The present method allows the user to traverse the inorder traversal (LPR) of the tree from where this method is called.
     * THe idea behind this method is to allow the user to visualize how an inorder traversal behaves without having access
     * to changing the data.
     * @implNote : The method is implemented with an inherent recursive nature that will create a single linked list of recursive
     * calls in the stack. Please recall that these can get pretty heavy inside stack memory.
     * @throws NullPointerException: If the given value, at any point in the recursion is null;
     */
    @Override
    public void inorderRecursive(BinaryTreeNode<E> Root){
        if (Root != null){
            inorderRecursive(Root.getLeftOrThreadedChild());
            System.out.println("Root Value At the Time= " + Root.getInternalStoredData());
            inorderRecursive(Root.getRightOrThreadedChild());
        }
    }

    public BinaryTreeNode<E> getEInternalRoot(){
        return this.eInternalRoot;
    }

    /**
     * This method allows the user to execute an inorder traversal iteratively. Given that this method takes in a parameter
     * that is an outside function, this function can perform operations on the data stored in each node as part of
     * the analysis phase of the algorithm, however, <b>keep in mind that the information stored should not be deleted,
     * and that said analysis function should in theory return an Optional of type E</b>
     * <br><br>
     * <p>The Optional returned will be queried to determine if the output should be appended to a list of values,
     * this because the user might pass a function to perform an operation on the data and would like the result, if the
     * operation does not return any value (i.e., Optional.isPresent() is false) then the function will return null.</p>
     * @param Root: The node from which to begin the iteration, while the parameter is called root, it does not have to
     *            the root per se, rather any node that
     * @param userDefinedProcessing: Functional parameter where the user can provide an input for a lambda function which
     *                             will be applied to the nodes of this tree.
     * @return: Either a nullish optional or a List of elements updated by a given user function, depending on the way
     *          each parameter is supplied
     * @implNote : Keep in mind that the Functional input of this method requires an optional to be returned, either
     * empty or with a value, but an Optional of type E. If this is not returned, the method will not work appropriately.
     */
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

    /**
     * The present method allows the user to perform a recursive preorder traversal in which the values are analyzed in the
     * PLR analysis method. The method does not allow the user to perform any kind of analysis on the data aside from printing
     * the values.
     * @param Root: The starting node from which to analyze this tree. This node does not need to be the head of the tree.
     */
    @Override
    public void preorderRecursive(BinaryTreeNode<E> Root) {
        if (Root != null) {
            System.out.println("Root Value At the Time= " + Root.getInternalStoredData());
            preorderRecursive(Root.getLeftOrThreadedChild());
            preorderRecursive(Root.getRightOrThreadedChild());
        }
    }

    /**
     * //TODO: finish the documentation of this method
     * @param Root:
     * @param userDefinedProcessing
     * @return
     */
    @Override
    public Optional<List<E>> preorderIterative(BinaryTreeNode<E> Root, Function<E, Optional<E>> userDefinedProcessing) {
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
    public Optional<List<E>> postorderIterative(BinaryTreeNode<E> Root, Function<E, Optional<E>> userDefinedProcessing) {
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
    public Optional<List<E>> topBottomLeftRightBFS(BinaryTreeNode<E> Root, Function<E, Optional<E>> userDefinedProcessing) {
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
    public Optional<List<E>> topBottomRightLeftBFS(BinaryTreeNode<E> Root, Function<E, Optional<E>> userDefinedProcessing) {
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
    public Optional<List<E>> bottomTopLeftRightBFS(BinaryTreeNode<E> Root, Function<E, Optional<E>> userDefinedProcessing) {
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
    public Optional<List<E>> bottomTopRightLeftBFS(BinaryTreeNode<E> Root, Function<E, Optional<E>> userDefinedProcessing) {
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

    public void insertionForBinaryTree(E elementToInsert){
        BinaryTreeNode<E> nodeAtAnalysis = this.eInternalRoot;
        if (elementToInsert == null){
            throw new NullPointerException("Error Code 0x001 - [Raised] The value passed into this method was null");
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

    public final boolean searchInBinaryTrees(BinaryTreeNode<E> Root, final E  element){
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

    //? Main Node Removal Definition
    public void deleteNode(E element) {
        BinaryTreeNode<E> nodeParent = this.getEInternalRoot(), nodeNow = this.getEInternalRoot();

        if (nodeNow == null){
            return; // Tree is empty
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
                //! 1. Check for the single node case
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
                //! 2. Check for Node with a single child node
                else if (nodeNow.getRightOrThreadedChild() == null || nodeNow.getLeftOrThreadedChild() == null){
                    BinaryTreeNode<E> childNode;
                    if (nodeNow.getLeftOrThreadedChild() != null){
                        childNode = nodeNow.getLeftOrThreadedChild();
                    }
                    else {
                        childNode = nodeNow.getRightOrThreadedChild();
                    }

                    if (nodeNow == this.getEInternalRoot()){
                        this.setInternalRoot(childNode);
                    }
                    else if (nodeParent.getLeftOrThreadedChild() == nodeNow){
                        nodeParent.setLeftOrThreadedChild(childNode);
                    }
                    else {
                        nodeParent.setRightOrThreadedChild(childNode);
                    }
                }
                //! 3. Check for Node with two children
                else if (nodeNow.getLeftOrThreadedChild() != null && nodeNow.getRightOrThreadedChild() != null){
                    deleteByMerging(nodeNow);
                }
                return;
            }
        }
    }

    public void deleteByCopying(BinaryTreeNode<E> node){
        BinaryTreeNode<E> previous, temp = node;

        if (node.getRightOrThreadedChild() == null){
            node = node.getLeftOrThreadedChild();
        } else if (node.getLeftOrThreadedChild() == null){
            node = node.getRightOrThreadedChild();
        } else {
            // Find the rightmost node in the left subtree
            previous = node;
            temp = node.getLeftOrThreadedChild();

            while (temp.getRightOrThreadedChild() != null) {
                previous = temp;
                temp = temp.getRightOrThreadedChild();
            }

            // Copy the data
            node.setInternalStoredData(temp.getInternalStoredData());

            // Delete the rightmost node in the left subtree
            if (previous.getRightOrThreadedChild() == temp) {
                previous.setRightOrThreadedChild(temp.getLeftOrThreadedChild());
            } else {
                previous.setLeftOrThreadedChild(temp.getLeftOrThreadedChild());
            }
        }
    }
    /**
     * Returns the number of elements in this list.  If this list contains
     * more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return this.eInternalSize;
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return this.eInternalSize == 0;
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param o element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this list
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public boolean contains(Object o) throws NullPointerException, ClassCastException{
        if (o == null){throw new NullPointerException("Error Code 0x001 - [Raised] Input value on BinaryTree.contains(Object o) was null.");}
        try{
            E element = (E) o;
            return this.searchInBinaryTrees(this.eInternalRoot, element);
        }
        catch (ClassCastException e){
            throw e;
        }
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element).
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must
     * allocate a new array even if this list is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all of the elements in this list in proper
     * sequence
     * @see Arrays#asList(Object[])
     */
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


    /**
     * Returns an array containing all of the elements in this list in
     * proper sequence (from first to last element); the runtime type of
     * the returned array is that of the specified array.  If the list fits
     * in the specified array, it is returned therein.  Otherwise, a new
     * array is allocated with the runtime type of the specified array and
     * the size of this list.
     *
     * <p>If the list fits in the specified array with room to spare (i.e.,
     * the array has more elements than the list), the element in the array
     * immediately following the end of the list is set to {@code null}.
     * (This is useful in determining the length of the list <i>only</i> if
     * the caller knows that the list does not contain any null elements.)
     *
     * <p>Like the {@link #toArray()} method, this method acts as bridge between
     * array-based and collection-based APIs.  Further, this method allows
     * precise control over the runtime type of the output array, and may,
     * under certain circumstances, be used to save allocation costs.
     *
     * <p>Suppose {@code x} is a list known to contain only strings.
     * The following code can be used to dump the list into a newly
     * allocated array of {@code String}:
     *
     * <pre>{@code
     *     String[] y = x.toArray(new String[0]);
     * }</pre>
     * <p>
     * Note that {@code toArray(new Object[0])} is identical in function to
     * {@code toArray()}.
     *
     * @param a the array into which the elements of this list are to
     *          be stored, if it is big enough; otherwise, a new array of the
     *          same runtime type is allocated for this purpose.
     * @return an array containing the elements of this list
     * @throws ArrayStoreException  if the runtime type of the specified array
     *                              is not a supertype of the runtime type of every element in
     *                              this list
     * @throws NullPointerException if the specified array is null
     */
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

    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * <p>Lists that support this operation may place limitations on what
     * elements may be added to this list.  In particular, some
     * lists will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added.  List
     * classes should clearly specify in their documentation any restrictions
     * on what elements may be added.
     *
     * @param e element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws UnsupportedOperationException if the {@code add} operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and this
     *                                       list does not permit null elements
     * @throws IllegalArgumentException      if some property of this element
     *                                       prevents it from being added to this list
     */
    @Override
    public boolean add(E e) {
        var sizePrev = this.size();
        this.insertionForBinaryTree(e);
        return sizePrev < this.size();
    }

    //TODO: finish up all methods for removes
    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present (optional operation).  If this list does not contain
     * the element, it is unchanged.  More formally, removes the element with
     * the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))}
     * (if such an element exists).  Returns {@code true} if this list
     * contained the specified element (or equivalently, if this list changed
     * as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     * @throws ClassCastException            if the type of the specified element
     *                                       is incompatible with this list
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if the specified element is null and this
     *                                       list does not permit null elements
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws UnsupportedOperationException if the {@code remove} operation
     *                                       is not supported by this list
     */
    @Override
    public boolean remove(Object o) throws NullPointerException, ClassCastException{
        if (o == null){throw new NullPointerException("Error Code 0x001 - [Raised] The given input was null.");}
        var prevSize = this.size();
        E element = (E) o;
        this.deleteNode(element);

        return prevSize > this.size();
    }

    /**
     * Returns {@code true} if this list contains all of the elements of the
     * specified collection.
     *
     * @param c collection to be checked for containment in this list
     * @return {@code true} if this list contains all of the elements of the
     * specified collection
     * @throws ClassCastException   if the types of one or more elements
     *                              in the specified collection are incompatible with this
     *                              list
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified collection contains one
     *                              or more null elements and this list does not permit null
     *                              elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>),
     *                              or if the specified collection is null
     * @see #contains(Object)
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        boolean result = false;
        if (c != null){
            for(Object element : c){
                result = this.searchInBinaryTrees(this.eInternalRoot, (E) element);
            }
        }
        else {
            throw new NullPointerException("Error Code 0x001 - [Raised] Element of Type <?> cannot be cast to this element");
        }
        return result;
    }

    /**
     * Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the specified
     * collection's iterator (optional operation).  The behavior of this
     * operation is undefined if the specified collection is modified while
     * the operation is in progress.  (Note that this will occur if the
     * specified collection is this list, and it's nonempty.)
     *
     * @param c collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws UnsupportedOperationException if the {@code addAll} operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of an element of the specified
     *                                       collection prevents it from being added to this list
     * @throws NullPointerException          if the specified collection contains one
     *                                       or more null elements and this list does not permit null
     *                                       elements, or if the specified collection is null
     * @throws IllegalArgumentException      if some property of an element of the
     *                                       specified collection prevents it from being added to this list
     * @see #add(Object)
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        var sizePrev = this.size();
        if (c != null){
            if (!c.isEmpty()) {
                if (this.getEInternalRoot() == null) {
                    List<E> list = (List<E>) c.stream().toList();
                    this.setInternalRoot(new BinaryTreeNode<>(list.get(0)));
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

    /**
     * Inserts all of the elements in the specified collection into this
     * list at the specified position (optional operation).  Shifts the
     * element currently at that position (if any) and any subsequent
     * elements to the right (increases their indices).  The new elements
     * will appear in this list in the order that they are returned by the
     * specified collection's iterator.  The behavior of this operation is
     * undefined if the specified collection is modified while the
     * operation is in progress.  (Note that this will occur if the specified
     * collection is this list, and it's nonempty.)
     *
     * @param index index at which to insert the first element from the
     *              specified collection
     * @param c     collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws UnsupportedOperationException if the {@code addAll} operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of an element of the specified
     *                                       collection prevents it from being added to this list
     * @throws NullPointerException          if the specified collection contains one
     *                                       or more null elements and this list does not permit null
     *                                       elements, or if the specified collection is null
     * @throws IllegalArgumentException      if some property of an element of the
     *                                       specified collection prevents it from being added to this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index > size()})
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        //! Given that  do not have a way to modify the tree iteartively without breaking down the tree, then we have to agree that in general
        //! we need to append the items just as if we were doing a normal addAll
        var sizePrev = this.size();
        this.addAll(c);
        return sizePrev < this.size();
    }

    /**
     * Removes from this list all of its elements that are contained in the
     * specified collection (optional operation).
     *
     * @param c collection containing elements to be removed from this list
     * @return {@code true} if this list changed as a result of the call
     * @throws UnsupportedOperationException if the {@code removeAll} operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of an element of this list
     *                                       is incompatible with the specified collection
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if this list contains a null element and the
     *                                       specified collection does not permit null elements
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>),
     *                                       or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    /**
     * Retains only the elements in this list that are contained in the
     * specified collection (optional operation).  In other words, removes
     * from this list all of its elements that are not contained in the
     * specified collection.
     *
     * @param c collection containing elements to be retained in this list
     * @return {@code true} if this list changed as a result of the call
     * @throws UnsupportedOperationException if the {@code retainAll} operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of an element of this list
     *                                       is incompatible with the specified collection
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if this list contains a null element and the
     *                                       specified collection does not permit null elements
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>),
     *                                       or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    @Override
    public boolean retainAll(Collection<?> c) throws NullPointerException{
        boolean removed = false;
        if (c == null){throw new NullPointerException("Error Code 0x001 - [Raised] Input value c was null.");}
        if (c.isEmpty()){throw new IllegalArgumentException("Error Code 0x001 - [Raised] Input value c was empty.");}
        else{
            var sizePrev = this.size();
            List<E> innerList = this.topBottomLeftRightBFS(this.getEInternalRoot(), Optional::of).get();

            for (E element : innerList) {
                if (!c.contains(element)) {
                    this.remove(element);
                    removed = true;
                }
            }
            return removed;
        }
    }

    /**
     * Replaces each element of this list with the result of applying the
     * operator to that element.  Errors or runtime exceptions thrown by
     * the operator are relayed to the caller.
     *
     * @param operator the operator to apply to each element
     * @throws UnsupportedOperationException if this list is unmodifiable.
     *                                       Implementations may throw this exception if an element
     *                                       cannot be replaced or if, in general, modification is not
     *                                       supported
     * @throws NullPointerException          if the specified operator is null or
     *                                       if the operator result is a null value and this list does
     *                                       not permit null elements
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @implSpec The default implementation is equivalent to, for this {@code list}:
     * <pre>{@code
     *     final ListIterator<E> li = list.listIterator();
     *     while (li.hasNext()) {
     *         li.set(operator.apply(li.next()));
     *     }
     * }</pre>
     * <p>
     * If the list's list-iterator does not support the {@code set} operation
     * then an {@code UnsupportedOperationException} will be thrown when
     * replacing the first element.
     * @since 1.8
     */
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

    /**
     * Sorts this list according to the order induced by the specified
     * {@link Comparator}.  The sort is <i>stable</i>: this method must not
     * reorder equal elements.
     *
     * <p>All elements in this list must be <i>mutually comparable</i> using the
     * specified comparator (that is, {@code c.compare(e1, e2)} must not throw
     * a {@code ClassCastException} for any elements {@code e1} and {@code e2}
     * in the list).
     *
     * <p>If the specified comparator is {@code null} then all elements in this
     * list must implement the {@link Comparable} interface and the elements'
     * {@linkplain Comparable natural ordering} should be used.
     *
     * <p>This list must be modifiable, but need not be resizable.
     *
     * @param c the {@code Comparator} used to compare list elements.
     *          A {@code null} value indicates that the elements'
     *          {@linkplain Comparable natural ordering} should be used
     * @throws ClassCastException            if the list contains elements that are not
     *                                       <i>mutually comparable</i> using the specified comparator
     * @throws UnsupportedOperationException if the list's list-iterator does
     *                                       not support the {@code set} operation
     * @throws IllegalArgumentException      (<a href="Collection.html#optional-restrictions">optional</a>)
     *                                       if the comparator is found to violate the {@link Comparator}
     *                                       contract
     * @implSpec The default implementation obtains an array containing all elements in
     * this list, sorts the array, and iterates over this list resetting each
     * element from the corresponding position in the array. (This avoids the
     * n<sup>2</sup> log(n) performance that would result from attempting
     * to sort a linked list in place.)
     * @implNote This implementation is a stable, adaptive, iterative mergesort that
     * requires far fewer than n lg(n) comparisons when the input array is
     * partially sorted, while offering the performance of a traditional
     * mergesort when the input array is randomly ordered.  If the input array
     * is nearly sorted, the implementation requires approximately n
     * comparisons.  Temporary storage requirements vary from a small constant
     * for nearly sorted input arrays to n/2 object references for randomly
     * ordered input arrays.
     *
     * <p>The implementation takes equal advantage of ascending and
     * descending order in its input array, and can take advantage of
     * ascending and descending order in different parts of the same
     * input array.  It is well-suited to merging two or more sorted arrays:
     * simply concatenate the arrays and sort the resulting array.
     *
     * <p>The implementation was adapted from Tim Peters's list sort for Python
     * (<a href="http://svn.python.org/projects/python/trunk/Objects/listsort.txt">
     * TimSort</a>).  It uses techniques from Peter McIlroy's "Optimistic
     * Sorting and Information Theoretic Complexity", in Proceedings of the
     * Fourth Annual ACM-SIAM Symposium on Discrete Algorithms, pp 467-474,
     * January 1993.
     * @since 1.8
     */
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

    public void deleteByMerging(BinaryTreeNode<E> node) {
        BinaryTreeNode<E> leftChild = node.getLeftOrThreadedChild();
        BinaryTreeNode<E> rightChild = node.getRightOrThreadedChild();

        if (leftChild == null) {
            // Should not happen as the method is supposed to be called only for nodes with two children
            return;
        }

        // Find the rightmost node of the left subtree
        BinaryTreeNode<E> rightmost = leftChild;
        BinaryTreeNode<E> parentOfRightmost = node;
        while (rightmost.getRightOrThreadedChild() != null) {
            parentOfRightmost = rightmost;
            rightmost = rightmost.getRightOrThreadedChild();
        }

        // Attach the right subtree to the rightmost node of the left subtree
        if (parentOfRightmost != node) {
            parentOfRightmost.setRightOrThreadedChild(rightmost.getLeftOrThreadedChild());
            rightmost.setLeftOrThreadedChild(leftChild);
        }

        rightmost.setRightOrThreadedChild(rightChild);

        // Replace node with the rightmost node
        if (node == this.getEInternalRoot()) {
            this.setInternalRoot(rightmost);
        } else {
            updateParentReference(node, rightmost);
        }
    }

    private void updateParentReference(BinaryTreeNode<E> node, BinaryTreeNode<E> newChild) {
        BinaryTreeNode<E> parent = this.getEInternalRoot();
        while (parent != null) {
            if (parent.getLeftOrThreadedChild() == node) {
                parent.setLeftOrThreadedChild(newChild);
                break;
            } else if (parent.getRightOrThreadedChild() == node) {
                parent.setRightOrThreadedChild(newChild);
                break;
            }

            int compareResult = node.getInternalStoredData().compareTo(parent.getInternalStoredData());
            parent = compareResult < 0 ? parent.getLeftOrThreadedChild() : parent.getRightOrThreadedChild();
        }
    }


    /**
     * Removes all of the elements from this list (optional operation).
     * The list will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the {@code clear} operation
     *                                       is not supported by this list
     */
    @Override
    public void clear() {
        this.eInternalRoot = null;
        this.eInternalSize = 0;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
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

    /**
     * Replaces the element at the specified position in this list with the
     * specified element (optional operation).
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the {@code set} operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and
     *                                       this list does not permit null elements
     * @throws IllegalArgumentException      if some property of the specified
     *                                       element prevents it from being added to this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index >= size()})
     */
    @Override
    public E set(int index, E element) {
        //! Get the items
        Optional<List<E>> resultBFS = this.topBottomLeftRightBFS(this.eInternalRoot,
                new Function<E, Optional<E>>() {
                    @Override
                    public Optional<E> apply(E e) {
                        return Optional.of(e);
                    }
                });
        BinaryTreeNode<E> rootTemp = this.eInternalRoot;
        E elementToAlter = resultBFS.map(es -> es.get(index)).orElse(null);
        while (rootTemp != null){
            if (elementToAlter.compareTo(rootTemp.getInternalStoredData()) < 0){
                rootTemp = rootTemp.getLeftOrThreadedChild();
            }
            else if (elementToAlter.compareTo(rootTemp.getInternalStoredData()) > 0){
                rootTemp = rootTemp.getRightOrThreadedChild();
            }
            else {
                break;
            }
        }
        //! Find the node where the value of the index is the same
        rootTemp.setInternalStoredData(element);
        return elementToAlter;
    }

    /**
     * Inserts the specified element at the specified position in this list
     * (optional operation).  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws UnsupportedOperationException if the {@code add} operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and
     *                                       this list does not permit null elements
     * @throws IllegalArgumentException      if some property of the specified
     *                                       element prevents it from being added to this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index > size()})
     */
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

    /**
     * Removes the element at the specified position in this list (optional
     * operation).  Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the {@code remove} operation
     *                                       is not supported by this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index >= size()})
     */
    @Override
    public E remove(int index) {
        return null;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this list
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public int indexOf(Object o) {

        return this.topBottomLeftRightBFS(this.eInternalRoot, new Function<E, Optional<E>>() {
            @Override
            public Optional<E> apply(E e) {
                return Optional.of(e);
            }
        }).get().indexOf((E) o);
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this list
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public int lastIndexOf(Object o) {
        return this.indexOf(o);
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence).
     *
     * @return a list iterator over the elements in this list (in proper
     * sequence)
     */
    @Override
    public ListIterator<E> listIterator() {
        return new BiDirectionalBFSIterator();
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list.
     * The specified index indicates the first element that would be
     * returned by an initial call to {@link ListIterator#next next}.
     * An initial call to {@link ListIterator#previous previous} would
     * return the element with the specified index minus one.
     *
     * @param index index of the first element to be returned from the
     *              list iterator (by a call to {@link ListIterator#next next})
     * @return a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index > size()})
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        return new BiDirectionalBFSIterator();
    }

    /**
     * Returns a view of the portion of this list between the specified
     * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive.  (If
     * {@code fromIndex} and {@code toIndex} are equal, the returned list is
     * empty.)  The returned list is backed by this list, so non-structural
     * changes in the returned list are reflected in this list, and vice-versa.
     * The returned list supports all of the optional list operations supported
     * by this list.<p>
     * <p>
     * This method eliminates the need for explicit range operations (of
     * the sort that commonly exist for arrays).  Any operation that expects
     * a list can be used as a range operation by passing a subList view
     * instead of a whole list.  For example, the following idiom
     * removes a range of elements from a list:
     * <pre>{@code
     *      list.subList(from, to).clear();
     * }</pre>
     * Similar idioms may be constructed for {@code indexOf} and
     * {@code lastIndexOf}, and all of the algorithms in the
     * {@code Collections} class can be applied to a subList.<p>
     * <p>
     * The semantics of the list returned by this method become undefined if
     * the backing list (i.e., this list) is <i>structurally modified</i> in
     * any way other than via the returned list.  (Structural modifications are
     * those that change the size of this list, or otherwise perturb it in such
     * a fashion that iterations in progress may yield incorrect results.)
     *
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex   high endpoint (exclusive) of the subList
     * @return a view of the specified range within this list
     * @throws IndexOutOfBoundsException for an illegal endpoint index value
     *                                   ({@code fromIndex < 0 || toIndex > size ||
     *                                   fromIndex > toIndex})
     */
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

    /**
     * Creates a {@link Spliterator} over the elements in this list.
     *
     * <p>The {@code Spliterator} reports {@link Spliterator#SIZED} and
     * {@link Spliterator#ORDERED}.  Implementations should document the
     * reporting of additional characteristic values.
     *
     * @return a {@code Spliterator} over the elements in this list
     * @implSpec The default implementation creates a
     * <em><a href="Spliterator.html#binding">late-binding</a></em>
     * spliterator as follows:
     * <ul>
     * <li>If the list is an instance of {@link RandomAccess} then the default
     *     implementation creates a spliterator that traverses elements by
     *     invoking the method {@link List#get}.  If such invocation results or
     *     would result in an {@code IndexOutOfBoundsException} then the
     *     spliterator will <em>fail-fast</em> and throw a
     *     {@code ConcurrentModificationException}.
     *     If the list is also an instance of {@link AbstractList} then the
     *     spliterator will use the list's {@link AbstractList modCount}
     *     field to provide additional <em>fail-fast</em> behavior.
     * <li>Otherwise, the default implementation creates a spliterator from the
     *     list's {@code Iterator}.  The spliterator inherits the
     *     <em>fail-fast</em> of the list's iterator.
     * </ul>
     * @implNote The created {@code Spliterator} additionally reports
     * {@link Spliterator#SUBSIZED}.
     * @since 1.8
     */
    @Override
    public Spliterator<E> spliterator() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param e
     * @throws NullPointerException          {@inheritDoc}
     * @throws UnsupportedOperationException {@inheritDoc}
     * @implSpec The implementation in this interface calls {@code add(0, e)}.
     * @since 21
     */
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

    /**
     * {@inheritDoc}
     *
     * @param e
     * @throws NullPointerException          {@inheritDoc}
     * @throws UnsupportedOperationException {@inheritDoc}
     * @implSpec The implementation in this interface calls {@code add(e)}.
     * @since 21
     */
    @Override
    public void addLast(E e) throws NullPointerException{
        this.insertionForBinaryTree(e);
    }

    /**
     * {@inheritDoc}
     *
     * @throws NoSuchElementException {@inheritDoc}
     * @implSpec If this List is not empty, the implementation in this interface returns the result
     * of calling {@code get(0)}. Otherwise, it throws {@code NoSuchElementException}.
     * @since 21
     */
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

    /**
     * {@inheritDoc}
     *
     * @throws NoSuchElementException {@inheritDoc}
     * @implSpec If this List is not empty, the implementation in this interface returns the result
     * of calling {@code get(size() - 1)}. Otherwise, it throws {@code NoSuchElementException}.
     * @since 21
     */
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

    /**
     * {@inheritDoc}
     *
     * @throws NoSuchElementException        {@inheritDoc}
     * @throws UnsupportedOperationException {@inheritDoc}
     * @implSpec If this List is not empty, the implementation in this interface returns the result
     * of calling {@code remove(0)}. Otherwise, it throws {@code NoSuchElementException}.
     * @since 21
     */
    @Override
    public E removeFirst() {
        var prev = this.getFirst();
        this.deleteNode(prev);
        return prev;
    }

    /**
     * {@inheritDoc}
     *
     * @throws NoSuchElementException        {@inheritDoc}
     * @throws UnsupportedOperationException {@inheritDoc}
     * @implSpec If this List is not empty, the implementation in this interface returns the result
     * of calling {@code remove(size() - 1)}. Otherwise, it throws {@code NoSuchElementException}.
     * @since 21
     */
    @Override
    public E removeLast() {
        var prev = this.getLast();
        this.deleteNode(prev);
        return prev;
    }

    /**
     * {@inheritDoc}
     *
     * @return a reverse-ordered view of this collection, as a {@code List}
     * @implSpec The implementation in this interface returns a reverse-ordered List
     * view. The {@code reversed()} method of the view returns a reference
     * to this List. Other operations on the view are implemented via calls to
     * public methods on this List. The exact relationship between calls on the
     * view and calls on this List is unspecified. However, order-sensitive
     * operations generally delegate to the appropriate method with the opposite
     * orientation. For example, calling {@code getFirst} on the view results in
     * a call to {@code getLast} on this List.
     * @since 21
     */
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

    public int getHeight(){
        Optional<List<E>> result = this.topBottomLeftRightBFS(this.eInternalRoot, new Function<E, Optional<E>>() {
            @Override
            public Optional<E> apply(E e) {
                return Optional.of(e);
            }
        });
        return -1;
    }

    //? Main internal setters and Getters
    public void setInternalRoot(BinaryTreeNode<E> rootToPlace) throws NullPointerException{
        if (rootToPlace == null){throw new NullPointerException("Error Code 0x001 - [Raised] Node passed into this function " +
                "is null.");}
        else{
            this.eInternalRoot = rootToPlace;
        }
    }
    class BiDirectionalBFSIterator implements ListIterator<E>{

        int pointerToCurrent = 0,pointerToPrevious = -1;
        List<E> elements = topBottomLeftRightBFS(getEInternalRoot(), Optional::of).get();
        Boolean calledNext = false, calledAdd = false, calledPrevious = false;

        /**
         * Returns {@code true} if this list iterator has more elements when
         * traversing the list in the forward direction. (In other words,
         * returns {@code true} if {@link #next} would return an element rather
         * than throwing an exception.)
         *
         * @return {@code true} if the list iterator has more elements when
         * traversing the list in the forward direction
         */
        @Override
        public boolean hasNext() {
            return pointerToCurrent < elements.size();
        }

        /**
         * Returns the next element in the list and advances the cursor position.
         * This method may be called repeatedly to iterate through the list,
         * or intermixed with calls to {@link #previous} to go back and forth.
         * (Note that alternating calls to {@code next} and {@code previous}
         * will return the same element repeatedly.)
         *
         * @return the next element in the list
         * @throws NoSuchElementException if the iteration has no next element
         */
        @Override
        public E next() {
            pointerToPrevious++;
            calledNext = true;
            return elements.get(pointerToCurrent++);
        }

        /**
         * Returns {@code true} if this list iterator has more elements when
         * traversing the list in the reverse direction.  (In other words,
         * returns {@code true} if {@link #previous} would return an element
         * rather than throwing an exception.)
         *
         * @return {@code true} if the list iterator has more elements when
         * traversing the list in the reverse direction
         */
        @Override
        public boolean hasPrevious() {
            return pointerToPrevious >= 0;
        }

        /**
         * Returns the previous element in the list and moves the cursor
         * position backwards.  This method may be called repeatedly to
         * iterate through the list backwards, or intermixed with calls to
         * {@link #next} to go back and forth.  (Note that alternating calls
         * to {@code next} and {@code previous} will return the same
         * element repeatedly.)
         *
         * @return the previous element in the list
         * @throws NoSuchElementException if the iteration has no previous
         *                                element
         */
        @Override
        public E previous() {
            if (pointerToPrevious == -1){
                throw new NoSuchElementException("No next() call has been made, i.e., previous returns wrong value.");
            }

            this.calledPrevious = true;
            return this.elements.get(pointerToPrevious--);
        }

        /**
         * Returns the index of the element that would be returned by a
         * subsequent call to {@link #next}. (Returns list size if the list
         * iterator is at the end of the list.)
         *
         * @return the index of the element that would be returned by a
         * subsequent call to {@code next}, or list size if the list
         * iterator is at the end of the list
         */
        @Override
        public int nextIndex() {
            if (this.pointerToCurrent + 1 > this.elements.size()){
                return this.elements.size();
            }

            return this.elements.indexOf(this.elements.get(this.pointerToCurrent + 1));
        }

        /**
         * Returns the index of the element that would be returned by a
         * subsequent call to {@link #previous}. (Returns -1 if the list
         * iterator is at the beginning of the list.)
         *
         * @return the index of the element that would be returned by a
         * subsequent call to {@code previous}, or -1 if the list
         * iterator is at the beginning of the list
         */
        @Override
        public int previousIndex() {
            if (this.pointerToPrevious - 1 < 0){
                return -1;
            }

            return this.elements.indexOf(this.elements.get(this.pointerToPrevious -1));
        }

        /**
         * Removes from the list the last element that was returned by {@link
         * #next} or {@link #previous} (optional operation).  This call can
         * only be made once per call to {@code next} or {@code previous}.
         * It can be made only if {@link #add} has not been
         * called after the last call to {@code next} or {@code previous}.
         *
         * @throws UnsupportedOperationException if the {@code remove}
         *                                       operation is not supported by this list iterator
         * @throws IllegalStateException         if neither {@code next} nor
         *                                       {@code previous} have been called, or {@code remove} or
         *                                       {@code add} have been called after the last call to
         *                                       {@code next} or {@code previous}
         */
        @Override
        public void remove() {
            if (!calledNext || !calledPrevious){
                throw new IllegalStateException("No previous call to next or previous() have been done before this operation");
            }
            else{
                {
                    if (calledNext){
                        var prev = this.elements.remove(this.pointerToCurrent -1);
                        deleteNode(prev);
                        calledNext = false; calledAdd = false; calledPrevious = false;
                        this.elements = topBottomLeftRightBFS(getEInternalRoot(), Optional::of).get();
                    }
                    else if (calledPrevious){
                        var prev = this.elements.remove(this.pointerToPrevious);
                        deleteNode(prev);
                        calledNext = false; calledAdd = false; calledPrevious = false;
                        this.elements = topBottomLeftRightBFS(getEInternalRoot(), Optional::of).get();
                    }
                }
            }
        }

        /**
         * Replaces the last element returned by {@link #next} or
         * {@link #previous} with the specified element (optional operation).
         * This call can be made only if neither {@link #remove} nor {@link
         * #add} have been called after the last call to {@code next} or
         * {@code previous}.
         *
         * @param e the element with which to replace the last element returned by
         *          {@code next} or {@code previous}
         * @throws UnsupportedOperationException if the {@code set} operation
         *                                       is not supported by this list iterator
         * @throws ClassCastException            if the class of the specified element
         *                                       prevents it from being added to this list
         * @throws IllegalArgumentException      if some aspect of the specified
         *                                       element prevents it from being added to this list
         * @throws IllegalStateException         if neither {@code next} nor
         *                                       {@code previous} have been called, or {@code remove} or
         *                                       {@code add} have been called after the last call to
         *                                       {@code next} or {@code previous}
         */
        @Override
        public void set(E e) {
            throw new UnsupportedOperationException("Setting an element on is not allowed in Binary Search Trees without rebalancing");
        }

        /**
         * Inserts the specified element into the list (optional operation).
         * The element is inserted immediately before the element that
         * would be returned by {@link #next}, if any, and after the element
         * that would be returned by {@link #previous}, if any.  (If the
         * list contains no elements, the new element becomes the sole element
         * on the list.)  The new element is inserted before the implicit
         * cursor: a subsequent call to {@code next} would be unaffected, and a
         * subsequent call to {@code previous} would return the new element.
         * (This call increases by one the value that would be returned by a
         * call to {@code nextIndex} or {@code previousIndex}.)
         *
         * @param e the element to insert
         * @throws UnsupportedOperationException if the {@code add} method is
         *                                       not supported by this list iterator
         * @throws ClassCastException            if the class of the specified element
         *                                       prevents it from being added to this list
         * @throws IllegalArgumentException      if some aspect of this element
         *                                       prevents it from being added to this list
         */
        @Override
        public void add(E e) {
            throw new UnsupportedOperationException("Adding elements with indeces are not allowed in Binary Search Trees.");
        }
    }


    //**-** Below this point we should include our declarations for book methods

    //? Recursive BFS Method
    public void recursiveBFS(BinaryTreeNode<E> root){
        if (root != null){
            recursiveBFSHelper(new ArrayDeque<>(){{add(root);}});
        }else {
            throw new NullPointerException("Value of Root Cannot be null for a recursive BFS implementation");
        }
    }
    private void recursiveBFSHelper(Queue<BinaryTreeNode<E>> levelList){
        if (levelList.isEmpty()){
            return; //! This is our recursive step, given that BFS essentially takes a look at each level iteratively, then if
                    //! at some point we receive an empty level we should assume that it means the level has ended
        }
        Queue<BinaryTreeNode<E>> nextLevelList = new ArrayDeque<>();

        while (!levelList.isEmpty()){
            BinaryTreeNode<E> current = levelList.poll();
            System.out.println(current.getInternalStoredData());

            if (current.getLeftOrThreadedChild() != null){
                nextLevelList.offer(current.getLeftOrThreadedChild());
            }
            if (current.getRightOrThreadedChild() != null){
                nextLevelList.offer(current.getRightOrThreadedChild());
            }
        }

        recursiveBFSHelper(nextLevelList);
    }


    //? Leave Nodes Count
    public int getNumberOfLeaves(){
        /*
         * This method is an alteration of the common BFS TopBottomLeftRight Traversal method implemented in this class.
         * The main changes occur in the way the nodes get process, rather than being appended into a list, or printed to
         * the screen, the condition checks if any node, non matter its level, presents the two-null children characteristic.
         *
         * If any node presents that characteristic, we do not care for the node, just for a count, so we do leafCount++. Else
         * we do nothing, and we append each node to the sides of the node at analysis.
         *
         * This implementation uses an iterative approach in which a Queue<BinaryTreeNode<E>> is used to store the nodes
         * that need to be analyzed. First we add the root of the given tree, and then we keep iterating, level by level
         * to find the nodes whose children are null.
         */

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

    //? Non-Leave node count
    public int getNumberOfNonLeaves(){
        /*
         * This method is a clear variation of the original Leave Node Counter in the sense that it only changes the conditions
         * to increase the counter in the case of non-null child nodes. Interestingly, the operator used in this method is
         * not the &&, rather it is an || operator, since a node can count as a non-leave even if it only has a single child
         *
         */
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
}


