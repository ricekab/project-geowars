package be.howest.twentytwo.parametergame.ui.factory;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class TextButtonFactory {
	
	private final TextButtonStyle style;
	
	public TextButtonFactory(TextButtonStyle style){
		this.style = style;
	}
	
	public TextButton createButton(String label, ChangeListener listener) {
		TextButton btn = new TextButton(label, style);
		btn.addListener(listener);
		btn.pad(5f);
		btn.getLabelCell().pad(10f);
		return btn;
	}
}
