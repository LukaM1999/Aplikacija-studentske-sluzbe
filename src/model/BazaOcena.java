package model;

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

public class BazaOcena implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -532190613446495075L;
	
	private static BazaOcena instance = null;

	public static BazaOcena getInstance() {
		if (instance == null) {
			instance = new BazaOcena();
		}
		return instance;
	}

	private List<Ocena> ocene;
	private List<String> kolone;
	private Ocena[] niz;
	
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

	private void init() {
		this.ocene = new ArrayList<Ocena>();
	}

	public List<Ocena> getOcene() {
		return this.ocene;
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
			return ocena.getDatumPolaganja().format(DateTimeFormatter.ofPattern("dd.MM.yyyy."));
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
	
	public void dodajOcenu(Student s, Predmet p, int vrednostOcene, LocalDate datumPolaganja) {
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
	
	public void obrisiOcene(String indeks) {
		for (Ocena o : ocene) {
			if (o.getStudent().getBrIndeksa().equals(indeks)) {
				ocene.remove(o);
			}
		}
	}
	
	
	
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
