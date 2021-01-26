
package model.entiteti;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Klasa predstavlja model entiteta profesora.
 * 
 * @author Mihajlo Kisić
 *
 */
public class Profesor {
	
	/**
	 * Enumeracija titula profesora.
	 * 
	 * @author Mihjalo Kisić
	 *
	 */
	public enum Titula {BSc, MSc, mr, dr, prof_dr}

	/**
	 * Enumeracija zvanja profesora.
	 * 
	 * @author Mihajlo Kisić
	 *
	 */
	public enum Zvanje {saradnik_u_nastavi, asistent,
		asistent_sa_doktoratom, docent,
		vanredni_profesor, redovni_profesor, profesor_emeritus}
	
	/**
	 * Ime profesora.
	 */
	private String ime;
	
	/**
	 * Prezime profesora.
	 */
	private String prezime;
	
	/**
	 * Datum rođenja profesora.
	 */
	private LocalDate datumRodjenja;
	
	/**
	 * Adresa stanovanja profesora.
	 */
	private String adresa;
	
	/**
	 * Kontakt telefon profesora.
	 */
	private String telefon;
	
	/**
	 * Email adresa profesora.
	 */
	private String email;
	
	/**
	 * Adresa kancelarije profesora.
	 */
	private String kancelarija;
	
	/**
	 * Broj lične karte profesora.
	 */
	private String brLicneKarte;
	
	/**
	 * Titula profesora.
	 */
	private Titula titula;
	
	/**
	 * Zvanje profesora.
	 */
	private Zvanje zvanje;
	
	/**
	 * Lista predmeta koje profesor predaje.
	 */
	private ArrayList<Predmet> predajePredmet = new ArrayList<Predmet>();
	
	/**
	 * Lista predmeta koje ne predaje nijedan profesor.
	 */
	private ArrayList<Predmet> predmetiBezProfesora = new ArrayList<Predmet>();
	
	
	/**
	 * Parametrizovani konstruktor profesora.
	 * 
	 * @param ime ime profesora
	 * @param prezime prezime profesora
	 * @param datumRodjenja datum rođenja profesora
	 * @param adresa adresa stanovanja profesora
	 * @param telefon kontakt telefon profesora
	 * @param email email adresa profesora
	 * @param kancelarija adresa kancelarije profesora
	 * @param brLicneKarte broj lične karte profesora
	 * @param titula titula profesora
	 * @param zvanje zvanje profesora
	 */
	public Profesor (String ime, String prezime, LocalDate datumRodjenja,
				   String adresa, String telefon, String email, 
				   String kancelarija, String brLicneKarte,
				   Titula titula, Zvanje zvanje) {
		setIme(ime);
		setPrezime(prezime);
		setDatumRodjenja(datumRodjenja);
		setAdresa(adresa);
		setTelefon(telefon);
		setEmail(email);
		setKancelarija(kancelarija);
		setBrLicneKarte(brLicneKarte);
		setTitula(titula);
		setZvanje(zvanje);
	}
	
	/**
	 * Konstruktor kopije profesora.
	 * 
	 * @param p objekat profesora
	 */
	public Profesor (Profesor p) {
		prezime = p.prezime;
		ime = p.ime;
		datumRodjenja = p.datumRodjenja;
		adresa = p.adresa;
		telefon = p.telefon;
		email = p.email;
		kancelarija = p.kancelarija;
		brLicneKarte = p.brLicneKarte;
		titula = p.titula;
		zvanje = p.zvanje;
		predajePredmet = p.predajePredmet;
	}
	
	/**
	 * Redukovani konstruktor sa parametrima profesora.
	 * 
	 * @param ime2 ime profesora
	 * @param prezime2 prezime profesora
	 * @param titula2 titula profesora
	 * @param zvanje2 zvanje profesora
	 */
	public Profesor(String ime2, String prezime2, Titula titula2, Zvanje zvanje2) {
		this.ime = ime2;
		this.prezime = prezime2;
		this.titula = titula2;
		this.zvanje = zvanje2;
	}

	/**
	 * Dobavlja prezime profesora.
	 * 
	 * @return prezime profesora
	 */
	public String getPrezime() {
		return prezime;
	}
	
	/**
	 * Postavlja prezime profesora na prosleđeno prezime.
	 * 
	 * @param prezime prezime profesora
	 */
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	/**
	 * Dobavlja ime profesora.
	 * 
	 * @return ime profesora
	 */
	public String getIme() {
		return ime;
	}
	
	/**
	 * Postavlja ime profesora na prosleđeno ime.
	 * 
	 * @param ime ime profesora
	 */
	public void setIme(String ime) {
		this.ime = ime;
	}
	
	/**
	 * Dobavlja datum rođenja profesora.
	 * 
	 * @return datum rođenja profesora
	 */
	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}
	
	/**
	 * Postavlja datum rođenja profesora na prosleđeni datum.
	 * 
	 * @param datumRodjenja2 datum rođenja profesora
	 */
	public void setDatumRodjenja(LocalDate datumRodjenja2) {
		this.datumRodjenja = datumRodjenja2;
	}
	
	/**
	 * Dobavlja adresu stanovanja profesora.
	 * 
	 * @return adresa stanovanja profesora
	 */
	public String getAdresa() {
		return adresa;
	}
	
	/**
	 * Postavlja adresu stanovanja profesora na prosleđenu adresu.
	 * 
	 * @param adresa adresa stanovanja profesora
	 */
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	
	/**
	 * Dobavlja kontakt telefon profesora.
	 * 
	 * @return kontakt telefon profesora
	 */
	public String getTelefon() {
		return telefon;
	}
	
	/**
	 * Postavlja kontakt telefon profesora na prosleđeni broj telefona.
	 * 
	 * @param telefon kontakt telefon profesora
	 */
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	/**
	 * Dobavlja email adresu profesora.
	 * 
	 * @return email adresa profesora
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Postavlja email adresu profesora na prosleđenu email adresu.
	 * 
	 * @param email email adresa profesora
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Dobavlja broj lične karte profesora.
	 * 
	 * @return broj lične karte profesora
	 */
	public String getBrLicneKarte() {
		return brLicneKarte;
	}
	
	/**
	 * Postavlja broj lične karte profesora na prosleđeni broj lične karte.
	 * 
	 * @param brLicneKarte broj lične karte profesora
	 */
	public void setBrLicneKarte(String brLicneKarte) {
		this.brLicneKarte = brLicneKarte;
	}
	
	/**
	 * Dobavlja titulu profesora.
	 * 
	 * @return titula profesora
	 */
	public Titula getTitula() {
		return titula;
	}
	
	/**
	 * Postavlja titulu profesora na prosleđenu titulu.
	 * 
	 * @param titula2 titula profesora.
	 */
	public void setTitula(Titula titula2) {
		this.titula = titula2;
	}
	
	/**
	 * Dobavlja zvanje profesora.
	 * 
	 * @return zvanje profesora
	 */
	public Zvanje getZvanje() {
		return zvanje;
	}
	
	/**
	 * Postavlja zvanje profesora na prosleđeno zvanje.
	 * 
	 * @param zvanje zvanje profesora
	 */
	public void setZvanje(Zvanje zvanje) {
		this.zvanje = zvanje;
	}
	
	/**
	 * Dobavlja listu predmeta koje predaje profesor.
	 * 
	 * @return lista predmeta koje predaje profesor
	 */
	public ArrayList<Predmet> getPredajePredmet() {
		return predajePredmet;
	}
	
	/**
	 * Postavlja listu predmeta koje predaje profesor na proseđenu listu.
	 * 
	 * @param predajePredmet lista predmeta koje predaje profesor
	 */
	public void setPredajePredmet(ArrayList<Predmet> predajePredmet) {
		this.predajePredmet = predajePredmet;
	}

	/**
	 * Dobavlja adresu kancelarije profesora.
	 * 
	 * @return adresa kancelarije profesora
	 */
	public String getKancelarija() {
		return kancelarija;
	}

	/**
	 * Postavlja adresu kancelarije na prosleđenu vrednost adrese kancelarije.
	 * 
	 * @param kancelarija adresa kancelarije profesora
	 */
	public void setKancelarija(String kancelarija) {
		this.kancelarija = kancelarija;
	}
	
	/**
	 * Dodaje prosleđeni predmet u listu predmeta koje predaje profesor.
	 * 
	 * @param p objekat predmeta koji želimo da dodamo
	 */
	public void dodajPredajePredmet(Predmet p) {
		this.predajePredmet.add(p);
	}
	
	/**
	 * Uklanja predmet iz liste predmeta koje predaje profesor koji se nalazi
	 * na prosleđenom indeksu u toj listi.
	 * 
	 * @param index indeks predmeta u listi predmeta koje predaje profesor
	 */
	public void izbrisiPredajePredmet(int index) {
		this.predajePredmet.remove(index);
	}
	
	/**
	 * Uklanja predmet iz liste predmeta koje predaje profesor ako se njegova šifra
	 * poklapa sa prosleđenom šifrom predmeta.
	 * 
	 * @param sifra šifra predmeta koji želimo da uklonimo
	 */
	public void izbrisiPredajePredmet(String sifra) {
		for(int i = 0; i < predajePredmet.size(); i++) {
			if(predajePredmet.get(i).getSifra().equals(sifra)) {
				this.predajePredmet.remove(i);
			}
		}
	}

	/**
	 * Dobavlja listu predmeta koje ne predaje nijedan profesor.
	 * 
	 * @return lista predmeta koje ne predaje nijedan profesor
	 */
	public ArrayList<Predmet> getPredmetiBezProfesora() {
		return predmetiBezProfesora;
	}

	/**
	 * Postavlja listu predmeta koje ne predaje profesor na prosleđenu listu predmeta koje
	 * ne predaje nijedan profesor.
	 * 
	 * @param predmetiBezProfesora lista predmeta koje ne predaje nijedan profesor
	 */
	public void setPredmetiBezProfesora(ArrayList<Predmet> predmetiBezProfesora) {
		this.predmetiBezProfesora = predmetiBezProfesora;
	}
	
	/**
	 * Dodaje prosleđeni predmet u listu predmeta koje ne predaje nijedan profesor.
	 * 
	 * @param p objekat predmeta koji želimo da dodamo
	 */
	public void dodajSlobodan(Predmet p) {
		this.predmetiBezProfesora.add(p);
	}
	
	/**
	 * Uklanja predmet iz liste predmeta koje ne predaje nijedan profesor koji se nalazi
	 * u toj listi na prosleđenom indeksu.
	 * 
	 * @param index indeks na kom se nalazi predmet koji hoćemo da uklonimo iz 
	 * liste predmeta koje ne predaje nijedan profesor
	 */
	public void izbrisiSlobodan(int index) {
		this.predmetiBezProfesora.remove(index);
	}
	
	/**
	 * Uklanja predmet iz liste predmeta koje ne predaje nijedan profesor ako je objekat tog predmeta
	 * jednak objektu prosleđenog predmeta.
	 * 
	 * @param p objekat predmeta koji želimo da uklonimo
	 */
	public void izbrisiSlobodan(Predmet p) {
		this.predmetiBezProfesora.remove(p);
	}
	
}
