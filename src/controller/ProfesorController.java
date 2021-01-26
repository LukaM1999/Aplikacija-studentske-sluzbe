package controller;

import java.io.IOException;
import java.util.List;

import model.baze.BazaProfesora;
import model.entiteti.Profesor;
import view.MainFrame;

//Koriscen materijal sa vezbi

/**
 * Klasa predstavlja kontroler profesora napravljen
 * po MVC šablonu.
 * 
 * @author Mihajlo Kisić
 *
 */
public class ProfesorController {

	/**
	 * Instanca kontrolera profesora.
	 */
	private static ProfesorController instance = null;
		
	/**
	 * Dobavlja instancu kontrolera profesora po Singleton šablonu.
	 * 
	 * @return instanca kontrolera profesora
	 */
	public static ProfesorController getInstance() {
		if (instance == null) {
			instance = new ProfesorController();
		}
		return instance;
	}

	/**
	 * Prazan konstruktor kontrolera profesora.
	 */
	private ProfesorController() {}

	/**
	 * Prosleđuje profesora metodi za dodavanje profesora u listu svih profesora
	 * baze profesora. Takođe poziva metodu glavnog prozora za ažuriranje prikaza profesora.
	 * 
	 * @param p objekat profesora
	 */
	public void dodajProfesora(Profesor p) {
		BazaProfesora.getInstance().dodajProfesora(p);
		MainFrame.getInstance().azurirajProfesore("DODAT", -1);
	}

	/**
	 * Prosleđuje indeks reda izmenjenog profesora u tabeli profesora metodi
	 * glavnog prozora za ažuriranje prikaza profesora.
	 * 
	 * @param row red izmenjenog profesora u tabeli profesora
	 */
	public void izmeniProfesora(int row) {
		if (row < 0) {
			return;
		}
		MainFrame.getInstance().izmeniProfesora(row);
	}

	/**
	 * Prosleđuje profesora metodi za brisanje profesora iz baze profesora koji se nalazi u
	 * listi profesora na prosleđenom indeksu. Takođe prosleđuje indeks reda profesora koji je obrisan u tabeli profesora
	 * metodi glavnog prozora za ažuriranje prikaza profesora.
	 * 
	 * @param rowSelectedIndex indeks na kom se nalazi profesor kog hoćemo da
	 * obrišemo u listi profesora baze profesora
	 */
	public void izbrisiProfesora(int rowSelectedIndex) {
		if (rowSelectedIndex < 0) {
			return;
		}

		Profesor p = BazaProfesora.getInstance().getRow(rowSelectedIndex);
		BazaProfesora.getInstance().izbrisiProfesora(p.getBrLicneKarte());
		MainFrame.getInstance().azurirajProfesore("UKLONJEN", rowSelectedIndex);
	}

	/**
	 * Dobavlja listu profesora baze profesora.
	 * 
	 * @return lista profesora
	 */
	public List<Profesor> getProfesori() {
		return BazaProfesora.getInstance().getProfesori();
	}

	/**
	 * Dobavlja profesora koji se nalazi na prosleđenom indeksu u listi profesora baze profesora.
	 * 
	 * @param row indeks reda liste profesora u kom se nalazi profesora kog hoćemo da dobavimo
	 * @return objekat profesora
	 */
	public Profesor getProfesor(int row) {
		return BazaProfesora.getInstance().getRow(row);
	}

	/**
	 * Prosleđuje putanju datoteke metodi za serijalizaciju profesora preko <code>XStream</code> biblioteke.
	 * 
	 * @param putanja putanja na kojoj se nalazi datoteka u koju se serijalizuju profesori
	 */
	public void XstreamSerialization(String putanja) {
		try {
			BazaProfesora.getInstance().XstreamSerialization(putanja);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Prosleđuje putanju datoteke metodi za deserijalizaciju profesora preko <code>XStream</code> biblioteke.
	 * 
	 * @param putanja putanja na kojoj se nalazi datoteka iz koje se deserijalizuju profesori
	 */
	public void XstreamDeserialization(String putanja) {
		try {
			BazaProfesora.getInstance().XstreamDeserialization(putanja);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
