package view.tables;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import view.abstractTableModels.AbstractTableModelOcena;

/**
 * Klasa u kojoj se pravi instanca funkcionalne tabele ocena
 * čiji redovi i kolone se mogu jednostruko selektovati.
 * 
 * @author Luka Miletić
 */
public class TableOcena extends JTable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -217391044285361696L;
	
	/**
	 * Instanca tabele ocena.
	 */
	private static TableOcena instance = null;
	
	/**
	 * Dobavlja instancu tabele ocena po Singleton šablonu.
	 * 
	 * @return instanca tabele ocena.
	 */
	public static TableOcena getInstance() {
		if (instance == null) {
			instance = new TableOcena();
		}
		return instance;
	}

	/**
	 * Kreira tabelu ocena po istoimenom apstraktnom modelu tabele.
	 */
	public TableOcena() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelOcena());
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
