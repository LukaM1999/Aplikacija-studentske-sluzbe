package view;

import javax.swing.table.AbstractTableModel;

import model.BazaOcena;

public class AbstractTableModelOcena extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4953354420568992083L;
	
	public  AbstractTableModelOcena() {}

	@Override
	public int getRowCount() {
		return BazaOcena.getInstance().getOcene().size();
	}

	@Override
	public int getColumnCount() {
		return BazaOcena.getInstance().getColumnCount();
	}

	@Override
	public String getColumnName(int column) {
		return BazaOcena.getInstance().getColumnName(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaOcena.getInstance().getValueAt(rowIndex, columnIndex);
	}

}
