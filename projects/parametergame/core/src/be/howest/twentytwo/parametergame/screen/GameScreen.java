package be.howest.twentytwo.parametergame.screen;

import java.util.ArrayList;
import java.util.Collection;

import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.input.PlayerInputProcessor;
import be.howest.twentytwo.parametergame.model.PhysicsBodyEntityListener;
import be.howest.twentytwo.parametergame.model.component.AIBrutalizerComponent;
import be.howest.twentytwo.parametergame.model.component.AIScoutComponent;
import be.howest.twentytwo.parametergame.model.component.AISuiciderComponent;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.CameraComponent;
import be.howest.twentytwo.parametergame.model.component.MovementComponent;
import be.howest.twentytwo.parametergame.model.component.SpriteComponent;
import be.howest.twentytwo.parametergame.model.component.TransformComponent;
import be.howest.twentytwo.parametergame.model.physics.collision.Constants;
import be.howest.twentytwo.parametergame.model.physics.collision.ContactProcessor;
import be.howest.twentytwo.parametergame.model.physics.collision.GravityContactProcessor;
import be.howest.twentytwo.parametergame.model.physics.collision.PlayerContactProcessor;
import be.howest.twentytwo.parametergame.model.physics.events.IPhysicsEvent;
import be.howest.twentytwo.parametergame.model.system.AiSystem;
import be.howest.twentytwo.parametergame.model.system.BackgroundRenderSystem;
import be.howest.twentytwo.parametergame.model.system.CameraSystem;
import be.howest.twentytwo.parametergame.model.system.MovementSystem;
import be.howest.twentytwo.parametergame.model.system.PhysicsRenderSystem;
import be.howest.twentytwo.parametergame.model.system.PhysicsSystem;
import be.howest.twentytwo.parametergame.model.system.RenderSystem;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen extends BaseScreen {

	private World world;
	private PooledEngine engine;
	private Viewport viewport; // Needs to be saved for resizes
	        
    public static Entity mainPlayer = null;
        
	public GameScreen(ScreenContext context, PooledEngine engine, Viewport vp){
		super(context);
		this.engine = engine;
		this.viewport = vp;
		// TODO: Don't need world? --> World should be part of the supplied engine.
		// The constructor below is just for testing.
	}

	public GameScreen(ScreenContext context) {
		super(context);
		initWorld();
		initUI();
	}

	private void initWorld() {
		engine = new PooledEngine(); // NOTE: engine.createEntity() to get the
										// pooled object.
		// TODO/NOTE: Engine needs to be passed to factories for construction

		Collection<IPhysicsEvent> events = new ArrayList<IPhysicsEvent>();

		world = new World(new Vector2(0f, 0f), true); // 0g world
		// world.setContactListener(createContactListener(events));
		ContactProcessor collisionListener = new GravityContactProcessor(events);
		collisionListener.addProcessor(new PlayerContactProcessor(events));
		world.setContactListener(collisionListener);

		// ECS systems
		// TODO: Viewport choice
		// A) Fitviewport = letterboxing (Also a bit easier to debug for atm)

		viewport = new FitViewport(320f, 180f); // Viewport size (in world units)

		/*
		 * B) ScreenViewport = full size without stretching, but shown field is different based on
		 * aspect ratio --> possible balance concern
		 */

		// ScreenViewport sv = new ScreenViewport();
		// sv.setUnitsPerPixel(0.25f);
		// viewport = sv;

		viewport.getCamera().position.x = 50f;
		viewport.getCamera().position.y = 50f;

		RenderSystem renderSys = new RenderSystem(getContext().getSpriteBatch(), viewport);
		BackgroundRenderSystem bgRenderSys = new BackgroundRenderSystem(getContext()
				.getSpriteBatch(), getContext().getAssetManager(), viewport);
		engine.addSystem(new MovementSystem(events));
		engine.addSystem(new PhysicsSystem(world, events));
                engine.addSystem(new AiSystem(events));
		engine.addSystem(new CameraSystem());
		engine.addSystem(bgRenderSys);
		engine.addSystem(renderSys);
		// engine.addSystem(new AISystem());
		engine.addSystem(new PhysicsRenderSystem(world, renderSys.getCamera()));

		engine.addEntityListener(Family.all(BodyComponent.class).get(),
				new PhysicsBodyEntityListener(world));

		// Asset loading here for now
		Gdx.app.log("GameScreen", "Loading assets...");
		getContext().getAssetManager().load("sprites/ships.pack", TextureAtlas.class);
		getContext().getAssetManager().load("sprites/geowars.pack", TextureAtlas.class);
		// getContext().getAssetManager().load("sprites/tiles.pack", TextureAtlas.class);
		// This is done in BackgroundRenderSystem
		getContext().getAssetManager().finishLoading();
		Gdx.app.log("GameScreen", "Asset loading finished!");

		Entity ship = createShip();
                mainPlayer = ship;
		Entity planet = createPlanet();
		Entity camEntity = createCameraEntity(ship, viewport.getCamera());

		engine.addEntity(ship);
		engine.addEntity(planet);
		
		engine.addEntity(createStaticCircle(-5f, -5f, 1f));
		engine.addEntity(createStaticCircle(0, 0, 1f));
		engine.addEntity(createStaticCircle(5f, 5f, 1f));
		engine.addEntity(createStaticCircle(50f, 50f, 1f));
		engine.addEntity(createStaticCircle(100f, 50f, 1f));
		engine.addEntity(createStaticCircle(150f, 50f, 1f));
		engine.addEntity(camEntity);
                
        int numberOfScoutEnemies = 4;
        for(int x = 0; x < numberOfScoutEnemies; x++)
        {
            Entity scoutShip = createAIShip((x - numberOfScoutEnemies/2)* 20, 100, "scouter");
            AIScoutComponent scoutComponent = engine.createComponent(AIScoutComponent.class);
            scoutShip.add(scoutComponent);
            engine.addEntity(scoutShip);           
        }
        
        int numberOfSuiciderEnemies = 4;
        for(int x = 0; x < numberOfSuiciderEnemies; x++)
        {
            Entity aiShip = createAIShip((x - numberOfSuiciderEnemies/2)* 20, 2, "suicider");
            AISuiciderComponent suiciderComponent = engine.createComponent(AISuiciderComponent.class);
            aiShip.add(suiciderComponent);
            engine.addEntity(aiShip);           
        }
        
        int numberOfBrutalizerEnemies = 4;
        for(int x = 0; x < numberOfBrutalizerEnemies; x++)
        {
            Entity brutalizerShip = createAIShip((x - numberOfBrutalizerEnemies/2)* 20, -100, "brutalizer");
            AIBrutalizerComponent brutalizerComponent = engine.createComponent(AIBrutalizerComponent.class);
            brutalizerShip.add(brutalizerComponent);
            engine.addEntity(brutalizerShip);           
        }

		/*
		 * events.add(new GravityPhysicsEvent(planet.getComponent(BodyComponent.class).getBody(),
		 * ship.getComponent( BodyComponent.class).getBody()));
		 */

		// INPUT MAPPING 2
		MovementComponent shipMC = MovementComponent.MAPPER.get(ship);
		Gdx.input.setInputProcessor(new PlayerInputProcessor(shipMC));

	}

	private void initUI() {
		// TODO: UI
	}

	// ///// WELCOME TO THE REFACTOR ZONE, ALL THIS HAS TO BE MOVED SOMEPLACE ELSE //////

	// //// TODO: TESTING ONLY - CREATING ENTITIES //////
	private Entity createShip() {
		Entity ship = engine.createEntity();
		TransformComponent transform = engine.createComponent(TransformComponent.class);
		transform.setPos(new Vector2(40f, 40f));
		transform.setWorldSize(new Vector2(8f, 8f));
		transform.setRotation(0f);
		ship.add(transform);

		MovementComponent moveComponent = engine.createComponent(MovementComponent.class);
		moveComponent.setMaxLinearVelocity(200f);
		moveComponent.setMaxAngularVelocity(100f);
		moveComponent.setLinearAcceleration(100f);
		moveComponent.setAngularAcceleration(50f);
		
		ship.add(moveComponent);

		BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		// bodyDef.fixedRotation = true; --> Should be true for all/player ships?

		bodyDef.position.set(10f, 10f);
		Body rigidBody = world.createBody(bodyDef); // Put in world
		bodyComponent.setBody(rigidBody);

		rigidBody.setLinearDamping(0.1f); // Air resistance type effect
		rigidBody.setAngularDamping(1f);

		CircleShape circle = new CircleShape();
		circle.setRadius(4f);

		// Fixture def with circle
		FixtureDef fixtureDef = new FixtureDef();

		fixtureDef.shape = circle;
		fixtureDef.density = 0.25f;
		fixtureDef.friction = 0.1f;
		fixtureDef.restitution = 0.75f; // = Bounciness

		fixtureDef.filter.categoryBits = Constants.PLAYER_CATEGORY;
		fixtureDef.filter.maskBits = Constants.PLAYER_COLLISION_MASK;

		rigidBody.createFixture(fixtureDef); // Attach fixture to body

		// Cleanup
		circle.dispose();

		bodyComponent.setBody(rigidBody);
		ship.add(bodyComponent);

		SpriteComponent sprite = engine.createComponent(SpriteComponent.class);

		// Texture texture = getContext().getAssetManager().get("mrArrow.png", Texture.class);
		// TextureRegion region = new TextureRegion(texture); // Load the full texture (it's not a
		// sheet)

		TextureAtlas spritesheet = getContext().getAssetManager().get("sprites/ships.pack",
				TextureAtlas.class);
		TextureRegion region = spritesheet.findRegion("recon");

		sprite.setRegion(region);
		ship.add(sprite);
		return ship;
	}


	private Entity createPlanet() {
		Entity planet = engine.createEntity();

		TransformComponent transform = new TransformComponent();
		transform.setPos(new Vector2(0f, 0f));
		transform.setWorldSize(new Vector2(40f, 40f));
		transform.setRotation(0f);
		planet.add(transform);

		BodyComponent bodyComponent = new BodyComponent();

		BodyDef bodyDef = new BodyDef();
		// Planet should be static
		bodyDef.type = BodyDef.BodyType.StaticBody;

		bodyDef.position.set(35f, 25f);
		Body rigidBody = world.createBody(bodyDef); // Put in world
		bodyComponent.setBody(rigidBody);

		CircleShape circle = new CircleShape();
		circle.setRadius(2f);

		// Fixture def with circle
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 5.5f;
		fixtureDef.friction = 0.1f;
		// fixtureDef.restitution = 0.5f; // = Bounciness
		fixtureDef.filter.categoryBits = Constants.PLANET_CATEGORY;
		fixtureDef.filter.maskBits = Constants.PLANET_MASK;

		rigidBody.createFixture(fixtureDef); // Attach fixture to body

		// Gravity fixture --> use with contact listener above
		circle.setRadius(20f);
		fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 0f;
		fixtureDef.isSensor = true;
		fixtureDef.filter.categoryBits = Constants.GRAVITY_CATEGORY;
		fixtureDef.filter.maskBits = Constants.PLANET_MASK;

		rigidBody.createFixture(fixtureDef);

		// Cleanup
		circle.dispose();

		bodyComponent.setBody(rigidBody);
		planet.add(bodyComponent);

		return planet;
	}

	private Entity createStaticCircle(float x, float y, float radius) {
		Entity circleEntity = engine.createEntity();

		TransformComponent transform = new TransformComponent();
		transform.setPos(new Vector2(x, y));
		transform.setWorldSize(new Vector2(2 * radius, 2 * radius));
		transform.setRotation(0f);
		circleEntity.add(transform);

		BodyComponent bodyComponent = new BodyComponent();

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;

		bodyDef.position.set(x, y);
		Body rigidBody = world.createBody(bodyDef); // Put in world
		bodyComponent.setBody(rigidBody);

		CircleShape circle = new CircleShape();
		circle.setRadius(radius);

		// Fixture def with circle
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0.1f;
		fixtureDef.restitution = 0.75f; // = Bounciness

		rigidBody.createFixture(fixtureDef); // Attach fixture to body

		// Cleanup
		circle.dispose();

		circleEntity.add(bodyComponent);

		return circleEntity;
	}

	private Entity createCameraEntity(Entity trackTarget, Camera camera) {
		Entity cameraEntity = engine.createEntity();

		CameraComponent camComp = engine.createComponent(CameraComponent.class);
		camComp.setCamera(camera);
		camComp.addTrackPoint(trackTarget, 1);

		cameraEntity.add(camComp);

		return cameraEntity;
	}
        
        private Entity createAIShip(float posx, float posy, String textureRegion) {
		Entity ship = engine.createEntity();
		TransformComponent transform = engine.createComponent(TransformComponent.class);
		transform.setPos(new Vector2(posx, posy));
		transform.setWorldSize(new Vector2(8f, 8f));
		transform.setRotation(0f);
		ship.add(transform);

		MovementComponent moveComponent = engine.createComponent(MovementComponent.class);
		moveComponent.setMaxLinearVelocity(25f);
		moveComponent.setMaxAngularVelocity(20f);
		moveComponent.setLinearAcceleration(7.5f);
		moveComponent.setAngularAcceleration(10f);
		ship.add(moveComponent);
                
                

		BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		// bodyDef.fixedRotation = true; --> Should be true for all/player ships?
                  
		bodyDef.position.set(posx, posy);
		Body rigidBody = world.createBody(bodyDef); // Put in world
		bodyComponent.setBody(rigidBody);

		rigidBody.setLinearDamping(0.1f); // Air resistance type effect
		rigidBody.setAngularDamping(0.5f);

		CircleShape circle = new CircleShape();
		circle.setRadius(4f);

		// Fixture def with circle
		FixtureDef fixtureDef = new FixtureDef();

		fixtureDef.shape = circle;
		fixtureDef.density = 0.25f;
		fixtureDef.friction = 0.1f;
		fixtureDef.restitution = 0.75f; // = Bounciness

		fixtureDef.filter.categoryBits = Constants.ENEMY_CATEGORY;
		fixtureDef.filter.maskBits = Constants.ENEMY_COLLISION_MASK;

		rigidBody.createFixture(fixtureDef); // Attach fixture to body

		// Cleanup
		circle.dispose();

		bodyComponent.setBody(rigidBody);
		ship.add(bodyComponent);

		SpriteComponent sprite = engine.createComponent(SpriteComponent.class);

		// getContext().getAssetManager().load("mrArrow.png", Texture.class);

		getContext().getAssetManager().load("sprites/AI.pack", TextureAtlas.class);
		getContext().getAssetManager().finishLoading();

		// Texture texture = getContext().getAssetManager().get("mrArrow.png", Texture.class);
		// TextureRegion region = new TextureRegion(texture); // Load the full texture (it's not a
		// sheet)

		TextureAtlas spritesheet = getContext().getAssetManager().get("sprites/AI.pack",
				TextureAtlas.class);
		TextureRegion region = spritesheet.findRegion(textureRegion);

		sprite.setRegion(region);
		ship.add(sprite);
		return ship;
	}

	// //// /ENTITIES //////

	// //// YOU ARE NOW LEAVING THE REFACTOR ZONE, I HOPE YOU ENJOYED YOUR STAY //////

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		engine.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void pause() {
		// TODO: pause boolean --> stop engine update?

	}

	@Override
	public void resume() {
		// TODO: See pause();
	}

	@Override
	public void hide() {
		// TODO: GameScreen - hide()
	}

	@Override
	public void dispose() {
		super.dispose();
		world.dispose();

	}

}
