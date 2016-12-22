package be.howest.twentytwo.parametergame.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.ui.factory.TextButtonFactory;

public class ShipLoadoutScreen extends BaseUIBackgroundScreen {

	public ShipLoadoutScreen(ScreenContext context, Engine engine, ChangeListener confirmListener) {
		super(context, engine);
	}
	
	public ShipLoadoutScreen(ScreenContext context, ChangeListener confirmListener) {
		super(context);
	}

	@Override
	public void show() {
		TextButtonFactory tbf = new TextButtonFactory(getSkin().get(TextButtonStyle.class));
		
		Table root = getRoot();
		
		
	}

}
