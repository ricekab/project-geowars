package be.howest.twentytwo.parametergame.screen;

import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.factory.BaseGameFactory;
import be.howest.twentytwo.parametergame.factory.LevelFactory;
import be.howest.twentytwo.parametergame.model.event.EventEnum;
import be.howest.twentytwo.parametergame.model.event.EventQueue;
import be.howest.twentytwo.parametergame.model.event.IEvent;
import be.howest.twentytwo.parametergame.model.event.game.EnemyKilledEvent;
import be.howest.twentytwo.parametergame.model.event.listener.IEventListener;
import be.howest.twentytwo.parametergame.model.event.listener.PlayerKilledEndGameListener;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

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
		assetMgr.load("sprites/ships.pack", TextureAtlas.class);
		assetMgr.load("sprites/geowars.pack", TextureAtlas.class);
		assetMgr.load("sprites/AI.pack", TextureAtlas.class);

		assetMgr.load("sprites/game.pack", TextureAtlas.class);
		assetMgr.load("sprites/tiles.pack", TextureAtlas.class);

		// TODO: Progress bar and stuff
	}

	@Override
	public void render(float delta) {
		AssetManager assets = getContext().getAssetManager();
		while (assets.update() == false) { // Still loading
			float progress = assets.getProgress();
			System.out.println(progress);
			// Update loading bar
			// Gdx.app.log("LoadingScreen", String.format("Loading: %f",
			// progress));
		}
		long start = System.nanoTime();
		Gdx.app.log("LoadingScreen", "Building universe...");

		getContext().setScreen(gameFactory.createGameScreen());

		Gdx.app.log("LoadingScreen",
				String.format("Universe built in %d ms", (System.nanoTime() - start) / 1000000));

	}
}
