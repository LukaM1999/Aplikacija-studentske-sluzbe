package controller;

import java.util.List;

import model.BazaProfesorPredaje;
import model.Predmet;
import model.Profesor;


public class ProfesorPredajeController {

	private static ProfesorPredajeController instance = null;
	
	public static ProfesorPredajeController getInstance() {
		if (instance == null) {
			instance = new ProfesorPredajeController();
		}
		return instance;
	}
	
	private ProfesorPredajeController () {}
	
	public void initPredajePredmet(Profesor p) {
		BazaProfesorPredaje.getInstance().initPredajePredmet(p);
	}
	
	public List<Predmet> getPredmeti(){
		return BazaProfesorPredaje.getInstance().getPredmeti();
	}
	
	
	public Predmet getPredmet(int row) { 
		return BazaProfesorPredaje.getInstance().getRow(row); }
	 
	
	public void dodajNepolozen(Predmet p) {
		BazaProfesorPredaje.getInstance().dodajPredmet(p);
	}
}
