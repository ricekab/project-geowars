package be.howest.twentytwo.parametergame;

import javax.inject.Inject;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;

import be.howest.twentytwo.parametergame.screen.MenuScreen;
import be.howest.twentytwo.parametergame.service.db.IDataService;
import be.howest.twentytwo.parametergame.service.platform.IPlatformService;

/**
 * Creates necessary globals and initializes the first screen. Access point for
 * global objects for now.
 */
public class ParameterGame extends Game {

	public static final boolean DEBUG_ENABLED = true;

	// Could move to service container but not sure if it really solves
	// anything.
	public AssetManager assetMgr;
	public SpriteBatch batch;
	public IPlatformService platformService;
	public IDataService dataService;
	// public Engine ecsEngine; --> can have multiple engines (for multiplayer
	// lag compensation, etc...)
	// should be created and populated by loaded assets for the level.
	// public final I18NBundle messages;

	@Inject
	public ParameterGame(IPlatformService platform, IDataService dataService) {
		this.platformService = platform;
		this.dataService = dataService;
	}

	@Override
	public void create() {
		// Has to be created here since this is libgdx tied
		this.assetMgr = new AssetManager();
		this.batch = new SpriteBatch();

		Texture.setAssetManager(assetMgr);
		
		setScreen(new MenuScreen(this));
	}

	@Override
	public void dispose() {
		this.assetMgr.dispose();
		this.batch.dispose();

	}
}
