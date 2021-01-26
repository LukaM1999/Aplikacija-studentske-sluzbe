package controller;

import java.util.List;

import model.baze.BazaProfesorPredaje;
import model.entiteti.Predmet;
import model.entiteti.Profesor;

/**
 * Klasa predstavlja kontroler predmeta koje predaje profesor napravljen
 * po MVC šablonu. 
 * 
 * @author Mihajlo Kisić
 *
 */
public class ProfesorPredajeController {

	/**
	 * Instanca kontrolera predmeta koje predaje profesor.
	 */
	private static ProfesorPredajeController instance = null;
	
	/**
	 * Dobavlja instancu kontrolera predmeta koje predaje profesor po Singleton šablonu.
	 * 
	 * @return instanca kontrolera predmeta koje predaje profesor
	 */
	public static ProfesorPredajeController getInstance() {
		if (instance == null) {
			instance = new ProfesorPredajeController();
		}
		return instance;
	}
	
	/**
	 * Prazan konstruktor kontrolera predmeta koje predaje profesor.
	 */
	private ProfesorPredajeController () {}
	
	/**
	 * Poziva metodu za inicijalizaciju spiska predmeta koje predaje profesor u bazi
	 * predmeta koje predaje profesor za prosleđenog profesora.
	 * 
	 * @param p objekat profesora
	 */
	public void initPredajePredmet(Profesor p) {
		BazaProfesorPredaje.getInstance().initPredajePredmet(p);
	}
	
	/**
	 * Dobavlja listu predmeta koje predaje profesor iz baze predmeta koje predaje profesor.
	 * 
	 * @return lista predmeta
	 */
	public List<Predmet> getPredmeti(){
		return BazaProfesorPredaje.getInstance().getPredmeti();
	}
	
	/**
	 * Dobavlja predmet koji predaje profesor koji se nalazi na prosleđenom indeksu
	 * u listi predmeta koje predaje profesor baze predmeta koje predaje profesor.
	 * 
	 * @param row indeks predmeta u listi predmeta koje predaje profesor
	 * @return objekat predmeta
	 */
	public Predmet getPredmet(int row) { 
		return BazaProfesorPredaje.getInstance().getRow(row); 
	}
	 
	/**
	 * Prosleđuje predmet metodi koja dodaje predmet u listu
	 * predmeta koje predaje profesor baze predmeta koje predaje profesor.
	 * 
	 * @param p objekat predmeta
	 */
	public void dodajNepolozen(Predmet p) {
		BazaProfesorPredaje.getInstance().dodajPredmet(p);
	}
}
