package view.dialogs;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.PredmetiBezProfesoraController;
import model.entiteti.Predmet;
import model.entiteti.Profesor;
import view.abstractTableModels.AbstractTableModelPredmetiBezProfesora;
import view.tables.TablePredmetiBezProfesora;

import javax.swing.JLabel;

public class DodajPredmetProfesoruDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3417973446528522618L;

	private JTable table;

	private AbstractTableModelPredmetiBezProfesora model;
		
	public DodajPredmetProfesoruDialog(Container container, String title, boolean b, Profesor p) {
		
		setSize(400,400);
		setResizable(false);
		setLocationRelativeTo(container);
		setModal(b);
		setTitle(title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setSize(400,400);
		
		
		JButton dodaj = new JButton();
		dodaj.setText("Dodaj");
		dodaj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dodaj.setBounds(65, 320, 100, 30);
		dodaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					
					
					int[] rows = TablePredmetiBezProfesora.getInstance().getSelectedRows();
					for(int i = 0; i < rows.length; i++) {
						
						String info = (String) TablePredmetiBezProfesora.getInstance().getValueAt(rows[i],0);
						String[] sifra = info.split("-");
						
						//sifra[0] = (String) TablePredmetiBezProfesora.getInstance().getValueAt(rows[i], 0);
				
						for (Predmet predmet : p.getPredmetiBezProfesora()) {
							if (predmet.getSifra().equals(sifra[0])) {
								PredmetiBezProfesoraController.getInstance().izbrisiSlobodan(sifra[0]);
								p.dodajPredajePredmet(predmet);
								p.izbrisiSlobodan(rows[i]);
								predmet.setProfesor(p);
								break;
							}
						}
						
						for(int j = 0; j < rows.length; j++) {
							rows[j] -= 1;
						}
				
					}
					model.fireTableDataChanged();
					validate();
					
					
					dispose();
				}
			}
				
		});
		
		panel.setLayout(null);
		panel.add(dodaj);
		getContentPane().add(panel);
		
		JButton odustani = new JButton("Odustani");
		odustani.setFont(new Font("Tahoma", Font.PLAIN, 14));
		odustani.setBounds(220, 320, 100, 30);
		odustani.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});
		panel.add(odustani);
		
		model = (AbstractTableModelPredmetiBezProfesora) TablePredmetiBezProfesora.getInstance().getModel();
		model.fireTableDataChanged();
		validate();
		
		table = TablePredmetiBezProfesora.getInstance();
		table.setBounds(10, 10, 365, 290);
		table.setTableHeader(null);
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(10, 40, 365, 260);
		panel.add(scroll);
		
		JLabel predmeti = new JLabel("Predmeti:");
		predmeti.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		predmeti.setBounds(10, 15, 100, 15);
		panel.add(predmeti);
		
		setVisible(true);
	}
}
