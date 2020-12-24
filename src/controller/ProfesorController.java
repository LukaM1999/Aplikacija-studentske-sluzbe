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
		
		public List<Profesor> getProfesori(){
			return BazaProfesora.getInstance().getProfesori();
		}
}
