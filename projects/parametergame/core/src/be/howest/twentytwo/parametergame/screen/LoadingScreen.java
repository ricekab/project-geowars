package be.howest.twentytwo.parametergame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import be.howest.twentytwo.parametergame.ScreenContext;

/**
 * An intermediary screen shown during loading of levels.
 */
public class LoadingScreen extends BaseScreen {

	public LoadingScreen(ScreenContext context) {
		super(context);
	}

	@Override
	public void show() {
		// Queue up assets for loading
		AssetManager assetMgr = getContext().getAssetManager();

		// TODO: If loading screen needs assets, load those first before queuing the others.
		// assetMgr.load(loadingscreenstuff)
		// assetMgr.finishLoading() // --> blocks until queue is cleared

		assetMgr.load("sprites/ships.pack", TextureAtlas.class);

	}

	@Override
	public void render(float delta) {
		while (getContext().getAssetManager().update() == false) { // Still loading
			float progress = getContext().getAssetManager().getProgress();
			Gdx.app.log("LoadingScreen", String.format("Loading: %f", progress));
		}
		// TODO: Should do creation in a separate thread instead of having the screen lockup at this
		// point.
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}

}
