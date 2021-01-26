package model.baze;

import java.util.ArrayList;
import java.util.List;

import controller.PredmetController;
import model.entiteti.Ocena;
import model.entiteti.Predmet;
import model.entiteti.Student;
import model.entiteti.Predmet.Semestar;


/**
 * Klasa koja predstavlja bazu predmeta koje student može da sluša.
 * 
 * @author Luka Miletić
 *
 */
public class BazaSlobodnihPredmeta {
	
	/**
	 * Instanca baze predmeta koje student može da sluša.
	 */
	private static BazaSlobodnihPredmeta instance = null;

	/**
	 * Dobavlja instancu baze predmeta koje student može da sluša po Singleton šablonu.
	 * 
	 * @return instanca baze predmeta koje student može da sluša
	 */
	public static BazaSlobodnihPredmeta getInstance() {
		if (instance == null) {
			instance = new BazaSlobodnihPredmeta();
		}
		return instance;
	}

	/**
	 * Lista predmeta koje student može da sluša.
	 */
	private List<Predmet> predmetiSlobodni;
	private List<Predmet> predmetiNepolozeni;
	private List<Ocena> predmetiPolozeni;
	
	/**
	 * Lista kolona predmeta koje student može da sluša.
	 */
	private List<String> kolone;
	
	/**
	 * Kreira praznu listu predmeta koje student može da sluša i dodaje
	 * jednu kolonu bez naziva radi adekvatnog prikaza u tabeli.
	 */
	private BazaSlobodnihPredmeta() {
		
		init();
		
		
		this.kolone = new ArrayList<String>();
		this.kolone.add("");
	}
	
	/**
	 * Inicijalizuje listu predmeta koje student može da sluša praznom listom.
	 */
	private void init() {
		this.predmetiSlobodni = new ArrayList<Predmet>();
	}
	
	/**
	 * Dobavlja listu predmeta koje student može da sluša.
	 * 
	 * @return lista predmeta koje student može da sluša
	 */
	public List<Predmet> getPredmeti() {
		return predmetiSlobodni;
	}

	/**
	 * Postavlja polje liste predmeta koje student može da sluša na prosleđenu listu predmeta.
	 * 
	 * @param predmeti lista predmeta
	 */
	public void setPredmeti(List<Predmet> predmeti) {
		this.predmetiSlobodni = predmeti;
	}
	
	/**
	 * Dobavlja broj kolona tabele predmeta koje student može da sluša.
	 * 
	 * @return predefinisan broj kolona tabele predmeta koje student može da sluša
	 */
	public int getColumnCount() {
		return 1;
	}
	
	/**
	 * Vraća naziv kolone tabele predmeta koje student može da sluša čiji indeks je prosleđen.
	 * 
	 * @param index indeks kolone tabele predmeta koje student može da sluša
	 * @return naziv kolone iz liste kolona sa prosleđenim indeksom
	 */
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}
	
	/**
	 * Dobavlja prosleđeni red iz liste predmeta koje student može da sluša odnosno predmet koji
	 * se u listi nalazi na indeksu koji je prosleđen.
	 * 
	 * @param rowIndex indeks reda liste predmeta koje student može da sluša
	 * @return predmet iz liste predmeta koje student može da sluša na prosleđenom indeksu
	 */
	public Predmet getRow(int rowIndex) {
		return this.predmetiSlobodni.get(rowIndex);
	}
	
	/**
	 * Dobavlja vrednost koja se nalazi na prosleđenom indeksu reda i kolone u tabeli
	 * predmeta koje student može da sluša u vidu <code>String</code>-a.
	 * 
	 * @param row indeks reda u kom se nalazi željena vrednost
	 * @param column indeks kolone u kojoj se nalazi željena vrednost
	 * @return vrednost koja se nalazi u prosleđenoj koloni i redu
	 */
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
	
	/**
	 * Inicijalizuje listu predmeta koje je student položio tako što joj vrednost postavi 
	 * na vrednost liste predmeta koje je student položio od prosleđenog studenta.
	 * 
	 * @param s objekat studenta čija se lista predmeta koje je položio koristi
	 */
	public void initPolozeni(Student s) {
		this.predmetiPolozeni = s.getSpisakPolozenih();
	}
	
	/**
	 * Inicijalizuje listu predmeta koje student nije položio tako što joj vrednost postavi 
	 * na vrednost liste predmeta koje student nije položio od prosleđenog studenta.
	 * 
	 * @param s objekat studenta čija se lista predmeta koje nije položio koristi
	 */
	public void initNepolozeni(Student s) {
		this.predmetiNepolozeni = s.getSpisakNepolozenih();
	}
	
	/**
	 * Inicijalizuje listu predmeta koje student može da sluša tako što joj vrednost postavi 
	 * na vrednost liste predmeta koje student može da sluša od prosleđenog studenta.
	 * 
	 * @param s objekat studenta čija se lista predmeta koje je položio koristi
	 */
	public void initSlobodne(Student s) {
		this.predmetiSlobodni = s.getSlobodne();
	}
	
	/**
	 * Pravi novi predmet pomoću prosleđenih parametara i dodaje ga u listu 
	 * predmeta koje student može da sluša.
	 * 
	 * @param sifra šifra predmeta
	 * @param naziv naziv predmeta
	 * @param ESPB broj ESPB poena predmeta
	 * @param godinaStudija godina studija na kojoj se predmet predaje
	 * @param semestar semestar u kom se predmet predaje
	 */
	public void dodajSlobodanPredmet(String sifra, String naziv, int ESPB, int godinaStudija, Semestar semestar) {
		this.predmetiSlobodni.add(new Predmet(sifra, naziv, ESPB, godinaStudija, semestar));
	}
	
	/**
	 * Dodaje prosleđeni predmet u listu predmeta koje student može da sluša.
	 * 
	 * @param p objekat predmeta koji hoćemo da dodamo u listu predmeta koje student može da sluša
	 */
	public void dodajSlobodanPredmet(Predmet p) {
		this.predmetiSlobodni.add(p);
	}
	
	/**
	 * Briše predmet iz liste predmeta koje student može da sluša ako mu
	 * se šifra poklapa sa prosleđenom šifrom predmeta.
	 * 
	 * @param id šifra predmeta koji hoćemo da obrišemo
	 */
	public void izbrisiSlobodanPredmet(String id) {
		for (Predmet p : predmetiSlobodni) {
			if (p.getSifra() == id) {
				predmetiSlobodni.remove(p);
				break;
			}
		}
	}
	
	/**
	 * Proverava za svaki predmet iz baze predmeta da li se nalazi u nekim od ocena
	 * iz baze ocena tj da li je položen, da li se nalazi u listi nepoloženih predmeta i 
	 * da li se nalazi u listi predmeta koje student može da sluša. Nijedan od ova tri uslova
	 * ne sme biti ispunjen, zajedno sa tim da trenutna godina studija prosleđenog predmeta
	 * mora biti veća ili jednaka godini na kojoj se predaje taj predmet, da bi se tom studentu
	 * dodao taj predmet u listu predmeta koje može da sluša.
	 * 
	 * @param s objekat studenta kome želimo da dodamo predmet u listu predmeta koje može da sluša
	 */
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
