package edu.ics211.h08;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QueueTest {

	PacketQueue test;

	@BeforeEach
	void setUp() throws Exception {
		test = new PacketQueue();
	}

	void testQueue() {
		assertTrue(test.size()==0);
		Packet fifoTest = new Packet(0);
		assertTrue(test.offer(fifoTest));
		assertTrue(test.offer(new Packet(1)));
		Packet peekTest = new Packet(2);
		assertTrue(test.offer(peekTest));
		System.out.println(test.size());
		//assertTrue(test.poll().equals(peekTest));
		assertTrue(test.offer(new Packet(3)));
		assertTrue(test.offer(new Packet(4)));
		System.out.println(test.size());
		//assertTrue(test.size()==6);
		assertTrue(test.offer(new Packet(5)));
		assertTrue(test.offer(new Packet(6)));
		assertTrue(test.offer(new Packet(7)));
		//assertTrue(test.offer(new Packet(8)));
		//Packet pollTest = new Packet(9);
		//assertTrue(test.offer(pollTest));
		//assertTrue(test.size()==9);
		//test.poll();
		//test.poll();
		//test.poll();

	}

	@Test
	void test() {
		testQueue();
		//fail("Not yet implemented");
	}

}
