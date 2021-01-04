package view;

import javax.swing.table.AbstractTableModel;

import model.BazaPredmetiBezProfesora;

public class AbstractTableModelPredmetiBezProfesora extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9177086443746075398L;
	
	public AbstractTableModelPredmetiBezProfesora() {}

	@Override
	public int getRowCount() {
		return BazaPredmetiBezProfesora.getInstance().getPredmeti().size();
	}

	@Override
	public int getColumnCount() {
		return BazaPredmetiBezProfesora.getInstance().getColumnCount();
	}

	@Override
	public String getColumnName(int column) {
		return BazaPredmetiBezProfesora.getInstance().getColumnName(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaPredmetiBezProfesora.getInstance().getValueAt(rowIndex, columnIndex);
	}
}
