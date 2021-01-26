package model.baze;

import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
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

import model.entiteti.Profesor;
import model.entiteti.Profesor.Titula;
import model.entiteti.Profesor.Zvanje;


/**
 * Klasa koja predstavlja bazu svih profesora.
 * 
 * @author Mihajlo Kisić
 *
 */
public class BazaProfesora implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3572422462341266636L;

	/**
	 * Instanca baze profesora.
	 */
	private static BazaProfesora instance = null;

	/**
	 * Dobavlja instancu baze profesora po Singleton šablonu.
	 * 
	 * @return instanca baze profesora
	 */
	public static BazaProfesora getInstance() {
		if (instance == null) {
			instance = new BazaProfesora();
		}
		return instance;
	}

	/**
	 * Lista svih profesora.
	 */
	private List<Profesor> profesori;
	
	/**
	 * Lista kolona tabele profesora.
	 */
	private List<String> kolone;
	
	/**
	 * Niz profesora korišćen pri <code>XStream</code> serijalizaciji.
	 */
	private Profesor[] niz;
	
	/**
	 * Poziva metodu <code>XstreamDeserialization</code> sa prosleđenom putanjom
	 * datoteke sa svim profesorima i dodaje kolone sa predefinisanim nazivima koji se mogu videti
	 * u zaglavlju tabele profesora.
	 */
	private BazaProfesora() {
				
		
		try {
			this.XstreamDeserialization("deserijalizacija" + File.separator + "profesori.xml");
		} catch (IOException e) { // TODO Auto-generated catchblock
			e.printStackTrace();
		}
		  
		 

		this.kolone = new ArrayList<String>();
		this.kolone.add("Ime");
		this.kolone.add("Prezime");
		this.kolone.add("Titula");
		this.kolone.add("Zvanje");
	}

	/**
	 * Inicijalizuje listu profesora praznom listom.
	 */
	private void init() {
		this.profesori = new ArrayList<Profesor>();
	}

	/**
	 * Dobavlja listu profesora.
	 * 
	 * @return lista profesora
	 */
	public List<Profesor> getProfesori() {
		return profesori;
	}

	/**
	 * Postavlja polje liste profesora na prosleđenu listu profesora.
	 * 
	 * @param profesori lista profesora
	 */
	public void setProfesori(List<Profesor> profesori) {
		this.profesori = profesori;
	}

	/**
	 * Dobavlja broj kolona tabele profesora.
	 * 
	 * @return predefinisan broj kolona tabele profesora
	 */
	public int getColumnCount() {
		return 4;
	}

	/**
	 * Vraća naziv kolone tabele profesora čiji indeks je prosleđen.
	 * 
	 * @param index indeks kolone tabele profesora
	 * @return naziv kolone iz liste kolona sa prosleđenim indeksom
	 */
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}

	/**
	 * Dobavlja prosleđeni red iz liste profesora odnosno profesora koji
	 * se u listi nalazi na indeksu koji je prosleđen.
	 * 
	 * @param rowIndex indeks reda liste profesora
	 * @return profesor iz liste profesora na prosleđenom indeksu
	 */
	public Profesor getRow(int rowIndex) {
		return this.profesori.get(rowIndex);
	}

	/**
	 * Dobavlja vrednost koja se nalazi na prosleđenom indeksu reda i kolone u tabeli
	 * profesora u vidu <code>String</code>-a.
	 * 
	 * @param row indeks reda u kom se nalazi željena vrednost
	 * @param column indeks kolone u kojoj se nalazi željena vrednost
	 * @return vrednost koja se nalazi u prosleđenoj koloni i redu
	 */
	public String getValueAt(int row, int column) {
		Profesor profesor = this.profesori.get(row);
		switch (column) {
		case 0:
			return profesor.getIme();
		case 1:
			return profesor.getPrezime();
		case 2:
			if (String.valueOf(profesor.getTitula()).equals("BSc")) {
				return "BSc";
			}
			else if (String.valueOf(profesor.getTitula()).equals("MSc")) {
				return "MSc";
			}	
			else if (String.valueOf(profesor.getTitula()).equals("mr")) {
				return "mr";
			}
			else if (String.valueOf(profesor.getTitula()).equals("dr")) {
				return "dr";
			}
			else if (String.valueOf(profesor.getTitula()).equals("prof_dr")) {
				return "prof. dr";
			}
		case 3:
			if (String.valueOf(profesor.getZvanje()).equals("saradnik_u_nastavi")) {
				return "Saradnik u nastavi";
			}
			else if (String.valueOf(profesor.getZvanje()).equals("asistent")) {
				return "Asistent";
			}
			else if (String.valueOf(profesor.getZvanje()).equals("asistent_sa_doktoratom")) {
				return "Asistent sa doktoratom";
			}
			else if (String.valueOf(profesor.getZvanje()).equals("docent")) {
				return "Docent";
			}
			else if (String.valueOf(profesor.getZvanje()).equals("vanredni_profesor")) {
				return "Vanredni profesor";
			}
			else if (String.valueOf(profesor.getZvanje()).equals("redovni_profesor")) {
				return "Redovni profesor";
			}
			else if (String.valueOf(profesor.getZvanje()).equals("profesor_emeritus")) {
				return "Profesor emeritus";
			}
		default:
			return null;
		}
	}

	/**
	 * Metoda koja vrši deserijalizaciju datoteke na prosleđenoj putanji.
	 * Razdvaja slogove po predefinisanom karakteru i dobijene vrednosti
	 * prosleđuje u metodu <code>dodajProfesora</code>.
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
		String kancelarija;
		String licna;
		Titula titula;
		Zvanje zvanje;

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
				licna = kolone[0];
				ime = kolone[1];
				prezime = kolone[2];
				datum = kolone[3];
				adresa = kolone[4];
				telefon = kolone[5];
				email = kolone[6];
				kancelarija = kolone[7];
				if (kolone[8].equals("BSc")) {
					titula = Titula.BSc;
				} else if (kolone[8].equals("MSc")) {
					titula = Titula.MSc;
				} else if (kolone[8].equals("mr")) {
					titula = Titula.mr;
				} else if (kolone[8].equals("dr")) {
					titula = Titula.dr;
				} else 
					titula = Titula.prof_dr;
				
				if (kolone[9].equals("Saradnik u nastavi")) {
					zvanje = Zvanje.saradnik_u_nastavi;
				} else if (kolone[9].equals("Asistent")) {
					zvanje = Zvanje.asistent;
				} else if (kolone[9].equals("Asistent sa doktoratom")) {
					zvanje = Zvanje.asistent_sa_doktoratom;
				} else if (kolone[9].equals("Docent")) {
					zvanje = Zvanje.docent;
				} else if (kolone[9].equals("Vanredni profesor")) {
					zvanje = Zvanje.vanredni_profesor;
				} else if (kolone[9].equals("Redovni profesor")) {
					zvanje = Zvanje.redovni_profesor;
				} else 
					zvanje = Zvanje.profesor_emeritus;


				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");

				dodajProfesora(ime, prezime, LocalDate.parse(datum, formatter), adresa, telefon, email, kancelarija, licna, titula, zvanje);

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

	/**
	 * Metoda koja postavlja vrednosti niza profesora na vrednosti profesora iz liste profesora
	 * i koristi <code>XStream</code> biblioteku da serijalizuje profesora iz niza profesora u datoteku
	 * koja se nalazi na prosleđenoj putanji.
	 * 
	 * @param putanja putanja datoteke u koju se serijalizuju profesori
	 * @throws IOException
	 */
	public void XstreamSerialization(String putanja) throws IOException {
			
			niz = new Profesor[profesori.size()];
			
			for (int i = 0; i < profesori.size(); i++) {
				niz[i] = profesori.get(i);
			}
			
			File f = new File(putanja);
			OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
			try {
	
				XStream xs = new XStream();
				// Naziv tag-a u xml umesto punog naziva klase.
				xs.alias("profesor", Profesor.class);
	
				// Serijalizacija u xml.
				xs.toXML(niz, os); 
	
	
	
			} finally {
				os.close();
			}
			
	}
	
	/**
	 * Metoda koja poziva inicijalizaciju liste profesora i pomoću <code>XStream</code> 
	 * biblioteke deserijalizuje profesora iz datoteke koja se nalazi na prosleđenoj putanji.
	 * Na kraju dodaje sve deserijalizovane profesora u listu profesora koja predstavlja polje ove klase.
	 * 
	 * @param putanja putanja datoteke iz koje se deserijalizuju profesori
	 * @throws IOException 
	 */
	public void XstreamDeserialization(String putanja) throws IOException {
		
		init();
		
		File f = new File(putanja);
		InputStream os = new BufferedInputStream(new FileInputStream(f));
		try {

			XStream xs = new XStream();
			xs.alias("profesor", Profesor.class);

			Profesor[] ucitaniProfesori = (Profesor[]) xs.fromXML(os);
			
			for(Profesor profesor: ucitaniProfesori) {
				profesori.add(profesor);
			}



		} finally {
			os.close();
		}
		
		
		
	}
	
	/**
	 * Pravi objekat novog profesora pomoću prosleđenih parametara i dodaje ga u listu profesora.
	 * 
	 * @param ime ime profesora
	 * @param prezime prezime profesora
	 * @param datum datum rođenja profesora
	 * @param adresa adresa stanovanja profesora
	 * @param telefon kontakt telefon profesora
	 * @param email email adresa profesora
	 * @param kancelarija adresa kancelarije profesora
	 * @param licna broj lične karte profesora
	 * @param titula titula profesora
	 * @param zvanje zvanje profesora
	 */
	public void dodajProfesora(String ime, String prezime, LocalDate datum, String adresa, String telefon, String email,
			String kancelarija, String licna, Titula titula, Zvanje zvanje) {
		this.profesori.add(new Profesor(ime, prezime, datum, adresa, telefon, email, kancelarija, licna, titula, zvanje));
	}

	/**
	 * Pravi novi objekat profesora pomoću prosleđenog objekta profesora i dodaje ga u listu profesora.
	 * 
	 * @param p objekat profesora koji se dodaje u listu profesora
	 */
	public void dodajProfesora(Profesor p) {
		this.profesori.add(new Profesor(p));
	}

	/**
	 * Uklanja profesora iz liste profesora ako mu je broj lične karte isti kao prosleđeni broj lične karte.
	 * 
	 * @param id broj lične karte profesora
	 */
	public void izbrisiProfesora(String id) {
		for (Profesor p : profesori) {
			if (p.getBrLicneKarte() == id) {
				profesori.remove(p);
				break;
			}
		}
	}

}
