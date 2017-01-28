package be.howest.twentytwo.parametergame.input;

import java.util.List;
import java.util.Map;

import be.howest.twentytwo.parametergame.input.actions.InputAction;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.badlogic.gdx.controllers.PovDirection;
public class ControllerInputListener extends ControllerAdapter {

	private Map<Integer, InputAction> buttonMap;
	private Map<PovDirection, InputAction> povMap;
	private List<ControllerAxisMapping> axisMapping;

	public ControllerInputListener(Map<Integer, InputAction> buttonMap,
			Map<PovDirection, InputAction> povMap, List<ControllerAxisMapping> axisMapping) {
		this.buttonMap = buttonMap;
		this.povMap = povMap;
		this.axisMapping = axisMapping;
	}

	@Override
	public boolean buttonDown(Controller controller, int buttonCode) {
		if(buttonMap.containsKey(buttonCode)) {
			buttonMap.get(buttonCode).start();
			return true;
		}
		return false;
	}

	@Override
	public boolean buttonUp(Controller controller, int buttonCode) {
		System.out.println("BUTTON " + buttonCode);
		
		if(buttonMap.containsKey(buttonCode)) {
			buttonMap.get(buttonCode).stop();
			return true;
		}
		return false;
	}

	@Override
	public boolean axisMoved(Controller controller, int axisCode, float value) {
		System.out.println("axisCode " + axisCode + " : " + value);
		for(ControllerAxisMapping cam : axisMapping){
			if(cam.getAxisCode() == axisCode){
				cam.handle(value);
			}
		}
		return false;
	}

	@Override
	public boolean povMoved(Controller controller, int povCode, PovDirection value) {
		if(povMap.containsKey(value)) {
			povMap.get(value).start();
			return true;
		}
		return false;
	}

}
