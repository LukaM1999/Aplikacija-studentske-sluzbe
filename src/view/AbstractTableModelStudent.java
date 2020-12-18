package view;

import javax.swing.table.AbstractTableModel;

import model.BazaStudenata;

public class AbstractTableModelStudent extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AbstractTableModelStudent() {}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return BazaStudenata.getInstance().getColumnCount();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return BazaStudenata.getInstance().getStudenti().size();
	}

	public String getColumnName(int column) {
		return BazaStudenata.getInstance().getColumnName(column);
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return BazaStudenata.getInstance().getValueAt(rowIndex, columnIndex);
	}

}
