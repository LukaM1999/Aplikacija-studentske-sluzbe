package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

public class TablePredmetiBezProfesora extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5761903382514643440L;
	
	private static TablePredmetiBezProfesora instance = null;
	
		public static TablePredmetiBezProfesora getInstance() {
			if (instance == null) {
				instance = new TablePredmetiBezProfesora();
			}
			return instance;
	}
		
	public TablePredmetiBezProfesora() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.setModel(new AbstractTableModelPredmetiBezProfesora());
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
