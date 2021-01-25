/**
 * 
 */
package model.entiteti;

import java.time.LocalDate;

public class Ocena {
	
	private Student student;
	private Predmet predmet;
	private int vrednostOcene;
	private LocalDate datumPolaganja;
	
	
	
	public Ocena(Student student, Predmet predmet, int vrednostOcene, LocalDate datumPolaganja) {
		super();
		this.student = student;
		this.predmet = predmet;
		this.vrednostOcene = vrednostOcene;
		this.datumPolaganja = datumPolaganja;
	}
	
	public Ocena(Ocena o) {
		this.student = o.student;
		this.predmet = o.predmet;
		this.vrednostOcene = o.vrednostOcene;
		this.datumPolaganja = o.datumPolaganja;
	}
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Predmet getPredmet() {
		return predmet;
	}
	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}
	public int getVrednostOcene() {
		return vrednostOcene;
	}
	public void setVrednostOcene(int vrednostOcene) {
		this.vrednostOcene = vrednostOcene;
	}
	public LocalDate getDatumPolaganja() {
		return datumPolaganja;
	}
	public void setDatumPolaganja(LocalDate datumPolaganja) {
		this.datumPolaganja = datumPolaganja;
	}

}
