package view;

import javax.swing.table.AbstractTableModel;

import model.BazaProfesora;



public class AbstractTableModelProfesor extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1406696593944639363L;

	public AbstractTableModelProfesor () {}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return BazaProfesora.getInstance().getProfesori().size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return BazaProfesora.getInstance().getColumnCount();
	}

	public String getColumnName(int column) {
		return BazaProfesora.getInstance().getColumnName(column);
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return BazaProfesora.getInstance().getValueAt(rowIndex, columnIndex);
	}

}
