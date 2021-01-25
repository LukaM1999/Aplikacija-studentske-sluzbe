package view.dialogs;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.OcenaController;
import controller.PredmetController;
import model.entiteti.Ocena;
import model.entiteti.Predmet;
import model.entiteti.Student;
import view.abstractTableModels.AbstractTableModelPolozeniIspiti;
import view.tables.TableNepolozeniIspiti;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class PolaganjeDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3632848417475164935L;

	private JButton potvrdi;
	private Boolean korektno;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PolaganjeDialog(Frame parent, String title, boolean modal, TableNepolozeniIspiti tabela, String regex,
			Student s, AbstractTableModelPolozeniIspiti model) {
		super(parent, title, modal);

		setSize(360, 370);
		setResizable(false);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel polaganjePanel = new JPanel();
		polaganjePanel.setSize(400, 400);

		JLabel sifra = new JLabel("Šifra*");
		sifra.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		sifra.setBounds(35, 30, 100, 30);
		polaganjePanel.add(sifra);

		JLabel naziv = new JLabel("Naziv*");
		naziv.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		naziv.setBounds(35, 80, 100, 30);
		polaganjePanel.add(naziv);

		JLabel ocena = new JLabel("Ocena*");
		ocena.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		ocena.setBounds(35, 130, 100, 30);
		polaganjePanel.add(ocena);

		JLabel datum = new JLabel("Datum*");
		datum.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		datum.setBounds(35, 180, 100, 30);
		polaganjePanel.add(datum);

		JTextField sifraUnos = new JTextField();
		sifraUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		sifraUnos.setEditable(false);
		sifraUnos.setBounds(170, 30, 135, 25);
		sifraUnos.setText((String) tabela.getValueAt(tabela.getSelectedRow(), 0));
		polaganjePanel.add(sifraUnos);

		JTextField nazivUnos = new JTextField();
		nazivUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		nazivUnos.setEditable(false);
		nazivUnos.setText((String) tabela.getValueAt(tabela.getSelectedRow(), 1));
		nazivUnos.setBounds(170, 80, 135, 25);
		polaganjePanel.add(nazivUnos);

		JTextField datumUnos = new JTextField();
		datumUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		datumUnos.setBounds(170, 180, 135, 25);
		polaganjePanel.add(datumUnos);
		datumUnos.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// potvrdi.setEnabled(korektno);
				changedUpdate(e);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// potvrdi.setEnabled(korektno);
				changedUpdate(e);

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if (!(Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS).matcher(datumUnos.getText()).matches())) {
					korektno = false;
					potvrdi.setEnabled(korektno);
				} else {
					korektno = true;
					potvrdi.setEnabled(korektno);
				}
			}
		});

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "6", "7", "8", "9", "10" }));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(170, 130, 135, 25);
		comboBox.setBackground(Color.white);
		polaganjePanel.add(comboBox);

		potvrdi = new JButton("Potvrdi");
		potvrdi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		potvrdi.setBounds(50, 250, 100, 35);
		potvrdi.setEnabled(false);
		polaganjePanel.add(potvrdi);
		potvrdi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String sifraVrednost = sifraUnos.getText();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
				LocalDate datumVrednost = LocalDate.parse(datumUnos.getText(), formatter);

				int ocenaVrednost = 6;
				if (comboBox.getSelectedIndex() == 1) {
					ocenaVrednost = 7;
				}
				if (comboBox.getSelectedIndex() == 2) {
					ocenaVrednost = 8;
				}
				if (comboBox.getSelectedIndex() == 3) {
					ocenaVrednost = 9;
				}
				if (comboBox.getSelectedIndex() == 4) {
					ocenaVrednost = 10;
				}

				for (Predmet p : PredmetController.getInstance().getPredmeti()) {
					if (p.getSifra().equals(sifraVrednost)) {
						Ocena o = new Ocena(s, p, ocenaVrednost, datumVrednost);
						OcenaController.getInstance().dodajOcenu(o);
						s.dodajPolozen(o);
						s.izbrisiNepolozen(tabela.getSelectedRow());
						model.fireTableDataChanged();
						validate();
					}
				}

				dispose();
			}
		});

		JButton odustani = new JButton("Odustani");
		odustani.setFont(new Font("Tahoma", Font.PLAIN, 14));
		odustani.setBounds(195, 250, 100, 35);
		polaganjePanel.add(odustani);
		odustani.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		polaganjePanel.setLayout(null);
		getContentPane().add(polaganjePanel);
	}
}
