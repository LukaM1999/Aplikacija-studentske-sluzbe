package domaci1;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
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
		
		
		add(Box.createHorizontalGlue());
		
		JTextField textfield = new JTextField(12);
		textfield.setMaximumSize(new Dimension (450,25));
		textfield.setBorder(BorderFactory.createBevelBorder(1));
		add(textfield);
		
		JButton btnSearch = new JButton();
		btnSearch.setToolTipText("Pretraga entiteta");
		btnSearch.setIcon(new ImageIcon("images/Search.png"));
		btnSearch.setBackground(Color.WHITE);
		add(btnSearch);
		
		setFloatable(false);
		setBackground (Color.WHITE);

	}
	
}
