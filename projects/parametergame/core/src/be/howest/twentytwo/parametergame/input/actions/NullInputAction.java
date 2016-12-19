package be.howest.twentytwo.parametergame.input.actions;

import com.badlogic.gdx.Gdx;

public class NullInputAction implements InputAction {

	@Override
	public void start() {
		Gdx.app.debug("NullInputAction", "Null Input start.");
	}

	@Override
	public void stop() {
		Gdx.app.debug("NullInputAction", "Null input stop.");
	}

}
