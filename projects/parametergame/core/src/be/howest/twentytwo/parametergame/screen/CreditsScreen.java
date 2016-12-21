package be.howest.twentytwo.parametergame.screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

import be.howest.twentytwo.parametergame.ScreenContext;

public class CreditsScreen extends BaseUIScreen {

	public CreditsScreen(ScreenContext context) {
		super(context);
	}

	@Override
	public void show() {
		Table root = getRoot();

		root.add(new Label("TEST\ntest", getSkin()));

		root.row();

		TextButton back = new TextButton("Back to main menu", getSkin());
		back.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				getContext().setScreen(new MenuScreen(getContext()));
				dispose();
			}
		});
		root.add(back);
	}

}
