package be.howest.twentytwo.parametergame.model.system;

import be.howest.twentytwo.parametergame.model.component.SpriteComponent;
import be.howest.twentytwo.parametergame.model.component.TransformComponent;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Responsible for rendering sprites onto the screen.
 */
public class RenderSystem extends IteratingSystem {

	private Viewport viewport;
	private SpriteBatch batch;

	public RenderSystem(SpriteBatch batch, Viewport viewport) {
		super(Family.all(TransformComponent.class, SpriteComponent.class).get());
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
		TransformComponent transform = TransformComponent.MAPPER.get(entity);
		SpriteComponent spriteComp = SpriteComponent.MAPPER.get(entity);

		getCamera().update(); // TODO: Might not be needed.
		batch.setProjectionMatrix(getCamera().combined);
		batch.begin();
		TextureRegion region = spriteComp.getRegion();

		float width = region.getRegionWidth();
		float height = region.getRegionHeight();
		float originX = width / 2f;
		float originY = height / 2f;

		batch.draw(region, transform.getPos().x, transform.getPos().y, originX, originY, width, height, 1f, 1f,
				transform.getRotation());
		batch.end();
	}

	public Viewport getViewport() {
		return viewport;
	}

	public Camera getCamera() {
		return viewport.getCamera();
	}

}
