package be.howest.twentytwo.parametergame.desktop;

import be.howest.twentytwo.parametergame.ParameterGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// Hard coded 1280x720 (16:9) for now
		config.width = 1280;
		config.height = 720;
		// Dependency Injection
		Injector inj = Guice.createInjector(new DesktopModule());
		ParameterGame game = inj.getInstance(ParameterGame.class);
		new LwjglApplication(game, config);
	}
}
