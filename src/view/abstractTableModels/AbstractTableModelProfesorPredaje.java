package view.abstractTableModels;

import javax.swing.table.AbstractTableModel;

import model.baze.BazaProfesorPredaje;

/**
 * Klasa koja predstavlja apstraktni model tabele predmeta koje predaje profesor,
 * na osnovu koga je kreirana tabela predmeta koje predaje profesor.
 * 
 * @author Mihajlo Kisić
 *
 */
public class AbstractTableModelProfesorPredaje extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -448326854977925451L;

	/**
	 * Kreira apstraktni model tabele predmeta koje predaje profesor.
	 */
	public AbstractTableModelProfesorPredaje () {}
	
	/**
	 * Dobavlja broj redova tabele predmeta koje predaje profesor odnosno
	 * broj predmeta u listi predmeta iz baze predmeta koje predaje profesor.
	 */
	@Override
	public int getRowCount() {
		return BazaProfesorPredaje.getInstance().getPredmeti().size();
	}

	/**
	 * Dobavlja broj kolona tabele predmeta koje predaje profesor.
	 */
	@Override
	public int getColumnCount() {
		return BazaProfesorPredaje.getInstance().getColumnCount();
	}

	/**
	 * Dobavlja naziv kolone za prosleđeni indeks kolone iz tabele predmeta koje predaje profesor.
	 * 
	 * @param column indeks kolone tabele predmeta koje predaje profesor
	 */
	public String getColumnName(int column) {
		return BazaProfesorPredaje.getInstance().getColumnName(column);
	}
	
	/**
	 * Dobavlja vrednost koja se nalazi u redu i koloni tabele predmeta koje predaje profesor, čiji indeksi su prosleđeni.
	 * 
	 * @param rowIndex indeks reda tabele predmeta koje predaje profesor
	 * @param columnIndex indeks kolone tabele predmeta koje predaje profesor
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaProfesorPredaje.getInstance().getValueAt(rowIndex, columnIndex);
	}

}
