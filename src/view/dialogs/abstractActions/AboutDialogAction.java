package view.dialogs.abstractActions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

//Uz pomoc materijala sa vezbi

/**
 * Klasa predstavlja apstraktnu akciju za dijalog
 * informacija o programu.
 * 
 * @author Luka Miletić
 *
 */
public class AboutDialogAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Prazan konstruktor apstraktne akcije za About dijalog, u kom
	 * se postavlja naziv dugmeta, ikonica i vezuje se akcelerator.
	 */
	@SuppressWarnings("deprecation")
	public AboutDialogAction() {
		putValue(NAME, "About");
		putValue(SMALL_ICON, new ImageIcon("images" + File.separator + "Help_about.png"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
