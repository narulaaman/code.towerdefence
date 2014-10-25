package towerdefense.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import towerdefense.gui.MapEditionDialog;
import towerdefense.gui.NewMapDialog;
import ca.concordia.soen6441.logic.GameMap;


/**
 * Action to create a new {@link GameMap} in the {@link MapEditionDialog}
 *
 */
public class NewMapAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7403090617352119267L;
	private static final Icon NEW_ICON = new ImageIcon(Object.class.getResource("/icons/newb.png"));
	private static final String MAP_SIZE_TEXT = "Enter the x and y values";
	
	private final MapEditionDialog mapEditionDialog;

	/**
	 * Constructs a {@link NewMapAction} to edit a map o a given {@link MapEditionDialog}
	 * @param mapEditionDialog {@link MapEditionDialog} to 
	 */
	public NewMapAction(MapEditionDialog mapEditionDialog) {
		super(null, NEW_ICON);
		this.mapEditionDialog = mapEditionDialog;
		setEnabled(true);
	}

	/**
	 * Performs the {@link NewMapAction}
	 * @param e not used - ignored
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		NewMapDialog newMapDialog = new NewMapDialog();
		JOptionPane.showConfirmDialog(null, newMapDialog, MAP_SIZE_TEXT, JOptionPane.OK_CANCEL_OPTION);
		mapEditionDialog.setMap(new GameMap(newMapDialog.getXField(), newMapDialog.getYField()));
		mapEditionDialog.setVisible(true);
    }
}
