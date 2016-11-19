package be.howest.twentytwo.parametergame.input;

import java.util.Map;

import com.badlogic.gdx.InputAdapter;

import be.howest.twentytwo.parametergame.input.actions.InputAction;

public class MyInputProcessor extends InputAdapter {
	
	private Map<Integer, InputAction> keyMap;
	
	@Override
	public boolean keyDown(int keycode) {
		if(keyMap.containsKey(keycode)){
			keyMap.get(keycode).start();
			return true;
		}
		return false;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		if(keyMap.containsKey(keycode)){
			keyMap.get(keycode).stop();
			return true;
		}
		return false;
	}
	
}
