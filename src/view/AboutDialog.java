package view;

import java.awt.Frame;

import javax.swing.JDialog;

public class AboutDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AboutDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);

		setSize(400, 400);
		setLocationRelativeTo(parent);
	}

}
