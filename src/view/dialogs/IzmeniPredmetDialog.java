package view.dialogs;

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
import model.entiteti.Ocena;
import model.entiteti.Predmet;
import model.entiteti.Profesor;
import model.entiteti.Student;
import model.entiteti.Predmet.Semestar;
import view.tables.TablePredmet;

/**
 * Klasa predstavlja dijalog izmene predmeta. Sastoji se od tekstualnih polja u
 * koja se unose informacije o predmetu, njima korespondentnih labela, dugmeta
 * za potvrdu i odustanak izmene predmeta, kao i dugmad za dodavanje profesora
 * na predmet i uklanjanje profesora sa predmeta. Za svako tekstualno polje su
 * vezani slušači događaja koji svaki put kada se promeni sadržaj tekstualnog
 * polja za koji je vezan, ažurira pokazatelj ispravnosti unosa za to polje.
 * Dugme potvrde se omogućava tek onda kada su svi pokazatelji postavljeni na
 * vrednost <code>true</code>, u suprotnom je dugme za potvrdu onemogućeno.
 * Svako polje koje je korektno popunjeno se boji zelenom bojom kako bi
 * korisniku bilo dato do znanja da je ispravno uneo tu informaciju o predmetu.
 * Za dugme potvrde izmene predmeta je takođe vezan slušač događaja u kom se
 * nalaze dodatne provere. Kada se klikne dugme potvrde, na primer ako predmet
 * sa istom šifrom već postoji u sistemu pojaviće se novi dijalog koji daje do
 * znanja referentu da je to slučaj i da treba da promeni šifru predmeta kog
 * hoće da izmeni. Ako je sve uneto ispravno, polja predmeta koji se menja se
 * ažuriraju vrednostima unetim u dijalogu izmene predmeta. Slušač događaja
 * dugmeta za dodavanje profesora na predmet, ako tekstualno polje, koje
 * predstavlja polje klase, sadrži ime i prezime profesora, radi sledeće. Traži
 * profesora sa istim imenom i prezimenom u bazi profesora i inicijalizuje
 * spisak predmeta koje on predaje. Ako je stara šifra predmeta koji se menja
 * jednaka sa šifrom predmeta iz liste predmeta koje predaje profesor sva polja
 * predmeta u toj listi se ažuriraju vrednostima polja predmeta koji se menja.
 * Takođe, ako je šifra predmeta bila menjana u dijalogu izmene predmeta,
 * uklanja se predmet sa starom šifrom iz liste predmeta koje predaje profesor.
 * Zatim se za sve studente iz baze studenata inicijalizuju liste položenih,
 * nepoloženih i predmeta koje mogu da slušaju. Prolazi se kroz njih i ako
 * predmet koji se nalazi u njima ima staru šifru predmeta, sva polja tog
 * predmeta se ažuriraju vrednostima polja predmeta koji se izmenio. Na kraju se
 * poziva metoda za ažuriranje prikaza predmeta preko kontrolera predmeta.
 * Slušač događaja dugmeta za uklanjanje profesora sa predmeta postavlja polje
 * profesora predmeta koji se menja na <code>null</code>. Zatim prolazi kroz sve
 * profesore baze profesora i izbacuje predmet sa izmenjenom šifrom iz njegove
 * liste predmeta koje predaje. Na kraju briše ime i prezime uklonjenog
 * profesora iz tekstualnog polja.
 * 
 * 
 * @author Luka Miletić
 *
 */
public class IzmeniPredmetDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4378274837268901426L;

	/**
	 * Regularni izraz koji se koristi za proveru korektnosti unosa šifre predmeta.
	 */
	private String sifraSablon = "\\p{IsUppercase}+[\\p{IsUppercase}0-9]+";

	/**
	 * Regularni izraz koji se koristi za proveru korektnosti unosa naziva predmeta.
	 */
	private String nazivSablon = "\\p{IsUppercase}(\\p{IsAlphabetic}+)[\\p{IsWhite_Space}\\p{IsAlphabetic}\\p{IsDigit}]*";

	/**
	 * Regularni izraz koji se koristi za proveru korektnosti unosa broja ESPB poena predmeta.
	 */
	private String ESPBSablon = "[1-9]{1,2}0?";

	/**
	 * Pokazatelj ispravnosti unosa šifre predmeta.
	 */
	private boolean sifraKorektno;
	
	/**
	 * Pokazatelj ispravnosti unosa naziva predmeta.
	 */
	private boolean nazivKorektno;
	
	/**
	 * Pokazatelj ispravnosti unosa broja ESPB poena predmeta.
	 */
	private boolean ESPBKorektno;

	/**
	 * Pokazatelj celokupne ispravnosti unosa svih informacija o predmetu.
	 */
	private boolean ispravno = true;

	/**
	 * Dugme potvrde izmene predmeta.
	 */
	private JButton ok;
	
	/**
	 * Dugme za dodavanje profesora na predmet.
	 */
	private JButton plus;
	
	/**
	 * Dugme za uklanjanje profesora sa predmeta.
	 */
	private JButton minus;

	/**
	 * Tekstualno polje u kom piše ime i prezime profesora koji predaje predmet.
	 */
	private static JTextField profesorUnos;

	/**
	 * Šifra predmeta pre njene izmene u dijalogu.
	 */
	private String stara;

	/**
	 * Kreira dijalog izmene predmeta, centriran u odnosu
	 * na glavni prozor.
	 * 
	 * @param parent roditeljski prozor dijaloga
	 * @param title naslov dijaloga
	 * @param modal modalnost dijaloga
	 */
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
										
										predmet.setProfesor(prof);
								
										predavan.setSifra(sifraVrednost);
										predavan.setNaziv(nazivVrednost);
										predavan.setESPB(Integer.parseInt(ESPBVrednost));
										predavan.setGodinaStudija(godinaStudija);
										predavan.setSemestar(semestarVrednost);

										
										if (!stara.equals(sifraVrednost)) {
											prof.izbrisiPredajePredmet(stara);
										}
										break outerloop;
									}
								}

								predmet.setProfesor(prof);
								prof.izbrisiPredajePredmet(stara);
								prof.dodajPredajePredmet(predmet);
								prof.izbrisiSlobodan(predmet);

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
