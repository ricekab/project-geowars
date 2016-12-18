package be.howest.twentytwo.parametergame.model.system;

import java.util.Collection;
import java.util.Iterator;

import be.howest.twentytwo.parametergame.model.physics.message.IPhysicsMessage;

import com.badlogic.ashley.systems.IntervalSystem;

public class PhysicsEventSystem extends IntervalSystem {
	
	private Collection<IPhysicsMessage> eventCollection; // TODO: Collection requirements? Might need
														// change.

	public PhysicsEventSystem(float interval, int priority) {
		super(interval, priority);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void updateInterval() {
		Iterator<IPhysicsMessage> it = eventCollection.iterator();
		IPhysicsMessage evt;
		while (it.hasNext()) {
			evt = it.next();
			if(!evt.isConsumed()) {
				evt.execute();
				if(evt.isConsumed()) {
					it.remove();
				}
			} else {
				it.remove(); // Not redundant, other systems can affect events.
			}
		}
	}

}
