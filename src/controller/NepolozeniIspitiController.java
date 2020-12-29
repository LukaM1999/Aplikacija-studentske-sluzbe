package controller;

import java.util.List;

import model.BazaNepolozenihIspita;
import model.Predmet;
import model.Student;

public class NepolozeniIspitiController {

	private static NepolozeniIspitiController instance = null;
	
	public static NepolozeniIspitiController getInstance() {
		if (instance == null) {
			instance = new NepolozeniIspitiController();
		}
		return instance;
	}
	
	private NepolozeniIspitiController() {}
	
	
	public void initSpisakNepolozenih(Student s) {
		BazaNepolozenihIspita.getInstance().initSpisakNepolozenih(s);
	}
	
	public List<Predmet> getPredmeti(){
		return BazaNepolozenihIspita.getInstance().getPredmeti();
	}
	
	public Predmet getPredmet(int row) {
		return BazaNepolozenihIspita.getInstance().getRow(row);
	}
}
