package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class BazaProfesora implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3572422462341266636L;

	private static BazaProfesora instance = null;

	public static BazaProfesora getInstance() {
		if (instance == null) {
			instance = new BazaProfesora();
		}
		return instance;
	}

	private List<Profesor> profesori;
	private List<String> kolone;

	private BazaProfesora() {
		
		deserijalizacija("deserijalizacija" + File.separator + "profesori.txt" );

		this.kolone = new ArrayList<String>();
		this.kolone.add("Ime");
		this.kolone.add("Prezime");
		this.kolone.add("Titula");
		this.kolone.add("Zvanje");
	}

	private void init() {
		this.profesori = new ArrayList<Profesor>();
	}

	public List<Profesor> getProfesori() {
		return profesori;
	}

	public void setProfesori(List<Profesor> profesori) {
		this.profesori = profesori;
	}

	public int getColumnCount() {
		return 4;
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
			return profesor.getIme();
		case 1:
			return profesor.getPrezime();
		case 2:
			return profesor.getTitula();
		case 3:
			return profesor.getZvanje();
		default:
			return null;
		}
	}

	public void deserijalizacija(String putanja) {
		String ime;
		String prezime;
		String datum;
		String adresa;
		String telefon;
		String email;
		String kancelarija;
		String licna;
		String titula;
		String zvanje;

		init();

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(putanja)));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] kolone = line.split(";");
				ime = kolone[0];
				prezime = kolone[1];
				datum = kolone[2];
				adresa = kolone[3];
				telefon = kolone[4];
				email = kolone[5];
				kancelarija = kolone[6];
				licna = kolone[7];
				titula = kolone[8];
				zvanje = kolone[9];

				dodajProfesora(ime, prezime, datum, adresa, telefon, email, kancelarija, licna, titula, zvanje);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void dodajProfesora(String ime, String prezime, String datum, String adresa, String telefon, String email,
			String kancelarija, String licna, String titula, String zvanje) {
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
