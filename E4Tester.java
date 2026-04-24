import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class E4Tester {

    // Shared fixtures
    private UnOrderedChain<Integer> unordered;
    private OrderedChain<Integer>   ordered;

    @BeforeEach
    void setUp() {
        unordered = new UnOrderedChain<>();
        ordered   = new OrderedChain<>();
    }

    @AfterEach
    void tearDown() {
        unordered = null;
        ordered   = null;
    }


    // UnOrderedChain Constructor tests
    @Test
    void unordered_defaultConstructor_emptyChain() {
        assertTrue(unordered.isEmpty());
        assertEquals(0, unordered.getNumberOfNodes());
        assertNull(unordered.getHead());
    }

    @Test
    void unordered_nodeConstructor_headSet() {
        Node<Integer> node = new Node<>(42);
        UnOrderedChain<Integer> chain = new UnOrderedChain<>(node);
        assertFalse(chain.isEmpty());
        assertEquals(1, chain.getNumberOfNodes());
        assertEquals(42, chain.getHead().getData());
    }

    // add() method tests

    @Test
    void unordered_add_singleElement_becomesHead() {
        unordered.add(10);
        assertEquals(10, unordered.getFirst());
        assertEquals(1, unordered.getNumberOfNodes());
    }

    @Test
    void unordered_add_multipleElements_latestIsHead() {
        unordered.add(10);
        unordered.add(20);
        unordered.add(30);
        // unordered adds at front, so 30, 20, 10
        assertEquals(30, unordered.getFirst());
        assertEquals(10, unordered.getLast());
        assertEquals(3, unordered.getNumberOfNodes());
    }

    @Test
    void unordered_add_returnsTrue() {
        assertTrue(unordered.add(5));
    }

    // remove() method tests

    @Test
    void unordered_remove_emptyChain_returnsNull() {
        assertNull(unordered.remove());
    }

    @Test
    void unordered_remove_singleElement_chainBecomesEmpty() {
        unordered.add(10);
        assertEquals(10, unordered.remove());
        assertTrue(unordered.isEmpty());
        assertEquals(0, unordered.getNumberOfNodes());
    }

    @Test
    void unordered_remove_multipleElements_removesHead() {
        unordered.add(10);
        unordered.add(20);
        // chain: 20, 10
        assertEquals(20, unordered.remove());
        assertEquals(10, unordered.getFirst());
        assertEquals(1, unordered.getNumberOfNodes());
    }

    // remove(T element) method test

    @Test
    void unordered_removeElement_emptyChain_returnsNull() {
        assertNull(unordered.remove(99));
    }

    @Test
    void unordered_removeElement_notPresent_returnsNull() {
        unordered.add(10);
        unordered.add(20);
        assertNull(unordered.remove(99));
        assertEquals(2, unordered.getNumberOfNodes());
    }

    @Test
    void unordered_removeElement_atHead_returnsElement() {
        unordered.add(10);
        unordered.add(20); // chain: 20, 10
        assertEquals(20, unordered.remove(20));
        assertEquals(10, unordered.getFirst());
        assertEquals(1, unordered.getNumberOfNodes());
    }

    @Test
    void unordered_removeElement_atTail_returnsElement() {
        unordered.add(10);
        unordered.add(20); // chain: 20, 10
        assertEquals(10, unordered.remove(10));
        assertEquals(20, unordered.getFirst());
        assertEquals(1, unordered.getNumberOfNodes());
    }

    @Test
    void unordered_removeElement_inMiddle_returnsElement() {
        unordered.add(10);
        unordered.add(20);
        unordered.add(30); // chain: 30, 20, 10
        assertEquals(20, unordered.remove(20));
        assertEquals(30, unordered.getFirst());
        assertEquals(10, unordered.getLast());
        assertEquals(2, unordered.getNumberOfNodes());
    }

    @Test
    void unordered_removeElement_duplicate_removesFirstOccurrence() {
        unordered.add(10);
        unordered.add(10); // chain: 10, 10
        assertEquals(10, unordered.remove(10));
        assertEquals(1, unordered.getNumberOfNodes());
    }

    // search() method tests

    @Test
    void unordered_search_emptyChain_returnsFalse() {
        assertFalse(unordered.search(10, unordered.getHead()));
    }

    @Test
    void unordered_search_elementPresent_returnsTrue() {
        unordered.add(10);
        unordered.add(20);
        assertTrue(unordered.search(10, unordered.getHead()));
    }

    @Test
    void unordered_search_elementNotPresent_returnsFalse() {
        unordered.add(10);
        unordered.add(20);
        assertFalse(unordered.search(99, unordered.getHead()));
    }

    // getFirst() and getLast() method tests

    @Test
    void unordered_getFirst_emptyChain_returnsNull() {
        assertNull(unordered.getFirst());
    }

    @Test
    void unordered_getLast_emptyChain_returnsNull() {
        assertNull(unordered.getLast());
    }

    @Test
    void unordered_getFirst_getLast_singleElement_sameValue() {
        unordered.add(42);
        assertEquals(42, unordered.getFirst());
        assertEquals(42, unordered.getLast());
    }

    @Test
    void unordered_getFirst_getLast_multipleElements() {
        unordered.add(10);
        unordered.add(20);
        unordered.add(30); // chain: 30, 20, 10
        assertEquals(30, unordered.getFirst());
        assertEquals(10, unordered.getLast());
    }

    // isEmpty() and getNumberOfNodes() method tests

    @Test
    void unordered_isEmpty_afterAddAndRemove_correctState() {
        assertTrue(unordered.isEmpty());
        unordered.add(1);
        assertFalse(unordered.isEmpty());
        unordered.remove();
        assertTrue(unordered.isEmpty());
    }

    @Test
    void unordered_getNumberOfNodes_tracksCorrectly() {
        assertEquals(0, unordered.getNumberOfNodes());
        unordered.add(1);
        unordered.add(2);
        assertEquals(2, unordered.getNumberOfNodes());
        unordered.remove();
        assertEquals(1, unordered.getNumberOfNodes());
    }

    // OrderedChain tests Constructor tests
    @Test
    void ordered_defaultConstructor_emptyChain() {
        assertTrue(ordered.isEmpty());
        assertEquals(0, ordered.getNumberOfNodes());
        assertNull(ordered.getHead());
    }

    @Test
    void ordered_nodeConstructor_headSet() {
        Node<Integer> node = new Node<>(42);
        OrderedChain<Integer> chain = new OrderedChain<>(node);
        assertFalse(chain.isEmpty());
        assertEquals(1, chain.getNumberOfNodes());
        assertEquals(42, chain.getHead().getData());
    }

    // add() OrderedChain method tests
    @Test
    void ordered_add_singleElement_becomesHead() {
        ordered.add(10);
        assertEquals(10, ordered.getFirst());
        assertEquals(1, ordered.getNumberOfNodes());
    }

    @Test
    void ordered_add_ascendingInput_maintainsOrder() {
        ordered.add(10);
        ordered.add(20);
        ordered.add(30);
        assertEquals(10, ordered.getFirst());
        assertEquals(30, ordered.getLast());
    }

    @Test
    void ordered_add_descendingInput_maintainsOrder() {
        ordered.add(30);
        ordered.add(20);
        ordered.add(10);
        assertEquals(10, ordered.getFirst());
        assertEquals(30, ordered.getLast());
    }

    @Test
    void ordered_add_randomInput_maintainsOrder() {
        ordered.add(50);
        ordered.add(10);
        ordered.add(40);
        ordered.add(20);
        ordered.add(30);
        assertEquals(10, ordered.getFirst());
        assertEquals(50, ordered.getLast());
        assertEquals(5, ordered.getNumberOfNodes());
    }

    @Test
    void ordered_add_smallerThanHead_becomesNewHead() {
        ordered.add(50);
        ordered.add(10); // 10 < 50, should become head
        assertEquals(10, ordered.getFirst());
        assertEquals(50, ordered.getLast());
    }

    @Test
    void ordered_add_returnsTrue() {
        assertTrue(ordered.add(5));
    }

    // remove() method tests
    @Test
    void ordered_remove_emptyChain_returnsNull() {
        assertNull(ordered.remove());
    }

    @Test
    void ordered_remove_singleElement_chainBecomesEmpty() {
        ordered.add(10);
        assertEquals(10, ordered.remove());
        assertTrue(ordered.isEmpty());
        assertEquals(0, ordered.getNumberOfNodes());
    }

    @Test
    void ordered_remove_multipleElements_removesSmallest() {
        ordered.add(30);
        ordered.add(10);
        ordered.add(20); // chain: 10, 20, 30
        assertEquals(10, ordered.remove());
        assertEquals(20, ordered.getFirst());
        assertEquals(2, ordered.getNumberOfNodes());
    }

    // remove(T element) method tests

    @Test
    void ordered_removeElement_emptyChain_returnsNull() {
        assertNull(ordered.remove(99));
    }

    @Test
    void ordered_removeElement_notPresent_returnsNull() {
        ordered.add(10);
        ordered.add(20);
        assertNull(ordered.remove(99));
        assertEquals(2, ordered.getNumberOfNodes());
    }

    @Test
    void ordered_removeElement_atHead_returnsElement() {
        ordered.add(10);
        ordered.add(20); // chain: 10, 20
        assertEquals(10, ordered.remove(10));
        assertEquals(20, ordered.getFirst());
        assertEquals(1, ordered.getNumberOfNodes());
    }

    @Test
    void ordered_removeElement_atTail_returnsElement() {
        ordered.add(10);
        ordered.add(20); // chain: 10, 20
        assertEquals(20, ordered.remove(20));
        assertEquals(10, ordered.getFirst());
        assertEquals(1, ordered.getNumberOfNodes());
    }

    @Test
    void ordered_removeElement_inMiddle_returnsElement() {
        ordered.add(10);
        ordered.add(20);
        ordered.add(30); // chain: 10, 20, 30
        assertEquals(20, ordered.remove(20));
        assertEquals(10, ordered.getFirst());
        assertEquals(30, ordered.getLast());
        assertEquals(2, ordered.getNumberOfNodes());
    }

    @Test
    void ordered_removeElement_earlyExit_valueAboveTarget_returnsNull() {
        ordered.add(10);
        ordered.add(30);
        ordered.add(50); // chain: 10, 30, 50
        // 20 sits between 10 and 30 — search overshoots at 30 and exits early
        assertNull(ordered.remove(20));
        assertEquals(3, ordered.getNumberOfNodes());
    }

    @Test
    void ordered_removeElement_duplicate_removesFirstOccurrence() {
        ordered.add(20);
        ordered.add(20); // chain: 20, 20
        assertEquals(20, ordered.remove(20));
        assertEquals(1, ordered.getNumberOfNodes());
    }

    // search() method tests
    @Test
    void ordered_search_emptyChain_returnsFalse() {
        assertFalse(ordered.search(10, ordered.getHead()));
    }

    @Test
    void ordered_search_elementPresent_returnsTrue() {
        ordered.add(10);
        ordered.add(30);
        assertTrue(ordered.search(30, ordered.getHead()));
    }

    @Test
    void ordered_search_elementNotPresent_returnsFalse() {
        ordered.add(10);
        ordered.add(30);
        assertFalse(ordered.search(99, ordered.getHead()));
    }

    // getFirst() and getLast() method tests
    @Test
    void ordered_getFirst_emptyChain_returnsNull() {
        assertNull(ordered.getFirst());
    }

    @Test
    void ordered_getLast_emptyChain_returnsNull() {
        assertNull(ordered.getLast());
    }

    @Test
    void ordered_getFirst_getLast_singleElement_sameValue() {
        ordered.add(42);
        assertEquals(42, ordered.getFirst());
        assertEquals(42, ordered.getLast());
    }

    @Test
    void ordered_getFirst_alwaysSmallest() {
        ordered.add(50);
        ordered.add(10);
        ordered.add(30);
        assertEquals(10, ordered.getFirst());
    }

    @Test
    void ordered_getLast_alwaysLargest() {
        ordered.add(50);
        ordered.add(10);
        ordered.add(30);
        assertEquals(50, ordered.getLast());
    }

    // isEmpty() and getNumberOfNodes() method tests
    @Test
    void ordered_isEmpty_afterAddAndRemove_correctState() {
        assertTrue(ordered.isEmpty());
        ordered.add(1);
        assertFalse(ordered.isEmpty());
        ordered.remove();
        assertTrue(ordered.isEmpty());
    }

    @Test
    void ordered_getNumberOfNodes_tracksCorrectly() {
        assertEquals(0, ordered.getNumberOfNodes());
        ordered.add(10);
        ordered.add(20);
        assertEquals(2, ordered.getNumberOfNodes());
        ordered.remove();
        assertEquals(1, ordered.getNumberOfNodes());
    }
}
