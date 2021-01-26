package controller;

import java.io.IOException;
import java.util.List;

import model.baze.BazaPredmeta;
import model.entiteti.Predmet;
import view.MainFrame;

//Koriscen materijal sa vezbi
/**
 * Klasa predstavlja kontroler predmeta napravljen
 * po MVC šablonu.
 * 
 * @author Mihajlo Kisić
 *
 */
public class PredmetController {
	
	/**
	 * Instanca kontrolera predmeta.
	 */
	private static PredmetController instance = null;
	
	/**
	 * Dobavlja instancu kontrolera predmeta po Singleton šablonu.
	 * 
	 * @return instanca kontrolera predmeta
	 */
	public static PredmetController getInstance() {
		if (instance == null) {
			instance = new PredmetController();
		}
		return instance;
	}
	
	/**
	 * Prazan konstruktor kontrolera predmeta.
	 */
	private PredmetController() {}
	
	/**
	 * Prosleđuje predmet metodi za dodavanje predmeta u listu svih predmeta
	 * baze predmeta. Takođe poziva metodu glavnog prozora za ažuriranje prikaza predmeta.
	 * 
	 * @param p objekat predmeta
	 */
	public void dodajPredmet(Predmet p) {
		// izmena modela
		BazaPredmeta.getInstance().dodajPredmet(p);
		// azuriranje prikaza
		MainFrame.getInstance().azurirajPredmete("DODAT", -1);
	}
	
	/**
	 * Prosleđuje indeks reda izmenjenog predmeta u tabeli predmeta metodi
	 * glavnog prozora za ažuriranje prikaza predmeta.
	 * 
	 * @param row red izmenjenog predmeta u tabeli predmeta
	 */
	public void izmeniPredmet(int row) {
		MainFrame.getInstance().izmeniPredmet(row);
	}
	
	/**
	 * Prosleđuje predmet metodi za brisanje predmeta iz baze predmeta koji se nalazi u
	 * listi predmeta na prosleđenom indeksu. Takođe prosleđuje indeks reda predmeta koji je obrisan u tabeli predmeta
	 * metodi glavnog prozora za ažuriranje prikaza predmeta.
	 * 
	 * @param rowSelectedIndex indeks na kom se nalazi predmet koji hoćemo da
	 * obrišemo u listi predmeta baze predmeta
	 */
	public void izbrisiPredmet(int rowSelectedIndex) {
    	if (rowSelectedIndex < 0) {
			return;
		}
    	// izmena modela
    	Predmet p = BazaPredmeta.getInstance().getRow(rowSelectedIndex);
		BazaPredmeta.getInstance().izbrisiPredmet(p.getSifra());
		// azuriranje prikaza
		MainFrame.getInstance().azurirajPredmete("UKLONJEN", rowSelectedIndex);
    }
	
	/**
	 * Dobavlja listu predmeta baze predmeta.
	 * 
	 * @return lista predmeta
	 */
	public List<Predmet> getPredmeti(){
		return BazaPredmeta.getInstance().getPredmeti();
	}
	
	/**
	 * Dobavlja predmet koji se nalazi na prosleđenom indeksu u listi predmeta baze predmeta.
	 * 
	 * @param row indeks reda liste predmeta u kom se nalazi predmet koji hoćemo da dobavimo
	 * @return objekat predmeta
	 */
	public Predmet getPredmet(int row) {
		return BazaPredmeta.getInstance().getRow(row);
	}
	
	/**
	 * Prosleđuje predmet metodi <code>dodajPolozili</code> baze predmeta.
	 * 
	 * @param p objekat predmeta
	 */
	public void popuniListuPolozili (Predmet p) {
		BazaPredmeta.getInstance().dodajPolozili(p.getSifra());
	}
	
	/**
	 * Prosleđuje putanju datoteke metodi za serijalizaciju predmeta preko <code>XStream</code> biblioteke.
	 * 
	 * @param putanja putanja na kojoj se nalazi datoteka u koju se serijalizuju predmeti
	 */
	public void XstreamSerialization(String putanja) {
		try {
			BazaPredmeta.getInstance().XstreamSerialization(putanja);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Prosleđuje putanju datoteke metodi za deserijalizaciju predmeta preko <code>XStream</code> biblioteke.
	 * 
	 * @param putanja putanja na kojoj se nalazi datoteka iz koje se deserijalizuju predmeti
	 */
	public void XstreamDeserialization(String putanja) {
		try {
			BazaPredmeta.getInstance().XstreamDeserialization(putanja);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
