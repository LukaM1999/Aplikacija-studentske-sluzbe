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
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import controller.OcenaController;
import controller.ProfesorController;
import model.entiteti.Ocena;
import model.entiteti.Predmet;
import model.entiteti.Profesor;
import model.entiteti.Predmet.Semestar;

/**
 * Klasa koja predstavlja bazu svih predmeta.
 * 
 * @author Mihajlo Kisić
 *
 */
public class BazaPredmeta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1599877952191814692L;
	
	/**
	 * Instanca baze predmeta.
	 */
	private static BazaPredmeta instance = null;

	/**
	 * Dobavlja instancu baze predmeta po Singleton šablonu.
	 * 
	 * @return instanca baze predmeta
	 */
	public static BazaPredmeta getInstance() {
		if (instance == null) {
			instance = new BazaPredmeta();
		}
		return instance;
	}

	/**
	 * Lista svih predmeta.
	 */
	private List<Predmet> predmeti;
	
	/**
	 * Lista kolona tabele predmeta.
	 */
	private List<String> kolone;
	
	/**
	 * Niz predmeta korišćen pri <code>XStream</code> serijalizaciji.
	 */
	private Predmet[] niz;
	
	/**
	 * Poziva metodu <code>XstreamDeserialization</code> sa prosleđenom putanjom
	 * datoteke sa svim predmetima i dodaje kolone sa predefinisanim nazivima koji se mogu videti
	 * u zaglavlju tabele predmeta.
	 */
	private BazaPredmeta() {		
		
		
		try {
			this.XstreamDeserialization("deserijalizacija" + File.separator + "predmeti.xml");
		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		this.kolone = new ArrayList<String>();
		this.kolone.add("Šifra");
		this.kolone.add("Naziv");
		this.kolone.add("ESPB");
		this.kolone.add("Godina studija");
		this.kolone.add("Semestar");
	}
	
	/**
	 * Inicijalizuje listu predmeta praznom listom.
	 */
	private void init() {
		this.predmeti = new ArrayList<Predmet>();
	}
	
	/**
	 * Dobavlja listu predmeta.
	 * 
	 * @return lista predmeta
	 */
	public List<Predmet> getPredmeti() {
		return predmeti;
	}

	/**
	 * Postavlja polje liste predmeta na prosleđenu listu predmeta.
	 * 
	 * @param predmeti lista predmeta
	 */
	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
	}
	
	/**
	 * Dobavlja broj kolona tabele predmeta.
	 * 
	 * @return predefinisan broj kolona tabele predmeta
	 */
	public int getColumnCount() {
		return 5;
	}
	
	/**
	 * Vraća naziv kolone tabele predmeta čiji indeks je prosleđen.
	 * 
	 * @param index indeks kolone tabele predmeta
	 * @return naziv kolone iz liste kolona sa prosleđenim indeksom
	 */
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}
	
	/**
	 * Dobavlja prosleđeni red iz liste predmeta odnosno predmet koji
	 * se u listi nalazi na indeksu koji je prosleđen.
	 * 
	 * @param rowIndex indeks reda liste predmeta
	 * @return predmet iz liste predmeta na prosleđenom indeksu
	 */
	public Predmet getRow(int rowIndex) {
		return this.predmeti.get(rowIndex);
	}
	
	/**
	 * Dobavlja vrednost koja se nalazi na prosleđenom indeksu reda i kolone u tabeli
	 * predmeta u vidu <code>String</code>-a.
	 * 
	 * @param row indeks reda u kom se nalazi željena vrednost
	 * @param column indeks kolone u kojoj se nalazi željena vrednost
	 * @return vrednost koja se nalazi u prosleđenoj koloni i redu
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
	 * prosleđuje u metodu <code>dodajPredmet</code>. Takođe poziva metodu 
	 * <code>dodajPredajePredmet</code> tako što joj prosledi trenutno deserijalizovan
	 * predmet ako pronađe profesora sa istim brojem lične karte kao onaj što je pronađen
	 * deserijalizacijom.
	 * 
	 * @param putanja putanja datoteke iz koje se vrši deserijalizacija
	 */
	public void deserijalizacija(String putanja) {
		String sifra;
		String naziv;
		Semestar semestar;
		int godina;
		int espb;
		String licna = null;
		Profesor prof = null;

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
				sifra = kolone[0];
				naziv = kolone[1];
				godina =Integer.parseInt(kolone[2]);
				espb = Integer.parseInt(kolone[3]);
				if(kolone[4] != null) {
					licna = kolone[4];
				}
				if(kolone[5].equals("Zimski")) {
					semestar = Semestar.Zimski;
				}
				else {
					semestar = Semestar.Letnji;
				}
				
				for(Profesor p: ProfesorController.getInstance().getProfesori()) {
					if(licna.equals(p.getBrLicneKarte())) {
						prof = p;
						break;
					}
				}
				
				dodajPredmet(sifra, naziv, espb, godina, semestar, prof);
				
				for(Profesor p: ProfesorController.getInstance().getProfesori()) {
					if(licna.equals(p.getBrLicneKarte())) {
						prof = p;
						prof.dodajPredajePredmet(this.predmeti.get(predmeti.size()-1));
						break;
					}
				}
				
				prof = null;

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
	 * Metoda koja postavlja vrednosti niza predmeta na vrednosti predmeta iz liste predmeta
	 * i koristi <code>XStream</code> biblioteku da serijalizuje predmete iz niza predmeta u datoteku
	 * koja se nalazi na prosleđenoj putanji.
	 * 
	 * @param putanja putanja datoteke u koju se serijalizuju predmeti
	 * @throws IOException
	 */
	public void XstreamSerialization(String putanja) throws IOException {
		
		niz = new Predmet[predmeti.size()];
		
		for (int i = 0; i < predmeti.size(); i++) {
			niz[i] = predmeti.get(i);
		}
		
		File f = new File(putanja);
		OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
		try {

			XStream xs = new XStream();
			// Naziv tag-a u xml umesto punog naziva klase.
			xs.alias("predmet", Predmet.class);

			// Serijalizacija u xml.
			xs.toXML(niz, os); 
			
		} finally {
			os.close();
		}
	}
	
	/**
	 * Metoda koja poziva inicijalizaciju liste predmeta i pomoću <code>XStream</code> 
	 * biblioteke deserijalizuje predmete iz datoteke koja se nalazi na prosleđenoj putanji.
	 * Na kraju dodaje sve deserijalizovane predmete u listu predmeta koja predstavlja polje ove klase.
	 * 
	 * @param putanja putanja datoteke iz koje se deserijalizuju predmeti
	 * @throws IOException 
	 */
	public void XstreamDeserialization(String putanja) throws IOException {

		init();

		File f = new File(putanja);
		InputStream os = new BufferedInputStream(new FileInputStream(f));
		try {

			XStream xs = new XStream();
			xs.alias("predmet", Predmet.class);

			Predmet[] ucitaniPredmeti = (Predmet[]) xs.fromXML(os);

			for (Predmet predmet : ucitaniPredmeti) {
				predmeti.add(predmet);
			}

		} finally {
			os.close();
		}
	}

	/**
	 * Pravi novi predmet pomoću prosleđenih parametara i dodaje ga u listu predmeta.
	 * 
	 * @param sifra šifra predmeta
	 * @param naziv naziv predmeta
	 * @param ESPB broj ESPB poena koliko vredi predmet
	 * @param godinaStudija godina studija na kojoj se predmet predaje
	 * @param semestar semstar u kom se predmet predaje
	 * @param p objekat profesora koji predaje ovaj predmet
	 */
	public void dodajPredmet(String sifra, String naziv, int ESPB, int godinaStudija, Semestar semestar, Profesor p) {
		this.predmeti.add(new Predmet(sifra, naziv, semestar, godinaStudija, ESPB, p));
	}
	
	/**
	 * Pravi novi predmet pomoću prosleđenog objekta predmeta i dodaje ga u listu predmeta.
	 * 
	 * @param p objekat predmeta koji se dodaje u listu predmeta
	 */
	public void dodajPredmet(Predmet p) {
		this.predmeti.add(new Predmet(p));
	}
	
	/**
	 * Uklanja predmet iz liste predmeta ako mu je šifra ista kao prosleđena.
	 * 
	 * @param id šifra predmeta
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
	 * Metoda koja prolazi kroz listu svih ocena i ako pronađe ocenu čiji predmet ima istu
	 * šifru kao prosleđena, poziva metodu <code>dodajPolozili</code> tog predmeta pri čemu joj prosleđuje
	 * objekat studenta iz ocene sa nađenim predmetom.
	 * 
	 * @param sifra šifra predmeta kome hoćemo da dodamo studenta koji ga je položio
	 */
	public void dodajPolozili(String sifra) {
		List<Ocena> ocene = OcenaController.getInstance().getOcene();
		
		for(int i = 0; i < ocene.size(); i++) {	
			if(sifra.equals(ocene.get(i).getPredmet().getSifra())) {
				Predmet polozenPredmet = ocene.get(i).getPredmet();
				polozenPredmet.dodajPolozili(ocene.get(i).getStudent());
			}
		}
	}
	
	
	
}
