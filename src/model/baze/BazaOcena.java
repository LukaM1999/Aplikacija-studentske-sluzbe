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

import controller.PredmetController;
import controller.StudentController;
import model.entiteti.Ocena;
import model.entiteti.Predmet;
import model.entiteti.Student;

/**
 * Klasa koja predstavlja bazu svih ocena.
 * 
 * @author Luka Miletić
 *
 */
public class BazaOcena implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -532190613446495075L;
	
	/**
	 * Instanca baze ocena.
	 */
	private static BazaOcena instance = null;

	/**
	 * Dobavlja instancu baze ocena po Singleton šablonu.
	 * 
	 * @return instanca baze ocena
	 */
	public static BazaOcena getInstance() {
		if (instance == null) {
			instance = new BazaOcena();
		}
		return instance;
	}

	/**
	 * Lista ocena svih studenata.
	 */
	private List<Ocena> ocene;
	
	/**
	 * Lista kolona tabele ocena.
	 */
	private List<String> kolone;
	
	/**
	 * Niz ocena korišćen pri <code>XStream</code> serijalizaciji.
	 */
	private Ocena[] niz;
	
	/**
	 * Poziva metodu <code>XstreamDeserialization</code> sa prosleđenom putanjom
	 * datoteke sa ocenama svih studenata i dodaje kolone sa predefinisanim nazivima koji se mogu videti
	 * u zaglavlju tabele ocena.
	 */
	private BazaOcena() {
		
		
		try {
			this.XstreamDeserialization("deserijalizacija" + File.separator + "ocene.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.kolone = new ArrayList<String>();
		this.kolone.add("Šifra predmeta");
		this.kolone.add("Naziv predmeta");
		this.kolone.add("ESPB");
		this.kolone.add("Ocena");
		this.kolone.add("Datum");
	}

	/**
	 * Inicijalizuje listu ocena praznom listom.
	 */
	private void init() {
		this.ocene = new ArrayList<Ocena>();
	}

	/**
	 * Dobavlja listu ocena.
	 * 
	 * @return lista ocena
	 */
	public List<Ocena> getOcene() {
		return this.ocene;
	}

	/**
	 * Postavlja polje liste ocena na prosleđenu listu ocena.
	 * 
	 * @param ocene lista ocena
	 */
	public void setOcene(List<Ocena> ocene) {
		this.ocene = ocene;
	}

	/**
	 * Dobavlja broj kolona tabele ocena.
	 * 
	 * @return predefinisan broj kolona tabele ocena
	 */
	public int getColumnCount() {
		return 5;
	}

	/**
	 * Vraća naziv kolone tabele ocena čiji indeks je prosleđen.
	 * 
	 * @param index indeks kolone tabele ocena
	 * @return naziv kolone iz liste kolona sa prosleđenim indeksom
	 */
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}

	/**
	 * Dobavlja prosleđeni red iz liste ocena odnosno ocenu koja
	 * se u listi nalazi na indeksu koji je prosleđen.
	 * 
	 * @param rowIndex indeks reda liste ocena
	 * @return ocena iz liste ocena na prosleđenom indeksu
	 */
	public Ocena getRow(int rowIndex) {
		return this.ocene.get(rowIndex);
	}

	/**
	 * Dobavlja vrednost koja se nalazi na prosleđenom indeksu reda i kolone u tabeli
	 * ocena u vidu <code>String</code>-a.
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
	 * Metoda koja vrši deserijalizaciju datoteke na prosleđenoj putanji.
	 * Razdvaja slogove po predefinisanom karakteru i dobijene vrednosti
	 * prosleđuje u metodu <code>dodajPolozen</code>.
	 * 
	 * @param putanja putanja datoteke iz koje se vrši deserijalizacija
	 */
	public void deserijalizacija(String putanja) {
		
		String indeks;
		String sifra;
		int vrednostOcene;
		String datum;
		

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
				sifra = kolone[1];
				vrednostOcene = Integer.parseInt(kolone[2]);
				datum = kolone[3];
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
				
				this.dodajPolozen(indeks, sifra, vrednostOcene, LocalDate.parse(datum, formatter));
				
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
	 * Metoda koja postavlja vrednosti niza ocena na vrednosti ocena iz liste ocena
	 * i koristi <code>XStream</code> biblioteku da serijalizuje ocene iz niza ocena u datoteku
	 * koja se nalazi na prosleđenoj putanji.
	 * 
	 * @param putanja putanja datoteke u koju se serijalizuju ocene
	 * @throws IOException
	 */
	public void XstreamSerialization(String putanja) throws IOException {
		
		niz = new Ocena[ocene.size()];
		
		for (int i = 0; i < ocene.size(); i++) {
			niz[i] = ocene.get(i);
		}
		
		File f = new File(putanja);
		OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
		try {

			XStream xs = new XStream();
			// Naziv tag-a u xml umesto punog naziva klase.
			xs.alias("ocena", Ocena.class);

			// Serijalizacija u xml.
			xs.toXML(niz, os); 
			
		} finally {
			os.close();
		}
	}
	
	/**
	 * Metoda koja poziva inicijalizaciju liste ocena i pomoću <code>XStream</code> 
	 * biblioteke deserijalizuje ocene iz datoteke koja se nalazi na prosleđenoj putanji.
	 * Na kraju se poziva metoda <code>dodajPolozen</code> kojoj se prosleđuju sve učitane ocene iz
	 * datoteke na prosleđenoj putanji.
	 * 
	 * @param putanja putanja datoteke iz koje se deserijalizuju ocene
	 * @throws IOException 
	 */
	public void XstreamDeserialization(String putanja) throws IOException {
		
		init();
		
		File f = new File(putanja);
		InputStream os = new BufferedInputStream(new FileInputStream(f));
		try {

			XStream xs = new XStream();
			xs.alias("ocena", Ocena.class);

			Ocena[] ucitaneOcene = (Ocena[]) xs.fromXML(os);
			
			for(Ocena o: ucitaneOcene) {
				this.dodajPolozen(o.getStudent().getBrIndeksa(), o.getPredmet().getSifra(), o.getVrednostOcene(),
						o.getDatumPolaganja());
			}



		} finally {
			os.close();
		}
		
		
		
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
	
	/**
	 * Uklanja sve ocene iz liste ocena za studenta sa prosleđenim indeksom.
	 * 
	 * @param indeks indeks studenta
	 */
	public void obrisiOcene(String indeks) {
		for (Ocena o : ocene) {
			if (o.getStudent().getBrIndeksa().equals(indeks)) {
				ocene.remove(o);
			}
		}
	}
	
	
	/**
	 * Metoda koja prolazi kroz listu svih studenata i ako nađe studenta čiji je indeks isti
	 * kao prosleđeni, prolazi kroz listu svih predmeta. Ako uspe da pronađe predmet koji ima istu
	 * šifru kao prosleđena, traži taj predmet u listi položenih predmeta nađenog studenta. Ako postoji u njoj, 
	 * metoda završava sa radom. U suprotnom prosleđuje objekat nađenog studenta i predmetam kao i vrednost ocene i datun polaganja 
	 * metodi <code>dodajPolozen</code>.
	 *  
	 * @param indeks indeks studenta
	 * @param sifra šifra predmeta
	 * @param vrednostOcene brojčana vrednost ocene (od 6 do 10)
	 * @param datum datum kada je student sa prosleđenim indeksom položio predmet sa prosleđenom šifrom
	 */
	public void dodajPolozen(String indeks, String sifra, int vrednostOcene, LocalDate datum) {
		List<Student> studenti = StudentController.getInstance().getStudenti();
		for(int i = 0; i < studenti.size(); i++) {
			if(indeks.equals(studenti.get(i).getBrIndeksa())) {
				Student polozio = studenti.get(i);
				List<Predmet> predmeti = PredmetController.getInstance().getPredmeti();
				for (int j = 0; j < predmeti.size(); j++) {
					if(sifra.equals(predmeti.get(j).getSifra())) {
						for(Ocena o: polozio.getSpisakPolozenih()) {
							if(o.getPredmet().getSifra().equals(sifra)) {
								return;
							}
						}
						Predmet polozenPredmet = predmeti.get(j);
						dodajOcenu(polozio, polozenPredmet, vrednostOcene, datum);
						break;
					}
				}
			}
		}
	}

}
