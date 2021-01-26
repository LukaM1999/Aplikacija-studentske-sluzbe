package view.tables;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import view.abstractTableModels.AbstractTableModelNepolozeniIspiti;

/**
 * Klasa u kojoj se pravi instanca funkcionalne tabele nepoloženih ispita
 * čiji redovi i kolone se mogu jednostruko selektovati.
 * 
 * @author Mihajlo Kisić
 */
public class TableNepolozeniIspiti extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8038757563140948107L;
	
	/**
	 * Instanca tabele nepoloženih ispita.
	 */
	private static TableNepolozeniIspiti instance = null;
	
	/**
	 * Dobavlja instancu tabele nepoloženih ispita po Singleton šablonu.
	 * 
	 * @return instanca tabele nepoloženih ispita.
	 */
	public static TableNepolozeniIspiti getInstance() {
		if (instance == null) {
			instance = new TableNepolozeniIspiti();
		}
		return instance;
	}
	
	/**
	 * Kreira tabelu nepoloženih ispita po istoimenom apstraktnom modelu tabele.
	 */
	public TableNepolozeniIspiti() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelNepolozeniIspiti());
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
