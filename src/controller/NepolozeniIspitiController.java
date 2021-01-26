package controller;

import java.util.List;

import model.baze.BazaNepolozenihIspita;
import model.entiteti.Predmet;
import model.entiteti.Student;

/**
 * Klasa predstavlja kontroler nepoloženih ispita napravljen
 * po MVC šablonu.
 * 
 * @author Mihajlo Kisić
 *
 */
public class NepolozeniIspitiController {

	/**
	 * Instanca kontrolera nepoloženih ispita.
	 */
	private static NepolozeniIspitiController instance = null;
	
	/**
	 * Dobavlja instancu kontrolera nepoloženih ispita po Singleton šablonu.
	 * 
	 * @return instanca kontrolera nepoloženih ispita
	 */
	public static NepolozeniIspitiController getInstance() {
		if (instance == null) {
			instance = new NepolozeniIspitiController();
		}
		return instance;
	}
	
	/**
	 * Prazan konstruktor kontrolera nepoloženih ispita.
	 */
	private NepolozeniIspitiController() {}
	
	/**
	 * Poziva metodu za inicijalizaciju spiska nepoloženih ispita u bazi
	 * nepoloženih ispita za prosleđenog studenta.
	 * 
	 * @param s objekat studenta
	 */
	public void initSpisakNepolozenih(Student s) {
		BazaNepolozenihIspita.getInstance().initSpisakNepolozenih(s);
	}
	
	/**
	 * Dobavlja listu predmeta iz baze nepoloženih ispita.
	 * 
	 * @return lista predmeta
	 */
	public List<Predmet> getPredmeti(){
		return BazaNepolozenihIspita.getInstance().getPredmeti();
	}
	
	/**
	 * Dobavlja predmet koji se nalazi na prosleđenom indeksu
	 * u listi predmeta baze nepoloženih ispita.
	 * 
	 * @param row indeks predmeta u listi predmeta
	 * @return objekat predmeta
	 */
	public Predmet getPredmet(int row) {
		return BazaNepolozenihIspita.getInstance().getRow(row);
	}
	
	/**
	 * Prosleđuje predmet metodi koja dodaje predmet u listu
	 * predmeta baze nepoloženih ispita.
	 * 
	 * @param p objekat predmeta
	 */
	public void dodajNepolozen(Predmet p) {
		BazaNepolozenihIspita.getInstance().dodajNepolozen(p);
	}
}
