package be.howest.twentytwo.parametergame.service.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import be.howest.twentytwo.parametergame.dataTypes.LevelData;

public class POJOFileAccessor implements IFileAccessor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * be.howest.twentytwo.parametergame.service.file.IFileAccessor#loadLevel(
	 * java.lang.String) Loads the level file.
	 * 
	 * @param location location of the file. this consists out of the path of
	 * the file and the file name + extention
	 * 
	 * @return returns a LevelData object, or null if no data was present.
	 */
	@Override
	public LevelData loadLevel(String location) {
		LevelData data = null; // TODO load a default level
		try {
			File f = new File(location);
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			data = (LevelData) ois.readObject();
		} catch (FileNotFoundException fe) {
			System.out.println("Could not locate file, please check the location & extention");
			fe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public void saveLevel(LevelData data, String location) {

		try {
			File f = new File(location);
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}