package view;

import java.awt.Frame;

import javax.swing.JDialog;

public class HelpDialog extends JDialog {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7532094756704393899L;

	public HelpDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);

		setSize(400, 400);
		setLocationRelativeTo(parent);
	}

}
