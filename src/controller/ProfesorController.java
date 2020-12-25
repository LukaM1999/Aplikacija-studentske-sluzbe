package controller;

import java.util.List;

import model.BazaProfesora;
import model.Profesor;
import view.MainFrame;

//Koriscen materijal sa vezbi

public class ProfesorController {

	private static ProfesorController instance = null;
		
		public static ProfesorController getInstance() {
			if (instance == null) {
				instance = new ProfesorController();
			}
			return instance;
		}
		
		private ProfesorController() {}
		
		public void dodajProfesora(Profesor p) {
			BazaProfesora.getInstance().dodajProfesora(p);
			MainFrame.getInstance().azurirajProfesore("DODAT", -1);
		}
		
		public void izmeniProfesora(int row) {
			if (row < 0) {
				return;
			}
			MainFrame.getInstance().izmeniProfesora(row);
		}
		
		public void izbrisiProfesora(int rowSelectedIndex) {
	    	if (rowSelectedIndex < 0) {
				return;
			}
	    	
	    	Profesor p = BazaProfesora.getInstance().getRow(rowSelectedIndex);
			BazaProfesora.getInstance().izbrisiProfesora(p.getBrLicneKarte());
			MainFrame.getInstance().azurirajProfesore("UKLONJEN", rowSelectedIndex);
	    }
		
		public List<Profesor> getProfesori(){
			return BazaProfesora.getInstance().getProfesori();
		}
		
		public Profesor getProfesor(int row) {
			return BazaProfesora.getInstance().getRow(row);
		}
}
