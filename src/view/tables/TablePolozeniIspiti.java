package view.tables;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import view.abstractTableModels.AbstractTableModelPolozeniIspiti;

/**
 * Klasa u kojoj se pravi instanca funkcionalne tabele položenih ispita
 * čiji redovi i kolone se mogu jednostruko selektovati.
 * 
 * @author Luka Miletić
 */
public class TablePolozeniIspiti extends JTable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2577399505076936567L;
	
	/**
	 * Instanca tabele položenih ispita.
	 */
	private static TablePolozeniIspiti instance = null;
	
	/**
	 * Dobavlja instancu tabele položenih ispita po Singleton šablonu.
	 * 
	 * @return instanca tabele položenih ispita.
	 */
	public static TablePolozeniIspiti getInstance() {
		if (instance == null) {
			instance = new TablePolozeniIspiti();
		}
		return instance;
	}

	/**
	 * Kreira tabelu položenih ispita po istoimenom apstraktnom modelu tabele.
	 */
	public TablePolozeniIspiti() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelPolozeniIspiti());
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
