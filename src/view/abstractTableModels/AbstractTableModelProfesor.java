package view.abstractTableModels;

import javax.swing.table.AbstractTableModel;

import model.baze.BazaProfesora;


/**
 * Klasa koja predstavlja apstraktni model tabele profesora,
 * na osnovu koga je kreirana tabela profesora.
 * 
 * @author Mihajlo Kisić
 *
 */
public class AbstractTableModelProfesor extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1406696593944639363L;

	/**
	 * Kreira apstraktni model tabele profesora.
	 */
	public AbstractTableModelProfesor () {}
	
	/**
	 * Dobavlja broj redova tabele profesora odnosno
	 * broj profesora u listi profesora iz baze profesora.
	 */
	@Override
	public int getRowCount() {
		return BazaProfesora.getInstance().getProfesori().size();
	}

	/**
	 * Dobavlja broj kolona tabele profesora.
	 */
	@Override
	public int getColumnCount() {
		return BazaProfesora.getInstance().getColumnCount();
	}

	/**
	 * Dobavlja naziv kolone za prosleđeni indeks kolone iz tabele profesora.
	 * 
	 * @param column indeks kolone tabele profesora
	 */
	public String getColumnName(int column) {
		return BazaProfesora.getInstance().getColumnName(column);
	}
	
	/**
	 * Dobavlja vrednost koja se nalazi u redu i koloni tabele profesora, čiji indeksi su prosleđeni.
	 * 
	 * @param rowIndex indeks reda tabele profesora
	 * @param columnIndex indeks kolone tabele profesora
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaProfesora.getInstance().getValueAt(rowIndex, columnIndex);
	}

}
