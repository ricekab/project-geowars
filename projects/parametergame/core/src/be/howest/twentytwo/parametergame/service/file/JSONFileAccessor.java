package be.howest.twentytwo.parametergame.service.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

import be.howest.twentytwo.parametergame.dataTypes.LevelData;
import be.howest.twentytwo.parametergame.dataTypes.LevelDataI;
import be.howest.twentytwo.parametergame.dataTypes.SettingsDataI;
import be.howest.twentytwo.parametergame.dataTypes.SettingsData;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;


public class JSONFileAccessor implements IFileAccessor{	// CAN SAVE, NOT LOAD
	
	private Json json;
	
	public JSONFileAccessor() {
		json = new Json();
		json.setOutputType(OutputType.minimal);
	}

	public LevelDataI loadLevel(String location) {
		LevelData levelData = null;
		//TODO
		return levelData;
	}
	
	public String readFile(String location) {
		String jsonData = "";
		//location = validatedLocation(location);
		Scanner s = new Scanner(location);
		while(s.hasNext()) {
			jsonData += s.next();
		}
		s.close();
		return jsonData;
	}
	
	public void saveLevel(LevelDataI data, String location) {
		String jsonData = json.prettyPrint(data);
		location = validatedLocation(location);
		try{
			File f = new File(location);
			FileOutputStream fos = new FileOutputStream(f);
			PrintStream ps = new PrintStream(fos);
			ps.print(jsonData);
			ps.close();
			fos.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String[] splitString(String sign) {
		String file = readFile("jsontest.txt");
		String[] pieces = file.split(sign);
		return pieces;
	}
	
	public String validatedLocation(String location) {
		//TODO
		/*
		String[] locationValidator = location.split(".");
		for(String s : locationValidator) {
			System.out.println(s);
		}
		if(locationValidator.length >= 1 && locationValidator[locationValidator.length - 1] != "txt") {
			location += ".txt";
		}
		*/
		return location;
	}
	
	public SettingsDataI loadSettings(String location) {
		SettingsDataI settings = new SettingsData();
		//TODO
		return settings;
	}
	
	public void saveSettings(SettingsDataI settings, String location) {
		//TODO
	}

	@Override
	public Map<String, String> loadKeymap(String location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveKeymap(Map<String, String> keymap, String location) {
		// TODO Auto-generated method stub
		
	}

}
