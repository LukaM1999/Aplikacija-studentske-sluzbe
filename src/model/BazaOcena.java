package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import controller.PredmetController;
import controller.StudentController;

public class BazaOcena {

	private static BazaOcena instance = null;

	public static BazaOcena getInstance() {
		if (instance == null) {
			instance = new BazaOcena();
		}
		return instance;
	}

	private List<Ocena> ocene;
	private List<String> kolone;
	
	//private List<Ocena> spisakPolozenih = new ArrayList<Ocena>();

	private BazaOcena() {

		deserijalizacija("deserijalizacija" + File.separator + "ocene.txt");

		this.kolone = new ArrayList<String>();
		this.kolone.add("Šifra predmeta");
		this.kolone.add("Naziv predmeta");
		this.kolone.add("ESPB");
		this.kolone.add("Ocena");
		this.kolone.add("Datum");
	}

	private void init() {
		this.ocene = new ArrayList<Ocena>();
		/*
		 * this.ocene.add(new Ocena( new Student("Pera", "Perić", "3.7.1998.",
		 * "Cara Lazara 20, Novi Sad", "066754498", "peraperic@gmail.com",
		 * "in-123-2017", 2017, 4, Status.B), new Predmet("E256", "Fizika", 9, 1,
		 * Semestar.Letnji), 9, "8.7.2018.")); this.ocene.add(new Ocena( new
		 * Student("Gorana", "Papov", "14.2.1999.", "Kralja Aleksandra 4, Novi Sad",
		 * "066043781", "gpapov@gmail.com", "sw-12-2016", 2016, 3, Status.S), new
		 * Predmet("E234", "Programski prevodioci", 4, 3, Semestar.Zimski), 7,
		 * "27.1.2020."));
		 * 
		 * 
		 * Student s1 = new Student("Pera", "Perić", "3.7.1998.",
		 * "Cara Lazara 20, Novi Sad", "066754498", "peraperic@gmail.com",
		 * "in-123-2017", 2017, 4, Status.B); Student s2 = new Student("Gorana",
		 * "Papov", "14.2.1999.", "Kralja Aleksandra 4, Novi Sad", "066043781",
		 * "gpapov@gmail.com", "sw-12-2016", 2016, 3, Status.S);
		 * s1.getSpisakPolozenih().add(ocene.get(0));
		 * System.out.println(s1.getSpisakPolozenih().get(0).getStudent().getBrIndeksa()
		 * ); s2.getSpisakPolozenih().add(ocene.get(1));
		 */
	}

	public List<Ocena> getOcene() {
		return ocene;
	}

	public void setOcene(List<Ocena> ocene) {
		this.ocene = ocene;
	}

	public int getColumnCount() {
		return 5;
	}

	public String getColumnName(int index) {
		return this.kolone.get(index);
	}

	public Ocena getRow(int rowIndex) {
		return this.ocene.get(rowIndex);
	}

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
			return ocena.getDatumPolaganja();
		default:
			return null;
		}
	}

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
			// TODO Auto-generated catch block
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
				
				dodajPolozen(indeks, sifra, vrednostOcene, datum);		
				
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
	
	public void dodajOcenu(Student s, Predmet p, int vrednostOcene, String datumPolaganja) {
		this.ocene.add(new Ocena(s, p, vrednostOcene, datumPolaganja));
	}

	public void dodajOcenu(Ocena o) {
		this.ocene.add(new Ocena(o));
	}

	public void ponistiOcenu(String indeks, String sifra) {
		for (Ocena o : ocene) {
			if (o.getPredmet().getSifra().equals(sifra) && o.getStudent().getBrIndeksa().equals(indeks)) {
				ocene.remove(o);
				break;
			}
		}
	}
	
	public void dodajPolozen(String indeks, String sifra, int vrednostOcene, String datum) {
		List<Student> studenti = StudentController.getInstance().getStudenti();
		for(int i = 0; i < studenti.size(); i++) {
			if(indeks.equals(studenti.get(i).getBrIndeksa())) {
				Student polozio = studenti.get(i);
				List<Predmet> predmeti = PredmetController.getInstance().getPredmeti();
				for (int j = 0; j < predmeti.size(); j++) {
					if(sifra.equals(predmeti.get(j).getSifra())) {
						Predmet polozenPredmet = predmeti.get(j);
						dodajOcenu(polozio, polozenPredmet, vrednostOcene, datum);
						polozio.dodajPolozen(polozio, polozenPredmet, vrednostOcene, datum);
						
					}
				}
			}
		}
	}

}
