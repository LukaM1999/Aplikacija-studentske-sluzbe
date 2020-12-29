package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import controller.PredmetController;
import model.Predmet;
import model.Student;

public class TablePredmet extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6347149562179666223L;
	
	private static TablePredmet instance = null;
	
	public static TablePredmet getInstance() {
		if (instance == null) {
			instance = new TablePredmet();
		}
		return instance;
	}

	
	public TablePredmet() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelPredmet());
		
		/*
		 * try { for (Predmet p : PredmetController.getInstance().getPredmeti()) {
		 * PredmetController.getInstance().popuniListuPolozili(p); } } catch
		 * (NullPointerException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * for (Predmet p : PredmetController.getInstance().getPredmeti()) { for
		 * (Student s : p.getPolozili() ) { System.out.println(s.getBrIndeksa()); } }
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
