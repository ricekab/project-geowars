package be.howest.twentytwo.parametergame.service.file;

import be.howest.twentytwo.parametergame.dataTypes.LevelData;
import be.howest.twentytwo.parametergame.dataTypes.LevelDataI;

/*
 * Reads and saves files with all data regarding the level design.
 */

public interface IFileAccessor {
	//This is the general blueprint for different classes, such as InMemoryFileAccessor, JSONFileAccessor,....	

	public LevelDataI loadLevel(String location);
	
	public void saveLevel(LevelDataI data, String location);

}
