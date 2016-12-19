package be.howest.twentytwo.parametergame.input;

import java.util.Map;

import be.howest.twentytwo.parametergame.model.physics.message.IPhysicsMessage;

import com.badlogic.gdx.InputProcessor;

public class TestInputProcessor implements InputProcessor {
	
	private Map<Integer, IPhysicsMessage> keyMap;

	public TestInputProcessor(Map<Integer, IPhysicsMessage> keyMap) {
		this.keyMap = keyMap;
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keyMap.containsKey(keycode)){
			keyMap.get(keycode).execute();
			return true;
		} else{
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
