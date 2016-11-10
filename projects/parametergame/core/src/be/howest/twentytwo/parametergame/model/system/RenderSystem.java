package be.howest.twentytwo.parametergame.model.system;

import be.howest.twentytwo.parametergame.model.component.SpriteComponent;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Responsible for rendering sprites onto the screen.
 */
public class RenderSystem extends IteratingSystem {

	private Viewport viewport;
	private SpriteBatch batch;

	// TODO: Should I just move mappers to some other static class, maybe as part of the component?
	private final ComponentMapper<SpriteComponent> SPRITE_MAPPER = ComponentMapper.getFor(SpriteComponent.class);

	public RenderSystem(SpriteBatch batch, Viewport viewport) {
		super(Family.all(SpriteComponent.class).get());
		this.batch = batch;
		this.viewport = viewport;
	}
	
	@Override
	public void update(float deltaTime) {
		Gdx.gl.glClearColor(255f, 255f, 255f, 1f);
		Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
		
		super.update(deltaTime);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		SpriteComponent spriteComp = SPRITE_MAPPER.get(entity);
		batch.begin();
		// TODO: Draw based on component data
		// batch.draw(...);
		batch.end();
	}

	public Viewport getViewport() {
		return viewport;
	}

	public Camera getCamera() {
		return viewport.getCamera();
	}

}
