/**
 * A single node in a linked chain, holding data and a reference to the next node.
 *
 * @param <T> the data type stored in this node; must implement Comparable
 */
public class Node<T extends Comparable<T>> {

    private T data;
    private Node<T> nextNode;

    /**
     * Constructs a node with the given data and next node reference.
     *
     * @param data     the data to store
     * @param nextNode the next node in the chain
     */
    public Node(T data, Node<T> nextNode) {
        this.data = data;
        this.nextNode = nextNode;
    }

    /**
     * Constructs a node with the given data and no successor.
     *
     * @param data the data to store
     */
    public Node(T data) {
        this.data = data;
        this.nextNode = null;
    }

    /**
     * Returns the data stored in this node.
     *
     * @return the node's data
     */
    public T getData() {
        return data;
    }

    /**
     * Returns the next node in the chain, or null if none exists.
     *
     * @return the next node
     */
    public Node<T> getNextNode() {
        return nextNode;
    }

    /**
     * Sets the data stored in this node.
     *
     * @param data the new data value
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Sets the next node in the chain. Pass null to mark this as the last node.
     *
     * @param nextNode the node to link as the successor
     */
    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }

    /**
     * Compares this node to another by their data values.
     *
     * @param otherNode the node to compare against
     * @return negative, zero, or positive if this node's data is less than,
     *         equal to, or greater than the other's
     */
    public int compareTo(Node<T> otherNode) {
        return this.data.compareTo(otherNode.data);
    }
}