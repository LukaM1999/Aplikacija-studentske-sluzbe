package view;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class DodajProfesoraDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6293346707131160182L;
	private JTextField imeUnos;
	private JTextField datumUnos;
	private JTextField adresaUnos;
	private JTextField telefonUnos;
	private JTextField emailUnos;
	private JTextField kancelarijaUnos;
	private JTextField licnaUnos;
	private JTextField titulaUnos;
	private JTextField zvanjeUnos;
	private JTextField prezimeUnos;
	
	public DodajProfesoraDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 14));

		setSize(500, 600);
		setLocationRelativeTo(parent);
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
		
		//textFields
		imeUnos = new JTextField();
		imeUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, imeUnos, 11, SpringLayout.NORTH, ime);
		springLayout.putConstraint(SpringLayout.WEST, imeUnos, 6, SpringLayout.EAST, ime);
		springLayout.putConstraint(SpringLayout.EAST, imeUnos, -68, SpringLayout.EAST, getContentPane());
		getContentPane().add(imeUnos);
		
		prezimeUnos = new JTextField();
		prezimeUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, prezimeUnos, 11, SpringLayout.NORTH, prezime);
		springLayout.putConstraint(SpringLayout.WEST, prezimeUnos, 6, SpringLayout.EAST, prezime);
		springLayout.putConstraint(SpringLayout.EAST, prezimeUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(prezimeUnos);
		
		datumUnos = new JTextField();
		datumUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, datumUnos, 11, SpringLayout.NORTH, datum);
		springLayout.putConstraint(SpringLayout.WEST, datumUnos, 6, SpringLayout.EAST, datum);
		springLayout.putConstraint(SpringLayout.EAST, datumUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(datumUnos);
		
		adresaUnos = new JTextField();
		adresaUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, adresaUnos, 11, SpringLayout.NORTH, adresa);
		springLayout.putConstraint(SpringLayout.WEST, adresaUnos, 6, SpringLayout.EAST, adresa);
		springLayout.putConstraint(SpringLayout.EAST, adresaUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(adresaUnos);
		
		telefonUnos = new JTextField();
		telefonUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, telefonUnos, 11, SpringLayout.NORTH, telefon);
		springLayout.putConstraint(SpringLayout.WEST, telefonUnos, 6, SpringLayout.EAST, telefon);
		springLayout.putConstraint(SpringLayout.EAST, telefonUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(telefonUnos);
		
		emailUnos = new JTextField();
		emailUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, emailUnos, 11, SpringLayout.NORTH, email);
		springLayout.putConstraint(SpringLayout.WEST, emailUnos, 6, SpringLayout.EAST, email);
		springLayout.putConstraint(SpringLayout.EAST, emailUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(emailUnos);
		
		kancelarijaUnos = new JTextField();
		kancelarijaUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, kancelarijaUnos, 11, SpringLayout.NORTH, kancelarija);
		springLayout.putConstraint(SpringLayout.WEST, kancelarijaUnos, 6, SpringLayout.EAST, kancelarija);
		springLayout.putConstraint(SpringLayout.EAST, kancelarijaUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(kancelarijaUnos);
		
		licnaUnos = new JTextField();
		licnaUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, licnaUnos, 11, SpringLayout.NORTH, brLicne);
		springLayout.putConstraint(SpringLayout.WEST, licnaUnos, 6, SpringLayout.EAST, brLicne);
		springLayout.putConstraint(SpringLayout.EAST, licnaUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(licnaUnos);
		
		titulaUnos = new JTextField();
		titulaUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, titulaUnos, 11, SpringLayout.NORTH, titula);
		springLayout.putConstraint(SpringLayout.WEST, titulaUnos, 6, SpringLayout.EAST, titula);
		springLayout.putConstraint(SpringLayout.EAST, titulaUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(titulaUnos);
		
		zvanjeUnos = new JTextField();
		zvanjeUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, zvanjeUnos, 11, SpringLayout.NORTH, zvanje);
		springLayout.putConstraint(SpringLayout.WEST, zvanjeUnos, 6, SpringLayout.EAST, zvanje);
		springLayout.putConstraint(SpringLayout.EAST, zvanjeUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(zvanjeUnos);
		
		
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
			}
		});
		getContentPane().add(ok);
		
	}
}
