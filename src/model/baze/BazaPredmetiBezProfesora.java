package model.baze;

import java.util.ArrayList;
import java.util.List;

import controller.PredmetController;
import model.entiteti.Predmet;
import model.entiteti.Profesor;
import model.entiteti.Predmet.Semestar;


/**
 * Klasa koja predstavlja bazu predmeta koje ne predaje nijedan profesor.
 * 
 * @author Mihajlo Kisić
 *
 */
public class BazaPredmetiBezProfesora {
	
	/**
	 * Instanca baze predmeta koje ne predaje nijedan profesor.
	 */
	private static BazaPredmetiBezProfesora instance = null;

	/**
	 * Dobavlja instancu baze predmeta koje ne predaje nijedan profesor po Singleton šablonu.
	 * 
	 * @return instanca baze predmeta koje ne predaje nijedan profesor
	 */
	public static BazaPredmetiBezProfesora getInstance() {
		if (instance == null) {
			instance = new BazaPredmetiBezProfesora();
		}
		return instance;
	}
	
	/**
	 * Lista predmeta koje ne predaje nijedan profesor.
	 */
	private List<Predmet> slobodniPredmeti;
	
	/**
	 * Lista predmeta koje predaje neki profesor.
	 */
	private List<Predmet> predajePredmet;
	
	/**
	 * Lista kolona tabele predmeta koje ne predaje nijedan profesor.
	 */
	private List<String> kolone;
	
	/**
	 * Kreira praznu listu predmeta koje ne predaje nijedan profesor i dodaje
	 * jednu kolonu bez naziva radi adekvatnog prikaza u tabeli.
	 */
	private BazaPredmetiBezProfesora () {
		 
		init();
		
		this.kolone = new ArrayList<String>();
		this.kolone.add("");
		
	}
	
	/**
	 * Inicijalizuje listu predmeta koje ne predaje nijedan profesor praznom listom.
	 */
	private void init() {
		this.slobodniPredmeti = new ArrayList<Predmet>();
	}
	
	/**
	 * Dobavlja listu predmeta koje ne predaje nijedan profesor.
	 * 
	 * @return lista predmeta koje ne predaje nijedan profesor
	 */
	public List<Predmet> getPredmeti() {
		return slobodniPredmeti;
	}

	/**
	 * Postavlja polje liste predmeta koje ne predaje nijedan profesor na prosleđenu listu predmeta.
	 * 
	 * @param predmeti lista predmeta
	 */
	public void setPredmeti(List<Predmet> predmeti) {
		this.slobodniPredmeti = predmeti;
	}
	
	/**
	 * Dobavlja broj kolona tabele predmeta koje ne predaje nijedan profesor.
	 * 
	 * @return predefinisan broj kolona tabele predmeta koje ne predaje nijedan profesor
	 */
	public int getColumnCount() {
		return 1;
	}
	
	/**
	 * Vraća naziv kolone tabele predmeta koje ne predaje nijedan profesor čiji indeks je prosleđen.
	 * 
	 * @param index indeks kolone tabele predmeta koje ne predaje nijedan profesor
	 * @return naziv kolone iz liste kolona sa prosleđenim indeksom
	 */
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}
	
	/**
	 * Dobavlja prosleđeni red iz liste predmeta koje ne predaje nijedan profesor odnosno predmet koji
	 * se u listi nalazi na indeksu koji je prosleđen.
	 * 
	 * @param rowIndex indeks reda liste predmeta koje ne predaje nijedan profesor
	 * @return predmet iz liste predmeta koje ne predaje nijedan profesor na prosleđenom indeksu
	 */
	public Predmet getRow(int rowIndex) {
		return this.slobodniPredmeti.get(rowIndex);
	}
	
	/**
	 * Dobavlja vrednost koja se nalazi na prosleđenom indeksu reda i kolone u tabeli
	 * predmeta koje ne predaje nijedan profesor u vidu <code>String</code>-a.
	 * 
	 * @param row indeks reda u kom se nalazi željena vrednost
	 * @param column indeks kolone u kojoj se nalazi željena vrednost
	 * @return vrednost koja se nalazi u prosleđenoj koloni i redu
	 */
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
	
	/**
	 * Inicijalizuje listu predmeta koje ne predaje nijedan profesor tako što joj vrednost postavi
	 * na vrednost liste predmeta koje ne predaje nijedan profesor od prosleđenog profesora.
	 * 
	 * @param p objekat profesora čija se lista predmeta koje ne predaje nijedan profesor koristi
	 */
	public void initSlobodne(Profesor p) {
		this.slobodniPredmeti = p.getPredmetiBezProfesora();
	}
	
	/**
	 * Pravi novi predmet pomoću prosleđenih parametara i dodaje ga u listu 
	 * predmeta koje ne predaje nijedan profesor, jedino ako predmet sa prosleđenom
	 * šifrom već ne postoji u njoj.
	 * 
	 * @param sifra šifra predmeta
	 * @param naziv naziv predmeta
	 * @param ESPB broj ESPB poena predmeta
	 * @param godinaStudija godina studija na kojoj se predmet predaje
	 * @param semestar semestar u kom se predmet predaje
	 */
	public void dodajSlobodanPredmet(String sifra, String naziv, int ESPB, int godinaStudija, Semestar semestar) {
		for(Predmet p : slobodniPredmeti) {
			if(p.getSifra().equals(sifra)) {
				return;
			}
		}
		this.slobodniPredmeti.add(new Predmet(sifra, naziv, ESPB, godinaStudija, semestar));
	}
	
	/**
	 * Dodaje prosleđeni predmet u listu predmeta koje ne predaje nijedan profesor
	 * ako se predmet sa istom šifrom kao šifra prosleđenog predmeta već ne nalazi
	 * u listi.
	 * 
	 * @param p objekat predmeta koji hoćemo da dodamo u listu predmeta koje ne predaje nijedan profesor
	 */
	public void dodajSlobodanPredmet(Predmet p) {
		for(Predmet pred : slobodniPredmeti) {
			if(pred.getSifra().equals(p.getSifra())) {
				return;
			}
		}
		this.slobodniPredmeti.add(p);
	}
	
	/**
	 * Briše predmet iz liste predmeta koje ne predaje nijedan profesor ako mu
	 * se šifra poklapa sa prosleđenom šifrom predmeta.
	 * 
	 * @param id šifra predmeta koji hoćemo da obrišemo
	 */
	public void izbrisiSlobodanPredmet(String id) {
		for (Predmet p : slobodniPredmeti) {
			if (p.getSifra() == id) {
				slobodniPredmeti.remove(p);
				break;
			}
		}
	}

	/**
	 * Inicijalizuje listu predmeta koje predaje profesor tako što joj vrednost postavi
	 * na vrednost liste predmeta koje predaje prosleđeni profesor.
	 * 
	 * @param p objekat profesora čija se lista predmeta koje predaje koristi
	 */
	public void initPredajePredmet(Profesor p) {
		this.predajePredmet = p.getPredajePredmet();
	}
	
	/**
	 * Dobavlja listu predmeta koje predaje profesor.
	 * 
	 * @return predajePredmet lista predmeta koje predaje profesor
	 */
	public List<Predmet> getPredajePredmet() {
		return predajePredmet;
	}

	/**
	 * Postavlja polje liste predmeta koje predaje profesor na prosleđenu listu predmeta.
	 * 
	 * @param predajePredmet lista predmeta koje predaje profesor
	 */
	public void setPredajePredmet(List<Predmet> predajePredmet) {
		this.predajePredmet = predajePredmet;
	}
	
	/**
	 * Metoda koja za svaki predmet iz baze predmeta proverava da li
	 * ga ne predaje nijedan profesor i da li ga nema u listi predmeta koje
	 * ne predaje nijedan profesor. Ako su oba uslova ispunjena, prosleđenom
	 * profesoru se u listu predmeta koje ne predaje nijedan profesor dodaje taj predmet.
	 * 
	 * @param p objekat profesora kome želimo da dodamo predmet u listu slobodnih predmeta
	 */
	public void popuniPredmetiBezProfesora(Profesor p) {
		
		boolean slobodan = false;
		
		for(Predmet svi : PredmetController.getInstance().getPredmeti()) {
			if (svi.getProfesor() == null) {
				slobodan = true;
			}
			
			for(Predmet slobodni : slobodniPredmeti) {
				if (svi.getSifra().equals(slobodni.getSifra())) {
					slobodan = false;
					break;
				}
			}
			
			if(slobodan) {
				p.dodajSlobodan(svi);
			}
			
			slobodan = false;
		}
		
	}
	
	
	
	
}
