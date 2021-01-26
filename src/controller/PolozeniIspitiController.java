package controller;

import java.util.List;

import model.baze.BazaNepolozenihIspita;
import model.baze.BazaOcena;
import model.baze.BazaPolozenihIspita;
import model.entiteti.Ocena;
import model.entiteti.Student;

/**
 * Klasa predstavlja kontroler položenih ispita napravljen
 * po MVC šablonu.
 * 
 * @author Luka Miletić
 *
 */
public class PolozeniIspitiController {
	
	/**
	 * Instanca kontrolera položenih ispita.
	 */
	private static PolozeniIspitiController instance = null;
	
	/**
	 * Dobavlja instancu kontrolera položenih ispita po Singleton šablonu.
	 * 
	 * @return instanca kontrolera položenih ispita
	 */
	public static PolozeniIspitiController getInstance() {
		if (instance == null) {
			instance = new PolozeniIspitiController();
		}
		return instance;
	}
	
	/**
	 * Prazan konstruktor kontrolera položenih ispita.
	 */
	private PolozeniIspitiController() {}
	
	/**
	 * Prosleđuje ocenu metodi koja dodaje ocenu u listu
	 * ocena baze ocena.
	 * 
	 * @param o objekat ocene
	 */
	public void dodajOcenu(Ocena o) {
		BazaOcena.getInstance().dodajOcenu(o);
	}
	
	/**
	 * Ako se indeks prosleđenog studenta poklapa sa indeksom studenta koji se nalazi
	 * u oceni, ova metoda će dodati tu ocenu prosleđenom studentu u listu položenih ispita.
	 * 
	 * @param s objekat studenta
	 */
	public void upisiOcenu(Student s) {
		List<Ocena> ocene = BazaOcena.getInstance().getOcene();
		for (Ocena o : ocene) {
			if (o.getStudent().getBrIndeksa().equals(s.getBrIndeksa())) {
				s.getSpisakPolozenih().add(o);
			}
		}
		
	}
	
	/**
	 * Prosleđuje ocenu metodi za poništavanje ocene iz baze položenih ispita koja se nalazi u
	 * listi položenih ispita na prosleđenom indeksu. Takođe prosleđuje predmet ocene na tom indeksu
	 * metodi za dodavanje predmeta u listu nepoloženih ispita baze nepoloženih ispita.
	 * 
	 * @param rowSelectedIndex indeks na kom se nalazi ocena koju hoćemo da
	 * poništimo u listi položenih ispita baze položenih ispita
	 */
	public void ponistiOcenu(int rowSelectedIndex) {
    	if (rowSelectedIndex < 0) {
			return;
		}
    	
    	Ocena o = BazaPolozenihIspita.getInstance().getRow(rowSelectedIndex);
		o.getStudent().ponistiOcenu(rowSelectedIndex);
		BazaPolozenihIspita.getInstance().ponistiOcenu(o.getStudent().getBrIndeksa(), o.getPredmet().getSifra());
		BazaNepolozenihIspita.getInstance().dodajNepolozen(o.getPredmet());
    }
	
	/**
	 * Poziva metodu za inicijalizaciju spiska položenih ispita u bazi
	 * položenih ispita za prosleđenog studenta.
	 * 
	 * @param s objekat studenta
	 */
	public void initSpisakPolozenih(Student s) {
		BazaPolozenihIspita.getInstance().initSpisakPolozenih(s);
	}
	
	/**
	 * Dobavlja listu ocena iz baze položenih ispita.
	 * 
	 * @return lista ocena
	 */
	public List<Ocena> getOcene(){
		return BazaPolozenihIspita.getInstance().getOcene();
	}
	
	/**
	 * Dobavlja ocenu koja se nalazi na prosleđenom indeksu
	 * u listi položenih ispita baze položenih ispita.
	 * 
	 * @param row indeks ocene u listi položenih ispita
	 * @return objekat ocene
	 */
	public Ocena getOcena(int row) {
		return BazaPolozenihIspita.getInstance().getRow(row);
	}
}
