package view;

import javax.swing.table.AbstractTableModel;

import model.BazaProfesorPredaje;

public class AbstractTableModelProfesorPredaje extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -448326854977925451L;

	public AbstractTableModelProfesorPredaje () {}
	
	@Override
	public int getRowCount() {
		return BazaProfesorPredaje.getInstance().getPredmeti().size();
	}

	@Override
	public int getColumnCount() {
		return BazaProfesorPredaje.getInstance().getColumnCount();
	}

	public String getColumnName(int column) {
		return BazaProfesorPredaje.getInstance().getColumnName(column);
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaProfesorPredaje.getInstance().getValueAt(rowIndex, columnIndex);
	}

}
