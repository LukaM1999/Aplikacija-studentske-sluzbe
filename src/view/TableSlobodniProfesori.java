package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

public class TableSlobodniProfesori extends JTable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1663254715361898783L;
	
	private static TableSlobodniProfesori instance = null;
	
	public static TableSlobodniProfesori getInstance() {
		if (instance == null) {
			instance = new TableSlobodniProfesori();
		}
		return instance;
	}


	public TableSlobodniProfesori() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelSlobodniProfesori());
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
