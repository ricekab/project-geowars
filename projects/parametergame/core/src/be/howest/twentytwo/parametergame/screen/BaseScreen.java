package be.howest.twentytwo.parametergame.screen;

import be.howest.twentytwo.parametergame.ScreenContext;

import com.badlogic.gdx.Screen;

public abstract class BaseScreen implements Screen {

	private final ScreenContext context;

	public BaseScreen(ScreenContext context) {
		this.context = context;
	}

	protected ScreenContext getContext() {
		return this.context;
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		context.dispose();
	}
}
