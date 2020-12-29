package view;

import javax.swing.table.AbstractTableModel;

import model.BazaSlobodnihPredmeta;

public class AbstractTableModelSlobodniPredmeti extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -375097574657851459L;

	public AbstractTableModelSlobodniPredmeti() {}

	@Override
	public int getRowCount() {
		return BazaSlobodnihPredmeta.getInstance().getPredmeti().size();
	}

	@Override
	public int getColumnCount() {
		return BazaSlobodnihPredmeta.getInstance().getColumnCount();
	}

	@Override
	public String getColumnName(int column) {
		return BazaSlobodnihPredmeta.getInstance().getColumnName(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaSlobodnihPredmeta.getInstance().getValueAt(rowIndex, columnIndex);
	}

}
