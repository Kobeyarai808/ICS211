package edu.ics211.h08;
import java.util.List;
import java.util.ArrayList;

public class Router implements RouterInterface {
	// declares the variables that belong to the class
	private List<Packet> rejected;
	private int time;
	private PacketSenderInterface sender;
	// declares as final so doesnt't change
	private final int numberQueues = 8;
	// declares there to be 8 packetQueues
	private static PacketQueue[] queues = new PacketQueue[8];

	/**
	 * constructor for router
	 * @param sender == has the sender method
	 */
	public Router(PacketSenderInterface sender) {
		// initializes all variables per each object of router
		time = 0;
		rejected = new ArrayList<Packet>();
		this.sender = sender;
		// generates 8 PacketQueues to create a router
		for(int i = 0; i < numberQueues; i++) {
			PacketQueue created = new PacketQueue();
			queues[i] = created;
		}
	}

	/**
	 * advance time method
	 *
	 * every time it is called, increments time and goes through all the queues
	 * and removes one packet from each
	 *
	 */
	@Override
	public void advanceTime() {
		// Increment time
		time++;
		// removes one packet from each and sends the packet
		for (int i = 0; i < numberQueues; i++) {
			if(queues[i].size() > 0) {
				sender.send(i,queues[i].poll());
			}
		}
	}

	/**
	 * acceptPacket method
	 * p == packet trying to add
	 *
	 * returns whether or not the packet was accepted into a queue
	 */
	@Override
	public boolean acceptPacket(Packet p) {
		// gets the address from the packet/ queue destination
		int got = p.getAddress();
		// tries to put it in
		if(!queues[got].offer(p)){
			// adds the packet to the rejected list
			rejected.add(p);
			// returns false since doesn't add the packet
			return false;
		}
		// if able to be put in then returns true
		return true;
	}

	/**
	 * getDroppedPackets method -- returns the packets that were not able to be added
	 * to the queues
	 */
	@Override
	public List<Packet> getDroppedPackets() {
		// creates a new arrayList to hold the packets
		List<Packet> gotRejected = new ArrayList<Packet>(rejected);
		// creatrs a new ArrayList to clear the packets that were just rejected
		rejected = new ArrayList<Packet>();
		// returns the list of packets that were rejected prior to clearing
		return gotRejected;
	}

	public int getTime(){
		return time;
	}
}