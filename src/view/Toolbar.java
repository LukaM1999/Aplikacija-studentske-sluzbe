package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import java.util.regex.PatternSyntaxException;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;

import controller.OcenaController;
import controller.PredmetController;
import controller.ProfesorController;
import controller.StudentController;
import model.Ocena;
import model.Predmet;
import model.Profesor;
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
								Iterator<Student> itS = StudentController.getInstance().getStudenti().iterator();
								while (itS.hasNext()) {
									Student s = itS.next();
									if (s.getBrIndeksa().equals(indeks)) {
										itS.remove();
										break;
									}
								}
								
								Iterator<Predmet> itP = PredmetController.getInstance().getPredmeti().iterator();
								while (itP.hasNext()) {
									Predmet p = itP.next();
									Iterator<Student> itStudent = p.getPolozili().iterator();
									while(itStudent.hasNext()) {
										Student s = itStudent.next();
										if(s.getBrIndeksa().equals(indeks)) {
											itStudent.remove();
											break;
										}
									}
								}
								
								while (itP.hasNext()) {
									Predmet p = itP.next();
									Iterator<Student> itStudent = p.getNepolozeni().iterator();
									while(itStudent.hasNext()) {
										Student s = itStudent.next();
										if(s.getBrIndeksa().equals(indeks)) {
											itStudent.remove();
											break;
										}
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
								
								Profesor prof = ProfesorController.getInstance().getProfesor(TableProfesor.getInstance().getSelectedRow());
								String licna = prof.getBrLicneKarte();


					// REFERENCE:
					// https://stackoverflow.com/questions/602636/why-is-a-concurrentmodificationexception-thrown-and-how-to-debug-it
								Iterator<Profesor> itP = ProfesorController.getInstance().getProfesori().iterator();
								while (itP.hasNext()) {
									Profesor p = itP.next();
									if (p.getBrLicneKarte().equals(licna)) {
										itP.remove();
										break;
									}
								}
								
								Iterator<Predmet> itPredmet = PredmetController.getInstance().getPredmeti().iterator();
								while (itPredmet.hasNext()) {
									Predmet p = itPredmet.next();
									if(p.getProfesor().getBrLicneKarte().equals(licna)) {
										p.setProfesor(null);
									}		
								}
								

								MainFrame.getInstance().azurirajProfesore("Uklonjen", -1);

							
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
					String[] text = textfield.getText().split(" ");
					if (text.length == 0) {
						TableStudent.getInstance().getSorter().setRowFilter(null);
					} else {
						if (text.length == 1) {
							try {
								TableStudent.getInstance().getSorter().setRowFilter(
										RowFilter.regexFilter("(?i)" + "(^.*" + text[0] + ".*$)", 2));
							} catch (PatternSyntaxException pse) {
								System.out.println("Bad regex pattern");
							}
						} else {
							if (text.length == 2) {

								// REFERNCE:
								// https://stackoverflow.com/questions/5194948/java-swing-combine-rowfilter-andfilter-with-rowfilter-orfilter
								try {
									TableStudent.getInstance().getFilters().add(
											RowFilter.regexFilter("(?i)" + "(^.*" + text[0] + ".*$)", 2));
									TableStudent.getInstance().getFilters().add(
											RowFilter.regexFilter("(?i)" + "(^.*" + text[1] + ".*$)", 1));
									TableStudent.getInstance().getSorter()
											.setRowFilter(RowFilter.andFilter(TableStudent.getInstance().getFilters()));

								} catch (PatternSyntaxException pse) {
									System.out.println("Bad regex pattern");
								}

								for (int i = 0; i < TableStudent.getInstance().getFilters().size(); i++) {
									TableStudent.getInstance().getFilters().remove(i);
								}
							} else {
								try {
									TableStudent.getInstance().getFilters().add(
											RowFilter.regexFilter("(?i)" + "(^.*" + text[0] + ".*$)", 2));
									TableStudent.getInstance().getFilters().add(
											RowFilter.regexFilter("(?i)" + "(^.*" + text[1] + ".*$)", 1));
									TableStudent.getInstance().getFilters().add(
											RowFilter.regexFilter("(?i)" + "(^.*" + text[2] + ".*$)", 0));
									TableStudent.getInstance().getSorter()
											.setRowFilter(RowFilter.andFilter(TableStudent.getInstance().getFilters()));

								} catch (PatternSyntaxException pse) {
									System.out.println("Bad regex pattern");
								}
								for (int i = 0; i < TableStudent.getInstance().getFilters().size(); i++) {
									TableStudent.getInstance().getFilters().remove(i);
								}
							}
						}
					}
					
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
