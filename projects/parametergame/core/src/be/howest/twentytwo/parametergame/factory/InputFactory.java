package be.howest.twentytwo.parametergame.factory;

import java.util.HashMap;
import java.util.Map;

import be.howest.twentytwo.parametergame.input.actions.InputAction;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input.Keys;

public class InputFactory extends AbstractInputFactory {

	/**
	 * Generate a mapping of {@link Keys} to {@link InputAction} for a specific
	 * player from a string map.
	 */
	public Map<Integer, InputAction> createPlayerKeymap(Map<String, String> keyStringMap, Entity playerEntity) {
		Map<Integer, InputAction> keyMap = new HashMap<Integer, InputAction>();
		for (String key : keyStringMap.keySet()) {
			keyMap.put(Keys.valueOf(key), createActionFor(keyStringMap.get(key), playerEntity));
		}
		return keyMap;
	}

}
