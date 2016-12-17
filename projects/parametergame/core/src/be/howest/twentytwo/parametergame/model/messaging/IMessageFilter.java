package be.howest.twentytwo.parametergame.model.messaging;

public interface IMessageFilter {

	public boolean filter(IEventMessage message);
	
}
