package edu.ics211.h08;

import java.util.ArrayList;
import java.util.List;

public class Router implements RouterInterface {

	private PacketQueue queue;
	private List droppedPackets;
	private int time;
	PacketSenderInterface sender;

	public Router(PacketSenderInterface theSender) {
		queue = new PacketQueue();
		droppedPackets = new ArrayList();
		time = 0;
		sender = theSender;
	}

	@Override public void advanceTime() {
		time++;
		sender.send(queue.size()-1,queue.poll());
	}

	@Override public boolean acceptPacket(Packet p) {
		if(queue.size()==8){
			droppedPackets.add(p);
			return false;
		}
		queue.offer(p);
		return true;
	}

	@Override public List getDroppedPackets() {
		List temp = droppedPackets;
		droppedPackets = new ArrayList();
		return temp;
	}
}
