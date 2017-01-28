package be.howest.twentytwo.parametergame.service.file;


import be.howest.twentytwo.parametergame.dataTypes.LevelDataI;
import be.howest.twentytwo.parametergame.dataTypes.SettingsDataI;
import be.howest.twentytwo.parametergame.dataTypes.UserData;
import be.howest.twentytwo.parametergame.dataTypes.UserDataI;

/*
 * Reads and saves files with all data regarding the level design.
 */

public interface IFileAccessor {
	//This is the general blueprint for different classes, such as InMemoryFileAccessor, JSONFileAccessor,....	

	/**
	 * @param location the location of the level. By default this is "levels/level[NUMBER].lvl"
	 * @return returns the level that was loaded from the location, or null if no level was found.
	 */
	public LevelDataI loadLevel(String location);
	public void saveLevel(LevelDataI data, String location);
	
	public SettingsDataI loadSettings(String location, UserDataI user);
	public void saveSettings(SettingsDataI data, String location);

}
