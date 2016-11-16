package be.howest.twentytwo.parametergame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * A context class which provides objects passed to and between screens.
 */
public class ScreenContext {
	
	private AssetManager assetManager;
	private SpriteBatch spriteBatch;
	private ShapeRenderer shapeRenderer;
	// TODO: Other context objects
	
	public ScreenContext() {
		// TODO Auto-generated constructor stub
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetManager assetManager) {
		this.assetManager = assetManager;
	}

	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}

	public void setSpriteBatch(SpriteBatch spriteBatch) {
		this.spriteBatch = spriteBatch;
	}

	public ShapeRenderer getShapeRenderer() {
		return shapeRenderer;
	}

	public void setShapeRenderer(ShapeRenderer shapeRenderer) {
		this.shapeRenderer = shapeRenderer;
	}
}
