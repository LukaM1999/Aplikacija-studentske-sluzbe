package view.abstractTableModels;

import javax.swing.table.AbstractTableModel;

import model.baze.BazaSlobodnihPredmeta;

/**
 * Klasa koja predstavlja apstraktni model tabele predmeta koje student može da sluša,
 * na osnovu koga je kreirana tabela predmeta koje student može da sluša.
 * 
 * @author Luka Miletić
 *
 */
public class AbstractTableModelSlobodniPredmeti extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -375097574657851459L;

	/**
	 * Kreira apstraktni model tabele predmeta koje student može da sluša.
	 */
	public AbstractTableModelSlobodniPredmeti() {}

	/**
	 * Dobavlja broj redova tabele predmeta koje student može da sluša odnosno
	 * broj predmeta u listi predmeta iz baze predmeta koje student može da sluša.
	 */
	@Override
	public int getRowCount() {
		return BazaSlobodnihPredmeta.getInstance().getPredmeti().size();
	}

	/**
	 * Dobavlja broj kolona tabele predmeta koje student može da sluša.
	 */
	@Override
	public int getColumnCount() {
		return BazaSlobodnihPredmeta.getInstance().getColumnCount();
	}

	/**
	 * Dobavlja naziv kolone za prosleđeni indeks kolone iz tabele predmeta koje student može da sluša.
	 * 
	 * @param column indeks kolone tabele predmeta koje student može da sluša
	 */
	@Override
	public String getColumnName(int column) {
		return BazaSlobodnihPredmeta.getInstance().getColumnName(column);
	}

	/**
	 * Dobavlja vrednost koja se nalazi u redu i koloni tabele predmeta koje student može da sluša, čiji indeksi su prosleđeni.
	 * 
	 * @param rowIndex indeks reda tabele predmeta koje student može da sluša
	 * @param columnIndex indeks kolone tabele predmeta koje student može da sluša
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaSlobodnihPredmeta.getInstance().getValueAt(rowIndex, columnIndex);
	}

}
