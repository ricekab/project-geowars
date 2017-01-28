package be.howest.twentytwo.parametergame.input.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.howest.twentytwo.parametergame.input.ControllerAxisMapping;
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
		List<ControllerAxisMapping> axis = new ArrayList<ControllerAxisMapping>();
		
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
		
		axis.add(new ControllerAxisMapping(1, -2.0f, -0.5f, createActionFor(Inputs.TURN_LEFT, player)));
		axis.add(new ControllerAxisMapping(1, 0.5f, 2.0f, createActionFor(Inputs.TURN_RIGHT, player)));
		
		axis.add(new ControllerAxisMapping(4, -2.0f, -0.3f, createActionFor(Inputs.ACCELERATE_FORWARD, player)));
		axis.add(new ControllerAxisMapping(4, 0.3f, 2.0f, createActionFor(Inputs.ACCELERATE_BACKWARD, player)));
		
		return new ControllerInputListener(buttons, pov, axis);
	}
}
