package view.abstractTableModels;

import javax.swing.table.AbstractTableModel;

import model.baze.BazaOcena;

/**
 * Klasa koja predstavlja apstraktni model tabele ocena,
 * na osnovu koga je kreirana tabela ocena.
 * 
 * @author Luka Miletić
 *
 */
public class AbstractTableModelOcena extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4953354420568992083L;
	
	/**
	 * Kreira apstraktni model tabele ocena.
	 */
	public  AbstractTableModelOcena() {}

	/**
	 * Dobavlja broj redova tabele ocena odnosno
	 * broj ocena u listi ocena iz baze ocena.
	 */
	@Override
	public int getRowCount() {
		return BazaOcena.getInstance().getOcene().size();
	}

	/**
	 * Dobavlja broj kolona tabele ocena.
	 */
	@Override
	public int getColumnCount() {
		return BazaOcena.getInstance().getColumnCount();
	}

	/**
	 * Dobavlja naziv kolone za prosleđeni indeks kolone iz tabele ocena.
	 * 
	 * @param column indeks kolone tabele ocena
	 */
	@Override
	public String getColumnName(int column) {
		return BazaOcena.getInstance().getColumnName(column);
	}

	/**
	 * Dobavlja vrednost koja se nalazi u redu i koloni tabele ocena, čiji indeksi su prosleđeni.
	 * 
	 * @param rowIndex indeks reda tabele ocena
	 * @param columnIndex indeks kolone tabele ocena
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaOcena.getInstance().getValueAt(rowIndex, columnIndex);
	}

}
