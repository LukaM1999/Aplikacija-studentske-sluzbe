package view;

import javax.swing.table.AbstractTableModel;

import model.BazaStudenata;

public class AbstractTableModelStudent extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5712265068405275698L;
	
	public AbstractTableModelStudent() {}

	@Override
	public int getColumnCount() {
		return BazaStudenata.getInstance().getColumnCount();
	}

	@Override
	public int getRowCount() {
		return BazaStudenata.getInstance().getStudenti().size();
	}

	@Override
	public String getColumnName(int column) {
		return BazaStudenata.getInstance().getColumnName(column);
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaStudenata.getInstance().getValueAt(rowIndex, columnIndex);
	}

}
