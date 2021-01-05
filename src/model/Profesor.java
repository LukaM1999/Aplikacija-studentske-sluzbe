/**
 * 
 */
package model;

import java.time.LocalDate;
import java.util.ArrayList;
//import java.util.Date;

public class Profesor {
	
	public enum Titula {BSc, MSc, mr, dr, prof_dr}

	public enum Zvanje {saradnik_u_nastavi, asistent,
		asistent_sa_doktoratom, docent,
		vanredni_profesor, redovni_profesor, profesor_emeritus}
	
	private String ime;
	private String prezime;
	private LocalDate datumRodjenja;
	private String adresa;
	private String telefon;
	private String email;
	private String kancelarija;
	private String brLicneKarte;
	private Titula titula;
	private Zvanje zvanje;
	private ArrayList<Predmet> predajePredmet = new ArrayList<Predmet>();
	private ArrayList<Predmet> predmetiBezProfesora = new ArrayList<Predmet>();
	
	
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
	
	public Profesor(String ime2, String prezime2, Titula titula2, Zvanje zvanje2) {
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
	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(LocalDate datumRodjenja2) {
		this.datumRodjenja = datumRodjenja2;
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
	public Titula getTitula() {
		return titula;
	}
	public void setTitula(Titula titula2) {
		this.titula = titula2;
	}
	public Zvanje getZvanje() {
		return zvanje;
	}
	public void setZvanje(Zvanje zvanje) {
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
	
	public void dodajPredajePredmet(Predmet p) {
		this.predajePredmet.add(p);
	}
	
	public void izbrisiPredajePredmet(int index) {
		this.predajePredmet.remove(index);
	}
	
	public void izbrisiPredajePredmet(String sifra) {
		for(int i = 0; i < predajePredmet.size(); i++) {
			if(predajePredmet.get(i).getSifra().equals(sifra)) {
				this.predajePredmet.remove(i);
			}
		}
	}

	public ArrayList<Predmet> getPredmetiBezProfesora() {
		return predmetiBezProfesora;
	}

	public void setPredmetiBezProfesora(ArrayList<Predmet> predmetiBezProfesora) {
		this.predmetiBezProfesora = predmetiBezProfesora;
	}
	
	public void dodajSlobodan(Predmet p) {
		this.predmetiBezProfesora.add(p);
	}
	
	public void izbrisiSlobodan(int index) {
		this.predmetiBezProfesora.remove(index);
	}
	
	public void izbrisiSlobodan(Predmet p) {
		this.predmetiBezProfesora.remove(p);
	}
	
}
