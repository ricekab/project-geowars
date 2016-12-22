package be.howest.twentytwo.parametergame.factory;

import be.howest.twentytwo.parametergame.input.Inputs;
import be.howest.twentytwo.parametergame.input.actions.AccelerateBackwardAction;
import be.howest.twentytwo.parametergame.input.actions.AccelerateForwardAction;
import be.howest.twentytwo.parametergame.input.actions.CycleSecondaryAction;
import be.howest.twentytwo.parametergame.input.actions.DampenToggleAction;
import be.howest.twentytwo.parametergame.input.actions.FirePrimaryAction;
import be.howest.twentytwo.parametergame.input.actions.FireSecondaryAction;
import be.howest.twentytwo.parametergame.input.actions.InputAction;
import be.howest.twentytwo.parametergame.input.actions.NullInputAction;
import be.howest.twentytwo.parametergame.input.actions.StopMovementAction;
import be.howest.twentytwo.parametergame.input.actions.TurnLeftAction;
import be.howest.twentytwo.parametergame.input.actions.TurnRightAction;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.MovementComponent;
import be.howest.twentytwo.parametergame.model.component.WeaponComponent;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;

public class AbstractInputFactory {

	protected InputAction createActionFor(String actionString, Entity player) {
		InputAction input;
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
			input = new DampenToggleAction(BodyComponent.MAPPER.get(player), MovementComponent.MAPPER.get(player));
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
		case Inputs.HALT_MOVE:
			input = new StopMovementAction(MovementComponent.MAPPER.get(player));
			break;
		default:
			Gdx.app.error("InputFactory", "ERR: Could not recognize input action string.");
			input = new NullInputAction();
		}
		return input;
	}

}