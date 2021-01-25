package model.baze;

import java.util.ArrayList;
import java.util.List;

import model.entiteti.Predmet;
import model.entiteti.Profesor;
import model.entiteti.Predmet.Semestar;

public class BazaProfesorPredaje {

	private static BazaProfesorPredaje instance = null;

	public static BazaProfesorPredaje getInstance() {
		if (instance == null) {
			instance = new BazaProfesorPredaje();
		}
		return instance;
	}
	
	private List<Predmet> predmeti;
	private List<String> kolone;
	
	private BazaProfesorPredaje() {
			
			init();
			
			this.kolone = new ArrayList<String>();
			this.kolone.add("Šifra");
			this.kolone.add("Naziv");
			this.kolone.add("Godina studija");
			this.kolone.add("Semestar");
		}

	private void init() {
		this.predmeti = new ArrayList<Predmet>();
	}
	
	public void initPredajePredmet(Profesor p) {
		this.predmeti = p.getPredajePredmet();
	}

	public List<Predmet> getPredmeti() {
		return predmeti;
	}
	
	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
	}
	
	public int getColumnCount() {
		return 4;
	}
	
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}
	
	public Predmet getRow(int rowIndex) {
		return this.predmeti.get(rowIndex);
	}
	
	public String getValueAt(int row, int column) {
		Predmet predmet = this.predmeti.get(row);
		switch (column) {
		case 0:
			return predmet.getSifra();
		case 1:
			return predmet.getNaziv();
		case 2:
			return String.valueOf(predmet.getGodinaStudija());
		case 3:
			return String.valueOf(predmet.getSemestar());
		default:
			return null;
		}
	}
	
	public void dodajPredmet(String sifra, String naziv, int ESPB, int godinaStudija, Semestar semestar) {
		this.predmeti.add(new Predmet(sifra, naziv, ESPB, godinaStudija, semestar));
	}
	
	public void dodajPredmet(Predmet p) {
		this.predmeti.add(new Predmet(p));
	}
	
	public void izbrisiPredmet(String id) {
		for (Predmet p : predmeti) {
			if (p.getSifra() == id) {
				predmeti.remove(p);
				break;
			}
		}
	}
	
	
}
