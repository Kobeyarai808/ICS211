package edu.ics211.h08;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class QueueTest {

	PacketQueue test;
	Packet item0;
	Packet item1;
	Packet item2;
	Packet item3;
	Packet item4;
	Packet item5;
	Packet item6;
	Packet item7;
	Packet item8;
	Packet item9;

	@BeforeEach
	void setUp() {
		test = new PacketQueue();
		item0 = new Packet(0);
		item1 = new Packet(1);
		item2 = new Packet(2);
		item3 = new Packet(3);
		item4 = new Packet(4);
		item5 = new Packet(5);
		item6 = new Packet(6);
		item7 = new Packet(7);
		item8 = new Packet(8);
		item9 = new Packet(9);
	}

	void testEmptyQueue(){
		assertThrows(NoSuchElementException.class, () -> test.peek());
		assertThrows(NoSuchElementException.class, () -> test.poll());
		assertTrue(test.size()==0);
		assertTrue(test.offer(item0));
		assertTrue(test.peek().equals(item0));
		assertTrue(test.size()==1);
		assertTrue(test.poll().equals(item0));
		assertTrue(test.size()==0);
		assertThrows(NoSuchElementException.class, () -> test.peek());
		assertThrows(NoSuchElementException.class, () -> test.poll());
	}
	void testQueue1Element(){

	}
	void testQueueNearFull(){

	}
	void testQueueFull(){

	}

	void testQueue() {


		assertTrue(test.size()==0);
		assertTrue(test.offer(item0));
		assertTrue(test.offer(item1));
		assertTrue(test.offer(item2));
		//System.out.println(test.size());
		assertTrue(test.size()==3);
		assertTrue(test.poll().equals(item0));
		assertTrue(test.offer(item3));
		assertTrue(test.offer(item4));
		//System.out.println(test.size());
		assertTrue(test.size()==4);
		assertTrue(test.offer(item5));
		assertTrue(test.offer(item6));
		assertTrue(test.offer(item7));
		assertTrue(test.offer(item8));
		assertTrue(!test.offer(item9));
		assertTrue(test.poll().equals(item1));
		assertTrue(test.poll().equals(item2));
		assertTrue(test.poll().equals(item3));
		//System.out.println(test.size());
		assertTrue(test.size()==5);
		assertTrue(test.peek().equals(item4));
	}

	@Test
	void test() {
		setUp();
		testEmptyQueue();
		setUp();
		testQueue();
		//fail("Not yet implemented");
	}

}
