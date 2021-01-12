package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import controller.PredmetController;
import model.BazaPredmeta;
import model.Predmet;

public class TablePredmet extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6347149562179666223L;

	private static TablePredmet instance = null;

	public static TablePredmet getInstance() {
		if (instance == null) {
			instance = new TablePredmet();
		}
		return instance;
	}
	private TableRowSorter<AbstractTableModelPredmet> sorter;
	
	private ArrayList<RowFilter<Object, Object>> filters;
	
	private int prva = 0;
	private int druga = 0;
	private int treca = 0;
	private int cetvrta = 0;
	private int peta = 0;
	
	public TablePredmet() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		AbstractTableModelPredmet model = new AbstractTableModelPredmet();
		this.setModel(model);
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
	
	public TableRowSorter<AbstractTableModelPredmet> getSorter() {
		return sorter;
	}
	
	public ArrayList<RowFilter<Object, Object>> getFilters() {
		return filters;
	}
}
