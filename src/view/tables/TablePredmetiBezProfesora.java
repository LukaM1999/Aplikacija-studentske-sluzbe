package view.tables;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import view.abstractTableModels.AbstractTableModelPredmetiBezProfesora;

/**
 * Klasa u kojoj se pravi instanca funkcionalne tabele predmeta koje
 * ne predaje nijedan profesor čiji redovi i kolone se mogu višestruko selektovati. 
 * 
 * @author Mihajlo Kisić
 *
 */
public class TablePredmetiBezProfesora extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5761903382514643440L;
	
	/**
	 * Instanca tabele predmeta koje ne predaje nijedan profesor.
	 */
	private static TablePredmetiBezProfesora instance = null;
	
	/**
	 * Dobavlja instancu tabele predmeta koje ne predaje nijedan profesor
	 * po Singleton konvenciji.
	 * 
	 * @return povratna vrednost instanca tabele predmeta koje ne predaje nijedan profesor.
	 */
	public static TablePredmetiBezProfesora getInstance() {
			if (instance == null) {
				instance = new TablePredmetiBezProfesora();
			}
			return instance;
	}
	
	/**
	 * Kreira tabelu predmeta bez profesora po istoimenom apstraktnom modelu tabele.
	 */
	public TablePredmetiBezProfesora() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.setModel(new AbstractTableModelPredmetiBezProfesora());
	}

	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component c = super.prepareRenderer(renderer, row, column);
		if (isRowSelected(row)) {
			c.setBackground(Color.LIGHT_GRAY);
		} else {
			c.setBackground(Color.WHITE);
		}
		return c;
	}
}
