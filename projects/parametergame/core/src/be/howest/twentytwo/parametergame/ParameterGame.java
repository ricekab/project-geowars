package be.howest.twentytwo.parametergame;

import javax.inject.Inject;

import be.howest.twentytwo.parametergame.screen.MenuScreen;
import be.howest.twentytwo.parametergame.screen.GameScreen;
import be.howest.twentytwo.parametergame.service.db.IDataService;
import be.howest.twentytwo.parametergame.service.platform.IPlatformService;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Creates necessary globals and initializes the first screen. Access point for global objects for now.
 */
public class ParameterGame extends Game {

	public static final boolean DEBUG_ENABLED = true;

	private final IPlatformService platformService;
	private final IDataService dataService;
	private ScreenContext context;

	@Inject
	public ParameterGame(IPlatformService platform, IDataService dataService) {
		this.platformService = platform;
		this.dataService = dataService;
	}

	@Override
	public void create() {
		// Has to be created here since this is libgdx tied
		context = new ScreenContext(this, new AssetManager(), new SpriteBatch(), new ShapeRenderer(), platformService,
				dataService);

		Texture.setAssetManager(context.getAssetManager());

                // For testing it's easier to not have the menu pop up
		setScreen(new GameScreen(context));
	}

	@Override
	public void dispose() {
		context.dispose();
	}
}
