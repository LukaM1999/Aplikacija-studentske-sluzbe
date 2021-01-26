package model.baze;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.entiteti.Ocena;
import model.entiteti.Predmet;
import model.entiteti.Student;


/**
 * Klasa koja predstavlja bazu svih položenih ispita
 * za konkretnog studenta.
 * 
 * @author Luka Miletić
 *
 */
public class BazaPolozenihIspita {
	
	/**
	 * Instanca baze položenih ispita.
	 */
	private static BazaPolozenihIspita instance = null;
	
	/**
	 * Dobavlja instancu baze položenih ispita po Singleton šablonu.
	 * 
	 * @return instanca baze položenih ispita
	 */
	public static BazaPolozenihIspita getInstance() {
		if (instance == null) {
			instance = new BazaPolozenihIspita();
		}
		return instance;
	}

	/**
	 * Lista ocena (ispita) koje je student položio.
	 */
	private List<Ocena> ocene;
	
	/**
	 * Lista kolona tabele položenih ispita.
	 */
	private List<String> kolone;
	
	/**
	 * Kreira praznu listu položenih ispita i dodaje
	 * kolone sa predefinisanim nazivima koji se mogu videti
	 * u zaglavlju tabele položenih ispita.
	 */
	private BazaPolozenihIspita() {
		
		init();
		
		

		this.kolone = new ArrayList<String>();
		this.kolone.add("Šifra predmeta");
		this.kolone.add("Naziv predmeta");
		this.kolone.add("ESPB");
		this.kolone.add("Ocena");
		this.kolone.add("Datum");
	}

	/**
	 * Inicijalizuje listu položenih ispita praznom listom.
	 */
	private void init() {
		this.ocene = new ArrayList<Ocena>();
	}

	/**
	 * Dobavlja listu položenih ispita.
	 * 
	 * @return lista položenih ispita
	 */
	public List<Ocena> getOcene() {
		return this.ocene;
	}

	/**
	 * Postavlja polje liste položenih ispita na prosleđenu listu ocena.
	 * 
	 * @param ocene lista ocena
	 */
	public void setOcene(List<Ocena> ocene) {
		this.ocene = ocene;
	}

	/**
	 * Dobavlja broj kolona tabele položenih ispita.
	 * 
	 * @return predefinisan broj kolona tabele položenih ispita
	 */
	public int getColumnCount() {
		return 5;
	}

	/**
	 * Vraća naziv kolone tabele položenih ispita, čiji indeks je prosleđen.
	 * 
	 * @param index indeks kolone tabele položenih ispita
	 * @return naziv kolone iz liste kolona sa prosleđenim indeksom
	 */
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}

	/**
	 * Dobavlja prosleđeni red iz liste položenih ispita odnosno ocenu koja
	 * se u listi nalazi na indeksu koji je prosleđen.
	 * 
	 * @param rowIndex indeks reda liste položenih ispita
	 * @return ocena iz liste položenih ispita na prosleđenom indeksu
	 */
	public Ocena getRow(int rowIndex) {
		return this.ocene.get(rowIndex);
	}

	/**
	 * Dobavlja vrednost koja se nalazi na prosleđenom indeksu reda i kolone u tabeli
	 * položenih ispita u vidu <code>String</code>-a.
	 * 
	 * @param row indeks reda u kom se nalazi željena vrednost
	 * @param column indeks kolone u kojoj se nalazi željena vrednost
	 * @return vrednost koja se nalazi u prosleđenoj koloni i redu
	 */
	public String getValueAt(int row, int column) {
		Ocena ocena = this.ocene.get(row);
		switch (column) {
		case 0:
			return ocena.getPredmet().getSifra();
		case 1:
			return ocena.getPredmet().getNaziv();
		case 2:
			return String.valueOf(ocena.getPredmet().getESPB());
		case 3:
			return String.valueOf(ocena.getVrednostOcene());
		case 4:
			return ocena.getDatumPolaganja().format(DateTimeFormatter.ofPattern("dd.MM.yyyy."));
		default:
			return null;
		}
	}

	/**
	 * Inicijalizuje listu položenih ispita tako što joj vrednost postavi
	 * na vrednost liste položenih ispita konkretnog, prosleđenog studenta.
	 * 
	 * @param s objekat studenta čija se lista položenih ispita koristi
	 */
	public void initSpisakPolozenih(Student s) {
		this.ocene = s.getSpisakPolozenih();
	}
	
	/**
	 * Pravi novu ocenu pomoću prosleđenih parametara i dodaje je u listu ocena.
	 * 
	 * @param s objekat studenta
	 * @param p objekat predmeta
	 * @param vrednostOcene brojčana vrednost ocene (od 6 do 10)
	 * @param datumPolaganja datum kada je prosleđeni student položio prosleđeni predmet
	 */
	public void dodajOcenu(Student s, Predmet p, int vrednostOcene, LocalDate datumPolaganja) {
		this.ocene.add(new Ocena(s, p, vrednostOcene, datumPolaganja));
	}

	/**
	 * Pravi novu ocenu pomoću prosleđenog objekta ocene i dodaje je u listu ocena.
	 * 
	 * @param o objekat ocene koja se dodaje u listu ocena
	 */
	public void dodajOcenu(Ocena o) {
		this.ocene.add(new Ocena(o));
	}

	/**
	 * Uklanja ocenu iz liste ocena čija šifra predmeta i indeks studenta se
	 * poklapaju sa prosleđenom šifrom i indeksom.
	 * 
	 * @param indeks indeks studenta u oceni
	 * @param sifra šifra predmeta u oceni
	 */
	public void ponistiOcenu(String indeks, String sifra) {
		for (Ocena o : ocene) {
			if (o.getPredmet().getSifra().equals(sifra) && o.getStudent().getBrIndeksa().equals(indeks)) {
				ocene.remove(o);
				break;
			}
		}
	}

}
