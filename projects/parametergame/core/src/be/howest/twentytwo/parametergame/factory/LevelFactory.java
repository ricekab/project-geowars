package be.howest.twentytwo.parametergame.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import be.howest.twentytwo.parametergame.ParameterGame;
import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.dataTypes.LevelDataI;
import be.howest.twentytwo.parametergame.dataTypes.PlanetData;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipData;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipData;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.input.PlayerInputProcessor;
import be.howest.twentytwo.parametergame.input.actions.InputAction;
import be.howest.twentytwo.parametergame.model.PhysicsBodyEntityListener;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.CameraComponent;
import be.howest.twentytwo.parametergame.model.event.EventQueue;
import be.howest.twentytwo.parametergame.model.physics.collision.ContactProcessor;
import be.howest.twentytwo.parametergame.model.physics.collision.GravityContactProcessor;
import be.howest.twentytwo.parametergame.model.physics.collision.PlayerContactProcessor;
import be.howest.twentytwo.parametergame.model.physics.message.IPhysicsMessage;
import be.howest.twentytwo.parametergame.model.system.AiSystem;
import be.howest.twentytwo.parametergame.model.system.BackgroundRenderSystem;
import be.howest.twentytwo.parametergame.model.system.CameraSystem;
import be.howest.twentytwo.parametergame.model.system.MovementSystem;
import be.howest.twentytwo.parametergame.model.system.PhysicsRenderSystem;
import be.howest.twentytwo.parametergame.model.system.PhysicsSystem;
import be.howest.twentytwo.parametergame.model.system.RenderSystem;
import be.howest.twentytwo.parametergame.model.system.WeaponSystem;
import be.howest.twentytwo.parametergame.service.db.IDataService;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Builds up the physics {@link World} as well as all populates the ECS engine
 * with level-defined entities.
 * 
 * Note -- The ECS engine can listen for new entities added and add their body
 * to the world. This simplifies adding entities to the engine without having to
 * pass the World around everywhere just in case it's needed. The downside (?)
 * is that the body definition is required as well.
 * 
 * --> Ended up opting for factories having access to the World object. They're
 * responsibly for creating the object in its entirety.
 * 
 * Logically, it makes sense for most of the builders/factories to keep a copy
 * of body def and fixture def since they'll make a lot of copies. This
 * basically becomes flyweight-esque.
 *
 * TODO: Fix docs
 */
public class LevelFactory {

	public LevelFactory() {
	}

	public PooledEngine createWorld(ScreenContext context, Viewport viewport, EventQueue eventQueue, String levelName) {
		LevelDataI levelData = context.getFileService().loadLevel(levelName);

		AssetManager assets = context.getAssetManager();

		// ENGINE
		PooledEngine engine = new PooledEngine();

		// MESSAGING OBJECTS
		Collection<IPhysicsMessage> physicsMessageQueue = new ArrayList<IPhysicsMessage>();

		// PHYSICS INIT
		World world = new World(new Vector2(0f, 0f), true);

		ContactProcessor collisionListener = new GravityContactProcessor(eventQueue, physicsMessageQueue);
		collisionListener.addProcessor(new PlayerContactProcessor(eventQueue, physicsMessageQueue));
		// TODO: Add other contact listeners here.

		world.setContactListener(collisionListener);

		// ENTITY FACTORIES
		PlayerShipFactory playerFactory = new PlayerShipFactory();
		PlanetFactory planetFactory = new PlanetFactory();

		// SYSTEMS
		RenderSystem renderSys = new RenderSystem(context.getSpriteBatch(), viewport);
		BackgroundRenderSystem bgRenderSys = new BackgroundRenderSystem(context.getSpriteBatch(), assets, viewport);
		engine.addSystem(new MovementSystem(physicsMessageQueue));
		engine.addSystem(new WeaponSystem(physicsMessageQueue));
		engine.addSystem(new PhysicsSystem(world, physicsMessageQueue));
		engine.addSystem(new AiSystem(physicsMessageQueue));
		engine.addSystem(new CameraSystem());
		engine.addSystem(bgRenderSys);
		engine.addSystem(renderSys);
		// engine.addSystem(new AISystem());
		// Sound, Animation, ...
		if (ParameterGame.DEBUG_ENABLED) {
			engine.addSystem(new PhysicsRenderSystem(world, renderSys.getCamera()));
		}

		engine.addEntityListener(Family.all(BodyComponent.class).get(), new PhysicsBodyEntityListener(world));

		// ENTITY CREATION
		IDataService dataService = context.getDataService();

		Collection<ShipDataI> ships = dataService.getShips(dataService.getUser("TEST"));
		if (ships.isEmpty()) {
			Gdx.app.error("LevelFactory", "ERR: NO SHIPS FOR USER");
		}
		// TODO: Currently just selecting a random ship.
		ShipDataI shipData = ships.iterator().next();
		Gdx.app.debug("LevelF", String.format("Weapons # %d", shipData.getWeapons().size()));
		PlayerShipDataI psData = new PlayerShipData(shipData);

		// TODO: SHIP WORLD SIZE from where?
		Entity playerShip = playerFactory.createPlayerShip(engine, world, assets, psData, 8.0f, 8.0f, 5.0f, 5.0f);

		engine.addEntity(playerShip);

		engine.addEntity(planetFactory.createPlanet(engine, world, assets,
				new PlanetData(60.0f, 80.0f, 4f, "planet01", 10f, 40f)));

		engine.addEntity(planetFactory.createPlanet(engine, world, assets,
				new PlanetData(-15.0f, 30.0f, 2f, "planet02", 10f, 24f)));

		// ENTITY CREATION - CAMERA
		Entity cameraEntity = engine.createEntity();

		CameraComponent camComp = engine.createComponent(CameraComponent.class);
		camComp.setCamera(viewport.getCamera());
		camComp.addTrackPoint(playerShip, 1);

		cameraEntity.add(camComp);

		engine.addEntity(cameraEntity);

		// INPUT

		InputFactory inputFactory = new InputFactory();

		Map<String, String> keyActionMap = context.getFileService().loadSettings("Some_Location", context.getDataService().getUser("SOMEUSER"))
				.getKeyBinds(context.getDataService().getUser("SOMEUSER"));

		Map<Integer, InputAction> keyActions = inputFactory.createPlayerKeymap(keyActionMap, playerShip);
		// 0. Get player 1 - Keyboard assumed for now
		// 1. Get keymap from file service (string: string)
		// 2. Convert to keycode: action

		// For controller,s input is slightly different (but same actions
		// mostly)
		Gdx.input.setInputProcessor(new PlayerInputProcessor(keyActions));

		return engine;
	}

}
