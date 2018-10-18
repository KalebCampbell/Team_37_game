package Application;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * Object used for door click pop up menu
 * 
 * @author royalliam
 *
 */
public class DoorPopUp extends JPopupMenu {

	private JMenuItem unlock;
	private JMenuItem cancel;

	public DoorPopUp() {
		this.unlock = new JMenuItem("Unlock");
		this.cancel = new JMenuItem("Cancel");
		add(unlock);
		add(cancel);
	}

	public JMenuItem getCancel() {
		return cancel;
	}

	public JMenuItem getDrop() {
		return unlock;
	}

}
