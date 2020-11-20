package domaci1;

import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class Toolbar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8730860374137818360L;

	public Toolbar() {
		
		super(SwingConstants.HORIZONTAL);
				
		JButton btnNew = new JButton();
		btnNew.setToolTipText("Kreiranje entiteta");
		btnNew.setIcon(new ImageIcon("images/File_new.png"));
		btnNew.setBackground(Color.WHITE);
		//btnNew.setMnemonic(KeyEvent.VK_N);
		add(btnNew);

		
		JButton btnEdit = new JButton();
		btnEdit.setToolTipText("Izmena entiteta");
		btnEdit.setIcon(new ImageIcon("images/Edit_edit.png"));
		btnEdit.setBackground(Color.WHITE);
		add(btnEdit);

		JButton btnDelete = new JButton();
		btnDelete.setToolTipText("Brisanje entiteta");
		btnDelete.setIcon(new ImageIcon("images/Edit_delete.png"));
		btnDelete.setBackground(Color.WHITE);
		add(btnDelete);

		JButton btnSearch = new JButton();
		btnSearch.setToolTipText("Pretraga entiteta");
		btnSearch.setIcon(new ImageIcon("images/Search.png"));
		btnSearch.setBackground(Color.WHITE);
		add(btnSearch);
		
		setFloatable(false);
		setBackground (Color.WHITE);

	}
	
}
