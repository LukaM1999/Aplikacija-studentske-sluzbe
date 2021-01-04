package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import controller.OcenaController;
import controller.PredmetController;
import controller.ProfesorController;
import controller.StudentController;
import model.Ocena;
import model.Student;
//Koriscen materijal sa vezbi
public class Toolbar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8730860374137818360L;

	public Toolbar() {
		
		super(SwingConstants.HORIZONTAL);
		
		
		JButton btnNew = new JButton();
		btnNew.setToolTipText("Kreiranje entiteta");
		btnNew.setIcon(new ImageIcon("images"+ File.separator +"File_new.png"));
		btnNew.setBackground(Color.WHITE);
		btnNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Tabs.getInstance().getSelectedIndex() == 0) {
					DodajStudentaDialog studentDialog = new DodajStudentaDialog(MainFrame.getInstance(),
							"Dodavanje studenta", true);
					studentDialog.setVisible(true);
				}
				if (Tabs.getInstance().getSelectedIndex() == 1) {
					DodajProfesoraDialog profesorDialog = new DodajProfesoraDialog(MainFrame.getInstance(),
							"Dodavanje profesora", true);
					profesorDialog.setVisible(true);
				}
				if (Tabs.getInstance().getSelectedIndex() == 2) {
					DodajPredmetDialog predmetDialog = new DodajPredmetDialog(MainFrame.getInstance(),
							"Dodavanje predmeta", true);
					predmetDialog.setVisible(true);
				}
			
			}
		});
		add(btnNew);
		

		JButton btnEdit = new JButton();
		btnEdit.setToolTipText("Izmena entiteta");
		btnEdit.setIcon(new ImageIcon("images"+ File.separator +"Edit_edit.png"));
		btnEdit.setBackground(Color.WHITE);
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Tabs.getInstance().getSelectedIndex() == 0) {
					if(TableStudent.getInstance().getSelectedRow()>=0) {
						IzmeniStudentaDialog studentDialog = new IzmeniStudentaDialog(MainFrame.getInstance(),
								"Izmena studenta", true);
						studentDialog.setVisible(true);
					}
				}	
				if (Tabs.getInstance().getSelectedIndex() == 1) {
					if(TableProfesor.getInstance().getSelectedRow()>=0) {
						IzmeniProfesoraDialog profesorDialog = new IzmeniProfesoraDialog(MainFrame.getInstance(),
								"Izmena profesora", true);
						profesorDialog.setVisible(true);
					}
				}
				if (Tabs.getInstance().getSelectedIndex() == 2) {
					if(TablePredmet.getInstance().getSelectedRow()>=0) {
					IzmeniPredmetDialog predmetDialog = new IzmeniPredmetDialog(MainFrame.getInstance(),
							"Izmena predmeta", true);
						predmetDialog.setVisible(true);
					}
				}	
			
			}
		});
		add(btnEdit);

		
		JButton btnDelete = new JButton();
		btnDelete.setToolTipText("Brisanje entiteta");
		btnDelete.setIcon(new ImageIcon("images"+ File.separator +"Edit_delete.png"));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
					if (Tabs.getInstance().getSelectedIndex() == 0) {
						
						if(TableStudent.getInstance().getSelectedRow()>=0) {
						
							int opcija = JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Da li ste sigurni da želite da obrišete studenta?", 
																	"Brisanje studenta", JOptionPane.YES_NO_OPTION);
							if(opcija == 0) {
								String indeks = (String) TableStudent.getInstance()
										.getValueAt(TableStudent.getInstance().getSelectedRow(), 0);

								Iterator<Ocena> itO = OcenaController.getInstance().getOcene().iterator();
								while (itO.hasNext()) {
									Ocena o = itO.next();
									if (o.getStudent().getBrIndeksa().equals(indeks)) {
										itO.remove();
									}
								}

					// REFERENCE:
					// https://stackoverflow.com/questions/602636/why-is-a-concurrentmodificationexception-thrown-and-how-to-debug-it
								Iterator<Student> it = StudentController.getInstance().getStudenti().iterator();
								while (it.hasNext()) {
									Student s = it.next();
									if (s.getBrIndeksa().equals(indeks)) {
										it.remove();
										break;
									}
								}

								MainFrame.getInstance().azurirajStudente("Uklonjen", -1);

							}				
						}
					}
					
					if (Tabs.getInstance().getSelectedIndex() == 1) { 
						
						if(TableProfesor.getInstance().getSelectedRow()>=0) {
							
							int opcija = JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Da li ste sigurni da želite da obrišete profesora?", 
								  									"Brisanje profesora", JOptionPane.YES_NO_OPTION);
							if(opcija == 0) {
								ProfesorController.getInstance().izbrisiProfesora(TableProfesor.getInstance().getSelectedRow());
							} 						
						} 
					}  					  
					
					if (Tabs.getInstance().getSelectedIndex() == 2) { 
							
						if(TablePredmet.getInstance().getSelectedRow()>=0) {
							
							int opcija = JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Da li ste sigurni da želite da obrišete predmet?", 
																	"Brisanje predmeta", JOptionPane.YES_NO_OPTION);
							if(opcija == 0) {
								PredmetController.getInstance().izbrisiPredmet(TablePredmet.getInstance().getSelectedRow());
							} 
						}					 
					}
					
			}
});

		add(btnDelete);
		
		
		add(Box.createHorizontalGlue());
		
		JTextField textfield = new JTextField(12);
		textfield.setMaximumSize(new Dimension (450,25));
		add(textfield);
		
		JButton btnSearch = new JButton();
		btnSearch.setToolTipText("Pretraga entiteta");
		btnSearch.setIcon(new ImageIcon("images"+ File.separator +"Search.png"));
		btnSearch.setBackground(Color.WHITE);
		add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Tabs.getInstance().getSelectedIndex() == 0) {
					
				}	
				if (Tabs.getInstance().getSelectedIndex() == 1) {
					
				}
				if (Tabs.getInstance().getSelectedIndex() == 2) {
					
				}	
			
			}
		});
		
		setFloatable(false);
		setBackground(Color.WHITE);

	}
	
}
