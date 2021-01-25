package view.abstractTableModels;

import javax.swing.table.AbstractTableModel;

import model.baze.BazaPredmeta;

/**
 * Klasa koja predstavlja apstraktni model tabele predmeta,
 * na osnovu koga je kreirana tabela predmeta.
 * 
 * @author Mihajlo Kisić
 *
 */
public class AbstractTableModelPredmet extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1860946642320823358L;
	
	/**
	 * Kreira apstraktni model tabele predmeta.
	 */
	public  AbstractTableModelPredmet() {}

	/**
	 * Dobavlja broj redova tabele predmeta odnosno
	 * broj predmeta u listi predmeta iz baze predmeta.
	 */
	@Override
	public int getRowCount() {
		return BazaPredmeta.getInstance().getPredmeti().size();
	}

	/**
	 * Dobavlja broj kolona tabele predmeta.
	 */
	@Override
	public int getColumnCount() {
		return BazaPredmeta.getInstance().getColumnCount();
	}

	/**
	 * Dobavlja naziv kolone za prosleđeni indeks kolone iz tabele predmeta.
	 * 
	 * @param column indeks kolone tabele predmeta
	 */
	@Override
	public String getColumnName(int column) {
		return BazaPredmeta.getInstance().getColumnName(column);
	}

	/**
	 * Dobavlja vrednost koja se nalazi u redu i koloni tabele predmeta, čiji indeksi su prosleđeni.
	 * 
	 * @param rowIndex indeks reda tabele predmeta
	 * @param columnIndex indeks kolone tabele predmeta
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaPredmeta.getInstance().getValueAt(rowIndex, columnIndex);
	}



}
