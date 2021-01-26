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

import model.baze.BazaProfesora;
import model.entiteti.Profesor;
import view.abstractTableModels.AbstractTableModelProfesor;

/**
 * Klasa u kojoj se pravi instanca funkcionalne tabele profesora, 
 * čiji redovi i kolone se mogu jednostruko selektovati. Takođe je 
 * moguće sortirati vrednosti tabele u opadajućem i rastućem poretku 
 * (leksikografski i brojčano). 
 * 
 * @author Mihajlo Kisić
 *
 */
public class TableProfesor extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2944037584344128019L;
	
	/**
	 * Instanca tabele profesora.
	 */
	private static TableProfesor instance = null;
	
	/**
	 * Dobavlja instancu tabele profesora po Singleton šablonu.
	 * 
	 * @return instanca tabele profesora.
	 */
	public static TableProfesor getInstance() {
		if (instance == null) {
			instance = new TableProfesor();
		}
		return instance;
	}

	/**
	 * Sorter tabele profesora.
	 */
	private TableRowSorter<AbstractTableModelProfesor> sorter;
	
	/**
	 * Lista filtera (kriterijuma) za sortiranje tabele profesora.
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
	 * Kreira tabelu profesora po istoimenom apstraktnom modelu tabele.
	 * Implementira slušače događaja koji broje koliko puta je kliknuto
	 * zaglavlje svake kolone i u zavisnosti od toga sortira tabelu profesora
	 * u rastućem ili opadajućem poretku po vrednostima iz kolone čije zaglavlje
	 * je izabrano. Onemogućene su ugrađene prečice tabele zbog konflikta sa
	 * već postojećim prečicama same aplikacije.
	 */
	@SuppressWarnings("deprecation")
	public TableProfesor() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		AbstractTableModelProfesor model = new AbstractTableModelProfesor();
		this.setModel(model);
		
		//REFERENCE: https://community.oracle.com/tech/developers/discussion/1368212/jtable-inhibit-ctrl-a-select-all
		InputMap im = this.getInputMap(javax.swing.JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK), "none");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK), "none");
		
		sorter = new TableRowSorter<AbstractTableModelProfesor>(model);
		
		// REFERNCE:
		// https://docs.oracle.com/javase/7/docs/api/javax/swing/RowFilter.html
		filters = new ArrayList<RowFilter<Object, Object>>();
		setRowSorter(sorter);
		sorter.setSortable(0, false);
		sorter.setSortable(1, false);
		sorter.setSortable(2, false);
		sorter.setSortable(3, false);
		this.getTableHeader().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int col = TableProfesor.getInstance().columnAtPoint(e.getPoint());

				switch (col) {
				case 0:
					prva++;
					druga = 0;
					treca = 0;
					cetvrta = 0;

					if ((prva % 2) == 0) {

				// REFERENCE: https://www.codebyamir.com/blog/sort-list-of-objects-by-field-java
						Collections.sort(BazaProfesora.getInstance().getProfesori(), new Comparator<Profesor>() {

							@Override
							public int compare(Profesor arg0, Profesor arg1) {
								return arg1.getIme().compareTo(arg0.getIme());
							}
						});
						
				//REFERENCE: https://stackoverflow.com/questions/1496143/dynamically-changing-the-column-header-text-in-jtable
						getColumnModel().getColumn(0).setHeaderValue("Ime " + "\u25BC");
						getColumnModel().getColumn(1).setHeaderValue("Prezime");
						getColumnModel().getColumn(2).setHeaderValue("Titula");
						getColumnModel().getColumn(3).setHeaderValue("Zvanje");

						
					} else {
						Collections.sort(BazaProfesora.getInstance().getProfesori(), new Comparator<Profesor>() {

							@Override
							public int compare(Profesor arg0, Profesor arg1) {
								return arg0.getIme().compareTo(arg1.getIme());
							}
						});

						getColumnModel().getColumn(0).setHeaderValue("Ime " + "\u25B2");
						getColumnModel().getColumn(1).setHeaderValue("Prezime");
						getColumnModel().getColumn(2).setHeaderValue("Titula");
						getColumnModel().getColumn(3).setHeaderValue("Zvanje");
						
					}
					break;

				case 1:
					prva = 0;
					druga++;
					treca = 0;
					cetvrta = 0;

					if ((druga % 2) == 0) {
						Collections.sort(BazaProfesora.getInstance().getProfesori(), new Comparator<Profesor>() {

							@Override
							public int compare(Profesor arg0, Profesor arg1) {
								return arg1.getPrezime().compareTo(arg0.getPrezime());
							}
						});

						getColumnModel().getColumn(0).setHeaderValue("Ime");
						getColumnModel().getColumn(1).setHeaderValue("Prezime " + "\u25BC");
						getColumnModel().getColumn(2).setHeaderValue("Titula");
						getColumnModel().getColumn(3).setHeaderValue("Zvanje");
						
					} else {
						Collections.sort(BazaProfesora.getInstance().getProfesori(), new Comparator<Profesor>() {

							@Override
							public int compare(Profesor arg0, Profesor arg1) {
								return arg0.getPrezime().compareTo(arg1.getPrezime());
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Ime");
						getColumnModel().getColumn(1).setHeaderValue("Prezime " + "\u25B2");
						getColumnModel().getColumn(2).setHeaderValue("Titula");
						getColumnModel().getColumn(3).setHeaderValue("Zvanje");
					}
					break;

				case 2:
					prva = 0;
					druga = 0;
					treca++;
					cetvrta = 0;

					if ((treca % 2) == 0) {
						Collections.sort(BazaProfesora.getInstance().getProfesori(), new Comparator<Profesor>() {

							@Override
							public int compare(Profesor arg0, Profesor arg1) {
								return String.valueOf(arg1.getTitula()).compareToIgnoreCase(String.valueOf(arg0.getTitula()));
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Ime");
						getColumnModel().getColumn(1).setHeaderValue("Prezime");
						getColumnModel().getColumn(2).setHeaderValue("Titula " + "\u25BC");
						getColumnModel().getColumn(3).setHeaderValue("Zvanje");
						
					} else {
						Collections.sort(BazaProfesora.getInstance().getProfesori(), new Comparator<Profesor>() {

							@Override
							public int compare(Profesor arg0, Profesor arg1) {
								return String.valueOf(arg0.getTitula()).compareToIgnoreCase(String.valueOf(arg1.getTitula()));
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Ime");
						getColumnModel().getColumn(1).setHeaderValue("Prezime");
						getColumnModel().getColumn(2).setHeaderValue("Titula " + "\u25B2");
						getColumnModel().getColumn(3).setHeaderValue("Zvanje");
						
					}
					break;

				case 3:
					prva = 0;
					druga = 0;
					treca = 0;
					cetvrta++;

					if ((cetvrta % 2) == 0) {
						Collections.sort(BazaProfesora.getInstance().getProfesori(), new Comparator<Profesor>() {

							@Override
							public int compare(Profesor arg0, Profesor arg1) {
								return String.valueOf(arg1.getZvanje()).compareToIgnoreCase(String.valueOf(arg0.getZvanje()));
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Ime");
						getColumnModel().getColumn(1).setHeaderValue("Prezime");
						getColumnModel().getColumn(2).setHeaderValue("Titula");
						getColumnModel().getColumn(3).setHeaderValue("Zvanje " + "\u25BC");
						
					} else {
						Collections.sort(BazaProfesora.getInstance().getProfesori(), new Comparator<Profesor>() {

							@Override
							public int compare(Profesor arg0, Profesor arg1) {
								return String.valueOf(arg0.getZvanje()).compareToIgnoreCase(String.valueOf(arg1.getZvanje()));
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Ime");
						getColumnModel().getColumn(1).setHeaderValue("Prezime");
						getColumnModel().getColumn(2).setHeaderValue("Titula");
						getColumnModel().getColumn(3).setHeaderValue("Zvanje " + "\u25B2");
						
					}
					break;
				
				default:
					break;
				}

			}
		});
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
	 * Dobavlja sorter tabele profesora.
	 * 
	 * @return sorter tabele profesora.
	 */
	public TableRowSorter<AbstractTableModelProfesor> getSorter() {
		return sorter;
	}
	
	/**
	 * Dobavlja listu filtera primenjenih na tabelu profesora.
	 * 
	 * @return lista filtera.
	 */
	public ArrayList<RowFilter<Object, Object>> getFilters() {
		return filters;
	}
	
}
