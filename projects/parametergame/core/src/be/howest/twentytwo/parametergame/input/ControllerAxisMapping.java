package be.howest.twentytwo.parametergame.input;

import be.howest.twentytwo.parametergame.input.actions.InputAction;

public class ControllerAxisMapping {

	private int axisCode;
	private float activationMinValue;
	private float activationMaxValue;
	private InputAction action;

	/**
	 * 
	 * @param axisCode
	 *            Axis code, different for every controller (potentially)
	 * @param minActivation
	 *            Minimum value for the action to start, values below this will cause the action to
	 *            stop.
	 * @param maxActivation
	 *            Maximum value for the action to start, values above this will cause the action to
	 *            stop.
	 * @param action
	 *            Action to be handled by this axis mapping.
	 */
	public ControllerAxisMapping(int axisCode, float minActivation, float maxActivation,
			InputAction action) {
		setAxisCode(axisCode);
		setActivationMinValue(minActivation);
		setActivationMaxValue(maxActivation);
		setAction(action);
	}

	public void handle(float value) {
		if(getActivationMinValue() < value && value < getActivationMaxValue()) {
			getAction().start();
		} else {
			getAction().stop();
		}
	}

	// GETS & SETS
	public int getAxisCode() {
		return axisCode;
	}

	public float getActivationMinValue() {
		return activationMinValue;
	}

	public float getActivationMaxValue() {
		return activationMaxValue;
	}

	public InputAction getAction() {
		return action;
	}

	public void setAxisCode(int axisCode) {
		this.axisCode = axisCode;
	}

	public void setActivationMinValue(float activationMinValue) {
		this.activationMinValue = activationMinValue;
	}

	public void setActivationMaxValue(float activationMaxValue) {
		this.activationMaxValue = activationMaxValue;
	}

	public void setAction(InputAction action) {
		this.action = action;
	}
}
