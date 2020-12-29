package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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

import controller.PolozeniIspitiController;
import controller.StudentController;
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

	private String emailSablon = "([\\p{IsLowercase}\\p{IsUppercase}0-9])+(\\.)?"
			+ "([\\p{IsLowercase}\\p{IsUppercase}0-9])+(\\@)\\p{IsAlphabetic}+([\\p{IsAlphabetic}\\.])*\\.\\p{IsAlphabetic}+";

	private String datumSablon = "(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((18|19|20|21)\\d\\d).?";

	private String adresaSablon = "\\p{IsUppercase}\\p{IsLowercase}+(\\p{IsWhite_Space}\\p{IsAlphabetic}+)*"
			+ "(\\p{IsWhite_Space}\\p{IsDigit}+)\\p{IsAlphabetic}?(\\,)(\\p{IsWhite_Space})?\\p{IsUppercase}(\\p{IsLowercase})+"
			+ "(\\p{IsWhite_Space}\\p{IsUppercase}(\\p{IsLowercase})+)?";


	private String indeksSablon = "([A-Za-z]{2}|[A-Za-z][1-9])-([0-9]{1,3})-(20[0-9]{2})";

	private String upisSablon = "(20[0-9]{2})";

	private TablePolozeniIspiti polozeniTable;

	private TableNepolozeniIspiti nepolozeniTable;

	private float avgOcena = 0;

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

	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	public IzmeniStudentaDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);

		setSize(500, 600);
		setResizable(false);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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
		springLayout.putConstraint(SpringLayout.NORTH, imeUnos, 11, SpringLayout.NORTH, ime);
		springLayout.putConstraint(SpringLayout.WEST, imeUnos, 6, SpringLayout.EAST, ime);
		springLayout.putConstraint(SpringLayout.EAST, imeUnos, -68, SpringLayout.EAST, panel);
		panel.add(imeUnos);
		imeUnos.requestFocusInWindow();
		imeUnos.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				imeUnos.setBackground(Color.WHITE);
				enableButton(ok);
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// REFERENCE:
				// https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
				if (!(Pattern.compile(imeSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(imeUnos.getText())
						.matches())) {
					imeUnos.setBackground(Color.RED);
					imeKorektno = false;
				} else {
					imeUnos.transferFocus();
					imeUnos.setBackground(Color.WHITE);
					imeKorektno = true;
					if (imeKorektno && prezimeKorektno && datumKorektno && adresaKorektno && telefonKorektno
							&& emailKorektno && indeksKorektno && upisKorektno) {
						ispravno = true;
					} else {
						ispravno = false;
					}

				}
				enableButton(ok);
			}

		});

		JTextField prezimeUnos = new JTextField();
		prezimeUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, prezimeUnos, 11, SpringLayout.NORTH, prezime);
		springLayout.putConstraint(SpringLayout.WEST, prezimeUnos, 6, SpringLayout.EAST, prezime);
		springLayout.putConstraint(SpringLayout.EAST, prezimeUnos, 0, SpringLayout.EAST, imeUnos);
		panel.add(prezimeUnos);
		prezimeUnos.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				prezimeUnos.setBackground(Color.WHITE);
				enableButton(ok);
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// REFERENCE:
				// https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
				if (!(Pattern.compile(prezimeSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(prezimeUnos.getText())
						.matches())) {
					prezimeUnos.setBackground(Color.RED);
					prezimeKorektno = false;
				} else {
					prezimeUnos.transferFocus();
					prezimeUnos.setBackground(Color.WHITE);
					prezimeKorektno = true;
					if (imeKorektno && prezimeKorektno && datumKorektno && adresaKorektno && telefonKorektno
							&& emailKorektno && indeksKorektno && upisKorektno) {
						ispravno = true;
					} else {
						ispravno = false;
					}

				}
				enableButton(ok);
			}

		});

		JTextField datumUnos = new JTextField();
		datumUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, datumUnos, 11, SpringLayout.NORTH, datum);
		springLayout.putConstraint(SpringLayout.WEST, datumUnos, 6, SpringLayout.EAST, datum);
		springLayout.putConstraint(SpringLayout.EAST, datumUnos, 0, SpringLayout.EAST, imeUnos);
		panel.add(datumUnos);
		datumUnos.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				datumUnos.setBackground(Color.WHITE);
				enableButton(ok);
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// REFERENCE:
				// https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
				if (!(Pattern.compile(datumSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(datumUnos.getText())
						.matches())) {
					datumUnos.setBackground(Color.RED);
					datumKorektno = false;
				} else {
					datumUnos.transferFocus();
					datumUnos.setBackground(Color.WHITE);
					datumKorektno = true;
					if (imeKorektno && prezimeKorektno && datumKorektno && adresaKorektno && telefonKorektno
							&& emailKorektno && indeksKorektno && upisKorektno) {
						ispravno = true;
					} else {
						ispravno = false;
					}

				}
				enableButton(ok);
			}

		});

		JTextField adresaUnos = new JTextField();
		adresaUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, adresaUnos, 11, SpringLayout.NORTH, adresa);
		springLayout.putConstraint(SpringLayout.WEST, adresaUnos, 6, SpringLayout.EAST, adresa);
		springLayout.putConstraint(SpringLayout.EAST, adresaUnos, 0, SpringLayout.EAST, imeUnos);
		panel.add(adresaUnos);
		adresaUnos.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				adresaUnos.setBackground(Color.WHITE);
				enableButton(ok);
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// REFERENCE:
				// https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
				if (!(Pattern.compile(adresaSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(adresaUnos.getText())
						.matches())) {
					adresaUnos.setBackground(Color.RED);
					adresaKorektno = false;
				} else {
					adresaUnos.transferFocus();
					adresaUnos.setBackground(Color.WHITE);
					adresaKorektno = true;
					if (imeKorektno && prezimeKorektno && datumKorektno && adresaKorektno && telefonKorektno
							&& emailKorektno && indeksKorektno && upisKorektno) {
						ispravno = true;
					} else {
						ispravno = false;
					}

				}
				enableButton(ok);
			}

		});

		JTextField telefonUnos = new JTextField();
		telefonUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, telefonUnos, 11, SpringLayout.NORTH, telefon);
		springLayout.putConstraint(SpringLayout.WEST, telefonUnos, 6, SpringLayout.EAST, telefon);
		springLayout.putConstraint(SpringLayout.EAST, telefonUnos, 0, SpringLayout.EAST, imeUnos);
		panel.add(telefonUnos);
		telefonUnos.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				telefonUnos.setBackground(Color.WHITE);
				enableButton(ok);
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// REFERENCE:
				// https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
				if (!(Pattern.compile(telefonSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(telefonUnos.getText())
						.matches())) {
					telefonUnos.setBackground(Color.RED);
					telefonKorektno = false;
				} else {
					telefonUnos.transferFocus();
					telefonUnos.setBackground(Color.WHITE);
					telefonKorektno = true;
					if (imeKorektno && prezimeKorektno && datumKorektno && adresaKorektno && telefonKorektno
							&& emailKorektno && indeksKorektno && upisKorektno) {
						ispravno = true;
					} else {
						ispravno = false;
					}

				}
				enableButton(ok);
			}

		});

		JTextField emailUnos = new JTextField();
		emailUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, emailUnos, 11, SpringLayout.NORTH, email);
		springLayout.putConstraint(SpringLayout.WEST, emailUnos, 6, SpringLayout.EAST, email);
		springLayout.putConstraint(SpringLayout.EAST, emailUnos, 0, SpringLayout.EAST, imeUnos);
		panel.add(emailUnos);
		emailUnos.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				emailUnos.setBackground(Color.WHITE);
				enableButton(ok);
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// REFERENCE:
				// https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
				if (!(Pattern.compile(emailSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(emailUnos.getText())
						.matches())) {
					emailUnos.setBackground(Color.RED);
					emailKorektno = false;
				} else {
					emailUnos.transferFocus();
					emailUnos.setBackground(Color.WHITE);
					emailKorektno = true;
					if (imeKorektno && prezimeKorektno && datumKorektno && adresaKorektno && telefonKorektno
							&& emailKorektno && indeksKorektno && upisKorektno) {
						ispravno = true;
					} else {
						ispravno = false;
					}

				}
				enableButton(ok);
			}

		});

		JTextField indeksUnos = new JTextField();
		indeksUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, indeksUnos, 11, SpringLayout.NORTH, indeks);
		springLayout.putConstraint(SpringLayout.WEST, indeksUnos, 6, SpringLayout.EAST, indeks);
		springLayout.putConstraint(SpringLayout.EAST, indeksUnos, 0, SpringLayout.EAST, imeUnos);
		panel.add(indeksUnos);
		indeksUnos.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				indeksUnos.setBackground(Color.WHITE);
				enableButton(ok);
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// REFERENCE:
				// https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
				if (!(Pattern.compile(indeksSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(indeksUnos.getText())
						.matches())) {
					indeksUnos.setBackground(Color.RED);
					indeksKorektno = false;
				} else {
					indeksUnos.transferFocus();
					indeksUnos.setBackground(Color.WHITE);
					indeksKorektno = true;
					if (imeKorektno && prezimeKorektno && datumKorektno && adresaKorektno && telefonKorektno
							&& emailKorektno && indeksKorektno && upisKorektno) {
						ispravno = true;
					} else {
						ispravno = false;
					}

				}
				enableButton(ok);
			}

		});

		JTextField upisUnos = new JTextField();
		upisUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, upisUnos, 11, SpringLayout.NORTH, upis);
		springLayout.putConstraint(SpringLayout.WEST, upisUnos, 6, SpringLayout.EAST, upis);
		springLayout.putConstraint(SpringLayout.EAST, upisUnos, 0, SpringLayout.EAST, imeUnos);
		panel.add(upisUnos);
		upisUnos.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				upisUnos.setBackground(Color.WHITE);
				enableButton(ok);
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// REFERENCE:
				// https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
				if (!(Pattern.compile(upisSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(upisUnos.getText())
						.matches())) {
					upisUnos.setBackground(Color.RED);
					upisKorektno = false;
				} else {
					upisUnos.transferFocus();
					upisUnos.setBackground(Color.WHITE);
					upisKorektno = true;
					if (imeKorektno && prezimeKorektno && datumKorektno && adresaKorektno && telefonKorektno
							&& emailKorektno && indeksKorektno && upisKorektno) {
						ispravno = true;
					} else {
						ispravno = false;
					}

				}
				enableButton(ok);
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
		Student student = StudentController.getInstance().getStudent(table.getSelectedRow());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");

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

		ok = new JButton("Potvrdi");
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
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "Pogrešno uneto ime!");
					return;
				}

				if (!(Pattern.compile(prezimeSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(prezimeVrednost)
						.matches())) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "Pogrešno uneto prezime!");
					return;
				}

				if (!Pattern.matches(datumSablon, datumVrednost)) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "Pogrešno unet datum rođenja!");
					return;
				}

				if (!(Pattern.compile(adresaSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(adresaVrednost)
						.matches())) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "Pogrešno uneta adresa!");
					return;
				}

				if (!Pattern.matches(telefonSablon, telefonVrednost)) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "Pogrešno unet kontakt telefon!");
					return;
				}

				if (!Pattern.matches(emailSablon, emailVrednost)) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "Pogrešno uneta e-mail adresa!");
					return;
				}

				if (!(Pattern.compile(indeksSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(indeksVrednost)
						.matches())) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "Pogrešno unet broj indeksa!");
					return;
				}

				String[] godRodjenja = datumVrednost.split("\\.");
				if (Integer.parseInt(upisVrednost) - Integer.parseInt(godRodjenja[2]) < 16) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "Pogrešno uneta godina upisa!");
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
				// REFERENCE:
				// https://stackoverflow.com/questions/136419/get-integer-value-of-the-current-year-in-java
				int trenutnaGodina = Year.now().getValue();
				if (Integer.parseInt(upisVrednost) > trenutnaGodina - godinaStudija) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "Pogrešna trenutna godina studija!");
					return;
				}

				String[] indeksGodina = indeksVrednost.split("-");
				if (Integer.parseInt(indeksGodina[2]) != Integer.parseInt(upisVrednost)) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(),
							"Godina na indeksu i godina upisa se razlikuju!");
					return;
				}

				Status status = Status.B;
				if (budzetCombo.getSelectedItem().toString() == "Samofinansiranje") {
					status = Status.S;
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
					dispose();
				}

			}
		});
		panel.add(ok);

		//-----------------------------------------------------------
		
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

		nepolozeniTable = TableNepolozeniIspiti.getInstance();
		
		JScrollPane nepolozeni = new JScrollPane(nepolozeniTable);
		nepolozeni.setBounds(5, 40, 475, 430);

		JButton dodaj = new JButton("Dodaj");
		dodaj.setBounds(5, 5, 100, 30);
		JButton obrisi = new JButton("Obriši");
		obrisi.setBounds(130, 5, 100, 30);
		JButton polaganje = new JButton("Polaganje");
		polaganje.setBounds(255, 5, 130, 30);

		nepolozeniPanel.setLayout(null);
		nepolozeniPanel.add(nepolozeni);
		nepolozeniPanel.add(dodaj);
		nepolozeniPanel.add(obrisi);
		nepolozeniPanel.add(polaganje);

	}

	
	
	
	
	
	public void izracunaj(Student s) {
		float prosecnaOcena = 0;
		float brOcena = 0;
		for (int i = 0; i < s.getSpisakPolozenih().size(); i++) {
			prosecnaOcena += s.getSpisakPolozenih().get(i).getVrednostOcene();
			brOcena++;
			validate();
		}
		avgOcena = prosecnaOcena / brOcena;

		// REFERENCE: https://www.baeldung.com/java-not-a-number
		if (Float.isNaN(avgOcena)) {
			avgOcena = 0;
		}

		ukupnoESPB = 0;
		for (int i = 0; i < s.getSpisakPolozenih().size(); i++) {
			ukupnoESPB += s.getSpisakPolozenih().get(i).getPredmet().getESPB();
			validate();
		}
	}

	public void enableButton(JButton button) {
		if (!ispravno) {
			button.setEnabled(false);
		} else {
			button.setEnabled(true);
		}
	}

}
