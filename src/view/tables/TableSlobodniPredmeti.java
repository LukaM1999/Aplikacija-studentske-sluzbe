package view.tables;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import view.abstractTableModels.AbstractTableModelSlobodniPredmeti;

/**
 * Klasa u kojoj se pravi instanca funkcionalne tabele predmeta koje student
 * može da sluša, čiji redovi i kolone se mogu jednostruko selektovati.
 * 
 * @author Luka Miletić
 */
public class TableSlobodniPredmeti extends JTable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5654297085273539531L;
	
	/**
	 * Instanca tabele predmeta koje student može da sluša.
	 */
	private static TableSlobodniPredmeti instance = null;
	
	/**
	 * Dobavlja instancu tabele predmeta koje student može da sluša po Singleton šablonu.
	 * 
	 * @return instanca tabele predmeta koje student može da sluša.
	 */
	public static TableSlobodniPredmeti getInstance() {
		if (instance == null) {
			instance = new TableSlobodniPredmeti();
		}
		return instance;
	}

	/**
	 * Kreira tabelu predmeta koje student može da sluša po apstraktnom modelu tabele <code>AbstractTableModelSlobodniPredmeti</code>.
	 */
	public TableSlobodniPredmeti() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelSlobodniPredmeti());
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
