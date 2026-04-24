/**
 * A chain where elements are maintained in ascending order.
 *
 * @param <T> the element type; must implement Comparable
 */
public class OrderedChain<T extends Comparable<T>> implements ChainedNode<T> {

    private Node<T> head;
    private int size;

    /**
     * Constructs an OrderedChain with the given node as the head.
     *
     * @param head the initial head node
     */
    public OrderedChain(Node<T> head) {
        this.head = head;
        this.size = 1;
    }

    /**
     * Constructs an empty OrderedChain.
     */
    public OrderedChain() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Returns the head node without removing it.
     *
     * @return the head node, or null if the chain is empty
     */
    public Node<T> getHead() {
        return head;
    }

    /**
     * Inserts an element into the chain in ascending order.
     *
     * @param element the element to add
     * @return true if successfully added, false otherwise
     */
    @Override
    public boolean add(T element) {
        Node<T> newNode = new Node<>(element); // create node first
        if (isEmpty() || element.compareTo(head.getData()) <= 0) {
            newNode.setNextNode(head); // newNode connects to existing chain
            head = newNode; // head pointer moves to newNode
        } else { // find the gap, connect newNode forward, then connect backward
            insertInOrder(newNode, element);
        }
        size++; // track size
        return true; // confirm successful add
    }

    /**
     * Traverses the chain to find and insert the new node in ascending order.
     *
     * @param newNode the node to insert
     * @param element the data used to determine the insertion position
     */
    private void insertInOrder(Node<T> newNode, T element) {
        Node<T> previous = head;              // safe to initialize, chain not empty
        Node<T> current = head.getNextNode(); // starts one ahead of previous
        while (current != null && current.getData().compareTo(element) <= 0) {
            previous = previous.getNextNode(); // advance previous
            current = current.getNextNode();   // advance current
        }
        newNode.setNextNode(current);          // connect newNode to rest of chain (Forward)
        previous.setNextNode(newNode);         // connect previous to newNode (Backward)
    }

    /**
     * Removes and returns the first element in the chain.
     *
     * @return the first element, or null if the chain is empty
     */
    @Override
    public T remove() {
        if (isEmpty()) {
            return null;
        } else {
            T firstNodeData = head.getData();
            Node<T> newPointer = head.getNextNode();
            head = newPointer;
            size -= 1;
            return firstNodeData;
        }
    }

    /**
     * Removes and returns the first occurrence of the specified element.
     * Exits early if the current node exceeds the target, since the
     * chain is sorted in ascending order.
     *
     * @param element the element to remove
     * @return the removed element, or null if not found
     */
    @Override
    public T remove(T element) {
        if (isEmpty()) {
            return null; // empty chain
        }
        if (head.getData().compareTo(element) == 0) {
            return remove(); // head is the target
        }
        Node<T> previous = head;
        Node<T> current  = head.getNextNode();
        while (current != null) {
            int cmp = current.getData().compareTo(element);
            if (cmp == 0) { // match found
                size--;
                return bridgeAndReturn(previous, current);
            }
            if (cmp > 0) { // overshot — ordered early exit
                return null;
            }
            previous = current;
            current  = current.getNextNode();
        }
        return null; // reached end without a match
    }

    /**
     * Relinks previous to current's successor, removing current from the chain.
     *
     * @param previous the node before the one being removed
     * @param current  the node to remove
     * @return the data of the removed node
     */
    private T bridgeAndReturn(Node<T> previous, Node<T> current) {
        previous.setNextNode(current.getNextNode());
        return current.getData();
    }

    /**
     * Recursively searches for the specified element starting from the given node.
     *
     * @param element the element to search for
     * @param node    the starting node for this recursive call
     * @return true if found, false otherwise
     */
    @Override
    public boolean search(T element, Node<T> node) {
        if (node == null) { return false; } // end of chain, not found
        if (node.getData().compareTo(element) == 0) { return true; } // element found
        return search(element, node.getNextNode()); // advance to next node
    }

    /**
     * Returns the first element without removing it.
     *
     * @return the first element, or null if the chain is empty
     */
    @Override
    public T getFirst() {
        return isEmpty() ? null : head.getData();
    }

    /**
     * Returns the last element without removing it.
     *
     * @return the last element, or null if the chain is empty
     */
    @Override
    public T getLast() {
        if (isEmpty()) { return null; } // guard against empty chain
        Node<T> current = head; // traversal pointer starts at head
        while (current.getNextNode() != null) { // stop when no next node exists
            current = current.getNextNode(); // advance current, head never moves
        }
        return current.getData(); // current is sitting on last node
    }

    /**
     * Returns the number of nodes in the chain.
     *
     * @return the node count as a non-negative integer
     */
    @Override
    public int getNumberOfNodes() {
        return size;
    }

    /**
     * Returns true if the chain contains no nodes.
     *
     * @return true if empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }
}