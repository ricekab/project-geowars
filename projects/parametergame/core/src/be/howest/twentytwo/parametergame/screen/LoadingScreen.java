package be.howest.twentytwo.parametergame.screen;

import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.factory.BaseGameFactory;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * An intermediary screen shown during loading of levels.
 */
public class LoadingScreen extends BaseUIBackgroundScreen {

	private BaseGameFactory gameFactory;

	public LoadingScreen(ScreenContext context, Engine engine, BaseGameFactory factory) {
		super(context, engine);
		gameFactory = factory;
	}

	@Override
	public void show() {
		// Queue up assets for loading
		AssetManager assetMgr = getContext().getAssetManager();

		// Setup loading bar or whatever
		assetMgr.load("sprites/game.pack", TextureAtlas.class);
		assetMgr.load("sprites/tiles.pack", TextureAtlas.class);

		// TODO: Progress bar and stuff
	}

	@Override
	public void render(float delta) {
		AssetManager assets = getContext().getAssetManager();
		while (assets.update() == false) { // Still loading
			float progress = assets.getProgress();
			// Update loading bar
			// Gdx.app.debug("LoadingScreen", String.format("Loading: %f", progress));
		}
		long start = System.nanoTime();
		Gdx.app.log("LoadingScreen", "Building universe...");

		getContext().setScreen(gameFactory.createGameScreen());

		Gdx.app.debug("LoadingScreen",
				String.format("Universe built in %d ms", (System.nanoTime() - start) / 1000000));

	}
}
