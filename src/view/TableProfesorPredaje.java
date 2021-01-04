package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;


public class TableProfesorPredaje extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -343970554148063257L;

	private static TableProfesorPredaje instance = null;

	public static TableProfesorPredaje getInstance() {
		if (instance == null) {
			instance = new TableProfesorPredaje();
		}
		return instance;
	}
	
	public TableProfesorPredaje() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelPredmet()); 
		 		  
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


}
