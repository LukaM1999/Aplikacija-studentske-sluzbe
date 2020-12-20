package view;

import java.awt.Frame;

import javax.swing.JDialog;

public class DodajStudentaDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5241927591473531655L;
	
	public DodajStudentaDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);

		setSize(400, 400);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

}
