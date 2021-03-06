package edu.ics211.h08;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Router implements RouterInterface {
	// declaration of vars
	private List<Packet> droppedPackets;
	private int time;
	private PacketSenderInterface packetSender;
	private final int numQueues = 8;
	private static PacketQueue[] queues = new PacketQueue[8];


	public Router(PacketSenderInterface sender) {
		// init vars
		time = 0;
		droppedPackets = new ArrayList<Packet>();
		packetSender = sender;
		for(int i=0; i<numQueues; i++) {
			queues[i] = new PacketQueue();
		}
	}


	@Override
	public void advanceTime() {
		// removes a packet from each queue and sends it
		for (int i = 0; i < numQueues; i++) {
			if(queues[i].size() > 0) {
				packetSender.send(i,queues[i].poll());
			}
		}
		// Increment time
		time++;
	}


	@Override
	public boolean acceptPacket(Packet p) {
		//System.out.println("Trying to send packet to queue " + p.getAddress());
		// get address
		int address = p.getAddress();
		// add address to queue, if fails will add to droppedPackets
		try{
			queues[address].offer(p);
		}catch(NoSuchElementException e){
			droppedPackets.add(p);
			return false;
		}
		return true;
	}


	@Override
	public List<Packet> getDroppedPackets() {
		// temp holds droppedPackets
		List<Packet> temp = new ArrayList<Packet>(droppedPackets);
		// resets droppedPackets to empty list
		droppedPackets = new ArrayList<Packet>();
		// returns the list of droppedPackets
		return temp;
	}

	public int getTime(){
		return time;
	}
}