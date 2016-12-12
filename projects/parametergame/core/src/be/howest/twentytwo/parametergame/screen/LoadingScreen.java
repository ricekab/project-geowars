package be.howest.twentytwo.parametergame.screen;

import java.util.ArrayList;
import java.util.Collection;

import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.dataTypes.PlanetData;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipData;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipData;
import be.howest.twentytwo.parametergame.factory.LevelFactory;
import be.howest.twentytwo.parametergame.factory.PlanetFactory;
import be.howest.twentytwo.parametergame.factory.PlayerShipFactory;
import be.howest.twentytwo.parametergame.model.PhysicsBodyEntityListener;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.CameraComponent;
import be.howest.twentytwo.parametergame.model.physics.collision.ContactProcessor;
import be.howest.twentytwo.parametergame.model.physics.collision.GravityContactProcessor;
import be.howest.twentytwo.parametergame.model.physics.events.IPhysicsEvent;
import be.howest.twentytwo.parametergame.model.system.AiSystem;
import be.howest.twentytwo.parametergame.model.system.BackgroundRenderSystem;
import be.howest.twentytwo.parametergame.model.system.CameraSystem;
import be.howest.twentytwo.parametergame.model.system.MovementSystem;
import be.howest.twentytwo.parametergame.model.system.PhysicsRenderSystem;
import be.howest.twentytwo.parametergame.model.system.PhysicsSystem;
import be.howest.twentytwo.parametergame.model.system.RenderSystem;
import be.howest.twentytwo.parametergame.service.db.IDataService;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

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
		Viewport viewport = new FitViewport(320f, 180f); // Viewport size (in
															// world units)
		/*
		 * B) ScreenViewport = full size without stretching, but shown field is
		 * different based on aspect ratio --> possible balance concern
		 */
		// ScreenViewport sv = new ScreenViewport();
		// sv.setUnitsPerPixel(0.25f);
		// viewport = sv;

		LevelFactory levelFactory = new LevelFactory();
		// TODO: Pass level data instead of null
		PooledEngine engine = levelFactory.createWorld(getContext(), viewport, "arcade01");

		Gdx.app.log("LoadingScreen", String.format("Loading done - %d ms", (System.nanoTime() - start) / 1000000));
		getContext().setScreen(new GameScreen(getContext(), engine, viewport));
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
