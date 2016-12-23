package be.howest.twentytwo.parametergame.factory;

import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.ui.data.LoadoutSelectionData;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class MPVersusGameFactory extends BaseGameFactory {

	public MPVersusGameFactory(ScreenContext context, String levelFile) {
		super(context, levelFile);
	}

	@Override
	public void setSelections(LoadoutSelectionData selections) {
		// TODO Auto-generated method stub

	}

	@Override
	public Screen createGameScreen() {
		Gdx.app.error("MPVersusFactory", "NOT IMPLEMENTED YET");
		return null;
	}

}
