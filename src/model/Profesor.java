/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Date;

import model.Student.Status;

public class Profesor {
	
	private String prezime;
	private String ime;
	private Date datumRodjenja;
	private String adresa;
	private int telefon;
	private String email;
	private String kancelarija;
	private int brLicneKarte;
	private String titula;
	private String zvanje;
	private ArrayList<Predmet> predajePredmet;
	
	
	public Profesor (String prezime, String ime, Date datumRodjenja,
				   String adresa, int telefon, String email, 
				   String kancelarija, int brLicneKarte,
				   String titula, String zvanje) {
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
	
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
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
		this.email = email;
	}
	public int getBrLicneKarte() {
		return brLicneKarte;
	}
	public void setBrLicneKarte(int brLicneKarte) {
		this.brLicneKarte = brLicneKarte;
	}
	public String getTitula() {
		return titula;
	}
	public void setTitula(String titula) {
		this.titula = titula;
	}
	public String getZvanje() {
		return zvanje;
	}
	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}
	public ArrayList<Predmet> getPredajePredmet() {
		return predajePredmet;
	}
	public void setPredajePredmet(ArrayList<Predmet> predajePredmet) {
		this.predajePredmet = predajePredmet;
	}

	public String getKancelarija() {
		return kancelarija;
	}

	public void setKancelarija(String kancelarija) {
		this.kancelarija = kancelarija;
	}
	
	
}
