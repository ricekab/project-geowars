package be.howest.twentytwo.parametergame.model.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import be.howest.twentytwo.parametergame.factory.ISpawnFactory;
import be.howest.twentytwo.parametergame.model.spawn.message.ISpawnMessage;

import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.Gdx;

/**
 * This system process {@link ISpawnMessage} requests and creates the requested
 * entity according to the message.
 */
public class SpawnSystem extends IntervalSystem {

	public static final int PRIORITY = 1;

	private Collection<ISpawnMessage> messages;
	private Collection<ISpawnFactory> factories;

	public SpawnSystem(Collection<ISpawnMessage> messageQueue) {
		super(PhysicsSystem.PHYSICS_TIMESTEP, PRIORITY);
		this.messages = messageQueue;
		this.factories = new HashSet<ISpawnFactory>();
	}

	@Override
	protected void updateInterval() {
		for (ISpawnMessage m : messages) {
			processMessage(m);
		}
		messages.clear();
	}

	private void processMessage(ISpawnMessage msg) {
		for (ISpawnFactory factory : factories) {
			if (factory.getType().equals(msg.getType())) {
				msg.execute(factory);
				return;
			}
		}
		Gdx.app.error("SpawnSystem", "ERR: Could not find appropriate factory for: " + msg.getType());
	}

	public void addFactory(ISpawnFactory factory) {
		// TODO: some duplication check or smth. Maybe move this into a set.
		factories.add(factory);
	}

	public Collection<ISpawnFactory> getSpawnFactories() {
		return factories;
	}
}