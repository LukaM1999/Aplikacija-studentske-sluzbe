package controller;

import java.util.List;

import model.BazaPredmetiBezProfesora;
import model.Predmet;
import model.Profesor;

public class PredmetiBezProfesoraController {

private static PredmetiBezProfesoraController instance = null;
	
	public static PredmetiBezProfesoraController getInstance() {
		if (instance == null) {
			instance = new PredmetiBezProfesoraController();
		}
		return instance;
	}
	
	private PredmetiBezProfesoraController() {}
	
	public void initSlobodne(Profesor p) {
		BazaPredmetiBezProfesora.getInstance().initSlobodne(p);
	}
	
	public void initPredajePredmet(Profesor p) {
		BazaPredmetiBezProfesora.getInstance().initPredajePredmet(p);
	}
	
	public void popuniPredmetiBezProfesora(Profesor p) {
		BazaPredmetiBezProfesora.getInstance().popuniPredmetiBezProfesora(p);
	}
	
	public List<Predmet> getPredmeti() {
		return BazaPredmetiBezProfesora.getInstance().getPredmeti();
	}
	
	public void izbrisiSlobodan(String sifra) {
		BazaPredmetiBezProfesora.getInstance().izbrisiSlobodanPredmet(sifra);
	}
}
