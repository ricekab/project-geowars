package be.howest.twentytwo.parametergame.factory;

import com.badlogic.gdx.Screen;

import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.ui.data.LoadoutSelectionData;

/**
 * Responsible for
 * 
 * @author KCYT
 *
 */
public abstract class BaseGameFactory {

	private ScreenContext context;
	private String levelFile;

	public BaseGameFactory(ScreenContext context, String levelFile) {
		this.context = context;
		this.levelFile = levelFile;
	}

	public abstract void setSelections(LoadoutSelectionData selections);

	public abstract Screen createGameScreen();

	public ScreenContext getContext() {
		return context;
	}

	public String getLevelFile() {
		return levelFile;
	}

}
