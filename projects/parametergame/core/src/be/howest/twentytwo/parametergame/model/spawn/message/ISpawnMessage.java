package be.howest.twentytwo.parametergame.model.spawn.message;

import be.howest.twentytwo.parametergame.factory.ISpawnFactory;

public interface ISpawnMessage {

	public String getType();

	public void execute(ISpawnFactory factory);

}
