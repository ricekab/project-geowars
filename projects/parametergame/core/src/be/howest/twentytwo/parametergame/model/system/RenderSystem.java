package be.howest.twentytwo.parametergame.model.system;

import be.howest.twentytwo.parametergame.model.component.SpriteComponent;
import be.howest.twentytwo.parametergame.model.component.TransformComponent;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Responsible for rendering sprites onto the screen.
 */
public class RenderSystem extends IteratingSystem {

	public final static int PRIORITY = 0;

	public final static float PIXELS_PER_METER = 16f;
	public final static float METERS_PER_PIXEL = 1f / PIXELS_PER_METER;

	private Viewport viewport;
	private SpriteBatch batch;

	public RenderSystem(SpriteBatch batch, Viewport viewport) {
		super(Family.all(TransformComponent.class, SpriteComponent.class).get(), PRIORITY);
		this.batch = batch;
		this.viewport = viewport;
	}

	@Override
	public void update(float deltaTime) {
		Gdx.gl.glClearColor(255f, 255f, 255f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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

		float offsetX = width / 2;
		float offsetY = height / 2;
		
		//float scaleX = METERS_PER_PIXEL; // Scale to world size to match physics object
		//float scaleY = METERS_PER_PIXEL;
		float scaleX = transform.getWorldSize().x / region.getRegionWidth();
		float scaleY = transform.getWorldSize().y / region.getRegionHeight();
		// TODO: Images are rotated here as sprites are all assumed to face north.
		batch.draw(region, transform.getPos().x - offsetX, transform.getPos().y - offsetY, offsetX,
				offsetY, width, height, scaleX, scaleY, transform.getRotation() - 90);
		batch.end();
	}

	public Viewport getViewport() {
		return viewport;
	}

	public Camera getCamera() {
		return viewport.getCamera();
	}

}
