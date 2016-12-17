package be.howest.twentytwo.parametergame.model.messaging;

public class AbstractEventMessage implements IEventMessage {

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
