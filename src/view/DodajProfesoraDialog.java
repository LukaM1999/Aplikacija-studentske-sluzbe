package view;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;

import controller.ProfesorController;
import model.Profesor;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;


public class DodajProfesoraDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6293346707131160182L;
	
	private String imeSablon = "\\p{IsUppercase}(\\p{IsAlphabetic}+)";

	private String prezimeSablon = "\\p{IsUppercase}(\\p{IsAlphabetic}+)";

	private String telefonSablon = "[0-9]{8,9}?";

	private String emailSablon = "([\\p{IsLowercase}\\p{IsUppercase}0-9])+(\\.)?"
			+ "([\\p{IsLowercase}\\p{IsUppercase}0-9])+(\\@)((gmail)|"
			+ "(maildrop)|(yahoo)|(hotmail))(\\.)com";
	
	//REFERENCE: https://stackoverflow.com/questions/2149680/regex-date-format-validation-on-java
	private String datumSablon = "(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((18|19|20|21)\\d\\d).?";
	
	private String adresaSablon = "\\p{IsUppercase}\\p{IsLowercase}+(\\p{IsWhite_Space}\\p{IsAlphabetic}+)?"
			+ "\\p{IsWhite_Space}\\p{IsDigit}+\\p{IsAlphabetic}?(\\,)(\\p{IsWhite_Space})?\\p{IsUppercase}(\\p{IsLowercase})+"
			+ "(\\p{IsWhite_Space}\\p{IsUppercase}(\\p{IsLowercase})+)?";
	
	private String licnaSablon = "[0-9]{9}";
	
	private String kancelarijaSablon = "[a-zA-Z0-9\\p{IsWhite_Space}]+";
	
	private String titulaSablon = "[A-Za-z\\p{IsWhite_Space}]+";
	
	private String zvanjeSablon = "[A-Za-z\\p{IsWhite_Space}]+";

	
	
	public DodajProfesoraDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 14));

		setSize(500, 600);
		setLocationRelativeTo(parent);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//REFERENCE: https://docs.oracle.com/javase/tutorial/uiswing/layout/spring.html
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		
		//Labels
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
		
		JLabel datum = new JLabel("Datum Rođenja*");
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
		
		JLabel telefon = new JLabel("Kontakt telefon*");
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
		
		JLabel kancelarija = new JLabel("Adresa kancelarije*");
		kancelarija.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, kancelarija, 5, SpringLayout.SOUTH, email);
		springLayout.putConstraint(SpringLayout.WEST, kancelarija, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, kancelarija, 45, SpringLayout.SOUTH, email);
		springLayout.putConstraint(SpringLayout.EAST, kancelarija, 0, SpringLayout.EAST, ime);
		getContentPane().add(kancelarija);
		
		JLabel brLicne = new JLabel("Broj lične karte*");
		brLicne.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, brLicne, 5, SpringLayout.SOUTH, kancelarija);
		springLayout.putConstraint(SpringLayout.WEST, brLicne, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, brLicne, 45, SpringLayout.SOUTH, kancelarija);
		springLayout.putConstraint(SpringLayout.EAST, brLicne, 0, SpringLayout.EAST, ime);
		getContentPane().add(brLicne);
		
		JLabel titula = new JLabel("Titula*");
		titula.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, titula, 5, SpringLayout.SOUTH, brLicne);
		springLayout.putConstraint(SpringLayout.WEST, titula, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, titula, 45, SpringLayout.SOUTH, brLicne);
		springLayout.putConstraint(SpringLayout.EAST, titula, 0, SpringLayout.EAST, ime);
		getContentPane().add(titula);
		
		JLabel zvanje = new JLabel("Zvanje*");
		zvanje.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, zvanje, 5, SpringLayout.SOUTH, titula);
		springLayout.putConstraint(SpringLayout.WEST, zvanje, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, zvanje, 45, SpringLayout.SOUTH, titula);
		springLayout.putConstraint(SpringLayout.EAST, zvanje, 0, SpringLayout.EAST, ime);
		getContentPane().add(zvanje);
		
		
		//Text fields
		JTextField imeUnos = new JTextField();
		imeUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, imeUnos, 11, SpringLayout.NORTH, ime);
		springLayout.putConstraint(SpringLayout.WEST, imeUnos, 6, SpringLayout.EAST, ime);
		springLayout.putConstraint(SpringLayout.EAST, imeUnos, -68, SpringLayout.EAST, getContentPane());
		getContentPane().add(imeUnos);
		
		JTextField prezimeUnos = new JTextField();
		prezimeUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, prezimeUnos, 11, SpringLayout.NORTH, prezime);
		springLayout.putConstraint(SpringLayout.WEST, prezimeUnos, 6, SpringLayout.EAST, prezime);
		springLayout.putConstraint(SpringLayout.EAST, prezimeUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(prezimeUnos);
		
		JTextField datumUnos = new JTextField();
		datumUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, datumUnos, 11, SpringLayout.NORTH, datum);
		springLayout.putConstraint(SpringLayout.WEST, datumUnos, 6, SpringLayout.EAST, datum);
		springLayout.putConstraint(SpringLayout.EAST, datumUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(datumUnos);
		
		JTextField adresaUnos = new JTextField();
		adresaUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, adresaUnos, 11, SpringLayout.NORTH, adresa);
		springLayout.putConstraint(SpringLayout.WEST, adresaUnos, 6, SpringLayout.EAST, adresa);
		springLayout.putConstraint(SpringLayout.EAST, adresaUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(adresaUnos);
		
		JTextField telefonUnos = new JTextField();
		telefonUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, telefonUnos, 11, SpringLayout.NORTH, telefon);
		springLayout.putConstraint(SpringLayout.WEST, telefonUnos, 6, SpringLayout.EAST, telefon);
		springLayout.putConstraint(SpringLayout.EAST, telefonUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(telefonUnos);
		
		JTextField emailUnos = new JTextField();
		emailUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, emailUnos, 11, SpringLayout.NORTH, email);
		springLayout.putConstraint(SpringLayout.WEST, emailUnos, 6, SpringLayout.EAST, email);
		springLayout.putConstraint(SpringLayout.EAST, emailUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(emailUnos);
		
		JTextField kancelarijaUnos = new JTextField();
		kancelarijaUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, kancelarijaUnos, 11, SpringLayout.NORTH, kancelarija);
		springLayout.putConstraint(SpringLayout.WEST, kancelarijaUnos, 6, SpringLayout.EAST, kancelarija);
		springLayout.putConstraint(SpringLayout.EAST, kancelarijaUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(kancelarijaUnos);
		
		JTextField licnaUnos = new JTextField();
		licnaUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, licnaUnos, 11, SpringLayout.NORTH, brLicne);
		springLayout.putConstraint(SpringLayout.WEST, licnaUnos, 6, SpringLayout.EAST, brLicne);
		springLayout.putConstraint(SpringLayout.EAST, licnaUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(licnaUnos);
		
		JTextField titulaUnos = new JTextField();
		titulaUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, titulaUnos, 11, SpringLayout.NORTH, titula);
		springLayout.putConstraint(SpringLayout.WEST, titulaUnos, 6, SpringLayout.EAST, titula);
		springLayout.putConstraint(SpringLayout.EAST, titulaUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(titulaUnos);
		
		JTextField zvanjeUnos = new JTextField();
		zvanjeUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, zvanjeUnos, 11, SpringLayout.NORTH, zvanje);
		springLayout.putConstraint(SpringLayout.WEST, zvanjeUnos, 6, SpringLayout.EAST, zvanje);
		springLayout.putConstraint(SpringLayout.EAST, zvanjeUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(zvanjeUnos);
		
		
		//Buttons
		JButton cancel = new JButton("Odustani");
		cancel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.WEST, cancel, 294, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, cancel, -68, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, cancel, 37, SpringLayout.SOUTH, zvanjeUnos);
		springLayout.putConstraint(SpringLayout.SOUTH, cancel, -10, SpringLayout.SOUTH, getContentPane());
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(cancel);
		
		JButton ok = new JButton("Potvrdi");
		ok.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, ok, 1, SpringLayout.NORTH, cancel);
		springLayout.putConstraint(SpringLayout.WEST, ok, 0, SpringLayout.WEST, ime);
		springLayout.putConstraint(SpringLayout.SOUTH, ok, 1, SpringLayout.SOUTH, cancel);
		springLayout.putConstraint(SpringLayout.EAST, ok, 157, SpringLayout.WEST, getContentPane());
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				 String imeVrednost = imeUnos.getText();
				 String prezimeVrednost = prezimeUnos.getText();
				 String datumVrednost = datumUnos.getText();
				 String adresaVrednost = adresaUnos.getText();
				 String telefonVrednost = telefonUnos.getText();
				 String emailVrednost = emailUnos.getText();
				 String kancelarijaVrednost = kancelarijaUnos.getText();
				 String licnaVrednost = licnaUnos.getText();
				 String titulaVrednost = titulaUnos.getText();
				 String zvanjeVrednost = zvanjeUnos.getText();
				// REFERENCE:
				// https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
				
		
				// REFERENCE:
				//https://www.javatpoint.com/java-regex
				
				if (!(Pattern.compile(imeSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(imeVrednost)
						.matches())) {
			// REFERENCE:
			// https://stackoverflow.com/questions/6270354/how-to-open-warning-information-error-dialog-in-swing/24164386
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
				
				if (!(Pattern.compile(kancelarijaSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(kancelarijaVrednost)
						.matches())) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "Pogrešno uneta kancelarija!");
					return;
				}
				
				if (!(Pattern.compile(licnaSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(licnaVrednost)
						.matches())) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "Pogrešno unet broj lične karte!");
					return;
				}
				//REFERENCE:
				//https://howtodoinjava.com/java/collections/arraylist/iterate-through-objects/
				for (Profesor p : ProfesorController.getInstance().getProfesori()) {
					if (p.getBrLicneKarte().equals(licnaVrednost)) {
						JOptionPane.showMessageDialog(MainFrame.getInstance(), "Već postoji profesor "
								+ "sa ovim brojem lične karte!");
						return;
					}
				}
				
				if (!(Pattern.compile(titulaSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(titulaVrednost)
						.matches())) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "Pogrešno uneta titula!");
					return;
				}
				
				if (!(Pattern.compile(zvanjeSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(zvanjeVrednost)
						.matches())) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "Pogrešno uneto zvanje!");
					return;
				}
				
				
				
				Profesor prof = new Profesor(prezimeVrednost, imeVrednost, datumVrednost, adresaVrednost,
						telefonVrednost, emailVrednost, kancelarijaVrednost, licnaVrednost,
						titulaVrednost, zvanjeVrednost);
				ProfesorController.getInstance().dodajProfesora(prof);
				
				dispose();
				
			}
		});
		getContentPane().add(ok);
		
	}
}
