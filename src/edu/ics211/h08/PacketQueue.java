package edu.ics211.h08;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class PacketQueue extends AbstractQueue<Packet> implements Queue<Packet> {

    private LinkedList<Packet> queue;
    private LinkedNode head;
    private LinkedNode tail;
    private int size;

    public PacketQueue(){
        queue = new LinkedList<Packet>();
        size = 0;
    }

    @Override public Iterator<Packet> iterator() {
        return new LinkedListIterator();
    }

    @Override public int size() {
        return size;
    }

    @Override public boolean offer(Packet packet) {
        if(size==8){
            return false;
        }
        queue.add(packet);
        return true;
    }

    @Override public Packet poll() {
        return iterator().next();
    }

    @Override public Packet peek() {
        return head.item;
    }

    private class LinkedNode{
        public Packet item;
        public LinkedNode next;
        public LinkedNode(Packet packet,LinkedNode nextNode){
            item = packet;
            next = nextNode;
        }
        public LinkedNode(Packet packet){
            item = packet;
        }
        public Packet add(Packet packet){
            LinkedNode toAdd = new LinkedNode(packet);
            if(head==null){
                head = toAdd;
                tail = toAdd;
            }
            else{
                tail.next = toAdd;
                tail = toAdd;
                size++;
            }
            return packet;
        }
    }

    /**
     * @author         Edo Biagioni and Kobey Arai
     * @lecture        ICS 211 Feb 3 (or later)
     * @date           February 1, 2011, modified October 24, 2021
     */
    private class LinkedListIterator implements java.util.Iterator<Packet> {
        private LinkedNode current;

        private LinkedListIterator() {
            current = head;  // head is declared in the enclosing class
        }

        public boolean hasNext() {
            return (current.next != null);
        }

        public Packet next() {
            if (hasNext()) {
                Packet result = current.item;
                current = current.next;   // may be null
                return result;
            }  // no next element
            throw new java.util.NoSuchElementException("linked list.next");
        }

        public void remove() {
            throw new UnsupportedOperationException("Linked list iterator remove not supported");
        }
    }
}