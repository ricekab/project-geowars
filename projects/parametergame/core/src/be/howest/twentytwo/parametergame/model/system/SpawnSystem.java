package be.howest.twentytwo.parametergame.model.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import be.howest.twentytwo.parametergame.factory.ISpawnFactory;
import be.howest.twentytwo.parametergame.model.spawn.message.ISpawnMessage;

import com.badlogic.ashley.systems.IntervalSystem;

public class SpawnSystem extends IntervalSystem {

	public static final int PRIORITY = 1;

	private Collection<ISpawnMessage> messages;
	private Collection<ISpawnFactory> factories;

	public SpawnSystem(Collection<ISpawnMessage> messageQueue) {
		super(PhysicsSystem.PHYSICS_TIMESTEP, PRIORITY);
		this.messages = messageQueue;
		this.factories = new ArrayList<ISpawnFactory>();
	}

	@Override
	protected void updateInterval() {
		Iterator<ISpawnMessage> it = messages.iterator();
		while (it.hasNext()) {
			processMessage(it.next());
			it.remove();
		}
	}

	private void processMessage(ISpawnMessage msg) {
		for (ISpawnFactory factory : factories) {
			if (factory.getType().equals(msg.getType())) {
				msg.execute(factory);
				return;
			}
		}
	}

	public void addFactory(ISpawnFactory factory) {
		// TODO: some duplication check or smth. Maybe move this into a set.
		factories.add(factory);
	}

	public Collection<ISpawnFactory> getSpawnFactories() {
		return factories;
	}
}