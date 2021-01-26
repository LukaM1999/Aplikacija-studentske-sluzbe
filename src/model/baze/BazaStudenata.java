package model.baze;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import model.entiteti.Student;
import model.entiteti.Student.Status;


/**
 * Klasa koja predstavlja bazu svih studenata.
 * 
 * @author Luka Miletić
 *
 */
public class BazaStudenata implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5282477148286140938L;

	/**
	 * Instanca baze studenata.
	 */
	private static BazaStudenata instance = null;

	/**
	 * Dobavlja instancu baze studenata po Singleton šablonu.
	 * 
	 * @return instanca baze studenata
	 */
	public static BazaStudenata getInstance() {
		if (instance == null) {
			instance = new BazaStudenata();
		}
		return instance;
	}

	/**
	 * Lista svih studenata.
	 */
	private List<Student> studenti;
	
	/**
	 * Lista kolona tabele studenata.
	 */
	private List<String> kolone;
	
	/**
	 * Niz studenata korišćen pri <code>XStream</code> serijalizaciji.
	 */
	private Student[] niz;
	
	/**
	 * Poziva metodu <code>XstreamDeserialization</code> sa prosleđenom putanjom
	 * datoteke sa svim studentima i dodaje kolone sa predefinisanim nazivima koji se mogu videti
	 * u zaglavlju tabele studenata.
	 */
	private BazaStudenata() {

				 		 		
		try {
			this.XstreamDeserialization("deserijalizacija" + File.separator + "studenti.xml");
		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.kolone = new ArrayList<String>();
		this.kolone.add("Indeks");
		this.kolone.add("Ime");
		this.kolone.add("Prezime");
		this.kolone.add("Godina studija");
		this.kolone.add("Status");
		this.kolone.add("Prosek");

	}

	/**
	 * Inicijalizuje listu studenata praznom listom.
	 */
	private void init() {
		this.studenti = new ArrayList<Student>();
	}

	/**
	 * Dobavlja listu studenata.
	 * 
	 * @return lista studenata
	 */
	public List<Student> getStudenti() {
		return studenti;
	}

	/**
	 * Postavlja polje liste studenata na prosleđenu listu studenata.
	 * 
	 * @param studenti lista studenata
	 */
	public void setStudenti(List<Student> studenti) {
		this.studenti = studenti;
	}

	/**
	 * Dobavlja broj kolona tabele studenata.
	 * 
	 * @return predefinisan broj kolona tabele studenata
	 */
	public int getColumnCount() {
		return 6;
	}

	/**
	 * Vraća naziv kolone tabele studenata čiji indeks je prosleđen.
	 * 
	 * @param index indeks kolone tabele studenata
	 * @return naziv kolone iz liste kolona sa prosleđenim indeksom
	 */
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}

	/**
	 * Dobavlja prosleđeni red iz liste studenata odnosno studenta koji
	 * se u listi nalazi na indeksu koji je prosleđen.
	 * 
	 * @param rowIndex indeks reda liste studenata
	 * @return student iz liste studenata na prosleđenom indeksu
	 */
	public Student getRow(int rowIndex) {
		return this.studenti.get(rowIndex);
	}

	/**
	 * Dobavlja vrednost koja se nalazi na prosleđenom indeksu reda i kolone u tabeli
	 * studenata u vidu <code>String</code>-a.
	 * 
	 * @param row indeks reda u kom se nalazi željena vrednost
	 * @param column indeks kolone u kojoj se nalazi željena vrednost
	 * @return vrednost koja se nalazi u prosleđenoj koloni i redu
	 */
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

	/**
	 * Metoda koja vrši deserijalizaciju datoteke na prosleđenoj putanji.
	 * Razdvaja slogove po predefinisanom karakteru i dobijene vrednosti
	 * prosleđuje u metodu <code>dodajStudenta</code>.
	 * 
	 * @param putanja putanja datoteke iz koje se vrši deserijalizacija
	 */
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
			e1.printStackTrace();
		}
		try {
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] kolone = line.split(";");
				indeks = kolone[0];
				ime = kolone[1];
				prezime = kolone[2];
				trenutnaGodina = Integer.parseInt(kolone[3]);
				datum = kolone[4];
				adresa = kolone[5];
				telefon = kolone[6];
				email = kolone[7];
				if (kolone[8].equals("B")) {
					status = Status.B;
				} else {
					status = Status.S;
				}
				godinaUpisa = Integer.parseInt(kolone[9]);
				

				// REFERENCE:
				// https://mkyong.com/java8/java-8-how-to-convert-string-to-localdate/
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");

				dodajStudenta(ime, prezime, LocalDate.parse(datum, formatter), adresa, telefon, email, indeks,
						godinaUpisa, trenutnaGodina, status);

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

	/**
	 * Metoda koja postavlja vrednosti niza studenata na vrednosti studenata iz liste studenata
	 * i koristi <code>XStream</code> biblioteku da serijalizuje studente iz niza studenata u datoteku
	 * koja se nalazi na prosleđenoj putanji.
	 * 
	 * @param putanja putanja datoteke u koju se serijalizuju studenti
	 * @throws IOException
	 */
	public void XstreamSerialization(String putanja) throws IOException {
		
		niz = new Student[studenti.size()];
		
		for (int i = 0; i < studenti.size(); i++) {
			niz[i] = studenti.get(i);
		}
		
		File f = new File(putanja);
		OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
		try {

			XStream xs = new XStream();
			// Naziv tag-a u xml umesto punog naziva klase.
			xs.alias("student", Student.class);

			// Serijalizacija u xml.
			xs.toXML(niz, os); 



		} finally {
			os.close();
		}
		
		
		
	}
	
	/**
	 * Metoda koja poziva inicijalizaciju liste studenata i pomoću <code>XStream</code> 
	 * biblioteke deserijalizuje studente iz datoteke koja se nalazi na prosleđenoj putanji.
	 * Na kraju dodaje sve deserijalizovane studente u listu studenata koja predstavlja polje ove klase.
	 * 
	 * @param putanja putanja datoteke iz koje se deserijalizuju studenti
	 * @throws IOException 
	 */
	public void XstreamDeserialization(String putanja) throws IOException {
		
		init();
		
		File f = new File(putanja);
		InputStream os = new BufferedInputStream(new FileInputStream(f));
		try {

			XStream xs = new XStream();
			xs.alias("student", Student.class);

			Student[] ucitaniStudenti = (Student[]) xs.fromXML(os);
			
			for(Student student: ucitaniStudenti) {
				studenti.add(student);
			}



		} finally {
			os.close();
		}
		
		
		
	}

	/**
	 * Dobavlja studenta koji ima isti indeks kao prosleđeni indeks.
	 * 
	 * @param indeks indeks studenta kog želimo da nađemo
	 * @return objekat studenta
	 */
	public Student getStudent(String indeks) {
		for (Student s : studenti) {
			if (indeks.equals(s.getBrIndeksa())) {
				return s;
			}
		}
		return null;
	}

	/**
	 * Pravi novi objekat studenta pomoću proslenih parametara i dodaje ga u listu studenata.
	 * 
	 * @param ime ime studenta
	 * @param prezime prezime studenta
	 * @param datumRodjenja datum rođenja studenta
	 * @param adresa adresa stanovanja studenta
	 * @param telefon kontakt telefon studenta
	 * @param email email adresa stuudenta
	 * @param brIndeksa indeks studenta
	 * @param godinaUpisa godina upisa studenta
	 * @param trenutnaGodina trenutna godina studija studenta
	 * @param statusStudenta status studenta u trenutnoj godini studija (budžet/samofinansiranje)
	 */
	public void dodajStudenta(String ime, String prezime, LocalDate datumRodjenja, String adresa, String telefon,
			String email, String brIndeksa, int godinaUpisa, int trenutnaGodina, Status statusStudenta) {
		this.studenti.add(new Student(ime, prezime, datumRodjenja, adresa, telefon, email, brIndeksa, godinaUpisa,
				trenutnaGodina, statusStudenta));
	}

	/**
	 * Pravi novi objekat studenta pomoću prosleđenog objekta studenta i dodaje ga u listu studenata.
	 * 
	 * @param s objekat studenta koji se dodaje u listu studenata
	 */
	public void dodajStudenta(Student s) {
		this.studenti.add(new Student(s));
	}
	
	/**
	 * Uklanja studenta iz liste studenata ako mu je indeks isti kao prosleđeni indeks.
	 * 
	 * @param id indeks studenta kog želimo da izbrišemo
	 */
	public void izbrisiStudenta(String id) {
		for (Student s : studenti) {
			if (s.getBrIndeksa().equals(id)) {
				studenti.remove(s);
				break;
			}
		}
	}
	

}
