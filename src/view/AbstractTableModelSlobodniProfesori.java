package view;

import javax.swing.table.AbstractTableModel;

import model.BazaSlobodnihProfesora;

public class AbstractTableModelSlobodniProfesori extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6892173465120479686L;
	
	public AbstractTableModelSlobodniProfesori () {}
	
	@Override
	public int getRowCount() {
		return BazaSlobodnihProfesora.getInstance().getProfesori().size();
	}

	@Override
	public int getColumnCount() {
		return BazaSlobodnihProfesora.getInstance().getColumnCount();
	}

	public String getColumnName(int column) {
		return BazaSlobodnihProfesora.getInstance().getColumnName(column);
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaSlobodnihProfesora.getInstance().getValueAt(rowIndex, columnIndex);
	}

}
