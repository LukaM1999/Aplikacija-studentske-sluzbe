package view;

import javax.swing.table.AbstractTableModel;

import model.BazaNepolozenihIspita;

public class AbstractTableModelNepolozeniIspiti extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1727204623118227513L;

	public AbstractTableModelNepolozeniIspiti() {}

	@Override
	public int getRowCount() {
		return BazaNepolozenihIspita.getInstance().getPredmeti().size();
	}

	@Override
	public int getColumnCount() {
		return BazaNepolozenihIspita.getInstance().getColumnCount();
	}

	@Override
	public String getColumnName(int column) {
		return BazaNepolozenihIspita.getInstance().getColumnName(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaNepolozenihIspita.getInstance().getValueAt(rowIndex, columnIndex);
	}

}
