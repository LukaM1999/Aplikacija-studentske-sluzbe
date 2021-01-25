package controller;


import java.util.List;

import model.baze.BazaSlobodnihPredmeta;
import model.entiteti.Predmet;
import model.entiteti.Student;

public class SlobodniPredmetiController {
	
private static SlobodniPredmetiController instance = null;
	
	public static SlobodniPredmetiController getInstance() {
		if (instance == null) {
			instance = new SlobodniPredmetiController();
		}
		return instance;
	}
	
	private SlobodniPredmetiController() {}
	
	public void initSpisakPolozenih(Student s) {
		BazaSlobodnihPredmeta.getInstance().initPolozeni(s);
	}
	
	public void initSpisakNepolozenih(Student s) {
		BazaSlobodnihPredmeta.getInstance().initNepolozeni(s);
	}
	
	public void initSlobodne(Student s) {
		BazaSlobodnihPredmeta.getInstance().initSlobodne(s);
	}
	
	public void popuniSlobodne(Student s) {
		BazaSlobodnihPredmeta.getInstance().popuniSlobodne(s);
	}
	
	public List<Predmet> getPredmeti() {
		return BazaSlobodnihPredmeta.getInstance().getPredmeti();
	}
	
	public void izbrisiSlobodan(String sifra) {
		BazaSlobodnihPredmeta.getInstance().izbrisiSlobodanPredmet(sifra);
	}
	

}
