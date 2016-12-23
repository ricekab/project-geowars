package be.howest.twentytwo.parametergame.ui.factory;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class TextButtonFactory {
	
	private final TextButtonStyle style;
	private final ChangeListener[] listeners;
	
	/**
	 * @param listeners - Listeners to be attached to all buttons made with this factory.
	 */
	public TextButtonFactory(TextButtonStyle style, ChangeListener... listeners){
		this.style = style;
		this.listeners = listeners;
	}
	
	public TextButton createButton(String label, ChangeListener listener) {
		TextButton btn = createButton(label);
		btn.addListener(listener);
		return btn;
	}
	
	public TextButton createButton(String label) {
		TextButton btn = new TextButton(label, style);
		for(ChangeListener listen : listeners){
			btn.addListener(listen);
		}
		btn.pad(5f);
		btn.getLabelCell().pad(10f);
		return btn;
	}
}
