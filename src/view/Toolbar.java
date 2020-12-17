package view;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import controller.DeleteAction;
import controller.EditAction;
import controller.NewAction;
//Koriscen materijal sa vezbi
public class Toolbar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8730860374137818360L;

	public Toolbar() {
		
		super(SwingConstants.HORIZONTAL);
		
		NewAction na = new NewAction();
		JButton btnNew = new JButton(na);
		btnNew.setToolTipText("Kreiranje entiteta");
		btnNew.setIcon(new ImageIcon("images"+ File.separator +"File_new.png"));
		btnNew.setBackground(Color.WHITE);
		add(btnNew);

		EditAction ea = new EditAction();
		JButton btnEdit = new JButton(ea);
		btnEdit.setToolTipText("Izmena entiteta");
		btnEdit.setIcon(new ImageIcon("images"+ File.separator +"Edit_edit.png"));
		btnEdit.setBackground(Color.WHITE);
		add(btnEdit);

		DeleteAction da = new DeleteAction();
		JButton btnDelete = new JButton(da);
		btnDelete.setToolTipText("Brisanje entiteta");
		btnDelete.setIcon(new ImageIcon("images"+ File.separator +"Edit_delete.png"));
		btnDelete.setBackground(Color.WHITE);
		add(btnDelete);
		
		
		add(Box.createHorizontalGlue());
		
		JTextField textfield = new JTextField(12);
		textfield.setMaximumSize(new Dimension (450,25));
		textfield.setBorder(BorderFactory.createBevelBorder(1));
		add(textfield);
		
		JButton btnSearch = new JButton();
		btnSearch.setToolTipText("Pretraga entiteta");
		btnSearch.setIcon(new ImageIcon("images"+ File.separator +"Search.png"));
		btnSearch.setBackground(Color.WHITE);
		add(btnSearch);
		
		setFloatable(false);
		setBackground (Color.WHITE);

	}
	
}
