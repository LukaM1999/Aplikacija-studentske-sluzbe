package controller;


import java.util.List;

import model.baze.BazaSlobodnihPredmeta;
import model.entiteti.Predmet;
import model.entiteti.Student;

/**
 * Klasa predstavlja kontroler predmeta koje student može da sluša napravljen
 * po MVC šablonu.
 * 
 * @author Luka Miletić
 *
 */
public class SlobodniPredmetiController {
	
	/**
	 * Instanca kontrolera predmeta koje student može da sluša.
	 */
	private static SlobodniPredmetiController instance = null;
	
	/**
	 * Dobavlja instancu kontrolera predmeta koje student može da sluša po Singleton šablonu.
	 * 
	 * @return instanca kontrolera predmeta koje student može da sluša
	 */
	public static SlobodniPredmetiController getInstance() {
		if (instance == null) {
			instance = new SlobodniPredmetiController();
		}
		return instance;
	}
	
	/**
	 * Prazan konstruktor kontrolera predmeta koje student može da sluša.
	 */
	private SlobodniPredmetiController() {}
	
	/**
	 * Poziva metodu za inicijalizaciju liste predmeta koje je student položio u bazi
	 * predmeta koje student može da sluša za prosleđenog studenta.
	 * 
	 * @param s objekat studenta
	 */
	public void initSpisakPolozenih(Student s) {
		BazaSlobodnihPredmeta.getInstance().initPolozeni(s);
	}
	
	/**
	 * Poziva metodu za inicijalizaciju liste predmeta koje student nije položio u bazi
	 * predmeta koje student može da sluša.
	 * 
	 * @param s objekat studenta
	 */
	public void initSpisakNepolozenih(Student s) {
		BazaSlobodnihPredmeta.getInstance().initNepolozeni(s);
	}
	
	/**
	 * Poziva metodu za inicijalizaciju liste predmeta koje student može da sluša u bazi
	 * predmeta koje student može da sluša.
	 * 
	 * @param s objekat studenta
	 */
	public void initSlobodne(Student s) {
		BazaSlobodnihPredmeta.getInstance().initSlobodne(s);
	}
	
	/**
	 * Prosleđuje studenta metodi <code>popuniSlobodne</code> baze predmeta koje student može da sluša.
	 * 
	 * @param s objekat studenta
	 */
	public void popuniSlobodne(Student s) {
		BazaSlobodnihPredmeta.getInstance().popuniSlobodne(s);
	}
	
	/**
	 * Dobavlja listu predmeta koje student može da sluša.
	 * 
	 * @return lista studenata
	 */
	public List<Predmet> getPredmeti() {
		return BazaSlobodnihPredmeta.getInstance().getPredmeti();
	}
	
	/**
	 * Prosleđuje šifru predmeta metodi za brisanje predmeta koje student može da sluša
	 * iz baze predmeta koje student može da sluša.
	 * 
	 * @param sifra šifra predmeta
	 */
	public void izbrisiSlobodan(String sifra) {
		BazaSlobodnihPredmeta.getInstance().izbrisiSlobodanPredmet(sifra);
	}
	

}
