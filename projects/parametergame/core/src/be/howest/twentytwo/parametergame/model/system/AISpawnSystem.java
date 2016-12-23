package be.howest.twentytwo.parametergame.model.system;

import java.util.Collection;
import java.util.Queue;

import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import be.howest.twentytwo.parametergame.dataTypes.ClusterDataI;
import be.howest.twentytwo.parametergame.dataTypes.SpawnPoolDataI;
import be.howest.twentytwo.parametergame.factory.ProjectileFactory;
import be.howest.twentytwo.parametergame.model.event.EventQueue;
import be.howest.twentytwo.parametergame.model.event.game.GameLoseEvent;
import be.howest.twentytwo.parametergame.model.event.game.GameSpawnDepletedEvent;
import be.howest.twentytwo.parametergame.model.physics.collision.Collision;
import be.howest.twentytwo.parametergame.model.spawn.message.ISpawnMessage;
import be.howest.twentytwo.parametergame.model.spawn.message.SpawnEntityMessage;

/**
 * This system is responsible for deciding when to spawn enemies on the field.
 */
public class AISpawnSystem extends IntervalSystem {

	public static final int PRIORITY = 0;
	private static final float SPAWN_CHECK_INTERVAL = 0.5f;

	private final World world;
	private final EventQueue events;
	private final Collection<ISpawnMessage> spawner;
	private final Queue<SpawnPoolDataI> spawnpools;

	private SpawnPoolDataI currentPool;
	private float timeSinceLastSpawn;
	private float spawnInterval;
	private boolean active;

	public AISpawnSystem(World world, EventQueue eventQueue, Collection<ISpawnMessage> spawnMessageQueue,
			Queue<SpawnPoolDataI> queue, float spawnInterval) {
		super(SPAWN_CHECK_INTERVAL, PRIORITY);
		this.world = world;
		this.events = eventQueue;
		this.spawner = spawnMessageQueue;
		this.spawnpools = queue;
		this.active = true;
		this.currentPool = null;
		this.spawnInterval = spawnInterval;
		this.timeSinceLastSpawn = 0f;
	}

	public AISpawnSystem(World world, EventQueue eventQueue, Collection<ISpawnMessage> spawnMessageQueue,
			Queue<SpawnPoolDataI> queue) {
		this(world, eventQueue, spawnMessageQueue, queue, 2f);
	}

	@Override
	protected void updateInterval() {
		// Do I need to spawn?
		if(!active){
			return;	// No more to be done
		}
		if(timeSinceLastSpawn < spawnInterval){
			timeSinceLastSpawn += SPAWN_CHECK_INTERVAL;
			return;
		}
		timeSinceLastSpawn = 0f;
		// Retrieve cluster to spawn
		if (currentPool == null || currentPool.isEmpty()) {
			if (spawnpools.isEmpty()) {
				System.out.println("GAME END");
				this.active = false;
				// No enemies left, player win.
				// 1. UI Message --> player won
				// 2. Upgrade screen/next level after some timedelay
				events.send(new GameLoseEvent());	// TODO: TEMP
				events.send(new GameSpawnDepletedEvent());
				return;
			}
			currentPool = spawnpools.poll();
		}
		// Start spawning based on cluster information
		ClusterDataI cluster = currentPool.getRandomCluster();
		for(int i = 0; i < cluster.getGroups(); i++){
			// Find appropriate location to spawn.
			// TODO
			for(int j = 0; j < cluster.getEnemies(); j++){
				spawner.add(new SpawnEntityMessage(cluster.getEnemyName(), findSpawnPosition(), new Vector2(), 0f,
						Collision.ENEMY_CATEGORY, Collision.ENEMY_MASK));		
			}
		}
	}

	private Vector2 findSpawnPosition() {
		Vector2 pos = new Vector2();
		// TODO: Calculate pos (currently just 0,0)
		return pos;
	}

}
