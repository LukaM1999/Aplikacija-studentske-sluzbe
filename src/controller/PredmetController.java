package controller;

import java.util.List;

import model.BazaPredmeta;
import model.Predmet;
import view.MainFrame;

//Koriscen materijal sa vezbi
public class PredmetController {
	
	private static PredmetController instance = null;
	
	public static PredmetController getInstance() {
		if (instance == null) {
			instance = new PredmetController();
		}
		return instance;
	}
	
	private PredmetController() {}
	
	public void dodajPredmet(Predmet p) {
		// izmena modela
		BazaPredmeta.getInstance().dodajPredmet(p);
		// azuriranje prikaza
		MainFrame.getInstance().azurirajPredmete("DODAT", -1);
	}
	
	public void izmeniPredmet(int row) {
		MainFrame.getInstance().izmeniPredmet(row);
	}
	
	public List<Predmet> getPredmeti(){
		return BazaPredmeta.getInstance().getPredmeti();
	}
	
	public Predmet getPredmet(int row) {
		return BazaPredmeta.getInstance().getRow(row);
	}
}
