package model.baze;

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
import model.entiteti.Predmet;
import model.entiteti.Student;
import model.entiteti.Predmet.Semestar;


/**
 * Klasa koja predstavlja bazu svih nepoloženih ispita
 * za konkretnog studenta.
 * 
 * @author Mihajlo Kisić
 *
 */
public class BazaNepolozenihIspita implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1498636907499345574L;
	
	/**
	 * Instanca baze nepoloženih ispita.
	 */
	private static BazaNepolozenihIspita instance = null;

	/**
	 * Dobavlja instancu baze nepoloženih ispita po Singleton konvenciji.
	 * 
	 * @return povratna vrednost instanca baze nepoloženih ispita
	 */
	public static BazaNepolozenihIspita getInstance() {
		if (instance == null) {
			instance = new BazaNepolozenihIspita();
		}
		return instance;
	}
	
	/**
	 * Lista predmeta (ispita) koje student nije položio.
	 */
	private List<Predmet> predmeti;
	
	/**
	 * Lista kolona tabele nepoloženih ispita.
	 */
	private List<String> kolone;
	
	/**
	 * Kreira praznu listu nepoloženih ispita i dodaje
	 * kolone sa predefinisanim nazivima koji se mogu videti
	 * u zaglavlju tabele nepoloženih ispita.
	 */
	private BazaNepolozenihIspita() {
	
		init();
				
		this.kolone = new ArrayList<String>();
		this.kolone.add("Šifra");
		this.kolone.add("Naziv");
		this.kolone.add("ESPB");
		this.kolone.add("Godina studija");
		this.kolone.add("Semestar");
	}
	
	/**
	 * Inicijalizuje listu nepoloženih ispita praznom listom.
	 */
	private void init() {
		this.predmeti = new ArrayList<Predmet>();
	}
	
	/**
	 * Dobavlja listu nepoloženih ispita.
	 * 
	 * @return predmeti lista nepoloženih ispita
	 */
	public List<Predmet> getPredmeti() {
		return predmeti;
	}

	/**
	 * Postavlja polje liste nepoloženih ispita na prosleđenu listu predmeta.
	 * 
	 * @param predmeti lista predmeta
	 */
	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
	}
	
	/**
	 * Dobavlja broj kolona tabele nepoloženih ispita.
	 * 
	 * @return 5 predefinisan broj kolona tabele nepoloženih ispita
	 */
	public int getColumnCount() {
		return 5;
	}
	
	/**
	 * Vraća naziv kolone tabele nepoloženih ispita čiji indeks je prosleđen.
	 * 
	 * @param index indeks kolone tabele nepoloženih ispita
	 * @return kolone[index] naziv kolone iz liste kolona sa prosleđenim indeksom
	 */
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}
	
	/**
	 * Dobavlja prosleđeni red iz liste nepoloženih ispita odnosno predmet koji
	 * se u listi nalazi na indeksu koji je prosleđen.
	 * 
	 * @param rowIndex indeks reda liste nepoloženih ispita
	 * @return predmeti[rowIndex] predmet iz liste nepoloženih ispita na prosleđenom indeksu
	 */
	public Predmet getRow(int rowIndex) {
		return this.predmeti.get(rowIndex);
	}
	
	/**
	 * Dobavlja vrednost koja se nalazi na prosleđenom indeksu reda i kolone u tabeli
	 * nepoloženih ispita u vidu <code>String</code>-a.
	 * 
	 * @param row indeks reda u kom se nalazi željena vrednost
	 * @param column indeks kolone u kojoj se nalazi željena vrednost
	 * @return stringVrednost vrednost koja se nalazi u prosleđenoj koloni i redu
	 */
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
	
	/**
	 * Metoda koja vrši deserijalizaciju datoteke na prosleđenoj putanji.
	 * Razdvaja slogove po predefinisanom karakteru i dobijene vrednosti
	 * prosleđuje u metodu <code>dodajNepolozen</code>.
	 * 
	 * @param putanja putanja datoteke iz koje se vrši deserijalizacija
	 */
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
	
	/**
	 * Inicijalizuje listu nepoloženih ispita tako što joj vrednost postavi
	 * na vrednost liste nepoloženih ispita konkretnog, prosleđenog studenta.
	 * 
	 * @param s objekat studenta čija se lista nepoloženih ispita koristi
	 */
	public void initSpisakNepolozenih(Student s) {
		this.predmeti = s.getSpisakNepolozenih();
	}
	
	/**
	 * Pravi novi predmet pomoću prosleđenih parametara i dodaje ga u listu
	 * nepoloženih ispita.
	 * 
	 * @param sifra šifra predmeta
	 * @param naziv naziv predmeta
	 * @param ESPB broj ESPB poena predmeta
	 * @param godinaStudija godina studija na kojoj se predmet predaje
	 * @param semestar semestar u kom se predmet predaje
	 */
	public void dodajPredmet(String sifra, String naziv, int ESPB, int godinaStudija, Semestar semestar) {
		this.predmeti.add(new Predmet(sifra, naziv, ESPB, godinaStudija, semestar));
	}
	
	/**
	 * Pravi novi predmet pomoću prosleđenog objekta predmeta i dodaje ga u listu
	 * nepoloženih ispita.
	 * 
	 * @param p objekat predmeta koji se dodaje u listu nepoloženih ispita
	 */
	public void dodajPredmet(Predmet p) {
		this.predmeti.add(new Predmet(p));
	}
	
	/**
	 * Dodaje postojeći predmet u listu nepoloženih ispita.
	 * 
	 * @param p objekat predmeta koji se dodaje u listu nepoloženih ispita
	 */
	public void dodajNepolozen(Predmet p) {	  
		this.predmeti.add(p);
	}
	
	/**
	 * Uklanja predmet iz liste nepoloženih ispita čija šifra
	 * se poklapa sa prosleđenom šifrom predmeta.
	 * 
	 * @param id šifra predmeta koji se briše
	 */
	public void izbrisiPredmet(String id) {
		for (Predmet p : predmeti) {
			if (p.getSifra() == id) {
				predmeti.remove(p);
				break;
			}
		}
	}
	
	/**
	 * Metoda koja prolazi kroz listu svih studenata i ako nađe studenta čiji je indeks isti
	 * kao prosleđeni, prolazi kroz listu svih predmeta. Ako uspe da pronađe predmet koji ima istu
	 * šifru kao prosleđena, traži taj predmet u listi nepoloženih predmeta nađenog studenta. Ako postoji u njoj, 
	 * metoda završava sa radom. U suprotnom prosleđuje objekat nađenog predmeta metodi <code>dodajNepolozen</code> za
	 * nađenog studenta.
	 * 
	 * @param indeks indeks studenta u čijem spisku nepoloženih ispita tražimo predmet
	 * @param sifra šifra predmeta koji želimo da dodamo studentu u njegovu listu nepoloženih ispita
	 */
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
