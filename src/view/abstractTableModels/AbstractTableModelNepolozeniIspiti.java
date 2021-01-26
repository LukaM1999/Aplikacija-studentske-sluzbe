package view.abstractTableModels;

import javax.swing.table.AbstractTableModel;

import model.baze.BazaNepolozenihIspita;

/**
 * Klasa koja predstavlja apstraktni model tabele nepoloženih ispita,
 * na osnovu koga je kreirana tabela nepoloženih ispita.
 * 
 * @author Mihajlo Kisić
 *
 */
public class AbstractTableModelNepolozeniIspiti extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1727204623118227513L;

	/**
	 * Kreira apstraktni model tabele nepoloženih ispita.
	 */
	public AbstractTableModelNepolozeniIspiti() {}

	
	/**
	 * Dobavlja broj redova tabele nepoloženih ispita odnosno
	 * broj predmeta u listi predmeta baze nepoloženih ispita.
	 */
	@Override
	public int getRowCount() {
		return BazaNepolozenihIspita.getInstance().getPredmeti().size();
	}
	
	/**
	 * Dobavlja broj kolona tabele nepoloženih ispita.
	 * 
	 * 
	 */
	@Override
	public int getColumnCount() {
		return BazaNepolozenihIspita.getInstance().getColumnCount();
	}

	/**
	 * Dobavlja naziv kolone za prosleđeni indeks kolone iz tabele nepoloženih ispita.
	 * 
	 * @param column indeks kolone tabele nepoloženih ispita
	 */
	@Override
	public String getColumnName(int column) {
		return BazaNepolozenihIspita.getInstance().getColumnName(column);
	}

	/**
	 * Dobavlja vrednost koja se nalazi u redu i koloni tabele nepoloženih ispita, čiji indeksi su prosleđeni.
	 * 
	 * @param rowIndex indeks reda tabele nepoloženih ispita
	 * @param columnIndex indeks kolone tabele nepoloženih ispita
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaNepolozenihIspita.getInstance().getValueAt(rowIndex, columnIndex);
	}

}
