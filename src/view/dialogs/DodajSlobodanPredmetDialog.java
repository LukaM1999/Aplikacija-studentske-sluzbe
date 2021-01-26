package view.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Container;
import java.awt.Font;
import javax.swing.JTable;

import controller.SlobodniPredmetiController;
import model.entiteti.Predmet;
import model.entiteti.Student;
import view.abstractTableModels.AbstractTableModelSlobodniPredmeti;
import view.tables.TableSlobodniPredmeti;

/**
 * Klasa predstavlja dijalog dodavanja predmeta koje student može da sluša
 * u listu nepoloženih predmeta studenta. Sastoji se od tabele predmeta koje student
 * može da sluša i dugmadi za potvrdu dodavanja i odustanak. Za dugme potvrde je
 * vezan slušač događaja iz izabranog reda tabele dobavlja šifru predmeta i prolazi
 * kroz listu predmeta koje može da sluša student prosleđen u konstruktoru dijaloga.
 * Ako se dobavljena šifra predmeta poklapa sa predmetom iz liste predmeta koje student
 * može da sluša, on se preko kontrolera predmeta koje student može da sluša briše iz
 * istoimene baze. Takođe se dodaje prosleđenom studentu u listu nepoloženih ispita i
 * briše iz njegove liste predmeta koje može da sluša tako što se metodi za brisanje slobodnih
 * predmeta tog studenta prosledi indeks izabranog reda tabele predmeta koje student može da sluša.
 * Na kraju se ažurira prikaz predmeta koje student može da sluša.
 * 
 * @author Luka Miletić
 *
 */
public class DodajSlobodanPredmetDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9037223482256955123L;

	
	/**
	 * Instanca tabele predmeta koje student može da sluša.
	 */
	private JTable table;

	/**
	 * Apstraktni model tabele predmeta koje student može da sluša.
	 */
	private AbstractTableModelSlobodniPredmeti model;

	/**
	 * Kreira dijalog dodavanja predmeta koje student može da sluša u njegovu
	 * listu nepoloženih predmeta, centriran u odnosu na prozor iz kog se poziva
	 * konstruktor dijaloga.
	 * 
	 * @param container prozor iz kog se poziva konstruktor dijaloga
	 * @param title naslov dijaloga
	 * @param b modalnost dijaloga
	 * @param s objekat studenta kome se dodaje predmet u listu nepoloženih ispita
	 */
	public DodajSlobodanPredmetDialog(Container container, String title, boolean b, Student s) {
				
		
		setSize(400, 400);
		setResizable(false);
		setLocationRelativeTo(container);
		setModal(b);
		setTitle(title);
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

				if (table.getSelectedRow() >= 0) {

					String info = (String) table.getValueAt(table.getSelectedRow(), 0);
					String[] sifra = info.split("-");
					
					for (Predmet p : s.getSlobodne()) {
						if (p.getSifra().equals(sifra[0])) {
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

		model = (AbstractTableModelSlobodniPredmeti) TableSlobodniPredmeti.getInstance().getModel();
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
