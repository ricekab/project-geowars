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

	// TODO: Is this a good ratio? For now we'll keep pixel-to-unit ratio at 16:1
	 
	public final static float PIXELS_PER_METER = 16f;
	public final static float METERS_PER_PIXEL = 1f / PIXELS_PER_METER;

	private Viewport viewport;
	private SpriteBatch batch;

	public RenderSystem(SpriteBatch batch, Viewport viewport) {
		super(Family.all(TransformComponent.class, SpriteComponent.class).get());
		this.batch = batch;
		this.viewport = viewport;
	}

	@Override
	public void update(float deltaTime) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Gdx.gl.glClearColor(100f, 100f, 100f, 1f);

		super.update(deltaTime);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		TransformComponent transform = TransformComponent.MAPPER.get(entity);
		SpriteComponent spriteComp = SpriteComponent.MAPPER.get(entity);

		getCamera().update(); // TODO: Has to be moved elsewhere (doing it foreach entity is obviously wrong)
		batch.setProjectionMatrix(getCamera().combined);
		batch.begin();
		TextureRegion region = spriteComp.getRegion();

		float width = region.getRegionWidth();
		float height = region.getRegionHeight();
		// float originY = -1 * height*METERS_PER_PIXEL / 2f;
		// float offsetX = * METERS_PER_PIXEL;
		// float offsetY = * METERS_PER_PIXEL;
		float offsetX = width / 2;
		float offsetY = height / 2;
		float scaleX = METERS_PER_PIXEL; // Scale to world size to match physics object
		float scaleY = METERS_PER_PIXEL;
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
