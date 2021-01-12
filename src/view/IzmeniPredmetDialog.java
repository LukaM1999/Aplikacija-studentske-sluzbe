package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.NepolozeniIspitiController;
import controller.PolozeniIspitiController;
import controller.PredmetController;
import controller.ProfesorController;
import controller.ProfesorPredajeController;
import controller.SlobodniPredmetiController;
import controller.StudentController;
import model.Ocena;
import model.Predmet;
import model.Predmet.Semestar;
import model.Profesor;
import model.Student;

public class IzmeniPredmetDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4378274837268901426L;

	private String sifraSablon = "\\p{IsUppercase}+[\\p{IsUppercase}0-9]+";

	private String nazivSablon = "\\p{IsUppercase}(\\p{IsAlphabetic}+)[\\p{IsWhite_Space}\\p{IsAlphabetic}\\p{IsDigit}]*";

	private String ESPBSablon = "[1-9]{1,2}0?";

	private boolean sifraKorektno;
	private boolean nazivKorektno;
	private boolean ESPBKorektno;

	private boolean ispravno = true;

	private JButton ok;
	private JButton plus;
	private JButton minus;

	private static JTextField profesorUnos;

	private String stara;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public IzmeniPredmetDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);

		setSize(550, 420);
		setResizable(false);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		ok = new JButton("Potvrdi");
		ok.setEnabled(true);

		plus = new JButton("+");
		minus = new JButton("-");

		// REFERENCE: https://docs.oracle.com/javase/tutorial/uiswing/layout/spring.html
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.WEST, ok, 136, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, ok, -25, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, ok, -295, SpringLayout.EAST, getContentPane());
		getContentPane().setLayout(springLayout);

		// Labels
		JLabel sifra = new JLabel("Šifra*");
		sifra.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, sifra, 35, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, sifra, 35, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, sifra, 75, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, sifra, 205, SpringLayout.WEST, getContentPane());
		getContentPane().add(sifra);

		JLabel naziv = new JLabel("Naziv predmeta*");
		naziv.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, naziv, 5, SpringLayout.SOUTH, sifra);
		springLayout.putConstraint(SpringLayout.WEST, naziv, 0, SpringLayout.WEST, sifra);
		springLayout.putConstraint(SpringLayout.SOUTH, naziv, 45, SpringLayout.SOUTH, sifra);
		springLayout.putConstraint(SpringLayout.EAST, naziv, 0, SpringLayout.EAST, sifra);
		getContentPane().add(naziv);

		JLabel ESPB = new JLabel("ESPB*");
		ESPB.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, ESPB, 5, SpringLayout.SOUTH, naziv);
		springLayout.putConstraint(SpringLayout.WEST, ESPB, 0, SpringLayout.WEST, sifra);
		springLayout.putConstraint(SpringLayout.SOUTH, ESPB, 45, SpringLayout.SOUTH, naziv);
		springLayout.putConstraint(SpringLayout.EAST, ESPB, 0, SpringLayout.EAST, sifra);
		getContentPane().add(ESPB);

		JLabel godinaStudija = new JLabel("Godina studija*");
		godinaStudija.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, godinaStudija, 5, SpringLayout.SOUTH, ESPB);
		springLayout.putConstraint(SpringLayout.WEST, godinaStudija, 0, SpringLayout.WEST, sifra);
		springLayout.putConstraint(SpringLayout.SOUTH, godinaStudija, 45, SpringLayout.SOUTH, ESPB);
		springLayout.putConstraint(SpringLayout.EAST, godinaStudija, 0, SpringLayout.EAST, sifra);
		getContentPane().add(godinaStudija);

		JLabel semestar = new JLabel("Semestar*");
		semestar.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, semestar, 5, SpringLayout.SOUTH, godinaStudija);
		springLayout.putConstraint(SpringLayout.WEST, semestar, 0, SpringLayout.WEST, sifra);
		springLayout.putConstraint(SpringLayout.SOUTH, semestar, 45, SpringLayout.SOUTH, godinaStudija);
		springLayout.putConstraint(SpringLayout.EAST, semestar, 0, SpringLayout.EAST, sifra);
		getContentPane().add(semestar);

		// Text fields
		JTextField sifraUnos = new JTextField();
		sifraUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		sifraUnos.setBackground(Color.GREEN);
		springLayout.putConstraint(SpringLayout.NORTH, sifraUnos, 11, SpringLayout.NORTH, sifra);
		springLayout.putConstraint(SpringLayout.WEST, sifraUnos, 6, SpringLayout.EAST, sifra);
		springLayout.putConstraint(SpringLayout.EAST, sifraUnos, -68, SpringLayout.EAST, getContentPane());
		getContentPane().add(sifraUnos);
		sifraUnos.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				changedUpdate(e);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				changedUpdate(e);

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if (!(Pattern.compile(sifraSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(sifraUnos.getText())
						.matches())) {
					sifraKorektno = false;
					sifraUnos.setBackground(Color.WHITE);
					ispravno = false;
				} else {
					sifraKorektno = true;
					sifraUnos.setBackground(Color.GREEN);

					if (sifraKorektno && nazivKorektno && ESPBKorektno) {
						ispravno = true;
					} else {
						ispravno = false;
					}
				}
				ok.setEnabled(ispravno);
			}

		});

		JTextField nazivUnos = new JTextField();
		nazivUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		nazivUnos.setBackground(Color.GREEN);
		springLayout.putConstraint(SpringLayout.NORTH, nazivUnos, 11, SpringLayout.NORTH, naziv);
		springLayout.putConstraint(SpringLayout.WEST, nazivUnos, 6, SpringLayout.EAST, naziv);
		springLayout.putConstraint(SpringLayout.EAST, nazivUnos, 0, SpringLayout.EAST, sifraUnos);
		getContentPane().add(nazivUnos);
		nazivUnos.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				changedUpdate(e);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				changedUpdate(e);

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if (!(Pattern.compile(nazivSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(nazivUnos.getText())
						.matches())) {
					nazivKorektno = false;
					nazivUnos.setBackground(Color.WHITE);
					ispravno = false;
				} else {
					nazivKorektno = true;
					nazivUnos.setBackground(Color.GREEN);

					if (sifraKorektno && nazivKorektno && ESPBKorektno) {
						ispravno = true;
					} else {
						ispravno = false;
					}
				}
				ok.setEnabled(ispravno);
			}

		});

		JTextField ESPBUnos = new JTextField();
		ESPBUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		ESPBUnos.setBackground(Color.GREEN);
		springLayout.putConstraint(SpringLayout.NORTH, ESPBUnos, 11, SpringLayout.NORTH, ESPB);
		springLayout.putConstraint(SpringLayout.WEST, ESPBUnos, 6, SpringLayout.EAST, ESPB);
		springLayout.putConstraint(SpringLayout.EAST, ESPBUnos, 0, SpringLayout.EAST, sifraUnos);
		getContentPane().add(ESPBUnos);
		ESPBUnos.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				changedUpdate(e);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				changedUpdate(e);

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if (!(Pattern.compile(ESPBSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(ESPBUnos.getText())
						.matches())) {
					ESPBKorektno = false;
					ESPBUnos.setBackground(Color.WHITE);
					ispravno = false;
				} else {
					ESPBKorektno = true;
					ESPBUnos.setBackground(Color.GREEN);

					if (sifraKorektno && nazivKorektno && ESPBKorektno) {
						ispravno = true;
					} else {
						ispravno = false;
					}
				}
				ok.setEnabled(ispravno);
			}

		});

		// Combo boxes
		String[] godinaStud = new String[] { "1", "2", "3", "4" };
		JComboBox godinaCombo = new JComboBox(godinaStud);
		godinaCombo.setBackground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.NORTH, godinaCombo, 21, SpringLayout.SOUTH, ESPBUnos);
		springLayout.putConstraint(SpringLayout.WEST, godinaCombo, 6, SpringLayout.EAST, godinaStudija);
		springLayout.putConstraint(SpringLayout.EAST, godinaCombo, 0, SpringLayout.EAST, sifraUnos);
		godinaCombo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		godinaCombo.setSelectedIndex(0);
		getContentPane().add(godinaCombo);

		String[] semestarUnos = new String[] { "Zimski", "Letnji" };
		JComboBox semestarCombo = new JComboBox(semestarUnos);
		semestarCombo.setBackground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.NORTH, semestarCombo, 21, SpringLayout.SOUTH, godinaCombo);
		springLayout.putConstraint(SpringLayout.WEST, semestarCombo, 6, SpringLayout.EAST, semestar);
		springLayout.putConstraint(SpringLayout.EAST, semestarCombo, 0, SpringLayout.EAST, sifraUnos);
		semestarCombo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		semestarCombo.setSelectedIndex(0);
		getContentPane().add(semestarCombo);

		TablePredmet table = TablePredmet.getInstance();
		Predmet predmet = PredmetController.getInstance().getPredmet(table.getSelectedRow());

		stara = predmet.getSifra();

		sifraUnos.setText(predmet.getSifra());
		nazivUnos.setText(predmet.getNaziv());
		ESPBUnos.setText(String.valueOf(predmet.getESPB()));
		sifraUnos.setText(predmet.getSifra());
		godinaCombo.setSelectedIndex(predmet.getGodinaStudija() - 1);
		if (predmet.getSemestar() == Semestar.Zimski) {
			semestarCombo.setSelectedIndex(0);
		} else {
			semestarCombo.setSelectedIndex(1);
		}

		// Buttons
		JButton cancel = new JButton("Odustani");
		springLayout.putConstraint(SpringLayout.NORTH, cancel, 0, SpringLayout.NORTH, ok);
		springLayout.putConstraint(SpringLayout.WEST, cancel, 52, SpringLayout.EAST, ok);
		springLayout.putConstraint(SpringLayout.SOUTH, cancel, 0, SpringLayout.SOUTH, ok);
		springLayout.putConstraint(SpringLayout.EAST, cancel, -128, SpringLayout.EAST, getContentPane());
		cancel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(cancel);

		ok.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		ok.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {

				String sifraVrednost = sifraUnos.getText();
				String nazivVrednost = nazivUnos.getText();
				String ESPBVrednost = ESPBUnos.getText();

				if (!(Pattern.compile(sifraSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(sifraVrednost).matches())) {
					JOptionPane.showMessageDialog(getContentPane(), "Pogrešno uneta šifra!");
					return;
				}

				if (!(Pattern.compile(nazivSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(nazivVrednost).matches())) {
					JOptionPane.showMessageDialog(getContentPane(), "Pogrešno unet naziv!");
					return;
				}

				if (!Pattern.matches(ESPBSablon, ESPBVrednost)) {
					JOptionPane.showMessageDialog(getContentPane(), "Pogrešno unet broj ESPB!");
					return;
				}

				int godinaStudija = 1;
				if (godinaCombo.getSelectedItem() == "2") {
					godinaStudija = 2;
				} else if (godinaCombo.getSelectedItem() == "3") {
					godinaStudija = 3;
				} else if (godinaCombo.getSelectedItem() == "4") {
					godinaStudija = 4;
				}

				Semestar semestarVrednost = Semestar.Zimski;
				if (semestarCombo.getSelectedItem().toString() == "Letnji") {
					semestarVrednost = Semestar.Letnji;
				}

				for (Predmet p : PredmetController.getInstance().getPredmeti()) {
					if (p.getSifra().equals(sifraVrednost) && !stara.equals(sifraVrednost)) {
						JOptionPane.showMessageDialog(getContentPane(), "Već postoji predmet sa unetom šifrom!");
						return;
					}
				}

				// REFERENCE:
				// https://stackoverflow.com/questions/8689122/joptionpane-yes-no-options-confirm-dialog-box-issue
				JOptionPane confirm = new JOptionPane();
				int answer = confirm.showConfirmDialog(getContentPane(),
						"Da li ste sigurni da želite da izmenite informacije ovog predmeta?", "Potvrda izmene",
						JOptionPane.OK_CANCEL_OPTION);
				if (answer == JOptionPane.YES_OPTION) {

					predmet.setSifra(sifraVrednost);
					predmet.setNaziv(nazivVrednost);
					predmet.setESPB(Integer.parseInt(ESPBVrednost));
					predmet.setGodinaStudija(godinaStudija);
					predmet.setSemestar(semestarVrednost);

					if (!profesorUnos.getText().equals("")) {

						String[] imePrezimeProf = profesorUnos.getText().split(" ");

						// REFERENCE:
						// https://stackoverflow.com/questions/886955/how-do-i-break-out-of-nested-loops-in-java
						outerloop: for (Profesor prof : ProfesorController.getInstance().getProfesori()) {
							if (prof.getIme().equals(imePrezimeProf[0])
									&& prof.getPrezime().equals(imePrezimeProf[1])) {

								ProfesorPredajeController.getInstance().initPredajePredmet(prof);
								for (Predmet predavan : prof.getPredajePredmet()) {
									if (predavan.getSifra().equals(stara)) {
										System.out.println("One time");
										predmet.setProfesor(prof);
										//prof.izbrisiPredajePredmet(stara);
										//prof.dodajPredajePredmet(predmet);

										predavan.setSifra(sifraVrednost);
										predavan.setNaziv(nazivVrednost);
										predavan.setESPB(Integer.parseInt(ESPBVrednost));
										predavan.setGodinaStudija(godinaStudija);
										predavan.setSemestar(semestarVrednost);

										
										if (!stara.equals(sifraVrednost)) {
											prof.izbrisiPredajePredmet(stara);
										}
										 
										// prof.dodajPredajePredmet(predmet);
										// prof.izbrisiSlobodan(predmet);
										break outerloop;
									}
								}

								// ProfesorPredajeController.getInstance().initPredajePredmet(prof);
								predmet.setProfesor(prof);
								prof.izbrisiPredajePredmet(stara);
								prof.dodajPredajePredmet(predmet);
								prof.izbrisiSlobodan(predmet);
								// ProfesorPredajeController.getInstance().initPredajePredmet(prof);

								break outerloop;

							}
						}

					}
					for (Student s : StudentController.getInstance().getStudenti()) {

						PolozeniIspitiController.getInstance().initSpisakPolozenih(s);
						NepolozeniIspitiController.getInstance().initSpisakNepolozenih(s);
						SlobodniPredmetiController.getInstance().initSlobodne(s);

						for (Predmet p : NepolozeniIspitiController.getInstance().getPredmeti()) {

							if (p.getSifra().equals(stara)) {
								p.setSifra(sifraVrednost);
								p.setNaziv(nazivVrednost);
								p.setESPB(Integer.parseInt(ESPBVrednost));
								p.setGodinaStudija(godinaStudija);
								p.setSemestar(semestarVrednost);
							}
						}
						for (Ocena o : PolozeniIspitiController.getInstance().getOcene()) {

							if (o.getPredmet().getSifra().equals(stara)) {
								o.getPredmet().setSifra(sifraVrednost);
								o.getPredmet().setNaziv(nazivVrednost);
								o.getPredmet().setESPB(Integer.parseInt(ESPBVrednost));
								o.getPredmet().setGodinaStudija(godinaStudija);
								o.getPredmet().setSemestar(semestarVrednost);
							}
						}
						for (Predmet p : SlobodniPredmetiController.getInstance().getPredmeti()) {

							if (p.getSifra().equals(stara)) {
								p.setSifra(sifraVrednost);
								p.setNaziv(nazivVrednost);
								p.setESPB(Integer.parseInt(ESPBVrednost));
								p.setGodinaStudija(godinaStudija);
								p.setSemestar(semestarVrednost);
							}
						}
					}
					PredmetController.getInstance().izmeniPredmet(table.getSelectedRow());

					dispose();
				}

			}
		});
		getContentPane().add(ok);

		JLabel profesor = new JLabel("Profesor*");
		springLayout.putConstraint(SpringLayout.NORTH, ok, 27, SpringLayout.SOUTH, profesor);
		profesor.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, profesor, 5, SpringLayout.SOUTH, semestar);
		springLayout.putConstraint(SpringLayout.WEST, profesor, 0, SpringLayout.WEST, sifra);
		springLayout.putConstraint(SpringLayout.SOUTH, profesor, 45, SpringLayout.SOUTH, semestar);
		springLayout.putConstraint(SpringLayout.EAST, profesor, 0, SpringLayout.EAST, sifra);
		getContentPane().add(profesor);

		profesorUnos = new JTextField();
		profesorUnos.setEditable(false);
		springLayout.putConstraint(SpringLayout.NORTH, profesorUnos, 21, SpringLayout.SOUTH, semestarCombo);
		springLayout.putConstraint(SpringLayout.WEST, profesorUnos, 6, SpringLayout.EAST, profesor);
		springLayout.putConstraint(SpringLayout.EAST, profesorUnos, -83, SpringLayout.EAST, sifraUnos);
		profesorUnos.setBackground(Color.WHITE);
		profesorUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		profesorUnos.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				changedUpdate(arg0);

			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				changedUpdate(arg0);

			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				if (profesorUnos.getText().equals("")) {
					minus.setEnabled(false);
					plus.setEnabled(true);
				} else {
					minus.setEnabled(true);
					plus.setEnabled(false);
				}

			}
		});

		if (predmet.getProfesor() != null) {
			profesorUnos.setText(predmet.getProfesor().getIme() + " " + predmet.getProfesor().getPrezime());
			minus.setEnabled(true);
			plus.setEnabled(false);
		} else {
			minus.setEnabled(false);
			plus.setEnabled(true);
		}

		getContentPane().add(profesorUnos);

		plus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new DodajProfesoraNaPredmetDialog(getContentPane(), "Odaberi profesora", true, predmet,
						profesorUnos);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, plus, 0, SpringLayout.NORTH, profesorUnos);
		springLayout.putConstraint(SpringLayout.WEST, plus, 15, SpringLayout.EAST, profesorUnos);
		springLayout.putConstraint(SpringLayout.SOUTH, plus, 0, SpringLayout.SOUTH, profesorUnos);
		springLayout.putConstraint(SpringLayout.EAST, plus, -107, SpringLayout.EAST, getContentPane());
		// REFERENCE:
		// https://stackoverflow.com/questions/5808195/removing-the-three-dots-from-a-jbutton
		plus.setMargin(new Insets(0, 0, 0, 0));
		getContentPane().add(plus);

		minus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int answer = JOptionPane.showConfirmDialog(getContentPane(), "        Da li ste sigurni?",
						"Ukloni profesora", JOptionPane.OK_CANCEL_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					predmet.setProfesor(null);

					for (Profesor p : ProfesorController.getInstance().getProfesori()) {
						Iterator<Predmet> itp = p.getPredajePredmet().iterator();
						while (itp.hasNext()) {
							Predmet pred = itp.next();
							if (pred.getSifra().equals(predmet.getSifra())) {
								itp.remove();
							}
						}
					}
					profesorUnos.setText(null);
				}

			}
		});

		springLayout.putConstraint(SpringLayout.NORTH, minus, 0, SpringLayout.NORTH, profesorUnos);
		springLayout.putConstraint(SpringLayout.WEST, minus, 40, SpringLayout.WEST, plus);
		springLayout.putConstraint(SpringLayout.SOUTH, minus, 33, SpringLayout.NORTH, profesor);
		springLayout.putConstraint(SpringLayout.EAST, minus, 0, SpringLayout.EAST, sifraUnos);
		minus.setMargin(new Insets(0, 0, 0, 0));
		getContentPane().add(minus);

	}

}
