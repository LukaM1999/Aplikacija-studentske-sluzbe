package model.baze;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.ProfesorController;
import model.entiteti.Profesor;
import model.entiteti.Profesor.Titula;
import model.entiteti.Profesor.Zvanje;


/**
 * Klasa koja predstavlja bazu profesora koji mogu da predaju predmete.
 * 
 * @author Luka Miletić
 *
 */
public class BazaSlobodnihProfesora {
	
	/**
	 * Instanca baze profesora koji mogu da predaju predmete.
	 */
	private static BazaSlobodnihProfesora instance = null;

	/**
	 * Dobavlja instancu baze profesora koji mogu da predaju predmete po Singleton šablonu.
	 * 
	 * @return instanca baze profesora koji mogu da predaju predmete
	 */
	public static BazaSlobodnihProfesora getInstance() {
		if (instance == null) {
			instance = new BazaSlobodnihProfesora();
		}
		return instance;
	}

	/**
	 * Lista profesora koji mogu da predaju predmete.
	 */
	private List<Profesor> profesori;
	
	/**
	 * Lista kolona tabele profesora koji mogu da predaju predmete.
	 */
	private List<String> kolone;

	/**
	 * Kreira praznu listu profesora koji mogu da predaju predmete i dodaje
	 * jednu kolonu bez naziva zbog adekvatnog prikaza tabele profesora koji mogu da predaju predmete.
	 */
	private BazaSlobodnihProfesora() {
		
		init();
		
		this.kolone = new ArrayList<String>();
		this.kolone.add("");
	}

	/**
	 * Inicijalizuje listu profesora koji mogu da predaju predmete listom svih profesora.
	 */
	private void init() {
		this.profesori = ProfesorController.getInstance().getProfesori();
	}

	/**
	 * Dobavlja listu profesora koji mogu da predaju predmete.
	 * 
	 * @return lista profesora koji mogu da predaju predmete
	 */
	public List<Profesor> getProfesori() {
		return profesori;
	}

	/**
	 * Postavlja polje liste profesora koji mogu da predaju predmete na prosleđenu listu profesora.
	 * 
	 * @param profesori lista profesora
	 */
	public void setProfesori(List<Profesor> profesori) {
		this.profesori = profesori;
	}

	/**
	 * Dobavlja broj kolona tabele profesora koji mogu da predaju predmete.
	 * 
	 * @return predefinisan broj kolona tabele profesora koji mogu da predaju predmete
	 */
	public int getColumnCount() {
		return 1;
	}

	/**
	 * Vraća naziv kolone tabele profesora koji mogu da predaju predmete čiji indeks je prosleđen.
	 * 
	 * @param index indeks kolone tabele profesora koji mogu da predaju predmete
	 * @return naziv kolone iz liste kolona sa prosleđenim indeksom
	 */
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}

	/**
	 * Dobavlja prosleđeni red iz liste profesora koji mogu da predaju predmete odnosno profesora koji
	 * se u listi nalazi na indeksu koji je prosleđen.
	 * 
	 * @param rowIndex indeks reda liste profesora koji mogu da predaju predmete
	 * @return predmet iz liste profesora koji mogu da predaju predmete na prosleđenom indeksu
	 */
	public Profesor getRow(int rowIndex) {
		return this.profesori.get(rowIndex);
	}

	/**
	 * Dobavlja vrednost koja se nalazi na prosleđenom indeksu reda i kolone u tabeli
	 * profesora koji mogu da predaju predmete u vidu <code>String</code>-a.
	 * 
	 * @param row indeks reda u kom se nalazi željena vrednost
	 * @param column indeks kolone u kojoj se nalazi željena vrednost
	 * @return vrednost koja se nalazi u prosleđenoj koloni i redu
	 */
	public String getValueAt(int row, int column) {
		Profesor profesor = this.profesori.get(row);
		switch (column) {
		case 0:
			return profesor.getIme() + " " + profesor.getPrezime();
		default:
			return null;
		}
	}

	
	/**
	 * Pravi objekat novog profesora pomoću prosleđenih parametara i dodaje ga u listu
	 * profesora koji mogu da predaju predmete.
	 * 
	 * @param ime ime profesora
	 * @param prezime prezime profesora
	 * @param datum datum rođenja profesora
	 * @param adresa adresa stanovanja profesora
	 * @param telefon kontakt telefon profesora
	 * @param email email adresa profesora
	 * @param kancelarija adresa kancelarije profesora
	 * @param licna broj lične karte profesora
	 * @param titula titula profesora
	 * @param zvanje zvanje profesora
	 */
	public void dodajProfesora(String ime, String prezime, LocalDate datum, String adresa, String telefon, String email,
			String kancelarija, String licna, Titula titula, Zvanje zvanje) {
		this.profesori.add(new Profesor(ime, prezime, datum, adresa, telefon, email, kancelarija, licna, titula, zvanje));
	}

	/**
	 * Pravi objekat novog profesora pomoću prosleđenog objekta profesora i dodaje ga u listu
	 * profesora koji mogu da predaju predmete.
	 * 
	 * @param p objekat profesora koji se dodaje u listu profesora koji mogu da predaju predmete
	 */
	public void dodajProfesora(Profesor p) {
		this.profesori.add(new Profesor(p));
	}

	/**
	 * Uklanja profesora iz liste profesora koji mogu da predaju predmete čiji broj
	 * lične karte se poklapa sa prosleđenim brojem lične karte.
	 * 
	 * @param id broj lične karte profesora koji se briše
	 */
	public void izbrisiProfesora(String id) {
		for (Profesor p : profesori) {
			if (p.getBrLicneKarte() == id) {
				profesori.remove(p);
				break;
			}
		}
	}

}
