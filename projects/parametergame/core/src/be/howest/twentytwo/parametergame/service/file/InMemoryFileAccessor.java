package be.howest.twentytwo.parametergame.service.file;

import be.howest.twentytwo.parametergame.dataTypes.BoxData;
import be.howest.twentytwo.parametergame.dataTypes.LevelData;

public class InMemoryFileAccessor implements IFileAccessor{

	@Override
	public LevelData getLevel(String location) {
		BoxData world = new BoxData(1000f, 500f, 0f, 0f);
		BoxData spawnBox = new BoxData(250f, 125f, 500f, 250f);
		LevelData data = new LevelData(world, spawnBox);
		//TODO
		return data;
	}
	
}
