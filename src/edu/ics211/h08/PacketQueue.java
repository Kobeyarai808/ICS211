package edu.ics211.h08;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class PacketQueue extends AbstractQueue<Packet> implements Queue<Packet> {
    //variable declarations
    private MyLinkedList<Packet> queue;
    private int size;
    private int amtIteratorCalled;

    public PacketQueue(){
        //variable init
        queue = new MyLinkedList<Packet>();
        size = 0;
        amtIteratorCalled = 0;
    }

    //returns an iterator
    @Override public Iterator<Packet> iterator() {
        return queue.iterator(amtIteratorCalled++);
    }

    //returns an iterator without incrementing its index
    public Iterator<Packet> iteratorNoIncrement() {
        return queue.iterator(amtIteratorCalled);
    }

    //gets the size of the queue
    @Override public int size() {
        return size;
    }

    //attempts to add packet to queue and return true, if full/fails returns false
    @Override public boolean offer(Packet packet) {
        if(size==10){
            throw new NoSuchElementException();
        }
        size++;
        queue.add(packet);
        return true;
    }

    //removes a packet from the queue
    @Override public Packet poll() {
        if(size==10){
            throw new NoSuchElementException();
        }
        size--;
        return iterator().next();
    }

    //returns current packet without incrementing the iterator
    @Override public Packet peek() {
        return iteratorNoIncrement().next();
    }

    /*private class LinkedList{

        private class LinkedNode{
            Packet item;
            LinkedNode next;
            private LinkedNode(Packet item){
                this.item = item;
                next = null;
            }
        }

        LinkedNode head;
        LinkedNode tail;
        int size;
        private LinkedList(){
            head = null;
            tail = null;
            size = 0;
        }
        private void add(Packet item){
            tail.next = new LinkedNode(item);
            tail = tail.next;
        }

        private class Iterator implements java.util.Iterator {
            private Iterator(){

            }
            @Override public boolean hasNext() {

            }
            @Override public Object next() {
                return null;
            }
        }
    }*/
}