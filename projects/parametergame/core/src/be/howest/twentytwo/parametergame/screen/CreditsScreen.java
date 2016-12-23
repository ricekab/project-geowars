package be.howest.twentytwo.parametergame.screen;

import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.ui.factory.TextButtonFactory;
import be.howest.twentytwo.parametergame.ui.listener.ButtonChangeSoundListener;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

public class CreditsScreen extends BaseUIBackgroundScreen {

	// Just doing it this way since I don't want to spend too much time on this.
	private static final String creditsText = "Geowars project - Group 22\n\n"
			+ "Floris Cobbaert - Lead Artist & Junior Systems Engineer\n"
			+ "Nick Dewitte - Lead Database Engineer & Senior Game Designer\n"
			+ "Kevin Tang - Lead Systems Engineer & Associate Game Designer\n"
			+ "Ward Van den Berghe - Project Manager & Lead AI Engineer\n\n\n" + "Special Thanks to\n"
			+ "All the lectors and project collaborators for providing valuable feedback\n"
			+ "Badlogic Games for providing the libGDX engine\n" + "Box2D for their awesome physics engine\n"
			+ "Raymond \"Raeleus\" Buckley for his excellent UI Skin (Neon)\n";

	public CreditsScreen(ScreenContext context, Engine engine) {
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
		window.getTitleTable().padBottom(10f);
		Label label = new Label(creditsText, getSkin());
		label.setAlignment(Align.center);
		window.add(label);
		root.add(window);

		root.row();

		TextButtonFactory tbf = new TextButtonFactory(getSkin().get(TextButtonStyle.class),
				new ButtonChangeSoundListener(getContext().getSoundService()));
		TextButton back = tbf.createButton("Back to main menu", new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				getContext().setScreen(new MenuScreen(getContext(), getEngine()));
				dispose();
			}
		});
		root.add(back);
	}

}
