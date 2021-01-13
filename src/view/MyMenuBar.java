package view;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import controller.OcenaController;
import controller.PredmetController;
import controller.ProfesorController;
import controller.StudentController;
import model.Ocena;
import model.Predmet;
import model.Profesor;
import model.Student;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Iterator;

//Koriscen materijal sa vezbi
public class MyMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5986249145568966369L;

	public MyMenuBar() {
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic(KeyEvent.VK_E);
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);

		JMenuItem newItem = new JMenuItem("New", new ImageIcon("images" + File.separator + "File_new.png"));
		newItem.setBackground(Color.WHITE);
		newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newItem.setMnemonic(KeyEvent.VK_N);
		newItem.addActionListener(new ActionListener() {

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

		JMenuItem closeItem = new JMenuItem("Close", new ImageIcon("images" + File.separator + "File_close.png"));
		closeItem.setBackground(Color.WHITE);
		closeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		closeItem.setMnemonic(KeyEvent.VK_C);
		closeItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane confirm = new JOptionPane();
				@SuppressWarnings("static-access")
				int answer = confirm.showConfirmDialog(MainFrame.getInstance(),
						"Da li ste sigurni da želite da napustite aplikaciju?", "Potvrda izlaska",
						JOptionPane.OK_CANCEL_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					//serijalizacija uradjena uz pomoc vezbi - XStream primer
					StudentController.getInstance().XstreamSerialization("deserijalizacija" + File.separator + "studenti.xml");
					ProfesorController.getInstance().XstreamSerialization("deserijalizacija" + File.separator + "profesori.xml"); 
					PredmetController.getInstance().XstreamSerialization("deserijalizacija" + File.separator + "predmeti.xml"); 
					OcenaController.getInstance().XstreamSerialization("deserijalizacija" + File.separator + "ocene.xml");
					System.exit(0);
				}

			}
		});

		JMenuItem editItem = new JMenuItem("Edit", new ImageIcon("images" + File.separator + "Edit_edit.png"));
		editItem.setBackground(Color.WHITE);
		editItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		editItem.setMnemonic(KeyEvent.VK_E);
		editItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Tabs.getInstance().getSelectedIndex() == 0) {
					if (TableStudent.getInstance().getSelectedRow() >= 0) {
						IzmeniStudentaDialog studentDialog = new IzmeniStudentaDialog(MainFrame.getInstance(),
								"Izmena studenta", true);
						studentDialog.setVisible(true);
					}
				}
				if (Tabs.getInstance().getSelectedIndex() == 1) {
					if (TableProfesor.getInstance().getSelectedRow() >= 0) {
						IzmeniProfesoraDialog profesorDialog = new IzmeniProfesoraDialog(MainFrame.getInstance(),
								"Izmena profesora", true);
						profesorDialog.setVisible(true);
					}
				}
				if (Tabs.getInstance().getSelectedIndex() == 2) {
					if (TablePredmet.getInstance().getSelectedRow() >= 0) {
						IzmeniPredmetDialog predmetDialog = new IzmeniPredmetDialog(MainFrame.getInstance(),
								"Izmena predmeta", true);
						predmetDialog.setVisible(true);
					}
				}
			}
		});

		JMenuItem deleteItem = new JMenuItem("Delete", new ImageIcon("images" + File.separator + "Edit_delete.png"));
		deleteItem.setBackground(Color.WHITE);
		deleteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		deleteItem.setMnemonic(KeyEvent.VK_D);
		deleteItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Tabs.getInstance().getSelectedIndex() == 0) {
					if (TableStudent.getInstance().getSelectedRow() >= 0) {
						int opcija = JOptionPane.showConfirmDialog(MainFrame.getInstance(),
								"Da li ste sigurni da želite da obrišete studenta?", "Brisanje studenta",
								JOptionPane.YES_NO_OPTION);
						if (opcija == 0) {

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

							Iterator<Predmet> itP = PredmetController.getInstance().getPredmeti().iterator();
							while (itP.hasNext()) {
								Predmet p = itP.next();
								Iterator<Student> itStudent = p.getPolozili().iterator();
								while (itStudent.hasNext()) {
									Student s = itStudent.next();
									if (s.getBrIndeksa().equals(indeks)) {
										itStudent.remove();
										break;
									}
								}
							}

							while (itP.hasNext()) {
								Predmet p = itP.next();
								Iterator<Student> itStudent = p.getNepolozeni().iterator();
								while (itStudent.hasNext()) {
									Student s = itStudent.next();
									if (s.getBrIndeksa().equals(indeks)) {
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
					if (TableProfesor.getInstance().getSelectedRow() >= 0) {
						int opcija = JOptionPane.showConfirmDialog(MainFrame.getInstance(),
								"Da li ste sigurni da želite da obrišete profesora?", "Brisanje profesora",
								JOptionPane.YES_NO_OPTION);
						if (opcija == 0) {
							Profesor prof = ProfesorController.getInstance()
									.getProfesor(TableProfesor.getInstance().getSelectedRow());
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
								if (p.getProfesor() != null) {
									if (p.getProfesor().getBrLicneKarte().equals(licna)) {
										p.setProfesor(null);
									}
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
							String sifra = (String) TablePredmet.getInstance()
									.getValueAt(TablePredmet.getInstance().getSelectedRow(), 0);
						
							Iterator<Ocena> itO = OcenaController.getInstance().getOcene().iterator();
							while(itO.hasNext()) {
								Ocena o = itO.next();
								if(o.getPredmet().getSifra().equals(sifra)) {
									itO.remove();
									break;
									}
							}
							
							Iterator<Student> itPol = StudentController.getInstance().getStudenti().iterator();
							while(itPol.hasNext()) {
								Student s = itPol.next();
								Iterator<Ocena> ito = s.getSpisakPolozenih().iterator();
								while(ito.hasNext()) {
									Ocena o = ito.next();
									if (o.getPredmet().getSifra().equals(sifra)) {
										ito.remove();
										break;
									}
								}
							}
							
							
							Iterator<Student> itN = StudentController.getInstance().getStudenti().iterator();
							while(itN.hasNext()) {
								Student s = itN.next();
								Iterator<Predmet> itP = s.getSpisakNepolozenih().iterator();
								while(itP.hasNext()) {
									Predmet p = itP.next();
									if (p.getSifra().equals(sifra)) {
										itP.remove();
										break;
									}
								}
							}
						
							Iterator<Student> itS = StudentController.getInstance().getStudenti().iterator();
							while(itS.hasNext()) {
								Student s = itS.next();
								Iterator<Predmet> itP = s.getSlobodne().iterator();
								while(itP.hasNext()) {
									Predmet p = itP.next();
									if (p.getSifra().equals(sifra)) {
										itP.remove();
										break;
									}
								}
							}
						
							Iterator<Profesor> itProf = ProfesorController.getInstance().getProfesori().iterator();
							while(itProf.hasNext()) {
								Profesor prof = itProf.next();
								Iterator<Predmet> itP = prof.getPredajePredmet().iterator();
								while(itP.hasNext()) {
									Predmet p = itP.next();
									if (p.getSifra().equals(sifra)) {
										itP.remove();
										break;
									}
								}
							}
							
							Iterator<Profesor> itProf2 = ProfesorController.getInstance().getProfesori().iterator();
							while(itProf2.hasNext()) {
								Profesor prof = itProf2.next();
								Iterator<Predmet> itP = prof.getPredmetiBezProfesora().iterator();
								while(itP.hasNext()) {
									Predmet p = itP.next();
									if (p.getSifra().equals(sifra)) {
										itP.remove();
										break;
									}
								}
							}
							
							Iterator<Predmet> p = PredmetController.getInstance().getPredmeti().iterator();
							while (p.hasNext()) {
								Predmet pred = p.next();
								if (pred.getSifra().equals(sifra)) {
									p.remove();
									break;
								}
							}
						
							MainFrame.getInstance().azurirajPredmete("Uklonjen", -1);
						} 
					}					 
				}
				
		}
		});

		HelpDialogAction helpAction = new HelpDialogAction();
		JMenuItem helpItem = new JMenuItem(helpAction);
		helpItem.setBackground(Color.WHITE);
		helpItem.setMnemonic(KeyEvent.VK_H);
		helpItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				HelpDialog helpDialog = new HelpDialog(MainFrame.getInstance(), "Help", false);
				helpDialog.setVisible(true);

			}
		});

		AboutDialogAction aboutAction = new AboutDialogAction();
		JMenuItem aboutItem = new JMenuItem(aboutAction);
		aboutItem.setBackground(Color.WHITE);
		aboutItem.setMnemonic(KeyEvent.VK_A);
		aboutItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AboutDialog aboutDialog = new AboutDialog(MainFrame.getInstance(), "About", true);
				aboutDialog.setVisible(true);

			}
		});

		fileMenu.add(newItem);
		fileMenu.addSeparator();
		fileMenu.add(closeItem);
		editMenu.add(editItem);
		editMenu.addSeparator();
		editMenu.add(deleteItem);
		helpMenu.add(helpItem);
		helpMenu.addSeparator();
		helpMenu.add(aboutItem);

		add(fileMenu);
		add(editMenu);
		add(helpMenu);
	}

}
