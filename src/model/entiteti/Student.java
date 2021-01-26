package model.entiteti;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;


//Koriscen materijal sa vezbi
/**
 * Klasa koja predstavlja model entiteta studenta
 * 
 * @author Luka Miletić
 *
 */
public class Student {
	
	/**
	 * Enumeracija statusa studenta.
	 * 
	 * @author Luka Miletić
	 *
	 */
	public enum Status {B, S}

	/**
	 * Ime studenta.
	 */
	private String ime;
	
	/**
	 * Prezime studenta.
	 */
	private String prezime;
	
	/**
	 * Datum rođenja studenta.
	 */
	private LocalDate datumRodjenja;
	
	/**
	 * Adresa stanovanja studenta.
	 */
	private String adresa;
	
	/**
	 * Kontakt telefon studenta.
	 */
	private String telefon;
	
	/**
	 * Email adresa studenta.
	 */
	private String email;
	
	/**
	 * Broj indeksa studenta.
	 */
	private String brIndeksa;
	
	/**
	 * Godina upisa studenta.
	 */
	private int godinaUpisa;
	
	/**
	 * Trenutna godina studija studenta.
	 */
	private int trenutnaGodina;
	
	/**
	 * Status studenta (budžet/samofinansiranje).
	 */
	private Status statusStudenta;
	
	/**
	 * Prosečna ocena studenta.
	 */
	private double prosecnaOcena = 0;
	
	/**
	 * Spisak položenih ispita studenta.
	 */
	private List<Ocena> spisakPolozenih = new ArrayList<Ocena>();
	
	/**
	 * Spisak nepoloženih ispita studenta.
	 */
	private List<Predmet> spisakNepolozenih = new ArrayList<Predmet>();
	
	/**
	 * Spisak predmeta koje student može da sluša.
	 */
	private List<Predmet> spisakSlobodnih = new ArrayList<Predmet>();

	/**
	 * Parametrizovani konstruktor studenta.
	 * 
	 * @param ime ime studenta
	 * @param prezime prezime studenta
	 * @param datumRodjenja datum rođenja studenta
	 * @param adresa adresa stanovanja studenta
	 * @param telefon kontakt telefon studenta
	 * @param email email adresa stuudenta
	 * @param brIndeksa indeks studenta
	 * @param godinaUpisa godina upisa studenta
	 * @param trenutnaGodina trenutna godina studija studenta
	 * @param statusStudenta status studenta u trenutnoj godini studija (budžet/samofinansiranje)
	 * @param prosecnaOcena prosečna ocena studenta
	 * @param spisakPolozenih spisak položenih ispita studenta
	 * @param spisakNepolozenih spisak nepoloženih ispita studenta
	 */
	public Student(String ime, String prezime, LocalDate datumRodjenja,
				   String adresa, String telefon, String email, 
				   String brIndeksa, int godinaUpisa,
				   int trenutnaGodina, Status statusStudenta,
				   float prosecnaOcena,
				   List<Ocena> spisakPolozenih,
				   List<Predmet> spisakNepolozenih) {
		setIme(ime);
		setPrezime(prezime);
		setDatumRodjenja(datumRodjenja);
		setAdresa(adresa);
		setTelefon(telefon);
		setEmail(email);
		setBrIndeksa(brIndeksa);
		setGodinaUpisa(godinaUpisa);
		setTrenutnaGodina(trenutnaGodina);
		setStatusStudenta(statusStudenta);
		setProsecnaOcena(prosecnaOcena);
		setSpisakPolozenih(spisakPolozenih);
		setSpisakNepolozenih(spisakNepolozenih);
	}
	
	/**
	 * Konstruktor kopije studenta.
	 * 
	 * @param s objekat studenta
	 */
	public Student(Student s) {
		ime = s.ime;
		prezime = s.prezime;
		datumRodjenja = s.datumRodjenja;
		adresa = s.adresa;
		telefon = s.telefon;
		email = s.email;
		brIndeksa = s.brIndeksa;
		godinaUpisa = s.godinaUpisa;
		trenutnaGodina = s.trenutnaGodina;
		statusStudenta = s.statusStudenta;
		prosecnaOcena = s.prosecnaOcena;
		spisakPolozenih = s.spisakPolozenih;
		spisakNepolozenih = s.spisakNepolozenih;
	}
	
	/**
	 * Redukovani parametrizovani konstruktor studenta.
	 * 
	 * @param ime ime studenta
	 * @param prezime prezime studenta
	 * @param datumRodjenja datum rođenja studenta
	 * @param adresa adresa stanovanja studenta
	 * @param telefon kontakt telefon studenta
	 * @param email email adresa stuudenta
	 * @param brIndeksa indeks studenta
	 * @param godinaUpisa godina upisa studenta
	 * @param trenutnaGodina trenutna godina studija studenta
	 * @param statusStudenta status studenta u trenutnoj godini studija (budžet/samofinansiranje)
	 */
	public Student(String ime, String prezime, LocalDate datumRodjenja,
			   String adresa, String telefon, String email, 
			   String brIndeksa, int godinaUpisa,
			   int trenutnaGodina, Status statusStudenta) {
		
	setIme(ime);
	setPrezime(prezime);
	setDatumRodjenja(datumRodjenja);
	setAdresa(adresa);
	setTelefon(telefon);
	setEmail(email);
	setBrIndeksa(brIndeksa);
	setGodinaUpisa(godinaUpisa);
	setTrenutnaGodina(trenutnaGodina);
	setStatusStudenta(statusStudenta);
}
	
	/**
	 * Dodaje prosleđeni predmet u listu nepoloženih ispita studenta.
	 * 
	 * @param p objekat predmeta
	 */
	public void dodajNepolozen(Predmet p) {
		this.spisakNepolozenih.add(p);
	}
	
	public void izbrisiNepolozen(int index) {
		this.spisakNepolozenih.remove(index);
	}
	
	/**
	 * Dodaje prosleđenu ocenu u listu položenih ispita studenta.
	 * 
	 * @param o objekat ocene
	 */
	public void dodajPolozen(Ocena o) {
		this.spisakPolozenih.add(o);
	}
	
	/**
	 * Pravi novu ocenu na osnovu prosleđenih parametara i dodaje je u
	 * listu položenih ispita studenta.
	 * 
	 * @param s objekat studenta kome dodajemo ocenu
	 * @param p objekat predmeta kog je položio student
	 * @param vrednostOcene brojčana vrednost ocene
	 * @param datumPolaganja datum kada je student položio ispit
	 */
	public void dodajPolozen(Student s, Predmet p, int vrednostOcene, LocalDate datumPolaganja) {
		this.spisakPolozenih.add(new Ocena(s, p, vrednostOcene, datumPolaganja));
	}
	
	/**
	 * Briše ocenu koja se nalazi na prosleđenom indeksu
	 * u listi položenih ispita.
	 * 
	 * @param index indeks ocene u listi položenih ispita
	 */
	public void ponistiOcenu(int index) {
		this.spisakPolozenih.remove(index);
	}
	
	/**
	 * Dodaje prosleđeni predmet u listu predmeta koje student može da sluša.
	 * 
	 * @param p objekat predmeta
	 */
	public void dodajSlobodan(Predmet p) {
		this.spisakSlobodnih.add(p);
	}
	
	/**
	 * Briše predmet koji se nalazi na prosleđenom indeksu
	 * u listi predmeta koje student može da sluša.
	 * 
	 * @param index indeks predmeta u listi predmeta koje student može da sluša
	 */
	public void izbrisiSlobodan(int index) {
		this.spisakSlobodnih.remove(index);
	}
	
	/**
	 * Postavlja vrednost liste predmeta koje student može da sluša
	 * na vrednost prosleđene liste predmeta.
	 * 
	 * @param slobodni lista predmeta koje student može da sluša
	 */
	public void setSlobodne(List<Predmet> slobodni) {
		this.spisakSlobodnih = slobodni;
	}
	
	/**
	 * Dobavlja listu predmeta koje student može da sluša.
	 * 
	 * @return lista predmeta koje student može da sluša
	 */
	public List<Predmet> getSlobodne() {
		return this.spisakSlobodnih;
	}
	
	/**
	 * Ispisuje na konzolu indeks studenta za svaki njegov
	 * položeni predmet.
	 */
	public void ispisPolozenih() {
		for (Ocena o: spisakPolozenih) {
			System.out.println(o.getStudent().getBrIndeksa());
		}
	}
	
	/**
	 * Dobavlja prosečnu ocenu studenta.
	 * 
	 * @return prosečna ocena studenta
	 */
	public double getProsecnaOcena() {
		return prosecnaOcena;
	}
	
	/**
	 * Računa prosečnu ocenu koristeći brojčane vrednosti
	 * ocena iz liste položenih ispita i zaokružuje je na
	 * dve decimale.
	 * 
	 * @param polozeni spisak ocena studenta
	 * @return prosečna ocena studenta zaokružena na dve decimale
	 */
	public double izracunajProsek(List<Ocena> polozeni) {
		double ukupno = 0;
		int brOcena = 0;
		for(Ocena o: polozeni) {
			ukupno += o.getVrednostOcene();
			brOcena++;
		}
		
		if(Double.isNaN(ukupno/brOcena)) {
			return 0;
		}
		//REFERENCE: https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
		return new BigDecimal(ukupno/brOcena).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	/**
	 * Postavlja prosečnu ocenu studenta na prosleđenu vrednost prosečne ocene.
	 * 
	 * @param prosecnaOcena prosečna ocena studenta
	 */
	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}
	
	/**
	 * Dobavlja spisak položenih ispita studenta.
	 * 
	 * @return lista ocena studenta
	 */
	public List<Ocena> getSpisakPolozenih() {
		return spisakPolozenih;
	}

	/**
	 * Postavlja vrednost spiska položenih ispita na
	 * prosleđenu vrednost liste ocena.
	 * 
	 * @param spisakPolozenih spisak položenih ispita studenta
	 */
	public void setSpisakPolozenih(List<Ocena> spisakPolozenih) {
		this.spisakPolozenih = spisakPolozenih;
	}

	/**
	 * Dobavlja spisak nepoloženih ispita studenta.
	 * 
	 * @return lista nepoloženih predmeta studenta
	 */
	public List<Predmet> getSpisakNepolozenih() {
		return spisakNepolozenih;
	}

	/**
	 * Postavlja vrednost spiska nepoloženih ispita na prosleđenu
	 * vrednost liste predmeta.
	 * 
	 * @param spisakNepolozenih lista nepoloženih predmeta studenta
	 */
	public void setSpisakNepolozenih(List<Predmet> spisakNepolozenih) {
		this.spisakNepolozenih = spisakNepolozenih;
	}

	/**
	 * Dobavlja ime studenta.
	 * 
	 * @return ime studenta
	 */
	public String getIme() {
		return ime;
	}

	/**
	 * Postavlja ime studenta na prosleđeno ime.
	 * 
	 * @param ime ime studenta
	 */
	public void setIme(String ime) {
		if (ime == null) {
			throw new NullPointerException();
		}
		ime = ime.trim();
		if (ime.isEmpty()) {
			throw new IllegalArgumentException("Ime mora biti uneto!");
		}
		this.ime = ime;
	}

	/**
	 * Dobavlja datum rođenja studenta.
	 * 
	 * @return datum rođenja studenta
	 */
	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}

	/**
	 * Postavlja datum rođenja studenta na prosleđeni datum rođenja.
	 * 
	 * @param datumRodjenja datum rođenja studenta
	 */
	public void setDatumRodjenja(LocalDate datumRodjenja) {
		if (datumRodjenja == null) {
			throw new NullPointerException();
		}
		if (datumRodjenja.toString().isEmpty()) {
			throw new IllegalArgumentException("Datum rođenja mora biti unet!");
		}
		this.datumRodjenja = datumRodjenja;
	}

	/**
	 * Dobavlja adresu stanovanja studenta.
	 * 
	 * @return adresa stanovanja studenta
	 */
	public String getAdresa() {
		return adresa;
	}

	/**
	 * Postavlja adresu stanovanja studenta na prosleđenu adresu.
	 * 
	 * @param adresa adresa stanovanja studenta
	 */
	public void setAdresa(String adresa) {
		if (adresa == null) {
			throw new NullPointerException();
		}
		adresa = adresa.trim();
		if (adresa.isEmpty()) {
			throw new IllegalArgumentException("Adresa mora biti uneta!");
		}
		this.adresa = adresa;
	}

	/**
	 * Dobavlja kontakt telefon studenta.
	 * 
	 * @return kontakt telefon studenta
	 */
	public String getTelefon() {
		return telefon;
	}

	/**
	 * Postavlja broj telefona studenta na prosleđeni broj telefona.
	 * 
	 * @param telefon kontakt telefon studenta
	 */
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	/**
	 * Dobavlja email adresu studenta.
	 * 
	 * @return email adresa studenta
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Postavlja email adresu studenta na prosleđenu email adresu.
	 * 
	 * @param email email adresa studenta
	 */
	public void setEmail(String email) {
		if (email == null) {
			throw new NullPointerException();
		}
		email = email.trim();
		if (email.isEmpty()) {
			throw new IllegalArgumentException("Email mora biti unet!");
		}
		this.email = email;
	}

	/**
	 * Dobavalja broj indeksa studenta.
	 * 
	 * @return indeks studenta
	 */
	public String getBrIndeksa() {
		return brIndeksa;
	}

	/**
	 * Postavlja broj indeksa studenta na prosleđenu vrednost.
	 * 
	 * @param brIndeksa broj indeksa studenta
	 */
	public void setBrIndeksa(String brIndeksa) {
		this.brIndeksa = brIndeksa;
	}

	/**
	 * Dobavlja godinu upisa studenta.
	 * 
	 * @return godina upisa studenta
	 */
	public int getGodinaUpisa() {
		return godinaUpisa;
	}

	/**
	 * Postavlja godinu upisa studenta na prosleđenu vrednost godine upisa.
	 * 
	 * @param godinaUpisa godina upisa studenta
	 */
	public void setGodinaUpisa(int godinaUpisa) {
		this.godinaUpisa = godinaUpisa;
	}

	/**
	 * Dobavlja trenutnu godinu studija studenta.
	 * 
	 * @return trenutna godina studija studenta
	 */
	public int getTrenutnaGodina() {
		return trenutnaGodina;
	}

	/**
	 * Postavlja trenutnu godinu studija studenta na prosleđenu godinu.
	 * 
	 * @param trenutnaGodina trenutna godina studija studenta
	 */
	public void setTrenutnaGodina(int trenutnaGodina) {
		this.trenutnaGodina = trenutnaGodina;
	}

	/**
	 * Dobavlja status studenta.
	 * 
	 * @return status studenta na trenutnoj godini studija (budžet/samofinansiranje)
	 */
	public Status getStatusStudenta() {
		return statusStudenta;
	}

	/**
	 * Postavlja status studenta na prosleđeni status studenta.
	 * 
	 * @param statusStudenta status studenta na trenutnoj godini studija (budžet/samofinansiranje)
	 */
	public void setStatusStudenta(Status statusStudenta) {
		this.statusStudenta = statusStudenta;
	}

	/**
	 * Dobavlja prezime studenta.
	 * 
	 * @return prezime studenta
	 */
	public String getPrezime() {
		return prezime;
	}
	
	/**
	 * Postavlja prezime studenta na prosleđeno prezime.
	 * 
	 * @param prezime prezime studenta
	 */
	public void setPrezime(String prezime) {
		if (prezime == null) {
			throw new NullPointerException();
		}
		prezime = prezime.trim();
		if (prezime.isEmpty()) {
			throw new IllegalArgumentException("Prezime mora biti uneto!");
		}
		this.prezime = prezime;
	}

}
