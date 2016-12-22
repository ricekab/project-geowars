package be.howest.twentytwo.parametergame.ui.factory;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class ImageButtonFactory {

	private final ImageButtonStyle style;
	
	public ImageButtonFactory(ImageButtonStyle style) {
		this.style = style;
	}
	
	public ImageButton createButton(Drawable icon, ChangeListener listener){
		ImageButton btn = new ImageButton(icon);
		btn.addListener(listener);
		btn.pad(5f);
		return btn;
	}
}
