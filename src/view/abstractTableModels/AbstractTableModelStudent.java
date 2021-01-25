package view.abstractTableModels;

import javax.swing.table.AbstractTableModel;

import model.baze.BazaStudenata;

/**
 * Klasa koja predstavlja apstraktni model tabele studenata,
 * na osnovu koga je kreirana tabela studenata.
 * 
 * @author Luka Miletić
 *
 */
public class AbstractTableModelStudent extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5712265068405275698L;
	
	/**
	 * Kreira apstraktni model tabele studenata.
	 */
	public AbstractTableModelStudent() {}

	/**
	 * Dobavlja broj redova tabele studenata odnosno
	 * broj studenata u listi studenata iz baze studenata.
	 */
	@Override
	public int getColumnCount() {
		return BazaStudenata.getInstance().getColumnCount();
	}

	/**
	 * Dobavlja broj kolona tabele studenata.
	 */
	@Override
	public int getRowCount() {
		return BazaStudenata.getInstance().getStudenti().size();
	}

	/**
	 * Dobavlja naziv kolone za prosleđeni indeks kolone iz tabele studenata.
	 * 
	 * @param column indeks kolone tabele studenata
	 */
	@Override
	public String getColumnName(int column) {
		return BazaStudenata.getInstance().getColumnName(column);
	}
	
	/**
	 * Dobavlja vrednost koja se nalazi u redu i koloni tabele studenata, čiji indeksi su prosleđeni.
	 * 
	 * @param rowIndex indeks reda tabele studenata
	 * @param columnIndex indeks kolone tabele studenata
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaStudenata.getInstance().getValueAt(rowIndex, columnIndex);
	}

}
