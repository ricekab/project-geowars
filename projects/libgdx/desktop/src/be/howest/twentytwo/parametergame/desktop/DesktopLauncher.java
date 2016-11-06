package be.howest.twentytwo.parametergame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import be.howest.twentytwo.parametergame.ParameterGame;
import be.howest.twentytwo.parametergame.platform.DesktopAPI;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new ParameterGame(new DesktopAPI()), config);
	}
}
