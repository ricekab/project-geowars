package be.howest.twentytwo.parametergame.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

import be.howest.twentytwo.parametergame.ParameterGame;
import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.dataTypes.EnemyDataI;
import be.howest.twentytwo.parametergame.dataTypes.LevelDataI;
import be.howest.twentytwo.parametergame.dataTypes.PlanetData;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipData;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.SettingsDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.UserDataI;
import be.howest.twentytwo.parametergame.dataTypes.WeaponDataI;
import be.howest.twentytwo.parametergame.input.PlayerInputProcessor;
import be.howest.twentytwo.parametergame.input.actions.InputAction;
import be.howest.twentytwo.parametergame.model.PhysicsBodyEntityListener;
import be.howest.twentytwo.parametergame.model.ai.BrutalizerAIMoveBehaviour;
import be.howest.twentytwo.parametergame.model.ai.BrutalizerAIShootBehaviour;
import be.howest.twentytwo.parametergame.model.ai.ScoutAIMoveBehaviour;
import be.howest.twentytwo.parametergame.model.ai.SuiciderAIMoveBehaviour;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.CameraComponent;
import be.howest.twentytwo.parametergame.model.event.EventEnum;
import be.howest.twentytwo.parametergame.model.event.EventQueue;
import be.howest.twentytwo.parametergame.model.event.IEvent;
import be.howest.twentytwo.parametergame.model.event.listener.DestroyEntityListener;
import be.howest.twentytwo.parametergame.model.event.listener.IEventListener;
import be.howest.twentytwo.parametergame.model.physics.collision.BulletContactProcessor;
import be.howest.twentytwo.parametergame.model.physics.collision.ContactProcessor;
import be.howest.twentytwo.parametergame.model.physics.collision.GravityContactProcessor;
import be.howest.twentytwo.parametergame.model.physics.collision.PlayerContactProcessor;
import be.howest.twentytwo.parametergame.model.physics.message.IPhysicsMessage;
import be.howest.twentytwo.parametergame.model.spawn.message.ISpawnMessage;
import be.howest.twentytwo.parametergame.model.system.AIMovementSystem;
import be.howest.twentytwo.parametergame.model.system.AiSystem;
import be.howest.twentytwo.parametergame.model.system.BackgroundRenderSystem;
import be.howest.twentytwo.parametergame.model.system.CameraSystem;
import be.howest.twentytwo.parametergame.model.system.MovementSystem;
import be.howest.twentytwo.parametergame.model.system.PhysicsDebugRenderSystem;
import be.howest.twentytwo.parametergame.model.system.PhysicsSystem;
import be.howest.twentytwo.parametergame.model.system.RenderSystem;
import be.howest.twentytwo.parametergame.model.system.SpawnSystem;
import be.howest.twentytwo.parametergame.model.system.TimerSystem;
import be.howest.twentytwo.parametergame.model.system.WeaponSystem;
import be.howest.twentytwo.parametergame.service.db.IDataService;

/**
 * Builds up the physics {@link World} as well as all populates the ECS engine with level-defined
 * entities.
 * 
 * Note -- The ECS engine can listen for new entities added and add their body to the world. This
 * simplifies adding entities to the engine without having to pass the World around everywhere just
 * in case it's needed. The downside (?) is that the body definition is required as well.
 * 
 * --> Ended up opting for factories having access to the World object. They're responsibly for
 * creating the object in its entirety.
 * 
 * Logically, it makes sense for most of the builders/factories to keep a copy of body def and
 * fixture def since they'll make a lot of copies. This basically becomes flyweight-esque.
 *
 * TODO: Fix docs
 */
public class LevelFactory {

	public LevelFactory() {
	}

	public PooledEngine createWorld(ScreenContext context, Viewport viewport,
			EventQueue eventQueue, String levelName) {
		LevelDataI levelData = context.getFileService().loadLevel(levelName);
		IDataService dataService = context.getDataService();
		AssetManager assets = context.getAssetManager();

		// ENGINE
		PooledEngine engine = new PooledEngine();

		// MESSAGING OBJECTS
		Collection<IPhysicsMessage> physicsMessageQueue = new ArrayList<IPhysicsMessage>();
		Collection<ISpawnMessage> spawnMessageQueue = new ArrayList<ISpawnMessage>();

		// PHYSICS INIT
		World world = new World(new Vector2(0f, 0f), true);

		ContactProcessor collisionListener = new GravityContactProcessor(eventQueue,
				physicsMessageQueue);
		collisionListener.addProcessor(new PlayerContactProcessor(eventQueue, physicsMessageQueue));
		collisionListener.addProcessor(new BulletContactProcessor(eventQueue, physicsMessageQueue));
		// TODO: Add other contact listeners here.

		world.setContactListener(collisionListener);

		// SYSTEMS
		RenderSystem renderSys = new RenderSystem(context.getSpriteBatch(), viewport);
		BackgroundRenderSystem bgRenderSys = new BackgroundRenderSystem(context.getSpriteBatch(),
				assets, viewport);
		SpawnSystem spawnSystem = new SpawnSystem(spawnMessageQueue);
		engine.addSystem(new MovementSystem(physicsMessageQueue));
		engine.addSystem(new WeaponSystem(spawnMessageQueue, eventQueue));
		PhysicsSystem physicsSystem = new PhysicsSystem(world, physicsMessageQueue);
		engine.addSystem(physicsSystem);
		// engine.addEntityListener(physicsSystem);
		engine.addSystem(new AiSystem(physicsMessageQueue));
		engine.addSystem(spawnSystem);
		engine.addSystem(new CameraSystem());
		engine.addSystem(bgRenderSys);
		engine.addSystem(renderSys);
		engine.addSystem(new TimerSystem(eventQueue));
		engine.addSystem(new AIMovementSystem());
		// engine.addSystem(new AISystem());
		// Sound, Animation, ...
		// engine.addSystem(new EntityDestroyerSystem());

		if(ParameterGame.DEBUG_ENABLED) {
			engine.addSystem(new PhysicsDebugRenderSystem(world, renderSys.getCamera(), context
					.getShapeRenderer()));
		}

		engine.addEntityListener(Family.all(BodyComponent.class).get(),
				new PhysicsBodyEntityListener(world));

		// ENTITY CREATION
		Set<WeaponDataI> allWeapons = new HashSet<WeaponDataI>();

		Collection<ShipDataI> ships = dataService.getShips(dataService.getUser("TEST"));
		if(ships.isEmpty()) {
			Gdx.app.error("LevelFactory", "ERR: NO SHIPS FOR USER");
		}
		// TODO: Currently just selecting a random ship.
		ShipDataI shipData = ships.iterator().next();
		PlayerShipData playerShipData = new PlayerShipData(shipData, "ID", 5f, 10, 1, 50f);

		// TODO: This is getting messy, needed for spawn system.
		allWeapons.addAll(shipData.getWeapons());

		PlayerShipFactory playerFactory = new PlayerShipFactory(engine, world, assets,
				playerShipData);
		PlanetFactory planetFactory = new PlanetFactory(engine, world, assets);

		// TODO: get position from start bounding box
		Entity playerShip = playerFactory.createPlayerShip(8.0f, 8.0f); // 5, 5
																		// original
																		// size

		engine.addEntity(playerShip);

		Body playerBody = BodyComponent.MAPPER.get(playerShip).getBody();

		// AI creation
		Collection<EnemyDataI> enemies = dataService.getEnemies("scouter", "brutalizer",
				"suicider", "obstacle", "suicideSquad");
		EnemyDataI enemy = enemies.iterator().next();
		// Spawn scout ship
		AIShipFactory aiScoutShipFactory = new AIShipFactory(engine, world, assets,
				enemy.getShipData(), playerBody, new ScoutAIMoveBehaviour(15f));
		aiScoutShipFactory.spawnEntity(new Vector2(-40, -20), 0f, new Vector2(0f, 0f));
		aiScoutShipFactory.spawnEntity(new Vector2(0, -50f), 1f, new Vector2(0f, 0f));
		aiScoutShipFactory.spawnEntity(new Vector2(0f, -60f), 45f, new Vector2(0f, 0f));
		aiScoutShipFactory.spawnEntity(new Vector2(30f, -180f), 0f, new Vector2(0f, 0f));
		aiScoutShipFactory.spawnEntity(new Vector2(30f, -190f), 0f, new Vector2(0f, 0f));

		// Spawn brutalizer ship
		AIShipFactory aiBrutalizerShipFactory = new AIShipFactory(engine, world, assets,
				enemy.getShipData(), playerBody, new BrutalizerAIMoveBehaviour(225f, 20f, 25f),
				new BrutalizerAIShootBehaviour());
		aiBrutalizerShipFactory.spawnEntity(new Vector2(-60, -10), 0f, new Vector2(0f, 0f));

		// Spawn obstacle
		AIShipFactory aiObstacleShipFactory = new AIShipFactory(engine, world, assets,
				enemy.getShipData(), playerBody);
		aiObstacleShipFactory.spawnEntity(new Vector2(-80, -40), 0f, new Vector2(0f, 0f));

		// Spawn suidicer
		AIShipFactory aiSuiciderShipFactory = new AIShipFactory(engine, world, assets,
				enemy.getShipData(), playerBody, new SuiciderAIMoveBehaviour(225f, 20f, 25f));
		aiSuiciderShipFactory.spawnEntity(new Vector2(-100, -80), 0f, new Vector2(0f, 0f));

		// Spawn suicide squad --> optional

		// End AI creation

		engine.addEntity(planetFactory.createPlanet(new PlanetData(60.0f, 80.0f, 4f, "planet0",
				10f, 40f)));
		engine.addEntity(planetFactory.createPlanet(new PlanetData(-15.0f, 30.0f, 2f, "planet2",
				5f, 24f)));

		// ENTITY CREATION - CAMERA
		Entity cameraEntity = engine.createEntity();

		CameraComponent camComp = engine.createComponent(CameraComponent.class);
		camComp.setCamera(viewport.getCamera());
		camComp.addTrackPoint(playerShip, 1);

		cameraEntity.add(camComp);

		engine.addEntity(cameraEntity);

		// Create projectile factories for spawner
		for (WeaponDataI w : allWeapons) {
			spawnSystem.addFactory(new ProjectileFactory(engine, world, assets, w));
		}

		// TODO: Create ship factories (of ai) for spawner

		// INPUT
		InputFactory inputFactory = new InputFactory();

		UserDataI user = context.getDataService().getUser("SOMEUSER");
		SettingsDataI settings = context.getFileService().loadSettings("Some_Location", user);
		settings.addPlayer(user);
		Map<String, String> keyActionMap = settings.getKeyBinds(user);

		Map<Integer, InputAction> keyActions = inputFactory.createPlayerKeymap(keyActionMap,
				playerShip);
		// 0. Get player 1 - Keyboard assumed for now
		// 1. Get keymap from file service (string: string)
		// 2. Convert to keycode: action

		// For controller,s input is slightly different (but same actions
		// mostly)
		Gdx.input.setInputProcessor(new PlayerInputProcessor(keyActions));

		eventQueue.register(EventEnum.PLAYER_KILLED, new IEventListener() {

			@Override
			public void handle(IEvent event) {
				// TODO: Disable input handling??
			}
		});

		eventQueue.register(EventEnum.DESTROY_ENTITY, new DestroyEntityListener(engine));

		return engine;
	}

}
