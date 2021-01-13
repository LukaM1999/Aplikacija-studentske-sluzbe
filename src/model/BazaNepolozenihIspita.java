package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import controller.PredmetController;
import controller.StudentController;
import model.Predmet.Semestar;

public class BazaNepolozenihIspita implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1498636907499345574L;
	private static BazaNepolozenihIspita instance = null;

	public static BazaNepolozenihIspita getInstance() {
		if (instance == null) {
			instance = new BazaNepolozenihIspita();
		}
		return instance;
	}
	
	private List<Predmet> predmeti;
	private List<String> kolone;
	
	private BazaNepolozenihIspita() {
	
		init();
				
		this.kolone = new ArrayList<String>();
		this.kolone.add("Šifra");
		this.kolone.add("Naziv");
		this.kolone.add("ESPB");
		this.kolone.add("Godina studija");
		this.kolone.add("Semestar");
	}
	
	private void init() {
		this.predmeti = new ArrayList<Predmet>();
	}
	
	public List<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
	}
	
	public int getColumnCount() {
		return 5;
	}
	
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}
	
	public Predmet getRow(int rowIndex) {
		return this.predmeti.get(rowIndex);
	}
	
	public String getValueAt(int row, int column) {
		Predmet predmet = this.predmeti.get(row);
		switch (column) {
		case 0:
			return predmet.getSifra();
		case 1:
			return predmet.getNaziv();
		case 2:
			return String.valueOf(predmet.getESPB());
		case 3:
			return String.valueOf(predmet.getGodinaStudija());
		case 4:
			return String.valueOf(predmet.getSemestar());
		default:
			return null;
		}
	}
	
public void deserijalizacija(String putanja) {
		
		String indeks;
		String sifra;
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(putanja)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] kolone = line.split(";");
				
				indeks = kolone[0];
				sifra = kolone[1];
				
		
		this.dodajNepolozen(indeks, sifra);		 
							
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	

	}
	
	public void initSpisakNepolozenih(Student s) {
		this.predmeti = s.getSpisakNepolozenih();
	}
	
	public void dodajPredmet(String sifra, String naziv, int ESPB, int godinaStudija, Semestar semestar) {
		this.predmeti.add(new Predmet(sifra, naziv, ESPB, godinaStudija, semestar));
	}
	
	public void dodajPredmet(Predmet p) {
		this.predmeti.add(new Predmet(p));
	}
	
	public void dodajNepolozen(Predmet p) {	  
		this.predmeti.add(p);
	}
	
	public void izbrisiPredmet(String id) {
		for (Predmet p : predmeti) {
			if (p.getSifra() == id) {
				predmeti.remove(p);
				break;
			}
		}
	}
	
	public void dodajNepolozen(String indeks, String sifra) {
		List<Student> studenti = StudentController.getInstance().getStudenti();
		for(int i = 0; i < studenti.size(); i++) {
			if(indeks.equals(studenti.get(i).getBrIndeksa())) {
				Student s = studenti.get(i);
				List<Predmet> predmeti = PredmetController.getInstance().getPredmeti();
				for (int j = 0; j < predmeti.size(); j++) {
					if(sifra.equals(predmeti.get(j).getSifra())) {
						for(Predmet p : s.getSpisakNepolozenih()) {
							if(p.getSifra().equals(sifra)) {
								return;
							}
						}					
						Predmet nepolozenPredmet = predmeti.get(j);
						s.dodajNepolozen(nepolozenPredmet);
						break;
					}
				}
			}
		}
	}
	
}	
