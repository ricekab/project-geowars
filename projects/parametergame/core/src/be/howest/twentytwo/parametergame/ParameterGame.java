package be.howest.twentytwo.parametergame;

import be.howest.twentytwo.parametergame.platform.IPlatformSpecificAPI;
import be.howest.twentytwo.parametergame.screen.MenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;

public class ParameterGame extends Game {
	public static final boolean DEBUG_ENABLED = true;
	
	public AssetManager assetMgr;
	public SpriteBatch batch;
	public Logger logger;
	public IPlatformSpecificAPI platformAPI;
	// public final I18NBundle messages;
	
	public ParameterGame(IPlatformSpecificAPI platform){
		this.platformAPI = platform;
	}
	
	@Override
	public void create () {
		this.assetMgr = new AssetManager();
		this.batch = new SpriteBatch();
		this.logger = new Logger("ParametergameLogger");
		
		logger.setLevel(Logger.DEBUG);
		Texture.setAssetManager(assetMgr);
		setScreen(new MenuScreen(this));
	}
	
	@Override
	public void dispose () {
		this.assetMgr.dispose();
		this.batch.dispose();
		
	}
}
