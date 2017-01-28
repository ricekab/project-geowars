package be.howest.twentytwo.parametergame.model.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BackgroundRenderSystem extends EntitySystem {
	
	public static final String BACKGROUND_SPRITE_PACK = "sprites/tiles.pack";

	public final static int PRIORITY = 0;

	private Viewport viewport;
	private SpriteBatch batch;
	private AssetManager assets;

	// private long seed;
	private TextureAtlas spritesheet;
	private TextureRegion[][] tiles;
	private float tileWidth;
	private float tileHeight;
	private float offsetX; // Note: Offsets are needed due to edge case around 0
	private float offsetY;

	public BackgroundRenderSystem(SpriteBatch batch, AssetManager assetManager, Viewport viewport,
			String packFile, float tileWidth, float tileHeight) {
		this.batch = batch;
		this.assets = assetManager;
		this.viewport = viewport;
		this.tiles = new TextureRegion[20][20];
		assets.load(packFile, TextureAtlas.class);
		assets.finishLoadingAsset(packFile);
		this.spritesheet = assets.get(packFile, TextureAtlas.class);
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.offsetX = tileWidth / 2;
		this.offsetY = tileHeight / 2;
	}

	public BackgroundRenderSystem(SpriteBatch batch, AssetManager assetManager, Viewport viewport,
			float tileWidth, float tileHeight) {
		this(batch, assetManager, viewport, BACKGROUND_SPRITE_PACK, tileWidth, tileHeight);
	}

	public BackgroundRenderSystem(SpriteBatch batch, AssetManager assetManager, Viewport viewport) {
		this(batch, assetManager, viewport, BACKGROUND_SPRITE_PACK, 50f, 50f);
	}

	@Override
	public void update(float deltaTime) {
		getViewport().apply();
		batch.setProjectionMatrix(getCamera().combined);
		batch.begin();
		/*
		 * Gdx.app.log( "BGRenderSys", String.format("World size: (%f, %f)",
		 * viewport.getWorldWidth(), viewport.getWorldHeight())); 
		 * Gdx.app.log("BGRenderSys",
		 * String.format("CameraPos: (%f, %f)", getCamera().position.x, getCamera().position.y));
		 */
		float minX = getCamera().position.x - getViewport().getWorldWidth() / 2;
		float minY = getCamera().position.y - getViewport().getWorldHeight() / 2;
		float maxX = getCamera().position.x + getViewport().getWorldWidth() / 2 + tileWidth;
		float maxY = getCamera().position.y + getViewport().getWorldHeight() / 2 + tileHeight;

		for (float x = minX; x < maxX; x += tileWidth) {
			int tileX = (int) Math.floor(x / tileWidth);
			for (float y = minY; y < maxY; y += tileHeight) {
				int tileY = (int) Math.floor(y / tileHeight);
				TextureRegion region = getTileRegion(tileX, tileY);
				batch.draw(region, tileX * tileWidth, tileY * tileHeight, offsetX, offsetY,
						tileWidth, tileHeight, 1f, 1f, 0);
			}
		}
		batch.end();
	}

	/**
	 * Returns the texture region that has to be drawn for that tile. If no texture region has been
	 * generated for that tile yet, it is done before returning the result back.
	 */
	private TextureRegion getTileRegion(int x, int y) {
		// Apparently, java modulo uses the sign retention one.
		while (x < 0) {
			x += tiles.length;
		}
		while (y < 0) {
			y += tiles.length;
		}
		int xIdx = x % tiles.length;
		int yIdx = y % tiles.length;
		if(tiles[xIdx][yIdx] == null) {
			return generateTileRegion(xIdx, yIdx);
		}
		return tiles[xIdx][yIdx];
	}

	private TextureRegion generateTileRegion(int x, int y) {
		// tiles[x][y] = spritesheet.findRegion("1_space");
		tiles[x][y] = spritesheet.getRegions().random();
		return tiles[x][y];
	}

	public Viewport getViewport() {
		return viewport;
	}

	public Camera getCamera() {
		return viewport.getCamera();
	}

}
