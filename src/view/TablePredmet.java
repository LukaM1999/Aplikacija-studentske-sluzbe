package view;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import controller.PredmetController;
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
