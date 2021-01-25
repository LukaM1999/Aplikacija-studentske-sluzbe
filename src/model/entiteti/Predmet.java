/**
 * 
 */
package model.entiteti;

import java.util.ArrayList;

public class Predmet {
	
	public enum Semestar {Letnji, Zimski}
	
	private String sifra;
	private String naziv;
	private Semestar semestar;
	private int godinaStudija;
	private Profesor profesor;
	private int ESPB;
	private ArrayList<Student> polozili = new ArrayList<Student>();
	private ArrayList<Student> nisuPolozili = new ArrayList<Student>();
	
	
	
	public Predmet (String sifra, String naziv, Semestar semestar,
			   int godinaStudija, int EPSB, Profesor p) {
	setSifra(sifra);
	setNaziv(naziv);
	setSemestar(semestar);
	setGodinaStudija(godinaStudija);
	setESPB(EPSB);
	setProfesor(p);
}
	public Predmet (Predmet p) {
		sifra = p.sifra;
		naziv = p.naziv;
		semestar = p.semestar;
		godinaStudija = p.godinaStudija;
		profesor = p.profesor;
		ESPB = p.ESPB;
		polozili = p.polozili;
		nisuPolozili = p.nisuPolozili;
		
	}
	
	public Predmet(String string, String string2, int i, int j, Semestar zimski) {
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
		this.ESPB = eSPB;
	}
	public ArrayList<Student> getPolozili() {
		return polozili;
	}
	public void setPolozili(ArrayList<Student> polozili) {
		this.polozili = polozili;
	}
	public ArrayList<Student> getNepolozeni() {
		return nisuPolozili;
	}
	public void setNepolozeni(ArrayList<Student> nisuPolozili) {
		this.nisuPolozili = nisuPolozili;
	}
	
	public void dodajPolozili (Student s) {
		this.polozili.add(s);
	}
	
	public void dodajNisuPolozili(Student s) {
		this.nisuPolozili.add(s);
	}
	
	public void ukloniPolozili (Student s) {
		this.polozili.remove(s);
	}
	
	public void ukloniNisuPolozili (Student s) {
		this.nisuPolozili.remove(s);
	}




}
