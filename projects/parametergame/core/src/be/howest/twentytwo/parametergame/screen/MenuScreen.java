package be.howest.twentytwo.parametergame.screen;

import be.howest.twentytwo.parametergame.ParameterGame;
import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.audio.SoundEngine;
import be.howest.twentytwo.parametergame.audio.SoundSequencer;
import be.howest.twentytwo.parametergame.dataTypes.UserDataI;
import be.howest.twentytwo.parametergame.model.system.BackgroundRenderSystem;
import be.howest.twentytwo.parametergame.ui.factory.TextButtonFactory;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen extends BaseUIBackgroundScreen {

	private TextButton arcade, versus;
	private TextField userField, passwordField;
	private Label loginStatusLabel;

	public MenuScreen(ScreenContext context, Engine backgroundEngine) {
		super(context, backgroundEngine);
	}

	public MenuScreen(ScreenContext context) {
		super(context);
                /*
                SoundSequencer seq = new SoundSequencer();
                SoundEngine se = new SoundEngine();
                se.playMusic("music/finished_long.ogg", true);  
                for(int i=0; i < 5; i++){
                    seq.addSound("sound/switch25.wav");
                }
                for(int i=0; i < 5; i++){
                    seq.play(1);
                    //System.out.println(seq.getSize());
                    //System.out.println(seq.getIndex());
                }
*/
                
                
                
	}

	@Override
	public void show() {
		TextButtonStyle textBtnStyle = getSkin().get("default", TextButtonStyle.class);
		TextButtonFactory tbf = new TextButtonFactory(textBtnStyle);

		Table root = getRoot();

		Table menu = new Table();
		root.add(menu);
		// Table - Children
		arcade = tbf.createButton("Play Arcade", new PlayArcadeListener());
		arcade.setDisabled(true);
		menu.add(arcade);

		menu.row();
		versus = tbf.createButton("Play Local Versus", new PlayVersusListener());
		versus.setDisabled(true);
		menu.add(versus);

		menu.row();
		menu.add(tbf.createButton("Credits", new CreditsListener()));

		menu.row();
		menu.add(tbf.createButton("Exit Game", new ExitListener()));

		root.add(createLoginWindow(tbf)).width(300f);

		if(ParameterGame.DEBUG_ENABLED) {
			userField.setText("debug");
			passwordField.setText("DEBUG");
			doLogin();
		}
		
		if(getContext().getUser() != null){
			setLoggedIn(getContext().getUser());
		}
	}

	private Window createLoginWindow(TextButtonFactory tbf) {
		Window window = new Window("Login", getSkin());
		window.setDebug(ParameterGame.DEBUG_ENABLED, true);

		window.getTitleTable().padBottom(15f);
		window.getTitleLabel().setStyle(getSkin().get("pressed", LabelStyle.class));
		Table login = new Table();
		window.add(login);

		InputListener enterInput = new LoginInputListener();
		userField = new TextField("Username", getSkin());
		login.add(new Label("User", getSkin(), "default"), userField);
		userField.addListener(enterInput);
		login.row();
		passwordField = new TextField("", getSkin());
		passwordField.setPasswordCharacter('*');
		passwordField.setPasswordMode(true);
		login.add(new Label("Password", getSkin(), "default"), passwordField);
		passwordField.addListener(enterInput);
		login.row();
		login.add(tbf.createButton("Confirm", new LoginListener()));
		loginStatusLabel = new Label("Not logged in", getSkin());
		login.add(loginStatusLabel);

		return window;
	}

	private class PlayArcadeListener extends ChangeListener {
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			// TODO: Switch to selection screen with relevant data
			getContext().setScreen(new LoadingScreen(getContext()));
			// getContext().setScreen(new ShipLoadoutScreen(getContext(), getEngine(), new
			// ArcadeLoadoutListener()));
			dispose();
		}
	}

	private class PlayVersusListener extends ChangeListener {
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			// TODO: Switch to versus
			// getContext().setScreen(new ControllerTestScreen(getContext()));
			getContext().setScreen(
					new ShipLoadoutScreen(getContext(), getEngine(), new ArcadeLoadoutListener()));
			dispose();
		}
	}

	private class CreditsListener extends ChangeListener {
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			getContext().setScreen(new CreditsScreen(getContext(), getEngine()));
			dispose();
		}
	}

	private class ExitListener extends ChangeListener {
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			Gdx.app.exit();
		}
	}

	private class LoginListener extends ChangeListener {
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			doLogin();
		}
	}

	private class LoginInputListener extends InputListener {

		@Override
		public boolean keyDown(InputEvent event, int keycode) {
			if(Keys.ENTER == keycode) {
				doLogin();
			}
			return false;
		}
	}

	private class ArcadeLoadoutListener extends ChangeListener {

		@Override
		public void changed(ChangeEvent event, Actor actor) {
			System.out.println("ArcadeLoadoutListener");
		}

	}

	private void doLogin() {
		// TODO: @Nick -- Can I get null back, how are non-existant users handled (Missing documentation)
		UserDataI user = getContext().getDataService().getUser(userField.getText());
		setLoggedIn(user);
	}
	
	private void setLoggedIn(UserDataI user){
		if(user != null) {
			loginStatusLabel.setText("Active Login: " + user.getUser());
			getContext().setUser(user);
			arcade.setDisabled(false);
			versus.setDisabled(false);
		}
	}
}
