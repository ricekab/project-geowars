package be.howest.twentytwo.parametergame.input;

import java.util.Map;

import be.howest.twentytwo.parametergame.model.physics.events.IPhysicsEvent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

/**
 * Purely made for testing input processors. Not for use in release.
 */
public class TestInputProcessor implements InputProcessor {
	
	/* TODO: Should be key:action mapping, not key:physicsEvt
	 * Not all inputs are physics event triggers (although some actions may trigger events)
	 */
	private Map<Integer, IPhysicsEvent> keyMap;
	
	public TestInputProcessor(Map<Integer, IPhysicsEvent> keyMap) {
		this.keyMap = keyMap;
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keyMap.containsKey(keycode)){
			Gdx.app.log("TestInputProcessor", "Key processed: " + Keys.toString(keycode));
			keyMap.get(keycode).execute();
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
