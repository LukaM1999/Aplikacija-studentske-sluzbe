package view.abstractTableModels;

import javax.swing.table.AbstractTableModel;

import model.baze.BazaSlobodnihProfesora;

/**
 * Klasa koja predstavlja apstraktni model tabele profesora koji mogu da predaju predmete,
 * na osnovu koga je kreirana tabela predmeta koje student može da sluša.
 * 
 * @author Luka Miletić
 *
 */
public class AbstractTableModelSlobodniProfesori extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6892173465120479686L;
	
	/**
	 * Kreira apstraktni model tabele profesora koji mogu da predaju predmete.
	 */
	public AbstractTableModelSlobodniProfesori () {}
	
	/**
	 * Dobavlja broj redova tabele profesora koji mogu da predaju predmete odnosno
	 * broj profesora u listi profesora iz baze profesora koji mogu da predaju predmete.
	 */
	@Override
	public int getRowCount() {
		return BazaSlobodnihProfesora.getInstance().getProfesori().size();
	}

	/**
	 * Dobavlja broj kolona tabele profesora koji mogu da predaju predmete.
	 */
	@Override
	public int getColumnCount() {
		return BazaSlobodnihProfesora.getInstance().getColumnCount();
	}

	/**
	 * Dobavlja naziv kolone za prosleđeni indeks kolone iz tabele profesora koji mogu da predaju predmete.
	 * 
	 * @param column indeks kolone tabele profesora koji mogu da predaju predmete
	 */
	public String getColumnName(int column) {
		return BazaSlobodnihProfesora.getInstance().getColumnName(column);
	}
	
	/**
	 * Dobavlja vrednost koja se nalazi u redu i koloni tabele profesora koji mogu da predaju predmete, čiji indeksi su prosleđeni.
	 * 
	 * @param rowIndex indeks reda tabele profesora koji mogu da predaju predmete
	 * @param columnIndex indeks kolone tabele profesora koji mogu da predaju predmete
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaSlobodnihProfesora.getInstance().getValueAt(rowIndex, columnIndex);
	}

}
