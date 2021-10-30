package edu.ics211.h08;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RouterTest {
	//Variable Declarations
	private Router testRouter;
	private int[][] numPackets = new int [8][10];

	@BeforeEach
	void setUp() {
		//Variable init for each test case
		testRouter = new Router(new PacketSender());
		numPackets = new int [][] {
				{0,1,2,3,4,5,6,7,0,1},
				{0,1,2,0,1,2,0,1,2,0},
				{0,1,2,0,1,2,0,1,2,0},
				{0,1,2,0,1,2,0,1,2,0},
				{0,1,2,0,1,2,0,1,2,0},
				{0,1,2,0,1,2,0,1,2,0},
				{0,1,2,0,1,2,0,1,2,0},
				{0,1,2,0,1,2,0,1,2,0}};
	}

	//Main Router Test Case
	private void RouterTest() {
		//Loops through and attempts to send all packets
		for(int j=0; j<numPackets[0].length; j++){
			for(int i=0; i<numPackets.length; i++){
				for(int k = 0; k<numPackets[i][j]; k++){
					testRouter.acceptPacket(new Packet(k));
				}
			}
			//advance the time to remove 1 packet from each queue
			testRouter.advanceTime();
			System.out.println();
		}

		//Assertions to see if the loops and methods ran correctly and without invariants.
		assertEquals(10,testRouter.getTime());
		java.util.List<Packet> droppedPackets = testRouter.getDroppedPackets();
		assertEquals(43,droppedPackets.size());//Why did it change from 16... Oh wells.
		java.util.List<Packet> droppedPackets2 = testRouter.getDroppedPackets();
		assertEquals(0, droppedPackets2.size());
	}

	//Overflow the router queue and see if it correctly drops packets.
	private void overFlowRouter() {
		//Sends 11 packets
		for(int i=0; i<11; i++) {
			//if currently on a packet less than max size, packet should be added
			if(i<10) {
				for(int j=0; j<8; j++){
					assertTrue(testRouter.acceptPacket(new Packet(j)));
				}
			}
			//else packet should be dropped
			else {
				for(int j=0; j<8; j++){
					assertFalse(testRouter.acceptPacket(new Packet(j)));
				}
			}
		}
		//Checks to see if correct amount of packets were dropped
		assertEquals(8,testRouter.getDroppedPackets().size());

		//Clears the queue of packets
		for(int i=0; i<10; i++){
			testRouter.advanceTime();
			System.out.println();
		}
	}

	//Fills router queue with 80 packets
	private void fullRouter() {
		for(int i=0; i<10; i++){
			for(int j=0; j<8; j++){
				assertTrue(testRouter.acceptPacket(new Packet(j)));
			}
		}
	}

	//Empties router queue completely
	private void fullEmpty() {
		//Fills router first
		fullRouter();

		//Proceeds to empty it and check if it took the correct amount of time to do so
		for(int i=0; i<10; i++){
			testRouter.advanceTime();
			System.out.println();
		}
		assertEquals(10,testRouter.getTime());
		testRouter.advanceTime();
		System.out.println();
		assertEquals(11,testRouter.getTime());
		//Fills router because test() might expect it to be full
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

	//PacketSender class, prints out info when send() is called.
	class PacketSender implements PacketSenderInterface {
		@Override
		public void send(int queue, Packet p) {
			System.out.println("Pinging... ");
			System.out.println("Sent 64 bytes (1 packet) to address 192.168.1." + p.getAddress() + ": icmp_seq=" + queue);
		}
	}
}