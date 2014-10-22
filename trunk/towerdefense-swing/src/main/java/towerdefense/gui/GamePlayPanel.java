package towerdefense.gui;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import towerdefense.gui.MapPanel.MapGridCoordinateClickedListener;
import ca.concordia.soen6441.logic.GamePlay;
import ca.concordia.soen6441.logic.GameMap;
import ca.concordia.soen6441.logic.Tower;
import ca.concordia.soen6441.logic.primitives.GridPosition;

public class GamePlayPanel extends JPanel implements Observer, MapGridCoordinateClickedListener {
	
	public interface TowerSelectedListener {
		void towerSelected(Tower tower);
	}

	private static final Image TOWER_ICON = new ImageIcon(Object.class.getResource("/icons/tower.png")).getImage();
	private final MapPanel mapPanel = new MapPanel();
	private final GamePlay gamePlay;
	private MapGridCoordinateClickedListener mapGridCoordinateClickedListener;
	private TowerSelectedListener towerSelectedListener;
	public GamePlayPanel(GamePlay gamePlay) {
		super(new GridBagLayout());
		this.gamePlay = gamePlay;
		gamePlay.addObserver(this);
		mapPanel.addMapGridCoordinateClickedListener(this);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = constraints.weighty = 1.0;
		mapPanel.setMap(gamePlay.getMap());
		add(mapPanel, constraints);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for (Tower tower : getGamePlay().getTowers()) {
			g.drawImage(TOWER_ICON, tileToScreenX(tower.getGridPosition().getX()),
					tileToScreenY(tower.getGridPosition().getY()), getTileWidth(), getTileHeight(),
					this);
		}
		
		
		
	}
	
	@Override
	public void mapGridCoordinateClicked(GridPosition gridPosition) {
		if (getGamePlay().hasTower(gridPosition))
		{
			fireTowerSelected(getGamePlay().getTower(gridPosition));
		}
		else {
			fireMapGridCoordinateClickedListener(gridPosition);
		}
	}
	
	
	int tileToScreenX(int x) {
		return mapPanel.tileToScreenX(x);
	}

	int tileToScreenY(int y) {
		return mapPanel.tileToScreenY(y);
	}
	
	int getTileWidth() {
		return mapPanel.getTileWidth();
	}

	int getTileHeight() {
		return mapPanel.getTileHeight();
	}
	
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("HelloWorldSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add the ubiquitous "Hello World" label.

		GameMap gameMap = new GameMap(9, 9);

		GamePlay level = new GamePlay(gameMap, 1000);
		GamePlayPanel gamePanel = new GamePlayPanel(level);

		frame.getContentPane().add(gamePanel);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	private void fireTowerSelected(Tower tower) {
		if (towerSelectedListener != null) {
			towerSelectedListener.towerSelected(tower);
		}
	}
	
	private void fireMapGridCoordinateClickedListener(GridPosition coord) {
		if (mapGridCoordinateClickedListener != null) {
			mapGridCoordinateClickedListener.mapGridCoordinateClicked(coord);
		}
	}

	public GamePlay getGamePlay() {
		return gamePlay;
	}
	
	public void setMapGridCoordinateClickedListener(MapGridCoordinateClickedListener listener) {
		this.mapGridCoordinateClickedListener = listener;
	}
	
	public void setTowerSelectedListener(TowerSelectedListener listener) {
		this.towerSelectedListener = listener;
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}