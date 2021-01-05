package model;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import model.Profesor.Titula;
import model.Profesor.Zvanje;


public class BazaProfesora implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3572422462341266636L;

	private static BazaProfesora instance = null;

	public static BazaProfesora getInstance() {
		if (instance == null) {
			instance = new BazaProfesora();
		}
		return instance;
	}

	private List<Profesor> profesori;
	private List<String> kolone;
	private Profesor[] niz;
	
	private BazaProfesora() {
		
		deserijalizacija("deserijalizacija" + File.separator + "profesori.txt" );

		this.kolone = new ArrayList<String>();
		this.kolone.add("Ime");
		this.kolone.add("Prezime");
		this.kolone.add("Titula");
		this.kolone.add("Zvanje");
	}

	private void init() {
		this.profesori = new ArrayList<Profesor>();
	}

	public List<Profesor> getProfesori() {
		return profesori;
	}

	public void setProfesori(List<Profesor> profesori) {
		this.profesori = profesori;
	}

	public int getColumnCount() {
		return 4;
	}

	public String getColumnName(int index) {
		return this.kolone.get(index);
	}

	public Profesor getRow(int rowIndex) {
		return this.profesori.get(rowIndex);
	}

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
				ime = kolone[0];
				prezime = kolone[1];
				datum = kolone[2];
				adresa = kolone[3];
				telefon = kolone[4];
				email = kolone[5];
				kancelarija = kolone[6];
				licna = kolone[7];
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
	
	
	
	public void dodajProfesora(String ime, String prezime, LocalDate datum, String adresa, String telefon, String email,
			String kancelarija, String licna, Titula titula, Zvanje zvanje) {
		this.profesori.add(new Profesor(ime, prezime, datum, adresa, telefon, email, kancelarija, licna, titula, zvanje));
	}

	public void dodajProfesora(Profesor p) {
		this.profesori.add(new Profesor(p));
	}

	public void izbrisiProfesora(String id) {
		for (Profesor p : profesori) {
			if (p.getBrLicneKarte() == id) {
				profesori.remove(p);
				break;
			}
		}
	}

}
