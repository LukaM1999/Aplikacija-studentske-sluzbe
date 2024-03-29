package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.NepolozeniIspitiController;
import controller.PolozeniIspitiController;
import controller.PredmetController;
import controller.SlobodniPredmetiController;
import controller.StudentController;
import model.Predmet;
import model.Student;
import model.Student.Status;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;

public class IzmeniStudentaDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7438718548915872276L;

	private String imeSablon = "\\p{IsUppercase}\\p{IsAlphabetic}+(\\p{IsWhite_Space}\\p{IsUppercase}\\p{IsAlphabetic}+)*";

	private String prezimeSablon = "\\p{IsUppercase}\\p{IsAlphabetic}+(\\p{IsWhite_Space}\\p{IsUppercase}\\p{IsAlphabetic}+)*";

	private String telefonSablon = "[0-9]{8,12}?";

	private String emailSablon = "([\\p{IsLowercase}\\p{IsUppercase}0-9\\.])+"
			+ "([\\p{IsLowercase}\\p{IsUppercase}0-9])+(\\@)\\p{IsAlphabetic}+([\\p{IsAlphabetic}\\.])*\\.\\p{IsAlphabetic}+";

	private String datumSablon = "(0[1-9]|[12][0-9]|3[01]).(0[1-9]|1[012]).((18|19|20|21)\\d\\d)\\.";

	private String adresaSablon = "\\p{IsUppercase}\\p{IsLowercase}+(\\p{IsWhite_Space}\\p{IsAlphabetic}+)*"
			+ "(\\p{IsWhite_Space}\\p{IsDigit}+)\\p{IsAlphabetic}?(\\,)(\\p{IsWhite_Space})?\\p{IsUppercase}(\\p{IsLowercase})+"
			+ "(\\p{IsWhite_Space}\\p{IsUppercase}(\\p{IsLowercase})+)?";

	private String indeksSablon = "([\\p{IsUppercase}]{2}|[\\p{IsUppercase}][1-9])-([0-9]{1,3})-(20[0-9]{2})";

	private String upisSablon = "(20[0-9]{2})";

	private TablePolozeniIspiti polozeniTable;

	private TableNepolozeniIspiti nepolozeniTable;

	private double avgOcena = 0;

	private int ukupnoESPB = 0;

	private JLabel prosek;

	private JLabel ESPB;

	private boolean imeKorektno = true;
	private boolean prezimeKorektno = true;
	private boolean datumKorektno = true;
	private boolean adresaKorektno = true;
	private boolean telefonKorektno = true;
	private boolean emailKorektno = true;
	private boolean indeksKorektno = true;
	private boolean upisKorektno = true;

	private boolean ispravno = true;

	private JButton ok;
	
	private String stariIndeks;
	

	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	public IzmeniStudentaDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);

		setSize(500, 600);
		setResizable(false);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		ok = new JButton("Potvrdi");
		ok.setEnabled(true);

		JPanel panel = new JPanel();

		SpringLayout springLayout = new SpringLayout();
		panel.setLayout(springLayout);

		// Labels
		JLabel ime = new JLabel("Ime*");
		ime.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, ime, 35, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, ime, 35, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.SOUTH, ime, 75, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, ime, 205, SpringLayout.WEST, panel);
		panel.add(ime);

		JLabel prezime = new JLabel("Prezime*");
		prezime.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, prezime, 5, SpringLayout.SOUTH, ime);
		springLayout.putConstraint(SpringLayout.WEST, prezime, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, prezime, 45, SpringLayout.SOUTH, ime);
		springLayout.putConstraint(SpringLayout.EAST, prezime, 0, SpringLayout.EAST, ime);
		panel.add(prezime);

		JLabel datum = new JLabel("Datum rođenja*");
		datum.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, datum, 5, SpringLayout.SOUTH, prezime);
		springLayout.putConstraint(SpringLayout.WEST, datum, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, datum, 45, SpringLayout.SOUTH, prezime);
		springLayout.putConstraint(SpringLayout.EAST, datum, 0, SpringLayout.EAST, ime);
		panel.add(datum);

		JLabel adresa = new JLabel("Adresa stanovanja*");
		adresa.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, adresa, 5, SpringLayout.SOUTH, datum);
		springLayout.putConstraint(SpringLayout.WEST, adresa, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, adresa, 45, SpringLayout.SOUTH, datum);
		springLayout.putConstraint(SpringLayout.EAST, adresa, 0, SpringLayout.EAST, ime);
		panel.add(adresa);

		JLabel telefon = new JLabel("Broj telefona*");
		telefon.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, telefon, 5, SpringLayout.SOUTH, adresa);
		springLayout.putConstraint(SpringLayout.WEST, telefon, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, telefon, 45, SpringLayout.SOUTH, adresa);
		springLayout.putConstraint(SpringLayout.EAST, telefon, 0, SpringLayout.EAST, ime);
		panel.add(telefon);

		JLabel email = new JLabel("E-mail adresa*");
		email.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, email, 5, SpringLayout.SOUTH, telefon);
		springLayout.putConstraint(SpringLayout.WEST, email, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, email, 45, SpringLayout.SOUTH, telefon);
		springLayout.putConstraint(SpringLayout.EAST, email, 0, SpringLayout.EAST, ime);
		panel.add(email);

		JLabel indeks = new JLabel("Broj indeksa*");
		indeks.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, indeks, 5, SpringLayout.SOUTH, email);
		springLayout.putConstraint(SpringLayout.WEST, indeks, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, indeks, 45, SpringLayout.SOUTH, email);
		springLayout.putConstraint(SpringLayout.EAST, indeks, 0, SpringLayout.EAST, ime);
		panel.add(indeks);

		JLabel upis = new JLabel("Godina upisa*");
		upis.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, upis, 5, SpringLayout.SOUTH, indeks);
		springLayout.putConstraint(SpringLayout.WEST, upis, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, upis, 45, SpringLayout.SOUTH, indeks);
		springLayout.putConstraint(SpringLayout.EAST, upis, 0, SpringLayout.EAST, ime);
		panel.add(upis);

		JLabel godinaStudija = new JLabel("Trenutna godina studija*");
		godinaStudija.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, godinaStudija, 5, SpringLayout.SOUTH, upis);
		springLayout.putConstraint(SpringLayout.WEST, godinaStudija, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, godinaStudija, 45, SpringLayout.SOUTH, upis);
		springLayout.putConstraint(SpringLayout.EAST, godinaStudija, 0, SpringLayout.EAST, ime);
		panel.add(godinaStudija);

		JLabel finansiranje = new JLabel("Način finansiranja*");
		finansiranje.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, finansiranje, 5, SpringLayout.SOUTH, godinaStudija);
		springLayout.putConstraint(SpringLayout.WEST, finansiranje, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, finansiranje, 45, SpringLayout.SOUTH, godinaStudija);
		springLayout.putConstraint(SpringLayout.EAST, finansiranje, 0, SpringLayout.EAST, ime);
		panel.add(finansiranje);

		// Text fields
		JTextField imeUnos = new JTextField();
		imeUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		imeUnos.setBackground(Color.GREEN);
		springLayout.putConstraint(SpringLayout.NORTH, imeUnos, 11, SpringLayout.NORTH, ime);
		springLayout.putConstraint(SpringLayout.WEST, imeUnos, 6, SpringLayout.EAST, ime);
		springLayout.putConstraint(SpringLayout.EAST, imeUnos, -68, SpringLayout.EAST, panel);
		panel.add(imeUnos);
		imeUnos.getDocument().addDocumentListener(new DocumentListener() {

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
				if (!(Pattern.compile(imeSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(imeUnos.getText())
						.matches())) {
					imeKorektno = false;
					imeUnos.setBackground(Color.WHITE);
					ispravno = false;
				} else {
					imeKorektno = true;
					imeUnos.setBackground(Color.GREEN);

					if (imeKorektno && prezimeKorektno && datumKorektno && adresaKorektno && telefonKorektno
							&& emailKorektno && indeksKorektno && upisKorektno) {
						ispravno = true;
					} else {
						ispravno = false;
					}
				}
				ok.setEnabled(ispravno);
			}

		});

		JTextField prezimeUnos = new JTextField();
		prezimeUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		prezimeUnos.setBackground(Color.GREEN);
		springLayout.putConstraint(SpringLayout.NORTH, prezimeUnos, 11, SpringLayout.NORTH, prezime);
		springLayout.putConstraint(SpringLayout.WEST, prezimeUnos, 6, SpringLayout.EAST, prezime);
		springLayout.putConstraint(SpringLayout.EAST, prezimeUnos, 0, SpringLayout.EAST, imeUnos);
		panel.add(prezimeUnos);
		prezimeUnos.getDocument().addDocumentListener(new DocumentListener() {

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
				if (!(Pattern.compile(prezimeSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(prezimeUnos.getText())
						.matches())) {
					prezimeKorektno = false;
					prezimeUnos.setBackground(Color.WHITE);
					ispravno = false;
				} else {
					prezimeKorektno = true;
					prezimeUnos.setBackground(Color.GREEN);

					if (imeKorektno && prezimeKorektno && datumKorektno && adresaKorektno && telefonKorektno
							&& emailKorektno && indeksKorektno && upisKorektno) {
						ispravno = true;
					} else {
						ispravno = false;
					}
				}
				ok.setEnabled(ispravno);
			}

		});

		JTextField datumUnos = new JTextField();
		datumUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		datumUnos.setBackground(Color.GREEN);
		springLayout.putConstraint(SpringLayout.NORTH, datumUnos, 11, SpringLayout.NORTH, datum);
		springLayout.putConstraint(SpringLayout.WEST, datumUnos, 6, SpringLayout.EAST, datum);
		springLayout.putConstraint(SpringLayout.EAST, datumUnos, 0, SpringLayout.EAST, imeUnos);
		panel.add(datumUnos);
		datumUnos.getDocument().addDocumentListener(new DocumentListener() {

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
				if (!(Pattern.compile(datumSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(datumUnos.getText())
						.matches())) {
					datumKorektno = false;
					datumUnos.setBackground(Color.WHITE);
					ispravno = false;
				} else {
					datumKorektno = true;
					datumUnos.setBackground(Color.GREEN);

					if (imeKorektno && prezimeKorektno && datumKorektno && adresaKorektno && telefonKorektno
							&& emailKorektno && indeksKorektno && upisKorektno) {
						ispravno = true;
					} else {
						ispravno = false;
					}
				}
				ok.setEnabled(ispravno);
			}

		});

		JTextField adresaUnos = new JTextField();
		adresaUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		adresaUnos.setBackground(Color.GREEN);
		springLayout.putConstraint(SpringLayout.NORTH, adresaUnos, 11, SpringLayout.NORTH, adresa);
		springLayout.putConstraint(SpringLayout.WEST, adresaUnos, 6, SpringLayout.EAST, adresa);
		springLayout.putConstraint(SpringLayout.EAST, adresaUnos, 0, SpringLayout.EAST, imeUnos);
		panel.add(adresaUnos);
		adresaUnos.getDocument().addDocumentListener(new DocumentListener() {

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
				if (!(Pattern.compile(adresaSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(adresaUnos.getText())
						.matches())) {
					adresaKorektno = false;
					adresaUnos.setBackground(Color.WHITE);
					ispravno = false;
				} else {
					adresaKorektno = true;
					adresaUnos.setBackground(Color.GREEN);

					if (imeKorektno && prezimeKorektno && datumKorektno && adresaKorektno && telefonKorektno
							&& emailKorektno && indeksKorektno && upisKorektno) {
						ispravno = true;
					} else {
						ispravno = false;
					}
				}
				ok.setEnabled(ispravno);
			}

		});

		JTextField telefonUnos = new JTextField();
		telefonUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		telefonUnos.setBackground(Color.GREEN);
		springLayout.putConstraint(SpringLayout.NORTH, telefonUnos, 11, SpringLayout.NORTH, telefon);
		springLayout.putConstraint(SpringLayout.WEST, telefonUnos, 6, SpringLayout.EAST, telefon);
		springLayout.putConstraint(SpringLayout.EAST, telefonUnos, 0, SpringLayout.EAST, imeUnos);
		panel.add(telefonUnos);
		telefonUnos.getDocument().addDocumentListener(new DocumentListener() {

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
				if (!(Pattern.compile(telefonSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(telefonUnos.getText())
						.matches())) {
					telefonKorektno = false;
					telefonUnos.setBackground(Color.WHITE);
					ispravno = false;
				} else {
					telefonKorektno = true;
					telefonUnos.setBackground(Color.GREEN);

					if (imeKorektno && prezimeKorektno && datumKorektno && adresaKorektno && telefonKorektno
							&& emailKorektno && indeksKorektno && upisKorektno) {
						ispravno = true;
					} else {
						ispravno = false;
					}
				}
				ok.setEnabled(ispravno);
			}

		});

		JTextField emailUnos = new JTextField();
		emailUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		emailUnos.setBackground(Color.GREEN);
		springLayout.putConstraint(SpringLayout.NORTH, emailUnos, 11, SpringLayout.NORTH, email);
		springLayout.putConstraint(SpringLayout.WEST, emailUnos, 6, SpringLayout.EAST, email);
		springLayout.putConstraint(SpringLayout.EAST, emailUnos, 0, SpringLayout.EAST, imeUnos);
		panel.add(emailUnos);
		emailUnos.getDocument().addDocumentListener(new DocumentListener() {

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
				if (!(Pattern.compile(emailSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(emailUnos.getText())
						.matches())) {
					emailKorektno = false;
					emailUnos.setBackground(Color.WHITE);
					ispravno = false;
				} else {
					emailKorektno = true;
					emailUnos.setBackground(Color.GREEN);

					if (imeKorektno && prezimeKorektno && datumKorektno && adresaKorektno && telefonKorektno
							&& emailKorektno && indeksKorektno && upisKorektno) {
						ispravno = true;
					} else {
						ispravno = false;
					}
				}
				ok.setEnabled(ispravno);
			}

		});

		JTextField indeksUnos = new JTextField();
		indeksUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		indeksUnos.setBackground(Color.GREEN);
		springLayout.putConstraint(SpringLayout.NORTH, indeksUnos, 11, SpringLayout.NORTH, indeks);
		springLayout.putConstraint(SpringLayout.WEST, indeksUnos, 6, SpringLayout.EAST, indeks);
		springLayout.putConstraint(SpringLayout.EAST, indeksUnos, 0, SpringLayout.EAST, imeUnos);
		panel.add(indeksUnos);
		indeksUnos.getDocument().addDocumentListener(new DocumentListener() {

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
				if (!(Pattern.compile(indeksSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(indeksUnos.getText())
						.matches())) {
					indeksKorektno = false;
					indeksUnos.setBackground(Color.WHITE);
					ispravno = false;
				} else {
					indeksKorektno = true;
					indeksUnos.setBackground(Color.GREEN);

					if (imeKorektno && prezimeKorektno && datumKorektno && adresaKorektno && telefonKorektno
							&& emailKorektno && indeksKorektno && upisKorektno) {
						ispravno = true;
					} else {
						ispravno = false;
					}
				}
				ok.setEnabled(ispravno);
			}

		});

		JTextField upisUnos = new JTextField();
		upisUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		upisUnos.setBackground(Color.GREEN);
		springLayout.putConstraint(SpringLayout.NORTH, upisUnos, 11, SpringLayout.NORTH, upis);
		springLayout.putConstraint(SpringLayout.WEST, upisUnos, 6, SpringLayout.EAST, upis);
		springLayout.putConstraint(SpringLayout.EAST, upisUnos, 0, SpringLayout.EAST, imeUnos);
		panel.add(upisUnos);
		upisUnos.getDocument().addDocumentListener(new DocumentListener() {

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
				if (!(Pattern.compile(upisSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(upisUnos.getText())
						.matches())) {
					upisKorektno = false;
					upisUnos.setBackground(Color.WHITE);
					ispravno = false;
				} else {
					upisKorektno = true;
					upisUnos.setBackground(Color.GREEN);

					if (imeKorektno && prezimeKorektno && datumKorektno && adresaKorektno && telefonKorektno
							&& emailKorektno && indeksKorektno && upisKorektno) {
						ispravno = true;
					} else {
						ispravno = false;
					}
				}
				ok.setEnabled(ispravno);
			}

		});

		// Combo boxes
		String[] godinaStud = new String[] { "I (prva)", "II (druga)", "III (treća)", "IV (četvrta)" };
		JComboBox godinaCombo = new JComboBox(godinaStud);
		godinaCombo.setBackground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.NORTH, godinaCombo, 21, SpringLayout.SOUTH, upisUnos);
		springLayout.putConstraint(SpringLayout.WEST, godinaCombo, 6, SpringLayout.EAST, godinaStudija);
		springLayout.putConstraint(SpringLayout.EAST, godinaCombo, 0, SpringLayout.EAST, imeUnos);
		godinaCombo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		godinaCombo.setSelectedIndex(0);
		panel.add(godinaCombo);

		String[] nacinFinansiranja = new String[] { "Budžet", "Samofinansiranje" };
		JComboBox budzetCombo = new JComboBox(nacinFinansiranja);
		budzetCombo.setBackground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.NORTH, budzetCombo, 21, SpringLayout.SOUTH, godinaCombo);
		springLayout.putConstraint(SpringLayout.WEST, budzetCombo, 6, SpringLayout.EAST, finansiranje);
		springLayout.putConstraint(SpringLayout.EAST, budzetCombo, 0, SpringLayout.EAST, imeUnos);
		budzetCombo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		budzetCombo.setSelectedIndex(0);
		panel.add(budzetCombo);

		TableStudent table = TableStudent.getInstance();
		Student student = StudentController.getInstance()
				.getStudent(table.getSelectedRow());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		
		stariIndeks = student.getBrIndeksa();

		imeUnos.setText(student.getIme());
		prezimeUnos.setText(student.getPrezime());
		datumUnos.setText(formatter.format(student.getDatumRodjenja()));
		adresaUnos.setText(student.getAdresa());
		telefonUnos.setText(student.getTelefon());
		emailUnos.setText(student.getEmail());
		indeksUnos.setText(student.getBrIndeksa());
		upisUnos.setText(String.valueOf(student.getGodinaUpisa()));
		godinaCombo.setSelectedIndex(student.getTrenutnaGodina() - 1);

		if (student.getStatusStudenta() == Status.B) {
			budzetCombo.setSelectedIndex(0);
		} else {
			budzetCombo.setSelectedIndex(1);
		}

		// Buttons
		JButton cancel = new JButton("Odustani");
		springLayout.putConstraint(SpringLayout.NORTH, cancel, 19, SpringLayout.SOUTH, budzetCombo);
		springLayout.putConstraint(SpringLayout.WEST, cancel, 280, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.SOUTH, cancel, -10, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, cancel, -80, SpringLayout.EAST, panel);
		cancel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(cancel);

		springLayout.putConstraint(SpringLayout.NORTH, ok, 0, SpringLayout.NORTH, cancel);
		springLayout.putConstraint(SpringLayout.WEST, ok, 53, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, ok, 0, SpringLayout.SOUTH, cancel);
		springLayout.putConstraint(SpringLayout.EAST, ok, -68, SpringLayout.WEST, cancel);
		ok.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String imeVrednost = imeUnos.getText();
				String prezimeVrednost = prezimeUnos.getText();
				String datumVrednost = datumUnos.getText();
				String adresaVrednost = adresaUnos.getText();
				String telefonVrednost = telefonUnos.getText();
				String emailVrednost = emailUnos.getText();
				String indeksVrednost = indeksUnos.getText();
				String upisVrednost = upisUnos.getText();

				if (!(Pattern.compile(imeSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(imeVrednost).matches())) {
					JOptionPane.showMessageDialog(getContentPane(), "Pogrešno uneto ime!");
					return;
				}

				if (!(Pattern.compile(prezimeSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(prezimeVrednost)
						.matches())) {
					JOptionPane.showMessageDialog(getContentPane(), "Pogrešno uneto prezime!");
					return;
				}

				if (!Pattern.matches(datumSablon, datumVrednost)) {
					JOptionPane.showMessageDialog(getContentPane(), "Pogrešno unet datum rođenja!");
					return;
				}

				if (!(Pattern.compile(adresaSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(adresaVrednost)
						.matches())) {
					JOptionPane.showMessageDialog(getContentPane(), "Pogrešno uneta adresa!");
					return;
				}

				if (!Pattern.matches(telefonSablon, telefonVrednost)) {
					JOptionPane.showMessageDialog(getContentPane(), "Pogrešno unet kontakt telefon!");
					return;
				}

				if (!Pattern.matches(emailSablon, emailVrednost)) {
					JOptionPane.showMessageDialog(getContentPane(), "Pogrešno uneta e-mail adresa!");
					return;
				}

				if (!(Pattern.compile(indeksSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(indeksVrednost)
						.matches())) {
					JOptionPane.showMessageDialog(getContentPane(), "Pogrešno unet broj indeksa!");
					return;
				}

				String[] godRodjenja = datumVrednost.split("\\.");
				if (Integer.parseInt(upisVrednost) - Integer.parseInt(godRodjenja[2]) < 16) {
					JOptionPane.showMessageDialog(getContentPane(), "Pogrešno uneta godina upisa!");
					return;
				}

				int godinaStudija = 0;
				if (godinaCombo.getSelectedItem() == "II (druga)") {
					godinaStudija = 1;
				} else if (godinaCombo.getSelectedItem() == "III (treća)") {
					godinaStudija = 2;
				} else if (godinaCombo.getSelectedItem() == "IV (četvrta)") {
					godinaStudija = 3;
				}

				String[] indeksGodina = indeksVrednost.split("-");
				if (Integer.parseInt(indeksGodina[2]) != Integer.parseInt(upisVrednost)) {
					JOptionPane.showMessageDialog(getContentPane(),
							"Godina na indeksu i godina upisa se razlikuju!");
					return;
				}

				// REFERENCE:
				// https://stackoverflow.com/questions/136419/get-integer-value-of-the-current-year-in-java
				int trenutnaGodina = Year.now().getValue();
				if (Integer.parseInt(upisVrednost) > trenutnaGodina - godinaStudija) {
					JOptionPane.showMessageDialog(getContentPane(), "Pogrešna trenutna godina studija!");
					return;
				}

				Status status = Status.B;
				if (budzetCombo.getSelectedItem().toString() == "Samofinansiranje") {
					status = Status.S;
				}
				
				for(Student s: StudentController.getInstance().getStudenti()) {
					if(s.getBrIndeksa().equals(indeksVrednost) && !stariIndeks.equals(indeksVrednost)) {
						JOptionPane.showMessageDialog(getContentPane(), "Već postoji student sa unetim indeksom!");
						return;
					}
				}

				// REFERENCE:
				// https://stackoverflow.com/questions/8689122/joptionpane-yes-no-options-confirm-dialog-box-issue
				JOptionPane confirm = new JOptionPane();
				@SuppressWarnings("static-access")
				int answer = confirm.showConfirmDialog(getContentPane(),
						"Da li ste sigurni da želite da izmenite informacije ovog studenta?", "Potvrda izmene",
						JOptionPane.OK_CANCEL_OPTION);
				if (answer == JOptionPane.YES_OPTION) {

					student.setIme(imeVrednost);
					student.setPrezime(prezimeVrednost);
					// REFERENCE:
					// https://mkyong.com/java8/java-8-how-to-convert-string-to-localdate/
					student.setDatumRodjenja(LocalDate.parse(datumVrednost, formatter));
					student.setTelefon(telefonVrednost);
					student.setAdresa(adresaVrednost);
					student.setBrIndeksa(indeksVrednost);
					student.setEmail(emailVrednost);
					student.setGodinaUpisa(Integer.parseInt(upisVrednost));
					student.setTrenutnaGodina(godinaStudija + 1);
					student.setStatusStudenta(status);

					StudentController.getInstance().izmeniStudenta(table.getSelectedRow());
					
					table.getSorter().setSortable(0, false);
					table.getSorter().setSortable(1, false);
					table.getSorter().setSortable(2, false);
					table.getSorter().setSortable(3, false);
					table.getSorter().setSortable(4, false);
					table.getSorter().setSortable(5, false);
					
					dispose();
				}

			}
		});
		panel.add(ok);

		// -----------------------------------------------------------

		JTabbedPane infoTabbedPane = new JTabbedPane();
		getContentPane().add(infoTabbedPane);
		infoTabbedPane.addTab("Informacije", panel);

		JPanel polozeniPanel = new JPanel();
		polozeniPanel.setSize(500, 600);
		infoTabbedPane.addTab("Položeni", polozeniPanel);

		PolozeniIspitiController.getInstance().initSpisakPolozenih(student);
		AbstractTableModelPolozeniIspiti model = (AbstractTableModelPolozeniIspiti) TablePolozeniIspiti.getInstance()
				.getModel();
		model.fireTableDataChanged();
		validate();

		polozeniTable = TablePolozeniIspiti.getInstance();

		JScrollPane polozeni = new JScrollPane(polozeniTable);
		polozeni.setBounds(5, 40, 475, 430);

		JButton ponistiOcenu = new JButton("Poništi ocenu");
		ponistiOcenu.setBounds(5, 5, 115, 30);
		ponistiOcenu.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		ponistiOcenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (polozeniTable.getSelectedRow() >= 0) {
					int answer = JOptionPane.showConfirmDialog(getContentPane(),
							"Da li ste sigurni da želite da poništite ocenu?", "Poništavanje ocene",
							JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) {

						String sifra = (String) TablePolozeniIspiti.getInstance()
								.getValueAt(TablePolozeniIspiti.getInstance().getSelectedRow(), 0);

						for (Predmet p : PredmetController.getInstance().getPredmeti()) {
							if (p.getSifra().equals(sifra)) {
								for (int i = 0; i < p.getPolozili().size(); i++) {
									if (p.getPolozili().get(i).getBrIndeksa().equals(student.getBrIndeksa())) {
										p.getPolozili().remove(i);
									}
								}
							}
						}

						for (Predmet p : PredmetController.getInstance().getPredmeti()) {
							if (p.getSifra().equals(sifra)) {
								p.dodajNisuPolozili(student);
							}
						}

						PolozeniIspitiController.getInstance().ponistiOcenu(polozeniTable.getSelectedRow());

						izracunaj(student);
						prosek.setText("Prosečna ocena: " + avgOcena);
						ESPB.setText("Ukupno ESPB: " + ukupnoESPB);
						student.setProsecnaOcena(avgOcena);
						MainFrame.getInstance().azurirajStudente("", -1);

						AbstractTableModelPolozeniIspiti model = (AbstractTableModelPolozeniIspiti) polozeniTable
								.getModel();
						model.fireTableDataChanged();
						validate();

						AbstractTableModelNepolozeniIspiti modelNepolozenih = (AbstractTableModelNepolozeniIspiti) nepolozeniTable
								.getModel();
						modelNepolozenih.fireTableDataChanged();
						validate();

					}

				}

			}

		});

		// REFERENCE:
		// https://www.caveofprogramming.com/guest-posts/absolute-layout-in-swing.html
		polozeniPanel.setLayout(null);
		polozeniPanel.add(polozeni);
		polozeniPanel.add(ponistiOcenu);

		this.izracunaj(student);
		student.setProsecnaOcena(avgOcena);
		MainFrame.getInstance().azurirajStudente("", -1);

		prosek = new JLabel("Prosečna ocena: " + avgOcena);
		prosek.setBounds(340, 475, 140, 25);
		prosek.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		polozeniPanel.add(prosek);

		ESPB = new JLabel("Ukupno ESPB: " + ukupnoESPB);
		ESPB.setBounds(340, 505, 140, 25);
		ESPB.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		polozeniPanel.add(ESPB);
		infoTabbedPane.setSelectedIndex(0);

		infoTabbedPane.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {

			}
		});

		// nepolozeni
		JPanel nepolozeniPanel = new JPanel();
		nepolozeniPanel.setSize(500, 600);
		infoTabbedPane.addTab("Nepoloženi", nepolozeniPanel);

		NepolozeniIspitiController.getInstance().initSpisakNepolozenih(student);
		AbstractTableModelNepolozeniIspiti modelNepolozeni = (AbstractTableModelNepolozeniIspiti) TableNepolozeniIspiti
				.getInstance().getModel();
		modelNepolozeni.fireTableDataChanged();
		validate();

		nepolozeniTable = TableNepolozeniIspiti.getInstance();

		JScrollPane nepolozeni = new JScrollPane(nepolozeniTable);
		nepolozeni.setBounds(5, 40, 475, 430);

		JButton dodaj = new JButton("Dodaj");
		dodaj.setBounds(5, 5, 100, 30);
		dodaj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				SlobodniPredmetiController.getInstance().initSpisakPolozenih(student);
				SlobodniPredmetiController.getInstance().initSpisakNepolozenih(student);
				SlobodniPredmetiController.getInstance().initSlobodne(student);
				SlobodniPredmetiController.getInstance().popuniSlobodne(student);

				new DodajSlobodanPredmetDialog(getContentPane(), "Dodavanje predmeta", true, student);

				modelNepolozeni.fireTableDataChanged();
				validate();
			}

		});

		JButton obrisi = new JButton("Obriši");
		obrisi.setBounds(130, 5, 100, 30);
		obrisi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (nepolozeniTable.getSelectedRow() >= 0) {
					int answer = JOptionPane.showConfirmDialog(getContentPane(),
							"Da li ste sigurni da želite da uklonite predmet?", "Uklanjanje predmeta",
							JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) {

						String sifra = (String) nepolozeniTable.getValueAt(nepolozeniTable.getSelectedRow(), 0);

						student.izbrisiNepolozen(nepolozeniTable.getSelectedRow());

						for (Predmet p : PredmetController.getInstance().getPredmeti()) {
							if (sifra.equals(p.getSifra())) {
								student.dodajSlobodan(p);
							}
						}
						model.fireTableDataChanged();
						modelNepolozeni.fireTableDataChanged();
						validate();
					}
				}
			}

		});

		JButton polaganje = new JButton("Polaganje");
		polaganje.setBounds(255, 5, 130, 30);
		polaganje.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (nepolozeniTable.getSelectedRow() >= 0) {

					PolaganjeDialog polaganje = new PolaganjeDialog(parent, "Unos ocene", true,
							nepolozeniTable, datumSablon, student, model);

					polaganje.setVisible(true);

					modelNepolozeni.fireTableDataChanged();
					validate();
					
					izracunaj(student);
					prosek.setText("Prosečna ocena: " + avgOcena);
					ESPB.setText("Ukupno ESPB: " + ukupnoESPB);
					student.setProsecnaOcena(avgOcena);
					MainFrame.getInstance().azurirajStudente("", -1);

					AbstractTableModelPolozeniIspiti model = (AbstractTableModelPolozeniIspiti) polozeniTable
							.getModel();
					model.fireTableDataChanged();
					validate();

					AbstractTableModelNepolozeniIspiti modelNepolozenih = (AbstractTableModelNepolozeniIspiti) nepolozeniTable
							.getModel();
					modelNepolozenih.fireTableDataChanged();
					validate();
				}
			}
		});

		nepolozeniPanel.setLayout(null);
		nepolozeniPanel.add(nepolozeni);
		nepolozeniPanel.add(dodaj);
		nepolozeniPanel.add(obrisi);
		nepolozeniPanel.add(polaganje);

	}

	public void izracunaj(Student s) {
		double prosecnaOcena = 0;
		int brOcena = 0;
		for (int i = 0; i < s.getSpisakPolozenih().size(); i++) {
			prosecnaOcena += s.getSpisakPolozenih().get(i).getVrednostOcene();
			brOcena++;
		}
		
		// REFERENCE: https://www.baeldung.com/java-not-a-number
		if (Double.isNaN(prosecnaOcena/brOcena)) {
					avgOcena = 0;
		}
		else {
			avgOcena = new BigDecimal(prosecnaOcena/brOcena).setScale(2, RoundingMode.HALF_UP).doubleValue();
		}

		ukupnoESPB = 0;
		for (int i = 0; i < s.getSpisakPolozenih().size(); i++) {
			ukupnoESPB += s.getSpisakPolozenih().get(i).getPredmet().getESPB();
		}
	}

}
