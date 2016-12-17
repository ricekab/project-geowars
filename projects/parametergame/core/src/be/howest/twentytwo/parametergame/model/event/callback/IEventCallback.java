package be.howest.twentytwo.parametergame.model.event.callback;

import be.howest.twentytwo.parametergame.model.event.type.IEventData;

public interface IEventCallback<T extends IEventData> {

	public void notify(T eventData);
}
