package view;

import java.awt.Frame;

import javax.swing.JDialog;

public class DodajProfesoraDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6293346707131160182L;
	
	public DodajProfesoraDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);

		setSize(400, 400);
		setLocationRelativeTo(parent);
	}
}
