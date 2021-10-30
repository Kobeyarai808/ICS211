package edu.ics211.h08;

import java.util.AbstractQueue;
import java.util.Iterator;
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
            return false;
        }
        size++;
        queue.add(packet);
        return true;
    }

    //removes a packet from the queue
    @Override public Packet poll() {
        size--;
        return iterator().next();
    }

    //returns current packet without incrementing the iterator
    @Override public Packet peek() {
        return iteratorNoIncrement().next();
    }
}