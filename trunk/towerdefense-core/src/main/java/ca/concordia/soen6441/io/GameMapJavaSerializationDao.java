package ca.concordia.soen6441.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import ca.concordia.soen6441.logic.GameMap;

public class GameMapJavaSerializationDao implements GameMapDao {
	

	
	public static final String MAP_FILENAME_EXTENSION = ".map";
	public static final String FILENAME_STRING_FORMAT = "." + File.separator + "%s" + MAP_FILENAME_EXTENSION;
	

	/* (non-Javadoc)
	 * @see ca.concordia.soen6441.io.gameMapDao#save(ca.concordia.soen6441.logic.GameMap, java.lang.String)
	 */
	@Override
	public void save(GameMap gameMap, String mapname) throws IOException {
		File file = new File(String.format(FILENAME_STRING_FORMAT, mapname));
		FileOutputStream outPut = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(outPut);
		oos.writeObject(gameMap);
		oos.close();
	}
	
	/* (non-Javadoc)
	 * @see ca.concordia.soen6441.io.IgameMapDao#load(java.io.File)
	 */
	@Override
	public GameMap load(String mapname) throws IOException, ClassNotFoundException {
		File file = new File(String.format(FILENAME_STRING_FORMAT, mapname));
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		GameMap gameMap = (GameMap) ois.readObject();
		ois.close();
		fis.close();
		return gameMap;
	}

	/* (non-Javadoc)
	 * @see ca.concordia.soen6441.io.gameMapDao#listAllNames()
	 */
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