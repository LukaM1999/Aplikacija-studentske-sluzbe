/**
 * 
 */
package model;

import java.util.ArrayList;
//import java.util.Date;

public class Profesor {
	
	private String prezime;
	private String ime;
	private String datumRodjenja;
	private String adresa;
	private String telefon;
	private String email;
	private String kancelarija;
	private String brLicneKarte;
	private String titula;
	private String zvanje;
	private ArrayList<Predmet> predajePredmet;
	
	//Promeniti redosled imena i prezimena u konstruktoru!!!
	
	public Profesor (String ime, String prezime, String datumRodjenja,
				   String adresa, String telefon, String email, 
				   String kancelarija, String brLicneKarte,
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
	
	public Profesor(String ime2, String prezime2, String titula2, String zvanje2) {
		this.ime = ime2;
		this.prezime = prezime2;
		this.titula = titula2;
		this.zvanje = zvanje2;
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
	public String getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(String datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
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
		this.email = email;
	}
	public String getBrLicneKarte() {
		return brLicneKarte;
	}
	public void setBrLicneKarte(String brLicneKarte) {
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
