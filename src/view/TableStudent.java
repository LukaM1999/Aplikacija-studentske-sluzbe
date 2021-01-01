package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import controller.OcenaController;
import controller.StudentController;
import model.BazaStudenata;
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

	private int prva = 0;
	private int druga = 0;
	private int treca = 0;
	private int cetvrta = 0;
	private int peta = 0;
	private int sesta = 0;

	public TableStudent() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelStudent());
		this.getTableHeader().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int col = TableStudent.getInstance().columnAtPoint(e.getPoint());

				switch (col) {
				case 0:
					prva++;
					druga = 0;
					treca = 0;
					cetvrta = 0;
					peta = 0;
					sesta = 0;

					if ((prva % 2) == 0) {

						// REFERENCE: https://www.codebyamir.com/blog/sort-list-of-objects-by-field-java
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return arg1.getBrIndeksa().compareTo(arg0.getBrIndeksa());
							}
						});
						
					//REFERENCE: https://stackoverflow.com/questions/1496143/dynamically-changing-the-column-header-text-in-jtable
						getColumnModel().getColumn(0).setHeaderValue("Indeks " + "\u25B2");
						getColumnModel().getColumn(1).setHeaderValue("Ime");
						getColumnModel().getColumn(2).setHeaderValue("Prezime");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Status");
						getColumnModel().getColumn(5).setHeaderValue("Prosek");
						
					} else {
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return arg0.getBrIndeksa().compareTo(arg1.getBrIndeksa());
							}
						});

						getColumnModel().getColumn(0).setHeaderValue("Indeks " + "\u25BC");
						getColumnModel().getColumn(1).setHeaderValue("Ime");
						getColumnModel().getColumn(2).setHeaderValue("Prezime");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Status");
						getColumnModel().getColumn(5).setHeaderValue("Prosek");
						
					}
					break;

				case 1:
					prva = 0;
					druga++;
					treca = 0;
					cetvrta = 0;
					peta = 0;
					sesta = 0;

					if ((druga % 2) == 0) {
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return arg1.getIme().compareTo(arg0.getIme());
							}
						});

						getColumnModel().getColumn(0).setHeaderValue("Indeks");
						getColumnModel().getColumn(1).setHeaderValue("Ime " + "\u25B2");
						getColumnModel().getColumn(2).setHeaderValue("Prezime");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Status");
						getColumnModel().getColumn(5).setHeaderValue("Prosek");
						getTableHeader().repaint();
						
					} else {
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return arg0.getIme().compareTo(arg1.getIme());
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Indeks");
						getColumnModel().getColumn(1).setHeaderValue("Ime " + "\u25BC");
						getColumnModel().getColumn(2).setHeaderValue("Prezime");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Status");
						getColumnModel().getColumn(5).setHeaderValue("Prosek");
						getTableHeader().repaint();
					}
					break;

				case 2:
					prva = 0;
					druga = 0;
					treca++;
					cetvrta = 0;
					peta = 0;
					sesta = 0;

					if ((treca % 2) == 0) {
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return arg1.getPrezime().compareTo(arg0.getPrezime());
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Indeks");
						getColumnModel().getColumn(1).setHeaderValue("Ime");
						getColumnModel().getColumn(2).setHeaderValue("Prezime " + "\u25B2");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Status");
						getColumnModel().getColumn(5).setHeaderValue("Prosek");
						
					} else {
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return arg0.getPrezime().compareTo(arg1.getPrezime());
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Indeks");
						getColumnModel().getColumn(1).setHeaderValue("Ime");
						getColumnModel().getColumn(2).setHeaderValue("Prezime " + "\u25BC");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Status");
						getColumnModel().getColumn(5).setHeaderValue("Prosek");
						
					}
					break;

				case 3:
					prva = 0;
					druga = 0;
					treca = 0;
					cetvrta++;
					peta = 0;
					sesta = 0;

					if ((cetvrta % 2) == 0) {
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return String.valueOf(arg0.getTrenutnaGodina())
										.compareTo(String.valueOf(arg1.getTrenutnaGodina()));
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Indeks");
						getColumnModel().getColumn(1).setHeaderValue("Ime");
						getColumnModel().getColumn(2).setHeaderValue("Prezime");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija " + "\u25B2");
						getColumnModel().getColumn(4).setHeaderValue("Status");
						getColumnModel().getColumn(5).setHeaderValue("Prosek");
						
					} else {
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return String.valueOf(arg1.getTrenutnaGodina())
										.compareTo(String.valueOf(arg0.getTrenutnaGodina()));
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Indeks");
						getColumnModel().getColumn(1).setHeaderValue("Ime");
						getColumnModel().getColumn(2).setHeaderValue("Prezime");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija " + "\u25BC");
						getColumnModel().getColumn(4).setHeaderValue("Status");
						getColumnModel().getColumn(5).setHeaderValue("Prosek");
						
					}
					break;

				case 4:
					prva = 0;
					druga = 0;
					treca = 0;
					cetvrta = 0;
					peta++;
					sesta = 0;

					if ((peta % 2) == 0) {
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return String.valueOf(arg1.getStatusStudenta())
										.compareTo(String.valueOf(arg0.getStatusStudenta()));
							}
						});
						
						
						getColumnModel().getColumn(0).setHeaderValue("Indeks");
						getColumnModel().getColumn(1).setHeaderValue("Ime");
						getColumnModel().getColumn(2).setHeaderValue("Prezime");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Status " + "\u25B2");
						getColumnModel().getColumn(5).setHeaderValue("Prosek");
							
					} else {
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return String.valueOf(arg0.getStatusStudenta())
										.compareTo(String.valueOf(arg1.getStatusStudenta()));
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Indeks");
						getColumnModel().getColumn(1).setHeaderValue("Ime");
						getColumnModel().getColumn(2).setHeaderValue("Prezime");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Status " + "\u25BC");
						getColumnModel().getColumn(5).setHeaderValue("Prosek");
						
					}
					break;

				case 5:
					prva = 0;
					druga = 0;
					treca = 0;
					cetvrta = 0;
					peta = 0;
					sesta++;

					if ((sesta % 2) == 0) {
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return String.valueOf(arg0.getProsecnaOcena())
										.compareTo(String.valueOf(arg1.getProsecnaOcena()));
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Indeks");
						getColumnModel().getColumn(1).setHeaderValue("Ime");
						getColumnModel().getColumn(2).setHeaderValue("Prezime");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Status");
						getColumnModel().getColumn(5).setHeaderValue("Prosek " + "\u25B2");
						
					} else {
						Collections.sort(BazaStudenata.getInstance().getStudenti(), new Comparator<Student>() {

							@Override
							public int compare(Student arg0, Student arg1) {
								return String.valueOf(arg1.getProsecnaOcena())
										.compareTo(String.valueOf(arg0.getProsecnaOcena()));
							}
						});
						
						getColumnModel().getColumn(0).setHeaderValue("Indeks");
						getColumnModel().getColumn(1).setHeaderValue("Ime");
						getColumnModel().getColumn(2).setHeaderValue("Prezime");
						getColumnModel().getColumn(3).setHeaderValue("Godina studija");
						getColumnModel().getColumn(4).setHeaderValue("Status");
						getColumnModel().getColumn(5).setHeaderValue("Prosek " + "\u25BC");
						
					}
					break;

				default:
					break;

				}

			}
		});

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
			if (Float.isNaN(s.getProsecnaOcena())) {
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
