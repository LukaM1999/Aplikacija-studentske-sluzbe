package view;

import java.awt.Color;
import java.awt.Component;


import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;


import controller.OcenaController;
import controller.StudentController;
import model.Student;

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
		
		
		for (int i = 0; i < StudentController.getInstance().getStudenti().size(); i++) {
			for (int j = 0; j < OcenaController.getInstance().getOcene().size(); j++)
				if (StudentController.getInstance().getStudenti().get(i).getBrIndeksa()
						.equals(OcenaController.getInstance().getOcene().get(j).getStudent().getBrIndeksa())) {
					StudentController.getInstance().getStudenti().get(i)
							.dodajPolozen(OcenaController.getInstance().getOcene().get(j));
				}
		}

		for (Student s : StudentController.getInstance().getStudenti()) {
			s.setProsecnaOcena(s.izracunajProsek(s.getSpisakPolozenih()));
			if(Float.isNaN(s.getProsecnaOcena())) {
				s.setProsecnaOcena(0); 
			}

		}
		

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
