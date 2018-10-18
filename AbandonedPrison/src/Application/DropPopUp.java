package Application;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class DropPopUp extends JPopupMenu {

	private JMenuItem drop;
	private JMenuItem cancel;

	public DropPopUp() {
		this.drop = new JMenuItem("Drop");
		this.cancel = new JMenuItem("Cancel");
		add(drop);
		add(cancel);
	}

	public JMenuItem getCancel() {
		return cancel;
	}

	public JMenuItem getDrop() {
		return drop;
	}

}
