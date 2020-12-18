/**
 * 
*/
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


//Koriscen materijal sa vezbi
public class MainFrame extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6391879538928021934L;

	public MainFrame() {
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		//postavljam dimenzije prozora i centriramo ga
		int sirina =screenWidth * 3/4;
		int duzina =screenHeight * 3/4;
		setSize(sirina, duzina);

		setTitle("Studentska služba");
		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		panel.setBackground(Color.WHITE);
		this.add(panel);
		
		JMenuBar menuBar = new MyMenuBar();
		
		setJMenuBar(menuBar);
		menuBar.setBackground(Color.WHITE);
		
		Toolbar toolbar = new Toolbar();
		add(toolbar, BorderLayout.NORTH);
		
		MyStatusBar statusBar = new MyStatusBar();
		add(statusBar.getStatusBar(),BorderLayout.SOUTH);
		statusBar.createStatusBar();
		
		//tabovi
		JTabbedPane tabs = new JTabbedPane();
		tabs.setBackground(Color.LIGHT_GRAY);
		
		TableStudent student = new TableStudent();
		TableProfesor profesor = new TableProfesor();
		TablePredmet predmet = new TablePredmet();
		
		tabs.addTab("Student", student);
		tabs.addTab("Profesor", profesor);
		tabs.addTab("Predmet", predmet);
		add(tabs, BorderLayout.CENTER);
		
		
	}
}

