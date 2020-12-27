package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	
	private BazaPredmeta() {
		
		deserijalizacija("deserijalizacija" + File.separator + "predmeti.txt");
		
		this.kolone = new ArrayList<String>();
		this.kolone.add("Šifra");
		this.kolone.add("Naziv");
		this.kolone.add("ESPB");
		this.kolone.add("Godina studija");
		this.kolone.add("Semestar");
	}
	
	private void init() {
		this.predmeti = new ArrayList<Predmet>();
		predmeti.add(new Predmet("E234", "Programski prevodioci", 4, 3, Semestar.Zimski));
		predmeti.add(new Predmet("E256", "Fizika", 9, 1, Semestar.Letnji));
		predmeti.add(new Predmet("E231", "Osnovi elektrotehnike", 9, 1, Semestar.Letnji));
		predmeti.add(new Predmet("E154", "Objektno programiranje", 8, 2, Semestar.Zimski));
		predmeti.add(new Predmet("E128", "Baze podataka 2", 8, 4, Semestar.Zimski));

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
				

				dodajPredmet(sifra, naziv, espb, godina, semestar);

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
	
	public void dodajPredmet(String sifra, String naziv, int ESPB, int godinaStudija, Semestar semestar) {
		this.predmeti.add(new Predmet(sifra, naziv, ESPB, godinaStudija, semestar));
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
}
