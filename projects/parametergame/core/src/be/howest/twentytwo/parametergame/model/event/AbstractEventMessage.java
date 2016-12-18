package be.howest.twentytwo.parametergame.model.event;

public abstract class AbstractEventMessage implements IEvent {

	private boolean isConsumed;
	
	public AbstractEventMessage() {
		this.isConsumed = false;
	}

	@Override
	public boolean isConsumed() {
		return isConsumed;
	}

	@Override
	public boolean setConsumed() {
		this.isConsumed = true;
		return isConsumed();
	}

}
