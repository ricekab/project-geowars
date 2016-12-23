package be.howest.twentytwo.parametergame.screen;

import be.howest.twentytwo.parametergame.ParameterGame;
import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.dataTypes.UserDataI;
import be.howest.twentytwo.parametergame.factory.BaseGameFactory;
import be.howest.twentytwo.parametergame.factory.MPVersusGameFactory;
import be.howest.twentytwo.parametergame.factory.SPGameFactory;
import be.howest.twentytwo.parametergame.ui.factory.TextButtonFactory;
import be.howest.twentytwo.parametergame.ui.listener.ButtonChangeSoundListener;
import be.howest.twentytwo.parametergame.utils.PassUtils;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MenuScreen extends BaseUIBackgroundScreen {

	private TextButton arcade, versus;
	private TextField userField, passwordField;
	private Label loginStatusLabel;

	public MenuScreen(ScreenContext context, Engine backgroundEngine) {
		super(context, backgroundEngine);
	}

	public MenuScreen(ScreenContext context) {
		super(context);

	}

	@Override
	public void show() {
		TextButtonStyle textBtnStyle = getSkin().get("default", TextButtonStyle.class);
		TextButtonFactory tbf = new TextButtonFactory(textBtnStyle,
				new ButtonChangeSoundListener(getContext().getSoundService()));

		Table root = getRoot();

		Table menu = new Table();
		root.add(menu);
		// Table - Children
		arcade = tbf.createButton("Play Arcade", new PlayArcadeListener());
		arcade.setDisabled(true);
		menu.add(arcade);

		menu.row();
		versus = tbf.createButton("Play Local Versus [DEBUG]", new PlayVersusListener());
		versus.setDisabled(true);
		menu.add(versus);

		menu.row();
		menu.add(tbf.createButton("Keybindings", new ShowKeybindsListener()));

		menu.row();
		menu.add(tbf.createButton("Credits", new CreditsListener()));

		menu.row();
		menu.add(tbf.createButton("Exit Game", new ExitListener()));
		
		menu.row();
		CheckBox debugCB = new CheckBox("Enable Debug", getSkin());
		debugCB.setChecked(ParameterGame.DEBUG_ENABLED);
		debugCB.addListener(new DebugCheckBoxListener());
		menu.add(debugCB);

		root.add(createLoginWindow(tbf)).width(300f);

		if (getContext().getMusicService().getMusic() != null
				&& getContext().getMusicService().getFile() != "music/finished_long.ogg") {
			getContext().getMusicService().disposeMusic();
		}
		if (getContext().getMusicService().getFile() != "music/finished_long.ogg")
			getContext().getMusicService().playMusic("music/finished_long.ogg");

		if (ParameterGame.DEBUG_ENABLED) {
			userField.setText("debug");
			passwordField.setText("DEBUG");
			doLogin();
		}

		if (getContext().getUser() != null) {
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
			BaseGameFactory gameFactory = new SPGameFactory(getContext(), "arcade01");
			getContext().setScreen(new ShipLoadoutScreen(getContext(), getEngine(),
					new ArcadeLoadoutListener(gameFactory), gameFactory));
			dispose();
		}
	}

	private class PlayVersusListener extends ChangeListener {
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			BaseGameFactory gameFactory = new MPVersusGameFactory(getContext(), "arcade01");
			getContext().setScreen(new ShipLoadoutScreen(getContext(), getEngine(),
					new VersusLoadoutListener(gameFactory), gameFactory, "Loadout - Player 1"));
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

	private class ShowKeybindsListener extends ChangeListener {
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			getContext().setScreen(new ShowKeyScreen(getContext(), getEngine()));
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
	
	private class DebugCheckBoxListener extends ChangeListener{
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			CheckBox c = (CheckBox)event.getListenerActor();
			ParameterGame.DEBUG_ENABLED = c.isChecked();
		}
	}

	private class LoginInputListener extends InputListener {

		@Override
		public boolean keyDown(InputEvent event, int keycode) {
			if (Keys.ENTER == keycode) {
				doLogin();
			}
			return false;
		}
	}

	private class ArcadeLoadoutListener extends ChangeListener {

		private BaseGameFactory factory;

		private ArcadeLoadoutListener(BaseGameFactory gameFactory) {
			this.factory = gameFactory;
		}

		@Override
		public void changed(ChangeEvent event, Actor actor) {
			getContext().setScreen(new LoadingScreen(getContext(), getEngine(), factory));
			getContext().getMusicService().disposeMusic();
			getContext().getMusicService().playMusic("music/Tranquil_Trance_Express.ogg");
		}
	}

	/**
	 * Show loadout screen twice, once for each player.
	 */
	private class VersusLoadoutListener extends ChangeListener {

		private BaseGameFactory factory;

		private VersusLoadoutListener(BaseGameFactory gameFactory) {
			this.factory = gameFactory;
		}

		@Override
		public void changed(ChangeEvent event, Actor actor) {
			Gdx.app.debug("MenuScreen", "Versus Mode selected");
			getContext().setScreen(new ShipLoadoutScreen(getContext(), getEngine(), new ArcadeLoadoutListener(factory),
					factory, "Loadout - Player 2"));
		}
	}

	private void doLogin() {
		String username = userField.getText();
		String plainPass = passwordField.getText();
		String encryptedPass = PassUtils.encrypt(plainPass);
		UserDataI user = getContext().getDataService().getUser(username, encryptedPass);
		setLoggedIn(user);
	}

	private void setLoggedIn(UserDataI user) {
		if (user != null) {
			loginStatusLabel.setText("Active Login: " + user.getUser());
			getContext().setUser(user);
			arcade.setDisabled(false);
			if(ParameterGame.DEBUG_ENABLED){
				versus.setDisabled(false);
			}
		}
	}
}
