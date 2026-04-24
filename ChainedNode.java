/**
 * Contract for a generic chained node structure supporting
 * add, remove, and search operations.
 *
 * @param <T> the element type; must implement Comparable
 */
public interface ChainedNode<T extends Comparable<T>> {

    /**
     * Inserts an element into the chain.
     *
     * @param element the element to add
     * @return true if successfully added, false otherwise
     */
    public boolean add(T element);

    /**
     * Removes and returns the first element in the chain.
     *
     * @return the first element, or null if the chain is empty
     */
    public T remove();

    /**
     * Removes and returns the first occurrence of the specified element.
     *
     * @param element the element to remove
     * @return the removed element, or null if not found
     */
    public T remove(T element);

    /**
     * Recursively searches for the specified element starting from the given node.
     *
     * @param element the element to search for
     * @param node    the starting node for this recursive call
     * @return true if found, false otherwise
     */
    public boolean search(T element, Node<T> node);

    /**
     * Returns the first element without removing it.
     *
     * @return the first element, or null if the chain is empty
     */
    public T getFirst();

    /**
     * Returns the last element without removing it.
     *
     * @return the last element, or null if the chain is empty
     */
    public T getLast();

    /**
     * Returns the number of nodes in the chain.
     *
     * @return the node count as a non-negative integer
     */
    public int getNumberOfNodes();

    /**
     * Returns true if the chain contains no nodes.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty();
}