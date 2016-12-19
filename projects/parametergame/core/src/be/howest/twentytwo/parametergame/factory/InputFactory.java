package be.howest.twentytwo.parametergame.factory;

import java.util.HashMap;
import java.util.Map;

import be.howest.twentytwo.parametergame.input.Inputs;
import be.howest.twentytwo.parametergame.input.actions.AccelerateBackwardAction;
import be.howest.twentytwo.parametergame.input.actions.AccelerateForwardAction;
import be.howest.twentytwo.parametergame.input.actions.CycleSecondaryAction;
import be.howest.twentytwo.parametergame.input.actions.DampenToggleAction;
import be.howest.twentytwo.parametergame.input.actions.FirePrimaryAction;
import be.howest.twentytwo.parametergame.input.actions.FireSecondaryAction;
import be.howest.twentytwo.parametergame.input.actions.InputAction;
import be.howest.twentytwo.parametergame.input.actions.NullInputAction;
import be.howest.twentytwo.parametergame.input.actions.TurnLeftAction;
import be.howest.twentytwo.parametergame.input.actions.TurnRightAction;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.MovementComponent;
import be.howest.twentytwo.parametergame.model.component.WeaponComponent;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class InputFactory {

	/**
	 * Generate a mapping of {@link Keys} to {@link InputAction} for a specific player from a string
	 * map.
	 */
	public Map<Integer, InputAction> createPlayerKeymap(Map<String, String> keyStringMap,
			Entity playerEntity) {
		Map<Integer, InputAction> keyMap = new HashMap<Integer, InputAction>();
		for (String key : keyStringMap.keySet()) {
			keyMap.put(Keys.valueOf(key), createActionFor(keyStringMap.get(key), playerEntity));
		}
		return keyMap;
	}

	protected InputAction createActionFor(String actionString, Entity player) {
		InputAction input;
		/*
		 * map.put(Keys.Z, new AccelerateForwardAction(playerMC)); map.put(Keys.S, new
		 * AccelerateBackwardAction(playerMC)); map.put(Keys.Q, new TurnLeftAction(playerMC));
		 * map.put(Keys.D, new TurnRightAction(playerMC));
		 */
		switch (actionString) {
			case Inputs.ACCELERATE_FORWARD:
				input = new AccelerateForwardAction(MovementComponent.MAPPER.get(player));
				break;
			case Inputs.ACCELERATE_BACKWARD:
				input = new AccelerateBackwardAction(MovementComponent.MAPPER.get(player));
				break;
			case Inputs.TURN_LEFT:
				input = new TurnLeftAction(MovementComponent.MAPPER.get(player));
				break;
			case Inputs.TURN_RIGHT:
				input = new TurnRightAction(MovementComponent.MAPPER.get(player));
				break;
			case Inputs.TOGGLE_LINEAR_DAMP:
				input = new DampenToggleAction(BodyComponent.MAPPER.get(player),
						MovementComponent.MAPPER.get(player));
				break;
			case Inputs.FIRE_PRIMARY:
				input = new FirePrimaryAction(WeaponComponent.MAPPER.get(player));
				break;
			case Inputs.FIRE_SECONDARY:
				input = new FireSecondaryAction(WeaponComponent.MAPPER.get(player));
				break;
			case Inputs.CYClE_SECONDARY:
				input = new CycleSecondaryAction(WeaponComponent.MAPPER.get(player));
				break;
			default:
				Gdx.app.error("InputFactory", "ERR: Could not recognize input action string.");
				input = new NullInputAction();
		}
		return input;
	}

}
