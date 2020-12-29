package controller;

import java.util.List;

import model.BazaNepolozenihIspita;
import model.BazaOcena;
import model.BazaPolozenihIspita;
import model.Ocena;
import model.Student;

public class PolozeniIspitiController {
	
	private static PolozeniIspitiController instance = null;
	
	public static PolozeniIspitiController getInstance() {
		if (instance == null) {
			instance = new PolozeniIspitiController();
		}
		return instance;
	}
	
	private PolozeniIspitiController() {}
	
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
    	
    	Ocena o = BazaPolozenihIspita.getInstance().getRow(rowSelectedIndex);
		o.getStudent().ponistiOcenu(rowSelectedIndex);
		BazaPolozenihIspita.getInstance().ponistiOcenu(o.getStudent().getBrIndeksa(), o.getPredmet().getSifra());
		BazaNepolozenihIspita.getInstance().dodajNepolozen(o.getPredmet());
    }
	
	public void initSpisakPolozenih(Student s) {
		BazaPolozenihIspita.getInstance().initSpisakPolozenih(s);
	}
	
	public List<Ocena> getOcene(){
		return BazaPolozenihIspita.getInstance().getOcene();
	}
	
	public Ocena getOcena(int row) {
		return BazaPolozenihIspita.getInstance().getRow(row);
	}
}
