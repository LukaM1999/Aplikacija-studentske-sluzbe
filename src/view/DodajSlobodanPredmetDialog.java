package view;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.JTable;

import controller.SlobodniPredmetiController;
import model.Predmet;
import model.Student;

public class DodajSlobodanPredmetDialog extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9037223482256955123L;
	
	private JTable table;
	
	private AbstractTableModelSlobodniPredmeti model;

	public DodajSlobodanPredmetDialog(Frame parent, String title, boolean modal, Student s) {
		super(parent, title, modal);

		setSize(400, 400);
		setResizable(false);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	
		
		JPanel slobodanPanel = new JPanel();
		slobodanPanel.setSize(400, 400);
		JButton dodajSlobodan = new JButton();
		dodajSlobodan.setText("Dodaj");
		dodajSlobodan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dodajSlobodan.setBounds(65, 320, 100, 30);
		dodajSlobodan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(table.getSelectedRow() >= 0) {
					
				
					String info = (String) table.getValueAt(table.getSelectedRow(), 0);
					String[] sifra = info.split("-");
					System.out.println(sifra[0]);
				
					for(Predmet p: s.getSlobodne()) {
						if(p.getSifra().equals(sifra[0])) {
							SlobodniPredmetiController.getInstance().izbrisiSlobodan(sifra[0]);
							s.dodajNepolozen(p);
							s.izbrisiSlobodan(table.getSelectedRow());
							break;
						}
					}
					dispose();
				}
				
			}	
			
		});
		
		
		slobodanPanel.setLayout(null);
		slobodanPanel.add(dodajSlobodan);
		getContentPane().add(slobodanPanel);
		
		JButton odustani = new JButton("Odustani");
		odustani.setFont(new Font("Tahoma", Font.PLAIN, 14));
		odustani.setBounds(220, 320, 100, 30);
		odustani.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		slobodanPanel.add(odustani);
		
		model = (AbstractTableModelSlobodniPredmeti) TableSlobodniPredmeti.getInstance()
				.getModel();
		model.fireTableDataChanged();
		validate();
		
		table = TableSlobodniPredmeti.getInstance();
		table.setBounds(10, 10, 365, 290);
		table.setTableHeader(null);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(10, 10, 365, 290);
		slobodanPanel.add(scroll);
		
		setVisible(true);
		
	}
}
