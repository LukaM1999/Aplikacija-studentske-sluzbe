/**
 * 
 */
package model.entiteti;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;


/**
 * Koriscen materijal sa vezbi
 *
 */
public class Student {
	
	public enum Status {B, S}

	private String ime;
	private String prezime;
	private LocalDate datumRodjenja;
	private String adresa;
	private String telefon;
	private String email;
	private String brIndeksa;
	private int godinaUpisa;
	private int trenutnaGodina;
	private Status statusStudenta;
	private double prosecnaOcena = 0;
	private List<Ocena> spisakPolozenih = new ArrayList<Ocena>();
	private List<Predmet> spisakNepolozenih = new ArrayList<Predmet>();
	private List<Predmet> spisakSlobodnih = new ArrayList<Predmet>();

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
	
	public void dodajNepolozen(Predmet p) {
		this.spisakNepolozenih.add(p);
	}
	
	public void izbrisiNepolozen(int index) {
		this.spisakNepolozenih.remove(index);
	}
	
	public void dodajPolozen(Ocena o) {
		this.spisakPolozenih.add(o);
	}
	
	public void dodajPolozen(Student s, Predmet p, int vrednostOcene, LocalDate datumPolaganja) {
		this.spisakPolozenih.add(new Ocena(s, p, vrednostOcene, datumPolaganja));
	}
	
	public void ponistiOcenu(int index) {
		this.spisakPolozenih.remove(index);
	}
	
	public void dodajSlobodan(Predmet p) {
		this.spisakSlobodnih.add(p);
	}
	
	public void izbrisiSlobodan(int index) {
		this.spisakSlobodnih.remove(index);
	}
	
	public void setSlobodne(List<Predmet> slobodni) {
		this.spisakSlobodnih = slobodni;
	}
	
	public List<Predmet> getSlobodne() {
		return this.spisakSlobodnih;
	}
	
	public void ispisPolozenih() {
		for (Ocena o: spisakPolozenih) {
			System.out.println(o.getStudent().getBrIndeksa());
		}
	}
	
	public double getProsecnaOcena() {
		return prosecnaOcena;
	}
	
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

	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public List<Ocena> getSpisakPolozenih() {
		return spisakPolozenih;
	}

	public void setSpisakPolozenih(List<Ocena> spisakPolozenih) {
		this.spisakPolozenih = spisakPolozenih;
	}

	public List<Predmet> getSpisakNepolozenih() {
		return spisakNepolozenih;
	}

	public void setSpisakNepolozenih(List<Predmet> spisakNepolozenih) {
		this.spisakNepolozenih = spisakNepolozenih;
	}

	public String getIme() {
		return ime;
	}

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

	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(LocalDate datumRodjenja) {
		if (datumRodjenja == null) {
			throw new NullPointerException();
		}
		if (datumRodjenja.toString().isEmpty()) {
			throw new IllegalArgumentException("Datum rođenja mora biti unet!");
		}
		this.datumRodjenja = datumRodjenja;
	}

	public String getAdresa() {
		return adresa;
	}

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

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

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

	public String getBrIndeksa() {
		return brIndeksa;
	}

	public void setBrIndeksa(String brIndeksa) {
		this.brIndeksa = brIndeksa;
	}

	public int getGodinaUpisa() {
		return godinaUpisa;
	}

	public void setGodinaUpisa(int godinaUpisa) {
		this.godinaUpisa = godinaUpisa;
	}

	public int getTrenutnaGodina() {
		return trenutnaGodina;
	}

	public void setTrenutnaGodina(int trenutnaGodina) {
		this.trenutnaGodina = trenutnaGodina;
	}

	public Status getStatusStudenta() {
		return statusStudenta;
	}

	public void setStatusStudenta(Status statusStudenta) {
		this.statusStudenta = statusStudenta;
	}

	public String getPrezime() {
		return prezime;
	}

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
