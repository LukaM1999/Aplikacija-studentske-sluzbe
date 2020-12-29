package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

public class TableNepolozeniIspiti extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8038757563140948107L;
private static TableNepolozeniIspiti instance = null;
	
	public static TableNepolozeniIspiti getInstance() {
		if (instance == null) {
			instance = new TableNepolozeniIspiti();
		}
		return instance;
	}
	
	public TableNepolozeniIspiti() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelNepolozeniIspiti());
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
