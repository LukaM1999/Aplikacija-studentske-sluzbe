package controller;

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
	
	private StudentController() {}
	
	public void dodajStudenta(Student s) {
		// izmena modela
		BazaStudenata.getInstance().dodajStudenta(s);
		// azuriranje prikaza
		MainFrame.getInstance().azurirajStudente("DODAT", -1);
	}
}
