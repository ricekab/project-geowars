package be.howest.twentytwo.parametergame.model.system;

import java.util.Collection;
import java.util.Queue;

import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.Vector2;

import be.howest.twentytwo.parametergame.dataTypes.ClusterDataI;
import be.howest.twentytwo.parametergame.dataTypes.SpawnPoolDataI;
import be.howest.twentytwo.parametergame.factory.ProjectileFactory;
import be.howest.twentytwo.parametergame.model.event.EventQueue;
import be.howest.twentytwo.parametergame.model.event.game.GameEndEvent;
import be.howest.twentytwo.parametergame.model.physics.collision.Collision;
import be.howest.twentytwo.parametergame.model.spawn.message.ISpawnMessage;
import be.howest.twentytwo.parametergame.model.spawn.message.SpawnProjectileMessage;

/**
 * This system is responsible for deciding when to spawn enemies on the field.
 */
public class AISpawnSystem extends IntervalSystem {

	public static final int PRIORITY = 0;
	private static final float SPAWN_CHECK_INTERVAL = 0.5f;

	private final EventQueue events;
	private final Collection<ISpawnMessage> spawner;
	private final Queue<SpawnPoolDataI> spawnpools;

	private SpawnPoolDataI currentPool;
	private float timeSinceLastSpawn;
	private float spawnInterval;

	public AISpawnSystem(EventQueue eventQueue, Collection<ISpawnMessage> spawnMessageQueue,
			Queue<SpawnPoolDataI> queue) {
		super(SPAWN_CHECK_INTERVAL, PRIORITY);
		this.events = eventQueue;
		this.spawner = spawnMessageQueue;
		this.spawnpools = queue;
	}

	@Override
	protected void updateInterval() {
		if (currentPool == null) {
			if (spawnpools.isEmpty()) {
				// No enemies left, player win.
				// 1. UI Message --> player won
				// 2. Upgrade screen/next level after some timedelay
				events.send(new GameEndEvent());
			}
			currentPool = spawnpools.poll();
		}
		ClusterDataI cluster = currentPool.getRandomCluster();
		cluster.getEnemyName();
		spawner.add(new SpawnProjectileMessage(cluster.getEnemyName(), findSpawnPosition(), new Vector2(), 0f,
				Collision.ENEMY_CATEGORY, Collision.ENEMY_MASK));
		cluster.takeOne();
	}

	private Vector2 findSpawnPosition() {
		Vector2 pos = new Vector2();
		// TODO: Calculate pos (currently just 0,0)
		return pos;
	}

}
