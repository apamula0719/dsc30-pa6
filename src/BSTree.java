/*
 * Name: TODO
 * PID:  TODO
 */

import java.util.*;

/**
 * Binary search tree implementation.
 * 
 * @author Aneesh Pamula
 * @since  5/10/2023
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            this.left = left;
            this.right = right;
            this.dataList = dataList;
            this.key = key;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            this.left = left;
            this.right = right;
            this.key = key;
            this.dataList = new LinkedList<>();
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            return key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            return left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            return right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {
            return dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setLeft(BSTNode newleft) {
            left = newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setRight(BSTNode newright) {
            right = newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            dataList = newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {
            return dataList.remove(data);
        }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        root = null;
        nelems = 0;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        return root;
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        return nelems;
    }

    /**
     * Insert a key into BST
     * 
     * @param key
     * @return true if insertion is successful and false otherwise
     */
    public boolean insert(T key) {
        if(key == null)
            throw new NullPointerException();
        if(root == null) {
            root = new BSTNode(null, null, key);
            nelems++;
            return true;
        }
        return insertHelper(root, key);
    }
    private boolean insertHelper(BSTNode node, T key){
        int compare = node.getKey().compareTo(key);
        if(compare == 0)
            return false;
        else if(compare > 0){
            if(node.left == null){
                node.left = new BSTNode(null, null, key);
                nelems++;
                return true;
            }
            return insertHelper(node.left, key);
        }
        else{
            if(node.right == null){
                node.right = new BSTNode(null, null, key);
                nelems++;
                return true;
            }
            return insertHelper(node.right, key);
        }
    }


    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) {
        if(key == null)
            throw new NullPointerException();
        if(root == null)
            return false;
        return findHelper(root, key);
    }

    private boolean findHelper(BSTNode node,T key){
        int compare = node.getKey().compareTo(key);
        if(compare == 0)
            return true;
        else if(compare > 0){
            if(node.left == null){
                return false;
            }
            return findHelper(node.left, key);
        }
        else{
            if(node.right == null){
                return false;
            }
            return findHelper(node.right, key);
        }
    }

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If eaither key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        if(key == null || data == null)
            throw new NullPointerException();
        if(root == null)
            throw new IllegalArgumentException();
        insertDataHelper(root, key, data);
    }

    private void insertDataHelper(BSTNode node, T key, T data){
        int compare = node.getKey().compareTo(key);
        if(compare == 0)
            node.addNewInfo(data);
        else if(compare > 0){
            if(node.left == null){
                throw new IllegalArgumentException();
            }
            insertDataHelper(node.left, key, data);
        }
        else{
            if(node.right == null){
                throw new IllegalArgumentException();
            }
            insertDataHelper(node.right, key, data);
        }
    }
    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        if(key == null)
            throw new NullPointerException();
        if(root == null)
            throw new IllegalArgumentException();
        return findDataListHelper(root, key);
    }

    private LinkedList<T> findDataListHelper(BSTNode node, T key){
        int compare = node.getKey().compareTo(key);
        if(compare == 0)
            return node.getDataList();
        else if(compare > 0){
            if(node.left == null){
                throw new IllegalArgumentException();
            }
            return findDataListHelper(node.left, key);
        }
        else{
            if(node.right == null){
                throw new IllegalArgumentException();
            }
            return findDataListHelper(node.right, key);
        }
    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        return findHeightHelper(root) - 1;
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        if(root == null)
            return 0;
        int leftSide = findHeightHelper(root.left);//Height of the subtree on the left
        int rightSide = findHeightHelper(root.right);//Height of the subtree on the right
        if(leftSide > rightSide)
            return 1 + leftSide;
        else if (leftSide < rightSide)
            return 1+ rightSide;
        else
            return 1 + leftSide;//both are same, doesn't matter
    }

    /* * * * * BST Iterator * * * * */

    public class BSTree_Iterator implements Iterator<T> {
        Stack<BSTNode> iterStack = new Stack<>();
        public BSTree_Iterator() {
            addToStack(root);
        }

        private void addToStack(BSTNode node){
            if(node.getLeft() != null){
                iterStack.push(node);
                addToStack(node.getLeft());
            }
            else{
                iterStack.push(node);
            }
        }
        public boolean hasNext() {
            return !iterStack.isEmpty();
        }

        public T next() {
            if(iterStack.isEmpty())
                throw new NoSuchElementException();
            BSTNode rightTree = iterStack.peek().getRight();
            BSTNode thisNode = iterStack.pop();
            if(rightTree != null)
                addToStack(rightTree);
            return thisNode.getKey();
        }
    }

    public Iterator<T> iterator() {
        return new BSTree_Iterator();
    }

    /* * * * * Extra Credit Methods * * * * */

    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        /* TODO */
        return null;
    }

    public T levelMax(int level) {
        /* TODO */
        return null;
    }
}
