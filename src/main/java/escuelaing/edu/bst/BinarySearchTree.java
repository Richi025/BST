package escuelaing.edu.bst;

import java.util.*;


/**
 * A Binary Search Tree (BST) implementation that implements the List interface.
 * It provides basic operations such as insertion, deletion, and searching
 * of elements, and maintains the elements in a sorted order.
 *
 * @param <T> the type of elements in this tree, which must implement Comparable.
 */
public class BinarySearchTree<T extends Comparable<T>> implements List<T> {
    private TreeNode<T> root;

    /**
     * Constructs an empty Binary Search Tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Adds an element to the binary search tree.
     * This method always returns true as per the List interface contract.
     *
     * @param value the element to be added to the tree
     * @return true
     */
    @Override
    public boolean add(T value) {
        insert(value);
        return true;
    }

    /**
     * Inserts an element into the binary search tree.
     *
     * @param value the element to be inserted
     */
    public void insert(T value) {
        root = insertRec(root, value);
    }

    /**
     * Recursively inserts an element into the tree.
     *
     * @param root the root of the current subtree
     * @param value the element to be inserted
     * @return the new root of the subtree
     */
    private TreeNode<T> insertRec(TreeNode<T> root, T value) {
        if (root == null) {
            root = new TreeNode<>(value);
            return root;
        }
        if (value.compareTo(root.value) < 0) {
            root.left = insertRec(root.left, value);
        } else if (value.compareTo(root.value) > 0) {
            root.right = insertRec(root.right, value);
        }
        return root;
    }

    /**
     * Returns the number of elements in the binary search tree.
     *
     * @return the number of elements
     */
    @Override
    public int size() {
        return countNodes();
    }

    public int countNodes() {
        return countNodesRec(root);
    }

    private int countNodesRec(TreeNode<T> node) {
        if (node == null) return 0;
        return 1 + countNodesRec(node.left) + countNodesRec(node.right);
    }

    /**
     * Returns the element at the specified position in the tree,
     * based on an in-order traversal.
     *
     * @param index the index of the element to return
     * @return the element at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public T get(int index) {
        List<T> elements = new ArrayList<>();
        inOrderRec(root, elements);
        if (index >= 0 && index < elements.size()) {
            return elements.get(index);
        }
        throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
    }

    /**
     * Performs an in-order traversal of the tree and adds elements to the list.
     *
     * @param root the root of the current subtree
     * @param elements the list to store the elements
     */
    private void inOrderRec(TreeNode<T> root, List<T> elements) {
        if (root != null) {
            inOrderRec(root.left, elements);
            elements.add(root.value);
            inOrderRec(root.right, elements);
        }
    }

    /**
     * Removes the specified element from the tree, if present.
     *
     * @param o the element to be removed
     * @return true if the element was removed, false otherwise
     */
    @Override
    public boolean remove(Object o) {
        if (o instanceof Comparable<?>) {
            @SuppressWarnings("unchecked")
            T value = (T) o;
            if (search(value) != null) {
                delete(value);
                return true;
            }
        }
        return false;
    }

    /**
     * Searches for an element in the binary search tree.
     *
     * @param value the element to be searched for
     * @return the TreeNode containing the value, or null if not found
     */
    public TreeNode<T> search(T value) {
        return searchRec(root, value);
    }

    /**
     * Recursively searches for an element in the tree.
     *
     * @param root the root of the current subtree
     * @param value the element to search for
     * @return the TreeNode containing the value, or null if not found
     */
    private TreeNode<T> searchRec(TreeNode<T> root, T value) {
        if (root == null || root.value.compareTo(value) == 0) {
            return root;
        }
        if (value.compareTo(root.value) < 0) {
            return searchRec(root.left, value);
        }
        return searchRec(root.right, value);
    }

    /**
     * Deletes the specified element from the binary search tree.
     *
     * @param value the element to be deleted
     */
    public void delete(T value) {
        root = deleteRec(root, value);
    }

    /**
     * Recursively deletes an element from the tree.
     *
     * @param root the root of the current subtree
     * @param value the element to be deleted
     * @return the new root of the subtree
     */
    private TreeNode<T> deleteRec(TreeNode<T> root, T value) {
        if (root == null) return root;

        if (value.compareTo(root.value) < 0) {
            root.left = deleteRec(root.left, value);
        } else if (value.compareTo(root.value) > 0) {
            root.right = deleteRec(root.right, value);
        } else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            root.value = findMin(root.right).value;
            root.right = deleteRec(root.right, root.value);
        }
        return root;
    }

    /**
     * Finds the minimum value in the given subtree.
     *
     * @param root the root of the subtree
     * @return the TreeNode with the minimum value
     */
    public TreeNode<T> findMin(TreeNode<T> root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    /**
     * Removes all elements from the tree.
     */
    @Override
    public void clear() {
        root = null;
    }

    /**
     * Checks if the tree is empty.
     *
     * @return true if the tree is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }


    /**
     * Checks if the tree contains the specified element.
     *
     * @param o the element to be checked
     * @return true if the tree contains the element, false otherwise
     */
    @Override
    public boolean contains(Object o) {
        if (o instanceof Comparable<?>) {
            @SuppressWarnings("unchecked")
            T value = (T) o;
            return search(value) != null;
        }
        return false;
    }

    /**
     * Returns an array containing all elements in the tree, in order.
     *
     * @return an array of elements
     */
    @Override
    public Object[] toArray() {
        List<T> elements = new ArrayList<>();
        inOrderRec(root, elements);
        return elements.toArray();
    }

    @Override
    public Object[] toArray(Object[] a) {
        List<T> elements = new ArrayList<>();
        inOrderRec(root, elements);
        return elements.toArray(a);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        for (T e : c) {
            if (add(e)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object e : c) {
            if (remove(e)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        List<Object> toRetain = new ArrayList<>();
        for (Object e : c) {
            if (contains(e)) {
                toRetain.add(e);
            }
        }
        if (toRetain.size() != size()) {
            clear();
            addAll((Collection<? extends T>) toRetain);
            return true;
        }
        return false;
    }

    @Override
    public T set(int index, T element) {
        throw new UnsupportedOperationException("set no está soportado en un BinarySearchTree.");
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException("add(index, element) no está soportado en un BinarySearchTree.");
    }

    @Override
    public T remove(int index) {
        T element = get(index);
        remove(element);
        return element;
    }

    @Override
    public int indexOf(Object o) {
        List<T> elements = new ArrayList<>();
        inOrderRec(root, elements);
        return elements.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        List<T> elements = new ArrayList<>();
        inOrderRec(root, elements);
        return elements.lastIndexOf(o);
    }

    @Override
    public Iterator<T> iterator() {
        List<T> elements = new ArrayList<>();
        inOrderRec(root, elements);
        return elements.iterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("listIterator no está soportado en un BinarySearchTree.");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("listIterator(index) no está soportado en un BinarySearchTree.");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        List<T> elements = new ArrayList<>();
        inOrderRec(root, elements);
        return new ArrayList<>(elements.subList(fromIndex, toIndex));
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("Unimplemented method 'addAll'");
    }
}
