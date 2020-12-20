package view;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

//Koriscen materijal sa vezbi
public class MyMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5986249145568966369L;

	public MyMenuBar() {
		JMenu File_m = new JMenu("File");
		File_m.setMnemonic(KeyEvent.VK_F);
		JMenu Edit_m = new JMenu("Edit");
		Edit_m.setMnemonic(KeyEvent.VK_E);
		JMenu Help_m = new JMenu("Help");
		Help_m.setMnemonic(KeyEvent.VK_H);
		

		
		JMenuItem New_mi = new JMenuItem("New", new ImageIcon("images" + File.separator +  "File_new.png"));
		New_mi.setBackground(Color.WHITE);
		New_mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		New_mi.setMnemonic(KeyEvent.VK_N);
		New_mi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Tabs.getTabIndex() == 0) {
					DodajStudentaDialog studentDialog = new DodajStudentaDialog(MainFrame.getInstance(),
							"Dodavanje studenta", true);
					studentDialog.setVisible(true);
				}
				if (Tabs.getTabIndex() == 1) {
					DodajProfesoraDialog profesorDialog = new DodajProfesoraDialog(MainFrame.getInstance(),
							"Dodavanje profesora", true);
					profesorDialog.setVisible(true);
				}
				if (Tabs.getTabIndex() == 2) {
					DodajPredmetDialog predmetDialog = new DodajPredmetDialog(MainFrame.getInstance(),
							"Dodavanje predmeta", true);
					predmetDialog.setVisible(true);
				}
			
			}
		});

		
		JMenuItem Close_mi = new JMenuItem("Close", new ImageIcon("images" + File.separator + "File_close.png"));
		Close_mi.setBackground(Color.WHITE);
		Close_mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		Close_mi.setMnemonic(KeyEvent.VK_C);

		
		JMenuItem Edit_mi = new JMenuItem("Edit", new ImageIcon("images" + File.separator + "Edit_edit.png"));
		Edit_mi.setBackground(Color.WHITE);
		Edit_mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		Edit_mi.setMnemonic(KeyEvent.VK_T);

		
		JMenuItem Delete_mi = new JMenuItem("Delete", new ImageIcon("images" + File.separator + "Edit_delete.png"));
		Delete_mi.setBackground(Color.WHITE);
		Delete_mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		Delete_mi.setMnemonic(KeyEvent.VK_D);

		
		HelpDialogAction hda = new HelpDialogAction();
		JMenuItem Help_mi = new JMenuItem(hda);
		Help_mi.setBackground(Color.WHITE);
		Help_mi.setMnemonic(KeyEvent.VK_P);
		Help_mi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				HelpDialog helpDialog = new HelpDialog(MainFrame.getInstance(), "Help", false);
				helpDialog.setVisible(true);

			}
		});
	

		AboutDialogAction ada = new AboutDialogAction();
		JMenuItem About_mi = new JMenuItem(ada);
		About_mi.setBackground(Color.WHITE);
		About_mi.setMnemonic(KeyEvent.VK_A);
		About_mi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AboutDialog aboutDialog = new AboutDialog(MainFrame.getInstance(), "About", true);
				aboutDialog.setVisible(true);

			}
		});

		
		File_m.add(New_mi);
		File_m.addSeparator();
		File_m.add(Close_mi);
		Edit_m.add(Edit_mi);
		Edit_m.addSeparator();
		Edit_m.add(Delete_mi);
		Help_m.add(Help_mi);
		Help_m.addSeparator();
		Help_m.add(About_mi);
		
		add(File_m);
		add(Edit_m);
		add(Help_m);
	}

}
