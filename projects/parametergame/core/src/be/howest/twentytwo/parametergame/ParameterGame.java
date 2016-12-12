package be.howest.twentytwo.parametergame;

import javax.inject.Inject;

import be.howest.twentytwo.parametergame.screen.MenuScreen;
import be.howest.twentytwo.parametergame.service.db.IDataService;
import be.howest.twentytwo.parametergame.service.file.IFileAccessor;
import be.howest.twentytwo.parametergame.service.platform.IPlatformService;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Creates necessary globals and initializes the first screen. Access point for global objects for
 * now.
 */
public class ParameterGame extends Game {

	public static final boolean DEBUG_ENABLED = true;

	private final IPlatformService platformService;
	private final IDataService dataService;
	private final IFileAccessor fileService;
	private ScreenContext context;

	@Inject
	public ParameterGame(IPlatformService platform, IDataService dataService, IFileAccessor fileService) {
		this.platformService = platform;
		this.dataService = dataService;
		this.fileService = fileService;
	}

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_INFO);

		// Has to be created here since this is libgdx tied
		context = new ScreenContext(this, new AssetManager(), new SpriteBatch(),
				new ShapeRenderer(), platformService, dataService, fileService);

		Texture.setAssetManager(context.getAssetManager());

		// For testing it's easier to not have the menu pop up
		// setScreen(new GameScreen(context));
		
		setScreen(new MenuScreen(context));
	}

	@Override
	public void dispose() {
		context.dispose();
	}
}
