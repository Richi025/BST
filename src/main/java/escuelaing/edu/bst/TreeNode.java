package escuelaing.edu.bst;

/**
 * Represents a node in a binary search tree.
 * Each node contains a value and references to its left and right children.
 *
 * @param <T> the type of the value stored in the node, which must implement Comparable
 */
public class TreeNode<T extends Comparable<T>> {
    T value;
    TreeNode<T> left;
    TreeNode<T> right;

    /**
     * Constructs a TreeNode with the given value.
     * Initially, the left and right children are set to null.
     *
     * @param value the value to be stored in this node
     */
    TreeNode(T value) {
        this.value = value;
        left = null;
        right = null;
    }
}
