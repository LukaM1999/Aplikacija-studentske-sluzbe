package model.baze;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.ProfesorController;
import model.entiteti.Profesor;
import model.entiteti.Profesor.Titula;
import model.entiteti.Profesor.Zvanje;

public class BazaSlobodnihProfesora {
	
	private static BazaSlobodnihProfesora instance = null;

	public static BazaSlobodnihProfesora getInstance() {
		if (instance == null) {
			instance = new BazaSlobodnihProfesora();
		}
		return instance;
	}

	private List<Profesor> profesori;
	private List<String> kolone;

	private BazaSlobodnihProfesora() {
		
		init();
		
		this.kolone = new ArrayList<String>();
		this.kolone.add("");
	}

	private void init() {
		this.profesori = ProfesorController.getInstance().getProfesori();
	}

	public List<Profesor> getProfesori() {
		return profesori;
	}

	public void setProfesori(List<Profesor> profesori) {
		this.profesori = profesori;
	}

	public int getColumnCount() {
		return 1;
	}

	public String getColumnName(int index) {
		return this.kolone.get(index);
	}

	public Profesor getRow(int rowIndex) {
		return this.profesori.get(rowIndex);
	}

	public String getValueAt(int row, int column) {
		Profesor profesor = this.profesori.get(row);
		switch (column) {
		case 0:
			return profesor.getIme() + " " + profesor.getPrezime();
		default:
			return null;
		}
	}

	

	public void dodajProfesora(String ime, String prezime, LocalDate datum, String adresa, String telefon, String email,
			String kancelarija, String licna, Titula titula, Zvanje zvanje) {
		this.profesori.add(new Profesor(ime, prezime, datum, adresa, telefon, email, kancelarija, licna, titula, zvanje));
	}

	public void dodajProfesora(Profesor p) {
		this.profesori.add(new Profesor(p));
	}

	public void izbrisiProfesora(String id) {
		for (Profesor p : profesori) {
			if (p.getBrLicneKarte() == id) {
				profesori.remove(p);
				break;
			}
		}
	}

}
