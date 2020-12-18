package view;

import javax.swing.table.AbstractTableModel;

import model.BazaPredmeta;

public class AbstractTableModelPredmet extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1860946642320823358L;

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return BazaPredmeta.getInstance().getPredmeti().size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return BazaPredmeta.getInstance().getColumnCount();
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return BazaPredmeta.getInstance().getColumnName(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return BazaPredmeta.getInstance().getValueAt(rowIndex, columnIndex);
	}



}
