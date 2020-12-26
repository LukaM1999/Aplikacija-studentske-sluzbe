package model;

import java.util.ArrayList;
import java.util.List;

import model.Student.Status;

public class BazaStudenata {

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

		init();

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
		studenti.add(new Student("Pera", "Perić", "3.7.1998.", "Cara Lazara 20, Novi Sad", "066754498",
				"peraperic@gmail.com", "in-123-2017", 2017, 4, Status.B));
		studenti.add(new Student("Stefan", "Vulin", "4.8.2000.", "Cara Dusana 10, Novi Sad", "065432901",
				"stefanvulin@gmail.com", "pr-53-2019", 2019, 2, Status.S));
		studenti.add(new Student("Gorana", "Papov", "14.2.1999.", "Kralja Aleksandra 4, Novi Sad", "066043781",
				"gpapov@gmail.com", "sw-12-2016", 2016, 3, Status.S));
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
