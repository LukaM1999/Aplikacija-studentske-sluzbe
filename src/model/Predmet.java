/**
 * 
 */
package model;

import java.util.ArrayList;

public class Predmet {
	
	public enum Semestar {Letnji, Zimski}
	
	private String sifra;
	private String naziv;
	private Semestar semestar;
	private int godinaStudija;
	private Profesor profesor;
	private int ESPB;
	private ArrayList<Student> polozili;
	private ArrayList<Student> oboreni;
	
	
	
	public Predmet (String sifra, String naziv, Semestar semestar,
			   int godinaStudija, Profesor profesor, int EPSB) {
	setSifra(sifra);
	setNaziv(naziv);
	setSemestar(semestar);
	setGodinaStudija(godinaStudija);
	setProfesor(profesor);
	setESPB(EPSB);
}
	public Predmet (Predmet p) {
		sifra = p.sifra;
		naziv = p.naziv;
		semestar = p.semestar;
		godinaStudija = p.godinaStudija;
		profesor = p.profesor;
		ESPB = p.ESPB;
		polozili = p.polozili;
		oboreni = p.oboreni;
		
	}
	
	public Predmet(String string, String string2, int i, int j, Semestar zimski) {
		// TODO Auto-generated constructor stub
		this.sifra = string;
		this.naziv = string2;
		this.ESPB = i;
		this.godinaStudija = j;
		this.semestar = zimski;
		
	}
	
	
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		if (sifra == null) {
			throw new NullPointerException();
		}
		sifra = sifra.trim();
		if (sifra.isEmpty()) {
			throw new IllegalArgumentException("Sifra mora biti uneta!");
		}
		this.sifra = sifra;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Semestar getSemestar() {
		return semestar;
	}
	public void setSemestar(Semestar semestar) {
		this.semestar = semestar;
	}
	public int getGodinaStudija() {
		return godinaStudija;
	}
	public void setGodinaStudija(int godinaStudija) {
		this.godinaStudija = godinaStudija;
	}
	public Profesor getProfesor() {
		return profesor;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	public int getESPB() {
		return ESPB;
	}
	public void setESPB(int eSPB) {
		ESPB = eSPB;
	}
	public ArrayList<Student> getPolozili() {
		return polozili;
	}
	public void setPolozili(ArrayList<Student> polozili) {
		this.polozili = polozili;
	}
	public ArrayList<Student> getOboreni() {
		return oboreni;
	}
	public void setOboreni(ArrayList<Student> oboreni) {
		this.oboreni = oboreni;
	}
	
	
	
	
}
