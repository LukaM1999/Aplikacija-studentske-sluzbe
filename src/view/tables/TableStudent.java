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

import model.baze.BazaStudenata;
import model.entiteti.Student;
import view.abstractTableModels.AbstractTableModelStudent;


/**
 * Klasa u kojoj se pravi instanca funkcionalne tabele studenata, 
 * čiji redovi i kolone se mogu jednostruko selektovati. Takođe je 
 * moguće sortirati vrednosti tabele u opadajućem i rastućem poretku 
 * (leksikografski i brojčano). 
 * 
 * @author Luka Miletić
 *
 */
public class TableStudent extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8306744364765947146L;

	
	/**
	 * Instanca tabele studenata.
	 */
	private static TableStudent instance = null;

	/**
	 * Dobavlja instancu tabele studenata po Singleton šablonu.
	 * 
	 * @return instanca tabele studenata.
	 */
	public static TableStudent getInstance() {
		if (instance == null) {
			instance = new TableStudent();
		}
		return instance;
	}
	
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
	
	/**
	 * Broji koliko puta je selektovana šesta kolona tabele.
	 */
	private int sesta = 0;
	
	/**
	 * Sorter tabele studenata.
	 */
	private TableRowSorter<AbstractTableModelStudent> sorter;
	
	/**
	 * Lista filtera (kriterijuma) za sortiranje tabele studenata.
	 */
	private ArrayList<RowFilter<Object, Object>> filters;

	/**
	 * Kreira tabelu studenata po istoimenom apstraktnom modelu tabele.
	 * Implementira slušače događaja koji broje koliko puta je kliknuto
	 * zaglavlje svake kolone i u zavisnosti od toga sortira tabelu studenata
	 * u rastućem ili opadajućem poretku po vrednostima iz kolone čije zaglavlje
	 * je izabrano. Onemogućene su ugrađene prečice tabele zbog konflikta sa
	 * već postojećim prečicama same aplikacije.
	 */
	@SuppressWarnings("deprecation")
	public TableStudent() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		AbstractTableModelStudent model = new AbstractTableModelStudent();
		this.setModel(model);
		
		//REFERENCE: https://community.oracle.com/tech/developers/discussion/1368212/jtable-inhibit-ctrl-a-select-all
		InputMap im = this.getInputMap(javax.swing.JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK), "none");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK), "none");

		
		sorter = new TableRowSorter<AbstractTableModelStudent>(model);
		
		//REFERNCE: https://docs.oracle.com/javase/7/docs/api/javax/swing/RowFilter.html
		filters = new ArrayList<RowFilter<Object, Object>>();
	    setRowSorter(sorter);
	    sorter.setSortable(0, false);
	    sorter.setSortable(1, false);
	    sorter.setSortable(2, false);
	    sorter.setSortable(3, false);
	    sorter.setSortable(4, false);
	    sorter.setSortable(5, false);
	    this.getTableHeader().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int col = TableStudent.getInstance().columnAtPoint(e.getPoint());

				switch (col) {
				case 0:
					prva++;
					druga = 0;
					treca = 0;
					cetvrta = 0;
					peta = 0;
					sesta = 0;

					if ((prva % 2) == 0) {

						// REFERENCE: https://www.codebyamir.com/blog/sort-list-of-objects-by-field-java
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return arg1.getBrIndeksa().compareTo(arg0.getBrIndeksa());
							}
						});
						
					//REFERENCE: https://stackoverflow.com/questions/1496143/dynamically-changing-the-column-header-text-in-jtable
						getColumnModel().getColumn(0).setHeaderValue("Indeks " + "\u25BC");
						getColumnModel().getColumn(1).setHeaderValue("Ime");
						getColumnModel().getColumn(2).setHeaderValue("Prezime");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Status");
						getColumnModel().getColumn(5).setHeaderValue("Prosek");
						
					} else {
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return arg0.getBrIndeksa().compareTo(arg1.getBrIndeksa());
							}
						});

						getColumnModel().getColumn(0).setHeaderValue("Indeks " + "\u25B2");
						getColumnModel().getColumn(1).setHeaderValue("Ime");
						getColumnModel().getColumn(2).setHeaderValue("Prezime");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Status");
						getColumnModel().getColumn(5).setHeaderValue("Prosek");
						
					}
					break;

				case 1:
					prva = 0;
					druga++;
					treca = 0;
					cetvrta = 0;
					peta = 0;
					sesta = 0;

					if ((druga % 2) == 0) {
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return arg1.getIme().compareTo(arg0.getIme());
							}
						});

						getColumnModel().getColumn(0).setHeaderValue("Indeks");
						getColumnModel().getColumn(1).setHeaderValue("Ime " + "\u25BC");
						getColumnModel().getColumn(2).setHeaderValue("Prezime");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Status");
						getColumnModel().getColumn(5).setHeaderValue("Prosek");
						
					} else {
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return arg0.getIme().compareTo(arg1.getIme());
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Indeks");
						getColumnModel().getColumn(1).setHeaderValue("Ime " + "\u25B2");
						getColumnModel().getColumn(2).setHeaderValue("Prezime");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Status");
						getColumnModel().getColumn(5).setHeaderValue("Prosek");
					}
					break;

				case 2:
					prva = 0;
					druga = 0;
					treca++;
					cetvrta = 0;
					peta = 0;
					sesta = 0;

					if ((treca % 2) == 0) {
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return arg1.getPrezime().compareTo(arg0.getPrezime());
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Indeks");
						getColumnModel().getColumn(1).setHeaderValue("Ime");
						getColumnModel().getColumn(2).setHeaderValue("Prezime " + "\u25BC");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Status");
						getColumnModel().getColumn(5).setHeaderValue("Prosek");
						
					} else {
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return arg0.getPrezime().compareTo(arg1.getPrezime());
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Indeks");
						getColumnModel().getColumn(1).setHeaderValue("Ime");
						getColumnModel().getColumn(2).setHeaderValue("Prezime " + "\u25B2");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Status");
						getColumnModel().getColumn(5).setHeaderValue("Prosek");
						
					}
					break;

				case 3:
					prva = 0;
					druga = 0;
					treca = 0;
					cetvrta++;
					peta = 0;
					sesta = 0;

					if ((cetvrta % 2) == 0) {
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return String.valueOf(arg0.getTrenutnaGodina())
										.compareTo(String.valueOf(arg1.getTrenutnaGodina()));
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Indeks");
						getColumnModel().getColumn(1).setHeaderValue("Ime");
						getColumnModel().getColumn(2).setHeaderValue("Prezime");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija " + "\u25BC");
						getColumnModel().getColumn(4).setHeaderValue("Status");
						getColumnModel().getColumn(5).setHeaderValue("Prosek");
						
					} else {
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return String.valueOf(arg1.getTrenutnaGodina())
										.compareTo(String.valueOf(arg0.getTrenutnaGodina()));
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Indeks");
						getColumnModel().getColumn(1).setHeaderValue("Ime");
						getColumnModel().getColumn(2).setHeaderValue("Prezime");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija " + "\u25B2");
						getColumnModel().getColumn(4).setHeaderValue("Status");
						getColumnModel().getColumn(5).setHeaderValue("Prosek");
						
					}
					break;

				case 4:
					prva = 0;
					druga = 0;
					treca = 0;
					cetvrta = 0;
					peta++;
					sesta = 0;

					if ((peta % 2) == 0) {
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return String.valueOf(arg1.getStatusStudenta())
										.compareTo(String.valueOf(arg0.getStatusStudenta()));
							}
						});
						
						
						getColumnModel().getColumn(0).setHeaderValue("Indeks");
						getColumnModel().getColumn(1).setHeaderValue("Ime");
						getColumnModel().getColumn(2).setHeaderValue("Prezime");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Status " + "\u25BC");
						getColumnModel().getColumn(5).setHeaderValue("Prosek");
							
					} else {
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return String.valueOf(arg0.getStatusStudenta())
										.compareTo(String.valueOf(arg1.getStatusStudenta()));
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Indeks");
						getColumnModel().getColumn(1).setHeaderValue("Ime");
						getColumnModel().getColumn(2).setHeaderValue("Prezime");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Status " + "\u25B2");
						getColumnModel().getColumn(5).setHeaderValue("Prosek");
						
					}
					break;

				case 5:
					prva = 0;
					druga = 0;
					treca = 0;
					cetvrta = 0;
					peta = 0;
					sesta++;

					if ((sesta % 2) == 0) {
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return Double.compare(arg0.getProsecnaOcena(), arg1.getProsecnaOcena());
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Indeks");
						getColumnModel().getColumn(1).setHeaderValue("Ime");
						getColumnModel().getColumn(2).setHeaderValue("Prezime");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Status");
						getColumnModel().getColumn(5).setHeaderValue("Prosek " + "\u25BC");
						
					} else {
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return Double.compare(arg1.getProsecnaOcena(), arg0.getProsecnaOcena());
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Indeks");
						getColumnModel().getColumn(1).setHeaderValue("Ime");
						getColumnModel().getColumn(2).setHeaderValue("Prezime");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Status");
						getColumnModel().getColumn(5).setHeaderValue("Prosek " + "\u25B2");
						
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
	 * Dobavlja sorter tabele studenata.
	 * 
	 * @return sorter tabele studenata.
	 */
	public TableRowSorter<AbstractTableModelStudent> getSorter() {
		return sorter;
	}
	
	/**
	 * Dobavlja listu filtera primenjenih na tabelu studenata.
	 * 
	 * @return lista filtera.
	 */
	public ArrayList<RowFilter<Object, Object>> getFilters() {
		return filters;
	}
}
