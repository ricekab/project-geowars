package be.howest.twentytwo.parametergame.screen;

import com.badlogic.gdx.Screen;

import be.howest.twentytwo.parametergame.ScreenContext;

public abstract class BaseScreen implements Screen {
	
	private final ScreenContext context;

	public BaseScreen(ScreenContext context){
		this.context = context;
	}
	
	protected ScreenContext getContext(){
		return this.context;
	}
	
	@Override
	public void dispose() {
		context.dispose();
	}
}
