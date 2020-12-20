package view;

import java.awt.Frame;

import javax.swing.JDialog;

public class DodajPredmetDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1340009763757734392L;
	
	public DodajPredmetDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);

		setSize(400, 400);
		setLocationRelativeTo(parent);
	}

}
