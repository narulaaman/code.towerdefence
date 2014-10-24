import towerdefense.gui.MapEditionDialog;
import towerdefense.gui.StartGameDialog;
import towerdefense.gui.actions.MapEditAction;
import towerdefense.gui.actions.NewMapAction;
import towerdefense.gui.actions.SaveMapAction;
import towerdefense.gui.actions.StartGamePlayAction;
import ca.concordia.soen6441.io.GameMapJavaSerializationDao;
import ca.concordia.soen6441.io.GameMapDao;



/**
 * This is the class to be Run to start our TowerDefense game
 */
public class RunMe {


	/**
	 * Created the GUI, this should be run in the EDT
	 */
	
	
	private static void createAndShowGUI() {
		GameMapDao gameMapDao = new GameMapJavaSerializationDao(); 
		MapEditionDialog mapEditionDialog = new MapEditionDialog(gameMapDao);
		mapEditionDialog.setVisible(false);
		MapEditAction mapEditAction = new MapEditAction(mapEditionDialog);
		StartGamePlayAction startGamePlayAction = new StartGamePlayAction();
		NewMapAction newMapAction = new NewMapAction(mapEditionDialog);
		
		StartGameDialog startPanel = new StartGameDialog(gameMapDao,newMapAction ,mapEditAction, startGamePlayAction);
        SaveMapAction saveMapAction = new SaveMapAction(mapEditionDialog, startPanel);
        mapEditionDialog.getSaveButton().addActionListener(saveMapAction);
        startPanel.setVisible(true);
    }
 
    /**
     * main method our application
     * @param args arguments passed by the environment, they are ignored
     */
    public static void main(String[] args) {
    	// Run the GUI in the EDT thread
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
