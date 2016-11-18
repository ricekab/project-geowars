package be.howest.twentytwo.parametergame.screen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.input.TestInputProcessor;
import be.howest.twentytwo.parametergame.model.PhysicsBodyEntityListener;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.SpriteComponent;
import be.howest.twentytwo.parametergame.model.component.TransformComponent;
import be.howest.twentytwo.parametergame.model.physics.collision.Constants;
import be.howest.twentytwo.parametergame.model.physics.collision.GravityContactProcessor;
import be.howest.twentytwo.parametergame.model.physics.events.IPhysicsEvent;
import be.howest.twentytwo.parametergame.model.physics.events.LinearForceEvent;
import be.howest.twentytwo.parametergame.model.physics.events.TorqueEvent;
import be.howest.twentytwo.parametergame.model.system.PhysicsRenderSystem;
import be.howest.twentytwo.parametergame.model.system.PhysicsSystem;
import be.howest.twentytwo.parametergame.model.system.RenderSystem;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen extends BaseScreen {

	private World world;
	private PooledEngine engine;
	private Viewport viewport; // Needs to be saved for resizes

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
		ContactListener collisionListener = new GravityContactProcessor(events);
		world.setContactListener(collisionListener);

		// ECS systems
		// TODO: Viewport choice
		// A) Fitviewport = letterboxing (Also a bit easier to debug for atm)
		viewport = new FitViewport(100f, 100f); // Viewport size (in world units)
		/*
		 * B) ScreenViewport = full size without stretching, but shown field is different based on
		 * aspect ratio --> possible balance concern
		 */
		/*
		 * ScreenViewport sv = new ScreenViewport(); sv.setUnitsPerPixel(0.2f); // Note: Real value
		 * should probably be higher? Depends on our units. viewport = sv;
		 */
		viewport.getCamera().translate(25f, 25f, 0f);

		RenderSystem renderSys = new RenderSystem(getContext().getSpriteBatch(), viewport);

		engine.addSystem(new PhysicsSystem(world, events));
		engine.addSystem(renderSys);
		engine.addSystem(new PhysicsRenderSystem(world, renderSys.getCamera()));

		engine.addEntityListener(Family.all(BodyComponent.class).get(),
				new PhysicsBodyEntityListener(world));

		Entity ship = createShip();
		Entity planet = createPlanet();

		engine.addEntity(ship);
		engine.addEntity(planet);
		engine.addEntity(createFloor());
		engine.addEntity(createStaticCircle(-5f, -5f, 1f));
		engine.addEntity(createStaticCircle(0, 0, 1f));
		engine.addEntity(createStaticCircle(5f, 5f, 1f));
		engine.addEntity(createStaticCircle(50f, 50f, 1f));

		/*
		 * events.add(new GravityPhysicsEvent(planet.getComponent(BodyComponent.class).getBody(),
		 * ship.getComponent( BodyComponent.class).getBody()));
		 */

		// INPUT MAPPING TEST
		Body shipBody = BodyComponent.MAPPER.get(ship).getBody();

		// NOTE: This is broken due to changes in event handling. This will be scrapped soon.
		Map<Integer, IPhysicsEvent> keyMap = new HashMap<Integer, IPhysicsEvent>();
		keyMap.put(Keys.Z, new LinearForceEvent(shipBody, 1000f));
		keyMap.put(Keys.S, new LinearForceEvent(shipBody, -500f));
		keyMap.put(Keys.Q, new TorqueEvent(shipBody, 500f));
		keyMap.put(Keys.D, new TorqueEvent(shipBody, -500f));
		Gdx.input.setInputProcessor(new TestInputProcessor(keyMap));
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
		transform.setScale(new Vector2(1f, 1f));
		transform.setRotation(0f);
		ship.add(transform);

		BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		// bodyDef.fixedRotation = true; --> Should be true for all/player ships?

		bodyDef.position.set(40f, 45f);
		Body rigidBody = world.createBody(bodyDef); // Put in world
		bodyComponent.setBody(rigidBody);
		rigidBody.applyForceToCenter(new Vector2(0f, -500), true);

		rigidBody.setLinearDamping(0.1f); // Air resistance type effect

		CircleShape circle = new CircleShape();
		circle.setRadius(2.5f);

		// Fixture def with circle
		FixtureDef fixtureDef = new FixtureDef();

		fixtureDef.shape = circle;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0.1f;
		fixtureDef.restitution = 0.75f; // = Bounciness

		fixtureDef.filter.categoryBits = Constants.PLAYER_CATEGORY;
		fixtureDef.filter.maskBits = Constants.PLAYER_MASK;

		rigidBody.createFixture(fixtureDef); // Attach fixture to body

		// Cleanup
		circle.dispose();

		bodyComponent.setBody(rigidBody);
		ship.add(bodyComponent);

		SpriteComponent sprite = engine.createComponent(SpriteComponent.class);

		getContext().getAssetManager().load("mrArrow.png", Texture.class);
		getContext().getAssetManager().finishLoading();
		Texture texture = getContext().getAssetManager().get("mrArrow.png", Texture.class);
		TextureRegion region = new TextureRegion(texture); // Load the full texture (it's not a
															// sheet)

		sprite.setRegion(region);
		ship.add(sprite);
		return ship;
	}

	private Entity createPlanet() {
		Entity planet = engine.createEntity();

		TransformComponent transform = new TransformComponent();
		transform.setPos(new Vector2(0f, 0f));
		transform.setScale(new Vector2(1f, 1f));
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
		circle.setRadius(4f);

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
		circle.setRadius(12f);
		fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 0f;
		fixtureDef.isSensor = true;
		fixtureDef.filter.categoryBits = Constants.GRAVITY_CATEGORY;
		// fixtureDef.filter.maskBits = Constants.PLANET_CATEGORY; // Breaks the sensor somehow

		rigidBody.createFixture(fixtureDef);

		// Cleanup
		circle.dispose();

		bodyComponent.setBody(rigidBody);
		planet.add(bodyComponent);

		return planet;
	}

	private Entity createFloor() {
		Entity floor = engine.createEntity();

		TransformComponent transform = new TransformComponent();
		transform.setPos(new Vector2(0f, 0f));
		transform.setScale(new Vector2(1f, 1f));
		transform.setRotation(0f);
		floor.add(transform);

		BodyComponent bodyComponent = new BodyComponent();

		BodyDef bodyDef = new BodyDef();
		// Planet should be static
		bodyDef.type = BodyDef.BodyType.StaticBody;

		bodyDef.position.set(10f, 10f);
		Body rigidBody = world.createBody(bodyDef); // Put in world
		bodyComponent.setBody(rigidBody);

		PolygonShape box = new PolygonShape();
		box.setAsBox(100f, 5f);

		// Fixture def with circle
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = box;
		fixtureDef.density = 5f;
		fixtureDef.friction = 0.5f;
		fixtureDef.restitution = 0.5f; // = Bounciness

		rigidBody.createFixture(fixtureDef); // Attach fixture to body

		// Cleanup
		box.dispose();

		bodyComponent.setBody(rigidBody);
		floor.add(bodyComponent);

		return floor;
	}

	private Entity createStaticCircle(float x, float y, float radius) {
		Entity circleEntity = engine.createEntity();

		TransformComponent transform = new TransformComponent();
		transform.setPos(new Vector2(x, y));
		transform.setScale(new Vector2(1f, 1f));
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
		world.dispose();

	}

}
