package main;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import view.MainFrame;

/**
 * Main klasa.
 * 
 * @author Mihajlo Kisić
 *
 */
public class Main {

	/**
	 * Main metoda koja čini vidljivim glavni prozor studentske službe. 
	 * 
	 * @param args argumenti komandne linije
	 */
	public static void main(String[] args) {
		
		JFrame frame = MainFrame.getInstance();
		
		ImageIcon icon = new ImageIcon("images"+ File.separator + "1.png");
		frame.setIconImage(icon.getImage());
		
		frame.setVisible(true);
		

	}
}

