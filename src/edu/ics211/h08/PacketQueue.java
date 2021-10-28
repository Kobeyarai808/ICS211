package edu.ics211.h08;

import java.util.AbstractQueue;
import java.util.Iterator;
import edu.ics211.h08.MyLinkedList;
import java.util.Queue;

public class PacketQueue extends AbstractQueue<Packet> implements Queue<Packet> {

    private MyLinkedList<Packet> queue;
    private int size;
    private int amtIteratorCalled;

    public PacketQueue(){
        queue = new MyLinkedList<Packet>();
        size = 0;
        amtIteratorCalled = 0;
    }

    @Override public Iterator<Packet> iterator() {
        return queue.iterator(amtIteratorCalled++);
    }

    public Iterator<Packet> iteratorNoIncrement() {
        return queue.iterator(amtIteratorCalled);
    }

    @Override public int size() {
        return size;
    }

    @Override public boolean offer(Packet packet) {
        if(size==8){
            return false;
        }
        size++;
        queue.add(packet);
        return true;
    }

    @Override public Packet poll() {
        size--;
        return iterator().next();
    }

    @Override public Packet peek() {
        return iteratorNoIncrement().next();
    }
}