package ca.concordia.soen6441.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import ca.concordia.soen6441.logic.Map;

public class MapJavaSerializationPersister implements MapPersister {
	
	/* (non-Javadoc)
	 * @see ca.concordia.soen6441.io.IMapPersister#save(ca.concordia.soen6441.logic.Map, java.io.File)
	 */
	
	public static final String MAP_FILENAME_EXTENSION = ".map";
	
	@Override
	public void save(Map map, String mapname) throws IOException {
		File file = new File("." + File.pathSeparator + mapname + MAP_FILENAME_EXTENSION);
		FileOutputStream outPut = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(outPut);
		oos.writeObject(map);
		oos.close();
	}
	
	/* (non-Javadoc)
	 * @see ca.concordia.soen6441.io.IMapPersister#load(java.io.File)
	 */
	@Override
	public Map load(String mapname) throws IOException, ClassNotFoundException {
		File file = new File("." + File.pathSeparator + mapname + MAP_FILENAME_EXTENSION);
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Map map = (Map) ois.readObject();
		ois.close();
		return map;
	}

	@Override
	public List<String> listAllNames() throws IOException {
		File directory = new File(".");
		List<String> fileList = new ArrayList<>();
 		for (File file : directory.listFiles())
		{
			if (file.isFile() && file.getName().endsWith(MAP_FILENAME_EXTENSION)) {
				String filename = file.getName();
				String mapName = filename.substring(0, filename.indexOf(MAP_FILENAME_EXTENSION));
				fileList.add(mapName);
			}
		}
		return fileList;
	}
	
}
