package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

public class TableSlobodniPredmeti extends JTable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5654297085273539531L;
	
	private static TableSlobodniPredmeti instance = null;
	
	public static TableSlobodniPredmeti getInstance() {
		if (instance == null) {
			instance = new TableSlobodniPredmeti();
		}
		return instance;
	}


	public TableSlobodniPredmeti() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelSlobodniPredmeti());
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
