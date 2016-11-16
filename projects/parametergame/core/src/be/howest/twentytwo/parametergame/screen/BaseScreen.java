package be.howest.twentytwo.parametergame.screen;

import be.howest.twentytwo.parametergame.ParameterGame;
import be.howest.twentytwo.parametergame.ScreenContext;

import com.badlogic.gdx.Screen;

public abstract class BaseScreen implements Screen {
	
	private final ScreenContext context;

	public BaseScreen(ScreenContext context){
		this.context = context;
	}
	
	protected ScreenContext getContext(){
		return this.context;
	}
}
