package be.howest.twentytwo.parametergame.model.system;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Debug renderer for the Box2D World.
 * 
 * @author Kevin CY Tang
 */
public class PhysicsDebugRenderSystem extends IteratingSystem {
	
	public final static int PRIORITY = 0;

	private final Box2DDebugRenderer renderer;
	private final ShapeRenderer shapes;
	private final World world;
	private final Camera cam;
	
	public PhysicsDebugRenderSystem(World world, Camera cam, ShapeRenderer shapeRenderer) {
		super(Family.all(BodyComponent.class).get(), PRIORITY);
		this.world = world;
		this.cam = cam;
		this.shapes = shapeRenderer;
		renderer = new Box2DDebugRenderer();
	}

	@Override
	public void update(float deltaTime) {
		renderer.render(world, cam.combined);
		shapes.setProjectionMatrix(cam.combined);
		shapes.begin(ShapeType.Line);
		shapes.setColor(Color.RED);
		super.update(deltaTime);
		shapes.end();
	}
	
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		Body body = BodyComponent.MAPPER.get(entity).getBody();
		Vector2 pos = body.getPosition();
		Vector2 vel = body.getLinearVelocity();
		shapes.line(pos.x, pos.y, pos.x + vel.x, pos.y + vel.y);
	}
	
}