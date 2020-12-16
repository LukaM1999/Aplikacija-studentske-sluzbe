package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import view.MainFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame frame = new MainFrame();
		
		ImageIcon icon = new ImageIcon("images/1.png");
		frame.setIconImage(icon.getImage());
		
		frame.setVisible(true);
		

	}
}

