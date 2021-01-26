package controller;

import java.io.IOException;
import java.util.List;

import model.baze.BazaOcena;
import model.entiteti.Ocena;
import model.entiteti.Student;

/**
 * Klasa predstavlja kontroler ocena napravljen
 * po MVC šablonu.
 * 
 * @author Luka Miletić
 *
 */
public class OcenaController {
	
	/**
	 * Instanca kontrolera ocena.
	 */
	private static OcenaController instance = null;
	
	/**
	 * Dobavlja instancu kontrolera ocena po Singleton šablonu.
	 * 
	 * @return instanca kontrolera ocena.
	 */
	public static OcenaController getInstance() {
		if (instance == null) {
			instance = new OcenaController();
		}
		return instance;
	}
	
	/**
	 * Prazan konstruktor kontrolera ocena.
	 */
	private OcenaController() {}
	
	/**
	 * Prosleđuje ocenu metodi koja dodaje ocenu u listu ocena
	 * baze ocena.
	 * 
	 * @param o objekat ocene
	 */
	public void dodajOcenu(Ocena o) {
		BazaOcena.getInstance().dodajOcenu(o);
	}
	
	/**
	 * Ako se indeks prosleđenog studenta poklapa sa indeksom studenta koji se nalazi
	 * u oceni, ova metoda će dodati tu ocenu prosleđenom studentu u listu položenih ispita.
	 * 
	 * @param s objekat studenta
	 */
	public void upisiOcenu(Student s) {
		List<Ocena> ocene = BazaOcena.getInstance().getOcene();
		for (Ocena o : ocene) {
			if (o.getStudent().getBrIndeksa().equals(s.getBrIndeksa())) {
				s.getSpisakPolozenih().add(o);
			}
		}
		
	}
	
	/**
	 * Prosleđuje metodi za poništavanje ocene iz baze ocena ocenu koja se nalazi u
	 * listi ocena na prosleđenom indeksu.
	 * 
	 * @param rowSelectedIndex indeks na kom se nalazi ocena koju hoćemo da
	 * poništimo u listi ocena baze ocena
	 */
	public void ponistiOcenu(int rowSelectedIndex) {
    	if (rowSelectedIndex < 0) {
			return;
		}
    	
    	Ocena o = BazaOcena.getInstance().getRow(rowSelectedIndex);
		BazaOcena.getInstance().ponistiOcenu(o.getStudent().getBrIndeksa(), o.getPredmet().getSifra());
    }
	
	/**
	 * Prosleđuje indeks studenta metodi koja briše ocene za tog studenta u bazi ocena.
	 * 
	 * @param indeks indeks studenta čije ocene želimo da obrišemo
	 */
	public void obrisiOcene(String indeks) {
		BazaOcena.getInstance().obrisiOcene(indeks);
	}
	
	/**
	 * Dobavlja listu ocena iz baze ocena.
	 * 
	 * @return lista ocena
	 */
	public List<Ocena> getOcene(){
		return BazaOcena.getInstance().getOcene();
	}
	
	/**
	 * Dobavlja ocenu koja se nalazi na prosleđenom indeksu u listi ocena baze ocena.
	 * 
	 * @param row indeks reda liste ocena u kom se nalazi ocena koju hoćemo da dobavimo
	 * @return objekat ocene
	 */
	public Ocena getOcena(int row) {
		return BazaOcena.getInstance().getRow(row);
	}
	
	/**
	 * Prosleđuje putanju datoteke metodi za serijalizaciju ocena preko <code>XStream</code> biblioteke.
	 * 
	 * @param putanja putanja na kojoj se nalazi datoteka u koju se serijalizuju ocene
	 */
	public void XstreamSerialization(String putanja) {
		try {
			BazaOcena.getInstance().XstreamSerialization(putanja);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
