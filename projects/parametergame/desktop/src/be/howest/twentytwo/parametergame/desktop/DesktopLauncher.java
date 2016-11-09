package be.howest.twentytwo.parametergame.desktop;

import be.howest.twentytwo.parametergame.ParameterGame;
import be.howest.twentytwo.parametergame.injection.DesktopModule;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// Dependency Injection
		Injector inj = Guice.createInjector(new DesktopModule());
		ParameterGame game = inj.getInstance(ParameterGame.class);
		new LwjglApplication(game, config);
	}
}
