package be.howest.twentytwo.parametergame.screen;

import be.howest.twentytwo.parametergame.ScreenContext;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

public class CreditsScreen extends BaseUIBackgroundScreen {

	// Just doing it this way since I don't want to spend too much time on this.
	private static final String creditsText = "Geowars project - Group 22\n\n"
			+ "Floris Cobbaert - Lead Artist & Systems Engineer\n"
			+ "Nick Dewitte - Database Engineer\n" + "Kevin Tang - Lead Systems Designer\n"
			+ "Ward Van den Berghe - Lead AI Engineer\n\n\n" + "Special Thanks to\n"
			+ "All the lectors and project collaborators for providing valuable feedback\n"
			+ "Badlogic Games for providing the libGDX engine\n"
			+ "Box2D for their awesome physics engine\n"
			+ "Raymond \"Raeleus\" Buckley for his excellent UI Skin (Neon)\n";

	public CreditsScreen(ScreenContext context, Engine engine){
		super(context, engine);
	}
	
	public CreditsScreen(ScreenContext context) {
		super(context);
	}

	@Override
	public void show() {
		Table root = getRoot();
		Window window = new Window("Credits", getSkin());
		window.getTitleLabel().setAlignment(Align.center);
		window.getTitleLabel().setStyle(getSkin().get("pressed", LabelStyle.class));
		Label label = new Label(creditsText, getSkin());
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
