package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import controller.PredmetController;
import model.Predmet;
import model.Predmet.Semestar;

public class DodajPredmetDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1340009763757734392L;
	
	// REFERENCE:
		// https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
		private String sifraSablon = "\\p{IsUppercase}+[\\p{IsUppercase}0-9]+";

		private String nazivSablon = "\\p{IsUppercase}(\\p{IsAlphabetic}+)[\\p{IsWhite_Space}\\p{IsAlphabetic}\\p{IsDigit}]*";

		private String ESPBSablon = "[1-9]{1,2}0?";
		
		private boolean sifraKorektno;
		private boolean nazivKorektno;
		private boolean ESPBKorektno;
		
		private boolean ispravno;
		
		private JButton ok;

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public DodajPredmetDialog(Frame parent, String title, boolean modal) {
			super(parent, title, modal);

			setSize(500, 380);
			setResizable(false);
			setLocationRelativeTo(parent);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			//REFERENCE: https://docs.oracle.com/javase/tutorial/uiswing/layout/spring.html
			SpringLayout springLayout = new SpringLayout();
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
	                if (!(Pattern.compile(sifraSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(sifraUnos.getText()).matches())) {
	                    sifraKorektno = false;
	                    sifraUnos.setBackground(Color.WHITE);
	                    ispravno = false;
	                } 
	                else { 
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
	                if (!(Pattern.compile(nazivSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(nazivUnos.getText()).matches())) {
	                    nazivKorektno = false;
	                    nazivUnos.setBackground(Color.WHITE);
	                    ispravno = false;
	                } 
	                else { 
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
	                if (!(Pattern.compile(ESPBSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(ESPBUnos.getText()).matches())) {
	                    ESPBKorektno = false;
	                    ESPBUnos.setBackground(Color.WHITE);
	                    ispravno = false;
	                } 
	                else { 
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
			String[] godinaStud = new String[] { "1" , "2", "3", "4" };
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
			

			// Buttons
			JButton cancel = new JButton("Odustani");
			cancel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			springLayout.putConstraint(SpringLayout.WEST, cancel, 294, SpringLayout.WEST, getContentPane());
			springLayout.putConstraint(SpringLayout.EAST, cancel, -68, SpringLayout.EAST, getContentPane());
			springLayout.putConstraint(SpringLayout.NORTH, cancel, 37, SpringLayout.SOUTH, semestar);
			springLayout.putConstraint(SpringLayout.SOUTH, cancel, -10, SpringLayout.SOUTH, getContentPane());
			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			getContentPane().add(cancel);

			ok = new JButton("Potvrdi");
			ok.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			ok.setEnabled(false);
			springLayout.putConstraint(SpringLayout.NORTH, ok, 0, SpringLayout.NORTH, cancel);
			springLayout.putConstraint(SpringLayout.WEST, ok, 0, SpringLayout.WEST, sifra);
			springLayout.putConstraint(SpringLayout.SOUTH, ok, 0, SpringLayout.SOUTH, cancel);
			springLayout.putConstraint(SpringLayout.EAST, ok, 157, SpringLayout.WEST, getContentPane());
			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					

					 String sifraVrednost = sifraUnos.getText();
					 String nazivVrednost = nazivUnos.getText();
					 String ESPBVrednost = ESPBUnos.getText();
					// REFERENCE:
					// https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
					
			
					// REFERENCE:
					//https://www.javatpoint.com/java-regex
					
					if (!(Pattern.compile(sifraSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(sifraVrednost)
							.matches())) {
				// REFERENCE:
				// https://stackoverflow.com/questions/6270354/how-to-open-warning-information-error-dialog-in-swing/24164386
						JOptionPane.showMessageDialog(getContentPane(), "Pogrešno uneta šifra!");
						return;
					}
					

					if (!(Pattern.compile(nazivSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(nazivVrednost)
							.matches())) {
						JOptionPane.showMessageDialog(getContentPane(), "Pogrešno unet naziv!");
						return;
					}

					
					if (!Pattern.matches(ESPBSablon, ESPBVrednost)) {
						JOptionPane.showMessageDialog(getContentPane(), "Pogrešno unet broj ESPB!");
						return;
					}
					
					int godinaStudija = 1;
					if(godinaCombo.getSelectedItem() == "2") {
						godinaStudija = 2;
					}
					else if(godinaCombo.getSelectedItem() == "3") {
						godinaStudija = 3;
					}
					else if(godinaCombo.getSelectedItem() == "4") {
						godinaStudija = 4;
					}
					
					Semestar semestarVrednost = Semestar.Zimski;
					if(semestarCombo.getSelectedItem().toString() == "Letnji") {
						semestarVrednost = Semestar.Letnji;
					}
					//REFERENCE:
					//https://howtodoinjava.com/java/collections/arraylist/iterate-through-objects/
					for (Predmet p : PredmetController.getInstance().getPredmeti()) {
						if (p.getSifra().equals(sifraVrednost)) {
							JOptionPane.showMessageDialog(getContentPane(), "Već postoji predmet "
									+ "sa ovom šifrom!");
							return;
						}
					}
					
					Predmet predmet = new Predmet(sifraVrednost, nazivVrednost, Integer.parseInt(ESPBVrednost), godinaStudija,
							semestarVrednost);
					PredmetController.getInstance().dodajPredmet(predmet);
					
					dispose();
					
				}
			});
			getContentPane().add(ok);

		
		}
		
		public void enableButton(JButton button) {
			if (!ispravno) {
				button.setEnabled(false);
			} else {
				button.setEnabled(true);
			}
		}
}
		
