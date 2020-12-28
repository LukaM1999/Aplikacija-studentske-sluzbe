package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import model.BazaOcena;
import model.BazaStudenata;

public class TableStudent extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8306744364765947146L;

	private static TableStudent instance = null;

	public static TableStudent getInstance() {
		if (instance == null) {
			instance = new TableStudent();
		}
		return instance;
	}

	public TableStudent() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelStudent());
		getTableHeader().addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int col = columnAtPoint(e.getPoint());
		        String name = getColumnName(col);
		        BazaStudenata.getInstance().sortiranje(col);
		    }
		});

		/*
		 * Comparator myComparator = new java.util.Comparator() {
		 *//**
			 * Custom compare to sort numbers as numbers. Strings as strings, with numbers
			 * ordered before strings.
			 * 
			 * @param o1
			 * @param o2
			 * @return
			 *//*
				 * @Override public int compare(Object oo1, Object oo2) { boolean
				 * isFirstNumeric, isSecondNumeric; String o1 = oo1.toString(), o2 =
				 * oo2.toString();
				 * 
				 * isFirstNumeric = o1.matches("\\d+"); isSecondNumeric = o2.matches("\\d+"); if
				 * (isFirstNumeric) { if (isSecondNumeric) { return
				 * Integer.valueOf(o1).compareTo(Integer.valueOf(o2)); } else { return -1; //
				 * numbers always smaller than letters } } else { if (isSecondNumeric) { return
				 * 1; // numbers always smaller than letters } else { // Those lines throw
				 * ArrayIndexOutOfBoundsException // isFirstNumeric =
				 * o1.split("[^0-9]")[0].matches("\\d+"); // isSecondNumeric =
				 * o2.split("[^0-9]")[0].matches("\\d+");
				 * 
				 * // Trying to parse String to Integer. // If there is no Exception then Object
				 * is numeric, else it's not. try{ Integer.parseInt(o1); isFirstNumeric = true;
				 * }catch(NumberFormatException e){ isFirstNumeric = false; } try{
				 * Integer.parseInt(o2); isSecondNumeric = true; }catch(NumberFormatException
				 * e){ isSecondNumeric = false; } if (isFirstNumeric) { if (isSecondNumeric) {
				 * int intCompare =
				 * Integer.valueOf(o1.split("[^0-9]")[0]).compareTo(Integer.valueOf(o2.split(
				 * "[^0-9]")[0])); if (intCompare == 0) { return o1.compareToIgnoreCase(o2); }
				 * return intCompare; } else { return -1; // numbers always smaller than letters
				 * } } else { if (isSecondNumeric) { return 1; // numbers always smaller than
				 * letters } else { return o1.compareToIgnoreCase(o2); } } } } } };
				 * 
				 * TableRowSorter sorter = new TableRowSorter(); setRowSorter(sorter);
				 * sorter.setModel(getModel()); for(int i = 0; i < this.getColumnCount(); i++)
				 * sorter.setComparator(i, myComparator);
				 */

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
