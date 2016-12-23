package be.howest.twentytwo.parametergame.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import be.howest.twentytwo.parametergame.ScreenContext;

public class ShowKeyScreen extends BaseUIBackgroundScreen {

	private static final String keyBindText = "Z - Accelerate Forward\n" + "S - Accelerate Backward\n"
			+ "Q/D - Turn Left / Right\n" + "Spacebar - Fire Primary Weapon\n" + "E - Fire Secondary Weapon\n"
			+ "R - Cycle Secondary Weapon\n" + "F - Toggle inertial dampeners";

	public ShowKeyScreen(ScreenContext context, Engine engine) {
		super(context, engine);
	}

	@Override
	public void show() {
		Table root = getRoot();
		Window window = new Window("Keybindings", getSkin());
		window.getTitleLabel().setAlignment(Align.center);
		window.getTitleLabel().setStyle(getSkin().get("pressed", LabelStyle.class));
		Label label = new Label(keyBindText, getSkin());
		label.setAlignment(Align.center);
		window.add(label);
		root.add(window);

		root.row();

		TextButton back = new TextButton("Back to main menu", getSkin());
		back.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				getContext().setScreen(new MenuScreen(getContext(), getEngine()));
				dispose();
			}
		});
		root.add(back);
	}

}
