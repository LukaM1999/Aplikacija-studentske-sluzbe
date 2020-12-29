package model;

import java.util.ArrayList;
import java.util.List;

import controller.PredmetController;
import model.Predmet.Semestar;

public class BazaSlobodnihPredmeta {
	
	private static BazaSlobodnihPredmeta instance = null;

	public static BazaSlobodnihPredmeta getInstance() {
		if (instance == null) {
			instance = new BazaSlobodnihPredmeta();
		}
		return instance;
	}

	
	private List<Predmet> predmetiSlobodni;
	private List<Predmet> predmetiNepolozeni;
	private List<Ocena> predmetiPolozeni;
	private List<String> kolone;
	
	private BazaSlobodnihPredmeta() {
		
		init();
		
		
		this.kolone = new ArrayList<String>();
		this.kolone.add("");
	}
	
	private void init() {
		this.predmetiSlobodni = new ArrayList<Predmet>();
	}
	
	
	public List<Predmet> getPredmeti() {
		return predmetiSlobodni;
	}

	public void setPredmeti(List<Predmet> predmeti) {
		this.predmetiSlobodni = predmeti;
	}
	
	public int getColumnCount() {
		return 1;
	}
	
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}
	
	public Predmet getRow(int rowIndex) {
		return this.predmetiSlobodni.get(rowIndex);
	}
	
	public String getValueAt(int row, int column) {
		Predmet predmet = this.predmetiSlobodni.get(row);
		switch (column) {
		case 0:
			String predmetInfo = predmet.getSifra() + "-" + predmet.getNaziv();
			return predmetInfo;
		default:
			return null;
		}
	}
	
	public void initPolozeni(Student s) {
		this.predmetiPolozeni = s.getSpisakPolozenih();
	}
	
	public void initNepolozeni(Student s) {
		this.predmetiNepolozeni = s.getSpisakNepolozenih();
	}
	
	public void initSlobodne(Student s) {
		this.predmetiSlobodni = s.getSlobodne();
	}
	
	public void dodajSlobodanPredmet(String sifra, String naziv, int ESPB, int godinaStudija, Semestar semestar) {
		this.predmetiSlobodni.add(new Predmet(sifra, naziv, ESPB, godinaStudija, semestar));
	}
	
	public void dodajSlobodanPredmet(Predmet p) {
		this.predmetiSlobodni.add(p);
	}
	
	public void izbrisiSlobodanPredmet(String id) {
		for (Predmet p : predmetiSlobodni) {
			if (p.getSifra() == id) {
				predmetiSlobodni.remove(p);
				break;
			}
		}
	}
	
	public void popuniSlobodne(Student s) {
		
		boolean polozen = false;
		boolean nepolozen = false;
		boolean slobodan = false;
				
		for(Predmet svi: PredmetController.getInstance().getPredmeti()) {
			for(Ocena polozeni: predmetiPolozeni) {
				if(svi.getSifra().equals(polozeni.getPredmet().getSifra())) {
					polozen = true;
					break;
				}
			}
			for (Predmet nepolozeni: predmetiNepolozeni) {
				if(svi.getSifra().equals(nepolozeni.getSifra())) {
					nepolozen = true;
					break;
				}
			}
			for(Predmet slobodni : predmetiSlobodni) {
				if(svi.getSifra().equals(slobodni.getSifra())) {
					slobodan = true;
					break;
				}
				
			}
			if(!polozen && !nepolozen && !slobodan && s.getTrenutnaGodina() >= svi.getGodinaStudija()) {
				dodajSlobodanPredmet(svi);
			}
			
			polozen = false;
			nepolozen = false;
			slobodan = false;
		}
	}
	
	

}
