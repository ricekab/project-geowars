package be.howest.twentytwo.parametergame.screen;

import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.factory.LevelFactory;
import be.howest.twentytwo.parametergame.model.event.EventEnum;
import be.howest.twentytwo.parametergame.model.event.EventQueue;
import be.howest.twentytwo.parametergame.model.event.IEvent;
import be.howest.twentytwo.parametergame.model.event.IEventListener;
import be.howest.twentytwo.parametergame.model.event.game.EnemyKilledEvent;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * An intermediary screen shown during loading of levels.
 */
public class LoadingScreen extends BaseScreen {

	private final String levelFile;

	public LoadingScreen(ScreenContext context, String levelFile) {
		super(context);
		this.levelFile = levelFile;
	}

	public LoadingScreen(ScreenContext context) {
		this(context, "arcade01");
	}

	@Override
	public void show() {
		// Queue up assets for loading
		AssetManager assetMgr = getContext().getAssetManager();

		// TODO: If loading screen needs assets, load those first before queuing
		// the others.
		// assetMgr.load(loadingscreenstuff)
		// assetMgr.finishLoading() // --> blocks until queue is cleared
		// Setup loading bar or whatever
		assetMgr.load("sprites/ships.pack", TextureAtlas.class);
		assetMgr.load("sprites/geowars.pack", TextureAtlas.class);
		assetMgr.load("sprites/tiles.pack", TextureAtlas.class);
	}

	@Override
	public void render(float delta) {
		AssetManager assets = getContext().getAssetManager();
		while (assets.update() == false) { // Still loading
			float progress = assets.getProgress();
			// Update loading bar
			// Gdx.app.log("LoadingScreen", String.format("Loading: %f",
			// progress));
		}
		long start = System.nanoTime();
		Gdx.app.log("LoadingScreen", "Building universe...");

		// VIEWPORT / CAM
		// A) Fitviewport = letterboxing (Also a bit easier to debug for atm)
		// 240 135 // 320 180
		Viewport viewport = new FitViewport(320f, 180f); // Viewport size (in
															// world units)
		/*
		 * B) ScreenViewport = full size without stretching, but shown field is different based on
		 * aspect ratio --> possible balance concern
		 */
		// ScreenViewport sv = new ScreenViewport();
		// sv.setUnitsPerPixel(0.25f);
		// viewport = sv;

		LevelFactory levelFactory = new LevelFactory();
		EventQueue eventQueue = new EventQueue();

		PooledEngine engine = levelFactory.createWorld(getContext(), viewport, eventQueue,
				levelFile);

		Gdx.app.log("LoadingScreen", "Initializing events...");
		registerSoundEvents(eventQueue);
		registerGameEvents(eventQueue);
		
		Gdx.app.log("LoadingScreen",
				String.format("Loading done - %d ms", (System.nanoTime() - start) / 1000000));
		getContext().setScreen(new GameScreen(getContext(), engine, viewport, eventQueue));
	}
	
	private void registerSoundEvents(EventQueue eventQueue){
		// register event handlers on event queue to send sound messages.
		// Will need another chain of objects to filter the messages
		// Eg. PlayerHit --> BulletHitSound or CrashedWithEnemySound or ...
	}
	
	private void registerGameEvents(EventQueue eventQueue){
		eventQueue.register(EventEnum.ENEMY_KILLED, new IEventListener() {
			
			@Override
			public void handle(IEvent event) {
				EnemyKilledEvent e = (EnemyKilledEvent)event;
				// Add score points and stuff.
			}
		});
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
