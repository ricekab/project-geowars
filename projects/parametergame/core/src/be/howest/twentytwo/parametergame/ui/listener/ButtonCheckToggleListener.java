package be.howest.twentytwo.parametergame.ui.listener;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class ButtonCheckToggleListener extends ChangeListener {

	private final Button btn;

	public ButtonCheckToggleListener(Button button) {
		this.btn = button;
	}

	@Override
	public void changed(ChangeEvent event, Actor actor) {
		btn.setChecked(!btn.isChecked());
	}
}
