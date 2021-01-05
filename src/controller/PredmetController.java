package controller;

import java.io.IOException;
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
	
	public void izbrisiPredmet(int rowSelectedIndex) {
    	if (rowSelectedIndex < 0) {
			return;
		}
    	// izmena modela
    	Predmet p = BazaPredmeta.getInstance().getRow(rowSelectedIndex);
		BazaPredmeta.getInstance().izbrisiPredmet(p.getSifra());
		// azuriranje prikaza
		MainFrame.getInstance().azurirajPredmete("UKLONJEN", rowSelectedIndex);
    }
	
	public List<Predmet> getPredmeti(){
		return BazaPredmeta.getInstance().getPredmeti();
	}
	
	public Predmet getPredmet(int row) {
		return BazaPredmeta.getInstance().getRow(row);
	}
	
	public void popuniListuPolozili (Predmet p) {
		BazaPredmeta.getInstance().dodajPolozili(p.getSifra());
	}
	
	public void XstreamSerialization(String putanja) {
		try {
			BazaPredmeta.getInstance().XstreamSerialization(putanja);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
