package model;

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
import model.Predmet.Semestar;


public class BazaPredmeta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1599877952191814692L;
	
	private static BazaPredmeta instance = null;

	public static BazaPredmeta getInstance() {
		if (instance == null) {
			instance = new BazaPredmeta();
		}
		return instance;
	}

	
	private List<Predmet> predmeti;
	private List<String> kolone;
	private Predmet[] niz;
	
	private BazaPredmeta() {
		
		//deserijalizacija("deserijalizacija" + File.separator + "predmeti.txt");
		
		try {
			this.XstreamDeserialization("deserijalizacija" + File.separator + "predmeti.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.kolone = new ArrayList<String>();
		this.kolone.add("Šifra");
		this.kolone.add("Naziv");
		this.kolone.add("ESPB");
		this.kolone.add("Godina studija");
		this.kolone.add("Semestar");
	}
	
	private void init() {
		this.predmeti = new ArrayList<Predmet>();
	}
	
	
	public List<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
	}
	
	public int getColumnCount() {
		return 5;
	}
	
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}
	
	public Predmet getRow(int rowIndex) {
		return this.predmeti.get(rowIndex);
	}
	
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
				if(kolone[2].equals("Zimski")) {
					semestar = Semestar.Zimski;
				}
				else {
					semestar = Semestar.Letnji;
				}
				godina =Integer.parseInt(kolone[3]);
				espb = Integer.parseInt(kolone[4]);
				if(!kolone[5].equals("null")) {
					licna = kolone[5];
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

	
	public void dodajPredmet(String sifra, String naziv, int ESPB, int godinaStudija, Semestar semestar, Profesor p) {
		this.predmeti.add(new Predmet(sifra, naziv, semestar, godinaStudija, ESPB, p));
	}
	
	public void dodajPredmet(Predmet p) {
		this.predmeti.add(new Predmet(p));
	}
	
	public void izbrisiPredmet(String id) {
		for (Predmet p : predmeti) {
			if (p.getSifra() == id) {
				predmeti.remove(p);
				break;
			}
		}
	}
	
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
