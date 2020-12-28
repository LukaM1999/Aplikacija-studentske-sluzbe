package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

public class TablePolozeniIspiti extends JTable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2577399505076936567L;
	
	private static TablePolozeniIspiti instance = null;
	
	public static TablePolozeniIspiti getInstance() {
		if (instance == null) {
			instance = new TablePolozeniIspiti();
		}
		return instance;
	}


	public TablePolozeniIspiti() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelPolozeniIspiti());
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
