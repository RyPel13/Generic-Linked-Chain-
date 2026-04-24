# Generic-Linked-Chain-
•	Implemented a custom generic linked data structure (Node<T>) in Java as a dynamic, type-safe alternative to fixed-size arrays, compatible with any reference type determined at runtime.

•	Designed the ChainedNode interface to define a reusable contract for both ordered and unordered collections, specifying abstract methods for add, remove, search, and traversal — enforcing a consistent API across multiple implementations.

•	Applied inheritance by extending UnOrderedChain into an OrderedChain subclass, overriding add() and remove(T element) to maintain ascending sorted order using the Comparable interface for generic object comparison.

•	Implemented a recursive search(T element, Node<T> node) method defined in the interface, demonstrating functional recursion over a pointer-based structure without relying on built-in collection utilities.

•	Developed JUnit test suites (E4Tester.java) covering all class methods, reinforcing test-driven development practices and validating both ordered and unordered behaviors under edge cases.

