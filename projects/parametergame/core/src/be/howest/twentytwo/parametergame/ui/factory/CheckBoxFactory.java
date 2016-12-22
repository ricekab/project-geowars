package be.howest.twentytwo.parametergame.ui.factory;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class CheckBoxFactory {
	
	private final CheckBoxStyle style;

	public CheckBoxFactory(CheckBoxStyle style) {
		this.style = style;
	}

	public CheckBox createButton(String label, ChangeListener listener) {
		CheckBox cb = createCheckBox(label);
		cb.addListener(listener);
		return cb;
	}

	public CheckBox createCheckBox(String label) {
		CheckBox cb = new CheckBox(label, style);
		cb.pad(5f);
		cb.getLabelCell().pad(10f);
		return cb;
	}
}
