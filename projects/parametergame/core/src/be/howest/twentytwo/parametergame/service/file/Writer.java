package be.howest.twentytwo.parametergame.service.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter.OutputType;

import be.howest.twentytwo.parametergame.dataTypes.LevelData;
import be.howest.twentytwo.parametergame.dataTypes.TestData;

public class Writer {	//Temporary test file

	public static void main(String[] args){
		/*
		->	This works	<-
		Json json = new Json();
		List<Integer> original = new ArrayList<>();
		for(int i = 1; i <= 10; i++){original.add(i);}
		System.out.println(original);
		String jsonString = json.toJson(original);
		List<Integer> loaded = json.fromJson(List.class, jsonString);
		System.out.println(loaded);
		System.out.println(loaded.equals(original)); 
		 */
		/*
		 * TODO fix the reading
		JSONFileAccessor jfa = new JSONFileAccessor();
		InMemoryFileAccessor mfa = new InMemoryFileAccessor();
		LevelData fakeData = mfa.loadLevel("something");
		jfa.saveLevel(fakeData, "test.txt");
		LevelData loadedData = jfa.loadLevel("test.txt");
		System.out.println(loadedData);
		*/

		/*
		->	This doesn't work. the key to fixing the file accessor is probably in here.	<-
		Json json = new Json();
		json.setOutputType(OutputType.json);
		TestData td = new TestData();
		String tdJson = json.toJson(td, TestData.class);
		
		try{
			String location = "simpleSaveTest.txt";
			File file = new File(location);
			FileOutputStream fos = new FileOutputStream(file);
			PrintStream ps = new PrintStream(fos);
			ps.print(tdJson);
			Scanner s = new Scanner(location);
			String loadedTD = "";
			while(s.hasNext()) {
				loadedTD += s.nextLine();
			}
			
			System.out.println("\nTD as JSON: " + tdJson);
			System.out.println("\nLoadedString: " + loadedTD);
			System.out.println("\nEqual: " + loadedTD.equals(tdJson));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		*/
		
		/*
		try {
		
		==> IT WORKS!!
		
			POJOFileAccessor pfa = new POJOFileAccessor();
			InMemoryFileAccessor imfa = new InMemoryFileAccessor();
			LevelData testData = imfa.loadLevel("someString");
			String location = "POJOTest.txt";
			
			pfa.saveLevel(testData, location);
			LevelData loadedData = pfa.loadLevel(location);
			
			System.out.println("equal: " + testData.equals(loadedData));	//.equals needs to be overridden
			
			//Writing stuff as text to make it readable
			
			Json json = new Json();
			System.out.println(json.toJson(testData));
			System.out.println(json.toJson(loadedData));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		*/
		
		JSONFileAccessor jfa = new JSONFileAccessor();
		InMemoryFileAccessor imfa = new InMemoryFileAccessor();
		LevelData testData = imfa.loadLevel("someString");
		
		
	}
	
}







