package be.howest.twentytwo.parametergame.screen;

import be.howest.twentytwo.parametergame.ScreenContext;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MenuScreen extends BaseUIScreen {

	public MenuScreen(ScreenContext context) {
		super(context);
	}

	@Override
	public void show() {
		TextButtonStyle textBtnStyle = getSkin().get("default", TextButtonStyle.class);
		Table root = getRoot();

		// Table - Children
		root.add(createButton("Play Arcade", textBtnStyle, new PlayArcadeListener()));

		root.row();
		root.add(createButton("Play Local Versus", textBtnStyle, new PlayVersusListener()));

		root.row();
		root.add(createButton("Credits", textBtnStyle, new CreditsListener()));

		root.row();
		root.add(createButton("Exit Game", textBtnStyle, new ExitListener()));
	}

	private TextButton createButton(String label, TextButtonStyle style, ChangeListener listener) {
		TextButton btn = new TextButton(label, style);
		btn.addListener(listener);
		btn.pad(5f);
		btn.getLabelCell().pad(10f);
		return btn;
	}

	private class PlayArcadeListener extends ChangeListener {
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			// TODO: Switch to selection screen with relevant data
			getContext().setScreen(new LoadingScreen(getContext()));
			dispose();
		}
	}

	private class PlayVersusListener extends ChangeListener {
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			getContext().setScreen(new ControllerTestScreen(getContext()));
			dispose();
		}
	}

	private class CreditsListener extends ChangeListener {
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			getContext().setScreen(new CreditsScreen(getContext()));
			dispose();
		}
	}

	private class ExitListener extends ChangeListener {
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			Gdx.app.exit();
		}
	}
}
