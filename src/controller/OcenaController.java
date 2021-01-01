package controller;

import java.util.List;

import model.BazaOcena;
import model.Ocena;
import model.Student;

public class OcenaController {
	
	private static OcenaController instance = null;
	
	public static OcenaController getInstance() {
		if (instance == null) {
			instance = new OcenaController();
		}
		return instance;
	}
	
	private OcenaController() {}
	
	public void dodajOcenu(Ocena o) {
		BazaOcena.getInstance().dodajOcenu(o);
	}
	
	public void upisiOcenu(Student s) {
		List<Ocena> ocene = BazaOcena.getInstance().getOcene();
		for (Ocena o : ocene) {
			if (o.getStudent().getBrIndeksa().equals(s.getBrIndeksa())) {
				s.getSpisakPolozenih().add(o);
			}
		}
		
	}
	
	public void ponistiOcenu(int rowSelectedIndex) {
    	if (rowSelectedIndex < 0) {
			return;
		}
    	
    	Ocena o = BazaOcena.getInstance().getRow(rowSelectedIndex);
		BazaOcena.getInstance().ponistiOcenu(o.getStudent().getBrIndeksa(), o.getPredmet().getSifra());
    }
	
	public void obrisiOcene(String indeks) {
		BazaOcena.getInstance().obrisiOcene(indeks);
	}
	
	public List<Ocena> getOcene(){
		return BazaOcena.getInstance().getOcene();
	}
	
	public Ocena getOcena(int row) {
		return BazaOcena.getInstance().getRow(row);
	}
}
