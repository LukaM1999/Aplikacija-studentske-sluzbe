package model;


import java.util.ArrayList;
import java.util.List;


public class BazaPolozenihIspita {
	
	private static BazaPolozenihIspita instance = null;

	public static BazaPolozenihIspita getInstance() {
		if (instance == null) {
			instance = new BazaPolozenihIspita();
		}
		return instance;
	}


	private List<Ocena> ocene;
	private List<String> kolone;
		
	private BazaPolozenihIspita() {
		
		init();

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
			return ocena.getDatumPolaganja();
		default:
			return null;
		}
	}

	
	public void initSpisakPolozenih(Student s) {
		this.ocene = s.getSpisakPolozenih();
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

}
