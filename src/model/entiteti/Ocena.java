/**
 * 
 */
package model.entiteti;

import java.time.LocalDate;

/**
 * Klasa predstavlja model entiteta ocene.
 * 
 * @author Luka Miletić
 *
 */
public class Ocena {
	
	/**
	 * Objekat studenta.
	 */
	private Student student;
	
	/**
	 * Objekat predmeta.
	 */
	private Predmet predmet;
	
	/**
	 * Brojčana vrednost ocene.
	 */
	private int vrednostOcene;
	
	/**
	 * Datum polaganja ispita.
	 */
	private LocalDate datumPolaganja;
	
	
	/**
	 * Parametrizovani konstruktor ocene.
	 * 
	 * @param student objekat studenta
	 * @param predmet objekat predmeta
	 * @param vrednostOcene brojčana vrednost ocene
	 * @param datumPolaganja datum polaganja ispita
	 */
	public Ocena(Student student, Predmet predmet, int vrednostOcene, LocalDate datumPolaganja) {
		super();
		this.student = student;
		this.predmet = predmet;
		this.vrednostOcene = vrednostOcene;
		this.datumPolaganja = datumPolaganja;
	}
	
	/**
	 * Konstruktor kopije ocene.
	 * 
	 * @param o objekat ocene
	 */
	public Ocena(Ocena o) {
		this.student = o.student;
		this.predmet = o.predmet;
		this.vrednostOcene = o.vrednostOcene;
		this.datumPolaganja = o.datumPolaganja;
	}
	
	/**
	 * Dobavlja objekat studenta iz ocene.
	 * 
	 * @return objekat studenta
	 */
	public Student getStudent() {
		return student;
	}
	
	/**
	 * Postavlja objekat studenta na prosleđeni objekat studenta.
	 * 
	 * @param student objekat studenta
	 */
	public void setStudent(Student student) {
		this.student = student;
	}
	
	/**
	 * Dobavlja objekat predmeta iz ocene.
	 * 
	 * @return objekat predmeta
	 */
	public Predmet getPredmet() {
		return predmet;
	}
	
	/**
	 * Postavlja objekat predmeta na prosleđeni objekat predmeta.
	 * 
	 * @param predmet objekat predmeta
	 */
	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}
	
	/**
	 * Dobavlja brojčanu vrednost ocene.
	 * 
	 * @return brojčana vrednost ocene
	 */
	public int getVrednostOcene() {
		return vrednostOcene;
	}
	
	/**
	 * Postavlja brojčanu vrednost ocene na prosleđenu vrednost.
	 * 
	 * @param vrednostOcene brojčana vrednost ocene
	 */
	public void setVrednostOcene(int vrednostOcene) {
		this.vrednostOcene = vrednostOcene;
	}
	
	/**
	 * Dobavlja datum polaganja ispita.
	 * 
	 * @return datum polaganja ispita
	 */
	public LocalDate getDatumPolaganja() {
		return datumPolaganja;
	}
	
	/**
	 * Postavlja datum polaganja ispita na prosleđeni datum.
	 * 
	 * @param datumPolaganja datum polaganja ispita
	 */
	public void setDatumPolaganja(LocalDate datumPolaganja) {
		this.datumPolaganja = datumPolaganja;
	}

}
