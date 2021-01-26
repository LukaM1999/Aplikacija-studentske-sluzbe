package model.baze;

import java.util.ArrayList;
import java.util.List;

import model.entiteti.Predmet;
import model.entiteti.Profesor;
import model.entiteti.Predmet.Semestar;

/**
 * Klasa koja predstavlja bazu predmeta koje predaje profesor.
 * 
 * @author Mihajlo Kisić
 *
 */
public class BazaProfesorPredaje {

	/**
	 * Instanca bazepredmeta koje predaje profesor.
	 */
	private static BazaProfesorPredaje instance = null;

	/**
	 * Dobavlja instancu baze predmeta koje predaje profesor po Singleton šablonu.
	 * 
	 * @return instanca baze predmeta koje predaje profesor
	 */
	public static BazaProfesorPredaje getInstance() {
		if (instance == null) {
			instance = new BazaProfesorPredaje();
		}
		return instance;
	}
	
	/**
	 * Lista predmeta koje predaje profesor.
	 */
	private List<Predmet> predmeti;
	
	/**
	 * Lista kolona tabele predmeta koje predaje profesor.
	 */
	private List<String> kolone;
	
	/**
	 * Kreira praznu listu predmeta koje predaje profesor i dodaje
	 * kolone sa predefinisanim nazivima koji se mogu videti u zaglavlju tabele
	 * predmeta koje predaje profesor.
	 */
	private BazaProfesorPredaje() {
			
			init();
			
			this.kolone = new ArrayList<String>();
			this.kolone.add("Šifra");
			this.kolone.add("Naziv");
			this.kolone.add("Godina studija");
			this.kolone.add("Semestar");
		}

	/**
	 * Inicijalizuje listu predmeta koje predaje profesor praznom listom.
	 */
	private void init() {
		this.predmeti = new ArrayList<Predmet>();
	}
	
	/**
	 * 
	 * @param p
	 */
	public void initPredajePredmet(Profesor p) {
		this.predmeti = p.getPredajePredmet();
	}
	
	/**
	 * Dobavlja listu predmeta koje predaje profesor.
	 * 
	 * @return lista predmeta koje predaje profesor
	 */
	public List<Predmet> getPredmeti() {
		return predmeti;
	}
	
	/**
	 * Postavlja polje liste predmeta koje predaje profesor na prosleđenu listu predmeta.
	 * 
	 * @param predmeti lista predmeta
	 */
	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
	}
	
	/**
	 * Dobavlja broj kolona tabele predmeta koje predaje profesor.
	 * 
	 * @return predefinisan broj kolona tabele predmeta koje predaje profesor
	 */
	public int getColumnCount() {
		return 4;
	}
	
	/**
	 * Vraća naziv kolone tabele predmeta koje predaje profesor čiji indeks je prosleđen.
	 * 
	 * @param index indeks kolone tabele predmeta koje predaje profesor
	 * @return naziv kolone iz liste kolona sa prosleđenim indeksom
	 */
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}
	
	/**
	 * Dobavlja prosleđeni red iz liste predmeta koje predaje profesor odnosno predmet koji
	 * se u listi nalazi na indeksu koji je prosleđen.
	 * 
	 * @param rowIndex indeks reda liste predmeta koje predaje profesor
	 * @return predmet iz liste predmeta koje predaje profesor na prosleđenom indeksu
	 */
	public Predmet getRow(int rowIndex) {
		return this.predmeti.get(rowIndex);
	}
	
	/**
	 * Dobavlja vrednost koja se nalazi na prosleđenom indeksu reda i kolone u tabeli
	 * predmeta koje predaje profesor u vidu <code>String</code>-a.
	 * 
	 * @param row indeks reda u kom se nalazi željena vrednost
	 * @param column indeks kolone u kojoj se nalazi željena vrednost
	 * @return vrednost koja se nalazi u prosleđenoj koloni i redu
	 */
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
	
	/**
	 * Pravi novi predmet pomoću prosleđenih parametara i dodaje ga u listu 
	 * predmeta koje predaje profesor.
	 * 
	 * @param sifra šifra predmeta
	 * @param naziv naziv predmeta
	 * @param ESPB broj ESPB poena predmeta
	 * @param godinaStudija godina studija na kojoj se predmet predaje
	 * @param semestar semestar u kom se predmet predaje
	 */
	public void dodajPredmet(String sifra, String naziv, int ESPB, int godinaStudija, Semestar semestar) {
		this.predmeti.add(new Predmet(sifra, naziv, ESPB, godinaStudija, semestar));
	}
	
	/**
	 * Dodaje prosleđeni predmet u listu predmeta koje predaje profesor.
	 * 
	 * @param p objekat predmeta koji hoćemo da dodamo u listu predmeta koje predaje profesor
	 */
	public void dodajPredmet(Predmet p) {
		this.predmeti.add(new Predmet(p));
	}
	
	/**
	 * Briše predmet iz liste predmeta koje predaje profesor ako mu
	 * se šifra poklapa sa prosleđenom šifrom predmeta.
	 * 
	 * @param id šifra predmeta koji hoćemo da obrišemo
	 */
	public void izbrisiPredmet(String id) {
		for (Predmet p : predmeti) {
			if (p.getSifra() == id) {
				predmeti.remove(p);
				break;
			}
		}
	}
	
	
}
