/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Koriscen materijal sa vezbi
 *
 */
public class Student {
	
	enum Status {B, S}

	private String ime;
	private String prezime;
	private Date datumRodjenja;
	private String adresa;
	private int telefon;
	private String email;
	private String brIndeksa;
	private int godinaUpisa;
	private int trenutnaGodina;
	private Status statusStudenta;
	private double prosecnaOcena;
	private ArrayList<Ocena> spisakPolozenih;
	private ArrayList<Ocena> spisakNepolozenih;
	
	

	public Student(String ime, String prezime, Date datumRodjenja,
				   String adresa, int telefon, String email, 
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

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
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

	public int getTelefon() {
		return telefon;
	}

	public void setTelefon(int telefon) {
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
