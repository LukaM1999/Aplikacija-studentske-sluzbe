package controller;

import java.util.List;

import model.BazaStudenata;
import model.Student;
import view.MainFrame;

//Koriscen materijal sa vezbi
public class StudentController {

	private static StudentController instance = null;

	public static StudentController getInstance() {
		if (instance == null) {
			instance = new StudentController();
		}
		return instance;
	}

	private StudentController() {
	}

	public void dodajStudenta(Student s) {
		BazaStudenata.getInstance().dodajStudenta(s);
		MainFrame.getInstance().azurirajStudente("DODAT", -1);
	}

	public void izmeniStudenta(int row) {
		MainFrame.getInstance().izmeniStudenta(row);
	}

	public void izbrisiStudenta(int rowSelectedIndex) {
		if (rowSelectedIndex < 0) {
			return;
		}

		Student s = BazaStudenata.getInstance().getRow(rowSelectedIndex);
		BazaStudenata.getInstance().izbrisiStudenta(s.getBrIndeksa());
		MainFrame.getInstance().azurirajStudente("UKLONJEN", rowSelectedIndex);
	}

	public List<Student> getStudenti() {
		return BazaStudenata.getInstance().getStudenti();
	}

	public Student getStudent(int row) {
		return BazaStudenata.getInstance().getRow(row);
	}

	public Student getStudent(String indeks) {
		return BazaStudenata.getInstance().getStudent(indeks);
	}

}
