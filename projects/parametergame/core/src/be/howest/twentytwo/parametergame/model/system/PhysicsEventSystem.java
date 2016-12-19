package be.howest.twentytwo.parametergame.model.system;

import java.util.Collection;
import java.util.Iterator;

import be.howest.twentytwo.parametergame.model.physics.message.IPhysicsMessage;

import com.badlogic.ashley.systems.IntervalSystem;

@Deprecated
/**
 * Currently not in use. Was originally made to split out some of the physics system but ended up not using it.
 */
public class PhysicsEventSystem extends IntervalSystem {
	
	public static final int PRIORITY = 1;
	
	private Collection<IPhysicsMessage> physicsMessages;

	public PhysicsEventSystem(float interval) {
		super(interval, PRIORITY);

	}

	@Override
	protected void updateInterval() {
		Iterator<IPhysicsMessage> it = physicsMessages.iterator();
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
