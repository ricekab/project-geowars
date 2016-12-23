package be.howest.twentytwo.parametergame.model.system;

import java.util.Collection;
import java.util.Queue;

import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;

import be.howest.twentytwo.parametergame.dataTypes.BoxDataI;
import be.howest.twentytwo.parametergame.dataTypes.ClusterDataI;
import be.howest.twentytwo.parametergame.dataTypes.LevelDataI;
import be.howest.twentytwo.parametergame.dataTypes.SpawnPoolDataI;
import be.howest.twentytwo.parametergame.factory.ProjectileFactory;
import be.howest.twentytwo.parametergame.model.event.EventQueue;
import be.howest.twentytwo.parametergame.model.event.game.GameLoseEvent;
import be.howest.twentytwo.parametergame.model.event.game.GameSpawnDepletedEvent;
import be.howest.twentytwo.parametergame.model.physics.collision.Collision;
import be.howest.twentytwo.parametergame.model.spawn.message.ISpawnMessage;
import be.howest.twentytwo.parametergame.model.spawn.message.SpawnEntityMessage;

/**
 * This system is responsible for deciding when and where to spawn enemies on
 * the field.
 */
public class AISpawnSystem extends IntervalSystem {

	public static final int PRIORITY = 0;
	private static final float SPAWN_CHECK_INTERVAL = 0.5f;

	private final World world;
	private final Body player;
	private final BoxDataI levelBounds;
	private final EventQueue events;
	private final Collection<ISpawnMessage> spawner;
	private final Queue<SpawnPoolDataI> spawnpools;

	private SpawnPoolDataI currentPool;
	private float timeSinceLastSpawn;
	private float spawnInterval;
	private boolean active;

	public AISpawnSystem(BoxDataI levelBounds, Body playerBody, EventQueue eventQueue,
			Collection<ISpawnMessage> spawnMessageQueue, Queue<SpawnPoolDataI> queue, float spawnInterval) {
		super(SPAWN_CHECK_INTERVAL, PRIORITY);
		this.world = playerBody.getWorld();
		this.player = playerBody;
		this.levelBounds = levelBounds;
		this.events = eventQueue;
		this.spawner = spawnMessageQueue;
		this.spawnpools = queue;
		this.active = true;
		this.currentPool = null;
		this.spawnInterval = spawnInterval;
		this.timeSinceLastSpawn = 0f;
	}

	public AISpawnSystem(BoxDataI levelBounds, Body playerBody, EventQueue eventQueue,
			Collection<ISpawnMessage> spawnMessageQueue, Queue<SpawnPoolDataI> queue) {
		this(levelBounds, playerBody, eventQueue, spawnMessageQueue, queue, 2f);
	}

	@Override
	protected void updateInterval() {
		// Do I need to spawn?
		if (!active) {
			return; // No more to be done
		}
		if (timeSinceLastSpawn < spawnInterval) {
			timeSinceLastSpawn += SPAWN_CHECK_INTERVAL;
			return;
		}
		timeSinceLastSpawn = 0f;
		// Retrieve cluster to spawn
		if (currentPool == null || currentPool.isEmpty()) {
			if (spawnpools.isEmpty()) {
				System.out.println("SPAWN END");
				this.active = false;
				// No enemies left, player win.
				// 1. UI Message --> player won
				// 2. Upgrade screen/next level after some timedelay
				events.send(new GameLoseEvent()); // TODO: TEMP
				events.send(new GameSpawnDepletedEvent());
				return;
			}
			currentPool = spawnpools.poll();
		}
		// Start spawning based on cluster information
		ClusterDataI cluster = currentPool.getRandomCluster();
		float clearBoxAreaSize = 50f;
		for (int i = 0; i < cluster.getGroups(); i++) {
			// Find appropriate location to spawn (group).
			Vector2 spawnBoxLower = findRandomSpawnPosition(clearBoxAreaSize);
			for (int j = 0; j < cluster.getEnemies(); j++) {
				Vector2 spawnPos = new Vector2(spawnBoxLower.x + (float) Math.random() * clearBoxAreaSize,
						spawnBoxLower.y + (float) Math.random() * clearBoxAreaSize);

				Gdx.app.debug("AISPAWN", "AI SPAWNED AT: " + spawnPos.toString());
				spawner.add(new SpawnEntityMessage(cluster.getEnemyName(), spawnPos, new Vector2(0f, 0f),
						(float) Math.random() * 360f, Collision.ENEMY_CATEGORY, Collision.ENEMY_MASK));
			}
		}
	}

	/**
	 * Returns the lower coordinates for the box where ai can be spawned in.
	 */
	private Vector2 findRandomSpawnPosition(float boxSize) {
		// Select a random world area.
		float randomX = 0f;
		float randomY = 0f;
		boolean positionFound = false;
		AreaClearAABBCallback areaClearCallback;
		while (!positionFound) {
			areaClearCallback = new AreaClearAABBCallback(Collision.ENEMY_SPAWN_FILTER_MASK);
			randomX = (float) Math.random() * levelBounds.getWidth() - levelBounds.getWidth() / 2;
			randomY = (float) Math.random() * levelBounds.getHeight() - levelBounds.getHeight() / 2;
			world.QueryAABB(areaClearCallback, randomX, randomY, randomX + boxSize, randomY + boxSize);
			positionFound = areaClearCallback.isAreaClear();
		}
		return new Vector2(randomX, randomY);
	}

	private class AreaClearAABBCallback implements QueryCallback {

		private short detectionMask;
		private boolean areaClear;

		public AreaClearAABBCallback(short detectionMask) {
			this.detectionMask = detectionMask;
			setAreaClear(true);
		}

		@Override
		public boolean reportFixture(Fixture fixture) {
			if ((fixture.getFilterData().categoryBits & detectionMask) > 0) {
				setAreaClear(false);
				return false;
			}
			return true;
		}

		public boolean isAreaClear() {
			return areaClear;
		}

		public void setAreaClear(boolean areaClear) {
			this.areaClear = areaClear;
		}

	}
}
