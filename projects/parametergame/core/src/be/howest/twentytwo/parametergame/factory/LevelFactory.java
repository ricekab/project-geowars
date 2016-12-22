package be.howest.twentytwo.parametergame.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import be.howest.twentytwo.parametergame.ParameterGame;
import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.dataTypes.BoxDataI;
import be.howest.twentytwo.parametergame.dataTypes.ClusterDataI;
import be.howest.twentytwo.parametergame.dataTypes.EnemyDataI;
import be.howest.twentytwo.parametergame.dataTypes.LevelDataI;
import be.howest.twentytwo.parametergame.dataTypes.PlanetData;
import be.howest.twentytwo.parametergame.dataTypes.PlanetDataI;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipData;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.SettingsDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.SpawnPoolDataI;
import be.howest.twentytwo.parametergame.dataTypes.UserDataI;
import be.howest.twentytwo.parametergame.dataTypes.WeaponDataI;
import be.howest.twentytwo.parametergame.input.PlayerInputProcessor;
import be.howest.twentytwo.parametergame.input.actions.InputAction;
import be.howest.twentytwo.parametergame.model.PhysicsBodyEntityListener;
import be.howest.twentytwo.parametergame.model.ai.BasicAIMoveBehaviour;
import be.howest.twentytwo.parametergame.model.ai.BasicAIShootBehaviour;
import be.howest.twentytwo.parametergame.model.ai.IAIMoveBehaviour;
import be.howest.twentytwo.parametergame.model.ai.IAIShootBehaviour;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.CameraComponent;
import be.howest.twentytwo.parametergame.model.event.EventEnum;
import be.howest.twentytwo.parametergame.model.event.EventQueue;
import be.howest.twentytwo.parametergame.model.event.IEvent;
import be.howest.twentytwo.parametergame.model.event.listener.DestroyEntityListener;
import be.howest.twentytwo.parametergame.model.event.listener.IEventListener;
import be.howest.twentytwo.parametergame.model.physics.collision.BaseContactProcessor;
import be.howest.twentytwo.parametergame.model.physics.collision.GravityContactProcessor;
import be.howest.twentytwo.parametergame.model.physics.collision.PlanetContactProcessor;
import be.howest.twentytwo.parametergame.model.physics.collision.PlayerBulletContactProcessor;
import be.howest.twentytwo.parametergame.model.physics.collision.PlayerContactProcessor;
import be.howest.twentytwo.parametergame.model.physics.message.IPhysicsMessage;
import be.howest.twentytwo.parametergame.model.spawn.message.ISpawnMessage;
import be.howest.twentytwo.parametergame.model.system.AIMovementSystem;
import be.howest.twentytwo.parametergame.model.system.AIShootSystem;
import be.howest.twentytwo.parametergame.model.system.BackgroundRenderSystem;
import be.howest.twentytwo.parametergame.model.system.CameraSystem;
import be.howest.twentytwo.parametergame.model.system.MovementSystem;
import be.howest.twentytwo.parametergame.model.system.PhysicsDebugRenderSystem;
import be.howest.twentytwo.parametergame.model.system.PhysicsSystem;
import be.howest.twentytwo.parametergame.model.system.RenderSystem;
import be.howest.twentytwo.parametergame.model.system.ShapeRenderSystem;
import be.howest.twentytwo.parametergame.model.system.SpawnSystem;
import be.howest.twentytwo.parametergame.model.system.TimerSystem;
import be.howest.twentytwo.parametergame.model.system.UISystem;
import be.howest.twentytwo.parametergame.model.system.WeaponSystem;
import be.howest.twentytwo.parametergame.service.db.IDataService;
import be.howest.twentytwo.parametergame.ui.data.LoadoutSelectionData;
import be.howest.twentytwo.parametergame.ui.message.UIMessage;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

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

	public PooledEngine createWorld(ScreenContext context, Viewport viewport,
			EventQueue eventQueue, String levelName, LoadoutSelectionData selections) {
		LevelDataI levelData = context.getFileService().loadLevel(levelName);
		IDataService dataService = context.getDataService();
		AssetManager assets = context.getAssetManager();
		UserDataI user = context.getUser();

		// ENGINE
		PooledEngine engine = new PooledEngine();

		// MESSAGING OBJECTS
		Collection<IPhysicsMessage> physicsMessageQueue = new ArrayList<IPhysicsMessage>();
		Collection<ISpawnMessage> spawnMessageQueue = new ArrayList<ISpawnMessage>();
		Collection<UIMessage> uiMessageQueue = new ArrayList<UIMessage>();

		// PHYSICS INIT
		World world = new World(new Vector2(0f, 0f), true);
		world.setContactListener(getCollisionChain(eventQueue, physicsMessageQueue));
		engine.addEntityListener(Family.all(BodyComponent.class).get(),
				new PhysicsBodyEntityListener(world));

		// UI Init
		Stage uiStage = createUI();

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
		engine.addSystem(spawnSystem);
		engine.addSystem(new CameraSystem());
		engine.addSystem(bgRenderSys);
		engine.addSystem(renderSys);
		engine.addSystem(new ShapeRenderSystem(context.getShapeRenderer(), viewport));
		engine.addSystem(new TimerSystem(eventQueue));
		engine.addSystem(new AIMovementSystem());
		engine.addSystem(new AIShootSystem());
		engine.addSystem(new UISystem(uiMessageQueue, uiStage));
		// engine.addSystem(new AISystem());
		// Sound, Animation, ...

		if(ParameterGame.DEBUG_ENABLED) {
			engine.addSystem(new PhysicsDebugRenderSystem(world, renderSys.getCamera(), context
					.getShapeRenderer()));
		}

		// ENTITY CREATION
		// Needed to prepare projectile factories
		Set<WeaponDataI> allWeapons = new HashSet<WeaponDataI>();

		// PLAYER
		PlayerShipDataI playerShipData = selections.getPlayerShip();
		ShipDataI shipData = playerShipData.getShipData();
		playerShipData.setDrones(selections.getDrones());
		allWeapons.addAll(shipData.getWeapons());

		PlayerShipFactory playerFactory = new PlayerShipFactory(engine, world, assets,
				playerShipData);
		BoxDataI spawnBox = levelData.getSpawnBox();
		Entity playerShip = playerFactory.createPlayerShip(
				spawnBox.getXCoord() + (float) Math.random() * spawnBox.getWidth(),
				spawnBox.getYCoord() + (float) Math.random() * spawnBox.getHeight());

		engine.addEntity(playerShip);

		Body playerBody = BodyComponent.MAPPER.get(playerShip).getBody();

		// PLANETS
		PlanetFactory planetFactory = new PlanetFactory(engine, world, assets);

		for (PlanetDataI pdata : levelData.getPlanets()) {
			engine.addEntity(planetFactory.createPlanet(pdata));
		}

		// ENEMIES / AI FACTORIES
		Collection<String> enemyNames = new HashSet<String>();
		Collection<ShipDataI> enemyShips = new HashSet<ShipDataI>();

		Queue<SpawnPoolDataI> spawnPools = levelData.getSpawnPools();
		Queue<SpawnPoolDataI> tempPools = new LinkedList<SpawnPoolDataI>(spawnPools);
		while (!tempPools.isEmpty()) {
			SpawnPoolDataI pool = tempPools.poll();
			for (ClusterDataI cluster : pool.getAllClusters()) {
				String name = cluster.getEnemyName();
				enemyNames.add(name);
			}
		}

		Collection<EnemyDataI> enemies = dataService.getEnemies(enemyNames
				.toArray(new String[enemyNames.size()]));

		for (EnemyDataI enemy : enemies) {
			// Adding all weapons for projectile factories
			allWeapons.addAll(enemy.getShipData().getWeapons());
		}

		// EnemyDataI enemy = enemies.iterator().next();
		// // Spawn scout ship
		// AIShipFactory aiScoutShipFactory = new AIShipFactory(engine, world, assets,
		// enemy.getShipData(), playerBody, new BasicAIMoveBehaviour(75f),
		// new BasicAIShootBehaviour(60, 80));// adjust for range
		//
		// // Spawn brutalizer ship
		// AIShipFactory aiBrutalizerShipFactory = new AIShipFactory(engine, world, assets,
		// enemy.getShipData(), playerBody, new BasicAIMoveBehaviour(50f), // adjust for range
		// new BasicAIShootBehaviour(120, 50)); // Fires every 4seconds
		// // (120/30), 50 = range
		// aiBrutalizerShipFactory.spawnEntity(new Vector2(-60, -10), 0f, new Vector2(0f, 0f));
		//
		// // Spawn obstacle
		// AIShipFactory aiObstacleShipFactory = new AIShipFactory(engine, world, assets,
		// enemy.getShipData(), playerBody);
		// aiObstacleShipFactory.spawnEntity(new Vector2(-80, -40), 0f, new Vector2(0f, 0f));
		//
		// // Spawn suidicer
		// AIShipFactory aiSuiciderShipFactory = new AIShipFactory(engine, world, assets,
		// enemy.getShipData(), playerBody, new BasicAIMoveBehaviour(5f)); // adjust for range
		// aiSuiciderShipFactory.spawnEntity(new Vector2(-100, -80), 0f, new Vector2(0f, 0f));

		// Spawn suicide squad --> optional

		// End AI creation

		// ENTITY CREATION - CAMERA
		Entity cameraEntity = engine.createEntity();

		CameraComponent camComp = engine.createComponent(CameraComponent.class);
		camComp.setCamera(viewport.getCamera());
		camComp.addTrackPoint(playerShip, 1);

		cameraEntity.add(camComp);

		engine.addEntity(cameraEntity);

		// Create projectile factories for spawner
		for (WeaponDataI wpn : allWeapons) {
			spawnSystem.addFactory(new ProjectileFactory(engine, world, assets, wpn));
		}
		
		AIMoveBehaviourFactory moveFactory = new AIMoveBehaviourFactory();
		AIShootBehaviourFactory shootFactory = new AIShootBehaviourFactory();
		for(EnemyDataI enemy : enemies){
			String behaviourString = enemy.getBehaviour();
			// TODO: Convert behaviour string into concrete behaviours
			IAIMoveBehaviour move = moveFactory.createBehaviour(behaviourString);
			IAIShootBehaviour shoot = shootFactory.createBehaviour(behaviourString);
			spawnSystem.addFactory(new AIShipFactory(engine, world, assets, shipData, selections.getDifficulty(), playerBody, move, shoot));
		}

		// INPUT
		InputFactory inputFactory = new InputFactory();

		SettingsDataI settings = context.getFileService().loadSettings("settings.ini", user);
		settings.addPlayer(user);
		Map<String, String> keyActionMap = settings.getKeyBinds(user);

		// 0. Get player 1 - Keyboard assumed for now
		// 1. Get keymap from file service (string: string)
		// 2. Convert to keycode: action

		// For controller,s input is slightly different (but same actions
		// mostly)
		Map<Integer, InputAction> keyActions = inputFactory.createPlayerKeymap(keyActionMap,
				playerShip);
		Gdx.input.setInputProcessor(new PlayerInputProcessor(keyActions));

		XBOneControllerInputFactory cif = new XBOneControllerInputFactory();
		Controllers.addListener(cif.createControllerListener(playerShip));

		eventQueue.register(EventEnum.PLAYER_KILLED, new IEventListener() {

			@Override
			public void handle(IEvent event) {
				// TODO: Disable input handling??
			}
		});

		eventQueue.register(EventEnum.DESTROY_ENTITY, new DestroyEntityListener(engine));

		return engine;
	}

	private ContactListener getCollisionChain(EventQueue events,
			Collection<IPhysicsMessage> physicsMessages) {
		BaseContactProcessor collisionListener = new GravityContactProcessor(events,
				physicsMessages);
		collisionListener.addProcessor(new PlayerContactProcessor(events, physicsMessages))
				.addProcessor(new PlayerBulletContactProcessor(events, physicsMessages))
				.addProcessor(new PlanetContactProcessor(events, physicsMessages));
		// TODO: Add other contact listeners here.
		return collisionListener;
	}

	private Stage createUI() {
		Stage stage = new Stage();
		Gdx.app.error("LevelFactory", "UI NOT IMPLEMENTED");
		return stage;
	}
}
