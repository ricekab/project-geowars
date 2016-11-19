package be.howest.twentytwo.parametergame.input;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

import be.howest.twentytwo.parametergame.input.actions.AccelerateForwardAction;
import be.howest.twentytwo.parametergame.input.actions.InputAction;

public class MyInputProcessor extends InputAdapter {
	
	private Map<Integer, InputAction> keyMap;
	
	public MyInputProcessor(Map<Integer, InputAction> keyMap){
		this.keyMap = keyMap;
	}
	
	//// TEST ////
	public MyInputProcessor() {
		Map<Integer, InputAction> map = new HashMap<>();
		
		// map.put(Keys.W, new AccelerateForwardAction());
		// left, right, back
		// damper on/off
		this.keyMap = map;
	}
	//// END TEST ////
	
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
