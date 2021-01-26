/**
 * 
 */
package model.entiteti;

import java.util.ArrayList;

/**
 * Klasa predstavlja model entiteta predmeta.
 * 
 * @author Mihajlo Kisić
 *
 */
public class Predmet {
	
	/**
	 * Enumeracija semestra u kom se predmet predaje (letnji/zimski).
	 * 
	 * @author Mihajlo Kisić
	 *
	 */
	public enum Semestar {Letnji, Zimski}
	
	/**
	 * Šifra predmeta.
	 */
	private String sifra;
	
	/**
	 * Naziv predmeta.
	 */
	private String naziv;
	
	/**
	 * Semestar u kom se predmet predaje.
	 */
	private Semestar semestar;
	
	/**
	 * Godina studija u kojoj se predmet predaje.
	 */
	private int godinaStudija;
	
	/**
	 * Profesor koji predaje predmet.
	 */
	private Profesor profesor;
	
	/**
	 * Broj ESPB poena koliko vredi predmet.
	 */
	private int ESPB;
	
	/**
	 * Spisak studenata koji su položili predmet.
	 */
	private ArrayList<Student> polozili = new ArrayList<Student>();
	
	/**
	 * Spisak studenata koji nisu položili predmet.
	 */
	private ArrayList<Student> nisuPolozili = new ArrayList<Student>();
	
	
	/**
	 * Parametrizovani konstruktor predmeta.
	 * 
	 * @param sifra šifra predmeta
	 * @param naziv naziv predmeta
	 * @param semestar semestar u kom se predmet predaje
	 * @param godinaStudija godina studija u kojoj se predmet predaje
	 * @param ESPB broj ESPB poena koliko vredi predmet
	 * @param p objekat profesora koji predaje predmet
	 */
	public Predmet(String sifra, String naziv, Semestar semestar, int godinaStudija, int ESPB, Profesor p) {
		setSifra(sifra);
		setNaziv(naziv);
		setSemestar(semestar);
		setGodinaStudija(godinaStudija);
		setESPB(ESPB);
		setProfesor(p);
	}
	
	/**
	 * Konstruktor kopije predmeta.
	 * 
	 * @param p objekat predmeta
	 */
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
	
	/**
	 * Konstruktor sa parametrima bez objekta profesora.
	 * 
	 * @param string šifra predmeta
	 * @param string2 naziv predmeta
	 * @param i broj ESPB poena koliko vredi predmet
	 * @param j godina studija na kojoj se predmet predaje
	 * @param zimski semestar u kom se predmet predaje
	 */
	public Predmet(String string, String string2, int i, int j, Semestar zimski) {
		this.sifra = string;
		this.naziv = string2;
		this.ESPB = i;
		this.godinaStudija = j;
		this.semestar = zimski;
		
	}
	
	/**
	 * Dobavlja šifru predmeta.
	 * 
	 * @return šifra predmeta
	 */
	public String getSifra() {
		return sifra;
	}
	
	/**
	 * Postavlja šifru predmeta na prosleđenu šifru.
	 * 
	 * @param sifra šifra predmeta
	 */
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
	
	/**
	 * Dobavlja naziv predmeta.
	 * 
	 * @return naziv predmeta
	 */
	public String getNaziv() {
		return naziv;
	}
	
	/**
	 * Postavlja naziv predmeta na prosleđeni naziv.
	 * 
	 * @param naziv naziv predmeta
	 */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	/**
	 * Dobavlja semestar u kom se predmet predaje.
	 * 
	 * @return semestar u kom se predmet predaje
	 */
	public Semestar getSemestar() {
		return semestar;
	}
	
	/**
	 * Postavlja semestar na prosleđenu vrednost semestra.
	 * 
	 * @param semestar semestar u kom se predaje predmet
	 */
	public void setSemestar(Semestar semestar) {
		this.semestar = semestar;
	}
	
	/**
	 * Dobavlja godinu studija na kojoj se predmet predaje.
	 * 
	 * @return godina studija na kojoj se predmet predaje
	 */
	public int getGodinaStudija() {
		return godinaStudija;
	}
	
	/**
	 * Postavlja godinu studija na kojoj se predmet predaje na prosleđenu godinu.
	 * 
	 * @param godinaStudija godina studija na kojoj se predmet predaje
	 */
	public void setGodinaStudija(int godinaStudija) {
		this.godinaStudija = godinaStudija;
	}
	
	/**
	 * Dobavlja objekat profesora.
	 * 
	 * @return objekat profesora
	 */
	public Profesor getProfesor() {
		return profesor;
	}
	
	/**
	 * Postavlja objekat profesora na prosleđen objekat profesora.
	 * 
	 * @param profesor objekat profesora
	 */
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
	/**
	 * Dobavlja broj ESPB bodova koliko vredi predmet.
	 * 
	 * @return broj ESPB poena koliko vredi predmet
	 */
	public int getESPB() {
		return ESPB;
	}
	
	/**
	 * Postavlja broj ESPB poena na prosleđeni broj.
	 * 
	 * @param ESPB broj ESPB poena koliko vredi predmet
	 */
	public void setESPB(int ESPB) {
		this.ESPB = ESPB;
	}
	
	/**
	 * Dobavlja spisak studenata koji su položili predmet.
	 * 
	 * @return lista studenata koji su položili predmet
	 */
	public ArrayList<Student> getPolozili() {
		return polozili;
	}
	
	/**
	 * Postavlja listu studenata koji su položili predmet na prosleđenu listu studenata.
	 * 
	 * @param polozili lista studenata koji su položili predmet
	 */
	public void setPolozili(ArrayList<Student> polozili) {
		this.polozili = polozili;
	}
	
	/**
	 * Dobavlja listu studenata koji nisu položili predmet.
	 * 
	 * @return lista studenata koji nisu položili predmet
	 */
	public ArrayList<Student> getNepolozeni() {
		return nisuPolozili;
	}
	
	/**
	 * Postavlja listu studenata koji nisu položili predmet na prosleđenu listu studenata.
	 * 
	 * @param nisuPolozili lista studenata koji nisu položili predmet
	 */
	public void setNepolozeni(ArrayList<Student> nisuPolozili) {
		this.nisuPolozili = nisuPolozili;
	}
	
	/**
	 * Dodaje prosleđenog studenta u listu studenata koji su položili predmet.
	 * 
	 * @param s objekat studenta koji je položio predmet
	 */
	public void dodajPolozili (Student s) {
		this.polozili.add(s);
	}
	
	/**
	 * Dodaje prosleđenog studenta u listu studenata koji nisu položili predmet.
	 * 
	 * @param s objekat studenta koji nije položio predmet
	 */
	public void dodajNisuPolozili(Student s) {
		this.nisuPolozili.add(s);
	}
	
	/**
	 * Uklanja prosleđenog studenta iz liste studenata koji su položili predmet.
	 * 
	 * @param s objekat studenta
	 */
	public void ukloniPolozili (Student s) {
		this.polozili.remove(s);
	}
	
	/**
	 * Uklanja prosleđenog studenta iz liste studenata koji nisu položili predmet.
	 * 
	 * @param s objekat studenta
	 */
	public void ukloniNisuPolozili (Student s) {
		this.nisuPolozili.remove(s);
	}




}
