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

import model.Student.Status;

public class BazaStudenata implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5282477148286140938L;
	
	private static BazaStudenata instance = null;

	public static BazaStudenata getInstance() {
		if (instance == null) {
			instance = new BazaStudenata();
		}
		return instance;
	}

	private List<Student> studenti;
	private List<String> kolone;

	private BazaStudenata() {

		deserijalizacija("deserijalizacija" + File.separator + "studenti.txt");
		
		

		this.kolone = new ArrayList<String>();
		this.kolone.add("Indeks");
		this.kolone.add("Ime");
		this.kolone.add("Prezime");
		this.kolone.add("Godina studija");
		this.kolone.add("Status");
		this.kolone.add("Prosek");
		
	}
	
	
	private void init() {
		this.studenti = new ArrayList<Student>();
	}

	public List<Student> getStudenti() {
		return studenti;
	}

	public void setStudenti(List<Student> studenti) {
		this.studenti = studenti;
	}

	public int getColumnCount() {
		return 6;
	}

	public String getColumnName(int index) {
		return this.kolone.get(index);
	}

	public Student getRow(int rowIndex) {
		return this.studenti.get(rowIndex);
	}

	public String getValueAt(int row, int column) {
		Student student = this.studenti.get(row);
		switch (column) {
		case 0:
			return student.getBrIndeksa();
		case 1:
			return student.getIme();
		case 2:
			return student.getPrezime();
		case 3:
			return String.valueOf(student.getTrenutnaGodina());
		case 4:
			return String.valueOf(student.getStatusStudenta());
		case 5:
			return String.valueOf(student.getProsecnaOcena());
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
		String indeks;
		int godinaUpisa;
		int trenutnaGodina;
		Status status;
		
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
				indeks = kolone[6];
				godinaUpisa = Integer.parseInt(kolone[7]);
				trenutnaGodina = Integer.parseInt(kolone[8]);
				if(kolone[9].equals("B")) {
					status = Status.B;
				}
				else {
					status = Status.S;
				}
				
				dodajStudenta(ime, prezime, datum, adresa, telefon, email, indeks, godinaUpisa, trenutnaGodina, status);
				
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

	public void dodajStudenta(String ime, String prezime, String datumRodjenja, String adresa, String telefon,
			String email, String brIndeksa, int godinaUpisa, int trenutnaGodina, Status statusStudenta) {
		this.studenti.add(new Student(ime, prezime, datumRodjenja, adresa, telefon, email, brIndeksa, godinaUpisa,
				trenutnaGodina, statusStudenta));
	}

	public void dodajStudenta(Student s) {
		this.studenti.add(new Student(s));
	}

	public void izbrisiStudenta(String id) {
		for (Student s : studenti) {
			if (s.getBrIndeksa().equals(id)) {
				studenti.remove(s);
				break;
			}
		}
	}
}
