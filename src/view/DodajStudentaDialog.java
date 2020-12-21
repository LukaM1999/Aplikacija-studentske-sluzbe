package view;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class DodajStudentaDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5241927591473531655L;
	
	public DodajStudentaDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);

		setSize(500, 600);
		setResizable(false);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
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
		
		//textFields
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
		
		JTextField indeksUnos = new JTextField();
		indeksUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, indeksUnos, 11, SpringLayout.NORTH, indeks);
		springLayout.putConstraint(SpringLayout.WEST, indeksUnos, 6, SpringLayout.EAST, indeks);
		springLayout.putConstraint(SpringLayout.EAST, indeksUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(indeksUnos);
		
		JTextField upisUnos = new JTextField();
		upisUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, upisUnos, 11, SpringLayout.NORTH, upis);
		springLayout.putConstraint(SpringLayout.WEST, upisUnos, 6, SpringLayout.EAST, upis);
		springLayout.putConstraint(SpringLayout.EAST, upisUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(upisUnos);
		
		JTextField godinaUnos = new JTextField();
		godinaUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, godinaUnos, 11, SpringLayout.NORTH, godinaStudija);
		springLayout.putConstraint(SpringLayout.WEST, godinaUnos, 6, SpringLayout.EAST, godinaStudija);
		springLayout.putConstraint(SpringLayout.EAST, godinaUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(godinaUnos);
		
		JTextField finansiranjeUnos = new JTextField();
		finansiranjeUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, finansiranjeUnos, 11, SpringLayout.NORTH, finansiranje);
		springLayout.putConstraint(SpringLayout.WEST, finansiranjeUnos, 6, SpringLayout.EAST, finansiranje);
		springLayout.putConstraint(SpringLayout.EAST, finansiranjeUnos, 0, SpringLayout.EAST, imeUnos);
		getContentPane().add(finansiranjeUnos);
		
		
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
