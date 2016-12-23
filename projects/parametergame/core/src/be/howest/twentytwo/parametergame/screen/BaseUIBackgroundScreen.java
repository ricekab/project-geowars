package be.howest.twentytwo.parametergame.screen;

import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.model.system.BackgroundRenderSystem;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public abstract class BaseUIBackgroundScreen extends BaseUIScreen {

	private final Engine engine;
	
	public BaseUIBackgroundScreen(ScreenContext context, Engine engine) {
		super(context);
		this.engine = engine;
	}
	
	public BaseUIBackgroundScreen(ScreenContext context) {
		super(context);
		engine = new Engine();
		engine.addSystem(new BackgroundRenderSystem(context.getSpriteBatch(), context
				.getAssetManager(), getViewport(), 125f, 125f));
		// Gdx.input.setInputProcessor(getStage());
	}
	
	public Engine getEngine(){
		return engine;
	}

	@Override
	public boolean preDraw(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		engine.update(delta);
		return true;
	}


}
