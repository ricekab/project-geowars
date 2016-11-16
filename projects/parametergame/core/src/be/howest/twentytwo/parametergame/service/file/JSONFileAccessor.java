package be.howest.twentytwo.parametergame.service.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import com.badlogic.gdx.utils.Json;

import be.howest.twentytwo.parametergame.dataTypes.LevelData;

public class JSONFileAccessor implements IFileAccessor{
	
	private Json json;
	
	public JSONFileAccessor() {
		json = new Json();
	}

	@Override
	public LevelData loadLevel(String location) {
		String jsonData = "";
		location = validatedLocation(location);
		Scanner s = new Scanner(location);
		while(s.hasNext()){
			jsonData += s.next();
		}
		System.out.println(json.fromJson(LevelData.class, jsonData));	//TODO study how the fromJson works, and fix this
		LevelData levelData = json.fromJson(LevelData.class, jsonData);
		s.close();
		return levelData;
	}
	
	public void saveLevel(LevelData data, String location) {
		String jsonData = json.toJson(data, LevelData.class);
		location = validatedLocation(location);
		try{
			File f = new File(location);
			FileOutputStream fos = new FileOutputStream(f);
			PrintStream ps = new PrintStream(fos);
			//System.out.println(jsonData);
			ps.print(jsonData);
			//System.out.println("succes writing your file to " + location);
			ps.close();
			fos.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String validatedLocation(String location) {
		String[] locationValidator = location.split(".txt");
		if(locationValidator[locationValidator.length - 1] != ".txt") {
			//location += "/LevelSave.txt";
		}
		return location;
	}

}
