package model.baze;

import java.util.ArrayList;
import java.util.List;

import controller.PredmetController;
import model.entiteti.Predmet;
import model.entiteti.Profesor;
import model.entiteti.Predmet.Semestar;

public class BazaPredmetiBezProfesora {
	
	private static BazaPredmetiBezProfesora instance = null;

	public static BazaPredmetiBezProfesora getInstance() {
		if (instance == null) {
			instance = new BazaPredmetiBezProfesora();
		}
		return instance;
	}
	
	private List<Predmet> slobodniPredmeti;
	private List<Predmet> predajePredmet;
	private List<String> kolone;
	
	private BazaPredmetiBezProfesora () {
		 
		init();
		
		this.kolone = new ArrayList<String>();
		this.kolone.add("");
		
	}
	
	private void init() {
		this.slobodniPredmeti = new ArrayList<Predmet>();
	}
	
	public List<Predmet> getPredmeti() {
		return slobodniPredmeti;
	}

	public void setPredmeti(List<Predmet> predmeti) {
		this.slobodniPredmeti = predmeti;
	}
	
	public int getColumnCount() {
		return 1;
	}
	
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}
	
	public Predmet getRow(int rowIndex) {
		return this.slobodniPredmeti.get(rowIndex);
	}
	
	public String getValueAt(int row, int column) {
		Predmet predmet = this.slobodniPredmeti.get(row);
		switch (column) {
		case 0:
			String predmetInfo = predmet.getSifra() + "-" + predmet.getNaziv();
			return predmetInfo;
		default:
			return null;
		}
	}
		
	public void initSlobodne(Profesor p) {
		this.slobodniPredmeti = p.getPredmetiBezProfesora();
	}
	
	public void dodajSlobodanPredmet(String sifra, String naziv, int ESPB, int godinaStudija, Semestar semestar) {
		for(Predmet p : slobodniPredmeti) {
			if(p.getSifra().equals(sifra)) {
				return;
			}
		}
		this.slobodniPredmeti.add(new Predmet(sifra, naziv, ESPB, godinaStudija, semestar));
	}
	
	public void dodajSlobodanPredmet(Predmet p) {
		for(Predmet pred : slobodniPredmeti) {
			if(pred.getSifra().equals(p.getSifra())) {
				return;
			}
		}
		this.slobodniPredmeti.add(p);
	}
	
	public void izbrisiSlobodanPredmet(String id) {
		for (Predmet p : slobodniPredmeti) {
			if (p.getSifra() == id) {
				slobodniPredmeti.remove(p);
				break;
			}
		}
	}

	public void initPredajePredmet(Profesor p) {
		this.predajePredmet = p.getPredajePredmet();
	}
	
	public List<Predmet> getPredajePredmet() {
		return predajePredmet;
	}

	public void setPredajePredmet(List<Predmet> predajePredmet) {
		this.predajePredmet = predajePredmet;
	}
	
	public void popuniPredmetiBezProfesora(Profesor p) {
		
		boolean slobodan = false;
		
		for(Predmet svi : PredmetController.getInstance().getPredmeti()) {
			if (svi.getProfesor() == null) {
				slobodan = true;
			}
			
			for(Predmet slobodni : slobodniPredmeti ) {
				if (svi.getSifra().equals(slobodni.getSifra())) {
					slobodan = false;
					break;
				}
			}
			
			if(slobodan) {
				p.dodajSlobodan(svi);
				//this.dodajSlobodanPredmet(svi);
			}
			
			slobodan = false;
		}
		
	}
	
	
	
	
}
