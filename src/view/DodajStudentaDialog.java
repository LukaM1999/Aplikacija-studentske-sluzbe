package view;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.PredmetController;
import controller.StudentController;
import model.Student;
import model.Student.Status;

import javax.swing.JComboBox;
import java.awt.Color;

public class DodajStudentaDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5241927591473531655L;

	// REFERENCE:
	// https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
	private String imeSablon = "\\p{IsUppercase}\\p{IsAlphabetic}+(\\p{IsWhite_Space}\\p{IsUppercase}\\p{IsAlphabetic}+)*";

	private String prezimeSablon = "\\p{IsUppercase}\\p{IsAlphabetic}+(\\p{IsWhite_Space}\\p{IsUppercase}\\p{IsAlphabetic}+)*";

	private String telefonSablon = "[0-9]{8,12}?";

	private String emailSablon = "([\\p{IsLowercase}\\p{IsUppercase}0-9\\.])+"
			+ "([\\p{IsLowercase}\\p{IsUppercase}0-9])+(\\@)\\p{IsAlphabetic}+([\\p{IsAlphabetic}\\.])*\\.\\p{IsAlphabetic}+";

	// REFERENCE:
	// https://stackoverflow.com/questions/2149680/regex-date-format-validation-on-java
	private String datumSablon = "(0[1-9]|[12][0-9]|3[01]).(0[1-9]|1[012]).((18|19|20|21)\\d\\d)\\.";

	private String adresaSablon = "\\p{IsUppercase}\\p{IsLowercase}+(\\p{IsWhite_Space}\\p{IsAlphabetic}+)*"
			+ "(\\p{IsWhite_Space}\\p{IsDigit}+)\\p{IsAlphabetic}?(\\,)(\\p{IsWhite_Space})?\\p{IsUppercase}(\\p{IsLowercase})+"
			+ "(\\p{IsWhite_Space}\\p{IsUppercase}(\\p{IsLowercase})+)?";

	private String indeksSablon = "([\\p{IsUppercase}]{2}|[\\p{IsUppercase}][1-9])-([0-9]{1,3})-(20[0-9]{2})";

	private String upisSablon = "(20[0-9]{2})";

	private boolean imeKorektno;
	private boolean prezimeKorektno;
	private boolean datumKorektno;
	private boolean adresaKorektno;
	private boolean telefonKorektno;
	private boolean emailKorektno;
	private boolean indeksKorektno;
	private boolean upisKorektno;

	private boolean ispravno = false;

	private JButton ok;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DodajStudentaDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);

		setSize(500, 600);
		setResizable(false);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// REFERENCE: https://docs.oracle.com/javase/tutorial/uiswing/layout/spring.html
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		// Labels
		JLabel ime = new JLabel("Ime*");
		ime.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, ime, 35, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, ime, 35, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, ime, 75, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, ime, 205, SpringLayout.WEST, getContentPane());
		getContentPane().add(ime);

		JLabel prezime = new JLabel("Prezime*");
		prezime.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, prezime, 5, SpringLayout.SOUTH, ime);
		springLayout.putConstraint(SpringLayout.WEST, prezime, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, prezime, 45, SpringLayout.SOUTH, ime);
		springLayout.putConstraint(SpringLayout.EAST, prezime, 0, SpringLayout.EAST, ime);
		getContentPane().add(prezime);

		JLabel datum = new JLabel("Datum rođenja*");
		datum.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, datum, 5, SpringLayout.SOUTH, prezime);
		springLayout.putConstraint(SpringLayout.WEST, datum, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, datum, 45, SpringLayout.SOUTH, prezime);
		springLayout.putConstraint(SpringLayout.EAST, datum, 0, SpringLayout.EAST, ime);
		getContentPane().add(datum);

		JLabel adresa = new JLabel("Adresa stanovanja*");
		adresa.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, adresa, 5, SpringLayout.SOUTH, datum);
		springLayout.putConstraint(SpringLayout.WEST, adresa, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, adresa, 45, SpringLayout.SOUTH, datum);
		springLayout.putConstraint(SpringLayout.EAST, adresa, 0, SpringLayout.EAST, ime);
		getContentPane().add(adresa);

		JLabel telefon = new JLabel("Broj telefona*");
		telefon.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, telefon, 5, SpringLayout.SOUTH, adresa);
		springLayout.putConstraint(SpringLayout.WEST, telefon, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, telefon, 45, SpringLayout.SOUTH, adresa);
		springLayout.putConstraint(SpringLayout.EAST, telefon, 0, SpringLayout.EAST, ime);
		getContentPane().add(telefon);

		JLabel email = new JLabel("E-mail adresa*");
		email.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, email, 5, SpringLayout.SOUTH, telefon);
		springLayout.putConstraint(SpringLayout.WEST, email, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, email, 45, SpringLayout.SOUTH, telefon);
		springLayout.putConstraint(SpringLayout.EAST, email, 0, SpringLayout.EAST, ime);
		getContentPane().add(email);

		JLabel indeks = new JLabel("Broj indeksa*");
		indeks.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, indeks, 5, SpringLayout.SOUTH, email);
		springLayout.putConstraint(SpringLayout.WEST, indeks, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, indeks, 45, SpringLayout.SOUTH, email);
		springLayout.putConstraint(SpringLayout.EAST, indeks, 0, SpringLayout.EAST, ime);
		getContentPane().add(indeks);

		JLabel upis = new JLabel("Godina upisa*");
		upis.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, upis, 5, SpringLayout.SOUTH, indeks);
		springLayout.putConstraint(SpringLayout.WEST, upis, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, upis, 45, SpringLayout.SOUTH, indeks);
		springLayout.putConstraint(SpringLayout.EAST, upis, 0, SpringLayout.EAST, ime);
		getContentPane().add(upis);

		JLabel godinaStudija = new JLabel("Trenutna godina studija*");
		godinaStudija.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, godinaStudija, 5, SpringLayout.SOUTH, upis);
		springLayout.putConstraint(SpringLayout.WEST, godinaStudija, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, godinaStudija, 45, SpringLayout.SOUTH, upis);
		springLayout.putConstraint(SpringLayout.EAST, godinaStudija, 0, SpringLayout.EAST, ime);
		getContentPane().add(godinaStudija);

		JLabel finansiranje = new JLabel("Način finansiranja*");
		finansiranje.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, finansiranje, 5, SpringLayout.SOUTH, godinaStudija);
		springLayout.putConstraint(SpringLayout.WEST, finansiranje, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, finansiranje, 45, SpringLayout.SOUTH, godinaStudija);
		springLayout.putConstraint(SpringLayout.EAST, finansiranje, 0, SpringLayout.EAST, ime);
		getContentPane().add(finansiranje);

		// Text fields
		JTextField imeUnos = new JTextField();
		imeUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, imeUnos, 11, SpringLayout.NORTH, ime);
		springLayout.putConstraint(SpringLayout.WEST, imeUnos, 6, SpringLayout.EAST, ime);
		springLayout.putConstraint(SpringLayout.EAST, imeUnos, -68, SpringLayout.EAST, getContentPane());
		getContentPane().add(imeUnos);
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
                if (!(Pattern.compile(imeSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(imeUnos.getText()).matches())) {
                    imeKorektno = false;
                    imeUnos.setBackground(Color.WHITE);
                    ispravno = false;
                } 
                else { 
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
		springLayout.putConstraint(SpringLayout.NORTH, prezimeUnos, 11, SpringLayout.NORTH, prezime);
		springLayout.putConstraint(SpringLayout.WEST, prezimeUnos, 6, SpringLayout.EAST, prezime);
		springLayout.putConstraint(SpringLayout.EAST, prezimeUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(prezimeUnos);
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
                if (!(Pattern.compile(prezimeSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(prezimeUnos.getText()).matches())) {
                    prezimeKorektno = false;
                    prezimeUnos.setBackground(Color.WHITE);
                    ispravno = false;
                } 
                else { 
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
		springLayout.putConstraint(SpringLayout.NORTH, datumUnos, 11, SpringLayout.NORTH, datum);
		springLayout.putConstraint(SpringLayout.WEST, datumUnos, 6, SpringLayout.EAST, datum);
		springLayout.putConstraint(SpringLayout.EAST, datumUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(datumUnos);
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
                if (!(Pattern.compile(datumSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(datumUnos.getText()).matches())) {
                    datumKorektno = false;
                    datumUnos.setBackground(Color.WHITE);
                    ispravno = false;
                } 
                else { 
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
		springLayout.putConstraint(SpringLayout.NORTH, adresaUnos, 11, SpringLayout.NORTH, adresa);
		springLayout.putConstraint(SpringLayout.WEST, adresaUnos, 6, SpringLayout.EAST, adresa);
		springLayout.putConstraint(SpringLayout.EAST, adresaUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(adresaUnos);
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
                if (!(Pattern.compile(adresaSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(adresaUnos.getText()).matches())) {
                    adresaKorektno = false;
                    adresaUnos.setBackground(Color.WHITE);
                    ispravno = false;
                } 
                else { 
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
		springLayout.putConstraint(SpringLayout.NORTH, telefonUnos, 11, SpringLayout.NORTH, telefon);
		springLayout.putConstraint(SpringLayout.WEST, telefonUnos, 6, SpringLayout.EAST, telefon);
		springLayout.putConstraint(SpringLayout.EAST, telefonUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(telefonUnos);
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
                if (!(Pattern.compile(telefonSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(telefonUnos.getText()).matches())) {
                    telefonKorektno = false;
                    telefonUnos.setBackground(Color.WHITE);
                    ispravno = false;
                } 
                else { 
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
		springLayout.putConstraint(SpringLayout.NORTH, emailUnos, 11, SpringLayout.NORTH, email);
		springLayout.putConstraint(SpringLayout.WEST, emailUnos, 6, SpringLayout.EAST, email);
		springLayout.putConstraint(SpringLayout.EAST, emailUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(emailUnos);
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
                if (!(Pattern.compile(emailSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(emailUnos.getText()).matches())) {
                    emailKorektno = false;
                    emailUnos.setBackground(Color.WHITE);
                    ispravno = false;
                } 
                else { 
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
		springLayout.putConstraint(SpringLayout.NORTH, indeksUnos, 11, SpringLayout.NORTH, indeks);
		springLayout.putConstraint(SpringLayout.WEST, indeksUnos, 6, SpringLayout.EAST, indeks);
		springLayout.putConstraint(SpringLayout.EAST, indeksUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(indeksUnos);
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
                if (!(Pattern.compile(indeksSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(indeksUnos.getText()).matches())) {
                    indeksKorektno = false;
                    indeksUnos.setBackground(Color.WHITE);
                    ispravno = false;
                } 
                else { 
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
		springLayout.putConstraint(SpringLayout.NORTH, upisUnos, 11, SpringLayout.NORTH, upis);
		springLayout.putConstraint(SpringLayout.WEST, upisUnos, 6, SpringLayout.EAST, upis);
		springLayout.putConstraint(SpringLayout.EAST, upisUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(upisUnos);
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
                if (!(Pattern.compile(upisSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(upisUnos.getText()).matches())) {
                    upisKorektno = false;
                    upisUnos.setBackground(Color.WHITE);
                    ispravno = false;
                } 
                else { 
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
		getContentPane().add(godinaCombo);

		String[] nacinFinansiranja = new String[] { "Budžet", "Samofinansiranje" };
		JComboBox budzetCombo = new JComboBox(nacinFinansiranja);
		budzetCombo.setBackground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.NORTH, budzetCombo, 21, SpringLayout.SOUTH, godinaCombo);
		springLayout.putConstraint(SpringLayout.WEST, budzetCombo, 6, SpringLayout.EAST, finansiranje);
		springLayout.putConstraint(SpringLayout.EAST, budzetCombo, 0, SpringLayout.EAST, imeUnos);
		budzetCombo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		budzetCombo.setSelectedIndex(0);
		getContentPane().add(budzetCombo);

		// Buttons
		JButton cancel = new JButton("Odustani");
		cancel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.WEST, cancel, 294, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, cancel, -68, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, cancel, 37, SpringLayout.SOUTH, finansiranje);
		springLayout.putConstraint(SpringLayout.SOUTH, cancel, -10, SpringLayout.SOUTH, getContentPane());
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(cancel);

		ok = new JButton("Potvrdi");
		ok.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, ok, 1, SpringLayout.NORTH, cancel);
		springLayout.putConstraint(SpringLayout.WEST, ok, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, ok, 1, SpringLayout.SOUTH, cancel);
		springLayout.putConstraint(SpringLayout.EAST, ok, 157, SpringLayout.WEST, getContentPane());
		ok.setEnabled(false);
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
					// REFERENCE:
					// https://stackoverflow.com/questions/6270354/how-to-open-warning-information-error-dialog-in-swing/24164386
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
					JOptionPane.showMessageDialog(getContentPane(), "Godina na indeksu i godina upisa se razlikuju!");
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
				// REFERENCE:
				// https://howtodoinjava.com/java/collections/arraylist/iterate-through-objects/
				for (Student s : StudentController.getInstance().getStudenti()) {
					if (s.getBrIndeksa().equals(indeksVrednost)) {
						JOptionPane.showMessageDialog(MainFrame.getInstance(),
								"Već postoji student " + "sa ovim brojem indeksa!");
						return;
					}
				}

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");

				Student student = new Student(imeVrednost, prezimeVrednost, LocalDate.parse(datumVrednost, formatter),
						adresaVrednost, telefonVrednost, emailVrednost, indeksVrednost, Integer.parseInt(upisVrednost),
						godinaStudija + 1, status);

				for (int i = 0; i < PredmetController.getInstance().getPredmeti().size(); i++) {
					if (PredmetController.getInstance().getPredmet(i).getGodinaStudija() <= godinaStudija + 1) {
						student.dodajSlobodan(PredmetController.getInstance().getPredmet(i));
					}
				}

				StudentController.getInstance().dodajStudenta(student);
				dispose();

			}
		});
		getContentPane().add(ok);

	}
}
