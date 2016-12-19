package be.howest.twentytwo.parametergame.input;

import java.util.Map;

import be.howest.twentytwo.parametergame.input.actions.InputAction;

import com.badlogic.gdx.InputAdapter;

public class PlayerInputProcessor extends InputAdapter {
	
	private Map<Integer, InputAction> keyMap;
	
	public PlayerInputProcessor(Map<Integer, InputAction> keyMap){
		this.keyMap = keyMap;
	}
	
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
