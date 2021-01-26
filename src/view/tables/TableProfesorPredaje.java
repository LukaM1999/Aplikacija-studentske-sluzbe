package view.tables;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import view.abstractTableModels.AbstractTableModelProfesorPredaje;

/**
 * Klasa u kojoj se pravi instanca funkcionalne tabele predmeta koje predaje profesor, 
 * čiji redovi i kolone se mogu višestruko selektovati. 
 * 
 * @author Mihajlo Kisić
 *
 */
public class TableProfesorPredaje extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -343970554148063257L;

	/**
	 * Instanca tabele predmeta koje predaje profesor.
	 */
	private static TableProfesorPredaje instance = null;

	/**
	 * Dobavlja instancu tabele predmeta koje predaje profesor
	 * po Singleton šablonu.
	 * 
	 * @return instanca tabele predmeta koje predaje profesor.
	 */
	public static TableProfesorPredaje getInstance() {
		if (instance == null) {
			instance = new TableProfesorPredaje();
		}
		return instance;
	}
	
	/**
	 * Kreira tabelu predmeta koje predaje profesor po apstraktnom modelu tabele <code>AbstractTableModelProfesorPredaje</code>.
	 */
	public TableProfesorPredaje() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.setModel(new AbstractTableModelProfesorPredaje()); 
		 		  
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
