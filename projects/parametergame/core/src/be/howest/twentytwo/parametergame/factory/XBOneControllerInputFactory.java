package be.howest.twentytwo.parametergame.factory;

import java.util.HashMap;
import java.util.Map;

import be.howest.twentytwo.parametergame.input.ControllerInputListener;
import be.howest.twentytwo.parametergame.input.Inputs;
import be.howest.twentytwo.parametergame.input.actions.InputAction;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;

public class XBOneControllerInputFactory extends AbstractInputFactory {

	public ControllerListener createControllerListener(Entity player){
		Map<Integer, InputAction> buttons = new HashMap<Integer, InputAction>();
		Map<PovDirection, InputAction> pov = new HashMap<PovDirection, InputAction>();
		
		buttons.put(0, createActionFor(Inputs.FIRE_PRIMARY, player));
		buttons.put(1, createActionFor(Inputs.FIRE_SECONDARY, player));
		buttons.put(2, createActionFor(Inputs.TOGGLE_LINEAR_DAMP, player));
		buttons.put(3, createActionFor(Inputs.CYClE_SECONDARY, player));
		buttons.put(7, createActionFor(Inputs.OPEN_MENU, player));
		
		pov.put(PovDirection.north, createActionFor(Inputs.ACCELERATE_FORWARD, player));
		pov.put(PovDirection.south, createActionFor(Inputs.ACCELERATE_BACKWARD, player));
		pov.put(PovDirection.east, createActionFor(Inputs.TURN_RIGHT, player));
		pov.put(PovDirection.west, createActionFor(Inputs.TURN_LEFT, player));
		pov.put(PovDirection.center, createActionFor(Inputs.HALT_MOVE, player));
		
		return new ControllerInputListener(buttons, pov);
	}
}
