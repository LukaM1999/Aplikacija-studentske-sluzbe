package controller;

import java.util.List;

import model.baze.BazaPredmetiBezProfesora;
import model.entiteti.Predmet;
import model.entiteti.Profesor;

/**
 * Klasa predstavlja kontroler predmeta koje ne predaje nijedan profesor, napravljen
 * po MVC šablonu.
 * 
 * @author Mihajlo Kisić
 *
 */
public class PredmetiBezProfesoraController {

	/**
	 * Instanca kontrolera predmeta koje ne predaje profesor.
	 */
	private static PredmetiBezProfesoraController instance = null;
	
	/**
	 * Dobavlja instancu kontrolera predmeta koje ne predaje nijedan profesor po Singleton šablonu.
	 * 
	 * @return instanca kontrolera predmeta koje ne predaje nijedan profesor
	 */
	public static PredmetiBezProfesoraController getInstance() {
		if (instance == null) {
			instance = new PredmetiBezProfesoraController();
		}
		return instance;
	}
	
	/**
	 * Prazan konstruktor kontrolera predmeta koje ne predaje nijedan profesor.
	 */
	private PredmetiBezProfesoraController() {}
	
	/**
	 * Poziva metodu za inicijalizaciju spiska predmeta koje ne predaje nijedan profesor u bazi
	 * predmeta koje ne predaje nijedan profesor za prosleđenog profesora.
	 * 
	 * @param p objekat profesora
	 */
	public void initSlobodne(Profesor p) {
		BazaPredmetiBezProfesora.getInstance().initSlobodne(p);
	}
	
	/**
	 * Poziva metodu za inicijalizaciju liste predmeta koje predaje prosleđeni profesor u bazi
	 * predmeta koje ne predaje nijedan profesor.
	 * 
	 * @param p objekat profesora
	 */
	public void initPredajePredmet(Profesor p) {
		BazaPredmetiBezProfesora.getInstance().initPredajePredmet(p);
	}
	
	/**
	 * Prosleđuje profesora metodi <code>popuniPredmetiBezProfesora</code> baze predmeta koje ne predaje nijedan profesor.
	 * 
	 * @param p objekat profesora
	 */
	public void popuniPredmetiBezProfesora(Profesor p) {
		BazaPredmetiBezProfesora.getInstance().popuniPredmetiBezProfesora(p);
	}
	
	/**
	 * Dobavlja listu predmeta iz baze predmeta koje ne predaje nijedan profesor.
	 * 
	 * @return lista predmeta
	 */
	public List<Predmet> getPredmeti() {
		return BazaPredmetiBezProfesora.getInstance().getPredmeti();
	}
	
	/**
	 * Prosleđuje šifru predmeta metodi za brisanje predmeta koje ne predaje nijedan profesor
	 * iz baze predmeta koje ne predaje nijedan profesor.
	 * 
	 * @param sifra šifra predmeta
	 */
	public void izbrisiSlobodan(String sifra) {
		BazaPredmetiBezProfesora.getInstance().izbrisiSlobodanPredmet(sifra);
	}
}
