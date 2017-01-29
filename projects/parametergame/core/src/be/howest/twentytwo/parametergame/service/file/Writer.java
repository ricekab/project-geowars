package be.howest.twentytwo.parametergame.service.file;

import java.util.ArrayList;
import java.util.List;

import be.howest.twentytwo.parametergame.dataTypes.LevelDataI;

public class Writer { // Temporary test file

	public static void main(String[] args) {

		IFileAccessor ifa = new InMemoryFileAccessor();
		IFileAccessor pfa = new POJOFileAccessor();

		List<String> levels = new ArrayList<String>();
		levels.add("empty_planets.lvl");
		levels.add("arcade1.lvl");
		levels.add("arcade2.lvl");
		levels.add("hcf.lvl");

		for (String level : levels) {
			System.out.println(level);
			LevelDataI memoryLevel = ifa.loadLevel(level);
			System.out.println("planet count: " + memoryLevel.getPlanets().size());
			System.out.println("levels/" + level);
			pfa.saveLevel(memoryLevel, "levels/" + level);
		}
//		 LevelDataI memoryLevel = ifa.loadLevel("level1.lvl");
//		 pfa.saveLevel(memoryLevel, "levels/level1.lvl");
//		 memoryLevel = ifa.loadLevel("level2.lvl");
//		 pfa.saveLevel(memoryLevel, "levels/level2.lvl");
		/*
		 * LevelDataI loadedLevel = pfa.loadLevel("level1.lvl"); boolean equal1 =
		 * memoryLevel.getSpawnBox().getWidth() == loadedLevel.getSpawnBox().getWidth(); boolean
		 * equal2 = memoryLevel.getSpawnBox().getYCoord() == loadedLevel.getSpawnBox().getYCoord();
		 * boolean equal3 = memoryLevel.getWorld().getHeight() ==
		 * loadedLevel.getWorld().getHeight(); boolean equal4 = memoryLevel.getWorld().getXCoord()
		 * == loadedLevel.getWorld().getXCoord();
		 * 
		 * if(equal1 && equal2 && equal3 && equal4){ System.out.println("IT'S WORKING"); }else{
		 * System.out.println("Or maybe not?"); }
		 */

		/*
		 * TODO fix the reading JSONFileAccessor jfa = new JSONFileAccessor(); InMemoryFileAccessor
		 * mfa = new InMemoryFileAccessor(); LevelData fakeData = mfa.loadLevel("something");
		 * jfa.saveLevel(fakeData, "test.txt"); LevelData loadedData = jfa.loadLevel("test.txt");
		 * System.out.println(loadedData);
		 */

		/*
		 * -> This doesn't work. the key to fixing the file accessor is probably in here. <- Json
		 * json = new Json(); json.setOutputType(OutputType.json); TestData td = new TestData();
		 * String tdJson = json.toJson(td, TestData.class);
		 * 
		 * try{ String location = "simpleSaveTest.txt"; File file = new File(location);
		 * FileOutputStream fos = new FileOutputStream(file); PrintStream ps = new PrintStream(fos);
		 * ps.print(tdJson); Scanner s = new Scanner(location); String loadedTD = "";
		 * while(s.hasNext()) { loadedTD += s.nextLine(); }
		 * 
		 * System.out.println("\nTD as JSON: " + tdJson); System.out.println("\nLoadedString: " +
		 * loadedTD); System.out.println("\nEqual: " + loadedTD.equals(tdJson));
		 * 
		 * } catch(Exception e) { e.printStackTrace(); }
		 */

		/*
		 * try {
		 * 
		 * JSONFileAccessor jfa = new JSONFileAccessor(); POJOFileAccessor pfa = new
		 * POJOFileAccessor(); InMemoryFileAccessor imfa = new InMemoryFileAccessor(); LevelDataI
		 * testData = imfa.loadLevel("someString"); String location = "jsontest.txt";
		 * jfa.saveLevel(testData, location); pfa.saveLevel(testData, location);
		 * 
		 * String file = jfa.readFile(location); System.out.println(file);
		 * 
		 * }catch(Exception e) { e.printStackTrace(); } /*
		 */

		/*
		 * JSONFileAccessor jfa = new JSONFileAccessor(); Json json = new Json();
		 * InMemoryFileAccessor imfa = new InMemoryFileAccessor(); LevelData testData =
		 * imfa.loadLevel("someString"); jfa.saveLevel(testData, "jsontest.txt"); LevelData
		 * levelData = jfa.loadLevel("jsontest.txt"); System.out.println(json.toJson(testData));
		 * System.out.println(json.toJson(levelData));
		 */
	}
}
