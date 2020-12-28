package view;

import javax.swing.table.AbstractTableModel;

import model.BazaPolozenihIspita;

public class AbstractTableModelPolozeniIspiti extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -896511322799218026L;
	
	public AbstractTableModelPolozeniIspiti() {}
	
	@Override
	public int getRowCount() {
		return BazaPolozenihIspita.getInstance().getOcene().size();
	}

	@Override
	public int getColumnCount() {
		return BazaPolozenihIspita.getInstance().getColumnCount();
	}

	@Override
	public String getColumnName(int column) {
		return BazaPolozenihIspita.getInstance().getColumnName(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaPolozenihIspita.getInstance().getValueAt(rowIndex, columnIndex);
	}

}
