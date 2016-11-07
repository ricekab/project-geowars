package be.howest.twentytwo.parametergame.screen;

import be.howest.twentytwo.parametergame.ParameterGame;
import com.badlogic.gdx.Screen;

public abstract class BaseScreen implements Screen {
	
	private final ParameterGame game;

	public BaseScreen(ParameterGame game){
		this.game = game;
	}
	
	protected ParameterGame getGame(){
		return this.game;
	}
}
