package edu.ics211.h08;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QueueTest {

	PacketQueue test;

	@BeforeEach
	void setUp() {
		test = new PacketQueue();
	}

	void testQueue() {
		Packet item0 = new Packet(0);
		Packet item1 = new Packet(1);
		Packet item2 = new Packet(2);
		Packet item3 = new Packet(3);
		Packet item4 = new Packet(4);
		Packet item5 = new Packet(5);
		Packet item6 = new Packet(6);
		Packet item7 = new Packet(7);
		Packet item8 = new Packet(8);
		Packet item9 = new Packet(9);


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
		testQueue();
		//fail("Not yet implemented");
	}

}
