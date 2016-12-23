package be.howest.twentytwo.parametergame;

import be.howest.twentytwo.parametergame.audio.SoundEngine;
import be.howest.twentytwo.parametergame.audio.SoundSequencer;
import be.howest.twentytwo.parametergame.dataTypes.UserDataI;
import be.howest.twentytwo.parametergame.service.db.IDataService;
import be.howest.twentytwo.parametergame.service.file.IFileAccessor;
import be.howest.twentytwo.parametergame.service.platform.IPlatformService;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * A context class which provides objects passed to and between screens.
 */
public class ScreenContext {

	private final AssetManager assetManager;
	private final SpriteBatch spriteBatch;
	private final ShapeRenderer shapeRenderer;
	private final IPlatformService platformService;
	private final IDataService dataService;
	private final IFileAccessor fileService;
	private final Game game;
	private final SoundSequencer soundService;
        private final SoundEngine musicService;
	private UserDataI user;

	// private I18NBundle internationalization;

	public ScreenContext(Game game, AssetManager assets, SpriteBatch batch, ShapeRenderer shapes,
			IPlatformService platform, IDataService dataService, IFileAccessor fileService) {
		this.game = game;
		this.assetManager = assets;
		this.spriteBatch = batch;
		this.shapeRenderer = shapes;
		this.platformService = platform;
		this.dataService = dataService;
		this.fileService = fileService;
		this.soundService = new SoundSequencer();
                this.musicService = new SoundEngine();
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}

	public ShapeRenderer getShapeRenderer() {
		return shapeRenderer;
	}

	public IPlatformService getPlatformService() {
		return platformService;
	}

	public IDataService getDataService() {
		return dataService;
	}

	public IFileAccessor getFileService() {
		return fileService;
	}

	private Game getGame() {
		return game;
	}

	public void setScreen(Screen screen) {
		getGame().setScreen(screen);
	}

	public UserDataI getUser() {
		return user;
	}

	public void setUser(UserDataI user) {
		this.user = user;
	}

	public SoundSequencer getSoundService() {
		return soundService;
	}
        
        public SoundEngine getMusicService() {
		return musicService;
	}

	public void dispose() {
		getAssetManager().clear();
		getAssetManager().dispose();
		getSpriteBatch().dispose();
		getShapeRenderer().dispose();
	}

}
