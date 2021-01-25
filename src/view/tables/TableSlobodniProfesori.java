package view.tables;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import view.abstractTableModels.AbstractTableModelSlobodniProfesori;

/**
 * Klasa u kojoj se pravi instanca funkcionalne tabele profesora
 * koji mogu da predaju predmete, čiji redovi i kolone se mogu jednostruko selektovati.
 * 
 * @author Luka Miletić
 */
public class TableSlobodniProfesori extends JTable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1663254715361898783L;
	
	/**
	 * Instanca tabele profesora koji mogu da predaju predmete.
	 */
	private static TableSlobodniProfesori instance = null;
	
	/**
	 * Dobavlja instancu tabele profesora koji koji mogu da predaju predmete po Singleton konvenciji.
	 * 
	 * @return povratna vrednost instanca tabele profesora koji mogu da predaju predmete.
	 */
	public static TableSlobodniProfesori getInstance() {
		if (instance == null) {
			instance = new TableSlobodniProfesori();
		}
		return instance;
	}

	/**
	 * Kreira tabelu profesora koji mogu da predaju predmete po apstraktnom modelu tabele <code>AbstractTableModelSlobodniProfesori</code>.
	 */
	public TableSlobodniProfesori() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelSlobodniProfesori());
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
