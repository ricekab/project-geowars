package be.howest.twentytwo.parametergame.screen;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.ui.factory.TextButtonFactory;

public class ShipLoadoutScreen extends BaseUIScreen {

	public ShipLoadoutScreen(ScreenContext context, ChangeListener confirmListener) {
		super(context);
	}

	@Override
	public void show() {
		TextButtonFactory tbf = new TextButtonFactory(getSkin().get(TextButtonStyle.class));
		
		Table root = getRoot();
		
		
	}

}
