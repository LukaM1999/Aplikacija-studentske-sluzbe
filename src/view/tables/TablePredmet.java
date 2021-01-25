package view.tables;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.InputMap;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import controller.PredmetController;
import model.baze.BazaPredmeta;
import model.entiteti.Predmet;
import view.abstractTableModels.AbstractTableModelPredmet;

/**
 * Klasa u kojoj se pravi instanca funkcionalne tabele predmeta,
 *  čiji redovi i kolone se mogu jednostruko selektovati. Takođe je
 *  moguće sortirati vrednosti tabele u opadajućem i rastućem poretku
 *  (leksikografski i brojčano). 
 * 
 * @author Mihajlo Kisić
 *
 */
public class TablePredmet extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6347149562179666223L;

	/**
	 * Instanca tabele predmeta.
	 */
	private static TablePredmet instance = null;

	/**
	 * Dobavlja instancu tabele predmeta po Singleton konvenciji.
	 * 
	 * @return povratna vrednost instanca tabele predmeta.
	 */
	public static TablePredmet getInstance() {
		if (instance == null) {
			instance = new TablePredmet();
		}
		return instance;
	}
	
	/**
	 * Sorter tabele predmeta.
	 */
	private TableRowSorter<AbstractTableModelPredmet> sorter;
	
	/**
	 * Lista filtera (kriterijuma) za sortiranje tabele predmeta.
	 */
	private ArrayList<RowFilter<Object, Object>> filters;
	
	/**
	 * Broji koliko puta je selektovana prva kolona tabele.
	 */
	private int prva = 0;
	
	/**
	 * Broji koliko puta je selektovana druga kolona tabele.
	 */
	private int druga = 0;
	
	/**
	 * Broji koliko puta je selektovana treća kolona tabele.
	 */
	private int treca = 0;
	
	/**
	 * Broji koliko puta je selektovana četvrta kolona tabele.
	 */
	private int cetvrta = 0;
	
	/**
	 * Broji koliko puta je selektovana peta kolona tabele.
	 */
	private int peta = 0;
	
	@SuppressWarnings("deprecation")
	/**
	 * Kreira tabelu predmeta po istoimenom apstraktnom modelu tabele.
	 * Implementira slušače događaja koji broje koliko puta je kliknuto
	 * zaglavlje svake kolone i u zavisnosti od toga sortira tabelu predmeta
	 * u rastućem ili opadajućem poretku po vrednostima iz kolone čije zaglavlje
	 * je izabrano. Onemogućene su ugrađene prečice tabele zbog konflikta sa
	 * već postojećim prečicama same aplikacije.
	 */
	public TablePredmet() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		AbstractTableModelPredmet model = new AbstractTableModelPredmet();
		this.setModel(model);
		
		//REFERENCE: https://community.oracle.com/tech/developers/discussion/1368212/jtable-inhibit-ctrl-a-select-all
		InputMap im = this.getInputMap(javax.swing.JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK), "none");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK), "none");
		
		sorter = new TableRowSorter<AbstractTableModelPredmet>(model);

		// REFERNCE:
		// https://docs.oracle.com/javase/7/docs/api/javax/swing/RowFilter.html
		filters = new ArrayList<RowFilter<Object, Object>>();
		setRowSorter(sorter);
		sorter.setSortable(0, false);
		sorter.setSortable(1, false);
		sorter.setSortable(2, false);
		sorter.setSortable(3, false);
		sorter.setSortable(4, false);
		
		this.getTableHeader().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int col = TablePredmet.getInstance().columnAtPoint(e.getPoint());

				switch (col) {
				case 0:
					prva++;
					druga = 0;
					treca = 0;
					cetvrta = 0;
					peta = 0;

					if ((prva % 2) == 0) {

						// REFERENCE: https://www.codebyamir.com/blog/sort-list-of-objects-by-field-java
						Collections.sort(BazaPredmeta.getInstance().getPredmeti(), new Comparator<Predmet>() {

							@Override
							public int compare(Predmet arg0, Predmet arg1) {
								return arg1.getSifra().compareTo(arg0.getSifra());
							}
						});
						
					//REFERENCE: https://stackoverflow.com/questions/1496143/dynamically-changing-the-column-header-text-in-jtable
						getColumnModel().getColumn(0).setHeaderValue("Šifra " + "\u25BC");
						getColumnModel().getColumn(1).setHeaderValue("Naziv");
						getColumnModel().getColumn(2).setHeaderValue("ESPB");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Semestar");
						
					} else {
						Collections.sort(BazaPredmeta.getInstance().getPredmeti(), new Comparator<Predmet>() {

							@Override
							public int compare(Predmet arg0, Predmet arg1) {
								return arg0.getSifra().compareTo(arg1.getSifra());
							}
						});

						getColumnModel().getColumn(0).setHeaderValue("Šifra " + "\u25B2");
						getColumnModel().getColumn(1).setHeaderValue("Naziv");
						getColumnModel().getColumn(2).setHeaderValue("ESPB");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Semestar");
						
					}
					break;

				case 1:
					prva = 0;
					druga++;
					treca = 0;
					cetvrta = 0;
					peta = 0;

					if ((druga % 2) == 0) {
						Collections.sort(BazaPredmeta.getInstance().getPredmeti(), new Comparator<Predmet>() {

							@Override
							public int compare(Predmet arg0, Predmet arg1) {
								return arg1.getNaziv().compareTo(arg0.getNaziv());
							}
						});

						getColumnModel().getColumn(0).setHeaderValue("Šifra");
						getColumnModel().getColumn(1).setHeaderValue("Naziv " + "\u25BC");
						getColumnModel().getColumn(2).setHeaderValue("ESPB");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Semestar");
						
						
					} else {
						Collections.sort(BazaPredmeta.getInstance().getPredmeti(), new Comparator<Predmet>() {

							@Override
							public int compare(Predmet arg0, Predmet arg1) {
								return arg0.getNaziv().compareTo(arg1.getNaziv());
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Šifra");
						getColumnModel().getColumn(1).setHeaderValue("Naziv " + "\u25B2");
						getColumnModel().getColumn(2).setHeaderValue("ESPB");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Semestar");
					}
					break;

				case 2:
					prva = 0;
					druga = 0;
					treca++;
					cetvrta = 0;
					peta = 0;

					if ((treca % 2) == 0) {
						Collections.sort(BazaPredmeta.getInstance().getPredmeti(), new Comparator<Predmet>() {

							@Override
							public int compare(Predmet arg0, Predmet arg1) {
								return Integer.compare(arg0.getESPB(), arg1.getESPB());
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Šifra");
						getColumnModel().getColumn(1).setHeaderValue("Naziv");
						getColumnModel().getColumn(2).setHeaderValue("ESPB " + "\u25BC");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Semestar");
						
					} else {
						Collections.sort(BazaPredmeta.getInstance().getPredmeti(), new Comparator<Predmet>() {

							@Override
							public int compare(Predmet arg0, Predmet arg1) {
								return Integer.compare(arg1.getESPB(), arg0.getESPB());
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Šifra");
						getColumnModel().getColumn(1).setHeaderValue("Naziv");
						getColumnModel().getColumn(2).setHeaderValue("ESPB " + "\u25B2");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Semestar");
						
					}
					break;

				case 3:
					prva = 0;
					druga = 0;
					treca = 0;
					cetvrta++;
					peta = 0;

					if ((cetvrta % 2) == 0) {
						Collections.sort(BazaPredmeta.getInstance().getPredmeti(), new Comparator<Predmet>() {

							@Override
							public int compare(Predmet arg0, Predmet arg1) {
								return String.valueOf(arg0.getGodinaStudija()).compareTo(String.valueOf(arg1.getGodinaStudija()));
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Šifra");
						getColumnModel().getColumn(1).setHeaderValue("Naziv");
						getColumnModel().getColumn(2).setHeaderValue("ESPB");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija " + "\u25BC");
						getColumnModel().getColumn(4).setHeaderValue("Semestar");
						
					} else {
						Collections.sort(BazaPredmeta.getInstance().getPredmeti(), new Comparator<Predmet>() {

							@Override
							public int compare(Predmet arg0, Predmet arg1) {
								return String.valueOf(arg1.getGodinaStudija()).compareTo(String.valueOf(arg0.getGodinaStudija()));
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Šifra");
						getColumnModel().getColumn(1).setHeaderValue("Naziv");
						getColumnModel().getColumn(2).setHeaderValue("ESPB");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija " + "\u25B2");
						getColumnModel().getColumn(4).setHeaderValue("Semestar");
						
					}
					break;

				case 4:
					prva = 0;
					druga = 0;
					treca = 0;
					cetvrta = 0;
					peta++;

					if ((peta % 2) == 0) {
						Collections.sort(BazaPredmeta.getInstance().getPredmeti(), new Comparator<Predmet>() {

							@Override
							public int compare(Predmet arg0, Predmet arg1)  {
								return String.valueOf(arg1.getSemestar())
										.compareTo(String.valueOf(arg0.getSemestar()));
							}
						});
						
						
						getColumnModel().getColumn(0).setHeaderValue("Šifra");
						getColumnModel().getColumn(1).setHeaderValue("Naziv");
						getColumnModel().getColumn(2).setHeaderValue("ESPB");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Semestar " + "\u25BC");
							
					} else {
						Collections.sort(BazaPredmeta.getInstance().getPredmeti(), new Comparator<Predmet>() {

							@Override
							public int compare(Predmet arg0, Predmet arg1)  {
								return String.valueOf(arg0.getSemestar())
										.compareTo(String.valueOf(arg1.getSemestar()));
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Šifra");
						getColumnModel().getColumn(1).setHeaderValue("Naziv");
						getColumnModel().getColumn(2).setHeaderValue("ESPB");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Semestar " + "\u25B2");
						
					}
					break;

				default:
					break;

				}

			}
		});  
		
		
		
		
		
		for (Predmet p : PredmetController.getInstance().getPredmeti()) {
			  PredmetController.getInstance().popuniListuPolozili(p); 
		  } 
		 		  
	}

	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component c = super.prepareRenderer(renderer, row, column);
		if (isRowSelected(row)) {
			c.setBackground(Color.LIGHT_GRAY);
		} else {
			c.setBackground(Color.WHITE);
		}
		return c;
	}
	
	/**
	 * Dobavlja sorter tabele predmeta.
	 * 
	 * @return sorter - sorter tabele predmeta.
	 */
	public TableRowSorter<AbstractTableModelPredmet> getSorter() {
		return sorter;
	}
	
	/**
	 * Dobavlja listu filtera primenjenih na tabelu predmeta.
	 * 
	 * @return filters - lista filtera.
	 */
	public ArrayList<RowFilter<Object, Object>> getFilters() {
		return filters;
	}
}
