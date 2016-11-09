package be.howest.twentytwo.parametergame.screen;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
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
	
	private void initWorld(){
		engine = new PooledEngine();	// NOTE: engine.createEntity() to get the pooled object.
		world = new World(new Vector2(0f , 0f), true);	// 0g world
	
		// ECS systems
		// TODO: This should be in the rendersystem I guess?
		Viewport viewport = new FitViewport(50f, 50f); // Viewport size (in world units)
		Camera cam = viewport.getCamera();
		
		engine.addSystem(new PhysicsSystem(world));
		engine.addSystem(new PhysicsRenderSystem(world, cam));
		
		engine.addEntity(createShip());
	}
	
	private void initUI(){
		// TODO
	}

	////// TESTING ONLY - CREATING ENTITIES //////
	private Entity createShip(){
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
        Body rigidBody = world.createBody(bodyDef);	// Put in world 
        bodyComponent.setBody(rigidBody);
        // rigidBody.applyAngularImpulse(50f, true);

        CircleShape circle = new CircleShape();
        circle.setRadius(3f);

        // Fixture def with circle
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 5f;
        fixtureDef.friction = 0.1f;
        fixtureDef.restitution = 0.5f; // = Bounciness

        rigidBody.createFixture(fixtureDef);	// Attach fixture to body

        // Cleanup
        circle.dispose();
		
		bodyComponent.setBody(rigidBody);
		ship.add(bodyComponent);
		return ship;
	}
	
	private Entity createPlanet(){
		return null;
	}
	
	private Entity createFloor(){
		return null;
	}
	////// /ENTITIES //////
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		engine.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
