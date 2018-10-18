package Application;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopUp extends JPopupMenu {

	private JMenuItem cancel;
	private JMenuItem pickup;

	public PopUp() {
		this.pickup = new JMenuItem("Pickup");
		this.cancel = new JMenuItem("Cancel");
		add(pickup);
		add(cancel);
	}

	public JMenuItem getCancel() {
		return cancel;
	}

	public JMenuItem getPickup() {
		return pickup;
	}

}
