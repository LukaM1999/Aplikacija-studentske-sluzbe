package controller;

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
			// izmena modela
			BazaProfesora.getInstance().dodajProfesora(p);
			// azuriranje prikaza
			MainFrame.getInstance().azurirajProfesore("DODAT", -1);
		}
}
