package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.Predmet;

public class DodajProfesoraNaPredmetDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8975468915958311560L;
	
	private TableSlobodniProfesori table;
	
	public static int row;
	
public DodajProfesoraNaPredmetDialog(Container container, String title, boolean b, Predmet p, JTextField profesorUnos) {
				
		
		setSize(400, 400);
		setResizable(false);
		setLocationRelativeTo(container);
		setModal(b);
		setTitle(title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel profesorPanel = new JPanel();
		profesorPanel.setSize(400, 400);
		JButton dodajProfesora = new JButton();
		dodajProfesora.setText("Dodaj");
		dodajProfesora.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dodajProfesora.setBounds(65, 320, 100, 30);
		dodajProfesora.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (table.getSelectedRow() >= 0) {

					String profesor = (String) table.getValueAt(table.getSelectedRow(), 0);
					row = table.getSelectedRow();
					profesorUnos.setText(profesor);
					dispose();
				}

			}

		});

		profesorPanel.setLayout(null);
		profesorPanel.add(dodajProfesora);
		getContentPane().add(profesorPanel);

		JButton odustani = new JButton("Odustani");
		odustani.setFont(new Font("Tahoma", Font.PLAIN, 14));
		odustani.setBounds(220, 320, 100, 30);
		odustani.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});
		profesorPanel.add(odustani);


		table = TableSlobodniProfesori.getInstance();
		table.setBounds(10, 10, 365, 290);
		table.setTableHeader(null);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(10, 10, 365, 290);
		profesorPanel.add(scroll);

		setVisible(true);

	}

}
