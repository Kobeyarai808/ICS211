package edu.ics211.h08;

import java.util.Comparator;

/**
 * A list implemented using a singly-linked list
 *
 * @author Edo Biagioni; Amanda Nitta & Kobey Arai
 * @lecture ICS 211 Jan 27 (or later)
 * @date January 26, 2011
 */
public class MyLinkedList<E> {
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
     * initializes an empty linked list
     */
    Comparator comparator;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
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
    
    public boolean add(E value) {
        LinkedNode<E> currentNode = head;
        if (currentNode != null) {
            // adds at the end of the linkedNode
            addAtEnd(value);
        }
        // adds at the front
        else {
            addAtFront(value);
            currentNode = head;
        }
        size++;
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

     public int size() {
        return size;
    }

     public E get(int index) {
        return nodeAtPosition(index).item;
    }

    //when want to remove node, need to have access to node before, singly linked doesnt have access
    // loop going it have two vairables, one for the one before and one for the one removing
     public boolean remove(E value) {
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
     public int indexOf(E value) {
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (comparator.compare(value, nodeAtPosition(i).item) == 0) {
                index = i;
                return index;
            }
        }
        return -1;
    }

    // returns an iterator
     public Iterator iterator(int index) {
        Iterator created = new Iterator(index);
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
        private Iterator(int index) {
            current = head;  // head is declared in the enclosing class
            for(int i=0;i<index;i++){
                next();
            }
        }
        // checks if the current node is null
        public boolean hasNext() {
            if (current!= null) {
                return true;
            }
            return false;
        }

        public LinkedNode<E> getCurrent(){
            return current;
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
            throw new UnsupportedOperationException("Linked list iterator remove not supported");
        }
    }

    /**
     * concatenates the elements of the linked list, separated by " ==> "
     *
     * @return the string representation of the list
     */
    public String toString() {
        LinkedNode<E> node = head;
        StringBuffer result = new StringBuffer();
        while (node != null) {
            result.append(node.item.toString());
            node = node.next;
            if (node != null) {
                result.append(" ==> ");
            }
        }
        return result.toString();
    }
}