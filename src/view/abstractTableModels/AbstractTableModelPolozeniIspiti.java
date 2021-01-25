package view.abstractTableModels;

import javax.swing.table.AbstractTableModel;

import model.baze.BazaPolozenihIspita;

/**
 * Klasa koja predstavlja apstraktni model tabele položenih ispita,
 * na osnovu koga je kreirana tabela položenih ispita.
 * 
 * @author Luka Miletić
 *
 */
public class AbstractTableModelPolozeniIspiti extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -896511322799218026L;
	
	/**
	 * Kreira apstraktni model tabele položenih ispita.
	 */
	public AbstractTableModelPolozeniIspiti() {}
	
	/**
	 * Dobavlja broj redova tabele položenih ispita odnosno
	 * broj predmeta u listi predmeta baze položenih ispita.
	 */
	@Override
	public int getRowCount() {
		return BazaPolozenihIspita.getInstance().getOcene().size();
	}

	/**
	 * Dobavlja broj kolona tabele položenih ispita.
	 */
	@Override
	public int getColumnCount() {
		return BazaPolozenihIspita.getInstance().getColumnCount();
	}

	/**
	 * Dobavlja naziv kolone za prosleđeni indeks kolone iz tabele položenih ispita.
	 * 
	 * @param column indeks kolone tabele položenih ispita
	 */
	@Override
	public String getColumnName(int column) {
		return BazaPolozenihIspita.getInstance().getColumnName(column);
	}

	/**
	 * Dobavlja vrednost koja se nalazi u redu i koloni tabele položenih ispita, čiji indeksi su prosleđeni.
	 * 
	 * @param rowIndex indeks reda tabele položenih ispita
	 * @param columnIndex indeks kolone tabele položenih ispita
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaPolozenihIspita.getInstance().getValueAt(rowIndex, columnIndex);
	}

}
