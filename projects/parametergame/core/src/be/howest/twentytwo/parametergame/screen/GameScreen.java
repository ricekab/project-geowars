package be.howest.twentytwo.parametergame.screen;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import be.howest.twentytwo.parametergame.ParameterGame;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.TransformComponent;
import be.howest.twentytwo.parametergame.model.system.PhysicsRenderSystem;
import be.howest.twentytwo.parametergame.model.system.PhysicsSystem;

public class GameScreen extends BaseScreen {

	private World world;
	private PooledEngine engine;

	public GameScreen(ParameterGame game) {
		super(game);
		initWorld();
		initUI();
	}

	///// TEMP --> rendersystem /////
	Camera tempCam;
	Viewport vp;
	///// ENDTEMP /////

	private void initWorld() {
		engine = new PooledEngine(); // NOTE: engine.createEntity() to get the
										// pooled object.
		world = new World(new Vector2(0f, 0f), true); // 0g world

		// ECS systems
		// TODO: This should be in the rendersystem I guess?
		Viewport viewport = new FitViewport(50f, 50f); // Viewport size (in
														// world units)
		vp = viewport;
		Camera cam = viewport.getCamera();
		tempCam = cam;

		// TODO: add render system before others
		engine.addSystem(new PhysicsRenderSystem(world, cam));
		engine.addSystem(new PhysicsSystem(world));

		engine.addEntity(createShip());
		engine.addEntity(createPlanet());
		engine.addEntity(createFloor());
	}

	private void initUI() {
		// TODO
	}

	////// TESTING ONLY - CREATING ENTITIES //////
	private Entity createShip() {
		Entity ship = engine.createEntity();
		TransformComponent transform = new TransformComponent();
		transform.setPosition(new Vector2(40f, 40f));
		transform.setScale(new Vector2(1f, 1f));
		transform.setRotation(0f);
		ship.add(transform);
		BodyComponent bodyComponent = new BodyComponent();

		BodyDef bodyDef = new BodyDef();
		// Planet should be static
		bodyDef.type = BodyDef.BodyType.DynamicBody;

		bodyDef.position.set(40f, 40f);
		Body rigidBody = world.createBody(bodyDef); // Put in world
		bodyComponent.setBody(rigidBody);
		rigidBody.applyForceToCenter(new Vector2(0f, -2500f), true);

		CircleShape circle = new CircleShape();
		circle.setRadius(4f);

		// Fixture def with circle
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0.1f;
		fixtureDef.restitution = 0.5f; // = Bounciness

		rigidBody.createFixture(fixtureDef); // Attach fixture to body

		// Cleanup
		circle.dispose();

		bodyComponent.setBody(rigidBody);
		ship.add(bodyComponent);
		return ship;
	}

	private Entity createPlanet() {
		Entity planet = engine.createEntity();

		TransformComponent transform = new TransformComponent();
		transform.setPosition(new Vector2(0f, 0f));
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

		PolygonShape box = new PolygonShape();
		box.setAsBox(100f, 5f);

		CircleShape circle = new CircleShape();
		circle.setRadius(6f);

		// Fixture def with circle
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 10f;
		fixtureDef.friction = 0.1f;
		//fixtureDef.restitution = 0.5f; // = Bounciness

		rigidBody.createFixture(fixtureDef); // Attach fixture to body

		// Cleanup
		circle.dispose();

		bodyComponent.setBody(rigidBody);
		planet.add(bodyComponent);

		return planet;
	}

	private Entity createFloor() {
		Entity floor = engine.createEntity();

		TransformComponent transform = new TransformComponent();
		transform.setPosition(new Vector2(0f, 0f));
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
	////// /ENTITIES //////

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(255f, 255f, 255f, 1f);
		Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
		getGame().batch.setProjectionMatrix(tempCam.combined);
		tempCam.position.set(40f, 40f, 0f);
		tempCam.update();

		engine.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		vp.update(width, height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
