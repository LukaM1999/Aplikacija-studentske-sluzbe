package controller;

import java.io.IOException;
import java.util.List;

import model.baze.BazaStudenata;
import model.entiteti.Student;
import view.MainFrame;

//Koriscen materijal sa vezbi
/**
 * Klasa predstavlja kontroler studenata napravljen
 * po MVC šablonu.
 * 
 * @author Luka Miletić
 *
 */
public class StudentController {

	/**
	 * Instanca kontrolera studenata.
	 */
	private static StudentController instance = null;

	/**
	 * Dobavlja instancu kontrolera studenata po Singleton šablonu.
	 * 
	 * @return instanca kontrolera studenata
	 */
	public static StudentController getInstance() {
		if (instance == null) {
			instance = new StudentController();
		}
		return instance;
	}

	/**
	 * Prazan konstruktor kontrolera studenata.
	 */
	private StudentController() {}

	/**
	 * Prosleđuje studenta metodi za dodavanje studenta u listu svih studenata
	 * baze studenata. Takođe poziva metodu glavnog prozora za ažuriranje prikaza studenata.
	 * 
	 * @param s objekat studenta
	 */
	public void dodajStudenta(Student s) {
		BazaStudenata.getInstance().dodajStudenta(s);
		MainFrame.getInstance().azurirajStudente("DODAT", -1);
	}

	/**
	 * Prosleđuje indeks reda izmenjenog studenta u tabeli studenata metodi
	 * glavnog prozora za ažuriranje prikaza studenata.
	 * 
	 * @param row red izmenjenog studenta u tabeli studenata
	 */
	public void izmeniStudenta(int row) {
		MainFrame.getInstance().izmeniStudenta(row);
	}
	
	/**
	 * Prosleđuje studenta metodi za brisanje studenta iz baze studenata koji se nalazi u
	 * listi studenata na prosleđenom indeksu. Takođe prosleđuje indeks reda studenta koji je obrisan u tabeli studenata
	 * metodi glavnog prozora za ažuriranje prikaza studenata.
	 * 
	 * @param rowSelectedIndex indeks na kom se nalazi student kog hoćemo da
	 * obrišemo u listi studenata baze studenata
	 */
	public void izbrisiStudenta(int rowSelectedIndex) {
		if (rowSelectedIndex < 0) {
			return;
		}

		Student s = BazaStudenata.getInstance().getRow(rowSelectedIndex);
		BazaStudenata.getInstance().izbrisiStudenta(s.getBrIndeksa());
		MainFrame.getInstance().azurirajStudente("UKLONJEN", rowSelectedIndex);
	}
	
	/**
	 * Prosleđuje indeks studenta metodi za brisanje studenta iz liste studenata
	 * baze studenata.
	 * 
	 * @param indeks indeks studenta kog želimo da obrišemo
	 */
	public void izbrisiStudenta(String indeks) {
		BazaStudenata.getInstance().izbrisiStudenta(indeks);
	}

	/**
	 * Dobavlja listu studenata baze studenata.
	 * 
	 * @return lista studenata
	 */
	public List<Student> getStudenti() {
		return BazaStudenata.getInstance().getStudenti();
	}

	/**
	 * Dobavlja studenta koji se nalazi na prosleđenom indeksu u listi studenata baze studenata.
	 * 
	 * @param row indeks reda liste studenata u kom se nalazi student kog hoćemo da dobavimo
	 * @return objekat studenta
	 */
	public Student getStudent(int row) {
		return BazaStudenata.getInstance().getRow(row);
	}

	/**
	 * Dobavlja studenta koji ima isti broj indeksa kao student u listi studenata baze studenata.
	 * 
	 * @param indeks indeks studenta kog hoćemo da dobavimo
	 * @return objekat studenta
	 */
	public Student getStudent(String indeks) {
		return BazaStudenata.getInstance().getStudent(indeks);
	}
	
	/**
	 * Prosleđuje putanju datoteke metodi za serijalizaciju studenata preko <code>XStream</code> biblioteke.
	 * 
	 * @param putanja putanja na kojoj se nalazi datoteka u koju se serijalizuju studenti
	 */
	public void XstreamSerialization(String putanja) {
		try {
			BazaStudenata.getInstance().XstreamSerialization(putanja);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Prosleđuje putanju datoteke metodi za deserijalizaciju studenata preko <code>XStream</code> biblioteke.
	 * 
	 * @param putanja putanja na kojoj se nalazi datoteka iz koje se deserijalizuju studenti
	 */
	public void XstreamDeserialization(String putanja) {
		try {
			BazaStudenata.getInstance().XstreamDeserialization(putanja);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
