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

import controller.PredmetController;
import model.Predmet;
import model.Predmet.Semestar;

public class IzmeniPredmetDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4378274837268901426L;

	private String sifraSablon = "\\p{IsUppercase}+[\\p{IsUppercase}0-9]+";

	private String nazivSablon = "\\p{IsUppercase}(\\p{IsAlphabetic}+)[\\p{IsWhite_Space}\\p{IsAlphabetic}\\p{IsDigit}]*";

	private String ESPBSablon = "[1-9]+0?";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public IzmeniPredmetDialog(Frame parent, String title, boolean modal) {
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

		JTextField nazivUnos = new JTextField();
		nazivUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, nazivUnos, 11, SpringLayout.NORTH, naziv);
		springLayout.putConstraint(SpringLayout.WEST, nazivUnos, 6, SpringLayout.EAST, naziv);
		springLayout.putConstraint(SpringLayout.EAST, nazivUnos, 0, SpringLayout.EAST, sifraUnos);
		getContentPane().add(nazivUnos);

		JTextField ESPBUnos = new JTextField();
		ESPBUnos.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, ESPBUnos, 11, SpringLayout.NORTH, ESPB);
		springLayout.putConstraint(SpringLayout.WEST, ESPBUnos, 6, SpringLayout.EAST, ESPB);
		springLayout.putConstraint(SpringLayout.EAST, ESPBUnos, 0, SpringLayout.EAST, sifraUnos);
		getContentPane().add(ESPBUnos);

		
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
		
		
		TablePredmet table = TablePredmet.getInstance();
		Predmet predmet = PredmetController.getInstance().getPredmet(table.getSelectedRow());
		
		sifraUnos.setText(predmet.getSifra());
		nazivUnos.setText(predmet.getNaziv());
		ESPBUnos.setText(String.valueOf(predmet.getESPB()));
		sifraUnos.setText(predmet.getSifra());
		godinaCombo.setSelectedIndex(predmet.getGodinaStudija()-1);		
		if(predmet.getSemestar() == Semestar.Zimski) {
			semestarCombo.setSelectedIndex(0);
		}
		else {
			semestarCombo.setSelectedIndex(1);
		}
		

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

		JButton ok = new JButton("Potvrdi");
		ok.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, ok, 0, SpringLayout.NORTH, cancel);
		springLayout.putConstraint(SpringLayout.WEST, ok, 0, SpringLayout.WEST, sifra);
		springLayout.putConstraint(SpringLayout.SOUTH, ok, 0, SpringLayout.SOUTH, cancel);
		springLayout.putConstraint(SpringLayout.EAST, ok, 157, SpringLayout.WEST, getContentPane());
		ok.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				
				 String sifraVrednost = sifraUnos.getText();
				 String nazivVrednost = nazivUnos.getText();
				 String ESPBVrednost = ESPBUnos.getText();
				
				if (!(Pattern.compile(sifraSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(sifraVrednost)
						.matches())) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "Pogrešno uneta šifra!");
					return;
				}
				

				if (!(Pattern.compile(nazivSablon, Pattern.UNICODE_CHARACTER_CLASS).matcher(nazivVrednost)
						.matches())) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "Pogrešno unet naziv!");
					return;
				}

				
				if (!Pattern.matches(ESPBSablon, ESPBVrednost)) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "Pogrešno unet broj ESPB!");
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
				
				
				predmet.setSifra(sifraVrednost);
				predmet.setNaziv(nazivVrednost);
				predmet.setESPB(Integer.parseInt(ESPBVrednost));
				predmet.setGodinaStudija(godinaStudija);
				predmet.setSemestar(semestarVrednost);
				
				
				//REFERENCE: https://stackoverflow.com/questions/8689122/joptionpane-yes-no-options-confirm-dialog-box-issue
				JOptionPane confirm = new JOptionPane();
				int answer = confirm.showConfirmDialog(null,
						"Da li ste sigurni da želite da izmenite informacije ovog predmeta?", "Potvrda izmene",
						JOptionPane.OK_CANCEL_OPTION);
				if(answer == JOptionPane.YES_OPTION) {
				PredmetController.getInstance().izmeniPredmet(table.getSelectedRow());
				dispose();
				}
				
			}
		});
		getContentPane().add(ok);

	
	}

}
