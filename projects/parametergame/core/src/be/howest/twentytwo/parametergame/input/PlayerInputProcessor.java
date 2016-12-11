package be.howest.twentytwo.parametergame.input;

import java.util.HashMap;
import java.util.Map;

import be.howest.twentytwo.parametergame.input.actions.AccelerateBackwardAction;
import be.howest.twentytwo.parametergame.input.actions.AccelerateForwardAction;
import be.howest.twentytwo.parametergame.input.actions.KeyInputAction;
import be.howest.twentytwo.parametergame.input.actions.TurnLeftAction;
import be.howest.twentytwo.parametergame.input.actions.TurnRightAction;
import be.howest.twentytwo.parametergame.model.component.MovementComponent;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class PlayerInputProcessor extends InputAdapter {
	
	private Map<Integer, KeyInputAction> keyMap;
	
	public PlayerInputProcessor(Map<Integer, KeyInputAction> keyMap){
		this.keyMap = keyMap;
	}
	
	//// TEST ////
	/**
	 * TODO: THIS IS A TEMP IMPLEMENTATION
	 * @param playerMC
	 */
	public PlayerInputProcessor(MovementComponent playerMC) {
		Map<Integer, KeyInputAction> map = new HashMap<>();
		
		// map.put(Keys.W, new AccelerateForwardAction());
		// left, right, back
		// damper on/off
		this.keyMap = map;
		
		map.put(Keys.Z, new AccelerateForwardAction(playerMC));
		map.put(Keys.S, new AccelerateBackwardAction(playerMC));
		map.put(Keys.Q, new TurnLeftAction(playerMC));
		map.put(Keys.D, new TurnRightAction(playerMC));
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
