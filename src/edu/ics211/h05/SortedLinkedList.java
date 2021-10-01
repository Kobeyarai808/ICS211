package edu.ics211.h05;

import java.util.Comparator;

/**
 * A list implemented using a singly-linked list
 *
 * @author Edo Biagioni; Amanda Nitta & Kobey Arai
 * @lecture ICS 211 Jan 27 (or later)
 * @date January 26, 2011
 */
public class SortedLinkedList<E> implements SortedLinkedListInterface<E>, Iterable {
    /**
     * A node in a singly-linked list
     *
     * @author Edo Biagioni; Amanda Nitta & Kobey Arai
     * @lecture ICS 211 Jan 27 or later
     * @date January 26, 2010
     */
    private static class LinkedNode<T> {
        private T item;
        private LinkedNode<T> next;

        /**
         * constructor to build a node with no successor
         *
         * @param value to be stored by this node
         */
        private LinkedNode(T value) {
            item = value;
            next = null;
        }

        /**
         * constructor to build a node with specified (maybe null) successor
         *
         * @param value     to be stored by this node
         * @param reference is the next field for this node
         */
        private LinkedNode(T value, LinkedNode<T> reference) {
            item = value;
            next = reference;
        }
    }

    // this is the start of the linked list.  If the list is empty, it is null
    protected LinkedNode<E> head;
    // this is the end of the linked list.  If the list is empty, it is null
    protected LinkedNode<E> tail;
    protected int size;
    // there are some relationships between the class variables.  This
    // method checks that these relationships always hold.  Any
    // property that always holds is called an invariant.

    // the property may not hold in the middle of a method,
    // so only call this at the beginning or end of a public method.

    /**
     * checks assertion
     *
     * @throws java.lang.AssertionError if the assertion is not true
     */
    private void verify(boolean mustBeTrue) {
        if (!mustBeTrue) {
            throw new java.lang.AssertionError("assertion error");
        }
    }

    /**
     * checks class invariants
     *
     * @throws java.lang.AssertionError if the invariant is violated
     */
    private void checkInvariants() {
        // uncomment the next line to skip the checks:
        // return;
        // either head and tail are both null, or neither is null.
        // size is zero if and only if they are null, and otherwise is positive
        verify((head == null) == (tail == null));
        verify((size == 0) == (head == null));
        verify(size >= 0);
        // if the list only has one element, head should be the same as tail
        // (and also if the list has no elements), otherwise they should differ
        verify((head == tail) == (size <= 1));
        // a non-null tail variable should always have a null "next" field
        verify((tail == null) || (tail.next == null));
        // check to make sure size is the same as the length of the list.
        // this code takes O(n), so comment it out if performance is important
        int measuredSize = 0;
        LinkedNode<E> node = head;
        // if visitedLast is null, the list is empty, and tail should also be null
        LinkedNode<E> visitedLast = null;
        while (node != null) {
            visitedLast = node;
            node = node.next;
            measuredSize++;
        }
        verify(measuredSize == size);
        // also make sure "last" really is the last node in the linked list
        verify(visitedLast == tail);
    }

    /**
     * initializes an empty linked list
     */
    Comparator comparator;

    public SortedLinkedList(java.util.Comparator<E> comparator) {
        head = null;
        tail = null;
        size = 0;
        this.comparator = comparator;
        // one of the constructor's jobs is to make sure that the invariants hold.
        checkInvariants();
    }

    // these private (helper) methods simplify implementation of
    // the public "add" methods
    // the helper methods never modify "size", the public methods
    // take care of that, so the invariants probably do not hold at the end of
    // a helper methods

    /**
     * adds at the head of the list
     *
     * @param value to be added
     */
    private void addAtFront(E value) {
        head = new LinkedNode<E>(value, head);
        if (tail == null) {
            tail = head;
        }
    }

    /**
     * adds at the tail of the list.  Assumes (and checks) that tail is not null
     *
     * @param value to be added
     * @throws RuntimeException
     */
    private void addAtEnd(E value) {
        if (tail == null) {
            throw new RuntimeException("invalid call to addAtEnd, tail is null");
        }
        LinkedNode<E> newNode = new LinkedNode<E>(value);
        tail.next = newNode;
        tail = newNode;
    }

    /**
     * adds a value to the list after the given node
     *
     * @param reference node after which the new value is added
     * @param value     to be added
     */
    private void addAfter(LinkedNode<E> reference, E value) {
        LinkedNode<E> newNode = new LinkedNode<E>(value, reference.next);
        reference.next = newNode;
        if (reference == tail) {  // if added at end, update tail value
            tail = newNode;
        }
    }

    /**
     * adds a value to the end of the list
     *
     * @param value to be added
     * @return true if added, false if not added.
     */
    @Override
    public boolean add(E value) {
        LinkedNode<E> currentNode = head;
        checkInvariants(); // useful for debugging
        if (currentNode != null) {
            //System.out.println("current node not null");
            for (int i = 0; i < size; i++) {
                // if the second node is null

                //if value is the same doesnt add
                if (comparator.compare(value, currentNode.item) == 0) {
                    return false;
                }
                if (currentNode.next == null) {
                    //if the value is less than the current node, insert in the front
                    if(comparator.compare(value,currentNode.item)<0){
                        addAtFront(value);
                        size++;
                        return true;
                    }
                    // if not adds after the current node
                    addAfter(currentNode,value);
                    size++;
                    return true;
                }

                // if value is less than the node, adds at the front
                else if (comparator.compare(value, currentNode.item) < 0) {
                    addAtFront(value);
                    size++;
                    return true;
                }
                // if value is less than the item following the current node, it adds after the
                // first ndoe
                else if (comparator.compare(value, currentNode.next.item) < 0) {
                    addAfter(currentNode, value);
                    size++;
                    return true;
                }

                // sets current node to the nexy one
                currentNode = currentNode.next;
            }
            // adds at the end of the linkedNode
            addAtEnd(value);
        }

        // adds at the front
        else {
            addAtFront(value);
            currentNode = head;
        }
        size++;
        checkInvariants();  // invariants valid at start, are they still valid?
        // i.e., did this method break the invariants?
        return true;
    }

    /**
     * adds a value to the list, in the given position
     *
     * @param index at which to add: 0 to add at the start
     * @param value to be added
     * @throws IndexOutOfBoundsException if the index is less than 0
     *                                   or greater than the number of elements in the linked list
     */
    public void add(int index, E value) {
        checkInvariants();
        if ((index < 0) || (index > size)) {
            String badIndex =
                    new String("index " + index + " must be between 0 and " + size);
            throw new IndexOutOfBoundsException(badIndex);
        }
        if (index == 0) {
            addAtFront(value);
        } else {
            addAfter(nodeAtPosition(index - 1), value);
        }
        size++;
        checkInvariants();
    }

    /**
     * returns the node at the requested position, may take time O(n)
     *
     * @param index of the requested node, 0 for the head node
     * @return the requested node
     * @throws NullPointerException if the index is larger than the linked list
     */
    private LinkedNode<E> nodeAtPosition(int index) {
        verify(index >= 0);
        LinkedNode<E> result = head;
        while (index > 0) {
            result = result.next;
            index--;
        }
        verify(result != null);
        return result;
    }

    @Override public int size() {
        return size;
    }

    @Override public E get(int index) {
        return nodeAtPosition(index).item;
    }

    //when want to remove node, need to have access to node before, singly linked doesnt have access
    // loop going it have two vairables, one for the one before and one for the one removing
    @Override public boolean remove(E value) {
        // starts at the head of the node
        LinkedNode<E> currentNode = head;
        // when find the equavalen node it removes it and decrements the size
        if(comparator.compare(value,currentNode.item)==0){
            head = currentNode.next;
            size--;
            return true;
        }
        // if currentNode.next is null
        while(currentNode.next != null){
            // finds the equivalent, removes it and connects the node following
            if(comparator.compare(value, currentNode.next.item)==0){
                currentNode.next = currentNode.next.next;
                size--;
                return true;
            }
            // goes to the next node
            currentNode = currentNode.next;
        }
        return false;
    }

    // finds the indexOf of the node
    @Override public int indexOf(E value) {
        checkInvariants();
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (comparator.compare(value, nodeAtPosition(i).item) == 0) {
                index = i;
                return index;
            }
            checkInvariants();
        }

        return -1;
    }

    //element at 0, 2, 4
    // returns the eveniterator
    @Override public Iterator evenIterator() {
        EvenIterator created = new EvenIterator();
        /// created = created.
        return created;
    }

    //1,3,5,7
    // returns the odd iterator
    @Override public Iterator oddIterator() {
        OddIterator created = new OddIterator();
        return created;
    }
    // returns an iterator
    @Override public Iterator iterator() {
        Iterator created = new Iterator();
        return created;
    }

    /**
     * A linked list iterator that does not support remove
     *
     * @author Edo Biagioni; Amanda Nitta and Kobey Arai
     * @lecture ICS 211 Feb 3 (or later)
     * @date February 1, 2011
     */

    // sorted Linked List Iterator
    private class Iterator implements java.util.Iterator<E> {
        private LinkedNode<E> current;

        private Iterator() {
            current = head;  // head is declared in the enclosing class
        }
        // checks if the current node is null
        public boolean hasNext() {
            if (current!= null) {
                return true;
            }
            return false;
        }
        // gets the next value and iterates to the next one
        public E next() {
            if (hasNext()) {
                E result = current.item;
                current = current.next;   // may be null
                return result;
            }


            // no next element
            throw new java.util.NoSuchElementException("linked list.next");
        }

        public void remove() {
            throw new UnsupportedOperationException
                    ("Linked list iterator remove not supported");
        }
    }
    // even iterator
    private class EvenIterator extends Iterator {
        private LinkedNode<E> current;
        // stars at the head
        private EvenIterator() {
            current = head;  // head is declared in the enclosing class
        }
        // checks if null
        public boolean hasNext() {
            return (current!= null);
        }
        // gets the value and iterates over two nodes
        public E next() {
            if (hasNext()) {
                E result = current.item;
                current = current.next;   // may be null
                if(hasNext()) {
                    current = current.next;   // may be null
                }
                return result;
            }  // no next element
            throw new java.util.NoSuchElementException("linked list.next");
        }

        public void remove() {
            throw new UnsupportedOperationException
                    ("Linked list iterator remove not supported");
        }
    }

    //odd iterator
    private class OddIterator extends Iterator {
        private LinkedNode<E> current;

        // starts at the second node/ index 1
        private OddIterator() {
            current = head.next;  // head is declared in the enclosing class
        }
        // checks if the current is null
        public boolean hasNext() {
            return (current!= null);
        }
        // gets the value at the current and then iterates over 2
        public E next() {
            if (hasNext()) {
                E result = current.item;
                current = current.next;
                if(hasNext()) {
                    current = current.next;   // may be null
                }
                return result;
            }  // no next element
            throw new java.util.NoSuchElementException("linked list.next");
        }

        public void remove() {
            throw new UnsupportedOperationException
                    ("Linked list iterator remove not supported");
        }
    }

    /**
     * concatenates the elements of the linked list, separated by " ==> "
     *
     * @return the string representation of the list
     */
    public String toString() {
        checkInvariants();
        LinkedNode<E> node = head;
        StringBuffer result = new StringBuffer();
        while (node != null) {
            result.append(node.item.toString());
            node = node.next;
            if (node != null) {
                result.append(" ==> ");
            }
        }
        checkInvariants();   // make sure we didn't break anything
        return result.toString();
    }

    /**
     * unit test method -- basic testing of the functionality
     *
     * @param arguments required, ignored
     */
    public static void main(String[] arguments) {
        /*StringComparator sc = new StringComparator();
        SortedLinkedList<String> ll = new SortedLinkedList<String>(sc);
        System.out.println(ll);
        ll.add("notBeingSorted");
        System.out.println(ll);
        ll.add("apple");
        System.out.println(ll);
        ll.add("microsoft");
        System.out.println(ll);
        ll.add("google");
        System.out.println(ll);
        ll.add("foo");
        System.out.println(ll);
        ll.add("world");
        System.out.println(ll);
        ll.add("hello");
        System.out.println(ll);
        ll.remove("apple");
        System.out.println(ll);
        ll.remove("notBeingSorted");
        System.out.println(ll);**/

        StringComparator sc = new StringComparator();
        SortedLinkedList<String> ll = new SortedLinkedList<String>(sc);
    	/*ll.add("banana");
    	ll.add("apple");
    	ll.add("microsoft");
    	ll.add("quick");
    	ll.add("google");
    	System.out.print(ll);*/
        ll.add("banana");
        ll.add("squash");
        ll.add("microsoft");
        ll.add("quick");
        ll.add("google");
        ll.add("dog");
        ll.add("plumeria");
        ll.add("dolphin");
        ll.add("picture");
        ll.add("zoo");
        ll.add("flower");
        ll.add("yellow");
        ll.remove("yellow");
        System.out.print(ll);

        DoublesComparator dc = new DoublesComparator();
        SortedLinkedList<Double> dll = new SortedLinkedList<Double>(dc);
        dll.add(7.01);
        System.out.println(dll);
        dll.add(2.01);
        System.out.println(dll);
        dll.add(6.01);
        System.out.println(dll);
        dll.add(3.01);
        System.out.println(dll);
        dll.add(6.00);
        System.out.println(dll);
        dll.add(8.01);
        System.out.println(dll);
        dll.add(0.01);
        System.out.println(dll);
        dll.add(0.01);
        System.out.println(dll);

        java.util.Iterator<Double> It = dll.iterator();
        String printValue = It.next().toString();
        while (It.hasNext()) {
            printValue = printValue + " " + It.next().toString();
        }
        System.out.println("this is the print value of iterator " + printValue);

        java.util.Iterator<Double> oIt = dll.oddIterator();
        String printValue3 = oIt.next().toString();

        while (oIt.hasNext()) {
            printValue3 = printValue3 + " " + oIt.next();
        }

        System.out.println("this is the print value of the odd iterator " + printValue3);

        java.util.Iterator<Double> eIt = dll.evenIterator();
        String printValue2 = eIt.next().toString();
        while (eIt.hasNext()) {
            printValue2 = printValue2 + " " + eIt.next();
        }
        System.out.println("this is the print value of even iterator " + printValue2);

    }
}