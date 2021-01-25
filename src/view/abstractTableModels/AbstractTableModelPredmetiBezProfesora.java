package view.abstractTableModels;

import javax.swing.table.AbstractTableModel;

import model.baze.BazaPredmetiBezProfesora;

/**
 * Klasa koja predstavlja apstraktni model tabele predmeta koje ne predaje nijedan profesor,
 * na osnovu koga je kreirana tabela predmeta koje ne predaje nijedan profesor.
 * 
 * @author Mihajlo Kisić
 *
 */
public class AbstractTableModelPredmetiBezProfesora extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9177086443746075398L;
	
	/**
	 * Kreira apstraktni model tabele predmeta koje ne predaje nijedan profesor.
	 */
	public AbstractTableModelPredmetiBezProfesora() {}
	
	/**
	 * Dobavlja broj redova tabele predmeta koje ne predaje nijedan profesor odnosno
	 * broj predmeta u listi predmeta baze predmeta koje ne predaje nijedan profesor.
	 */
	@Override
	public int getRowCount() {
		return BazaPredmetiBezProfesora.getInstance().getPredmeti().size();
	}

	/**
	 * Dobavlja broj kolona tabele predmeta koje ne predaje nijedan profesor.
	 */
	@Override
	public int getColumnCount() {
		return BazaPredmetiBezProfesora.getInstance().getColumnCount();
	}

	/**
	 * Dobavlja naziv kolone za prosleđeni indeks kolone iz tabele predmeta koje ne predaje nijedan profesor.
	 * 
	 * @param column indeks kolone tabele predmeta koje ne predaje nijedan prodesor
	 */
	@Override
	public String getColumnName(int column) {
		return BazaPredmetiBezProfesora.getInstance().getColumnName(column);
	}

	/**
	 * Dobavlja vrednost koja se nalazi u redu i koloni tabele predmeta koje ne predaje nijedan profesor, čiji indeksi su prosleđeni.
	 * 
	 * @param rowIndex indeks reda tabele predmeta koje ne predaje nijedan profesor.
	 * @param columnIndex indeks kolone tabele predmeta koje ne predaje nijedan profesor.
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaPredmetiBezProfesora.getInstance().getValueAt(rowIndex, columnIndex);
	}
}
