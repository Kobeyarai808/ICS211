package edu.ics211.h08;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RouterTest {
	Router testRouter;
	int[][] numPackets = new int [8][10];
	int[][] numberOfPackets = new int[8][10];

	void setUp() {
		testRouter = new Router(new PacketSender());
		numberOfPackets = new int [][] {
				{0,1,2,3,4,5,6,7,0,1},
				{0,1,2,3,4,5,6,7,0,1},
				{0,1,2,3,4,5,6,7,0,1},
				{0,1,2,3,4,5,6,7,0,1},
				{0,1,2,3,4,5,6,7,0,1},
				{0,1,2,3,4,5,6,7,0,1},
				{0,1,2,3,4,5,6,7,0,1},
				{0,1,2,3,4,5,6,7,0,1}};

		numPackets = new int [][] {{1,2,4,6,3,5,2,1,7,4}, {0,1,2,0,1,2,1,2,1,0},
				{0,1,2,0,1,2,1,2,1,0},{0,1,2,0,1,2,1,2,1,0},{0,1,2,0,1,2,1,2,1,0},{1,1,2,0,1,2,1,2,1,0},
				{0,1,2,0,1,2,1,2,1,0},{0,1,2,0,1,2,1,2,1,0}};
	}

	private void RouterTest() {
		for(int j=0; j<numPackets[0].length; j++){
			for(int i=0; i<numPackets.length; i++){
				for(int k = 0; k<numPackets[i][j]; k++){
					if(j>=4){
						if(k == 0) {
							testRouter.acceptPacket(new Packet(0));//assertTrue
						}
						else {
							testRouter.acceptPacket(new Packet(0));//assertFalse
						}
					}
					else{
						testRouter.acceptPacket(new Packet(k));//assertTrue
					}
				}
			}
			testRouter.advanceTime();
			System.out.println();
		}

		assertEquals(10,testRouter.getTime());
		java.util.List<Packet> droppedPackets = testRouter.getDroppedPackets();
		assertEquals(16,droppedPackets.size());
		java.util.List<Packet> droppedPackets2 = testRouter.getDroppedPackets();
		assertEquals(0, droppedPackets2.size());
	}

	private void overFlowRouter() {
		// adds packets to router to cause an overflow
		for(int i=0; i<20; i++) {
			if(i<10) {
				for(int j=0; j<8; j++){
					assertTrue(testRouter.acceptPacket(new Packet(j)));
				}
			}
			else {
				for(int j=0; j<8; j++){
					assertFalse(testRouter.acceptPacket(new Packet(j)));
				}
			}
		}

		assertEquals(80,testRouter.getDroppedPackets().size());

		// clears the router
		for(int i=0; i<10; i++){
			testRouter.advanceTime();
			System.out.println();
		}

		// proves time works after emptying everything
		testRouter.advanceTime();
		System.out.println();
		testRouter.advanceTime();
		System.out.println();
		testRouter.advanceTime();
	}

	private void fullRouter() {
		// fills each queue with 10 packets
		for(int i=0; i<10; i++){
			for(int j=0; j<8; j++){
				assertTrue(testRouter.acceptPacket(new Packet(j)));
			}
		}
	}

	private void fullEmpty() {
		fullRouter();
		// calling to get everything empty
		for(int i=0; i<10; i++){
			testRouter.advanceTime();
			System.out.println();
		}

		assertEquals(10,testRouter.getTime());
		//checks if still works although nothing is in queue
		testRouter.advanceTime();
		System.out.println();

		// checks to show still incrementing time interval
		assertEquals(11,testRouter.getTime());
		// proves that can add packets to router after emptying router
		fullRouter();
	}

	@Test
	void test() {
		setUp();
		RouterTest();
		setUp();
		fullRouter();
		setUp();
		overFlowRouter();
		setUp();
		fullEmpty();

	}

	class PacketSender implements PacketSenderInterface {
		@Override
		public void send(int queue, Packet p) {
			System.out.println("Pinging... ");
			System.out.println("Sent 64 bytes to address " + p.getAddress() + ": icmp_seq=" + queue);
		}
	}
}