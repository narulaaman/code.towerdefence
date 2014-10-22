package ca.concordia.soen6441.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import ca.concordia.soen6441.logic.Tile.TileType;
import ca.concordia.soen6441.logic.primitives.IntCoordinate;

/**
 * This class represents the Tower Defense playable Map. 
 */
public class Map extends Observable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final Tile grid[][];

	final int width;
	final int height;

	private IntCoordinate startTile;
	private IntCoordinate endTile;
	
	// REMOVE THIS HACK

	public List<IntCoordinate> pathCoordinates = new ArrayList<IntCoordinate>();

	public Map(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.grid = new Tile[width][height];

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				grid[x][y] = new Tile(Tile.TileType.SCENERY);
			}
		}
	}

	public void set(Tile tile, IntCoordinate coordinate) {
		grid[coordinate.getX()][coordinate.getY()] = tile;
// TODO: remove this method

	}

	public Tile getTile(int x, int y) {
		return grid[x][y];
	}

	public boolean canPlace(Tower tower) {
		if (hasStartTile()) {
			if (tower.getCoordinate().equals(getStartTile())) {
				return false;
			}
		}
		if (hasEndTile()) {
			if (tower.getCoordinate().equals(getEndTile())) {
				return false;
			}
		}
		int x = tower.getCoordinate().getX();
		int y = tower.getCoordinate().getY();
		Tile tile = grid[x][y];
		return tile.getType() == Tile.TileType.SCENERY;
	}

	public boolean outOfBounds(IntCoordinate coordinate) {
		return coordinate.getX() < 0 || coordinate.getX() > width
				|| coordinate.getY() < 0 || coordinate.getY() > height;
	}

	public void remove(IntCoordinate coordinate) {
		grid[coordinate.getX()][coordinate.getY()] = null;
	}

	public boolean isValid() {
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(grid);
		result = prime * result + height;
		result = prime * result + width;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Map other = (Map) obj;
		if (height != other.height)
			return false;
		if (width != other.width)
			return false;
		if (!Arrays.deepEquals(grid, other.grid))
			return false;
		return true;
	}

	public IntCoordinate getStartTile() {
		return startTile;
	}

	public void setStartTile(IntCoordinate startTile) {
		this.startTile = startTile;
		setChanged();
		notifyObservers();
	}

	public IntCoordinate getEndTile() {
		return endTile;
	}
	
	public void setEndTile(IntCoordinate endTile) {
		this.endTile = endTile;
		setChanged();
		notifyObservers();
	}
	

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setTile(IntCoordinate intCoordinate, TileType tileType) {
		
		if (grid[intCoordinate.getX()][intCoordinate.getY()].type != tileType) {
			grid[intCoordinate.getX()][intCoordinate.getY()] = new Tile(tileType);
			
			setChanged();
			notifyObservers();
		}
	}
	
	public boolean hasStartTile() {
		return startTile != null;
	}
	
	public boolean hasEndTile() {
		return endTile != null;
	}
}
